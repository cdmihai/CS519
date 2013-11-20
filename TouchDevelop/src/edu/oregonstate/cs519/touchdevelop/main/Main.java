package edu.oregonstate.cs519.touchdevelop.main;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONValue;

import edu.oregonstate.cs519.touchdevelop.ast.ASTNode;

public class Main {

	public static void main(String[] args) throws Exception {
		
		String json = "";
		
		List<Map<String,Object>> listOfEvents = (List<Map<String, Object>>) JSONValue.parse(json);
		for (Map<String, Object> event : listOfEvents) {
			Map<String, Object> ast = (Map<String, Object>) event.get(ASTNode.AST);
			if (ast != null) {
				
				continue;
			}
			
		}
	}

}
