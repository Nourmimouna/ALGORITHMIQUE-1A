/**
 * Implementation des abres binaires avec valeurs entières
 */
import java.util.Stack;
import java.util.Deque; 
import java.util.ArrayDeque; 

public class ArbreBinaire {
   
   
    private Arbre root; 
    private Arbre left; 
    private Arbre right ; 
    /*
     *  Constructeur d'arbre vide (racine = null)
     */
    public ArbreBinaire() {	
        this.root= null; 
    }
    
    /** 
     * Constructeur avec un arbre
     */


    public ArbreBinaire(Arbre r) {
        this.root= r ;
        /*if(! isBST()){
            throw new IllegalArgumentException("non ");
        }
        */
    }


	public Arbre get_root(){
        return this.root; 
    }
    /**
     * Représentation en chaîne de l'arbre.
     * Retourne :
     * - "Arbre vide" si l'abre est vide.
     * - "Arbre [ contenu ]" sinon, où "contenu" est la représentation en chaîne du nœud racine.
     */
    @Override
    public String toString() {
        if ( this.isEmpty()){
            return "Arbre vide"; 
        }
        return " Arbre ["+this.root.getVal()+"]";
    }

    /**
     * Test si l'arbre est vide 
     */
    public boolean isEmpty() {
        if(this.root==null){
            return true;
        }
        return false;
    }
    
    /**
     * */
         public int rec_height(Arbre t) {
        if (t.isLeaf()){
            return 0;
        }
        int x1 =1; 
        int x2=1;
        if( t.getLeft()!= null){
            x1+= rec_height(t.getLeft()); 
        }

        if( t.getRight() != null){
            x2+= rec_height(t.getRight()); 
        }
        if ( x1 < x2){
            return x2; 
                    } 
        else{
            return x1; 
        }

    }

    public int height(){
        return rec_height(this.get_root());
    }

    /**
     * Test si l'are est un ABR
     */


   
    public boolean isBSTRec(Arbre t) {
    if (t == null){
        return true; 
    }
    int min= -Integer.MAX_VALUE;
    int max= Integer.MAX_VALUE;

    Boolean A ,B; 

    A=B= false ; 
    
    if(t.getLeft() != null && t.getLeft().getVal()< t.getVal() ){
       
        A= t.getLeft().isIntBST( min, t.getVal()); 

    }

    if ( t.getRight() != null && t.getRight().getVal() > t.getVal()){
        
        B= t.getRight().isIntBST( t.getVal(), max); 
    }
    return A && B ;      
    }
    

  


        
    
    public boolean isBST() {
        Arbre r = this.get_root();
     return isBSTRec(r); 
    }



    /**
     * Calcule la cle maximale si arbre est ABR
     */
    public int getMax() {
        int max = this.get_root().getVal(); 

        if (this.get_root().getLeft() != null){
            if(this.get_root().getLeft().getVal() > max){
                max =this.get_root().getLeft().getVal() ; 
            }
        }
        
        if (this.get_root().getRight()!= null){
            if(this.get_root().getRight().getVal() > max){
                max =this.get_root().getRight().getVal() ; 
            }
        }
        return max; 

    }

    /**
     * Calcule le nombre de cles plus petites que x
     * -1 si arbre vide
     * 
     */

    public int less_rec( Arbre t , Integer x ){
        int c =0; 
        if (t.getLeft() != null){
            c+= less_rec(t.getLeft(), x );
        }
        if(t.getVal() < x){
            c++; 
        }
        if(t.getRight() != null){
            c+=less_rec(t.getRight(), x); 
        }
        return c; 
        }



    
    public int less(Integer x) {
        if (this.isEmpty()){
            return -1; 
        }
        
        Arbre r = this.root; 
        return less_rec(r, x);  
        }






/*
    */
    public boolean contains(Integer x , Arbre r ) {
    
        boolean A = false; 
        boolean B= false ; 
        boolean C = false ; 
        
        if (r.getLeft() != null){
           A=contains(x,r.getLeft());
        }

        B= (r.getVal() == x ); 


        if(r.getRight() != null){
         C=contains(x,r.getRight()); 
        }
        
        return A || B || C ; 
    }

    public boolean contains(Integer x) {
        Arbre r = this.get_root(); 
        return contains(x,r);
    }






    public static ArbreBinaire buildTree1() {
        Arbre one = new Arbre(1);
        Arbre three = new Arbre(3);
        Arbre two = new Arbre(2, one, three);
        return new ArbreBinaire(two);
    }

    public static ArbreBinaire buildTree2() {
        return new ArbreBinaire(new Arbre(1));
    }

    public static ArbreBinaire buildTree3() {
        Arbre one = new Arbre(1);
        Arbre three = new Arbre(3, one, null);
        Arbre eight = new Arbre(8, three, null);
        Arbre ten = new Arbre(10, eight, null);
        return new ArbreBinaire(ten);
    }

    /**
     * Tests unitaires de la classe
     */
    public static void testIsEmpty() {
        System.out.println("Test isEmpty:");
        int score = 0;
        int nTests = 2;

        // Test 1
        ArbreBinaire t8 = new ArbreBinaire(Arbre.buildArbre8());
        if (!t8.isEmpty()) {
            System.out.println("\t- test 1: pass");
            score++;
        } else {
            System.out.println("\t- test 1: fail");
        }

        // Test 2
        ArbreBinaire empty = new ArbreBinaire();
        if (empty.isEmpty()) {
            System.out.println("\t- test 2: pass");
            score++;
        } else {
            System.out.println("\t- test 2: fail");
        }	    

	    // Score final
    	System.out.println("Test isEmpty: score " + score + "/" + nTests);
    }
    
    public static void testHeight() {
        System.out.println("Test height:");
        int score = 0;
        int nTests = 4;

	    // Test 1 
        ArbreBinaire t8 = new ArbreBinaire(Arbre.buildArbre8());
    	if (t8.height() == 2) {
	        System.out.println("\t- test 1: pass");
    	    score++;
        } else {
            System.out.println("\t- test 1: fail");
        }

	    // Test 2 
        ArbreBinaire t1 = buildTree1();
    	if (t1.height() == 1) {
	        System.out.println("\t- test 2: pass");
    	    score++;
        } else {
            System.out.println("\t- test 2: fail");
        }

	    // Test 3 
        ArbreBinaire t2 = buildTree2();
    	if (t2.height() == 0) {
	        System.out.println("\t- test 3: pass");
    	    score++;
        } else {
            System.out.println("\t- test 3: fail");
        }

	    // Test 4
        ArbreBinaire t3 = buildTree3();
    	if (t3.height() == 3) {
	        System.out.println("\t- test 4: pass");
    	    score++;
        } else {
            System.out.println("\t- test 4: fail");
        }

	    // Score final
    	System.out.println("Test height: score " + score + "/" + nTests);
    }
    
    public static void testIsBST() {
        System.out.println("Test isBST:");
        int score = 0;
        int nTests = 2;

	    // Test 1
        ArbreBinaire t8 = new ArbreBinaire(Arbre.buildArbre8());
        if (t8.isBST()) {
            System.out.println("\t- test 1: pass");
            score++;
        } else {
            System.out.println("\t- test 1: fail");
        }

        // Test 2
        Arbre two = new Arbre(2);
        Arbre one = new Arbre(1, two, null);
        try {
            new ArbreBinaire(one);
            System.out.println("\t- test 2: fail");
        } catch(Exception e) {
            System.out.println("\t- test 2: pass");
            score++;
        }

	    // Score final
        System.out.println("Test isBST: score " + score + "/" + nTests);
    }
    
    public static void testGetMax() {
        System.out.println("Test getMax:");
        int score = 0;
        int nTests = 4;

        // Test 1
        ArbreBinaire t8 = new ArbreBinaire(Arbre.buildArbre8());
        if (t8.getMax() == 10) {
            System.out.println("\t- test 1: pass"); 
            score++;
        } else {
            System.out.println("\t- test 1: fail");
        }

	    // Test 2 
        ArbreBinaire t1 = buildTree1();
    	if (t1.getMax() == 3) {
	        System.out.println("\t- test 2: pass");
    	    score++;
        } else {
            System.out.println("\t- test 2: fail");
        }

	    // Test 3 
        ArbreBinaire t2 = buildTree2();
    	if (t2.getMax() == 1) {
	        System.out.println("\t- test 3: pass");
    	    score++;
        } else {
            System.out.println("\t- test 3: fail");
        }

	    // Test 4
        ArbreBinaire t3 = buildTree3();
    	if (t3.getMax() == 10) {
	        System.out.println("\t- test 4: pass");
    	    score++;
        } else {
            System.out.println("\t- test 4: fail");
        }

        // Score final
        System.out.println("Test getMax: score " + score + "/" + nTests);
    }

    public static void testLess() {
        System.out.println("Test less:");
        int score = 0;
        int nTests = 4;
        
        // Test 1
        ArbreBinaire t8 = new ArbreBinaire(Arbre.buildArbre8());
        if (t8.less(8) == 3) {
            System.out.println("\t- test 1: pass");
            score ++;
        } else {
            System.out.println("\t- test 1: fail");
        }

	    // Test 2 
    	if (t8.less(5) == 1) {
	        System.out.println("\t- test 2: pass");
    	    score++;
        } else {
            System.out.println("\t- test 2: fail");
        }

        // Test 3
        ArbreBinaire t1 = buildTree1();
    	if (t1.less(2) == 1) {
	        System.out.println("\t- test 3: pass");
    	    score++;
        } else {
            System.out.println("\t- test 3: fail");
        }

	    // Test 4
    	if (t1.less(3) == 2) {
	        System.out.println("\t- test 4: pass");
    	    score++;
        } else {
            System.out.println("\t- test 4: fail");
        }

        // Score final
        System.out.println("Test less: score " + score + "/" + nTests);
    }

    public static void testContains() {
        System.out.println("Test contains:");
        int score = 0;
        int nTests = 4;
        
        // Test 1
        ArbreBinaire t8 = new ArbreBinaire(Arbre.buildArbre8());
        if (t8.contains(8)) {
            System.out.println("\t- test 1: pass");
            score ++;
        } else {
            System.out.println("\t- test 1: fail");
        }

	    // Test 2 
    	if (t8.contains(6)) {
	        System.out.println("\t- test 2: pass");
    	    score++;
        } else {
            System.out.println("\t- test 2: fail");
        }

        // Test 3
    	if (t8.contains(9)) {
	        System.out.println("\t- test 3: pass");
    	    score++;
        } else {
            System.out.println("\t- test 3: fail");
        }

	    // Test 4
    	if (!t8.contains(77)) {
	        System.out.println("\t- test 4: pass");
    	    score++;
        } else {
            System.out.println("\t- test 4: fail");
        }

        // Score final
        System.out.println("Test contains: score " + score + "/" + nTests);
    }

    public static void main(String[] args) {
       
        testIsEmpty();
        testHeight();
        testIsBST();
        testGetMax();
        testLess();
        testContains();
        Arbre one = new Arbre(1);
		Arbre six = new Arbre(6);
		Arbre nine = new Arbre(3);
		Arbre five = new Arbre(5, one, six);
		Arbre ten = new Arbre(0, nine, null);
		Arbre eight = new Arbre(8, five, ten);
		ArbreBinaire A = new ArbreBinaire(eight);

        ArbreBinaire t8 = new ArbreBinaire(Arbre.buildArbre8());
        System.out.println(t8.less(8)); 
        System.out.println(A.isBST());


    }

}

