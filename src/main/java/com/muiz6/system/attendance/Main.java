package com.muiz6.system.attendance;

import com.muiz6.system.attendance.ui.Strings;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(Strings.APPLICATION_TITLE);

		// get gradle resource
		final URL fxmlResource = ClassLoader.getSystemClassLoader()
				.getResource("layout/panel_navigation.fxml");
		// load navigation panel from fxml
		final Parent root = FXMLLoader.load(fxmlResource);

		primaryStage.setScene(new Scene(root, 800, 600));
		primaryStage.setMinWidth(800);
		primaryStage.setMinHeight(600);
		primaryStage.setMaximized(true);
		primaryStage.show();
	}
}