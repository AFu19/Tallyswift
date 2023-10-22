package main;

import java.util.ArrayList;

import data.Barang;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
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

public class Main extends Application{
	
	private Scene scene1, scene2, scene3;
	private BorderPane bp;
	private GridPane gp;
	private StackPane sp;
	private FlowPane fp;
	private Label userIdLbl;
	private Label passwordLbl;
	private Label title;
	private Label signIn;
	private TextField userIdTF;
	private PasswordField passwordTF;
	private Button signInBtn;
	private Button continuebtn;
	private Button pageTransaksiBtn;
	private Button masterDataBtn;
	private Rectangle rectangle;
	private ArrayList<Barang> dataBarang = new ArrayList<>();
	private String titleColor = "#2D6936";
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void onboarding() {
		//pane set
		bp = new BorderPane();
		gp = new GridPane();
		sp = new StackPane();
		//end pane set
		
		//font set
		Font fontTitle = Font.font("Poppins", FontWeight.BOLD, FontPosture.ITALIC, 44);
		Font fontWelcome = Font.font("Poppins", FontWeight.BOLD, 32);		
		Font fontDesc = Font.font("Poppins", 22);
		Font fontBtn = Font.font("Poppins", FontWeight.BOLD, 28);
		//end font set
		
		//header
		title = new Label("E-Cashier");
		title.setFont(fontTitle);
		title.setTextFill(Color.web(titleColor));
		title.setPadding(new Insets(5, 0, 0, 0));
		
		rectangle = new Rectangle(0, 0, 1111, 80);
		rectangle.setFill(Color.web("#F4F2DE"));
		sp.getChildren().addAll(rectangle, title);
		
		bp.setTop(sp);
		bp.setAlignment(title, Pos.CENTER);
		//end header
		
		//body
		gp.setAlignment(Pos.CENTER);
		
		//left container
		Image img = new Image("onboard.png");
		ImageView imageView = new ImageView(img); 
		imageView.setFitWidth(596);
		imageView.setFitHeight(419);
		gp.setMargin(imageView, new Insets(0, 25, 0, 0));
		gp.add(imageView, 0, 0);
		//end left container

		//right container
		GridPane gp2 = new GridPane();
		gp2.setAlignment(Pos.CENTER);
		
		Label welcome = new Label("Welcome!!!");
		gp2.add(welcome, 0, 0);
		gp2.setHalignment(welcome, HPos.CENTER);
		welcome.setFont(fontWelcome);
		welcome.setTextFill(Color.web(titleColor));

		Label desc1 = new Label("Empowering Businesses with Transactions");
		gp2.add(desc1, 0, 1);
		gp2.setHalignment(desc1, HPos.CENTER);
		desc1.setFont(fontDesc);

		Label desc2 = new Label("and Effortless Inventory Management");
		gp2.add(desc2, 0, 2);
		gp2.setHalignment(desc2, HPos.CENTER);
		desc2.setFont(fontDesc);

		continuebtn = new Button("Continue");
		gp2.add(continuebtn, 0, 3);
		gp2.setHalignment(continuebtn, HPos.CENTER);
		continuebtn.setPrefWidth(311);
		continuebtn.setPrefHeight(52);
		continuebtn.setStyle("-fx-background-color: #D6F7E7; -fx-border-radius: 15px; -fx-border-color: black;");
		continuebtn.setFont(fontBtn);
		gp2.setMargin(continuebtn, new Insets(30, 0, 0, 0));
		continuebtn.setOnMouseEntered(e-> scene1.setCursor(Cursor.HAND));
		continuebtn.setOnMouseExited(e -> scene1.setCursor(Cursor.DEFAULT));

		gp.add(gp2, 1, 0);
		//end right container
		
		bp.setCenter(gp);
		//end body
		
		scene1 = new Scene(bp, 1111, 790);
	}
	
	private void signIn() {
		//pane set
		bp = new BorderPane();
		gp = new GridPane();
		sp = new StackPane();
		//end pane set
		
		//font set
		Font fontTitle = Font.font("Poppins", FontWeight.BOLD, FontPosture.ITALIC, 44);
		Font fontSignIn = Font.font("Poppins", FontWeight.BOLD, 52);
		Font fontLbl = Font.font("Poppins", 12);
		Font fontTF = Font.font("Poppins", 16);
		Font fontButton = Font.font("Poppins", FontWeight.BOLD, 18);
		//end font set
		
		//header
		title = new Label("E-Cashier");
		title.setFont(fontTitle);
		title.setTextFill(Color.web(titleColor));
		title.setPadding(new Insets(5, 0, 0, 0));

		rectangle = new Rectangle(0, 0, 1111, 80);
		rectangle.setFill(Color.web("#F4F2DE"));
		sp.getChildren().addAll(rectangle, title);
		
		bp.setTop(sp);
		bp.setAlignment(title, Pos.CENTER);
		//end header
		
		//body
		bp.setCenter(gp);
		bp.setAlignment(gp, Pos.CENTER);
		
		gp.setPadding(new Insets(70, 168, 0, 168));
		gp.setPrefWidth(775);

		signIn = new Label("Sign In");
		signIn.setFont(fontSignIn);
		signIn.setTextFill(Color.web(titleColor));
		gp.add(signIn, 0, 0);
		gp.setMargin(signIn, new Insets(0, 0, 85, 0));
		gp.setHalignment(signIn, HPos.CENTER);

		userIdLbl = new Label("UserID");
		userIdLbl.setFont(fontLbl);
		userIdLbl.setTextFill(Color.web(titleColor));
		gp.add(userIdLbl, 0, 1);

		userIdTF = new TextField();
		userIdTF.setPrefWidth(775);
		userIdTF.setPrefHeight(44);
		userIdTF.setFont(fontTF);
		gp.add(userIdTF, 0, 2);
		gp.setMargin(userIdTF, new Insets(0, 0, 58, 0));

		passwordLbl = new Label("Password");
		passwordLbl.setFont(fontLbl);
		passwordLbl.setTextFill(Color.web(titleColor));
		gp.add(passwordLbl, 0, 3);

		passwordTF = new PasswordField();
		passwordTF.setPrefWidth(775);
		passwordTF.setPrefHeight(44);
		passwordTF.setFont(fontTF);
		gp.add(passwordTF, 0, 4);
		
		signInBtn = new Button("Sign In");
		signInBtn.setPrefWidth(311);
		signInBtn.setPrefHeight(52);
		signInBtn.setStyle("-fx-background-color: #D6F7E7; -fx-border-radius: 15px; -fx-border-color: black;");		
		signInBtn.setFont(fontButton);
		signInBtn.setOnMouseEntered(e-> scene2.setCursor(Cursor.HAND));
		signInBtn.setOnMouseExited(e -> scene2.setCursor(Cursor.DEFAULT));
		bp.setAlignment(signInBtn, Pos.CENTER);
				
		bp.setBottom(signInBtn);
		bp.setMargin(signInBtn, new Insets(0, 0, 81, 0));
		//end body
		
		scene2 = new Scene(bp, 1111, 790);
	}
	
	private void transaksi() {
		//pane set
		bp = new BorderPane();
		gp = new GridPane();
		GridPane gp3 = new GridPane();
		GridPane gp2 = new GridPane();
		sp = new StackPane();
		fp = new FlowPane();
		FlowPane fp2 = new FlowPane();
		//end pane set
		
		//font set
		Font fontTitle = Font.font("Poppins", FontWeight.BOLD, FontPosture.ITALIC, 44);
		Font fontListBarang = Font.font("Poppins", 24);
		Font fontSearchTF = Font.font("Poppins", 18);
		Font fontBold36 = Font.font("Poppins", FontWeight.BOLD, 28);	
		//end font set
		
		//header
		title = new Label("E-Cashier");
		title.setFont(fontTitle);
		title.setTextFill(Color.web(titleColor));
		title.setPadding(new Insets(5, 0, 0, 0));
		
		Image img = new Image("logo.png");
		ImageView imageView = new ImageView(img);
		imageView.setFitWidth(70);
		imageView.setFitHeight(49);
		
		fp.getChildren().add(title);
		fp.getChildren().add(imageView);
		fp.setMargin(title, new Insets(0, 0, 0, 811));
		fp.setMargin(imageView, new Insets(13, 0, 0, 13));
		
		rectangle = new Rectangle(0, 0, 1111, 80);
		rectangle.setFill(Color.web("#F4F2DE"));
		sp.getChildren().addAll(rectangle, fp);
		
		bp.setTop(sp);
		bp.setAlignment(title, Pos.CENTER);
		//end header
		
		//body
		bp.setCenter(gp3);
		
		//left container
		//left container judul
		Label dataBaranglbl = new Label("Data Barang");
		dataBaranglbl.setFont(fontBold36);
		dataBaranglbl.setTextFill(Color.web(titleColor));
		gp3.add(dataBaranglbl, 0, 0);
		gp3.setMargin(dataBaranglbl, new Insets(15, 0, 0, 35));
		
		TextField searchTF = new TextField();
		searchTF.setFont(fontSearchTF);
		searchTF.setPrefWidth(400);
		searchTF.setPrefHeight(49);
		searchTF.setStyle("-fx-background-color: #FFFFFF; -fx-border-radius: 10px; -fx-border-color: black;");
		fp2.getChildren().add(searchTF);

		Image searchIcon = new Image("search.png");
		ImageView searchIconView = new ImageView(searchIcon);
		searchIconView.setFitHeight(30);
		searchIconView.setFitWidth(30);
		StackPane searchPane = new StackPane();
		Button searchBtn = new Button();
		searchBtn.setPrefHeight(49);
		searchBtn.setPrefWidth(60);
		searchBtn.setStyle("-fx-background-color: #FFFFFF; -fx-border-radius: 10px; -fx-border-color: black;");
		searchBtn.setOnMouseEntered(e-> scene3.setCursor(Cursor.HAND));
		searchBtn.setOnMouseExited(e -> scene3.setCursor(Cursor.DEFAULT));
		searchPane.getChildren().addAll(searchBtn, searchIconView);
		fp2.getChildren().add(searchPane);
		fp2.setHgap(6);
		
		gp3.add(fp2, 0, 1);
		gp3.setMargin(fp2, new Insets(16, 0, 0, 35));
		//end left container judul
		
		//left container produk
		Barang b = new Barang("BR001", "KG001", "Teh Gelas", 3000, 10);
		dataBarang.add(b);
		dataBarang.add(new Barang("BR002", "KG002", "Yakult", 6000, 10));
		dataBarang.add(new Barang("BR003", "KG001", "Momogi", 1000, 10));
		dataBarang.add(new Barang("BR004", "KG002", "Susu ultra milk", 3000, 10));
		dataBarang.add(new Barang("BR005", "KG001", "Indomie", 6000, 10));
		dataBarang.add(new Barang("BR006", "KG001", "Chitato", 6000, 10));
		dataBarang.add(new Barang("BR007", "KG002", "Aqua", 3000, 10));
		dataBarang.add(new Barang("BR008", "KG001", "Coca Cola", 3000, 10));
		dataBarang.add(new Barang("BR009", "KG002", "Chocolatos", 1500, 10));
		dataBarang.add(new Barang("BR010", "KG001", "Teh Pucuk", 1500, 10));
		
		ScrollPane scrollProduk = new ScrollPane();
		scrollProduk.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollProduk.setVbarPolicy(ScrollBarPolicy.NEVER);
		scrollProduk.setStyle("-fx-background-color: #FFFFFF; -fx-border-radius: 10px 10px 0 0; -fx-border-color: black;");
		scrollProduk.setPadding(new Insets(15));
		
		GridPane listProduk = new GridPane();
		listProduk.setVgap(10);

		int idx = 0;
		for (Barang barang : dataBarang) {
			FlowPane gridNamaHarga = new FlowPane();
			gridNamaHarga.setPrefWidth(436); 
			gridNamaHarga.setPrefHeight(66);
			gridNamaHarga.setPadding(new Insets(0, 5, 0, 15));
			
			Label namaProduk = new Label(barang.getNamaBarang());
			namaProduk.setFont(fontListBarang);
			HBox hbb = new HBox();
			hbb.setPrefWidth(320);
			hbb.setPrefHeight(66);
			hbb.getChildren().add(namaProduk);
			gridNamaHarga.getChildren().add(hbb);
			hbb.setAlignment(Pos.CENTER_LEFT);
			
			Label hargaProduk = new Label("Rp" + barang.getHargaSatuan());			
			hargaProduk.setFont(fontListBarang);
			HBox hb = new HBox();
			hb.setPrefWidth(85);
			hb.setPrefHeight(66);
			hb.getChildren().add(hargaProduk);
			gridNamaHarga.getChildren().add(hb);
			hb.setAlignment(Pos.CENTER_RIGHT);
			
			Button produkBtn = new Button();
			produkBtn.setFont(fontListBarang);
			produkBtn.setPrefWidth(436); 
			produkBtn.setPrefHeight(66);
			produkBtn.setStyle("-fx-background-color: #FFFFFF; -fx-border-radius: 10px; -fx-border-color: black;");
			
			StackPane stackListProduk = new StackPane();			
			stackListProduk.getChildren().addAll(produkBtn, gridNamaHarga);
			stackListProduk.setOnMouseEntered(e-> scene3.setCursor(Cursor.HAND));
			stackListProduk.setOnMouseExited(e -> scene3.setCursor(Cursor.DEFAULT));
			stackListProduk.setOnMouseClicked(e -> System.out.println(barang.getNamaBarang()));

			listProduk.add(stackListProduk, 0, idx);
			idx++;
		}
		
		scrollProduk.setContent(listProduk);
		
		gp3.add(scrollProduk, 0, 2);
		gp3.setMargin(scrollProduk, new Insets(22, 0, 0, 35));
		//end left container produk
		//end left container
		
		//end body
		
		//footer
		gp2.setPrefWidth(1110);
		pageTransaksiBtn = new Button("Transaksi Pembelian");
		pageTransaksiBtn.setPrefWidth(555);
		pageTransaksiBtn.setPrefHeight(80);
		pageTransaksiBtn.setFont(fontBold36);
		pageTransaksiBtn.setTextFill(Color.web("#004F38"));
		pageTransaksiBtn.setStyle("-fx-background-color: #F4F2DE; -fx-border-color: black black transparent transparent;");
		pageTransaksiBtn.setOnMouseEntered(e-> scene3.setCursor(Cursor.HAND));
		pageTransaksiBtn.setOnMouseExited(e -> scene3.setCursor(Cursor.DEFAULT));
		
		masterDataBtn = new Button("Master Data");
		masterDataBtn.setPrefWidth(555);
		masterDataBtn.setPrefHeight(80);
		masterDataBtn.setFont(fontBold36);
		masterDataBtn.setTextFill(Color.web("#004F38"));
		masterDataBtn.setStyle("-fx-background-color: #FCFCFC; -fx-border-color: black black transparent transparent;");
		masterDataBtn.setOnMouseEntered(e-> scene3.setCursor(Cursor.HAND));
		masterDataBtn.setOnMouseExited(e -> scene3.setCursor(Cursor.DEFAULT));
		
		gp2.add(pageTransaksiBtn, 0, 0);
		gp2.add(masterDataBtn, 1, 0);
		bp.setBottom(gp2);
		//end footer
		
		scene3 = new Scene(bp, 1111, 790);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		onboarding();
		continuebtn.setOnMouseClicked(e -> primaryStage.setScene(scene2));
		
		signIn();
		signInBtn.setOnMouseClicked(e -> {
			String userID = userIdTF.getText();
			String password = passwordTF.getText();
			System.out.println(userID);
			System.out.println(password);
		});
		
		transaksi();
		
		primaryStage.setScene(scene3);
		primaryStage.setResizable(false);
		primaryStage.setMaximized(false);
		primaryStage.show();
	}

}
