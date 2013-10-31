package edu.oregonstate.cs519.touchdevelop;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ScriptTest {
	
	private Map<String,String> hashMap;

	@Before
	public void setUp() {
		hashMap = new HashMap<String, String>();
	}

	@Test
	public void testCreateScriptWithName() {
		hashMap.put(Script.NAME, "TestScript");
		Script script = new Script(hashMap);
		assertEquals("TestScript",script.getName());
	}
}
