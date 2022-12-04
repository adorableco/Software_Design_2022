package gui_package;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import reservation_package.Reservation;

import java.util.*;



class ReservationChange extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;

	
	private JPanel listPanel = new JPanel();
//	private JPanel areaPanel = new JPanel();
//	
	private JTextArea informationGuideWindow = new JTextArea("");

	
	private JPanel informationRpanel = new JPanel(new GridLayout(1,1));
	
	private JPanel informationPanel = new JPanel(new GridLayout(2,1));
	private JLabel informationWindow = new JLabel("Select the Menu");
	private JPanel informationWpanel = new JPanel(new GridLayout(1,2));
	private JButton informationWRokBtn = new JButton("예약 변경 하러 가기");
	private JButton BackBtn = new JButton("뒤돌아 가기");
	
	private String Menu[] = {"(2) 예약 변경","(2-1) 저장하기"};
	private int Selected_Menu_Index;
	
	private Reservation SelectedReservation;
	private File Selected_File;
	
	private JList<String> GuiMenu = new JList<String>(Menu);
	private ArrayList<String> MenuList = new ArrayList<String>();
	
	


	void createFrame(String title) {
		this.setTitle(title);
		this.setSize(800,600);
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frame = getSize();
		
		int xPos = (int)(screen.getWidth() / 2 - frame.getWidth()/2) + 100;
		int yPos = (int)(screen.getHeight() / 2 - frame.getHeight()/2) + 100;
		
		this.setLocation(xPos, yPos);
		this.setResizable(true);
		
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		
	}
	
	public ReservationChange(Reservation reservation_Info, File Selected_File) {
		createFrame("Reservation Change");
		this.SelectedReservation = reservation_Info;
		this.Selected_File = Selected_File;
		
		LineBorder line = new LineBorder(Color.black,1);
		informationWpanel.setBorder(line);
		
		GuiMenu.setFixedCellHeight(25);
		GuiMenu.setFixedCellWidth(140);

		
		
		informationWpanel.setPreferredSize(new Dimension(600,47));
		
		informationGuideWindow.setOpaque(true);
		informationGuideWindow.setBackground(Color.GRAY);
		informationGuideWindow.setForeground(Color.white);
		informationGuideWindow.setFont(new Font("Serief", Font.BOLD, 15));
		informationWindow.setOpaque(true);
		informationWindow.setBackground(Color.LIGHT_GRAY);
		informationWindow.setFont(new Font("Serief", Font.BOLD, 15));
		
		listPanel.add(GuiMenu);
		GuiMenu.setSelectedIndex(0);
		GuiMenu.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				Selected_Menu_Index = e.getLastIndex();
				
				switch(Selected_Menu_Index) {
				case 1:
					informationWindow.setText("날짜 변경하러 가기");
					break;
				case 2:
					informationWindow.setText("저장하시겠습니까??");
				}
			}
		});
//		areaPanel.add(scrollPane);
		
		informationWpanel.add(informationWRokBtn);
		informationWpanel.add(BackBtn);
		
		
		informationPanel.add(informationWindow);
		informationPanel.add(informationWpanel);
		informationRpanel.add(informationGuideWindow);
//		informationRWpanel.add(informationWindow);
		
		
		super.setLayout(new BorderLayout(20,30));
		add(listPanel, BorderLayout.WEST);
		add(informationRpanel, BorderLayout.CENTER);
		add(informationPanel, BorderLayout.SOUTH);
		
		

		if(SelectedReservation.Get_State() == 0) informationGuideWindow.setText("상태 : 이용예정\n");
		else if(SelectedReservation.Get_State() == 1) informationGuideWindow.setText("상태 : 이용완료\n");
		else informationGuideWindow.setText("상태 : 예약 취소\n");
		
		informationGuideWindow.setText( informationGuideWindow.getText() + "이용 날짜 : " + SelectedReservation.Get_Use_Day().toString() + "\n");
		informationGuideWindow.setText( informationGuideWindow.getText() + "이용 시간 : " + SelectedReservation.Get_Use_Start_Time() + " ~ " + SelectedReservation.Get_Use_Finish_Time()+"\n");
		informationGuideWindow.setText( informationGuideWindow.getText() + "할일 목록 : " + SelectedReservation.Get_Use_Service() + "\n");
		informationGuideWindow.setText( informationGuideWindow.getText() + "결제 금액 : " + Integer.toString(SelectedReservation.Get_Cost())+ "\n");

		informationGuideWindow.setText( informationGuideWindow.getText() + "도우미 이름 : " + SelectedReservation.Get_Helper_Name()+ "\n");

		informationGuideWindow.setText( informationGuideWindow.getText() + "도우미 번호 : " + SelectedReservation.Get_Helper_Phone()+ "\n");

		informationGuideWindow.setText( informationGuideWindow.getText() + "도우미 주소 : " + SelectedReservation.Get_Helper_Address()+ "\n");
		
		
		this.setVisible(true);
		this.setFocusable(true);
		
		
		informationWRokBtn.addActionListener(this);
		BackBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane confirm = new JOptionPane();
				int result;
				result = confirm.showConfirmDialog(null, "돌아가시겠습니까? ", "돌아가기", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(result == 0) {
						dispose();
				} 
			}
		});
		
	}
	public void actionPerformed(ActionEvent e) {
		switch(Selected_Menu_Index) {
		case(0):
			new Reservation_helper_GUI(SelectedReservation, Selected_File);
			break;
//		case(2):
//			System.out.println(Selected_File.getName());
//			try {
//				FileWriter fw = new FileWriter(Selected_File.getPath(), false);
//				fw.write(SelectedReservation.Get_name() + " " + SelectedReservation.Get_State());
//				fw.close();
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//			break;
		}
	}	
	
}



public class Reservation_Check_Gui_ReservationChange {
	public Reservation_Check_Gui_ReservationChange(Reservation reservation_Info, File Selected_File) {
		new ReservationChange(reservation_Info, Selected_File);
	}
}
