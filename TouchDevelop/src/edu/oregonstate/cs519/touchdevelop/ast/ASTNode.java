package edu.oregonstate.cs519.touchdevelop.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class ASTNode {
	
	public static final String ID = "id";
	public static final String AST = "ast";
	public static final String DECLARATIONS = "decls";
	public static final String BODY = "body";

	private Map<String, Object> map;
	
	@SuppressWarnings("unchecked")
	public ASTNode(String JSONString) {
		map = (Map<String, Object>) JSONValue.parse(JSONString);
	}

	public ASTNode(Map<String, Object> map) {
		this.map = map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object getProperty(String name) {
		Object object = map.get(name);
		
		if (object instanceof List) {
			List temp = new ArrayList<ASTNode>();
			for (Object item : (List) object) {
				temp.add(new ASTNode((Map)item));
			}
			object = temp;
		}
		
		if (object instanceof Map) {
			object = new ASTNode((Map) object);
		}
		
		return object;
	}

	public void updateProperty(String name, String newProperty) {
		map.put(name, newProperty);
	}
	
	public String getJSON() {
		return JSONObject.toJSONString(map);
	}
}
