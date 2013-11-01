package edu.oregonstate.cs519.touchdevelop.main;

import java.util.ArrayList;
import java.util.List;

import edu.oregonstate.cs519.touchdevelop.Script;
import edu.oregonstate.cs519.touchdevelop.ScriptLibrary;
import edu.oregonstate.cs519.touchdevelop.TouchDevelopAccess;

public class Main {

	public static void main(String[] args) {
		int noOfScripts = 5000;
		
		ScriptLibrary instance = ScriptLibrary.getInstance();
		instance.setPersitanceDestination("/Users/caius/Downloads/TouchDevelopScripts");
		
		List<Script> scripts = TouchDevelopAccess.getScripts(noOfScripts);
		List<Script> scriptsWithTwoOrMoreSuccessors = new ArrayList<Script>();
		for (Script script : scripts) {
			instance.addScript(script);
			List<Script> successors = script.getSuccessors();
			if (successors.size() > 1)
				scriptsWithTwoOrMoreSuccessors.add(script);
		}
		
		instance.save();
		
		System.out.println("Number of scripts with two or more successors is: " + scriptsWithTwoOrMoreSuccessors.size());
		System.out.println("This is " + scriptsWithTwoOrMoreSuccessors.size()/noOfScripts * 100 + "% of all scripts");
	}

}