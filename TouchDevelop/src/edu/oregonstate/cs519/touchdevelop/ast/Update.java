package edu.oregonstate.cs519.touchdevelop.ast;

import java.util.Map;
import java.util.Set;

public class Update {

	private Map<String, Object> updates;
	private String affectedNodeID;
	private String owner;

	@Deprecated
	public Update(String affectedNode, Map<String, Object> updates) {
		this(affectedNode, updates, ASTNode.DEFAULT_OWNER);
	}

	public Update(String affectedNode, Map<String, Object> updates, String owner) {
		this.affectedNodeID = affectedNode;
		this.updates = updates;
		this.owner = owner;
	}
	
	public void apply() {
		ASTNode affectedNode = getNode(affectedNodeID);
		if (updates == null) {
			affectedNode.delete();
			return;
		}
		Set<String> keys = updates.keySet();
		for (String property: keys) {
			affectedNode.updateProperty(property, updates.get(property),owner);
		}
	}

	private ASTNode getNode(String affectedNodeID) {
		ASTNode node = ASTNodeManager.getInstance().getNode(affectedNodeID);
		if (node == null)
			node = new ASTNode("{\"id\":\"" + affectedNodeID + "\"}",owner);
		return node;
	}
}
