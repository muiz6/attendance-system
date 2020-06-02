package com.muiz6.system.attendance;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;


public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(Strings.APPLICATION_TITLE);

		final FXMLLoader loader = new FXMLLoader();
		// get gradle resource
		URL url = ClassLoader.getSystemClassLoader()
				.getResource("layout/panel_navigation.fxml");
		loader.setLocation(url);
		final BorderPane root = loader.load();

		primaryStage.setScene(new Scene(root, 800, 600));
		primaryStage.setMaximized(true);
		primaryStage.show();
	}
}