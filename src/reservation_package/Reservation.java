package reservation_package;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Reservation {
	private LocalDate date;
	private LocalTime time;
	private String service;
	private int cost;
	
	public void setDate(LocalDate date){
		this.date=date;
	}
	public void setTime(LocalTime time){
		this.time=time;
	}
	public void setService(String service) {
		this.service=service;
	}
	public void setCost(int cost) {
		this.cost=cost;
	}
	public String madeDate() {
		//date를 String으로 바꾸고 예약 정보를 String으로 합치기
		String s_date=this.date.format(DateTimeFormatter.ofPattern("yyyy MM dd"));
		String s_time=this.time.format(DateTimeFormatter.ofPattern("HH mm"));
		String dataString=String.format("%s %s %s %d", s_date,s_time,service,cost);
		return dataString;
	}
	
}
