package Sources;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;

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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.ActionEvent;
public class WithdrawBalance extends AbstractBorder{

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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(UserWallet u) {
		frame = new JFrame();
		frame.setBounds(100, 100, 350, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//title
		JLabel lblNewLabel = new JLabel("Withdraw Balance");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(71, 11, 202, 25);
		frame.getContentPane().add(lblNewLabel);
		
		//withdraw
		JLabel lblNewLabel_1 = new JLabel("IDR");
		lblNewLabel_1.setBounds(150, 47, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(71, 72, 183, 28);
		textField.setBorder(new Register(Color.black.darker(),2,6,0));
		frame.getContentPane().add(textField);
		
		//confirm button
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WithdrawFunction(textField);
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(165, 114, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		//back button
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toHome(e, u);
			}
		});
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(71, 114, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
	}
	public void toHome(ActionEvent evt,UserWallet u) {
		Home pro1 = new Home(u);
		pro1.frame.setVisible(true);
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(this.frame.EXIT_ON_CLOSE);
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
				e.printStackTrace();
			}
			
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		
		Pattern pat = Pattern.compile(numPat);
		Matcher mat = pat.matcher(value);
		boolean ok = mat.matches();
		System.out.println(value);
		if(ok) {
//value type double 
		double value1 = Double.parseDouble(value);
		double balance1 = Double.parseDouble(balance);
//IDR check
		if(!value.isEmpty()) {
			if(value1 <= balance1 && value1 >0) {
				double saldo = balance1 - value1;
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
								    "\n" + address+"\n"+Math.round(saldo));
						Write.close();
					} 
					catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				
				else {
					FileWriter reWrite;
					try {
						reWrite = new FileWriter(file,false);
						reWrite.write(""+name+"\n"+password+"\n"+ 
							    email + "\n" + brth +
							    "\n" + address+"\n"+Math.round(saldo));
						System.out.println("ternyata masuk else");
						
						reWrite.close();
					} 
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					
				}
				
				if(f2.exists()) {
					FileWriter reWrite;
					try {
						reWrite = new FileWriter(f2,false);
						reWrite.write(""+name+"\n"+password+"\n"+ 
							    email + "\n" + brth +
							    "\n" + address+"\n"+Math.round(saldo));
						System.out.println("ternyata masuk else");
						JOptionPane.showMessageDialog(frame, "Transaksi Berhasil", "Input Success", JOptionPane.PLAIN_MESSAGE);
						reWrite.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
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
