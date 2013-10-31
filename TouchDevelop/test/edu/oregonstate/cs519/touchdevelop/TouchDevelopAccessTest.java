package edu.oregonstate.cs519.touchdevelop;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class TouchDevelopAccessTest {
	
	@Test
	public void testGetScripts() {
		List<Script> scripts = (List<Script>) TouchDevelopAccess.getScripts();
		assertTrue(scripts.size() > 0);
	}
	
	@Test
	public void testGetNumberOfScripts() {
		List<Script> scripts = TouchDevelopAccess.getScripts(50);
		assertEquals(50, scripts.size());
	}
	
	@Test
	public void testGetDifferentNumberOfScripts() {
		List<Script> scripts = TouchDevelopAccess.getScripts(52);
		assertEquals(52, scripts.size());
	}
	
	@Test
	public void testGetScriptSuccessors() {
		List<Script> scripts = TouchDevelopAccess.getSuccessors("qoipfdvx");
		assertEquals(1, scripts.size());
		assertEquals("qfbr",scripts.get(0).getID());
	}
}
