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
import android.widget.LinearLayout;
import android.widget.ListView;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Initialize context and cities array
		myContext = this;
		//Grab cities array from strings.xml resource file
		citiesArray = getResources().getStringArray(R.array.cities_array);
		
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
				Toast.makeText(myContext, "You have selected " + citiesArray[position], Toast.LENGTH_LONG).show();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
      	
      	/*//Create list adapter
      	ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(myContext, android.R.layout.simple_list_item_1, citiesArray);
      	
      	//Create list view
      	ListView listView = new ListView(myContext);
      	listView.setLayoutParams(spinnerParams);
      	listView.setAdapter(listAdapter);
      	//Add list view to my layout
      	myLayout.addView(listView);
      	
      	//Set on item click for list view
      	listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				//Create toast to test spinner selection
				Toast.makeText(myContext, "You have selected " + citiesArray[position] + " from list view", Toast.LENGTH_LONG).show();
								
			}
		});*/
		
		setContentView(myLayout); 
		//setContentView(R.layout.activity_main);
	}
}
