package reservation_package;

import java.time.*;
import java.util.*;

public class AnimalHospital extends Company {
	private String name;
	private String phone;
	private BusinessHours[] hours = new BusinessHours[7];
	private float rating;
	private String address;
	private String specialty;
	
	public AnimalHospital (String name, String phone, String Mon_openingHours, String Mon_closingTime, String Tue_openingHours,String Tue_closingTime,String Wed_openingHours,String Wed_closingTime,String Thu_openingHours,String Thu_closingTime,String Fri_openingHours,String Fri_closingTime,String Sat_openingHours,String Sat_closingTime,String Sun_openingHours,String Sun_closingTime, float rating, String address, String specialty) {
		super(name,phone,Mon_openingHours,Mon_closingTime,Tue_openingHours,Tue_closingTime,Wed_openingHours,Wed_closingTime,Thu_openingHours,Thu_closingTime,Fri_openingHours,Fri_closingTime,Sat_openingHours,Sat_closingTime,Sun_openingHours,Sun_closingTime,rating,address);
		this.specialty = specialty;
	}
	public AnimalHospital (List<String> attr) {
		super(attr);
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
		this.specialty = attr.get(18);
	}
//	public Boolean checkWithinBH(LocalDate date, LocalTime time) {
//		DayOfWeek dayOfWeek = date.getDayOfWeek();
//        int dayOfWeekNumber = dayOfWeek.getValue();
//        LocalTime[] times = this.hours[dayOfWeekNumber].getTime();
//        if (time.isBefore(times[1]) && time.isAfter(times[0])){
//        	return true;
//        }
//        else {
//        	return false;
//        }
// 
//	}

}
