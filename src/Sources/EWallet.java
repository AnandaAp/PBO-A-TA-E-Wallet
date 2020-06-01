package Sources;

import java.sql.SQLException;

public abstract class EWallet {
	public abstract boolean Register(String nama, String tanggalLahir, String password, String email, String alamat, boolean cek) throws SQLException;

}
