package com.crossover.trial.properties.analyzer;

import java.util.Properties;

import com.crossover.trial.properties.model.TypeSafeProperty;

public class DefaultPropertyAnalyzer implements PropertyAnalyzer {

	private Properties knownTypes = new Properties();

	@Override
	public TypeSafeProperty analyzeProperty(String name, String value) {

		String type = String.class.getCanonicalName();
		
		if (knownTypes.containsKey(name)) {
			type = knownTypes.getProperty(name);
		} else { // some heuristic here. 
			if (name.endsWith("timeout"))
				type = Integer.class.getCanonicalName();
			else if (name.startsWith("is")) {
				type = Boolean.class.getCanonicalName();
			}
		}

		return new TypeSafeProperty(name, value, type);
	}

	@Override
	public void setKnownTypes(Properties knownTypes) {
		this.knownTypes = knownTypes;
	}
}
