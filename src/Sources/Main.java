package Sources;

public class Main {

	public static void main(String[] args) {
		Currency test = new IDR(500000);
		Currency check = new USD(30);
		System.out.println(test.getValue());
		check.toIDR();
		test.addValue(check.getValue());
		System.out.println(test.getValue());
	}

}
