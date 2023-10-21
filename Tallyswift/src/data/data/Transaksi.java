package data;

public class Transaksi {
	private String namaBarang;
	private Integer qty;
	private Integer harga;
	private Integer metodePembayaran;
	
	public Transaksi(String namaBarang, Integer qty, Integer harga, Integer metodePembayaran) {
		super();
		this.namaBarang = namaBarang;
		this.qty = qty;
		this.harga = harga;
		this.metodePembayaran = metodePembayaran;
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

	public Integer getMetodePembayaran() {
		return metodePembayaran;
	}

	public void setMetodePembayaran(Integer metodePembayaran) {
		this.metodePembayaran = metodePembayaran;
	}
	
}
