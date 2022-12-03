package database_package;

import java.util.*;
import participant_package.Pet;
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
			fw.write(pet_data,0,pet_data.length());
			fw.write("\r\n",0,2);
			fw.close();
			System.out.println("등록 성공\n");
		}catch(IOException e) {
			System.out.println("등록 실패\n");
			e.printStackTrace();
		}
	}
}
