package com.muiz6.system.attendance;

import com.muiz6.system.attendance.ui.Strings;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

	private static final String _TAG = "Main: ";

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Repository.initializeDataBase();
		Repository.markEmployeeAbsentAll();

		primaryStage.setTitle(Strings.APPLICATION_TITLE);

		final Parent root = (Parent) Util
				.getFxmlNode(Constants.RES_FXML_PANEL_NAVIGATION);
		if (root == null) {
			throw new IOException(_TAG + "Could not access Fxml resource");
		}

		primaryStage.setScene(new Scene(root, 800, 600));
		primaryStage.setMinWidth(800);
		primaryStage.setMinHeight(600);
		primaryStage.setMaximized(true);
		primaryStage.show();
	}
}