/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;
import static fr.insa.mathieu.architecture_officielle.Mur.longueur;
import static java.lang.Math.sqrt;
/**
 *
 * @author stard
 */
public class Sol {
    private String id;
    private Coin supg;// coin supérieur gauche
    private Coin supd;// coin supérieur droit
    private Coin infg;// coin inférieur gauche
    private double prix_sol;
    private Revêtement revêtement_sol;
    
    public Sol(Coin supg, Coin supd, Coin infg){
        this.id ="idc";
        this.supg = supg;
        this.supd = supd;
        this.infg = infg;
    }

    
    
  public static double surface (Coin supg, Coin supd, Coin infg){
      double surface = longueur(supg,supd)*longueur (supg,infg);
      return surface; 
  }
     public Sol(Revêtement revêtement) {
        this.revêtement_sol = revêtement;
        // Il faudrait ajouter le prix, mais pour ça il faut la surface du mur
    }
    
     public double prix (){
        double prix= surface (this.getSupg(),this.getSupd() ,this.getInfg())* (this.getRevêtement_sol().getPrix_unitaire());
        return prix;
     }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Coin getSupg() {
        return supg;
    }

    public void setSupg(Coin supg) {
        this.supg = supg;
    }

    public Coin getSupd() {
        return supd;
    }

    public void setSupd(Coin supd) {
        this.supd = supd;
    }

    public Coin getInfg() {
        return infg;
    }

    public void setInfg(Coin infg) {
        this.infg = infg;
    }

    public Revêtement getRevêtement_sol() {
        return revêtement_sol;
    }



    
    
    
}
