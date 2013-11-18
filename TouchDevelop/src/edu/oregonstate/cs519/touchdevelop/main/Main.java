package edu.oregonstate.cs519.touchdevelop.main;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jgit.api.MergeResult;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import edu.oregonstate.cs519.gitmerge.GitMerger;
import edu.oregonstate.cs519.touchdevelop.CloudManager;
import edu.oregonstate.cs519.touchdevelop.FileLibrary;
import edu.oregonstate.cs519.touchdevelop.MemoryLibrary;
import edu.oregonstate.cs519.touchdevelop.Script;

public class Main {

	public static void main(String[] args) throws Exception {
		
		MemoryLibrary library = new MemoryLibrary("../results/TDScripts.json");
		
		List<String> lines = Files.readAllLines(Paths.get("../results/conflict-100-mr.txt"), Charset.defaultCharset());
		for (String line : lines) {
			String[] split = line.split(",");
			String aScriptID = split[0];
			String bScriptID = split[1];
			
			Script aScript = library.getScript(aScriptID);
			Script bScript = library.getScript(bScriptID);

			String baseScriptID = aScript.getRootID();
			Script baseScript = library.getScript(baseScriptID);
			
			try {
				MergeResult mergeResult = GitMerger.merge(baseScript.getText(), aScript.getText(), bScript.getText(), false, aScriptID + "-" + bScriptID);
				System.out.println(aScriptID + "+" + bScriptID + " => " + mergeResult.getMergeStatus());
			} catch (NullPointerException e) {
				System.out.println("Aborted: " + aScriptID + "+" + baseScriptID);
			}
		}
	}

}
