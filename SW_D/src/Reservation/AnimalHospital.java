package Reservation;

import java.util.List;

public class AnimalHospital extends Company {
	String specialty;
	
	public AnimalHospital (String name, String phone, String Mon_openingHours, String Mon_closingTime, String Tue_openingHours,String Tue_closingTime,String Wed_openingHours,String Wed_closingTime,String Thu_openingHours,String Thu_closingTime,String Fri_openingHours,String Fri_closingTime,String Sat_openingHours,String Sat_closingTime,String Sun_openingHours,String Sun_closingTime, float rating, String address, String specialty) {
		super(name,phone,Mon_openingHours,Mon_closingTime,Tue_openingHours,Tue_closingTime,Wed_openingHours,Wed_closingTime,Thu_openingHours,Thu_closingTime,Fri_openingHours,Fri_closingTime,Sat_openingHours,Sat_closingTime,Sun_openingHours,Sun_closingTime,rating,address);
		this.specialty = specialty;
	}
	public AnimalHospital (List<String> attr) {
		super(attr);
		this.specialty = attr.get(-1);
	}

}
