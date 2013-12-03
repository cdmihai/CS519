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
	private String owner;
	
	/**
	 * @deprecated Use {@link #Operation(String,String)} instead
	 */
	@SuppressWarnings("unchecked")
	protected Operation(String jsonString) {
		this(jsonString, null);
	}

	@SuppressWarnings("unchecked")
	protected Operation(String jsonString, String owner) {
		this((Map<String, Object>) JSONValue.parse(jsonString), owner);
	}

	@Deprecated
	public Operation(Map<String, Object> map) {
		this(map, null);
	}

	public Operation(Map<String, Object> map, String owner) {
		this.map = map;
		this.owner = owner;
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
						.get(affectedNode),owner));
			}
		}
	}
	
	public ASTNode apply() {
		return initialProgram;
	}

	public ASTNode apply(ASTNode program) {
		initialProgram = program;
		for (Update update : updates) {
			try {
				update.apply();
			} catch (ConflictException e) {
				e.printStackTrace();
			}
		}
		return initialProgram;
	}
}
