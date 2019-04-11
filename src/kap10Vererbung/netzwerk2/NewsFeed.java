package kap10Vererbung.netzwerk2;

import java.util.ArrayList;

/**
 * Die Klasse kap10Vererbung.netzwerk1.kap10Vererbung.netzwerk2.NewsFeed speichert neue Einsendungen für den Newsfeed einer
 * Sozialen-Netzwerk-Anwendung.
 * 
 * Derzeit wird das Anzeigen der Einsendungen durch die Ausgabe auf die Konsole 
 * simuliert. (Später soll die Anzeige in einem Webbrowser erfolgen.)
 * 
 * Diese Version speichert keine Daten auf Festplatte. Auch gibt es keine 
 * Unterstützung fürdas Durchsuchen oder Anordnen der Einsendungen.
 * 
 * @author Michael Kölling und David J. Barnes
 * @version 0.2
 */
public class NewsFeed
{
    private ArrayList<Einsendung> einsendungen;

    /**
     * Erzeuge einen leeren Newsfeed.
     */
    public NewsFeed()
    {
        einsendungen = new ArrayList<>();
    }

    /**
     * Füge dem Newsfeed eine kap10Vererbung.netzwerk2.Einsendung hinzu.
     * 
     * @param einsendung  die hinzuzufügende kap10Vererbung.netzwerk2.Einsendung
     */
    public void erfasseEinsendung(Einsendung einsendung)
    {
        einsendungen.add(einsendung);
    }

    /**
     * Gebe den Newsfeed aus. Derzeit werden die Details des Newsfeed auf die
     * Konsole ausgegeben. (Noch zu tun: ersetze diesen Code durch die Anzeige im
     * Webbrowser.)
     */
    public void zeigen()
    {
        // alle Einsendungen anzeigen
        for(Einsendung einsendung : einsendungen) {
            einsendung.anzeigen();
            System.out.println();   // leere Zeile zwischen den Einsendungen
        }
    }
}
