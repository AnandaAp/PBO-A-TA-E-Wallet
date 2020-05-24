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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class Wallet  extends AbstractBorder{
	private JLabel hideAndShowPassword;
	public JFrame frame;
	private JTextField txtUsername;
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
    /**
	 * @wbp.parser.constructor
	 */
	public Wallet(UserWallet u) {
		this.initialize(u);
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

	private void initialize(UserWallet u) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 394, 242);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//title
		JLabel lblNewLabel_1 = new JLabel("E-Wallet");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(154, 25, 89, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		//username
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblNewLabel.setBounds(37, 77, 71, 16);
		frame.getContentPane().add(lblNewLabel);
		
		txtUsername = new JTextField();
		txtUsername.setBorder(new Register(Color.black.darker(),2,6,0));
		txtUsername.setBounds(117, 72, 236, 28);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		//password
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblPassword.setBounds(37, 126, 71, 16);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(new Register(Color.black.darker(),2,6,0));
		passwordField.setBounds(117, 121, 236, 28);
		frame.getContentPane().add(passwordField);
		
		//login button
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				readArray("User.txt", txtUsername, passwordField, arg0, u);
			}
		});
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(152, 161, 90, 23);
		frame.getContentPane().add(btnNewButton);
		
		//register button
		JLabel lblNewLabel_2 = new JLabel("Don't have any account yet?");
		lblNewLabel_2.setBounds(58, 190, 178, 20);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel regis = new JLabel("Register Now!!!");
		regis.setForeground(new Color(0, 206, 209));
		regis.setToolTipText("Click this to go to register page");
		regis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MyButton1ActionPerformed(e, u);
			}
		});
		regis.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		regis.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		regis.setBounds(236, 189, 101, 23);
		frame.getContentPane().add(regis);
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
	}
	public void MyButton1ActionPerformed(MouseEvent e,UserWallet u) { 
		Register jfrm1= new Register(u);
		jfrm1.frame.setVisible(true);
		this.frame.setVisible(false);
		this.frame.setDefaultCloseOperation(this.frame.EXIT_ON_CLOSE);
		this.frame.dispose();
	}
	public void readArray (String File, JTextField txtUsername, JPasswordField passwordField, ActionEvent x, UserWallet u) {
		String email = txtUsername.getText(),pass = passwordField.getText();
		int line = 0;
		BufferedReader fileInput;
		File file = new File(File);
		if(file.exists()) {
			try {
				fileInput = new BufferedReader(new FileReader(new File(File)));
				String line2;
				try {
					line2 = fileInput.readLine();
					while(line2 != null) {
						line+=1;
						line2 = fileInput.readLine();
					}
					fileInput.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String[] words = new String[line];
			int i =0;
			BufferedReader fileHasil;
			try {
				fileHasil = new BufferedReader(new FileReader(new File("user.txt")));
				String line2;
				try {
					line2 = fileHasil.readLine();
					while(line2 != null) {
						words[i] = line2;	
						i++;
						line2 = fileHasil.readLine();
					}
					fileHasil.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int j=1; j < words.length; j+=4) {
				if(words[j].equals(email) && words[j+1].equals(pass)) {
					Main.User = email;
					this.ToHome(x, u);
					break;
				}
				else if(!words[j].equals(email) && !words[j+1].equals(pass) && j+2==words.length) {
					JOptionPane.showMessageDialog(frame, "Username atau Password yang anda masukan salah!!!", "Login", JOptionPane.WARNING_MESSAGE);
					break;
				}
				else if(!words[j].equals(email) && words[j+1].equals(pass) && j+2==words.length) {
					JOptionPane.showMessageDialog(frame, "Username atau Password yang anda masukan salah!!!", "Login", JOptionPane.WARNING_MESSAGE);
					break;
				}
				else if(words[j].equals(email) && !words[j+1].equals(pass) && j+2==words.length) {
					JOptionPane.showMessageDialog(frame, "Username atau Password yang anda masukan salah!!!", "Login", JOptionPane.WARNING_MESSAGE);
					break;
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(frame, "Username atau Password yang anda masukan salah!!!","Login",JOptionPane.WARNING_MESSAGE);
		}
		
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
	public void ToHome(ActionEvent evt,UserWallet u) { 
		Home jfrm1= new Home(u); 
		jfrm1.frame.setVisible(true);
		this.frame.setVisible(false);
		this.frame.setDefaultCloseOperation(this.frame.EXIT_ON_CLOSE);
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
