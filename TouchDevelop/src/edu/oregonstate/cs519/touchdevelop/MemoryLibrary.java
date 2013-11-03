package edu.oregonstate.cs519.touchdevelop;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryLibrary implements ScriptManager {

	private static MemoryLibrary instance = null;

	private Map<String, Script> library;

	private FileLibrary fileLibrary;

	private ScriptManager next;

	public MemoryLibrary(ScriptManager next) {
		this.next = next;
		library = new HashMap<String, Script>();
	}

	public static MemoryLibrary getInstance() {
		if (instance == null)
			instance = new MemoryLibrary(null);
		return instance;
	}

	public void setPersitanceDestination(String location) {
		fileLibrary = new FileLibrary(location, new NullLibrary());
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
		if (script == null)
			script = next.getScript(id);
		return script;
	}

	public void save() {
		Collection<Script> scripts = library.values();
		for (Script script : scripts) {
			fileLibrary.writeScript(script);
		}
	}

	@Override
	public List<Script> getScripts(int number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Script> getAllScripts() {
		// TODO Auto-generated method stub
		return null;
	}
}
