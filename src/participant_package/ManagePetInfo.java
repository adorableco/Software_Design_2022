package participant_package;

import database_package.PetDB;

public class ManagePetInfo {
	//public User_GUI gui = new User_GUI();
	public static int n; //등록한 반려동물 수  
	public Pet [] pet;
	
	public ManagePetInfo() {

		pet = new Pet[5];
		for(int i=0; i<5; i++)
			pet[i] = new Pet();
		PetDB petDB = new PetDB();
		this.n = petDB.dataDownload_pet(this.pet);
	}
	
	public Pet registerPet() {
		n++;
		return pet[n-1];
	}
	
	public void DeletePet(int deleteNum) {
		
		for(int i=deleteNum-1; i<n-1; i++)
			pet[i] = pet[i+1];
		n--; //총 반려동물 마릿수 -1
	}
	
}
