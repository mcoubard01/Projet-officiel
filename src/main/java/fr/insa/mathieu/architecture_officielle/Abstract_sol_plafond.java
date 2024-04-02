/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;
import static fr.insa.mathieu.architecture_officielle.Mur.longueur;
/**
 *
 * @author oscar
 */
public abstract class Abstract_sol_plafond {
    
    private String id;
    private Coin supg;// coin supérieur gauche
    private Coin supd;// coin supérieur droit
    private Coin infg;// coin inférieur gauche
    private Revêtement revêtement_sol;
    Revêtement standard = new Revêtement(1);

    public Abstract_sol_plafond(String id, Coin supg, Coin supd, Coin infg, Revêtement revêtement_sol) {
        this.id = id;
        this.supg = supg;
        this.supd = supd;
        this.infg = infg;
        this.revêtement_sol = revêtement_sol;
    }

    public Abstract_sol_plafond(Coin supg, Coin supd, Coin infg, Revêtement revêtement_sol) {
        this.id = "idc";// a remplir automatiquement 
        this.supg = supg;
        this.supd = supd;
        this.infg = infg;
        this.revêtement_sol = revêtement_sol;// la fonction de mathieu a redéfinir dans sol et plafond 
    }
    
      public static double surface (Coin supg, Coin supd, Coin infg){
      double surface = longueur(supg,supd)*longueur (supg,infg);
      return surface; 
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

    public void setRevêtement_sol(Revêtement revêtement_sol) {
        this.revêtement_sol = revêtement_sol;
    }


    
    
    
}
