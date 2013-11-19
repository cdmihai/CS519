package edu.oregonstate.cs519.touchdevelop.ast;

import java.util.Map;

public class ASTNode {
	
	public static final String ID = "id";

	private Map<String, Object> map;

	public ASTNode(Map<String, Object> map) {
		this.map = map;
	}

	public Object getProperty(String name) {
		return map.get(name);
	}

}
