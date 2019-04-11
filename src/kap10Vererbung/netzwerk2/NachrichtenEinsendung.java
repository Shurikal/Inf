package kap10Vererbung.netzwerk2;

/**
 * Diese Klasse speichert Informationen über eine kap10Vererbung.netzwerk2.Einsendung für den Newsfeed eines sozialen Netzwerks.
 * Der Hauptteil der kap10Vererbung.netzwerk2.Einsendung besteht aus einer (möglicherweise mehrzeiligen) Textnachricht.
 * Weitere Daten, wie Autor und Datum, werden ebenfalls gespeichert.
 * 
 * @author Michael Kölling und David J. Barnes
 * @version 0.2
 */
public class NachrichtenEinsendung extends Einsendung
{
    private String nachricht;   // eine beliebig lange, mehrzeilige Nachricht

    /**
     * Konstruktor für Objekte der Klasse kap10Vererbung.netzwerk1.kap10Vererbung.netzwerk2.NachrichtenEinsendung.
     * 
     * @param autor  der Benutzername des Einsenders
     * @param text   der Text dieser kap10Vererbung.netzwerk2.Einsendung
     */
    public NachrichtenEinsendung(String autor, String text)
    {
        super(autor);
        nachricht = text;
    }

    /**
     * Liefere den Text der kap10Vererbung.netzwerk2.Einsendung.
     * 
     * @return  der Nachrichtentext der kap10Vererbung.netzwerk2.Einsendung
     */
    public String gibText()
    {
        return nachricht;
    }
}
