package data;

public class Keranjang {
	private String namaBarang;
	private Integer qty;
	private Integer harga;
	
	public Keranjang(String namaBarang, Integer qty, Integer harga) {
		super();
		this.namaBarang = namaBarang;
		this.qty = qty;
		this.harga = harga;
	}
	public String getNamaBarang() {
		return namaBarang;
	}
	public void setNamaBarang(String namaBarang) {
		this.namaBarang = namaBarang;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Integer getHarga() {
		return harga;
	}
	public void setHarga(Integer harga) {
		this.harga = harga;
	}
	
	
}
