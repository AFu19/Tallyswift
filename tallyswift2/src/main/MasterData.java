package main;

import java.util.ArrayList;

import data.Barang;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import util.Connect;

public class MasterData extends Application{
	
	private Connect connect = Connect.getInstance();
	private Scene scene;
	private BorderPane sceneBorderPane, containerBorderPane;
	private GridPane crudGridPane, tabelGridPane, footerGridPane;
	private StackPane headerStackPane;
	private FlowPane headerFlowPane;
	private Label titleLabel, kodeTitleLabel, kategoriTitleLabel, namaTitleLabel, hargaTitleLabel, stokTitleLabel, kodeLabel, kategoriLabel, namaLabel, hargaLabel, stokLabel;
	private Rectangle headerRectangle;
	private Font fontBoldItalic44, fontBold18, fontBold28, fontRegular14;
	private Image logoImage;
	private ImageView logoImageView;
	private String titleColor = "#2D6936";
	private ArrayList<Barang> dataBarang;
	private ObservableList<HBox> dataListTabel;
	private ListView<HBox> listViewTabel;
	private Button tambahBarangButton, ubahBarangButton, hapusBarangButton, footerPageTransaksiButton, footerPageMasterDataButton;
	private HBox tabelHeaderBox, tabelBodyBox;
	
	private void getBarangData() {
		dataBarang.clear();
		
		String query = "SELECT * FROM product JOIN productcategory WHERE product.CategoryID = productcategory.CategoryID ORDER BY ProductID";
		connect.rs = connect.execQuery(query);
		try {
			while (connect.rs.next()) {
				String kodeBarang = connect.rs.getString("ProductID");
				String kategoriID = connect.rs.getString("Category");
				String namaBarang = connect.rs.getString("ProductName");
				Integer hargaSatuan = connect.rs.getInt("Price");
				Integer stok = connect.rs.getInt("Stock");
				
				dataBarang.add(new Barang(kodeBarang, kategoriID, namaBarang, hargaSatuan, stok));
			}
		} catch (Exception e) {}
		
	}
	
	private void initialize() {
		dataBarang = new ArrayList<>();
		
		sceneBorderPane = new BorderPane();
		containerBorderPane = new BorderPane();
		
		crudGridPane = new GridPane();
		tabelGridPane = new GridPane();
		footerGridPane = new GridPane();
		
		headerFlowPane = new FlowPane();
		
		headerStackPane = new StackPane();
		
		titleLabel = new Label("E-Cashier");
		kodeTitleLabel = new Label("Kode");
		kategoriTitleLabel = new Label("Kategori");
		namaTitleLabel = new Label("Nama Barang");
		hargaTitleLabel = new Label("Harga Satuan");
		stokTitleLabel = new Label("Stok");
		
		fontBoldItalic44 = Font.font("Poppins", FontWeight.BOLD, FontPosture.ITALIC, 44);
		fontBold28 = Font.font("Poppins", FontWeight.BOLD, 28);
		fontBold18 = Font.font("Poppins", FontWeight.BOLD, 18);
		fontRegular14 = Font.font("Poppins", 14);
		
		headerRectangle = new Rectangle(0, 0, 1111, 80);
		
		logoImage = new Image("logo.png");
		logoImageView = new ImageView(logoImage);
		
		tambahBarangButton = new Button("Tambah Barang");
		ubahBarangButton = new Button("Ubah Barang");
		hapusBarangButton = new Button("Hapus Barang");
		
		tabelHeaderBox = new HBox();
		
		listViewTabel = new ListView<>();
		
		dataListTabel = FXCollections.observableArrayList();
		
		footerPageTransaksiButton = new Button("Transaksi Pembelian");
		footerPageMasterDataButton = new Button("Master Data");
		
		scene = new Scene(sceneBorderPane, 1111, 790);
		scene.getStylesheets().add("style2.css");
	}
	
	private void positioning() {
		initializeHeader();
		initializeContainer();
		initializeFooter();
		
		sceneBorderPane.setTop(headerStackPane);
		sceneBorderPane.setCenter(containerBorderPane);
		sceneBorderPane.setBottom(footerGridPane);
	}

	private void initializeHeader() {
		styleHeader();
		
		headerFlowPane.getChildren().addAll(titleLabel, logoImageView);
		
		headerStackPane.getChildren().addAll(headerRectangle, headerFlowPane);
	}
	
	private void styleHeader() {
		titleLabel.setFont(fontBoldItalic44);
		titleLabel.setTextFill(Color.web(titleColor));
		titleLabel.setPadding(new Insets(5, 0, 0, 0));
		
		logoImageView.setFitWidth(70);
		logoImageView.setFitHeight(49);
		
		headerFlowPane.setMargin(titleLabel, new Insets(0, 0, 0, 811));
		headerFlowPane.setMargin(logoImageView, new Insets(13, 0, 0, 13));
		
		headerRectangle.setFill(Color.web("#F4F2DE"));
		
		sceneBorderPane.setAlignment(titleLabel, Pos.CENTER);
	}
	
	private void initializeContainer() {
		styleContainer();
		getBarangData();
		
		containerBorderPane.setTop(crudGridPane);
		containerBorderPane.setCenter(tabelGridPane);
		
		crudGridPane.add(tambahBarangButton, 0, 0);
		crudGridPane.add(ubahBarangButton, 1, 0);
		crudGridPane.add(hapusBarangButton, 2, 0);
		
		tabelGridPane.add(tabelHeaderBox, 0, 0);
		tabelGridPane.add(listViewTabel, 0, 1);
		
		tabelHeaderBox.getChildren().addAll(kodeTitleLabel, kategoriTitleLabel, namaTitleLabel, hargaTitleLabel, stokTitleLabel);
		
		for (Barang barang : dataBarang) {
			tabelBodyBox = new HBox();
			
			kodeLabel = new Label(barang.getKodeBarang());
			kategoriLabel = new Label(barang.getKategoriID());
			namaLabel = new Label(barang.getNamaBarang());
			hargaLabel = new Label("Rp" + barang.getHargaSatuan());
			stokLabel = new Label(barang.getStok().toString());
			
			tabelBodyBox.getChildren().addAll(kodeLabel, kategoriLabel, namaLabel, hargaLabel, stokLabel);
			
			dataListTabel.add(tabelBodyBox);
			
			//styling
			tabelBodyBox.setStyle("-fx-border-color: transparent transparent #CDCDCD transparent");
			
			kodeLabel.setMinSize(120, 60);
			kodeLabel.setAlignment(Pos.CENTER);
			kodeLabel.setFont(fontRegular14);
			
			kategoriLabel.setMinSize(200, 60);
			kategoriLabel.setAlignment(Pos.CENTER);
			kategoriLabel.setFont(fontRegular14);
			
			namaLabel.setMinSize(300, 60);
			namaLabel.setAlignment(Pos.CENTER);
			namaLabel.setFont(fontRegular14);
			
			hargaLabel.setMinSize(150, 60);
			hargaLabel.setAlignment(Pos.CENTER);
			hargaLabel.setFont(fontRegular14);
			
			stokLabel.setMinSize(100, 60);
			stokLabel.setAlignment(Pos.CENTER);
			stokLabel.setFont(fontRegular14);
		}
		listViewTabel.setItems(dataListTabel);
	}
	
	private void styleContainer() {
		containerBorderPane.setStyle("-fx-background-color: white;");
		containerBorderPane.setMargin(crudGridPane, new Insets(24, 0, 0, 35));
		containerBorderPane.setMargin(tabelGridPane, new Insets(20, 0, 0, 0));
		
		crudGridPane.setHgap(75);
		
		tambahBarangButton.setFont(fontBold18);
		tambahBarangButton.setTextFill(Color.web("#959595"));
		tambahBarangButton.setPadding(new Insets(0, 10, 0, 10));
		tambahBarangButton.setStyle("-fx-background-color: white; -fx-border-color: transparent;");
		tambahBarangButton.setOnMouseEntered(e-> scene.setCursor(Cursor.HAND));
		tambahBarangButton.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
		
		ubahBarangButton.setFont(fontBold18);
		ubahBarangButton.setTextFill(Color.web("#959595"));
		ubahBarangButton.setPadding(new Insets(0, 10, 0, 10));
		ubahBarangButton.setStyle("-fx-background-color: white; -fx-border-color: transparent;");
		ubahBarangButton.setOnMouseEntered(e-> scene.setCursor(Cursor.HAND));
		ubahBarangButton.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
		
		hapusBarangButton.setFont(fontBold18);
		hapusBarangButton.setTextFill(Color.web("#959595"));
		hapusBarangButton.setPadding(new Insets(0, 10, 0, 10));
		hapusBarangButton.setStyle("-fx-background-color: white; -fx-border-color: transparent;");
		hapusBarangButton.setOnMouseEntered(e-> scene.setCursor(Cursor.HAND));
		hapusBarangButton.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
		
		tabelGridPane.setMinSize(1111, 500);
		tabelGridPane.setStyle("-fx-background-color: white;");
		
		kodeTitleLabel.setMinSize(120, 70);
		kodeTitleLabel.setAlignment(Pos.CENTER);
		kodeTitleLabel.setFont(fontBold18);
		
		kategoriTitleLabel.setMinSize(200, 70);
		kategoriTitleLabel.setAlignment(Pos.CENTER);
		kategoriTitleLabel.setFont(fontBold18);
		
		namaTitleLabel.setMinSize(300, 70);
		namaTitleLabel.setAlignment(Pos.CENTER);
		namaTitleLabel.setFont(fontBold18);
		
		hargaTitleLabel.setMinSize(150, 70);
		hargaTitleLabel.setAlignment(Pos.CENTER);
		hargaTitleLabel.setFont(fontBold18);
		
		stokTitleLabel.setMinSize(100, 70);
		stokTitleLabel.setAlignment(Pos.CENTER);
		stokTitleLabel.setFont(fontBold18);
		
		listViewTabel.setMinSize(1111, 490);
	}
	
	private void initializeFooter() {
		styleFooter();
		
		footerGridPane.add(footerPageTransaksiButton, 0, 0);
		footerGridPane.add(footerPageMasterDataButton, 1, 0);
	}
	
	private void styleFooter() {
		footerGridPane.setPrefWidth(1111);
		
		footerPageTransaksiButton.setPrefWidth(555);
		footerPageTransaksiButton.setPrefHeight(80);
		footerPageTransaksiButton.setFont(fontBold28);
		footerPageTransaksiButton.setTextFill(Color.web("#004F38"));
		footerPageTransaksiButton.setStyle("-fx-background-color: #FCFCFC; -fx-border-color: black black transparent transparent;");
		footerPageTransaksiButton.setOnMouseEntered(e-> scene.setCursor(Cursor.HAND));
		footerPageTransaksiButton.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
		
		footerPageMasterDataButton.setPrefWidth(556);
		footerPageMasterDataButton.setPrefHeight(80);
		footerPageMasterDataButton.setFont(fontBold28);
		footerPageMasterDataButton.setTextFill(Color.web("#004F38"));
		footerPageMasterDataButton.setStyle("-fx-background-color: #F4F2DE; -fx-border-color: black black transparent transparent;");
		footerPageMasterDataButton.setOnMouseEntered(e-> scene.setCursor(Cursor.HAND));
		footerPageMasterDataButton.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
	}
	
	public MasterData(Stage stage) {
		try {
			this.start(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void start(Stage masterPage) throws Exception {
		initialize();
		positioning();
		changePage(masterPage);
		
		masterPage.setScene(scene);
		masterPage.show();
	}
	
	private void changePage(Stage stage) {
		footerPageTransaksiButton.setOnMouseClicked(e -> {
			new Transaction(stage);
		});
		tambahBarangButton.setOnMouseClicked(e -> {
			new TambahBarang(stage);
		});
		ubahBarangButton.setOnMouseClicked(e -> {
			new UbahBarang(stage);
		});
		hapusBarangButton.setOnMouseClicked(e -> {
			new DeleteBarang(stage);
		});
	}

}
