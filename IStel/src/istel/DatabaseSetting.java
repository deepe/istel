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

    /** Create table query */
    public static final String QUERY_CREATE_BEST_TIMES = "CREATE TABLE player_time (name VARCHAR(128) NOT NULL, best_time TIME NOT NULL, field_size VARCHAR(128))";
    /** Insert osobu into osoba */
    
    public static final String QUERY_ADD_BEST_TIME = "INSERT INTO player_time (name, best_time, field_size) VALUES (?, ?, ?)";
    /** Select best time query */
    public static final String QUERY_SELECT_BEST_TIMES = "SELECT name, best_time, field_size FROM player_time";
    
    
    //---------------------------------
    public static final String QUERY_ADD_INTO_OBEC = "INSERT INTO obec (obec, psc) VALUES (?, ?)";
    public static final String QUERY_ADD_INTO_ADRESA = "INSERT INTO adresa (id_obec, ulica, cislo_domu) VALUES (LAST_INSERT_ID(), ?, ?)";
    public static final String QUERY_ADD_INTO_OSOBA = "INSERT INTO osoba (id_adresa, meno, priezvisko) VALUES (LAST_INSERT_ID(), ?, ?)";
    public static final String QUERY_ADD_INTO_CISLO = "INSERT INTO cislo (id_osoba, tel_cislo) VALUES (LAST_INSERT_ID(), ?)";
    
    
    
    
    
    
    
    //================================

    private DatabaseSetting() {}
}