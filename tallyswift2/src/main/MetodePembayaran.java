package main;

import java.util.ArrayList;
import java.util.List;

import data.DetailTransaksi;
import data.Kategori;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class MetodePembayaran extends Application{

	private ArrayList<DetailTransaksi> dataDetailTransaksi = new ArrayList<>();
	private Scene scene;
	private BorderPane sceneBorderPane;
	private GridPane footerGridPane, containerGridPane, pilihanMetodeGridPane;
	private StackPane headerStackPane, judulStackPane;
	private FlowPane headerFlowPane;
	private Label titleLabel, judulLabel;
	private Rectangle headerRectangle;
	private Font fontBoldItalic44, fontBold28;
	private Image logoImage, backImage, cashImage, debitImage;
	private ImageView logoImageView, backImageView, cashImageView, debitImageView;
	private String titleColor = "#2D6936";
	private Button footerPageTransaksiButton, footerPageMasterDataButton;
	private HBox backIconBox, backImageBox, cashImageBox, debitImageBox;
	private Integer totalHarga = 0;
	
	private void initialize() {
		sceneBorderPane = new BorderPane();
		
		containerGridPane = new GridPane();
		pilihanMetodeGridPane = new GridPane();
		footerGridPane = new GridPane();
		
		headerFlowPane = new FlowPane();
		
		headerStackPane = new StackPane();
		judulStackPane = new StackPane();
		
		titleLabel = new Label("E-Cashier");
		judulLabel= new Label("Pilih salah satu metode pembayaran :");
		
		fontBoldItalic44 = Font.font("Poppins", FontWeight.BOLD, FontPosture.ITALIC, 44);
		fontBold28 = Font.font("Poppins", FontWeight.BOLD, 28);
		
		headerRectangle = new Rectangle(0, 0, 1111, 80);
		
		logoImage = new Image("logo.png");
		logoImageView = new ImageView(logoImage);
		
		backImage = new Image("arrowBack.png");
		backImageView = new ImageView(backImage);
		
		cashImage = new Image("cash.png");
		cashImageView = new ImageView(cashImage);
		
		debitImage = new Image("debit.png");
		debitImageView = new ImageView(debitImage);
		
		backIconBox = new HBox();
		backImageBox = new HBox();
		cashImageBox = new HBox();
		debitImageBox = new HBox();
		
		footerPageTransaksiButton = new Button("Transaksi Pembelian");
		footerPageMasterDataButton = new Button("Master Data");
		
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
		styleContainer();
		
		containerGridPane.add(judulStackPane, 0, 0);
		containerGridPane.add(pilihanMetodeGridPane, 0, 1);
		
		judulStackPane.getChildren().addAll(judulLabel, backIconBox);
		
		backIconBox.getChildren().add(backImageBox);
		backImageBox.getChildren().add(backImageView);
		
		pilihanMetodeGridPane.add(cashImageBox, 0, 0);
		pilihanMetodeGridPane.add(debitImageBox, 1, 0);
		
		cashImageBox.getChildren().add(cashImageView);
		debitImageBox.getChildren().add(debitImageView);
	}
	
	private void styleContainer() {
		containerGridPane.setStyle("-fx-background-color: white");
		
		judulStackPane.setMinWidth(1111);
		judulStackPane.setPadding(new Insets(40, 40, 0, 40));
		
		judulLabel.setAlignment(Pos.CENTER);
		judulLabel.setFont(fontBold28);
		judulLabel.setTextFill(Color.web("#2D6936"));
		
		backIconBox.setAlignment(Pos.CENTER_LEFT);
		backImageBox.setMinSize(30, 30);
		backImageBox.setMaxSize(30, 30);
		backImageBox.setOnMouseEntered(e-> scene.setCursor(Cursor.HAND));
		backImageBox.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
		
		backImageView.setFitHeight(30);
		backImageView.setFitWidth(30);
		
		pilihanMetodeGridPane.setPadding(new Insets(70, 0, 0, 0));
		pilihanMetodeGridPane.setAlignment(Pos.CENTER);
		pilihanMetodeGridPane.setHgap(210);
		
		cashImageBox.setStyle("-fx-border-color: black; -fx-border-radius: 10;");
		cashImageBox.setOnMouseEntered(e-> scene.setCursor(Cursor.HAND));
		cashImageBox.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
		cashImageBox.setOnMouseClicked(e -> {
			System.out.println("Cash");
		});
		
		debitImageBox.setStyle("-fx-border-color: black; -fx-border-radius: 10;");
		debitImageBox.setOnMouseEntered(e-> scene.setCursor(Cursor.HAND));
		debitImageBox.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
		debitImageBox.setOnMouseClicked(e -> {	
			System.out.println("Debit");
		});
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
	
	public MetodePembayaran(Stage stage, ArrayList<DetailTransaksi> detailTransaksi, Integer grandtotal) {
		try {
			for (DetailTransaksi detailTransaksi2 : detailTransaksi) {
				dataDetailTransaksi.add(detailTransaksi2);
			}
			totalHarga = grandtotal;
			this.start(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage metodePembayaranPage) throws Exception {
		initialize();
		positioning();
		
		changePage(metodePembayaranPage);
		popUpCash(metodePembayaranPage);
		
		metodePembayaranPage.setScene(scene);
	}
	
	private void changePage(Stage stage) {
		footerPageMasterDataButton.setOnMouseClicked(e -> {
			new MasterData(stage);
		});
		backImageBox.setOnMouseClicked(e -> {
			new Transaction(stage);
		});
		debitImageBox.setOnMouseClicked(e -> {
			new DebitPayment(stage, dataDetailTransaksi, totalHarga);
		});
	}
	
	private void popUpCash(Stage stage) {
		cashImageBox.setOnMouseClicked(e -> {
			//Make
			Stage popupStage;
			Scene popupScene;
			GridPane popupPane;
			Label notifLabel, grandtotalLabel, nominalKurangLabel;
			TextField nominalTF;
			HBox buttonBox;
			Button nextButton;
			
			BorderPane popUpBorderPane;
			
			Label trBerhasilLabel;
			GridPane tabelGridPane;

			//Initialize
			popupStage = new Stage();
			popUpBorderPane = new BorderPane();
			popupPane = new GridPane();
			notifLabel = new Label("Masukkan nominal yang dibayarkan:");
			grandtotalLabel = new Label("Grandtotal : Rp" + totalHarga);
			nominalKurangLabel = new Label();
			nominalTF = new TextField();
			buttonBox = new HBox();
			nextButton = new Button("Next");
			
			popupScene = new Scene(popUpBorderPane, 480, 300);
			
			trBerhasilLabel = new Label("Transaksi Berhasil!");
			tabelGridPane = new GridPane();
			
			//Position
			popupStage.initModality(Modality.APPLICATION_MODAL);
			popupStage.setScene(popupScene);
			
			popUpBorderPane.setCenter(popupPane);
			
			popupPane.add(notifLabel, 0, 0);
			popupPane.add(grandtotalLabel, 0, 1);
			popupPane.add(nominalTF, 0, 2);
			popupPane.add(nominalKurangLabel, 0, 3);
			popupPane.add(buttonBox, 0, 4);
			
			buttonBox.getChildren().add(nextButton);
			
			
			//Style
			notifLabel.setFont(Font.font("Poppins", FontWeight.BOLD, 18));
			notifLabel.setMinWidth(400);
			notifLabel.setAlignment(Pos.CENTER);
			
			grandtotalLabel.setFont(Font.font("Poppins", 18));
			grandtotalLabel.setMinWidth(400);
			grandtotalLabel.setAlignment(Pos.CENTER);
			
			nominalTF.setMinSize(400, 30);
			
			buttonBox.setAlignment(Pos.CENTER);
			nextButton.setStyle("-fx-background-color: #E8F9F0; -fx-background-radius: 10px; -fx-border-color: black; -fx-border-radius: 10px;");
			nextButton.setFont(Font.font("Poppins", FontWeight.BOLD, 24));
			nextButton.setMinSize(200, 40);
			popupPane.setMargin(buttonBox, new Insets(0, 0, 50, 0));				

			popupPane.setAlignment(Pos.CENTER);
			popupPane.setPadding(new Insets(20, 0, 0, 0));
			popupPane.setMargin(grandtotalLabel, new Insets(0, 0, 20, 0));
			popupPane.setMargin(buttonBox, new Insets(20, 0, 0, 0));
			
			popupStage.setResizable(false);
			popupStage.setMaximized(false);
			
			//Event handler
			nominalTF.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*")) {
						nominalTF.setText(newValue.replaceAll("[^\\d]", ""));
					}
				}
			});
			
			nextButton.setOnMouseEntered(event -> popupScene.setCursor(Cursor.HAND));
			nextButton.setOnMouseExited(event -> popupScene.setCursor(Cursor.DEFAULT));
			
			nextButton.setOnMouseClicked(event -> {
				if (nominalTF.getText().isEmpty()) {
					nominalKurangLabel.setText("Isi nominal!");
					nominalKurangLabel.setTextFill(Color.RED);
				}else if (Integer.valueOf(nominalTF.getText()) < totalHarga) {
					nominalKurangLabel.setText("Nominal kurang dari total harga");
					nominalKurangLabel.setTextFill(Color.RED);
				}else {
					popupStage.close();
					new TransaksiBerhasil(stage, dataDetailTransaksi, "Cash", Integer.valueOf(nominalTF.getText()));
				}
				
			});
			
			popupStage.setOnCloseRequest(event -> {
				new MetodePembayaran(stage, dataDetailTransaksi, totalHarga);
			});
			
			popupStage.showAndWait();
		});
	}

}
