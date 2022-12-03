package database_package;

import java.util.*;
import participant_package.ManagePetInfo;
import participant_package.Pet;
import reservation_package.Reservation;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;

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
			fw.write(pet_data,0,pet_data.length());
			fw.write("\r\n",0,2);
			fw.close();
			System.out.println("등록 성공\n");
		}catch(IOException e) {
			System.out.println("등록 실패\n");
			e.printStackTrace();
		}
	}
	public int  dataDownload_pet(Pet[] pet) {
		int n=0;
		try {
			BufferedReader Br = new BufferedReader(new FileReader("./DataBase/Pet DB.txt"));
			String line;
			while((line = Br.readLine())!=null) {
				System.out.println(line);
				String [] petData = line.split(",");
				pet[n].type = petData[0];
				pet[n].name = petData[1];
				pet[n].age = Integer.parseInt(petData[2]);
				pet[n].breed = petData[3];
				pet[n].weight = Integer.parseInt(petData[4]);
				pet[n].illness = petData[5];
				pet[n].drug = petData[6];
				pet[n].hospital = petData[7];
				pet[n].shop = petData[8];
				n++;
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return n;
	}
}
