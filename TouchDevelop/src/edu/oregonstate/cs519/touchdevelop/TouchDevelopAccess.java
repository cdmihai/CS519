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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Script> getScripts() {
		String call = API_ROOT + "scripts";
		Map<String, Object> map = doCall(call);
		List<Map> listOfMapScripts = (List<Map>) map.get("items");
		List<Script> scripts = new ArrayList<Script>();
		for (Map<String, Object> mapScript : listOfMapScripts) {
			scripts.add(new Script(mapScript));
		}
		return scripts;
	}

	private static Map<String, Object> doCall(String call) {
		String response = HttpRequest.get(call).body();
		Map<String, Object> map = (Map<String, Object>) JSONValue.parse(response);
		return map;
	}
	
}
