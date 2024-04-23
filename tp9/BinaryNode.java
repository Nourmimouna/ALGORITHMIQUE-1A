import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BinaryNode implements Comparable<BinaryNode>{
	private char letter;
	private int weight;
	private BinaryNode leftSon = null;
	private BinaryNode rightSon = null;
	
    /** 
     * Constructeur d'un nœud avec clé donnée
     */
    public BinaryNode(char letter, int weight) {
		this.letter = letter;
		this.weight = weight;
    }
	
    /**
     * Getters et Setters
     */
	public char getLetter(){
		return this.letter;
	}
	
	public void setLetter(char letter){
		this.letter = letter;
	}

	public int getWeight(){
	
		return this.weight;
		
	
	}
	
	public void setWeight(int weight){
		this.weight = weight;
	}
	
	public BinaryNode getLeftSon(){
		return this.leftSon;
	}
	
	public void setLeftSon(BinaryNode left){
		this.leftSon = left;
	}
	
	public BinaryNode getRightSon(){
		return this.rightSon;
	}
	
	public void setRightSon(BinaryNode right){
		this.rightSon = right;
	}
    /**
     * Affiche le nœud et ses fils
     * La méthode doit renvoyer "(a 4) ((b 1) (c 1))" pour le nœud
     * de l'exemple suivant :
     * 
     *             ' ' 6
     *            /     \
     *       'a' 4    ' ' 2
     *               /     \
     *           'b' 1    'c' 1
     */
	
    @Override
    public String toString() {
        if (this.isLeaf()){
			return ""+this.letter+" "+this.weight;
		}
		if (!this.isHufmal()){
			return "not an Hufman tree";
		}
        return "(" + this.leftSon + ") (" + this.rightSon + ")";
    }
	
    /**
     * Affiche un tableau d'entier (sert pour les tests)
     */
	
    public static String printTab(int[] tab) {
		String s = "{";
		for (int i = 0; i < tab.length; i++){
			if (i > 0){
				s = s+",";
			}
			s=s+tab[i];
		}
		return s+"}";
    }
	
    /**
     * Constructeur d'un nœud en fusionnant deux noeuds déjà existant
     */
    public BinaryNode(BinaryNode left, BinaryNode right) {
		if ( right != null && left != null){
		weight= left.getWeight()+right.getWeight(); 
		letter= ' '; 
		this.setLeftSon(left); 
		this.setRightSon(right); 
		}
		if ( right != null && left == null){
			weight= right.getWeight(); 
			letter= ' '; 
			this.setLeftSon(null); 
			this.setRightSon(right); 
		}
		if ( right == null && left != null){
			weight= left.getWeight(); 
			letter= ' '; 
			this.setLeftSon(left); 
			this.setRightSon(null); 
		}
    }

    /**
     * Compare deux arbres, en comparant juste les poids deux noeuds parents.
	 * Retourne un entier négatif (si son poids est inférieur à celui de b),
	 * zéro (si ils ont le même poids) et un entier positif sinon
     */
	 @Override
    public int compareTo(BinaryNode b) {
		if (this.getWeight() == b.getWeight()){
			return 0; 
		}
		if (this.getWeight() < b.getWeight()){
			return -1; 
		}
		return 1; 
        
    }

    /**
     * Teste si noeud code une feuille de Hufman
     */
    public boolean isLeaf() {
		if (this.getLeftSon()!=null || this.getRightSon()!=null ){
			return false ; 
		}
        if (this.getLeftSon()==null && this.getRightSon()==null ){
			if ( this.getWeight() >=1){

				return  ( this.getLetter() <= 'z' && this.getLetter() >= 'a' )  ; 
    		}
		}
		return false ; }

    /**
     * Teste si noeud code un arbre de Hufman
     */
    public boolean isHufmal() {
		if( isLeaf()){
			return true ; 
		}
		boolean a , b; 
		a = b = false ;  
		if( getLeftSon() != null){
			 	a= getLeftSon().isHufmal(); 

		}
		
		if( getRightSon() != null){
			b =getRightSon().isHufmal(); 
		}

		boolean c = false ; 
		if (getLeftSon() != null && getRightSon() != null){
			if (getWeight()== getRightSon().getWeight()+getLeftSon().getWeight() ){
				c=true ; 
			}
		}
		else if(getLeftSon() == null && getRightSon() != null){
			if (getWeight()== getRightSon().getWeight()){
				c=true ; 
			}
			}

		else if(getLeftSon() != null && getRightSon() == null){
			if (getWeight()== getLeftSon().getWeight()){
				c=true ; 
			}
			}
		return a && b && c ; 
      
    }

    /**
     * Transforme une liste d'arbres de Hufman en un arbre de Hufman en suivant
	 * l'algorithme du code de Hufman
     */
    public static BinaryNode mergeTrees(PriorityQueue<BinaryNode> treeQueue) {
		while( treeQueue.size() != 1){
			BinaryNode b1 = treeQueue.poll();
			BinaryNode b2 = treeQueue.poll();
			BinaryNode n = new BinaryNode(b1,b2); 
			treeQueue.add(n); 

		}
		return treeQueue.poll(); 
		
    }

    /**
     * Compte dans une chaîne de caractère le nombre d'occurences de chaque lettre
	 * de l'alphabet et renvoie ça dans un tableau de 26 cases
     */
    public static int[] letterCount(String s) {
		int[] tab = new int[26];
		s = s.toLowerCase(); // Convertit la chaîne en minuscules pour normaliser
	
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (Character.isLetter(c)) { // Vérifie si le caractère est une lettre
				int index = c - 'a'; // Calcul de l'index dans le tableau
				tab[index]++; // Incrémente le compteur
			}
		}
		return tab;
	}
	

    /**
     * A partir d'un tableau de 26 entiers, construit l'encodage de Hufman correspondant
     */
    public static BinaryNode buildTree(int[] tab) {
		PriorityQueue<BinaryNode> treeQueue = new PriorityQueue<>();
        // Création des nœuds pour chaque caractère avec leur fréquence
        for (int i = 0; i < 26; i++) {
            if (tab[i] > 0) { // Seulement si la fréquence est supérieure à zéro
                char character = (char) ('a' + i); // Convertit l'index en caractère
                treeQueue.offer(new BinaryNode( character,tab[i]));
            }
        }

        // Construction de l'arbre de Huffman
        while (treeQueue.size() > 1) {
            BinaryNode left = treeQueue.poll();
            BinaryNode right = treeQueue.poll();

            BinaryNode parent = new BinaryNode('\0', left.weight+ right.weight);
            parent.setLeftSon(left);
            parent.setRightSon(right);

            treeQueue.offer(parent);
        }

        return treeQueue.poll(); // Racine de l'arbre de Huffman
    }
    

    /**
     * Constructeur qui produit l'arbre de Hufman à partir d'une chaîne de caractère
     */
    public BinaryNode(String input) {
		int[] frequencies = new int[26]; // Tableau pour stocker les fréquences des lettres de l'alphabet
		// Compter les fréquences des lettres dans la chaîne d'entrée
		for (char c : input.toCharArray()) {
			if (Character.isLetter(c)) { // Vérifier si le caractère est une lettre
				c = Character.toLowerCase(c); // Convertir en minuscule pour la normalisation
				int index = c - 'a'; // Calculer l'index dans le tableau des fréquences
				frequencies[index]++; // Incrémenter la fréquence de la lettre
			}
		}
		// Construire l'arbre de Huffman à partir des fréquences
		PriorityQueue<BinaryNode> treeQueue = new PriorityQueue<>();
		for (int i = 0; i < 26; i++) {
			if (frequencies[i] > 0) { // Si la fréquence est supérieure à zéro
				char character = (char) ('a' + i); // Convertir l'index en caractère
				treeQueue.offer(new BinaryNode( character, frequencies[i])); // Ajouter le nœud à la file de priorité
			}
		}
		// Construire l'arbre de Huffman à partir de la file de priorité
		while (treeQueue.size() > 1) {
			BinaryNode left = treeQueue.poll();
			BinaryNode right = treeQueue.poll();
			// Créer un nouveau nœud avec les deux nœuds extraits comme enfants
			BinaryNode parent = new BinaryNode( '\0', left.getWeight() + right.getWeight()); // Le caractère n'a pas d'importance pour les nœuds internes
			parent.setLeftSon(left);
            parent.setRightSon(right);
			treeQueue.offer(parent); // Ajouter le nouveau nœud à la file de priorité
		}
		// La racine de l'arbre de Huffman est maintenant dans la file de priorité
		BinaryNode root = treeQueue.poll();
		// Copier les propriétés de la racine de l'arbre dans l'instance actuelle
		this.setWeight(root.getWeight());
		this.setLetter(root.getLetter());
		this.setLeftSon(root.getLeftSon());
		this.setRightSon(root.getRightSon());
	}
	
    /**
     * Renvoie le poids de l'arbre de Hufman (pour chaque feuille, on ajoute
	 * la profondeur de cette feuille fois son poids). On vous conseille d'utiliser
	 * une fonction intermédiaire.
     */	public int weightTree() {
    return weightTreeHelper(this, 0); // Appel de la fonction intermédiaire avec la racine de l'arbre et la profondeur initiale 0
	}

	private int weightTreeHelper(BinaryNode node, int depth) {
		if (node == null) {
			return 0; // Retourne 0 si le nœud est vide
		}
		if (node.getLeftSon() == null && node.getRightSon() == null) {
			return node.getWeight() * depth; // Retourne le poids de la feuille (fréquence * profondeur)
		}
		// Appel récursif pour le sous-arbre gauche et droit en augmentant la profondeur
		return weightTreeHelper(node.getLeftSon(), depth + 1) + weightTreeHelper(node.getRightSon(), depth + 1);
	}

	
    public static int testImplementation() {
        int score = 0;
        int nTests = 13;

        System.out.println("Testing the implementation:");

        BinaryNode b1 = new BinaryNode('a', 4);
		if (b1.isLeaf()){
			System.out.println("\t- test 1: pass");
			score++;
		}
		else{
			System.out.println("\t- test 1: fail");
			return  score;
		}
		
        BinaryNode b2 = new BinaryNode('D', 12);
		if (!b2.isLeaf()){
			System.out.println("\t- test 2: pass");
			score++;
		}
		else{
			System.out.println("\t- test 2: fail");
			return  score;
		}
		
        BinaryNode b3 = new BinaryNode('b', 0);
		if (!b3.isLeaf()){
			System.out.println("\t- test 3: pass");
			score++;
		}
		else{
			System.out.println("\t- test 3: fail");
			return  score;
		}
		
		if (b1.isHufmal()){
			System.out.println("\t- test 4: pass");
			score++;
		}
		else{
			System.out.println("\t- test 4: fail");
			return  score;
		}
		
		BinaryNode t1 = new BinaryNode(b1, new BinaryNode(new BinaryNode('b',1), new BinaryNode('c', 1)));
		if (t1.isHufmal()){
			System.out.println("\t- test 5: pass");
			score++;
		}
		else{
			System.out.println("\t- test 5: fail");
			return  score;
		}
		
		BinaryNode t2 = new BinaryNode(t1, null);
		if (!t2.isHufmal()){
			System.out.println("\t- test 6: pass");
			score++;
		}
		else{
			System.out.println("\t- test 6: fail");
			return  score;
		}
		
		PriorityQueue<BinaryNode> p1 = new PriorityQueue<>();
		p1.offer(t1);
		try{
			if (mergeTrees(p1).toString().equals(t1.toString())){
				System.out.println("\t- test 7: pass");
				score++;
			}
			else{
				System.out.println("\t- test 7: fail");
			return  score;
			}
		}
		catch (Exception e){
			System.out.println("\t- test 7: fail with an exception");
			return  score;
		}
		
		String s;
		try{
			PriorityQueue<BinaryNode> p2 = new PriorityQueue<>();
			p2.offer(new BinaryNode('b',1));
			p2.offer(new BinaryNode('a',4));
			p2.offer(new BinaryNode('c',1));
			s = mergeTrees(p2).toString();
			if (s.equals(t1.toString()) || s.equals("(a 4) ((c 1) (b 1))") || s.equals("((c 1) (b 1)) (a 4)") || s.equals("((b 1) (c 1)) (a 4)")){
				System.out.println("\t- test 8: pass");
				score++;
			}
			else{
				System.out.println("\t- test 8: fail");
				return  score;
			}
		}
		catch (Exception e){
			System.out.println("\t- test 8: fail with an exception");
			return  score;
		}
		
		int[] tab1 = letterCount("abaaca");
		
		if (printTab(tab1).equals("{4,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}")){
			System.out.println("\t- test 9: pass");
			score++;
		}
		else{
			System.out.println("\t- test 9: fail");
			return  score;
		}
		
		int[] tab2 = letterCount("!;:azertyuIOPQS)b56dfghjklmwxcv,n ");
		if (printTab(tab2).equals("{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}")){
			System.out.println("\t- test 10: pass");
			score++;
		}
		else{
			System.out.println("\t- test 10: fail");
			return  score;
		}
		
		try{
			BinaryNode b4 = new BinaryNode("abaaca");
			s = b4.toString();
			if (s.equals(t1.toString()) || s.equals("(a 4) ((c 1) (b 1))") || s.equals("((c 1) (b 1)) (a 4)") || s.equals("((b 1) (c 1)) (a 4)")){
				System.out.println("\t- test 11: pass");
				score++;
			}
			else{
				System.out.println("\t- test 11: fail");
				return  score;
			}
		}
		catch (Exception e){
			System.out.println("\t- test 11: fail with an exception");
		}
		
		try{
			BinaryNode b4 = new BinaryNode("abaaca");
			if (b4.weightTree() == 8){
				System.out.println("\t- test 12: pass");
				score++;
			}
			else{
				System.out.println("\t- test 12: fail");
				return  score;
			}
		}
		catch (Exception e){
			System.out.println("\t- test 12: fail with an exception");
			return  score;
		}
		
		try{
			BinaryNode b5 = new BinaryNode("Ceci est la grande phrase finale de test de cet exercice. Quel est donc son score ?");
			if (b5.weightTree() == 242){
				System.out.println("\t- test 13: pass");
				score++;
			}
			else{
				System.out.println("\t- test 13: fail");
				return  score;
			}
		}
		catch (Exception e){
			System.out.println("\t- test 13: fail with an exception");
			return  score;
		}
	return score;		
	}
	
    public static void main(String[] args) {
        // Score final
        System.out.println("Test: score " + testImplementation() + "/" + 13);
		
		int[] tab1 = letterCount("abaaca");
		System.out.println(printTab(tab1));
		
    }
	
}
