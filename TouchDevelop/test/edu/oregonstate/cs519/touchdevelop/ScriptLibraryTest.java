package edu.oregonstate.cs519.touchdevelop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ScriptLibraryTest {

	private MemoryLibrary instance;

	@Before
	public void setUp() {
		instance = new MemoryLibrary(new FileLibrary(TestUtils.TEST_FOLDER, new CloudManager()));
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
	
	@Test
	public void testGetScriptFromCloud() throws Exception {
		Script script = instance.getScript("qoipfdvx");
		assertNotNull(script);
		assertEquals("qoipfdvx", script.getID());
		assertEquals("Bike maintenance", script.getName());
		assertEquals("qoipfdvx",script.getRootID());
		assertEquals("antga",script.getUserID());
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
		
		byte[] byte1 = Files.readAllBytes(Paths.get(TestUtils.TEST_FOLDER + "/aaaa1"));
		byte[] byte2 = Files.readAllBytes(Paths.get(TestUtils.TEST_FOLDER + "/bbbb1"));
		
		String file1 = new String(byte1);
		String file2 = new String(byte2);
		
		assertEquals("{\"id\":\"aaaa1\",\"name\":\"test\",\"userid\":\"test_usr\"}",file1);
		assertEquals("{\"id\":\"bbbb1\",\"name\":\"test2\",\"userid\":\"test_usr2\"}", file2);
	}

}
