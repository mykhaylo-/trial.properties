package com.crossover.trial.properties.analyzer;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.crossover.trial.properties.model.TypeSafeProperty;

public class DefaultPropertyAnalyzerTest {

	private DefaultPropertyAnalyzer unit;

	@Before
	public void setUp() {
		unit = new DefaultPropertyAnalyzer();
	}

	@Test
	public void testAnalyzeUnknowProperty() throws Exception {
		TypeSafeProperty expectedProperty = new TypeSafeProperty("someproperty", "37", "java.lang.String");
		assertEquals(expectedProperty, unit.analyzeProperty("someproperty", "37"));
	}

	@Test
	public void testAnalyzeKnownProperty() throws Exception {
		Properties knownTypes = new Properties();
		knownTypes.put("aws.useHttps", "java.lang.Boolean");
		unit.setKnownTypes(knownTypes);
		TypeSafeProperty expectedProperty = new TypeSafeProperty("aws.useHttps", "false", "java.lang.Boolean");
		assertEquals(expectedProperty, unit.analyzeProperty("aws.useHttps", "false"));
	}

	@Test
	public void testAnalyzeResolvesBooleanIfNameStartsWithIs() {
		TypeSafeProperty expectedProperty = new TypeSafeProperty("isPasswordProtected", "true", "java.lang.Boolean");
		assertEquals(expectedProperty, unit.analyzeProperty("isPasswordProtected", "true"));
	}

	@Test
	public void testAnalyzeResolvesIntegerIfNameEndsWithTimeout() {
		TypeSafeProperty expectedProperty = new TypeSafeProperty("connection.timeout", "60000", "java.lang.Integer");
		assertEquals(expectedProperty, unit.analyzeProperty("connection.timeout", "60000"));
	}

}
