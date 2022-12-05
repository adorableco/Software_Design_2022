package HCI_LAYER.Participant_UI_Package.Reservation_Manage_GUI;

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

import PD_LAYER.reservation_package.Reservation;
import database_package.ReservationDB;
import java.util.*;



class ReservationCancel extends JFrame{
	
private static final long serialVersionUID = 1L;

	
	private JPanel listPanel = new JPanel();
//	private JPanel areaPanel = new JPanel();
//	
	private JTextArea informationGuideWindow = new JTextArea("");

	
	private JPanel informationRpanel = new JPanel(new GridLayout(1,1));
	
	private JPanel informationPanel = new JPanel(new GridLayout(2,1));
	private JLabel informationWindow = new JLabel("Select the Menu");
	private JPanel informationWpanel = new JPanel(new GridLayout(1,2));
	private JButton CancelBtn = new JButton("취소하기");
	private JButton BackBtn = new JButton("뒤로가기");
	
	private Reservation SelectedReservation;
	private File Selected_File;

	String Menu[] = {"(3) 취소"};
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
	
	public ReservationCancel(Reservation reservation_Info, File Selected_File) {
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
//		areaPanel.add(scrollPane);
		
		informationWpanel.add(BackBtn);
		informationWpanel.add(CancelBtn);
		
		informationPanel.add(informationWindow);
		informationPanel.add(informationWpanel);
		informationRpanel.add(informationGuideWindow);
//		informationRWpanel.add(informationWindow);
		
		
		super.setLayout(new BorderLayout(20,30));
		add(listPanel, BorderLayout.WEST);
		add(informationRpanel, BorderLayout.CENTER);
		add(informationPanel, BorderLayout.SOUTH);
		

		if(SelectedReservation.Get_State() == 0) informationGuideWindow.setText("이용예정\n");
		else if(SelectedReservation.Get_State() == 1) informationGuideWindow.setText("이용완료\n");
		else informationGuideWindow.setText("예약 취소\n");
		informationGuideWindow.setText( informationGuideWindow.getText() +"이용 날짜 : " + SelectedReservation.Get_Use_Day()+ "\n");
		informationGuideWindow.setText(informationGuideWindow.getText() + "이용 시간 : " + SelectedReservation.Get_Use_Start_Time()+" ~ "+SelectedReservation.Get_Use_Finish_Time() + "\n");
		informationGuideWindow.setText( informationGuideWindow.getText() +"할일 목록 : " + SelectedReservation.Get_Use_Service() + "\n");
		informationGuideWindow.setText( informationGuideWindow.getText() +"결제 금액 : " + Integer.toString(SelectedReservation.Get_Cost())+ "\n");
		informationGuideWindow.setText( informationGuideWindow.getText() +"반려 동물 : " + SelectedReservation.Get_SelectedPet()+ "\n");

		if(!(SelectedReservation.Get_Review().equals("0"))) {
			informationGuideWindow.setText( informationGuideWindow.getText() +"리뷰내용 : " + SelectedReservation.Get_Review()+ "\n");
		}
		else {
			informationGuideWindow.setText( informationGuideWindow.getText() +"리뷰내용 : 없음"+ "\n");
		}

		informationGuideWindow.setText( informationGuideWindow.getText() +"------------------------------------------------------"+ "\n");
		informationGuideWindow.setText(informationGuideWindow.getText() +"도우미 이름 : " + SelectedReservation.Get_Helper_Name()+ "\n");
		informationGuideWindow.setText(informationGuideWindow.getText() +"도우미 전화번호 : " + SelectedReservation.Get_Helper_Phone()+ "\n");
		informationGuideWindow.setText(informationGuideWindow.getText() +"도우미 주소 : " + SelectedReservation.Get_Helper_Address()+ "\n");

		informationGuideWindow.setText(informationGuideWindow.getText() + "------------------------------------------------------"+ "\n");
		if(!(SelectedReservation.Get_Company().equals("없음"))) {
			informationGuideWindow.setText(informationGuideWindow.getText() +"업체명 : " + SelectedReservation.Get_Company()+ "\n");
			informationGuideWindow.setText(informationGuideWindow.getText() +"업체번호 : " + SelectedReservation.Get_Company_phone()+ "\n");
			informationGuideWindow.setText(informationGuideWindow.getText() +"업체주소 : " + SelectedReservation.Get_Company_Address()+ "\n");
			if(!SelectedReservation.Get_Company_Speciality().equals("")) {
				informationGuideWindow.setText(informationGuideWindow.getText() +"진료정보  : " + SelectedReservation.Get_Company_Speciality()+ "\n");
			}
		}

		informationGuideWindow.setText( informationGuideWindow.getText() +"------------------------------------------------------"+ "\n");

		informationGuideWindow.setText(informationGuideWindow.getText() + "예약한 반려동물 이름  : " + SelectedReservation.Get_SelectedPet()+ "\n");
		this.setVisible(true);
		this.setFocusable(true);
		
		
		CancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane confirm = new JOptionPane();
				int result;
				result = confirm.showConfirmDialog(null, "정말 취소하시겠습니까?", "예약 취소", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(result == 0) {
					ReservationDB RDB = new ReservationDB();
					SelectedReservation.setState(2);
					RDB.savdFile_Selected(reservation_Info, Selected_File);
					dispose();
				}
			}
		});
		
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
	
	
}




public class Reservation_Check_Gui_ReservationCancel {
	public Reservation_Check_Gui_ReservationCancel(Reservation reservation_Info, File Selected_File) {

		new ReservationCancel(reservation_Info, Selected_File);
	}
}
