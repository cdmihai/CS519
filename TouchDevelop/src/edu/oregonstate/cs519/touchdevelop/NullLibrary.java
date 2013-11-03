package edu.oregonstate.cs519.touchdevelop;

import java.util.ArrayList;
import java.util.List;

public class NullLibrary implements ScriptManager {

	@Override
	public void addScript(Script script) {
	}

	@Override
	public Script getScript(String id) {
		return null;
	}

	@Override
	public List<Script> getScripts(int number) {
		return new ArrayList<Script>();
	}

	@Override
	public List<Script> getAllScripts() {
		return new ArrayList<Script>();
	}

}
