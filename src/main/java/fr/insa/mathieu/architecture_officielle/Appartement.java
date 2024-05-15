/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;
//sx
import java.util.ArrayList;

/**
 *
 * @author stard
 */
public class Appartement {
    private Etage etage;
    private ArrayList<Pièce> liste_pièce; // avec le même étage
    private int id;
    
    //CONSTRUCTOR
    public Appartement(Etage etage) {
        
        this.etage = etage;
        this.liste_pièce=new ArrayList<>();
        this.id = IDManager.newId(this);
    }
    
    //FUNCTION
    public void add_pièce(Pièce pièce){
        if (pièce.getAppartement()!=this){
            if(pièce.getAppartement()==null){
                this.liste_pièce.add(pièce);//On ajoute la pièce à la liste des pièce de cet appartement
            }
            else{
                throw new Error ("La pièce appartient déjà à un autre appartement"); // TODO TO DO affichage de message d'erreur dans l'interface graphique
            }
            
        }
        else {
            System.out.println("La pièce est déjà dans l'appartement");
        }   
    }
    public double prix(){
        double prix=0;//initialisation du prix
        for (int i=0; i<this.liste_pièce.size();i++){
            prix = prix+this.liste_pièce.get(i).prix(); //le prix de la pièce à l'indice 'i' de l'appartement en question
        }
        return prix;
    }

    public static String syntaxeToString(){
        return "#Syntaxe : \"Appartement;id;IdDUEtage;liste_pièce(identifiants)\"";
    }
    @Override
    public String toString() {
        //Syntaxe : voir syntaxeToString(), et l'actualiser siu besoin 
        ArrayList<Integer> listeDesIdDesPièces = new ArrayList<>();
        for (int i=0 ; i< listeDesIdDesPièces.size() ; i++){
            listeDesIdDesPièces.add(liste_pièce.get(i).getId());
        }
        return "Appartement;" + id + ";" + etage.getId() + ";liste_pièce=" + listeDesIdDesPièces;
        //éventuellement il  n'y aura pas besoin de etage.getId(), puisque l'id de l'appartement intègre déjà l'id de l'étage.
    }
    
    
    
    //GET
    public Etage getEtage() {
        return etage;
    }
    public ArrayList<Pièce> getListe_pièce() {
        return liste_pièce;
    }  
    public int getId(){
        return id;
    }
    
    //SET 
//!!pas de setId() !!!
    
    public void setEtage(Etage etage) {
        this.etage = etage;
    }

    public void setListe_pièce(ArrayList<Pièce> liste_pièce) {
        this.liste_pièce = liste_pièce;
    }
    
}
