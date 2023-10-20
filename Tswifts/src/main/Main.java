package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;

public class Main extends Application {

    private Label kategoriLabel = new Label("Kategori:");
    private TextField kategoriTextField = new TextField();
    private Label namaBarangLabel = new Label("Nama Barang:");
    private TextField namaBarangTextField = new TextField();
    private Label hargaSatuanLabel = new Label("Harga Satuan:");
    private TextField hargaSatuanTextField = new TextField();
    private Label stokLabel = new Label("Stok:");
    private TextField stokTextField = new TextField();
    private Button cancelButton = new Button("Cancel");
    private Button tambahButton = new Button("Tambah");

    @Override
    public void start(Stage primaryStage) {
        VBox leftVBox = new VBox(kategoriLabel, kategoriTextField, namaBarangLabel, namaBarangTextField, hargaSatuanLabel, hargaSatuanTextField, stokLabel, stokTextField);
        leftVBox.setPadding(new Insets(10));
        leftVBox.setSpacing(10);

        HBox buttonHBox = new HBox(cancelButton, tambahButton);
        buttonHBox.setAlignment(Pos.CENTER_RIGHT);
        buttonHBox.setSpacing(10);

        VBox centerVBox = new VBox(leftVBox, buttonHBox);
        centerVBox.setSpacing(10);

        Button transaksiPembelianButton = new Button("Transaksi Pembelian");
        Button masterDataButton = new Button("Master Data");
        HBox footerHBox = new HBox(transaksiPembelianButton, masterDataButton);
        footerHBox.setAlignment(Pos.CENTER);
        footerHBox.setSpacing(10);

        // Create a ToolBar for the menu at the top
        ToolBar topMenuToolBar = new ToolBar(
                createSubMenuButton("Tambah Barang"),
                createSubMenuButton("Ubah Barang"),
                createSubMenuButton("Hapus Barang"),
                createSubMenuButton("Tambah Kategori"),
                createSubMenuButton("Ubah Kategori")
        );

        BorderPane root = new BorderPane();
        root.setCenter(centerVBox);
        root.setTop(topMenuToolBar); // Set the ToolBar as the top content
        root.setBottom(footerHBox);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ecashier");
        primaryStage.show();
    }

    private Button createSubMenuButton(String text) {
        Button button = new Button(text);
        // Add any styling or event handling for the buttons here.
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
