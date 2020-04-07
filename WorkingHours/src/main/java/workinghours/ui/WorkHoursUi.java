package workinghours.ui;

import java.io.FileInputStream;
import java.util.Properties;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import workinghours.dao.FileUserDao;
import workinghours.service.WorkhourService;
import workinghours.ui.controller.LoginFormController;
import workinghours.ui.controller.RegisterFormController;

public class WorkHoursUi extends Application {

	private WorkhourService whService;

	@Override
	public void init() throws Exception {
		Properties properties = new Properties();
		properties.load(new FileInputStream("application.properties"));

		String userFile = properties.getProperty("userFile");
		String workhourEventFile = properties.getProperty("workhourEventFile");

		FileUserDao userDao = new FileUserDao(userFile);

		whService = new WorkhourService(userDao);
	}

	@Override
	public void start(Stage stage) throws Exception {

		// Luodaan kirjautumisnäkymä
		FXMLLoader loginViewLoader = new FXMLLoader(getClass().getResource("fxml/loginView.fxml"));
		Parent loginPane = loginViewLoader.load();
		Scene loginScene = new Scene(loginPane, 420, 220);

		FXMLLoader registerViewLoader = new FXMLLoader(getClass().getResource("fxml/registerView.fxml"));
		Parent registerParent = registerViewLoader.load();
		Scene registerScene = new Scene(registerParent, 600, 275);

		LoginFormController loginFormController = loginViewLoader.getController();
		loginFormController.setRegisterScene(registerScene);
		loginFormController.setWorkhourService(whService);

		RegisterFormController registerFormController = registerViewLoader.getController();
		registerFormController.setLoginScene(loginScene);
		registerFormController.setWorkhourService(whService);

		stage.setTitle("Login");
		stage.setScene(loginScene);
		stage.show();
	}
}
