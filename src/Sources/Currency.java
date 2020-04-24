package Sources;

public abstract class Currency {
	private double value;
	public Currency() {
	}
	public Currency(double value) {
		this.value = value;
	}
	public abstract void addValue(double value);
	public abstract double getValue();
	public abstract void toIDR();
}
