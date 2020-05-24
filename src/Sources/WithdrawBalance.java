package Sources;
import java.awt.EventQueue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WithdrawBalance {

	public JFrame frame;
	private JTextField textField;
	public String name,password, email, brth, address, balance;
	public WithdrawBalance(UserWallet u ) {
		initialize(u);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(UserWallet u) {
		frame = new JFrame();
		frame.setBounds(100, 100, 350, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//title
		JLabel lblNewLabel = new JLabel("Withdraw Balance");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(71, 11, 202, 25);
		frame.getContentPane().add(lblNewLabel);
		
		//withdraw
		JLabel lblNewLabel_1 = new JLabel("IDR");
		lblNewLabel_1.setBounds(150, 47, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(71, 72, 183, 20);
		frame.getContentPane().add(textField);
		
		//confirm button
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WithdrawFunction(textField);
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(165, 114, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		//back button
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toHome(e, u);
			}
		});
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(71, 114, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
	}
	public void toHome(ActionEvent evt,UserWallet u) {
		Home pro1 = new Home(u);
		pro1.frame.setVisible(true);
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(this.frame.EXIT_ON_CLOSE);
		this.frame.dispose();
	}
	public void WithdrawFunction(JTextField textField) {
		String value = textField.getText();
		String info = "";
		int count = 0;
		try {
			BufferedReader infoinput = new BufferedReader (new FileReader(new File("profile/"+Main.User+".txt")));
			try {
				info = infoinput.readLine();
				while(info != null) {
					count += 1;
					switch(count) {
					case 1 : name = info; break;
					case 2 : password = info; break;
					case 3 : email = info; break;
					case 4 : brth = info; break;
					case 5 : address = info;break;
					case 6 : balance = info;break;
					default : break;
					}
					info = infoinput.readLine();
				}				
				infoinput.close();
				
			}catch (IOException e) {
				e.printStackTrace();
			}
			
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		
		//value type double 
		double value1 = Double.parseDouble(value);
		double balance1 = Double.parseDouble(balance);
		//IDR check
		if(!value.isEmpty()) {
			if(value1 <= balance1 && value1 >0) {
				double saldo = balance1 - value1;
				File f2 = new File("profile/"+Main.User+".txt");
				File file = new File("profile/temp.txt");
			
	//Overwrite file	
				
				if(!file.exists()) {
					try {
						file.createNewFile();
						FileWriter Write = new FileWriter(file);
				
						Write.write(""+name+"\n"+password+"\n"+ 
								    email + "\n" + brth +
								    "\n" + address+"\n"+Math.round(saldo));
						Write.close();
					} 
					catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				
				else {
					FileWriter reWrite;
					try {
						reWrite = new FileWriter(file,false);
						reWrite.write(""+name+"\n"+password+"\n"+ 
							    email + "\n" + brth +
							    "\n" + address+"\n"+Math.round(saldo));
						System.out.println("ternyata masuk else");
						reWrite.close();
					} 
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					
				}
				
				if(f2.exists()) {
					FileWriter reWrite;
					try {
						reWrite = new FileWriter(f2,false);
						reWrite.write(""+name+"\n"+password+"\n"+ 
							    email + "\n" + brth +
							    "\n" + address+"\n"+Math.round(saldo));
						System.out.println("ternyata masuk else");
						reWrite.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
							
			}
				//overwrite file end
			}
			else {
				JOptionPane.showMessageDialog(frame, "Input yang anda masukan salah","Input Error",JOptionPane.WARNING_MESSAGE);
			}
		}	
	}
}
