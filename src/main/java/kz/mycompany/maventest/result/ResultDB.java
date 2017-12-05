package kz.mycompany.maventest.result;

import kz.mycompany.maventest.dao.*;
import kz.mycompany.maventest.model.*;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicken
 */
public class ResultDB {
    public static String returnRes(String r_type, String r_xml){        
        r_xml = r_xml.replaceAll("\n|\r", "");
        Person unmarshPerson;
        String s = "NOTHING";
            try {
                unmarshPerson = JaxbWorker.fromXML(r_xml);
                if (unmarshPerson != null) {                    
                    s = "SELECT * FROM TESTUSER.PERSON WHERE UPPER(NAME) = UPPER('"+unmarshPerson.getName()+"') and UPPER(POSITION) = UPPER('"+unmarshPerson.getPosition()+"')";                    
                }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(ResultDB.class.getName()).log(Level.SEVERE, null, ex);
            }    
        
        
        if (s.equals("NOTHING")){
            return null;
        }
        
        String res = null;
        Connection con = ConnectionDB.getInstance().getConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(s);
                if (rs.next()) {
                    if (r_type.equals("RSalary")){
                        res = rs.getString("SALARY");
                    } else if(r_type.equals("RAge")){
                        res = rs.getString("AGE");
                    }                                        
                } 
            statement.close();
            rs.close();
            ConnectionDB.getInstance().closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ResultDB.class.getName()).log(Level.SEVERE, null, ex);            
        }        
        return res;
    }
}
