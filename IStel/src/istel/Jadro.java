package istel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import istel.DatabaseSetting;
import istel.jadro.Uzivatel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author erik
 */
public class Jadro {

    private static Jadro instance;
    private Uzivatel uzivatel;
    private final long CAS_ODHLASENIA = 300000L; //v milisekundach
    private long poslednaAktivita;

    //private Uzivatel uzivatel;
    public Jadro() {
        instance = this;
        uzivatel = new Uzivatel(); //bezparametricky kon. je anonym
        poslednaAktivita();
    }

    public void pridajKontakt(String meno, String priezvisko, String ulica, String cisloDomu, String obec, String psc, String telefon) {
        poslednaAktivita();
        if (getUzivatel().jeObsluha()) {

            try {
                Class.forName(DatabaseSetting.DRIVER_CLASS);
                Connection connection = DriverManager.getConnection(DatabaseSetting.URL,
                        DatabaseSetting.USER, DatabaseSetting.PASSWORD);

                
                PreparedStatement pstm = connection.prepareStatement(DatabaseSetting.QUERY_ADD_INTO_OBEC);
                pstm.setString(1, obec);
                pstm.setString(2, psc);
                pstm.execute();
                pstm.close();

                PreparedStatement pstm2 = connection.prepareStatement(DatabaseSetting.QUERY_ADD_INTO_ADRESA);
                pstm2.setString(1, ulica);
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

                connection.close();
            } catch (Exception e) {
                System.out.println("Nepodarilo sa vlozit novy kontakt do databazy "
                        + e.getMessage());
            }
        }
    }
    
    public ResultSet vyhladajKontakt(){
        try {
            Class.forName(DatabaseSetting.DRIVER_CLASS);
                    Connection connection = DriverManager.getConnection(DatabaseSetting.URL,
                            DatabaseSetting.USER, DatabaseSetting.PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(DatabaseSetting.QUERY_SELECT_VYHLADAJ);
        statement.close();
        connection.close();        
        
        return result;
        } catch (SQLException ex) {
            Logger.getLogger(Jadro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Jadro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
   

    public void odhlasit() {
        this.uzivatel = new Uzivatel();
    }
    
    private void poslednaAktivita() {
        this.poslednaAktivita = System.currentTimeMillis();
    }
    
    public void skontrolujAktivitu() {
        if(System.currentTimeMillis() - poslednaAktivita > CAS_ODHLASENIA)  {
            odhlasit();
        }
    }
    
    public Jadro getInstance() {
        return instance;
    }

    /**
     * @return the uzivatel
     */
    public Uzivatel getUzivatel() {
        return uzivatel;
    }
}
