/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package istel.kontakt;

/**
 *
 * @author t0ki
 */
public class Adresa {
    
    private String obec;
    private int psc;
    private int id_obec;
    
    private String ulica;
    private int cisloDomu;
    private int id_ulica;
    
    public Adresa(String obec, int psc, String ulica, int cisloDommu) {
        this.obec = obec;
        this.psc = psc;
        this.ulica = ulica;
        this.cisloDomu = cisloDommu;
    }
    
    public Adresa(int id_obec, String obec, int psc, int id_ulica, String ulica, int cisloDommu) {
        this.id_obec = id_obec;
        this.id_ulica = id_ulica;
        this.obec = obec;
        this.psc = psc;
        this.ulica = ulica;
        this.cisloDomu = cisloDommu;
    }

    /**
     * @return the cisloDomu
     */
    public int getCisloDomu() {
        return cisloDomu;
    }

    /**
     * @return the id_obec
     */
    public int getId_obec() {
        return id_obec;
    }

    /**
     * @return the id_ulica
     */
    public int getId_ulica() {
        return id_ulica;
    }

    /**
     * @return the obec
     */
    public String getObec() {
        return obec;
    }

    /**
     * @return the psc
     */
    public int getPsc() {
        return psc;
    }

    /**
     * @return the ulica
     */
    public String getUlica() {
        return ulica;
    }
    
}
