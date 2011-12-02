package istel;
/**
 * Database settings, select, insert and create table
 * @author t0ki
 */
public class DatabaseSetting {
    /** Database driver **/
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    /** Url adress to database */
    public static final String URL = "jdbc:mysql://147.232.179.140:3306/telzoznam";
    /** User name to database */
    public static final String USER = "teluser";
    /** Password to database */
    public static final String PASSWORD = "kongo";

    public static final String QUERY_SELECT_VYHLADAJ = "SELECT meno,priezvisko,tel_cislo,ulica,cislo_domu,obec,psc from v_vyhladaj WHERE meno like ? OR priezvisko like ? OR obec like ? OR psc like ?";
    public static final String QUERY_SELECT_VYHLADAJ_TEST = "SELECT priezvisko,meno,tel_cislo,ulica,cislo_domu,obec,psc from v_vyhladaj";
    
    
    //---------------------------------
    public static final String QUERY_SELECT_PRIVILEGIES = "SELECT prava FROM uzivatelia WHERE meno = ? AND heslo = ?";
    public static final String QUERY_ADD_INTO_OBEC = "INSERT INTO obec (obec, psc) VALUES (?, ?)";
    public static final String QUERY_ADD_INTO_ADRESA = "INSERT INTO adresa (id_obec, ulica, cislo_domu) VALUES (LAST_INSERT_ID(), ?, ?)";
    public static final String QUERY_ADD_INTO_OSOBA = "INSERT INTO osoba (id_adresa, meno, priezvisko) VALUES (LAST_INSERT_ID(), ?, ?)";
    public static final String QUERY_ADD_INTO_CISLO = "INSERT INTO cislo (id_osoba, tel_cislo) VALUES (LAST_INSERT_ID(), ?)";
    
    
    
    
    
    
    
    //================================

    private DatabaseSetting() {}
}