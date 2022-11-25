package Reservation;

import java.time.LocalTime;

public class BusinessHours {
	LocalTime opening;
	LocalTime closing;

	public BusinessHours(String opening, String closing) {
		this.opening = LocalTime.parse(opening);
		this.closing = LocalTime.parse(closing);
	}
	public BusinessHours(LocalTime opening, LocalTime closing) {
		this.opening = opening;
		this.closing = closing;
	}
	
	public void print() {
		System.out.println(opening);
		System.out.println(opening);
	}
}
