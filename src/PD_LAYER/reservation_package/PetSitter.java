package PD_LAYER.reservation_package;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


public class PetSitter {
	private String name;
	private String phone;
	private BusinessHours[] hours = new BusinessHours[7];
	private String address;
	
	public PetSitter(String name, String phone, String Mon_openingHours, String Mon_closingTime, String Tue_openingHours,String Tue_closingTime,String Wed_openingHours,String Wed_closingTime,String Thu_openingHours,String Thu_closingTime,String Fri_openingHours,String Fri_closingTime,String Sat_openingHours,String Sat_closingTime,String Sun_openingHours,String Sun_closingTime,String address) {
		this.name = name;
		this.phone = phone;
		this.hours[0] = new BusinessHours(Mon_openingHours, Mon_closingTime);
		this.hours[1] = new BusinessHours(Tue_openingHours, Tue_closingTime);
		this.hours[2] = new BusinessHours(Wed_openingHours, Wed_closingTime);
		this.hours[3] = new BusinessHours(Thu_openingHours, Thu_closingTime);
		this.hours[4] = new BusinessHours(Fri_openingHours, Fri_closingTime);
		this.hours[5] = new BusinessHours(Sat_openingHours, Sat_closingTime);
		this.hours[6] = new BusinessHours(Sun_openingHours, Sun_closingTime);
		this.address = address;
	}
	public PetSitter(List<String> attr) {
		this.name = attr.get(0);
		this.phone = attr.get(1);
		this.hours[0] = new BusinessHours(attr.get(2), attr.get(3));
		this.hours[1] = new BusinessHours(attr.get(4), attr.get(5));
		this.hours[2] = new BusinessHours(attr.get(6), attr.get(7));
		this.hours[3] = new BusinessHours(attr.get(8), attr.get(9));
		this.hours[4] = new BusinessHours(attr.get(10), attr.get(11));
		this.hours[5] = new BusinessHours(attr.get(12), attr.get(13));
		this.hours[6] = new BusinessHours(attr.get(14), attr.get(15));
		this.address = attr.get(16);
	}
	
	public String getAttributeInString() throws IllegalArgumentException, IllegalAccessException {
		// need to implement. DB Connector에서 write하는데 쓰임.
		List<String> petsitterAttrList = new LinkedList<String>();
		Field[] attrs = this.getClass().getDeclaredFields();
		
		for(Field f : attrs) {
			f.setAccessible(true);
			if(f.getName() == null) {
				continue;
			}
			if(f.getName() == "hours") {
				BusinessHours[] hs = (BusinessHours[]) f.get(this);
				for(BusinessHours h : hs) {
					String[] times = h.getStringTime();
					petsitterAttrList.add(times[0]);
					petsitterAttrList.add(times[1]);
				}
					
			}
			else {
				petsitterAttrList.add(f.get(this).toString());
			}
		}
		return String.join(",", petsitterAttrList);
		
	}
	public String[] getAttributeInList() throws IllegalArgumentException, IllegalAccessException {
		// need to implement. DB Connector에서 write하는데 쓰임.
		
		Field[] attrs = this.getClass().getDeclaredFields();
//		String[] companyAttrList = new String[19];
		String[] petsitterAttrList = new String[5];
		int i = 0;
		for(Field f : attrs) {
			f.setAccessible(true);
			if(f.getName() == "hours") {
				BusinessHours[] hs = (BusinessHours[]) f.get(this);
				for(BusinessHours h : hs) {
					if(h == null) {
						continue;
					}
//					String[] times = h.getStringTime();
//					companyAttrList[i++] = times[0];
//					companyAttrList[i++] = times[1];
				}
					
			}
			else if(f.getName() == "rating"){
				petsitterAttrList[i++] = f.get(this).toString();
			}
			else {
				petsitterAttrList[i++] = f.get(this).toString();
			}
		}

		return petsitterAttrList;
		
	}
	
	public Boolean checkWithinBH(LocalDate date, LocalTime time) {
		DayOfWeek dayOfWeek = date.getDayOfWeek();
        int dayOfWeekNumber = dayOfWeek.getValue();
        LocalTime[] times = this.hours[dayOfWeekNumber].getTime();
        if (time.isBefore(times[1]) && time.isAfter(times[0])){
        	return true;
        }
        else {
        	return false;
        }
 
	}
	
	public String getAddress() {
		return this.address;
	}
	public String Get_Name() {
		return this.name;
	}
	public String Get_phon() {
		return this.phone;
	}
}
