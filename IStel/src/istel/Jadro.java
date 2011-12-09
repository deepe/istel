package istel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import istel.jadro.Uzivatel;
import istel.kontakt.Kontakt;
import java.util.ArrayList;

/**
 *
 * @author erik
 */
public class Jadro {

    private static Jadro instance;
    private Uzivatel uzivatel;
    private final long CAS_ODHLASENIA = 300000l; //v milisekundach
    private long poslednaAktivita;
    private Connection pripojenieDb = null;

    //private Uzivatel uzivatel;
    public Jadro() {
        instance = this;
        uzivatel = new Uzivatel(); //bezparametricky kon. je anonym
        pripojDb();
        poslednaAktivita();
    }

    private void pripojDb() {
        try {
            Class.forName(DatabaseSetting.DRIVER_CLASS);
            this.pripojenieDb = DriverManager.getConnection(DatabaseSetting.URL,
                DatabaseSetting.USER, DatabaseSetting.PASSWORD);
        } catch (SQLException ex) {
            System.out.println("Nepodarilo sa pripojit k db");
        } catch (ClassNotFoundException ex) {
            System.out.println("Nepodarilo sa pirpojit k db");
        }
        
    }
    
    private void odpojdDB() {
        try {
            this.pripojenieDb.close();
        } catch (SQLException ex) {
            System.out.println("Nepodarilo sa odpojit do db.");
        }
    }
    
    private Connection getConnection() {
        poslednaAktivita();
        return this.pripojenieDb;
    }

    public boolean pridajKontakt(Kontakt kontakt) {
        poslednaAktivita();
        if (getUzivatel().jeObsluha()) {

        } else {
            System.out.println("Nie si obsluha!");
        }
        return false;
    }

    public ArrayList<Kontakt> vyhladajKontakt(Kontakt kontakt) {
//        poslednaAktivita();
//        try {
//            PreparedStatement statement = this.getConnection().prepareStatement(DatabaseSetting.QUERY_SELECT_VYHLADAJ);
//
//            statement.setString(1, meno);
//            statement.setString(2, priezvisko);
//            statement.setString(3, obec);
//            statement.setString(4, psc);
//
//            ResultSet result = statement.executeQuery();
//            return result;
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//
//        return null;
        poslednaAktivita();
        if (getUzivatel().jeObsluha()) {

        } else {
            System.out.println("Nie si obsluha!");
        }
        return null;
    }

    public boolean zmazKontakt(Kontakt kontakt) {
        poslednaAktivita();

            //Dorobit
            
//            String delete_cislo = "delete * from cislo where tel_cislo LIKE '" + telefon + "'";
//            String deleteUser = "delete * from osoba where meno like " + meno + "and priezvisko like " + priezvisko;
//            Statement pstm = this.getConnection().createStatement();
//            pstm.executeUpdate(delete_cislo);
//            pstm.execute(deleteUser);
//            pstm.close();
        if (getUzivatel().jeObsluha()) {

        } else {
            System.out.println("Nie si obsluha!");
        }
        return false;
    }

    public boolean prihslasitSa(String meno, String heslo) {
        poslednaAktivita();
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
        if (System.currentTimeMillis() - poslednaAktivita > CAS_ODHLASENIA && !this.uzivatel.jeAnonym()) {
            System.out.println("Bol si automaticky odhlaseny ;)");
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
