/**
 * Classe qui encode l'ensemble des inscrits à une unité d'enseignement (UE)
 */

 import java.io.*;
 import java.util.Scanner;
 import java.util.HashSet;
 
 
 public class InscritsUE {
     /* A COMPLETER: attributs privés */
     private HashSet<NoEtud> inscrits; 
     /**
      * Constructeur
      */
     public InscritsUE() {
         this.inscrits = new HashSet<>(100); 
     }
 
     /**
      * Ajoute l'étudiant dont le numéro est n
      * 
      * Comme pour la classe HashSet, la méthode renvoie true si le numéro est
      * ajouté
      */
     public boolean add(NoEtud n) {
        if(this.inscrits.contains(n)){
            return false ; 
        }
        this.inscrits.add(n); 
        return true ; 
    }
 
     /**
      * Rrenvoie la chaîne de caractères avec la liste des inscrits
      */
    public String toString() {
       String s =""; 
       for(NoEtud e : this.inscrits){
            s+= e.toString()+" \n";
       }
        return s;
    }
 
     /**
      * Ajoute à l'ensemble inscrits les numéros d'étudiant stockés dans le
      * fichier fname
      */
     public void loadFromFile(String fname) {
        try {
			// Création d'un fileReader pour lire le fichier
			FileReader fileReader = new FileReader(fname);
			
			// Création d'un bufferedReader qui utilise le fileReader
			BufferedReader reader = new BufferedReader(fileReader);
			
			// une fonction à essayer pouvant générer une erreur
			String line = reader.readLine();
			
			while (line != null) {
				// affichage de la ligne
				int n = Integer.valueOf(line); 
                this.inscrits.add(new NoEtud(n)); 
				// lecture de la prochaine ligne
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
        
     }
 
    public static void main(String[] args) {
    InscritsUE Liste1 = new InscritsUE(); 
    Liste1.add(new NoEtud(12150220));     
    NoEtud et1 =new NoEtud(20101103);     
    NoEtud et2 =new NoEtud(10002320);  
    Liste1.add(et2); 
    Liste1.add(et1); 
    System.out.println(Liste1.toString());
    
    InscritsUE Liste2 = new InscritsUE(); 
    Liste2.loadFromFile("tp05.dat");
    System.out.println(Liste2.toString());

}
}
 