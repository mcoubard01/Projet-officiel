/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author stard
 */
/*

*/
public class Etage {
    private int hauteur_etage;
    private ArrayList<Mur> liste_mur;
    //private int niveau;
    private int id;
    
//2eme option pour l'ID : au lieu de maps dans le IDManager, simplement créer la variable ci dessous et l'incrémenter directment dans le condtructeur
    //private static int compteurID =0; //pourquoi static? voir commentaire dans IDManager
//--------------    
//dansle constructeur : 
    //this.id = compteurID;
    //compteurID++;
    //---

    
    
    
    
//CONSTRUCTOR
    public Etage(int hauteur) { 
        this.id=IDManager.newId(this);
        //this.id = MapEtage.setIdInMapEtage(this);
        this.hauteur_etage = hauteur;
        this.liste_mur=new ArrayList<Mur>();
        //System.out.println("map size is" + mapEtage.size() );        
        System.out.println("id is" + id );
    }
    
    //TODO : une fonction qui détecte sur quel étage on se trouve actuellement dans l'éxécution.

    
//FUNCTIONS
    
    
// GET
    public int getHauteur_etage() {
        return hauteur_etage;
    }
    public double getPrix_etage() {
        double prix_etage=0;// TO DO A COMPLETER (mis 0 car pour le moment pas fait)
        return prix_etage; 
    }

    public ArrayList<Mur> getListe_mur() {
        return liste_mur;
    }
    public int getId(){
        return id;
    }
    
    
  
    
// SET
    public void setHauteur_etage(int hauteur_etage) {
        this.hauteur_etage = hauteur_etage;
    }
    
   
    
    public static void main(String[] args){
        Etage etage1 = new Etage(5);
        Etage etage2 = new Etage(6);
        System.out.println("id of etage1 is " + etage1.getId());
        System.out.println("id of etage2 is " + etage2.getId());

    }
}


