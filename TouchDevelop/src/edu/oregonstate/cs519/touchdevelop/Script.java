package edu.oregonstate.cs519.touchdevelop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Script {

	public static final String SUCCESSORS = "successors";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String USER_ID = "userid";
	public static final String ROOT_ID = "rootid";
	public static final String TEXT = "text";

	private Map<String, Object> hashMap;
	private List<Script> successors = null;

	public Script(Map<String, Object> hashMap) {
		this.hashMap = hashMap;
	}
	
	public Script(String scriptID) {
		Map<String, Object> map = new HashMap<>();
		map.put(Script.ID, scriptID);
		
		this.hashMap = map;
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
		String text = (String) hashMap.get(TEXT);
		if (text != null)
			return text;

		text = TouchDevelopAccess.getText(getID());
		String[] lines = text.split("\n");
		StringBuffer buffer = new StringBuffer();
		for (String line : lines) {
			if (line.startsWith("meta"))
				continue;
			buffer.append(line);
			buffer.append("\n");
		}
		String finalText = buffer.toString();
		hashMap.put(TEXT, finalText);
		return finalText;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Script))
			return false;
		return ((Script) obj).getID().equals(getID());
	}

	public int successorTreeDepth() {
		ArrayList<Integer> depths = new ArrayList<>();

		for (Script successor : getSuccessors()) {
			depths.add(successor.successorTreeDepth());
		}

		int maxDepth = depths.isEmpty() ? 0 : Collections.max(depths);

		return 1 + maxDepth;
	}

	public int successorTreeWidth() {
		ArrayList<Integer> widths = new ArrayList<>();

		for (Script successor : getSuccessors()) {
			widths.add(successor.successorTreeWidth());
		}

		int maxSuccWidth = widths.isEmpty() ? 0 : Collections.max(widths);

		return Math.max(1, Math.max(getSuccessors().size(), maxSuccWidth));
	}

	public Set<Script> getTransitiveSuccessors() {
		Set<Script> collectedSuccessors = new HashSet<>();
		
		collectTransitiveSuccessorsFor(this, collectedSuccessors);
		
		return collectedSuccessors;
	}

	private void collectTransitiveSuccessorsFor(Script rootScript, Set<Script> collectedSuccessors) {
		collectedSuccessors.addAll(rootScript.getSuccessors());
		
		for (Script script : rootScript.getSuccessors()) {
			collectTransitiveSuccessorsFor(script, collectedSuccessors);
		}
	}
}
