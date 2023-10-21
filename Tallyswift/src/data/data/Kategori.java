package data;

public class Kategori {
	private String kategoriID;
	private String kategori;
	
	public Kategori(String kategoriID, String kategori) {
		super();
		this.kategoriID = kategoriID;
		this.kategori = kategori;
	}
	
	public void generateID(int idx) {
		if (idx < 10) {
			this.kategoriID = "KG00" + idx;
		}else if (idx < 100) {
			this.kategoriID = "KG0" + idx;
		}else if (idx < 1000) {
			this.kategoriID = "KG" + idx;
		}else {
			System.out.println("Kategori max, tidak dapat menambah kategori");
		}
	}
	
	public String getKategoriID() {
		return kategoriID;
	}
	public void setKategoriID(String kategoriID) {
		this.kategoriID = kategoriID;
	}
	public String getKategori() {
		return kategori;
	}
	public void setKategori(String kategori) {
		this.kategori = kategori;
	}
	
	
}
