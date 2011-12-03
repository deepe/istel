package istel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
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
    
    private Connection getConnection() {
        poslednaAktivita();
        return this.pripojenieDb;
    }

    public void pridajKontakt(String meno, String priezvisko, String ulica, String cisloDomu, String obec, String psc, String telefon) throws SQLException, ClassNotFoundException {
        poslednaAktivita();
        if (getUzivatel().jeObsluha()) {

            String id_obec = null;
            String id_adresa = null;
            String id_osoba = null;

            try {
                PreparedStatement pstm = this.getConnection().prepareStatement(DatabaseSetting.QUERY_ADD_INTO_OBEC);
                pstm.setString(1, obec);
                pstm.setString(2, psc);
                pstm.execute();
                pstm.close();
            } catch (Exception e) {
                // existuje uz obec v db ideme vybrat id
                // a ulozit ho do premeny id_obec
                PreparedStatement statement = this.getConnection().prepareStatement(DatabaseSetting.QUERY_SELECT_ID_OBEC);
                statement.setString(1, obec);
                statement.setString(2, psc);
                ResultSet result = statement.executeQuery();
                id_obec = result.getString("id_obec");
                System.out.println(id_obec);
            }

            try {
                PreparedStatement pstm2;
                if (id_obec == null) {
                    pstm2 = this.getConnection().prepareStatement(DatabaseSetting.QUERY_ADD_INTO_ADRESA);
                } else {
                    // obec je db vkladame novy 
                    pstm2 = this.getConnection().prepareStatement(DatabaseSetting.QUERY_ADD_INTO_ADRESA_WITH_ID_OBEC);
                    pstm2.setString(3, id_obec);
                }
                pstm2.setString(1, ulica);
                int cislo = Integer.parseInt(cisloDomu);
                pstm2.setInt(2, cislo);
                pstm2.execute();
                pstm2.close();
            } catch (Exception e) {
                // existuje uz adresa v db vyberame id_adresa
                PreparedStatement statement = this.getConnection().prepareStatement(DatabaseSetting.QUERY_SELECT_ID_ADRESA);
                statement.setString(1, id_obec);
                statement.setString(2, ulica);
                statement.setString(3, cisloDomu);
                ResultSet result = statement.executeQuery();
                id_adresa = result.getString(1);
            }

            try {
                PreparedStatement pstm3;
                if (id_adresa == null) {
                    pstm3 = this.getConnection().prepareStatement(DatabaseSetting.QUERY_ADD_INTO_OSOBA);
                } else {
                    pstm3 = this.getConnection().prepareStatement(DatabaseSetting.QUERY_ADD_INTO_OSOBA_WITH_ID_ADRESA);
                    pstm3.setString(3, id_adresa);
                }
                pstm3.setString(1, meno);
                pstm3.setString(2, priezvisko);
                pstm3.execute();
                pstm3.close();
            } catch (Exception e) {
                // existuje uz osoba v db vyberame id do premeny 
                PreparedStatement statement = this.getConnection().prepareStatement(DatabaseSetting.QUERY_SELECT_ID_OSOBA);
                statement.setString(1, id_adresa);
                statement.setString(2, ulica);
                statement.setString(3, cisloDomu);
                ResultSet result = statement.executeQuery();
                id_osoba = result.getString(1);
            }

            try {
                PreparedStatement pstm4;
                if (id_osoba == null) {
                    pstm4 = this.getConnection().prepareStatement(DatabaseSetting.QUERY_ADD_INTO_CISLO);
                } else {
                    pstm4 = this.getConnection().prepareStatement(DatabaseSetting.QUERY_ADD_INTO_CISLO_WITH_ID_OSOBA);
                    pstm4.setString(2, id_osoba);
                }
                pstm4.setString(1, telefon);
                pstm4.execute();
                pstm4.close();

            } catch (Exception e) {
                System.out.println("Uzivatel s tymto tel. cislom existuje v db");

            }

        } else {
            System.out.println("Nie si obsluha!");
        }
    }

    public ResultSet vyhladajKontakt(String meno, String priezvisko, String obec, String psc) {
        poslednaAktivita();
        try {
            PreparedStatement statement = this.getConnection().prepareStatement(DatabaseSetting.QUERY_SELECT_VYHLADAJ);

            statement.setString(1, meno);
            statement.setString(2, priezvisko);
            statement.setString(3, obec);
            statement.setString(4, psc);

            ResultSet result = statement.executeQuery();
            return result;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


        return null;
    }

    public void zmazKontakt(String meno, String priezvisko, String telefon) {
        poslednaAktivita();
        try {

            String delete_cislo = "delete * from cislo where tel_cislo LIKE '" + telefon + "'";
            String deleteUser = "delete * from osoba where meno like " + meno + "and priezvisko like " + priezvisko;
            Statement pstm = this.getConnection().createStatement();
            pstm.executeUpdate(delete_cislo);
            pstm.execute(deleteUser);
            pstm.close();

            pripojenieDb.close();
        } catch (Exception e) {
            System.out.println("Nepodarilo sa vymazat kontakt "
                    + e.getMessage());
        }

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
