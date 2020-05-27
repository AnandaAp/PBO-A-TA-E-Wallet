package Sources;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.Font;

import javax.swing.ImageIcon;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;



import javax.swing.JTextArea;
import javax.swing.JPanel;

public class History implements BackHome{
	public String transaksi1, transaksi2, transaksi3, transaksi4;
	public String transaksi5;
	public JFrame frame;
	private ImageIcon icon;
	
	public History(UserWallet u) {
		initialize(u);
	}

	private void initialize(UserWallet u) {
		String alamat = "profile/"+Main.User+"History.txt";
		File file = new File(alamat);
		try {
			frame = new JFrame();
			frame.setResizable(false);
			frame.setIconImage(Toolkit.getDefaultToolkit().getImage("images/wallet.png"));
			frame.setTitle("E-Wallet");
			frame.getContentPane().setBackground(new Color(234, 240, 248));
			frame.getContentPane().setForeground(Color.BLACK);
			frame.setBounds(100, 100, 310, 320);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			JLabel lblNewLabel = new JLabel("History");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblNewLabel.setBounds(110, 6, 81, 25);
			frame.getContentPane().add(lblNewLabel);
			JPanel panel = new JPanel();
			panel.setBounds(13, 42, 274, 223);			
			frame.getContentPane().add(panel);
			JTextArea area = new JTextArea(20,20);
			area.setEditable(false);
			if(file.exists()) {
				try {
					FileReader read = new FileReader(alamat);
					BufferedReader bread = new BufferedReader(read);
					area.read(bread, null);
					bread.close();
					panel.add(area, BorderLayout.CENTER);
					panel.setLayout(new BorderLayout(0,0));
					JScrollPane scroll = new JScrollPane(area,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					panel.add(scroll);
				} catch (IOException e1) {
					System.out.println(e1.getMessage());
					e1.printStackTrace();
				}
			}
			else {
				area.setText("Belum Ada Transaksi");
				panel.add(area, BorderLayout.CENTER);
				panel.setLayout(new BorderLayout(0,0));
				JScrollPane scroll = new JScrollPane(area,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				panel.add(scroll);
				JOptionPane.showMessageDialog(frame, "Maaf Belum Ada Transaksi Yang Tercatat");
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		this.icon = new ImageIcon("images/Back.png");
		JLabel back = new JLabel("");
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toHome(u);
			}
		});
		back.setIcon(this.icon);
		back.setFont(new Font("Tahoma", Font.BOLD, 11));
		back.setBounds(3, 6, 47, 25);
		frame.getContentPane().add(back);
		
		JLabel copyRight = new JLabel("\u00A9Copyright 2020");
		copyRight.setToolTipText("Author - Rusel Alexander /71180251 - Y. T. Rinto Pradhana / 71180259 - Ananda Apriliansah / 71180263 - Yoga Kurnia Widi Pratama / 71180277");
		copyRight.setFont(new Font("Dialog", Font.PLAIN, 10));
		copyRight.setBounds(105, 263, 87, 17);
		frame.getContentPane().add(copyRight);
		
		
		
		
	}

	
	@Override
	public void toHome(UserWallet u) {
		Home pro1= new Home(u);
		pro1.frame.setVisible(true);
		this.frame.setVisible(false);
		this.frame.dispose();
	}
}
