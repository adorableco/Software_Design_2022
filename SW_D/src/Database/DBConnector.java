package Database;

import java.util.*;
import java.io.*;

import Reservation.Company;

public interface DBConnector {
	//header 반환
	public default List<String> returnDBHeader(String fname) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(fname));
		String header = reader.readLine();
		reader.close();
		
		return Arrays.asList(header.split(","));
		
	};
	//DB 읽어옴
	public default List<Company> readDB(String fname) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(fname));
		reader.readLine(); //header 제거
		String str;
		List<Company> companyDB = new LinkedList<Company>();
        while ((str = reader.readLine()) != null) {
            companyDB.add(new Company(Arrays.asList(str.split(","))));
        }
		reader.close();
		
		return companyDB;
	};
	//DB 값 추가
	public default void addDB(String fname, String str) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fname));
        writer.write(str);
        writer.close();
		return;
	};
	
	/* need to implement
	//DB 값 삭제
	public default Boolean deleteDB(String fname, String name) {
		if (!isinDB(fname, name)) {
			return false;
		}
		
		
		return true;
			
	};
	//DB에 존재하는지 검색
	public default Boolean isinDB(String fname, String searchName) {
		Boolean flag;
		
		return flag;
		
	};
	*/
	
}
