package Sources;

public class USD extends Currency{
	//we using the currency from date 16 mei 2020
	//$1 USD = 14.865 IDR
	private double value;
	private final double nilaiTukarSekarang = 14865;
	IDR currency =  new IDR();
	
	public USD(double value) {
		super(value);
		this.value = value;
		this.toIDR();
	}
	public double getValue() {
		return this.value;
	}
	public final double getNilaiTukarSekarang() {
		return this.nilaiTukarSekarang;
	}
	@Override
	public void toIDR() {		
			this.value = this.getValue() * this.getNilaiTukarSekarang(); 
//			currency.addValue(this.value);
	}
	@Override
	public void addValue(double value) {
		this.value += value;
	}

}
