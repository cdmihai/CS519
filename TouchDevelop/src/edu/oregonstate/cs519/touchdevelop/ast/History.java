package edu.oregonstate.cs519.touchdevelop.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONValue;

@SuppressWarnings("rawtypes")
public class History {
	
	private List<Map> listOfOperations;
	private List<Operation> operations;
	private ASTNode program;

	/**
	 * @deprecated Use {@link #History(String,String)} instead
	 */
	@SuppressWarnings({ "unchecked" })
	public History(String historyJSON) {
		this(historyJSON, ASTNode.DEFAULT_OWNER);
	}

	@SuppressWarnings({ "unchecked" })
	public History(String historyJSON, String owner) {
		Object listOfOperations = JSONValue.parse(historyJSON);
		this.listOfOperations = (List<Map>) listOfOperations;
		Map initialEvent = this.listOfOperations.get(0);
		Operation initialOperation = new Operation(initialEvent, owner);
		program = initialOperation.apply();
		
		this.listOfOperations.remove(0);
		operations = new ArrayList<Operation>();
		for (Map operationMap : this.listOfOperations) {
			operations.add(new Operation(operationMap, owner));
		}
	}

	public ASTNode getProgram() {
		return program;
	}

	public ASTNode apply() {
		for (Operation operation : operations) {
			program = operation.apply(program);
		}
		return program;
	}
}
