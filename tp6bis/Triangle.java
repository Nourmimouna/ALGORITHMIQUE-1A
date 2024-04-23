/**
 * TP 6: Programmation dynamique - distance d’alignement
 */


public class Triangle {
	
    /**
     * Calcule la factorielle de n et renvoie la valeur
     */
    public static long fact(int n) {
        long resultat = 1;
        for(int i = n ; i > 0 ; i--){
            resultat *= i;
        }
        return resultat;
    }

    /**
     * Calcule le coefficient binomial avec la définition factorielle
     */
    public static long binomFact (int n, int k) {
        long resultat;
        if(k == 0 || k == n){
            resultat = 1;
        }
        else{
            resultat = (int)(fact(n) / (fact(k)*fact((n-k))));
        }
        return resultat;

    }

    /**
     * Calcule le coefficient binomial avec la définition récursive
     */
    public static long binomRec (int n, int k) {
        if(k==0 || k==n){
           return 1;
        }
        else{
            return binomRec(n - 1, k - 1) + binomRec(n - 1, k);
        }
    }

    /**
     * Calcule combien de fois le case de base k=0 est appelé dans un appel récursif
	 * dans la fonction précédente
     */
    public static long comptBinomRec (int n, int k) {

        long compteur = 0;
        long resultat;

        if( k==0 || k==n ){
            if(k ==0){
                return 1;
            }
            else{
                return 0;
            }
        }
        else{
            return comptBinomRec(n - 1, k - 1) + comptBinomRec(n - 1, k);
        }
    }

    /**
     * Calcule le coefficient binomial avec la programmation dynamique
     */
    public static long binomDyn(int n, int k) {
        long[][] binTab = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0 || j == i) {
                    binTab[i][j] = 1;
                } 
                else {
                    binTab[i][j] = binTab[i - 1][j]+ binTab[i - 1][j - 1] ;
                }
            }
        }
        return binTab[n][k];
    }

    /**
     * Teste l’implémentation
     */
    public static void testImplementation() {
        int score = 0;
        int nTests = 6;

        System.out.println("Test de fact :");
		
		int[] val= new int[]{0,1,2,3,4,10};
		long[] fac= new long[]{1,1,2,6,24,3628800};

        for (int i=0; i<6; i++){
			if (fact(val[i])==fac[i]) {
				System.out.println("\t- test  "+i+" : pass");
				score++;
			} else {
				System.out.println("\t- test  "+i+" : fail");
			}
		}

        System.out.println("Score " + score + "/" + nTests);
    
        score = 0;
        nTests = 6;

    System.out.println("Test de binomFact :");
		
		int[] valn= new int[]{5,5,5,5,5,5};
		int[] valk= new int[]{0,1,2,3,4,5};
		long[] binF= new long[]{1,5,10,10,5,1};

        for (int i=0; i<6; i++){
			//System.out.println(binomFact(valn[i],valk[i]));
			if (binomFact(valn[i],valk[i])==binF[i]) {
				System.out.println("\t- test  "+i+" : pass");
				score++;
			} else {
				System.out.println("\t- test  "+i+" : fail");
			}
		}

    System.out.println("Score " + score + "/" + nTests);
    
        score = 0;
        nTests = 6;

    System.out.println("Test de binomRec :");
		
		long[] binR= new long[]{1,5,10,10,5,1};

        for (int i=0; i<6; i++){
			//System.out.println(binomRec(valn[i],valk[i]));
			if (binomRec(valn[i],valk[i])==binF[i]) {
				System.out.println("\t- test  "+i+" : pass");
				score++;
			} else {
				System.out.println("\t- test  "+i+" : fail");
			}
		}

        System.out.println("Score " + score + "/" + nTests);
    
    
        score = 0;
        nTests = 6;

    System.out.println("Test de comptBinomRec :");
		
		long[] cBinR= new long[]{1,4,6,4,1,0};

        for (int i=0; i<6; i++){
			//System.out.println(comptBinomRec(valn[i],valk[i]));
			if (comptBinomRec(valn[i],valk[i])==cBinR[i]) {
				System.out.println("\t- test  "+i+" : pass");
				score++;
			} else {
				System.out.println("\t- test  "+i+" : fail");
			}
		}

        System.out.println("Score " + score + "/" + nTests);
    
        score = 0;
        nTests = 6;

    System.out.println("Test de binomDyn :");
		
		long[] binD= new long[]{1,5,10,10,5,1};

        for (int i=0; i<6; i++){
			//System.out.println(binomDyn(valn[i],valk[i]));
			if (binomDyn(valn[i],valk[i])==binD[i]) {
				System.out.println("\t- test  "+i+" : pass");
				score++;
			} else {
				System.out.println("\t- test  "+i+" : fail");
			}
		}

        System.out.println("Score " + score + "/" + nTests);
    }

    public static void main(String[] args) {
        testImplementation();
    }
}
