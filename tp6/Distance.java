
/**
 * TP 6: Programmation dynamique - distance d’alignement
 */


public class Distance {
    /* A COMPLETER: attributs privés */
    private String world1;
    
    private String world2;

    private int[][] D; 
    /**
     * Constructeur
     */
    public Distance(String word1, String word2) {
        if ( this.world1 != null && this.world2 != null){
        this.D= new int[this.world1.length()+1][this.world2.length()+1]; 
        }
    }
    
    /*for (int i =0; i < this.world1.length(); i++){
            this.D[i][0]=0; 
        }
        for(int j =0; j<this.world2.length(); j++){
            this.D.[0][j]=0; 
        }
        for (int i =0; i < this.world1.length(); i++){cost=
        
            for(int j =0; j<this.world2.length(); j++){
            this.D.[i][j]=min( this.D.[i][j],  this.D.[i][j])
        } */

    /**
     * Calcule le coût du remplacement du symbole a par b
     */
    int cost(char c, char d) {
       int j=0; 
       if(c != d){
        j++; 
       }
       return j; 
    }

    /**
     * Calcule la matrice de coûts
     */
    public void computeCosts() {
        if ( this.world1 != null && this.world2 != null){
        int n1 =this.world1.length(); 
        int n2= this.world2.length(); 
        int count =0; 
        for( int i=0; i< min(n1, n2); i++){
            count +=cost(this.world1.charAt(i),this.world2.charAt(i));

        }
    }
}

    /**
     * Retourne la distance d’alignement
     */
    public int distance() {
        /* A COMPLETER */
        return -1;
    }

    /**
     * Retourne une représentation des deux mots alignés
     * 
     * Exemple: si les deux mots sont "and" et "ad" la méthode doit renvoyer
     * la chaine de caractères :
     *   "and
     *    a-d"
     */
    public String alignment() {
        /* A COMPLETER */
        return "";
    }

    /**
     * Teste l’implémentation
     */
    public static void testImplementation() {
        int score = 0;
        int nTests = 10;

        System.out.println("Testing the implementation:");

        Distance dist1 = new Distance("chien", "chat");
        if (dist1.distance() == 3) {
            System.out.println("\t- test  1: pass");
            score++;
        } else {
            System.out.println("\t- test  1: fail");
        }

        Distance dist2 = new Distance("book", "back");
        if (dist2.distance() == 2) {
            System.out.println("\t- test  2: pass");
            score++;
        } else {
            System.out.println("\t- test  2: fail");
        }

        Distance dist3 = new Distance("elephant", "relevant");
        if (dist3.distance() == 3) {
            System.out.println("\t- test  3: pass");
            score++;
        } else {
            System.out.println("\t- test  3: fail");
        }

        Distance dist4 = new Distance("Saturday", "Sunday");
        if (dist4.distance() == 3) {
            System.out.println("\t- test  4: pass");
            score++;
        } else {
            System.out.println("\t- test  4: fail");
        }

        Distance dist5 = new Distance("Google", "Facebook");
        if (dist5.distance() == 8) {
            System.out.println("\t- test  5: pass");
            score++;
        } else {
            System.out.println("\t- test  5: fail");
        }

        Distance dist6 = new Distance("semaine", "madeleine");
        if (dist6.distance() == 5) {
            System.out.println("\t- test  6: pass");
            score++;
        } else {
            System.out.println("\t- test  6: fail");
        }

        Distance dist7 = new Distance("potato", "patata");
        if ("potato\npatata".equals(dist7.alignment())) {
            System.out.println("\t- test  7: pass");
            score++;
        } else {
            System.out.println("\t- test  7: fail");
        }

        Distance dist8 = new Distance("man", "mad");
        if ("man\nmad".equals(dist8.alignment())) {
            System.out.println("\t- test  8: pass");
            score++;
        } else {
            System.out.println("\t- test  8: fail");
        }

        Distance dist9 = new Distance("and", "ad");
        if ("and\na-d".equals(dist9.alignment())) {
            System.out.println("\t- test  9: pass");
            score++;
        } else {
            System.out.println("\t- test  9: fail");
        }

        Distance dist10 = new Distance("wong", "wrong");
        if ("w-ong\nwrong".equals(dist10.alignment())) {
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
