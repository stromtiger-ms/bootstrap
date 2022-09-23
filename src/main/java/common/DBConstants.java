package common;

public final class DBConstants {
    // Connection Variables
    public final static String DB_URL = System.getenv("DB_URL");
    public final static String DB_USER = System.getenv("DB_USERNAME");
    public final static String DB_PASSWORD = System.getenv("DB_PASSWORD");

    // Table names
    private static final String TABLE_NAME_STROMLASTDATEN = "testtabelle";
    // Statements
    public static final String INSERT_STROMLASTDATUM = "INSERT INTO " + TABLE_NAME_STROMLASTDATEN + " (zeit, kw, status, kundeId) VALUES (?, ?, ?, ?)";

    // File locations
    public static final String CSV_FILE_PATH = "src/main/resources/import.csv";
    public static final String SQL_FILE_PATH = "src/main/resources/bootstrap.sql";

    private DBConstants() {
        // private constructor to prevent instantiation
    }
}
