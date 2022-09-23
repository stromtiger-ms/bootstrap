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

        // Get Datasets from CSV-File
        CSV_Handler csv_handler = new CSV_Handler();
        List<Stromlastdaten> stromlastdatenList = csv_handler.readStromlastdatenFormCSV("src/main/resources/import.csv");

        // Create Insert Statements from Datasets and load into database
        try {
            dbConnectionHandler.importDataToDatabase(stromlastdatenList);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        // happy hacking
    }
}
