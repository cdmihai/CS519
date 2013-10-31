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
}
