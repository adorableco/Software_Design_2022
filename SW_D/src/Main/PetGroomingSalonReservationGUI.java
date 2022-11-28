package Main;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

import Database.PetGroomingSalonDBConnector;
import Reservation.PetGroomingSalon;

public class PetGroomingSalonReservationGUI extends MainGUI{
	private PetGroomingSalonDBConnector conn;
	
	public PetGroomingSalonReservationGUI(String title) {
		createFrame(title);
		this.conn = new PetGroomingSalonDBConnector();
		// 나중에 LocalDate, LocalTime now에서 변경 필요
		//임시 resvDate, resvTime
		LocalDate resvDate = LocalDate.now();
		LocalTime resvTime = LocalTime.parse("10:00");
		this.add(infoPanel(resvDate, resvTime), BorderLayout.NORTH);
		try {
			this.add(showList(this.conn.searchDBwithTime(resvDate, resvTime)), BorderLayout.CENTER);
//			this.add(showList(this.conn.readDB()), BorderLayout.WEST);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JButton resvButton = new JButton("예약하기");
		this.add(resvButton, BorderLayout.PAGE_END);
		this.setVisible(true);
		this.setFocusable(true);
	}
	
	public JPanel infoPanel(LocalDate resvDate, LocalTime resvTime) {
		JPanel info = new JPanel();
		JLabel resvDateInfo = new JLabel(resvDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
		JLabel resvTimeInfo = new JLabel(resvTime.format(DateTimeFormatter.ofPattern("HH:mm에 예약을 진행합니다.")));
		info.add(resvDateInfo);
		info.add(resvTimeInfo);
		return info;
	}
	
	public JPanel showList(LinkedList<PetGroomingSalon> salonDB) throws IOException {
		JPanel cList = new JPanel();
		JLabel select = new JLabel();
		cList.setLayout(new BorderLayout());
		String[] header = this.conn.getDBHeader();
		String[][] contents = new String[salonDB.size()][19];
		for (int i = 0; i < salonDB.size();i++) {
			try {
				contents[i] = salonDB.get(i).getAttributeInList();
//				System.out.println(String.join(",", contents[i]));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		JTable companyListTable = new JTable(contents, header);
		companyListTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resizeColumnWidth(companyListTable);
		companyListTable.getColumnModel().getColumn(0).setMinWidth(225);
		companyListTable.getColumnModel().getColumn(1).setMinWidth(175);
		companyListTable.getColumnModel().getColumn(2).setMinWidth(85);
		companyListTable.getColumnModel().getColumn(3).setMinWidth(275);
		
		MouseListener tableListener = new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JTable table = (JTable) e.getSource();
				int row = table.getSelectedRow();
//				System.out.print(table.getModel().getValueAt(row,0 )+"\t");
				select.setText(table.getModel().getValueAt(row,0)+"에 예약을 진행합니다.");
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
}
