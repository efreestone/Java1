//Elijah Freestone
//Java 1 term 1403
//Week 2
//March 9th, 2014

package com.elijahfreestone.java1project2;

//Create enum of cities enhanced with extra fields (strings an floats)
public enum CitiesEnum {
	LOVELAND("Loveland", "CO", 31.6f, -0.2f),
	LOSANGELES("Los Angeles", "CA", 68.8f, 20.4f),
	NEWYORK("New York", "NY", 59.5f, 15.2f),
	CHICAGO("Chicago", "IL", 38.3f, 3.5f),
	ORLANDO("Orlando", "FL", 71.7f, 22.0f);
	
	//Declare local instance variables
	private final String cityName;
	private final String stateName;
	private final Float fahrTemp;
	private final Float celsiusTemp;
	
	//Create constructor for enum fields
	private CitiesEnum(String cityName, String stateName, Float fahrTemp, Float celsiusTemp){
		this.cityName = cityName;
		this.stateName = stateName;
		this.fahrTemp = fahrTemp;
		this.celsiusTemp = celsiusTemp;
		
	}

	//Create getters for enum strings/floats
	public String getCityName() {
		return cityName;
	}

	public String getStateName() {
		return stateName;
	}

	public Float getFahrTemp() {
		return fahrTemp;
	}

	public Float getCelsiusTemp() {
		return celsiusTemp;
	}
}
