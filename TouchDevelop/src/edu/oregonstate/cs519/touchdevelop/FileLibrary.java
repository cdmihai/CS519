package edu.oregonstate.cs519.touchdevelop;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

class FileLibrary {

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

	public void writeScript(Script script) {
		Map<String, Object> map = script.getHashMap();
		String string = JSONObject.toJSONString(map);
		try {
			String scriptID = script.getID();
			Path filePath = resolveScriptPath(scriptID);
			Path file = Files.createFile(filePath);
			Files.write(file, string.getBytes(), StandardOpenOption.WRITE);
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
		} catch (IOException e) {
			return null;
		}
		String scriptJSON = new String(bytes);
		Map<String, Object> scriptMap = (Map<String, Object>) JSONValue.parse(scriptJSON);
		return new Script(scriptMap);
	}
	
}
