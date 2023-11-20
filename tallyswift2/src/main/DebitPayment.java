package main;

import java.util.ArrayList;

import data.DetailTransaksi;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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

public class DebitPayment extends Application{
	
	private ArrayList<DetailTransaksi> dataDetailTransaksi = new ArrayList<>();
	private Scene scene;
	private BorderPane sceneBorderPane;
	private GridPane footerGridPane, containerGridPane, pilihanMetodeGridPane;
	private StackPane headerStackPane, judulStackPane;
	private FlowPane headerFlowPane;
	private Label titleLabel, judulLabel;
	private Rectangle headerRectangle;
	private Font fontBoldItalic44, fontBold18, fontBold28;
	private Image logoImage, backImage, cimbImage, permataImage, bcaImage, briImage, mandiriImage;
	private ImageView logoImageView, backImageView, cimbImageView, permataImageView, bcaImageView, briImageView, mandiriImageView;
	private String titleColor = "#2D6936";
	private Button nextButton, footerPageTransaksiButton, footerPageMasterDataButton;
	private HBox backIconBox, backImageBox, cimbBox, mandiriBox, bcaBox, permataBox, briBox, nextBtnBox;
	private Integer totalHarga = 0;
	private RadioButton cimbRB, permataRB, bcaRB, briRB, mandiriRB;
	private ToggleGroup debitPaymentTG;
	
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
		fontBold18 = Font.font("Poppins", FontWeight.BOLD, 18);
		fontBold28 = Font.font("Poppins", FontWeight.BOLD, 28);
		
		headerRectangle = new Rectangle(0, 0, 1111, 80);
		
		logoImage = new Image("logo.png");
		logoImageView = new ImageView(logoImage);
		
		backImage = new Image("arrowBack.png");
		backImageView = new ImageView(backImage);
		
		cimbImage =  new Image("debitCIMB.png");
		cimbImageView = new ImageView(cimbImage);
		
		bcaImage =  new Image("debitBCA.png");
		bcaImageView = new ImageView(bcaImage);

		briImage =  new Image("debitBRI.png");
		briImageView = new ImageView(briImage);

		mandiriImage =  new Image("debitMandiri.png");
		mandiriImageView = new ImageView(mandiriImage);
		
		permataImage =  new Image("debitPermata.png");
		permataImageView = new ImageView(permataImage);		
		
		cimbRB = new RadioButton("CIMB NIAGA");
		bcaRB = new RadioButton("BCA");
		briRB = new RadioButton("BRI");
		mandiriRB = new RadioButton("Mandiri");
		permataRB = new RadioButton("Permata Bank");
		
		debitPaymentTG = new ToggleGroup();
		
		backIconBox = new HBox();
		backImageBox = new HBox();
		cimbBox =  new HBox();
		mandiriBox = new HBox();
		bcaBox = new HBox();
		permataBox = new HBox();
		briBox = new HBox();
		nextBtnBox = new HBox();
		
		nextButton = new Button("Next");
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
		containerGridPane.add(nextBtnBox, 0, 2);
		
		judulStackPane.getChildren().addAll(judulLabel, backIconBox);
		
		backIconBox.getChildren().add(backImageBox);
		backImageBox.getChildren().add(backImageView);
		
		pilihanMetodeGridPane.add(cimbBox, 0, 0);
		pilihanMetodeGridPane.add(bcaBox, 0, 1);
		pilihanMetodeGridPane.add(briBox, 0, 2);
		pilihanMetodeGridPane.add(mandiriBox, 1, 0);
		pilihanMetodeGridPane.add(permataBox, 1, 1);
		
		debitPaymentTG.getToggles().addAll(cimbRB, bcaRB, briRB, permataRB, mandiriRB);
		
		cimbBox.getChildren().addAll(cimbRB, cimbImageView);
		bcaBox.getChildren().addAll(bcaRB, bcaImageView);
		briBox.getChildren().addAll(briRB, briImageView);
		permataBox.getChildren().addAll(permataRB, permataImageView);
		mandiriBox.getChildren().addAll(mandiriRB, mandiriImageView);
		
		nextBtnBox.getChildren().add(nextButton);
	}
	
	private void styleContainer() {
		containerGridPane.setStyle("-fx-background-color: white");
		
		judulStackPane.setMinWidth(1111);
		judulStackPane.setPadding(new Insets(40, 40, 0, 40));
		
		judulLabel.setAlignment(Pos.CENTER);
		judulLabel.setFont(fontBold18);
		judulLabel.setTextFill(Color.web("#2D6936"));
		
		backIconBox.setAlignment(Pos.CENTER_LEFT);
		backImageBox.setMinSize(30, 30);
		backImageBox.setMaxSize(30, 30);
		backImageBox.setOnMouseEntered(e-> scene.setCursor(Cursor.HAND));
		backImageBox.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
		
		backImageView.setFitHeight(30);
		backImageView.setFitWidth(30);
		
		pilihanMetodeGridPane.setAlignment(Pos.CENTER);
		pilihanMetodeGridPane.setMinHeight(450);
//		pilihanMetodeGridPane.setStyle("-fx-background-color: red");
		pilihanMetodeGridPane.setVgap(35);
		pilihanMetodeGridPane.setHgap(60);
		
		cimbBox.setMinSize(400, 100);
		cimbBox.setMaxSize(400, 100);
		cimbBox.setAlignment(Pos.CENTER);
		cimbBox.setStyle("-fx-border-color: black; -fx-border-radius: 10;");
		cimbBox.setOnMouseEntered(e-> scene.setCursor(Cursor.HAND));
		cimbBox.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
		cimbBox.setOnMouseClicked(e -> {
			cimbRB.setSelected(true);
		});
		cimbRB.setMinWidth(280);
		cimbRB.setFont(fontBold18);
		cimbImageView.setFitWidth(75);
		cimbImageView.setFitHeight(53);
		
		bcaBox.setMinSize(400, 100);
		bcaBox.setMaxSize(400, 100);
		bcaBox.setAlignment(Pos.CENTER);
		bcaBox.setStyle("-fx-border-color: black; -fx-border-radius: 10;");
		bcaBox.setOnMouseEntered(e-> scene.setCursor(Cursor.HAND));
		bcaBox.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
		bcaBox.setOnMouseClicked(e -> {
			bcaRB.setSelected(true);
		});
		bcaRB.setMinWidth(280);
		bcaRB.setFont(fontBold18);
		bcaImageView.setFitWidth(75);
		bcaImageView.setFitHeight(53);
		
		briBox.setMinSize(400, 100);
		briBox.setMaxSize(400, 100);
		briBox.setAlignment(Pos.CENTER);
		briBox.setStyle("-fx-border-color: black; -fx-border-radius: 10;");
		briBox.setOnMouseEntered(e-> scene.setCursor(Cursor.HAND));
		briBox.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
		briBox.setOnMouseClicked(e -> {
			briRB.setSelected(true);
		});
		briRB.setMinWidth(280);
		briRB.setFont(fontBold18);
		briImageView.setFitWidth(75);
		briImageView.setFitHeight(53);
		
		mandiriBox.setMinSize(400, 100);
		mandiriBox.setMaxSize(400, 100);
		mandiriBox.setAlignment(Pos.CENTER);
		mandiriBox.setStyle("-fx-border-color: black; -fx-border-radius: 10;");
		mandiriBox.setOnMouseEntered(e-> scene.setCursor(Cursor.HAND));
		mandiriBox.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
		mandiriBox.setOnMouseClicked(e -> {
			mandiriRB.setSelected(true);
		});
		mandiriRB.setMinWidth(280);
		mandiriRB.setFont(fontBold18);
		mandiriImageView.setFitWidth(75);
		mandiriImageView.setFitHeight(53);

		permataBox.setMinSize(400, 100);
		permataBox.setMaxSize(400, 100);
		permataBox.setAlignment(Pos.CENTER);
		permataBox.setStyle("-fx-border-color: black; -fx-border-radius: 10;");
		permataBox.setOnMouseEntered(e-> scene.setCursor(Cursor.HAND));
		permataBox.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
		permataBox.setOnMouseClicked(e -> {
			permataRB.setSelected(true);
		});
		permataRB.setMinWidth(280);
		permataRB.setFont(fontBold18);
		permataImageView.setFitWidth(75);
		permataImageView.setFitHeight(53);
		
		nextBtnBox.setAlignment(Pos.CENTER);
		nextButton.setStyle("-fx-background-color: #E8F9F0; -fx-background-radius: 10;  -fx-border-color: black; -fx-border-radius: 10");
		nextButton.setMinSize(270, 50);
		nextButton.setFont(fontBold18);
		nextButton.setOnMouseEntered(e-> scene.setCursor(Cursor.HAND));
		nextButton.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
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
	
	
	public DebitPayment(Stage stage, ArrayList<DetailTransaksi> detailTransaksi, Integer grandtotal) {
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
	public void start(Stage debitPaymentPage) throws Exception {
		initialize();
		positioning();
		
		changePage(debitPaymentPage);
		
		debitPaymentPage.setScene(scene);
	}
	
	private void changePage(Stage stage) {
		footerPageMasterDataButton.setOnMouseClicked(e -> {
			new MasterData(stage);
		});
		backImageBox.setOnMouseClicked(e -> {
			new MetodePembayaran(stage, dataDetailTransaksi, totalHarga);
		});
		nextButton.setOnMouseClicked(e -> {
			RadioButton rb = (RadioButton) debitPaymentTG.getSelectedToggle();
			if (rb != null) {
				String rbVal = rb.getText();
				
				new TransaksiBerhasil(stage, dataDetailTransaksi, "Debit - " + rbVal, totalHarga);
			}
		});
	}

}
