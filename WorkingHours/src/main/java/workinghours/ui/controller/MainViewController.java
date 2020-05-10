package workinghours.ui.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import workinghours.entities.WorkhourEvent;
import workinghours.service.WorkhourService;
import workinghours.ui.WorkhourListCell;

public class MainViewController implements Initializable {

	@FXML
	private ListView<WorkhourEvent> workhourListView;

	@FXML
	private Button addEvent;

	@FXML
	private Button previousButton;

	@FXML
	private DatePicker datePicker;

	@FXML
	private DatePicker insertPicker;

	@FXML
	private Button nextButton;

	@FXML
	private TextField description;

	@FXML
	private TextField hours;

	@FXML
	private Label descLabel;

	@FXML
	private Label hourLabel;

	@FXML
	private Button deleteButton;

	private ObservableList<WorkhourEvent> observableList;

	private WorkhourService whService;

	private Scene loginScene;

	public MainViewController() {
		observableList = FXCollections.observableArrayList();

	}

	public void updateListView(List<WorkhourEvent> events) {
		workhourListView.getItems().clear();
		observableList.addAll(events);
	}
	
	@FXML
	protected void nextDate(ActionEvent event) {
		LocalDate date = datePicker.getValue();
		datePicker.setValue(date.plusDays(1));
		updateListView(whService.getEventsBySelectedDate(datePicker.getValue()));
	}

	@FXML
	protected void previousDate(ActionEvent event) {
		LocalDate date = datePicker.getValue();
		datePicker.setValue(date.minusDays(1));
		updateListView(whService.getEventsBySelectedDate(datePicker.getValue()));
	}

	@FXML
	protected void logout(ActionEvent event) {
		workhourListView.getItems().clear();
		whService.logout();
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(loginScene);
	}

	@FXML
	protected void deleteSelected(ActionEvent event) {
		final int selectedIndex = workhourListView.getSelectionModel().getSelectedIndex();
		if (selectedIndex == -1) {
			return;
		}
		WorkhourEvent deleteItem = workhourListView.getItems().get(selectedIndex);
		workhourListView.getItems().remove(selectedIndex);
		whService.deleteItem(deleteItem);

	}

	@FXML
	protected void newEvent(ActionEvent event) {
		try {
			if (!description.getText().isEmpty() & !hours.getText().isEmpty()) {
				whService.createWorkhourEvent(insertPicker.getValue(), description.getText(),
						Double.valueOf(hours.getText()));
				description.setText("");
				hours.setText("");
				updateListView(whService.getEventsBySelectedDate(datePicker.getValue()));
			}
		} catch (Exception e) {

		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		workhourListView.setItems(observableList);
		workhourListView.setCellFactory(workhourListView -> new WorkhourListCell());
		insertPicker.setValue(LocalDate.now());
		datePicker.setValue(LocalDate.now());

		hours.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*(\\.\\d*)?")) {
					hours.setText(oldValue);
				}
			}
		});

		datePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
			datePicker.setValue(newValue);
			updateListView(whService.getEventsBySelectedDate(newValue));
		});
	}

	public void setLoginScene(Scene scene) {
		this.loginScene = scene;
	}

	public void setWorkhourService(WorkhourService whService) {
		this.whService = whService;
	}
}
