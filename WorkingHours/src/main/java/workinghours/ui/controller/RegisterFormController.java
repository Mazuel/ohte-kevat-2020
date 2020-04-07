package workinghours.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import workinghours.service.WorkhourService;

public class RegisterFormController {

	@FXML
	private Label errorField;

	@FXML
	private TextField nameField;

	@FXML
	private TextField usernameField;

	@FXML
	private TextField passwordField;

	@FXML
	private Button submitButton;

	@FXML
	private Button backButton;

	private Scene loginScene;

	private WorkhourService workhourService;

	@FXML
	protected void backToLogin(ActionEvent event) {
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(loginScene);
	}

	@FXML
	protected void register(ActionEvent event) {

		String name = nameField.getText();
		String username = usernameField.getText();
		String password = passwordField.getText();
		errorField.setTextFill(Color.web("#ff0000", 0.8));

		if (isValidFields(name, username, password)) {
			errorField.setText("Some fields are empty or invalid!");
			return;
		}

		boolean success = workhourService.createUser(name, username, password);

		if (!success) {
			errorField.setText("Username already exists!");
			return;
		}

		Alert a = new Alert(AlertType.INFORMATION);
		a.setContentText("User successfully created!");
		a.show();

		backToLogin(event);

	}

	private boolean isValidFields(String name, String username, String password) {
		if (name.isEmpty() || password.isEmpty() || username.isEmpty()) {
			return true;
		}
		return false;
	}

	public void setWorkhourService(WorkhourService workhourService) {
		this.workhourService = workhourService;
	}

	public void setLoginScene(Scene loginScene) {
		this.loginScene = loginScene;
	}
}
