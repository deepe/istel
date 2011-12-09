/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package istel.kontakt;

/**
 *
 * @author t0ki
 */
public class Osoba {
    
    private String meno;
    private String priezvisko;
    private int id_osoba;
    
    public Osoba(String meno, String priezvisko, int id_osoba) {
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.id_osoba = id_osoba;
    }
    
    public Osoba(String meno, String priezvisko) {
        this.meno = meno;
        this.priezvisko = priezvisko;
    }

    /**
     * @return the id_osoba
     */
    public int getId_osoba() {
        return id_osoba;
    }

    /**
     * @return the meno
     */
    public String getMeno() {
        return meno;
    }

    /**
     * @return the priezvisko
     */
    public String getPriezvisko() {
        return priezvisko;
    }

    /**
     * @param meno the meno to set
     */
    public void setMeno(String meno) {
        this.meno = meno;
    }

    /**
     * @param priezvisko the priezvisko to set
     */
    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }
    
}
