package com.crossover.trial.properties.parser;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

import org.junit.Test;

public class JsonParserTest {

	private JsonParser unit = new JsonParser();
	
	@Test
	public void testParse() throws Exception {
		String json = "{\"useHttps\":\"true\"}";
		byte[] data = json.getBytes();
		InputStream inputStream = new ByteArrayInputStream(data);
		Properties actualProperties = unit.parse(inputStream);
		
		Properties expectedProperties = new Properties();
		expectedProperties.put("useHttps", "true");
		
		assertEquals(expectedProperties, actualProperties);
	}
	
	@Test(expected=UncheckedIOException.class)
	public void testExceptionIsThrownWhenCannotReadFromInputStream() throws Exception {
		InputStream inputStream = mock(InputStream.class);
		when(inputStream.read()).thenThrow(IOException.class);
		
		Properties actualProperties = unit.parse(inputStream);
		
		fail("UncheckedIOException should have been thrown");
	}
}
