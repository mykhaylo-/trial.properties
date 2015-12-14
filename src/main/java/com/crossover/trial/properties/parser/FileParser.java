package com.crossover.trial.properties.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public interface FileParser {

	Properties parse(InputStream inputStream) throws IOException;
}
