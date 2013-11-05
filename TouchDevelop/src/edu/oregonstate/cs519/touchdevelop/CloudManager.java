package edu.oregonstate.cs519.touchdevelop;

import java.util.ArrayList;
import java.util.List;

public class CloudManager implements ScriptManager {

	@Override
	public void addScript(Script script) {
	}

	@Override
	public Script getScript(String id) {
		return TouchDevelopAccess.getScript(id);
	}

	@Override
	public List<Script> getScripts(int number) {
		return TouchDevelopAccess.getScripts(number);
	}

	@Override
	public List<Script> getKnownScripts() {
		return new ArrayList<Script>();
	}

	@Override
	public List<Script> getAllScripts() {
		return TouchDevelopAccess.getAllScripts();
	}

}
