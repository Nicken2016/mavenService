package kz.mycompany.maventest.result;

import kz.mycompany.maventest.model.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;




/**
 *
 * @author Nicken
 */
public class JaxbWorker {
    public static Person fromXML (String xxx) throws UnsupportedEncodingException{        
        JaxbWorker jw = new JaxbWorker();
        xxx = xxx.replaceAll("\n|\r", "");
        if (!jw.checkXML(xxx)){                        
            return null;    
            }                        
        try {                        
            JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();                     
            StringReader reader = new StringReader(xxx);            
            return (Person) un.unmarshal(reader);            
        } catch (JAXBException ex) {
            Logger.getLogger(JaxbWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     private boolean checkXML(String request) {
         
        try {
            File xsd = new File(getClass().getClassLoader().getResource("/person.xsd").getFile());            
            if (!xsd.exists()) {                
                return false;
            }                        
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsd);
            Validator validator = schema.newValidator();
            byte[] reqBytes = request.getBytes("UTF-8");
            ByteArrayInputStream is = new ByteArrayInputStream(reqBytes);
            validator.validate(new StreamSource(is));
            return true;
        } catch (org.xml.sax.SAXException ex) {
            Logger.getLogger(JaxbWorker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JaxbWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;        
     }    
}
