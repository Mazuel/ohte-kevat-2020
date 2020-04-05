module ohteprojekti.workinghours {
    requires javafx.controls;
	requires com.google.gson;
	requires javafx.fxml;
	requires java.base;
	requires javafx.graphics;
	opens ohteprojekti.workinghours.ui.controller;
	exports ohteprojekti.workinghours.ui.controller;
	exports ohteprojekti.workinghours.ui;
}