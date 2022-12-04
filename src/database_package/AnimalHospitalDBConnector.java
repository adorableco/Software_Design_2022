package database_package;

import java.io.*;
import java.time.*;
import java.util.*;

import reservation_package.AnimalHospital;
import reservation_package.Company;

public class AnimalHospitalDBConnector {
	private String fname;
	private int col;
	
	public AnimalHospitalDBConnector() {
		this.fname = "./Database/AnimalHospitalDB.txt";
		setColSize();
	}
	public AnimalHospitalDBConnector(String fname) {
		this.fname = fname;
		setColSize();
	}
	//header 반환
	public String[] getDBHeader() throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(this.fname));
		String header = reader.readLine();
		reader.close();
//		System.out.println(header.substring(0, 17)+header.substring(248));
		header = header.substring(0, 17)+header.substring(248);
		return header.split(",");
		
	};
	public void setColSize(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.fname));
			this.col = reader.readLine().split(",").length;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	};
	public int getColSize() {
		return this.col;
	}
	public LinkedList<AnimalHospital> readDB() throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(this.fname));
		reader.readLine(); //header 제거
		String str;
		LinkedList<AnimalHospital> hospitalDB = new LinkedList<AnimalHospital>();
        while ((str = reader.readLine()) != null) {
        	hospitalDB.add(new AnimalHospital(Arrays.asList(str.split(","))));
        }
		reader.close();
		
		return hospitalDB;
	};
	//DB 값 추가
	public void addDB(String str) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.fname));
        writer.write(str);
        writer.close();
		return;
	};
	
	public LinkedList<AnimalHospital> searchDBwithTime(LocalDate date, LocalTime start_time,LocalTime finish_time) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(this.fname));
		reader.readLine(); //header 제거
		String str;
		LinkedList<AnimalHospital> hospitalDB = new LinkedList<AnimalHospital>();
        while ((str = reader.readLine()) != null) {
        	AnimalHospital hospital = new AnimalHospital(Arrays.asList(str.split(",")));
        	if (hospital.checkWithinBH(date, start_time,finish_time)) { //참이면 추가
        		hospitalDB.add(hospital);
        	}	
        }
		reader.close();
		
		return hospitalDB;
	}
	

	public AnimalHospital Get_Selected_Company(String Company){
		AnimalHospital Selected_Company = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fname));
			String Line = reader.readLine();
			System.out.println(Line);
			while((Line = reader.readLine()) != null) {
				String[] contents = Line.split(",");
				if(contents[0].equals(Company)) {
					Selected_Company = new AnimalHospital(Arrays.asList(contents));
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Selected_Company;
	}
	/* need to implement
	//DB 값 삭제
	public Boolean deleteDB(String fname, String name) {
		if (!isinDB(fname, name)) {
			return false;
		}
		
		
		return true;
			
	};
	//DB에 존재하는지 검색
	public Boolean isinDB(String fname, String searchName) {
		Boolean flag;
		
		return flag;
		
	};
	*/
}
