package reservation_package;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationPetGroomingSalon {
	private String name;
	private LocalDate date;
	private LocalTime time;
	
	public ReservationPetGroomingSalon() {
		
	}
	public ReservationPetGroomingSalon(String name, LocalDate date, LocalTime time) {
		this.name = name;
		this.date = date;
		this.time = time;
	}
	
	public LocalDate Get_Use_Day() {
		return this.date;
	}
	public LocalTime Get_Use_Time() {
		return this.time;
	}
	public String Get_Company_Name() {
		return this.name;
	}
	
	public void setDate(LocalDate date){
		this.date=date;
	}
	public void setTime(LocalTime time){
		this.time=time;
	}
	public void setCompanyName(String name){
		this.name=name;
	}
}

