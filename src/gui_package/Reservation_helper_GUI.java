package gui_package;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import javax.swing.*;
//reservation 객체 선언

import database_package.ReservationDB;
import reservation_package.Reservation;

public class Reservation_helper_GUI extends JFrame {
	Reservation res;
	ReservationDB resDB;
	
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
	private JButton cancel_button;
	private JButton next_button;
	private String service_choose;
	private int serviceNum;
	private String ye;
	private String mo;
	private String da;
	private String ho;
	private String mi;
	private int cost;
	
	public Reservation_helper_GUI(Reservation reservation_Info, File Selected_File) {
		res=new Reservation();
		resDB=new ReservationDB();
		LocalDate resvDate = reservation_Info.Get_Use_Day();
		LocalTime resvTime = reservation_Info.Get_Use_Time();
		String resvService = reservation_Info.Get_Use_Service();
		setTitle("도우미 예약하기");
		
		JPanel title = new JPanel();
		
		JLabel reservation_font = new JLabel("도우미 예약");
		reservation_font.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		reservation_font.setForeground(new Color(5, 0, 153));
		
		// 컴포넌트를 title 컨테이너에 올림.
		title.add(reservation_font);
		JPanel info_panel = new JPanel();
		info_panel.setLayout(new GridLayout(5, 2));
		
		//도우미 검색
		dim1=new Dimension(230,30);
		JPanel helper_search_panel_1 = new JPanel(new FlowLayout(FlowLayout.RIGHT,50,30));
		JLabel helper_search_label = new JLabel("도우미 검색 : ", JLabel.CENTER);
		helper_search_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		helper_search_panel_1.add(helper_search_label);
		
		JPanel helper_search_panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,30));
		helper_search_button = new JButton("도우미 검색");
		helper_search_button.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		helper_search_button.setPreferredSize(dim1);
			
		helper_search_panel_2.add(helper_search_button);
		
		info_panel.add(helper_search_panel_1); 
		info_panel.add(helper_search_panel_2);
		
		//이용날짜
		dim2=new Dimension(70,30);
		JPanel date_panel_1 = new JPanel(new FlowLayout(FlowLayout.RIGHT,50,30));
		JLabel date_label = new JLabel("이용 날짜 : ", JLabel.CENTER);
		date_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		date_panel_1.add(date_label);
			
		JPanel date_panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,32));
		yearCombo = new JComboBox<String>(year);
		yearCombo.setSelectedIndex(Arrays.asList(year).indexOf(Integer.toString(resvDate.getYear())));
		
		monthCombo = new JComboBox<String>(month);
		monthCombo.setSelectedIndex(Arrays.asList(month).indexOf(Integer.toString(resvDate.getMonthValue())));
		
		dayCombo = new JComboBox<String>(day);
		dayCombo.setSelectedIndex(Arrays.asList(day).indexOf(Integer.toString(resvDate.getDayOfMonth())));
		
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
		time_panel_1.add(time_label);
					
		JPanel time_panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,32));
		hourCombo = new JComboBox<String>(hour);
		hourCombo.setSelectedIndex(Arrays.asList(hour).indexOf(Integer.toString(resvTime.getHour())));
		
		minCombo = new JComboBox<String>(min);
		minCombo.setSelectedIndex(Arrays.asList(min).indexOf(Integer.toString(resvTime.getMinute())));
		
		hourCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		minCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
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
		service_panel_1.add(service_label);
					
		JPanel service_panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,32));
		serviceCombo = new JComboBox<String>(service);
		serviceCombo.setSelectedIndex(Arrays.asList(service).indexOf(resvService));
		
		serviceCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		serviceCombo.setPreferredSize(dim1);
		service_panel_2.add(serviceCombo);
		
		info_panel.add(service_panel_1); 
		info_panel.add(service_panel_2);		
		
		//"취소","다음" Button
		dim4=new Dimension(150,30);
		JPanel cancel_panel = new JPanel(new FlowLayout(FlowLayout.RIGHT,50,30));
		cancel_button = new JButton("취소");
		cancel_button.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		cancel_button.setPreferredSize(dim4);
		
		JPanel next_panel = new JPanel(new FlowLayout(FlowLayout.LEFT,50,30));
		next_button = new JButton("다음");
		next_button.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		next_button.setPreferredSize(dim4);
		
		cancel_panel.add(cancel_button); 
		next_panel.add(next_button);
		
		info_panel.add(cancel_panel); 
		info_panel.add(next_panel);
		
		//out_info_panel
		JPanel out_info_panel = new JPanel();
		out_info_panel.setLayout(new FlowLayout());
		out_info_panel.add(info_panel);
		
		setLayout(new BorderLayout());
		
		add(title, BorderLayout.NORTH);
		add(out_info_panel, BorderLayout.CENTER);
		
		
		
		// 이벤트 처리
		//도우미 검색 Action
		helper_search_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//도우미 검색 창으로 넘어감
				new PetSitterSearchGUI("도우미 검색 서비스");
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
					//d.setEnabled(false);
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
					//h.setEnabled(false);
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
					//m.setEnabled(false);
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
					next_button.setText("결제");
					//meau.setEnabled(false);
				}
					
				else if (service_choose.equals("병원 동행")) {
					next_button.setText("병원 예약");
					//meau.setEnabled(false);
				}
					
				else if(service_choose.equals("반려동물 미용샵 동행")) {
					next_button.setText("미용샵 예약");
					//meau.setEnabled(false);
				}
			}
		});
		
		cancel_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//메인 창으로 넘어감
				dispose();  // 현재의 frame을 종료시키는 메서드.
			}
		});

		next_button.addActionListener(new ActionListener() {

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
					res.setService(service_choose);
					res.setCost(cost);
					resDB.dataUpload(res);
					
					dispose();
				}					
				else if(n.getText().equals("병원 예약")) {
					JOptionPane.showMessageDialog(null,"병원을 예약하시겠습니까?");
					//병원 예약창으로 넘어감
					new AnimalHospitalReservationGUI("병원 예약 서비스");
					dispose();
				}
				else if(n.getText().equals("미용샵 예약")) {
					JOptionPane.showMessageDialog(null,"반려동물 미용샵을 예약하시겠습니까?");
					//미용샵 예약창으로 넘어감
					new PetGroomingSalonReservationGUI("반려동물 미용실 예약 서비스");
					dispose();
				}
				
			}
			
		});
		setBounds(200, 200, 800, 600);
		
		setResizable(false);  // 화면 크기 고정하는 작업
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		
		requestFocus();
		
	}
	
	public Reservation_helper_GUI() {
		res=new Reservation();
		resDB=new ReservationDB();
		setTitle("도우미 예약하기");
		
		JPanel title = new JPanel();
		
		JLabel reservation_font = new JLabel("도우미 예약");
		reservation_font.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		reservation_font.setForeground(new Color(5, 0, 153));
		
		// 컴포넌트를 title 컨테이너에 올림.
		title.add(reservation_font);
		JPanel info_panel = new JPanel();
		info_panel.setLayout(new GridLayout(5, 2));
		
		//도우미 검색
		dim1=new Dimension(230,30);
		JPanel helper_search_panel_1 = new JPanel(new FlowLayout(FlowLayout.RIGHT,50,30));
		JLabel helper_search_label = new JLabel("도우미 검색 : ", JLabel.CENTER);
		helper_search_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		helper_search_panel_1.add(helper_search_label);
		
		JPanel helper_search_panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,30));
		helper_search_button = new JButton("도우미 검색");
		helper_search_button.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		helper_search_button.setPreferredSize(dim1);
			
		helper_search_panel_2.add(helper_search_button);
		
		info_panel.add(helper_search_panel_1); 
		info_panel.add(helper_search_panel_2);
		
		//이용날짜
		dim2=new Dimension(70,30);
		JPanel date_panel_1 = new JPanel(new FlowLayout(FlowLayout.RIGHT,50,30));
		JLabel date_label = new JLabel("이용 날짜 : ", JLabel.CENTER);
		date_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		date_panel_1.add(date_label);
			
		JPanel date_panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,32));
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
		time_panel_1.add(time_label);
					
		JPanel time_panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,32));
		hourCombo = new JComboBox<String>(hour);
		minCombo = new JComboBox<String>(min);
		hourCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		minCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
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
		service_panel_1.add(service_label);
					
		JPanel service_panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,32));
		serviceCombo = new JComboBox<String>(service);
		serviceCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		serviceCombo.setPreferredSize(dim1);
		service_panel_2.add(serviceCombo);
		
		info_panel.add(service_panel_1); 
		info_panel.add(service_panel_2);		
		
		//"취소","다음" Button
		dim4=new Dimension(150,30);
		JPanel cancel_panel = new JPanel(new FlowLayout(FlowLayout.RIGHT,50,30));
		cancel_button = new JButton("취소");
		cancel_button.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		cancel_button.setPreferredSize(dim4);
		
		JPanel next_panel = new JPanel(new FlowLayout(FlowLayout.LEFT,50,30));
		next_button = new JButton("다음");
		next_button.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		next_button.setPreferredSize(dim4);
		
		cancel_panel.add(cancel_button); 
		next_panel.add(next_button);
		
		info_panel.add(cancel_panel); 
		info_panel.add(next_panel);
		
		
		//out_info_panel
		JPanel out_info_panel = new JPanel();
		out_info_panel.setLayout(new FlowLayout());
		out_info_panel.add(info_panel);
		
		setLayout(new BorderLayout());
		
		add(title, BorderLayout.NORTH);
		add(out_info_panel, BorderLayout.CENTER);
		
		// 이벤트 처리
		//도우미 검색 Action
		helper_search_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//도우미 검색 창으로 넘어감
				new PetSitterSearchGUI("도우미 검색 서비스");
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
					//d.setEnabled(false);
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
					//h.setEnabled(false);
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
					//m.setEnabled(false);
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
					next_button.setText("결제");
					//meau.setEnabled(false);
				}
					
				else if (service_choose.equals("병원 동행")) {
					next_button.setText("병원 예약");
					//meau.setEnabled(false);
				}
					
				else if(service_choose.equals("반려동물 미용샵 동행")) {
					next_button.setText("미용샵 예약");
					//meau.setEnabled(false);
				}
			}
		});
		
		cancel_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//메인 창으로 넘어감
				dispose();  // 현재의 frame을 종료시키는 메서드.
			}
		});

		next_button.addActionListener(new ActionListener() {

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
					res.setService(service_choose);
					res.setCost(cost);
					resDB.dataUpload(res);
					
					dispose();
				}					
				else if(n.getText().equals("병원 예약")) {
					JOptionPane.showMessageDialog(null,"병원을 예약하시겠습니까?");
					//병원 예약창으로 넘어감
					new AnimalHospitalReservationGUI("병원 예약 서비스");
					dispose();
				}
				else if(n.getText().equals("미용샵 예약")) {
					JOptionPane.showMessageDialog(null,"반려동물 미용샵을 예약하시겠습니까?");
					//미용샵 예약창으로 넘어감
					new PetGroomingSalonReservationGUI("반려동물 미용실 예약 서비스");
					dispose();
				}
				
			}
			
		});
		setBounds(200, 200, 800, 600);
		
		setResizable(false);  // 화면 크기 고정하는 작업
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		
		requestFocus();
		
	}
}

