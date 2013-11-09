package edu.oregonstate.cs519.touchdevelop;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class TouchDevelopAccessTest {
	
	@Test
	public void testGetScripts() {
		List<Script> scripts = (List<Script>) TouchDevelopAccess.getScripts();
		assertTrue(scripts.size() > 0);
	}
	
	@Test
	public void testGetNumberOfScripts() {
		List<Script> scripts = TouchDevelopAccess.getScripts(50);
		assertEquals(50, scripts.size());
	}
	
	@Test
	public void testGetDifferentNumberOfScripts() {
		List<Script> scripts = TouchDevelopAccess.getScripts(52);
		assertEquals(52, scripts.size());
	}
	
	@Test
	public void testGetScriptSuccessors() {
		List<Script> scripts = TouchDevelopAccess.getSuccessors("qoipfdvx");
		assertEquals(1, scripts.size());
		assertEquals("qfbr",scripts.get(0).getID());
	}
	
	@Test
	public void getParticularScript() {
		Script script = TouchDevelopAccess.getScript("qoipfdvx");
		assertEquals("qoipfdvx", script.getID());
		assertEquals("Bike maintenance", script.getName());
		assertEquals("qoipfdvx",script.getRootID());
		assertEquals("antga",script.getUserID());
	}
	
	@Test
	public void testGetTextForScript() {
		String text = TouchDevelopAccess.getText("nkil");
		String expected = "meta version \"v2.2,js,ctx\";\n" + 
				"meta name \"Bike maintenance\";\n" + 
				"meta icon \"Wheel\";\n" + 
				"meta color \"#ff007fff\";\n" + 
				"meta rootId \"xYRAvgUHmQaEWAhboZVw0kz7\";\n" + 
				"meta hasUniqueIds \"yes\";\n" + 
				"meta platform \"current\";\n" + 
				"#WHjZi3SYPs7CNaUWWwC246kk\n" + 
				"table Bike {\n" + 
				"  type = \"Object\";\n" + 
				"  fields {\n" + 
				"    #FdjRpX2tusyUodi2Ibrd4NbR make : String_field\n" + 
				"    #jJScqCqF0mSJWQ3HVVbKsBkt model : String_field\n" + 
				"  }\n" + 
				"}\n" + 
				"meta import controls {\n" + 
				"  pub \"mzwz\"\n" + 
				"  usage {\n" + 
				"\n" + 
				"    action button(text: String, tapped: Action)\n" + 
				"    action checkbox(text: String, value: Boolean, on_value_change: Boolean_Action)\n" + 
				"    action show_example()\n" + 
				"    action input_string_collection(label: String, strings: String_Collection)\n" + 
				"    action input_text_line(label: String, value: String, on_value_change: Text_Action)\n" + 
				"    action input_text_area(label: String, value: String, on_value_change: Text_Action)\n" + 
				"    action input_password(label: String, value: String, on_value_change: Text_Action)\n" + 
				"    action input_number(label: String, value: Number, on_value_change: Number_Action)\n" + 
				"    action labeled_content(label: String, content: Action)\n" + 
				"    action indented_content(content: Action)\n" + 
				"  }\n" + 
				"}\n" + 
				"#iiHeOY1LertieGD9pMlkF1it\n" + 
				"var bikes : Bike_Collection {\n" + 
				"}\n" + 
				"#dc8ooRMj7Hs2BKfq4d21Qb15\n" + 
				"action main() {\n" + 
				"  if box→is_init then {\n" + 
				"    skip;\n" + 
				"  }\n" + 
				"  if true then {\n" + 
				"    \"Bikes:\" →post_to_wall;\n" + 
				"    if data→bikes→is_invalid then {\n" + 
				"      \"No bikes found\" →post_to_wall;\n" + 
				"    }\n" + 
				"    else {\n" + 
				"      foreach Bike in data→bikes\n" + 
				"      where true\n" + 
				"      do {\n" + 
				"        do box {\n" + 
				"          @\\u267b→controls→button($Bike→make→get ∥ \" \" ∥ $Bike→model→get, $tapped2);\n" + 
				"          where tapped2() {\n" + 
				"            code→showBike($Bike);\n" + 
				"          }\n" + 
				"        }\n" + 
				"      }\n" + 
				"    }\n" + 
				"    do box {\n" + 
				"      @\\u267b→controls→button(\"Add Bike \", $tapped);\n" + 
				"      where tapped() {\n" + 
				"        code→addBike;\n" + 
				"      }\n" + 
				"    }\n" + 
				"  }\n" + 
				"  meta page;\n" + 
				"}\n" + 
				"#xGjgKKuj2vgMovqJs1GSCgqk\n" + 
				"action addBike() {\n" + 
				"  if box→is_init then {\n" + 
				"    data→currentMake := \"\";\n" + 
				"    data→currentModel := \"\";\n" + 
				"  }\n" + 
				"  if true then {\n" + 
				"    do box {\n" + 
				"      @\\u267b→controls→input_text_line(\"Make\", data→currentMake, $on_value_change);\n" + 
				"      where on_value_change(text: String) {\n" + 
				"        data→currentMake := $text;\n" + 
				"      }\n" + 
				"      @\\u267b→controls→input_text_line(\"Model\", data→currentModel, $on_value_change2);\n" + 
				"      where on_value_change2(text: String) {\n" + 
				"        data→currentModel := $text;\n" + 
				"      }\n" + 
				"      @\\u267b→controls→button(\"Add\", $tapped);\n" + 
				"      where tapped() {\n" + 
				"        code→saveBike(data→currentMake, data→currentModel);\n" + 
				"        wall→pop_page;\n" + 
				"      }\n" + 
				"    }\n" + 
				"  }\n" + 
				"  meta page;\n" + 
				"}\n" + 
				"#xvEx5mBxE1cSUbiIAT3hDQiv\n" + 
				"action saveBike(make: String, model: String) {\n" + 
				"  $newBike := records→Bike→create;\n" + 
				"  $newBike→make→set($make);\n" + 
				"  if data→bikes→is_invalid then {\n" + 
				"    data→bikes := records→Bike→create_collection;\n" + 
				"  }\n" + 
				"  $newBike→model→set($model);\n" + 
				"  data→bikes→add($newBike);\n" + 
				"  meta private;\n" + 
				"}\n" + 
				"#Dp0raTs0B0VJYLD4nB9SugKP\n" + 
				"var currentMake : String {\n" + 
				"  transient = true;\n" + 
				"}\n" + 
				"#I35B5MMzMuuz06Ltl2e284Ng\n" + 
				"var currentModel : String {\n" + 
				"  transient = true;\n" + 
				"}\n" + 
				"#TEXPOysO9Xy05b6xHzpaUQb6\n" + 
				"action showBike(Bike: Bike) {\n" + 
				"  if box→is_init then {\n" + 
				"    skip;\n" + 
				"  }\n" + 
				"  if true then {\n" + 
				"    do box {\n" + 
				"      ($Bike→make→get ∥ \" \" ∥ $Bike→model→get)→post_to_wall;\n" + 
				"    }\n" + 
				"    do box {\n" + 
				"      foreach operations in records→operations_table\n" + 
				"      where true\n" + 
				"      do {\n" + 
				"        ($operations→operationName→get ∥ \" -> \" ∥ $operations→mileage→get→to_string)→post_to_wall;\n" + 
				"      }\n" + 
				"    }\n" + 
				"    do box {\n" + 
				"      @\\u267b→controls→button(\"Add Operation\", $tapped);\n" + 
				"      where tapped() {\n" + 
				"        code→addOperation($Bike);\n" + 
				"      }\n" + 
				"    }\n" + 
				"  }\n" + 
				"  meta page;\n" + 
				"}\n" + 
				"#xu52qhyRTBNwEaFNGHnMRTuc\n" + 
				"table operations {\n" + 
				"  type = \"Table\";\n" + 
				"  fields {\n" + 
				"    #YX6xbyI6xkJFeNH8G0EPq5HA operationName : String_field\n" + 
				"    #qgmYZFpSQI83HDZ2h3A50eBT mileage : Number_field\n" + 
				"    #alHVN80CXS3aJoF2a2NiWQid bike : Bike_field\n" + 
				"  }\n" + 
				"}\n" + 
				"#xDdzXmWmy5WTndM1qqK2JaeH\n" + 
				"action addOperation(Bike: Bike) {\n" + 
				"  if box→is_init then {\n" + 
				"    skip;\n" + 
				"  }\n" + 
				"  if true then {\n" + 
				"    do box {\n" + 
				"      @\\u267b→controls→input_text_line(\"Operation\", data→operation, $on_value_change);\n" + 
				"      where on_value_change(text: String) {\n" + 
				"        data→operation := $text;\n" + 
				"      }\n" + 
				"      @\\u267b→controls→input_text_line(\"Mileage\", data→mileage, $on_value_change2);\n" + 
				"      where tapped() {\n" + 
				"        skip;\n" + 
				"      }\n" + 
				"      where on_value_change2(text: String) {\n" + 
				"        data→mileage := $text;\n" + 
				"      }\n" + 
				"      @\\u267b→controls→button(\"Add\", $tapped2);\n" + 
				"      where tapped2() {\n" + 
				"        code→saveOperation($Bike, data→operation, data→mileage→to_number);\n" + 
				"        wall→pop_page;\n" + 
				"      }\n" + 
				"    }\n" + 
				"  }\n" + 
				"  meta page;\n" + 
				"}\n" + 
				"#xlRDvvoeVbMkSsBv0HrI20lO\n" + 
				"var operation : String {\n" + 
				"  transient = true;\n" + 
				"}\n" + 
				"#s92gHd6Stn814InHC2lF7Vgk\n" + 
				"var mileage : String {\n" + 
				"  transient = true;\n" + 
				"}\n" + 
				"#xkS5u2jtwDuDngNYt3kPQ4HU\n" + 
				"action saveOperation(Bike: Bike, operation: String, mileage: Number) {\n" + 
				"  $newRow := records→operations_table→add_row;\n" + 
				"  $newRow→bike→set($Bike);\n" + 
				"  $newRow→mileage→set($mileage);\n" + 
				"  $newRow→operationName→set($operation);\n" + 
				"  skip;\n" + 
				"  meta private;\n" + 
				"}\n";
		assertEquals(expected,text);
	}
}
