package edu.oregonstate.cs519.touchdevelop.ast;

import java.util.ArrayList;
import java.util.HashMap;
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
	public static final String DEFAULT_OWNER = "default";
	
	private ASTNode parent;
	private HashMap<String,String> propertiesChanged;
	
	private Map<String, Object> map;
	
	@SuppressWarnings("unchecked")
	public ASTNode(String JSONString) {
		this((Map<String, Object>) JSONValue.parse(JSONString));
	}

	public ASTNode(Map<String, Object> map, ASTNode parent) {
		this.map = map;
		this.parent = parent;
		ASTNodeManager.getInstance().addNode(this);
		Set<String> keys = map.keySet();
		for (String key : keys) {
			Object thing = map.get(key);
			expandProperty(key, thing);
		}
		propertiesChanged = new HashMap<String,String>();
	}
	
	public ASTNode(Map<String, Object> map) {
		this(map ,null);
	}

	public Object getProperty(String name) {
		Object object = map.get(name);
		return object;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object expandProperty(String propertyName, Object contents) {
		
		//list of nodes
		if (contents instanceof List) {
			List temp = new ArrayList<ASTNode>();
			for (Object item : (List) contents) {
				if (item instanceof Map) //actual nodes
					temp.add(new ASTNode((Map)item, this));
				else if (item instanceof String) { // references to other nodes
					ASTNode node = ASTNodeManager.getInstance().getNode((String) item);
					temp.add(node);
				}
			}
			map.put(propertyName, temp);
			contents = temp;
		}
		
		//actual node
		if (contents instanceof Map) {
			contents = new ASTNode((Map) contents, this);
			map.put(propertyName,contents);
		}
		return contents;
	}

	public void updateProperty(String name, Object newProperty) {
	}

	public void updateProperty(String name, Object newProperty, String origin) {
		Object expandedProperty = expandProperty(name, newProperty);
		map.put(name, expandedProperty);
		propertiesChanged.put(name, origin);
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

	public void delete() {
		parent.removeNode(this);
		ASTNodeManager.getInstance().deleteNode(this);
	}

	@SuppressWarnings("rawtypes")
	private void removeNode(ASTNode astNode) {
		Set<String> keys = map.keySet();
		ASTNode toRemove = null;
		for (String key : keys) {
			Object thing = map.get(key);
			if (thing instanceof ASTNode)
				if (thing == astNode) {
					toRemove = (ASTNode) thing;
					break;
				}
			if (thing instanceof List) {
				List list = (List) thing;
				for (Object element : list) {
					if (element instanceof ASTNode)
						if (element == astNode) {
							toRemove = (ASTNode) element;
							break;
						}
				}
				if (toRemove != null) {
					list.remove(toRemove);
					return;
				}
			}
		}
		map.remove(toRemove);
	}
	
	public ASTNode getParent() {
		return parent;
	}

	public boolean isNodeChanged() {
		return !propertiesChanged.isEmpty();
	}

	public boolean isPropertyChanged(String property) {
		return propertiesChanged.keySet().contains(property);
	}

	public String getOwner(String propertyName) {
		return propertiesChanged.get(propertyName);
	}
}
