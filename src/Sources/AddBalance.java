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
public class AddBalance extends AbstractBorder implements BackHome{

	private static final long serialVersionUID = 1L;
	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
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
    private JLabel lblNewLabel_3;
    public ImageIcon icon;
    
    /**
	 * @wbp.parser.constructor
	 */
	public AddBalance(UserWallet u) {
		this.initialize(u);
	}
	public AddBalance(Color color) {
		this(color, 4, 8, 7);
	}
	public AddBalance(
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
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("images/wallet.png"));
		frame.setTitle("E-Wallet");
		frame.setBounds(100, 100, 450, 264);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(234, 240, 248));
		//title
		JLabel lblNewLabel = new JLabel("Add Balance");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(155, 11, 131, 25);
		frame.getContentPane().add(lblNewLabel);
		
		//add idr
		JLabel lblNewLabel_1 = new JLabel("IDR");
		lblNewLabel_1.setBounds(112, 71, 27, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(34, 89, 180, 32);
		textField.setBorder(new Register(Color.black.darker(),2,6,0));
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		//add usd
		JLabel lblNewLabel_2 = new JLabel("USD");
		lblNewLabel_2.setBounds(317, 71, 32, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(236, 89, 180, 32);
		textField_1.setBorder(new Register(Color.black.darker(),2,6,0));
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		//confirm button
		btnNewButton = new JButton("Confrim");
		btnNewButton.setBackground(new Color(0, 255, 204));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBalanceFunction(textField,textField_1);
			}

		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(230, 158, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		//back button
		btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toHome(u);
			}
		});
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(132, 158, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		lblNewLabel_3 = new JLabel("Anda Hanya Dapat Memasukan Salah Satu Currency");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBounds(90, 130, 283, 16);
		frame.getContentPane().add(lblNewLabel_3);
		//Copyrigth
		JLabel copyRight = new JLabel("\u00A9Copyright 2020");
		copyRight.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		copyRight.setBounds(182, 206, 89, 14);
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
	
	//back home
	
	@Override
	public void toHome(UserWallet u) {
		Home pro1 = new Home(u);
		pro1.frame.setVisible(true);
		this.frame.setVisible(false);
		this.frame.dispose();
	}
	
//History method idr
	public void createHistoryIDR(String idr) {
		File history = new File("profile/"+Main.User+"history.txt");
		if(!history.exists()) {
			try {
				history.createNewFile();
				FileWriter Write = new FileWriter(history);
				Write.write("Pemasukkan sebesar Rp."+idr+"\n");
				Write.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				history.createNewFile();
				FileWriter Write = new FileWriter(history,true);
				Write.write("Pemasukkan sebesar Rp."+idr+"\n");
				Write.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
//History method usd
	public void createHistoryUSD(String usd) {
		File history = new File("profile/"+Main.User+"history.txt");
		if(!history.exists()) {
			try {
				history.createNewFile();
				FileWriter Write = new FileWriter(history);
				Write.write("Pemasukkan sebesar $"+usd+"\n");
				Write.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				history.createNewFile();
				FileWriter Write = new FileWriter(history,true);
				Write.write("Pemasukkan sebesar $"+usd+"\n");
				Write.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
//Addbalance
	public void AddBalanceFunction(JTextField textField, JTextField textField_1) {
		String idr = textField.getText();
		String usd = textField_1.getText();
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
		System.out.println(balance);
		Pattern pat = Pattern.compile(numPat);
		Matcher mat = pat.matcher(idr);
		Matcher matu = pat.matcher(usd);
		boolean ok = mat.matches();
		boolean ok2 = matu.matches();
		if(ok || ok2) {
			//balance type double 
			double balance1 = Double.parseDouble(balance);
			
			
			IDR idr2 = new IDR(balance1);
			
			
//IDR check
			if(!idr.isEmpty()&& usd.isEmpty()) {
			  
				double idr1 =Double.parseDouble(idr);
				if(idr1 > 0) {
					idr2.addValue(idr1);
					File f2 = new File("profile/"+Main.User+".txt");
					File file = new File("profile/temp.txt");
					
					this.createHistoryIDR(idr);
		
//Overwrite file	
					if(!file.exists()) {
						try {
							file.createNewFile();
							FileWriter Write = new FileWriter(file);
					
							Write.write(""+name+"\n"+password+"\n"+ 
									    email + "\n" + brth +
									    "\n" + address+"\n"+Math.round(idr2.getValue()));
							
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
								    "\n" + address+"\n"+Math.round(idr2.getValue()));
							System.out.println("ternyata masuk else");
							
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
								    "\n" + address+"\n"+Math.round(idr2.getValue()));
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
					JOptionPane.showMessageDialog(frame, "Input yang anda masukan salah","Input Error",JOptionPane.WARNING_MESSAGE);
				}
			}	
//USD check
			else if(!usd.isEmpty() && idr.isEmpty()) {
				System.out.println("masuk usd");
				  double usd1 = Double.parseDouble(usd);
				  if(usd1 > 0) {
					  System.out.println("objek usd");
						USD usd2 = new USD();
						System.out.println("objek ada");
					this.createHistoryUSD(usd);
					usd2.addValue(usd1);
					usd2.toIDR();
					idr2.addValue(usd2.getValue());
					System.out.println(usd2.getValue());
					File f3 = new File("profile/"+Main.User+".txt");
					File file1 = new File("profile/temp.txt");
				
				//overwrite file
					if (file1.exists()){
						FileWriter reWrite;
						try {
							reWrite = new FileWriter(file1,false);
							reWrite.write(""+name+"\n"+password+"\n"+ 
								    email + "\n" + brth +
								    "\n" + address+"\n"+Math.round(idr2.getValue()));
							System.out.println("ternyata masuk else");
							reWrite.close();
							} 
						catch (IOException e) {
							e.printStackTrace();
							}				
					}
					
					if(f3.exists()) {
						FileWriter reWrite;
						try {
							reWrite = new FileWriter(f3,false);
							reWrite.write(""+name+"\n"+password+"\n"+ 
								    email + "\n" + brth +
								    "\n" + address+"\n"+Math.round(idr2.getValue()));
							System.out.println("ternyata masuk else");
							JOptionPane.showMessageDialog(frame, "Transaksi Berhasil", "Input Success", JOptionPane.PLAIN_MESSAGE);
							reWrite.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				  }
				  
				}//end usd check	
			else{
				JOptionPane.showMessageDialog(frame, "Maaf anda hanya dapat memasukkan satu currency saja ","Input Error",JOptionPane.WARNING_MESSAGE);
			}		
		}
			
		else {
			JOptionPane.showMessageDialog(frame, "Input yang anda masukan salah","Input Error",JOptionPane.WARNING_MESSAGE);
		}
		
	}//end method
	
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
