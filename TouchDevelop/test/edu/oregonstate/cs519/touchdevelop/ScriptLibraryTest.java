package edu.oregonstate.cs519.touchdevelop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ScriptLibraryTest {

	private static final String TEST_FILE = "test.json";
	private MemoryLibrary instance;

	@Before
	public void setUp() {
		instance = new MemoryLibrary(TEST_FILE);
	}
	
	@After
	public void tearDown() {
		try {
			Files.delete(Paths.get(TEST_FILE));
		} catch (IOException e) {
		}
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
	public void testGetScriptsFromFile() throws Exception {
		Files.write(Paths.get(TEST_FILE),
				"[{\"id\":\"bbbb\",\"name\":\"test1\",\"userid\":\"test_usr\"}]"
						.getBytes(), StandardOpenOption.CREATE);
		instance = new MemoryLibrary(TEST_FILE);
		Script script = instance.getScript("bbbb");
		assertNotNull(script);
		assertEquals("bbbb", script.getID());
		assertEquals("test1", script.getName());
		assertEquals("test_usr", script.getUserID());
		TestUtils.deleteTestFiles();
	}
	
	@Test
	public void testSaveLibraryToFiles() throws Exception {
		HashMap<String, Object> scriptMap1 = new HashMap<String, Object>();
		scriptMap1.put(Script.ID, "aaaa1");
		scriptMap1.put(Script.NAME, "test");
		scriptMap1.put(Script.USER_ID, "test_usr");
		
		HashMap<String, Object> scriptMap2 = new HashMap<String, Object>();
		scriptMap2.put(Script.ID, "bbbb1");
		scriptMap2.put(Script.NAME, "test2");
		scriptMap2.put(Script.USER_ID, "test_usr2");
		
		instance.addScript(new Script(scriptMap1));
		instance.addScript(new Script(scriptMap2));
		instance.saveScriptsToFile();
		
		byte[] bytes = Files.readAllBytes(Paths.get(TEST_FILE));
		
		String file = new String(bytes);
		
		assertEquals("[{\"id\":\"bbbb1\",\"name\":\"test2\",\"userid\":\"test_usr2\"},{\"id\":\"aaaa1\",\"name\":\"test\",\"userid\":\"test_usr\"}]",file);
	}

}
