/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle; //test commentaire

/**
 *
 * @author stard
 */
public class Plafond extends sol_plafond{
    /*
    private String id;
    private Coin supg;// coin supérieur gauche
    private Coin supd;// coin supérieur droit
    private Coin infg;// coin inférieur gauche
    private Coin infd;
    */
    private Revêtement revêtement_plaf;

//CONSTRUCTOR
    public Plafond(Coin supg, Coin supd, Coin infg, Coin infd, Revêtement revêtement_sol) {
        super(supg, supd, infg, infd, revêtement_sol);
    }
        
// FUNCTION
    @Override
    public boolean contrôle(Revêtement r){
    boolean result=(r.getPourPlafond()).equals("1");
    return result;
}
    // GET

    public Revêtement getRevêtement_plaf() {
        return revêtement_plaf;
    }
    
}
