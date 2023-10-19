package main;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application{
	
	private Scene scene1, scene2;
	private BorderPane bp;
	private GridPane gp;
	private StackPane sp;
	private Label userIdLbl;
	private Label passwordLbl;
	private Label title;
	private Label signIn;
	private TextField userIdTF;
	private PasswordField passwordTF;
	private Button signInBtn;
	Button continuebtn;
	private Rectangle rectangle;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void onboarding() {
		bp = new BorderPane();
		gp = new GridPane();
		sp = new StackPane();
		title = new Label("E-Cashier");
		Font fontTitle = Font.font("Poppins", FontWeight.BOLD, FontPosture.ITALIC, 52);
		title.setFont(fontTitle);
		title.setTextFill(Color.web("#2D6936"));
		
		rectangle = new Rectangle(0, 0, 1111, 80);
		rectangle.setFill(Color.web("#F4F2DE"));
		sp.getChildren().addAll(rectangle, title);
		
		bp.setTop(sp);
		bp.setAlignment(title, Pos.CENTER);
		Label welcome = new Label("Welcome!!!");
		Label desc1 = new Label("Empowering Businesses with Transactions");
		Label desc2 = new Label("and Effortless Inventory Management");
		continuebtn = new Button("Continue");
		
		bp.setCenter(gp);
		
		gp.setAlignment(Pos.CENTER);
		
		Image img = new Image("onboard.png");
		
		ImageView imageView = new ImageView(img); 
		
		gp.add(imageView, 0, 0);
		
		GridPane gp2 = new GridPane();
		gp2.add(welcome, 0, 0);
		gp2.add(desc1, 0, 1);
		gp2.add(desc2, 0, 2);
		gp2.add(continuebtn, 0, 3);
		gp.add(gp2, 1, 0);
		
		imageView.setFitWidth(596);
		imageView.setFitHeight(419);
		gp.setMargin(imageView, new Insets(0, 25, 0, 0));
		gp2.setAlignment(Pos.CENTER);
		gp2.setHalignment(welcome, HPos.CENTER);
		gp2.setHalignment(desc1, HPos.CENTER);
		gp2.setHalignment(desc2, HPos.CENTER);
		gp2.setHalignment(continuebtn, HPos.CENTER);
		Font fontWelcome = Font.font("Poppins", FontWeight.BOLD, 32);
		welcome.setFont(fontWelcome);
		welcome.setTextFill(Color.web("#2D6936"));
		Font fontDesc = Font.font("Poppins", 22);
		desc1.setFont(fontDesc);
		desc2.setFont(fontDesc);
		continuebtn.setPrefWidth(311);
		continuebtn.setPrefHeight(52);
		continuebtn.setStyle("-fx-background-color: #D6F7E7; -fx-border-radius: 15px; -fx-border-color: black;");
		Font fontBtn = Font.font("Poppins", FontWeight.BOLD, 28);
		continuebtn.setFont(fontBtn);
		gp2.setMargin(continuebtn, new Insets(30, 0, 0, 0));
		continuebtn.setOnMouseEntered(e-> scene1.setCursor(Cursor.HAND));
		continuebtn.setOnMouseExited(e -> scene1.setCursor(Cursor.DEFAULT));
		
		scene1 = new Scene(bp, 1111, 790);
	}
	
	private void signIn() {
		bp = new BorderPane();
		gp = new GridPane();
		sp = new StackPane();
		userIdLbl = new Label("UserID");
		passwordLbl = new Label("Password");
		title = new Label("E-Cashier");
		Font fontTitle = Font.font("Poppins", FontWeight.BOLD, FontPosture.ITALIC, 52);
		title.setFont(fontTitle);
		title.setTextFill(Color.web("#2D6936"));
		
		signIn = new Label("Sign In");
		Font fontSignIn = Font.font("Poppins", FontWeight.BOLD, 52);
		signIn.setFont(fontSignIn);
		signIn.setTextFill(Color.web("#2D6936"));
		
		userIdTF = new TextField();
		passwordTF = new PasswordField();
		
		signInBtn = new Button("Sign In");
		
		rectangle = new Rectangle(0, 0, 1111, 80);
		rectangle.setFill(Color.web("#F4F2DE"));
		sp.getChildren().addAll(rectangle, title);
		
		bp.setTop(sp);
		bp.setAlignment(title, Pos.CENTER);
		bp.setAlignment(signInBtn, Pos.CENTER);
		
		bp.setCenter(gp);
		bp.setAlignment(gp, Pos.CENTER);
		
		gp.setPadding(new Insets(70, 168, 0, 168));
		gp.add(signIn, 0, 0);
		gp.setMargin(signIn, new Insets(0, 0, 85, 0));
		gp.setHalignment(signIn, HPos.CENTER);
		
		gp.add(userIdLbl, 0, 1);
		gp.add(userIdTF, 0, 2);
		gp.setMargin(userIdTF, new Insets(0, 0, 58, 0));
		gp.add(passwordLbl, 0, 3);
		gp.add(passwordTF, 0, 4);

		gp.setPrefWidth(775);
		Font fontLbl = Font.font("Poppins", 12);
		userIdLbl.setFont(fontLbl);
		userIdLbl.setTextFill(Color.web("#2D6936"));
		passwordLbl.setFont(fontLbl);
		passwordLbl.setTextFill(Color.web("#2D6936"));
		Font fontTF = Font.font("Poppins", 16);
		userIdTF.setPrefWidth(775);
		userIdTF.setPrefHeight(44);
		userIdTF.setFont(fontTF);
		passwordTF.setPrefWidth(775);
		passwordTF.setPrefHeight(44);
		passwordTF.setFont(fontTF);
		
		bp.setBottom(signInBtn);
		signInBtn.setPrefWidth(311);
		signInBtn.setPrefHeight(52);
		signInBtn.setStyle("-fx-background-color: #D6F7E7; -fx-border-radius: 15px; -fx-border-color: black;");
		Font fontButton = Font.font("Poppins", FontWeight.BOLD, 18);
		signInBtn.setFont(fontButton);
		bp.setMargin(signInBtn, new Insets(0, 0, 81, 0));
		
		signInBtn.setOnMouseEntered(e-> scene2.setCursor(Cursor.HAND));
		signInBtn.setOnMouseExited(e -> scene2.setCursor(Cursor.DEFAULT));
		
		scene2 = new Scene(bp, 1111, 790);
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
		
		primaryStage.setScene(scene1);
		primaryStage.setResizable(false);
		primaryStage.setMaximized(false);
		primaryStage.show();
	}

}
