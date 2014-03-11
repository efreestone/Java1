//Elijah Freestone
//Java 1 term 1403
//Week 2
//March 9th, 2014

package com.elijahfreestone.java1project2;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
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
		        titleView.setText("Tip Calculator");
		        //Add title with centered params
		        myLayout.addView(titleView, centerParams);
		
		setContentView(myLayout); 
		//setContentView(R.layout.activity_main);
	}
}
