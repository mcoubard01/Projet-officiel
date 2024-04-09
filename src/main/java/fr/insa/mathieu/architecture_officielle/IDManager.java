/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;
import java.util.*;


/**
 *
 * @author thomas beverly
  branche de thomas
 */
public class IDManager {
    static HashMap<String,Integer> mapEtage = new HashMap<String,Integer>(); //pourquoi un hashmap? je ne sais pas. d'après ce que je comprends, on ne peut pas créer de "map = new Map<>()" (Map est abstraite et ne peut doc être instanciée, d'après le messsge de debug
    //Je veux une map pour chaque classe
    //Qui prend en entrée un object, et lui associe un identifiant
    //Dans les constructeurs des objets, on pourra assigner l'attribut id à la valeur associée à this, l'obejt en question.
    //Ainsi, dans le constructeur, l'attribut Id se verra assigner l'int associé à l'objet dans la map : "this.id=mapObjet.get(this)"
    //on pourra récupérer l'ID d'un objet grâce à obj.getId()
    //on crééra une méthode qui permet d'incrémenter l'ID dans le map (située à  l'intérieur de IDManager) voir p.ex. la méthode addId ci-dessous
    //
    
    /*
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
    */
    public static void main(String[] args){
        /*Mur mur1 = new Mur();
        addID(mur1);
*/
        Etage etage1 = new Etage(5);
        mapEtage.put("hello",100);
        mapEtage.put("hi",100);
        //System.out.println("key 100 is assoc to " + mapEtage.get(100));
        System.out.print(mapEtage);
    }
    
    
}
