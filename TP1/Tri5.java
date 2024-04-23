public class Tri{

// Fonction isSorted 
	public Boolean isSorted(int[] tab){
		int n=tab.length;  
		for(int i=0;i<n;i++){
			if(tab[i]>tab[i+1]){
				return false; 
				}
				}
			return true; 
			}
			
//A. Tri `a bulles (“bubble sort”)

	public void triBulles(int[] tab){
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
	
	
//B. Tri par insertion (“insertion sort”)

	public void triInsertion(int[] tab){
		int n =tab.length; 
		for(int j=n-2; j>1;i--){
			int k = tab[j];
			int i = j+1; 
			while(i<n && k>tab[i]){
				tab[i-1] = tab[i];
				i++; 
				}
			tab[i-1] = k; 
			}
			}	
			
//C. Tri par fusion (“merge sort”)

	public void triFusion(int[] tab, int j , int i ){
		if(i<j){
			int m =(i+j) /2; 
			triFusion(T, i, m);
			triFusion(T, m+1, j);
			fusion(T, i, m, j);
			}
			}
				

