import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation {
	private LocalDateTime date;
	private String service;
	private String username="문채원";
	private String helper_name="xxx";
	private int num=10;
	
	public void setDate(LocalDateTime date){
		this.date=date;
		System.out.print(this.date);
	}
	public LocalDateTime getDate(){
		return this.date;
	}
	public void setService(String service) {
		this.service=service;
	}
	public String madeDate() {
		//date를 String으로 바꾸고 예약 정보를 String으로 합치기
		String s_date=this.date.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm"));
		String dataString=String.format("%s %d %s %s %s", username,num,helper_name,s_date,service);
		return dataString;
	}
	
}
