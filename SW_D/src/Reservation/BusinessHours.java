package Reservation;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
	
	public String[] getStringTime() {
		String[] time_str = new String[2];
		time_str[0] = this.opening.format(DateTimeFormatter.ofPattern("HH:mm"));
		time_str[1] = this.closing.format(DateTimeFormatter.ofPattern("HH:mm"));
		return time_str;
	}
}
