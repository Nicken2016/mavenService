/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.mycompany.maventest;

import kz.mycompany.maventest.result.*;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Nicken
 */
@WebService(serviceName = "TestWS")
public class TestWS {    
    @WebMethod(operationName = "TestDb")
    public String TestDb(@WebParam(name = "r_type") String r_type, @WebParam(name = "r_xml") String r_xml) {
        String s = ResultDB.returnRes(r_type, r_xml);
        if (s.equals(null)){
            return "Error";
        }
        return s;
    }
}
