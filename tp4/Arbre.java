/**
 * Implementation des noeuds d'abres binaires avec valeurs entières
 */


public class Arbre {
	private Integer val; // Valeur du noeud
	private Arbre left; // Fils gauche
	private Arbre right; // Fils droit
	
    /** 
     * Constructeur d'un nœud avec clé donnée
     */
    public Arbre(Integer val) {
		this.val = val;
		this.left = null;
		this.right = null;
    }
	
    /**
     * Constructeur d'un nœud avec clé et fils indiqués en argument
     */
    public Arbre(Integer val, Arbre lson, Arbre rson) {
		this.val = val;
		this.left = lson;
		this.right = rson;
    }

	/* Accesseurs (getters) pour tous les attributs */

	public Integer getVal() {
		return this.val;
	}

	public Arbre getLeft() {
		return this.left;
	}

	public Arbre getRight() {
		return this.right;
	}

	/* Mutateurs (setters) pour tous les attributs */

	public void setVal(Integer val) {
		this.val = val;
	}

	public void setLeft(Arbre left) {
		this.left = left;
	}

	public void setRight(Arbre right) {
		this.right = right;
	}


    /**
     * Teste si noeud est une feuille
     */
     public boolean isLeaf() {
        if( this.right==null && this.left==null){
            return true; 
        }
        return false;
    }

	/**
	 * Affiche le nœud et ses fils
	 * La méthode doit renvoyer "((1) 5 (6)) 8 ((9) 10 ())" pour le nœud 8
	 * de l'exemple suivant :
	 * 
	 *          8
	 *        /   \
	 *       5    10
	 *     /  \   /
	 *    1   6  9
	 */
    @Override

    public String toString() {
        String s =""; 
 
		if(this == null ){
			return ""; 
		}

        if(this.left != null){
            s+="("+this.left.toString()+") ";  

        }
		if(this.left == null && !this.isLeaf() ){
			s+= "() "; 
		} 
		s+=this.getVal(); 

        if(this.right != null){
            s+= " ("+this.right.toString()+")"; 
		}
		if(this.right == null && !this.isLeaf() ){
			s+= " ()"; 
		} 
		

        return s ; 
    }

	//ublic String infixe(Arbre t); 

    /**
     * Teste si le nœud est la racine d'un ABR ayant des valeurs entre
	 * min et max
	 * 
     */

    public boolean isIntBSTrec(Integer min, Integer max) {
	return 	 this.getVal()<max && this.getVal()>min; 
	}

    public boolean isIntBST(Integer min, Integer max) {
	
	boolean A; 
	boolean B; 
	boolean C; 

	C=A=B = true; 
	if (this.right != null){
		A=this.right.isIntBSTrec(min, max); 
	}
	if (this.left != null){
		A=this.left.isIntBSTrec(min, max); 
	}
	if(this.isLeaf()){
		C= this.getVal()<max && this.getVal()>min;  
	} 
	
		return A && B && C;
    }

    /**
     * Tests unitaires de la classe
     */

    /**
     * Construit le noeud ((1) 5 (6)) 8 ((9) 10 ())
     */ 
    public static Arbre buildArbre8() {
		Arbre one = new Arbre(1);
		Arbre six = new Arbre(6);
		Arbre nine = new Arbre(9);
		Arbre five = new Arbre(5, one, six);
		Arbre ten = new Arbre(10, nine, null);
		Arbre eight = new Arbre(8, five, ten);
		return eight;
    }

    public static void testIsLeaf() {
		System.out.println("Test isLeaf:");
		int score = 0;

		// Test 1
		Arbre n8 = buildArbre8();
		if (!n8.isLeaf()) {
			System.out.println("\t- test 1: pass");
			score ++;
		}
		else {
			System.out.println("\t- test 1: fail");
		}

		/* A COMPLETER: ajouter des tests */

		// Score final
		System.out.println("Test isLeaf: score " + score + "/1");
    }
    
    public static void testToString() {
		System.out.println("Test toString:");
		int score = 0;

		// Test 1
		Arbre n8 = buildArbre8();
		if (n8.toString().equals("((1) 5 (6)) 8 ((9) 10 ())")) {
			System.out.println("\t- test 1: pass");
			score ++;
		}
		else {
			System.out.println("\t- test 1: fail");
		}

		/* A COMPLETER: ajouter des tests */

		// Score final
		System.out.println("Test toString: score " + score + "/1");
    }
    
    public static void testIsIntBst() {
		System.out.println("Test isIntBST:");
		int score = 0;

		// Test 1
		Arbre n8 = buildArbre8();
		if (n8.isIntBST(0,20)) {
			System.out.println("\t- test 1: pass");
			score ++;
		}
		else {
			System.out.println("\t- test 1: fail");
		}

		/* A COMPLETER: ajouter des tests */

		// Score final
        System.out.println("Test isIntBST: score " + score + "/1");
    }
    
    public static void main(String[] args) {
		testIsLeaf();
		testToString();
		testIsIntBst();
		Arbre one = new Arbre(1);
		Arbre six = new Arbre(6);
		Arbre nine = new Arbre(9);
		Arbre five = new Arbre(5, one, six);
		Arbre ten = new Arbre(10,null, nine);
		Arbre eight = new Arbre(8, five, ten);
		System.out.println(eight.toString());
    }
}

