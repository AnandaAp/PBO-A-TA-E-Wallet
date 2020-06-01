package Sources;
import java.sql.*;
public class ConnectionDataBase {
//	public static String url = "jdbc:mysql://e-wallet.mysql.database.azure.com:3306/ewallet?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT";
	private final String url ="jdbc:mysql://e-wallet.mysql.database.azure.com:3306/ewallet?useSSL=true&requireSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT";// myDbConn = DriverManager.getConnection(url, "author@e-wallet", {your_password});
	public java.sql.Connection con;
	private Statement stmt;
	private final String author="author@e-wallet";
	private final String password="PBOewallet2020!";
	public void connectDB() {
		try {
			this.setCon(this.url, this.author, this.password);
			this.setStmt(con.createStatement());
		} catch (Exception e) {
			System.out.println("expression: "+e.getMessage());
		}
	
	}
	public void closeDB() {
		try {
			this.getStmt().close();
			this.con.close();
		}
		catch(SQLException e) {
			System.out.println("error: "+e.getMessage());
		}
	}
//	public void createTable(){
//		String createTableUser = 
//				"CREATE TABLE user_akun"
//				+ "(id_user int NOT NULL AUTO_INCREMENT PRIMARY KEY,"
//				+ "nama VARCHAR(255) NOT NULL,"
//				+ "email VARCHAR(255) NOT NULL,"
//				+ "password VARCHAR(16) NOT NULL,"
//				+ "alamat VARCHAR(255) NOT NULL,"
//				+ "tglLahir Varchar(17) NOT NULL);";
//		this.connectDML(createTableUser);
//		System.out.println("success");
//	}
	public void insertTableUser(String nama,String email,String password,String alamat, String tanggalLahir) {
		String insert = 
		"INSERT INTO user_akun (nama,email,password,alamat,tglLahir)" +
		" VALUES (?,?,?,?,?)";
		try(PreparedStatement ps = con.prepareStatement(insert)){
			ps.setString(1, nama);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, alamat);
			ps.setString(5, tanggalLahir);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("error: "+e.getMessage());
		}
		
		System.out.println("adding user success");
	}
	public void insertTableSaldo(String email,double value) {
		String insert = 
				"Insert into user_saldo (user_email,saldo)"+" Values"+
				"(?,?)";
		try(PreparedStatement ps = con.prepareStatement(insert)){
			ps.setString(1, email);
			ps.setDouble(2, value);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("error: "+e.getMessage());
		}
		System.out.println("insert saldo success");
	}
	public Statement getStmt() {
		return stmt;
	}
	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}
	
	public void setCon(String url,String author,String password) {
		try {
			this.con = DriverManager.getConnection(url,author,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
