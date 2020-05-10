package workinghours.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import workinghours.dao.DatabaseInitializer;
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
	
	private final String DATABASE_NAME = "workhours.db"; 

	@Override
	public void init() throws Exception {

		DatabaseInitializer.createDatabase(DATABASE_NAME);

		UserDao userDao = new SqlUserDao(DATABASE_NAME);
		WorkhourEventDao eventDao = new SqlEventDao(DATABASE_NAME);

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
		Scene mainScene = new Scene(mainPane, 650, 510);

		MainViewController mainViewController = mainViewLoader.getController();
		mainViewController.setWorkhourService(whService);
		mainViewController.setLoginScene(loginScene);

		LoginFormController loginFormController = loginViewLoader.getController();
		loginFormController.setRegisterScene(registerScene);
		loginFormController.setMainScene(mainScene);
		loginFormController.setWorkhourService(whService);
		loginFormController.setMainController(mainViewController);

		RegisterFormController registerFormController = registerViewLoader.getController();
		registerFormController.setLoginScene(loginScene);
		registerFormController.setWorkhourService(whService);
		
		stage.setMaxWidth(695);
		stage.setMaxHeight(550);

		stage.setTitle("WorkingHours");
		stage.setScene(loginScene);
		stage.show();
	}
}
