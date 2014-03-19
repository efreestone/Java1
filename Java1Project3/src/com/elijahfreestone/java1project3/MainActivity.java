//Elijah Freestone
//Java 1 term 1403
//Week 3
//March 15th, 2014

package com.elijahfreestone.java1project3;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.elijahfreestone.cities.CitiesJSON;
import com.elijahfreestone.cities.CitiesJSON.getData;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class MainActivity extends Activity {
	//Declare context
	Context myContext;
	//Declare array of cities
	String[] citiesArray;
	//Declare current array
	String[] currentArray;
	//Declare temp measurement array
	String[] tempMeasureArray;
	//Declare selected city
	String selectedCity;
	//Declare selected temp measurement
	public static String selectedTempMeasurement;
	//Declare temp display view
	public static TextView displayTempView;
	//Declare TAG constant
	static String TAG = "NETWORK DATA - MAINACTIVITY";
	//Declare URL string for API call
	String callURLString;
	//
	String mainURL;
	//Declare Weather Underground API key
	String apiKey = "8204b5c9bf753afb";
	
	public static String testURL; //= "http://api.wunderground.com/api/8204b5c9bf753afb/conditions/q/CO/Loveland.json";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Initialize context and cities array
		myContext = this;
		//Grab cities array from strings.xml resource file
		citiesArray = getResources().getStringArray(R.array.cities_array);
		//Grab current array from strings.xml resource file
		currentArray = getResources().getStringArray(R.array.current_forecast);
		//Grab temp measure array from strings.xml resource file
		tempMeasureArray = getResources().getStringArray(R.array.temp_measurement);
				
		//Create linear layout instance
		LinearLayout myLayout = new LinearLayout(this);
		//Set orientation
		myLayout.setOrientation(LinearLayout.VERTICAL);
		//Set my layout parameters to match the parent item
		myLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				        
		//Create center params used to center various items
		LinearLayout.LayoutParams centerParams = new LinearLayout.LayoutParams(
				                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		//Set gravity to center
		centerParams.gravity = Gravity.CENTER;

		//Create title text view
		TextView titleView = new TextView(this);
		//Set text appearance for title
		titleView.setTextAppearance(this, android.R.attr.textAppearanceLarge);
		//Set text for title from resources
		titleView.setText(R.string.titleString);
		//Add title with centered params
	    myLayout.addView(titleView, centerParams);
		        
	    //Create city spinner adapter
	    ArrayAdapter<String> citySpinnerAdapter = new ArrayAdapter<String>(myContext, android.R.layout.simple_spinner_item, citiesArray);
	  	//Set dropdown of city spinner
     	citySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		      		
      	//Create city spinner
      	Spinner citySpinner = new Spinner(myContext);
      	//Set adapter
	   	citySpinner.setAdapter(citySpinnerAdapter);
    	LayoutParams spinnerParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
      	citySpinner.setLayoutParams(spinnerParams);
      	//Add spinner to my layout
	   	myLayout.addView(citySpinner);
		      	
      	//Set on item selected for city spinner
      	citySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
      		//Use unimplemented methods, modified as per spinner video for better clarity
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				//Create toast to test spinner selection
				//Toast.makeText(myContext, "You have selected " + citiesArray[position], Toast.LENGTH_LONG).show();
						
				//Set selected city from spinner selection	
				selectedCity = citiesArray[position];
				//System.out.println(selectedCity);
				if (selectedCity.equalsIgnoreCase("Loveland")) {
					testURL = "http://api.wunderground.com/api/8204b5c9bf753afb/conditions/q/CO/Loveland.json";
				} else if (selectedCity.equalsIgnoreCase("LosAngeles")) {
					testURL = "http://api.wunderground.com/api/8204b5c9bf753afb/conditions/q/CA/Los_Angeles.json";
					System.out.println("testURL changed to " + testURL);
				} else if (selectedCity.equalsIgnoreCase("NewYork")) {
					testURL = "http://api.wunderground.com/api/8204b5c9bf753afb/conditions/q/NY/New_York.json";
				} else if (selectedCity.equalsIgnoreCase("Chicago")) {
					testURL = "http://api.wunderground.com/api/8204b5c9bf753afb/conditions/q/IL/Chicago.json";
				} else if (selectedCity.equalsIgnoreCase("Orlando")) {
					testURL = "http://api.wunderground.com/api/8204b5c9bf753afb/conditions/q/FL/Orlando.json";
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				testURL = "http://api.wunderground.com/api/8204b5c9bf753afb/conditions/q/CO/Loveland.json";	
			}
		});
		      	
      	//Create measurement title text view
      	final TextView measureTitleView = new TextView(this);
      	//Set text appearance for title
      	measureTitleView.setTextAppearance(this, android.R.attr.textAppearanceLarge);
   		//Set text for title from resources
      	measureTitleView.setText(R.string.measureTitleString);
        //Add title with centered params
        myLayout.addView(measureTitleView, centerParams);
	      	
      	//Create grid view adapter
      	ArrayAdapter<String> tempGridAdapter = new ArrayAdapter<String>(myContext, android.R.layout.simple_list_item_1, tempMeasureArray); 
      	//Create grid view
      	final GridView tempGridView = new GridView(myContext);
      	//Set grid layout params
      	tempGridView.setLayoutParams(centerParams);
      	//Set grid to 2 column
      	tempGridView.setNumColumns(2);
      	//Set grid adapter
      	tempGridView.setAdapter(tempGridAdapter);
      	//Add grid view to my layout
      	myLayout.addView(tempGridView);
      	
      	//Initialize selected temp measurement (fahrenheit or celsius). If nothing is selected, default is Fahrenheit
      	selectedTempMeasurement = "Fahrenheit";
		      	
     	//Set on item click for grid view
      	tempGridView.setOnItemClickListener(new OnItemClickListener() {
      		//Use unimplemented methods, modified as per grid view video for better clarity
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//Toast.makeText(myContext, "You have selected " + tempMeasureArray[position] + " to display temp", Toast.LENGTH_LONG).show();
					
				//Set temp measurement from grid selection
				selectedTempMeasurement = tempMeasureArray[position];
						
				//Change text of measure title to show what is currently selected (fahrenheit or celsius).
				measureTitleView.setText(selectedTempMeasurement + " currently selected");
			}
		});
      	
      	//Create current/forecast spinner adapter
      	ArrayAdapter<String> currentSpinnerAdapter = new ArrayAdapter<String>(myContext, android.R.layout.simple_spinner_item, currentArray);
      	//Set dropdown of current spinner
      	currentSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
      	
      	//Create current spinner
      	Spinner currentSpinner = new Spinner(myContext);
      	//Set adapter
      	currentSpinner.setAdapter(currentSpinnerAdapter);
      	//Set layout params
      	currentSpinner.setLayoutParams(spinnerParams);
      	//Add current spinner to my layout
      	myLayout.addView(currentSpinner);
      	
      	//Set on item selected for current spinner
      	currentSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
      		//Use unimplemented methods, modified as per spinner video for better clarity
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				//Create toast to test spinner selection
				Toast.makeText(myContext, "You have selected " + currentArray[position], Toast.LENGTH_LONG).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
						
			}
		});
		      	
	   	//Create get weather button
      	Button weatherButton = new Button(this);
	    //Set button text
        weatherButton.setText(R.string.weatherButtonString);
        //Set width of the button
        weatherButton.setWidth(500);
        //Add button to my layout with centered params
        myLayout.addView(weatherButton, centerParams);
        
        //Create onClick event for weather button
        weatherButton.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
				//Get info from city JSON object 
				String selectedCityFromEnum = CitiesJSON.readJSON(selectedCity);
				//displayTempView.setText(selectedCityFromEnum);
				
				if (CitiesJSON.connectionStatus(myContext)) {
					//displayTempView.setText("Yay it works!!");
					CitiesJSON.getData data = new getData();
					data.execute(testURL);
				} else {
					//Create alert dialog for no connection
					AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
					// Setting dialog title
					alertDialog.setTitle("Error");
					// Setting message
					alertDialog.setMessage("This application requires internet access. Please check your network connection and try again.");
					// Setting OK Button
					alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", (DialogInterface.OnClickListener) null);
					// Showing Alert Message
					alertDialog.show();
					
					//displayTempView.setText("You broke it!!");
				}
			}
		});
		      	
        //Create measurement title text view
      	displayTempView = new TextView(this);
      	//Set text appearance for title
      	displayTempView.setTextAppearance(this, android.R.attr.textAppearanceLarge);
   		//Set text for title from resources
      	displayTempView.setText(R.string.displayTempString);
        //Add title with centered params
        myLayout.addView(displayTempView, centerParams);
	      	
        //Override xml layout with my linear layout
		setContentView(myLayout); 
		
		//connectionStatus(myContext);
//		if (CitiesJSON.connectionStatus(myContext)) {
//			//displayTempView.setText("Yay it works!!");
//			CitiesJSON.getData data = new getData();
//			data.execute(testURL);
//		} else {
//			displayTempView.setText("You broke it!!");
//		}
		
		//setContentView(R.layout.activity_main);
	}

	
//	//Method to test network
//	public Boolean connectionStatus(Context myContext){
//		//Set connection bool to false
//		Boolean connectionBool = false;
//		
//		//Create connectivity manager
//		ConnectivityManager cm = (ConnectivityManager) myContext.getSystemService(Context.CONNECTIVITY_SERVICE);
//		NetworkInfo ni = cm.getActiveNetworkInfo();
//		//Check if network info is null
//		if(ni != null){
//			//Check if network info is connected
//			if (ni.isConnected()) {
//				System.out.println(TAG + " Connection Type: " + ni.getTypeName());
//				//Set connection bool to true
//				connectionBool = true;
//			}
//		}
//		//Return connection bool
//		return connectionBool;
//	}
//	
//	//Method to get response from url
//	public static String getResponse(URL url) {
//		//Create string for response
//		String response = "";
//		
//		try {
//			//Open connection
//			URLConnection connection = url.openConnection();
//			//Create buffered input stream
//			BufferedInputStream bin = new BufferedInputStream(connection.getInputStream());
//			//Create byte and set to 1024
//			byte[] contextByte = new byte[1024];
//			//Create int for bytes read
//			int byteRead = 0;
//			//Create string buffer for adding response to
//			StringBuffer responseBuffer = new StringBuffer();
//			//While loop
//			while ((byteRead = bin.read(contextByte)) != -1) {
//				//Set response string to receive bytes
//				response = new String(contextByte, 0, byteRead);
//				//Append response to response buffer
//				responseBuffer.append(response);
//			}
//			
//			//Fill response string with response buffer once while loop completes
//			response = responseBuffer.toString();
//			//System.out.println(TAG + response);
//			//Log response
//			Log.i(TAG, response);
//		} catch (IOException e) {
//			//Set response to error
//			response = "URL broken";
//			//e.printStackTrace();
//			//Log error
//			Log.e(TAG, "Something went wrong", e);
//		}
//		//Return reponse string
//		return response;
//	}
//	
//	//Async getData class to call url
//	static class getData extends AsyncTask<String, Void, String>{
//
//		@Override
//		protected String doInBackground(String... params) {
//			String responseString = "";
//			try {
//				//Apply testURL to local url
//				URL url = new URL(testURL);
//				//Call getResponse and apply to responseString
//				responseString = getResponse(url);
//			} catch (MalformedURLException e) {
//				//Set response string to error
//				responseString = "Something went wrong in getData";
//				//e.printStackTrace();
//				//Log error
//				Log.e(TAG, "ERROR: ", e);
//			}
//			//Return responseString
//			return responseString;
//		}
//		
//		//Override onPostExecute to display response
//		@Override
//		protected void onPostExecute(String result) {
//			displayTempView.setText(result);
//			super.onPostExecute(result);
//			System.out.println(result);
//		}
//		
//	}
}