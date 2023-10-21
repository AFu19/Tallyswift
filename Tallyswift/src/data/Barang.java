package data;

public class Barang {
	private String kodeBarang; //BR[0-9][0-9][0-9]
	private String kategoriID; //KG[0-9][0-9][0-9]
	private String namaBarang;
	private Integer hargaSatuan;
	private Integer stok;
	private String kategori;
	
	public Barang(String kodeBarang, String kategoriID, String namaBarang, String kategori, Integer hargaSatuan, Integer stok) {
		this.kodeBarang = kodeBarang;
		this.kategoriID = kategoriID;
		this.namaBarang = namaBarang;
		this.hargaSatuan = hargaSatuan;
		this.stok = stok;
		this.kategori = kategori;
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

	public String getKategori() {
		return kategori;
	}

	public void setKategori(String kategori) {
		this.kategori = kategori;
	}
	
	
}
