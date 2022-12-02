package CheckingReservation;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.*;



class ReservationCancel extends JFrame implements ActionListener{
	
private static final long serialVersionUID = 1L;

	
	private JPanel listPanel = new JPanel();
//	private JPanel areaPanel = new JPanel();
//	
	private JTextArea informationGuideWindow = new JTextArea("");

	
	private JPanel informationRpanel = new JPanel(new GridLayout(1,1));
	
	private JPanel informationPanel = new JPanel(new GridLayout(2,1));
	private JLabel informationWindow = new JLabel("Select the Menu");
	private JPanel informationWpanel = new JPanel(new GridLayout(1,2));
	private JButton informationWRokBtn = new JButton("Cancel");
	
	private Reservation_Info SelectedReservation;
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
	
	public ReservationCancel(Reservation_Info reservation_Info, File Selected_File) {
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
		
		
	}
	public void actionPerformed(ActionEvent e) {
		Selected_File.delete();
		dispose();
	}
	
	
}




public class Reservation_Gui_ReservationCancel {
	public Reservation_Gui_ReservationCancel(Reservation_Info reservation_Info, File Selected_File) {

		new ReservationCancel(reservation_Info, Selected_File);
	}
}