/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;
import java.util.*;


/**
 * @version 1.1
 * @author thomas beverly
  branche de thomas
 */

public class IDManager {
    //Je veux une map pour chaque classe
    //Qui prend en entrée un object, et lui associe un identifiant
    //Dans les constructeurs des objets, on pourra assigner l'attribut id à la valeur associée à this, l'obejt en question.
    //Ainsi, dans le constructeur, l'attribut Id se verra assigner l'int associé à l'objet dans la map : "this.id=mapObjet.get(this)"
    //on pourra récupérer l'ID d'un objet grâce à obj.getId()
    //on crééra une méthode qui permet d'incrémenter l'ID dans le map (située à  l'intérieur de IDManager) voir p.ex. la méthode addId ci-dessous
    
//pour les Appartments
    //static private HashMap<Mur,Integer> mapAppartement = new HashMap<>();
    //static int compteurAppartement = 0;
    
    
//pour les étages
    static private HashMap<Etage,Integer> mapEtage = new HashMap<>(); //pourquoi un hashmap? je ne sais pas. d'après ce que je comprends, on ne peut pas créer de "map = new Map<>()" (Map est abstraite et ne peut doc être instanciée, d'après le messsge de debug
    static int compteurEtage = 0;
    
//pour les pièces
    static private HashMap<Pièce,Integer> mapPièce = new HashMap<>();
    static int compteurPièce = 0;  //TODO faire une méthode où la pièce a le numéro de l'étage devant
    
//pour les murs
    static private HashMap<Mur,Integer> mapMur = new HashMap<>();
    static int compteurMur = 0;
    
//pour les coins
    static private HashMap<Coin,Integer> mapCoin = new HashMap<>();
    static int compteurCoin = 0;
    
//pour les fenêtres
    static private HashMap<Fenêtre,Integer> mapFenêtre = new HashMap<>();
    static int compteurFenêtre = 0;
    
//pour les portes
    static private HashMap<Porte,Integer> mapPorte = new HashMap<>();
    static int compteurPorte = 0;
    
    

    
    /* POURQUOI "STATIC" ? voir ci-dessous : 
    //excellente explication de static https://www.freecodecamp.org/news/static-variables-in-java/ 
    //"Whenever a variable is declared as static, this means there is only one copy of it for the entire class,
    rather than each instance (object of the class) having its own copy. 
    A static method means it can be called without creating an instance of the class. "
    c'est exactement ce qu'on recherche!! compteurID sera une varaible qui ne dépend pas des objets créés.
    */

    
    
    //CONSTRUCTOR
    //pas de constructeurs dans cette classe. On ajoute un id en appelant IDManager.newId(Etage etage) (ou mur, ou fenêtre...)
    public IDManager(){} //contructeur test mais INUTILISé (fait acte de présence)
    
    
    //FUNCTIONS
    //j'avais en tête la structure suivante, mais soit instanceof ne fonctionne pas, soit "Object can't be converted to Etage"...
    /*
    public static int newId(Object obj){
        if (obj instanceof Etage){  
        mapEtage.put(obj,compteurEtage);
        compteurEtage++;
        return mapEtage.get(obj);
        }
    //répéter le test
    }
    */
    //sauf que ça ne fonctionne pas. pour l'instant, il faut faire chque classse manuellement.
    
    //TODO : serait -il utile de créer une Arraylist des maps, voire même une map de maps? à refléchir
    
    //même méthode que ci-dessous pour les appartements
    
    public static int newId(Etage etage){
        mapEtage.put(etage,compteurEtage); //rdc : 0 , 1er étage : 1 ,etc. 
        compteurEtage++;
        return mapEtage.get(etage);
    }
    
    public static int newId(Pièce pièce){
        int idDeLaPièce = compteurMur + 1000* pièce.getListe_mur()[0].getÉtage().getId(); //étage auquel la pièce se trouve
//ainsi, pour l'étage ézéro, 0<=idDeLaPièce<=999 ; pour l'étage 1, 1000<= idDeLaPièce <=1999 ,etc.
        mapPièce.put(pièce,idDeLaPièce);
        compteurPièce++;
        return mapPièce.get(pièce);
    }
    
    public static int newId(Mur mur){
        int idDuMur = compteurMur + 1000*mur.getÉtage().getId();  
//ainsi, pour l'étage ézéro, 0<=idDuMur<=999 ; pour l'étage 1, 1000<= idDuMur <=1999 ,etc.
        mapMur.put(mur,idDuMur);
        compteurMur++;
        return mapMur.get(mur);
    }
    
    public static int newId(Coin coin){
        //TODO : à réfléchir : Peut-être coin devrait il utiliser un compteru similaire à mur? (id supérieur à 1000 pour le 1er étage...)
        mapCoin.put(coin,compteurCoin);
        compteurCoin++;
        return mapCoin.get(coin);
    }
    
    //il n'y a pas d'Id pour les ouvertures actuellement. Les méthodes ci-dessous n'ont donc pas d'utilité à ce jour (12/04/24)
    public static int newId(Fenêtre fenêtre){
        mapFenêtre.put(fenêtre,compteurFenêtre);
        compteurFenêtre++;
        return mapFenêtre.get(fenêtre);
    }
    
    public static int newId(Porte porte){
        mapPorte.put(porte,compteurPorte);
        compteurPorte++;
        return mapPorte.get(porte);
    }
    
    //GET
    
    
    
    //TODO : faire des méthodes permettant de manipuler la map (en fonction de nos besoins)
    //les maps sont actuellement en privé
    
    public boolean containsValue(int IdARechercher, String typeDeObjet){ //cette méthode permet de chercher dans une map une valeur d'identifiant
        switch (typeDeObjet){
            
            case "Etage": //TODO : idée : Ecrire le test tel que la fonction comprenne les paramètres Etage, Étage, étage, etage, etc. pour plus de facilité d'usage? (case-insensitive test)
                return mapEtage.containsValue(IdARechercher);
            //case "Appartement"
            case "Pièce":
                 return mapPièce.containsValue(IdARechercher);
            case "Mur":
                 return mapMur.containsValue(IdARechercher);
            case "Coin":
                 return mapCoin.containsValue(IdARechercher);
            case "Fenêtre":
                 return mapFenêtre.containsValue(IdARechercher);
            case "Porte":
                 return mapPorte.containsValue(IdARechercher);
        } 
        return false;
    }    
    
    //SET
    //pas de "set" car cela dérèglerait le compteur automatique
    
    
    
    
    public static void main(String[] args){
        
        
        Etage etage1 = new Etage(5);
        Mur mur1 = new Mur();
        mur1.setEtage(etage1);
        System.out.println("etage1 is assoc to " + mapEtage.get(etage1) +", which is its ID. "); //etage1.getId() fonctionne aussi, donc
        System.out.print("mur 1 has an Id of "+ mur1.getId());
    }
    
    
}
