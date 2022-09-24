package database;

import common.DBConstants;
import model.Stromlastdaten;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.List;

public class DBConnectionHandler {

    private static Connection connection;

    public DBConnectionHandler(){}

    public void connect() {
        System.out.println("Connecting to Database");
        String dbUrl = DBConstants.DB_URL;
        String dbUser = DBConstants.DB_USER;
        String dbPassword = DBConstants.DB_PASSWORD;
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            System.out.println("Connection established...");
        } catch (SQLException e) {
            System.out.println("Connection refused / aborted");
            throw new RuntimeException(e);
        }
    }

    public void createDatabaseScheme() {

    }

    public boolean checkTableExists(String tableName) throws SQLException {
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet tables = databaseMetaData.getTables(null, null, tableName, null);
        return tables.next();
    }

    public void importDataToDatabase(List<Stromlastdaten> stromlastdatenList) throws SQLException {
        int numberOfDatasets = stromlastdatenList.size();
        int currentDataSet = 0;
        int lastPercentage = -1;

        PreparedStatement insertStromlastDatumBatch = connection.prepareStatement(DBConstants.INSERT_STROMLASTDATUM);

        for (Stromlastdaten stromlastdaten : stromlastdatenList) {
            insertStromlastDatumBatch.clearParameters();
            int percentage = (int) ((double) currentDataSet/numberOfDatasets * 100);

            // Print percentage of processed rows
            if (percentage > lastPercentage) {
                lastPercentage = percentage;
                System.out.println("Progress: " + percentage + "%");
            }

            currentDataSet++;
            if (! (stromlastdaten.getZeit() == null)) {
                insertStromlastDatumBatch.setString(1, stromlastdaten.getZeit());
                insertStromlastDatumBatch.setDouble(2, stromlastdaten.getKw());
                insertStromlastDatumBatch.setString(3, stromlastdaten.getStatus());
                insertStromlastDatumBatch.setInt(4, stromlastdaten.getKundeId());
                insertStromlastDatumBatch.setBoolean(5, false);
                insertStromlastDatumBatch.addBatch();
            }
        }

        System.out.println("Sending results to Database...");
        insertStromlastDatumBatch.executeBatch();
    }

    public void runSQLScript(String scriptPath) {
        ScriptRunner scriptRunner = new ScriptRunner(connection);
        try {
            Reader fileReader = new BufferedReader(new FileReader(scriptPath));
            scriptRunner.setLogWriter(null);
            scriptRunner.runScript(fileReader);

        } catch (IOException ioException) {
            System.out.println(":(");
            ioException.printStackTrace();
        }
    }
}
