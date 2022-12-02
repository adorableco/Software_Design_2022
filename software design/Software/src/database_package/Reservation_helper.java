package database_package;

import java.util.*;

import gui_package.Reservation_helper_GUI;

import java.io.*;

//여기서 gui랑 DB 생성해야함
//gui에서 main의 DB객체에 접근

public class Reservation_helper {
	private String sdate;
	private String stime;
	private String service;
	private String[] dataset;
	private FileWriter fw;
	private String username="문채원";
	private String helper_name="xxx";
	private int num=10;
	
	void date_store(){
		sdate=String.format("%s년 %s월 %s일",dataset[0], dataset[1], dataset[2]);
	}
	
	void time_store(){
		stime=String.format("%s:%s",dataset[3], dataset[4]);
	}
	
	void service_store() {
		service=dataset[5];
	}
	//1. 도우미 검색 먼저 하기 -- 도우미 정보 return하는 type 뭘로 할것인가
	//2. 이용 날짜, 이용 시간, 이용 서비스 입력받음 (함수 만들기)
	//3. GUI에서 정보받아와서 파일열어서 저장하기(파일입출력)
	void dataUpload() {
		//사용자 이름, 예약 고유번호 같이 입력
		//예약 고유번호 있으면 삭제나 검색하기 쉬움
		fw=null;
		try {
			fw=new FileWriter("Reservation DB.txt",true);
			String reservation_data=String.format("%s  %d  %s  %s  %s  %s",username,num,helper_name,sdate,stime,service);
			fw.write(reservation_data,0,reservation_data.length());
			fw.write("\r\n",0,2);
			fw.close();
			System.out.println("예약 성공\n");
		}catch(IOException e) {
			System.out.println("예약 실패\n");
			e.printStackTrace();
		}
	}
	public void store(String[] data){
		dataset= new String[6];
		dataset=data;
		date_store();
		time_store();
		service_store();
		dataUpload();
	}
	public static void main(String[] args) {
		Reservation_helper_GUI RHG = new Reservation_helper_GUI();
	}
}

