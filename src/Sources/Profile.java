package Sources;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.ImageIcon;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.Toolkit;

public class Profile {

	public JFrame frame;
	public String nam, eml, bdy, ads;
	public ImageIcon icon;
	private BufferedReader infoInput;
	public Profile(UserWallet u) {
		initialize(u);
	}

	private void initialize(UserWallet u) {
		String info = "";
		int lihat = 0;
		try {
			this.infoInput= new BufferedReader(new FileReader(new File("profile/"+Main.User+".txt")));
			try {
				info = infoInput.readLine();
				while(info != null) {
					lihat += 1;
					switch(lihat) {
						case 1 : nam = info; break;
						case 2 : break;
						case 3 : eml = info; break;
						case 4 : bdy = info; break;
						case 5 : ads = info; break;
						default : break;
					}
					info = infoInput.readLine();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(nam);
		System.out.println(eml);
		System.out.println(bdy);
		System.out.println(ads);
		
		
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("images/wallet.png"));
		frame.setTitle("E-Wallet");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(234, 240, 248));
		
		//title
		JLabel profile = new JLabel("Profile");
		profile.setFont(new Font("Tahoma", Font.BOLD, 20));
		profile.setBounds(180, 5, 76, 25);
		frame.getContentPane().add(profile);
		
		//print name
		JLabel name = new JLabel("Name : ");
		name.setFont(new Font("Tahoma", Font.PLAIN, 10));
		name.setBounds(30, 47, 72, 14);
		frame.getContentPane().add(name);

		JLabel nameValue = new JLabel(nam);
		nameValue.setBounds(124, 47, 302, 14);
		frame.getContentPane().add(nameValue);
		
		//print email
		JLabel email = new JLabel("E-mail : ");
		email.setFont(new Font("Tahoma", Font.PLAIN, 10));
		email.setBounds(30, 78, 72, 14);
		frame.getContentPane().add(email);

		JLabel emailValue = new JLabel(eml);
		emailValue.setBounds(124, 78, 302, 14);
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
				toHome(e, u);
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
		logOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				logOut(e, u);
			}
		});
		logOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logOut.setBounds(152, 212, 117, 29);
		frame.getContentPane().add(logOut);
	}
	
	public void logOut(MouseEvent e, UserWallet u) {
		int keluar = JOptionPane.showConfirmDialog(frame, "Apakah Anda Benar ingin keluar?","Log Out",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,new ImageIcon("images/Exit.png"));
		switch (keluar) {
			case 0: 
				Wallet pro1= new Wallet(u);
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
	public void toHome(MouseEvent e, UserWallet u) {
		Home pro1= new Home(u);
		pro1.frame.setVisible(true);
		this.frame.setVisible(false);
		this.frame.dispose();
	}
}