package Main;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

import Database.AnimalHospitalDBConnector;
import Reservation.AnimalHospital;

public class AnimalHospitalReservationGUI extends MainGUI{
	public AnimalHospitalReservationGUI(String title) {
		createFrame(title);
		AnimalHospitalDBConnector conn = new AnimalHospitalDBConnector();
		try {
			this.add(showList(conn), BorderLayout.WEST);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setVisible(true);
		this.setFocusable(true);
	}
	
	public JPanel showList(AnimalHospitalDBConnector conn) throws IOException {
		JPanel cList = new JPanel();
		LinkedList<AnimalHospital> hospitalDB = conn.readDB();
		String[] header = conn.getDBHeader();
		String[][] contents = new String[hospitalDB.size()][19];
		for (int i = 0; i < hospitalDB.size();i++) {
			try {
				contents[i] = hospitalDB.get(i).getAttributeInList();
				System.out.println(String.join(",", contents[i]));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		JTable companyListTable = new JTable(contents, header);
		companyListTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resizeColumnWidth(companyListTable);
		JScrollPane scrollTable = new JScrollPane(companyListTable);
		scrollTable.setPreferredSize(new Dimension(780, 100));
		cList.add(scrollTable);
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
