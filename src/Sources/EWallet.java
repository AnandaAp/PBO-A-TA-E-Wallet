package Sources;

import java.util.Scanner;

public abstract class EWallet {
	public abstract boolean Register(String nama, String tanggalLahir, String password, String email, String alamat, boolean cek);

}
