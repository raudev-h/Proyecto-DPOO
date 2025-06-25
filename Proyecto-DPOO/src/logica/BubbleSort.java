package logica;
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
}