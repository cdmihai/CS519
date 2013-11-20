package edu.oregonstate.cs519.touchdevelop.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Operation {

	private Map<String, Object> map;
	private ASTNode initialProgram;
	private List<Update> updates;

	public Operation(Map<String, Object> map) {
		this.map = map;
		updates = new ArrayList<Update>();
		createUpdates();
	}

	private void createUpdates() {
		Map mapOfUpdates = (Map) map.get("updates");
		if (mapOfUpdates == null) {
			Map astOfProgram = (Map) map.get("ast");
			initialProgram = new ASTNode(astOfProgram);
		} else {
			Set<String> affectedNodes = mapOfUpdates.keySet();
			for (String affectedNode : affectedNodes) {
				updates.add(new Update(affectedNode, (Map<String, Object>) mapOfUpdates
						.get(affectedNode)));
			}
		}
	}

	public void apply() {
	}
}
