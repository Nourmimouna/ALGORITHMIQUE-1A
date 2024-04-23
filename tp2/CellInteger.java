/**
 * Classe qui représente une cellule de liste d'entiers
 */


public class CellInteger {
    
    private Integer data; 
    private CellInteger next; 
    
    public CellInteger() {
        
        this.data = 0; 
        this.next = null; 
        
    }

    public CellInteger(Integer val) {
        this.data = val; 
  }

    /**
     * Méthode d'accès en lecture à data
     */
    public Integer getData() {
        return this.data;
    }

    /**
     * Méthode d'accès en lecture à next
     */
    public CellInteger getNext() {
        
        return this.next ;
    }

    /**
     * Méthode d'accès en écriture pour data
     */
    public void setData(Integer val) {
        this.data= val; 
    }

    /**
     * Méthode d'accès en écriture pour next
     */
    public void setNext(CellInteger n) {
        this.next = n ; 
    }

    /**
     * Returne une chaîne représentant le contenu de la cellule
	 * La représentation sera de pa forme a -> b -> c si a est la
	 * valeur de la première cellule, b celle de la suivante, et
	 * c la dernière, qui pointe vers null
     */
    public String toString() {

        String s=""; 
        CellInteger i = this;
        
        
        while(i.getNext().getNext() != null){ 
            s+=   i.getNext().getData()+" -> ";
            i=i.getNext(); 
        }
        s+= i.getNext().getData(); 

        return s; 
    }

    public static void main(String[] args) {
       CellInteger c1= new CellInteger(3);
    
       CellInteger c2= new CellInteger(5);
       
       CellInteger c3= new CellInteger(7);
       
       c1.setNext(c2);
       c2.setNext(c3);
       String s = c1.toString(); 
       System.out.println(s);

    }
}
