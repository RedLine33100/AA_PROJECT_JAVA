package fr.gimlbl.aa.kruskal;

public class Util {
    public static void checkArgs(String[] args) {
        if (args.length < 2 || args.length > 3) {
            System.err.println("Arguments attendus :\n" +
                    "* Nom du fichier contenant la description du graphe\n" +
                    "* Sommet de départ à partir duquel l'arbre recouvrant de poids minimum est calculé\n" +
                    "* Nom de fichier où écrire le résultat (facultatif)");
        }
    }
}
