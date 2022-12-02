package UserUI.ReserVation_Package;

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

import PDLayer.Reservation_Info;

import java.util.*;



class ReservationChange extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;

	
	private JPanel listPanel = new JPanel();
//	private JPanel areaPanel = new JPanel();
//	
	private JTextArea informationGuideWindow = new JTextArea("");

	private JTextArea informationTA = new JTextArea(10,3);
	
	private JScrollPane scrollPane = new JScrollPane(informationTA);
	
	private JPanel informationRpanel = new JPanel(new GridLayout(1,1));
	
	private JPanel informationPanel = new JPanel(new GridLayout(2,1));
	private JLabel informationWindow = new JLabel("Select the Menu");
	private JPanel informationWpanel = new JPanel(new GridLayout(1,2));
	private JButton informationWRokBtn = new JButton("Confirm");
	
	private String Menu[] = {"(2) 예약 변경", "(2-1) 날짜변경", "(2-2) 저장하기"};
	private int Selected_Menu_Index;
	
	private Reservation_Info SelectedReservation;
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
	
	public ReservationChange(Reservation_Info reservation_Info, File Selected_File) {
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
					informationWindow.setText("변경할 날짜를 입력하세요");
					break;
				case 2:
					informationWindow.setText("저장하시겠습니까??");
				}
			}
		});
//		areaPanel.add(scrollPane);
		
		informationWpanel.add(scrollPane);
		informationWpanel.add(informationWRokBtn);
		
		informationPanel.add(informationWindow);
		informationPanel.add(informationWpanel);
		informationRpanel.add(informationGuideWindow);
//		informationRWpanel.add(informationWindow);
		
		
		super.setLayout(new BorderLayout(20,30));
		add(listPanel, BorderLayout.WEST);
		add(informationRpanel, BorderLayout.CENTER);
		add(informationPanel, BorderLayout.SOUTH);
		
		informationGuideWindow.setText(this.SelectedReservation.Get_name() + "\n");
		informationGuideWindow.append("예약상태 : " + Integer.toString(this.SelectedReservation.Get_State()) + "\n");
		
		
		this.setVisible(true);
		this.setFocusable(true);
		
		
		informationWRokBtn.addActionListener(this);
		informationTA.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("enter " + informationTA.getText().length());
				}
			} 
			
		});
		
	}
	public void actionPerformed(ActionEvent e) {
		dispose();
//		switch(Selected_Menu_Index) {
//		case(1):
//			int input = Integer.valueOf(informationTA.getText());
//			informationTA.setText("");
//			SelectedReservation.changeState(input);
//			dispose();
//			new ReservationChange(SelectedReservation, Selected_File);
//			break;
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
//		}
	}	
	
}



public class Reservation_Gui_ReservationChange {
	public Reservation_Gui_ReservationChange(Reservation_Info reservation_Info, File Selected_File) {
		new ReservationChange(reservation_Info, Selected_File);
	}
}
