package com.transgrid.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {
	public static Properties property;

	public static Properties getProperty() {
		try {
			property = new Properties();
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream inputStream = loader.getResourceAsStream("ui.properties");
			property.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return property;

	}
}
