package ohte2020.WorkingHours;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	@Override
	public void start(Stage stage) {

		//Luodaan kirjautumisnäkymä
		GridPane loginGrid = createLoginPane();
		Text sceneTitle = new Text("Welcome");
		sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		loginGrid.add(sceneTitle, 0, 0, 2, 1);

		Label userName = new Label("User Name:");
		loginGrid.add(userName, 0, 1);

		TextField userTextField = new TextField();
		loginGrid.add(userTextField, 1, 1);

		Label pw = new Label("Password:");
		loginGrid.add(pw, 0, 2);

		PasswordField pwBox = new PasswordField();
		loginGrid.add(pwBox, 1, 2);
		
		Button loginButton = new Button("Login");
		Button registerButton = new Button("Register");
		
		loginGrid.add(loginButton, 1, 3);
//		loginGrid.add(registerButton, 1, 3);
		
		stage.setTitle("Workhours");
		Scene scene = new Scene(loginGrid, 300, 275);
		stage.setScene(scene);
		stage.show();
	}

	private GridPane createLoginPane() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		return grid;
	}

	public static void main(String[] args) {
		launch();
	}

}