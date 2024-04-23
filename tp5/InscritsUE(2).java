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
        if(!this.inscrits.contains(n)){

            this.inscrits.add(NoEtud(n)); 
            return true; 
        }
        return false ; 
    }

    /**
     * Rrenvoie la chaîne de caractères avec la liste des inscrits
     */
    public String toString() {
        String s =""; 
        for(NoEtud e : this.inscrits){
            s+= e.toString()+"\n";
        }
        return s;
    }

    /**
     * Ajoute à l'ensemble inscrits les numéros d'étudiant stockés dans le
     * fichier fname
     */
    public void loadFromFile(String fname) {
        /* A COMPLETER */
    }

    public static void main(String[] args) {
        /* A COMPLETER */
    }
}
