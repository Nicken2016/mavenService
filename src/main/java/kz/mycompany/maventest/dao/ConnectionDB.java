package kz.mycompany.maventest.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *
 * @author Nicken
 */
public class ConnectionDB {
    private static Connection connection;
    private ConnectionDB() {
    }
    private static ConnectionDB instance = null;
    
    public static ConnectionDB getInstance(){
        if (instance==null)
            instance = new ConnectionDB();
        return instance;
    }
    
    public Connection getConnection(){
        Context ctx;
        connection = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("jdbc/DerbyRes");
             connection = ds.getConnection();
        } catch (NamingException e) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException e) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, e);
        }
        return connection;
    }
    
    public void closeConnection() {
        if (connection != null) {            
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
    }
    
}
