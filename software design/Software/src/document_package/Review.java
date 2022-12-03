package document_package;


import java.io.*;
import java.util.*;

public class Review {
	public static void main(String[] args) throws IOException {
		FileInputStream file = new FileInputStream(new File("C:\\\\Users\\\\SEC\\\\eclipse-workspace\\\\PeerPet_Service\\\\Review.txt"));
		InputStreamReader reader=new InputStreamReader(file,"UTF-8");
        BufferedReader in =new BufferedReader(reader);
		
		int ch;
		while((ch = in.read()) != -1) {
			System.out.print((char) ch);
		}
		                          
		in.close();
	}
	
}
