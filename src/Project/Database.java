/**
* @file Database.java
* @description 
* @date 05.03.2020
* @author Alperen Derin
*/

package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class Database {
    
    private static Database instance = null;
    Connection connection = null;
    
    public static Database getInstance(){
        if(instance == null)
            instance = new Database();
        
        return instance;
    }
            
    public boolean connectDatabase() throws ClassNotFoundException, SQLException, InterruptedException{
        String url = "your_url";
        String user = "your_username";
        String password = "your_password";
        
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url, user, password);
        
        if(connection != null){
            System.out.println("Database is connecting...");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Database connected");

            return true;
        }
        System.out.println("Database is not connected");
        return false;
    }
    
    
    public boolean verify(String username, String sifre) throws SQLException, InterruptedException{
        PreparedStatement pst = connection.prepareStatement("SELECT \"kullanici_adi\", \"sifre\" FROM \"kisiler\" ");
        ResultSet rs = pst.executeQuery();
        
        while (rs.next()) {
            
            String tmp_kullanici_adi = rs.getString("kullanici_adi");
            String tmp_password = rs.getString("sifre");
            
            if(tmp_kullanici_adi.equalsIgnoreCase(username) && tmp_password.equalsIgnoreCase(sifre)){
                   System.out.println("Giris Bilgileriniz Dogru Ana Menüye Yönlendiriliyorsunuz...");
                   TimeUnit.SECONDS.sleep(2);
                   return true;
            }
        }
        
        System.out.println("Giris Bilgileriniz Hatali, Secim Ekranina Yonlendiriliyorsunuz...\n");
        TimeUnit.SECONDS.sleep(2);
        return false;
    }
    
    public boolean addUser(String username,String password) throws SQLException{  
        PreparedStatement st = null;
        st = connection.prepareStatement("INSERT INTO kisiler(kullanici_adi,sifre) values(?,?)");
        
        st.setString(1, username);		// username in first position 
        st.setString(2, password);		// password in second position and id in third position (id value is auto-incrementing)
        
        if(st.executeUpdate() != 0)
            return true;
        
        return false;
    }

    String getUsersFromDatabase() throws SQLException {
        String users = null;
        PreparedStatement prep_statement = connection.prepareStatement("SELECT \"kullanici_adi\", \"sifre\", \"id\" FROM \"kisiler\"");
        ResultSet rs = prep_statement.executeQuery();
        
        int index = 0;
        while(rs.next()){
            if(index == 0){
                users = rs.getString("kullanici_adi");
                index++;
                users += "#";
            }
            else{
                users += rs.getString("kullanici_adi");
                users += "#";
            }
        }
        
        return users;
    }
}