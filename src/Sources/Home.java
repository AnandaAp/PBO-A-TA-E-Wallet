package Sources;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
//import java.awt.Frame;
import java.util.Locale;

public class Home {
	
	@SuppressWarnings("exports")
	public JFrame frame;
	private String name;
	private Double balance;
	
	public Home() {
		initialize();
		
	}
	
	private void initialize() {
		ConnectionDataBase db = new ConnectionDataBase();
		db.connectDB();
		String query = "SELECT saldo From user_saldo where email = ?";
		String sql = "Select nama from user_akun where email = ?";
		ResultSet rs;
		try (PreparedStatement pr = db.con.prepareStatement(query)){
			pr.setString(1, Main.User);
			rs = pr.executeQuery();
			if(rs.next()) {
				this.balance = rs.getDouble("saldo");
				
			}
		} catch (SQLException e) {
			System.out.println("error: "+e.getMessage());
			e.printStackTrace();
		}
		try(PreparedStatement pr = db.con.prepareStatement(sql)){
			pr.setString(1, Main.User);
			rs = pr.executeQuery();
			if(rs.next()) {
				this.name =rs.getString("nama");
			}
		}
		catch (SQLException e1) {
			System.out.println("error: "+e1.getMessage());
			e1.printStackTrace();
		}
		
		
		
		IDR idr = new IDR(this.balance);
		frame = new JFrame();
		frame.setLocale(new Locale("jv", "ID"));
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("images/wallet.png"));
		frame.setTitle("E-WAllet");
		frame.getContentPane().setBackground(new Color(234, 240, 248));
		frame.setBounds(100, 100, 450, 317);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Welcome Text
		JLabel welcomeText = new JLabel("WELCOME");
		welcomeText.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		welcomeText.setBounds(150, 25, 196, 50);
		frame.getContentPane().add(welcomeText);
		//Welcome Icon
		JLabel welcomeIcon = new JLabel("");
		welcomeIcon.setIcon(new ImageIcon("images/Welcome.png"));
		welcomeIcon.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		welcomeIcon.setBounds(20, 25, 108, 100);
		frame.getContentPane().add(welcomeIcon);
		
		//Profile button
		JLabel profile = new JLabel();
		profile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toProfile();
			}
		});
		profile.setToolTipText("Cek Profil Kamu");
		profile.setIcon(new ImageIcon("images/Account.png"));
		profile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		profile.setFont(new Font("Tahoma", Font.BOLD, 11));
		profile.setBounds(390, 14, 37, 30);
		frame.getContentPane().add(profile);
		
		//Add Balanca button
		JButton addBalance = new JButton("Add Balance");
		addBalance.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toAddBalance(idr);
			}
		});
		
		addBalance.setFont(new Font("Tahoma", Font.BOLD, 11));
		addBalance.setBounds(98, 200, 120, 23);
		frame.getContentPane().add(addBalance);
		
		//Withdraw Balanca button
		JButton withdrawBalance = new JButton("Withdraw Balance");
		withdrawBalance.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		withdrawBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toWithdrawBalance(idr);
			}
		});
		
		withdrawBalance.setFont(new Font("Tahoma", Font.BOLD, 11));
		withdrawBalance.setBounds(222, 200, 137, 23);
		frame.getContentPane().add(withdrawBalance);
		
		JLabel nameTitle = new JLabel(name);
		nameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		nameTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		nameTitle.setBounds(126, 76, 234, 25);
		frame.getContentPane().add(nameTitle);
		
		//Info - Balance
		JLabel saldo = new JLabel("Saldo Anda");
		saldo.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		saldo.setBounds(191, 139, 77, 14);
		frame.getContentPane().add(saldo);
		
		JLabel saldoValue = new JLabel("Rp."+Math.round(this.balance));
		saldoValue.setHorizontalAlignment(SwingConstants.CENTER);
		saldoValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		saldoValue.setBounds(100, 163, 243, 25);
		frame.getContentPane().add(saldoValue);
		
		//History Button
		JLabel history = new JLabel("");
		history.setToolTipText("Cek History Transaksi Kamu");
		history.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toHistory();
			}
		});
		history.setIcon(new ImageIcon("images/HISTORY.png"));
		history.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		history.setFont(new Font("Tahoma", Font.BOLD, 11));
		history.setBounds(389, 57, 40, 30);
		frame.getContentPane().add(history);
		
		//Copyright
		JLabel copyRight = new JLabel("\u00A9Copyright 2020");
		copyRight.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		copyRight.setBounds(181, 257, 89, 14);
		copyRight.setToolTipText("Author - Rusel Alexander /71180251 - " + 
				"Y. T. Rinto Pradhana / 71180259 - " + 
				"Ananda Apriliansah / 71180263 - " + 
				"Yoga Kurnia Widi Pratama / 71180277");
		frame.getContentPane().add(copyRight);
		
	}
	

	public void toAddBalance(Currency c) {
		AddBalance pro1 = new AddBalance(c);
		pro1.frame.setVisible(true);
		this.frame.setVisible(true);
		this.frame.dispose();
	}
	public void toWithdrawBalance(Currency c) {
		WithdrawBalance pro1 = new WithdrawBalance(c);
		pro1.frame.setVisible(true);
		this.frame.setVisible(true);
		this.frame.dispose();
	}
	public void toProfile() {
		Profile pro1= new Profile();
		pro1.frame.setVisible(true);
		this.frame.setVisible(false);
		this.frame.dispose();
	}
	public void toHistory() {
		History h1 = new History();
		h1.frame.setVisible(true);
		this.frame.setVisible(false);
		this.frame.dispose();
	}
	
}
