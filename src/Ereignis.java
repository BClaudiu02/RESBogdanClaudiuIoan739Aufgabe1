import java.time.LocalDate;

public class Ereignis {
    int id;
    String charaktername;
    final Stufe stufe;
    String Beschreibung;
    LocalDate datum;
    double Kraftpunkte;

    public Ereignis(int id, String charaktername, Stufe stufe, String beschreibung, LocalDate datum, double kraftpunkte) {
        this.id = id;
        this.charaktername = charaktername;
        this.stufe = stufe;
        Beschreibung = beschreibung;
        this.datum = datum;
        Kraftpunkte = kraftpunkte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharaktername() {
        return charaktername;
    }

    public void setCharaktername(String charaktername) {
        this.charaktername = charaktername;
    }

    public Stufe getStufe() {
        return stufe;
    }

    public String getBeschreibung() {
        return Beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        Beschreibung = beschreibung;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public double getKraftpunkte() {
        return Kraftpunkte;
    }

    public void setKraftpunkte(double kraftpunkte) {
        Kraftpunkte = kraftpunkte;
    }

    @Override
    public String toString() {
        return "Ereignis{" +
                "id=" + id +
                ", charaktername='" + charaktername + '\'' +
                ", stufe=" + stufe +
                ", Beschreibung='" + Beschreibung + '\'' +
                ", datum=" + datum +
                ", Kraftpunkte=" + Kraftpunkte +
                '}';
    }
}
