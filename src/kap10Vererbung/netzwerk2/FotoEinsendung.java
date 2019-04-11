package kap10Vererbung.netzwerk2;

/**
 * Diese Klasse speichert Informationen über eine kap10Vererbung.netzwerk2.Einsendung für den Newsfeed eines sozialen Netzwerks.
 * Der Hauptteil der kap10Vererbung.netzwerk2.Einsendung besteht aus einem Foto und einer Überschrift.
 * Weitere Daten, wie Autor und Datum, werden ebenfalls gespeichert.
 * 
 * @author Michael Kölling und David J. Barnes
 * @version 0.2
 */
public class FotoEinsendung extends Einsendung
{
    private String dateiname;  // der Name der Bilddatei
    private String ueberschrift;   // einzeilige Bildüberschrift

    /**
     * Konstruktor für Objekte der Klasse kap10Vererbung.netzwerk2.FotoEinsendung.
     * 
     * @param autor          der Benutzername des Einsenders
     * @param dateiname      der Dateiname des Bildes in dieser kap10Vererbung.netzwerk2.Einsendung
     * @param ueberschrift   eine Überschrift für das Bild
     */
    public FotoEinsendung(String autor, String dateiname, String ueberschrift)
    {
        super(autor);
        this.dateiname = dateiname;
        this.ueberschrift = ueberschrift;
    }
    
    /**
     * Liefere den Dateinamen des Bildes aus der kap10Vererbung.netzwerk2.Einsendung.
     * 
     * @return  den Namen der Bilddatei
     */
    public String gibBilddateiname()
    {
        return dateiname;
    }

    /**
     * Liefere die Überschrift des Bildes aus der kap10Vererbung.netzwerk2.Einsendung.
     * 
     * @return  die Überschrift des Bildes
     */
    public String gibUeberschrift()
    {
        return ueberschrift;
    }
}
