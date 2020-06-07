package Sources;
import javax.swing.JFrame;
import java.sql.*;


public class UserWallet extends EWallet{
	@SuppressWarnings("exports")
	public JFrame frame;
	private String nama;
	private String tanggalLahir;
	private String password;
	private String email;
	private String alamat;
	private double saldo;
	
	public UserWallet() {
	}
	public UserWallet(String nama, String tgl, String pass, String alamat, String email) {
		this.setNama(nama);
		this.setAlamat(alamat);
		this.setEmail(email);
		this.setPassword(pass);
		this.setTanggalLahir(tgl);
	}

	@Override
	public boolean Register(String nama,String tanggalLahir, String password,String email, String alamat, boolean cek) throws SQLException {
		ConnectionDataBase db = new ConnectionDataBase();
		db.connectDB();
		String sql = "select * from user_akun Where email = ?";
		ResultSet rs ;
		try(PreparedStatement pr = db.con.prepareStatement(sql)) {
			pr.setString(1, email);
			rs = pr.executeQuery();
			if(rs.next()) {	
				cek = true;
			}
			else {
				db.insertTableUser(nama, email, password, alamat, tanggalLahir);
				db.insertTableSaldo(email, 0);
				cek = false;
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("sql error: "+e.getMessage());
			e.printStackTrace();
			cek = true;
		}
		db.closeDB();
		return cek;
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
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo += saldo;
	}
	

}
