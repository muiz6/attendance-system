package com.muiz6.system.attendance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.util.Callback;
import javafx.util.Pair;

import javax.annotation.Nullable;
import java.io.IOException;
import java.net.URL;

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

	/**
	 * Use when reference to both the node and its controller is needed
	 * @param resource: path to fxml resource
	 * @param controllerFactory: custom controller factory to use with the FXMLLoader
	 * @param <T>: controller, same type as the object returned by controller factory
	 * @return Pair<Node, T>: pair object containing the node and its controller
	 */
	@Nullable
	public static <T> Pair<Node, T> getFxmlNodeAndController(String resource,
			Callback<Class<?>, Object> controllerFactory) {
		final URL fxmlResource = ClassLoader.getSystemClassLoader()
				.getResource(resource);
		try {
			final FXMLLoader loader =  new FXMLLoader(fxmlResource);
			loader.setControllerFactory(controllerFactory);
			return new Pair<>(loader.load(), loader.getController());
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}