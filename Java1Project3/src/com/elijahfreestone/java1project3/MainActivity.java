//Elijah Freestone
//Java 1 term 1403
//Week 3
//March 15th, 2014

package com.elijahfreestone.java1project3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.elijahfreestone.cities.CitiesJSON;
import com.elijahfreestone.cities.CitiesJSON.getData;

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
	//Decalre URL string for setting city
	String cityURLString;
	//Declare URL string for setting type of call between current weather and 3-day forecast
	public static String callURLModString;
	//Declare main url string to build api call on
	String mainURLString;
	
	public static String fullURLString; //= "http://api.wunderground.com/api/8204b5c9bf753afb/conditions/q/CO/Loveland.json";
	
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
		//Grab main url string from resources
		mainURLString = myContext.getString(R.string.mainURLString);
				
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
					//Set city url string to Loveland
					cityURLString = myContext.getString(R.string.lovelandString);
					//testURL = "http://api.wunderground.com/api/8204b5c9bf753afb/conditions/q/CO/Loveland.json";
				} else if (selectedCity.equalsIgnoreCase("LosAngeles")) {
					//Set city url string to Los Angeles
					cityURLString = myContext.getString(R.string.losSngelesString);
					//testURL = "http://api.wunderground.com/api/8204b5c9bf753afb/conditions/q/CA/Los_Angeles.json";
				} else if (selectedCity.equalsIgnoreCase("NewYork")) {
					//Set city url string to New York
					cityURLString = myContext.getString(R.string.newYorkString);
					//testURL = "http://api.wunderground.com/api/8204b5c9bf753afb/conditions/q/NY/New_York.json";
				} else if (selectedCity.equalsIgnoreCase("Chicago")) {
					//Set city url string to Chicago
					cityURLString = myContext.getString(R.string.chicagoString);
					//testURL = "http://api.wunderground.com/api/8204b5c9bf753afb/conditions/q/IL/Chicago.json";
				} else if (selectedCity.equalsIgnoreCase("Orlando")) {
					//Set city url string to Orlando
					cityURLString = myContext.getString(R.string.orlandoString);
					//testURL = "http://api.wunderground.com/api/8204b5c9bf753afb/conditions/q/FL/Orlando.json";
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				//Set city url string to Loveland by default if the spinner is not activated
				cityURLString = myContext.getString(R.string.lovelandString);
				//testURL = "http://api.wunderground.com/api/8204b5c9bf753afb/conditions/q/CO/Loveland.json";	
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
				//Toast.makeText(myContext, "You have selected " + currentArray[position], Toast.LENGTH_LONG).show();
				
				//Cast selected modifier to a string
				String selectedMod = currentArray[position];
				//Check the modifier string and set callURLModString accordingly
				if (selectedMod.equalsIgnoreCase("Current Weather")) {
					//Set call modifier to conditions
					callURLModString = myContext.getString(R.string.conditionsURLString);
				} else if (selectedMod.equalsIgnoreCase("3-day Forecast")) {
					//Set call modifier to forecast
					callURLModString = myContext.getString(R.string.forecastURLString);
					//System.out.println(callURLModString);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				//Set call modifier to conditions by default if spinner isn't activated
				callURLModString = myContext.getString(R.string.conditionsURLString);
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
				//Build url based on selections of spinners
				fullURLString = mainURLString + callURLModString + cityURLString;
				
				if (CitiesJSON.connectionStatus(myContext)) {
					//displayTempView.setText("Yay it works!!");
					CitiesJSON.getData data = new getData();
					data.execute(fullURLString);
				} else {
					//Create alert dialog for no connection
					AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
					// Set dialog title to Connection Error
					alertDialog.setTitle(R.string.noConnectionTitle);
					// Set alert message. setMessage only has a charSequence version so egtString must be used.
					alertDialog.setMessage(myContext.getString(R.string.noConnectionAlert));
					// Set OK Button
					alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", (DialogInterface.OnClickListener) null);
					// Show Alert Dailog 
					alertDialog.show();
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
		
		//setContentView(R.layout.activity_main);
	}
}