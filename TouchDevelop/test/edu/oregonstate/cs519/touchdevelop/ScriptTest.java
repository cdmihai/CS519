package edu.oregonstate.cs519.touchdevelop;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ScriptTest {
	
	private Map<String,Object> hashMap;

	@Before
	public void setUp() {
		hashMap = new HashMap<String, Object>();
	}

	@Test
	public void testCreateScriptWithName() {
		hashMap.put(Script.NAME, "TestScript");
		Script script = new Script(hashMap);
		assertEquals("TestScript",script.getName());
	}
	
	@Test
	public void testCreateScriptWithId() {
		hashMap.put(Script.ID, "aaaa");
		Script script = new Script(hashMap);
		assertEquals("aaaa", script.getID());
	}
	
	@Test
	public void testCreateScriptWithUserId() {
		hashMap.put(Script.USER_ID, "bob");
		Script script = new Script(hashMap);
		assertEquals("bob",script.getUserID());
	}
	
	@Test
	public void testCreateScriptWithRootId() {
		hashMap.put(Script.ROOT_ID, "bbbb");
		Script script = new Script(hashMap);
		assertEquals(script.getRootID(), "bbbb");
	}
	
	@Test
	public void testGetSuccessors() {
		hashMap.put(Script.ID, "qoipfdvx");
		Script script = new Script(hashMap);
		List<Script> successors = script.getSuccessors();
		assertEquals(1, successors.size());
		assertEquals("qfbr",successors.get(0).getID());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetSuccessorsInMap() {
		hashMap.put(Script.ID, "qoipfdvx");
		Script script = new Script(hashMap);
		script.getSuccessors();
		Map<String, Object> scriptMap = script.getHashMap();
		List<String> successors = (List<String>) scriptMap.get(Script.SUCCESSORS);
		assertEquals(1, successors.size());
		assertEquals("qfbr", successors.get(0));
	}
	
	@Test
	public void testGetSuccessorsFromMap() {
		// base script
		hashMap.put(Script.ID,"qoipfdvx");
		List<String> scriptList = Collections.singletonList("aaaaa");
		hashMap.put(Script.SUCCESSORS, scriptList);
		Script script = new Script(hashMap);
		
		// successor script
		HashMap<String, Object> successorScriptMap = new HashMap<String,Object>();
		successorScriptMap.put(Script.ID, "aaaaa");
		MemoryLibrary.getInstance().addScript(new Script(successorScriptMap));
		
		// testing the thing
		List<Script> successors = script.getSuccessors();
		assertEquals(1, successors.size());
		assertEquals("aaaaa", successors.get(0).getID());
	}
	
}
