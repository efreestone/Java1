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
				cityObject.put("fahrenheitTemp", city.getFahrenheitTemp());
				cityObject.put("celsiusTemp", city.getCelsiusTemp());
				// Add city object to query object
				queryObject.put(city.name().toString(), cityObject);
			}
			// Add query to cities objects
			citiesObject.put("cities", queryObject);

		} catch (JSONException e) {
			e.printStackTrace();
			//System.out.println("Build JSON catch");
		}
		// Return the cities object
		return citiesObject;
	}
	
	//Create read JSON method
	public static String readJSON(String selectedCity){
		//String for results and city info
		String results, city, state, fahrenheit, celsius, tempSelected;
		//Create new JSON object to hold object from buildJSON
		JSONObject newObject = buildJSON();
		
		//public String passedTemp = MainActivity
		try {
			System.out.println("Read JSON try");
			//Get query object and apply to strings for use in results string
			city = newObject.getJSONObject("cities").getJSONObject(selectedCity).getString("cityName");
			state = newObject.getJSONObject("cities").getJSONObject(selectedCity).getString("stateName");
			fahrenheit = newObject.getJSONObject("cities").getJSONObject(selectedCity).getString("fahrenheitTemp");
			celsius = newObject.getJSONObject("cities").getJSONObject(selectedCity).getString("celsiusTemp");
			
			//Set tempSelected based on selected temp in MainActivity. Defaults to Fahrenheit if nothing is selected
			if (MainActivity.selectedTempMeasurement.equalsIgnoreCase("Fahrenheit")) {
				tempSelected = fahrenheit + " �F";
			} else { 
				tempSelected = celsius + " �C";
			}
			
			//Set results string with city info strings
			results = "City: " + city + "\r\n"
					+ "State: " + state + "\r\n"
					+ "Current Temp: " + tempSelected + "\r\n";
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
