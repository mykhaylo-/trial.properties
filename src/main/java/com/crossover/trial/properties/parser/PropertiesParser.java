package com.crossover.trial.properties.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesParser implements FileParser {

	@Override
	public Properties parse(InputStream inputStream) throws IOException{
		Properties result = new Properties();
		result.load(inputStream);
		return result;
	}
}
