package UserUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ReserVation_Package.Reservation_Gui;

import java.util.*;



class CheckReservationMode extends JFrame implements ActionListener, ListSelectionListener{
	
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
	
	private JPanel listPanel = new JPanel();
//	private JPanel areaPanel = new JPanel();
//	
	private JLabel informationGuideWindow = new JLabel("Welcome to CheckReservation Section", JLabel.CENTER);
	
	
	private JPanel informationRpanel = new JPanel(new GridLayout(1,1));
	
	private JPanel informationPanel = new JPanel(new GridLayout(2,1));
	private JLabel informationWindow = new JLabel("Select the Menu");
	private JPanel informationWpanel = new JPanel(new GridLayout(1,2));
	private JButton informationWRokBtn = new JButton("Confirm");
	
	String Menu[] = {"(1) 반려동물 등록", "(2) 도우미 예약", "(3) 예약 조회"};
	private JList<String> GuiMenu = new JList<String>(Menu);
	private ArrayList<String> MenuList = new ArrayList<String>();
	private ArrayList<String> addList = new ArrayList<String>();
	private ArrayList<String> subMenuList = new ArrayList<String>();
	
	
	
	public CheckReservationMode() {
		createFrame("Checking Reservation");
		
		LineBorder line = new LineBorder(Color.black,1);
		informationWpanel.setBorder(line);
		
		GuiMenu.setFixedCellHeight(25);
		GuiMenu.setFixedCellWidth(140);
		GuiMenu.addListSelectionListener(this);

		
		
		informationWpanel.setPreferredSize(new Dimension(600,47));
		
		informationGuideWindow.setOpaque(true);
		informationGuideWindow.setBackground(Color.GRAY);
		informationGuideWindow.setForeground(Color.white);
		informationGuideWindow.setFont(new Font("Serief", Font.BOLD, 15));
		informationWindow.setOpaque(true);
		informationWindow.setBackground(Color.LIGHT_GRAY);
		informationWindow.setFont(new Font("Serief", Font.BOLD, 15));
		
		listPanel.add(GuiMenu);
//		areaPanel.add(scrollPane);
		
		informationWpanel.add(informationWRokBtn);
		
		informationPanel.add(informationWindow);
		informationPanel.add(informationWpanel);
		informationRpanel.add(informationGuideWindow);
//		informationRWpanel.add(informationWindow);
		
		
		super.setLayout(new BorderLayout(20,30));
		add(listPanel, BorderLayout.WEST);
		add(informationRpanel, BorderLayout.CENTER);
		add(informationPanel, BorderLayout.SOUTH);
		
		
		
		this.setVisible(true);
		this.setFocusable(true);
		
		
		informationWRokBtn.addActionListener(this);
		
	}
	
	
	
	
	private int selectedMenu;
	
		
	
	
	public void displayGuiMenu(int selection) {
		selectedMenu = selection;
		System.out.println(selectedMenu);
		System.out.println(Menu[selection]);
		
		switch(selection) {
		case(0):
			informationWindow.setText(Menu[selection] + "이동");
			break;
		case(1):
			informationWindow.setText(Menu[selection] + "이동");
			break;

		case(2):
			informationWindow.setText(Menu[selection] + "이동");
			break;
		}
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		int selection = GuiMenu.getSelectedIndex();
		if(!e.getValueIsAdjusting()) {
			displayGuiMenu(selection);
		}
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(selectedMenu) {
		case(0):
			//반려동물 등록 추가해주세요.
			break;
		case(1):
			//반려동물 등록 추가해주세요.
			break;
		case(2):
			new Reservation_Gui();
			break;
		}
	}
	
	
	
	
	
}



public class MainGUI {
	public static void main(String[] args) {
		new CheckReservationMode();
	}
}
