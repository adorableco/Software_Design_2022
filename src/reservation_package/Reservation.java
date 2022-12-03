package reservation_package;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Reservation {
	private LocalDate date;
	private LocalTime time;
	private String service;
	private int State;
	private int cost;
	private String helper;
	private String Review;
	private int Petcode;
	private int Companycode;
	
	public Reservation() {
	}
	
	
	public Reservation(LocalDate Use_Day, LocalTime Use_Time, String Use_Service
			,int State, int Cost, String Review){
		this.date = Use_Day;
		this.time = Use_Time;
		this.service = Use_Service;
		this.State = State;
		this.cost = Cost;
		this.Review = Review;
	}

	
	public void setDate(LocalDate date){
		this.date=date;
	}
	public void setTime(LocalTime time){
		this.time=time;
	}
	public void setService(String service) {
		this.service=service;
	}
	public void setCost(int cost) {
		this.cost=cost;
	}
	
	public void setHelper(String helper) {
		this.helper=helper;
	}


	public String madeDate() {
		//date를 String으로 바꾸고 예약 정보를 String으로 합치기
		String s_date=this.date.format(DateTimeFormatter.ofPattern("yyyy MM dd"));
		String s_time=this.time.format(DateTimeFormatter.ofPattern("HH mm"));
		String dataString=String.format("%s %s %s %d", s_date,s_time,service,cost);
		return dataString;
	}
	
	public int Get_State() {
		return this.State;
	}
	public LocalDate Get_Use_Day() {
		return this.date;
	}
	public LocalTime Get_Use_Time() {
		return this.time;
	}
	public String Get_Use_Service() {
		return this.service;
	}
	public int Get_Cost() {
		return this.cost;
	}
	public String Get_Review() {
		return this.Review;
	}
	
	
}
