package Sources;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.regex.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.geom.*;
import java.sql.SQLException;

import javax.swing.border.AbstractBorder;
import javax.swing.SwingConstants;
public class Register extends AbstractBorder{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("exports")
	public JFrame frame;
	private JTextField fullname,email,birth,address;
	public static boolean cek = false;
	public String strRegEx = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])"
			+ "(?=.*[!@#$%^&*()_+{}:;'/?>.<,¡™£¢§¶•ªº–≠“‘«æ…ÚÆ¯˘¿Â˜ı◊])(?=\\S+$).{8,16}$";
	public String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)"
			+ "*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	public String dateRegEx = "([1-9]|[12][0-9]|[3][01]) "
			+ "(Januari|Februari|Maret|April|Mei|Juni|Juli|Agustus|September|Oktober|November|Desember|"
			+ "januari|februari|maret|april|mei|juni|juli|agustus|september|oktober|november|desember) \\d{4}";
//	public String dateRegEx = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$";
	private boolean check = false;
	private JLabel hideAndShowPassword;
	private JPasswordField passwordField; 
	private ImageIcon image;
	private Color color;
    private int thickness = 4,radii = 8,pointerSize = 7;
    private Insets insets = null;
    private BasicStroke stroke = null;
    private int strokePad,pointerPad = 4;
    RenderingHints hints;
    /**
	 * @wbp.parser.constructor
	 */
	public Register() {
		this.initialize();
	}
	public Register(@SuppressWarnings("exports") Color color) {
		this(color, 4, 8, 7);
	}
	public Register(
		    @SuppressWarnings("exports") Color color, int thickness, int radii, int pointerSize) {
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
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("images/wallet.png"));
		frame.setTitle("E-Wallet");
		frame.setBackground(new Color(234, 240, 248));
		frame.getContentPane().setBackground(new Color(234, 240, 248));
		frame.setBounds(100, 100, 650, 452);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//title
		JLabel lblNewLabel = new JLabel("Register");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(280, 27, 94, 25);
		frame.getContentPane().add(lblNewLabel);
		
		//full name
		JLabel namaLengkapLabel = new JLabel("Nama Lengkap");
		namaLengkapLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		namaLengkapLabel.setBounds(28, 83, 100, 24);
		frame.getContentPane().add(namaLengkapLabel);
		fullname = new JTextField();
		fullname.setBorder(new Register(Color.black.darker(),2,6,0));
		fullname.setText("");
		fullname.setBounds(132, 81, 454, 32);
		frame.getContentPane().add(fullname);
		fullname.setColumns(10);
		
		//email
		JLabel emailLabel = new JLabel("E-mail");
		emailLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		emailLabel.setBounds(28, 142, 50, 14);
		frame.getContentPane().add(emailLabel);
		
		email = new JTextField();
		email.setBorder(new Register(Color.black.darker(),2,6,0));
		email.setText("");
		email.setColumns(10);
		email.setBounds(132, 134, 454, 32);
		frame.getContentPane().add(email);
		
		//password
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		passwordLabel.setBounds(28, 197, 69, 14);
		frame.getContentPane().add(passwordLabel);
		this.passwordField = new JPasswordField();
		passwordField.setBorder(new Register(Color.black.darker(),2,6,0));
		passwordField.setEchoChar('�');
		passwordField.setBounds(132, 189, 454, 32);
		frame.getContentPane().add(passwordField);
		@SuppressWarnings("deprecation")
		String cekPass = passwordField.getText();
		//password warning
		JLabel lblNewLabel_6_1 = new JLabel("password harus: 8-16 karakter, 1 huruf kecil, 1 huruf besar, 1 angka, dan 1 simbol");
		lblNewLabel_6_1.setForeground(Color.RED);
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNewLabel_6_1.setBounds(137, 220, 401, 14);
		frame.getContentPane().add(lblNewLabel_6_1);
		//address
		JLabel addressLabel = new JLabel("Alamat");
		addressLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		addressLabel.setBounds(28, 251, 61, 14);
		frame.getContentPane().add(addressLabel);
		
		address = new JTextField();
		address.setBorder(new Register(Color.black.darker(),2,6,0));
		address.setBounds(132, 243, 454, 32);
		frame.getContentPane().add(address);
		address.setColumns(10);
		
		//birthday
		JLabel birthLabel = new JLabel("Tanggal Lahir");
		birthLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		birthLabel.setBounds(28, 300, 94, 25);
		frame.getContentPane().add(birthLabel);
		
		birth = new JTextField();
		birth.setBorder(new Register(Color.black.darker(),2,6,0));
		birth.setColumns(10);
		birth.setBounds(133, 296, 454, 32);
		frame.getContentPane().add(birth);
		
		JLabel lblNewLabel_6 = new JLabel("ex. 12 April 1998");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_6.setBounds(139, 328, 100, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		//register button
		JButton registerButton = new JButton("Register");
		registerButton.setForeground(Color.WHITE);
		registerButton.setBackground(new Color(64,162,41));
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					try {
						goToUserWaller(fullname, email, passwordField, address,cekPass,lblNewLabel_6_1);
					} catch (SQLException e) {
						System.out.println("error: "+e.getMessage());
						e.printStackTrace();
					}
			}
		});
		registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		registerButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		registerButton.setBounds(332, 358, 87, 32);
		frame.getContentPane().add(registerButton);
		
		JLabel cancelLabel = new JLabel("Cancel");
		cancelLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				backToLogin();
			}
		});
		cancelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cancelLabel.setBackground(Color.WHITE);
		cancelLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		cancelLabel.setBounds(274, 358, 50, 32);
		frame.getContentPane().add(cancelLabel);
		
		hideAndShowPassword = new JLabel("");
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
		hideAndShowPassword.setBounds(593, 196, 26, 16);
		frame.getContentPane().add(hideAndShowPassword);
		
		JLabel copyRight = new JLabel("\u00A9Copyright 2020");
		copyRight.setToolTipText("Author - Rusel Alexander /71180251 - Y. T. Rinto Pradhana / 71180259 - Ananda Apriliansah / 71180263 - Yoga Kurnia Widi Pratama / 71180277");
		copyRight.setFont(new Font("Dialog", Font.PLAIN, 10));
		copyRight.setBounds(287, 396, 87, 17);
		frame.getContentPane().add(copyRight);
		
		
	}
	public void Clicked(@SuppressWarnings("exports") MouseEvent e, @SuppressWarnings("exports") JLabel j) {
		if(!this.check) {
			this.passwordField.setEchoChar((char)0);
			this.image =  new ImageIcon("images/eyeclosed.png");
			j.setIcon(this.image);
			j.setToolTipText("Hide Password");
			this.check = true;
		}
		else {
			this.passwordField.setEchoChar('�');
			this.image = new ImageIcon("images/eyes.png");
			j.setIcon(this.image);
			j.setToolTipText("Show Text");
			this.check = false;
		}
	}
	public void backToLogin() {
		Wallet w = new Wallet();
		w.frame.setVisible(true);
		this.frame.setVisible(false);
		this.frame.dispose();
	}
	public void goToUserWaller(@SuppressWarnings("exports") JTextField fullname,@SuppressWarnings("exports") JTextField email,@SuppressWarnings("exports") JPasswordField passwordField,@SuppressWarnings("exports") JTextField address,String cekPass,@SuppressWarnings("exports") JLabel lblNewLabel_6_1) throws SQLException {
		UserWallet u = new UserWallet();
		@SuppressWarnings("deprecation")
		String nama = fullname.getText(),Email = email.getText(), 
				   password = passwordField.getText(),alamat = address.getText(),
				   tanggalLahir = birth.getText();
			if(!nama.isEmpty() && !Email.isEmpty() && !alamat.isEmpty()
				&& !password.isEmpty() && !tanggalLahir.isEmpty()
			) {
				Pattern patt = Pattern.compile(strRegEx),emPatt = Pattern.compile(emailPattern),
						date = Pattern.compile(dateRegEx);
				Matcher match=patt.matcher(password),matchEm = emPatt.matcher(Email),
						matchDate = date.matcher(tanggalLahir);
				boolean mat = match.matches(),emMat = matchEm.matches(), dateMat = matchDate.matches();
				
				
				if(!emMat) {
					JOptionPane.showMessageDialog(frame, "Format Email Salah","Email Error Message",JOptionPane.WARNING_MESSAGE,new ImageIcon("images/Mail.png"));
				}
				if(!mat) {
					JOptionPane.showMessageDialog(frame, "Password tidak sesuai kriteria","Password Error Message",JOptionPane.WARNING_MESSAGE,new ImageIcon("images/Lock.png"));
				}
				if(!dateMat) {
					JOptionPane.showMessageDialog(frame, "Format tanggal lahir Salah","Email Error Message",JOptionPane.WARNING_MESSAGE,new ImageIcon("images/Calender.png"));
				}
				else if(emMat && mat && dateMat){
					cek = u.Register(nama, tanggalLahir, password, Email, alamat,cek);
					if(cek == true) {
						JOptionPane.showMessageDialog(frame, "Email sudah pernah digunakan","Register",JOptionPane.WARNING_MESSAGE,new ImageIcon("images/Mail.png"));
					}
					else {
						JOptionPane.showMessageDialog(frame, "Register berhasil, Selamat datang " + nama,
								  "Register",JOptionPane.PLAIN_MESSAGE,new ImageIcon("images/Register.png"));
						backToLogin();
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(frame, "Data tidak boleh ada yang kosong!!!",
						  					  "Register",JOptionPane.WARNING_MESSAGE,new ImageIcon("images/attention.png"));
			}	
	}
	@SuppressWarnings("exports")
	@Override
	public Insets getBorderInsets(Component c) {
	    return insets;
	}
	
	@SuppressWarnings("exports")
	@Override
	public Insets getBorderInsets(Component c, Insets insets) {
	    return getBorderInsets(c);
	}
	
	@Override
	public void paintBorder(
	    @SuppressWarnings("exports") Component c,
	    @SuppressWarnings("exports") Graphics g,
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