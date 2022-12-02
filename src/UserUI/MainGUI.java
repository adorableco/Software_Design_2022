package UserUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import UserUI.ReserVation_Package.Reservation_Gui;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel mainSubPanel = new JPanel();
		mainSubPanel.setBackground(new Color(255, 255, 255));
		mainSubPanel.setBounds(0, 0, 156, 572);
		frame.getContentPane().add(mainSubPanel);
		mainSubPanel.setLayout(null);
		
		JButton button1 = new JButton("도우미 예약");
		button1.setBounds(25, 29, 103, 29);
		mainSubPanel.add(button1);
		
		JButton button2 = new JButton("예약 정보 관리");
		button2.setBounds(25, 93, 103, 29);
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Reservation_Gui();
				
			}
		});
		mainSubPanel.add(button2);
	
		
		JButton button3 = new JButton("반려동물 관리");
		button3.setBounds(25, 163, 103, 29);
		button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserGUI();
			}
		});
		mainSubPanel.add(button3);
		JLabel lblPitAPat = new JLabel();
		lblPitAPat.setFont(new Font("Lucida Grande", Font.PLAIN, 43));
		lblPitAPat.setBounds(384, 200, 371, 111);
		frame.getContentPane().add(lblPitAPat);
	}
}
