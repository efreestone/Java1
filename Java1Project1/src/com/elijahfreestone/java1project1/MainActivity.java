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
import android.graphics.Color;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
//Import layout params 
import android.view.ViewGroup.LayoutParams;

public class MainActivity extends Activity {
	//Global variables
	//Declare tip display text view
	static TextView tipDisplay;
	//Declare entered cost int
	static int enteredCost;
	//Declare enterd cost string
	String enteredString;
	//Declare cost entered boolean
	static boolean costEnteredBool;
	//Declare rating bar
	RatingBar serviceRatingBar;
	//Declare service rating float
	static float serviceRatingFloat;
	//Declare multiplier for tip calculation
	static float tipPercent;
	//Declare tip amount float
	static float tipAmountFloat;

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
        final EditText costText = new EditText(this);
        //Set hint text
        costText.setHint("Cost, rounded to nearest dollar");
        //Set input type to number. This is an integer so cost entered needs to be whole number as of now.
        costText.setInputType(InputType.TYPE_CLASS_NUMBER); //| InputType.TYPE_NUMBER_FLAG_DECIMAL
        
        //Add cost edit text to my layout with width set by center params and the width of Hint
        myLayout.addView(costText, centerParams);
        
        //Create top margin params
      	LinearLayout.LayoutParams topMarginParams = new LinearLayout.LayoutParams(
      			LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
      	//Set gravity to center text view
      	topMarginParams.gravity = Gravity.CENTER;
      	//Set top margin to separate ratings label from the edit text
      	topMarginParams.topMargin = 30;
      	
        //Create ratings title text view
        TextView ratingsTitleView = new TextView(this);
        //Set text for title
        ratingsTitleView.setText("Rate Service");
        //Add ratings title with centered params
        myLayout.addView(ratingsTitleView, topMarginParams);
        
        //Create Rating bar to rate service
        serviceRatingBar = new RatingBar(this);
        //Add rating bar to my layout with centered params
        myLayout.addView(serviceRatingBar, centerParams);

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
        //Set default text
        tipDisplay.setText("Tip will display here");
        //Add tip display to my layout with top margin params
        myLayout.addView(tipDisplay, topMarginParams);
        
        //Create onClick event for calculate button
        calculateButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Grab cost input from edit text field
				enteredString = costText.getText().toString();
				//Check if a cost amount has been entered
				if (costText.getText().toString().equals("")) {
					//Grab string from noCostEntered Resource XML file and cast to local variable
					String noCostString = getResources().getString(R.string.noCostResString);
					//Set text color to red
					tipDisplay.setTextColor(Color.RED);
					//Display Resource string
					tipDisplay.setText(noCostString);
				} else {
					costEnteredBool = true;
					//Grab service rating
					serviceRatingFloat = serviceRatingBar.getRating();
					//Implicitly cast service rating float to int
					int serviceRating = (int)serviceRatingFloat;
					
					//Switch statement to set tip percentage based on star rating. Half stars are rounded down
					switch (serviceRating) {
						//1-1.5 star selected, set tipPercent to %5
						case 1: tipPercent = 0.05f; break;
						//2-2.5 star selected, set tipPercent to %10
						case 2: tipPercent = 0.1f; break;
						//3-3.5 star selected, set tipPercent to %15
						case 3: tipPercent = 0.15f; break;
						//4-4.5 star selected, set tipPercent to %20
						case 4: tipPercent = 0.2f; break;
						//5 star selected, set tipPercent to %25
						case 5: tipPercent = 0.25f; break;
						//No star selected, set tipPercent to %1
						default: tipPercent = 0.01f; break;
					}
					
					//Parse int from cost entered string
					enteredCost = Integer.parseInt(enteredString);
					
					//Call method/function to calculate tip and display
					MainActivity.calculateTip();
				}
			}
		});
        
		setContentView(myLayout); 
	}
	
	//Custom method/function to calculate tip based on cost entered and rating given, then display it
	public static void calculateTip() {
		//Do/while loop to calculate and display tip amount based on cost and rating
			do {
				//Cast enteredCost into float
				float enteredCostFloat = enteredCost;
				//Multiply entered cost int by tip percentage
				tipAmountFloat = enteredCostFloat * tipPercent;
				//Display tip rating and tip amount
				tipDisplay.setText("For a rating of " + serviceRatingFloat + ", your tip should be $" + tipAmountFloat);
			} while (costEnteredBool == false);
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

}
