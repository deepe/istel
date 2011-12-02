/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package istel.jadro;

import istel.DatabaseSetting;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author t0ki
 */
public class Uzivatel {
    
    String meno;
    String heslo;
    int prava  = 0;
    
    
    public Uzivatel() {
        this.prava = 0;
    }
    
    public Uzivatel(String meno, String heslo) {
        this.meno = meno;
        this.heslo = heslo;
        vyberPrava();
    }
    
    public void vyberPrava() {
        try {
            Class.forName(DatabaseSetting.DRIVER_CLASS);
            Connection connection = DriverManager.getConnection(DatabaseSetting.URL,
                    DatabaseSetting.USER, DatabaseSetting.PASSWORD);

            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(
                    "SELECT prava FROM uzivatelia WHERE meno = \"" + meno +
                    "\" AND heslo = \"" + heslo + "\"");
            
            if (rs.next()) {
                this.prava = rs.getInt(1);
                System.out.println(prava + System.currentTimeMillis());
            }
            
            rs.close();
            stm.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Nemozem identifykovat uzivatela" + e.getLocalizedMessage());
        }
    }
    
    public boolean jeObsluha() {
        vyberPrava();
        if((prava & UzivatelPrava.OBSLUHA) == UzivatelPrava.OBSLUHA) {
            return true;
        }
        return false;
    }
    
    public boolean jeAdministrator() {
        vyberPrava();
        if((prava & UzivatelPrava.ADMINISTARTOR) == UzivatelPrava.ADMINISTARTOR) {
            return true;
        }
        return false;
    }
    
    
    public boolean jeAnonym() {
        if(prava == 0 || prava == UzivatelPrava.ANONYM) {
            return true;
        }
        return false;
    }
    
    
}
