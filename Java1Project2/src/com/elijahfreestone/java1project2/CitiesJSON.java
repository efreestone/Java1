//Elijah Freestone
//Java 1 term 1403
//Week 2
//March 9th, 2014

package com.elijahfreestone.java1project2;

import org.json.JSONException;
import org.json.JSONObject;

public class CitiesJSON {
	//Create JSON build method
	public static JSONObject buildJSON(){
		// Create cities object
		JSONObject citiesObject = new JSONObject();
		try {
			// Create query object
			JSONObject queryObject = new JSONObject();

			// Create city object in query object
			for (CitiesEnum city : CitiesEnum.values()) {
				// Create city object
				JSONObject cityObject = new JSONObject();

				// Add city info to city object
				cityObject.put("cityName", city.getCityName());
				cityObject.put("stateName", city.getStateName());
				cityObject.put("fahrenheitTemp", city.getFahrenheitTemp().toString());
				cityObject.put("celsiusTemp", city.getCelsiusTemp().toString());
				// Add city object to query object
				queryObject.put(city.name().toString(), cityObject);
			}
			// Add query to cities objects
			citiesObject.put("query", queryObject);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Build JSON catch");
		}
		// Return the cities object
		return citiesObject;
	}
	
	//Create read JSON method
	public static String readJSON(String selectedCity){
		//String for results
		String results, cityName, stateName, fahrenheitTemp, celsiusTemp;
		
		JSONObject object = buildJSON();
		
		try {
			System.out.println("Read JSON try");
			//Get query object and apply to strings for use in results string
			cityName = object.getJSONObject("query").getJSONObject(selectedCity).getString("cityName");
			stateName = object.getJSONObject("query").getJSONObject(selectedCity).getString("stateName");
			fahrenheitTemp = object.getJSONObject("query").getJSONObject(selectedCity).getString("fahrenheitTemp");
			celsiusTemp = object.getJSONObject("query").getJSONObject(selectedCity).getString("celsiusTemp");
			//Set results string with city info strings
			results = "City: " + cityName + "\r\n"
					+ "State: " + stateName + "\r\n"
					+ "Fahrenheit: " + fahrenheitTemp + "\r\n"
					+ "Celsius: " + celsiusTemp + "\r\n";
		} catch (JSONException e) {
			e.printStackTrace();
			results = e.toString();
			//results = "broken";
			System.out.println("Read JSON catch");
		}
		
		//Return results
		return results;
	}
}
