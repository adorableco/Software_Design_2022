package Database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;

import Reservation.PetGroomingSalon;

public class PetGroomingSalonDBConnector {
	private String fname;
	private int col;
	
	public PetGroomingSalonDBConnector() {
		this.fname = "Database\\PetGroomingSalonDB.txt";
		setColSize();
	}
	public PetGroomingSalonDBConnector(String fname) {
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
	public LinkedList<PetGroomingSalon> readDB() throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(this.fname));
		reader.readLine(); //header 제거
		String str;
		LinkedList<PetGroomingSalon> salonDB = new LinkedList<PetGroomingSalon>();
        while ((str = reader.readLine()) != null) {
        	salonDB.add(new PetGroomingSalon(Arrays.asList(str.split(","))));
        }
		reader.close();
		
		return salonDB;
	};
	//DB 값 추가
	public void addDB(String str) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.fname));
        writer.write(str);
        writer.close();
		return;
	};
	
	public LinkedList<PetGroomingSalon> searchDBwithTime(LocalDate date, LocalTime time) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(this.fname));
		reader.readLine(); //header 제거
		String str;
		LinkedList<PetGroomingSalon> salonDB = new LinkedList<PetGroomingSalon>();
        while ((str = reader.readLine()) != null) {
        	PetGroomingSalon salon = new PetGroomingSalon(Arrays.asList(str.split(",")));
        	if (salon.checkWithinBH(date, time)) { //참이면 추가
        		salonDB.add(salon);
        	}	
        }
		reader.close();
		
		return salonDB;
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
