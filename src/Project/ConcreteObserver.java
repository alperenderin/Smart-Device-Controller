/**
* @file ConcreteObserver.java
* @description 
* @date 05.03.2020
* @author Alperen Derin
*/

package Project;

public class ConcreteObserver implements IObserver {
    
    public String username;
    NetworkInterface network_interface = new NetworkInterface();
    
    // since the Update method is called separately for each person, the temperature and activity values on their interfaces will be updated separately.
    @Override
    public void update(int temperature, boolean status)
    {
        network_interface.updateInterface(temperature, status);
    }
    
    public ConcreteObserver(String username){
        this.username = username;
    }
    
}