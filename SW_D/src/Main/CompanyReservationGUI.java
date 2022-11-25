//package Main;
//
//import java.io.IOException;
//import java.util.*;
//
//import javax.swing.*;
//
//import Database.CompanyDBConnector;
//import Reservation.Company;
//
//public class CompanyReservationGUI extends MainGUI{
//	private String fname = "";
//	public CompanyReservationGUI(String title) {
//		createFrame(title);
//		CompanyDBConnector DBconn = new CompanyDBConnector(fname);
//		this.setVisible(true);
//		this.setFocusable(true);
//	}
//	
//	public JPanel showList(CompanyDBConnector DBconn) throws IOException {
//		JPanel cList = new JPanel();
//		LinkedList<Company> companyDB = DBconn.readDB();
//		String[] header = DBconn.getDBHeader();
//		List<String[]> contents = new ArrayList<String[]>();
//		for (Company c : companyDB) {
//			try {
//				contents.add((String[]) c.getAttributeInList().toArray());
//			} catch (IllegalArgumentException | IllegalAccessException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		String[][] tbContents = (String[][]) contents.toArray();
//		JTable companyListTable = new JTable(tbContents, header);
//		return cList;
//		
//	}
//}
