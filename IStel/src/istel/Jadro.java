package istel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import istel.jadro.Uzivatel;
import istel.kontakt.Adresa;
import istel.kontakt.Cislo;
import istel.kontakt.Kontakt;
import istel.kontakt.Osoba;
import java.sql.*;
import java.util.ArrayList;
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
            System.out.println("Nepodarilo sa pirpojit k db");
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
            try {
                
                int id_obec = this.vyberObecId(kontakt);
                if(id_obec == -1) {
                    // V databaze nie je obec ideme pridat novu obec
                    PreparedStatement pstmObec = this.getConnection().prepareStatement(DatabaseSetting.QUERY_ADD_INTO_OBEC);
                    pstmObec.setString(1, kontakt.getAdresa().getObec());
                    pstmObec.setString(2, kontakt.getAdresa().getPsc());
                    pstmObec.execute();
                    pstmObec.close();
                    id_obec = this.vyberObecId(kontakt);
                }
                kontakt.getAdresa().setId_obec(id_obec);
                
                System.out.println(kontakt.getAdresa().getId_obec());
                
                
                int id_adresa = this.vyberAdresaId(kontakt);
                if(id_adresa == -1) {
                    // V databaze nie je obec ideme pridat novu adresa
                    PreparedStatement pstmAdresa = this.getConnection().prepareStatement(DatabaseSetting.QUERY_ADD_INTO_ADRESA);
                    pstmAdresa.setInt(1, kontakt.getAdresa().getId_obec());
                    pstmAdresa.setString(2, kontakt.getAdresa().getUlica());
                    pstmAdresa.setString(3, kontakt.getAdresa().getCisloDomu());
                    pstmAdresa.execute();
                    pstmAdresa.close();
                    id_adresa = this.vyberAdresaId(kontakt);
                }
                kontakt.getAdresa().setId_adresa(id_adresa);
                
                
                int id_osoba = this.vyberOsobaId(kontakt);
                if(id_osoba == -1) {
                    // V databaze nie je osoba
                    PreparedStatement pstmOsoba = this.getConnection().prepareStatement(DatabaseSetting.QUERY_ADD_INTO_OSOBA);
                    pstmOsoba.setInt(1, kontakt.getAdresa().getId_adresa());
                    pstmOsoba.setString(2, kontakt.getOsoba().getMeno());
                    pstmOsoba.setString(3, kontakt.getOsoba().getPriezvisko());
                    pstmOsoba.execute();
                    pstmOsoba.close();
                    id_osoba = this.vyberOsobaId(kontakt);
                }
                kontakt.getOsoba().setId_osoba(id_osoba);
                
                
                // Vlozenie tel cisla do db;
                PreparedStatement pstmCislo = this.getConnection().prepareStatement(DatabaseSetting.QUERY_ADD_INTO_CISLO);
                for(int index = 0; index < kontakt.getCisla().size(); index++) {
                    pstmCislo.setInt(1, kontakt.getOsoba().getId_osoba());
                    pstmCislo.setString(2, kontakt.getCisla().get(index).getCislo());
                    pstmCislo.execute();
                }
                pstmCislo.close();
                
                
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return false;
            }


        } else {
            System.out.println("Nie si obsluha!");
        }
        return false;
    }

    public ArrayList<Kontakt> vyhladajKontakt(Kontakt kontakt) {
        Kontakt kontaktDoArray = null;
        ArrayList<Kontakt> odosliKontakty = new ArrayList<Kontakt>();
        
        poslednaAktivita();
        try {
            PreparedStatement statement = this.getConnection().prepareStatement(DatabaseSetting.QUERY_SELECT_VYHLADAJ);

            statement.setString(1, kontakt.getOsoba().getMeno());
            statement.setString(2, kontakt.getOsoba().getPriezvisko());
            statement.setString(3, kontakt.getAdresa().getObec());
            statement.setString(4, kontakt.getAdresa().getPsc());

            ResultSet result = statement.executeQuery();
            while(result.next()){
                Osoba osoba = new Osoba(result.getString("meno"),result.getString("priezvisko"));
                Adresa adresa = new Adresa(result.getString("obec"), result.getString("psc"), result.getString("ulica"), result.getString("cislo_domu"));
                Cislo cislo = new Cislo(result.getString("tel_cislo"));
                odosliKontakty.add(new Kontakt(osoba, adresa, cislo));
            }
            //return result;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

        //return null;
        poslednaAktivita();
        if (getUzivatel().jeObsluha()) {
        } else {
            System.out.println("Nie si obsluha!");
        }
        return odosliKontakty;
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

    private int vyberObecId(Kontakt kontakt) throws SQLException {
        int id_obec = -1;
        PreparedStatement pstmObec = this.getConnection().prepareStatement(DatabaseSetting.QUERY_SELECT_ID_OBEC);
        pstmObec.setString(1, kontakt.getAdresa().getObec());
        pstmObec.setString(2, String.valueOf(kontakt.getAdresa().getPsc()));
        ResultSet resultObecId = pstmObec.executeQuery();
               
        resultObecId.last();
        int rowCount = resultObecId.getRow();
        if (rowCount != 0) {
            id_obec = resultObecId.getInt("id_obec");

        }
        return id_obec;
    }

    private int vyberAdresaId(Kontakt kontakt) throws SQLException {
        int id_adresa = -1;
        PreparedStatement pstmAdresa = this.getConnection().prepareStatement(DatabaseSetting.QUERY_SELECT_ID_ADRESA);
        pstmAdresa.setInt(1, kontakt.getAdresa().getId_obec());
        pstmAdresa.setString(2, kontakt.getAdresa().getUlica());
        pstmAdresa.setString(3, kontakt.getAdresa().getCisloDomu());
        ResultSet resultAdresaId = pstmAdresa.executeQuery();

        resultAdresaId.last();
        int rowCount = resultAdresaId.getRow();
        if (rowCount != 0) {
            id_adresa = resultAdresaId.getInt("id_adresa");

        }
        return id_adresa;
    }

    private int vyberOsobaId(Kontakt kontakt) throws SQLException {
        int id_osoba = -1;
        PreparedStatement pstmOsoba = this.getConnection().prepareStatement(DatabaseSetting.QUERY_SELECT_ID_OSOBA);
        pstmOsoba.setInt(1, kontakt.getAdresa().getId_adresa());
        pstmOsoba.setString(2, kontakt.getOsoba().getMeno());
        pstmOsoba.setString(3, kontakt.getOsoba().getPriezvisko());
        ResultSet resultOsobaId = pstmOsoba.executeQuery();

        resultOsobaId.last();
        int rowCount = resultOsobaId.getRow();
        if (rowCount != 0) {
            id_osoba = resultOsobaId.getInt("id_osoba");

        }
        return id_osoba;
    }
}
