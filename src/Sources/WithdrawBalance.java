package Sources;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
public class WithdrawBalance extends AbstractBorder implements BackHome{

	
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	private JTextField textField;
	public String name,password, email, brth, address, balance;
	private Color color;
    private int thickness = 4;
    private int radii = 8;
    private int pointerSize = 7;
    private Insets insets = null;
    private BasicStroke stroke = null;
    private int strokePad;
    private int pointerPad = 4;
    RenderingHints hints;
    private String numPat = "\\d+";
    public ImageIcon icon;
    
    /**
	 * @wbp.parser.constructor
	 */
	public WithdrawBalance(UserWallet u) {
		this.initialize(u);
	}
	public WithdrawBalance(Color color) {
		this(color, 4, 8, 7);
	}
	public WithdrawBalance(
		    Color color, int thickness, int radii, int pointerSize) {
	        this.thickness = thickness;
	        this.radii = radii;
	        this.pointerSize = pointerSize;
		    this.color = color;
		
		    stroke = new BasicStroke(thickness);
		    strokePad = thickness/2;
		
		    hints = new RenderingHints(
		        RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		
		    int pad = radii + strokePad;
		    int bottomPad = pad + pointerSize + strokePad;
		    insets = new Insets(pad,pad,bottomPad,pad);
		}

	private void initialize(UserWallet u) {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("images/wallet.png"));
		frame.setTitle("E-Wallet");
		frame.setBounds(100, 100, 350, 239);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(234, 240, 248));
		
		//title
		JLabel lblNewLabel = new JLabel("Withdraw Balance");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(79, 21, 189, 25);
		frame.getContentPane().add(lblNewLabel);
		
		//withdraw
		JLabel lblNewLabel_1 = new JLabel("IDR");
		lblNewLabel_1.setBounds(161, 56, 27, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(79, 74, 183, 32);
		textField.setBorder(new Register(Color.black.darker(),2,6,0));
		frame.getContentPane().add(textField);
		
		//confirm button
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.setBackground(new Color(0, 255, 204));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WithdrawFunction(textField);
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(179, 111, 85, 23);
		frame.getContentPane().add(btnNewButton);
		
		//back button
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toHome(u);
			}
		});
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(80, 111, 85, 23);
		frame.getContentPane().add(btnNewButton_1);
		JLabel copyRight = new JLabel("\u00A9Copyright 2020");
		copyRight.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		copyRight.setBounds(129, 174, 89, 14);
		copyRight.setToolTipText("Author - Rusel Alexander /71180251 - " + 
				"Y. T. Rinto Pradhana / 71180259 - " + 
				"Ananda Apriliansah / 71180263 - " + 
				"Yoga Kurnia Widi Pratama / 71180277");
		frame.getContentPane().add(copyRight);
		this.icon = new ImageIcon("images/Back.png");
		JLabel back = new JLabel("");
		back.setIcon(new ImageIcon("images/Back.png"));
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toHome(u);
			}
		});
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		back.setFont(new Font("Tahoma", Font.BOLD, 11));
		back.setBounds(6, 5, 37, 25);
		frame.getContentPane().add(back);
	}
	@Override
	public void toHome(UserWallet u) {
		Home pro1 = new Home(u);
		pro1.frame.setVisible(true);
		this.frame.setVisible(true);
		this.frame.dispose();
	}
	
//create Withdraw History
	public void createHistoryWithdraw(String value) {
		File history = new File("profile/"+Main.User+"history.txt");
		if(!history.exists()) {
			try {
				history.createNewFile();
				FileWriter Write = new FileWriter(history);
				Write.write("Pengeluaran sebesar Rp."+value+"\n");
				Write.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				history.createNewFile();
				FileWriter Write = new FileWriter(history,true);
				Write.write("Pengeluaran sebesar Rp."+value+"\n");
				Write.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
//Withdraw Function
	public void WithdrawFunction(JTextField textField) {
		String value = textField.getText();
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
					case 2 : password = info; break;
					case 3 : email = info; break;
					case 4 : brth = info; break;
					case 5 : address = info;break;
					case 6 : balance = info;break;
					default : break;
					}
					info = infoinput.readLine();
				}				
				infoinput.close();
				
			}catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			}catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		
		Pattern pat = Pattern.compile(numPat);
		Matcher mat = pat.matcher(value);
		boolean ok = mat.matches();
		if(ok) {
//value type double 
		double value1 = Double.parseDouble(value);
		double balance1 = Double.parseDouble(balance);
		IDR idr = new IDR(balance1);
//IDR check
		if(!value.isEmpty()) {
			if(value1 <= idr.getValue() && value1 >0) {
//				double saldo = balance1 - value1;
				idr.withdrawValue(value1);
				File f2 = new File("profile/"+Main.User+".txt");
				File file = new File("profile/temp.txt");
			this.createHistoryWithdraw(value);
//Overwrite file	
				
				if(!file.exists()) {
					try {
						file.createNewFile();
						FileWriter Write = new FileWriter(file);
				
						Write.write(""+name+"\n"+password+"\n"+ 
								    email + "\n" + brth +
								    "\n" + address+"\n"+Math.round(idr.getValue()));
						Write.close();
					} 
					catch (IOException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
					
				}
				
				else {
					FileWriter reWrite;
					try {
						reWrite = new FileWriter(file,false);
						reWrite.write(""+name+"\n"+password+"\n"+ 
							    email + "\n" + brth +
							    "\n" + address+"\n"+Math.round(idr.getValue()));
						reWrite.close();
					} 
					catch (IOException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
					}	
					
				}
				
				if(f2.exists()) {
					FileWriter reWrite;
					try {
						reWrite = new FileWriter(f2,false);
						reWrite.write(""+name+"\n"+password+"\n"+ 
							    email + "\n" + brth +
							    "\n" + address+"\n"+Math.round(idr.getValue()));
						JOptionPane.showMessageDialog(frame, "Transaksi Berhasil", "Input Success", JOptionPane.PLAIN_MESSAGE);
						reWrite.close();
					} catch (IOException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
							
			}
				//overwrite file end
			}
			else {
				JOptionPane.showMessageDialog(frame, "Input tidak boleh melebihi jumlah saldo","Input Error",JOptionPane.WARNING_MESSAGE);
			}
		}
		}
		else {
			JOptionPane.showMessageDialog(frame, "Input yang anda masukkan salah","Input Error",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	@Override
	public Insets getBorderInsets(Component c) {
	    return insets;
	}
	
	@Override
	public Insets getBorderInsets(Component c, Insets insets) {
	    return getBorderInsets(c);
	}
	
	@Override
	public void paintBorder(
	    Component c,
	    Graphics g,
	    int x, int y,
	    int width, int height) {
	
	    Graphics2D g2 = (Graphics2D)g;
	
	    int bottomLineY = height-thickness-pointerSize;
	
	    RoundRectangle2D.Double bubble = new RoundRectangle2D.Double(
	        0+strokePad,
	        0+strokePad,
	        width-thickness,
	        bottomLineY,
	        radii,
	        radii
	        );
	
	    Polygon pointer = new Polygon();
	
	    // left point
	    pointer.addPoint(
	        strokePad+radii+pointerPad,
	        bottomLineY);
	    // right point
	    pointer.addPoint(
	        strokePad+radii+pointerPad+pointerSize,
	        bottomLineY);
	    // bottom point
	    pointer.addPoint(
	        strokePad+radii+pointerPad+(pointerSize/2),
	        height-strokePad);
	
	    Area area = new Area(bubble);
	    area.add(new Area(pointer));
	
	    g2.setRenderingHints(hints);
	
	    Area spareSpace = new Area(new Rectangle(0,0,width,height));
	    spareSpace.subtract(area);
	    g2.setClip(spareSpace);
	    g2.clearRect(0,0,width,height);
	    g2.setClip(null);
	
	    g2.setColor(color);
	    g2.setStroke(stroke);
	    g2.draw(area);
	}
}