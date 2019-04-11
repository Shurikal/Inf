package kap10Vererbung.netzwerk1;

import java.util.ArrayList;

/**
 * Die Klasse kap10Vererbung.netzwerk1.kap10Vererbung.netzwerk2.NewsFeed speichert neue Einsendungen für den Newsfeed einer
 * Sozialen-Netzwerk-Anwendung.
 * 
 * Derzeit wird das Anzeigen der Einsendungen durch die Ausgabe auf die Konsole simuliert.
 * (Später soll die Anzeige in einem Webbrowser erfolgen.)
 * 
 * Diese Version speichert keine Daten auf Festplatte. Auch gibt es keine Unterstützung für
 * das Durchsuchen oder Anordnen der Einsendungen.
 * 
 * @author Michael Kölling und David J. Barnes
 * @version 0.1
 */
public class NewsFeed
{
    private ArrayList<NachrichtenEinsendung> nachrichten;
    private ArrayList<FotoEinsendung> fotos;

    /**
     * Erzeuge einen leeren Newsfeed.
     */
    public NewsFeed()
    {
        nachrichten = new ArrayList<>();
        fotos = new ArrayList<>();
    }

    /**
     * Füge dem Newsfeed eine eingesendete Nachricht hinzu.
     * 
     * @param nachricht  der eingesendete Text, der hinzugefügt werden soll
     */
    public void erfasseNachricht(NachrichtenEinsendung nachricht)
    {
        nachrichten.add(nachricht);
    }

    /**
     * Füge dem Newsfeed ein eingesendetes Foto hinzu.
     * 
     * @param foto  das eingesendete Foto, das hinzugefügt werden soll
     */
    public void erfasseFoto(FotoEinsendung foto)
    {
        fotos.add(foto);
    }

    /**
     * Gebe den Newsfeed aus. Derzeit werden die Details des Newsfeed auf die
     * Konsole ausgegeben. (Noch zu tun: ersetze diesen Code durch die Anzeige im
     * Webbrowser.)
     */
    public void zeigen()
    {
        // alle Text-Einsendungen anzeigen
        for(NachrichtenEinsendung nachricht : nachrichten) {
            nachricht.anzeigen();
            System.out.println();   // leere Zeile zwischen den Einsendungen
        }

        // alle Fotos anzeigen
        for(FotoEinsendung foto : fotos) {
            foto.anzeigen();
            System.out.println();   // leere Zeile zwischen den Einsendungen
        }
    }
}
