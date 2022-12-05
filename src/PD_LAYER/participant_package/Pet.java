package PD_LAYER.participant_package;

import java.time.format.DateTimeFormatter;

public class Pet  { //반려동물 정보를 가지고 있는 클래스 
	
	private String type;
	private String name;
	private int age;
	private String breed;
	private int weight;
	private String illness;
	private String drug;
	private String hospital;
	private String shop;
	
	public Pet() {
		this.type = "0";
		this.name = "0";
		this.age = 0;
		this.breed = "0";
		this.weight = 0;
		this.illness = "0";
		this.drug = "0";
		this.hospital = "0";
		this.shop = "0";
	}
	public void registerType(String type) 
	{
		this.type = type;
	}
	
	public void registerName(String name) //반려동물 정보를 등록하는 method
	{
		this.name = name;
	}
	
	public void registerAge(int age) //반려동물 정보를 등록하는 method
	{
		this.age = age;
	}
	
	public void registerBreed(String breed) //반려동물 정보를 등록하는 method
	{
		this.breed = breed;
	}
	
	public void registerWeight(int weight) //반려동물 정보를 등록하는 method
	{
		this.weight = weight;
	}
	
	public void registerIllness(String illness) 
	{
		this.illness = illness;
	}
	
	public void registerDrug(String drug) 
	{
		this.drug = drug;
	}
	
	public void registerHospital(String hospital) 
	{
		this.hospital = hospital;
	}
	
	public void registerShop(String shop) 
	{
		this.shop = shop;
	}
	
	public String Get_Type() {
		return this.type;
	}
	public String Get_name() {
		return this.name;
	}
	public String Get_age() {
		return Integer.toString(this.age);
	}
	public String madepetDB() {
		String petData =this.type+" "+this.name+" "+this.age+" "+this.breed+" "+this.weight+" "+this.illness+" "+this.drug+" "+this.hospital+" "+this.shop;
		return petData;
	}
	public String Get_breed() {
		return this.breed;
	}
}