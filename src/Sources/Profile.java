package Sources;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.ImageIcon;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

public class Profile implements BackHome{

	public JFrame frame;
	private String nam, eml, bdy, ads;
	public ImageIcon icon;
	private String sql = "select nama,email,tglLahir,alamat from user_akun where email = ?";
	private ResultSet rs;
	public Profile() {
		initialize();
	}

	private void initialize() {
		ConnectionDataBase db = new ConnectionDataBase();
		db.connectDB();
		try(PreparedStatement ps = db.con.prepareStatement(sql)){
			ps.setString(1, Main.User);
			this.rs = ps.executeQuery();
			if(rs.next()) {
				this.nam = rs.getString("nama");
				this.ads = rs.getString("alamat");
				this.bdy = rs.getString("tglLahir");
				this.eml = rs.getString("email");
			}
			this.rs.close();
			ps.close();
			db.closeDB();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("images/wallet.png"));
		frame.setTitle("E-Wallet");
		frame.setBounds(100, 100, 450, 308);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(234, 240, 248));
		
		//title
		JLabel profile = new JLabel("Profile");
		profile.setFont(new Font("Tahoma", Font.BOLD, 20));
		profile.setBounds(180, 5, 76, 25);
		frame.getContentPane().add(profile);
		
		//print name
		JLabel name = new JLabel("Name : ");
		name.setHorizontalAlignment(SwingConstants.LEFT);
		name.setFont(new Font("Tahoma", Font.PLAIN, 10));
		name.setBounds(30, 47, 72, 14);
		frame.getContentPane().add(name);

		JLabel nameValue = new JLabel(nam);
		nameValue.setBounds(124, 47, 302, 15);
		frame.getContentPane().add(nameValue);
		
		//print email
		JLabel email = new JLabel("E-mail : ");
		email.setFont(new Font("Tahoma", Font.PLAIN, 10));
		email.setBounds(30, 78, 72, 14);
		frame.getContentPane().add(email);

		JLabel emailValue = new JLabel(eml);
		emailValue.setBounds(124, 78, 302, 16);
		frame.getContentPane().add(emailValue);
		
		//print birthday
		JLabel birth = new JLabel("Date of Birth : ");
		birth.setFont(new Font("Tahoma", Font.PLAIN, 10));
		birth.setBounds(30, 114, 72, 14);
		frame.getContentPane().add(birth);
		
		JLabel birthValue = new JLabel(bdy);
		birthValue.setBounds(124, 114, 302, 14);
		frame.getContentPane().add(birthValue);
		
		//print address
		JLabel address = new JLabel("Address : ");
		address.setFont(new Font("Tahoma", Font.PLAIN, 10));
		address.setBounds(30, 149, 72, 14);
		frame.getContentPane().add(address);
		
		JLabel addressValue = new JLabel(ads);
		addressValue.setBounds(124, 149, 302, 14);
		frame.getContentPane().add(addressValue);
		
		//back button
		this.icon = new ImageIcon("images/Back.png");
		JLabel back = new JLabel("");
		back.setIcon(new ImageIcon("images/Back.png"));
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toHome();
			}
		});
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		back.setFont(new Font("Tahoma", Font.BOLD, 11));
		back.setBounds(6, 5, 37, 25);
		frame.getContentPane().add(back);
		
		JLabel copyRight = new JLabel("\u00A9Copyright 2020");
		copyRight.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		copyRight.setBounds(171, 253, 89, 14);
		copyRight.setToolTipText("Author - Rusel Alexander /71180251 - " + 
				"Y. T. Rinto Pradhana / 71180259 - " + 
				"Ananda Apriliansah / 71180263 - " + 
				"Yoga Kurnia Widi Pratama / 71180277");
		frame.getContentPane().add(copyRight);
		
		JButton logOut = new JButton("Log Out");
		logOut.setForeground(Color.WHITE);
		logOut.setBackground(Color.RED);
		logOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				logOut();
			}
		});
		logOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logOut.setBounds(152, 212, 117, 29);
		frame.getContentPane().add(logOut);
	}
	
	public void logOut() {
		int keluar = JOptionPane.showConfirmDialog(frame, "Apakah Anda Benar ingin keluar?","Log Out",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,new ImageIcon("images/Exit.png"));
		switch (keluar) {
			case 0: 
				Wallet pro1= new Wallet();
				pro1.frame.setVisible(true);
				this.frame.setVisible(false);
				this.frame.dispose();
				Main.User = "";
				;
				break;
			case 1:
				break;
					
			default:
				throw new IllegalArgumentException("Unexpected value: " + keluar);
			}
		
	}
	@Override
	public void toHome() {
		Home pro1= new Home();
		pro1.frame.setVisible(true);
		this.frame.setVisible(false);
		this.frame.dispose();
	}
}