package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import data.DetailTransaksi;
import javafx.application.Application;
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

public class TransaksiBerhasil extends Application{

	private Connect connect = Connect.getInstance();
	private ArrayList<DetailTransaksi> dataDetailTransaksi = new ArrayList<>();
	private Scene scene;
	private BorderPane sceneBorderPane;
	private GridPane containerGridPane, totalGridPane;
	private StackPane headerStackPane;
	private FlowPane headerFlowPane;
	private Label titleLabel, trTitleLabel, produkTitleLabel, qtyTitleLabel, hargaTitleLabel, subtotalLabel, ppnLabel, totalLabel, pembayaranLabel, kembalianLabel, subtotalRpLabel, ppnRpLabel, totalRpLabel, pembayaranRpLabel, kembalianRpLabel, angkaSubtotalLabel, angkaPPNLabel, angkaGrandtotalLabel, angkaNominalLabel, angkaKembaliLabel;
	private Rectangle headerRectangle;
	private Font fontBoldItalic44, fontBold18, fontBold28, fontReg18;
	private Image logoImage;
	private ImageView logoImageView;
	private String titleColor = "#2D6936", metodePembayaran = "Cash";
	private HBox trTitleBox, tableTitleBox, selesaiButtonBox;
	private ListView<HBox> produkListView;
	private Integer lastId, subtotal = 0, ppnInt = 0, grandtotal = 0, nominal = 0, nominalKembali = 0;
	private double ppn = 0;
	private Button selesaiButton;
	
	private void getLastId() {
		lastId = 0;
		String tempId = "";
		
		String query = "SELECT * FROM transactionheader";
		connect.rs = connect.execQuery(query);
		
		try {
			while (connect.rs.next()) {
				tempId = connect.rs.getString("TransactionID");
				lastId = Integer.valueOf(tempId.substring(2, 5)) + 1;
			}
		} catch (Exception e) {}
		
	}
	
	private void addTransactionHeader(String trId, String trDate, String paymentMethod) {
		String query = "INSERT INTO transactionheader "
				+ "VALUES('" + trId + "', '" + trDate + "', '" + paymentMethod + "')";
		connect.execUpdate(query);
	}
	
	private void addTransactionDetail(String trId, String prodId, Integer qty) {
		String query = "INSERT INTO transactiondetail "
				+ "VALUES('" + trId + "', '" + prodId + "', " + qty + ")";
		connect.execUpdate(query);
	}
	
	private void updateStock(Integer qty, String prodId) {
		String query = "UPDATE product SET product.Stock = product.Stock - " + qty + " WHERE ProductID = '" + prodId +"'";
		connect.execUpdate(query);
	}
	
	private void initialize() {
		//Dummy data ------------------------------------------------------------------
//		dataDetailTransaksi.add(new DetailTransaksi("PR790", "Sarimi isi 2", 13, 10000));
//		dataDetailTransaksi.add(new DetailTransaksi("PR791", "Sarimi isi 3", 31, 15000));
//		dataDetailTransaksi.add(new DetailTransaksi("PR792", "Sarimi isi 4", 32, 5000));
//		dataDetailTransaksi.add(new DetailTransaksi("PR793", "Sarimi isi 5", 23, 20000));
//		dataDetailTransaksi.add(new DetailTransaksi("PR794", "Sarimi isi 6", 34, 32000));
//		dataDetailTransaksi.add(new DetailTransaksi("PR795", "Sarimi isi 7", 63, 52000));
//		dataDetailTransaksi.add(new DetailTransaksi("PR796", "Sarimi isi 8", 13, 19000));
//		dataDetailTransaksi.add(new DetailTransaksi("PR797", "Sarimi isi 9", 36, 11000));
//		dataDetailTransaksi.add(new DetailTransaksi("PR798", "Sarimi isi 10", 33, 2000));
//		dataDetailTransaksi.add(new DetailTransaksi("PR799", "Sarimi isi 12", 37, 22000));
		
		for (DetailTransaksi detailTransaksi : dataDetailTransaksi) {
			subtotal += detailTransaksi.getHargaItem();
		}
		ppn = subtotal * 0.1;
		ppnInt = (int) ppn;
		grandtotal = subtotal + ppnInt;
		nominalKembali = nominal - grandtotal;
		
		sceneBorderPane = new BorderPane();
		
		containerGridPane = new GridPane();
		totalGridPane = new GridPane();
		
		headerFlowPane = new FlowPane();
		
		headerStackPane = new StackPane();
		
		titleLabel = new Label("E-Cashier");
		trTitleLabel = new Label("Transaksi Berhasil");
		produkTitleLabel = new Label("Produk");
		qtyTitleLabel = new Label("Qty");
		hargaTitleLabel = new Label("Harga");
		subtotalLabel = new Label("Subtotal");
		ppnLabel = new Label("PPN (10%)");
		totalLabel = new Label("Total");
		pembayaranLabel = new Label("Pembayaran: " + metodePembayaran);
		kembalianLabel = new Label("Kembalian: ");
		subtotalRpLabel = new Label("Rp");
		ppnRpLabel = new Label("Rp");
		totalRpLabel = new Label("Rp");
		pembayaranRpLabel = new Label("Rp");
		kembalianRpLabel = new Label("Rp");
		angkaSubtotalLabel = new Label(subtotal.toString());
		angkaPPNLabel = new Label(ppnInt.toString());
		angkaGrandtotalLabel = new Label(grandtotal.toString());
		angkaNominalLabel = new Label(nominal.toString());
		angkaKembaliLabel = new Label(nominalKembali.toString());
		
		fontBoldItalic44 = Font.font("Poppins", FontWeight.BOLD, FontPosture.ITALIC, 44);
		fontBold18 = Font.font("Poppins", FontWeight.BOLD, 18);
		fontBold28 = Font.font("Poppins", FontWeight.BOLD, 28);
		fontReg18 = Font.font("Poppins", 18);
		
		headerRectangle = new Rectangle(0, 0, 1111, 80);
		
		logoImage = new Image("logo.png");
		logoImageView = new ImageView(logoImage);
		
		trTitleBox = new HBox();
		tableTitleBox = new HBox();
		selesaiButtonBox = new HBox();
		
		selesaiButton = new Button("Selesai");
		
		produkListView = new ListView<>();
		
		scene = new Scene(sceneBorderPane, 1111, 790);
	}
	
	
	private void positioning() {
		initializeHeader();
		initializeContainer();
		
		sceneBorderPane.setTop(headerStackPane);
		sceneBorderPane.setCenter(containerGridPane);
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
		
		containerGridPane.add(trTitleBox, 0, 0);
		containerGridPane.add(tableTitleBox, 0, 1);
		containerGridPane.add(produkListView, 0, 2);
		containerGridPane.add(totalGridPane, 0, 3);
		containerGridPane.add(selesaiButtonBox, 0, 4);
		
		trTitleBox.getChildren().add(trTitleLabel);
		
		tableTitleBox.getChildren().addAll(produkTitleLabel, qtyTitleLabel, hargaTitleLabel);
		
		if (!dataDetailTransaksi.isEmpty()) {
			for (DetailTransaksi detailTransaksi : dataDetailTransaksi) {
				HBox tempBox;
				Label prodNamaLabel, prodQtyLabel, rpLabel, prodHargaLabel;
				
				tempBox = new HBox();
				prodNamaLabel = new Label(detailTransaksi.getProductName());
				prodQtyLabel = new Label(detailTransaksi.getQuantity().toString());
				rpLabel = new Label("Rp");
				prodHargaLabel = new Label(detailTransaksi.getHargaItem().toString());
				
				tempBox.getChildren().addAll(prodNamaLabel, prodQtyLabel, rpLabel, prodHargaLabel);
				produkListView.getItems().add(tempBox);
				
				tempBox.setPadding(new Insets(0, 20, 0, 16));
				
				prodNamaLabel.setMinWidth(500);
				prodNamaLabel.setFont(fontReg18);
				prodNamaLabel.setAlignment(Pos.CENTER_LEFT);
				
				prodQtyLabel.setMinWidth(300);
				prodQtyLabel.setAlignment(Pos.CENTER);
				prodQtyLabel.setFont(fontReg18);
				
				rpLabel.setMinWidth(50);
				rpLabel.setFont(fontReg18);
				
				prodHargaLabel.setMinWidth(130);
				prodHargaLabel.setAlignment(Pos.CENTER_RIGHT);
				prodHargaLabel.setFont(fontReg18);
			}
		}
		
		totalGridPane.add(subtotalLabel, 0, 0);
		totalGridPane.add(subtotalRpLabel, 1, 0);
		totalGridPane.add(angkaSubtotalLabel, 2, 0);
		
		totalGridPane.add(ppnLabel, 0, 1);
		totalGridPane.add(ppnRpLabel, 1, 1);
		totalGridPane.add(angkaPPNLabel, 2, 1);
		
		totalGridPane.add(totalLabel, 0, 2);
		totalGridPane.add(totalRpLabel, 1, 2);
		totalGridPane.add(angkaGrandtotalLabel, 2, 2);
		
		totalGridPane.add(pembayaranLabel, 0, 3);
		totalGridPane.add(pembayaranRpLabel, 1, 3);
		totalGridPane.add(angkaNominalLabel, 2, 3);
		
		totalGridPane.add(kembalianLabel, 0, 4);
		totalGridPane.add(kembalianRpLabel, 1, 4);
		totalGridPane.add(angkaKembaliLabel, 2, 4);
		
		selesaiButtonBox.getChildren().add(selesaiButton);
	}
	
	private void styleContainer() {
		containerGridPane.setStyle("-fx-background-color: white");
		
		trTitleBox.setAlignment(Pos.CENTER);
		trTitleBox.setPadding(new Insets(16, 0, 0, 0));
		trTitleBox.setMinWidth(1111);
		trTitleLabel.setFont(fontBold28);
		
		tableTitleBox.setPadding(new Insets(20, 20, 10, 24));
		
		produkTitleLabel.setMinWidth(500);
		produkTitleLabel.setFont(fontBold18);
		
		qtyTitleLabel.setMinWidth(300);
		qtyTitleLabel.setAlignment(Pos.CENTER);
		qtyTitleLabel.setFont(fontBold18);
		
		hargaTitleLabel.setMinWidth(180);
		hargaTitleLabel.setAlignment(Pos.CENTER);
		hargaTitleLabel.setFont(fontBold18);
		
		produkListView.setMaxHeight(250);
		
		totalGridPane.setPadding(new Insets(20, 20, 10, 24));

		subtotalLabel.setMinWidth(800);
		subtotalLabel.setFont(fontReg18);
		subtotalLabel.setAlignment(Pos.CENTER_LEFT);
		
		subtotalRpLabel.setMinWidth(50);
		subtotalRpLabel.setFont(fontReg18);
		subtotalRpLabel.setAlignment(Pos.CENTER_LEFT);
		
		angkaSubtotalLabel.setMinWidth(130);
		angkaSubtotalLabel.setFont(fontReg18);
		angkaSubtotalLabel.setAlignment(Pos.CENTER_RIGHT);
		
		ppnLabel.setMinWidth(800);
		ppnLabel.setFont(fontReg18);
		ppnLabel.setAlignment(Pos.CENTER_LEFT);
		ppnLabel.setTextFill(Color.web("#BFBFBF"));
		
		ppnRpLabel.setMinWidth(50);
		ppnRpLabel.setFont(fontReg18);
		ppnRpLabel.setAlignment(Pos.CENTER_LEFT);
		ppnRpLabel.setTextFill(Color.web("#BFBFBF"));
		
		angkaPPNLabel.setMinWidth(130);
		angkaPPNLabel.setFont(fontReg18);
		angkaPPNLabel.setAlignment(Pos.CENTER_RIGHT);
		angkaPPNLabel.setTextFill(Color.web("#BFBFBF"));
		
		totalLabel.setMinWidth(800);
		totalLabel.setFont(fontReg18);
		totalLabel.setAlignment(Pos.CENTER_LEFT);
		
		totalRpLabel.setMinWidth(50);
		totalRpLabel.setFont(fontReg18);
		totalRpLabel.setAlignment(Pos.CENTER_LEFT);
		
		angkaGrandtotalLabel.setMinWidth(130);
		angkaGrandtotalLabel.setFont(fontReg18);
		angkaGrandtotalLabel.setAlignment(Pos.CENTER_RIGHT);
				
		pembayaranLabel.setMinWidth(800);
		pembayaranLabel.setFont(fontReg18);
		pembayaranLabel.setAlignment(Pos.CENTER_LEFT);
		pembayaranLabel.setStyle("-fx-border-color: transparent transparent black transparent");
		pembayaranLabel.setPadding(new Insets(0, 0, 10, 0));
		totalGridPane.setMargin(pembayaranLabel, new Insets(20, 0, 0, 0));
		
		pembayaranRpLabel.setMinWidth(50);
		pembayaranRpLabel.setFont(fontReg18);
		pembayaranRpLabel.setAlignment(Pos.CENTER_LEFT);
		pembayaranRpLabel.setStyle("-fx-border-color: transparent transparent black transparent");
		pembayaranRpLabel.setPadding(new Insets(0, 0, 10, 0));
		totalGridPane.setMargin(pembayaranRpLabel, new Insets(20, 0, 0, 0));
		
		angkaNominalLabel.setMinWidth(130);
		angkaNominalLabel.setFont(fontReg18);
		angkaNominalLabel.setAlignment(Pos.CENTER_RIGHT);
		angkaNominalLabel.setStyle("-fx-border-color: transparent transparent black transparent");
		angkaNominalLabel.setPadding(new Insets(0, 0, 10, 0));
		totalGridPane.setMargin(angkaNominalLabel, new Insets(20, 0, 0, 0));
		
		kembalianLabel.setMinWidth(800);
		kembalianLabel.setFont(fontBold18);
		kembalianLabel.setAlignment(Pos.CENTER_LEFT);
		totalGridPane.setMargin(kembalianLabel, new Insets(10, 0, 0, 0));
		
		kembalianRpLabel.setMinWidth(50);
		kembalianRpLabel.setFont(fontBold18);
		kembalianRpLabel.setAlignment(Pos.CENTER_LEFT);
		totalGridPane.setMargin(kembalianRpLabel, new Insets(10, 0, 0, 0));
		
		angkaKembaliLabel.setMinWidth(130);
		angkaKembaliLabel.setFont(fontBold18);
		angkaKembaliLabel.setAlignment(Pos.CENTER_RIGHT);
		totalGridPane.setMargin(angkaKembaliLabel, new Insets(10, 0, 0, 0));
		
		selesaiButtonBox.setAlignment(Pos.CENTER);
		selesaiButtonBox.setMinHeight(90);
		selesaiButton.setStyle("-fx-background-color: #D6F7E7; -fx-background-radius: 10px; -fx-border-color: black; -fx-border-radius: 10;");
		selesaiButton.setFont(fontBold18);
		selesaiButton.setMinSize(240, 60);
		selesaiButton.setOnMouseEntered(e -> scene.setCursor(Cursor.HAND));
		selesaiButton.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
	}

	public TransaksiBerhasil(Stage stage, ArrayList<DetailTransaksi> detailTransaksi, String pembayaran, Integer inputNominal) {
		try {
			for (DetailTransaksi detailTransaksi2 : detailTransaksi) {
				dataDetailTransaksi.add(detailTransaksi2);
			}
			metodePembayaran = pembayaran;
			nominal = inputNominal;
			this.start(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage transaksiBerhasilPage) throws Exception {
		initialize();
		positioning();
		selesaiButton.requestFocus();
		
		selesaiTransaksi(transaksiBerhasilPage);
		
		transaksiBerhasilPage.setScene(scene);
		transaksiBerhasilPage.show();
	}

	private void selesaiTransaksi(Stage stage) {
		selesaiButton.setOnMouseClicked(e -> {
			getLastId();
			
			String idTransaksi = "";
			
			if (lastId == 0) {
				idTransaksi = "TR001";
			}else if (lastId < 10) {
				idTransaksi = "TR00" + lastId;
			}else if (lastId < 100) {
				idTransaksi = "TR0" + lastId;
			}else if (lastId < 1000) {
				idTransaksi = "TR" + lastId;
			}
			
			Date currentDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentDateTime = dateFormat. format(currentDate);
			
			addTransactionHeader(idTransaksi, currentDateTime, metodePembayaran);
			
			for (DetailTransaksi detailTransaksi : dataDetailTransaksi) {
				addTransactionDetail(idTransaksi, detailTransaksi.getProductID(), detailTransaksi.getQuantity());
				updateStock(detailTransaksi.getQuantity(), detailTransaksi.getProductID());
			}
			
			new Transaction(stage);
		});
	}
	
}
