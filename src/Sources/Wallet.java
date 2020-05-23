package Sources;
import java.util.*;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class Wallet{
	public JFrame frame;
	private JTextField txtUsername;
	private JPasswordField passwordField;
	
	public Wallet(UserWallet u) {
		this.initialize(u);
	}

	private void initialize(UserWallet u) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//title
		JLabel lblNewLabel_1 = new JLabel("E-Wallet");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(170, 29, 89, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		//username
		JLabel lblNewLabel = new JLabel("email");
		lblNewLabel.setBounds(75, 74, 59, 14);
		frame.getContentPane().add(lblNewLabel);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(144, 71, 184, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		//password
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(75, 113, 59, 14);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(144, 110, 184, 20);
		frame.getContentPane().add(passwordField);
		
		//login button
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				readArray("User.txt", txtUsername, passwordField, arg0, u);
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(170, 147, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		//register button
		JLabel lblNewLabel_2 = new JLabel("Don't have any account yet? Register now!");
		lblNewLabel_2.setBounds(10, 206, 264, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Register");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyButton1ActionPerformed(e,u);
			}
		});
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
	}
	
	public void MyButton1ActionPerformed(ActionEvent evt,UserWallet u) { 
		register jfrm1= new register(u); 
		jfrm1.frame.setVisible(true);
		this.frame.setVisible(false);
		this.frame.setDefaultCloseOperation(this.frame.EXIT_ON_CLOSE);
		this.frame.dispose();
	}
	
	public void readArray (String File, JTextField txtUsername, JPasswordField passwordField, ActionEvent x, UserWallet u) {
		String email = txtUsername.getText(),pass = passwordField.getText();
		int line = 0;
		BufferedReader fileInput;
		try {
			fileInput = new BufferedReader(new FileReader(new File("user.txt")));
			String line2;
			try {
				line2 = fileInput.readLine();
				while(line2 != null) {
					line+=1;
					line2 = fileInput.readLine();
				}
				fileInput.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] words = new String[line];
		int i =0;
		BufferedReader fileHasil;
		try {
			fileHasil = new BufferedReader(new FileReader(new File("user.txt")));
			String line2;
			try {
				line2 = fileHasil.readLine();
				while(line2 != null) {
					words[i] = line2;	
					i++;
					line2 = fileHasil.readLine();
				}
				fileHasil.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int j=1; j < words.length; j+=4) {
			if(words[j].equals(email) && words[j+1].equals(pass)) {
				Main.User = email;
				this.ToHome(x, u);
				break;
			}
			else if(!words[j].equals(email) && !words[j+1].equals(pass) && j+2==words.length) {
				JOptionPane.showMessageDialog(frame, "Username atau Password yang anda masukan salah!!!", "Login", JOptionPane.WARNING_MESSAGE);
				break;
			}
			else if(!words[j].equals(email) && words[j+1].equals(pass) && j+2==words.length) {
				JOptionPane.showMessageDialog(frame, "Username atau Password yang anda masukan salah!!!", "Login", JOptionPane.WARNING_MESSAGE);
				break;
			}
			else if(words[j].equals(email) && !words[j+1].equals(pass) && j+2==words.length) {
				JOptionPane.showMessageDialog(frame, "Username atau Password yang anda masukan salah!!!", "Login", JOptionPane.WARNING_MESSAGE);
				break;
			}
		}
	}
	
	public void ToHome(ActionEvent evt,UserWallet u) { 
		Home jfrm1= new Home(u); 
		jfrm1.frame.setVisible(true);
		this.frame.setVisible(false);
		this.frame.setDefaultCloseOperation(this.frame.EXIT_ON_CLOSE);
		this.frame.dispose();
	}
}