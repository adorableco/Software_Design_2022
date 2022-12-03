package database_package;

import java.util.*;
import reservation_package.Reservation;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationDBConector {
	private FileWriter fw;
	private String reservation_data;
	public void dataUpload(Reservation rs) {
		reservation_data=rs.madeDate();
		//사용자 이름, 예약 고유번호 같이 입력
		//예약 고유번호 있으면 삭제나 검색하기 쉬움
		fw=null;
		try {
			fw=new FileWriter("Reservation DB.txt",true);
			fw.write(reservation_data,0,reservation_data.length());
			fw.write("\r\n",0,2);
			fw.close();
			System.out.println("예약 성공\n");
		}catch(IOException e) {
			System.out.println("예약 실패\n");
			e.printStackTrace();
		}
	}
	public ArrayList<Reservation> data_Download(){
		ArrayList<Reservation> Reserv_Info = new ArrayList<Reservation>();
		File[] flist;
		try {
					
					File Path = new File("./DataBase/Reservation/");
					flist = Path.listFiles(new FilenameFilter() {
						
						@Override
						public boolean accept(File dir, String name) {
							return !name.equals(".DS_Store");
						}
					});
					
					Arrays.sort(flist);
					
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
										LocalTime.of( Integer.parseInt(contentlist[3]),Integer.parseInt(contentlist[4]),0),
										contentlist[5], Integer.parseInt(contentlist[6]), Integer.parseInt(contentlist[7]), contentlist[8])
										);
							}
						}
					}
		}catch (FileNotFoundException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Reserv_Info;
	}
}
