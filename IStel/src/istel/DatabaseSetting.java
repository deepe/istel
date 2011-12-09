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
    
    //---------------------------------
    public static final String QUERY_SELECT_VYHLADAJ = "SELECT meno,priezvisko,tel_cislo,ulica,cislo_domu,obec,psc FROM v_vyhladaj WHERE meno LIKE '?%' AND priezvisko LIKE '%?% AND obec LIKE '?%' AND psc = ?";
       
    public static final String QUERY_SELECT_PRIVILEGIES = "SELECT prava FROM uzivatelia WHERE meno = ? AND heslo = ?";
    public static final String QUERY_ADD_INTO_OBEC = "INSERT INTO obec (obec, psc) VALUES (?, ?)";
    public static final String QUERY_ADD_INTO_ADRESA = "INSERT INTO adresa (id_obec, ulica, cislo_domu) VALUES (?, ?, ?)";
    public static final String QUERY_ADD_INTO_OSOBA = "INSERT INTO osoba (id_adresa, meno, priezvisko) VALUES (?, ?, ?)";
    public static final String QUERY_ADD_INTO_CISLO = "INSERT INTO cislo (id_osoba, tel_cislo) VALUES (?, ?)";
    
    public static final String QUERY_SELECT_ID_OBEC= "SELECT id_obec FROM obec WHERE obec = ? AND psc = ?";
    public static final String QUERY_SELECT_ID_ADRESA= "SELECT id_adresa FROM adresa WHERE id_obec = ? AND ulica = ? AND cislo_domu = ?";
    public static final String QUERY_SELECT_ID_OSOBA= "SELECT id_osoba FROM osoba WHERE id_adresa = ? AND meno = ? AND priezvisko = ?";
    
    public final static String QUERY_DELETE_FROM_OBEC ="DELETE * FROM obec WHERE id_obec = ?";
    public final static String QUERY_DELETE_FROM_ADRESA ="DELETE * FROM adresa WHERE id_adresa = ?";
    public final static String QUERY_DELETE_FROM_OSOBA ="DELETE * FROM osoba WHERE id_osoba = ?";
    public final static String QUERY_DELETE_FROM_CISLO ="DELETE * FROM cislo WHERE id_cislo = ?";
    
    public final static String QUERY_SELECT_FOR_DELETE_ID_OBEC = "SELECT id_obec FROM adresa WHERE id_adresa = ?";
    public final static String QUERY_SELECT_FOR_DELETE_ID_ADRESA = "SELECT id_adresa FROM osoba WHERE id_osoba = ?";
    public final static String QUERY_SELECT_FOR_DELETE_ID_OSOBA = "SELECT id_osoba FROM osoba WHERE meno = ? AND priezvisko = ?";
    public final static String QUERY_SELECT_FOR_DELETE_ID_CISLO = "SELECT id_cislo FROM cislo WHERE id_osoba = ?";
    
    private DatabaseSetting() {}
}
