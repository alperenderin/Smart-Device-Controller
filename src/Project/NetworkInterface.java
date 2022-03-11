/**
* @file NetworkInterface.java
* @description 
* @date 05.03.2020
* @author Alperen Derin
*/

package Project;

import java.sql.SQLException;
import java.util.Scanner;

public class NetworkInterface{
    
	OperationUnit operation_unit = OperationUnit.getInstance();
    Database db = Database.getInstance();
    Scanner input = new Scanner(System.in);
    
    int user_choice;
    
    public void systemInterface(){
        System.out.println("\nIOT Cihazý Ana Menusu");
        System.out.println("Sistemin Aktiflik Durumu:" + operation_unit.agent.getCihazDurumu());
        System.out.println("Odanýn Sýcaklýgý:" + operation_unit.temperatureSensor.getTemperature());
        
        System.out.println("\n1-Sicakligi Guncelle\t2-Cihazi Ac/Kapat\t3-Menuden Cik");
        user_choice = input.nextInt();
        if(user_choice == 1)
            operation_unit.controlTemperature();
        else if(user_choice == 2)
            operation_unit.controlStatus();
        else if(user_choice == 3)
            System.exit(1);
    }
    
    public void updateInterface(int temperature, boolean activity){
        System.out.println("\nIOT Cihazi Menusu");
        System.out.println("Sistemin Aktiflik Durumu:" + activity);
        System.out.println("Odanýn Sýcaklýgý:" + temperature);
        
        System.out.println("\n1-Sicakligi Guncelle\t2-Cihazi Ac/Kapat\t3-Menuden Cýk");
        user_choice = input.nextInt();
        if(user_choice == 1)
            operation_unit.controlTemperature();
        else if(user_choice == 2)
            operation_unit.controlStatus();
        else if(user_choice == 3)
            System.exit(1);
    }
    
    public void addObserver(String username,String password){
        operation_unit.addObserver(new ConcreteObserver(username));
        // the process of adding information to the database
    }
    
    ////////////////////////////////////////////// Database Operations /////////////////////////////////////////
    public boolean connectDatabase() throws ClassNotFoundException, SQLException, InterruptedException{
        return db.connectDatabase();
    }
    
    public boolean verifyUserFromDatabase(String username, String password) throws SQLException, InterruptedException{
        return db.verify(username, password);
    }
    
    public boolean addUserToDatabase(String username,String password) throws SQLException{
        operation_unit.addObserver(new ConcreteObserver(username));
        
        return db.addUser(username, password);
    }
    
    public boolean getUserFromDatabase() throws SQLException{
        String users = db.getUsersFromDatabase();
        String regularEx[] = users.split("#");
        
        int index = 0;
        while(regularEx.length != index + 1){
            operation_unit.addObserver(new ConcreteObserver(regularEx[index]));
            index++;
        }
        
        if(operation_unit.observers.isEmpty() && regularEx[0] != null) // if the observer list is empty, but there is a user in the sequence pulled from the database, this mean it's not added
            return false;
        
        return true;
    }
}