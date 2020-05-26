package Sources;

public class IDR extends Currency{
	private double value = 0;
	public IDR(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return this.value;
	}
	public void getIDR() {
		this.getValue();
	}
	public void setValue(double value) {
		this.value = value;
	}
	public void addValue(double value) {
		this.value += value;
	}
	@Override
	public void toIDR() {
		this.setValue(this.value);
	}
	public void withdrawValue(double value) {
		this.value = this.getValue() - value;
	}
	

}
