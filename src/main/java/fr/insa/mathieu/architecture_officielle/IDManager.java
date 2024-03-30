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
    static int currentWallId = 0;
    public static void addID(Object obj){  //obj peut-être un mur, un étage, une pièce...     
        
           
        if (obj instanceof Etage){  
        } 
        
        if (obj instanceof Mur){
            listeDeMurs.add(currentWallId);
            int id = currentWallId;
            currentWallId++;
            System.out.println("size of ArrayList is " + listeDeMurs.size() + ", id of wall is : " + id);
            
            
        } 
        if (obj instanceof Pièce){}
        if (obj instanceof Plafond){}
        if (obj instanceof Sol){}
        
        
        
    }
    
    public static int IdDuDernierMur(){
        return listeDeMurs.size()-1;
    }
    
    public static void main(String[] args){
        Mur mur1 = new Mur();
        addID(mur1);
    }
    
    
}
