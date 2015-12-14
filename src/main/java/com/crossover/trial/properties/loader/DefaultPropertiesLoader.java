package com.crossover.trial.properties.loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;

import com.crossover.trial.properties.parser.FileParser;
import com.crossover.trial.properties.parser.JsonParser;
import com.crossover.trial.properties.parser.PropertiesParser;

public class DefaultPropertiesLoader implements PropertiesLoader {

	public static final Logger log = Logger.getLogger(DefaultPropertiesLoader.class.getName());

	private static Map<String, FileParser> parsersCache = Stream.of(new SimpleEntry<String, FileParser>("properties", new PropertiesParser()),
			new SimpleEntry<String, FileParser>("json", new JsonParser())).collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue()));

	private final FileSystemManager fileSystemManager;

	public DefaultPropertiesLoader(FileSystemManager fileSystemManager) {
		this.fileSystemManager = fileSystemManager;
	}

	@Override
	public Properties loadProperties(String... uris) throws Exception {
		Properties properties = new Properties();

		for (String uri : uris) {
			properties.putAll(processUri(uri));
		}

		return properties;
	}

	private Properties processUri(String uri) {
		Properties result = new Properties();
		try {
			FileObject file = fileSystemManager.resolveFile(uri);

			String fileExtension = file.getName().getExtension();

			FileParser parser = parsersCache.get(fileExtension);
			InputStream inputStream = file.getContent().getInputStream();
			
			result.putAll(parser.parse(inputStream));
		} catch (IOException e) {
			log.log(Level.WARNING, "Exception thrown when loading properties from the following URI: " + uri, e);
		}
		return result;
	}
}
