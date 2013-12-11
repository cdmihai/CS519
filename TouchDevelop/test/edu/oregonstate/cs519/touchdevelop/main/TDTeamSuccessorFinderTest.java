package edu.oregonstate.cs519.touchdevelop.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class TDTeamSuccessorFinderTest {

	@Test
	public void testGetScriptIDs() throws IOException {
		String content = new String(Files.readAllBytes(Paths.get("../testData/TDMicroEdits.json")));
		Set<String> scriptIDs = new TDTeamSuccessorFinder().getScriptIDsFromFile(content);
		assertEquals(3, scriptIDs.size());
		assertTrue(scriptIDs.contains("bubu"));
		assertTrue(scriptIDs.contains("bubu"));
		assertTrue(scriptIDs.contains("btyqjojk"));
	}
	
	/*@Test
	public void testFindSuccessors3() throws Exception {
		Set<String> scripts = new HashSet<>();
		scripts.add("lemvusrz");
		scripts.add("qmislbvx");
		scripts.add("osatcewo");
		scripts.add("lxpyligx");
		
		scripts.add("pwrjydht");
		
		TDTeamSuccessorFinder finder = new TDTeamSuccessorFinder();
		Map<String, List<String>> succ = finder.findSuccessors(scripts);
		
		assertEquals(1, succ.keySet().size());
		
		assertEquals(3, succ.get("lemvusrz").size());
		
		assertTrue(succ.get("lemvusrz").contains("qmislbvx"));
		assertTrue(succ.get("lemvusrz").contains("osatcewo"));
		assertTrue(succ.get("lemvusrz").contains("lxpyligx"));
	}*/
	
	@Test
	public void testFindSuccessors2() throws Exception {
		Set<String> scripts = new HashSet<>();
		scripts.add("lemvusrz");
		scripts.add("qmislbvx");
		scripts.add("osatcewo");
		//scripts.add("lxpyligx");
		
		scripts.add("pwrjydht");
		
		TDTeamSuccessorFinder finder = new TDTeamSuccessorFinder();
		Map<String, List<String>> succ = finder.findSuccessors(scripts);
		
		assertEquals(1, succ.keySet().size());
		
		assertEquals(2, succ.get("lemvusrz").size());
		
		assertTrue(succ.get("lemvusrz").contains("qmislbvx"));
		assertTrue(succ.get("lemvusrz").contains("osatcewo"));
	}
	
	@Test
	public void testFindSuccessorsNone() throws Exception {
		Set<String> scripts = new HashSet<>();
		scripts.add("lemvusrz");
		scripts.add("qmislbvx");
		//scripts.add("osatcewo");
		//scripts.add("lxpyligx");
		
		scripts.add("pwrjydht");
		
		TDTeamSuccessorFinder finder = new TDTeamSuccessorFinder();
		Map<String, List<String>> succ = finder.findSuccessors(scripts);
		
		assertEquals(0, succ.keySet().size());
	}
}
