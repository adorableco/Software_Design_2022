package UserUI.ReserVation_Package;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import PDLayer.Reservation_Info;

import java.util.*;



class ReviewMode extends JFrame implements ActionListener{
	
	
	private static final long serialVersionUID = 1L;

	
	
	private JPanel listPanel = new JPanel();

	

	private JTextArea informationTA = new JTextArea(10,5);
	
	private JScrollPane scrollPane = new JScrollPane(informationTA);
	
	private JPanel informationRpanel = new JPanel(new GridLayout(1,1));
	
	private JPanel informationPanel = new JPanel(new GridLayout(1,1));
//	private JLabel informationWindow = new JLabel("Select the Menu");
	private JPanel informationWpanel = new JPanel(new GridLayout(1,1));
	private JPanel informationBtnPanel = new JPanel(new GridLayout(1,1));
	private JButton informationWRokBtn = new JButton("저장하기");
	
	private JOptionPane ConfirmBtn = new JOptionPane();
	

	private int selectedMenu; // selectedmenu index
	
	String Menu[] = {"(1) 리뷰 작성하기"};
	private Reservation_Info SelectedReservation;
	private File Selected_File;
	
	private JList<String> GuiMenu = new JList<String>(Menu);
	private ArrayList<String> MenuList = new ArrayList<String>();
	private ArrayList<String> subMenuList = new ArrayList<String>();
	
	private ArrayList<String> addList = new ArrayList<String>();
	
	void createFrame(String title) {
		this.setTitle(title);
		this.setSize(800,400);
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frame = getSize();
		
		int xPos = (int)(screen.getWidth() / 2 - frame.getWidth()/2);
		int yPos = (int)(screen.getHeight() / 2 - frame.getHeight()/2);
		
		this.setLocation(xPos, yPos);
		this.setResizable(true);
		
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
	}
	
	public ReviewMode(Reservation_Info reservation_Info, File selected_File2/*Reservation_Info reservation_Info, File Selected_File*/) {
		createFrame("Checking Reservation");
		this.SelectedReservation = reservation_Info;
		this.Selected_File = Selected_File;
		
		LineBorder line = new LineBorder(Color.black,1);
		informationWpanel.setBorder(line);
		
		GuiMenu.setFixedCellHeight(25);
		GuiMenu.setFixedCellWidth(140);
		

		informationTA.setOpaque(true);
		informationTA.setBackground(Color.white);
		informationTA.setForeground(Color.black);
		informationTA.setFont(new Font("Serief", Font.BOLD, 15));
		
		

		
		listPanel.add(GuiMenu);
		GuiMenu.setSelectedIndex(0);

		
		informationWpanel.add(scrollPane);
		informationBtnPanel.add(informationWRokBtn);

		informationPanel.add(informationWpanel);

		
		
		add(listPanel, BorderLayout.WEST);

		add(informationPanel, BorderLayout.CENTER);
		add(informationBtnPanel, BorderLayout.SOUTH);
		
		
		
		this.setVisible(true);
		this.setFocusable(true);
		
		
		informationWRokBtn.addActionListener(this);
		
	}
	
	
	public void SaveInput() {
		addList.add(informationTA.getText());
		System.out.println("ADDLIST 사이즈 " + addList.size());
		System.out.println("돌아감");
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == informationWRokBtn) {
			int result = 0;
			result = ConfirmBtn.showConfirmDialog(null, "저장을 완료하시겠습니까?", "리뷰 작성하기", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Result 값 : " + result);
			if( result == 0) {
				SaveInput();
				 try {
			        	int n = 0;
			            // 1. 파일 객체 생성
			            File file = new File("C:\\Users\\SEC\\OneDrive\\바탕 화면\\소설\\Reservation" + "\\예약1.txt");
			 
			            // 2. 파일 존재여부 체크 및 생성
			            if (!file.exists()) {
			                file.createNewFile();
			            }
			 
			            // 3. Buffer를 사용해서 File에 write할 수 있는 BufferedWriter 생성
			            FileWriter fw = new FileWriter(file,true);
			            BufferedWriter writer = new BufferedWriter(fw);
			 
			            // 4. 파일에 쓰기
			 
			            writer.write("\n" + addList.get(0) );
			            n++;
			           
			            informationTA.setText("");
			            
			            writer.close();
			 
			        } catch (IOException e1) {
			            e1.printStackTrace();
			        }
				JOptionPane.showMessageDialog(null, "저장이 완료되었습니다.");
				dispose();
			}
		}
	}
	
	
	
}



public class Reservation_Gui_ReservatioonReview {
	public Reservation_Gui_ReservatioonReview(Reservation_Info reservation_Info, File Selected_File) {
		new ReviewMode(reservation_Info, Selected_File);
	}
}
