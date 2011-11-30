package istel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.*;
import istel.DatabaseSetting;

/**
 *
 * @author erik
 */
public class Jadro {
   
    private static Jadro instance;
    
    //private Uzivatel uzivatel;
    
    public Jadro() {
        instance = this;
        //uzivatel = new anonym();      
    }
    
    public void pridajKontakt(String meno, String priezvisko, String adresa, String cisloDomu, String mesto, String psc, String telefon){
        int cislo = Integer.parseInt(psc);
        System.out.println(cislo);
        System.out.println(mesto);
         try {
            Class.forName(DatabaseSetting.DRIVER_CLASS);
            Connection connection = DriverManager.getConnection(DatabaseSetting.URL,
                    DatabaseSetting.USER, DatabaseSetting.PASSWORD);


            PreparedStatement pstm = connection.prepareStatement(DatabaseSetting.QUERY_ADD_INTO_OBEC);
            pstm.setString(1, mesto);
            
            
           
            pstm.setInt(2, cislo);
            pstm.execute(); 
            pstm.close();
            
         /*   PreparedStatement pstm2 = connection.prepareStatement(DatabaseSetting.QUERY_ADD_INTO_ADRESA);
            pstm2.setString(1, adresa);
           //convert string into t
            int cislo = Integer.parseInt(cisloDomu);
            pstm2.setInt(2, cislo);
            pstm2.execute(); 
            pstm2.close();
            
            PreparedStatement pstm3 = connection.prepareStatement(DatabaseSetting.QUERY_ADD_INTO_OSOBA);
            pstm3.setString(1, meno);
            pstm3.setString(2, priezvisko);
            pstm3.execute(); 
            pstm3.close();
            
            PreparedStatement pstm4 = connection.prepareStatement(DatabaseSetting.QUERY_ADD_INTO_CISLO);
            pstm4.setString(1, telefon);
            pstm4.execute(); 
            pstm4.close();
            */
            connection.close();
        } catch (Exception e) {
            System.out.println("Exception occured during saving high score to database: "
                    + e.getMessage());
        }
    }
    
    public Jadro getInstance(){
        return instance;
    }
}
