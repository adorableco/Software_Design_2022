package Reservation;

import java.util.*;

public class Company {
	String name;
	String phone;
	BusinessHours[] hours = new BusinessHours[7];
	float rating;
	String address;
//	String specialty;
	
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

}
