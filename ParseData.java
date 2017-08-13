package com.java.commons;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.data.pojo.JsonObjectDetails;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class ParseData {

	private void generateParsedData(String [] data_lines){
		JSONParser parser = new JSONParser();
		ArrayList<JsonObjectDetails> jsonList = new ArrayList<>();
		for(String line : data_lines){
			
				try {
					JSONObject object = (JSONObject) parser.parse(line);
					//JSONArray array = (JSONArray)object;
					System.out.println("Element: "+object.get("action"));
					String action = (String) object.get("action");
					Long change_size = (Long) object.get("change_size");
					boolean is_bot = (boolean) object.get("is_bot");
					JsonObjectDetails jObject = new JsonObjectDetails();
					if(action.equalsIgnoreCase("edit")&& (Long)change_size!=null && !(is_bot)){
						
						jObject.setAction(action);
						jObject.setChangeSize(change_size);
						jObject.setAnon((boolean)object.get("is_anon"));
						jObject.setPageTitle((String)object.get("page_title"));
						jObject.setUrl((String) object.get("url"));
						jObject.setUser((String) object.get("user"));
						jsonList.add(jObject);
					}
					DatabaseConnection dconnect = new DatabaseConnection();
					dconnect.insert(jsonList);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("exception occured...");
				//	System.out.println("Error at Position: "+e.getPosition());
					
				}
			
			
		}
	}

	

	public void parseData(String data) {
		String[] lines = data.split("<br>");
		generateParsedData(lines);
	}
}
