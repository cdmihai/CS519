package edu.oregonstate.cs519.touchdevelop;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ScriptLibraryTest {

	private ScriptLibrary instance;

	@Before
	public void setUp() {
		instance = ScriptLibrary.getInstance();
		instance.setPersitanceDestination(TestUtils.TEST_FOLDER);
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
		assertEquals("test", script.getName());
	}

	@Test
	public void testGetScriptFromFile() throws Exception {
		Files.createDirectories(Paths.get(TestUtils.TEST_FOLDER));
		Files.write(Paths.get(TestUtils.TEST_FOLDER + "/bbbb"),
				"{\"id\":\"bbbb\",\"name\":\"test1\",\"userid\":\"test_usr\"}"
						.getBytes(), StandardOpenOption.CREATE);
		Script script = instance.getScript("bbbb");
		assertNotNull(script);
		assertEquals("bbbb", script.getID());
		assertEquals("test1", script.getName());
		assertEquals("test_usr", script.getUserID());
		TestUtils.deleteTestFiles();
	}

}
