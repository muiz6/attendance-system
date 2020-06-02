package com.muiz6.system.attendance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javax.annotation.Nullable;
import java.io.IOException;
import java.net.URL;

public abstract class Util {

	// public static HashMap<String, String> readJsonFile(String path)
	// 		throws java.io.FileNotFoundException {
	// 	BufferedReader bufferedReader
	// 			= new BufferedReader(new FileReader(Constants.PATH_TO_SETTING_JSON));
	// 	Gson gson = new Gson();
	//
	// 	// TODO: fix this warning
	// 	return gson.fromJson(bufferedReader, HashMap.class);
	// }

	@Nullable
	public static Node getFxmlNode(String resource) {
		final URL fxmlResource = ClassLoader.getSystemClassLoader()
				.getResource(resource);
		try {
			final Node node = FXMLLoader.load(fxmlResource);
			return node;
		}
		catch (IOException e) {
			System.out.println(e.toString());
		}
		return null;
	}
}
