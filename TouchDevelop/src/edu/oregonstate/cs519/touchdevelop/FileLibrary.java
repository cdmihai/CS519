package edu.oregonstate.cs519.touchdevelop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import org.json.simple.JSONObject;

public class FileLibrary {

	private Path directory;

	public FileLibrary(String string) {
		try {
			Path dirPath = Paths.get(string);
			directory = Files.createDirectory(dirPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeScript(Script script) {
		Map<String, Object> map = script.getHashMap();
		String string = JSONObject.toJSONString(map);
		try {
			Path filePath = directory.resolve(Paths.get(script.getName()));
			Path file = Files.createFile(filePath);
			Files.write(file, string.getBytes(), StandardOpenOption.WRITE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
