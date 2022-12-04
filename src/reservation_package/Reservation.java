package reservation_package;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import database_package.AnimalHospitalDBConnector;
import database_package.PetGroomingSalonDBConnector;
import database_package.PetSitterDBConnector;
import participant_package.Pet;

public class Reservation {
	private LocalDate date;
	private LocalTime start_time;
	private LocalTime finish_time;
	private String service;
	private int State;
	private int cost;
	private String Review;
	private AnimalHospital SelectedCompany_Hospital = null;
	private PetGroomingSalon SelectedCompany_Salon = null;
	//private Company SelectedCompany;
	private PetSitter Selected_helper;
	private String SelectedPet;

	
	

	public Reservation() {
	}
	
	
	public Reservation(LocalDate Use_Day, LocalTime Use_Start_Time, LocalTime Use_Finish_Time,String Use_Service 
						,int State, int Cost, String Review, String Company, String helper, String SelectedPet){

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

		PetSitterDBConnector PDB = new PetSitterDBConnector();
		this.Selected_helper = PDB.Get_Selected_PetSitter(helper);

		if(!Company.equals("null")){
			//company가져오기.
			if(this.service.equals("병원동행")) {
				AnimalHospitalDBConnector ADBC = new AnimalHospitalDBConnector();
				SelectedCompany_Hospital = ADBC.Get_Selected_Company(Company);
			}
			else {

				PetGroomingSalonDBConnector SDBC = new PetGroomingSalonDBConnector();
				SelectedCompany_Salon = SDBC.Get_Selected_Company(Company);
			}
		}

		this.SelectedPet = SelectedPet;
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
	public void setState(int State) {
		
		this.State = State;
	}
	public void setHelper(String helper) {
		PetSitterDBConnector PDB = new PetSitterDBConnector();
		this.Selected_helper = PDB.Get_Selected_PetSitter(helper);
	}
	public void setCompany(String company) {
		if(this.service.equals("병원동행")) {
			AnimalHospitalDBConnector ADBC = new AnimalHospitalDBConnector();
			SelectedCompany_Hospital = ADBC.Get_Selected_Company(company);
		}
		else {

			PetGroomingSalonDBConnector SDBC = new PetGroomingSalonDBConnector();
			SelectedCompany_Salon = SDBC.Get_Selected_Company(company);
		}
	}
	
	public void setSelectedPet(String SelectedPet) {
		this.SelectedPet = SelectedPet;
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
		return this.Selected_helper.Get_Name();
	}
	public String Get_Helper_Phone() {
		return this.Selected_helper.Get_phon();
	}
	public String Get_Helper_Address() {
		return this.Selected_helper.getAddress();
	}
	public String Get_Company() {
		if(SelectedCompany_Hospital != null) {
			return SelectedCompany_Hospital.Get_Company_Name();
		}
		else if( SelectedCompany_Salon != null) {
			return SelectedCompany_Salon.Get_Company_Name();
		}
		else {
			return "없음";
		}
	}

	public String Get_SelectedPet() {
		return this.SelectedPet;
	}
}

//	public String Get_Company() {
//		return this.Company;
//	}
	
