package fr.gimlbl.aa.kruskal;

public class Util {
    public static void checkArgs(String[] args) {
        if (args.length < 2 || args.length > 3) {
            System.err.println("Arguments attendus :\n" +
                    "* Nom de fichier décrivant le graphe\n" +
                    "* Sommet de départ\n" +
                    "* Nom de fichier où écrire le résultat (facultatif)");
        }
    }
}
