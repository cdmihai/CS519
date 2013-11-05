package edu.oregonstate.cs519.touchdevelop.main;

import java.util.List;

import edu.oregonstate.cs519.touchdevelop.CloudManager;
import edu.oregonstate.cs519.touchdevelop.FileLibrary;
import edu.oregonstate.cs519.touchdevelop.MemoryLibrary;
import edu.oregonstate.cs519.touchdevelop.Script;

public class Main {

	public static void main(String[] args) {
		int noOfScriptsWithMoreThanOneSuccessor = 0;

		MemoryLibrary library = new MemoryLibrary(new FileLibrary("/Users/caius/Downloads/TouchDevelopScripts",new CloudManager()));
		
		List<Script> allScripts = library.getAllScripts();
		for (Script script : allScripts) {
			List<Script> successors = script.getSuccessors();
			if (successors.size() > 1)
				noOfScriptsWithMoreThanOneSuccessor++;
			library.addScript(script);
		}
		
		System.out.println(noOfScriptsWithMoreThanOneSuccessor);
	}

}
