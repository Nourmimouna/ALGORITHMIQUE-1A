/**
 * TP5: Arbres
 */
import java.lang.*;


public class Arbre{
    private int val; // Valeur du nœud
    private Arbre left; // Enfant gauche
    private Arbre right; // Enfant droit

    /**
     * Constructeur 1
     * Initialise la valeur du nœud à val et les enfants gauche et droit à null
     */
    public Arbre(int val) {
        this.val= val; 
        this.left=null; 
        this.right=null; 
    }

    /**
     * Constructeur 2
     * Initialise la valeur du nœud et les enfants du nœud
     */
    public Arbre(int val, Arbre left, Arbre right) {
        this.val=val; 
        this.left=left; 
        this.right=right; 
    }

    /**
     * Assigne la valeur du nœud
     */
    public void setVal(int val) {
        this.val = val;
    }

    /**
     * Assigne l'enfant gauche
     */
    public void setLeft(Arbre arbre) {
        this.left = arbre;
    }

    /**
     * Assigne l'enfant droit
     */
    public void setRight(Arbre arbre) {
        this.right = arbre;
    }

    /**
     * Renvoie la valeur du nœud
     */
    public int getVal() {
        return this.val;
    }

    /**
     * Renvoie le enfant gauche
     */
    public Arbre getLeft() {
        return this.left;
    }

    /**
     * Renvoie le enfant droit
     */
    public Arbre getRight() {
        return this.right;
    }

    /**
     * Vérifie si l'arbre est une feuille
     * i.e. il n'a pas d'enfants.
     */
    public boolean isLeaf() {
        if( this.right==null && this.left==null){
            return true; 
        }
        return false;
    }

    /**
     * Compare deux noeuds.
     */
    public boolean equals(Arbre n) {
        
        Arbre i = this;
        Arbre j =n ; 
        boolean B=false; 
        boolean A=false; 
        Arbre i_1=new Arbre(0); 
        Arbre i_2= new Arbre(0);
        Arbre j_1=new Arbre(0); 
        Arbre j_2= new Arbre(0);
        if( n == null){
            return false;
        }
        if(j.val == i.val){
            if( j.isLeaf() == j.isLeaf()){
                return true; 
            }
            else{
                i_1= i.left ; 
                i_2=i.right; 
                j_1=j.left ; 
                j_2=j.right;  
                A= i_1.equals(j_1); 
                B= i_2.equals(j_2);
                return A==B; 
            }
        }
            
        
        return false;
    }



    public void visite(){
        System.out.print(this.getVal()); 
        
    }
    /**
     * Parcours prefixe: méthode récursive
     */
    public void parcoursPrefixe() {
        this.visite(); 
        if(this.left != null){
            this.left.parcoursPrefixe(); 
        }
        if(this.right != null){
            this.right.parcoursPrefixe(); 
        }
    }
    /**
     * Parcours postfixe: méthode récursive
     */
    public void parcoursPostfixe() {
        
        if(this.left != null){
            this.left.parcoursPostfixe(); 
        }
        if(this.right != null){
            this.right.parcoursPostfixe(); 
        }
        this.visite(); 
    }  
    
    /**
     * Parcours infixe: méthode récursive
     */
    public void parcoursInfixe() {
        
        if(this.left != null){
            this.left.parcoursInfixe(); 
        }
        this.visite(); 
    
        if(this.right != null){
            this.right.parcoursInfixe(); 
        }
    }    


    /**
     * Renvoie le nombre de nœuds dans l’arbre
     */
    public int nombreNoeuds() {
        if (this.isLeaf()){
            return 1;
        }
        int x =1; 
        if( this.left != null){
            x+= this.left.nombreNoeuds(); 
        }

        if( this.right != null){
            x+= this.right.nombreNoeuds(); 
        }
        return x; 
    }

    /**
     * Renvoie la hauteur de l’arbre
     */
    public int hauteur() {
        if (this.isLeaf()){
            return 0;
        }
        int x1 =1; 
        int x2=1;
        if( this.left != null){
            x1+= this.left.hauteur(); 
        }

        if( this.right != null){
            x2+= this.right.hauteur(); 
        }
        if ( x1 < x2){
            return x2; 
                    } 
        else{
            return x1; 
        }

    }

    /**
     * Renvoie la représentation en chaine de caractères de l’arbre
     * La méthode doit renvoyer "4 2 3 8 7" pour l'arbre suivant:
     * 8
     * / \
     * 2 7
     * / \
     * 4 3
     */
    

    public String toString() {
        String s =""; 
     

        if(this.left != null){
            s+=this.left.toString()+" ";  
        }

           
        s+=this.getVal();  
    
        if(this.right != null){
            s+= " "+this.right.toString(); 
        }
        return s ; 
    }

    /**
     * Renvoie la somme des valeurs contenues dans les nœuds de l’arbre
     */
    public int somme() {
        int somme =0; 
        
        
        if(this.left != null){
            somme+= this.left.somme(); 
        }
        somme += this.getVal(); 

        if(this.right != null){
            somme+= this.right.somme(); 
        }
        return somme;
    }

    /**
     * Renvoie la somme des valeurs contenus dans les feuilles de l’arbre
     */
    public int sommeFeuilles() {

        int somme =0; 
        
        
        if(this.left != null){
            somme+= this.left.sommeFeuilles(); 
        }

        if(this.right != null){
            somme+= this.right.sommeFeuilles(); 
        }


        if (this.isLeaf()){
            somme += this.getVal(); 
        }
        return somme;
    }
    

    /**
     * Ajout: méthode récursive
     */
    public void ajoutVal(int val) {
        if (val > this.getVal()){
            if (this.right != null){
            this.right.ajoutVal(val); 
             }
             else{
                this.right = new Arbre(val , null , null); 
            }
        }
        if (val <= this.getVal()){
            
            if (this .left != null){ 
           (this.left).ajoutVal(val); 
            }
            else{
                this.left = new Arbre(val , null , null); 
            }
        }
    }
    /**
     * Constructeur à partir d'un tableau
     */
    public Arbre(int[] tab) {
        int n= tab.length; 
        int i=1; 
        this.val=tab[0]; 
        while (i < n){
         int val = tab[i]; 
         this.ajoutVal(val); 
         i++;
        }
    }
//non pas ca tres mal 
  
  

    public static void testArbre1() {
        Arbre d = new Arbre(1, null, null);
        Arbre b = new Arbre(2, d, null);
        Arbre c = new Arbre(7, null, null);
        Arbre arbre = new Arbre(5, b, c);
        Arbre d2 = new Arbre(1, null, null);
        Arbre b2 = new Arbre(2, d2, null);
        Arbre c2 = new Arbre(7, null, null);
        Arbre a2 = new Arbre(5, b2, c2);
        int score = 0;
        System.out.println("**** Test arbre1");
        if (arbre.nombreNoeuds() == 4) {
            System.out.println("\t nombreNoeuds: OK!");
            ++score;
        } else {
            System.out.println("\t nombreNoeuds: fail!");
        }
        if (arbre.hauteur() == 2) {
            System.out.println("\t hauteur: OK!");
            ++score;
        } else {
            System.out.println("\t hauteur: fail!");
        }
        if (arbre.toString().equals("1 2 5 7")) {
            System.out.println("\t toString: OK!");
            ++score;
        } else {
            System.out.println("\t toString: fail!");
        }
        if (arbre.somme() == 15) {
            System.out.println("\t somme: OK!");
            ++score;
        } else {
            System.out.println("\t somme: fail!");
        }
        if (arbre.sommeFeuilles() == 8) {
            System.out.println("\t sommeFeuilles: OK!");
            ++score;
        } else {
            System.out.println("\t sommeFeuilles: fail!");
        }
        if (arbre.equals(a2)) {
            System.out.println("\t equals: OK!");
            ++score;
        } else {
            System.out.println("\t equals: fail!");
        }
        System.out.println("**** Score: " + score + "/6");
    }

    public static void testArbre2() {
        Arbre c = new Arbre(6, null, null);
        Arbre b = new Arbre(5, c, null);
        Arbre arbre = new Arbre(3, b, null);
        Arbre c2 = new Arbre(6, null, null);
        Arbre b2 = new Arbre(5, c2, null);
        Arbre a2 = new Arbre(3, b2, null);
        int score = 0;
        System.out.println("**** Test arbre2");
        if (arbre.nombreNoeuds() == 3) {
            System.out.println("\t nombreNoeuds: OK!");
            ++score;
        } else {
            System.out.println("\t nombreNoeuds: fail!");
        }
        if (arbre.hauteur() == 2) {
            System.out.println("\t hauteur: OK!");
            ++score;
        } else {
            System.out.println("\t hauteur: fail!");
        }
        if (arbre.toString().equals("6 5 3")) {
            System.out.println("\t toString: OK!");
            ++score;
        } else {
            System.out.println("\t toString: fail!");
        }
        if (arbre.somme() == 14) {
            System.out.println("\t somme: OK!");
            ++score;
        } else {
            System.out.println("\t somme: fail!");
        }
        if (arbre.sommeFeuilles() == 6) {
            System.out.println("\t sommeFeuilles: OK!");
            ++score;
        } else {
            System.out.println("\t sommeFeuilles: fail!");
        }
        if (arbre.equals(a2)) {
            System.out.println("\t equals: OK!");
            ++score;
        } else {
            System.out.println("\t equals: fail!");
        }
        System.out.println("**** Score: " + score + "/6");
    }

    public static void testArbre3() {
        Arbre arbre = new Arbre(2);
        Arbre b = new Arbre(1);
        Arbre c = new Arbre(3);
        Arbre d = new Arbre(4);
        Arbre e = new Arbre(5);
        Arbre f = new Arbre(9);
        Arbre g = new Arbre(10);
        arbre.setLeft(b);
        arbre.setRight(c);
        b.setLeft(d);
        b.setRight(e);
        c.setLeft(f);
        c.setRight(g);
        Arbre a2 = new Arbre(2);
        Arbre b2 = new Arbre(1);
        Arbre c2 = new Arbre(3);
        Arbre d2 = new Arbre(4);
        Arbre e2 = new Arbre(5);
        Arbre f2 = new Arbre(9);
        Arbre g2 = new Arbre(10);
        a2.setLeft(b2);
        a2.setRight(c2);
        b2.setLeft(d2);
        b2.setRight(e2);
        c2.setLeft(f2);
        c2.setRight(g2);
        int score = 0;
        System.out.println("**** Test arbre3");
        if (arbre.nombreNoeuds() == 7) {
            System.out.println("\t nombreNoeuds: OK!");
            ++score;
        } else {
            System.out.println("\t nombreNoeuds: fail!");
        }
        if (arbre.toString().equals("4 1 5 2 9 3 10")) {
            System.out.println("\t toString: OK!");
            ++score;
        } else {
            System.out.println("\t toString: fail!");
        }
        if (arbre.hauteur() == 2) {
            System.out.println("\t hauteur: OK!");
            ++score;
        } else {
            System.out.println("\t hauteur: fail!");
        }
        if (arbre.somme() == 34) {
            System.out.println("\t somme: OK!");
            ++score;
        } else {
            System.out.println("\t somme: fail!");
        }
        if (arbre.sommeFeuilles() == 28) {
            System.out.println("\t sommeFeuilles: OK!");
            ++score;
        } else {
            System.out.println("\t sommeFeuilles: fail!");
        }
        if (arbre.equals(a2)) {
            System.out.println("\t equals: OK!");
            ++score;
        } else {
            System.out.println("\t equals: fail!");
        }
        System.out.println("**** Score: " + score + "/6");
    }

    public static void testArbre4() {
        Arbre arbre = new Arbre(2);
        Arbre a2 = new Arbre(2);
        int score = 0;
        System.out.println("**** Test arbre4");
        if (arbre.nombreNoeuds() == 1) {
            System.out.println("\t nombreNoeuds: OK!");
            ++score;
        } else {
            System.out.println("\t nombreNoeuds: fail!");
        }
        if (arbre.toString().equals("2")) {
            System.out.println("\t toString: OK!");
            ++score;
        } else {
            System.out.println("\t toString: fail!");
        }
        if (arbre.hauteur() == 0) {
            System.out.println("\t hauteur: OK!");
            ++score;
        } else {
            System.out.println("\t hauteur: fail!");
        }
        if (arbre.somme() == 2) {
            System.out.println("\t somme: OK!");
            ++score;
        } else {
            System.out.println("\t somme: fail!");
        }
        if (arbre.sommeFeuilles() == 2) {
            System.out.println("\t sommeFeuilles: OK!");
            ++score;
        } else {
            System.out.println("\t sommeFeuilles: fail!");
        }
        if (arbre.equals(a2)) {
            System.out.println("\t equals: OK!");
            ++score;
        } else {
            System.out.println("\t equals: fail!");
        }
        System.out.println("**** Score: " + score + "/6");
    }

    public static void testArbre5() {
        Arbre arbre = new Arbre(7);
        Arbre b = new Arbre(1);
        Arbre c = new Arbre(2);
        Arbre d = new Arbre(4);
        arbre.setLeft(b);
        b.setLeft(c);
        c.setRight(d);
        Arbre a2 = new Arbre(7);
        Arbre b2 = new Arbre(1);
        Arbre c2 = new Arbre(2);
        Arbre d2 = new Arbre(4);
        a2.setLeft(b2);
        b2.setLeft(c2);
        c2.setRight(d2);
        int score = 0;
        System.out.println("**** Test arbre5");
        if (arbre.nombreNoeuds() == 4) {
            System.out.println("\t nombreNoeuds: OK!");
            ++score;
        } else {
            System.out.println("\t nombreNoeuds: fail!");
        }
        if (arbre.toString().equals("2 4 1 7")) {
            System.out.println("\t toString: OK!");
            ++score;
        } else {
            System.out.println("\t toString: fail!");
        }
        if (arbre.hauteur() == 3) {
            System.out.println("\t hauteur: OK!");
            ++score;
        } else {
            System.out.println("\t hauteur: fail!");
        }
        if (arbre.somme() == 14) {
            System.out.println("\t somme: OK!");
            ++score;
        } else {
            System.out.println("\t somme: fail!");
        }
        if (arbre.sommeFeuilles() == 4) {
            System.out.println("\t sommeFeuilles: OK!");
            ++score;
        } else {
            System.out.println("\t sommeFeuilles: fail!");
        }
        if (arbre.equals(a2)) {
            System.out.println("\t equals: OK!");
            ++score;
        } else {
            System.out.println("\t equals: fail!");
        }
        System.out.println("**** Score: " + score + "/6");
    }

    public static void testAjout() {
        System.out.println("**** Test fonction ajout");
        Arbre arbre = new Arbre(4);
        int[] tab = { 2, 6, 1, 7, 3, 5 };
        for (int i = 0; i < tab.length; i++) {
            arbre.ajoutVal(tab[i]);
        }
        Arbre a = new Arbre(4);
        Arbre b = new Arbre(2);
        Arbre c = new Arbre(6);
        Arbre d = new Arbre(1);
        Arbre e = new Arbre(3);
        Arbre f = new Arbre(5);
        Arbre g = new Arbre(7);
        a.setLeft(b);
        a.setRight(c);
        b.setLeft(d);
        b.setRight(e);
        c.setLeft(f);
        c.setRight(g);

        if (a.equals(arbre)) {
            System.out.println("\t ajout: OK!");
        } else {
            System.out.println("\t ajout: fail!");
        }

    }

    public static void testDeRecherche() {
        System.out.println("**** Test isDeRecherche");
        int score = 0;
        Arbre arbre = new Arbre(4);
        Arbre b = new Arbre(2);
        Arbre c = new Arbre(6);
        Arbre d = new Arbre(1);
        Arbre e = new Arbre(3);
        Arbre f = new Arbre(5);
        Arbre g = new Arbre(7);
        arbre.setLeft(b);
        arbre.setRight(c);
        b.setLeft(d);
        b.setRight(e);
        c.setLeft(f);
        c.setRight(g);
        if (arbre.isDeRecherche()) {
            System.out.println("\t IsBinaire 1: OK!");
            ++score;
        } else {
            System.out.println("\t IsBinaire 1: fail!");
        }

        arbre = new Arbre(4);
        b = new Arbre(2);
        c = new Arbre(6);
        d = new Arbre(3);
        e = new Arbre(1);
        f = new Arbre(5);
        g = new Arbre(7);
        arbre.setLeft(b);
        arbre.setRight(c);
        b.setLeft(d);
        b.setRight(e);
        c.setLeft(f);
        c.setRight(g);
        if (!arbre.isDeRecherche()) {
            System.out.println("\t IsBinaire 2: OK!");
            ++score;
        } else {
            System.out.println("\t IsBinaire 2: fail!");
        }

        arbre = new Arbre(4);
        b = new Arbre(3);
        c = new Arbre(6);
        d = new Arbre(1);
        e = new Arbre(7);
        f = new Arbre(2);
        g = new Arbre(8);
        arbre.setLeft(b);
        arbre.setRight(c);
        b.setLeft(d);
        b.setRight(e);
        c.setLeft(f);
        c.setRight(g);
        if (!arbre.isDeRecherche()) {
            System.out.println("\t IsBinaire 3: OK!");
            ++score;
        } else {
            System.out.println("\t IsBinaire 3: fail!");
        }

        arbre = new Arbre(4);
        b = new Arbre(4);
        arbre.setRight(b);
        if (arbre.isDeRecherche()) {
            System.out.println("\t IsBinaire 4: OK!");
            ++score;
        } else {
            System.out.println("\t IsBinaire 4: fail!");
        }

        arbre = new Arbre(4);
        b = new Arbre(4);
        arbre.setLeft(b);
        if (!arbre.isDeRecherche()) {
            System.out.println("\t IsBinaire 5: OK!");
            ++score;
        } else {
            System.out.println("\t IsBinaire 5: fail!");
        }
        System.out.println("**** Score: " + score + "/5");
    }

    public static void testTableau() {
        System.out.println("**** Test constructeur Tableau");
        int[][] inputs = {
                { 4, 2, 6, 1, 7, 3, 5 },
                { 4, 4, 4 },
                { 1, 2, 3, 4, 5 },
                { 5, 4, 3, 2, 1 },
                { 2, 6, 9, 1, 5, 3, 4, 6, 0, 8, 7 } };
        String[] outputs = {
                "1 2 3 4 5 6 7",
                "4 4 4",
                "1 2 3 4 5",
                "1 2 3 4 5",
                "0 1 2 3 4 5 6 6 7 8 9" };

        int nbTests = inputs.length;
    
        int score = 0;
        for (int i = 0; i < nbTests; i++) {
            Arbre arbre = new Arbre(inputs[i]);
            String s = arbre.toString();
            if (s.equals(outputs[i])) {
                System.out.println("Test " + i + " passed!");
                score++;
            } else {
                System.out.println("Test " + i + " failed!");
            }
        }
        System.out.println("**** Final score: " + score + "/" + nbTests);
    }

    public static void main(String[] args) {
        testArbre1();
        testArbre2();
        testArbre3();
        testArbre4();
        testArbre5();
        testAjout();
        testDeRecherche();
        testTableau();
        
        Arbre[] garden = new Arbre[3]; 
        Arbre a = new Arbre(4);
        Arbre b = new Arbre(2);
        Arbre c = new Arbre(6);
        Arbre d = new Arbre(1);
        Arbre e = new Arbre(3);
        Arbre f = new Arbre(5);
        Arbre g = new Arbre(7);
        a.setLeft(b);
        a.setRight(c);
        b.setLeft(d);
        b.setRight(e);
        c.setLeft(f);
        c.setRight(g);
        
        garden[0]=a;

        Arbre a2 = new Arbre(2);
        Arbre b2 = new Arbre(1);
        Arbre c2 = new Arbre(3);
        Arbre d2 = new Arbre(4);
        Arbre e2 = new Arbre(5);
        Arbre f2 = new Arbre(9);
        Arbre g2 = new Arbre(10);
        a2.setLeft(b2);
        a2.setRight(c2);
        b2.setLeft(d2);
        b2.setRight(e2);
        c2.setLeft(f2);
        c2.setRight(g2);

        garden[1]=a2; 
        garden[2]=f2;


        for(int i =0; i<3; i++){
        System.out.println(garden[i].toString()); 
        }

    }
}
