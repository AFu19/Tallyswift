package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    private TextField tfKategori, tfNamaBarang, tfHargaSatuan, tfStok;
    private Button btnCancel, btnUbah;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        //Top menu
        HBox topMenu = new HBox();
        topMenu.setPadding(new Insets(5, 5, 5, 5));
        topMenu.setSpacing(5);
        topMenu.setAlignment(Pos.CENTER);
        topMenu.getChildren().addAll(
                new Button("Tambah Barang"),
                new Button("Ubah Barang"),
                new Button("Hapus Barang"),
                new Button("Tambah Kategori")
        );
        root.setTop(topMenu);

        //Center
        GridPane center = new GridPane();
        center.setPadding(new Insets(5, 5, 5, 5));
        center.setHgap(5);
        center.setVgap(5);

        Label lblKategori = new Label("Kategori");
        tfKategori = new TextField();
        tfKategori.setPromptText("Masukkan kategori");

        Label lblNamaBarang = new Label("Nama Barang");
        tfNamaBarang = new TextField();
        tfNamaBarang.setPromptText("Masukkan nama barang");

        Label lblHargaSatuan = new Label("Harga Satuan");
        tfHargaSatuan = new TextField();
        tfHargaSatuan.setPromptText("Masukkan harga satuan");

        Label lblStok = new Label("Stok");
        tfStok = new TextField();
        tfStok.setPromptText("Masukkan stok");

        center.add(lblKategori, 0, 0);
        center.add(tfKategori, 1, 0);
        center.add(lblNamaBarang, 0, 1);
        center.add(tfNamaBarang, 1, 1);
        center.add(lblHargaSatuan, 0, 2);
        center.add(tfHargaSatuan, 1, 2);
        center.add(lblStok, 0, 3);
        center.add(tfStok, 1, 3);

        root.setCenter(center);

        //Bottom
        HBox bottom = new HBox();
        bottom.setPadding(new Insets(5, 5, 5, 5));
        bottom.setSpacing(5);
        bottom.setAlignment(Pos.CENTER);

        btnCancel = new Button("Cancel");
        btnUbah = new Button("Ubah");

        bottom.getChildren().addAll(btnCancel, btnUbah);

        root.setBottom(bottom);

        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Ubah Kategori");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}