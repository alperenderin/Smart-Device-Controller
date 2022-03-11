/**
* @file Main.java
* @description 
* @date 05.03.2020
* @author Alperen Derin
*/

package Project;

import java.sql.* ; // for standard JDBC programs
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, InterruptedException{
        
        Scanner input = new Scanner(System.in);
        NetworkInterface network = new NetworkInterface();
        
        String username = null;
        String password = null;
       

        if(network.connectDatabase() == false)
            System.exit(0);
           
        do{
	        if(network.getUserFromDatabase() == true)
	            System.out.println("Veritabanýnda Onceden Bulunan Kullanýcilar Observer Olarak Eklendiler");
	        
	        System.out.println("\n1-Uye Ol\n2-Giris Yap");
	        int user_choice = input.nextInt();
	    
	        if(user_choice == 1){
	            System.out.println("Uye Olma Ekranýndasýnýz Bilgilerinizi Doðru Þekilde Doldurunuz");
	            System.out.println("Kullanýcý Adýnýzý Giriniz:");
	            username = input.next(); 
	
	            System.out.println("Sifrenizi Giriniz");
	            password = input.next(); 
	
	            if(network.addUserToDatabase(username, password) != false)
	                System.out.println("Basarýlý Bir Sekilde Uye Oldunuz\n");
	            else{
	                System.out.println("Uyelik Isleminiz Basarýsýz Oldu Giris Ekranýna Yonlendiriliyorsunuz...\n");
	                TimeUnit.SECONDS.sleep(2);
	            }
	        }
	    
	        if(user_choice == 1 || user_choice == 2){
	            System.out.println("Giris Ekranýndasýnýz");
	            System.out.println("Kullanýcý Adýnýzý Giriniz:");
	            username = input.next(); 
	
	            System.out.println("Sifrenizi Giriniz");
	            password = input.next(); 
	        }
        }while(network.verifyUserFromDatabase(username, password) == false);

        network.systemInterface();  
        
    }
}
