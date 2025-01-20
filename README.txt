# L'algorithme de Kruskal

Projet réalisé par Nino Gimenez et Axel Leblanc.

Ce projet consiste en l’implémentation de l’algorithme de Kruskal, permettant, à partir d’un graphe, de
construire un arbre recouvrant de poids minimum. Pour cela, nous mettons en œuvre l’algorithme pour des
graphes représentés de deux manières : par des listes d’adjacence et par une matrice d’adjacence. Nous
réalisons l'ensemble des structures de données dont nous avons besoin.

-----

Notre travail se présente sous la forme d'un projet Maven, pour être indépendant de tout IDE.

## Prérequis

Les éléments suivants doivent être installés :
* Java 8 ou version ultérieure du JDK
* Apache Maven (<https://maven.apache.org/install.html>)

Les commandes qui suivent devront être exécutées dans le répertoire du projet, au même niveau que le fichier `pom.xml`.

## Compiler le projet :

    mvn compile


## Exécuter les tests unitaires (JUnit 4)

    mvn test


## Exécuter le programme

    java -classpath target/classes fr.gimlbl.aa.kruskal.<Kruskal> <in> <out>

...où les éléments suivants doivent être renseignés :
* `<Kruskal>` est le nom de la classe principale à exécuter : `KruskalL` pour utiliser un graphe représenté par ses
  *listes* d'adjacence ; `KruskalM` pour utiliser un graphe représenté par sa *matrice* d'adjacence
* `<in>` est le nom du fichier contenant la représentation du graphe sur lequel exécuter l'algorithme de Kruskal.
  Dans le répertoire du projet, nous mettons à disposition des fichiers pour tester le programme.
* `<out>` est le nom de fichier où écrire le résultat (facultatif). Si non renseigné, le résultat est affiché dans la
  sortie standard.

## Exécuter le test de performances

    java -classpath target/classes fr.gimlbl.aa.util.PerformanceTest
