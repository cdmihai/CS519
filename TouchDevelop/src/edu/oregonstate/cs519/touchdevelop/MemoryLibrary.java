package edu.oregonstate.cs519.touchdevelop;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MemoryLibrary implements ScriptManager {

	private static MemoryLibrary instance = null;

	private Map<String, Script> library;

	private FileLibrary fileLibrary;

	private MemoryLibrary() {
		library = new HashMap<String, Script>();
	}

	public static MemoryLibrary getInstance() {
		if (instance == null)
			instance = new MemoryLibrary();
		return instance;
	}

	public void setPersitanceDestination(String location) {
		fileLibrary = new FileLibrary(location);
	}

	/* (non-Javadoc)
	 * @see edu.oregonstate.cs519.touchdevelop.IScriptLibrary#addScript(edu.oregonstate.cs519.touchdevelop.Script)
	 */
	@Override
	public void addScript(Script script) {
		library.put(script.getID(), script);
	}

	/* (non-Javadoc)
	 * @see edu.oregonstate.cs519.touchdevelop.IScriptLibrary#getScript(java.lang.String)
	 */
	@Override
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

	public void save() {
		Collection<Script> scripts = library.values();
		for (Script script : scripts) {
			fileLibrary.writeScript(script);
		}
	}
}
