package edu.oregonstate.cs519.touchdevelop.main;

import java.nio.file.Files;
import java.nio.file.Paths;

import edu.oregonstate.cs519.touchdevelop.ast.ASTNode;
import edu.oregonstate.cs519.touchdevelop.ast.History;

public class ForMergeMain {

	public static void main(String[] args) throws Exception {
		String json1 = new String(Files.readAllBytes(Paths.get("../microedits/sum/v01.json")));
		String json2 = new String(Files.readAllBytes(Paths.get("../microedits/sum/v02.json")));
		
		History history = new History(json1, "a");
		ASTNode program = history.apply();
		History history2 = new History(program, json2, "b");
		ASTNode program2 = history2.apply();
		System.out.println(program2.toJSONString());
		System.out.println("There were " + history2.getConflicts().size() + " conflicts");
	}

}
