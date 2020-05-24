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
import javax.swing.*;
import javax.swing.border.AbstractBorder;
public class register extends AbstractBorder{
	public JFrame frame;
	private JTextField fullname;
	private JTextField email;
	private JTextField birth;
	private JTextField address;
	public static boolean cek = false;
	public String strRegEx = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+{}:;'/?>.<,¡™£¢§¶•ªº–≠“‘«æ…ÚÆ¯˘¿Â˜ı◊])(?=\\S+$).{8,}$";
	public String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	public String dateRegEx = "([1-9]|[12][0-9]|[3][01]) (Januari|Februari|Maret|April|Mei|Juni|Juli|Agustus|September|Oktober|November|Desember|januari|februari|maret|april|mei|juni|juli|agustus|september|oktober|november|desember) \\d{4}";
	private boolean check = false;
	private JLabel hideAndShowPassword;
	private JPasswordField passwordField; 
	private ImageIcon image;
	private Color color;
    private int thickness = 4;
    private int radii = 8;
    private int pointerSize = 7;
    private Insets insets = null;
    private BasicStroke stroke = null;
    private int strokePad;
    private int pointerPad = 4;
    RenderingHints hints;
    /**
	 * @wbp.parser.constructor
	 */
	public register(UserWallet u) {
		this.initialize(u);
	}
	register(Color color) {
		this(color, 4, 8, 7);
	}
	public register(
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
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 625, 452);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//title
		JLabel lblNewLabel = new JLabel("Register");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(280, 27, 94, 25);
		frame.getContentPane().add(lblNewLabel);
		
		//full name
		JLabel lblNewLabel_1 = new JLabel("Full Name");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(28, 87, 74, 14);
		frame.getContentPane().add(lblNewLabel_1);
		fullname = new JTextField();
		fullname.setBorder(new register(Color.black.darker(),2,6,0));
		fullname.setText("");
		fullname.setBounds(132, 81, 454, 28);
		frame.getContentPane().add(fullname);
		fullname.setColumns(10);
		
		//email
		JLabel lblNewLabel_2 = new JLabel("E-mail");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(28, 140, 50, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		email = new JTextField();
		email.setBorder(new register(Color.black.darker(),2,6,0));
		email.setText("");
		email.setColumns(10);
		email.setBounds(132, 134, 454, 28);
		frame.getContentPane().add(email);
		
		//password
		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(28, 195, 69, 14);
		frame.getContentPane().add(lblNewLabel_3);
		this.passwordField = new JPasswordField();
		passwordField.setBorder(new register(Color.black.darker(),2,6,0));
		passwordField.setEchoChar('•');
		passwordField.setBounds(132, 189, 454, 28);
		frame.getContentPane().add(passwordField);
		String cekPass = passwordField.getText();
		//password warning
		JLabel lblNewLabel_6_1 = new JLabel("password harus: minimal 8 karakter, 1 huruf kecil, 1 huruf besar, 1 angka, dan 1 simbol(!@#$%^&*)");
		lblNewLabel_6_1.setForeground(Color.RED);
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNewLabel_6_1.setBounds(137, 220, 446, 14);
		frame.getContentPane().add(lblNewLabel_6_1);
		//address
		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(28, 249, 61, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		address = new JTextField();
		address.setBorder(new register(Color.black.darker(),2,6,0));
		address.setBounds(132, 243, 454, 28);
		frame.getContentPane().add(address);
		address.setColumns(10);
		
		//birthday
		JLabel lblNewLabel_5 = new JLabel("Tanggal Lahir");
		lblNewLabel_5.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(28, 298, 94, 25);
		frame.getContentPane().add(lblNewLabel_5);
		
		birth = new JTextField();
		birth.setBorder(new register(Color.black.darker(),2,6,0));
		birth.setColumns(10);
		birth.setBounds(133, 296, 454, 28);
		frame.getContentPane().add(birth);
		
		JLabel lblNewLabel_6 = new JLabel("ex. 12 Agustus 1998");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_6.setBounds(139, 328, 100, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		//register button
		JButton btnNewButton = new JButton("Register");
		btnNewButton.setBackground(new Color(0, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					goToUserWaller(fullname, email, passwordField, address, u, arg0,cekPass,lblNewLabel_6_1);
			}
		});
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(332, 367, 100, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backToLogin(e, u);
			}
		});
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(220, 367, 104, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		hideAndShowPassword = new JLabel("");
		this.image = new ImageIcon("eyes.png");
		this.hideAndShowPassword = new JLabel("");
		hideAndShowPassword.setIcon(image);
		hideAndShowPassword.setToolTipText("Show Password");
		hideAndShowPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("mouse di click");
				Clicked(e, hideAndShowPassword);
				System.out.println("berhasil berubah icon");
			}
		});
		hideAndShowPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hideAndShowPassword.setBounds(593, 196, 26, 16);
		frame.getContentPane().add(hideAndShowPassword);
		
		
	}
	public void Clicked(MouseEvent e, JLabel j) {
		if(!this.check) {
			this.passwordField.setEchoChar((char)0);
			this.image =  new ImageIcon("eyeclosed.png");
			j.setIcon(this.image);
			j.setToolTipText("Hide Password");
			this.check = true;
		}
		else {
			this.passwordField.setEchoChar('•');
			System.out.println("show pass");
			this.image = new ImageIcon("eyes.png");
			j.setIcon(this.image);
			j.setToolTipText("Show Text");
			System.out.println("show pass success");
			this.check = false;
		}
	}
	public void backToLogin(ActionEvent e,UserWallet u) {
		Wallet w = new Wallet(u);
		w.frame.setVisible(true);
		this.frame.setVisible(false);
		this.frame.setDefaultCloseOperation(this.frame.EXIT_ON_CLOSE);
		this.frame.dispose();
	}
	public void goToUserWaller(JTextField fullname,JTextField email,JPasswordField passwordField,JTextField address,UserWallet u, ActionEvent arg0,String cekPass,JLabel lblNewLabel_6_1) {
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
				
				
				if(!mat) {
					JOptionPane.showMessageDialog(frame, "Password tidak sesuai kriteria","Password Error Message",JOptionPane.WARNING_MESSAGE);
				}
				else if(!emMat) {
					JOptionPane.showMessageDialog(frame, "Format Email Salah","Email Error Message",JOptionPane.WARNING_MESSAGE);
				}
				else if(!dateMat) {
					JOptionPane.showMessageDialog(frame, "Format tanggal lahir Salah","Email Error Message",JOptionPane.WARNING_MESSAGE);
				}
				else {
					cek = u.Register(nama, tanggalLahir, password, Email, alamat,cek);
					if(cek == true) {
						JOptionPane.showMessageDialog(frame, "Email sudah pernah digunakan","Register",JOptionPane.WARNING_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(frame, "Register berhasil, Selamat datang " + nama,
								  "Register",JOptionPane.PLAIN_MESSAGE);
						backToLogin(arg0, u);
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(frame, "Data tidak boleh ada yang kosong!!!",
						  					  "Register",JOptionPane.WARNING_MESSAGE);
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
