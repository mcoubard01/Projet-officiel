/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;
import java.util.ArrayList;

public class Pièce {
    private String id;
    private ArrayList <Mur> liste_mur;
    private double surface;
    private Revêtement revêtement;
    private double prix;
    

    public Pièce(ArrayList<Mur> liste_mur) {
        this.liste_mur = liste_mur;
    }

    public ArrayList<Mur> getListe_mur() {
        return liste_mur;
    }

    // GET
    public double getSurface() { // avoir la possibilité de retrouver la surface
        return surface;
    }

    public Revêtement getRevêtement() { // avoir la possibilité de reprendre le revêtement
        return revêtement;
    }

    public double getPrix() { 
        return prix;
    }

    // SET
    public void setListe_mur(ArrayList<Mur> liste_mur) {
        this.liste_mur = liste_mur;
    }

    public void setRevêtement(Revêtement revêtement) {
        this.revêtement = revêtement;
    }
    
    
    
    
    
}
