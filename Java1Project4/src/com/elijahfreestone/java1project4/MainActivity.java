//Elijah Freestone
//Java 1 term 1403
//Week 4
//March 22nd, 2014

package com.elijahfreestone.java1project4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

import com.elijahfreestone.cities.NetworkConnection;
import com.elijahfreestone.cities.NetworkConnection.getData;

public class MainActivity extends Activity {
	//Declare context
	Context myContext;
	//Declare array of cities
	String[] citiesArray;
	//Declare current array
	String[] currentArray;
	//Declare selected city
	String selectedCity;
	//Declare selected temp measurement
	public static String selectedTempMeasurement;
	//Declare temp display view
	public static TextView displayTempView;
	//Declare TAG constant
	static String TAG = "NETWORK DATA - MAINACTIVITY";
	//Declare URL string for setting city
	String cityURLString;
	//Declare URL string for setting type of call between current weather and 3-day forecast
	public static String callURLModString;
	//Declare main url string to build api call on
	String mainURLString;
	//Declare string to hold full url, built based on settings when button is clicked
	public static String fullURLString; //Example: "http://api.wunderground.com/api/8204b5c9bf753afb/conditions/q/CO/Loveland.json";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		//Initialize context and cities array
		myContext = this;
		//Grab cities array from strings.xml resource file
		citiesArray = getResources().getStringArray(R.array.cities_array);
		//Grab current array from strings.xml resource file
		currentArray = getResources().getStringArray(R.array.current_forecast);
		//Grab main url string from resources
		mainURLString = myContext.getString(R.string.mainURLString);
		        
	    //Create city spinner adapter
	    ArrayAdapter<String> citySpinnerAdapter = new ArrayAdapter<String>(myContext, android.R.layout.simple_spinner_item, citiesArray);
	  	//Set dropdown of city spinner
     	citySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     	
     	//Create city spinner from layout xml and set adapter
     	Spinner citySpinner = (Spinner) findViewById(R.id.citySpinner);
     	citySpinner.setAdapter(citySpinnerAdapter);
		      	
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
					System.out.println(cityURLString);
				} else if (selectedCity.equalsIgnoreCase("Los Angeles")) {
					//Set city url string to Los Angeles
					cityURLString = myContext.getString(R.string.losAngelesString);
					System.out.println(cityURLString);
				} else if (selectedCity.equalsIgnoreCase("New York")) {
					//Set city url string to New York
					cityURLString = myContext.getString(R.string.newYorkString);
					System.out.println(cityURLString);
				} else if (selectedCity.equalsIgnoreCase("Chicago")) {
					//Set city url string to Chicago
					cityURLString = myContext.getString(R.string.chicagoString);
					System.out.println(cityURLString);
				} else if (selectedCity.equalsIgnoreCase("Orlando")) {
					//Set city url string to Orlando
					cityURLString = myContext.getString(R.string.orlandoString);
					System.out.println(cityURLString);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				//Set city url string to Loveland by default if the spinner is not activated
				cityURLString = myContext.getString(R.string.lovelandString);	
			}
		});
        
      	//Create radio group from layout xml to grab selection
        RadioGroup tempUnitRadioGroup = (RadioGroup) findViewById(R.id.tempUnitRadioGroup);
      	
      	//Initialize selected temp measurement (fahrenheit or celsius). If nothing is selected, default is Fahrenheit
      	selectedTempMeasurement = myContext.getString(R.string.fahrenheitString);
      	
      	//Set on checked change for radios
      	tempUnitRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      		
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				//Cast id of radio selected
				RadioButton selectedButton = (RadioButton) findViewById(checkedId);
				//Cast text from radio selected
				String radioText = selectedButton.getText().toString();
				//Toast.makeText(myContext, "You have selected " + radioText + " to display temp", Toast.LENGTH_LONG).show();	
				
				//If celsius is selected. Defaults to fahrenheit if nothing selected
				if (radioText.equalsIgnoreCase("Celsius")) {
					//Set selected temp to celsius
					selectedTempMeasurement = myContext.getString(R.string.celsiusString);
				} else {
					//Set selected temp to fahrenheit
					selectedTempMeasurement = myContext.getString(R.string.fahrenheitString);
				}
			}
		});
      	
      	
      	//Create current/forecast spinner adapter
      	ArrayAdapter<String> currentSpinnerAdapter = new ArrayAdapter<String>(myContext, android.R.layout.simple_spinner_item, currentArray);
      	//Set dropdown of current spinner
      	currentSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //simple_list_item_single_choice
      	
      	//Create current spinner from layout xml and set adapter
      	Spinner currentSpinner = (Spinner) findViewById(R.id.currentSpinner);
      	currentSpinner.setAdapter(currentSpinnerAdapter);
      	
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
      	
      	//Create weather button from layout xml
      	Button weatherButton = (Button) findViewById(R.id.weatherButton);
      	//weatherButton.setBackgroundResource(R.drawable.round_button);
      	
      	final LinearLayout inflateView = (LinearLayout) findViewById(R.id.parentView);
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View myView = inflater.inflate(R.layout.icon_layout, null);
        
        //Create onClick event for weather button
        weatherButton.setOnClickListener(new OnClickListener() {
        	//Create bool for use in checking if inflateView has been added
        	Boolean viewInflated = false;
				
			@Override
			public void onClick(View v) {
				//Build url based on selections of spinners
				fullURLString = mainURLString + callURLModString + cityURLString;
				
				//Check network connection
				if (NetworkConnection.connectionStatus(myContext)) {
					//displayTempView.setText("Yay it works!!");
					//Create instance of get data class which is located in NetworkConnection.jar
					getData data = new getData();
					//Call get data and pass the full URL for the api call
					data.execute(fullURLString);
					
					//Check viewInflated bool
					if (viewInflated == false) {
						//Set bool to true
						viewInflated = true;
						inflateView.addView(myView); 
						//Change style of displayed temp/forecast
						displayTempView.setTextAppearance(myContext, R.style.displayStyle);
					}
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
        
        //Set display temp view from layout xml to allow adding json results
        displayTempView = (TextView) findViewById(R.id.displayTempView);
	      	
        //Override xml layout with my linear layout
		//setContentView(myLayout); 
		//setContentView(R.layout.activity_main);
	}
}
