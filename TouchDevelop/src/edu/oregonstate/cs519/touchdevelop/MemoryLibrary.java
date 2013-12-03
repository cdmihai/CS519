package edu.oregonstate.cs519.touchdevelop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

public class MemoryLibrary implements ScriptManager {

	private static MemoryLibrary instance = null;

	private Map<String, Script> library;

	private String libraryFile;

	public MemoryLibrary(ScriptManager next) {
		library = new HashMap<String, Script>();
	}
	
	public MemoryLibrary(String libraryFile) {
		this.libraryFile = libraryFile;
		library = new HashMap<String, Script>();
		try {
			byte[] jsonBytes = Files.readAllBytes(Paths.get(libraryFile));
			String jsonString = new String(jsonBytes);
			List<Map> listOfScripts = (List<Map>) JSONValue.parse(jsonString);
			for (Map scriptMap : listOfScripts) {
				Script script = new Script(scriptMap);
				addScript(script);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static MemoryLibrary getInstance() {
		if (instance == null)
			instance = new MemoryLibrary(new NullLibrary());
		return instance;
	}
	
	public void setNext(ScriptManager next) {
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
			script = TouchDevelopAccess.getScript(id);
		}
		return script;
	}

	@Override
	public List<Script> getScripts(int number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Script> getKnownScripts() {
		return (List<Script>) getAllScripts();
	}

	@Override
	public List<Script> getAllScripts() {
		ArrayList<Script> list = new ArrayList<Script>();
		list.addAll(library.values());
		return list;
	}
	
	public void saveScriptsToFile() {
		List<Script> scripts = getAllScripts();
		List<Map> listOfMaps = new ArrayList<Map>();
		int all = scripts.size();
		int current = 0;
		for (Script script : scripts) {
			System.out.println((++current) + "/" + all + ": " + script.getID());
			Map<String, Object> map = script.getHashMap();
			listOfMaps.add(map);
		}
		String jsonString = JSONArray.toJSONString(listOfMaps);
		try {
			Files.write(Paths.get(libraryFile), jsonString.getBytes(), StandardOpenOption.CREATE);
		} catch (IOException e) {
		}
	}
}
