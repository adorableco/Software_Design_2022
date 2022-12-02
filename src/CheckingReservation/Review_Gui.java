package CheckingReservation;
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

import java.util.*;



class ReviewMode extends JFrame implements ActionListener{
	
	
	private static final long serialVersionUID = 1L;

	
	
	private JPanel listPanel = new JPanel();

	

	private JTextArea informationTA = new JTextArea(10,3);
	
	private JScrollPane scrollPane = new JScrollPane(informationTA);
	
	private JPanel informationRpanel = new JPanel(new GridLayout(1,1));
	
	private JPanel informationPanel = new JPanel(new GridLayout(1,1));
//	private JLabel informationWindow = new JLabel("Select the Menu");
	private JPanel informationWpanel = new JPanel(new GridLayout(1,1));
	private JPanel informationBtnPanel = new JPanel(new GridLayout(1,1));
	private JButton informationWRokBtn = new JButton("�����ϱ�");
	
	private JOptionPane ConfirmBtn = new JOptionPane();
	

	private int selectedMenu; // selectedmenu index
	
	String Menu[] = {"(1) ���� �ۼ��ϱ�"};
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
	
	public ReviewMode(/*Reservation_Info reservation_Info, File Selected_File*/) {
		createFrame("Checking Reservation");
		//this.SelectedReservation = reservation_Info;
		//this.Selected_File = Selected_File;
		
		LineBorder line = new LineBorder(Color.black,1);
		informationWpanel.setBorder(line);
		
		GuiMenu.setFixedCellHeight(25);
		GuiMenu.setFixedCellWidth(140);

		
		
		informationWpanel.setPreferredSize(new Dimension(600,47));

		
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
		System.out.println("ADDLIST ������ " + addList.size());
		System.out.println("���ư�");
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == informationWRokBtn) {
			int result = 0;
			result = ConfirmBtn.showConfirmDialog(null, "������ �Ϸ��Ͻðڽ��ϱ�?", "���� �ۼ��ϱ�", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Result �� : " + result);
			if( result == 0) {
				SaveInput();
				 try {
			        	int n = 0;
			            // 1. ���� ��ü ����
			            File file = new File("C:\\Users\\SEC\\OneDrive\\���� ȭ��\\�Ҽ�\\Reservation" + "\\����" + n + ".txt");
			 
			            // 2. ���� ���翩�� üũ �� ����
			            if (!file.exists()) {
			                file.createNewFile();
			            }
			 
			            // 3. Buffer�� ����ؼ� File�� write�� �� �ִ� BufferedWriter ����
			            FileWriter fw = new FileWriter(file);
			            BufferedWriter writer = new BufferedWriter(fw);
			 
			            // 4. ���Ͽ� ����
			            writer.write(addList.get(0));
			            n++;
			           
			            informationTA.setText("");
			            writer.close();
			 
			        } catch (IOException e1) {
			            e1.printStackTrace();
			        }
				JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.");
			}
		}
	}
	
	
	
}



public class Review_Gui {
//	public Review_Gui(Reservation_Info reservation_Info, File Selected_File) {
//		new ReviewMode(reservation_Info, Selected_File);
//	}
	public static void main(String[] args) {
		new ReviewMode();
	}
}
