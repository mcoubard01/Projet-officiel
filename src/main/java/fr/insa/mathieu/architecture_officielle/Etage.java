/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;

/**
 *
 * @author stard
 */
public class Etage {
    private int hauteur_etage;
    private double prix_etage;
    private double id;
    
//CONSTRUCTOR
    public Etage(int hauteur_etage) {
        this.hauteur_etage = hauteur_etage;
    }
    
//FUNCTIONS
    
// GET
    public int getHauteur_etage() {
        return hauteur_etage;
    }
    public double getPrix_etage() {
        return prix_etage; // A COMPLETER
    }

    public Etage(double id) {
        this.id = id;
    }
    
    
// SET
    public void setHauteur_etage(int hauteur_etage) {
        this.hauteur_etage = hauteur_etage;
    }
     
}
