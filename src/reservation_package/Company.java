package reservation_package;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Company {
	private String name;
	private String phone;
	private BusinessHours[] hours = new BusinessHours[7];
	private float rating;
	private String address;
	
	public Company(String name, String phone, String Mon_openingHours, String Mon_closingTime, String Tue_openingHours,String Tue_closingTime,String Wed_openingHours,String Wed_closingTime,String Thu_openingHours,String Thu_closingTime,String Fri_openingHours,String Fri_closingTime,String Sat_openingHours,String Sat_closingTime,String Sun_openingHours,String Sun_closingTime, float rating, String address) {
		this.name = name;
		this.phone = phone;
		this.hours[0] = new BusinessHours(Mon_openingHours, Mon_closingTime);
		this.hours[1] = new BusinessHours(Tue_openingHours, Tue_closingTime);
		this.hours[2] = new BusinessHours(Wed_openingHours, Wed_closingTime);
		this.hours[3] = new BusinessHours(Thu_openingHours, Thu_closingTime);
		this.hours[4] = new BusinessHours(Fri_openingHours, Fri_closingTime);
		this.hours[5] = new BusinessHours(Sat_openingHours, Sat_closingTime);
		this.hours[6] = new BusinessHours(Sun_openingHours, Sun_closingTime);
		this.rating = rating;
		this.address = address;
	}
	public Company(List<String> attr) {
		this.name = attr.get(0);
		this.phone = attr.get(1);
		this.hours[0] = new BusinessHours(attr.get(2), attr.get(3));
		this.hours[1] = new BusinessHours(attr.get(4), attr.get(5));
		this.hours[2] = new BusinessHours(attr.get(6), attr.get(7));
		this.hours[3] = new BusinessHours(attr.get(8), attr.get(9));
		this.hours[4] = new BusinessHours(attr.get(10), attr.get(11));
		this.hours[5] = new BusinessHours(attr.get(12), attr.get(13));
		this.hours[6] = new BusinessHours(attr.get(14), attr.get(15));
		this.rating = Float.valueOf(attr.get(16));
		this.address = attr.get(17);
	}
	
	public String getAttributeInString() throws IllegalArgumentException, IllegalAccessException {
		// need to implement. DB Connector에서 write하는데 쓰임.
		List<String> companyAttrList = new LinkedList<String>();
		Field[] attrs = this.getClass().getDeclaredFields();
		
		for(Field f : attrs) {
			f.setAccessible(true);
			if(f.getName() == null) {
				continue;
			}
			if(f.getName() == "hours") {
				BusinessHours[] hs = (reservation_package.BusinessHours[]) f.get(this);
				for(BusinessHours h : hs) {
					String[] times = h.getStringTime();
					companyAttrList.add(times[0]);
					companyAttrList.add(times[1]);
				}
					
			}
			else {
				companyAttrList.add(f.get(this).toString());
			}
		}
		return String.join(",", companyAttrList);
		
	}
	public String[] getAttributeInList() throws IllegalArgumentException, IllegalAccessException {
		// need to implement. DB Connector에서 write하는데 쓰임.
		
		Field[] attrs = this.getClass().getDeclaredFields();
//		String[] companyAttrList = new String[19];
		String[] companyAttrList = new String[5];
		int i = 0;
		for(Field f : attrs) {
			f.setAccessible(true);
			if(f.getName() == "hours") {
				BusinessHours[] hs = (reservation_package.BusinessHours[]) f.get(this);
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
				companyAttrList[i++] = f.get(this).toString();
			}
			else {
				companyAttrList[i++] = f.get(this).toString();
			}
		}
//		for (String str : companyAttrList) {
//			System.out.println(str);
//		}
		return companyAttrList;
		
//		for(Field f : attrs) {
//			f.setAccessible(true);
//			if(f.getName() == null) {
//				continue;
//			}
//			if(f.getName() == "hours") {
//				BusinessHours[] hs = (Reservation.BusinessHours[]) f.get(this);
//				for(BusinessHours h : hs) {
//					String[] times = h.getStringTime();
//					companyAttrList.add(times[0]);
//					companyAttrList.add(times[1]);
//				}
//					
//			}
//			else {
//				companyAttrList.add(f.get(this).toString());
//			}
//		}
//		return (ArrayList<String>) companyAttrList;
		
	}
	
	public Boolean checkWithinBH(LocalDate date, LocalTime start_time,LocalTime finish_time) {
		DayOfWeek dayOfWeek = date.getDayOfWeek();
        int dayOfWeekNumber = dayOfWeek.getValue();
        LocalTime[] times = this.hours[dayOfWeekNumber].getTime();
        if (start_time.isBefore(times[1]) && start_time.isAfter(times[0])){
        	if(finish_time.isBefore(times[1])&&finish_time.isAfter(times[0]))
        		return true;
        	else
        		return false;
        }
        else {
        	return false;
        }
 
	}

}
