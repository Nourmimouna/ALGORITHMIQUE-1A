
/**
 * TP 8: codage d'un graphe non orienté- classe Graph
 */

import java.util.*;

public class GraphMatching extends Graph {
	List<Integer> uEndpoint;
	List<Integer> vEndpoint;

    /**
     * Constructeur sans paramètre
     */
    public GraphMatching() {
        this(1);
		uEndpoint = new ArrayList<>();
		vEndpoint = new ArrayList<>();
    }

    /**
     * Constructeur avec paramètres
     */
    public GraphMatching(int n) {
		super(n);
		uEndpoint = new ArrayList<>();
		vEndpoint = new ArrayList<>();
    }

    /**
     * Taille du couplage
     */
    public int sizeMatching() {
		return uEndpoint.size();
    }
	
    /**
     * Ajoute un sommet déconnecté à la liste des sommets et retourne son numéro
     */
	@Override
    public int addVertex() {
		int n = super.addVertex();
		return n;
    }
	
    /**
     * Vérifie si une paire (u,v) est bien une arête du graphe
     */
    public boolean isEdge(int u, int v) {
		return getNeighbors(u).contains(v) && getNeighbors(v).contains(u); 
    }
	
    /**
     * Vérifie si un sommet u est déjà couplé ou non. S'il est couplé, il renvoie
	 * l'autre extrémité de l'arête. S'il n'est pas couplé, renvoie -1.
     */
    public Integer isMatched(int u) {
		for (int i = 0; i < uEndpoint.size(); i++) {
			if (uEndpoint.get(i) == u) {
				return vEndpoint.get(i);
			}
		}
		for (int i = 0; i < vEndpoint.size(); i++) {
			if (vEndpoint.get(i) == u) {
				return uEndpoint.get(i);
			}
		}
		return -1;
	}

	/**
	 * Ajoute une arête dans le couplage si elle existe et que c'est possible de
	 * l'ajouter.
	 * Renvoie true si l'ajout a fonctionné, et false sinon
	 */
	public boolean addMatch(int u, int v) {
		if (isMatched(u) == -1 && isMatched(v) == -1 && isEdge(u, v)) {
			uEndpoint.add(u);
			vEndpoint.add(v);
			return true;
		}
		return false;
	}

	/**
	 * Vérifie si le couplage maximal est bon (i.e. chaque sommet est couplé au plus
	 * une fois
	 * et si un sommet n'est pas couplé, tous ses voisins le sont)
	 */
	public boolean isMaximalMatching() {
		int contient[] = new int[getVertexSize()];
		for (int i : uEndpoint) {
			contient[i] += 1;
		}
		for (int i : vEndpoint) {
			contient[i] += 1;
		}
		for (int i : contient) {
			if (i > 1) {
				return false;
			}
			if (i == 0) {
				for (int j : getNeighbors(i)) {
					if (contient[j] == 0) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Construit un couplage maximal et renvoie sa taille
	 */
	public int buildMatching() {
		int taille = 0;
		for (int i = 0; i < getVertexSize(); i++) {
			for (int j : getNeighbors(i)) {
				if (addMatch(i, j)) {
					taille++;
					break;
				}
			}
		}
		return taille;
	}

	/**
	 * Trouve un chemin augmentant de taille 4 et le renvoie sous forme de tableau
	 */
	public int[] augmentant4() {
		// chercher tout les nombre seul en faire des binome et les mettre dan
		// searchpath pour regarder si c'est bien des augmentant taille 4
		List<Integer> alone = new ArrayList<Integer>();
		for (int i = 0; i < getVertexSize(); i++) {
			if (isMatched(i) == -1) {
				alone.add(i);
			}
		}
		for (int i = 0; i < alone.size() - 1; i++) {
			for (int j = i + 1; j < alone.size(); j++) {
				for (int b : getNeighbors(alone.get(i))) {
					if (isEdge(isMatched(b), alone.get(j))) {
						int ret[] = new int[4];
						ret[0] = alone.get(i);
						ret[1] = b;
						ret[2] = isMatched(b);
						ret[3] = alone.get(j);
						return ret;
					}
				}
			}
		}
		return new int[0];
	}
	public void update(int[] tab) {
		for (int i = 0; i < vEndpoint.size(); i++) {
			if (vEndpoint.get(i) == tab[1] || uEndpoint.get(i) == tab[1]) {
				vEndpoint.remove(i);
				uEndpoint.remove(i);
				addMatch(tab[0], tab[1]);
				addMatch(tab[2], tab[3]);
				break;
			}
		}
		return;
	}

	/**
	 * Construit un couplage sans chemin augmentant de taille 4 et renvoie sa taille
	 */
	public int noAugment4() {

		int taille = uEndpoint.size();
		taille += buildMatching();
		while (true) {
			int ted[] = augmentant4();
			if (ted.length != 4) {
				break;
			}
			update(ted);
			taille++;
		}
		return taille;
	}


	
    public static int testImplementationMatching() {
        int score = 0;
        int nTests = 18;

        System.out.println("Testing the implementation ("+nTests+" tests):");

        GraphMatching g1 = new GraphMatching(5);
		g1.addEdge(0,1);
		if (g1.isEdge(0,1)){
			System.out.println("\t- test 1: pass");
			score++;
		}
		else{
			System.out.println("\t- test 1: fail");
			return score;
		}
		
		if (g1.isEdge(1,0)){
			System.out.println("\t- test 2: pass");
			score++;
		}
		else{
			System.out.println("\t- test 2: fail");
			return score;
		}
		
		if (g1.isEdge(2,3) == false){
			System.out.println("\t- test 3: pass");
			score++;
		}
		else{
			System.out.println("\t- test 3: fail");
			return score;
		}
		
		g1.uEndpoint.add(0);
		g1.vEndpoint.add(1);
		if (g1.isMatched(1) == 0){
			System.out.println("\t- test 4: pass");
			score++;
		}
		else{
			System.out.println("\t- test 4: fail");
			return score;
		}
		
		if (g1.isMatched(0) == 1){
			System.out.println("\t- test 5: pass");
			score++;
		}
		else{
			System.out.println("\t- test 5: fail");
			return score;
		}
		
		if (g1.isMatched(2) == -1){
			System.out.println("\t- test 6: pass");
			score++;
		}
		else{
			System.out.println("\t- test 6: fail");
			return score;
		}
		
		GraphMatching g2 = new GraphMatching(6);
        g2.addEdge(0, 1);
        g2.addEdge(0, 2);
        g2.addEdge(1, 3);
        g2.addEdge(1, 4);
		if (g2.addMatch(0,1) && (g2.uEndpoint.size()==1)){
			System.out.println("\t- test 7: pass");
			score++;
		}
		else{
			System.out.println("\t- test 7: fail");
			return score;
		}
		
		if (!g2.addMatch(3,4)){
			System.out.println("\t- test 8: pass");
			score++;
		}
		else{
			System.out.println("\t- test 8: fail");
			return score;
		}
		
		if (!g2.addMatch(0,1)){
			System.out.println("\t- test 9: pass");
			score++;
		}
		else{
			System.out.println("\t- test 9: fail");
			return score;
		}
		
		if (!g2.addMatch(0,2)){
			System.out.println("\t- test 10: pass");
			score++;
		}
		else{
			System.out.println("\t- test 10: fail");
			return score;
		}
		
		GraphMatching g3 = new GraphMatching(6);
        g3.addEdge(0, 1);
        g3.addEdge(0, 2);
        g3.addEdge(1, 3);
        g3.addEdge(1, 4);
        g3.addEdge(2, 5);
		g3.addMatch(0, 1);
		
		if (!g3.isMaximalMatching()){
			System.out.println("\t- test 11: pass");
			score++;
		}
		else{
			System.out.println("\t- test 11: fail");
			return score;
		}
		
		g3.addMatch(2, 5);
		if (g3.isMaximalMatching()){
			System.out.println("\t- test 12: pass");
			score++;
		}
		else{
			System.out.println("\t- test 12: fail");
			return score;
		}
		
		g3.uEndpoint.add(0);
		g3.vEndpoint.add(2);
		if (!g3.isMaximalMatching()){
			System.out.println("\t- test 13: pass");
			score++;
		}
		else{
			System.out.println("\t- test 13: fail");
			return score;
		}
		
		GraphMatching g4 = new GraphMatching(6);
        g4.addEdge(0, 1);
        g4.addEdge(0, 2);
        g4.addEdge(1, 3);
        g4.addEdge(1, 4);
        g4.addEdge(2, 5);
		
		if (g4.buildMatching() == 2){
			System.out.println("\t- test 14: pass");
			score++;
		}
		else{
			System.out.println("\t- test 14: fail");
			return score;
		}
		
		GraphMatching g5 = new GraphMatching(4);
        g5.addEdge(0, 1);
        g5.addEdge(1, 2);
        g5.addEdge(2, 3);
		g5.addMatch(1, 2);
		int[] tab5 = g5.augmentant4();
		int[] t1 = {0,1,2,3};
		int[] t2 = {3,2,1,0};
		
		if (Arrays.equals(tab5,t1) || Arrays.equals(tab5,t2)){
			System.out.println("\t- test 15: pass");
			score++;
		}
		else{
			System.out.println("\t- test 15: fail");
			return score;
		}
		
		GraphMatching g7 = new GraphMatching(4);
        g7.addEdge(0, 1);
        g7.addEdge(1, 2);
        g7.addEdge(0, 2);
		g7.addMatch(1, 2);
		int[] tab7 = g7.augmentant4();
		
		if (tab7.length == 0){
			System.out.println("\t- test 16: pass");
			score++;
		}
		else{
			System.out.println("\t- test 16: fail");
			return score;
		}
		
		g5.update(t1);
		
		if (g5.sizeMatching() == 2 && g5.isMaximalMatching()){
			System.out.println("\t- test 17: pass");
			score++;
		}
		else{
			System.out.println("\t- test 17: fail");
			return score;
		}
		
		GraphMatching g6 = new GraphMatching(12);
        g6.addEdge(0, 1);
        g6.addEdge(1, 2);
        g6.addEdge(2, 3);
        g6.addEdge(4, 5);
        g6.addEdge(5, 6);
        g6.addEdge(6, 7);
        g6.addEdge(8, 9);
        g6.addEdge(9, 10);
        g6.addEdge(10, 11);
		g6.addMatch(1, 2);
		g6.addMatch(5, 6);
		g6.addMatch(9, 10);
		
		if (g6.noAugment4() == 6){
			System.out.println("\t- test 18: pass");
			score++;
		}
		else{
			System.out.println("\t- test 18: fail");
			return score;
		}
		
		return score;
	}


    public static void main(String[] args) {
		int score = testImplementationMatching();
        System.out.println("Score " + score + "/18");
		GraphMatching g2 = new GraphMatching(6);
        g2.addEdge(0, 1);
        g2.addEdge(0, 2);
        g2.addEdge(1, 3);
        g2.addEdge(1, 4);
		System.out.println(g2.addMatch(0,1));
		
    }
}
