package ReserVation_Package;

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

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.*;
import java.util.List;



class CheckReservationMode extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;

	String Menu[] = {"(1) 전체정보 ","(2) 리뷰작성", "(3) 예약 변경", "(4) 예약 취소"};
	
	//Get Reservation Information
	private ArrayList<Reservation_Info> Reserv_Info = new ArrayList<Reservation_Info>();
	
	
	private JPanel listPanel = new JPanel();
	
	private JPanel informationRpanel = new JPanel(new GridLayout(1,1));
	
	private JPanel informationPanel = new JPanel(new GridLayout(2,1));
	private JLabel informationWindow = new JLabel("Select the Menu");
	private JPanel informationWpanel = new JPanel(new GridLayout(1,2));
	private JButton informationWRokBtn = new JButton("이동하기");
	
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
	
	
	
	public CheckReservationMode() {
		createFrame("Checking Reservation");
		
		//Get Reservation Information
		try {
			
			File Path = new File("/Users/sanghee/Desktop/Java/SoftWare_Design/DataBase/Reservation");
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
						this.Reserv_Info.add(new Reservation_Info(contentlist[0], Integer.valueOf(contentlist[1]).intValue()));
					}
				}
			}
			
		}catch (FileNotFoundException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		this.Reserv_Info.add(new Reservation_Info("예약 1",1));
//		this.Reserv_Info.add(new Reservation_Info("예약 2",2));
//		this.Reserv_Info.add(new Reservation_Info("예약 3",3));
//		this.Reserv_Info.add(new Reservation_Info("예약 4",4));
		
		//
		for(int i = 0;i < Reserv_Info.size(); i++) {
			Accesable_Info.addElement( Reserv_Info.get(i).Get_name() );
		}
		
		
		this.informationGuideWindow = new JList(Accesable_Info);
	
		
		LineBorder line = new LineBorder(Color.black,1);
		informationWpanel.setBorder(line);
		
		GuiMenu.setFixedCellHeight(25);
		GuiMenu.setFixedCellWidth(140);
		GuiMenu.setSelectedIndex(0);

		
		
		informationWpanel.setPreferredSize(new Dimension(600,47));
		
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
		
	}


		
	
	
	public void displayGuiMenu(int selection) {
		selectedMenu = selection;
		
		switch(selection) {

		case(0):
			informationWindow.setText("전체 정보");

			Accesable_Info.clear();
			for(int i = 0; i < Reserv_Info.size(); i++) {
				Accesable_Info.addElement( Reserv_Info.get(i).Get_name() );
			}
			break;
		case(1):
			informationWindow.setText("리뷰 작성 카테고리를 선택하시겠습니까?");
			Accesable_Info.clear();
			
			// State == 0 -> 
			for(int i = 0;i < Reserv_Info.size(); i++) {
				if( Reserv_Info.get(i).Get_State() == 0 )
					Accesable_Info.addElement( Reserv_Info.get(i).Get_name() );

				else 
					Accesable_Info.addElement( "" );
			}
			
			break;
		case(2):
			informationWindow.setText("예약 변경을 선택 하시겠습니까?");
			Accesable_Info.clear();
			for(int i = 0;i < Reserv_Info.size(); i++) {
				if( Reserv_Info.get(i).Get_State() == 1 )
					Accesable_Info.addElement( Reserv_Info.get(i).Get_name() );
				else 
					Accesable_Info.addElement( "" );
			}
			
			
			break;
		case(3):
			informationWindow.setText("예약 취소 선택 하시겠습니까?");
			Accesable_Info.clear();
			

			for(int i = 0;i < Reserv_Info.size(); i++) {
				if( Reserv_Info.get(i).Get_State() == 2 )
					Accesable_Info.addElement( Reserv_Info.get(i).Get_name() );

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
					Accesable_Info.addElement( Reserv_Info.get(temp).Get_name() );
					Accesable_Info.addElement( Integer.toString( Reserv_Info.get(temp).Get_State()) );
					break;
				case (1):
					break;
				case (2): // 예약변경
					new Reservation_Gui_ReservationChange(Reserv_Info.get(InformationIndex), flist[InformationIndex] );
					break;
				case (3): // 예약 취소
					new Reservation_Gui_ReservationCancel(Reserv_Info.get(InformationIndex), flist[InformationIndex]);
					break;
			}
			
		}
		else {
			dispose();
			new Reservation_Gui();
		}
	}
	
}



public class Reservation_Gui {
	
	public Reservation_Gui(){

		new CheckReservationMode();
	}
	public static void main(String[] args) {
		new CheckReservationMode();
	}
}
