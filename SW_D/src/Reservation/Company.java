package Reservation;

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

}
