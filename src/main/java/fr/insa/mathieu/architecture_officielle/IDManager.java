/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;
import java.util.*;


/**
 *
 * @author thomas beverly
 */
public class IDManager {
    
    private static ArrayList<Integer> listeDeMurs = new ArrayList<>();
    
    public static void addID(Object obj, int id){  //obj peut-être un mur, un étage, une pièce...     
        
           
        if (obj instanceof Etage){  
        }
        
        if (obj instanceof Mur){
            listeDeMurs.add(listeDeMurs.size()+1);
            System.out.println(listeDeMurs.size());
        } 
        if (obj instanceof Pièce){}
        if (obj instanceof Plafond){}
        if (obj instanceof Sol){}
        
        
        
    }
    
    public static void main(String[] args){
        Mur mur1 = new Mur();
        addID(mur1,3);
    }
    
    
}
