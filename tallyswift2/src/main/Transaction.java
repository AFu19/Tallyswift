package main;

import java.util.ArrayList;

import data.Barang;
import data.DetailTransaksi;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
	private ObservableList<Barang> dataBarang, searchedBarang;
	private BorderPane sceneBorderPane, dataBarangKosongBorderPane;
	private GridPane containerGridPane, leftContainerGridPane, rightContainerGridPane, footerGridPane, kontenListProdukGridPane, searchGridPane, cartGridPane, cartTotalGridPane;
	private StackPane headerStackPane;
	private FlowPane headerFlowPane;
	private StackPane searchStackPane;
	private Label titleLabel, titleDataBarangLabel, dataBarangKosongLabel, judulKeranjangLabel, subtotalRupiahLabel, subtotalLabel, ppnLabel, angkaSubtotalLabel, angkaPPNLabel, ppnRupiahLabel, grandtotalLabel, grandtotalRupiahLabel, grandtotalAngkaLabel;
	private String titleColor = "#2D6936";
	private Rectangle headerRectangle;
	private Font fontBoldItalic44, fontRegular18, fontRegular14, fontBold28, fontBold18;
	private Image logoImage, searchIconImage;
	private ImageView logoImageView, searchIconImageView;
	private TextField searchTextField;
	private Button searchButton, checkoutButton, deleteCartButton, footerPageTransaksiButton, footerPageMasterDataButton;
	private HBox judulKeranjangBox, checkoutButtonBox, deleteCartButtonBox;
	private ListView<Barang> listViewBarang;
	private ListView<HBox> cartListView;
	private ArrayList<Barang> dataCartBarang;
	private ArrayList<DetailTransaksi> dataDetailBarang = new ArrayList<>();
	private int subtotal = 0, grandtotal = 0;
	private double doublePPN = subtotal * 0.1;
	private int ppnInt = (int) doublePPN;
	
	private void getBarangData() {
		dataBarang.clear();
		
		String query = "SELECT * FROM product WHERE stock > 0";
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
		
		containerGridPane = new GridPane();
		leftContainerGridPane = new GridPane();
		rightContainerGridPane = new GridPane();
		footerGridPane = new GridPane();
		searchGridPane = new GridPane();
		cartGridPane = new GridPane();
		cartTotalGridPane = new GridPane();
		
		headerStackPane = new StackPane();
		searchStackPane = new StackPane();
		
		headerFlowPane = new FlowPane();
		
		titleLabel = new Label("E-Cashier");
		titleDataBarangLabel = new Label("Data Barang");
		dataBarangKosongLabel = new Label("Tidak ada barang yang ditambahkan");
		judulKeranjangLabel = new Label("Keranjang Belanja");
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
		fontRegular18 = Font.font("Poppins", 18);
		fontRegular14 = Font.font("Poppins", 14);
		fontBold28 = Font.font("Poppins", FontWeight.BOLD, 28);
		fontBold18 = Font.font("Poppins", FontWeight.BOLD, 18);
		
		headerRectangle = new Rectangle(0, 0, 1111, 80);
		
		logoImage = new Image("logo.png");
		logoImageView = new ImageView(logoImage);
		
		searchTextField = new TextField();
		
		searchIconImage = new Image("search.png");
		searchIconImageView = new ImageView(searchIconImage);
		
		searchButton = new Button();
		deleteCartButton = new Button("Remove");
		checkoutButton = new Button("Checkout");
		
		footerPageTransaksiButton = new Button("Transaksi Pembelian");
		footerPageMasterDataButton = new Button("Master Data");
		
		judulKeranjangBox = new HBox();
		checkoutButtonBox = new HBox();
		deleteCartButtonBox = new HBox();
		
		listViewBarang = new ListView<>();
		cartListView = new ListView<>();
		
		dataCartBarang = new ArrayList<>();
		
		scene = new Scene(sceneBorderPane, 1111, 790);
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
						if (!dataCartBarang.contains(barang)) {
							searchedBarang.add(barang);
						}
					}
				}
				
				if (searchedBarang.size() <= 7) {
					listViewBarang.setPadding(new Insets(15, 13, 10, 12));					
				}else {
					listViewBarang.setPadding(new Insets(15, 0, 10, 12));
				}
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
			searchedBarang.add(barang);
		}
		
		listViewBarang.setItems(searchedBarang);
		listViewBarang.setPadding(new Insets(15, 0, 10, 12));
		listViewBarang.setStyle("-fx-border-color: transparent;");
		listViewBarang.getStylesheets().add("style.css");
		listViewBarang.getStyleClass().add("list-view-1");
		listViewBarang.setMinSize(482, 495);
		listViewBarang.setMaxSize(482, 495);
		listViewBarang.setOnMouseClicked(e -> {
			Barang barangSelected = listViewBarang.getSelectionModel().getSelectedItem();
			if (barangSelected != null) {
				int itemPrice = barangSelected.getHargaSatuan();
				
				Label namaBarangCartLabel = new Label(barangSelected.getNamaBarang());
				Label itemTotalPriceLabel = new Label(barangSelected.getHargaSatuan().toString());
				Label rupiahCartLabel = new Label("Rp");
				
				Spinner<Integer> itemQuantitySpinner = new Spinner<>(1, barangSelected.getStok(), 1);
				
				IntegerProperty itemTotalPrice = new SimpleIntegerProperty(0);
				itemTotalPrice.bind(Bindings.createIntegerBinding(
						() -> itemQuantitySpinner.getValue() * itemPrice, 
						itemQuantitySpinner.valueProperty()
				));
				itemTotalPriceLabel.textProperty().bind(Bindings.createStringBinding(
						() -> String.format("%d", itemTotalPrice.get()),
						itemTotalPrice
				));
				
				HBox itemBox = new HBox(namaBarangCartLabel, itemQuantitySpinner, rupiahCartLabel, itemTotalPriceLabel);
				namaBarangCartLabel.setAlignment(Pos.CENTER_LEFT);
				namaBarangCartLabel.setMinSize(280, 30);
				namaBarangCartLabel.setMaxSize(280, 30);
				namaBarangCartLabel.setFont(fontRegular18);
				
				itemQuantitySpinner.setMaxWidth(90);
				itemQuantitySpinner.setOnMouseClicked(event -> updateSubtotal());
				
				rupiahCartLabel.setAlignment(Pos.CENTER);
				rupiahCartLabel.setMinSize(30, 30);
				rupiahCartLabel.setMaxSize(30, 30);
				rupiahCartLabel.setFont(fontRegular18);
				
				itemTotalPriceLabel.setAlignment(Pos.CENTER_RIGHT);
				itemTotalPriceLabel.setMinSize(70, 30);
				itemTotalPriceLabel.setMaxSize(70, 30);
				itemTotalPriceLabel.setFont(fontRegular18);
				
				checkoutButton.setDisable(false);
				
				cartListView.getItems().add(itemBox);
				
				dataCartBarang.add(barangSelected);
				
				listViewBarang.getItems().remove(barangSelected);
				
				searchButton.requestFocus();
			}
		});
		
		kontenListProdukGridPane.add(listViewBarang, 0, 0);
		kontenListProdukGridPane.setStyle("-fx-background: black;");
		
		leftContainerGridPane.add(kontenListProdukGridPane, 0, 2);
		leftContainerGridPane.setMargin(listViewBarang, new Insets(22, 0, 0, 35));
	}
	
	private void initializeRightContainer() {
		styleRightContainer();
		
		rightContainerGridPane.add(judulKeranjangBox, 0, 0);
		rightContainerGridPane.add(cartGridPane, 0, 1);
		
		judulKeranjangBox.getChildren().add(judulKeranjangLabel);
		
		cartGridPane.add(cartListView, 0, 0);
		cartGridPane.add(deleteCartButtonBox, 0, 1);
		cartGridPane.add(cartTotalGridPane, 0, 2);
		cartGridPane.add(checkoutButtonBox, 0, 3);
		
		deleteCartButtonBox.getChildren().add(deleteCartButton);
		
		cartTotalGridPane.add(subtotalLabel, 0, 0); 
		cartTotalGridPane.add(subtotalRupiahLabel, 1, 0);
		cartTotalGridPane.add(angkaSubtotalLabel, 2, 0);
		
		cartTotalGridPane.add(ppnLabel, 0, 1);
		cartTotalGridPane.add(ppnRupiahLabel, 1, 1);
		cartTotalGridPane.add(angkaPPNLabel, 2, 1);
		
		cartTotalGridPane.add(grandtotalLabel, 0, 2);
		cartTotalGridPane.add(grandtotalRupiahLabel, 1, 2);
		cartTotalGridPane.add(grandtotalAngkaLabel, 2, 2);
		
		checkoutButtonBox.getChildren().add(checkoutButton);
	}
	
	private void styleRightContainer() {
		containerGridPane.setMargin(rightContainerGridPane, new Insets(38, 0, 0, 49));
		
		judulKeranjangLabel.setFont(fontBold28);
		judulKeranjangLabel.setTextFill(Color.web(titleColor));
		
		judulKeranjangBox.setMinSize(510, 74);
		judulKeranjangBox.setMaxSize(510, 74);
		judulKeranjangBox.setStyle("-fx-background-color: #FFFCFC; -fx-border-radius: 10px 10px 0 0; -fx-border-color: black;");
		judulKeranjangBox.setAlignment(Pos.CENTER);
		
		cartGridPane.setMinSize(510, 518);
		cartGridPane.setMaxSize(510, 518);
		cartGridPane.setStyle("-fx-background-color: #FFFCFC; -fx-border-color: transparent black transparent black;");
		
		cartListView.setMinSize(508, 240);
		cartListView.setMaxSize(508, 240);
		cartListView.getItems().addListener((ListChangeListener.Change<? extends HBox> c) -> {
            updateSubtotal();
        });
		
		deleteCartButtonBox.setAlignment(Pos.CENTER_RIGHT);
		cartGridPane.setMargin(deleteCartButtonBox, new Insets(10, 10, 0, 0));
		
		deleteCartButton.setMinSize(110, 20);
		deleteCartButton.setFont(fontBold18);
		deleteCartButton.setTextFill(Color.RED);
		deleteCartButton.setStyle("-fx-background-color: white; -fx-border-color: red; -fx-border-radius: 10; -fx-border-width: 2;");
		deleteCartButton.setOnMouseEntered(e -> scene.setCursor(Cursor.HAND));
		deleteCartButton.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
		deleteCartButton.setOnMouseClicked(e -> {
			int selectedIndex = cartListView.getSelectionModel().getSelectedIndex();
			if (selectedIndex >= 0) {

				HBox removeBox = cartListView.getItems().remove(selectedIndex);

				String selectedBox = removeBox.getChildren().get(0).toString();
				String tempName = selectedBox.substring(selectedBox.indexOf("'") + 1, selectedBox.length() - 1);
				
				
				for (Barang barang : dataCartBarang) {
					if (barang.getNamaBarang().equals(tempName)) {
						listViewBarang.getItems().add(barang);
						dataCartBarang.remove(barang);
						break;
					}
				}
            }
		});
		
		cartTotalGridPane.setStyle("-fx-background-color: #FFFCFC;");
		cartTotalGridPane.setAlignment(Pos.TOP_CENTER);
		cartTotalGridPane.setPadding(new Insets(5, 25, 0, 10));
		
		subtotalLabel.setFont(fontRegular18);
		subtotalLabel.setAlignment(Pos.CENTER_LEFT);
		subtotalLabel.setMinSize(380, 30);
		
		subtotalRupiahLabel.setFont(fontRegular18);
		
		angkaSubtotalLabel.setFont(fontRegular18);
		angkaSubtotalLabel.setMinSize(70, 30);
		angkaSubtotalLabel.setAlignment(Pos.CENTER_RIGHT);
		
		ppnLabel.setFont(fontRegular18);
		ppnLabel.setAlignment(Pos.CENTER_LEFT);
		ppnLabel.setMinSize(350, 30);
		ppnLabel.setTextFill(Color.web("#BFBFBF"));
		
		ppnRupiahLabel.setFont(fontRegular18);
		ppnRupiahLabel.setTextFill(Color.web("#BFBFBF"));
		
		angkaPPNLabel.setFont(fontRegular18);
		angkaPPNLabel.setMinSize(70, 30);
		angkaPPNLabel.setAlignment(Pos.CENTER_RIGHT);
		angkaPPNLabel.setTextFill(Color.web("#BFBFBF"));
		
		grandtotalLabel.setFont(fontRegular18);
		grandtotalLabel.setAlignment(Pos.BOTTOM_LEFT);
		grandtotalLabel.setMinSize(380, 50);
		
		grandtotalRupiahLabel.setFont(fontRegular18);
		grandtotalRupiahLabel.setMinHeight(50);
		grandtotalRupiahLabel.setAlignment(Pos.BOTTOM_CENTER);
		
		grandtotalAngkaLabel.setFont(fontRegular18);
		grandtotalAngkaLabel.setMinSize(70, 50);
		grandtotalAngkaLabel.setAlignment(Pos.BOTTOM_RIGHT);
		
		
		checkoutButtonBox.setAlignment(Pos.CENTER);
		cartGridPane.setMargin(checkoutButtonBox, new Insets(20, 0, 0, 0));
		
		checkoutButton.setMinSize(470, 75);
		checkoutButton.setFont(fontBold28);
		checkoutButton.setDisable(true);
		checkoutButton.setStyle("-fx-background-color: #D6F7E7; -fx-background-radius: 10;  -fx-border-color: black; -fx-border-radius: 10;");
		checkoutButton.setOnMouseEntered(e -> scene.setCursor(Cursor.HAND));
		checkoutButton.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
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
	
	private void updateSubtotal() {
		subtotal = cartListView.getItems().stream()
                .mapToInt(itemBox -> {
                    Label totalLabel = (Label) itemBox.getChildren().get(3);
                    return Integer.parseInt(totalLabel.getText());
                })
                .sum();
		
		angkaSubtotalLabel.setText(String.valueOf(subtotal));
		doublePPN = subtotal * 0.1;
		ppnInt = (int) doublePPN;
		
		angkaPPNLabel.setText(String.valueOf(ppnInt));
		
		grandtotal = subtotal + ppnInt;
		grandtotalAngkaLabel.setText(String.valueOf(grandtotal));
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
		checkoutBtn(transactionPage);
		
		transactionPage.setScene(scene);
	}
	
	private void changePage(Stage stage) {
		footerPageMasterDataButton.setOnMouseClicked(e -> {
			new MasterData(stage);
		});
	}
	
	private void checkoutBtn(Stage stage) {
		checkoutButton.setOnMouseClicked(e -> {
			String cartProdName;
			Integer cartQty;
			for (int i = 0; i < cartListView.getItems().size(); i++) {
				String tempProdID = cartListView.getItems().get(i).getChildren().get(0).toString();
				cartProdName = tempProdID.substring(tempProdID.indexOf("'") + 1, tempProdID.length() - 1);
				
				Spinner<Integer> tempCartQtySpinner = (Spinner<Integer>) cartListView.getItems().get(i).getChildren().get(1);
				cartQty = tempCartQtySpinner.getValue();
				
				String cartProdID = "";
				for (Barang barang : dataCartBarang) {
					if (barang.getNamaBarang().equals(cartProdName)) {
						cartProdID = barang.getKodeBarang();
					}
				}
				
				Label tempPriceLabel = (Label) cartListView.getItems().get(i).getChildren().get(3);
				Integer cartProdPrice = Integer.valueOf(tempPriceLabel.getText());
				
				dataDetailBarang.add(new DetailTransaksi(cartProdID, cartProdName, cartQty, cartProdPrice));
			}
			
			new MetodePembayaran(stage, dataDetailBarang, grandtotal);
		});
	}
}