package edu.oregonstate.cs519.touchdevelop;

import java.util.HashMap;
import java.util.Map;

public class ScriptLibrary {

	private static ScriptLibrary instance = null;

	private Map<String, Script> library;

	private FileLibrary fileLibrary;

	private ScriptLibrary() {
		library = new HashMap<String, Script>();
	}

	public static ScriptLibrary getInstance() {
		if (instance == null)
			instance = new ScriptLibrary();
		return instance;
	}

	public void setPersitanceDestination(String location) {
		fileLibrary = new FileLibrary(location);
	}

	public void addScript(Script script) {
		library.put(script.getID(), script);
	}

	public Script getScript(String id) {
		Script script = library.get(id);
		if (script == null) {
			script = fileLibrary.getScript(id);
			if (script != null)
				addScript(script);
		}
		if (script == null) {
			script = TouchDevelopAccess.getScript(id);
			if (script != null)
				addScript(script);
		}
		return script;
	}
}
