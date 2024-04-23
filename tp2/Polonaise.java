/*
 * Codage de l'algorithme pour evaluation d'expressions en forme Polonaise
 * inverse.
 */

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.NoSuchElementException;


public class Polonaise {
    String[] expression ; 
    /**
     * Constructeur qui initialise l'attribut en utilisant l'argument
     */
    public Polonaise(String arg) {
        /* A COMPLETER */
    }

    /**
     * Retourne une representation de l'expression sous forme de chaine de
     * caracteres
     */
    public String toString() {
        
        return "";
    }

    /**
     * Evaluation de l'expression en forme polonaise.
     * @return valeur de l'expression ou null
     */
    public Integer eval() {
        /* A COMPLETER : utiliser Deque */
        return -1;
    }

    /**
     * Teste l'algorithme
     */
    public static void testPolonaise() {
        String[] expressions = {
            "10 7 -", "", "2 3 + 5 *", "2 2 * 2 /", "5 5 / 1 -",
            "10 * /", "/ 10 1", "87 7 - 2 / 4 /", "1 1 *", "a b +"
        };
        Integer[] expected = {
            3, null, 25, 2, 0,
            null, null, 10, 1, null
        };

        System.out.println("**** Test:");
        int nbTests = expressions.length;
        int score = 0;
        for (int i = 0; i < nbTests; i++) {
            Polonaise p = new Polonaise(expressions[i]);
            Integer result = p.eval();
            if (result == expected[i]) {
                System.out.println("Test " + i + " passed!");
                score++;
            } else {
                System.out.println("Test " + i + " failed with " + result + " != " + expected[i]);
            }
        }
        System.out.println("**** Final score: " + score + "/" + nbTests);
    }

    public static void main(String[] args) {
        testPolonaise();
    }
}
