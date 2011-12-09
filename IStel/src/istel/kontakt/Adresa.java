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
    private String psc;
    private int id_obec;
    
    private String ulica;
    private String cisloDomu;
    private int id_adresa;
    
    public Adresa(String obec, String psc, String ulica, String cisloDommu) {
        this.obec = obec;
        this.psc = psc;
        this.ulica = ulica;
        this.cisloDomu = cisloDommu;
    }
    
    public Adresa(int id_obec, String obec, String psc, int id_ulica, String ulica, String cisloDommu) {
        this.id_obec = id_obec;
        this.id_adresa = id_ulica;
        this.obec = obec;
        this.psc = psc;
        this.ulica = ulica;
        this.cisloDomu = cisloDommu;
    }

    /**
     * @return the cisloDomu
     */
    public String getCisloDomu() {
        return cisloDomu;
    }

    /**
     * @return the id_adresa
     */
    public int getId_adresa() {
        return id_adresa;
    }

    /**
     * @return the id_obec
     */
    public int getId_obec() {
        return id_obec;
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
    public String getPsc() {
        return psc;
    }

    /**
     * @return the ulica
     */
    public String getUlica() {
        return ulica;
    }

    /**
     * @param id_adresa the id_adresa to set
     */
    public void setId_adresa(int id_adresa) {
        this.id_adresa = id_adresa;
    }

    /**
     * @param id_obec the id_obec to set
     */
    public void setId_obec(int id_obec) {
        this.id_obec = id_obec;
    }

    /**
     * @param obec the obec to set
     */
    public void setObec(String obec) {
        this.obec = obec;
    }

    /**
     * @param psc the psc to set
     */
    public void setPsc(String psc) {
        this.psc = psc;
    }

    /**
     * @param ulica the ulica to set
     */
    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    /**
     * @param cisloDomu the cisloDomu to set
     */
    public void setCisloDomu(String cisloDomu) {
        this.cisloDomu = cisloDomu;
    }


    
}
