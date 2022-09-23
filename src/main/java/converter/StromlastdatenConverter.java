package converter;

import model.Stromlastdaten;

import java.io.IOException;

public class StromlastdatenConverter {
    public StromlastdatenConverter() {}

    public Stromlastdaten convertToStromlastdaten(String[] attributes) throws RuntimeException {
        Stromlastdaten stromlastdaten = new Stromlastdaten();
        if (attributes.length != 4) {
            throw new RuntimeException("Falsche länge der Eingabedaten");
        }
        try {
            stromlastdaten.setZeit(attributes[0]);
            stromlastdaten.setKw(Double.parseDouble(attributes[1]));
            stromlastdaten.setStatus(attributes[2]);
            stromlastdaten.setKundeId(Integer.parseInt(attributes[3]));
            return stromlastdaten;
        } catch (Exception ex) {
            return new Stromlastdaten();
        }

    }
}
