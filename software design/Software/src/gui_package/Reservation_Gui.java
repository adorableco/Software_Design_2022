package gui_package;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.*;



class CheckReservationMode extends JFrame implements ActionListener, ListSelectionListener{
	
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
	
	private JPanel listPanel = new JPanel();
//	private JPanel areaPanel = new JPanel();
//	
	private JLabel informationGuideWindow = new JLabel("Welcome to CheckReservation Section", JLabel.CENTER);
	
	
	

	private JTextArea informationTA = new JTextArea(10,3);
	
	private JScrollPane scrollPane = new JScrollPane(informationTA);
	
	private JPanel informationRpanel = new JPanel(new GridLayout(1,1));
	
	private JPanel informationPanel = new JPanel(new GridLayout(2,1));
	private JLabel informationWindow = new JLabel("Select the Menu");
	private JPanel informationWpanel = new JPanel(new GridLayout(1,2));
	private JButton informationWRokBtn = new JButton("Confirm");
	
	String Menu[] = {"(1) ���� �ۼ�", "(2) ���� ����", "(3) ���� ���"};
	private JList<String> GuiMenu = new JList<String>(Menu);
	private ArrayList<String> MenuList = new ArrayList<String>();
	private ArrayList<String> addList = new ArrayList<String>();
	private ArrayList<String> subMenuList = new ArrayList<String>();
	
	
	
	public CheckReservationMode() {
		createFrame("Checking Reservation");
		
//		LineBorder line = new LineBorder(Color.black,1);
//		informationWpanel.setBorder(line);
		
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
//		areaPanel.add(scrollPane);
		
		informationWpanel.add(scrollPane);
		informationWpanel.add(informationWRokBtn);
		
		informationPanel.add(informationWindow);
		informationPanel.add(informationWpanel);
		informationRpanel.add(informationGuideWindow);
//		informationRWpanel.add(informationWindow);
		
		
		add(listPanel, BorderLayout.WEST);
		add(informationRpanel, BorderLayout.CENTER);
		add(informationPanel, BorderLayout.SOUTH);
		
		
		
		this.setVisible(true);
		this.setFocusable(true);
		
		
		informationWRokBtn.addActionListener(this);
		informationTA.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("enter " + informationTA.getText().length());
					if(MenuList.size() > 0) {
						doNextInput();
					}else {
						doSubNextInput();
					}
				}
			} 
			
		});
		
		GuiMenu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GuiMenu.addListSelectionListener(this);
	}
	
	private int selectedMenu;
	
	public void doNextInput() {
		switch(selectedMenu) {
		case 0:
			if(MenuList.size() > 0) {
				informationWindow.setText(MenuList.get(0));
				MenuList.remove(0);
				System.out.println("MenuList ������ " + MenuList.size());
				if(MenuList.size() == 0) {
					subMenuList.add("���� �ۼ��� �Ϸ��Ͻðڽ��ϱ�? �Ϸ��Ͻǲ��� ��ư�� �����ּ���");
				}
			}
			break;
		}
	}
	
	public void doSubNextInput() {
		if(subMenuList.size() > 0) {
			informationWindow.setText(subMenuList.get(0));
			subMenuList.remove(0);
		}
	}
	
	
		
	
	
	public void displayGuiMenu(int selection) {
		selectedMenu = selection;
		
		
		switch(selection) {
		case(0):
			informationWindow.setText("���� �ۼ� ī�װ��� �����Ͻðڽ��ϱ�?(���ý� ��ư�� �����ּ���)");
			MenuList.add("�ۼ��Ͻ� �������� �������ּ���(�������� ��ȣ�� �����ø� �˴ϴ�.)");
			MenuList.add("�ۼ����ڸ� �����ֽñ� �ٶ��ϴ�.(0000-00-00 �������� �ۼ��� ��)");
			MenuList.add("������� �ۼ����ֽñ� �ٶ��ϴ�.");
			break;
		case(1):
			informationWindow.setText("���� ������ ���� �Ͻðڽ��ϱ�?");
			MenuList.add("�����Ͻ� ������ �ش� ��¥�� �Է����ּ���.(0000.00.00 ���Ŀ� �°� �Է��� ��)");
			MenuList.add("�����Ͻ� ");
			break;
		}
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		int selection = GuiMenu.getSelectedIndex();
		if(!e.getValueIsAdjusting()) {
			displayGuiMenu(selection);
		}
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(selectedMenu) {
		case 0:
			if(e.getSource() == informationWRokBtn) {
				informationWindow.setText("�ۼ� �Ϸ�");
			}
		}
	}
	
	
	
	
	
}



public class Reservation_Gui {
	public static void main(String[] args) {
		new CheckReservationMode();
	}
}
