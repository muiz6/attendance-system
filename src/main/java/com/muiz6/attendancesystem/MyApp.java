package com.muiz6.attendancesystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MyApp extends Application{
    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bedford International School - Portal");

        WebView webView = new WebView();

        webView.getEngine().load(this.getClass().getResource("/html/login.html").toString());

        StackPane stack = new StackPane();
        stack.getChildren().add(webView);
        Scene scene = new Scene(stack);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}