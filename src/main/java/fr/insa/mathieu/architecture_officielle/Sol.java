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
public class Sol extends Abstract_sol_plafond {
    private String id;
    private Coin supg;// coin supérieur gauche
    private Coin supd;// coin supérieur droit
    private Coin infg;// coin inférieur gauche
    // private double prix_sol; PAS BESOIN DE PRIX EN ATTRIBUT, C'EST SEULEMENT UNE FONCTION QUI RENVERRA UN DOUBLE
    private Revêtement revêtement_sol;
    Revêtement standard = new Revêtement(1); // Mise en place d'un revêtement standard
    
    public Sol(String id, Coin supg, Coin supd, Coin infg, Revêtement revêtement_sol) {
      super(id,supg,supd,infg,revêtement_sol);
    }
 public Sol(Coin supg, Coin supd, Coin infg, Revêtement revêtement_sol) {
      super(supg,supd,infg,revêtement_sol);
    }

}
