package edu.oregonstate.cs519.touchdevelop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONValue;

import com.github.kevinsawicki.http.HttpRequest;

/**
 * Class for easily accessing the REST API of the TouchDevelop platform.
 * 
 * It is intended to be used a static function thing.
 * 
 * @author caius
 * 
 */
class TouchDevelopAccess {

	private static final String API_ROOT = "http://www.touchdevelop.com/api/";

	private TouchDevelopAccess() {
	}

	/**
	 * Gets the first list of scripts returned by the TouchDevelop API.
	 * 
	 * @return a list of scripts
	 */
	public static List<Script> getScripts() {
		String call = API_ROOT + "scripts";
		Map<String, Object> map = doCall(call);
		List<Script> scripts = getScriptsFromMap(map);
		return scripts;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static List<Script> getScriptsFromMap(Map<String, Object> map) {
		List<Map> listOfMapScripts = (List<Map>) map.get("items");
		List<Script> scripts = new ArrayList<Script>();
		for (Map<String, Object> mapScript : listOfMapScripts) {
			scripts.add(new Script(mapScript));
		}
		return scripts;
	}

	@SuppressWarnings("unchecked")
	private static Map<String, Object> doCall(String call) {
		String response = HttpRequest.get(call).body();
		Map<String, Object> map = (Map<String, Object>) JSONValue.parse(response);
		return map;
	}

	public static List<Script> getScripts(int number) {
		List<Script> scripts = new ArrayList<Script>();
		String continuation = null;
		while(scripts.size() < number) {
			continuation = getScripts(scripts, continuation);
		}
		return scripts.subList(0, number);
	}

	private static String getScripts(List<Script> scripts, String continuation) {
		String call = API_ROOT + "scripts";
		if (continuation != null)
			call = call + "?continuation=" + continuation;
		Map<String, Object> responseMap = doCall(call);
		List<Script> currentScripts = getScriptsFromMap(responseMap);
		scripts.addAll(currentScripts);
		return (String) responseMap.get("continuation");
	}

	public static List<Script> getSuccessors(String scriptID) {
		String call = API_ROOT + scriptID + "/successors";
		Map<String, Object> responseMap = doCall(call);
		List<Script> successors = getScriptsFromMap(responseMap);
		return successors;
	}

	public static Script getScript(String scriptID) {
		String call = API_ROOT + scriptID;
		Map<String, Object> responseMap = doCall(call);
		return new Script(responseMap);
	}
	
}
