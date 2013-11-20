package edu.oregonstate.cs519.touchdevelop.ast;

import java.util.Map;
import java.util.Set;

public class Update {

	private Map<String, Object> updates;
	private String affectedNodeID;

	public Update(String affectedNode, Map<String, Object> updates) {
		this.affectedNodeID = affectedNode;
		this.updates = updates;
	}
	
	public void apply() {
		ASTNode affectedNode = getNode(affectedNodeID);
		Set<String> keys = updates.keySet();
		for (String property: keys) {
			affectedNode.updateProperty(property, updates.get(property));
		}
	}

	private ASTNode getNode(String affectedNodeID) {
		ASTNode node = ASTNodeManager.getInstance().getNode(affectedNodeID);
		return node;
	}
}
