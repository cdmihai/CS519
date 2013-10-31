package edu.oregonstate.cs519.touchdevelop;

import java.util.Map;

public class Script {

	public static final String NAME = "name";
	private String scriptName;

	public Script(Map<String, String> hashMap) {
		scriptName = hashMap.get(NAME);
	}

	public Object getName() {
		return scriptName;
	}
	
}
