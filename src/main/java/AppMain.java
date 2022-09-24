import common.DBConstants;
import database.DBConnectionHandler;
import database.CSV_Handler;
import model.Stromlastdaten;

import java.sql.SQLException;
import java.util.List;

public class AppMain {

    public static void main(String[] args) {
        // Establish Database connection
        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler();
        dbConnectionHandler.connect();

        // Bootstrap Database Scheme
        System.out.println("Bootstrapping database");
        dbConnectionHandler.runSQLScript(DBConstants.SQL_FILE_PATH);
        // Get Datasets from CSV-File
        CSV_Handler csv_handler = new CSV_Handler();
        List<Stromlastdaten> stromlastdatenList = csv_handler.readStromlastdatenFormCSV(DBConstants.CSV_FILE_PATH);

        // Create Insert Statements from Datasets and load into database
        try {
            dbConnectionHandler.importDataToDatabase(stromlastdatenList);
            System.out.println("Committing changes");
            dbConnectionHandler.commit();
            System.out.println("Programm endet Successfully - Closing DB Connection gracefully");
            dbConnectionHandler.closeConnection();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        // happy hacking
    }
}
