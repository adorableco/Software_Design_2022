package database_package;
import java.sql.*;

public class Reservation {
	
	
	private Date Use_Day;  // 이용날짜 
	private Time Use_Time; // 이용시간
	private String Use_Service; // 이용 서비스
	private int State;  // 이용 상태 0: 이용 예정  1:이용완료  2:예약 취소
	private int Cost;  // 결제 비용
	private String Review; // 리뷰 내
	// 도우미 코드, 업체 예약 정보 ( 업체코드 ) 추가 해야함.
	
	
	public Reservation(Date Use_Day, Time Use_Time, String Use_Service
			,int State, int Cost, String Review){
		this.Use_Day = Use_Day;
		this.Use_Time = Use_Time;
		this.Use_Service = Use_Service;
		this.State = State;
		this.Cost = Cost;
		this.Review = Review;
	}
	
	public String Get_Use_Day() {
		return this.Use_Day.toString();
	}

	public String Get_Use_Time() {
		return this.Use_Time.toString();
	}

	public String Get_Use_Service() {
		return this.Use_Service;
	}

	public int Get_State() {
		return this.State;
	}
	public int Get_Cost() {
		return this.Cost;
	}
	public String Get_Review() {
		return this.Review;
	}
	
	
}
