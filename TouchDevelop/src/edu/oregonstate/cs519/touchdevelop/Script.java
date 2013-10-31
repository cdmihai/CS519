package edu.oregonstate.cs519.touchdevelop;

import java.util.Map;

public class Script {

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String USER_ID = "userid";
	public static final String ROOT_ID = "rootid";
	
	private Map<String, Object> hashMap;

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
}
