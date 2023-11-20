package data;

public class Barang {
	private String kodeBarang; //BR[0-9][0-9][0-9]
	private String kategoriID; //KG[0-9][0-9][0-9]
	private String namaBarang;
	private Integer hargaSatuan;
	private Integer stok;
	
	public Barang(String kodeBarang, String kategoriID, String namaBarang, Integer hargaSatuan, Integer stok) {
		this.kodeBarang = kodeBarang;
		this.kategoriID = kategoriID;
		this.namaBarang = namaBarang;
		this.hargaSatuan = hargaSatuan;
		this.stok = stok;
	}
	
	public String getKodeBarang() {
		return kodeBarang;
	}
	public void setKodeBarang(String kodeBarang) {
		this.kodeBarang = kodeBarang;
	}
	public String getKategoriID() {
		return kategoriID;
	}
	public void setKategoriID(String kategoriID) {
		this.kategoriID = kategoriID;
	}
	public String getNamaBarang() {
		return namaBarang;
	}
	public void setNamaBarang(String namaBarang) {
		this.namaBarang = namaBarang;
	}
	public Integer getHargaSatuan() {
		return hargaSatuan;
	}
	public void setHargaSatuan(Integer hargaSatuan) {
		this.hargaSatuan = hargaSatuan;
	}
	public Integer getStok() {
		return stok;
	}
	public void setStok(Integer stok) {
		this.stok = stok;
	}
	
	@Override
	public String toString() {
		return namaBarang;
	}
}
