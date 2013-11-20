package edu.oregonstate.cs519.touchdevelop.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		Set<String> keys = map.keySet();
		for (String key : keys) {
			Object thing = map.get(key);
			expandProperty(key, thing);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object getProperty(String name) {
		Object object = map.get(name);
		
		return object;
	}

	public Object expandProperty(String propertyName, Object contents) {
		if (contents instanceof List) {
			List temp = new ArrayList<ASTNode>();
			for (Object item : (List) contents) {
				temp.add(new ASTNode((Map)item));
			}
			map.put(propertyName, temp);
			contents = temp;
		}
		
		if (contents instanceof Map) {
			contents = new ASTNode((Map) contents);
			map.put(propertyName,contents);
		}
		return contents;
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
		return getProperty(ID).toString();
	}
}
