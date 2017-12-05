package kz.mycompany.maventest.model;

import javax.xml.bind.annotation.*;

/**
 *
 * @author Nicken
 */
@XmlRootElement(name = "PERSON")
@XmlType(propOrder = {"name", "position"})
public class Person {        
    private String name;    
    private String position;
    
    public Person() {
    }

    public Person(String name, String position) {        
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }
    
    @XmlElement( name = "NAME" )
    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }
    
    @XmlElement( name = "POSITION" )
    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", position=" + position + '}';
    }

      
    
}
