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
	
	private Scene scene;
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
	private Rectangle rectangle;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void initialize() {
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
		
		scene = new Scene(bp, 1111, 790);
		
	}
	
	private void setPosition() {
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
		
		signInBtn.setOnMouseEntered(e-> scene.setCursor(Cursor.HAND));
		signInBtn.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
		signInBtn.setOnMouseClicked(e -> {
			String userID = userIdTF.getText();
			String password = passwordTF.getText();
			System.out.println(userID);
			System.out.println(password);
		});
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initialize();
		setPosition();
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setMaximized(false);
		primaryStage.show();
	}

}
