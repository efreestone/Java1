//Elijah Freestone
//Java 1 term 1403
//Week 2
//March 9th, 2014

package com.elijahfreestone.java1project2;

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
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;

public class MainActivity extends Activity {
	//Declare context
	Context myContext;
	//Declare array of cities
	String[] citiesArray;
	//Declare temp measurment array
	String[] tempMeasureArray;
	//Declare selected city
	String selectedCity;
	//Declare selected temp measurement
	String selectedTempMeasurement;
	//Declare temp display view
	TextView displayTempView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Initialize context and cities array
		myContext = this;
		//Grab cities array from strings.xml resource file
		citiesArray = getResources().getStringArray(R.array.cities_array);
		//Grab temp measure array for strings.xml resource file
		tempMeasureArray = getResources().getStringArray(R.array.temp_measurement);
		//Initialize selected temp measurement (fahrenheit or celsius)
		//selectedTempMeasurement = "Fahrenheit";
		
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
		//Set text for title
        titleView.setText("Local Weather");
        //Add title with centered params
        myLayout.addView(titleView, centerParams);
        
        //Create spinner adapter
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
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				//Create toast to test spinner selection
				//Toast.makeText(myContext, "You have selected " + citiesArray[position], Toast.LENGTH_LONG).show();
				
				//Set selected city from spinner selection
				selectedCity = CitiesJSON.readJSON(citiesArray[position]);	
				System.out.println(selectedCity);
				//selectedCity = CitiesEnum.Chicago.toString();
				//selectedCity = CitiesEnum.valueOf(citiesArray[position]).getCityName(); 
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
      	
      	//Create measurement title text view
      	TextView measureTitleView = new TextView(this);
      	//Set text appearance for title
      	measureTitleView.setTextAppearance(this, android.R.attr.textAppearanceLarge);
   		//Set text for title
      	measureTitleView.setText("Please select units of measurement");
        //Add title with centered params
        myLayout.addView(measureTitleView, centerParams);
      	
      	//Create grid view adapter
      	ArrayAdapter<String> tempGridAdapter = new ArrayAdapter<String>(myContext, android.R.layout.simple_list_item_1, tempMeasureArray);
      	//Create grid view
      	GridView tempGridView = new GridView(myContext);
      	//Set grid layout params
      	tempGridView.setLayoutParams(spinnerParams);
      	//Set grid to 2 column
      	tempGridView.setNumColumns(2);
      	//Set grid adapter
      	tempGridView.setAdapter(tempGridAdapter);
      	//Add grid view to my layout
      	myLayout.addView(tempGridView);
      	
      	//Set on item click for grid view
      	tempGridView.setOnItemClickListener(new OnItemClickListener() {
      		//Use unimplemented methods, modified as per grid view video for better clarity
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Toast.makeText(myContext, "You have selected " + tempMeasureArray[position] + " to display temp", Toast.LENGTH_LONG).show();
				
				//Set temp measurement from grid selection
				selectedTempMeasurement = tempMeasureArray[position];
			}
		});
      	
      	//Create get weather button
        Button weatherButton = new Button(this);
        //Set button text
        weatherButton.setText("Get Weather");
        //Set width of the button
        weatherButton.setWidth(500);
        //Add button to my layout with centered params
        myLayout.addView(weatherButton, centerParams);
        
        //Create onClick event for weather button
        weatherButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Get info from city JSON object
				//String selectedCityFromEnum = CitiesEnum.valueOf(citiesArray[position]).setCityName();
				displayTempView.setText(selectedCity);
			}
		});
      	
        //Create measurement title text view
      	displayTempView = new TextView(this);
      	//Set text appearance for title
      	displayTempView.setTextAppearance(this, android.R.attr.textAppearanceLarge);
   		//Set text for title
      	displayTempView.setText("Please select a city and measurement unit");
        //Add title with centered params
        myLayout.addView(displayTempView, centerParams);
      	
		
		setContentView(myLayout); 
		//setContentView(R.layout.activity_main);
	}
}
