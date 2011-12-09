/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package istel.kontakt;

import istel.kontakt.Cislo;
import istel.kontakt.Adresa;
import java.util.ArrayList;

/**
 *
 * @author t0ki
 */
public class Kontakt {

    private Osoba osoba;
    private Adresa adresa;
    private ArrayList<Cislo> cisla;

    public Kontakt(Osoba osoba, Adresa adresa, Cislo cislo) {
        this.osoba = osoba;
        this.adresa = adresa;
        this.cisla = new ArrayList<Cislo>();
        this.cisla.add(cislo);
    }

    /**
     * @return the adresa
     */
    public Adresa getAdresa() {
        return adresa;
    }

    /**
     * @return the cisla
     */
    public ArrayList<Cislo> getCisla() {
        return cisla;
    }

    /**
     * @return the osoba
     */
    public Osoba getOsoba() {
        return osoba;
    }
    
    public void addCislo(Cislo cislo) {
        this.cisla.add(cislo);
    }
    
    public boolean removeCislo(Cislo cislo) {
        
        for(int index = 0; cisla.size() > index; index++) {
            if(cisla.equals(cislo)) {
                cisla.remove(index);
                return true;
            }
        }
        return false;
    }
}
