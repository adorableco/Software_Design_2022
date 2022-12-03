package database_package;

import java.util.*;
import reservation_package.Reservation;
import java.io.*;
import java.time.format.DateTimeFormatter;

public class ReservationDB {
//	private FileWriter fw;
//	private String reservation_data;
//	public void dataUpload(Reservation rs) {
//		reservation_data=rs.madeDate();
//		//사용자 이름, 예약 고유번호 같이 입력
//		//예약 고유번호 있으면 삭제나 검색하기 쉬움
//		fw=null;
//		try {
//			fw=new FileWriter("Reservation DB.txt",true);
//			fw.write(reservation_data,0,reservation_data.length());
//			fw.write("\r\n",0,2);
//			fw.close();
//			System.out.println("예약 성공\n");
//		}catch(IOException e) {
//			System.out.println("예약 실패\n");
//			e.printStackTrace();
//		}
//	}
	private int resvInt = 7;
	private String path = "./DataBase/Reservation/";
	
	public void saveFile(Reservation resvInfo) {
		int userId = 1;
		String fname = this.path + Integer.toString(userId) + "_Reser_" + Integer.toString(resvInt) + ".txt";
		String resv = resvInfo.Get_Use_Day().format(DateTimeFormatter.ofPattern("yyyy MM dd"))+ " " +
				resvInfo.Get_Use_Time().format(DateTimeFormatter.ofPattern("HH mm")) + " " +
				resvInfo.Get_Use_Service() + " " +
				Integer.toString(resvInfo.Get_State()) + " " +
				Integer.toString(resvInfo.Get_Cost()) + " " +
				resvInfo.Get_Review();
		System.out.println(resv)
;		
	    try {
	    	BufferedWriter writer = new BufferedWriter(new FileWriter(fname));
			writer.write(resv);
			writer.flush();
		    writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	public void saveFile(Reservation resvInfo, File originalFile) {
		String fname = this.path + originalFile.getName().substring(0, originalFile.getName().lastIndexOf("."))+"_temp.txt";
		String resv = resvInfo.Get_Use_Day().format(DateTimeFormatter.ofPattern("yyyy MM dd"))+
				resvInfo.Get_Use_Time().format(DateTimeFormatter.ofPattern("HH mm")) +
				resvInfo.Get_Use_Service() +
				Integer.toString(resvInfo.Get_State()) +
				Integer.toString(resvInfo.Get_Cost()) +
				resvInfo.Get_Review();
		
	    try {
	    	BufferedWriter writer = new BufferedWriter(new FileWriter(fname));
			writer.write(resv);
			writer.flush();
		    writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	
	public void setResvInt(int n) {
		this.resvInt = n;
		return;
	}
}
