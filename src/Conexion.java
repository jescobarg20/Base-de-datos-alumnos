import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    Connection conectar = null;

    public Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdnotas", "root", "Escobar$2006");
            
            System.out.println("Conexion exitosa");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return conectar;
    }
}