package edu.oregonstate.cs519.touchdevelop.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Map;

public class TDSuccessorLoaderMain {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("../results/TDSUCC"));
		TDTeamSuccessorFinder finder = (TDTeamSuccessorFinder) ois.readObject();
		
		Map<String, List<String>> successorMapping = finder.getSuccessorMapping();
		
		System.out.println(successorMapping.keySet().size());
		System.out.println(successorMapping);
		
		for (String rootScript : successorMapping.keySet()) {
			List<String> successors = successorMapping.get(rootScript);
			
			for (String successor : successors) {
				System.out.println(successor + " is in files: " + finder.getScriptToFileMapping().get(successor));
			}
			System.out.println("-----------------------------------------------");
		}
	}
}
