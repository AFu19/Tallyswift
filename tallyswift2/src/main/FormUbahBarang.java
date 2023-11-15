package main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.Barang;
import data.Kategori;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.Connect;

public class FormUbahBarang extends Application{

	private Connect connect = Connect.getInstance();
	private Scene scene;
	private BorderPane sceneBorderPane, containerBorderPane;
	private GridPane crudGridPane, inputDataGridPane, footerGridPane;
	private StackPane headerStackPane;
	private FlowPane headerFlowPane;
	private Label titleLabel, kategoriLabel, namaBarangLabel, hargaLabel, stokLabel, fieldKosongLabel;
	private TextField namaBarangTF, hargaTF, stokTF;
	private Rectangle headerRectangle;
	private Font fontBoldItalic44, fontSBold28, fontBold18, fontBold28, fontRegular20;
	private Image logoImage;
	private ImageView logoImageView;
	private String titleColor = "#2D6936", kodeUbah;
	private List<Kategori> dataKategori;
	private Button tambahBarangButton, ubahBarangButton, hapusBarangButton, cancelButton, ubahButton, footerPageTransaksiButton, footerPageMasterDataButton;
	private HBox tambahButtonBox;
	private ComboBox<Kategori> kategoriList;
	private String selectedID, kodeBarang, kategoriID, namaBarang;
	private Integer hargaSatuan, stok;
	private Barang barang;
	private Kategori valueKategori;
	
	private void getKategori() {
		dataKategori.clear();
		
		String query = "SELECT * FROM productcategory";
		connect.rs = connect.execQuery(query);
		try {
			while (connect.rs.next()) {
				String idKategori = connect.rs.getString("CategoryID");
				String namaKategori = connect.rs.getString("Category");
				
				dataKategori.add(new Kategori(idKategori, namaKategori));
			}
		} catch (Exception e) {}
	}
	
	private void getBarangValue() {
		String query = "SELECT * FROM product WHERE ProductID = '" + kodeUbah + "'";
		connect.rs = connect.execQuery(query);
		
		try {
			while (connect.rs.next()) {
				kodeBarang = connect.rs.getString("ProductID");
				kategoriID = connect.rs.getString("CategoryID");
				namaBarang = connect.rs.getString("ProductName");
				hargaSatuan = connect.rs.getInt("Price");
				stok = connect.rs.getInt("Stock");

				barang = new Barang(kodeBarang, kategoriID, namaBarang, hargaSatuan, stok);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void getKategoriValue() {
		String query = "SELECT * FROM productcategory WHERE CategoryID = '" + barang.getKategoriID() + "'";
		connect.rs = connect.execQuery(query);
		
		try {
			while (connect.rs.next()) {
				String kategoriID = connect.rs.getString("CategoryID");
				String kategoriVal = connect.rs.getString("Category");

				valueKategori = new Kategori(kategoriID, kategoriVal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void editData(String ProductID, String CategoryID, String ProductName, String Price, String Stock) {
		String query = String.format(
				"UPDATE product "
				+ "SET CategoryID = '%s', ProductName = '%s', Price = %s, Stock = %s "
				+ "WHERE ProductID = '%s'", CategoryID, ProductName, Price, Stock, ProductID);
		
		connect.execUpdate(query);
	}
	
	private void initialize() {
		getBarangValue();
		
		dataKategori = new ArrayList<>();
		
		sceneBorderPane = new BorderPane();
		containerBorderPane = new BorderPane();
		
		crudGridPane = new GridPane();
		inputDataGridPane = new GridPane();
		footerGridPane = new GridPane();
		
		headerFlowPane = new FlowPane();
		
		headerStackPane = new StackPane();
		
		titleLabel = new Label("E-Cashier");
		kategoriLabel = new Label("Kategori");
		namaBarangLabel = new Label("Nama Barang");
		hargaLabel = new Label("Harga Satuan");
		stokLabel = new Label("Stok");
		fieldKosongLabel = new Label("");
		
		namaBarangTF = new TextField();
		hargaTF = new TextField();
		stokTF = new TextField();
		
		fontBoldItalic44 = Font.font("Poppins", FontWeight.BOLD, FontPosture.ITALIC, 44);
		fontSBold28 = Font.font("Poppins", FontWeight.EXTRA_BOLD, 28);
		fontBold28 = Font.font("Poppins", FontWeight.BOLD, 28);
		fontBold18 = Font.font("Poppins", FontWeight.BOLD, 18);
		fontRegular20 = Font.font("Poppins", 20);
		
		headerRectangle = new Rectangle(0, 0, 1111, 80);
		
		logoImage = new Image("logo.png");
		logoImageView = new ImageView(logoImage);
		
		tambahBarangButton = new Button("Tambah Barang");
		ubahBarangButton = new Button("Ubah Barang");
		hapusBarangButton = new Button("Hapus Barang");
		cancelButton = new Button("Cancel");
		ubahButton = new Button("Ubah");
		
		tambahButtonBox = new HBox();
		
		kategoriList = new ComboBox<>();
		
		footerPageTransaksiButton = new Button("Transaksi Pembelian");
		footerPageMasterDataButton = new Button("Master Data");
		
		scene = new Scene(sceneBorderPane, 1111, 790);
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
		getKategori();
		
		containerBorderPane.setTop(crudGridPane);
		containerBorderPane.setCenter(inputDataGridPane);
		containerBorderPane.setBottom(tambahButtonBox);
		
		crudGridPane.add(tambahBarangButton, 0, 0);
		crudGridPane.add(ubahBarangButton, 1, 0);
		crudGridPane.add(hapusBarangButton, 2, 0);
		
		inputDataGridPane.add(kategoriLabel, 0, 0);
		inputDataGridPane.add(kategoriList, 0, 1);
		inputDataGridPane.add(namaBarangLabel, 0, 2);
		inputDataGridPane.add(namaBarangTF, 0, 3);
		inputDataGridPane.add(hargaLabel, 0, 4);
		inputDataGridPane.add(hargaTF, 0, 5);
		inputDataGridPane.add(stokLabel, 0, 6);
		inputDataGridPane.add(stokTF, 0, 7);
		inputDataGridPane.add(fieldKosongLabel, 0, 8);
		
		kategoriList.setItems(FXCollections.observableArrayList(dataKategori));
		
		tambahButtonBox.getChildren().addAll(cancelButton, ubahButton);
	}
	
	private void styleContainer() {
		getKategoriValue();
		
		containerBorderPane.setStyle("-fx-background-color: white;");
		containerBorderPane.setMargin(crudGridPane, new Insets(24, 0, 0, 35));
		containerBorderPane.setMargin(tambahButtonBox, new Insets(0, 0, 28, 0));
		
		crudGridPane.setHgap(75);
		
		tambahBarangButton.setFont(fontBold18);
		tambahBarangButton.setTextFill(Color.web("#959595"));
		tambahBarangButton.setPadding(new Insets(0, 10, 0, 10));
		tambahBarangButton.setStyle("-fx-background-color: white; -fx-border-color: transparent;");
		tambahBarangButton.setOnMouseEntered(e-> scene.setCursor(Cursor.HAND));
		tambahBarangButton.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
		
		ubahBarangButton.setFont(fontBold18);
		ubahBarangButton.setTextFill(Color.web("#2D6936"));
		ubahBarangButton.setPadding(new Insets(0, 10, 0, 10));
		ubahBarangButton.setStyle("-fx-background-color: white; -fx-border-color: transparent transparent #2D6936 transparent;");
		ubahBarangButton.setOnMouseEntered(e-> scene.setCursor(Cursor.HAND));
		ubahBarangButton.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
		
		hapusBarangButton.setFont(fontBold18);
		hapusBarangButton.setTextFill(Color.web("#959595"));
		hapusBarangButton.setPadding(new Insets(0, 10, 0, 10));
		hapusBarangButton.setStyle("-fx-background-color: white; -fx-border-color: transparent;");
		hapusBarangButton.setOnMouseEntered(e-> scene.setCursor(Cursor.HAND));
		hapusBarangButton.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
		
		inputDataGridPane.setMinSize(1111, 470);
		inputDataGridPane.setAlignment(Pos.CENTER);
		inputDataGridPane.setMargin(kategoriLabel, new Insets(10, 0, 0, 0));
		inputDataGridPane.setMargin(namaBarangLabel, new Insets(10, 0, 0, 0));
		inputDataGridPane.setMargin(hargaLabel, new Insets(10, 0, 0, 0));
		inputDataGridPane.setMargin(stokLabel, new Insets(10, 0, 0, 0));
		
		kategoriLabel.setFont(fontRegular20);
		namaBarangLabel.setFont(fontRegular20);
		hargaLabel.setFont(fontRegular20);
		stokLabel.setFont(fontRegular20);
		
		kategoriList.setMinSize(1000, 35);
		kategoriList.setValue(valueKategori);
		kategoriList.setOnAction(e -> {
			Kategori selected = kategoriList.getValue();
			selectedID = selected.getKategoriID();
		});
		
		namaBarangTF.setMinSize(1000, 35);
		namaBarangTF.setStyle("-fx-background-radius: 5px");
		namaBarangTF.setText(namaBarang);
		
		hargaTF.setMinSize(1000, 35);
		hargaTF.setPromptText("Lebih dari 0");
		hargaTF.setStyle("-fx-background-radius: 5px");
		hargaTF.setText(hargaSatuan.toString());
		hargaTF.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					hargaTF.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		
		stokTF.setMinSize(1000, 35);
		stokTF.setPromptText("Lebih dari 0");
		stokTF.setStyle("-fx-background-radius: 5px");
		stokTF.setText(stok.toString());
		stokTF.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					stokTF.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		
		tambahButtonBox.setAlignment(Pos.CENTER);
		tambahButtonBox.setSpacing(20);
		
		cancelButton.setStyle("-fx-background-color: #2D6936; -fx-background-radius: 20px");
		cancelButton.setMinSize(200, 70);
		cancelButton.setTextFill(Color.web("#FFFFFF"));
		cancelButton.setFont(fontSBold28);
		cancelButton.setAlignment(Pos.CENTER);
		cancelButton.setOnMouseEntered(e-> scene.setCursor(Cursor.HAND));
		cancelButton.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
		
		ubahButton.setStyle("-fx-background-color: #2D6936; -fx-background-radius: 20px");
		ubahButton.setMinSize(200, 70);
		ubahButton.setTextFill(Color.web("#FFFFFF"));
		ubahButton.setFont(fontSBold28);
		ubahButton.setAlignment(Pos.CENTER);
		ubahButton.setOnMouseEntered(e-> scene.setCursor(Cursor.HAND));
		ubahButton.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
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
	
	public FormUbahBarang(Stage stage, String kodeUbah) {
		this.kodeUbah = kodeUbah;
		try {
			this.start(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage formUbahPage) throws Exception {
		initialize();
		positioning();
		changePage(formUbahPage);
		popItemChanged(formUbahPage);
		
		formUbahPage.setScene(scene);
		formUbahPage.show();
	}
	
	private void changePage(Stage stage) {
		footerPageTransaksiButton.setOnMouseClicked(e -> {
			new Transaction(stage);
		});
		footerPageMasterDataButton.setOnMouseClicked(e -> {
			new MasterData(stage);
		});
		tambahBarangButton.setOnMouseClicked(e -> {
			new TambahBarang(stage);
		});
		cancelButton.setOnMouseClicked(e -> {
			new UbahBarang(stage);
		});
		hapusBarangButton.setOnMouseClicked(e -> {
			new DeleteBarang(stage);
		});
	}
	
	private void popItemChanged(Stage stage) {
		ubahButton.setOnMouseClicked(e -> {
			for (Kategori kategori : dataKategori) {
				if (kategoriList.getValue().toString().equals(kategori.getKategori())) {
					selectedID = kategori.getKategoriID();
				}
			}
			
			if (namaBarangTF.getText().isEmpty() || hargaTF.getText().isEmpty() || Integer.valueOf(hargaTF.getText()) < 1 || stokTF.getText().isEmpty() || Integer.valueOf(stokTF.getText()) < 1) {
				fieldKosongLabel.setText("Semua field harus diisi value dengan benar!");
				fieldKosongLabel.setTextFill(Color.RED);
			}else {
				
				editData(kodeBarang, selectedID, namaBarangTF.getText(), hargaTF.getText(), stokTF.getText());
				
				Stage popupStage;
				Scene popupScene;
				BorderPane popupPane;
				Label notifLabel;
				HBox buttonBox;
				Button nextButton;
				
				popupStage = new Stage();
				
				popupPane = new BorderPane();
				notifLabel = new Label("Barang berhasil diubah!");
				buttonBox = new HBox();
				nextButton = new Button("Next");
				
				popupScene = new Scene(popupPane, 480, 250);
				
				
				popupStage.initModality(Modality.APPLICATION_MODAL);
				popupStage.setTitle("Notification");
				
				notifLabel.setFont(Font.font("Poppins", FontWeight.BOLD, 24));
				
				nextButton.setStyle("-fx-background-color: #E8F9F0; -fx-background-radius: 10px; -fx-border-color: black; -fx-border-radius: 10px;");
				nextButton.setFont(Font.font("Poppins", FontWeight.BOLD, 24));
				nextButton.setMinSize(200, 40);
				nextButton.setOnMouseEntered(event -> popupScene.setCursor(Cursor.HAND));
				nextButton.setOnMouseExited(event -> popupScene.setCursor(Cursor.DEFAULT));
				
				buttonBox.getChildren().add(nextButton);
				buttonBox.setAlignment(Pos.CENTER);
				popupPane.setMargin(buttonBox, new Insets(0, 0, 50, 0));				

				nextButton.setOnMouseClicked(event -> {
					popupStage.hide();
					new UbahBarang(stage);
				});
				
				popupPane.setCenter(notifLabel);
				popupPane.setBottom(buttonBox);
				
				popupStage.setScene(popupScene);
				popupStage.setResizable(false);
				popupStage.setMaximized(false);
				
				popupStage.setOnHidden(event -> {
					new UbahBarang(stage);
				});
				
				popupStage.showAndWait();
			}
		});
        
    }
	
}
