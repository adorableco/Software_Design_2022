package reservation_package;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Reservation {
	private LocalDate date;
	private LocalTime start_time;
	private LocalTime finish_time;
	private String service;
	private int State;
	private int cost;
	private String helper;
	private String Review;
	private int Petcode;
	private int Companycode;
	
	public Reservation() {
	}
	
	
	public Reservation(LocalDate Use_Day, LocalTime Use_Start_Time, LocalTime Use_Finish_Time,String Use_Service
			,int State, int Cost, String Review, String helper){
		this.date = Use_Day;
		this.start_time = Use_Start_Time;
		this.finish_time=Use_Finish_Time;
		this.service = Use_Service;
		this.State = State;
		this.cost = Cost;
		this.Review = Review;
		this.helper = helper;
	}

	
	public void setDate(LocalDate date){
		this.date=date;
	}
	public void setStartTime(LocalTime time){
		this.start_time=time;
	}
	public void setFinishTime(LocalTime time){
		this.finish_time=time;
	}
	public void setService(String service) {
		this.service=service;
	}
	public void setCost(int cost) {
		this.cost=cost;
	}
	
	public void setHelper(String helper) {
		this.helper=helper;
		System.out.println("reservation : "+this.helper);
	}
	
	public int Get_State() {
		return this.State;
	}
	public LocalDate Get_Use_Day() {
		return this.date;
	}
	public LocalTime Get_Use_Start_Time() {
		return this.start_time;
	}
	public LocalTime Get_Use_Finish_Time() {
		return this.finish_time;
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
	public String Get_Helper() {
		return this.helper;
	}
	
	
}
