package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		// Membuat tombol-tombol untuk menu
		Button tambahBarangButton = new Button("Tambah Barang");
		Button ubahBarangButton = new Button("Ubah Barang");
		Button hapusBarangButton = new Button("Hapus Barang");
		Button tambahKategoriButton = new Button("Tambah Kategori");
		Button ubahKategoriButton = new Button("Ubah Kategori");
		
		tambahBarangButton.setPadding(new Insets(20));
		ubahBarangButton.setPadding(new Insets(20));
		hapusBarangButton.setPadding(new Insets(20));
		tambahKategoriButton.setPadding(new Insets(20));
		ubahKategoriButton.setPadding(new Insets(20));
		
		
		GridPane gp;
		GridPane tabel;
		
		gp = new GridPane();
		
		gp.add(tambahBarangButton, 0, 0);
		gp.add(ubahBarangButton, 1, 0);
		gp.add(hapusBarangButton, 2, 0);
		gp.add(tambahKategoriButton, 3, 0);
		gp.add(ubahKategoriButton, 4, 0);

		gp.setAlignment(Pos.TOP_CENTER);
		gp.setHgap(100);
		gp.setPadding(new Insets(20));
		
	
		

		// Membuat tabel dengan 6 kolom: kode, kategori, nama barang, harga satuan, stok, delete
		TableView<Item> columns = new TableView<>();
		
		TableColumn<Item, String> kodeCol = new TableColumn<>("Kode");
		TableColumn<Item, String> kategoriCol = new TableColumn<>("Kategori");
		TableColumn<Item, String> namaBarangCol = new TableColumn<>("Nama Barang");
		TableColumn<Item, Double> hargaSatuanCol = new TableColumn<>("Harga Satuan");
		TableColumn<Item, Integer> stokCol = new TableColumn<>("Stok");
		TableColumn<Item, Button> deleteCol = new TableColumn<>("Delete");
		
		columns.getColumns().addAll(kodeCol, kategoriCol, namaBarangCol, hargaSatuanCol, stokCol, deleteCol);
		
		tabel = new GridPane();
		
		tabel.add(columns, 0, 0);
		
		tabel.setAlignment(Pos.CENTER_RIGHT);
		

		// Membuat tombol "Hapus" di bawah kanan halaman
		Button hapusButton = new Button("Hapus");
		
		hapusButton.setPadding(new Insets (20));
		
		HBox bottomRight = new HBox(10);
		bottomRight.getChildren().add(hapusButton);
		
		bottomRight.setAlignment(Pos.BOTTOM_RIGHT);
		bottomRight.setPadding(new Insets(25));
		

		// Membuat layout utama dengan VBox
		VBox root = new VBox(10);
		root.getChildren().addAll(gp, columns, bottomRight);

		// Menata tata letak untuk memenuhi panjang halaman
		VBox.setVgrow(columns, Priority.NEVER);
		VBox.setVgrow(bottomRight, Priority.NEVER);

		// Memberikan padding yang sama rata untuk navigasi dan kolom
		VBox.setMargin(gp, new Insets(10));
		VBox.setMargin(columns, new Insets(10));

		// Membuat scene dan menambahkan ke stage
		Scene scene = new Scene(root, 800, 600);
		primaryStage.setScene(scene);

		// Menampilkan stage
		primaryStage.setTitle("Aplikasi Manajemen Barang");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}


}

class Item {
	private String kode;
	private String kategori;
	private String namaBarang;
	private double hargaSatuan;
	private int stok;
	private Button deleteButton;

	public Item(String kode, String kategori, String namaBarang, double hargaSatuan, int stok, Button deleteButton) {
		super();
		this.kode = kode;
		this.kategori = kategori;
		this.namaBarang = namaBarang;
		this.hargaSatuan = hargaSatuan;
		this.stok = stok;
		this.deleteButton = deleteButton;
	}

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public String getKategori() {
		return kategori;
	}

	public void setKategori(String kategori) {
		this.kategori = kategori;
	}

	public String getNamaBarang() {
		return namaBarang;
	}

	public void setNamaBarang(String namaBarang) {
		this.namaBarang = namaBarang;
	}

	public double getHargaSatuan() {
		return hargaSatuan;
	}

	public void setHargaSatuan(double hargaSatuan) {
		this.hargaSatuan = hargaSatuan;
	}

	public int getStok() {
		return stok;
	}

	public void setStok(int stok) {
		this.stok = stok;
	}

	public Button getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(Button deleteButton) {
		this.deleteButton = deleteButton;
	}

}
