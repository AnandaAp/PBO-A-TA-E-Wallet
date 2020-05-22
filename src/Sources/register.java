package Sources;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.regex.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class register extends JFrame {
	public JFrame frame;
	private JTextField fullname;
	private JTextField email;
	private JTextField birth;
	private JTextField address;
	public static boolean cek = false;
	private JPasswordField passwordField;
	public String strRegEx = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$";
	public register(UserWallet u) {
		this.initialize(u);
	}
	private void initialize(UserWallet u) {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//title
		JLabel lblNewLabel = new JLabel("Register");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 94, 25);
		frame.getContentPane().add(lblNewLabel);
		
		//full name
		JLabel lblNewLabel_1 = new JLabel("Full Name");
		lblNewLabel_1.setBounds(10, 47, 94, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		fullname = new JTextField();
		fullname.setText("");
		fullname.setBounds(118, 47, 203, 20);
		frame.getContentPane().add(fullname);
		fullname.setColumns(10);
		
		//email
		JLabel lblNewLabel_2 = new JLabel("E-mail");
		lblNewLabel_2.setBounds(10, 81, 94, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		email = new JTextField();
		email.setText("");
		email.setColumns(10);
		email.setBounds(118, 78, 203, 20);
		frame.getContentPane().add(email);
		
		//password
		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setBounds(10, 106, 94, 14);
		frame.getContentPane().add(lblNewLabel_3);
		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(118, 109, 203, 20);
		frame.getContentPane().add(passwordField);
		String cekPass = passwordField.getText();
		//password warning
		JLabel lblNewLabel_6_1 = new JLabel("password harus: minimal 8 karakter, 1 huruf kecil, 1 huruf besar, 1 angka, dan 1 simbol");
		lblNewLabel_6_1.setForeground(Color.RED);
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNewLabel_6_1.setBounds(118, 140, 400, 14);
		frame.getContentPane().add(lblNewLabel_6_1);
		//address
		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setBounds(10, 167, 94, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		address = new JTextField();
		address.setBounds(118, 164, 203, 20);
		frame.getContentPane().add(address);
		address.setColumns(10);
		
		//birthday
		JLabel lblNewLabel_5 = new JLabel("Date of Birth");
		lblNewLabel_5.setBounds(10, 206, 94, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		birth = new JTextField();
		birth.setColumns(10);
		birth.setBounds(118, 203, 203, 20);
		frame.getContentPane().add(birth);
		
		JLabel lblNewLabel_6 = new JLabel("ex. 12 August 1998");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_6.setBounds(118, 234, 100, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		//register button
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					goToUserWaller(fullname, email, passwordField, address, u, arg0,cekPass,lblNewLabel_6_1);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(335, 227, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backToLogin(e, u);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(335, 11, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
	}
	public void backToLogin(ActionEvent e,UserWallet u) {
		Wallet w = new Wallet(u);
		w.frame.setVisible(true);
		this.frame.setVisible(false);
		this.frame.setDefaultCloseOperation(this.frame.EXIT_ON_CLOSE);
		this.frame.dispose();
	}
	public void goToUserWaller(JTextField fullname,JTextField email,JPasswordField passwordField,JTextField address,UserWallet u, ActionEvent arg0,String cekPass,JLabel lblNewLabel_6_1) {
		String nama = fullname.getText(),Email = email.getText(), 
				   password = passwordField.getText(),alamat = address.getText(),
				   tanggalLahir = birth.getText();
			if(!nama.isEmpty() && !Email.isEmpty() && !alamat.isEmpty()
				&& !password.isEmpty() && !tanggalLahir.isEmpty()
			) {
				Pattern patt = Pattern.compile(strRegEx);
				Matcher match=patt.matcher(password);
				boolean mat = match.matches();
				if(!mat) {
					System.out.println("tidak sesuai pattern,password:"+password+" panjang:"+password.length());
					JOptionPane.showMessageDialog(frame, "Password tidak sesuai kriteria","Password Error Message",JOptionPane.WARNING_MESSAGE);
					System.out.println("error pass");
				}
				else {
					System.out.println("semua benar");
					cek = u.Register(nama, tanggalLahir, password, Email, alamat,cek);
					if(cek == true) {
						JOptionPane.showMessageDialog(frame, "Email sudah pernah digunakan","Register",JOptionPane.WARNING_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(frame, "Register berhasil, Selamat datang " + nama,
								  "Register",JOptionPane.PLAIN_MESSAGE);
						backToLogin(arg0, u);
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(frame, "Data tidak boleh ada yang kosong!!!",
						  					  "Register",JOptionPane.WARNING_MESSAGE);
			}
			System.out.println("keluar if");
	
	}
}