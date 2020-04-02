module ohte2020.WorkingHours {
    requires javafx.controls;
	requires com.google.gson;
	requires javafx.fxml;
	opens ohte2020.WorkingHours.ui.controller;
	exports ohte2020.WorkingHours.ui.controller;
	exports ohte2020.WorkingHours.ui;
}