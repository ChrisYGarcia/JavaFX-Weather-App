/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;


import java.util.Calendar;
import java.util.Date;

/**S
 *
 * @author Chris Garcia
 */
public class WeatherUI implements WeatherForecast {
    String icon = "";
    String imageURL = "http://openweathermap.org/img/w/" + icon + ".png";
    String Description = "";
    long timeOfCalc = 0;
    
    WeatherData day;

    
    public WeatherUI(String i, String desc, long time, WeatherData d) {
        icon = i;
        Description = desc;
        timeOfCalc = time;
        
        day = d;
    }
    
    public String convertedTime() {
            Date date = new Date ();
            date.setTime((long)timeOfCalc*1000);
            String s = date.toString();
          return s;
    }
    
    public String convertedTimeShort() {
        
            Date date = new Date ();
            date.setTime((long)timeOfCalc*1000);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            String slash = "/";
            int month = (cal.get(Calendar.MONTH));
            month++;
            String monthStr = String.valueOf(month);
            String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
            String year = String.valueOf(cal.get(Calendar.YEAR));
            String dateStr = monthStr + slash +day + slash + year;
            
          return dateStr;
    }

    public double reportTemp() {
       return day.temp;
      
    }
    
    public double reportWindSpeed() {
        return day.windspeed;
    }
    
    public double reportHumidity() {
        return day.humidity;
    }
    
    
    public String getImageURL() {
        imageURL = "http://openweathermap.org/img/w/" + icon + ".png";
        return imageURL;
    }

    public void setImageURL(String url) {
        imageURL = url;
    }

    public String showWarning() {
        return Description;
    }

    public void setDescription(String desc) {
        Description = desc;
    }

    public long getTimeOfCalc() {
        return timeOfCalc;
    }

    public void setTimeOfCalc(long time) {
        
        
        timeOfCalc = time;
    }
    
   @Override
    public String toString() {
        return "WeatherUI{" + "icon=" + icon + ", imageURL=" + imageURL + ", Description=" + Description + ", timeOfCalc=" + timeOfCalc + ", day=" + day + '}';
    }
    
}
