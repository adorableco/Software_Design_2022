package database_package;

import java.util.*;
import reservation_package.Reservation;
import java.io.*;

public class ReservationDB {
	private FileWriter fw;
	private String reservation_data;
	public void dataUpload(Reservation rs) {
		reservation_data=rs.madeDate();
		//사용자 이름, 예약 고유번호 같이 입력
		//예약 고유번호 있으면 삭제나 검색하기 쉬움
		fw=null;
		try {
			fw=new FileWriter("Reservation DB.txt",true);
			fw.write(reservation_data,0,reservation_data.length());
			fw.write("\r\n",0,2);
			fw.close();
			System.out.println("예약 성공\n");
		}catch(IOException e) {
			System.out.println("예약 실패\n");
			e.printStackTrace();
		}
	}
}
