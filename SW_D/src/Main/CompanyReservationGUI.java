package Main;

import java.util.*;

import javax.swing.*;

import Reservation.Company;

public class CompanyReservationGUI extends MainGUI{
	public CompanyReservationGUI(String title) {
		createFrame(title);
		this.setVisible(true);
		this.setFocusable(true);
	}
	
	public JPanel showList(List<Company> db) {
		JPanel cList = new JPanel();
		for (Company c : db) {
			
		}
		return cList;
		
	}
}
