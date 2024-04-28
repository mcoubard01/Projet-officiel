/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle; //test commentaire
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author stard
 */
public class Plafond extends sol_plafond{
    
    //private String id;
    private Coin supg;// coin supérieur gauche
    private Coin supd;// coin supérieur droit
    private Coin infg;// coin inférieur gauche
    private Coin infd;
    
    private Revêtement revêtement_plaf;
public Plafond(List<Coin> coins){
        super (coins);
         Revêtement revêtement_standard = new Revêtement(9999);// le 9999 permet de tester avec le programme que Thomas avit créé quand on veut éviter de lire le fichier 
        this.revêtement_plaf=revêtement_standard;
        
    }
//CONSTRUCTOR
    public Plafond(Coin supg, Coin supd, Coin infg, Coin infd, Revêtement revêtement_sol) {
        super(supg, supd, infg, infd, revêtement_sol);
    }
        
// FUNCTION
    //@Override
  /*  public boolean contrôle(Revêtement r){
    boolean result=(r.getPourPlafond()).equals("1");
    return result;
}
*/
    // GET

    public Revêtement getRevêtement_plaf() {
        return revêtement_plaf;
    }
  
     public static void main(String []args ){
         List<Coin> coins = new ArrayList<>();
        coins.add(new Coin( "coin1",0, 0));
        coins.add(new Coin("coin2",0, 2));
        coins.add(new Coin("coin3",2, 2));
        Plafond plafond = new Plafond(coins);
         System.out.println ("est c e que ca marche");
         System.out.println("La surface calculée est : " + plafond.trouverEtCalculerSurface());
        System.out.println("le prix est de : "+ plafond.trouverEtCalculerPrix());
     }
    
    
    
    
    
    
    
    
    
    
    
    
}
