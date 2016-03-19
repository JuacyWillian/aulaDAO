package auladao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FactoryConnection {
    private Connection con;
    private final String database;
    private final String user;
    private final String pwd;

    public FactoryConnection() {
        this.database = "pessoas";
        this.user = "root";
        this.pwd = "f0d45e";
    }
    
    
    
    public Connection create(){
        
        
        String url = "jdbc:mysql://localhost:3306/"+database;
        
        try {
            con = DriverManager.getConnection(url, user, pwd);
            return con;
            
        } catch (SQLException ex) {
            Logger.getLogger(FactoryConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
