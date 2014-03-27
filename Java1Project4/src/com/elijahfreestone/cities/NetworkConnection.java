//Elijah Freestone
//Java 1 term 1403
//Week 4
//March 22nd, 2014

package com.elijahfreestone.cities;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.elijahfreestone.java1project4new.MainActivity;
import com.elijahfreestone.cities.CitiesJSON;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;

public class NetworkConnection {
	// Declare TAG constant
	static String TAG = "NETWORK DATA - NETWORKCONNECTION";
	// Declare response string
	static String responseString;

	// Method to test network
	public static Boolean connectionStatus(Context myContext) {
		// Set connection bool to false
		Boolean connectionBool = false;

		// Create connectivity manager
		ConnectivityManager connectionManager = (ConnectivityManager) myContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		// Create Network info
		NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
		// Check if network info is null
		if (networkInfo != null) {
			// Check if network info is connected
			if (networkInfo.isConnected()) {
				// Set connection bool to true
				connectionBool = true;
			}
		}
		// Return connection bool
		return connectionBool;
	}

	// Method to get response from url
	public static String getResponse(URL url) {
		// Create string for response
		String response = "";

		try {
			// Open connection
			URLConnection connection = url.openConnection();
			// Create buffered input stream
			BufferedInputStream bufferedInputStream = new BufferedInputStream(connection.getInputStream());
			// Create byte and set to 1024
			byte[] contextByte = new byte[1024];
			// Create int for bytes read
			int byteRead = 0;
			// Create string buffer for adding response to
			StringBuffer responseBuffer = new StringBuffer();
			// While loop
			while ((byteRead = bufferedInputStream.read(contextByte)) != -1) {
				// Set response string to receive bytes
				response = new String(contextByte, 0, byteRead);
				// Append response to response buffer
				responseBuffer.append(response);
			}

			// Fill response string with response buffer once while loop
			// completes
			response = responseBuffer.toString();
			// System.out.println(response);
			// Log response
			Log.i(TAG, response);
		} catch (IOException e) {
			// Set response to error
			response = "URL broken";
			// e.printStackTrace();
			// Log error
			Log.e(TAG, "Something went wrong", e);
		}
		// Return reponse string
		return response;
	}

	// Async getData class to call url
	public static class getData extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			responseString = "";
			try {
				// Apply testURL to local url
				URL url = new URL(MainActivity.fullURLString);
				// Call getResponse and apply to responseString
				responseString = getResponse(url);
			} catch (MalformedURLException e) {
				// Set response string to error
				responseString = "Something went wrong in getData";
				// e.printStackTrace();
				// Log error
				Log.e(TAG, "ERROR: ", e);
			}
			// Return responseString
			return responseString;
		}

		// Override onPostExecute to display response
		@Override
		protected void onPostExecute(String result) {
			try {
				// Parse JSON and display according to settings selected
				MainActivity.displayTempView.setText(CitiesJSON.readJSON(result));
				// Recenter display text
				MainActivity.displayTempView.setGravity(Gravity.CENTER);
				super.onPostExecute(result);
				// System.out.println(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
