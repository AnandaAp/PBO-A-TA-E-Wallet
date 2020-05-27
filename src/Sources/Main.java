package Sources;

import java.awt.EventQueue;

public class Main {
	public static String User;
	public static String getUser() {
		return User;
	}
	public static void main(String[] args) throws Exception {
		UserWallet r = new UserWallet();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Wallet window = new Wallet(r);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
