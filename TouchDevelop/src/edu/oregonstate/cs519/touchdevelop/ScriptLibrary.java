package edu.oregonstate.cs519.touchdevelop;

import java.util.HashMap;
import java.util.Map;


public class ScriptLibrary {
	
	private static ScriptLibrary instance = null;
	
	private Map<String, Script> library;
	
	private ScriptLibrary() {
		library = new HashMap<String, Script>();
	}

	public static ScriptLibrary getInstance() {
		if (instance == null)
			instance = new ScriptLibrary();
		return instance;
	}

	public void addScript(Script script) {
		library.put(script.getID(), script);
	}

	public Script getScript(String id) {
		return library.get(id);
	}

}
