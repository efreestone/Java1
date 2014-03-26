//Elijah Freestone
//Java 1 term 1403
//Week 4
//March 22nd, 2014

package com.elijahfreestone.cities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.elijahfreestone.java1project4.MainActivity;

public class CitiesJSON {
	//Declare TAG constant
	static String TAG = "NETWORK DATA - CITIESJSON";
	//Declare response string
	static String responseString;

	// Create read JSON method
	public static String readJSON(String selectedCity) throws JSONException {
		//Strings for results and city info if current weather is selected
		String results, city, state, fahrenheit, celsius, tempSelected;
		//Strings for forecast info if 3-day is selected
		String forecastDay, highTempFahr, lowTempFahr, highTempCels, lowTempCels, highTempSelected, lowTempSelected, objectInfo;
		StringBuffer forecastBuffer;
		//Create new JSON object to hold object from API call
		JSONObject newObject = new JSONObject(selectedCity);

		try {
			//Initialize object info and forecast buffer
			objectInfo = "";
			forecastBuffer = new StringBuffer();
			//Check if 3-day forecast selected. Forecast has a nested JSONArray while conditions doesn't
			if (MainActivity.callURLModString.equalsIgnoreCase("forecast/q/")) {
				//Grab JSON array from newObject
				JSONArray forecastArray = newObject.getJSONObject("forecast").getJSONObject("simpleforecast").getJSONArray("forecastday");
				//System.out.println(forecastArray.getJSONObject(1));
				
				for (int i = 0; i < forecastArray.length(); i++) {
					//System.out.println("Object " + i + forecastArray.getJSONObject(i));
					//Grab day for forecast
					forecastDay = forecastArray.getJSONObject(i).getJSONObject("date").getString("weekday");
					//Grab high/low in fahrenheit
					highTempFahr = forecastArray.getJSONObject(i).getJSONObject("high").getString("fahrenheit");
					lowTempFahr = forecastArray.getJSONObject(i).getJSONObject("low").getString("fahrenheit");
					//Grab high/low in celsius
					highTempCels = forecastArray.getJSONObject(i).getJSONObject("high").getString("celsius");
					lowTempCels = forecastArray.getJSONObject(i).getJSONObject("low").getString("celsius");
					
					//Set tempSelected based on selected temp in MainActivity.
					//Defaults to Fahrenheit if nothing is selected
					if (MainActivity.selectedTempMeasurement.equalsIgnoreCase("Fahrenheit")) {
						//Set high/low to fahrenheit
						highTempSelected = highTempFahr + " ¡F";
						lowTempSelected = lowTempFahr + " ¡F";
					} else {
						//Set high/low to celsius
						highTempSelected = highTempCels + " ¡C";
						lowTempSelected = lowTempCels + " ¡C";
					}

					//Format object info to be appended
					objectInfo = forecastDay + ": High " + highTempSelected + " / Low " + lowTempSelected + "\r\n";
					//Append object info for each day
					forecastBuffer.append(objectInfo);
				}
				
				//Cast forecast buffer into results
				results = forecastBuffer.toString();
			//Hit is Current Weather is selected in second spinner
			} else { 
				System.out.println("Read JSON try");
				//Get query object and apply to strings for use in results string
				city = newObject.getJSONObject("current_observation").getJSONObject("display_location").getString("city");
				state = newObject.getJSONObject("current_observation").getJSONObject("display_location").getString("state");
				fahrenheit = newObject.getJSONObject("current_observation").getString("temp_f");
				celsius = newObject.getJSONObject("current_observation").getString("temp_c");

				//Set tempSelected based on selected temp in MainActivity.
				//Defaults to Fahrenheit if nothing is selected
				if (MainActivity.selectedTempMeasurement.equalsIgnoreCase("Celsius")) {
					tempSelected = celsius + " ¡C";
				} else {
					tempSelected = fahrenheit + " ¡F";
				}

				// Set results string with city info strings
				results = "City: " + city + "\r\n" + "State: " + state + "\r\n"
						+ "Current Temp: " + tempSelected + "\r\n";
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
			results = e.toString();
			// results = "broken";
			System.out.println("Read JSON catch");
		}

		// Return results
		return results;
	}
}
