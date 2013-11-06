package edu.oregonstate.cs519.touchdevelop.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import edu.oregonstate.cs519.touchdevelop.CloudManager;
import edu.oregonstate.cs519.touchdevelop.FileLibrary;
import edu.oregonstate.cs519.touchdevelop.MemoryLibrary;
import edu.oregonstate.cs519.touchdevelop.Script;

public class Main {

	public static void main(String[] args) {
		int noOfScriptsWithMoreThanOneSuccessor = 0;

		FileLibrary fileLibrary = new FileLibrary("/Users/caius/Downloads/TouchDevelopScripts",new CloudManager());
		MemoryLibrary library = MemoryLibrary.getInstance();
		library.setNext(fileLibrary);
		
		List<Script> knownScripts = fileLibrary.getKnownScripts();
		int all = knownScripts.size();
		List<Script> scriptsWithMoreThanTwoSuccessors = new ArrayList<Script>();
		int current = 0;
		for (Script script : knownScripts) {
			current++;
			System.out.println("Processing " + current + "/" + all);
			List<Script> successors = script.getSuccessors();
			if (successors.size() > 1)
				scriptsWithMoreThanTwoSuccessors.add(script);
		}
		
		System.out.println((float)(scriptsWithMoreThanTwoSuccessors.size())/(float)(knownScripts.size()));
		
		for (Script script : scriptsWithMoreThanTwoSuccessors) {
			try {
				Path filePath = Paths.get("good.txt");
				if (!Files.exists(filePath))
					Files.createFile(filePath);
				Files.write(filePath, script.getID().getBytes(), StandardOpenOption.APPEND);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
