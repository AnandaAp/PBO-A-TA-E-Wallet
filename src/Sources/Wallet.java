package Sources;
import java.util.*;
public class Wallet{
	private ArrayList user = new ArrayList<UserWallet>();
	public void tambahUser(UserWallet u) {
		user.add(u);
	}
	public void print() {
		Collections.sort(user);
		for(int i = 0; i < user.size(); i++) {
			System.out.println(user.get(i) + "\n");
		}
	}
	public void addSaldo(/*double saldo*/Currency idr) {
		user.stream().forEach(elt -> ((UserWallet) elt).setSaldo(idr.getValue()));
	}
	
}
