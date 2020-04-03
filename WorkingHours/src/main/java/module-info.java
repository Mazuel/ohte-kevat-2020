module ohte2020.WorkingHours {
    requires javafx.controls;
	requires com.google.gson;
	requires javafx.fxml;
	requires java.base;
	requires javafx.graphics;
	opens ohte2020.WorkingHours.ui.controller;
	exports ohte2020.WorkingHours.ui.controller;
	exports ohte2020.WorkingHours.ui;
}