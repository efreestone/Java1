//Elijah Freestone
//Java 1 term 1403
//Week 4
//March 22nd, 2014

package com.elijahfreestone.cities;

import java.net.MalformedURLException;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;

import com.elijahfreestone.java1project4.MainActivity;

//Async getData class to call url
public class getData extends AsyncTask<String, Void, String>{

	@Override
	protected String doInBackground(String... params) {
		CitiesJSON.responseString = "";
		try {
			//Apply testURL to local url
			URL url = new URL(MainActivity.fullURLString);
			//Call getResponse and apply to responseString
			CitiesJSON.responseString = CitiesJSON.getResponse(url);
		} catch (MalformedURLException e) {
			//Set response string to error
			CitiesJSON.responseString = "Something went wrong in getData";
			//e.printStackTrace();
			//Log error
			Log.e(CitiesJSON.TAG, "ERROR: ", e);
		}
		//Return responseString
		return CitiesJSON.responseString;
	}
	
	//Override onPostExecute to display response
	@Override
	protected void onPostExecute(String result) {
		try {
			//Parse JSON and display according to settings selected
			MainActivity.displayTempView.setText(CitiesJSON.readJSON(result));
			//Recenter display text
			MainActivity.displayTempView.setGravity(Gravity.CENTER);
			super.onPostExecute(result);
			//System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}