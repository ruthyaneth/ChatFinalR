package client.view;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.*;
import java.net.*;
import java.util.*;




public class first_page {

	private JFrame frame;
    private JTextField enterField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					first_page window = new first_page();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public first_page() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
				
		frame = new JFrame("Welcome to Chat Client");
		frame.setBounds(150, 150, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final JTextField textB = new JTextField(21);
		textB.setBounds(210, 100, 245, 30);
		textB.setSize(60, 20);
		frame.add(textB);

		
		JLabel lblYouNeedTo = new JLabel("Verification:");
		lblYouNeedTo.setBounds(10, 11, 223, 14);
		frame.getContentPane().add(lblYouNeedTo);
		
		final JLabel capcha = new JLabel(generateCaptcha());
		capcha.setBounds(150, 100, 235, 20);
		frame.getContentPane().add(capcha);		
		
		JButton btnNewButton = new JButton("Next");
		btnNewButton.setBounds(335, 227, 89, 23);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  String getTxt = textB.getText();
			  if(getTxt.equals(capcha.getText()))
			  {
				  try {
					  frame.dispose();
						ServerTest();						 
				
				  } catch (Exception e1) {
					
					e1.printStackTrace();
				}
		          
			  }
			  else
			  {  
				  JOptionPane.showMessageDialog(null,"Wrong Captcha! Please try again!.");
				  capcha.setText(generateCaptcha());	
			  }
		  }
		});

		
	}

	protected void ServerTest() {
		
		Server applicationS = new Server(); // create server
		 applicationS.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		 applicationS.waitForPackets(); // run server application
		 applicationS.setVisible(true);
	}

	/*
	 * Generate Captcha
	*/
	public String generateCaptcha() {  
		  Random random = new Random();  
		  int length = 5;  
		  StringBuffer captchaStringBuffer = new StringBuffer();  
		  for (int i = 0; i < length; i++) {  
		   int captchaNumber = Math.abs(random.nextInt()) % 60;  
		   int charNumber = 0;  
		   if (captchaNumber < 26) {  
		    charNumber = 65 + captchaNumber;  
		   }  
		   else if (captchaNumber < 52){  
		    charNumber = 97 + (captchaNumber - 26);  
		   }  
		   else {  
		    charNumber = 48 + (captchaNumber - 52);  
		   }  
		   captchaStringBuffer.append((char)charNumber);  
		  }  
		  
		  return captchaStringBuffer.toString();  
		 }   

}