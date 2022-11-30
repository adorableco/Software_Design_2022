

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Reservation_helper_GUI extends JFrame {

	private Dimension dim1,dim2,dim3,dim4;
	private String[] year= {"2022","2023","2024","2025","2026","2027","2028","2029","2030"};
	private String[] month= {"01","02","03","04","05","06","07","08","09","10","11","12"};
	private String[] day= {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	private String[] hour= {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"};
	private String[] min= {"00","10","20","30","40","50"};
	private String[] service= {"산책","집 방문","병원 동행","반려동물 미용샵 동행"};
	private JButton helper_search_button;
	private JComboBox<String> yearCombo;
	private JComboBox<String> monthCombo;
	private JComboBox<String> dayCombo;
	private JComboBox<String> hourCombo;
	private JComboBox<String> minCombo;
	private JComboBox<String> serviceCombo;
	private JButton cancel_button;
	private JButton next_button;
	String service_choose;
	String ye;
	String mo;
	String da;
	String ho;
	String mi;
	String[] data=new String[6];
	
	String[] setdata() {
		data[0]=ye;
		data[1]=mo;
		data[2]=da;
		data[3]=ho;
		data[4]=mi;
		data[5]=service_choose;
		
		return data;
	}
	
	public Reservation_helper_GUI() {
		
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
			}
		});
		
		//이용 날짜 Action
		yearCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox y=(JComboBox)e.getSource();
				ye=y.getSelectedItem().toString();
				y.setEnabled(false);
			}
			
		});
		monthCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox m=(JComboBox)e.getSource();
				mo=m.getSelectedItem().toString();
				m.setEnabled(false);
			}
			
		});
		dayCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox d=(JComboBox)e.getSource();
				da=d.getSelectedItem().toString();
				d.setEnabled(false);
			}
			
		});
		
		//이용 시간 Action
		hourCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox h=(JComboBox)e.getSource();
				ho=h.getSelectedItem().toString();
				h.setEnabled(false);
			}
			
		});
		minCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox m=(JComboBox)e.getSource();
				mi=m.getSelectedItem().toString();
				m.setEnabled(false);
			}
			
		});
		
		//이용 서비스 Action
		serviceCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox meau = (JComboBox)e.getSource();
				service_choose = meau.getSelectedItem().toString();
				meau.setEnabled(false);
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
				//서비스콤보가 "산책","집방문"이면 결제창으로 넘어감
				//넘어가기전에"결제하시겠습니까" 메세지창 띄우기 
				//서비스콤보가 "병원 동행","미용샵 동행"이면 각각의 예약 창으로 넘어감
				if(service_choose.equals("산책") || service_choose.equals("집 방문")) {
					JOptionPane.showMessageDialog(null,"결제하시겠습니까?");
					//결제창 넘어감??
				}
				else if(service_choose.equals("병원 동행")) {
					JOptionPane.showMessageDialog(null,"병원을 예약하시겠습니까?");
				}
				else if(service_choose.equals("반려동물 미용샵 동행")) {
					JOptionPane.showMessageDialog(null,"미용샵을 예약하시겠습니까?");
				}
				
			}
			
		});
		
		//콤보에서 선택한 item을 변수들에 저장해둠 
		//Reservation_helper.java에서 그 변수들 가지고 저장함\
		
		setBounds(200, 200, 800, 600);
		
		setResizable(false);  // 화면 크기 고정하는 작업
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		
		requestFocus();
		
	}
	public static void main(String[] args) {
		new Reservation_helper_GUI();
	}
}

