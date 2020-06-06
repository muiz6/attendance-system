package com.muiz6.system.attendance;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.util.Callback;

import javax.annotation.Nullable;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Util {

	@Nullable
	public static Node getFxmlNode(String resource) {

		// get gradle resource
		final URL fxmlResource = ClassLoader.getSystemClassLoader()
				.getResource(resource);
		try {
			final Node node = FXMLLoader.load(fxmlResource);
			return node;
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Nullable
	public static Node getFxmlNode(String resource,
    		Callback<Class<?>, Object> controllerFactory) {

		// get gradle resource
		final URL fxmlResource = ClassLoader.getSystemClassLoader()
				.getResource(resource);
		try {
			final FXMLLoader loader =  new FXMLLoader(fxmlResource);
			loader.setControllerFactory(controllerFactory);
			return loader.load();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static String getDate(long timestamp) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy");
		Date date = new Date(timestamp);
		return format.format(date);
	}
}
