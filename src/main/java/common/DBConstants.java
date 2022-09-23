package common;

public final class DBConstants {
    // Connection Variables
    public final static String DB_URL = System.getenv("DB_URL");
    public final static String DB_USER = System.getenv("DB_USERNAME");
    public final static String DB_PASSWORD = System.getenv("DB_PASSWORD");

    // Table names
    private static final String TABLE_NAME_STROMLASTDATEN = "testtabelle";

    private static final String TABLE_NAME_VERBRAUCHE = "verbraucher";

    // Statements
    public static final String INSERT_STROMLASTDATUM = "INSERT INTO " + TABLE_NAME_STROMLASTDATEN + " (zeit, kw, status, kundeId) VALUES (?, ?, ?, ?)";

    private DBConstants() {
        // private constructor to prevent instantiation
    }
}
