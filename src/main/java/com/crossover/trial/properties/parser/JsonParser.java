package com.crossover.trial.properties.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class JsonParser implements FileParser {

	@Override
	public Properties parse(InputStream inputStream) throws IOException {
		JsonObject result = new Gson().fromJson(read(inputStream), JsonObject.class);
		Map<String,String> map = 
		result.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue().getAsString()));
		
		Properties properties = new Properties();
		properties.putAll(map);
		return properties;
	}

	private static String read(InputStream input) throws IOException {
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
			return buffer.lines().collect(Collectors.joining("\n"));
		}
	}
}
