package com.mycompany.mavenproject1;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import com.google.gson.*;
import com.google.gson.reflect.*;
import java.awt.image.BufferedImage;
import org.json.JSONArray; 
import org.json.JSONObject; 
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;



/**
 * JavaFX App
 */
public class App extends Application {


     public static Map<String, Object> jsonToMap(String str) {
        Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {}.getType());
        
        return map;
    }

    @Override
    public void start(Stage stage) {
 
        Group root = new Group();
		Scene scene = new Scene(root, 1550, 900);
		stage.setScene(scene);
		stage.setTitle("Weather Station");
                scene.getStylesheets().add(getClass().getClassLoader().getResource("style1.css").toExternalForm());
                
                // Setting up GridPane
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(25);
		grid.setHgap(25);
		scene.setRoot(grid);

		// Country Name
		TextField country = new TextField();
		country.setPromptText("Country Name");
		country.setPrefColumnCount(20);
		GridPane.setConstraints(country, 0, 0);
		grid.getChildren().add(country);
		country.setPrefHeight(20);
		// City Name
		TextField city = new TextField();
		GridPane.setConstraints(city, 0, 1);
		grid.getChildren().add(city);
		city.setPromptText("City Name");
		city.setPrefColumnCount(20);
		city.setPrefHeight(20);
		// Button Part
		Button btn = new Button();
                Button btn2 = new Button();
		grid.getChildren().add(btn);
                grid.getChildren().add(btn2);
		btn.setText("Search");
                btn2.setText("Reset");
		GridPane.setConstraints(btn, 0, 4);
                
		GridPane.setHalignment(btn, HPos.RIGHT);
                GridPane.setConstraints(btn2, 0, 5);
                GridPane.setHalignment(btn2, HPos.RIGHT);
              //  grid.setGridLinesVisible(true);
             
               // When user clicks the Search Button
		stage.show();
                btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
                            
                                // Loading up Labels and Images
                                Label lblTemperature = new Label();
                                Label lblHumidity = new Label();
				Label lblPressure = new Label();
                                Label lblDescription = new Label();
                                Label lblTime = new Label();      
                                Label lblDayOne = new Label();
                                Label lblDayTwo = new Label();
                                Label lblDayTwoDW = new Label();
                                Label lblDayTwoT = new Label();
                                Label lblDayTwoH = new Label();
                                Label lblDayTwoWS = new Label();
                                Label lblDayTwoDate = new Label();
                                Label lblDayThree = new Label();
                                Label lblDayThreeDW = new Label();
                                Label lblDayThreeT = new Label();
                                Label lblDayThreeH = new Label();
                                Label lblDayThreeWS = new Label();
                                Label lblDayThreeDate = new Label();
                                Label lblBestTemps = new Label();
                                Label lblBestTemps1 = new Label();
                                Label lblBestTemps2 = new Label();
                                Label lblBestTemps3 = new Label();
                                Label lblBestTemps4 = new Label();
                                Label lblBestTemps5 = new Label();  
                                ImageView iv = new ImageView();
                                ImageView iv2 = new ImageView();
                                ImageView iv3 = new ImageView();
                                ImageView iv4 = new ImageView();
                                ImageView iv5 = new ImageView();
                                
                                // Getting User Input for Country, City
				String countryValue = country.getText();
				String cityValue = city.getText();
                                Label weatherConditionlbl = new Label("Weather conditions for " + city.getText());
                                GridPane.setConstraints(weatherConditionlbl, 2, 0);
                                grid.getChildren().add(weatherConditionlbl);
                                
                                // Loading up API URL to get data
                                String API_KEY = "ENTER_YOUR_API_KEY_HERE";
                                String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + cityValue + ","
							+ countryValue + "&appid=" + API_KEY + "&units=imperial";    

				try {				
                                        StringBuilder result = new StringBuilder();
					URL url = new URL(urlString);
					URLConnection conn =  url.openConnection();
										
					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                                        String line;
                                        
                                        while((line = br.readLine()) != null ) {
                                            result.append(line);
                                        }   
                                        br.close();
                                        
                                        Map<String, Object> respMap = jsonToMap(result.toString());
                                        JSONArray obj = new JSONObject(result.toString()).getJSONArray("weather");
                                        Object obj2 = new JSONObject(result.toString()).get("dt");
                                        String timeOfCalcStr = obj2.toString();
                                        long timeOfCalc = Long.parseLong(timeOfCalcStr);
                                      
                                      //  System.out.println(obj.getJSONObject(0).getString("main"));
                                        String desc = obj.getJSONObject(0).getString("main");
                                        String icon = obj.getJSONObject(0).getString("icon");
                                        
                                        
                                         
                                       
                                        Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
                                        Map<String, Object> windMap = jsonToMap(respMap.get("wind").toString());
                                     
                                     
                                      
                                        String temp = mainMap.get("temp").toString();
                                        String humidity = mainMap.get("humidity").toString();
                                        String windspeed = windMap.get("speed").toString();
                                             
					String urlString2 = "http://api.openweathermap.org/data/2.5/forecast?q=" + cityValue + ","
							+ countryValue + "&appid=" + API_KEY + "&units=imperial";
                                        
					try {				
                                        StringBuilder result2 = new StringBuilder();
					URL url2 = new URL(urlString2);
					URLConnection conn2 =  url2.openConnection();
										
					BufferedReader br2 = new BufferedReader(new InputStreamReader((conn2.getInputStream())));
                                        String line2;
                                        
                                        while((line2 = br2.readLine()) != null ) {
                                            result2.append(line2);
                                        }   
                                        br2.close();
//                                        
//                                        JSONArray listObj = new JSONObject(result2.toString()).getJSONArray("list");
//                                          for(int i = 0; i < listObj.length(); i++) {
//                                              if(i % 8 == 0) {
//                                                  JSONObject innerObj = listObj.getJSONObject(i);
//                                                  for(Iterator it = innerObj.keys(); it.hasNext(); ) {
//                                                  String key = (String)it.next();
//                                              //    System.out.println(key + ":" + innerObj.get(key));
//                                        }
//                                              }
//                                          }
                                                                                
//                                        JSONArray obj = new JSONObject(result.toString()).getJSONArray("weather");
//                                        Object obj2 = new JSONObject(result.toString()).get("dt");
//                                        String timeOfCalcStr = obj2.toString();
//                                        long timeOfCalc = Long.parseLong(timeOfCalcStr);
//                                      
                                        
//                                        System.out.println(obj.getJSONObject(0).getString("main"));
//                                        String desc = obj.getJSONObject(0).getString("main");
//                                        String icon = obj.getJSONObject(0).getString("icon");
                                        
                                        // Setting up the JSON values from the forecast API JSON
                                        JSONObject testObj = new JSONObject(result2.toString());
                                        JSONArray list = testObj.optJSONArray("list");
                                        FiveDayForecast forecast = new FiveDayForecast();       // Forecast LinkedList
                                        
                                        // Iterating through List array, every 8 times to get full 24 hrs of weather data
                                        if (list != null) {
                                            for (int i = 4; i < list.length(); i++) {
                                                if( i % 8 == 0) {
                                              // going through every 8th JSON object starting from 4
                                             JSONObject objAtIndex =  list.optJSONObject(i);
                                            if (objAtIndex != null) {
                                            // Parsing data and putting it into WeatherData and WeatherUI
                                            JSONObject mainTemps = objAtIndex.optJSONObject("main");                                         
                                            JSONArray smallImageUrls = objAtIndex.optJSONArray("weather");
                                            JSONObject windTemp = objAtIndex.optJSONObject("wind");  
                                            
                                            double tempVal = mainTemps.getDouble("temp");
                                            double humidVal = mainTemps.getDouble("humidity");
                                            double windsVal = windTemp.getDouble("speed");
                                            
                                            WeatherData wd = new WeatherData(tempVal,humidVal,windsVal);
                                            Long dateStr = objAtIndex.getLong("dt");
                                            JSONObject testObj2 = smallImageUrls.optJSONObject(0);
                                            String tester = testObj2.get("main").toString();
                                            String tester2 = testObj2.get("icon").toString();
                                            WeatherUI wui = new WeatherUI(tester2, tester, dateStr, wd);
                                            forecast.addDay(wui);
                                            
                                            System.out.println(i);
                                          }
                                            
                                                }
                                    }
                                        }
                                        
                                       
                                        
                                   //     System.out.println(forecast.Output());
                                        
                                        GridPane.setConstraints(lblDayOne, 0, 4);
					grid.getChildren().add(lblDayOne);
                                        GridPane.setConstraints(lblDescription, 2, 1);
					grid.getChildren().add(lblDescription);
                                        
					GridPane.setConstraints(lblTemperature, 2, 2);
					grid.getChildren().add(lblTemperature);
					// Humidity
					GridPane.setConstraints(lblHumidity, 2, 3);
					grid.getChildren().add(lblHumidity);
                                            
					// Pressure
					GridPane.setConstraints(lblPressure, 2, 4);
					grid.getChildren().add(lblPressure);
                                        GridPane.setConstraints(lblTime, 2, 5);
					grid.getChildren().add(lblTime);
                                            					
				
					WeatherData  currWeather = new WeatherData();
                                        currWeather.setTemp(Double.parseDouble(temp));
					currWeather.setHumidity(Double.parseDouble(humidity));
                                        currWeather.setWindSpeed(Double.parseDouble(windspeed));
                                        WeatherUI weatherUI = new WeatherUI(icon, desc, timeOfCalc, currWeather);
                                        forecast.addDay(weatherUI);
                                        
                                        
                                        lblDescription.setText("Description / Warning: " + weatherUI.showWarning());
					lblTemperature.setText("Temperature: " + weatherUI.reportTemp() + "°F");
					lblHumidity.setText("Humidity: " + weatherUI.reportHumidity() + "%");
					lblPressure.setText("Wind Speed: " + weatherUI.reportWindSpeed() + " MPH");
                                        lblTime.setText("Date of Approx. Calculation: " + weatherUI.convertedTime());
                                        
                                        URL url3 = new URL(weatherUI.getImageURL());
                                        URLConnection connection = url3.openConnection();
                                        connection.setRequestProperty("User-Agent", "xxxxxx");
                                       
                                        BufferedImage image = ImageIO.read(url3);
                                        Image image2 = SwingFXUtils.toFXImage(image, null);
                                        
                                        iv.setImage(image2);
                                        iv.setPreserveRatio(true);
                                        iv.setFitHeight(100);
                                        iv.setFitWidth(100);
                                        grid.getChildren().add(iv);
                                        GridPane.setConstraints(iv, 3, 1);
                                        
                                        
                                       
                                        lblDayTwo.setText("Day Two");                                      
                                        grid.getChildren().add(lblDayTwo);
                                        GridPane.setConstraints(lblDayTwo, 0, 8);
                                        lblDayTwoDW.setText(forecast.forecastData.get(0).Description);                                      
                                        grid.getChildren().add(lblDayTwoDW);
                                        GridPane.setConstraints(lblDayTwoDW, 0, 9);
                                        URL url4 = new URL(forecast.forecastData.get(0).getImageURL());
                                        URLConnection connection1 = url4.openConnection();
                                        connection1.setRequestProperty("User-Agent", "xxxxxx");                                     
                                        BufferedImage image1 = ImageIO.read(url4);
                                        Image image3 = SwingFXUtils.toFXImage(image1, null);                                    
                                        iv2.setImage(image3);
                                        iv2.setPreserveRatio(true);
                                        iv2.setFitHeight(50);
                                        iv2.setFitWidth(50);
                                        grid.getChildren().add(iv2);
                                        GridPane.setConstraints(iv2, 0, 10); 
                                        lblDayTwoT.setText(""+forecast.forecastData.get(0).reportTemp() + "°F");
                                        grid.getChildren().add(lblDayTwoT);
                                        GridPane.setConstraints(lblDayTwoT, 0, 11); 
                                        lblDayTwoH.setText(""+forecast.forecastData.get(0).reportHumidity() + "%");
                                        grid.getChildren().add(lblDayTwoH);
                                        GridPane.setConstraints(lblDayTwoH, 0, 12); 
                                        lblDayTwoWS.setText(""+forecast.forecastData.get(0).reportWindSpeed() + " MPH");
                                        lblDayTwoH.setText(""+forecast.forecastData.get(0).reportHumidity() + "%");
                                        lblDayTwoWS.setText(""+forecast.forecastData.get(0).reportWindSpeed() + " MPH");
                                        grid.getChildren().add(lblDayTwoWS);
                                        GridPane.setConstraints(lblDayTwoWS, 0, 13);
                                        lblDayTwoDate.setText(forecast.forecastData.get(0).convertedTimeShort());
                                        grid.getChildren().add(lblDayTwoDate);
                                        GridPane.setConstraints(lblDayTwoDate, 0, 14);
                                        
                                        lblDayThree.setText("Day Three          Day Four          Day Five");
                                        grid.getChildren().add(lblDayThree);
                                        GridPane.setConstraints(lblDayThree, 2, 8);
                                                                    
                                        lblDayThreeDW.setText(forecast.forecastData.get(1).Description + "              " 
                                        + forecast.forecastData.get(2).Description + "            "
                                        + forecast.forecastData.get(3).Description);
                                        grid.getChildren().add(lblDayThreeDW);
                                        GridPane.setConstraints(lblDayThreeDW, 2, 9);
                                        URL url5 = new URL(forecast.forecastData.get(1).getImageURL());
                                        URLConnection connection2 = url5.openConnection();
                                        connection1.setRequestProperty("User-Agent", "xxxxxx");                                     
                                        BufferedImage imagelbl3 = ImageIO.read(url5);
                                        Image image4 = SwingFXUtils.toFXImage(imagelbl3, null);                                    
                                        iv3.setImage(image4);
                                        iv3.setPreserveRatio(true);
                                        iv3.setFitHeight(50);
                                        iv3.setFitWidth(50);
                                   //     grid.getChildren().add(iv3);
                                //        GridPane.setConstraints(iv3, 2, 10);
                                        URL url6 = new URL(forecast.forecastData.get(2).getImageURL());
                                        URLConnection connection3 = url6.openConnection();
                                        connection1.setRequestProperty("User-Agent", "xxxxxx");                                     
                                        BufferedImage imagelbl4 = ImageIO.read(url6);
                                        Image image5 = SwingFXUtils.toFXImage(imagelbl4, null);                                    
                                        iv4.setImage(image5);
                                        iv4.setPreserveRatio(true);
                                        iv4.setFitHeight(50);
                                        iv4.setFitWidth(50);
                                        URL url7 = new URL(forecast.forecastData.get(3).getImageURL());
                                        URLConnection connection4 = url7.openConnection();
                                        connection1.setRequestProperty("User-Agent", "xxxxxx");                                     
                                        BufferedImage imagelbl5 = ImageIO.read(url7);
                                        Image image6 = SwingFXUtils.toFXImage(imagelbl5, null);                                    
                                        iv5.setImage(image6);
                                        iv5.setPreserveRatio(true);
                                        iv5.setFitHeight(50);
                                        iv5.setFitWidth(50);
                                        HBox ab = new HBox(225);
                                        ab.getChildren().addAll(iv3, iv4);
                                        ab.getChildren().add(iv5);
                                        grid.getChildren().add(ab);
                                        GridPane.setConstraints(ab, 2, 10);
                                        lblDayThreeT.setText(forecast.forecastData.get(1).reportTemp() + "°F" + "            " 
                                        + forecast.forecastData.get(2).reportTemp() + "°F" + "           "
                                        + forecast.forecastData.get(3).reportTemp() + "°F");
                                        grid.getChildren().add(lblDayThreeT);
                                        GridPane.setConstraints(lblDayThreeT, 2, 11);
                                        lblDayThreeH.setText(forecast.forecastData.get(1).reportHumidity() + "%" + "              " 
                                        + forecast.forecastData.get(2).reportHumidity() + "%" + "             "
                                        + forecast.forecastData.get(3).reportHumidity()+ "%");
                                        grid.getChildren().add(lblDayThreeH);
                                        GridPane.setConstraints(lblDayThreeH, 2, 12);
                                        lblDayThreeWS.setText(forecast.forecastData.get(1).reportWindSpeed() + " MPH" + "           " 
                                        + forecast.forecastData.get(2).reportWindSpeed() + " MPH" + "         "
                                        + forecast.forecastData.get(3).reportWindSpeed()+ " MPH");
                                        grid.getChildren().add(lblDayThreeWS);
                                        GridPane.setConstraints(lblDayThreeWS, 2, 13);
                                        lblDayThreeDate.setText(forecast.forecastData.get(1).convertedTimeShort() + "         "
                                        + forecast.forecastData.get(2).convertedTimeShort() + "        " + 
                                        forecast.forecastData.get(3).convertedTimeShort());
                                        grid.getChildren().add(lblDayThreeDate);
                                        GridPane.setConstraints(lblDayThreeDate, 2, 14);
                                        
                                        
                                        
                                        Stack<Double> sortedTemps = forecast.sort();   
                                       
                                         
                                       //  System.out.println(forecast.findDay(sortedTemps.pop()));
                                         
                                        lblBestTemps.setText("Best Weather Days:");
                                        grid.getChildren().add(lblBestTemps);
                                        GridPane.setConstraints(lblBestTemps, 3, 8);                             
                                        lblBestTemps1.setText(forecast.findDay(sortedTemps.pop()));
                                        grid.getChildren().add(lblBestTemps1);
                                        GridPane.setConstraints(lblBestTemps1, 3, 9);
                                        lblBestTemps2.setText(forecast.findDay(sortedTemps.pop()));
                                        grid.getChildren().add(lblBestTemps2);
                                        GridPane.setConstraints(lblBestTemps2, 3, 10);
                                        lblBestTemps3.setText(forecast.findDay(sortedTemps.pop()));
                                        grid.getChildren().add(lblBestTemps3);
                                        GridPane.setConstraints(lblBestTemps3, 3, 11);
                                        lblBestTemps4.setText(forecast.findDay(sortedTemps.pop()));
                                        grid.getChildren().add(lblBestTemps4);
                                        GridPane.setConstraints(lblBestTemps4, 3, 12);
                                        lblBestTemps5.setText(forecast.findDay(sortedTemps.pop()));
                                        grid.getChildren().add(lblBestTemps5);
                                        GridPane.setConstraints(lblBestTemps5, 3, 13);
                                        
                                 btn2.setOnAction(e -> {
                                     
                                        lblTemperature.setText("");
                                        lblHumidity.setText("");
                                        lblPressure.setText("");
                                        lblDescription.setText("");
                                        lblTime.setText("");
                                        weatherConditionlbl.setText("");
                                        lblDayOne.setText("");
                                        lblDayTwo.setText("");
                                        lblDayTwoDW.setText("");
                                        lblDayTwoT.setText("");
                                        lblDayTwoH.setText("");
                                        lblDayTwoWS.setText("");
                                        lblDayTwoDate.setText("");
                                        lblDayThree.setText("");
                                        lblDayThreeDW.setText("");
                                        lblDayThreeT.setText("");
                                        lblDayThreeH.setText("");
                                        lblDayThreeWS.setText("");
                                        lblDayThreeDate.setText("");
                                        lblBestTemps.setText("");
                                        lblBestTemps1.setText("");
                                        lblBestTemps2.setText("");
                                        lblBestTemps3.setText("");
                                        lblBestTemps4.setText("");
                                        lblBestTemps5.setText("");
                                        iv.setImage(null);
                                        iv2.setImage(null);
                                        iv3.setImage(null);
                                        iv4.setImage(null);
                                        iv5.setImage(null);
                                
                                
                                });
                                 
                                 
                                } 
                                  catch (MalformedURLException e) {
				} catch (IOException | IllegalArgumentException e) {
				}
			
                                
                                } catch (MalformedURLException e) {
				} catch (IOException | IllegalArgumentException e) {
				}}
		});
                
            
	}
    
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
