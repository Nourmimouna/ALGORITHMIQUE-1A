/**
 * TP4: Algorithmes de tri
 */
import java.util.Arrays;
import java.util.Random;
import java.util.PriorityQueue;
import java.util.function.Consumer;

/**
 * Implementation de quatre algorithmes de tri
 */
public class Tri1 {
    /**
     * Methode utilisee pour les tests.
     * @return true si le tableau en entree est trie.
     */
    public static boolean isSorted(int[] tab) {
		int n=tab.length;  
		for(int i=0;i<n;i++){
			if(tab[i]>tab[i+1]){
				return false; 
				}
				}
			return true; 
			}
       
    	

    /**
     * Implementation du tri a bulles
     */
    public static void triBulles(int[] tab) {
		int n=tab.length; 
		for(int i=0; i<n;i++){
			for(int j=0; j<i;j++){
				if(tab[j]>tab[j+1]){
					int tmp=tab[j]; 
					tab[j]=tab[j+1]; 
					tab[j+1]=tmp; 
					}
					}
					}
				}
    	

    /**
     * Implementation du tri par insertion
     */
    public static void triInsertion(int[] tab) {
        	int n =tab.length; 
		for(int j=n-2; j>1;j--){
			int k = tab[j];
			int i = j+1; 
			while(i<n && k>tab[i]){
				tab[i-1] = tab[i];
				i++; 
				}
			tab[i-1] = k; 
			}
		}	
	    

    /**
     * Implementation du tri par fusion
     */
    public static void triFusion(int[] T, int i, int j) {
		if(i<j){
			int m =(i+j) /2; 
			triFusion(T, i, m);
			triFusion(T, m+1, j);
			fusion(T, i, m, j);
			}
		else{ 
			
			int m =(i+j) /2; 
			triFusion(T, j, m);
			triFusion(T, m+1, i);
			fusion(T, j, m, i);
			}
			
    		}
    		

    /**
     * Implementation de la methode fusion utilise par triFusion
     */
    public static void fusion(int[] tableau, int i, int j, int k){
       		int[] t=new int[tableau.length];
       		for(int r=i;r<k;r++){
       			t[r]=tableau[r]; 
       			}
       		int p=i; int q=j+1; 
       		for(int r=i;r<k;r++){
       			if(p>j){
       				tableau[r]=t[q]; 
       				q++;
       				}
       			else if(q>k){
       				tableau[r]=t[p];
       				p++; 
       				} 
       			else if(t[p]<=t[q]){
       				tableau[r]=t[p]; 
       				p++;
       				}
       			else{
       				tableau[r]=t[q]; 
       				q++;
       				}
       				}
       			}
    

    /**
     * Methode utilisee pour les tests
     */
    public static void triFusion(int[] tableau) {
        triFusion(tableau, 0, tableau.length - 1);
    }

    /**
     * Implementation de la recherche dichotomique
     */
    public static int dichotomie(int[] tableau, int x) {
        /* A COMPLETER */
        return -2;
    }

    /**
     * Implementation de la partie recursive de la recherche dichotomique
     */
    public static int recDichotomie(int[] tableau, int x, int i, int j) {
        /* A COMPLETER */
        return -2;
    }

    /**
     * Implementation de la recherche trichotomique
     */
    public static int trichotomie(int[] tableau, int x) {
        /* A COMPLETER */
        return -2;
    }

    /**
     * Implementation de la partie recursive de la recherche trichotomique
     */
    public static int recTrichotomie(int[] tableau, int x, int i, int j) {
        /* A COMPLETER */
        return -2;
    }

    /**
     * Comptage d'etapes de la recherche dichotomique
     */
    public static int compteDichotomie(int[] tableau, int x) {
        /* A COMPLETER */
        return 0;
	}

    /**
     * Comptage d'etapes de la recherche trichotomique
     */
    public static int compteTrichotomie(int[] tableau, int x) {
        /* A COMPLETER */
        return 0;
	}
	
	
	
	
	//La suite est la section des tests. Il n'y a rien a changer, a part
	//pour les questions de compte du nombre de comparaisons
	
	
	
	
	
	
	
	

    /**
     * Teste un algorithme de tri
     * @return le nombre de tests passes avec succes
     */
    public static int testTri(Consumer<int[]> tri, int[] ns, Random rng) {
        int score = 0;
        for (int n : ns) {
            int[] tableau = new int[n];
            for (int i = 0; i < n; ++i) tableau[i] = rng.nextInt();
            var set1 = java.util.Arrays.stream(tableau).boxed().collect(java.util.stream.Collectors.toSet());
            tri.accept(tableau);
            var set2 = java.util.Arrays.stream(tableau).boxed().collect(java.util.stream.Collectors.toSet());
            if (isSorted(tableau) && set1.equals(set2)) {
                System.out.println("\t n = " + n + " passed!");
                ++score;
            } else {
                System.out.println("\t n = " + n + " failed!");
            }
        }
        return score;
    }

    /**
     * Teste un algorithme de recherche dichotomique
     * @return le nombre de tests passes avec succes
     */
    public static int testDicho(Random rng) {
        int score = 0;
        int n = 1000;
        for (int i = 1; i < 6; i++) {
            int[] tableau = new int[n];
            for (int j = 0; j < n; ++j) tableau[j] = rng.nextInt();
            Arrays.sort(tableau);
            int x = tableau[((rng.nextInt() % 1000)+1000)%1000];
            int j = dichotomie(tableau, x);
            if (j < 0 || j >= tableau.length) {
                System.out.println("\t Recherche dichotomique "+ i +" failed!");
            } else if (tableau[j] != x){
                System.out.println("\t Recherche dichotomique "+ i +" failed!");
            } else{
                System.out.println("\t Recherche dichotomique "+ i +" passed!");
                ++score;
			}
        }
        for (int i = 6; i < 11; i++) {
            int[] tableau = new int[n];
            for (int j = 0; j < n; ++j) tableau[j] = rng.nextInt();
            triFusion(tableau);
            int x;
            do{
				x = rng.nextInt();
			}while(Arrays.binarySearch(tableau,x)>=0);
            
            int j = dichotomie(tableau, x);
            if (j != -1) {
                System.out.println("\t Recherche dichotomique "+ i +" failed!");
            } else{
                System.out.println("\t Recherche dichotomique "+ i +" passed!");
                ++score;
			}
        }
        return score;
    }

    /**
     * Teste un algorithme de recherche trichotomique
     * @return le nombre de tests passes avec succes
     */
    public static int testTricho(Random rng) {
        int score = 0;
        int n = 1000;
        for (int i = 1; i < 6; i++) {
            int[] tableau = new int[n];
            for (int j = 0; j < n; ++j) tableau[j] = rng.nextInt();
            Arrays.sort(tableau);
            int x = tableau[((rng.nextInt() % 1000)+1000)%1000];
            int j = trichotomie(tableau, x);
            if (j < 0 || j >= tableau.length) {
                System.out.println("\t Recherche trichotomique "+ i +" failed!");
            } else if (tableau[j] != x){
                System.out.println("\t Recherche trichotomique "+ i +" failed!");
            } else{
                System.out.println("\t Recherche trichotomique "+ i +" passed!");
                ++score;
			}
        }
        for (int i = 6; i < 11; i++) {
            int[] tableau = new int[n];
            for (int j = 0; j < n; ++j) tableau[j] = rng.nextInt();
            triFusion(tableau);
            int x;
            do{
				x = rng.nextInt();
			}while(Arrays.binarySearch(tableau,x)>=0);
            
            int j = trichotomie(tableau, x);
            if (j != -1) {
                System.out.println("\t Recherche trichotomique "+ i +" failed!");
            } else{
                System.out.println("\t Recherche trichotomique "+ i +" passed!");
                ++score;
			}
        }
        return score;
    }

    /**
     * Compte le nombre de comparaisons dichotomiques
     * @return le nombre de comparaisons en moyenne
     */
    public static int compteDicho(Random rng, int n) {
        int total = 0;
        for (int i = 1; i < 100; i++) {
            int[] tableau = new int[n];
            for (int j = 0; j < n; ++j) tableau[j] = rng.nextInt();
            Arrays.sort(tableau);
            int x = tableau[((rng.nextInt() % n)+n)%n];
            total +=compteDichotomie(tableau, x);
        }
        return total/100;
    }

    /**
     * Compte le nombre de comparaisons trichotomiques
     * @return le nombre de comparaisons en moyenne
     */
    public static int compteTricho(Random rng, int n) {
        int total = 0;
        for (int i = 1; i < 100; i++) {
            int[] tableau = new int[n];
            for (int j = 0; j < n; ++j) tableau[j] = rng.nextInt();
            Arrays.sort(tableau);
            int x = tableau[((rng.nextInt() % n)+n)%n];
            total +=compteTrichotomie(tableau, x);
        }
        return total/100;
    }

    /**
     * Teste les algorithmes de tri et de recherche
     */
    public static void test() {
        Random rng = new Random();
        long seed = 172391361L;
        int[] ns = {10, 21, 50, 77, 100, 501, 1000};
        int score;
        
        // Test Est Trie
        System.out.println("**** Test isSorted:");
        int[][]t = {{1,2,3,4,5,6,7},{1,2,3,5,4,6,7},{3,2,3,4,5,6,7},{1,2,3,4,5,6,5},{1,1,1,1,1,1,1}};
        boolean[] tbool = {true, false, false, false, true};
		score = 0;
		for (int i=0; i<t.length; i++){
			if (tbool[i] == isSorted(t[i])){
				score++;
			}
		}
        System.out.println("**** Score: " + score + "/" + t.length);
		System.out.println("");
        
        // Tri a bulles
        System.out.println("**** Test triBulles:");
        rng.setSeed(seed);
        score = testTri(Tri::triBulles, ns, rng);
        System.out.println("**** Score: " + score + "/" + ns.length);

        // Tri par insertion
        System.out.println("**** Test triInsertion:");
        rng.setSeed(seed);
        score = testTri(Tri::triInsertion, ns, rng);
        System.out.println("**** Score: " + score + "/" + ns.length);

        // Tri par fusion
        System.out.println("**** Test triFusion:");
        rng.setSeed(seed);
        score = testTri(Tri::triFusion, ns, rng);
        System.out.println("**** Score: " + score + "/" + ns.length);

        // Recherche dichotomique
        System.out.println("**** Test Dichotomie:");
        rng.setSeed(seed);
        score = testDicho(rng);
        System.out.println("**** Score: " + score + "/10");

        // Recherche trichotomique
        System.out.println("**** Test Trichotomie:");
        rng.setSeed(seed);
        score = testTricho(rng);
        System.out.println("**** Score: " + score + "/10");


		// A decommenter pour faire les comptes de comparaisons
		// Vous pouvez jouer avec la taille n des tableaux
        /*
        int n = 100000;
        int moyenne;
        
        // Compte dichotomique
        System.out.println("**** La recherche dichotomique a fait, en moyenne,");
        rng.setSeed(seed);
        moyenne = compteDicho(rng, n);
        System.out.println(moyenne+" comparaisons sur des tableaux de taille "+n);

        // Compte trichotomique
        System.out.println("**** La recherche trichotomique a fait, en moyenne,");
        rng.setSeed(seed);
        moyenne = compteTricho(rng, n);
        System.out.println(moyenne+" comparaisons sur des tableaux de taille "+n);
        */
    }

    public static void main(String[] args) {
       test();
    }
}
