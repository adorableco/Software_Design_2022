package database_package;

public class Pet  { //반려동물 정보를 가지고 있는 클래스 
	
	public String type;
	public String name;
	public int age;
	public String breed;
	public int weight;
	public String illness;
	public String drug;
	public String hospital;
	public String shop;
	
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
	
}