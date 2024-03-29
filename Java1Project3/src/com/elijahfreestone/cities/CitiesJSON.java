//Elijah Freestone
//Java 1 term 1403
//Week 3
//March 15th, 2014

package com.elijahfreestone.cities;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;

import com.elijahfreestone.java1project3.MainActivity;

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
						highTempSelected = highTempFahr + " �F";
						lowTempSelected = lowTempFahr + " �F";
					} else {
						//Set high/low to celsius
						highTempSelected = highTempCels + " �C";
						lowTempSelected = lowTempCels + " �C";
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
					tempSelected = celsius + " �C";
				} else {
					tempSelected = fahrenheit + " �F";
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
	
	//Method to test network
	public static Boolean connectionStatus(Context myContext){
		//Set connection bool to false
		Boolean connectionBool = false;
			
		//Create connectivity manager
		ConnectivityManager connectionManager = (ConnectivityManager) myContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		//Create Network info
		NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
		//Check if network info is null
		if(networkInfo != null){
			//Check if network info is connected
			if (networkInfo.isConnected()) {
				//Set connection bool to true
				connectionBool = true;
			}
		}
		//Return connection bool
		return connectionBool;
	}
		
	//Method to get response from url
	public static String getResponse(URL url) {
		//Create string for response
		String response = "";
			
		try {
			//Open connection
			URLConnection connection = url.openConnection();
			//Create buffered input stream
			BufferedInputStream bufferedInputStream = new BufferedInputStream(connection.getInputStream());
			//Create byte and set to 1024
			byte[] contextByte = new byte[1024];
			//Create int for bytes read
			int byteRead = 0;
			//Create string buffer for adding response to
			StringBuffer responseBuffer = new StringBuffer();
			//While loop
			while ((byteRead = bufferedInputStream.read(contextByte)) != -1) {
				//Set response string to receive bytes
				response = new String(contextByte, 0, byteRead);
				//Append response to response buffer
				responseBuffer.append(response);
			}
			
			//Fill response string with response buffer once while loop completes
			response = responseBuffer.toString();
			//System.out.println(response);
			//Log response
			Log.i(TAG, response);
		} catch (IOException e) {
			//Set response to error
			response = "URL broken";
			//e.printStackTrace();
			//Log error
			Log.e(TAG, "Something went wrong", e);
		}
		//Return reponse string
		return response;
	}
		
	//Async getData class to call url
	public static class getData extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			responseString = "";
			try {
				//Apply testURL to local url
				URL url = new URL(MainActivity.fullURLString);
				//Call getResponse and apply to responseString
				responseString = getResponse(url);
			} catch (MalformedURLException e) {
				//Set response string to error
				responseString = "Something went wrong in getData";
				//e.printStackTrace();
				//Log error
				Log.e(TAG, "ERROR: ", e);
			}
			//Return responseString
			return responseString;
		}
		
		//Override onPostExecute to display response
		@Override
		protected void onPostExecute(String result) {
			try {
				//Parse JSON and display according to settings selected
				MainActivity.displayTempView.setText(readJSON(result));
				//Recenter display text
				MainActivity.displayTempView.setGravity(Gravity.CENTER);
				super.onPostExecute(result);
				//System.out.println(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
}
