package Sources;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.Font;

import javax.swing.ImageIcon;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	private String sql = "select * from history where email = ?";
	public History() {
		initialize();
	}

	private void initialize() {
		try {
			ConnectionDataBase db = new ConnectionDataBase();
			db.connectDB();
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
				try {
					try(PreparedStatement pr = db.con.prepareStatement(sql)){
						pr.setString(1, Main.User);
						ResultSet rs = pr.executeQuery();
						if(rs.isBeforeFirst()) {
							while(rs.next()) {
								area.append(rs.getString("keterangan"));
								area.append("\n");
								panel.add(area, BorderLayout.CENTER);
								panel.setLayout(new BorderLayout(0,0));
								JScrollPane scroll = new JScrollPane(area,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
								panel.add(scroll);
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
						area.setEditable(false);
						pr.close();
						rs.close();
						db.closeDB();
					}
				} catch (SQLException e1) {
					System.out.println(e1.getMessage());
					e1.printStackTrace();
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
				toHome();
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
	public void toHome() {
		Home pro1= new Home();
		pro1.frame.setVisible(true);
		this.frame.setVisible(false);
		this.frame.dispose();
	}
}
