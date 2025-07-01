package logica;
import java.time.LocalDate;
import java.util.*;

public class BubbleSort {
        
    public static void sort(ArrayList<Map.Entry<String,Integer>> list){
        int n = list.size();

        for(int i = 0; i < n - 1; i++){
            for(int j = 0; j < n - i - 1; j++){
                swap(list, j);
            }
        }
    }
    

    private static void swap(ArrayList<Map.Entry<String,Integer>> list, int j){
        
        if(list.get(j).getValue() > list.get(j + 1).getValue()){
            Map.Entry<String, Integer> temp = list.get(j);
            list.set(j,list.get(j + 1));
            list.set(j + 1, temp);   
        }
    }
    
    public static void sortMesDatos(ArrayList<Map.Entry<String, MesDatos>> lista){
    	
    	int n = lista.size();
    	
    	for(int i = 0; i < n - 1; i++){
    		for (int j = 0; j < n - i - 1; j++){
    			
    			LocalDate fecha1 = LocalDate.parse(lista.get(j).getKey());
    			LocalDate fecha2 = LocalDate.parse(lista.get(j + 1).getKey());
    			
    			if(fecha1.isAfter(fecha2)){
    				// Intercambiar posiciones
    				
    				Map.Entry<String, MesDatos> temp = lista.get(j);
    				lista.set(j, lista.get(j + 1));
    				lista.set(j + 1, temp);
    			}
    		}
    	}
    }
}