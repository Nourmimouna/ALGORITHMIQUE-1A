
/**
 * TP 8: codage d'un graphe non orienté- classe Graph
 */

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WeightedGraph {
    private int vertexSize;
    private final List<Map<Integer,Integer>> adjacency; // matrice d'adjacence maintenue symétrique

    /**
     * Constructeur sans paramètre
     */
    public WeightedGraph() {
        this(1);
    }

    public List<Map<Integer,Integer>> getadjacency(){
        return this.adjacency; 
    }

    public Map<Integer, Integer>  getNeighbors(int src) {
        return adjacency.get(src);
    }

    /**
     * Constructeur avec paramètres
     */
    public WeightedGraph(int n) {
        this.adjacency = new ArrayList<>(n);
        /* remarque : le paramètre du constructeur n'est qu'une capacité initiale, ce n'est pas la taille de la liste représentée (c'est donc juste de l'optimisation de passer ce paramètre maintenant) */
        this.vertexSize = 0;
        for (int i = 0; i < n; i++)
            addVertex();
    }

    /**
     * Retourne la taille du graphe (en nombre de sommets)
     */
    public int getVertexSize() {
        return vertexSize;
    }

    /**
     * Retourne true si et seulement si i est un sommet du graphe
     */
    public boolean isVertex(int i) {
        return i >= 0 && i < vertexSize;
    }

    /**
     * Ajoute un sommet déconnecté à la liste des sommets et retourne son numéro
     */
    public final int addVertex() { // final par sécurité :  cette méthode est appelée dans le constructeur
        var outgoingEdges = new HashMap<Integer, Integer>();
        adjacency.add(outgoingEdges);
        return vertexSize++;
    }

    /**
     * Retourne la valeur de l'arc entre src et dst (null si et seulement si un tel
     * arc n'existe pas).
     */
    public Integer getEdgeWeight(int src, int dst) {
        if (isVertex(src) && isVertex(dst)) {
            return adjacency.get(src).get(dst); // Premier get en O(1), deuxième en O(log(d)) (dépend de l'implémentation de hashCode et de HashMap)
        } else
            return null;
    }

    /**
     * Ajoute un ou deux arcs (en fonction du type de graphe) entre src et dst avec
     * l'étiquette lab Retourne true si l’opération a été effectuée avec succès et
     * false sinon
     */
    public boolean addEdge(int src, int dst, Integer weight) {
        if (isVertex(src) && isVertex(dst)) {
            adjacency.get(src).put(dst, weight);
            adjacency.get(dst).put(src, weight);
            return true;
        } else
            return false;
    }

    /**
     * Retourne la liste d'adjacence pour le sommet src
     */
    public Map<Integer, Integer> neighbours(int src) {
        return adjacency.get(src);
    }

    /**
     * Affiche la liste d'adjacence du graphe dans une chaîne de caractères (dans le
     * même format que pour la classe Edge). Si le graphe n'est pas orienté, chaque
     * arête ne doit apparaître qu'une seule fois.
     */
    @Override
    public String toString() {
        return IntStream.range(0, vertexSize).mapToObj(x->x).flatMap(
            src -> IntStream
            .range(0, adjacency.get(src).size())
            .mapToObj(dst -> {
                    var weight = adjacency.get(src).get(dst);
                    return ((src<=dst && weight != null)? String.format("(%s--%s)[%s]", src, dst, weight):null);
            })
            .filter(x -> (x != null)))
            .collect(Collectors.joining(", "));
    }


    /**
     * Teste l’implémentation
     */
    public static void testImplementation() {
        int score = 0;
        int nTests = 7;

        System.out.println("Testing the implementation:");

        WeightedGraph g1 = new WeightedGraph();
        if (g1.isVertex(0)) {
            System.out.println("\t- test 1: pass");
            score++;
        } else {
            System.out.println("\t- test 1: fail");
        }

        g1.addVertex();
        if (g1.isVertex(1)) {
            System.out.println("\t- test 2: pass");
            score++;
        } else {
            System.out.println("\t- test 2: fail");
        }

        WeightedGraph g2 = new WeightedGraph(6);
        g2.addEdge(0, 1, 20);
        g2.addEdge(0, 2, 21);
        g2.addEdge(1, 3, 22);
        g2.addEdge(1, 4, 23);

        var weight = g2.getEdgeWeight(0, 1);
        if (20 == weight) {
            System.out.println("\t- test 3: pass");
            score++;
        } else {
            System.out.println("\t- test 3: fail");
        }

        weight = g2.getEdgeWeight(1, 0);
        if (20 == weight) {
            System.out.println("\t- test 4: pass");
            score++;
        } else {
            System.out.println("\t- test 4: fail");
        }

        weight = g2.getEdgeWeight(4, 5);
        if (null == weight) {
            System.out.println("\t- test 5: pass");
            score++;
        } else {
            System.out.println("\t- test 5: fail");
        }

        weight = g2.getEdgeWeight(4, 15);
        if (null == weight) {
            System.out.println("\t- test 6: pass");
            score++;
        } else {
            System.out.println("\t- test 6: fail");
        }

        g2.addEdge(4, 5, 25);
        weight = g2.getEdgeWeight(4, 5);
        if (25 == weight) {
            System.out.println("\t- test 7: pass");
            score++;
        } else {
            System.out.println("\t- test 7: fail");
        }

        System.out.println("Score " + score + "/" + nTests);
    }

    public static void main(String[] args) {
        testImplementation();
    }
}