package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("E-Cashier");

        VBox root = new VBox();
        root.setPrefHeight(400.0);
        root.setPrefWidth(640.0);

        HBox titleBar = new HBox();
        titleBar.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        titleBar.setPrefHeight(51.0);
        titleBar.setPrefWidth(640.0);
        titleBar.setStyle("-fx-background-color: lightgreen;");

        Text titleText = new Text("E-Cashier");
        titleText.setFont(Font.font("Calibri Italic", 30.0));
        HBox.setMargin(titleText, new Insets(5.0, 11.0, 5.0, 0));

//        ImageView logoImageView = new ImageView(new Image("logocashier-removebg-preview.png"));
//        logoImageView.setFitHeight(37.0);
//        logoImageView.setFitWidth(47.0);
//        logoImageView.setPreserveRatio(true);
//        HBox.setMargin(logoImageView, new Insets(0, 5.0, 0, 0));

        titleBar.getChildren().addAll(titleText);

        HBox menuBar = new HBox();
        menuBar.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        menuBar.setPrefHeight(53.0);
        menuBar.setPrefWidth(619.0);

        Hyperlink addLink = new Hyperlink("Tambah Barang");
        addLink.setTextFill(javafx.scene.paint.Color.web("#2d6936"));
        addLink.setUnderline(true);
        addLink.setFont(Font.font(14.0));
        HBox.setMargin(addLink, new Insets(0, 0, 0, 12.0));

        Hyperlink editLink = new Hyperlink("Ubah Barang");
        editLink.setTextFill(javafx.scene.paint.Color.web("#959595"));
        editLink.setFont(Font.font(14.0));
        HBox.setMargin(editLink, new Insets(0, 0, 0, 35.0));

        Hyperlink deleteLink = new Hyperlink("Hapus Barang");
        deleteLink.setTextFill(javafx.scene.paint.Color.web("#959595"));
        deleteLink.setFont(Font.font(14.0));
        HBox.setMargin(deleteLink, new Insets(0, 0, 0, 35.0));

        menuBar.getChildren().addAll(addLink, editLink, deleteLink);

        VBox.setMargin(menuBar, new Insets(10.0, 10.0, 10.0, 10.0));

        VBox inputFields = new VBox();
        inputFields.setPrefHeight(210.0);
        inputFields.setPrefWidth(619.0);

        Text categoryText = new Text("Kategori");

        MenuButton categoryMenuButton = new MenuButton("Pilih Kategori...");
        categoryMenuButton.setMnemonicParsing(false);
        categoryMenuButton.setPrefHeight(26.0);
        categoryMenuButton.setPrefWidth(645.0);
        categoryMenuButton.getItems().addAll(
                new MenuItem("Makanan"),
                new MenuItem("Minuman"),
                new MenuItem("Keperluan Masak"),
                new MenuItem("Bayi"),
                new MenuItem("Alat Kebersihan")
        );
        VBox.setMargin(categoryMenuButton, new Insets(5.0));

        Text nameText = new Text("Nama Barang");
        VBox.setMargin(nameText, new Insets(10.0));

        TextField nameField = new TextField("Nama barang...");
        VBox.setMargin(nameField, new Insets(5.0));

        Text priceText = new Text("Harga Satuan");
        VBox.setMargin(priceText, new Insets(10.0));

        TextField priceField = new TextField("Rp....");
        VBox.setMargin(priceField, new Insets(5.0));

        Text stockText = new Text("Stok");
        VBox.setMargin(stockText, new Insets(10.0));

        TextField stockField = new TextField("Stok...");
        VBox.setMargin(stockField, new Insets(5.0));

        inputFields.getChildren().addAll(categoryText, categoryMenuButton, nameText, nameField, priceText, priceField, stockText, stockField);
        VBox.setMargin(inputFields, new Insets(6.0, 10.0, 10.0, 10.0));

        HBox buttons = new HBox();
        buttons.setPrefHeight(36.0);
        buttons.setPrefWidth(640.0);
        buttons.setAlignment(javafx.geometry.Pos.CENTER);

        Button cancelButton = new Button("Batal");
        cancelButton.setMnemonicParsing(false);
        cancelButton.setPrefHeight(31.0);
        cancelButton.setPrefWidth(76.0);
        cancelButton.setStyle("-fx-background-color: green;");
        cancelButton.setTextFill(javafx.scene.paint.Color.WHITE);
        cancelButton.setFont(Font.font(15.0));
        HBox.setMargin(cancelButton, new Insets(0, 30.0, 0, 0));

        Button addButton = new Button("Tambah");
        addButton.setMnemonicParsing(false);
        addButton.setPrefHeight(31.0);
        addButton.setPrefWidth(76.0);
        addButton.setStyle("-fx-background-color: green;");
        addButton.setTextFill(javafx.scene.paint.Color.WHITE);
        addButton.setFont(Font.font(15.0));

        buttons.getChildren().addAll(cancelButton, addButton);

        HBox bottomButtons = new HBox();
        bottomButtons.setPrefHeight(100.0);
        bottomButtons.setPrefWidth(200.0);

        Button purchaseButton = new Button("Transaksi Pembelian");
        purchaseButton.setMnemonicParsing(false);
        purchaseButton.setPrefHeight(99.0);
        purchaseButton.setPrefWidth(330.0);
        purchaseButton.setStyle("-fx-background-color: transparent;");
        purchaseButton.setTextFill(javafx.scene.paint.Color.web("#004f38"));

        Button dataButton = new Button("Master Data");
        dataButton.setMnemonicParsing(false);
        dataButton.setPrefHeight(99.0);
        dataButton.setPrefWidth(330.0);
        dataButton.setStyle("-fx-background-color: F4F2DE;");
        dataButton.setTextFill(javafx.scene.paint.Color.web("#004f38"));
        HBox.setMargin(dataButton, new Insets(0, 0, 0, 10.0));

        bottomButtons.getChildren().addAll(purchaseButton, dataButton);
        VBox.setMargin(bottomButtons, new Insets(9.0, 1.0, 3.0, 1.0));

        root.getChildren().addAll(titleBar, menuBar, inputFields, buttons, bottomButtons);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
