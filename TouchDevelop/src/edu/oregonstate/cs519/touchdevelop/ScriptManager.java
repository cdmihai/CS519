package edu.oregonstate.cs519.touchdevelop;

import java.util.List;

public interface ScriptManager {

	public void addScript(Script script);

	public Script getScript(String id);
	
	public List<Script> getScripts(int number);
	
	public List<Script> getAllScripts();

}