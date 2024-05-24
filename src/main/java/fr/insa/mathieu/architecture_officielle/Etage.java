/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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
    private ArrayList<Facade> liste_mur_facade;//TODO a modifier avec liste Facade

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
        
        this.liste_mur_facade=new ArrayList<>();
        //System.out.println("map size is" + mapEtage.size() );        
       // System.out.println("id is" + id );
    }
    public Etage(double hauteur){
        this.id=IDManager.newId(this);
        this.hauteur_etage = hauteur;
        this.liste_appartement = new ArrayList<>();
        this.listPièceOrpheline=new ArrayList<>();
        this.liste_mur_facade=new ArrayList<>();
        this.listMurOrphelin=new ArrayList<Mur>();
    }
    
    //TODO : une fonction qui détecte sur quel niveau on se trouve actuellement dans l'éxécution. incrémenter le niveau à chaque nouveau niveau

    
//FUNCTIONS
    public void add(Facade facade){//TODO a
        this.liste_mur_facade.add(facade);
    }

    
    public void addOrphelin(Mur mur){
        this.listMurOrphelin.add(mur);
    }
    
    public double prix(){
        double prix = 0;
        for(Appartement appartement:this.liste_appartement){
            prix = prix + appartement.prix();
        }
        for (Pièce pièce : this.getListPièceOrpheline()){
            prix = prix + pièce.prix();
        }
        return prix;
    }
    
    /**
     * addOrphelin est appelé par le constructeur Mur(coin, coin, étage) afin de le déclarer comme orphelin 
     * SEULEMENT SI pièce1 est null 
     * @param mur 
     */

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
    
    /**
     * merci de ne pas faire de changement substantiel dans la syntaxe des toStringSauvegarde()
     * //////////Attention : cette syntaxe est utiulisée dans IDManager.récupérerUnEtage() !!!!
     * //////////Si on change la syntaxe de etage.toStringSauvegarde(), il faut changer la méthode susdite.
     * @return String
     */
    public static String syntaxeToString(){
        return "#Syntaxe : \"Etage;id;hauteur_etage; liste_mur_facade (id);liste_appartement (id) ;listPiècesOrphelines (id);listeMurOrphelin (id)\"";
        // \" permet d'afficher le caractère 'guillemet' dans le String.
                //merci de ne pas faire de changement substantiel dans la syntaxe des toString()
    }
    /**ceci est le toString() de sauvegarde.
    *MERCI DE NE PAS MODIFIER CETTE FONCTION sans me consulter
    * @return MurEnString : String
    */
    public String toStringSauvegarde() {
        //#Syntaxe : #Syntaxe : \"Etage;id;hauteur_etage; liste_mur_facade (id);liste_appartement (id) ;listPiècesOrphelines (id);listeMurOrphelin (id)\"
        ArrayList<Integer> listeDesIdDesFacades = new ArrayList<>();
        for (int i = 0; i < liste_mur_facade.size() ; i++){
            listeDesIdDesFacades.add(liste_mur_facade.get(i).getId());
            //créée une liste des identifiants des Facades qui forment l'étage
        }
        ArrayList<Integer> listeDesIdDesAppartements = new ArrayList<>();
        for (int i = 0; i < this.liste_appartement.size() ; i++){
            listeDesIdDesAppartements.add(liste_appartement.get(i).getId());
            //créée une liste des identifiants des appartements qui forment l'étage
        }
        ArrayList<Integer> listeDesIdDesPiècesOrphelines = new ArrayList<>();
        for (int i = 0; i < this.listPièceOrpheline.size() ; i++){
            listeDesIdDesPiècesOrphelines.add(listPièceOrpheline.get(i).getId());
            //créée une liste des identifiants des pièces orphlines de l'étage
        }
        ArrayList<Integer> listeDesIdDesMursOrphelins = new ArrayList<>();
        for (int i = 0; i < this.listMurOrphelin.size() ; i++){
            listeDesIdDesMursOrphelins.add(listMurOrphelin.get(i).getId());
            //créée une liste des identifiants des murs orphelins de l'étage
        }
        return "Etage;" + this.id + ";" + this.hauteur_etage + ";" + listeDesIdDesFacades 
                + ";" + listeDesIdDesAppartements 
                + ";" + listeDesIdDesPiècesOrphelines
                + ";" + listeDesIdDesMursOrphelins;
    }
    
    @Override
    public String toString() {
        //Syntaxe : voir et actualiser au besoin la méthode syntaxeToString()
        return "Etage; id : " + this.id + ";hauteur : " + this.hauteur_etage + ";liste_appartement=" + this.liste_appartement+";ListePièceOrpheline="+this.listPièceOrpheline + ";Liste des murs orphelins" + this.listMurOrphelin;
    }
    
    //TODO TODO fonction add pour ajouter un appartement à l'étage
    public void dessine(GraphicsContext context){
        for (Appartement appartement : this.liste_appartement){
            appartement.setPaint(new Color(Math.random(), Math.random(), Math.random(), Math.random()));
            appartement.dessine(context);
        }
        this.dessine_limiteEtage(context);
        for(Pièce pièce:this.listPièceOrpheline){
            pièce.dessine(context);
        }
         System.out.println("liste des murs orphelins : "+listMurOrphelin);
         //problème: la liste des murs orhpelins est vide, alors que chaque création de new Mur(coin,coin,etage) est censée ajouter automatiquement ce nouveau mur à la liste susdite.
         //idée de source du problème : 
         //dans le contrôleur, l'étage actuel est mal géré (18/05/24,branch Thomas)
        for(Mur mur:this.listMurOrphelin){
            System.out.println("dessin des murs orphelins.");
            System.out.println("mur.toString() :"+mur.toString());
            mur.dessine(context);
        }
    }
    public void dessine_limiteEtage(GraphicsContext context){
        //context.setStroke(Color.INDIGO);
            //On dessine avec facade.dessine, plutôt que de réécrire Facade.dessint(), comme on le faisiat jusque là
        for(Mur mur:this.liste_mur_facade){
            mur.dessine(context);
            //context.strokeLine(facade.getDebut().getX(), facade.getDebut().getY(), facade.getFin().getX(), facade.getFin().getY());
        }
    }
    //TODO peut être à enlever le highlight
    void highlight(GraphicsContext context) {
        
    }
    
// GET
    public double getHauteur_etage() {
        return hauteur_etage;
    }
    public double getPrix_etage() {
        return this.prix(); 
    }
    public ArrayList<Pièce> getListPièceOrpheline() {
        return listPièceOrpheline;
    }

    public ArrayList<Mur> getListMurOrphelin() {
        return listMurOrphelin;
    }
    public ArrayList<Facade> getListe_mur_facade() {//TODO possibilité de mettre dans l'ID Manager ses attributs
        return liste_mur_facade;
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


