package bildverarbeitung;
import java.util.*;

/**
 * Klasse zur Verarbeitung von Bildern.
 * 
 * @author Carlo Bach
 * @version 16.2.2016
 */
public class Bild {
    
    private String titel;
    private int[][] bild;
       
    /**
     * Erzeuge ein neues leeres Bild mit dem Titel "Kein Bild".
     */
    public Bild() {
        this("Kein Bild", null);
    }
    
    /**
     * Erzeuge ein neues Bild mit dem gegebenen Titel und den Bilddaten.
     * 
     * @param titel die Titel des Bildes
     * @param bild die Bilddaten
     */
    public Bild(String titel, int[][] bild) {
        this.titel = titel;
        this.bild = bild;
    }

    /**
     * Der Titel des Bildes.
     * 
     * @return der Titel des Bildes
     */
    public String gibTitel() {
        return titel + " (" + gibBreite() + " x " + gibHoehe() + ")";
    }
    
    /**
     * Die Breite des Bildes. Falls kein Bild gesetzt worden ist, gibt die Methode 0 zur�ck.
     * 
     * @return die Bildbreite, oder 0, falls kein Bild gesetzt worden ist.
     */
    public int gibBreite() {
        if (bild != null){
            return bild.length;
        } else {
            return 0;
        }
    }
    
    /**
     * Die H�he des Bildes. Falls kein Bild gesetzt worden ist, gibt die Methode 0 zur�ck.
     * 
     * @return die Bildh�he, oder 0, falls kein Bild gesetzt worden ist.
     */
    public int gibHoehe() {
        if (bild != null && bild[0] != null){
            return bild[0].length;
        } else {
            return 0;
        }
    }

    /**
     * Die Intensit�t an der gegebenen Spalte und Zeile. Falls die Angaben bez�glich Zeile oder Spalte ung�ltig sind, wird 0 zur�ckgegeben. 
     * 
     * @param spalte die Spalte
     * @param zeile die Zeile
     * @return der Intensit�tswert zwischen 0 (schwarz) und 255 (weiss), oder 0 bei ung�ltigen Zeilen-/Spaltenwerten 
     */
    public int gibIntensitaetswert(int spalte, int zeile) {
        if (0 <= spalte && spalte < gibBreite() && 0 <= zeile && zeile < gibHoehe()) {
            return bild[spalte][zeile];
        } else {
            //Der Rueckgabewert 0 ermoeglicht eine einfache Randbehandlung bei linearen Filtern.
            return 0;
        }
    }

    /**
     * Die Bilddaten des Bildes.
     * 
     * @return die Bilddaten oder null, falls kein Bild gesetzt wurde.
     */
    public int[][] gibBilddaten() {
        return bild;
    }
    
    /**
     * Bild kopieren
     *
     * @return eine Kopie des Bildes. Als Titel wird an den Originalnamen die Endung " - Kopie" angeh�ngt
     */
    public Bild erstelleKopie() {
        int[][] b = new int[gibBreite()][gibHoehe()];
        for (int spalte = 0; spalte < gibBreite(); spalte++) {
            for (int zeile = 0; zeile < gibHoehe(); zeile++) {
                b[spalte][zeile] = bild[spalte][zeile];
            }
        }
        return new Bild(gibTitel() + " - Kopie", b);
    }
    
    /**
     * Aufgabe: Histogramm berechnen
     * 
     * @return das Histogramm 
     */
    public int[] gibHistogramm() {
        //Deklarieren Sie eine int[]-Variable und erzeugen Sie einen Array der richtigen L�nge!
        
        //Wenden Sie das Iterationsmuster mit den zwei for-Schleifen an, um jedes Pixel zu erreichen.

        int[] hist = new int[256];
        for(int spalte =0; spalte < gibBreite();spalte++)
        {
            for (int zeile=0; zeile < gibHoehe();zeile++)
            {
                hist[gibIntensitaetswert(spalte,zeile)]++;
            }
        }
        return hist;
    }

    /**
     * Aufgabe: Invertieren 
     */
    public void bild_invertieren() {
        //I'(x,y) = 255 - I(x,y)
        for(int spalte =0; spalte < gibBreite();spalte++)
        {
            for (int zeile=0; zeile < gibHoehe();zeile++)
            {
                this.bild[spalte][zeile]=255-this.bild[spalte][zeile];
            }
        }
    }

    /**
     * Aufgabe: Aufhellen um einen Offset anheben
     * 
     * @param offset der Offset, um den die Intensitaetswerte angehoben werden
     */
    public void bild_aufhellen(int offset) {
        //I'(x,y) = I(x,y) + wert; Achtung: der Wertebereich 0 .. 255 darf nicht verlassen werden
        //Verwenden Sie Math.max bzw. Math.min
        for(int spalte =0; spalte < gibBreite();spalte++)
        {
            for (int zeile=0; zeile < gibHoehe();zeile++)
            {
                if(offset >0)
                    this.bild[spalte][zeile]=Math.min(this.bild[spalte][zeile]+offset,255);
                if(offset<0)
                    this.bild[spalte][zeile]=Math.max(this.bild[spalte][zeile]+offset,0);
            }
        }
    }

    /**
     * Aufgabe: Kontrast um einen Faktor erh�hen 
     * 
     * @param faktor der Faktor, um den die Intensitaetswerte angehoben werden
     */
    public void bild_kontrastErhoehen(double faktor) {
        //I'(x,y) = faktor * I(x,y); Achtung: der Wertebereich 0 .. 255 darf nicht verlassen werden. 
        //Verwenden Sie Math.max bzw. Math.min sowie die cast-Operation

        for(int spalte =0; spalte < gibBreite();spalte++)
        {
            for (int zeile=0; zeile < gibHoehe();zeile++)
            {
                int gray = this.bild[spalte][zeile];
                gray *=faktor;
                if(gray >255){gray = 255;}
                if(gray <0){gray =0;}
                this.bild[spalte][zeile]=gray;
            }
        }
    }

    /**
     * Aufgabe: Kontrast optimieren 
     */
    public void bild_kontrastOptimieren() {
        // Suchen Sie zuerst die minimale und maximale Intensitaet im Bild. Transformieren Sie das Bild im Anschluss.
        //1. minimale und maximale Intensitaet bestimmen (eine alternative Loesung wuerde das Histogramm verwenden)

        /*int min = 255;
        int max = 0;
        int x = 0;
        for(int i : gibHistogramm())
        {
            if(i!=0)
            {
                if(x<min){min =x;}
                if(x>max){max =x;}
            }
            x++;
        }
        */
        int min = 1000000000;
        int max = -1000000000;
        for(int spalte =0; spalte < gibBreite();spalte++)
        {
            for (int zeile=0; zeile < gibHoehe();zeile++)
            {
                if(bild[spalte][zeile]<min){min =bild[spalte][zeile];}
                if(bild[spalte][zeile]>max){max =bild[spalte][zeile];}
            }
        }


        //2. transformieren
        for(int spalte =0; spalte < gibBreite();spalte++)
        {
            for (int zeile=0; zeile < gibHoehe();zeile++)
            {
                int gray =this.bild[spalte][zeile];
                gray-=min;
                if(gray <0) { gray =0; }
                gray = Math.min(255,(int)(gray*255.0/(double)(max-min)));

                this.bild[spalte][zeile]=gray;
            }
        }

    }

    /**
     * Aufgabe: Schwellwertverfahren 
     * 
     * @param schwellwert der Schwellwert, der als Grenze genommen wird
     */
    public void bild_schwellwertAnwenden(int schwellwert)
    {
        for(int spalte =0; spalte < gibBreite();spalte++)
        {
            for (int zeile=0; zeile < gibHoehe();zeile++)
            {
                int gray =this.bild[spalte][zeile];

                if(gray <schwellwert) { gray =0; }
                if (gray >= schwellwert) { gray =255; }

                this.bild[spalte][zeile]=gray;
            }
        }
    }

    /**
     * Aufgabe: Mittelwertfilter 3x3
     * 
     * @param eingangsbild das Bild, das gefiltert wird
     */
    public void bild_mittelwertfilter(Bild eingangsbild)
    {
        for(int spalte =0; spalte < gibBreite();spalte++)
        {
            for (int zeile=0; zeile < gibHoehe();zeile++)
            {
                int c =0;
                int speicher =0;
                for(int x=-1;x<=1;x++)
                {
                    for(int y=-1;y<=1;y++)
                    {
                       if(spalte+x <0 || spalte+x>gibBreite()-1)
                       {
                           c++;
                           continue;
                       }
                       if(zeile+y<0 || zeile+y>gibHoehe()-1)
                       {
                           c++;
                           continue;
                       }
                       speicher += eingangsbild.bild[spalte+x][zeile+y];
                    }
                }
                speicher = (int)(speicher /(9.0-c));
                this.bild[spalte][zeile]=speicher;
            }
        }
    }

    /**
     * Aufgabe: Genereller Linearer Filter mit einem Filterkern
     * 
     * @param eingangsbild das Bild, das gefiltert wird
     * @param filter der Filterkern
     */
    public void bild_linearerfilter(Bild eingangsbild, double[][] filter)
    {
        double min = 0;
        double max = 0;
        for(double[] i : filter)
        {
            for(double d: i)
            {
                if(d<0){min+=d;}
                if(d>0){max+=d;}
            }
        }
        min = min*255;
        max = max*255;

        int x_mid = filter.length/2;    //mitte breite
        int y_mid= filter[0].length/2;    //mitte höhe

        for(int spalte =x_mid; spalte < gibBreite()-x_mid;spalte++)
        {
            for (int zeile=y_mid; zeile < gibHoehe()-y_mid;zeile++)
            {
                double speicher =0;

                //Filter teil




                for(int x=0-x_mid;x<x_mid+1;x++)
                {
                    for(int y=0-y_mid;y<y_mid+1;y++)
                    {

                        double f = filter[x+x_mid][y+y_mid];
                        speicher += eingangsbild.bild[spalte+x][zeile+y]*f;
                    }
                }
                speicher = Math.sqrt(speicher * speicher);
                //speicher = Math.sqrt(speicher*speicher);
                speicher= ((speicher)*255.0/(max-min))*2;
                this.bild[spalte][zeile]=(int)speicher;
            }

        }

    }


    public void bild_sobel(Bild eingangsbild, double[][] filter)
    {
        double[][] filter_y = {{-1.0,0.0,1.0},{-2.0,0.0,2.0},{-1.0,0.0,1.0}};
        double[][] filter_x = {{-1.0,-2.0,-1.0},{0.0,0.0,0.0},{1.0,2.0,1.0}};

        for(int spalte =1; spalte < gibBreite()-1;spalte++)
        {
            for (int zeile=1; zeile < gibHoehe()-1;zeile++)
            {
                int c =0;
                double speicher_x =0;
                double speicher_y =0;

                //Filter teil

                int x_mid = filter_x.length/2;    //mitte breite
                int y_mid= filter_x[0].length/2;    //mitte höhe


                for(int x=0-x_mid;x<x_mid+1;x++)
                {
                    for(int y=0-y_mid;y<y_mid+1;y++)
                    {
                        double f_x = filter_x[x+x_mid][y+y_mid];
                        double f_y = filter_y[x+x_mid][y+y_mid];
                        speicher_x += eingangsbild.bild[spalte+x][zeile+y]*f_x;
                        speicher_y += eingangsbild.bild[spalte+x][zeile+y]*f_y;
                    }
                }
                //speicher= (speicher-1020)*255/2040;
                double speicher =  (int)(Math.sqrt(speicher_x*speicher_x+speicher_y*speicher_y));

                speicher = (speicher)*(255/1443.0);
                this.bild[spalte][zeile]=(int)speicher;
            }


        }
    }



    /**
     * Aufgabe: Medianfilter 3x3
     * 
     * @param eingangsbild das Bild, das gefiltert wird
     */
    public void bild_medianfilter(Bild eingangsbild)
    {

    }

    /**
     * Aufgabe: Template Matching
     * 
     * @param suchbild das Bild, das durchsucht wird
     * @param template das Bild, das gesucht wird
     */
    public void bild_templatematching(Bild suchbild, Bild template)
    {
        double zwischenspeicher=0;
        Bild Resultate = new Bild("Ergebnis",new int[suchbild.gibBreite()-template.gibBreite()][suchbild.gibHoehe()-template.gibHoehe()]);
        //Suchbild
        for(int outer_spalte =0; outer_spalte < (suchbild.gibBreite()-template.gibBreite());outer_spalte++)
        {
            for (int outer_zeile=0; outer_zeile < (suchbild.gibHoehe()-template.gibHoehe());outer_zeile++)
            {

                //Inneres Bild
                for(int inner_spalte =0; inner_spalte < template.gibBreite();inner_spalte++)
                {
                    for (int inner_zeile=0; inner_zeile < template.gibHoehe();inner_zeile++)
                    {
                        zwischenspeicher += Math.pow(suchbild.bild[outer_spalte+inner_spalte][outer_zeile+inner_zeile]-template.bild[inner_spalte][inner_zeile],2);
                    }
                }
                Resultate.bild[outer_spalte][outer_zeile]=(int)(Math.sqrt(zwischenspeicher));
                zwischenspeicher =0;
            }
        }
        bild_kontrastOptimieren();
        this.bild = Resultate.bild;
    }
        
    
    /**
     * Aufgabe: Geometrische Transformation: Rotation. Beachten Sie, dass das Rotationszentrum zuerst in den Ursprung geschoben, dann rotiert und wieder zurueckgeschoben werden muss.
     * 
     * @param eingangsbild das Bild, das veraendert wird
     * @param rotationszentrumSpalte die u-Position des Rotationszentrums
     * @param rotationszentrumZeile die v-Position des Rotationszentrums
     * @param winkel der Rotationswinkel (Achtung in Radian)
     */
    public void bild_rotation(Bild eingangsbild, int rotationszentrumSpalte, int rotationszentrumZeile, double winkel)
    {



        for(int spalte =0; spalte < eingangsbild.gibBreite();spalte++)
        {
            for (int zeile=0; zeile < eingangsbild.gibHoehe();zeile++)
            {
                int x =(int)((spalte-rotationszentrumSpalte)*Math.cos(winkel)+(zeile-rotationszentrumZeile)*Math.sin(winkel));
                int y =(int)((-(spalte-rotationszentrumSpalte)*Math.sin(winkel))+((zeile-rotationszentrumZeile)*Math.cos(winkel)));
                this.bild[spalte][zeile] = eingangsbild.gibIntensitaetswert(x+rotationszentrumSpalte,y+rotationszentrumZeile);
            }
        }

    }

    /**
     * Aufgabe: Verzerren. Twirl-Transformation: Rotation um alpha, die mit dem Abstand vom Zentrum abnimmt 
     * 
     * @param eingangsbild das Bild, das veraendert wird
     * @param rmax der maximale Radius, bis wohin gedreht wird
     * @param alpha der Rotationswinkel (Achtung in Radian)
     */
    public void bild_twirl(Bild eingangsbild, int rmax, double alpha)
    {
        int rotationszentrumSpalte = eingangsbild.gibBreite()/2;
        int rotationszentrumZeile = eingangsbild.gibHoehe()/2;
        for(int spalte =0; spalte < eingangsbild.gibBreite();spalte++)
        {
            for (int zeile=0; zeile < eingangsbild.gibHoehe();zeile++)
            {
                int x =0;
                int y =0;
                double beta = Math.atan2(1,2);
                if(Math.sqrt(Math.pow(spalte-rotationszentrumSpalte,2)+Math.pow(zeile-rotationszentrumZeile,2))<=rmax)
                {
                    x = (int)(x+(Math.sqrt(Math.pow(spalte-rotationszentrumSpalte,2)+Math.pow(zeile-rotationszentrumZeile,2)))*Math.cos(alpha))+rotationszentrumSpalte;
                    y = (int)(y+(Math.sqrt(Math.pow(spalte-rotationszentrumSpalte,2)+Math.pow(zeile-rotationszentrumZeile,2)))*Math.sin(alpha))+rotationszentrumZeile;
                }else{
                    x = spalte+rotationszentrumSpalte;
                    y = zeile+rotationszentrumZeile;


                }
                this.bild[spalte][zeile] = eingangsbild.gibIntensitaetswert(x,y);
            }
        }
    }

}