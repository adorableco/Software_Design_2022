package gui_package;


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

import database_package.AnimalHospitalDBConnector;
import database_package.ReservationDB;
import reservation_package.AnimalHospital;
import reservation_package.Reservation;

public class AnimalHospitalReservationGUI extends JFrame{
	private AnimalHospitalDBConnector conn;
	private Reservation resv;
//	private ReservationAnimalHospital hos_resv;
	public AnimalHospitalReservationGUI() {
		this.setTitle("피어펫 서비스");
	}
	public AnimalHospitalReservationGUI(String title, Reservation res) {
		this.resv = res;
		createFrame(title);

		this.conn = new AnimalHospitalDBConnector();
//		this.hos_resv = new ReservationAnimalHospital();
		
		// 나중에 LocalDate, LocalTime now에서 변경 필요
		//임시 resvDate, resvTime
		LocalDate resvDate = resv.Get_Use_Day();
		LocalTime resvStartTime = resv.Get_Use_Start_Time();
		LocalTime resvFinishTime = resv.Get_Use_Finish_Time();
//		hos_resv.setDate(resvDate);
//		hos_resv.setTime(resvStartTime);
		
		this.add(infoPanel(resvDate, resvStartTime,resvFinishTime), BorderLayout.NORTH);
		try {
			
//			this.add(cList, BorderLayout.CENTER);
			this.add(showList(this.conn.searchDBwithTime(resvDate, resvStartTime,resvFinishTime)), BorderLayout.CENTER);
//			this.add(showList(this.conn.readDB()), BorderLayout.WEST);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JButton resvButton = new JButton("예약하기");
		this.add(resvButton, BorderLayout.PAGE_END);
		
		resvButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				ReservationAnimalHospitalDB resDB=new ReservationAnimalHospitalDB();
//				resDB.saveFile(hos_resv);
				ReservationDB resDB=new ReservationDB();
				JOptionPane.showMessageDialog(null,Integer.toString(resv.Get_Cost())+"원 결제되었습니다.");
				resDB.saveFile(resv);
				
				dispose();
			}
			
		});
		this.setVisible(true);
		this.setFocusable(true);
	}
	
	public JPanel infoPanel(LocalDate resvDate, LocalTime resvStartTime,LocalTime resvFinishTime) {
		JPanel info = new JPanel();
		JLabel resvDateInfo = new JLabel(resvDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
		JLabel resvStartTimeInfo = new JLabel(resvStartTime.format(DateTimeFormatter.ofPattern("HH:mm ~ ")));
		JLabel resvFinishTimeInfo = new JLabel(resvFinishTime.format(DateTimeFormatter.ofPattern("HH:mm에 예약을 진행합니다.")));
		info.add(resvDateInfo);
		info.add(resvStartTimeInfo);
		info.add(resvFinishTimeInfo);
		return info;
	}
	
	public JPanel showList(LinkedList<AnimalHospital> hospitalDB) throws IOException {

		JPanel cList = new JPanel();
		JLabel select = new JLabel();
		cList.setLayout(new BorderLayout());
//		LinkedList<AnimalHospital> hospitalDB = this.conn.searchDBwithTime(resvDate, resvStartTime,resvFinishTime);
		String[] header = this.conn.getDBHeader();
		String[][] contents = new String[hospitalDB.size()][19];
		for (int i = 0; i < hospitalDB.size();i++) {
			try {
				contents[i] = hospitalDB.get(i).getAttributeInList();
//				System.out.println(String.join(",", contents[i]));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		JTable companyListTable = new JTable(contents, header) {
			public boolean isCellEditable(int i, int c){
	          return false;
	         }
		};
		companyListTable.getTableHeader().setReorderingAllowed(false);
		companyListTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resizeColumnWidth(companyListTable);
		companyListTable.getColumnModel().getColumn(0).setMinWidth(200);
		companyListTable.getColumnModel().getColumn(1).setMinWidth(150);
		companyListTable.getColumnModel().getColumn(2).setMinWidth(60);
		companyListTable.getColumnModel().getColumn(3).setMinWidth(250);
		companyListTable.getColumnModel().getColumn(4).setMinWidth(100);
		
		MouseListener tableListener = new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JTable table = (JTable) e.getSource();
				int row = table.getSelectedRow();
//				System.out.print(table.getModel().getValueAt(row,0 )+"\t");
				String comp = table.getModel().getValueAt(row,0).toString();
				select.setText(comp+"에 예약을 진행합니다.");
				resv.setCompany(comp);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		companyListTable.addMouseListener(tableListener);
		
		JScrollPane scrollTable = new JScrollPane(companyListTable);
		scrollTable.setPreferredSize(new Dimension(780, 200));
		cList.add(new JLabel("선택한 예약일에 대한 병원 목록 조회 결과입니다."), BorderLayout.NORTH);
		JPanel subList = new JPanel();
		subList.add(scrollTable, BorderLayout.CENTER);
		subList.add(select, BorderLayout.SOUTH);
		cList.add(subList, BorderLayout.CENTER);
		return cList;
	
	}
	public void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 50; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}

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
}
