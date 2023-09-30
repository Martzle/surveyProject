// clienthandler

package finalproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ClientHandler extends Thread{
	Socket mcSocket;
	BufferedReader mcBufferedReader;
	public String[] answers;
	BufferedWriter mcBufferedWriter;
	ArrayList<Question> qArrs;
	// initialize variables 
	
	public ClientHandler(Socket mcSocket, BufferedReader mcBufferedReader, BufferedWriter mcBufferedWriter, ArrayList<Question> q) {
		// constructor which makes the socket, IO stuff and passes the questions array over
		this.mcSocket=mcSocket;
		this.mcBufferedReader=mcBufferedReader;
		this.mcBufferedWriter=mcBufferedWriter;
		this.qArrs = q;
	}
	
	@Override
	public void run() {
		int amount = qArrs.size();
		String[] send = new String[amount];
		String[] sendType = new String[amount];
		answers = new String[amount];
		// initialize variables
		
		while (true) {
			// runs for however long the client is connected
			try {
				for (int i=0;i<amount;i++) {
					send[i]=qArrs.get(i).content;
					sendType[i]=Integer.toString(qArrs.get(i).type);
					// separate the question components (type, content of question)
				}
				for (int i=0;i<amount;i++) {
					// send content
					mcBufferedWriter.write(send[i]);
					mcBufferedWriter.newLine();
					mcBufferedWriter.flush();
				}
				for (int i=0;i<amount;i++) {
					// send types
					mcBufferedWriter.write(sendType[i]);
					mcBufferedWriter.newLine();
					mcBufferedWriter.flush();
				}
				
				for (int i=0;i<amount;i++) {
					// recieve answers and display them to the server side 
					answers[i]=mcBufferedReader.readLine();
					JOptionPane.showMessageDialog(null, "The client responded " + answers[i] + " to Question " + (i+1) + "!");
				}
				break; // leave
			}catch(Exception e) {
				e.printStackTrace();
			}

		}
		try {
			// close everything
			this.mcBufferedReader.close();
			this.mcBufferedWriter.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}