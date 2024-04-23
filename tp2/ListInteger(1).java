/**
 * Implementation d'une liste d'entiers
 */


public class ListInteger{
    
    private CellInteger head; 

    public ListInteger() {
        this.head = new CellInteger(); 
    }

    /**
     * Ajoute une cellule au début de la liste
     */
    public void addFirst(Integer d) {
        CellInteger new_head= new CellInteger(d); 
        new_head.setNext( this.head); 
        this.head.setNext(new_head);  
    }

    /**
     * Retourne une chaîne représentant les données contenues dans la liste
     */
    public String toString() {
        
        return this.head.toString(); 
       
    }

    /**
     * Ajoute une cellule à la fin de la liste
     */
    public void add(Integer d) {
      CellInteger n = this.head; 
      while(n.getNext() != null){
        n=n.getNext(); 
      }  
      CellInteger newie= new CellInteger(d); 
      n.setNext( newie); 
        }

    /**
     * Renvoie le premier entier de la liste ou null si la liste est vide
     */
    public Integer element() {
        if(this.head==null){
            return null; 
        }
        else{
            return this.head.getData(); 
        }
        
        }

    public int size() {
        int taille =1;
        CellInteger i = this.head;  
        while(i.getNext() != null){
            i=i.getNext() ; 
            taille++; 
        }
        return taille; 
        }

    /**
     * Renvoie true si la liste contient d
     */
    public boolean contains(Integer d) {
        CellInteger i = this.head; 
        do{ 
            if(i.getData()==d){
                return true; 
            }
            i=i.getNext() ; 

        }while(i.getNext()!=null);
        
        return false;
    }
    /**
     * Enlève la première cellule qui contient l'entier d et renvoie true si la
     * liste a changé
     */
    public boolean remove(Integer d) {
        
        if(contains(d)){
            CellInteger a= this.head.getNext(); 
            CellInteger b= this.head; 
            if (b.getData()==d ){
                this.head= a; 
                return true;
            }
            else{
                while(a.getData() != d){
                    a=a.getNext(); 
                    b=b.getNext(); 
                }
                b.setNext( a.getNext()) ; 
                return true; 
            }
        }
        else{
        return false;
        }
    }

    /**
     * Renvoie l'element en position idx
     */
    public Integer get(int idx) {
        int i =0; 
        CellInteger o = this.head; 
        if(this.size()< i){
            return null; 
        } 
        else{
            while(i<idx){
                o=o.getNext(); 
                i++; 
            }
            
        }
        return o.getData(); 
    }

    /**
     * Affecte l'element en position idx à d et renvoie l'ancienne valeur
     */
    public Integer set(int idx, Integer d) {
        if(this.size()>= idx){
            CellInteger i = this.head; 
            int count = 0;
            while(count != idx){
                count ++; 
                i=i.getNext(); 
            }
            i.setData(d);
            return get(idx); 
        }
        else{
            return null; 
        }
    }

    /**
     * Enlève la cellule en tête de liste et la renvoie
     */
    public Integer removeFirst() {
        Integer i= this.head.getData();
         
        if (this.remove(i)){
            return  i; 
        }


    }

    public static void main(String[] args) {
        ListInteger list = new ListInteger(); 
        list.addFirst(5); 
        list.add(6);
        list.add(7); 
        String s = list.toString(); 
        System.out.println(s); 
    }
}
