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
    
    
    private Connection getConnection() throws ClassNotFoundException, SQLException {
            Class.forName(DatabaseSetting.DRIVER_CLASS);
            Connection connection = DriverManager.getConnection(DatabaseSetting.URL,
                        DatabaseSetting.USER, DatabaseSetting.PASSWORD);
            return connection; 
    }

    public void pridajKontakt(String meno, String priezvisko, String ulica, String cisloDomu, String obec, String psc, String telefon) {
        poslednaAktivita();
        if (getUzivatel().jeObsluha()) {

            try {
                PreparedStatement pstm = this.getConnection().prepareStatement(DatabaseSetting.QUERY_ADD_INTO_OBEC);
                pstm.setString(1, obec);
                pstm.setString(2, psc);
                pstm.execute();
                pstm.close();
            } catch (Exception e) {
                // existuje uz obec v db mozeme ignorovat
            }

            try {
                PreparedStatement 
                pstm2 = this.getConnection().prepareStatement(DatabaseSetting.QUERY_ADD_INTO_ADRESA);
                pstm2.setString(1, ulica);
                int cislo = Integer.parseInt(cisloDomu);
                pstm2.setInt(2, cislo);
                pstm2.execute();
                pstm2.close();
            } catch (Exception e) {
                // existuje uz adresa v db mozeme ignorovat
            }
            
            try {
                PreparedStatement pstm3 = this.getConnection().prepareStatement(DatabaseSetting.QUERY_ADD_INTO_OSOBA);
                pstm3.setString(1, meno);
                pstm3.setString(2, priezvisko);
                pstm3.execute();
                pstm3.close();
            } catch (Exception e) {
                // existuje uz osoba v db mozeme ignorovat
            }
            
            try {
                PreparedStatement pstm4 = this.getConnection().prepareStatement(DatabaseSetting.QUERY_ADD_INTO_CISLO);
                pstm4.setString(1, telefon);
                pstm4.execute();
                pstm4.close();

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            
        } else {
            System.out.println("Nie si obsluha!");
        }
    }

    public ResultSet vyhladajKontakt(String meno, String priezvisko, String obec, String psc) {
        try {

            Class.forName(DatabaseSetting.DRIVER_CLASS);
            Connection connection = DriverManager.getConnection(DatabaseSetting.URL,
                    DatabaseSetting.USER, DatabaseSetting.PASSWORD);
            PreparedStatement statement = connection.prepareStatement(DatabaseSetting.QUERY_SELECT_VYHLADAJ);

            statement.setString(1, meno);
            statement.setString(2, priezvisko);
            statement.setString(3, obec);
            statement.setString(4, psc);



            ResultSet result = statement.executeQuery();


            return result;
        } catch (SQLException ex) {
            Logger.getLogger(Jadro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Jadro.class.getName()).log(Level.SEVERE, null, ex);
        }


        return null;
    }

    public void zmazKontakt(String meno, String priezvisko, String telefon) {

        try {
            Class.forName(DatabaseSetting.DRIVER_CLASS);
            Connection connection = DriverManager.getConnection(DatabaseSetting.URL,
                    DatabaseSetting.USER, DatabaseSetting.PASSWORD);

            String delete_cislo = "delete * from cislo where tel_cislo LIKE '" + telefon + "'";
            String deleteUser = "delete * from osoba where meno like " + meno + "and priezvisko like " + priezvisko;
            Statement pstm = connection.createStatement();
            pstm.executeUpdate(delete_cislo);
            pstm.execute(deleteUser);
            pstm.close();

            connection.close();
        } catch (Exception e) {
            System.out.println("Nepodarilo sa vymazat kontakt "
                    + e.getMessage());
        }

    }

    public boolean prihslasitSa(String meno, String heslo) {
        this.uzivatel = new Uzivatel(meno, heslo);
        if (uzivatel.jeObsluha() || uzivatel.jeAdministrator()) {
            return true;
        }
        return false;
    }

    public void odhlasit() {
        this.uzivatel = new Uzivatel();
    }

    private void poslednaAktivita() {
        this.poslednaAktivita = System.currentTimeMillis();
    }

    public void skontrolujAktivitu() {
        if (System.currentTimeMillis() - poslednaAktivita > CAS_ODHLASENIA) {
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
