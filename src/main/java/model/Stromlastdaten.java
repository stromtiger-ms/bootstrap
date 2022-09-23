package model;

public class Stromlastdaten {
    private String zeit;
    private double kw;
    private String status;
    Integer kundeId;

    public String getZeit() {
        return zeit;
    }

    public void setZeit(String zeit) {
        this.zeit = zeit;
    }

    public double getKw() {
        return kw;
    }

    public void setKw(double kw) {
        this.kw = kw;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getKundeId() {
        return kundeId;
    }

    public void setKundeId(Integer kundeId) {
        this.kundeId = kundeId;
    }

    @Override
    public String toString() {
        return "Stromlastdaten{" +
                "zeit='" + zeit + '\'' +
                ", kw=" + kw +
                ", status='" + status + '\'' +
                ", kundeId=" + kundeId +
                '}';
    }
}
