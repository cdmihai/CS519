package edu.oregonstate.cs519.touchdevelop;

import static org.junit.Assert.assertEquals;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileLibraryTest {

	private FileLibrary fileDumper;

	@Before
	public void setUp() {
		fileDumper = new FileLibrary(TestUtils.TEST_FOLDER, new NullLibrary());
	}

	@After
	public void tearDown() throws Exception {
		TestUtils.deleteTestFiles();
	}

	@Test
	public void testDumpScript() throws Exception {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put(Script.ID, "aaaa");
		hashMap.put(Script.NAME, "test");
		hashMap.put(Script.USER_ID, "test_usr");
		Script script = new Script(hashMap);
		fileDumper.writeScript(script);
		byte[] fileBytes = Files.readAllBytes(Paths
				.get(TestUtils.TEST_FOLDER + "/aaaa"));
		String contents = new String(fileBytes);
		assertEquals(
				"{\"id\":\"aaaa\",\"name\":\"test\",\"userid\":\"test_usr\"}",
				contents);
	}

	@Test
	public void testGetScript() throws Exception {
		Files.write(Paths.get(TestUtils.TEST_FOLDER + "/test"),
				"{\"name\":\"test\",\"userid\":\"test_usr\"}".getBytes(),
				StandardOpenOption.CREATE);
		Script script = fileDumper.getScript("test");
		assertEquals("test", script.getName());
		assertEquals("test_usr", script.getUserID());
	}
	
	@Test
	public void testCreateOnExistingPath() throws Exception {
		fileDumper = new FileLibrary(TestUtils.TEST_FOLDER, new NullLibrary());
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(Script.ID,"xxxxx");
		fileDumper.writeScript(new Script(map));
	}

}
