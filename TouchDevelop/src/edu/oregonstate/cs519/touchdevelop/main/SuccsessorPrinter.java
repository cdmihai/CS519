package edu.oregonstate.cs519.touchdevelop.main;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import edu.oregonstate.cs519.touchdevelop.Script;

public class SuccsessorPrinter {
	public static void main(String[] args) {
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put(Script.ID, "yvph");

		Set<Script> transitiveSuccessors = new Script(hashMap).getTransitiveSuccessors();

		for (Script script : transitiveSuccessors) {
			List<Script> successors = script.getSuccessors();

			if (successors.size() < 2)
				continue;

			System.out.println(script.getID());

			for (Script successor : successors) {
				System.out.println("\t" + successor.getID());
			}

			System.out.println("\n");
		}
	}
}
