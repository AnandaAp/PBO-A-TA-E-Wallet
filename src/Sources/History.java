package Sources;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class History {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					History window = new History();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public History() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("History");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 81, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel Transaksi1 = new JLabel("");
		Transaksi1.setBounds(10, 47, 414, 14);
		frame.getContentPane().add(Transaksi1);
		
		JLabel Transaksi2 = new JLabel("");
		Transaksi2.setBounds(10, 80, 414, 14);
		frame.getContentPane().add(Transaksi2);
		
		JLabel Transaksi3 = new JLabel("");
		Transaksi3.setBounds(10, 116, 414, 14);
		frame.getContentPane().add(Transaksi3);
		
		JLabel Transaksi4 = new JLabel("");
		Transaksi4.setBounds(10, 153, 414, 14);
		frame.getContentPane().add(Transaksi4);
		
		JLabel Transaksi5 = new JLabel("");
		Transaksi5.setBounds(10, 191, 414, 14);
		frame.getContentPane().add(Transaksi5);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Show All History");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(291, 227, 133, 23);
		frame.getContentPane().add(btnNewButton_1);
	}
}
