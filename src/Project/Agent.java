/**
* @file Aget.java
* @description 
* @date 05.03.2020
* @author Alperen Derin
*/

package Project;

public class Agent {
    private static Agent instanceAgent = null;
    private boolean activity = false;

    public boolean turnOffDevice(){
    	activity = false;
        return activity;
    }
    public boolean turnOnDevice(){
    	activity = true;
        return activity;
    }

    public boolean getCihazDurumu(){
    	return activity;
    }
    
    private Agent(){
    	
    }
    
    public static Agent getAgentInstance(){
        if(instanceAgent == null)
            instanceAgent = new Agent();
        
        return instanceAgent;
    } 
}