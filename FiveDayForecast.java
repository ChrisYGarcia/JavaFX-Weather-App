/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author Christopher
 */
import java.util.LinkedList;
import java.util.Stack;

public class FiveDayForecast {
    LinkedList<WeatherUI> forecastData = new LinkedList<>();

    public FiveDayForecast() {
       
    }
    
    public void addDay(WeatherUI day) {
        forecastData.add(day);
    }
    

    public Stack<Double> sort() {
        
     Stack<Double> tempStack = new Stack<>();
     Stack<Double> sortedStack = new Stack<>();
     
     forecastData.stream().map((i) -> i.reportTemp()).forEachOrdered((x) -> {
         tempStack.push(x);
        });
     
        
         while(!tempStack.isEmpty()) {
             double val = tempStack.pop();
             while(!sortedStack.isEmpty() && sortedStack.peek() > val) {
                 double val2 = sortedStack.pop();
                 tempStack.push(val2);
             }
                 sortedStack.push(val);
         }
         return sortedStack;
   }

    public String findDay(double temp) {
        String dateOfTemp = "";
         for(WeatherUI o : forecastData){
             if(o.reportTemp() == temp) {
                 dateOfTemp = o.convertedTimeShort();
             }
        }
         
         return dateOfTemp;
    }
    
    public String Output() {
        
       String s = "";
        for(WeatherUI o : forecastData){
        }
        return s;
    }
    
}
