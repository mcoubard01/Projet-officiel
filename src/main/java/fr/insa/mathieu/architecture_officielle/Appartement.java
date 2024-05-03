/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;

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
        this.id = IDManager.newId(this);
        this.etage = etage;
        this.liste_pièce=new ArrayList<>();
    }
    
    //FUNCTION
    public void add_pièce(Pièce pièce){
        if (pièce.getAppartement()!=this){
            if(pièce.getAppartement()==null){
                this.liste_pièce.add(pièce);//On ajoute la pièce à la liste des pièce de cet appartement
            }
            else{
                throw new Error ("La pièce appartient déjà à un autre appartement"); // TO DO affichage de message d'erreur dans l'interface graphique
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
    
    //GET
    public Etage getEtage() {
        return etage;
    }
    public ArrayList<Pièce> getListe_pièce() {
        return liste_pièce;
    }  
}
