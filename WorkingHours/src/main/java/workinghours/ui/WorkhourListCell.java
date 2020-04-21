package workinghours.ui;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import workinghours.entities.WorkhourEvent;

public class WorkhourListCell extends ListCell<WorkhourEvent> {

	@FXML
	private Label eventId;

	@FXML
	private Label date;

	@FXML
	private Label description;

	@FXML
	private Label hours;

	@FXML
	private HBox hBox;

	private FXMLLoader fxmlLoader;

	@Override
	protected void updateItem(WorkhourEvent event, boolean empty) {
		super.updateItem(event, empty);

		if (empty || event == null) {

			setText(null);
			setGraphic(null);

		} else {
			if (fxmlLoader == null) {
				fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/workhourListCell.fxml"));
				fxmlLoader.setController(this);

				try {
					fxmlLoader.load();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			
			eventId.setText(String.valueOf(1));
			date.setText(event.getInsertDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
			description.setText(event.getDescription());
			hours.setText(String.valueOf(event.getHours()));
			
            setText(null);
            setGraphic(hBox);
		}
		

	}
}
