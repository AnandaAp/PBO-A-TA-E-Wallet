package Sources;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Home {
	
	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	public String name, email, body, address;
	public String balance;
	
	public Home(UserWallet u) {
		initialize(u);
		
	}
	
	
	
	
	private void initialize(UserWallet u) {
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
					case 2 : break;
					case 3 : email = info; break;
					case 4 : body = info; break;
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
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Greetings
		JLabel lblNewLabel = new JLabel("Welcome!");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 120, 25);
		frame.getContentPane().add(lblNewLabel);
		
		//Profile button
		JButton btnNewButton = new JButton("Profile");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toProfile(e,u);
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(335, 16, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		//Add Balanca button
		JButton btnNewButton_1 = new JButton("Add Balance");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toAddBalance(e,u);
			}
		});
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(76, 200, 120, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		//Withdraw Balanca button
		JButton btnNewButton_1_1 = new JButton("Withdraw Balance");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toWithdrawBalance(e,u);
			}
		});
		
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1_1.setBounds(215, 200, 135, 23);
		frame.getContentPane().add(btnNewButton_1_1);
		
		//Info - Name
		JLabel lblNewLabel_1 = new JLabel("Name : ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(10, 66, 65, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(name);
		lblNewLabel_2.setBounds(84, 66, 189, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		//Info - Balance
		JLabel lblNewLabel_1_1 = new JLabel("Balance : ");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1_1.setBounds(10, 102, 65, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Rp."+balance);
		lblNewLabel_2_1.setBounds(84, 102, 189, 14);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		//History Button
		JButton btnNewButton_2 = new JButton("History");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toHistory(e ,u);
			}
		});
		
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBounds(335, 50, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
	}
	

	public void toAddBalance(ActionEvent evt,UserWallet u) {
		AddBalance pro1 = new AddBalance(u);
		pro1.frame.setVisible(true);
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(this.frame.EXIT_ON_CLOSE);
		this.frame.dispose();
	}
	public void toWithdrawBalance(ActionEvent evt, UserWallet u) {
		WithdrawBalance pro1 = new WithdrawBalance(u);
		pro1.frame.setVisible(true);
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(this.frame.EXIT_ON_CLOSE);
		this.frame.dispose();
	}
	public void toProfile(ActionEvent evt, UserWallet u) {
		Profile pro1= new Profile(u);
		pro1.frame.setVisible(true);
		this.frame.setVisible(false);
		this.frame.setDefaultCloseOperation(this.frame.EXIT_ON_CLOSE);
		this.frame.dispose();
	}
	public void toHistory(ActionEvent evt, UserWallet u ) {
		History h1 = new History(u);
		h1.frame.setVisible(true);
		this.frame.setVisible(false);
		this.frame.setDefaultCloseOperation(this.frame.EXIT_ON_CLOSE);
		this.frame.dispose();
	}
}
