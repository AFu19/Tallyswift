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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
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

public class Transaction extends Application{

	private Connect connect = Connect.getInstance();
	private Scene scene;
	private ObservableList<Barang> dataBarang;
	private ObservableList<HBox> searchedBarang;
	private BorderPane sceneBorderPane, dataBarangKosongBorderPane, dataCartKosongBorderPane;
	private GridPane containerGridPane, leftContainerGridPane, rightContainerGridPane, footerGridPane, kontenListProdukGridPane, searchGridPane, cartGridPane, cartBoxGridPane, listCartGridPane, subtotalGridPane, grandTotalGridPane;
	private ScrollPane cartScrollPane;
	private StackPane headerStackPane;
	private FlowPane headerFlowPane;
	private StackPane searchStackPane;
	private Label titleLabel, titleDataBarangLabel, dataBarangKosongLabel, listNamaProdukLabel, listHargaProdukLabel, judulKeranjangLabel, dataCartKosongLabel, namaProdukCartLabel, hargaProdukCartLabel, rupiahLabel, subtotalRupiahLabel, subtotalLabel, ppnLabel, angkaSubtotalLabel, angkaPPNLabel, ppnRupiahLabel, grandtotalLabel, grandtotalRupiahLabel, grandtotalAngkaLabel;
	private String titleColor = "#2D6936";
	private Rectangle headerRectangle;
	private Font fontBoldItalic44, fontRegular24, fontRegular18, fontRegular14, fontBold28;
	private Image logoImage, searchIconImage;
	private ImageView logoImageView, searchIconImageView;
	private TextField searchTextField;
	private Button searchButton, checkoutButton, footerPageTransaksiButton, footerPageMasterDataButton;
	private HBox boxListNamaProduk, boxListHargaProduk, listProdukBox, judulKeranjangBox, cartBox, namaProdukCartBox, qtyProdukCartBox, hargaProdukCartBox;
	private ListView<HBox> listViewBarang;
	private ArrayList<Barang> dataCartBarang;
	private Spinner<Integer> qtyProdukCartSpinner;
	private int idxCart = 0, subtotal = 0, grandtotal = 0;
	private double doublePPN = subtotal * 0.1;
	private int ppnInt = (int) doublePPN;
	
	private void getBarangData() {
		dataBarang.clear();
		
		String query = "SELECT * FROM product";
		connect.rs = connect.execQuery(query);
		try {
			while (connect.rs.next()) {
				String kodeBarang = connect.rs.getString("ProductID");
				String kategoriID = connect.rs.getString("CategoryID");
				String namaBarang = connect.rs.getString("ProductName");
				Integer hargaSatuan = connect.rs.getInt("Price");
				Integer stok = connect.rs.getInt("Stock");
				
				dataBarang.add(new Barang(kodeBarang, kategoriID, namaBarang, hargaSatuan, stok));
			}
		} catch (Exception e) {}
		
	}
	
	private void initialize() {
		dataBarang = FXCollections.observableArrayList();
		searchedBarang = FXCollections.observableArrayList();
		
		sceneBorderPane = new BorderPane();
		dataBarangKosongBorderPane = new BorderPane();
		dataCartKosongBorderPane = new BorderPane();
		
		containerGridPane = new GridPane();
		leftContainerGridPane = new GridPane();
		rightContainerGridPane = new GridPane();
		footerGridPane = new GridPane();
		searchGridPane = new GridPane();
		cartGridPane = new GridPane();
		cartBoxGridPane = new GridPane();
		listCartGridPane = new GridPane();
		subtotalGridPane = new GridPane();
		grandTotalGridPane = new GridPane();
		
		headerStackPane = new StackPane();
		searchStackPane = new StackPane();
		
		headerFlowPane = new FlowPane();

		cartScrollPane = new ScrollPane();
		
		titleLabel = new Label("E-Cashier");
		titleDataBarangLabel = new Label("Data Barang");
		dataBarangKosongLabel = new Label("Tidak ada barang yang ditambahkan");
		judulKeranjangLabel = new Label("Keranjang Belanja");
		dataCartKosongLabel = new Label("Tidak ada barang di keranjang belanja");
		subtotalLabel = new Label("Subtotal");
		subtotalRupiahLabel = new Label("Rp");
		angkaSubtotalLabel = new Label(String.valueOf(subtotal));
		ppnLabel = new Label("PPN (10%)");
		ppnRupiahLabel = new Label("Rp");
		angkaPPNLabel = new Label(String.valueOf(ppnInt));
		grandtotalLabel = new Label("Total");
		grandtotalRupiahLabel= new Label("Rp");
		grandtotalAngkaLabel = new Label(String.valueOf(grandtotal));
		
		fontBoldItalic44 = Font.font("Poppins", FontWeight.BOLD, FontPosture.ITALIC, 44);
		fontRegular24 = Font.font("Poppins", 24);
		fontRegular18 = Font.font("Poppins", 18);
		fontRegular14 = Font.font("Poppins", 14);
		fontBold28 = Font.font("Poppins", FontWeight.BOLD, 28);
		
		headerRectangle = new Rectangle(0, 0, 1111, 80);
		
		logoImage = new Image("logo.png");
		logoImageView = new ImageView(logoImage);
		
		searchTextField = new TextField();
		
		searchIconImage = new Image("search.png");
		searchIconImageView = new ImageView(searchIconImage);
		
		searchButton = new Button();
		checkoutButton = new Button("Checkout");
		
		footerPageTransaksiButton = new Button("Transaksi Pembelian");
		footerPageMasterDataButton = new Button("Master Data");
		
		judulKeranjangBox = new HBox();
		cartBox = new HBox();
		
		listViewBarang = new ListView<>();
		
		dataCartBarang = new ArrayList<>();
		
		scene = new Scene(sceneBorderPane, 1111, 790);
		scene.getStylesheets().add("style.css");
	}
	
	private void positioning() {
		initializeHeader();
		initializeContainer();
		initializeFooter();
		
		sceneBorderPane.setTop(headerStackPane);
		sceneBorderPane.setCenter(containerGridPane);
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
		initializeLeftContainer();
		initializeRightContainer();
		
		containerGridPane.add(leftContainerGridPane, 0, 0);
		containerGridPane.add(rightContainerGridPane, 1, 0);
	}
	
	private void initializeLeftContainer() {
		getBarangData();
//		dataBarang.add(new Barang("BR001", "KG001", "Susu", 7000, 10));
		
		styleLeftContainer();
		
		searchGridPane.add(searchTextField, 0, 0);
		searchGridPane.add(searchStackPane, 1, 0);
		
		leftContainerGridPane.add(titleDataBarangLabel, 0, 0);
		leftContainerGridPane.add(searchGridPane, 0, 1);
		
		if (dataBarang.isEmpty()) {
			styleBarangKosong();
		}else {
			initListBarang();
		}
	}
	
	private void styleLeftContainer() {
		titleDataBarangLabel.setFont(fontBold28);
		titleDataBarangLabel.setTextFill(Color.web(titleColor));
		leftContainerGridPane.setMargin(titleDataBarangLabel, new Insets(15, 0, 0, 35));
		
		searchTextField.setFont(fontRegular18);
		searchTextField.setPrefWidth(417);
		searchTextField.setPrefHeight(49);
		searchTextField.setStyle("-fx-background-color: #FFFFFF; -fx-border-radius: 10px; -fx-border-color: black;");
		searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!dataBarang.isEmpty()) {
				
				listViewBarang.getItems().clear();
				
				kontenListProdukGridPane = new GridPane();
				
				String keyWord = newValue.toLowerCase();
				
				
				for (Barang barang : dataBarang) {
					if (barang.getNamaBarang().toLowerCase().contains(keyWord)) {
						
						listProdukBox = new HBox();
						boxListNamaProduk = new HBox();
						boxListHargaProduk = new HBox();
						
						listNamaProdukLabel = new Label(barang.getNamaBarang());
						listHargaProdukLabel = new Label("Rp" + barang.getHargaSatuan());
						
						
						boxListNamaProduk.getChildren().add(listNamaProdukLabel);
						
						boxListHargaProduk.getChildren().add(listHargaProdukLabel);
						
						listProdukBox.getChildren().addAll(boxListNamaProduk, boxListHargaProduk);
						
						searchedBarang.add(listProdukBox);
						
						listNamaProdukLabel.setFont(fontRegular24);
						listHargaProdukLabel.setFont(fontRegular24);
						
						boxListNamaProduk.setMinSize(300, 66);
						boxListNamaProduk.setMaxSize(300, 66);
						boxListNamaProduk.setAlignment(Pos.CENTER_LEFT);
						
						boxListHargaProduk.setMinSize(110, 66);
						boxListHargaProduk.setMaxSize(110, 66);
						boxListHargaProduk.setAlignment(Pos.CENTER_RIGHT);
						
						listProdukBox.setMinSize(440, 66);
						listProdukBox.setMaxSize(440, 66);
						listProdukBox.setPadding(new Insets(0, 0, 0, 15));
						listProdukBox.setStyle("-fx-background-color: white; -fx-border-radius: 10px; -fx-border-color: black;");
						listProdukBox.setOnMouseEntered(e-> scene.setCursor(Cursor.HAND));
						listProdukBox.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
						listProdukBox.setOnMouseClicked(e -> {
							if (dataCartBarang.isEmpty()) {
								initializeCart();					
							}
							
							addCart(new Barang(barang.getKodeBarang(), barang.getKategoriID(), barang.getNamaBarang(), barang.getHargaSatuan(), barang.getStok()));
						});
					}
				}
				
				listViewBarang.setItems(searchedBarang);
				listViewBarang.setPadding(new Insets(15, 0, 15, 12));
				listViewBarang.setStyle("-fx-border-color: transparent");
				listViewBarang.setMinWidth(482);
				listViewBarang.setMinHeight(494);
				
				kontenListProdukGridPane.add(listViewBarang, 0, 0);
				
				leftContainerGridPane.getChildren().remove(kontenListProdukGridPane);
				leftContainerGridPane.add(kontenListProdukGridPane, 0, 2);
				leftContainerGridPane.setMargin(listViewBarang, new Insets(22, 0, 0, 35));
			
			}
			
		});
		
		searchIconImageView.setFitWidth(30);
		searchIconImageView.setFitHeight(30);
		
		searchButton.setPrefHeight(49);
		searchButton.setPrefWidth(60);
		searchButton.setStyle("-fx-background-color: #FFFFFF; -fx-border-radius: 10px; -fx-border-color: black;");
		
		searchStackPane.getChildren().addAll(searchButton, searchIconImageView);
		
		searchGridPane.setHgap(5);
		
		leftContainerGridPane.setMargin(searchGridPane, new Insets(16, 0, 0, 35));
		
	}

	private void styleBarangKosong() {
		dataBarangKosongLabel.setFont(fontRegular14);
		
		dataBarangKosongBorderPane.setCenter(dataBarangKosongLabel);
		dataBarangKosongBorderPane.setAlignment(dataBarangKosongLabel, Pos.CENTER);
		dataBarangKosongBorderPane.setStyle("-fx-background-color: #FFFFFF; -fx-border-radius: 10px 10px 0 0; -fx-border-color: black;");
		dataBarangKosongBorderPane.setPrefWidth(466);
		dataBarangKosongBorderPane.setPrefHeight(493);
		
		leftContainerGridPane.setMargin(dataBarangKosongBorderPane, new Insets(22, 0, 0, 35));
		
		leftContainerGridPane.add(dataBarangKosongBorderPane, 0, 2);
	}
	
	private void initListBarang() {
		kontenListProdukGridPane = new GridPane();
		
		for (Barang barang : dataBarang) {

			listProdukBox = new HBox();
			boxListNamaProduk = new HBox();
			boxListHargaProduk = new HBox();
			
			listNamaProdukLabel = new Label(barang.getNamaBarang());
			listHargaProdukLabel = new Label("Rp" + barang.getHargaSatuan());

			boxListNamaProduk.getChildren().add(listNamaProdukLabel);

			boxListHargaProduk.getChildren().add(listHargaProdukLabel);
			
			listProdukBox.getChildren().addAll(boxListNamaProduk, boxListHargaProduk);

			searchedBarang.add(listProdukBox);
			

			listNamaProdukLabel.setFont(fontRegular24);
			listHargaProdukLabel.setFont(fontRegular24);

			boxListNamaProduk.setMinSize(300, 66);
			boxListNamaProduk.setMaxSize(300, 66);
			boxListNamaProduk.setAlignment(Pos.CENTER_LEFT);
			
			boxListHargaProduk.setMinSize(110, 66);
			boxListHargaProduk.setMaxSize(110, 66);
			boxListHargaProduk.setAlignment(Pos.CENTER_RIGHT);
			
			listProdukBox.setMinSize(440, 66);
			listProdukBox.setMaxSize(440, 66);
			listProdukBox.setPadding(new Insets(0, 0, 0, 15));
			listProdukBox.setStyle("-fx-background-color: white; -fx-border-radius: 10px; -fx-border-color: black;");
			listProdukBox.setOnMouseEntered(e-> scene.setCursor(Cursor.HAND));
			listProdukBox.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
			listProdukBox.setOnMouseClicked(e -> {
				if (dataCartBarang.isEmpty()) {
					initializeCart();					
				}
				
				addCart(new Barang(barang.getKodeBarang(), barang.getKategoriID(), barang.getNamaBarang(), barang.getHargaSatuan(), barang.getStok()));
			});
			
		}
		
		listViewBarang.setItems(searchedBarang);
		listViewBarang.setPadding(new Insets(15, 0, 15, 12));
		listViewBarang.setStyle("-fx-border-color: transparent;");
		listViewBarang.setMinWidth(482);
		listViewBarang.setMinHeight(494);
		
		kontenListProdukGridPane.add(listViewBarang, 0, 0);
		kontenListProdukGridPane.setStyle("-fx-background: black;");
		
		leftContainerGridPane.add(kontenListProdukGridPane, 0, 2);
		leftContainerGridPane.setMargin(listViewBarang, new Insets(22, 0, 0, 35));
		
	}
	
	private void initializeRightContainer() {
		styleRightContainer();
		
		judulKeranjangBox.getChildren().add(judulKeranjangLabel);

		rightContainerGridPane.add(judulKeranjangBox, 0, 0);
		rightContainerGridPane.add(cartScrollPane, 0, 1);
		
		initializeEmptyCart();
		
	}
	
	private void styleRightContainer() {
		containerGridPane.setMargin(rightContainerGridPane, new Insets(38, 0, 0, 49));
		
		judulKeranjangLabel.setFont(fontBold28);
		judulKeranjangLabel.setTextFill(Color.web(titleColor));
		
		judulKeranjangBox.setMinSize(510, 74);
		judulKeranjangBox.setMaxSize(510, 74);
		judulKeranjangBox.setStyle("-fx-background-color: #FFFCFC; -fx-border-radius: 10px 10px 0 0; -fx-border-color: black;");
		judulKeranjangBox.setAlignment(Pos.CENTER);
		
	}
	
	private void initializeEmptyCart() {
		cartScrollPane.setContent(dataCartKosongBorderPane);
		
		dataCartKosongBorderPane.setCenter(dataCartKosongLabel);
		
		styleEmptyCart();		
	}
	
	private void styleEmptyCart() {
		dataCartKosongLabel.setFont(fontRegular14);
		
		dataCartKosongBorderPane.setAlignment(dataCartKosongLabel, Pos.CENTER);
		dataCartKosongBorderPane.setMinSize(510, 510);
		dataCartKosongBorderPane.setStyle("-fx-background-color: #FFFCFC;");
		
		cartScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		cartScrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		cartScrollPane.setMinSize(510, 518);
		cartScrollPane.setMaxWidth(510);
		cartScrollPane.setStyle("-fx-border-color: transparent black transparent black;");
	}
	
	private void initializeCart() {
		styleCart();

		cartScrollPane.setContent(cartGridPane);
		
		cartGridPane.add(cartBox, 0, 0);
		cartGridPane.add(checkoutButton, 0, 1);
		
		cartBox.getChildren().add(cartBoxGridPane);
		
		cartBoxGridPane.add(listCartGridPane, 0, 0);
		cartBoxGridPane.add(subtotalGridPane, 0, 1);
		cartBoxGridPane.add(grandTotalGridPane, 0, 2);
		
		
		subtotalGridPane.add(subtotalLabel, 0, 0);
		subtotalGridPane.add(subtotalRupiahLabel, 1, 0);
		subtotalGridPane.add(angkaSubtotalLabel, 2, 0);
		
		subtotalGridPane.add(ppnLabel, 0, 1);
		subtotalGridPane.add(ppnRupiahLabel, 1, 1);
		subtotalGridPane.add(angkaPPNLabel, 2, 1);
		
		grandTotalGridPane.add(grandtotalLabel, 0, 0);
		grandTotalGridPane.add(grandtotalRupiahLabel, 1, 0);
		grandTotalGridPane.add(grandtotalAngkaLabel, 2, 0);
	}
	
	private void styleCart() {
		cartBoxGridPane.setVgap(40);
		
		cartScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		cartScrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		cartScrollPane.setMinSize(510, 518);
		cartScrollPane.setMaxWidth(510);
		cartScrollPane.setStyle("-fx-background-color: #FFFCFC; -fx-border-color: transparent black transparent black;");
		
		cartGridPane.setMinSize(510, 513);
		cartGridPane.setMaxWidth(510);
		cartGridPane.setPadding(new Insets(18, 17, 10, 18));
		cartGridPane.setStyle("-fx-background-color: #FFFCFC;");
		
		cartBox.setMinSize(470, 380);
		
		subtotalLabel.setFont(fontRegular18);
		subtotalLabel.setAlignment(Pos.CENTER_LEFT);
		subtotalLabel.setMinSize(383, 30);
		
		subtotalRupiahLabel.setFont(fontRegular18);
		
		angkaSubtotalLabel.setFont(fontRegular18);
		angkaSubtotalLabel.setMinSize(55, 30);
		angkaSubtotalLabel.setAlignment(Pos.CENTER_RIGHT);
		
		ppnLabel.setFont(fontRegular18);
		ppnLabel.setAlignment(Pos.CENTER_LEFT);
		ppnLabel.setMinSize(383, 30);
		ppnLabel.setTextFill(Color.web("#BFBFBF"));
		
		ppnRupiahLabel.setFont(fontRegular18);
		ppnRupiahLabel.setTextFill(Color.web("#BFBFBF"));
		
		angkaPPNLabel.setFont(fontRegular18);
		angkaPPNLabel.setMinSize(55, 30);
		angkaPPNLabel.setAlignment(Pos.CENTER_RIGHT);
		angkaPPNLabel.setTextFill(Color.web("#BFBFBF"));
		
		grandtotalLabel.setFont(fontRegular18);
		grandtotalLabel.setAlignment(Pos.CENTER_LEFT);
		grandtotalLabel.setMinSize(383, 30);
		
		grandtotalRupiahLabel.setFont(fontRegular18);
		
		grandtotalAngkaLabel.setFont(fontRegular18);
		grandtotalAngkaLabel.setMinSize(55, 30);
		grandtotalAngkaLabel.setAlignment(Pos.CENTER_RIGHT);
		
		checkoutButton.setMinSize(470, 75);
		checkoutButton.setFont(fontBold28);
		checkoutButton.setStyle("-fx-background-color: #D6F7E7; -fx-border-color: black; -fx-border-radius: 10;");
		checkoutButton.setOnMouseEntered(e -> scene.setCursor(Cursor.HAND));
		checkoutButton.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
		cartGridPane.setMargin(checkoutButton, new Insets(20, 0, 0, 0));
	}
	
	private void addCart(Barang cart) {
		dataCartBarang.add(new Barang(cart.getKodeBarang(), cart.getKategoriID(), cart.getNamaBarang(), cart.getHargaSatuan(), cart.getStok()));
		
		subtotal += cart.getHargaSatuan();
		angkaSubtotalLabel.setText(String.valueOf(subtotal));
		
		doublePPN = subtotal * 0.1;
		ppnInt = (int) doublePPN;
		angkaPPNLabel.setText(String.valueOf(ppnInt));
		
		grandtotal = subtotal + ppnInt;
		grandtotalAngkaLabel.setText(String.valueOf(grandtotal));
		
		namaProdukCartLabel = new Label(cart.getNamaBarang());
		qtyProdukCartSpinner = new Spinner<>(1, cart.getStok(), 1);
		rupiahLabel = new Label("@ Rp");
		hargaProdukCartLabel = new Label(String.valueOf(cart.getHargaSatuan()));		
		
		listCartGridPane.add(namaProdukCartLabel, 0, idxCart);
		listCartGridPane.add(qtyProdukCartSpinner, 1, idxCart);
		listCartGridPane.add(rupiahLabel, 2, idxCart);
		listCartGridPane.add(hargaProdukCartLabel, 3, idxCart);
		listCartGridPane.setVgap(5);
		listCartGridPane.setMinHeight(150);
		
		namaProdukCartLabel.setAlignment(Pos.CENTER_LEFT);
		namaProdukCartLabel.setMinSize(260, 30);
		namaProdukCartLabel.setMaxSize(260, 30);
		namaProdukCartLabel.setFont(fontRegular18);
		
		qtyProdukCartSpinner.setMaxSize(90, 30);
		listCartGridPane.setMargin(qtyProdukCartSpinner, new Insets(0, 10, 0, 0));
		
		rupiahLabel.setFont(fontRegular18);
		
		hargaProdukCartLabel.setAlignment(Pos.CENTER_RIGHT);
		hargaProdukCartLabel.setMinSize(55, 30);
		hargaProdukCartLabel.setMaxSize(55, 30);
		hargaProdukCartLabel.setFont(fontRegular18);
		
		idxCart++;
		
		cartBoxGridPane.getChildren().removeAll(listCartGridPane);
		cartBoxGridPane.add(listCartGridPane, 0, 0);
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
		footerPageTransaksiButton.setStyle("-fx-background-color: #F4F2DE; -fx-border-color: black black transparent transparent;");
		footerPageTransaksiButton.setOnMouseEntered(e-> scene.setCursor(Cursor.HAND));
		footerPageTransaksiButton.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
		
		footerPageMasterDataButton.setPrefWidth(556);
		footerPageMasterDataButton.setPrefHeight(80);
		footerPageMasterDataButton.setFont(fontBold28);
		footerPageMasterDataButton.setTextFill(Color.web("#004F38"));
		footerPageMasterDataButton.setStyle("-fx-background-color: #FCFCFC; -fx-border-color: black black transparent transparent;");
		footerPageMasterDataButton.setOnMouseEntered(e-> scene.setCursor(Cursor.HAND));
		footerPageMasterDataButton.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
	}
	
	
	public Transaction(Stage stage) {
		try {
			this.start(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage transactionPage) throws Exception {
		initialize();
		positioning();
		changePage(transactionPage);
		
		transactionPage.setScene(scene);
	}

	private void changePage(Stage stage) {
		footerPageMasterDataButton.setOnMouseClicked(e -> {
			new MasterData(stage);
		});
	}
	
}
	