package workinghours.ui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import workinghours.entities.WorkhourEvent;
import workinghours.service.WorkhourService;
import workinghours.ui.WorkhourListCell;

public class MainViewController implements Initializable {

	@FXML
	private ListView<WorkhourEvent> workhourListView;

	@FXML
	private Button addEvent;
	
	private ObservableList<WorkhourEvent> observableList;
	
	private WorkhourService whService;

	public MainViewController() {
		observableList = FXCollections.observableArrayList();
	}
	
	public void updateListView(List<WorkhourEvent> events) {
		observableList.addAll(events);
	}

	@FXML
	protected void newEvent(ActionEvent event) {
		try {
			observableList.add(whService.createWorkhourEvent("Toimiiko?", 1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		workhourListView.setItems(observableList);
		workhourListView.setCellFactory(workhourListView -> new WorkhourListCell());
	}

	public void setWorkhourService(WorkhourService whService) {
		this.whService = whService;
	}
}
