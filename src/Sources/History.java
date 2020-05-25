package Sources;
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

public class History {
	public String transaksi1, transaksi2, transaksi3, transaksi4;
	public String transaksi5;
	public JFrame frame;
	public History(UserWallet u) {
		initialize(u);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(UserWallet u) {
			String info = "";
			String info2= "";
			int count = 0,size = 0,step =0;
			
			try {
				BufferedReader infoinput2 = new BufferedReader (new FileReader(new File("profile/"+Main.User+"History.txt")));
				BufferedReader infoinput = new BufferedReader (new FileReader(new File("profile/"+Main.User+"History.txt")));
				try {
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
					for(int i = temp.length-1;i > 0;i--) {
						if(temp.length-1 ==i) {
							transaksi1 = temp[i];
							
						}
						else if(temp.length-2 ==i) {
							transaksi2 = temp[i];
							
						}
						else if(temp.length-3 ==i) {
							transaksi3 = temp[i];
							
						}
						else if(temp.length-4 ==i) {
							transaksi4 = temp[i];
							
						}
						else if(temp.length-5 ==i) {
							transaksi5 = temp[i];
							
						}
					
					}
					
					infoinput.close();
					infoinput2.close();

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
		
		JLabel lblNewLabel = new JLabel("History");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 81, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel Transaksi1 = new JLabel(transaksi1);
		Transaksi1.setBounds(10, 47, 414, 14);
		frame.getContentPane().add(Transaksi1);
		
		JLabel Transaksi2 = new JLabel(transaksi2);
		Transaksi2.setBounds(10, 80, 414, 14);
		frame.getContentPane().add(Transaksi2);
		
		JLabel Transaksi3 = new JLabel(transaksi3);
		Transaksi3.setBounds(10, 116, 414, 14);
		frame.getContentPane().add(Transaksi3);
		
		JLabel Transaksi4 = new JLabel(""+transaksi4);
		Transaksi4.setBounds(10, 153, 414, 14);
		frame.getContentPane().add(Transaksi4);
		
		JLabel Transaksi5 = new JLabel(transaksi5);
		Transaksi5.setBounds(10, 191, 414, 14);
		frame.getContentPane().add(Transaksi5);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toHome(e,u);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Show All History");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAllHistory();	
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(291, 227, 133, 23);
		frame.getContentPane().add(btnNewButton_1);
	}

	
	public void toHome(ActionEvent evt, UserWallet u) {
		Home pro1= new Home(u);
		pro1.frame.setVisible(true);
		this.frame.setVisible(false);
		this.frame.setDefaultCloseOperation(this.frame.EXIT_ON_CLOSE);
		this.frame.dispose();
	}
	
	public void showAllHistory() {
		ProcessBuilder pb = new ProcessBuilder("Notepad.exe","profile/"+Main.User+"History.txt");
				try {
					pb.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
}
