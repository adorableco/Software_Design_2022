package Database;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
	public List<Company> readDB(String fname);
	//DB 값 추가, return 값은 True(성공), False(실패)
	public Boolean addDB(String fname);
	//DB 값 삭제, return 값은 True(성공), False(실패)
	public Boolean deleteDB(String fname);
	
}
