package edu.oregonstate.cs519.touchdevelop.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONValue;

public class Operation {

	private Map<String, Object> map;
	private ASTNode initialProgram;
	private List<Update> updates;
	
	@SuppressWarnings("unchecked")
	protected Operation(String jsonString) {
		this((Map<String, Object>) JSONValue.parse(jsonString));
	}

	public Operation(Map<String, Object> map) {
		this.map = map;
		updates = new ArrayList<Update>();
		createUpdates();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
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
	
	public ASTNode apply() {
		return initialProgram;
	}

	public ASTNode apply(ASTNode node) {
		for (Update update : updates) {
			update.apply();
		}
		return initialProgram;
	}
}
