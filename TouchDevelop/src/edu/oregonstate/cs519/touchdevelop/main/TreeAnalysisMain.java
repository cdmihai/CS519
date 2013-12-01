package edu.oregonstate.cs519.touchdevelop.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import edu.oregonstate.cs519.touchdevelop.MemoryLibrary;
import edu.oregonstate.cs519.touchdevelop.Script;

public class TreeAnalysisMain {
	private static final String RESULTS_FILE = "../results/trees.csv";

	public static void main(String[] args) throws IOException {
		MemoryLibrary library = new MemoryLibrary("../results/TDScripts.json");
		List<Script> rootScripts = new ArrayList<>();
		
		writeToFile("rootScript, transitiveSuccessors, treeDepth, treeWidth\n", StandardOpenOption.CREATE);
		
		long start = System.currentTimeMillis();

		System.out.println("getting roots ...");
		
		for (Script script : library.getAllScripts()) {
			if (script.getRootID().equals(script.getID()))
				rootScripts.add(script);
		}
		
		System.out.println("getting data about " + rootScripts.size() + " trees ...");
		
		for (Script script : rootScripts) {
			System.out.println("getting info about root: " + script.getID());
			
			if(script.getSuccessors().isEmpty()){
				System.err.println("\t 0 successors, skipping to next root script");
				continue;
			}

			StringBuffer line = new StringBuffer();
			
			line.append(script.getID());
			line.append(",");
			
			System.out.println("\t computting transitive successors");
			line.append(script.getTransitiveSuccessors().size());
			line.append(",");
			
			System.out.println("\t getting depth");
			line.append(script.successorTreeDepth());
			line.append(",");
			
			System.out.println("\t getting width");
			line.append(script.successorTreeWidth());
			
			line.append("\n");
			
			writeToFile(line.toString(), StandardOpenOption.APPEND);
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("\nfinished in " + (end - start) / 1000 + "seconds");
		
	}

	private static void writeToFile(String string, StandardOpenOption openOption) throws IOException {
		Files.write(Paths.get(RESULTS_FILE), string.getBytes(), openOption);
	}
}
