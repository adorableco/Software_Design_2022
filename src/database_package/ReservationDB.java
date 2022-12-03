package database_package;

import java.util.*;
import reservation_package.Reservation;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ReservationDB {
//	private FileWriter fw;
//	private String reservation_data;
//	public void dataUpload(Reservation rs) {
//		reservation_data=rs.madeDate();
//		//사용자 이름, 예약 고유번호 같이 입력
//		//예약 고유번호 있으면 삭제나 검색하기 쉬움
//		fw=null;
//		try {
//			fw=new FileWriter("Reservation DB.txt",true);
//			fw.write(reservation_data,0,reservation_data.length());
//			fw.write("\r\n",0,2);
//			fw.close();
//			System.out.println("예약 성공\n");
//		}catch(IOException e) {
//			System.out.println("예약 실패\n");
//			e.printStackTrace();
//		}
//	}
	private int resvInt = 7;
	private String path = "./DataBase/Reservation/";
	
	
	public File[] Get_Filelist() {
		File f = new File(path);
		File[] flist = f.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return !name.equals(".DS_Store");
			}
		});
		Arrays.sort(flist);
		return flist;
	}
	
	public ArrayList<Reservation> Get_Reserve_Info(File[] flist){

		ArrayList<Reservation> Reserv_Info = new ArrayList<Reservation>();
		
		try {
			for(int i = 0; i < flist.length; i++) {
				// Contain User name
				if(flist[i].getName().contains("1_Res")) {
					BufferedReader br = new BufferedReader(new FileReader(flist[i]));
					String content;
					String name;
					int state;
					while((content = br.readLine()) != null) {
						String[] contentlist = content.split(" ");
						Reserv_Info.add(
								new Reservation(
								LocalDate.of(Integer.parseInt(contentlist[0]), Integer.parseInt(contentlist[1]), Integer.parseInt(contentlist[2])),
								LocalTime.of( Integer.parseInt(contentlist[3]),Integer.parseInt(contentlist[4]),0), LocalTime.of(Integer.parseInt(contentlist[5]), Integer.parseInt(contentlist[6]),0),
								contentlist[7], Integer.parseInt(contentlist[8]), Integer.parseInt(contentlist[9]), contentlist[10], contentlist[11])
								);
					}
				}
			}
		}catch (FileNotFoundException e) {
			// TODO: handle exception
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Reserv_Info;
	}
	
	
	public void saveFile(Reservation resvInfo) {
		int userId = 1;
		String fname = this.path + Integer.toString(userId) + "_Reser_" + Integer.toString(resvInt) + ".txt";
		String resv = resvInfo.Get_Use_Day().format(DateTimeFormatter.ofPattern("yyyy MM dd"))+ " " +
				resvInfo.Get_Use_Start_Time().format(DateTimeFormatter.ofPattern("HH mm")) + " " +
				resvInfo.Get_Use_Finish_Time().format(DateTimeFormatter.ofPattern("HH mm")) + " " +
				resvInfo.Get_Use_Service() + " " +
				Integer.toString(resvInfo.Get_State()) + " " +
				Integer.toString(resvInfo.Get_Cost()) + " " +
				resvInfo.Get_Review() + " "+
				resvInfo.Get_Helper();
		System.out.println(resv)
;		
	    try {
	    	BufferedWriter writer = new BufferedWriter(new FileWriter(fname));
			writer.write(resv);
			writer.flush();
		    writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	
	public void saveFile(Reservation resvInfo, File originalFile) {
		String fname = this.path + originalFile.getName().substring(0, originalFile.getName().lastIndexOf("."))+"_temp.txt";
		String resv = resvInfo.Get_Use_Day().format(DateTimeFormatter.ofPattern("yyyy MM dd"))+ " " + 
				resvInfo.Get_Use_Start_Time().format(DateTimeFormatter.ofPattern("HH mm")) + " " +
				resvInfo.Get_Use_Finish_Time().format(DateTimeFormatter.ofPattern("HH mm")) + " " +
				resvInfo.Get_Use_Service() + " " +
				Integer.toString(resvInfo.Get_State()) + " " +
				Integer.toString(resvInfo.Get_Cost()) + " " + 
				resvInfo.Get_Review() + " "+
				resvInfo.Get_Helper();
		
	    try {
	    	BufferedWriter writer = new BufferedWriter(new FileWriter(fname));
			writer.write(resv);
			writer.flush();
		    writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	
	public void setResvInt(int n) {
		this.resvInt = n;
		return;
	}
}
