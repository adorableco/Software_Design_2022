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
	
	String Menu[] = {"(1) 리뷰 작성", "(2) 예약 변경", "(3) 예약 취소"};
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
				System.out.println("MenuList 사이즈 " + MenuList.size());
				if(MenuList.size() == 0) {
					subMenuList.add("리뷰 작성을 완료하시겠습니까? 완료하실꺼면 버튼을 눌러주세요");
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
			informationWindow.setText("리뷰 작성 카테고리를 선택하시겠습니까?(선택시 버튼을 눌러주세요)");
			MenuList.add("작성하실 예약목록을 선택해주세요(예약목록을 번호로 적으시면 됩니다.)");
			MenuList.add("작성일자를 적어주시기 바랍니다.(0000-00-00 형식으로 작성할 것)");
			MenuList.add("리뷰란을 작성해주시기 바랍니다.");
			break;
		case(1):
			informationWindow.setText("예약 변경을 선택 하시겠습니까?");
			MenuList.add("변경하실 예약의 해당 날짜를 입력해주세요.(0000.00.00 형식에 맞게 입력할 것)");
			MenuList.add("변경하실 ");
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
				informationWindow.setText("작성 완료");
			}
		}
	}
	
	
	
	
	
}



public class Reservation_Gui {
	public static void main(String[] args) {
		new CheckReservationMode();
	}
}
