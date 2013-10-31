package edu.oregonstate.cs519.touchdevelop;

import java.util.Map;

public class Script {

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String USER_ID = "userid";
	public static final String ROOT_ID = "rootid";
	
	private String scriptName;
	private String id;
	private String userID;
	private String rootID;

	public Script(Map<String, Object> hashMap) {
		scriptName = (String) hashMap.get(NAME);
		id = (String) hashMap.get(ID);
		userID = (String) hashMap.get(USER_ID);
		rootID = (String) hashMap.get(ROOT_ID);
	}

	public String getName() {
		return scriptName;
	}

	public String getID() {
		return id;
	}

	public String getUserID() {
		return userID;
	}

	public String getRootID() {
		return rootID;
	}
}
