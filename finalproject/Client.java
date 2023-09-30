// client

package finalproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import javax.swing.JOptionPane;


public class Client {

	public static void main (String[] args) throws IOException{
		
		try {
			String[] content = new String[10];
			String[] content2 = new String[10];
			String[] answers = new String[10];
			String ip = JOptionPane.showInputDialog("Enter IP Address <3: ");
			int numberOQu = Integer.parseInt(JOptionPane.showInputDialog("Enter amount of questions from server"));
			Socket mcSocket=new Socket(ip, 3333);
			BufferedReader mcBufferedReader = new BufferedReader(new InputStreamReader(mcSocket.getInputStream()));
			BufferedWriter mcBufferedWriter = new BufferedWriter(new OutputStreamWriter(mcSocket.getOutputStream()));
			// initialize everything
			
			for (int i=0;i<numberOQu;i++) {
				String output=mcBufferedReader.readLine();
				content[i]=output;
				// to get the questions
			}
			for (int i=0;i<numberOQu;i++) {
				String output=mcBufferedReader.readLine();
				content2[i]=output;
				// to get the types
			}
			
			for (int i=0;i<numberOQu;i++) {	
				switch (content2[i]) {
				// switch in order to write the answers and send them back to the clientHandler
					case "0":{
						// if the type is a yes/no
						int n = JOptionPane.showConfirmDialog(null, "Question " + (i+1) + ": \n\n" + content[i], "Answer!", JOptionPane.YES_NO_OPTION);
						if (n==JOptionPane.YES_OPTION){
							answers[i]="Yes";
						}
						else {
							answers[i]="No";
						}
						break;
					}
					case "1":{
						// if the type is a free answer
						String a=JOptionPane.showInputDialog(null, "Question " + (i+1) + ": \n\n"+ content[i]);
						answers[i]=a;
						break;
					}
					default:{
						// if for some godforsaken reason there is an error this exists
						return;
					}
				}
			}
			for (int i=0;i<numberOQu;i++) {
				// writes answers back to the clientHandler
				mcBufferedWriter.write(answers[i]);
				mcBufferedWriter.newLine();
				mcBufferedWriter.flush();
			}
			
			// close everything 
			mcSocket.close();
			mcBufferedReader.close();
			mcBufferedWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
          