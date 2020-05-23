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
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddBalance {

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	public String name,password, email, brth, address, balance;
	

	public AddBalance(UserWallet u) {
		initialize(u);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(UserWallet u) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//title
		JLabel lblNewLabel = new JLabel("Add Balance");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(147, 11, 146, 25);
		frame.getContentPane().add(lblNewLabel);
		
		//add idr
		JLabel lblNewLabel_1 = new JLabel("IDR");
		lblNewLabel_1.setBounds(99, 65, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(24, 90, 180, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		//add usd
		JLabel lblNewLabel_2 = new JLabel("USD");
		lblNewLabel_2.setBounds(300, 65, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(226, 90, 180, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		//confirm button
		btnNewButton = new JButton("Confrim");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBalanceFunction(textField,textField_1);
			}

		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(226, 159, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		//back button
		btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toHome(e, u);
			}
		});
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(115, 159, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
	}
	
	//back home
	public void toHome(ActionEvent evt,UserWallet u) {
		Home pro1 = new Home(u);
		pro1.frame.setVisible(true);
		this.frame.setVisible(false);
		this.frame.setDefaultCloseOperation(this.frame.EXIT_ON_CLOSE);
		this.frame.dispose();
	}
	
	//Addbalance
	public void AddBalanceFunction(JTextField textField, JTextField textField_1) {
		String idr = textField.getText();
		String usd = textField_1.getText();
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
		
		//balance type double 
		double balance1 = Double.parseDouble(balance);
		
		
		IDR idr2 = new IDR(balance1);
		//IDR check
		if(!idr.isEmpty()&& usd.isEmpty()) {
		  
			double idr1 =Double.parseDouble(idr);
			idr2.addValue(idr1);
			File f2 = new File("profile/"+Main.User+".txt");
			File file = new File("profile/temp.txt");
		
//Overwrite file	
			
			if(!file.exists()) {
				try {
					file.createNewFile();
					FileWriter Write = new FileWriter(file);
			
					Write.write(""+name+"\n"+password+"\n"+ 
							    email + "\n" + brth +
							    "\n" + address+"\n"+Math.round(idr2.getValue()));
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
						    "\n" + address+"\n"+Math.round(idr2.getValue()));
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
						    "\n" + address+"\n"+Math.round(idr2.getValue()));
					System.out.println("ternyata masuk else");
					reWrite.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						
		}
			//overwrite file end

	}	
		//USD check
		else if(!usd.isEmpty()) {
			System.out.println("masuk usd");
			  double usd1 = Double.parseDouble(usd);
			  System.out.println("objek usd");
				USD usd2 = new USD(0);
				System.out.println("objek ada");

			usd2.addValue(usd1);
			usd2.toIDR();
			idr2.addValue(usd2.getValue());
			System.out.println(usd2.getValue());
			File f3 = new File("profile/"+Main.User+".txt");
			File file1 = new File("profile/temp.txt");
		
		//overwrite file
			if (file1.exists()){
				FileWriter reWrite;
				try {
					reWrite = new FileWriter(file1,false);
					reWrite.write(""+name+"\n"+password+"\n"+ 
						    email + "\n" + brth +
						    "\n" + address+"\n"+Math.round(idr2.getValue()));
					System.out.println("ternyata masuk else");
					reWrite.close();
					} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}				
			}
			
			if(f3.exists()) {
				FileWriter reWrite;
				try {
					reWrite = new FileWriter(f3,false);
					reWrite.write(""+name+"\n"+password+"\n"+ 
						    email + "\n" + brth +
						    "\n" + address+"\n"+Math.round(idr2.getValue()));
					System.out.println("ternyata masuk else");
					reWrite.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
			
		} //end usd check

		
}//?
}//?