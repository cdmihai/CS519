package edu.oregonstate.cs519.touchdevelop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Script {

	public static final String SUCCESSORS = "successors";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String USER_ID = "userid";
	public static final String ROOT_ID = "rootid";

	private Map<String, Object> hashMap;
	private List<Script> successors = null;

	public Script(Map<String, Object> hashMap) {
		this.hashMap = hashMap;
	}

	public String getName() {
		return (String) hashMap.get(NAME);
	}

	public String getID() {
		return (String) hashMap.get(ID);
	}

	public String getUserID() {
		return (String) hashMap.get(USER_ID);
	}

	public String getRootID() {
		return (String) hashMap.get(ROOT_ID);
	}

	public Map<String, Object> getHashMap() {
		return hashMap;
	}

	@SuppressWarnings("unchecked")
	public List<Script> getSuccessors() {
		if (successors == null) {
			successors = new ArrayList<Script>();
			List<String> successorsIDs = (List<String>) hashMap.get(SUCCESSORS);
			if (successorsIDs != null) {
				for (String id : successorsIDs) {
					ScriptManager instance = MemoryLibrary.getInstance();
					Script script = instance.getScript(id);
					successors.add(script);
				}
			} else {
				successors = TouchDevelopAccess.getSuccessors(getID());
				successorsIDs = new ArrayList<String>();
				for (Script successor : successors) {
					successorsIDs.add(successor.getID());
				}
				hashMap.put(SUCCESSORS, successorsIDs);
			}
		}
		return successors;
	}
	
	public String getText() {
		String text = TouchDevelopAccess.getText(getID());
		String[] lines = text.split("\n");
		StringBuffer buffer = new StringBuffer();
		for (String line : lines) {
			if (line.startsWith("meta"))
				continue;
			buffer.append(line);
			buffer.append("\n");
		}
		return buffer.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Script))
			return false;
		return ((Script)obj).getID().equals(getID());
	}
}
