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
public abstract class sol_plafond {  //ça marche même si ce n'est pas une majuscule?
    
    private String id;
    private Coin supg;// coin supérieur gauche
    private Coin supd;// coin supérieur droit
    private Coin infg;// coin inférieur gauche
    private Coin infd;// coin inférieur droit
    private Revêtement revêtement_sol;
    
    Revêtement standard = new Revêtement(1);//Revêtement standard

    public sol_plafond(String id, Coin supg, Coin supd, Coin infg,Coin infd, Revêtement revêtement_sol) {
        this.id = id;
        this.supg = supg;
        this.supd = supd;
        this.infg = infg;
        this.infd = infd;
        this.revêtement_sol = revêtement_sol;
    }

    public sol_plafond(Coin supg, Coin supd, Coin infg,Coin infd, Revêtement revêtement_sol) {
        this.id = "idc";// a remplir automatiquement 
        this.supg = supg;
        this.supd = supd;
        this.infg = infg;
        this.infd = infd;
        this.revêtement_sol = revêtement_sol;// la fonction de mathieu a redéfinir dans sol et plafond 
    }
    
    //FUNCTION
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
    public Coin getSupd() {
        return supd;
    }
    public Coin getInfg() {
        return infg;
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
    public void setRevêtement_sol(Revêtement revêtement_sol) {
        this.revêtement_sol = revêtement_sol;
    }


    
    
    
}
