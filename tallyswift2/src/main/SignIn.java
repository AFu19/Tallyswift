package main;

import java.util.ArrayList;

import data.Account;
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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import util.Connect;

public class SignIn extends Application{
	
	private Scene scene;
	private BorderPane sceneBorderPane;
	private GridPane containerGridPane;
	private StackPane headerStackPane;
	private Font fontBoldItalic44, fontBold52, fontRegular12, fontRegular16, fontBold18;
	private Label titleLabel, signInLabel, userIdLabel, passwordLabel, wrongPasswordLabel;
	private String titleColor = "#2D6936";
	private Rectangle headerRectangle;
	private TextField userIdTextField;
	private PasswordField passwordPasswordField;
	private Button signInButton;
	private ArrayList<Account> dataAccount;
	private Connect connect = Connect.getInstance();
	
	private void getUserData(String inputUserID, String inputPassword) {
		dataAccount.clear();
		
		String query = "SELECT * FROM user WHERE UserID = '" + inputUserID + "' AND Password = '" + inputPassword + "'";
		connect.rs = connect.execQuery(query);
		
		try {
			while (connect.rs.next()) {
				String UserID = connect.rs.getString("UserID");
				String Password = connect.rs.getString("Password");
				
				dataAccount.add(new Account(UserID, Password));
			}
		} catch (Exception e) { System.out.println(query);}
		
	}
	
	private void initialize() {
		sceneBorderPane = new BorderPane();
		containerGridPane = new GridPane();
		headerStackPane = new StackPane();
		
		fontBoldItalic44 = Font.font("Poppins", FontWeight.BOLD, FontPosture.ITALIC, 44);
		fontBold52 = Font.font("Poppins", FontWeight.BOLD, 52);
		fontRegular12 = Font.font("Poppins", 12);
		fontRegular16 = Font.font("Poppins", 16);
		fontBold18 = Font.font("Poppins", FontWeight.BOLD, 18);
		
		titleLabel = new Label("E-Cashier");
		signInLabel = new Label("Sign In");
		userIdLabel = new Label("UserID");
		passwordLabel = new Label("Password");
		wrongPasswordLabel = new Label();
		
		userIdTextField = new TextField();
		
		passwordPasswordField = new PasswordField();
		
		signInButton = new Button("Sign In");
		
		headerRectangle = new Rectangle(0, 0, 1111, 80);
		
		dataAccount = new ArrayList<>();
		
		scene = new Scene(sceneBorderPane, 1111, 790);
	}
	
	private void positioning() {
		initializeHeader();
		initializeBody();
		styleFooter();
		
		sceneBorderPane.setTop(headerStackPane);
		sceneBorderPane.setCenter(containerGridPane);
		sceneBorderPane.setBottom(signInButton);
	}
	
	private void initializeHeader() {
		styleHeader();
		
		headerStackPane.getChildren().addAll(headerRectangle, titleLabel);
	}
	
	private void styleHeader() {
		titleLabel.setFont(fontBoldItalic44);
		titleLabel.setTextFill(Color.web(titleColor));
		titleLabel.setPadding(new Insets(5, 0, 0, 0));
		
		headerRectangle.setFill(Color.web("#F4F2DE"));
		
		sceneBorderPane.setAlignment(titleLabel, Pos.CENTER);
	}
	
	private void initializeBody() {
		styleBody();
		positioningBody();
	}
	
	private void positioningBody() {		
		containerGridPane.add(signInLabel, 0, 0);
		containerGridPane.add(userIdLabel, 0, 1);
		containerGridPane.add(userIdTextField, 0, 2);
		containerGridPane.add(passwordLabel, 0, 3);
		containerGridPane.add(passwordPasswordField, 0, 4);
		containerGridPane.add(wrongPasswordLabel, 0, 5);
	}
	
	private void styleBody() {
		sceneBorderPane.setAlignment(containerGridPane, Pos.CENTER);
		
		containerGridPane.setPadding(new Insets(70, 168, 0, 168));
		containerGridPane.setPrefWidth(775);
		
		signInLabel.setFont(fontBold52);
		signInLabel.setTextFill(Color.web(titleColor));
		containerGridPane.setMargin(signInLabel, new Insets(0, 0, 85, 0));
		containerGridPane.setHalignment(signInLabel, HPos.CENTER);
		
		userIdLabel.setFont(fontRegular12);
		userIdLabel.setTextFill(Color.web(titleColor));
		
		userIdTextField.setPrefWidth(775);
		userIdTextField.setPrefHeight(44);
		userIdTextField.setFont(fontRegular16);
		containerGridPane.setMargin(userIdTextField, new Insets(0, 0, 58, 0));
		
		passwordLabel.setFont(fontRegular12);
		passwordLabel.setTextFill(Color.web(titleColor));
		
		passwordPasswordField.setPrefWidth(775);
		passwordPasswordField.setPrefHeight(44);
		passwordPasswordField.setFont(fontRegular16);
	}
	
	private void styleFooter() {
		signInButton.setPrefWidth(311);
		signInButton.setPrefHeight(52);
		signInButton.setStyle("-fx-background-color: #D6F7E7; -fx-background-radius: 15px; -fx-border-radius: 15px; -fx-border-color: black;");		
		signInButton.setFont(fontBold18);
		signInButton.setOnMouseEntered(e-> scene.setCursor(Cursor.HAND));
		signInButton.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
		
		sceneBorderPane.setAlignment(signInButton, Pos.CENTER);
		sceneBorderPane.setMargin(signInButton, new Insets(0, 0, 81, 0));
	}
	
	public SignIn(Stage stage) {
		try {
			this.start(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage signInStage) throws Exception {
		initialize();
		positioning();
		changePage(signInStage);
		
		signInStage.setScene(scene);
	}
	
	private void changePage(Stage stage) {
		signInButton.setOnMouseClicked(e -> {
			validateUser(stage);
		});
		
		passwordPasswordField.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				validateUser(stage);
			}
		});
	}
	
	private void validateUser(Stage stage) {
		getUserData(userIdTextField.getText(), passwordPasswordField.getText());
		
		if (dataAccount.isEmpty()) {
			wrongPasswordLabel.setText("Username/password salah!");
			wrongPasswordLabel.setTextFill(Color.RED);
		}else {
			new Transaction(stage);
		}
	}

}
