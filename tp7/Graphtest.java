/**
 * TP 9: codage d'un graphe - classe Graph
 */

 import java.util.*;
 import java.util.stream.Collectors;
 
 
 public class Graph{
     private boolean isDirected;
     private int vertexSize;
     private List<List<Edge>> adjacency;
 
     /**
      * Constructeur sans paramètre
      */
     public Graph() 
         {
         /* A COMPLETER */
         this.isDirected = true;
         this.vertexSize = 1;
         this.adjacency = new ArrayList<List<Edge>>();
         this.adjacency.add(new ArrayList<Edge>());
         
         }
 
     /**
      * Constructeur avec paramètres
      */
     public Graph(boolean isDir, int n)
         {
         /* A COMPLETER */
         this.isDirected = isDir;
         this.vertexSize = n;
         this.adjacency = new ArrayList<List<Edge>>(n);
         for (int i = 0; i < n; i++) {
             this.adjacency.add(new ArrayList<Edge>());
                                     }
         }
      
         
 
     /**
      * Retourne le type du graphe (true = dirigé)
      */
     public boolean getDir() 
                 {
                      return this.isDirected;
                 }
 
     /**
      * Retourne true si et seulement si i est un sommet du graphe
      */
     public boolean isVertex(int i)
     {
         /* A COMPLETER */
         if (i >= 0 && i < this.vertexSize)
             {
                 return true;
             }
         return false;
     }
 
     /**
      * Ajoute un sommet déconnecté à la liste des sommets et retourne son numéro
      */
     public int addVertex()
     {
         /* A COMPLETER */
         this.adjacency.add(new ArrayList<Edge>()); //On ajoute d'une nouvelle liste d'arêtes à la liste d'adjacence
         return this.vertexSize++;
         
     }
 
     /**
      * Retourne la liste des arcs entre src et dst (la liste doit être vide
      * s'il n'y a pas de tel arc dans le graphe)
      */
     public List<Edge> getEdges(int src, int dst)
     {
         /* A COMPLETER */
 
 
     List<Edge> edges = new ArrayList<Edge>();
         if (!isVertex(src) || !isVertex(dst)) { //Si l'un des sommets n'est pas valide, on renvoie
         //simplement une liste vide
             return edges;
         }
         // On Parcourt la liste des arêtes adjacentes au sommet source, si l'arête mène au sommet de destination
         //On ajoute l'arête à la liste des arêtes.
         for (Edge edge : adjacency.get(src)) { 
             if (edge.getDst() == dst) {
                 edges.add(edge);
             }
         }
         return edges;
     }
         
       
     
 
     /**
      * Ajoute un ou deux arcs (en fonction du type de graphe) entre src et dst
      * avec l'étiquette lab
      * Retourne true si l’opération a été effectuée avec succès et false sinon
      */
     public boolean addEdge(int src, int dst, String lab)
     {
         /* A COMPLETER */
         if (!isVertex(src) || !isVertex(dst)) {
             return false;
         }
         Edge edge = new Edge(src, dst, lab, isDirected);
          //On ajoute l'arête à la liste adjacente du sommet source,si l'arête n'est pas dirigée alors on cree 
          //une arête inverse entre les sommets destination et source 
          // Par la suite on ajoute l'arête inverse à la liste d'adjacente du sommet destination
        this.adjacency.get(src).add(edge);
         if (!isDirected) {
             Edge reverseEdge = new Edge(dst, src, lab, isDirected);
             this.adjacency.get(dst).add(reverseEdge);
         }
         return true;
     }
          
     
 
     /**
      * Affiche la liste d'adjacence du graphe dans une chaîne de caractères
      * (dans le même format que pour la classe Edge). Si le graphe n'est pas
      * orienté, chaque arête ne doit apparaître qu'une seule fois.
      * 
      * Exemple (non dirig): [0 -- 1 [label = a], 0 -- 2 [label = b], 1 -- 2 [label = c]
      * Exemple (dirig): [0 -> 2 [label = b], 1 -> 0 [label = a], 1 -> 2 [label = a]
      */
     @Override
     public String toString() {
        StringBuilder sb = new StringBuilder();
         sb.append("[");
         for (int i = 0; i < vertexSize; i++) {
             for (Edge edge : this.adjacency.get(i)) {
                 if (isDirected || edge.getSrc() <= edge.getDst()) {
                     sb.append(edge.toString()).append(", ");
                 }
             }
         }
         if (sb.length() > 1) {
             sb.setLength(sb.length() - 2);
         }
         sb.append("]");
         return sb.toString();
     }
     
 
     /**
      * Renvoie le chemin (s'il existe) entre n1 et n2 en effectuant un parcours
      * en largeur du graphe. La chaîne de caractère doit être de la forme 
      * "1 -> 3 -> 5 -> 2"
      */
     public String searchPathBFS(int n1, int n2) {
         /* A COMPLETER */
         if (!isVertex(n1) || !isVertex(n2)) {
             return "";
         }
         Queue<Integer> queue = new LinkedList<Integer>();
         Map<Integer, Integer> prev = new HashMap<Integer, Integer>();
         Set<Integer> visited = new HashSet<Integer>();
         //On ajoute le sommet n1 à la file d'attente et à l'ensemble des sommets visités
         // Tant que notre file d'attente n'est pas vide on récupère et on supprime le premier
         // sommet de notre file d'attente et si notre sommet courant est egale au sommet n2 on arrête simplement la boucle.
         queue.add(n1);
         visited.add(n1);
         while (!queue.isEmpty()) {
             int current = queue.poll();
             if (current == n2) {
                 break;
             }
             for (Edge edge : this.adjacency.get(current)) {
                 int neighbor = edge.getDst();
                 if (!visited.contains(neighbor)) {
                     queue.add(neighbor);
                     visited.add(neighbor);
                     prev.put(neighbor, current);
                 }
             }
         }
         List<Integer> path = new ArrayList<Integer>();
         if (!prev.containsKey(n2)) { // Si la liste des predecesseurs, ne contient pas n2 , on renvoie une chaine vide
         // pour indiquer qu'il n'y a pas de chemin entre les sommets n1 et n2.
             return "";
         }
         for (Integer current = n2; current != null; current = prev.get(current)) { // On va parcourir toutes notre listes
         // de predecesseurs jusqu 'à retomber sur n1 et on ajoute chaque sommet au chemin durant le parcours
             path.add(current);
         }
         Collections.reverse(path); //On a besoin de faire une
         // inversion de l'ordre du chemin pour obtenir le chemin du sommet n1 au sommet n2
         return path.stream().map(String::valueOf).collect(Collectors.joining(" -> "));
         
     }
 
     /**
      * Renvoie le chemin (s'il existe) entre n1 et n2 en effectuant un parcours
      * en profondeur du graphe
      */
     public String searchPathDFS(int n1, int n2) {
         /* A COMPLETER */
         if (!isVertex(n1) || !isVertex(n2)) {
             return "";
         }
         Stack<Integer> stack = new Stack<Integer>();
         Map<Integer, Integer> prev = new HashMap<Integer, Integer>();
         Set<Integer> visited = new HashSet<Integer>();
         stack.push(n1);
         visited.add(n1);
         while (!stack.isEmpty()) { // Tant que notre pile n'est pas vide, on récupère
         // et on supprime le sommet en haut de celle-ci. Par ailleurs, si le sommet
         // courant est egale au sommet n2, on arrete notre boucle vu que ça n'a plus d'interet. 
 
             int current = stack.pop();
             if (current == n2) {
                 break;
             }
             for (Edge edge : adjacency.get(current)) {
                 int neighbor = edge.getDst();
                 if (!visited.contains(neighbor)) {
                 //  // On ajoute le sommet voisin à la pile et à l'ensemble des sommets visités et
                 // On Stocke le sommet courant comme étant le prédécesseur du sommet voisin dans le chemin
                     stack.push(neighbor);
                     visited.add(neighbor);
                     prev.put(neighbor, current);
                 }
             }
         }
         List<Integer> path = new ArrayList<Integer>();
         if (!prev.containsKey(n2)) {
             return "";
         }
         for (Integer current = n2; current != null; current = prev.get(current)) {
             path.add(current);
         }
         Collections.reverse(path);
         return path.stream().map(String::valueOf).collect(Collectors.joining(" -> "));
     }
 
     /**
      * Teste l’implémentation
      */
     public static void testImplementation() {
         int score = 0;
         int nTests = 10;
 
         System.out.println("Testing the implementation:");
 
         Graph g1 = new Graph();
         if (g1.isVertex(0)) {
             System.out.println("\t- test 1: pass");
             score++;
         } else {
             System.out.println("\t- test 1: fail");
         }
 
         if (g1.getDir()) {
             System.out.println("\t- test 2: pass");
             score++;
         } else {
             System.out.println("\t- test 2: fail");
         }
 
         g1.addVertex();
         if (g1.isVertex(1)) {
             System.out.println("\t- test 3: pass");
             score++;
         } else {
             System.out.println("\t- test 3: fail");
         }
 
         Graph g2 = new Graph(true, 6);
         g2.addEdge(0, 1, "a");
         g2.addEdge(0, 2, "b");
         g2.addEdge(1, 3, "c");
         g2.addEdge(1, 4, "d");
 
         List<Edge> edges = g2.getEdges(0, 1);
         if (edges.size() == 1) {
             System.out.println("\t- test 4: pass");
             score++;
         } else {
             System.out.println("\t- test 4: fail");
         }
 
         edges = g2.getEdges(4, 5);
         if (edges.size() == 0) {
             System.out.println("\t- test 5: pass");
             score++;
         } else {
             System.out.println("\t- test 5: fail");
         }
 
         edges = g2.getEdges(4, 15);
         if (edges.size() == 0) {
             System.out.println("\t- test 6: pass");
             score++;
         } else {
             System.out.println("\t- test 6: fail");
         }
 
         g2.addEdge(4, 5, "e");
         edges = g2.getEdges(4, 5);
         if (edges.size() == 1) {
             System.out.println("\t- test 7: pass");
             score++;
         } else {
             System.out.println("\t- test 7: fail");
         }
 
         Graph g3 = new Graph(true, 2);
         g3.addEdge(0, 1, "a");
         g3.addVertex();
         g3.addEdge(2, 0, "b");
         g3.addEdge(1, 2, "c");
         if("[0 -> 1 [label = a], 1 -> 2 [label = c], 2 -> 0 [label = b]]".equals(g3.toString())){
             System.out.println("\t- test 8: pass");
             score++;
         } else {
             System.out.println("\t- test 8: fail");
         }
 
         Graph g4 = new Graph(true, 7);
         g4.addEdge(0, 1, "a");
         g4.addEdge(0, 2, "b");
         g4.addEdge(0, 3, "c");
         g4.addEdge(1, 4, "c");
         g4.addEdge(2, 5, "d");
         g4.addEdge(3, 6, "d");
         g4.addEdge(4, 5, "d");
         g4.addEdge(6, 5, "d");
         if ("0 -> 2 -> 5".equals(g4.searchPathBFS(0, 5))) {
             System.out.println("\t- test 9: pass");
             score++;
         } else {
             System.out.println("\t- test 9: fail");
         }
         if ("0 -> 1 -> 4 -> 5".equals(g4.searchPathDFS(0, 5)) || "0 -> 3 -> 6 -> 5".equals(g4.searchPathDFS(0, 5))) {
             System.out.println("\t- test 10: pass");
             score++;
         } else {
             System.out.println("\t- test 10: fail");
         }
 
         System.out.println("Score " + score + "/" + nTests);
     }
 
     public static void main(String[] args) {
         testImplementation();
     }
 }