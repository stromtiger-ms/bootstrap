package common;

import model.Stromlastdaten;

import java.sql.*;
import java.util.List;

public class DBConnectionHandler {

    private Connection connection;

    public DBConnectionHandler(){}

    public void connect() {
        System.out.println("Stelle Verbindung zur Datenbank her");
        String dbUrl = DBConstants.DB_URL;
        String dbUser = DBConstants.DB_USER;
        String dbPassword = DBConstants.DB_PASSWORD;
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            System.out.println("Verbindung hergestellt");
        } catch (SQLException e) {
            System.out.println("Verbindung abgebrochen");
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void importDataToDatabase(List<Stromlastdaten> stromlastdatenList) throws SQLException {
        Integer numberOfDatasets = stromlastdatenList.size();
        Integer currentDataSet = 0;
        PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO testtabelle (zeit, kw, status, kundeId) VALUES (?, ?, ?, ?) ");
        for (Stromlastdaten stromlastdaten : stromlastdatenList) {
            insertStatement.clearParameters();
            System.out.println("Lade Datensatz: " + currentDataSet + " / " + numberOfDatasets);
            currentDataSet++;
            if (! (stromlastdaten.getZeit() == null)) {
                insertStatement.setString(1, stromlastdaten.getZeit());
                insertStatement.setDouble(2, stromlastdaten.getKw());
                insertStatement.setString(3, stromlastdaten.getStatus());
                insertStatement.setInt(4, stromlastdaten.getKundeId());
                insertStatement.addBatch();
            }
        }
        insertStatement.executeBatch();
    }
}
