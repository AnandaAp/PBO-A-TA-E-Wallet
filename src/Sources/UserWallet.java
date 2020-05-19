package Sources;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
public class UserWallet extends EWallet{
	private String nama;
	private String tanggalLahir;
	private String password;
	private String email;
	private String alamat;
	private double saldo;
//	private ArrayList user = new ArrayList<UserWallet>();
	
	public UserWallet() {
		String tNama,tTgl,tPass,tEmail,tAlamat;
		Scanner nama = new Scanner(System.in);
		System.out.println("nama: ");
		tNama = nama.nextLine();
		Scanner tgl = new Scanner(System.in);
		System.out.println("tanggal lahir: ");
		tTgl = tgl.nextLine();
//		TextField pass = new TextField(16);
//		Scanner pass = new Scanner(System.in);
//		System.out.println("password: ");
		String message = "enter password";
		if( System.console() == null ) 
		{ // inside IDE like Eclipse or NetBeans
		  final JPasswordField pf = new JPasswordField(); 
		  tPass = JOptionPane.showConfirmDialog( null, pf, message,
		    JOptionPane.OK_CANCEL_OPTION,
		    JOptionPane.QUESTION_MESSAGE ) == JOptionPane.OK_OPTION ? 
		      new String( pf.getPassword() ) : "";
		}
		else 
		  tPass = new String( System.console().readPassword( "%s> ", message ) );
//		tPass = pass.nextLine();
		Scanner email = new Scanner(System.in);
		System.out.println("email: ");
		tEmail = email.nextLine();
		Scanner alamat = new Scanner(System.in);
		System.out.println("alamat: ");
		tAlamat = alamat.nextLine();
		this.Register(tNama,tTgl,tPass,tEmail,tAlamat);
	}
	@Override
	public void Register(String nama,String tanggalLahir, String password,String email, String alamat) {
		this.setNama(nama);
		this.setTanggalLahir(tanggalLahir);
		this.setPassword(password);
		this.setEmail(email);
		this.setAlamat(alamat);
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getTanggalLahir() {
		return tanggalLahir;
	}

	public void setTanggalLahir(String tanggalLahir) {
		this.tanggalLahir = tanggalLahir;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	@Override
	public String toString() {
		System.out.println("-------INFO AKUN USER-------");
		return "Nama : " + this.getNama() + "\n" +
				"Tanggal lahir : " + this.getTanggalLahir() + "\n" +
				"Password : " + this.getPassword() + "\n" +
				"Email : " + this.getEmail() + "\n" +
				"Alamat : " + this.getAlamat() + "\n" + 
				"Saldo : " + Math.round(this.getSaldo());
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo += saldo;
	}
}
