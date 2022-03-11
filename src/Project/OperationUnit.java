/**
* @file OperationUnit.java
* @description 
* @date 05.03.2020
* @author Alperen Derin
*/

package Project;

import java.util.List;
import java.util.ArrayList;

public class OperationUnit {

    private static OperationUnit instance = null;
    private int temperature = 0;
    private boolean activity = false;
    
    public Agent agent;
    public TemperatureSensor temperatureSensor;
       
    public final List<IObserver> observers = new ArrayList<IObserver>();

    public void controlStatus(){
        if(false == activity){
            activity = agent.turnOnDevice();
            this.Notify(temperature, activity);
        }
        else if(true == activity){
            activity = agent.turnOffDevice();
            this.Notify(temperature, activity);
        }
    }

    public void controlTemperature(){
        if(temperature != temperatureSensor.measureTemperature())
            setTemperature(temperatureSensor.measureTemperature());
    }
    
    public void setTemperature(int new_temperature)
    {
        this.temperature = new_temperature;
        this.Notify(temperature, activity);
    }
    
    /////////////////////////////// Observer Pattern Operations ////////////////////////////////////////
    // will update their interfaces via the update method by calling the update method for each user
    private void Notify(int temperature, boolean activity){
        // when there is any change, the Update method of our observers is triggered and the desired action is taken.    
        for(IObserver user : observers)
           user.update(temperature, activity);
    }
        
    public void addObserver(IObserver user){
        this.observers.add(user);
    }
    
    public void removeObserver(IObserver user){
        this.observers.remove(user);
    }

    // we will check the temperature from the temperature sensor at certain times
    // if the temperature has changed, the temperature change will be reflected on the interface of all users
    private OperationUnit(){
        agent = Agent.getAgentInstance();
        temperatureSensor = TemperatureSensor.getTemperatureInstance();	//We get random values for this scenario
    }

    public static OperationUnit getInstance(){
        if(instance == null)
            instance = new OperationUnit();

        return instance;
    }
}