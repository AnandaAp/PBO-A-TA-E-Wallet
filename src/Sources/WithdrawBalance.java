package Sources;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
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
	private JTextField idrTextField;
	private double balance;
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
    private String query = "select saldo from user_saldo where user_email = ?";
    private String sql = "UPDATE user_saldo set saldo = ? where user_email = ?";
    private String storedProcedure = "call setelah_transaksi(?,?,?,?)";
    private ResultSet rs;
    
    /**
	 * @wbp.parser.constructor
	 */
	public WithdrawBalance(Currency u) {
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

	private void initialize(Currency u) {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("images/wallet.png"));
		frame.setTitle("E-Wallet");
		frame.setBounds(100, 100, 374, 239);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(234, 240, 248));
		
		//title
		JLabel withdrawLabel = new JLabel("Withdraw Balance");
		withdrawLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		withdrawLabel.setBounds(98, 21, 189, 25);
		frame.getContentPane().add(withdrawLabel);
		
		//withdraw
		JLabel idrLabel = new JLabel("IDR");
		idrLabel.setBounds(145, 75, 27, 14);
		frame.getContentPane().add(idrLabel);
		
		idrTextField = new JTextField();
		idrTextField.setColumns(10);
		idrTextField.setBounds(64, 97, 183, 35);
		idrTextField.setBorder(new Register(Color.black.darker(),2,6,0));
		frame.getContentPane().add(idrTextField);
		
		//confirm button
		JButton confirmButton = new JButton("Confirm");
		confirmButton.setForeground(Color.WHITE);
		confirmButton.setBackground(new Color(64,162,41));
		confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WithdrawFunction(idrTextField);
			}
		});
		
		confirmButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		confirmButton.setBounds(249, 95, 85, 40);
		frame.getContentPane().add(confirmButton);
		JLabel copyRight = new JLabel("\u00A9Copyright 2020");
		copyRight.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		copyRight.setBounds(141, 174, 89, 14);
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
				toHome();
			}
		});
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		back.setFont(new Font("Tahoma", Font.BOLD, 11));
		back.setBounds(6, 5, 37, 25);
		frame.getContentPane().add(back);
	}
	@Override
	public void toHome() {
		Home pro1 = new Home();
		pro1.frame.setVisible(true);
		this.frame.setVisible(true);
		this.frame.dispose();
	}
	
//create Withdraw History
	public void createHistoryWithdraw(ConnectionDataBase db,double idr,double idr1) {
		try(PreparedStatement pr = db.con.prepareStatement(this.storedProcedure)) {
			pr.setString(1,Main.User);
			pr.setString(2, "Pemasukan sebesar Rp."+Math.round(idr));
			pr.setDouble(3, idr1);
			java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			pr.setDate(4, date);
			rs = pr.executeQuery();
			pr.close();
			rs.close();
		} 
		catch (SQLException e) {
			System.out.println("error: "+e.getMessage());
			e.printStackTrace();
		}
	}
//Withdraw Function
	public void WithdrawFunction(JTextField textField) {
		String value = textField.getText();
		Pattern pat = Pattern.compile(numPat);
		Matcher mat = pat.matcher(value);
		boolean ok = mat.matches();
		
		if(ok && !value.isEmpty()) {
//value type double
			ConnectionDataBase db = new ConnectionDataBase();
			db.connectDB();
			//memindahkan nilai saldo dari database ke var balance
			try(PreparedStatement pr = db.con.prepareStatement(this.query)){
				pr.setString(1, Main.User);
				this.rs = pr.executeQuery();
				if(rs.next()) {
					this.balance = this.rs.getDouble("saldo");
				}
				rs = null;
				pr.close();
			}
			catch (SQLException e) {
				System.out.println("error: "+e.getMessage());
				e.printStackTrace();
			}
			double value1 = Double.parseDouble(value);

			IDR idr = new IDR(this.balance);
	//IDR check
			if(value1 <= idr.getValue() && value1 >0) {
				idr.withdrawValue(value1);
				try(PreparedStatement pr = db.con.prepareStatement(sql)){
					pr.setDouble(1, idr.getValue());
					pr.setString(2, Main.User);
					pr.executeUpdate();
					pr.close();
				}
				catch (SQLException e) {
					System.out.println("error: "+e.getMessage());
					e.printStackTrace();
				}
				this.createHistoryWithdraw(db, value1, idr.getValue());
				db.closeDB();
				JOptionPane.showMessageDialog(frame, "Transaksi Berhasil", "Input Success", JOptionPane.WARNING_MESSAGE,new ImageIcon("images/withdraw.png"));
			}
			else {
				JOptionPane.showMessageDialog(frame, "Input tidak boleh melebihi jumlah saldo","Input Error",JOptionPane.WARNING_MESSAGE);
			}			
		}
		else {
			if(!ok && !value.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Input yang anda masukkan salah","Input Error",JOptionPane.WARNING_MESSAGE);
			}
			else if(value.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Maaf anda harus memasukan nominal!","Input Error",JOptionPane.WARNING_MESSAGE);
			}
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