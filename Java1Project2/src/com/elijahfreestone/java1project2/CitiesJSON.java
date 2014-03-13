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
			citiesObject.put("query", queryObject);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Return the cities object
		return citiesObject;
	}
}
