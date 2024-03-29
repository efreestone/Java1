//Elijah Freestone
//Java 1 term 1403
//Week 2
//March 9th, 2014

package com.elijahfreestone.java1project2;

//Create enum of cities enhanced with extra fields (strings an floats)
public enum CitiesEnum {
	Loveland("Loveland", "CO", 31.6f, -0.2f),
	LosAngeles("Los Angeles", "CA", 68.8f, 20.4f),
	NewYork("New York", "NY", 59.5f, 15.2f),
	Chicago("Chicago", "IL", 38.3f, 3.5f),
	Orlando("Orlando", "FL", 71.7f, 22.0f);
	
	//Declare local instance variables
	private String cityName;
	private String stateName;
	private Float fahrenheitTemp;
	private Float celsiusTemp;
	
	//Create constructor for enum fields
	private CitiesEnum(String cityName, String stateName, Float fahrenheitTemp, Float celsiusTemp){
		this.cityName = cityName;
		this.stateName = stateName;
		this.fahrenheitTemp = fahrenheitTemp;
		this.celsiusTemp = celsiusTemp;
	}

	//Setters for enum strings/floats
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public void setFahrenheitTemp(Float fahrenheitTemp) {
		this.fahrenheitTemp = fahrenheitTemp;
	}

	public void setCelsiusTemp(Float celsiusTemp) {
		this.celsiusTemp = celsiusTemp;
	}

	//Getters for enum strings/floats
	public String getCityName() {
		return cityName;
	}

	public String getStateName() {
		return stateName;
	}

	public Float getFahrenheitTemp() {
		return fahrenheitTemp;
	}

	public Float getCelsiusTemp() {
		return celsiusTemp;
	}
}
