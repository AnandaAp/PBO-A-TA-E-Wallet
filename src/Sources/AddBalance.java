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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
public class AddBalance extends AbstractBorder implements BackHome{

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("exports")
	public JFrame frame;
	private JTextField textField;
	private JButton confirmButton;
	private JButton cancelButton;
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
    private final String numPat = "\\d+";
    private JLabel lblNewLabel_3;
    @SuppressWarnings("exports")
	public ImageIcon icon;
    
    private String query = "select saldo from user_saldo where email = ?";
    private String sql = "UPDATE user_saldo set saldo = ? where email = ?";
    private String storedProcedure = "call setelah_transaksi(?,?,?,?)";
    private JComboBox<String> comboBox;
    /**
	 * @wbp.parser.constructor
	 */
	public AddBalance(Currency c) {
		this.initialize(c);
	}
	public AddBalance(@SuppressWarnings("exports") Color color) {
		this(color, 4, 8, 7);
	}
	public AddBalance(
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Currency c) {
		System.out.println("user: "+Main.User);
		frame = new JFrame();
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("images/wallet.png"));
		frame.setTitle("E-Wallet");
		frame.setBounds(100, 100, 450, 264);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(234, 240, 248));
		//title
		JLabel lblNewLabel = new JLabel("Add Balance");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(161, 11, 131, 25);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(90, 103, 261, 32);
		textField.setBorder(new Register(Color.black.darker(),2,6,0));
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"IDR", "USD"}));
		comboBox.setMaximumRowCount(2);
		comboBox.setBounds(363, 101, 65, 36);
		frame.getContentPane().add(comboBox);
		
		//confirm button
		confirmButton = new JButton("Confirm");
		confirmButton.setForeground(Color.WHITE);
		confirmButton.setBackground(new Color(64,162,41));
		confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBalanceFunction(textField,comboBox);					
			}

		});
		
		confirmButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		confirmButton.setBounds(175, 146, 89, 32);
		frame.getContentPane().add(confirmButton);
		
		//back button
		cancelButton = new JButton("Cancel");
		cancelButton.setEnabled(false);
		cancelButton.setForeground(Color.BLACK);
		cancelButton.setBackground(new Color(255, 255, 255));
		cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toHome();
			}
		});
		
		cancelButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		cancelButton.setBounds(126, 139, 89, 32);
//		frame.getContentPane().add(cancelButton);
		
		lblNewLabel_3 = new JLabel("Silahkan masukan nominal yang akan anda catat");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setBounds(104, 84, 236, 16);
		frame.getContentPane().add(lblNewLabel_3);
		//Copyrigth
		JLabel copyRight = new JLabel("\u00A9Copyright 2020");
		copyRight.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		copyRight.setBounds(181, 209, 89, 14);
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
	
	//back home
	
	@Override
	public void toHome() {
		Home pro1 = new Home();
		pro1.frame.setVisible(true);
		this.frame.setVisible(false);
		this.frame.dispose();
	}
	
//History method idr
	public void createHistoryIDR(double idr1,double idr,ConnectionDataBase db,@SuppressWarnings("exports") ResultSet rs) {

		try(PreparedStatement pr = db.con.prepareStatement(this.storedProcedure)) {
			pr.setString(1,Main.User);
			pr.setString(2, "Pemasukan sebesar Rp."+Math.round(idr1));
			pr.setDouble(3, idr);
			java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			pr.setDate(4, date);
			rs = pr.executeQuery();
			pr.close();
			rs.close();
		} 
		catch (SQLException e) {
			System.out.println("error: "+e.getMessage());
		}
	}
	
//History method usd
	public void createHistoryUSD(double idr,double usd1,@SuppressWarnings("exports") ResultSet rs,ConnectionDataBase db) {

		try(PreparedStatement pr = db.con.prepareStatement(this.storedProcedure)) {
			pr.setString(1,Main.User);
			pr.setString(2, "Pemasukan sebesar $"+Math.round(usd1));
			pr.setDouble(3, idr);
			java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			pr.setDate(4, date);
			rs = pr.executeQuery();
			pr.close();
			rs.close();
		} 
		catch (SQLException e) {
			System.out.println("error: "+e.getMessage());
		}
	}
	
	
//Addbalance
	
	public void AddBalanceFunction(@SuppressWarnings("exports") JTextField textField,JComboBox<String> comboBox) {
		String value = textField.getText();
		Pattern pat = Pattern.compile(this.numPat);
		Matcher mat = pat.matcher(value);
		boolean ok = mat.matches();
	
		
		ConnectionDataBase db = new ConnectionDataBase();
		ResultSet rs;
		if(ok) {
			db.connectDB();
			//memindahkan nilai saldo dari database ke var balance
			try(PreparedStatement pr = db.con.prepareStatement(this.query)){
				pr.setString(1, Main.User);
				rs = pr.executeQuery();
				if(rs.next()) {
					this.balance = rs.getDouble("saldo");
				}
				rs = null;
			}
			catch (SQLException e) {
				System.out.println("error: "+e.getMessage());
			}
			System.out.println("balance : "+this.balance);
			IDR idr2 = new IDR(this.balance);
//IDR check
			if(comboBox.getSelectedItem() == "IDR") {
			  
				double idr1 =Double.parseDouble(value);
				if(idr1 > 0) {
					int keluar = JOptionPane.showConfirmDialog(frame, "Apakah Anda yakin?","Log Out",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,new ImageIcon("images/save.png"));
					if(keluar == 0) {
						idr2.addValue(idr1);
						try(PreparedStatement pr = db.con.prepareStatement(this.sql)) {
							pr.setDouble(1, idr2.getValue());
							pr.setString(2, Main.User);
							pr.executeUpdate();
							pr.close();
						} catch (SQLException e) {
							System.out.println("error: "+e.getMessage());
							e.printStackTrace();
						}
						rs = null;
						this.createHistoryIDR(idr1, idr2.getValue(), db, rs);
						db.closeDB();
						JOptionPane.showMessageDialog(frame, "Transaksi Berhasil", "Input Success", JOptionPane.WARNING_MESSAGE,new ImageIcon("images/save.png"));
					}
				}
				else {
					JOptionPane.showMessageDialog(frame, "Input yang anda masukan salah!","Input Error",JOptionPane.WARNING_MESSAGE);
				}
			}	
//USD check
			else if(comboBox.getSelectedItem() == "USD") {
				System.out.println("masuk usd");
				  double usd1 = Double.parseDouble(value);
				  if(usd1 > 0) {
					  int keluar = JOptionPane.showConfirmDialog(frame, "Apakah Anda yakin?","Log Out",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,new ImageIcon("images/save.png"));
					  if(keluar == 0) {
						  USD usd2 = new USD();
						  usd2.addValue(usd1);
						  usd2.toIDR();
						  idr2.addValue(usd2.getValue());
						  db.connectDB();
						  try(PreparedStatement pr = db.con.prepareStatement(this.sql)){
							  pr.setDouble(1, idr2.getValue());
							  pr.setString(2, Main.User);
							  pr.executeUpdate();
							  pr.close();
						  }
						  catch (SQLException e) {
								System.out.println("error: "+e.getMessage());
								e.printStackTrace();
						  }
						  rs = null;
						  this.createHistoryUSD(idr2.getValue(), usd1, rs, db);
						  db.closeDB();
						  JOptionPane.showMessageDialog(frame, "Transaksi Berhasil", "Input Success", JOptionPane.WARNING_MESSAGE,new ImageIcon("images/save.png"));
					  }
				  }
				  
				}//end usd check	
			else{
				JOptionPane.showMessageDialog(frame, "Maaf input yang anda masukan salah!","Input Error",JOptionPane.WARNING_MESSAGE);
			}		
		}
			
		else {
			if(!ok && !value.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Input yang anda masukan salah","Input Error",JOptionPane.WARNING_MESSAGE);
			}
			else if(value.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Maaf anda harus memasukan nominal!","Input Error",JOptionPane.WARNING_MESSAGE);
			}
		}
		
	}//end method
	
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
	
	@SuppressWarnings("exports")
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
