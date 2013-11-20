package edu.oregonstate.cs519.touchdevelop.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class ASTNode implements JSONAware {
	
	public static final String ID = "id";
	public static final String AST = "ast";
	public static final String DECLARATIONS = "decls";
	public static final String BODY = "body";

	private Map<String, Object> map;
	
	@SuppressWarnings("unchecked")
	public ASTNode(String JSONString) {
		this((Map<String, Object>) JSONValue.parse(JSONString));
	}

	public ASTNode(Map<String, Object> map) {
		this.map = map;
		ASTNodeManager.getInstance().addNode(this);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object getProperty(String name) {
		Object object = map.get(name);
		
		if (object instanceof List) {
			List temp = new ArrayList<ASTNode>();
			for (Object item : (List) object) {
				temp.add(new ASTNode((Map)item));
			}
			map.put(name, temp);
			object = temp;
		}
		
		if (object instanceof Map) {
			object = new ASTNode((Map) object);
			map.put(name,object);
		}
		
		return object;
	}

	public void updateProperty(String name, String newProperty) {
		map.put(name, newProperty);
	}
	
	public String getJSON() {
		return JSONObject.toJSONString(map);
	}
	
	@Override
	public String toJSONString() {
		return JSONObject.toJSONString(map);
	}
	
	@Override
	public String toString() {
		return toJSONString();
	}
}
