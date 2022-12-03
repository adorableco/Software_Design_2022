package gui_package;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import javax.swing.*;
//reservation 객체 선언
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database_package.ReservationDB;
import reservation_package.Reservation;

public class Reservation_helper_GUI extends JFrame {
	Reservation res;
	ReservationDB resDB;
	PetSitterSearchGUI petsitterSearchGUI;
	
	String Menu[]= {"(1) 도우미 예약"};
	private JPanel listPanel = new JPanel();
	
	private JPanel out_info_panel = new JPanel(new GridLayout(1,1));
	
	private JPanel b_n_e_panel = new JPanel(new GridLayout(2,1)); //밑에 뒤로가기랑 다음, 설명, 포함
	private JLabel explan_label = new JLabel("Select the Menu");
	private JPanel back_next_panel = new JPanel(new GridLayout(1,2)); //뒤로가기랑 다음버튼 포함
	private JButton next_btn = new JButton("이동하기");
	private JButton BackBtn = new JButton("뒤로가기");
	
	private JList<String> menu_btn = new JList<String>(Menu); //옆에 메뉴버튼
	
	JPanel info_panel;
	private Dimension dim1,dim2,dim3,dim4, dim5;
	private String[] year= {"yyyy","2022","2023","2024","2025","2026","2027","2028","2029","2030"};
	private String[] month= {"MM","01","02","03","04","05","06","07","08","09","10","11","12"};
	private String[] day= {"dd","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	private String[] hour= {"HH","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"};
	private String[] min= {"mm","00","10","20","30","40","50"};
	private String[] service= {"서비스 선택","산책","집 방문","병원 동행","반려동물 미용샵 동행"};
	private JButton helper_search_button;
	private JComboBox<String> yearCombo;
	private JComboBox<String> monthCombo;
	private JComboBox<String> dayCombo;
	private JComboBox<String> hourCombo;
	private JComboBox<String> minCombo;
	private JComboBox<String> serviceCombo;
	
	private JComboBox<String> PetnameCombo;
	private String service_choose;
	private int serviceNum;
	private String ye;
	private String mo;
	private String da;
	private String ho;
	private String mi;
	public String helper;
	private int cost;
	
	//frame 생성
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
		
	public Reservation_helper_GUI(Reservation reservation_Info,File Selected_File) {
		res=new Reservation();
		resDB=new ReservationDB();
		LocalDate resvDate = reservation_Info.Get_Use_Day();
		LocalTime resvTime = reservation_Info.Get_Use_Time();
		
		ye = Integer.toString(resvDate.getYear());
		mo = Integer.toString(resvDate.getMonthValue());
		da = Integer.toString(resvDate.getDayOfMonth());
		ho = Integer.toString(resvTime.getHour());
		mi = Integer.toString(resvTime.getMinute());
		service_choose = reservation_Info.Get_Use_Service();
		
		createFrame("도우미 예약");
	
		LineBorder line = new LineBorder(Color.black,1);
		back_next_panel.setBorder(line);
		
		menu_btn.setFixedCellHeight(25);
		menu_btn.setFixedCellWidth(140);
		menu_btn.setSelectedIndex(0);

		back_next_panel.setPreferredSize(new Dimension(600,47));
		back_next_panel.add(BackBtn);
		
		explan_label.setOpaque(true);
		explan_label.setBackground(Color.LIGHT_GRAY);
		explan_label.setFont(new Font("Serief", Font.BOLD, 15));
		
		
		listPanel.add(menu_btn);
		
		JLabel reservation_font = new JLabel("도우미 예약");
		reservation_font.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		reservation_font.setForeground(new Color(5, 0, 153));
		
		// 컴포넌트를 title 컨테이너에 올림.
		info_panel = new JPanel();
		info_panel.setOpaque(true);
		info_panel.setBackground(Color.gray);
		info_panel.setForeground(Color.white);
		info_panel.setOpaque(true);
		info_panel.setFont(new Font("Serief", Font.BOLD, 15));
		info_panel.setLayout(new GridLayout(4, 2));
		//info_panel.setVisible(true);
		
		
		
		//이용날짜
		dim2=new Dimension(70,30);
		JPanel date_panel_1 = new JPanel(new FlowLayout(FlowLayout.RIGHT,50,30));
		JLabel date_label = new JLabel("이용 날짜 : ", JLabel.CENTER);
		date_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		date_label.setForeground(Color.white);
		date_panel_1.setOpaque(true);
		date_panel_1.setBackground(Color.gray);
		date_panel_1.setOpaque(true);
		date_panel_1.add(date_label);
			
		JPanel date_panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,32));
		date_panel_2.setOpaque(true);
		date_panel_2.setBackground(Color.gray);
		date_panel_2.setOpaque(true);
		yearCombo = new JComboBox<String>(year);
		yearCombo.setSelectedIndex(Arrays.asList(year).indexOf(resvDate.format(DateTimeFormatter.ofPattern("yyyy"))));
		
		monthCombo = new JComboBox<String>(month);
		monthCombo.setSelectedIndex(Arrays.asList(month).indexOf(resvDate.format(DateTimeFormatter.ofPattern("MM"))));
		
		dayCombo = new JComboBox<String>(day);
		dayCombo.setSelectedIndex(Arrays.asList(day).indexOf(resvDate.format(DateTimeFormatter.ofPattern("dd"))));
		
		yearCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		monthCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		dayCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		yearCombo.setPreferredSize(dim2);
		monthCombo.setPreferredSize(dim2);
		dayCombo.setPreferredSize(dim2);
				
		date_panel_2.add(yearCombo);
		date_panel_2.add(monthCombo);
		date_panel_2.add(dayCombo);
		
		info_panel.add(date_panel_1); 
		info_panel.add(date_panel_2);
		
		//이용시간
		dim3=new Dimension(110,30);
		JPanel time_panel_1 = new JPanel(new FlowLayout(FlowLayout.RIGHT,50,30));
		JLabel time_label = new JLabel("이용 시간 : ", JLabel.CENTER);
		time_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		time_label.setForeground(Color.white);
		time_panel_1.setOpaque(true);
		time_panel_1.setBackground(Color.gray);
		time_panel_1.setOpaque(true);
		time_panel_1.add(time_label);
					
		JPanel time_panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,32));
		hourCombo = new JComboBox<String>(hour);
		hourCombo.setSelectedIndex(Arrays.asList(hour).indexOf(resvTime.format(DateTimeFormatter.ofPattern("HH"))));
		
		minCombo = new JComboBox<String>(min);
		minCombo.setSelectedIndex(Arrays.asList(min).indexOf(resvTime.format(DateTimeFormatter.ofPattern("mm"))));
		
		hourCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		minCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		time_panel_2.setOpaque(true);
		time_panel_2.setBackground(Color.gray);
		time_panel_2.setOpaque(true);
		hourCombo.setPreferredSize(dim3);
		minCombo.setPreferredSize(dim3);
						
		time_panel_2.add(hourCombo);
		time_panel_2.add(minCombo);
				
		info_panel.add(time_panel_1); 
		info_panel.add(time_panel_2);
		
		//이용서비스
		dim1=new Dimension(230,30);
		JPanel service_panel_1 = new JPanel(new FlowLayout(FlowLayout.RIGHT,50,30));
		JLabel service_label = new JLabel("이용 서비스 : ", JLabel.CENTER);
		service_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		service_label.setForeground(Color.white);
		service_panel_1.setOpaque(true);
		service_panel_1.setBackground(Color.gray);
		service_panel_1.setOpaque(true);
		service_panel_1.add(service_label);
					
		JPanel service_panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,32));
		serviceCombo = new JComboBox<String>(service);
		serviceCombo.setSelectedIndex(Arrays.asList(service).indexOf(service_choose));

		serviceCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		service_panel_2.setOpaque(true);
		service_panel_2.setBackground(Color.gray);
		service_panel_2.setOpaque(true);
		serviceCombo.setPreferredSize(dim1);
		service_panel_2.add(serviceCombo);
		
		info_panel.add(service_panel_1); 
		info_panel.add(service_panel_2);	
		explan_label.setText("도우미 예약");
		
		//도우미 검색
		dim1=new Dimension(230,30);
		JPanel helper_search_panel_1 = new JPanel(new FlowLayout(FlowLayout.RIGHT,50,30));
		JLabel helper_search_label = new JLabel("도우미 검색 : ", JLabel.CENTER);
		helper_search_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		helper_search_label.setForeground(Color.white);
		helper_search_panel_1.setOpaque(true);
		helper_search_panel_1.setBackground(Color.gray);
		helper_search_panel_1.setOpaque(true);
		helper_search_panel_1.add(helper_search_label);
				
		JPanel helper_search_panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,30));
		helper_search_button = new JButton("도우미 검색");
		helper_search_button.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		helper_search_panel_2.setOpaque(true);
		helper_search_panel_2.setBackground(Color.gray);
		helper_search_panel_2.setOpaque(true);
		helper_search_button.setPreferredSize(dim1);
					
		helper_search_panel_2.add(helper_search_button);
				
		info_panel.add(helper_search_panel_1); 
		info_panel.add(helper_search_panel_2);
		
		// 이벤트 처리
		//도우미 검색 Action
		helper_search_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//도우미 검색 창으로 넘어감
				petsitterSearchGUI = new PetSitterSearchGUI("도우미 검색 서비스");
				dispose();
				petsitterSearchGUI.fr.addWindowListener(new WindowAdapter() {
					@Override
			        public void windowClosing(WindowEvent e) {
						helper_search_button.setText("도우미 선택 완료");
						helper_search_button.setEnabled(false);
			        }
			    });
			}
		});
		
		//이용 날짜 Action
		yearCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox y=(JComboBox)e.getSource();
				if(y.getSelectedItem().equals("yyyy"))
					JOptionPane.showMessageDialog(null,"잘못선택하였습니다.");
				else {
					ye=y.getSelectedItem().toString();
					//y.setEnabled(false);
				}
			}
		});
		monthCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox m=(JComboBox)e.getSource();
				if(m.getSelectedItem().equals("MM"))
					JOptionPane.showMessageDialog(null,"잘못선택하였습니다.");
				else {
					mo=m.getSelectedItem().toString();
					//m.setEnabled(false);
				}
			}
			
		});
		dayCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox d=(JComboBox)e.getSource();
				if(d.getSelectedItem().equals("dd"))
					JOptionPane.showMessageDialog(null,"잘못선택하였습니다.");
				else {
					da=d.getSelectedItem().toString();
				}
			}
			
		});
		
		//이용 시간 Action  몇시간하는지도 받아야함
		hourCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox h=(JComboBox)e.getSource();
				if(h.getSelectedItem().equals("HH"))
					JOptionPane.showMessageDialog(null,"잘못선택하였습니다.");
				else {
					ho=h.getSelectedItem().toString();
				}
			}
			
		});
		minCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox m=(JComboBox)e.getSource();
				if(m.getSelectedItem().equals("mm"))
					JOptionPane.showMessageDialog(null,"잘못선택하였습니다.");
				else {
					mi=m.getSelectedItem().toString();
				}
				int servIndex = serviceCombo.getSelectedIndex();
				switch(servIndex) {
				case(1):{
					next_btn.setText("저장");
				}
				case(2):{
					break;
				}
				case(3): {
					next_btn.setText("병원 예약");
					break;
				}
				case(4):{
					next_btn.setText("반려동물 미용샵 예약");
				}
				}
			}
			
		});
		
		//이용 서비스 Action
		ActionListener nextActionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox meau = (JComboBox)e.getSource();
				if(meau.getSelectedItem().equals("서비스 선택"))
					JOptionPane.showMessageDialog(null,"잘못선택하였습니다.");
				
				service_choose = meau.getSelectedItem().toString();
				
				if(service_choose.equals("산책")||service_choose.equals("집 방문")) {
					next_btn.setText("저장");
				}
					
				else if (service_choose.equals("병원 동행")) {
					next_btn.setText("병원 예약");
				}
					
				else if(service_choose.equals("반려동물 미용샵 동행")) {
					next_btn.setText("반려동물 미용샵 예약");
				}
			}
		};

		serviceCombo.addActionListener(nextActionListener);
		next_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton n=(JButton)e.getSource();
				//서비스콤보가 "산책","집방문"이면 결제창으로 넘어감
				//넘어가기전에"결제하시겠습니까" 메세지창 띄우기 
				//서비스콤보가 "병원 동행","미용샵 동행"이면 각각의 예약 창으로 넘어감
				
				//지금 짠 코드는 결제 안하고 바로 예약 DB에 저장함
				//예약 성공하면 메인창으로 넘어감
				if(n.getText().equals("저장")) {
					cost=10000;
					JOptionPane.showMessageDialog(null,"저장되었습니다.");  
					//reservation 클래스에 정보줌
					int i_ye=Integer.parseInt(ye);
					int i_mo=Integer.parseInt(mo);
					int i_da=Integer.parseInt(da);
					int i_ho=Integer.parseInt(ho);
					int i_mi=Integer.parseInt(mi);
					LocalDate date=LocalDate.of(i_ye, i_mo, i_da);
					LocalTime time=LocalTime.of(i_ho, i_mi);
					res.setDate(date);
					res.setTime(time);
					res.setService(service_choose);
					res.setCost(cost);
					resDB.saveFile(res, Selected_File);
					
					dispose();
				}					
				else if(n.getText().equals("병원 예약")) {
					JOptionPane.showMessageDialog(null,"병원을 예약하시겠습니까?");
					//병원 예약창으로 넘어감
					new AnimalHospitalReservationGUI("병원 예약 서비스", res);
					dispose();
				}
				else if(n.getText().equals("미용샵 예약")) {
					JOptionPane.showMessageDialog(null,"반려동물 미용샵을 예약하시겠습니까?");
					//미용샵 예약창으로 넘어감
					new PetGroomingSalonReservationGUI("반려동물 미용실 예약 서비스", res);
					dispose();
				}
			}
			
		});
		
		//뒤로가기 버튼
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
		
		back_next_panel.add(next_btn);
		
		b_n_e_panel.add(explan_label);
		b_n_e_panel.add(back_next_panel);
		out_info_panel.add(info_panel);
		
		
		super.setLayout(new BorderLayout(20,30));
		add(listPanel, BorderLayout.WEST);
		add(out_info_panel, BorderLayout.CENTER);
		add(b_n_e_panel, BorderLayout.SOUTH);
		
		this.setVisible(true);
		this.setFocusable(true);
	
	
	}
	
	public Reservation_helper_GUI() {
		res=new Reservation();
		resDB=new ReservationDB();
		//setTitle("도우미 예약하기");
		
		createFrame("도우미 예약");
	
		LineBorder line = new LineBorder(Color.black,1);
		back_next_panel.setBorder(line);
		
		menu_btn.setFixedCellHeight(25);
		menu_btn.setFixedCellWidth(140);
		menu_btn.setSelectedIndex(0);

		back_next_panel.setPreferredSize(new Dimension(600,47));
		back_next_panel.add(BackBtn);
		
		explan_label.setOpaque(true);
		explan_label.setBackground(Color.LIGHT_GRAY);
		explan_label.setFont(new Font("Serief", Font.BOLD, 15));
		
		
		listPanel.add(menu_btn);
		
		JLabel reservation_font = new JLabel("도우미 예약");
		reservation_font.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		reservation_font.setForeground(new Color(5, 0, 153));
		
		// 컴포넌트를 title 컨테이너에 올림.
		info_panel = new JPanel();
		info_panel.setOpaque(true);
		info_panel.setBackground(Color.gray);
		info_panel.setForeground(Color.white);
		info_panel.setOpaque(true);
		info_panel.setFont(new Font("Serief", Font.BOLD, 15));
		info_panel.setLayout(new GridLayout(4, 2));
		//info_panel.setVisible(true);
		
		
		//이용날짜
		dim2=new Dimension(70,30);
		JPanel date_panel_1 = new JPanel(new FlowLayout(FlowLayout.RIGHT,50,30));
		JLabel date_label = new JLabel("이용 날짜 : ", JLabel.CENTER);
		date_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		date_label.setForeground(Color.white);
		date_panel_1.setOpaque(true);
		date_panel_1.setBackground(Color.gray);
		date_panel_1.setOpaque(true);
		date_panel_1.add(date_label);
			
		JPanel date_panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,32));
		date_panel_2.setOpaque(true);
		date_panel_2.setBackground(Color.gray);
		date_panel_2.setOpaque(true);
		yearCombo = new JComboBox<String>(year);
		monthCombo = new JComboBox<String>(month);
		dayCombo = new JComboBox<String>(day);
		yearCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		monthCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		dayCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		yearCombo.setPreferredSize(dim2);
		monthCombo.setPreferredSize(dim2);
		dayCombo.setPreferredSize(dim2);
				
		date_panel_2.add(yearCombo);
		date_panel_2.add(monthCombo);
		date_panel_2.add(dayCombo);
		
		info_panel.add(date_panel_1); 
		info_panel.add(date_panel_2);
		
		//이용시간
		dim3=new Dimension(110,30);
		JPanel time_panel_1 = new JPanel(new FlowLayout(FlowLayout.RIGHT,50,30));
		JLabel time_label = new JLabel("이용 시간 : ", JLabel.CENTER);
		time_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		time_label.setForeground(Color.white);
		time_panel_1.setOpaque(true);
		time_panel_1.setBackground(Color.gray);
		time_panel_1.setOpaque(true);
		time_panel_1.add(time_label);
					
		JPanel time_panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,32));
		hourCombo = new JComboBox<String>(hour);
		minCombo = new JComboBox<String>(min);
		hourCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		minCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		time_panel_2.setOpaque(true);
		time_panel_2.setBackground(Color.gray);
		time_panel_2.setOpaque(true);
		hourCombo.setPreferredSize(dim3);
		minCombo.setPreferredSize(dim3);
						
		time_panel_2.add(hourCombo);
		time_panel_2.add(minCombo);
				
		info_panel.add(time_panel_1); 
		info_panel.add(time_panel_2);
		
		//이용서비스
		dim1=new Dimension(230,30);
		JPanel service_panel_1 = new JPanel(new FlowLayout(FlowLayout.RIGHT,50,30));
		JLabel service_label = new JLabel("이용 서비스 : ", JLabel.CENTER);
		service_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		service_label.setForeground(Color.white);
		service_panel_1.setOpaque(true);
		service_panel_1.setBackground(Color.gray);
		service_panel_1.setOpaque(true);
		service_panel_1.add(service_label);
					
		JPanel service_panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,32));
		serviceCombo = new JComboBox<String>(service);
		serviceCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		service_panel_2.setOpaque(true);
		service_panel_2.setBackground(Color.gray);
		service_panel_2.setOpaque(true);
		serviceCombo.setPreferredSize(dim1);
		service_panel_2.add(serviceCombo);
		
		info_panel.add(service_panel_1); 
		info_panel.add(service_panel_2);	
		explan_label.setText("도우미 예약");
		
		//도우미 검색
		dim1=new Dimension(230,30);
		JPanel helper_search_panel_1 = new JPanel(new FlowLayout(FlowLayout.RIGHT,50,30));
		JLabel helper_search_label = new JLabel("도우미 검색 : ", JLabel.CENTER);
		helper_search_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		helper_search_label.setForeground(Color.white);
		helper_search_panel_1.setOpaque(true);
		helper_search_panel_1.setBackground(Color.gray);
		helper_search_panel_1.setOpaque(true);
		helper_search_panel_1.add(helper_search_label);
				
		JPanel helper_search_panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,30));
		helper_search_button = new JButton("도우미 검색");
		helper_search_button.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		helper_search_panel_2.setOpaque(true);
		helper_search_panel_2.setBackground(Color.gray);
		helper_search_panel_2.setOpaque(true);
		helper_search_button.setPreferredSize(dim1);
					
		helper_search_panel_2.add(helper_search_button);
				
		info_panel.add(helper_search_panel_1); 
		info_panel.add(helper_search_panel_2);
		
		// 이벤트 처리
		//도우미 검색 Action
		helper_search_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//도우미 검색 창으로 넘어감
				petsitterSearchGUI=new PetSitterSearchGUI("도우미 검색 서비스");
				petsitterSearchGUI.fr.addWindowListener(new WindowAdapter() {
					@Override
			        public void windowClosing(WindowEvent e) {
						helper_search_button.setEnabled(false);
						JLabel helper_search_complete = new JLabel("도우미 선택 완료");
						helper_search_panel_2.add(helper_search_complete);
			        }
			    });
			}
		});
		
		//이용 날짜 Action
		yearCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox y=(JComboBox)e.getSource();
				if(y.getSelectedItem().equals("yyyy"))
					JOptionPane.showMessageDialog(null,"잘못선택하였습니다.");
				else {
					ye=y.getSelectedItem().toString();
					//y.setEnabled(false);
				}
			}
		});
		monthCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox m=(JComboBox)e.getSource();
				if(m.getSelectedItem().equals("MM"))
					JOptionPane.showMessageDialog(null,"잘못선택하였습니다.");
				else {
					mo=m.getSelectedItem().toString();
					//m.setEnabled(false);
				}
			}
			
		});
		dayCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox d=(JComboBox)e.getSource();
				if(d.getSelectedItem().equals("dd"))
					JOptionPane.showMessageDialog(null,"잘못선택하였습니다.");
				else {
					da=d.getSelectedItem().toString();
				}
			}
			
		});
		
		//이용 시간 Action  몇시간하는지도 받아야함
		hourCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox h=(JComboBox)e.getSource();
				if(h.getSelectedItem().equals("HH"))
					JOptionPane.showMessageDialog(null,"잘못선택하였습니다.");
				else {
					ho=h.getSelectedItem().toString();
				}
			}
			
		});
		minCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox m=(JComboBox)e.getSource();
				if(m.getSelectedItem().equals("mm"))
					JOptionPane.showMessageDialog(null,"잘못선택하였습니다.");
				else {
					mi=m.getSelectedItem().toString();
				}
			}
			
		});
		
		//이용 서비스 Action
		serviceCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox meau = (JComboBox)e.getSource();
				if(meau.getSelectedItem().equals("서비스 선택"))
					JOptionPane.showMessageDialog(null,"잘못선택하였습니다.");
				
				service_choose = meau.getSelectedItem().toString();
				
				if(service_choose.equals("산책")||service_choose.equals("집 방문")) {
					next_btn.setText("결제");
				}
					
				else if (service_choose.equals("병원 동행")) {
					next_btn.setText("병원 예약");
				}
					
				else if(service_choose.equals("반려동물 미용샵 동행")) {
					next_btn.setText("반려동물 미용샵 예약");
				}
			}
		});
		
		next_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton n=(JButton)e.getSource();
				//서비스콤보가 "산책","집방문"이면 결제창으로 넘어감
				//넘어가기전에"결제하시겠습니까" 메세지창 띄우기 
				//서비스콤보가 "병원 동행","미용샵 동행"이면 각각의 예약 창으로 넘어감
				
				//지금 짠 코드는 결제 안하고 바로 예약 DB에 저장함
				//예약 성공하면 메인창으로 넘어감
				if(n.getText().equals("결제")) {
					cost=10000;
					JOptionPane.showMessageDialog(null,cost+"원 결제되었습니다.");  //결제창 넘어감??
					//reservation 클래스에 정보줌
					int i_ye=Integer.parseInt(ye);
					int i_mo=Integer.parseInt(mo);
					int i_da=Integer.parseInt(da);
					int i_ho=Integer.parseInt(ho);
					int i_mi=Integer.parseInt(mi);
					LocalDate date=LocalDate.of(i_ye, i_mo, i_da);
					LocalTime time=LocalTime.of(i_ho, i_mi);
					res.setDate(date);
					res.setTime(time);
					res.setHelper(helper);
					res.setService(service_choose);
					res.setCost(cost);
					resDB.saveFile(res);
					
					dispose();
				}					
				else if(n.getText().equals("병원 예약")) {
					JOptionPane.showMessageDialog(null,"병원을 예약하시겠습니까?");
					//병원 예약창으로 넘어감
					int i_ye=Integer.parseInt(ye);
					int i_mo=Integer.parseInt(mo);
					int i_da=Integer.parseInt(da);
					int i_ho=Integer.parseInt(ho);
					int i_mi=Integer.parseInt(mi);
					LocalDate date=LocalDate.of(i_ye, i_mo, i_da);
					LocalTime time=LocalTime.of(i_ho, i_mi);
					res.setDate(date);
					res.setTime(time);
					res.setHelper(helper);
					res.setService(service_choose);
					res.setCost(cost);
					resDB.saveFile(res);
					new AnimalHospitalReservationGUI("병원 예약 서비스", res);
					dispose();
				}
				else if(n.getText().equals("미용샵 예약")) {
					JOptionPane.showMessageDialog(null,"반려동물 미용샵을 예약하시겠습니까?");
					//미용샵 예약창으로 넘어감
					int i_ye=Integer.parseInt(ye);
					int i_mo=Integer.parseInt(mo);
					int i_da=Integer.parseInt(da);
					int i_ho=Integer.parseInt(ho);
					int i_mi=Integer.parseInt(mi);
					LocalDate date=LocalDate.of(i_ye, i_mo, i_da);
					LocalTime time=LocalTime.of(i_ho, i_mi);
					res.setDate(date);
					res.setTime(time);
					res.setHelper(helper);
					res.setService(service_choose);
					res.setCost(cost);
					resDB.saveFile(res);
					new PetGroomingSalonReservationGUI("반려동물 미용실 예약 서비스", res);
					dispose();
				}
			}
			
		});
		
		//뒤로가기 버튼
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
		
		back_next_panel.add(next_btn);
		
		b_n_e_panel.add(explan_label);
		b_n_e_panel.add(back_next_panel);
		out_info_panel.add(info_panel);
		
		
		super.setLayout(new BorderLayout(20,30));
		add(listPanel, BorderLayout.WEST);
		add(out_info_panel, BorderLayout.CENTER);
		add(b_n_e_panel, BorderLayout.SOUTH);
		
		this.setVisible(true);
		this.setFocusable(true);
	
	
	}
}

