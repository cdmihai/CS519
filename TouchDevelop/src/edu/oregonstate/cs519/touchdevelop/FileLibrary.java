package edu.oregonstate.cs519.touchdevelop;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

class FileLibrary implements ScriptManager {

	private Path directory;
	private ScriptManager next;
	
	public FileLibrary(String string, ScriptManager next) {
		this.next = next;
		Path dirPath = Paths.get(string);
		try {
			directory = Files.createDirectory(dirPath);
		} catch (FileAlreadyExistsException e) {
			directory = dirPath;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addScript(Script script) {
		next.addScript(script);
		Map<String, Object> map = script.getHashMap();
		String string = JSONObject.toJSONString(map);
			String scriptID = script.getID();
			Path filePath = resolveScriptPath(scriptID);
			try {
				Files.createFile(filePath);
			} catch (FileAlreadyExistsException e) {
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Files.write(filePath, string.getBytes(), StandardOpenOption.WRITE);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	private Path resolveScriptPath(String scriptID) {
		return directory.resolve(Paths.get(scriptID));
	}

	@SuppressWarnings("unchecked")
	public Script getScript(String scriptID) {
		Path filePath = resolveScriptPath(scriptID);
		byte[] bytes;
		try {
			bytes = Files.readAllBytes(filePath);
		} catch (NoSuchFileException e){
			return next.getScript(scriptID);
		} catch (IOException e) {
			return null;
		}
		String scriptJSON = new String(bytes);
		Map<String, Object> scriptMap = (Map<String, Object>) JSONValue.parse(scriptJSON);
		return new Script(scriptMap);
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
