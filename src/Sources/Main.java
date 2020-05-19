package Sources;

public class Main {

	public static void main(String[] args) {
		UserWallet r = new UserWallet();
		Wallet w = new Wallet();
		w.tambahUser(r);
		w.print();
		w.addSaldo(new IDR(500000));
		w.print();
		w.addSaldo(new USD(30));
		w.print();
	}

}
