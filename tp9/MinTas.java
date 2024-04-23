import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinTas {
    
    private final List<Integer> elements = new ArrayList<>();
	
    /**
     * Retourne l'index du père du noeud dont l'index est passé en paramètre.
     */
    private int idxPere(int idx)
    {
        return (idx-1)/2;
    }

    /**
     * Retourne l'index du fils gauche du nœud d'index idx.
     */
    private int idxFilsGauche(int idx)
    {
        return (1+2*idx);
    }

    /**
     * Retourne l'index du fils droite du nœud d'index idx.
     */
    private int idxFilsDroite(int idx)
    {
        return 2*(idx+1);
    }

    /**
     * Échange les éléments situés aux deux indices passés en paramètre si et
     * seulement s'ils ne sont pas dans l'ordre (c'est à dire : l'indice le plus
     * grand doit, à la fin correspondre à l'élément le plus grand).
     *
     * Remarque : cette méthode ne vérifie pas si l'un des indices correspond à un
     * nœud ancêtre de l'autre. Cette méthode n'a pas lieu d'être appelée quand ce
     * n'est pas le cas.
     * 
     * @return true si l'échange a eu lieu, false sinon.
     */
    private boolean echangeSiNecessaire(int idx1, int idx2)
            {
        if(idx1 == idx2){return false;}
        int a0=Math.min(idx1, idx2);
        int b0=Math.max(idx1, idx2);
        Integer b1=this.elements.get(b0);
        Integer a1=this.elements.get(a0);
        
        if (a1>b1)
            {
                this.elements.set(b0,a1);
                this.elements.set(a0,b1);
            return true;
             }
        return false;
        
        }

    /**
     * Répare le tas en s'assurant que l'élément d'index idx et tous ses ancêtres
     * jusqu'à la racine sont bien dans le bon ordre (et en rétablissant l'ordre par
     * échanges successifs, le cas échéant).
     * 
     * @idx point de départ de la percolation
     */
    private void percoleVersLeHaut(int idx)
            {
            int A=idx;
            while (A> 0 && this.echangeSiNecessaire(A, this.idxPere(A)))
            {A = this.idxPere(A);}}
    
	
    /**
     * Insère l'élément elem dans le tas, puis répare le tas.
     * 
     * @param elem élément à insérer
     */
    public void insere(int elem) {
        this.elements.add(elem);
    int a= this.elements.size()-1;
    while (a > 0 && this.elements.get(a)<= this.elements.get(idxPere(a))) {
        echangeSiNecessaire(a, this.idxPere(a));
        a = this.idxPere(a);
    }
    }
  
    /**
     * Insère les éléments passés en paramètre (tout en réparant le tas).
     * 
     * @param elems éléments à insérer
     */
    public void insereTout(int... elems)
            {
        for (var i : elems)
                {
            insere(i);}
                }

                
    /**
     * Répare le sous-arbre dont le nœud d'indice idx est la racine en descendant
     * dans l'arbre et en procédant à des échanges successifs avec le plus petit
     * fils jusqu'à ce qu'il n'y ait plus d'échange possible.
     * 
     * @param idx index de la racine du sous-arbre à réparer
     * 
     */

    private void percoleVersLeBas(int idx)
            {
        int parent = idx;
        int gosse = this.idxFilsGauche(parent);
        int a=this.elements.size();
        while (gosse <a) 
            {
            int child_right = this.idxFilsDroite(parent);
            if (child_right < this.elements.size() && this.elements.get(child_right) < this.elements.get(gosse)) {
                gosse = child_right;
            }
            if (this.elements.get(gosse)<this.elements.get(parent))
                {
                this.echangeSiNecessaire(gosse,parent);
                parent = gosse;
                gosse = this.idxFilsGauche(parent);
                }
            else 
                {
                  break;
                }
            }
        }

    /**
     * Élimine le plus petit élément du tas et répare le tas.
     * 
     * @return l'élément qui a été supprimé le cas échéant, null si le tas était vide.
     **/
    public Integer elimine() {
        if (this.elements.isEmpty()) {
            return null;
        }
        int lastIndex = this.elements.size() - 1;
        Integer removedElement = this.elements.get(0);
        Integer lastElement = this.elements.get(lastIndex);
        this.elements.set(0, lastElement);
        this.elements.remove(lastIndex);
        percoleVersLeBas(0);
        return removedElement;
    }
    /**
     * Élimine l'élément d'index idx s'il existe, puis répare le tas.
     * 
     * @param idx index de l'élément à éliminer
     * @return true si une suppression a eu lieu, false sinon.
     */

    public boolean elimineIdx(int idx)
    {
        if (this.elements.size()==0){return false;}
        else if(idx>=this.elements.size()){return false;}
        else if(this.elements.size()==1)
            {this.elements.remove(this.elements.size()-1); return true;}

        Integer b=this.elements.remove(this.elements.size()-1);
            this.elements.set(idx, b);
            this.percoleVersLeHaut(idx); 
            this.percoleVersLeBas(idx);
            return true;
        
    }

    /**
     * 
     * @return true si le tableau contient bien la représentation d'un tas, false
     *         sinon
     */
    private boolean verifieTas() {
        for (int i = 0; i < elements.size() / 2; i++) {
            var filsG = idxFilsGauche(i);
            var filsD = idxFilsDroite(i);
            if (filsG < elements.size() && elements.get(filsG) < elements.get(i))
                return false;
            if (filsD < elements.size() && elements.get(filsD) < elements.get(i))
                return false;
        }
        return true;
    }


    public static void main(String[] args) {
        var testBench = new Object() {
            int totalTests = 0, testSuccesses = 0;

            void printTestResult(boolean r) {
                totalTests++;
                System.out.printf("Test %d: %s\n", totalTests, r ? "PASSED" : "FAILED");
                if (r)
                    testSuccesses++;
            }

            void printSummary() {
                System.out.printf("Final result: %d/%d\n", testSuccesses, totalTests);
            }

        };

        System.out.println("\n--- TESTS PARTIE OBLIGATOIRE ---");
        Integer[] elems = { 23, 231, 120, 4, 9109, 199, 18 };


        var tas = new MinTas();
        testBench.printTestResult(tas.idxPere(2) == 0);
        testBench.printTestResult(tas.idxPere(1) == 0);
        testBench.printTestResult(tas.idxFilsGauche(2) == 5);
        testBench.printTestResult(tas.idxFilsDroite(1) == 4);
		
        for (var x : elems) {
            tas.insere(x);
            testBench.printTestResult(tas.verifieTas() && tas.elements.size()>0);
        }
        var filled = tas.elements.size() > 1;
        testBench.printTestResult(filled && !tas.echangeSiNecessaire(0, 1));
        if (filled) {
            var ech = tas.elements.get(0);
            tas.elements.set(0, tas.elements.get(1));
            tas.elements.set(1, ech);
        }
        testBench.printTestResult(filled && tas.echangeSiNecessaire(0, 1));

        var sortedList = new ArrayList<Integer>();
		int max = 7;
        while (!tas.elements.isEmpty() && max >0) {
            var elem = tas.elimine();
            testBench.printTestResult(null != elem);
            testBench.printTestResult(tas.verifieTas());
            sortedList.add(elem);
			max--;
        }
        testBench.printTestResult(null == tas.elimine());
        Arrays.sort(elems);
        testBench.printTestResult(sortedList.equals(Arrays.asList(elems)));
        testBench.printSummary();

        System.out.println("\n--- TESTS PARTIE BONUS ---");
        tas.insereTout(23, 231, 120, 4, 9109, 199, 18);
        testBench.printTestResult(tas.elimineIdx(1));
        testBench.printTestResult(tas.verifieTas());
        testBench.printTestResult(!tas.elimineIdx(15));
        testBench.printTestResult(tas.verifieTas());
        testBench.printSummary();

    }

}
