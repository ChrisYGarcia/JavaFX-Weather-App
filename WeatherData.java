/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author Chris
 */
public class WeatherData {
        double temp;
	double windspeed;
	double humidity;
	double temp_min;
	double temp_max;
        
        public WeatherData (double t, double h, double ws){
            temp = t;
            humidity = h;
            windspeed = ws;
        
        }
        
        public WeatherData (){
          
        
        }
        
	public double getTemp() {
		return temp;
	}
	public void setTemp(double t) {
		temp = t;
	}
	public double getWindSpeed() {
		return windspeed;
	}
	public void setWindSpeed(double ws) {
		windspeed = ws;
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double h) {
		humidity = h;
	}
	public double getTemp_min() {
		return temp_min;
	}
	public void setTemp_min(double temp_min) {
		this.temp_min = temp_min;
	}
	public double getTemp_max() {
		return temp_max;
	}
	public void setTemp_max(double temp_max) {
		this.temp_max = temp_max;
	}
}
