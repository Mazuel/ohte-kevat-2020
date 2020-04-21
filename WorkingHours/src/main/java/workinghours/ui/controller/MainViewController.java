package workinghours.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import workinghours.entities.User;
import workinghours.entities.WorkhourEvent;
import workinghours.ui.WorkhourListCell;

public class MainViewController implements Initializable {

	@FXML
	private ListView<WorkhourEvent> workhourListView;

	private ObservableList<WorkhourEvent> observableList;

	public MainViewController() {
		observableList = FXCollections.observableArrayList();
		User user = new User("Riku", "Richard", "12345");
		observableList.addAll(new WorkhourEvent(user, "Töitä", 4), new WorkhourEvent(user, "Unelmointi", 2),
				new WorkhourEvent(user, "Kivien neppailu", 10), new WorkhourEvent(user, "Herääminen todellisuuteen", 3),
				new WorkhourEvent(user, "Töitä", 4));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		workhourListView.setItems(observableList);
		workhourListView.setCellFactory(workhourListView -> new WorkhourListCell());

	}

}
