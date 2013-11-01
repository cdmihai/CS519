package edu.oregonstate.cs519.touchdevelop.main;

import java.util.ArrayList;
import java.util.List;

import edu.oregonstate.cs519.touchdevelop.Script;
import edu.oregonstate.cs519.touchdevelop.ScriptLibrary;
import edu.oregonstate.cs519.touchdevelop.TouchDevelopAccess;

public class Main {

	public static void main(String[] args) {
		ScriptLibrary instance = ScriptLibrary.getInstance();
		instance.setPersitanceDestination("/Users/caius/Downloads/TouchDevelopScripts");
		
		List<Script> scripts = TouchDevelopAccess.getScripts(2000);
		List<Script> scriptsWithTwoOrMoreSuccessors = new ArrayList<Script>();
		for (Script script : scripts) {
			instance.addScript(script);
			List<Script> successors = script.getSuccessors();
			if (successors.size() > 2);
				scriptsWithTwoOrMoreSuccessors.add(script);
		}
		
		instance.save();
		
		System.out.println("Number of scripts with two or more successors is: " + scriptsWithTwoOrMoreSuccessors.size());
		System.out.println("This is " + scriptsWithTwoOrMoreSuccessors.size()/2000 * 100 + "% of all scripts");
	}

}
