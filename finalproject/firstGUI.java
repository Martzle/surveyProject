// first GUI

package finalproject;

import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import finalproject.universalGUIConstants;

public class firstGUI implements ActionListener, ItemListener {
	JFrame welcome;
	JComboBox<String> nums;
	ArrayList<Question> questions;
	JButton start;
	GridBagConstraints gbc = new GridBagConstraints();
	int amount;
	// initializing variables
	
	firstGUI() {
		questions = new ArrayList<Question>();
		String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
		// initialize questions list and strings for combobox
		
		welcome = new JFrame("Enter amount of questions and create survey");
		welcome.setSize(universalGUIConstants.getWidth(), universalGUIConstants.getLength());
		welcome.setLocationRelativeTo(null);
		welcome.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		welcome.setLayout(new GridBagLayout());
		welcome.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		// make the welcome frame
		
		
		gbc.anchor=GridBagConstraints.NORTHEAST;
		gbc.weightx=1;
		gbc.weighty=1;
		gbc.fill = GridBagConstraints.BOTH;
		// gridbaglayout constraints
		
		nums = new JComboBox<String>(numbers);
		gbc.gridx=1;
		gbc.gridy=4;
		gbc.gridheight=1;
		gbc.gridwidth=1;
		nums.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				amount = Integer.parseInt((String)nums.getSelectedItem());
			}
		});
		welcome.add(nums, gbc);
		// create and add combobox to select amount of questions
		
		start = new JButton("Create Survey");
		gbc.gridx=1;
		gbc.gridy=1;
		gbc.gridwidth=3;
		gbc.gridheight=2;
		start.setBackground(universalGUIConstants.getBackgroundColour());
		start.setForeground(universalGUIConstants.getForegroundColour());
		start.setFont(universalGUIConstants.getTitleFont());
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					EnterQs(amount);
					// once button is pressed, run the method that will create the questions
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		welcome.add(start, gbc);
		// make button to start survey 
		
		welcome.setVisible(true);
		// add all GUI elements
	}
	
	void EnterQs(int qNum) throws IOException {		
		for (int i=0;i<qNum;i++) {
			int a;
			int n = JOptionPane.showConfirmDialog(null, "Type for question " + (i + 1) + ":\nYes: Yes/No question\n No: Free answer question", "Make questions!", JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				a=0;
				// type 1
			}
			else {
				a=1;
				// type  2
			}
			String s = JOptionPane.showInputDialog("Enter your question: ");
			Question qu = new Question(a, s);
			questions.add(qu);
		}
		// loop to create questions, prompts the type first then the actual question contents
		// after that, it will make an object of type question for each one and store it into an arrayList
		
		welcome.setVisible(false);
		GUI go = new GUI(questions);
		// remove all elements then go to the next menu

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// if I remove this the program breaks
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// same for this one
		
	}
}
