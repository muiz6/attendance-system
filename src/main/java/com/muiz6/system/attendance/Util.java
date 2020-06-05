package com.muiz6.system.attendance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

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
}
