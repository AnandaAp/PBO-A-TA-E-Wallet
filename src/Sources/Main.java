package Sources;

import java.awt.EventQueue;

public class Main {
	public static int countUser;
	public static void main(String[] args) throws Exception {
		UserWallet r = new UserWallet();
//		Wallet w = new Wallet();
//		w.tambahUser(r);
//		w.printAccount();
//		w.addSaldo(new IDR(500000));
//		w.printAccount();
//		w.addSaldo(new USD(30));
//		w.printAccount();
//		Login login = new Login();
//		Login.Login();
//		Wallet w = new Wallet();
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
