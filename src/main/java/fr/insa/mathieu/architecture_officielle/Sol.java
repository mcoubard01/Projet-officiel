/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;
import static fr.insa.mathieu.architecture_officielle.Mur.longueur;
/**
 *
 * @author stard
 */
public class Sol {
    private String id;
    private Coin supg;// coin supérieur gauche
    private Coin supd;// coin supérieur droit
    private Coin infg;// coin inférieur gauche
    // private double prix_sol; PAS BESOIN DE PRIX EN ATTRIBUT, C'EST SEULEMENT UNE FONCTION QUI RENVERRA UN DOUBLE
    private Revêtement revêtement_sol;
    Revêtement standard = new Revêtement(1); // Mise en place d'un revêtement standard
    
    // CONSTRUCTOR
    public Sol(Coin supg, Coin supd, Coin infg){
        this.id ="idc";
        this.supg = supg;
        this.supd = supd;
        this.infg = infg;
        this.revêtement_sol=standard;
        
    }
    public Sol(Coin supg, Coin supd, Coin infg, Revêtement revêtement_sol) {
        this.supg = supg;
        this.supd = supd;
        this.infg = infg;
        this.revêtement_sol = revêtement_sol;
    }
    

    
    // FONCTIONS
  public static double surface (Coin supg, Coin supd, Coin infg){
      double surface = longueur(supg,supd)*longueur (supg,infg);
      return surface; 
  }
    
     public double prix (){
        double prix= surface (this.getSupg(),this.getSupd() ,this.getInfg())* (this.getRevêtement_sol().getPrix_unitaire());
        return prix;
     }

    // GET 
     public String getId() {
        return id;
    }
    public Coin getSupg() {
        return supg;
    }
    public Coin getInfg() {
        return infg;
    }
    public Coin getSupd() {
        return supd;
    }
    public Revêtement getRevêtement_sol() {
        return revêtement_sol;
    }
    
    // SET
    public void setId(String id) {
        this.id = id;
    }
    public void setSupg(Coin supg) {
        this.supg = supg;
    }
    public void setSupd(Coin supd) {
        this.supd = supd;
    }
    public void setInfg(Coin infg) {
        this.infg = infg;
    }
}
