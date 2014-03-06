//Elijah Freestone
//Java 1 term 1403
//Week 1
//March 4th, 2014

package com.elijahfreestone.java1project1;

import android.os.Bundle;
import android.widget.Button;
//Import linear layout widget
import android.widget.LinearLayout;
//Import text view widget
import android.widget.TextView;

import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
//Import layout params 
import android.view.ViewGroup.LayoutParams;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Create linear layout instance
		LinearLayout myLayout = new LinearLayout(this);
		//Set orientation
		myLayout.setOrientation(LinearLayout.VERTICAL);
		//Set my layout parameters to match the parent item
		myLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		
		//Create left margin param
		LinearLayout.LayoutParams leftMarginParams = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftMarginParams.leftMargin = 250;
        
        //Create center param used to center various items
        LinearLayout.LayoutParams centerParams = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //Set gravity to center
        centerParams.gravity = Gravity.CENTER;

		//Create title text view
        TextView titleView = new TextView(this);
        //Set parameters for the title
        LayoutParams titleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //Pass layout param for title
        titleView.setLayoutParams(titleParams);
        //Set text appearance for title
        titleView.setTextAppearance(this, android.R.attr.textAppearanceLarge);
        //Set text for title
        titleView.setText("Tip Calculator");
        //Add centered title
        myLayout.addView(titleView, centerParams);

        //Create calculate button
        Button calculateButton = new Button(this);
        calculateButton.setText("Calculate Tip");
        myLayout.addView(calculateButton, centerParams);
		
		setContentView(myLayout); //R.layout.activity_main
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
