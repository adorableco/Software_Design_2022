package Main;

import javax.swing.*;
import javax.swing.event.*;

import Database.AnimalHospitalDBConnector;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import Reservation.Company;

public class MainGUI extends JFrame {

	public MainGUI() {
		this.setTitle("피어펫 서비스");
	}
	public MainGUI(String title) {
		createFrame(title);

		JPanel button_panel = new JPanel();
		button_panel.setLayout(new GridLayout(10,1));
		JButton hospitalResvButton = new JButton("병원 예약");
		JButton salonResvButton = new JButton("반려동물 미용실 예약");
		JButton petsitterSrchButton = new JButton("도우미 검색 서비스");
		button_panel.add(hospitalResvButton);
		button_panel.add(salonResvButton);
		button_panel.add(petsitterSrchButton);
		ActionListener hospitalResvListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AnimalHospitalReservationGUI("병원 예약 서비스");
			}
			
		};
		ActionListener salonResvListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new PetGroomingSalonReservationGUI("반려동물 미용실 예약 서비스");
			}
			
		};
		ActionListener petsitterSrchListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new PetSitterSearchGUI("도우미 검색 서비스");
			}
			
		};
		hospitalResvButton.addActionListener(hospitalResvListener);
		salonResvButton.addActionListener(salonResvListener);
		petsitterSrchButton.addActionListener(petsitterSrchListener);
		
		this.add(button_panel, BorderLayout.WEST);
		
		this.setVisible(true);
		this.setFocusable(true);
	}
	
	void createFrame(String title) {
		this.setTitle(title);
		this.setSize(800,600);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frame = getSize();
		
		int xPos = (int)(screen.getWidth() / 2 - frame.getWidth()/2);
		int yPos = (int)(screen.getHeight() / 2 - frame.getHeight()/2);
		
		this.setLocation(xPos, yPos);
		this.setResizable(true);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout());	
	}

	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		new MainGUI("피어펫 서비스");
//		AnimalHospitalDBConnector conn = new AnimalHospitalDBConnector(); 
	}

}
