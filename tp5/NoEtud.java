/**
 * Classe qui encode un numéro d’étudiant
 */

public class NoEtud {
    private int s;
    private int aa;
    private int mm;
    private int xxx;

    /**
     * Construit le numéro d’étudiant dont les composants sont donnés en paramètre.
     * Si les paramètres ne sont pas cohérent, le numéro doit être 2 15 01 000
     */


    boolean verif_contrainte(int s, int aa, int mm, int xxx){
        if (s > 3 || s ==0 ||  mm > 12 || mm==0 || xxx ==0 || xxx>999){
            return true ; 
        }
        return false; 
    }

    private boolean verif_borne(int n) {
        return n >= 10000000 && n <= 40000000;
    }
    
 public NoEtud(int s, int aa, int mm, int xxx) {
        if (verif_contrainte(s, aa, mm, xxx)) {
            this.s = 2;
            this.aa = 15;
            this.mm = 1;
            this.xxx = 0;
        } else {
            this.s = s;
            this.aa = aa;
            this.mm = mm;
            this.xxx = xxx;
        }

        if (!verif_borne(toInteger())) {
            this.s = 1;
            this.aa = 0;
            this.mm = 1;
            this.xxx = 0;
        }
    }
    /**
     * Constructeur à partf(! isBST()){
            throw new IllegalArgumentException("non ");
        }ir d'un entier
     */
    public static int Convertisseur(String s , int i , int j){
        String c =""; 
        while(i != j +1){
            c+= s.charAt(i); 
            i++; 

        } 
    return Integer.parseInt(c); 
    }
    public NoEtud(int n) {
        String s = Integer.toString(n);
        this.s = Convertisseur(s, 0, 0);
        this.aa = Convertisseur(s, 1, 2);
        this.mm = Convertisseur(s, 3, 4);
        this.xxx = Convertisseur(s, 5, 7);

        if (verif_contrainte(this.s, aa, mm, xxx)) {
            this.s = 2;
            this.aa = 15;
            this.xxx = 0;
            this.mm = 1;
        }

        if (!verif_borne(n)) {
            this.s = 1;
            this.aa = 0;
            this.mm = 1;
            this.xxx = 0;
        }
    }
/* 
        String s= ""+n+"";
    
        if(n >=10000000 && n<= 4000000){
          
            this.NoEtud(Convertisseur(s,0,0),Convertisseur(s,1,2),Convertisseur(s,3,4),Convertisseur(s,5,7));
            
            }
        
            this.NoEtud(1,00,01,000);
        }
    /**
     * Getters et Setters
     */

    public int getS(){
		return s;
	}

    public int getAa(){
		return aa;
	}

    public int getMm(){
		return mm;
	}

    public int getXxx(){
		return xxx;
	}
	
	public void setS(int s){
		this.s = s;
	}
	
	public void setAa(int aa){
		this.aa = aa;
	}
	
	public void setMm(int mm){
		this.mm = mm;
	}
	
	public void setXxx(int xxx){
		this.xxx = xxx;
	}

    /**
     * Renvoie la chaîne de caractères correspondant au numéro d'étudiant
     * 
     * Exemple : si s=1, aa=0, mm=1 et xxx=0, alors la méthode doit renvoyer "1 00
     * 01 000"
     * 
     * Si vous avez des doutes sur le formatage de chaines de caractères :
     * https://koor.fr/Java/Tutorial/java_system_out_printf.wp
     */
    public String toString() {
        String sString =Integer.toString(s);
        String aaString ="";
        String mmString ="";
        String xxxString ="";

        if( aa >=10){
            aaString = Integer.toString(aa); 
        }
        if ( aa <10){
            aaString= "0"+Integer.toString(aa);
        }

        if( mm >=10){
            mmString = Integer.toString(mm); 
        }
        
        if ( mm <10){
            mmString= "0"+Integer.toString(mm);
        }

        if( xxx >=100){
            xxxString = Integer.toString(xxx); 
        }
    
        if( xxx < 10){
            xxxString= "0"+"0"+Integer.toString(xxx);
        }
            
        if(xxx <100 && xxx >=10){
           xxxString= "0"+Integer.toString(xxx);
        }    
            
        return sString + " " + aaString + " " + mmString + " " + xxxString;
    }
    /**
     * Renvoie l'entier qui représente le numéro d'étudiant.
     *
     * Exemple : si s=1, aa=0, mm=1 et xxx=0, alors la méthode doit renvoyer
     * l'entier 10001000
     */
    public int toInteger() {
        return s * 10000000 + aa * 100000 + mm * 1000 + xxx;
    }

    /**
     * Fonction de hachage 1
     */
    public int hashCodeInt() {
        return toInteger();
    }


    /**
     * Fonction de hachage 2
     */
    public int hashCodeUniform(int m) {
        return hashCodeInt()*m;   
    }

    /**
     * Fonction de hachage 3
     */
    public int hashCodeMod(int m) {
        return hashCodeInt()%m;
    }
    /**
     * Fonction de hachage 4
     */
    private static final double A = (Math.sqrt(5) + 1) / 2;

    private double frac(double num) {
        return num - Math.floor(num);
    }

    public int hashCodeGold(int m) {
        double fracValue = frac(toInteger() * A);
        return (int) (m * fracValue);
    }

    /**
     * Redéfinition de la méthode equals pour qu'elle corresponde à l'égalité
     * structurelle (c'est-à-dire, ici : equals retourne true si et seulement si
     * elle compare deux instances de NoEtud contenant la même séquence de
     * chiffres).
     * 
     * Remarque : pour deux objets "égaux" selon la méthode equals, il faut
     * s'assurer que la méthode hashCode retourne la même valeur.
     * 
     * Pour plus d'info : https://cs108.epfl.ch/archive/16/c/CSET/CSET-notes.html
     */
    @Override
    public boolean equals(Object n) {
        return this.toInteger() == ((NoEtud) n).toInteger();
    }

    /**
     * Redéfinition de la méthode hashCode
     */
   
    @Override
public int hashCode() {
    return hashCodeUniform(100);
}

    /**
     * Teste l’implémentation
     */
    public static void testImplementation() {
        int score = 0;
        int nTests = 0;

        System.out.println("Test NoEtud:");

        // Test 1
        nTests++;
        NoEtud etud1 = new NoEtud(1, 9, 3, 111);
        if (etud1.toString().equals("1 09 03 111")) {
            System.out.println("\t- test  1: pass");
            score++;
        } else {
            System.out.println("\t- test  1: fail");
        }

        // Test 2
        nTests++;
        NoEtud etud2 = new NoEtud(5, 9, 3, 111);
        if (etud2.toString().equals("2 15 01 000")) {
            System.out.println("\t- test  2: pass");
            score++;
        } else {
            System.out.println("\t- test  2: fail");
        }

        // Test 3
        nTests++;
        NoEtud etud3 = new NoEtud(1, 9, 15, 999);
        if (etud3.toString().equals("2 15 01 000")) {
            System.out.println("\t- test  3: pass");
            score++;
        } else {
            System.out.println("\t- test  3: fail");
        }

        // Test 4
        nTests++;
        NoEtud etud4 = new NoEtud(1, 9, 12, 1982);
        if (etud4.toString().equals("2 15 01 000")) {
            System.out.println("\t- test  4: pass");
            score++;
        } else {
            System.out.println("\t- test  4: fail");
        }

        // Test 5
        nTests++;
        NoEtud etud5 = new NoEtud(10003123);
        if (etud5.toString().equals("1 00 03 123")) {
            System.out.println("\t- test  5: pass");
            score++;
        } else {
            System.out.println("\t- test  5: fail");
        }

        // Test 6
        nTests++;
        NoEtud etud6 = new NoEtud(10000123);
        if (etud6.toString().equals("1 00 01 000")) {
            System.out.println("\t- test  6: pass");
            score++;
        } else {
            System.out.println("\t- test  6: pass");
            score++;
        }

        // Test 7
        nTests++;
        if (etud1.toInteger() == 10903111) {
            System.out.println("\t- test  7: pass");
            score++;
        } else {
            System.out.println("\t- test  7: fail");
        }

        // Test 8
        nTests++;
        if (etud2.toInteger() == 21501000) {
            System.out.println("\t- test  8: pass");
            score++;
        } else {
            System.out.println("\t- test  8: fail");
        }

        // Test 9
        nTests++;
        if (etud2.hashCodeInt() == 21501000) {
            System.out.println("\t- test  9: pass");
            score++;
        } else {
            System.out.println("\t- test  9: fail");
        }

        // Test 10
        nTests++;
        if (etud2.hashCodeUniform(2) == 43002000) {
            System.out.println("\t- test 10: pass");
            score++;
        } else {
            System.out.println("\t- test 10: fail");
        }

        // Test 11
        nTests++;
        if (etud5.hashCodeUniform(1) == 10003123) {
            System.out.println("\t- test 11: pass");
            score++;
        } else {
            System.out.println("\t- test 11: fail");
        }

        // Test 12
        nTests++;
        if (etud1.hashCodeMod(100) == 11) {
            System.out.println("\t- test 12: pass");
            score++;
        } else {
            System.out.println("\t- test 12: fail");
        }

        // Test 13
        nTests++;
        if (etud5.hashCodeMod(100) == 23) {
            System.out.println("\t- test 13: pass");
            score++;
        } else {
            System.out.println("\t- test 13: fail");
        }

        // Test 14
        nTests++;
        if (etud1.hashCodeGold(77) == 13) {
            System.out.println("\t- test 14: pass");
            score++;
        } else {
            System.out.println("\t- test 14: fail");
        }

        // Test 15
        nTests++;
        if (etud5.hashCodeGold(77) == 0) {
            System.out.println("\t- test 15: pass");
            score++;
        } else {
            System.out.println("\t- test 15: fail");
        }

        System.out.println("Score " + score + "/" + nTests);
    }

    public static void main(String[] args) {
        testImplementation();
        NoEtud et = new NoEtud(1, 11, 30, 115); 
        NoEtud et2 = new NoEtud(11130115);
        System.out.println(et.toString()); 
        System.out.println(et2.toString()); 
        NoEtud etud2 = new NoEtud(5, 9, 3, 111);
        
        System.out.println(etud2.toString()); 
        NoEtud etud6 = new NoEtud(10000123);
    
        System.out.println(etud6.toString());
    
    }
}
