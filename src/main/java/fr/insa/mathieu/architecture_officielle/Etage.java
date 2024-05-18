/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author stard
 */
/*

*/
public class Etage {
    private double hauteur_etage;
    private ArrayList<Appartement> liste_appartement;
    private int niveau; //niveau ne sert à rien? on supprime?  signé thomas
    private int id;
    private Architecture_officielle batiment;
    
    private ArrayList<Pièce> listPièceOrpheline;
    private ArrayList<Mur> listMurOrphelin;
    private ArrayList<Mur> liste_mur;//les 4 murs délimitant l'étage TODO reprendre ces murs pour recuperer les coordonnee dans facade. 

//2eme option pour l'ID : au lieu de maps dans le IDManager, simplement créer la variable ci dessous et l'incrémenter directment dans le condtructeur
    //private static int compteurID =0; //pourquoi static? voir l'explication en commentaire dans IDManager
//--------------    
//dansle constructeur : 
    //this.id = compteurID;
    //compteurID++;
    //---
  
//CONSTRUCTOR

    public Etage(double hauteur,Architecture_officielle batiment) { 
        this.id=IDManager.newId(this);
        this.hauteur_etage = hauteur;
        this.liste_appartement = new ArrayList<>();
        this.batiment=batiment;
        batiment.add(this);
        this.listPièceOrpheline=new ArrayList<Pièce>();
        this.listMurOrphelin=new ArrayList<Mur>();
        
        this.liste_mur=new ArrayList<>();
        //System.out.println("map size is" + mapEtage.size() );        
       // System.out.println("id is" + id );
    }
    public Etage(double hauteur){
        this.id=IDManager.newId(this);
        this.hauteur_etage = hauteur;
        this.liste_appartement = new ArrayList<>();
        this.listPièceOrpheline=new ArrayList<>();
        this.liste_mur=new ArrayList<>();
        this.listMurOrphelin=new ArrayList<Mur>();
    }
    
    //TODO : une fonction qui détecte sur quel niveau on se trouve actuellement dans l'éxécution. incrémenter le niveau à chaque nouveau niveau

    
//FUNCTIONS
    public void add(Mur mur){
        this.liste_mur.add(mur);
    }
    
    /**
     * addOrphelin est appelé par le constructeur Mur(coin, coin, étage) afin de le déclarer comme orphelin 
     * SEULEMENT SI pièce1 est null 
     * @param mur 
     */
    public void addOrphelin(Mur mur){
        this.listMurOrphelin.add(mur);
    }
    
    //toString1() sert à afficher autre chose que le toString() par défaut
    public String toString1() {
        String résultat ;
        résultat = "Etage n." + this.getId() + "(id), hauteur: " + hauteur_etage + " {\n";
        //méthode d'itération vue sur https://www.geeksforgeeks.org/iterate-map-java/?ref=ml_lbp
        for (Mur murATester : IDManager.mapMur.keySet()){ //la méthode keySet() permet de tester pour chaque clé (ici, des murs)
            if (murATester.getÉtage().getId() == this.getId()){ //"Si le mur-clé est sur l'étage actuel"
                résultat += murATester.toString() + "\n";
            }
        }
        résultat += "\n}\n";
        return résultat;
    }
    
    public static String syntaxeToString(){
        return "#Syntaxe : \"Etage;id;hauteur_etage;liste_appartement\"";
        // \" permet d'afficher le caractère 'guillemet' dans le String.
                //merci de ne pas faire de changement substanciel dans la syntaxe des toString()

    }
    @Override
    public String toString() {
        //Syntaxe : voir et actualiser au besoin la méthode syntaxeToString()
        return "Etage;" + this.id + ";" + this.hauteur_etage + ";liste_appartement=" + this.liste_appartement+";ListePièceOrpheline="+this.listPièceOrpheline;
    }
    
    //TODO TODO fonction add pour ajouter un appartement à l'étage
    public void dessine(GraphicsContext context){
        for (Appartement appartement : this.liste_appartement){
            appartement.dessine(context);
        }
        this.dessine_limiteEtage(context);
        for(Pièce pièce:this.listPièceOrpheline){
            pièce.dessine(context);
        }
        for(Mur mur:this.listMurOrphelin){
            mur.dessine(context);
        }
    }
    public void dessine_limiteEtage(GraphicsContext context){
        context.setStroke(Color.INDIGO);
        for(Mur mur:this.liste_mur){
            context.strokeLine(mur.getDebut().getX(), mur.getDebut().getY(), mur.getFin().getX(), mur.getFin().getY());
        }
    }
    void highlight(GraphicsContext context) {
        
    }
    
// GET
    public double getHauteur_etage() {
        return hauteur_etage;
    }
    public double getPrix_etage() {
        double prix_etage=0;// TODO A COMPLETER (mis 0 car pour le moment pas fait)
        return prix_etage; 
    }
    public ArrayList<Pièce> getListPièceOrpheline() {
        return listPièceOrpheline;
    }

    public ArrayList<Mur> getListMurOrphelin() {
        return listMurOrphelin;
    }
    public ArrayList<Mur> getListe_mur() {//TODO possibilité de mettre dans l'ID Manager ses attributs
        return liste_mur;
    }
    
    
    

    
    public int getId(){
        return id;
    }
    public ArrayList<Appartement> getListe_appartement() {
        return liste_appartement;
    }
    public int getNiveau() {
        return niveau;
    }
    public Architecture_officielle getBatiment() {
        return batiment;
    }
    

  /*  QUELLE est l'UTILITE ??????
    public int getId(){
        return id;
    }
    */ 
// SET
    public void setHauteur_etage(double hauteur_etage) {
        this.hauteur_etage = hauteur_etage;
    }
    public void setListe_appartement(ArrayList<Appartement> liste_appartement) {
        this.liste_appartement = liste_appartement;
    }
    public void addAppartement(Appartement appartement){
        this.liste_appartement.add(appartement);
    }
    public void setNiveau(int niveau) { // Pour pouvoir par exemple mettre un niveau -1
        this.niveau = niveau;
    }
   
    /*
    public static void main(String[] args){
        Etage etage1 = new Etage(5);
        Etage etage2 = new Etage(6);
        System.out.println("hauteur of etage1 is " + etage1.getEtage());
        //System.out.println("id of etage2 is " + etage2.getId());

    }
    */

    
}


