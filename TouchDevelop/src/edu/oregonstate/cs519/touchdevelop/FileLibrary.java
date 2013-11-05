package edu.oregonstate.cs519.touchdevelop;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
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
		knowScript(script);
		next.addScript(script);
	}

	public void knowScript(Script script) {
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
	public List<Script> getKnownScripts() {
		final ArrayList<Script> scripts = new ArrayList<Script>();
		try {
			Files.walkFileTree(directory, new FileVisitor<Path>() {

				@Override
				public FileVisitResult preVisitDirectory(Path dir,
						BasicFileAttributes attrs) throws IOException {
					// TODO Auto-generated method stub
					return FileVisitResult.CONTINUE;
				}

				@SuppressWarnings("unchecked")
				@Override
				public FileVisitResult visitFile(Path file,
						BasicFileAttributes attrs) throws IOException {
					byte[] bytes = Files.readAllBytes(file);
					String content = new String(bytes);
					scripts.add(new Script((Map<String,Object>) JSONValue.parse(content)));
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file, IOException exc)
						throws IOException {
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc)
						throws IOException {
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return scripts;
	}

	@Override
	public List<Script> getAllScripts() {
		List<Script> allScripts = next.getAllScripts();
		for (Script script : allScripts) {
			knowScript(script);
		}
		return allScripts;
	}
	
}
