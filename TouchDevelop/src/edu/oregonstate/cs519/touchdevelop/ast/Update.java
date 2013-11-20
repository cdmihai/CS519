package edu.oregonstate.cs519.touchdevelop.ast;

import java.util.Map;
import java.util.Set;

public class Update {

	private Map<String, Object> updates;
	private ASTNode affectedNode;

	public Update(ASTNode affectedNode, Map<String, Object> updates) {
		this.affectedNode = affectedNode;
		this.updates = updates;
	}
	
	public void apply() {
		Set<String> keys = updates.keySet();
		for (String property: keys) {
			affectedNode.updateProperty(property, (String) updates.get(property));
		}
	}
}
