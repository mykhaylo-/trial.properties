package com.crossover.trial.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.vfs2.VFS;

import com.crossover.trial.properties.analyzer.DefaultPropertyAnalyzer;
import com.crossover.trial.properties.analyzer.PropertyAnalyzer;
import com.crossover.trial.properties.loader.DefaultPropertiesLoader;
import com.crossover.trial.properties.loader.PropertiesLoader;
import com.crossover.trial.properties.model.TypeSafeProperty;

public class Main {


	private static PropertyAnalyzer propertyAnalyzer = new DefaultPropertyAnalyzer();

	public static void main(String[] args) throws Exception {
		init();

		List<TypeSafeProperty> typesafeProperties = new ArrayList<TypeSafeProperty>();

		PropertiesLoader propertiesLoader = new DefaultPropertiesLoader(VFS.getManager());
		
		Properties properties = propertiesLoader.loadProperties(args);

		properties.forEach((key, value) -> typesafeProperties.add(propertyAnalyzer.analyzeProperty(key.toString(), value.toString())));

		typesafeProperties.stream().sorted().forEachOrdered(System.out::println);
	}

	private static void init() throws IOException {

		// Just to suppress INFO output from Apache VFS.
		// Not sure how does it affect autograder.
		Logger globalLogger = Logger.getLogger("");
		globalLogger.setLevel(Level.WARNING);

		Properties knownTypes = new Properties();

		try (final InputStream stream = Main.class.getResourceAsStream("/known_properties.properties")) {
			knownTypes.load(stream);
		}

		propertyAnalyzer.setKnownTypes(knownTypes);
	}
}
