package Sources;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Profile {

	public JFrame frame;
	public String nam, eml, bdy, ads;

	public Profile(UserWallet u) {
		initialize(u);
	}

	private void initialize(UserWallet u) {
		String info = "";
		int lihat = 0;
		try {
			BufferedReader infoinput = new BufferedReader(new FileReader(new File("profile/"+Main.User+".txt")));
			try {
				info = infoinput.readLine();
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
					info = infoinput.readLine();
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//title
		JLabel lblNewLabel = new JLabel("Profile");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 72, 25);
		frame.getContentPane().add(lblNewLabel);
		
		//print name
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(10, 47, 72, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_5 = new JLabel(nam);
		lblNewLabel_5.setBounds(104, 47, 302, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		//print email
		JLabel lblNewLabel_2 = new JLabel("E-mail");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_2.setBounds(10, 78, 72, 14);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_5_1 = new JLabel(eml);
		lblNewLabel_5_1.setBounds(104, 78, 302, 14);
		frame.getContentPane().add(lblNewLabel_5_1);
		
		//print birthday
		JLabel lblNewLabel_3 = new JLabel("Date of Birth");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_3.setBounds(10, 114, 72, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_5_2 = new JLabel(bdy);
		lblNewLabel_5_2.setBounds(104, 114, 302, 14);
		frame.getContentPane().add(lblNewLabel_5_2);
		
		//print address
		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_4.setBounds(10, 149, 72, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5_3 = new JLabel(ads);
		lblNewLabel_5_3.setBounds(104, 149, 302, 14);
		frame.getContentPane().add(lblNewLabel_5_3);
		
		//back button
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		//logout button
		JButton btnNewButton_1 = new JButton("Logout");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.User="";
				toHome(e, u);
			}
		});
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(335, 227, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
	}
	
	public void toHome(ActionEvent evt, UserWallet u) {
		Wallet pro1= new Wallet(u);
		pro1.frame.setVisible(true);
		this.frame.setVisible(false);
		this.frame.setDefaultCloseOperation(this.frame.EXIT_ON_CLOSE);
		this.frame.dispose();
	}

}
