package reservation_package;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import database_package.PetSitterDBConnector;

public class Reservation {
	private LocalDate date;
	private LocalTime start_time;
	private LocalTime finish_time;
	private String service;
	private int State;
	private int cost;
	private String Review;
	private String Company;
	private PetSitter helper;
	

	PetSitterDBConnector PDB = new PetSitterDBConnector();
	public Reservation() {
	}
	
	
	public Reservation(LocalDate Use_Day, LocalTime Use_Start_Time, LocalTime Use_Finish_Time,String Use_Service
			,int State, int Cost, String Review, String Company, String helper){
		this.date = Use_Day;
		this.start_time = Use_Start_Time;
		this.finish_time=Use_Finish_Time;
		this.service = Use_Service;
		this.State = State;
		this.cost = Cost;
		if (Review.equals("null")) {
			this.Review = "0";
		}
		else {
			this.Review = Review;
		}
		
		this.helper = PDB.Get_Selected_PetSitter(helper);
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

		this.helper = PDB.Get_Selected_PetSitter(helper);
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
	public String Get_Helper_Name() {
		return this.helper.Get_Name();
	}
	public String Get_Helper_Phone() {
		return this.helper.Get_phon();
	}
	public String Get_Helper_Address() {
		return this.helper.getAddress();
	}
//	public String Get_Company() {
//		return this.Company;
//	}
	
	
}
