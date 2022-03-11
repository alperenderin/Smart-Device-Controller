/**
* @file TempratureSensor.java
* @description 
* @date 05.03.2020
* @author Alperen Derin
*/

package Project;

import java.util.Random;

public class TemperatureSensor { 
    
    private static TemperatureSensor temperatureInstance = null;
    
    Random rand = new Random();
    private int temperature = rand.nextInt(40);
    
    // the temperature inside the operation unit is checked at regular intervals
    public int measureTemperature(){
        temperature = rand.nextInt(40);
        
       return temperature;
    }
    
    public int getTemperature(){
    	return temperature;
    }

    private TemperatureSensor() {
    	
    }
    
    public static TemperatureSensor getTemperatureInstance(){
        if(temperatureInstance == null)
            temperatureInstance = new TemperatureSensor();
        
        return temperatureInstance;
    }
    
    

}