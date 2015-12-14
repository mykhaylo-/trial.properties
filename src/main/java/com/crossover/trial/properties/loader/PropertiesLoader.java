package com.crossover.trial.properties.loader;

import java.util.Properties;

public interface PropertiesLoader {

	Properties loadProperties(String ... uris) throws Exception;
}
