package Sources;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class History {
	public String transaksi1, transaksi2, transaksi3, transaksi4;
	public String transaksi5;
	public JFrame frame;
	private JLabel Transaksi1,Transaksi2,Transaksi3,Transaksi4,Transaksi5 ;
	private ImageIcon icon;
	public History(UserWallet u) {
		initialize(u);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(UserWallet u) {
		String info = "";
		String info2= "";
		int size = 0,step =0;
		String alamat = "profile/"+Main.User+"History.txt";
		File file = new File(alamat);
		try {
			frame = new JFrame();
			frame.setIconImage(Toolkit.getDefaultToolkit().getImage("images/wallet.png"));
			frame.setTitle("E-Wallet");
			frame.getContentPane().setBackground(new Color(234, 240, 248));
			frame.getContentPane().setForeground(Color.BLACK);
			frame.setBounds(100, 100, 310, 300);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			JLabel lblNewLabel = new JLabel("History");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblNewLabel.setBounds(120, 6, 81, 25);
			frame.getContentPane().add(lblNewLabel);
			if(file.exists()) {
				try {
					BufferedReader infoinput2 = new BufferedReader (new FileReader(new File(alamat)));
					BufferedReader infoinput = new BufferedReader (new FileReader(new File(alamat)));
					info = infoinput.readLine();
					info2 = infoinput2.readLine();
					while(info != null) {
						size++;				
						info = infoinput.readLine();
					}
					String[] temp = new String[size];		
					while(info2 != null) {
						temp[step]=info2;
						step++;
						info2 =infoinput2.readLine();
					}
					if(temp.length <= 5 ) {
						for(int i = 0;i < temp.length;i++) {
							switch(i) {
								case 0:this.transaksi1 = temp[i];break;
								case 1:this.transaksi2 = temp[i];break;
								case 2:this.transaksi3 = temp[i];break;
								case 3:this.transaksi4 = temp[i];break;
								case 4:this.transaksi5 = temp[i];break;
							}
						}
					}
					else {
						for(int i = temp.length;i > 0;i--) {
							if(temp.length-1 ==i) {
								transaksi5 = temp[i];				
							}
							else if(temp.length-2 ==i) {
								transaksi4 = temp[i];		
							}
							else if(temp.length-3 ==i) {
								transaksi3 = temp[i];	
							}
							else if(temp.length-4 ==i) {
								transaksi2 = temp[i];		
							}
							else if(temp.length-5 ==i) {
								transaksi1 = temp[i];		
							}
						}
					}
						infoinput.close();
						infoinput2.close();
					}
				catch (FileNotFoundException e) {
					e.printStackTrace();	
				}
				this.Transaksi1 = new JLabel(this.transaksi1);
				Transaksi1.setBounds(40, 47, 414, 14);
				frame.getContentPane().add(Transaksi1);
				
				this.Transaksi2 = new JLabel(this.transaksi2);
				Transaksi2.setBounds(40, 80, 414, 14);
				frame.getContentPane().add(Transaksi2);
				
				this.Transaksi3 = new JLabel(this.transaksi3);
				Transaksi3.setBounds(40, 116, 414, 14);
				frame.getContentPane().add(Transaksi3);
				
				this.Transaksi4 = new JLabel(this.transaksi4);
				Transaksi4.setBounds(40, 153, 414, 14);
				frame.getContentPane().add(Transaksi4);
				
				this.Transaksi5 = new JLabel(this.transaksi5);
				Transaksi5.setBounds(40, 191, 414, 14);
				frame.getContentPane().add(Transaksi5);
				
			}
			else {
				JOptionPane.showMessageDialog(frame, "Maaf Belum Ada Transaksi Yang Tercatat");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.icon = new ImageIcon("images/Back.png");
		JLabel back = new JLabel("");
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toHome(e, u);
			}
		});
		back.setIcon(this.icon);
		back.setFont(new Font("Tahoma", Font.BOLD, 11));
		back.setBounds(3, 6, 47, 25);
		frame.getContentPane().add(back);
		JButton btnNewButton_1 = new JButton("Show All History");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAllHistory();	
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(91, 233, 133, 23);
		frame.getContentPane().add(btnNewButton_1);
	}

	
	public void toHome(MouseEvent e, UserWallet u) {
		Home pro1= new Home(u);
		pro1.frame.setVisible(true);
		this.frame.setVisible(false);
		this.frame.dispose();
	}
	
	public void showAllHistory() {
		ProcessBuilder pb = new ProcessBuilder("Notepad.exe","profile/"+Main.User+"History.txt");
		try {
			pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
