package Sources;

public class USD extends Currency{
	private double value;
	//we using the currency from date 24 April 2020
	//$1 USD = 15.400 IDR
	private final double nilaiTukarSekarang = 15400;
	IDR currency =  new IDR();
	
	public USD(double value) {
		this.value = value;
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
	}

}
