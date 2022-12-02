package gui_package;
import participant_package.ManagePetInfo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database_package.Pet;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class PetInfoGUI {

	private JFrame frame;
	private JTextField nameText;
	private JTextField breedText;
	private JTextField weightText;
	private JTextField drugText;
	private JTextField hospitalText;
	private JTextField shopText;
	private JTextField illnessText;
	
	private JComboBox typeCombobox = new JComboBox();
	private JComboBox ageCombobox = new JComboBox();
	private JPanel registerFirstPanel = new JPanel();
	public ManagePetInfo info = new ManagePetInfo();
	private JTable table;
	private DefaultTableModel model;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PetInfoGUI window = new PetInfoGUI();
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
	public PetInfoGUI() {
		initialize();
	}
	
	private boolean checkEmpty(String str) {
		if(str.isEmpty())
			return false;
		else return true;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JPanel registerSecondPanel = new JPanel();
		registerSecondPanel.setBounds(154, 0, 646, 572);
		frame.getContentPane().add(registerSecondPanel);
		registerSecondPanel.setVisible(false);
		registerSecondPanel.setLayout(null);
		
		JButton previousButton = new JButton("이전");
		previousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerSecondPanel.setVisible(false);
				registerFirstPanel.setVisible(true);
			}
		});
		previousButton.setBounds(189, 450, 117, 29);
		registerSecondPanel.add(previousButton);
		
		JLabel registerpageLabel2 = new JLabel("반려동물 정보 등록 페이지");
		registerpageLabel2.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		registerpageLabel2.setBounds(229, 70, 188, 16);
		registerSecondPanel.add(registerpageLabel2);
		
		JButton completeButton = new JButton("완료");
		completeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pet pet = info.registerPet();
				if(checkEmpty(nameText.getText()))
						pet.registerName(nameText.getText());
				else {
					JOptionPane.showMessageDialog(null, "필수 항목을 입력해주세요.");
					return;
				}
				
				if(checkEmpty(breedText.getText()))
					pet.registerBreed(breedText.getText());
				else {
					JOptionPane.showMessageDialog(null, "필수 항목을 입력해주세요.");
					return;
				}
				
				String mass = weightText.getText();
				if(checkEmpty(mass)) {
					try {
						pet.registerWeight(Integer.parseInt(mass));
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null,"무게는 숫자로 입력해주세요.");
							(info.n)--;
							return;
							}
				}

				
				pet.registerType(typeCombobox.getSelectedItem().toString());
				pet.registerAge(Integer.parseInt(ageCombobox.getSelectedItem().toString()));
				pet.registerIllness(illnessText.getText());
				pet.registerDrug(drugText.getText());
				pet.registerHospital(hospitalText.getText());
				pet.registerShop(shopText.getText());
				
				model.insertRow(info.n, new Object[] {info.pet[(info.n)-1].name,info.pet[(info.n)-1].breed,info.pet[(info.n)-1].age});
				
				nameText.setText("");;
				breedText.setText("");
				drugText.setText("");
				hospitalText.setText("");
				shopText.setText("");
				illnessText.setText("");
				weightText.setText("");
				
				System.out.println("name : "+pet.name);
				System.out.println("type : " + pet.type);
				System.out.println("breed : "+pet.breed);
				System.out.println("age : "+pet.age);
				System.out.println("weight : "+pet.weight);
				System.out.println("illness : "+pet.illness);
				System.out.println("drug : "+pet.drug);
				System.out.println("hospital : "+pet.hospital);
				System.out.println("shop : "+pet.shop);
				
				registerSecondPanel.setVisible(false);
			}
		});
		completeButton.setBounds(352, 450, 117, 29);
		registerSecondPanel.add(completeButton);
		
		JLabel illnessLabel = new JLabel("앓고 있는 질병");
		illnessLabel.setBounds(189, 156, 89, 16);
		registerSecondPanel.add(illnessLabel);
		
		illnessText = new JTextField("");
		illnessText.setColumns(10);
		illnessText.setBounds(334, 145, 130, 40);
		registerSecondPanel.add(illnessText);
		
		JLabel drugLabel = new JLabel("복용 중인 약 명칭");
		drugLabel.setBounds(192, 209, 89, 16);
		registerSecondPanel.add(drugLabel);
		
		drugText = new JTextField("");
		drugText.setBounds(334, 197, 130, 40);
		registerSecondPanel.add(drugText);
		drugText.setColumns(10);
		
		JLabel hospitalLabel = new JLabel("이용 중인 동물 병원");
		hospitalLabel.setBounds(189, 258, 105, 16);
		registerSecondPanel.add(hospitalLabel);
		
		hospitalText = new JTextField("");
		hospitalText.setColumns(10);
		hospitalText.setBounds(334, 249, 130, 40);
		registerSecondPanel.add(hospitalText);
		
		JLabel shopLabel = new JLabel("이용 중인 반려동물 미용실");
		shopLabel.setBounds(189, 314, 152, 16);
		registerSecondPanel.add(shopLabel);
		
		shopText = new JTextField("");
		shopText.setColumns(10);
		shopText.setBounds(334, 302, 130, 40);
		registerSecondPanel.add(shopText);
		
	
		registerFirstPanel.setBounds(154, 0, 646, 572);
		frame.getContentPane().add(registerFirstPanel);
		registerFirstPanel.setVisible(false);
		registerFirstPanel.setLayout(null);
		
		JLabel noticeLabel = new JLabel("* 표시가 있는 항목은 필수로 입력해주세요.");
		noticeLabel.setBounds(210, 115, 300, 16);
		registerFirstPanel.add(noticeLabel);
		
		JLabel typeLabel = new JLabel("*반려동물 종류");
		typeLabel.setBounds(191, 162, 77, 16);
		registerFirstPanel.add(typeLabel);
		
		JLabel nameLabel = new JLabel("*반려동물 이름");
		nameLabel.setBounds(191, 204, 77, 16);
		registerFirstPanel.add(nameLabel);
		
		JLabel breedLabel = new JLabel("*반려동물 품종");
		breedLabel.setBounds(191, 249, 77, 16);
		registerFirstPanel.add(breedLabel);
		
		JLabel ageLabel = new JLabel("*반려동물 출생연도");
		ageLabel.setBounds(191, 299, 106, 16);
		registerFirstPanel.add(ageLabel);
		
		nameText = new JTextField("");
		nameText.setBounds(327, 192, 130, 40);
		registerFirstPanel.add(nameText);
		nameText.setColumns(10);
		
		breedText = new JTextField("");
		breedText.setColumns(10);
		breedText.setBounds(327, 237, 130, 40);
		registerFirstPanel.add(breedText);
		
		typeCombobox.setModel(new DefaultComboBoxModel(new String[] {"개", "고양이", "파충류", "양서류", "기타"}));
		typeCombobox.setBounds(327, 158, 130, 27);
		registerFirstPanel.add(typeCombobox);
		
		ageCombobox.setModel(new DefaultComboBoxModel(new String[] {"2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999"}));
		ageCombobox.setBounds(327, 295, 130, 27);
		registerFirstPanel.add(ageCombobox);
		
		JLabel weightLabel = new JLabel("반려동물 무게");
		weightLabel.setBounds(191, 345, 77, 16);
		registerFirstPanel.add(weightLabel);
		
		weightText = new JTextField("");
		weightText.setColumns(10);
		weightText.setBounds(327, 334, 130, 40);
		registerFirstPanel.add(weightText);
		
		JLabel kgLabel = new JLabel("kg");
		kgLabel.setBounds(476, 345, 77, 16);
		registerFirstPanel.add(kgLabel);
		
		JButton nextButton = new JButton("다음");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerFirstPanel.setVisible(false);
				registerSecondPanel.setVisible(true);
			}
		});
		nextButton.setBounds(261, 450, 117, 29);
		registerFirstPanel.add(nextButton);
		
		JLabel registerpageLabel = new JLabel("반려동물 정보 등록 페이지");
		registerpageLabel.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		registerpageLabel.setBounds(229, 70, 188, 16);
		registerFirstPanel.add(registerpageLabel);
		
		JPanel deletePanel = new JPanel();
		deletePanel.setBounds(154, 0, 646, 572);
		frame.getContentPane().add(deletePanel);
		deletePanel.setVisible(false);
		deletePanel.setLayout(null);
		
		JLabel deletepageLabel = new JLabel("반려동물 정보 삭제 페이지");
		deletepageLabel.setBounds(229, 70, 188, 16);
		deletepageLabel.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		deletePanel.add(deletepageLabel);
		
		String header[] = {"이름","품종","출생연도"};
		model = new DefaultTableModel(0,3);
		table = new JTable(model);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setIntercellSpacing(new Dimension(5,5));
		table.setGridColor(new Color(0,0,0));
		table.setRowHeight(30);
		table.setBounds(127, 121, 400, 240);
		model.insertRow(0, header);
		deletePanel.add(table);
		
		JButton deleteactionButton = new JButton("선택한 정보 삭제");
		deleteactionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choiceIndex = table.getSelectedRow();
				if(choiceIndex!=0) {
					info.DeletePet(choiceIndex);
					model.removeRow(choiceIndex);
				}
			}
		});
		deleteactionButton.setBounds(270, 450, 117, 29);
		deletePanel.add(deleteactionButton);
		table.setVisible(true);
		
		JPanel subPanel = new JPanel();
		subPanel.setBackground(new Color(255, 255, 255));
		subPanel.setBounds(0, 0, 156, 572);
		frame.getContentPane().add(subPanel);
		subPanel.setLayout(null);
		
		JButton registerButton = new JButton("반려동물 정보 등록");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(info.n>=5)
					JOptionPane.showMessageDialog(null, "반려동물은 최대 5마리까지만 등록이 가능합니다.");
				else {
					registerFirstPanel.setVisible(true);
					registerSecondPanel.setVisible(false);
					deletePanel.setVisible(false);
				}
			}
		});
		registerButton.setBounds(8, 33, 140, 29);
		subPanel.add(registerButton);
		
		JButton deleteButton = new JButton("반려동물 정보 삭제");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerFirstPanel.setVisible(false);
				registerSecondPanel.setVisible(false);
				deletePanel.setVisible(true);
			}
		});
		deleteButton.setBounds(8, 81, 140, 29);
		subPanel.add(deleteButton);
	}
}
