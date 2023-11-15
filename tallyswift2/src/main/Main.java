package main;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

	Scene onboarding_scene;
	private BorderPane scene_borderpane;
	private GridPane container_gridpane, rightContainer_gridpane;
	private StackPane header_stackpane;
	private Font font_boldItalic44, font_bold32, font_regular22, font_bold28;
	private Label lbl_header, lbl_welcome, lbl_desc1, lbl_desc2;
	private Rectangle rect_header;
	private String titleColor = "#2D6936";
	private Image img_onboarding;
	private ImageView imageView_onboarding;
	private Button continue_btn;
	
	private void initialize() {
		scene_borderpane = new BorderPane();
		container_gridpane = new GridPane();
		rightContainer_gridpane = new GridPane();
		header_stackpane = new StackPane();
		
		font_boldItalic44 = Font.font("Poppins", FontWeight.BOLD, FontPosture.ITALIC, 44);
		font_bold32 = Font.font("Poppins", FontWeight.BOLD, 32);		
		font_regular22 = Font.font("Poppins", 22);
		font_bold28 = Font.font("Poppins", FontWeight.BOLD, 28);
		
		lbl_header = new Label("E-Cashier");
		lbl_welcome = new Label("Welcome!!!");
		lbl_desc1 = new Label("Empowering Businesses with Transactions");
		lbl_desc2 = new Label("and Effortless Inventory Management");
		
		rect_header = new Rectangle(0, 0, 1111, 80);
		
		img_onboarding = new Image("onboard.png");
		
		imageView_onboarding = new ImageView(img_onboarding);
		
		continue_btn = new Button("Continue");
		
		onboarding_scene = new Scene(scene_borderpane, 1111, 790);
	}
	
	private void initializeHeader() {
		styleHeader();
		
		header_stackpane.getChildren().addAll(rect_header, lbl_header);
	}
	
	private void styleHeader() {
		lbl_header.setFont(font_boldItalic44);
		lbl_header.setTextFill(Color.web(titleColor));
		lbl_header.setPadding(new Insets(5, 0, 0, 0));
		
		rect_header.setFill(Color.web("#F4F2DE"));
		
		scene_borderpane.setAlignment(lbl_header, Pos.CENTER);
	}
	
	private void initializeBody() {
		initializeLeftContainer();
		positioningRightContainer();

		container_gridpane.setAlignment(Pos.CENTER);
	}
	
	private void initializeLeftContainer() {
		styleLeftContainer();
		
		container_gridpane.add(imageView_onboarding, 0, 0);
		container_gridpane.add(rightContainer_gridpane, 1, 0);
	}

	private void styleLeftContainer() {
		imageView_onboarding.setFitWidth(596);
		imageView_onboarding.setFitHeight(419);
		container_gridpane.setMargin(imageView_onboarding, new Insets(0, 25, 0, 0));		
	}

	
	private void positioningRightContainer() {
		initializeRightContainer();
		
		rightContainer_gridpane.add(lbl_welcome, 0, 0);
		rightContainer_gridpane.add(lbl_desc1, 0, 1);
		rightContainer_gridpane.add(lbl_desc2, 0, 2);
		rightContainer_gridpane.add(continue_btn, 0, 3);
	}

	private void initializeRightContainer() {
		styleRightContainer();
		
		rightContainer_gridpane.setAlignment(Pos.CENTER);
	}
	
	private void styleRightContainer() {
		rightContainer_gridpane.setHalignment(lbl_welcome, HPos.CENTER);
		lbl_welcome.setFont(font_bold32);
		lbl_welcome.setTextFill(Color.web(titleColor));
		
		rightContainer_gridpane.setHalignment(lbl_desc1, HPos.CENTER);
		lbl_desc1.setFont(font_regular22);
		
		rightContainer_gridpane.setHalignment(lbl_desc2, HPos.CENTER);
		lbl_desc2.setFont(font_regular22);
		
		rightContainer_gridpane.setHalignment(continue_btn, HPos.CENTER);
		rightContainer_gridpane.setMargin(continue_btn, new Insets(30, 0, 0, 0));
		continue_btn.setPrefWidth(311);
		continue_btn.setPrefHeight(52);
		continue_btn.setStyle("-fx-background-color: #D6F7E7; -fx-border-radius: 15px; -fx-border-color: black;");
		continue_btn.setFont(font_bold28);
		continue_btn.setOnMouseEntered(e -> scene_borderpane.setCursor(javafx.scene.Cursor.HAND));
		continue_btn.setOnMouseExited(e -> scene_borderpane.setCursor(javafx.scene.Cursor.DEFAULT));
	}
	
	private void positioning() {
		initializeHeader();
		initializeBody();
		
		scene_borderpane.setTop(header_stackpane);
		scene_borderpane.setCenter(container_gridpane);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initialize();
		positioning();
		changePage(primaryStage);
		
		primaryStage.setScene(onboarding_scene);
		primaryStage.setResizable(false);
		primaryStage.setMaximized(false);
		primaryStage.show();
	}
	
	private void changePage(Stage stage) {
		continue_btn.setOnMouseClicked(e -> {
			new SignIn(stage);
		});
	}

}
