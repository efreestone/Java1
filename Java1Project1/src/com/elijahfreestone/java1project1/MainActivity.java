//Elijah Freestone
//Java 1 term 1403
//Week 1
//March 4th, 2014

package com.elijahfreestone.java1project1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
//Import linear layout widget
import android.widget.LinearLayout;
//Import text view widget
import android.widget.TextView;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
//import android.view.Menu;
//Import layout params 
import android.view.ViewGroup.LayoutParams;

public class MainActivity extends Activity {
	//Declare tip display text view
	TextView tipDisplay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
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
        
        //Create text view
        EditText costText = new EditText(this);
        //Set hint text
        costText.setHint("Total cost of meal");
        //Add cost edit text to my layout with width set by center params and the width of Hint
        myLayout.addView(costText, centerParams);
        
        //Create top margin params
      	LinearLayout.LayoutParams topMarginParams = new LinearLayout.LayoutParams(
      			LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
      	//Set gravity to center text view
      	topMarginParams.gravity = Gravity.CENTER;
      	//Set top margin to seperate ratings label from the edit text
      	topMarginParams.topMargin = 30;
      	
        //Create ratings title text view
        TextView ratingsTitleView = new TextView(this);
        //Set text for title
        ratingsTitleView.setText("Rate Service");
        //Add ratings title with centered params
        myLayout.addView(ratingsTitleView, topMarginParams);
        
        //Create Rating bar to rate service
        RatingBar serviceRating = new RatingBar(this);
        //Add rating bar to my layout with centered params
        myLayout.addView(serviceRating, centerParams);

        //Create calculate button
        Button calculateButton = new Button(this);
        //Set button text
        calculateButton.setText("Calculate Tip");
        //Set width of the button
        calculateButton.setWidth(500);
        //Add button to my layout with centered params
        myLayout.addView(calculateButton, centerParams);
        
        //Create tip display text view
        tipDisplay = new TextView(this);
        tipDisplay.setText("Tip will display here");
        myLayout.addView(tipDisplay, centerParams);
        
        //Create onClick event for calculate button
        calculateButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				tipDisplay.setText("click works");
				
			}
		});
        
		setContentView(myLayout); //R.layout.activity_main
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

}
