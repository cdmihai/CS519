package edu.oregonstate.cs519.touchdevelop;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ScriptLibraryTest {
	
	private ScriptLibrary instance;

	@Before
	public void setUp() {
		instance = ScriptLibrary.getInstance();
	}
	
	@Test
	public void testGetExisitingScript() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put(Script.ID, "aaaa");
		hashMap.put(Script.NAME, "test");
		hashMap.put(Script.ROOT_ID, "bbbb");
		hashMap.put(Script.USER_ID, "testuser");
		instance.addScript(new Script(hashMap));
		Script script = instance.getScript("aaaa");
		assertEquals("test",script.getName());
	}

}
