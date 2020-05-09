package workinghours.ui;

import java.io.FileInputStream;
import java.util.Properties;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import workinghours.dao.DatabaseInitializer;
import workinghours.dao.SqlConnection;
import workinghours.dao.SqlEventDao;
import workinghours.dao.SqlUserDao;
import workinghours.dao.UserDao;
import workinghours.dao.WorkhourEventDao;
import workinghours.service.WorkhourService;
import workinghours.ui.controller.LoginFormController;
import workinghours.ui.controller.MainViewController;
import workinghours.ui.controller.RegisterFormController;

public class WorkHoursUi extends Application {

	private WorkhourService whService;

	@Override
	public void init() throws Exception {
		Properties properties = new Properties();
		properties.load(new FileInputStream("application.properties"));

		String dbUrl = properties.getProperty("dbname");

		DatabaseInitializer.InitializeDb("workhours.db");

		UserDao userDao = new SqlUserDao(dbUrl);
		WorkhourEventDao eventDao = new SqlEventDao(dbUrl);

		whService = new WorkhourService(userDao, eventDao);
	}

	@Override
	public void start(Stage stage) throws Exception {

		// Luodaan kirjautumisnäkymä
		FXMLLoader loginViewLoader = new FXMLLoader(getClass().getResource("/fxml/loginView.fxml"));
		Parent loginPane = loginViewLoader.load();
		Scene loginScene = new Scene(loginPane, 420, 220);

		FXMLLoader registerViewLoader = new FXMLLoader(getClass().getResource("/fxml/registerView.fxml"));
		Parent registerParent = registerViewLoader.load();
		Scene registerScene = new Scene(registerParent, 600, 275);

		FXMLLoader mainViewLoader = new FXMLLoader(getClass().getResource("/fxml/mainView.fxml"));
		Parent mainPane = mainViewLoader.load();
		Scene mainScene = new Scene(mainPane, 600, 400);

		MainViewController mainViewController = mainViewLoader.getController();
		mainViewController.setWorkhourService(whService);

		LoginFormController loginFormController = loginViewLoader.getController();
		loginFormController.setRegisterScene(registerScene);
		loginFormController.setMainScene(mainScene);
		loginFormController.setWorkhourService(whService);
		loginFormController.setMainController(mainViewController);

		RegisterFormController registerFormController = registerViewLoader.getController();
		registerFormController.setLoginScene(loginScene);
		registerFormController.setWorkhourService(whService);

		stage.setTitle("Login");
		stage.setScene(loginScene);
		stage.show();
	}
}
