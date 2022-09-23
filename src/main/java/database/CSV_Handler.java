package database;

import converter.StromlastdatenConverter;
import model.Stromlastdaten;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSV_Handler {

    private final StromlastdatenConverter stromlastdatenConverter = new StromlastdatenConverter();

    public CSV_Handler() {}

    public List<Stromlastdaten> readStromlastdatenFormCSV(String filePath) {
        System.out.println("Reading contents form file...");
        Path pathToFile = Paths.get(filePath);
        List<Stromlastdaten> stromlastdatenList = new ArrayList<>();

        try(BufferedReader bufferedReader = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = bufferedReader.readLine();
            int index = 0;
            while (line != null) {
                String[] attributes = line.split((","));
                Stromlastdaten stromlastdaten = stromlastdatenConverter.convertToStromlastdaten(attributes);
                stromlastdatenList.add(stromlastdaten);
                line = bufferedReader.readLine();
            }
            return stromlastdatenList;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return null;
        }

    }

}
