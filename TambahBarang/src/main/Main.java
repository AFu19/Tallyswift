package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Font;

public class Main extends Application {
	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tambah Barang");

        // Header
        HBox header = new HBox();
        header.setMinHeight(50);
        header.setStyle("-fx-background-color: #F4F2DE;");
        Label titleLabel = new Label("E-Cashier");
        titleLabel.setFont(Font.font("Poppins", 20));
        titleLabel.setStyle("-fx-text-fill: #2D6936; -fx-font-style: italic;");
        titleLabel.setPadding(new Insets(0, 10, 0, 0));
        
        Image image = new Image("logo.png");
        ImageView logo = new ImageView(image);
        logo.setFitWidth(80);
        logo.setFitHeight(30); 
        logo.setPreserveRatio(true);
        
        header.getChildren().addAll(titleLabel, logo);
        header.setAlignment(Pos.CENTER_RIGHT);

        // Tombol tambah barang, ubah barang, hapus barang
        Button tambahbarangBtn = new Button("Tambah Barang");
        Button ubahbarangBtn = new Button("Ubah Barang");
        Button hapusbarangBtn = new Button("Hapus Barang");
        HBox tombolatas = new HBox(10);
        tombolatas.getChildren().addAll(tambahbarangBtn, ubahbarangBtn, hapusbarangBtn);
        tombolatas.setPadding(new Insets(0, 0, 0, 10));

        // Kategori Dropdown
        Label kategori = new Label("Kategori");
        ComboBox<String> kategoriList = new ComboBox<>();
        kategoriList.getItems().addAll("Makanan", "Minuman", "Bahan Masakan", "Bumbu Dapur");
        kategoriList.setPromptText("Pilih Kategori");
        kategoriList.setPrefWidth(800);
        HBox.setHgrow(kategoriList, Priority.ALWAYS);

        // Nama Barang
        Label namaBarang = new Label("Nama Barang");
        TextField namaBarangField = new TextField();
        namaBarangField.setPromptText("Nama Barang");
        namaBarangField.setPrefWidth(800);
        HBox.setHgrow(namaBarangField, Priority.ALWAYS);

        // Harga Satuan
        Label hargaSatuan = new Label("Harga Satuan");
        TextField hargaSatuanField = new TextField();
        hargaSatuanField.setPromptText("Harga Satuan");
        
        // Stok
        Label stok = new Label("Stok");
        TextField stokField = new TextField();
        stokField.setPromptText("Stok");

        // Tombol Cancel dan Tambah
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setStyle("-fx-background-color: #2D6936; -fx-text-fill: white;");
        cancelBtn.setMinWidth(80);
        cancelBtn.setMinHeight(40);
        Button tambahBtn = new Button("Tambah");
        tambahBtn.setStyle("-fx-background-color: #2D6936; -fx-text-fill: white;");
        tambahBtn.setMinWidth(80);
        tambahBtn.setMinHeight(40);

        HBox buttonCT = new HBox(10);
        buttonCT.getChildren().addAll(cancelBtn, tambahBtn);
        buttonCT.setAlignment(Pos.CENTER);
        buttonCT.setSpacing(40);
        
        tambahBtn.setOnAction(e -> {
            // pop up message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informasi");
            alert.setHeaderText(null);
            alert.setContentText("Barang berhasil ditambahkan");
            alert.showAndWait();
        });

        // Tombol transaksi pembelian dan master data        
        Button transaksiBtn = new Button("Transaksi Pembelian");
        transaksiBtn.setPrefHeight(100);
        transaksiBtn.setPrefWidth(400);
        transaksiBtn.setStyle("-fx-background-color: white; -fx-text-fill: #2D6936;");
        Button masterDataBtn = new Button("Master Data");
        masterDataBtn.setPrefHeight(100);
        masterDataBtn.setPrefWidth(400);
        masterDataBtn.setStyle("-fx-background-color: #F4F2DE; -fx-text-fill: #2D6936;");
        
        HBox bottomBar = new HBox();
        bottomBar.setMinHeight(50);
        bottomBar.getChildren().addAll(transaksiBtn, masterDataBtn);
        bottomBar.setAlignment(Pos.CENTER);

        
        // Pane Pengisian Data
        GridPane contentcontainer = new GridPane();
    	contentcontainer.add(kategori, 0, 0);
    	contentcontainer.add(kategoriList, 0, 1);
    	contentcontainer.add(namaBarang, 0, 2);
    	contentcontainer.add(namaBarangField, 0, 3);
    	contentcontainer.add(hargaSatuan, 0, 4);
    	contentcontainer.add(hargaSatuanField, 0, 5);
    	contentcontainer.add(stok, 0, 6);
    	contentcontainer.add(stokField, 0, 7);
    	contentcontainer.setPadding(new Insets(0, 10, 0, 10));
    	
        // Main Layout
        VBox mainLayout = new VBox();
        mainLayout.getChildren().addAll(header, tombolatas, contentcontainer, buttonCT);
        mainLayout.setSpacing(20);
    	BorderPane bp = new BorderPane();
    	bp.setTop(mainLayout);
        bp.setBottom(bottomBar);

        Scene scene = new Scene(bp, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}





