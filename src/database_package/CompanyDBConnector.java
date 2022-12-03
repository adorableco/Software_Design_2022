//package Database;
//
//import java.util.*;
//import java.io.*;
//
//import Reservation.Company;
//
//public class CompanyDBConnector {
//	private String fname;
//	private int col;
//	
//	public CompanyDBConnector(String fname) {
//		this.fname = fname;
//		setColSize();
//	}
//	
//	//header 반환
//	public String[] getDBHeader() throws IOException{
//		BufferedReader reader = new BufferedReader(new FileReader(this.fname));
//		String header = reader.readLine();
//		reader.close();
//		
//		return header.split(",");
//		
//	};
//	public void setColSize(){
//		try {
//			BufferedReader reader = new BufferedReader(new FileReader(this.fname));
//			this.col = reader.readLine().split(",").length;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	};
//	public int getColSize() {
//		return this.col;
//	}
//	//DB 읽어옴
//	public LinkedList<Company> readDB() throws IOException{
//		BufferedReader reader = new BufferedReader(new FileReader(this.fname));
//		reader.readLine(); //header 제거
//		String str;
//		LinkedList<Company> companyDB = new LinkedList<Company>();
//        while ((str = reader.readLine()) != null) {
//            companyDB.add(new Company(Arrays.asList(str.split(","))));
//        }
//		reader.close();
//		
//		return companyDB;
//	};
//	//DB 값 추가
//	public void addDB(String str) throws IOException {
//        BufferedWriter writer = new BufferedWriter(new FileWriter(this.fname));
//        writer.write(str);
//        writer.close();
//		return;
//	};
//	
//	/* need to implement
//	//DB 값 삭제
//	public Boolean deleteDB(String fname, String name) {
//		if (!isinDB(fname, name)) {
//			return false;
//		}
//		
//		
//		return true;
//			
//	};
//	//DB에 존재하는지 검색
//	public Boolean isinDB(String fname, String searchName) {
//		Boolean flag;
//		
//		return flag;
//		
//	};
//	*/
//	
//}
