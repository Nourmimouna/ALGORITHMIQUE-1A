/**
 * TP 6: Programmation dynamique - distance d’alignement
 */


public class Distance {

    public enum Fleche {H, H_G, G};
    private String mot1;
    private String mot2;
    private int[][] costs;
    private Fleche[][] fleches;





    /**
     * Constructeur
     */
    public Distance(String mot1, String mot2) {
        this.mot1 = mot1;
        this.mot2 = mot2;
        this.costs = new int[this.mot1.length() +1][this.mot2.length() +1];
        this.fleches = new Fleche[this.mot1.length() +1][this.mot2.length() +1];
    }

    /**
     * Calcule le coût du remplacement du symbole a par b
     */
    
    int cost(char a, char b){
       if(a == b || a == ' ' || b == ' '){
            return 0;
        }
       else{
            return 1;
        }
    }

    /**
     * Calcule la matrice de coûts
     */
    public void computeCosts(){

        int n = this.mot1.length();
        int m = this.mot2.length();

        for (int i = 0; i <= n; i++) {
            this.costs[i][0] = i;
            this.fleches[i][0] = Fleche.H;
        }

        for (int j = 1; j <= m; j++) {
            this.costs[0][j] = j;
            this.fleches[0][j] = Fleche.G;
        }

        for (int i = 1; i <= n; i++){

            for (int j = 1; j <= m; j++) {
                this.costs[i][j]=Math.min( (Math.min( this.costs[i-1][j-1]+cost(this.mot1.charAt(i-1),this.mot2.charAt(j-1)) , this.costs[i][j-1]+1) ) , this.costs[i-1][j]+1);
                
                int ind1 = this.costs[i-1][j-1]+cost(this.mot1.charAt(i-1),this.mot2.charAt(j-1));
                int ind2 = this.costs[i][j-1]+1;
                int ind3 = this.costs[i-1][j]+1;
                
                if (ind1 <= ind2 && ind1 <= ind3) {
                    costs[i][j] = ind1;
                    fleches[i][j] = Fleche.H_G;
                } 
                else if(ind2 <= ind1 && ind2 <= ind3){
                    costs[i][j] = ind2;
                    fleches[i][j] = Fleche.G;
                } 
                else{
                    costs[i][j] = ind3;
                    fleches[i][j] = Fleche.H;
                }
            }
       
        }


    }

    /**
     * Retourne la distance d’alignement
     */
    public int distance() {
        computeCosts();
        return this.costs[this.mot1.length()][this.mot2.length()];
    }

    /**
     * Retourne une représentation des deux mots alignés
     * 
     * Exemple: si les deux mots sont "and" et "ad" la méthode doit renvoyer
     * la chaine de caractères :
     *   "and
     *    a-d"
     */
    public String alignment(){

        String mot11 = "";
        String mot22 = "";
        computeCosts();
        int n = this.mot1.length();
        int m = this.mot2.length();

        while (n > 0 || m > 0) {
            if (fleches[n][m] == Fleche.H_G) {
                mot11 = this.mot1.charAt(n-1)+mot11;
                mot22 = this.mot2.charAt(m-1)+mot22;
                m--;
                n--;
            } 
            else if (fleches[n][m] == Fleche.G) {
                mot11 = "-"+mot11;
                mot22 = this.mot2.charAt(m-1)+mot22;
                m--;
            } else {
                mot22 = "-"+mot22;
                mot11 = this.mot1.charAt(n-1)+mot11;
                n--;
            }
        }
        return ""+mot11+"\n"+mot22;
            
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
