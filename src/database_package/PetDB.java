package database_package;

import java.util.*;

import PD_LAYER.participant_package.Pet;

import java.io.*;

public class PetDB {
	private FileWriter fw;
	private String pet_data;
	public void dataUpload_pet(Pet rs) {
		pet_data=rs.madepetDB();
		//사용자 이름, 예약 고유번호 같이 입력
		//예약 고유번호 있으면 삭제나 검색하기 쉬움
		fw=null;
		try {
			fw=new FileWriter("./DataBase/Pet DB.txt",true);
			fw.write(pet_data);
			fw.write("\n");
			fw.close();
			System.out.println("등록 성공\n");
		}catch(IOException e) {
			System.out.println("등록 실패\n");
			e.printStackTrace();
		}
	}
	public int dataDownload_pet(Pet[] pet) {
		int n=0;
		try {
			BufferedReader Br = new BufferedReader(new FileReader("./DataBase/Pet DB.txt"));
			String line;
			while((line = Br.readLine())!=null) {
				if(line.trim().isEmpty()) break;
				System.out.println(line);
				String [] petData = line.split(" ");
				pet[n].registerType(petData[0]);
				pet[n].registerName(petData[1]);
				pet[n].registerAge(Integer.parseInt(petData[2]));
				pet[n].registerBreed( petData[3]);
				pet[n].registerWeight( Integer.parseInt(petData[4]));
				pet[n].registerIllness (petData[5]);
				pet[n].registerDrug (petData[6]);
				pet[n].registerHospital (petData[7]);
				pet[n].registerShop (petData[8]);
				n++;
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return n;
	}
}
