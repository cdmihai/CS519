package edu.oregonstate.cs519.touchdevelop.main;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.eclipse.jgit.api.MergeResult;

import edu.oregonstate.cs519.gitmerge.GitMerger;
import edu.oregonstate.cs519.touchdevelop.CloudManager;
import edu.oregonstate.cs519.touchdevelop.FileLibrary;
import edu.oregonstate.cs519.touchdevelop.MemoryLibrary;
import edu.oregonstate.cs519.touchdevelop.Script;

public class Main {

	public static void main(String[] args) throws Exception {

		FileLibrary fileLibrary = new FileLibrary("/Volumes/RAM Disk/TouchDevelopScripts",new CloudManager());
		MemoryLibrary library = MemoryLibrary.getInstance();
		library.setNext(fileLibrary);
		
		List<String> lines = Files.readAllLines(Paths.get("good.txt"), Charset.defaultCharset());
		for (String scriptID : lines) {
			Script script = library.getScript(scriptID);
			List<Script> successors = script.getSuccessors();
			String base = script.getText();
			for (Script successor : successors) {
				String a = successor.getText();
				for (Script successor2 : successors) {
					if (successor2.equals(successor))
						continue;
					String b = successor2.getText();
					MergeResult result = GitMerger.merge(base, a, b);
					System.out.println(successor.getID() + " with " + successor2.getID() + " => " + result.getMergeStatus().toString());
				}
			}
		}
		
	}

}
