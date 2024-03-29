//Elijah Freestone
//Java 1 term 1403
//Week 2
//March 9th, 2014

package com.elijahfreestone.java1project2;


import android.app.Activity;
import android.content.Context;
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

public class MainActivity extends Activity {
	//Declare context
	Context myContext;
	//Declare array of cities
	String[] citiesArray;
	//Declare temp measurement array
	String[] tempMeasureArray;
	//Declare selected city
	String selectedCity;
	//Declare selected temp measurement
	static String selectedTempMeasurement;
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
				selectedCity = citiesArray[position];
				//System.out.println(selectedCity);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
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
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				//Toast.makeText(myContext, "You have selected " + tempMeasureArray[position] + " to display temp", Toast.LENGTH_LONG).show();
				
				//Set temp measurement from grid selection
				selectedTempMeasurement = tempMeasureArray[position];
				
				//Change text of measure title to show what is currently selected (fahrenheit or celsius).
				measureTitleView.setText(selectedTempMeasurement + " currently selected");

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
				displayTempView.setText(selectedCityFromEnum);
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
