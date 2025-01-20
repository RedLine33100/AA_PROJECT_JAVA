package fr.gimlbl.aa.util;

import java.util.Random;
/**
 *
 * @author abergey
 */
public class GraphGenerator {
    public static final int NB_SOMMETS_DEFAUT = 10 ;
    public static final int POURCENTAGE_ARCS_DEFAULT = 40 ;
    public static final int POIDS_MAX_DEFAUT = 10 ;
    public static final int POIDS_MIN_DEFAUT = 1 ;


    /**
     * @param args the command line arguments
     */
    public static String generateRandomGraph(String[] args) {

        // initialisation des paramètres du graphe
        int nbSommets = NB_SOMMETS_DEFAUT ;
        int pourArcs = POURCENTAGE_ARCS_DEFAULT ;
        int poidsMax = POIDS_MAX_DEFAUT ;
        int poidsMin = POIDS_MIN_DEFAUT ;
        switch (args.length){
            case 4 : poidsMax = Integer.parseInt(args[3]) ;
            case 3 : poidsMin = Integer.parseInt(args[2]) ;
            case 2 : pourArcs = Integer.parseInt(args[1]) ;
            case 1 : nbSommets = Integer.parseInt(args[0]) ;
        }

        // Génération du graphe

        StringBuilder stringBuilder = new StringBuilder() ;
        stringBuilder.append(nbSommets).append(" ");
        Random r = new Random();
        for (int x = 1 ; x <= nbSommets ; x++ ){
            stringBuilder.append(x).append(" ");
            for (int y = 1 ; y < x ; y++){
                boolean existeArc = (r.nextInt(100)<pourArcs);
                if (existeArc){ // Il y a un arc entre x et y
                    stringBuilder.append(y).append(" ");
                    int poids = r.nextInt(poidsMax-poidsMin+1)+poidsMin ;
                    stringBuilder.append(poids).append(" ");

                }
            }
            stringBuilder.append(0+ " ");
        }
        return stringBuilder.toString() ;
    }
}