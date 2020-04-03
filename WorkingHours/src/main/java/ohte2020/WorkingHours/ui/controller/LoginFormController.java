package ohte2020.WorkingHours.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ohte2020.WorkingHours.service.WorkhourService;

public class LoginFormController {

	@FXML
	private Label errorField;

	@FXML
	private TextField nameField;

	@FXML
	private TextField passwordField;

	@FXML
	private Button loginButton;

	@FXML
	private Button createUserButton;

	private Scene registerScene;

	private WorkhourService workhourService;

	@FXML
	protected void handleLogin() {
		errorField.setTextFill(Color.web("#ff0000", 0.8));
		if (nameField.getText().isEmpty() && passwordField.getText().isEmpty()) {
			errorField.setText("Invalid username or password");
			return;
		}
		boolean loginSuccessful = workhourService.login(nameField.getText(), passwordField.getText());

		if (loginSuccessful) {
			//Siirry yleisnäkymään
			Alert a = new Alert(AlertType.INFORMATION);
			a.setContentText("Login successful!");
			a.show();
			return;
		}
		errorField.setText("Invalid username or password");
	}

	@FXML
	protected void switchToRegisterScene(ActionEvent event) {
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(registerScene);
	}

	public void setWorkhourService(WorkhourService workhourService) {
		this.workhourService = workhourService;
	}

	public void setRegisterScene(Scene scene) {
		this.registerScene = scene;
	}

}
