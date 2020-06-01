package Sources;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.sql.*;
public class Wallet  extends AbstractBorder{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel hideAndShowPassword;
	public JFrame frame;
	private JTextField emailField;
	private JPasswordField passwordField;
	private ImageIcon image;
	private boolean check = false;
	private Color color;
    private int thickness = 4;
    private int radii = 8;
    private int pointerSize = 7;
    private Insets insets = null;
    private BasicStroke stroke = null;
    private int strokePad;
    private int pointerPad = 4;
    RenderingHints hints;
    private final JLabel copyright = new JLabel("\u00A9Copyright 2020");
    /**
	 * @wbp.parser.constructor
	 */
	public Wallet() {
		this.initialize();
	}
	public Wallet(Color color) {
		this(color, 4, 8, 7);
	}
	public Wallet(
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

	private void initialize() {
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("images/wallet.png"));
		frame.setTitle("E-Wallet");
		frame.getContentPane().setBackground(new Color(234, 240, 248));
		frame.setBounds(100, 100, 420, 275);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//title
		JLabel lblNewLabel_1 = new JLabel("E-Wallet");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(154, 25, 89, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		//username
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		emailLabel.setBounds(37, 78, 71, 16);
		frame.getContentPane().add(emailLabel);
		
		emailField = new JTextField();
		emailField.setBorder(new Register(Color.black.darker(),2,6,0));
		emailField.setBounds(117, 72, 236, 32);
		frame.getContentPane().add(emailField);
		emailField.setColumns(10);
		
		//password
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		passwordLabel.setBounds(37, 127, 71, 16);
		frame.getContentPane().add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('•');
		passwordField.setBorder(new Register(Color.black.darker(),2,6,0));
		passwordField.setBounds(117, 121, 236, 32);
		frame.getContentPane().add(passwordField);
		
		//login button
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cekAkun(emailField, passwordField);
			}
		});
		loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		loginButton.setBounds(152, 161, 90, 23);
		frame.getContentPane().add(loginButton);
		
		//register button
		JLabel registerLabel = new JLabel("Don't have any account yet?");
		registerLabel.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		registerLabel.setBounds(82, 191, 162, 20);
		frame.getContentPane().add(registerLabel);
		
		JLabel registerButton = new JLabel("Register Now!!!");
		registerButton.setForeground(new Color(0, 206, 209));
		registerButton.setToolTipText("Click this to go to register page");
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toRegister();
			}
		});
		registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		registerButton.setFont(new Font("Lucida Sans", Font.BOLD, 11));
		registerButton.setBounds(239, 190, 101, 23);
		frame.getContentPane().add(registerButton);
		this.hideAndShowPassword = new JLabel("New label");
		
		this.image = new ImageIcon("images/eyes.png");
		this.hideAndShowPassword = new JLabel("");
		hideAndShowPassword.setIcon(image);
		hideAndShowPassword.setToolTipText("Show Password");
		hideAndShowPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Clicked(e, hideAndShowPassword);
			}
		});
		hideAndShowPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hideAndShowPassword.setBounds(362, 128, 25, 16);
		frame.getContentPane().add(hideAndShowPassword);
		
		copyright.setBounds(159, 219, 87, 17);
		frame.getContentPane().add(copyright);
		copyright.setToolTipText("Author - Rusel Alexander /71180251 - Y. T. Rinto Pradhana / 71180259 - Ananda Apriliansah / 71180263 - Yoga Kurnia Widi Pratama / 71180277");
		copyright.setFont(new Font("Dialog", Font.PLAIN, 10));
	}
	public void toRegister() { 
		Register jfrm1= new Register();
		jfrm1.frame.setVisible(true);
		this.frame.setVisible(false);
		this.frame.dispose();
	}
	
	public void cekAkun(JTextField txtUsername, JPasswordField passwordField) {
		@SuppressWarnings("deprecation")
		String email = txtUsername.getText(),pass = passwordField.getText();
		ConnectionDataBase db = new ConnectionDataBase();
		db.connectDB();
		try(db.con) {
			String query = "select * from user_akun where email = ? and password = ?";
			ResultSet rs;
			try (PreparedStatement pr = db.con.prepareStatement(query)){
				pr.setString(1, email);
				pr.setString(2, pass);
				rs = pr.executeQuery();
				if(rs.next()) {
					Main.User = email;
					this.ToHome();
				}
				else {
					JOptionPane.showMessageDialog(frame, "Username atau Password yang anda masukan salah!!!","Login"
							,JOptionPane.WARNING_MESSAGE, new ImageIcon("images/Lock.png"));
				}
				rs.close();
			} catch (SQLException e) {
				System.out.println("error: "+e.getMessage());
			}
			
			
		} catch (SQLException e) {
			System.out.println("error: "+e.getMessage());
		}
		db.closeDB();
	}
	public void Clicked(MouseEvent e, JLabel j) {
		if(!this.check) {
			this.passwordField.setEchoChar((char)0);
			this.image =  new ImageIcon("images/eyeclosed.png");
			j.setIcon(this.image);
			j.setToolTipText("Hide Password");
			this.check = true;
		}
		else {
			this.passwordField.setEchoChar('•');
			this.image = new ImageIcon("images/eyes.png");
			j.setIcon(this.image);
			j.setToolTipText("Show Text");
			this.check = false;
		}
	}
	public void ToHome() { 
		Home jfrm1= new Home(); 
		jfrm1.frame.setVisible(true);
		this.frame.setVisible(false);
		this.frame.dispose();
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