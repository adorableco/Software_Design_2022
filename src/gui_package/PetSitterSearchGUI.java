package gui_package;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

import database_package.PetSitterDBConnector;
import reservation_package.PetSitter;
import reservation_package.Reservation;

public class PetSitterSearchGUI extends JFrame{
	public JTable table;
	public JFrame fr;
	protected int row;
	private PetSitterDBConnector conn;
	private Reservation res;
	private JComboBox addrCombo;
	private TableRowSorter<TableModel> sorter;
	public PetSitterSearchGUI() {
		
	}
	
	public PetSitterSearchGUI(String title, Reservation res) {
		this.res = res;
		createFrame1(title);
		this.conn = new PetSitterDBConnector();
		// 나중에 LocalDate, LocalTime now에서 변경 필요
		//임시 resvDate, resvTime
		LocalDate resvDate = res.Get_Use_Day();
		LocalTime resvTime = res.Get_Use_Start_Time();
		fr.add(infoPanel(resvDate, resvTime), BorderLayout.NORTH);
		LinkedList<PetSitter> petsitterDB = new LinkedList<PetSitter>();
		
		try {
			petsitterDB = this.conn.searchDBwithTime(resvDate, resvTime);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		addrCombo = addressComboBox();
		
		JPanel tablePanel = new JPanel();
		try {
//			tablePanel = showList(petsitterDB);
			JPanel cList = new JPanel();
			JLabel select = new JLabel();
			cList.setLayout(new BorderLayout());
			
			JPanel subList = new JPanel();		
			
			String[] header = this.conn.getDBHeader();
			String[][] contents = new String[petsitterDB.size()][19];
			for (int i = 0; i < petsitterDB.size();i++) {
				try {
					contents[i] = petsitterDB.get(i).getAttributeInList();
//					System.out.println(String.join(",", contents[i]));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			JTable petsitterListTable = new JTable(contents, header);
			petsitterListTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			resizeColumnWidth(petsitterListTable);
			petsitterListTable.getColumnModel().getColumn(0).setMinWidth(200);
			petsitterListTable.getColumnModel().getColumn(1).setMinWidth(150);
			petsitterListTable.getColumnModel().getColumn(2).setMinWidth(420);
			
			sorter = new TableRowSorter<>(petsitterListTable.getModel());
			petsitterListTable.setRowSorter(sorter);
			if (addrCombo.getSelectedItem().toString().length() > 2) {
				sorter.setRowFilter(RowFilter.regexFilter(addrCombo.getSelectedItem().toString()));
			
			} else {
			        sorter.setRowFilter(null);
			
			}
	         
			MouseListener tableListener = new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					table = (JTable) e.getSource();
					row = table.getSelectedRow();
//					System.out.print(table.getModel().getValueAt(row,0 )+"\t");
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
			petsitterListTable.addMouseListener(tableListener);
			
			
			
			JScrollPane scrollTable = new JScrollPane(petsitterListTable);
			scrollTable.setPreferredSize(new Dimension(780, 200));
			cList.add(new JLabel("선택한 예약일에 대한 도우미 조회 결과입니다."), BorderLayout.NORTH);

			subList.add(scrollTable, BorderLayout.CENTER);
			subList.add(select, BorderLayout.SOUTH);
			cList.add(subList, BorderLayout.CENTER);
			tablePanel = cList;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		JLabel ex = new JLabel();
		tablePanel.add(addrCombo, BorderLayout.NORTH);
//		tablePanel.add(ex, BorderLayout.SOUTH);
		

		addrCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String addr = addrCombo.getSelectedItem().toString();
				if(addr == "선택") {
//						petsitterDB = conn.searchDBwithTime(resvDate, resvTime);
					sorter.setRowFilter(null);
				}
				else {
//						petsitterDB = conn.searchDBwithAddress(petsitterDB, addr);
					sorter.setRowFilter(RowFilter.regexFilter(addr));
				}
			}
			
		});

		
		fr.add(tablePanel, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel(new GridLayout(1,2));
	      
	      JButton backBtn = new JButton("뒤로가기");
	      buttonPanel.add(backBtn);
	      backBtn.addActionListener(new ActionListener() {
	         
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            JOptionPane confirm = new JOptionPane();
	            int result;
	            result = confirm.showConfirmDialog(null, "돌아가시겠습니까? ", "돌아가기", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
	            if(result == 0) {
	               fr.dispose();
	               dispose();
	            } 
	         }
	      });
	      
	      JButton resvButton = new JButton("선택하기");
	      buttonPanel.add(resvButton);
	      resvButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            try {
//	                  String[] a=petSitter.readDB().get(row).getAttributeInString().split(",");
//	                  helper = a[0];
	               BufferedWriter writer = new BufferedWriter(new FileWriter("./Database/petsitter_temp.txt"));
	               writer.write(table.getModel().getValueAt(row,0).toString());
//	               System.out.println("search:"+table.getModel().getValueAt(row,0).toString());
	               writer.flush();
	                writer.close();
	               fr.dispose();
	               dispose();
	            } catch (IllegalArgumentException | IOException e1) {
	               // TODO Auto-generated catch block
	               e1.printStackTrace();
	            }
	      
	         }
	      });
	      fr.add(buttonPanel, BorderLayout.PAGE_END);
		fr.setVisible(true);
		fr.setFocusable(true);
	}
	
	public JPanel infoPanel(LocalDate resvDate, LocalTime resvTime) {
		JPanel info = new JPanel();
		JLabel resvDateInfo = new JLabel(resvDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
		JLabel resvTimeInfo = new JLabel(resvTime.format(DateTimeFormatter.ofPattern("HH:mm에 예약을 진행합니다.")));
		info.add(resvDateInfo);
		info.add(resvTimeInfo);
		return info;
	}
	
//	public JPanel showList(LinkedList<PetSitter> petsitterDB) throws IOException {
//
//		return cList;
//	
//	}
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
	
	public JComboBox addressComboBox() {
		String address[] = {"선택", "대구광역시 북구", "대구광역시 중구", "대구광역시 동구"};
		JComboBox addrCombo = new JComboBox(address); 
		return addrCombo;
	}
	void createFrame1(String title) {
		fr = new JFrame();
		fr.setTitle(title);
		fr.setSize(800,600);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frame = getSize();
		
		int xPos = (int)(screen.getWidth() / 2 - frame.getWidth()/2);
		int yPos = (int)(screen.getHeight() / 2 - frame.getHeight()/2);
		
		fr.setLocation(xPos, yPos);
		fr.setResizable(true);
		
		
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		fr.setLayout(new BorderLayout());	
	}
	
}
