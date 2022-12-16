package it.unipi.brewathome;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServicebrewathomeApplication {

    private static final String DB_NAME = "gianmaria_saggini";
    
    public static void main(String[] args) {
            
            try (Connection co = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root","");){

            Statement st = co.createStatement();
            st.executeUpdate("CREATE SCHEMA IF NOT EXISTS " + DB_NAME + ";");
            }
            catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            
            SpringApplication.run(ServicebrewathomeApplication.class, args);
    }

}
