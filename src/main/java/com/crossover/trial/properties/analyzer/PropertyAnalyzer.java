package com.crossover.trial.properties.analyzer;

import java.util.Properties;

import com.crossover.trial.properties.model.TypeSafeProperty;

public interface PropertyAnalyzer {

	void setKnownTypes(Properties knownTypes);

	TypeSafeProperty analyzeProperty(String name, String value);
}
