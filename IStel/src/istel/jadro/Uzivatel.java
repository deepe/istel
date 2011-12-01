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
            ResultSet rs = stm.executeQuery(DatabaseSetting.QUERY_SELECT_BEST_TIMES);

            if(rs.getFetchSize() == 0) {
                System.out.println("Uzivatel neexistuje!");
            } else if(rs.getFetchSize() == 1) {
                this.prava = Integer.parseInt(rs.getString("prava"));
            } else {
                System.out.println("Chyba pri autentifikacii!");
            }

            rs.close();
            stm.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Nemozem identifykovat uzivatela");
        }
    }
    
    public boolean jeObsluha() {
        vyberPrava();
        if((prava & UzivatelPrava.OBSLUHA) == 1) {
            return true;
        }
        return false;
    }
    
    public boolean jeAdministrator() {
        vyberPrava();
        if((prava & UzivatelPrava.ADMINISTARTOR) == 1) {
            return true;
        }
        return false;
    }
    
    
    public boolean jePrihlaseny() {
        if(prava != 0) {
            return false;
        }
        return true;
    }
    
    
}
