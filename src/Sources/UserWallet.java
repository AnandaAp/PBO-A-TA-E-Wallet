package Sources;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class UserWallet extends EWallet{
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
	public boolean Register(String nama,String tanggalLahir, String password,String email, String alamat, boolean cek) {
		String line = ""; 
		
		File file = new File("user.txt");
		if(!file.exists()) {
			try {
				file.createNewFile();
				FileWriter Write = new FileWriter(file);
				this.setPassword(password);
				this.setEmail(email);
				this.setNama(nama);
				Write.write("user "+this.getNama()+"\n"+this.getEmail()+"\n"+this.getPassword()+"\n");
				Write.close();
				this.setTanggalLahir(tanggalLahir);
				this.setAlamat(alamat);
				this.createUserProfile();
				cek = false;
				System.out.println("try selesai");
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("harusnya ga masuk else");
		}
		else {
			System.out.println("ternyata masuk else");
			try {
				System.out.println("memulai try");
				this.setPassword(password);
				this.setEmail(email);
				BufferedReader fileInput = new BufferedReader(new FileReader(new File("user.txt")));
				line = fileInput.readLine();
				System.out.println("masuk ke pengecekan isi file");
				while(line != null) {
					line = fileInput.readLine();
					System.out.println(line);
					System.out.println("email cek :"+this.getEmail());
					
					if(this.getEmail().equals(line)) {
						cek = true;
						System.out.println(cek);
						break;
					}
					else {
						this.setNama(nama);
						this.setTanggalLahir(tanggalLahir);
						this.setAlamat(alamat);
						FileWriter Write = new FileWriter(file,true);
						Write.append("\n");
						Write.append("user "+this.getNama()+"\n"+this.getEmail()+"\n"+this.getPassword()+"\n");
						Write.close();
						this.createUserProfile();
						cek = false;
						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("apakah error:"+cek);
		return cek;
	}
	public void createUserProfile() {
		File file = new File("profile/"+this.getEmail()+".txt");
		if(!file.exists()) {
			try {
				file.createNewFile();
				FileWriter Write = new FileWriter(file);
				Write.write(""+this.getNama()+"\n"+this.getPassword()+"\n"+ 
						    this.getEmail() + "\n" + this.getTanggalLahir() +
						    "\n" + this.getAlamat());
				Write.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
