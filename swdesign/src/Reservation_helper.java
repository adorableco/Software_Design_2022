import java.text.ParseException;
import java.text.SimpleDateFormat; //날짜 변환
import java.util.Date;
import java.util.*;
import java.io.*;


public class Reservation_helper {
	
	
	SimpleDateFormat date_format=new SimpleDateFormat("yyyyMMdd");
	SimpleDateFormat time_format=new SimpleDateFormat("HH:mm");
	
	private String sdate;
	Date date;
	private String stime;
	Date time;
	private String service;
	private String[] dataset = new String[6];
	
	void date_store() throws ParseException{
		sdate=String.format("%s%s%s",dataset[0], dataset[1], dataset[2]);
		date=date_format.parse(sdate);
	}
	
	void time_store() throws ParseException{
		sdate=String.format("%s%s%s",dataset[3], dataset[4]);
		time=time_format.parse(stime);
	}
	
	void service_store() {
		service=dataset[5];
	}
	void print() {
		System.out.println(sdate+" "+stime+" "+service);
	}
	//1. 도우미 검색 먼저 하기 -- 도우미 정보 return하는 type 뭘로 할것인가
	//2. 이용 날짜, 이용 시간, 이용 서비스 입력받음 (함수 만들기)
	//3. GUI에서 정보받아와서 파일열어서 저장하기(파일입출력)
	
	void reservation(Reservation_helper_GUI RHG) throws ParseException{
		dataset=RHG.setdata();
		date_store();
		time_store();
		print();
	}
	
	public static void main(String[] args) throws ParseException {
		Reservation_helper rh=new Reservation_helper();
		Reservation_helper_GUI RHG = new Reservation_helper_GUI();
		rh.reservation(RHG);
	}
}
