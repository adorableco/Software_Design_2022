package gui_package;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database_package.ReservationDB;
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
	private JLabel informationWindow = new JLabel("예약 변경 하러 가기");
	private JPanel informationWpanel = new JPanel(new GridLayout(1,2));
	private JButton informationWRokBtn = new JButton("예약 변경 하러 가기");
	private JButton BackBtn = new JButton("뒤돌아 가기");
	
	private String Menu[] = {"(2) 예약 변경"};
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
				Selected_Menu_Index = GuiMenu.getSelectedIndex();
				System.out.println(Selected_Menu_Index);
				switch(Selected_Menu_Index) {
				case 0:
					informationWindow.setText("날짜 변경하러 가기");
					break;
				case 1:
					informationWindow.setText("저장하시겠습니까??");
					break;
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
		
//		
//
//		if(SelectedReservation.Get_State() == 0) informationGuideWindow.setText("상태 : 이용예정\n");
//		else if(SelectedReservation.Get_State() == 1) informationGuideWindow.setText("상태 : 이용완료\n");
//		else informationGuideWindow.setText("상태 : 예약 취소\n");
//		
//		informationGuideWindow.setText( informationGuideWindow.getText() + "이용 날짜 : " + SelectedReservation.Get_Use_Day().toString() + "\n");
//		informationGuideWindow.setText( informationGuideWindow.getText() + "이용 시간 : " + SelectedReservation.Get_Use_Start_Time() + " ~ " + SelectedReservation.Get_Use_Finish_Time()+"\n");
//		informationGuideWindow.setText( informationGuideWindow.getText() + "할일 목록 : " + SelectedReservation.Get_Use_Service() + "\n");
//		informationGuideWindow.setText( informationGuideWindow.getText() + "결제 금액 : " + Integer.toString(SelectedReservation.Get_Cost())+ "\n");
//		informationGuideWindow.setText( informationGuideWindow.getText() + "반려 동물 : " + SelectedReservation.Get_SelectedPet()+ "\n");
//
//		informationGuideWindow.setText( informationGuideWindow.getText() + "도우미 이름 : " + SelectedReservation.Get_Helper_Name()+ "\n");
//
//		informationGuideWindow.setText( informationGuideWindow.getText() + "도우미 번호 : " + SelectedReservation.Get_Helper_Phone()+ "\n");
//
//		informationGuideWindow.setText( informationGuideWindow.getText() + "도우미 주소 : " + SelectedReservation.Get_Helper_Address()+ "\n");
		
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
			Reservation_helper_GUI add = new Reservation_helper_GUI(SelectedReservation, Selected_File);
			add.addWindowListener(new WindowListener() {
				
				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowClosing(WindowEvent e) {
					
				}
				
				@Override
				public void windowClosed(WindowEvent e) {
					System.out.println("closed");
					String filename = Selected_File.getPath();
					String[] contents = filename.split(".txt");
					System.out.println(contents[0]);
					File tempfile = new File(contents[0]+ "_temp.txt");
					System.out.println(tempfile);

					
					if(tempfile.exists()) {
						Reservation temp = null;
						try {
							BufferedReader br = new BufferedReader(new FileReader(tempfile));
							String content = br.readLine();
							br.close();
							String[] contentlist = content.split(" ");
							temp = new Reservation(
									LocalDate.of(Integer.parseInt(contentlist[0]), Integer.parseInt(contentlist[1]), Integer.parseInt(contentlist[2])),
									LocalTime.of( Integer.parseInt(contentlist[3]),Integer.parseInt(contentlist[4]),0), LocalTime.of(Integer.parseInt(contentlist[5]), Integer.parseInt(contentlist[6]),0),
									contentlist[7], Integer.parseInt(contentlist[8]), Integer.parseInt(contentlist[9]), contentlist[10], contentlist[11], contentlist[12],contentlist[13]);
						} catch (IOException e1) {
							e1.printStackTrace();
						}

						if(temp.Get_State() == 0) informationGuideWindow.setText("이용예정\n");
						else if(temp.Get_State() == 1) informationGuideWindow.setText("이용완료\n");
						else informationGuideWindow.setText("예약 취소\n");
						informationGuideWindow.setText( informationGuideWindow.getText() +"이용 날짜 : " + temp.Get_Use_Day()+ "\n");
						informationGuideWindow.setText(informationGuideWindow.getText() + "이용 시간 : " + temp.Get_Use_Start_Time()+" ~ "+temp.Get_Use_Finish_Time() + "\n");
						informationGuideWindow.setText( informationGuideWindow.getText() +"할일 목록 : " + temp.Get_Use_Service() + "\n");
						informationGuideWindow.setText( informationGuideWindow.getText() +"결제 금액 : " + Integer.toString(temp.Get_Cost())+ "\n");
						informationGuideWindow.setText( informationGuideWindow.getText() +"반려 동물 : " + temp.Get_SelectedPet()+ "\n");

						if(!(temp.Get_Review().equals("0"))) {
							informationGuideWindow.setText( informationGuideWindow.getText() +"리뷰내용 : " + temp.Get_Review()+ "\n");
						}
						else {
							informationGuideWindow.setText( informationGuideWindow.getText() +"리뷰내용 : 없음"+ "\n");
						}

						informationGuideWindow.setText( informationGuideWindow.getText() +"------------------------------------------------------"+ "\n");
						informationGuideWindow.setText(informationGuideWindow.getText() +"도우미 이름 : " + temp.Get_Helper_Name()+ "\n");
						informationGuideWindow.setText(informationGuideWindow.getText() +"도우미 전화번호 : " + temp.Get_Helper_Phone()+ "\n");
						informationGuideWindow.setText(informationGuideWindow.getText() +"도우미 주소 : " + temp.Get_Helper_Address()+ "\n");

						informationGuideWindow.setText(informationGuideWindow.getText() + "------------------------------------------------------"+ "\n");
						if(!(temp.Get_Company().equals("없음"))) {
							informationGuideWindow.setText(informationGuideWindow.getText() +"업체명 : " + temp.Get_Company()+ "\n");
							informationGuideWindow.setText(informationGuideWindow.getText() +"업체번호 : " + temp.Get_Company_phone()+ "\n");
							informationGuideWindow.setText(informationGuideWindow.getText() +"업체주소 : " + temp.Get_Company_Address()+ "\n");
							if(!temp.Get_Company_Speciality().equals("")) {
								informationGuideWindow.setText(informationGuideWindow.getText() +"진료정보  : " + temp.Get_Company_Speciality()+ "\n");
							}
						}

						informationGuideWindow.setText( informationGuideWindow.getText() +"------------------------------------------------------"+ "\n");

						informationGuideWindow.setText(informationGuideWindow.getText() + "예약한 반려동물 이름  : " + temp.Get_SelectedPet()+ "\n");
						
						JOptionPane confirm = new JOptionPane();
						int result;
						result = confirm.showConfirmDialog(null, "예약을 변경하시겟습니까? \n" +
						"이용 날짜 : " + temp.Get_Use_Day().toString() + "\n" +
						"이용 시간 : " + temp.Get_Use_Start_Time() + " ~ " + temp.Get_Use_Finish_Time()+"\n" +
						"할일 목록 : " + temp.Get_Use_Service() + "\n" +
						"도우미 이름 : " + temp.Get_Helper_Name()+ "\n"
						, "예약변경", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
						if(result == 0) { //예
							Selected_File.delete();
							tempfile.renameTo(new File(filename));
							dispose();
						} 
						else { //아니오

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
							tempfile.delete();
						}
					}
					
				}
				
				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			break;
//		case(2):
//			System.out.println(Selected_File.getName());
//			try {
//				FileWriter fw = new FileWriter(Selected_File.getPath(), false);
////				fw.write(SelectedReservation.Get_name() + " " + SelectedReservation.Get_State());
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
