package gui_package;

import javax.swing.*;
import javax.swing.event.*;
import database_package.AnimalHospitalDBConnector;
import database_package.ReservationDB;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import reservation_package.Company;
import reservation_package.Reservation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;



class CheckReservationMode extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;

	String Menu[] = {"(1) 전체정보 ","(2) 리뷰작성", "(3) 예약 변경", "(4) 예약 취소"};
	
	//Get Reservation Information
	private ArrayList<Reservation> Reserv_Info = new ArrayList<Reservation>();
	
	
	private JPanel listPanel = new JPanel();
	
	private JPanel informationRpanel = new JPanel(new GridLayout(1,1));
	
	private JPanel informationPanel = new JPanel(new GridLayout(2,1));
	private JLabel informationWindow = new JLabel("Select the Menu");
	private JPanel informationWpanel = new JPanel(new GridLayout(1,2));
	private JButton informationWRokBtn = new JButton("이동하기");
	private JButton BackBtn = new JButton("뒤로가기");
	
	private JList<String> GuiMenu = new JList<String>(Menu);
	private ArrayList<String> MenuList = new ArrayList<String>();
	private ArrayList<String> addList = new ArrayList<String>();
	private ArrayList<String> subMenuList = new ArrayList<String>();

	DefaultListModel<String> Accesable_Info = new DefaultListModel<>();
	private JList informationGuideWindow;
	


	private int selectedMenu = 0;
	private int InformationIndex;
	private File[] flist;
	
	
	
	//Information

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
	
	
	
	@SuppressWarnings("deprecation")
	public CheckReservationMode() {
		createFrame("Checking Reservation");
		
		//Get Reservation Information
		try {
			
			File Path = new File("./DataBase/Reservation/");
			flist = Path.listFiles(new FilenameFilter() {
				
				@Override
				public boolean accept(File dir, String name) {
					return !name.equals(".DS_Store");
				}
			});
			
			Arrays.sort(flist);
			
			for(int i = 0; i < flist.length; i++) {
				// Contain User name
				if(flist[i].getName().contains("1_Res")) {
					BufferedReader br = new BufferedReader(new FileReader(flist[i]));
					String content;
					String name;
					int state;
					while((content = br.readLine()) != null) {
						String[] contentlist = content.split(" ");
						this.Reserv_Info.add(
								new Reservation(
										LocalDate.of(Integer.parseInt(contentlist[0]), Integer.parseInt(contentlist[1]), Integer.parseInt(contentlist[2])),
										LocalTime.of( Integer.parseInt(contentlist[3]),Integer.parseInt(contentlist[4]),0),
										contentlist[5], Integer.parseInt(contentlist[6]), Integer.parseInt(contentlist[7]), contentlist[8],contentlist[9])
										);
					}
				}
			}
			
		}catch (FileNotFoundException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		this.Reserv_Info.add(new Reservation("예약 1",1));
//		this.Reserv_Info.add(new Reservation("예약 2",2));
//		this.Reserv_Info.add(new Reservation("예약 3",3));
//		this.Reserv_Info.add(new Reservation("예약 4",4));
		
		//
		for(int i = 0; i < Reserv_Info.size(); i++) {
		switch(Reserv_Info.get(i).Get_State()) {
		case(0):
			Accesable_Info.addElement( "이용예정:            " + Reserv_Info.get(i).Get_Use_Day());
			break;
		case(1):
			Accesable_Info.addElement( "이용완료:            " + Reserv_Info.get(i).Get_Use_Day());
			break;
		case(2):
			Accesable_Info.addElement( "예약취소:            " + Reserv_Info.get(i).Get_Use_Day());
			break;
		}
	}
		
		this.informationGuideWindow = new JList(Accesable_Info);
	
		
		LineBorder line = new LineBorder(Color.black,1);
		informationWpanel.setBorder(line);
		
		GuiMenu.setFixedCellHeight(25);
		GuiMenu.setFixedCellWidth(140);
		GuiMenu.setSelectedIndex(0);

		
		
		informationWpanel.setPreferredSize(new Dimension(600,47));
		informationWpanel.add(BackBtn);
		
		informationGuideWindow.setOpaque(true);
		informationGuideWindow.setBackground(Color.GRAY);
		informationGuideWindow.setForeground(Color.white);
		informationGuideWindow.setFont(new Font("Serief", Font.BOLD, 15));
		
		informationWindow.setOpaque(true);
		informationWindow.setBackground(Color.LIGHT_GRAY);
		informationWindow.setFont(new Font("Serief", Font.BOLD, 15));
		
		
		listPanel.add(GuiMenu);
		GuiMenu.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int selection = GuiMenu.getSelectedIndex();
				displayGuiMenu(selection);
				
			}
		});
		informationGuideWindow.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				InformationIndex = informationGuideWindow.getSelectedIndex();
				System.out.println(InformationIndex);
//				int state = Reserv_Info.get(InformationIndex).Get_State();
//				switch(state) {
//				case(0):
//					
//				}
			}
		});
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


		
	
	
	public void displayGuiMenu(int selection) {
		selectedMenu = selection;
		
		switch(selection) {

		case(0):
			informationWindow.setText("상세 보기");
			Accesable_Info.clear();
			for(int i = 0; i < Reserv_Info.size(); i++) {
				switch(Reserv_Info.get(i).Get_State()) {
				case(0):
					Accesable_Info.addElement( "이용예정:            " + Reserv_Info.get(i).Get_Use_Day());
					break;
				case(1):
					Accesable_Info.addElement( "이용완료:            " + Reserv_Info.get(i).Get_Use_Day());
					break;
				case(2):
					Accesable_Info.addElement( "예약취소:            " + Reserv_Info.get(i).Get_Use_Day());
					break;
				}
			}
			break;
		case(1):
			informationWindow.setText("리뷰 작성 카테고리를 선택하시겠습니까?");
			Accesable_Info.clear();
			
			// State == 0 -> 
			for(int i = 0;i < Reserv_Info.size(); i++) {
				if( Reserv_Info.get(i).Get_State() == 1 )
					Accesable_Info.addElement( Reserv_Info.get(i).Get_Use_Day().toString());

				else 
					Accesable_Info.addElement( "" );
			}
			
			break;
		case(2):
			informationWindow.setText("예약 변경을 선택 하시겠습니까?");
			Accesable_Info.clear();
			for(int i = 0;i < Reserv_Info.size(); i++) {
				if( Reserv_Info.get(i).Get_State() == 0 )
					Accesable_Info.addElement( Reserv_Info.get(i).Get_Use_Day().toString() );
				else 
					Accesable_Info.addElement( "" );
			}
			
			
			break;
		case(3):
			informationWindow.setText("예약 취소 선택 하시겠습니까?");
			Accesable_Info.clear();
			

			for(int i = 0;i < Reserv_Info.size(); i++) {
				if( Reserv_Info.get(i).Get_State() == 0 )
					Accesable_Info.addElement( Reserv_Info.get(i).Get_Use_Day().toString() );

				else 
					Accesable_Info.addElement( "" );
			}
			break;
		}
	}
//	@Override
//	public void valueChanged(ListSelectionEvent e) {
//		int selection = GuiMenu.getSelectedIndex();
//		if(!e.getValueIsAdjusting()) {
//			System.out.println(e.getSource().getClass().fi;
//
//			displayGuiMenu(selection);
//				InformationIndex = informationGuideWindow.getSelectedIndex();
//		}
//		// TODO Auto-generated method stub
//		
//	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(informationWRokBtn.getText().equals("이동하기")) {
			informationWRokBtn.setText("새로고침");
			switch(this.selectedMenu) {
				case (0): // 전체 메뉴 
					int temp = InformationIndex;
					Accesable_Info.clear();
					System.out.println(temp);
					
					// 정보 Print하
					if(Reserv_Info.get(temp).Get_State() == 0) Accesable_Info.addElement("이용예정");
					else if(Reserv_Info.get(temp).Get_State() == 1) Accesable_Info.addElement("이용완료");
					else Accesable_Info.addElement("예약 취소");
					Accesable_Info.addElement( "이용 날짜 : " + Reserv_Info.get(temp).Get_Use_Day() );
					Accesable_Info.addElement( "이용 시간 : " + Reserv_Info.get(temp).Get_Use_Time() );
					Accesable_Info.addElement( "할일 목록 : " + Reserv_Info.get(temp).Get_Use_Service() );
					Accesable_Info.addElement( "결제 금액 : " + Integer.toString(Reserv_Info.get(temp).Get_Cost()));
					if(!(Reserv_Info.get(temp).Get_Review().equals("0"))) {
						Accesable_Info.addElement( "리뷰내용 : " + Reserv_Info.get(temp).Get_Review());
					}
					else {
						Accesable_Info.addElement( "리뷰내용 : 없음");
					}
					break;
					
					
				case (1):
					new Reservation_Check_Gui_ReservatioonReview(Reserv_Info.get(InformationIndex), flist[InformationIndex]);
					break;
				case (2): // 예약변경
					new Reservation_Check_Gui_ReservationChange(Reserv_Info.get(InformationIndex), flist[InformationIndex] );
					break;
				case (3): // 예약 취소
					new Reservation_Check_Gui_ReservationCancel(Reserv_Info.get(InformationIndex), flist[InformationIndex]);
					break;
			}
			
		}
		else {
			dispose();
			new Reservation_Check_Gui();
		}
	}
	
}



public class Reservation_Check_Gui {
	
	public Reservation_Check_Gui(){

		new CheckReservationMode();
	}
	public static void main(String[] args) {
		new CheckReservationMode();
	}
}
