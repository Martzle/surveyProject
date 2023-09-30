package finalproject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.*;

public class GUI {
    JFrame homeFrame;
    JPanel viewPanel;
    JTabbedPane tabs;
    GridBagConstraints gbc = new GridBagConstraints();
    JLabel title;
	ServerSocket mcServerSocket;
	Socket mcSocket;
	JList<String> list;
	static BufferedReader mcBufferedReader;
	static BufferedWriter mcBufferedWriter;
	// initialize variables 
    
    GUI(ArrayList<Question> q) throws IOException {
    	mcServerSocket = new ServerSocket(3333);
    	String[] listDisplay = new String[q.size()];
        homeFrame = new JFrame("Survey Viewer");
        homeFrame.setSize(750,750);
        homeFrame.setLocationRelativeTo(null);
        homeFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // initialize variables
        
        homeFrame.setLayout(new GridBagLayout());
        homeFrame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        homeFrame.getContentPane().setBackground(universalGUIConstants.getBackgroundColour());
        // making layout 
        
        tabs = new JTabbedPane();
        tabs.setForeground(universalGUIConstants.getForegroundColour());
        tabs.setBackground(universalGUIConstants.getBackgroundColour());
        // making tabs
        
        gbc.anchor=GridBagConstraints.NORTH;
        gbc.weightx=1;
        gbc.weighty=2;
        gbc.fill = GridBagConstraints.BOTH;
        // getting gridbag constants or the GUI
        
        viewPanel = new JPanel();
        viewPanel.setLayout(new GridBagLayout());
        viewPanel.setSize(universalGUIConstants.getLength(), universalGUIConstants.getWidth());
        viewPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        viewPanel.setBackground(universalGUIConstants.getBackgroundColour());
        // make our JPanel we see everything on
        
        JButton goButton = new JButton("Connect");
        goButton.setSize(200, 100);
        goButton.setBackground(universalGUIConstants.getBackgroundColour());
        goButton.setForeground(universalGUIConstants.getForegroundColour());
        goButton.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		try {
					run(q);
					// opens the clientHandler when pressed
				} catch (IOException e1) {
					e1.printStackTrace();
				}
        	}
        });
        gbc.gridy=0;
        viewPanel.add(goButton, gbc);
        // button to connect to the client 
        
        title = new JLabel("Your Questions: ");
        title.setFont(universalGUIConstants.getTitleFont());
        title.setForeground(universalGUIConstants.getForegroundColour());
        title.setLocation(0, 100);
        viewPanel.add(title, gbc);
        tabs.addTab("View", viewPanel);
        // title of the panel, add it to tabs (obsolete but I like how it looks)
        
        for (int i=0;i<q.size();i++) {
            listDisplay[i]=((i+1) +": " + (q.get(i).content));
            
        }
        list = new JList<String>(listDisplay);
        list.setBackground(universalGUIConstants.getBackgroundColour());
        list.setForeground(universalGUIConstants.getForegroundColour());
        list.setFont(universalGUIConstants.getMainFont());
        list.setLocation(0, 100);
        viewPanel.add(list, gbc);
        // make a JList to display questions
        
        homeFrame.add(tabs, gbc);
        homeFrame.setVisible(true);
        // add everything and make it visible to the user
    }
    
    public void run(ArrayList<Question> q) throws IOException {
		try {
			// look for connection
			mcSocket = mcServerSocket.accept();
							
			// open buffered reader/writer
            mcBufferedReader = new BufferedReader(new InputStreamReader(mcSocket.getInputStream()));
            mcBufferedWriter = new BufferedWriter(new OutputStreamWriter(mcSocket.getOutputStream()));
            
            // make a new clientHandler thread
            Thread t = new ClientHandler(mcSocket, mcBufferedReader, mcBufferedWriter, q);

            // begin the thread
			t.start();
		}
		catch(Exception e){
			// upon an error close the socket
			mcSocket.close();
			e.printStackTrace();
		}
    }

}