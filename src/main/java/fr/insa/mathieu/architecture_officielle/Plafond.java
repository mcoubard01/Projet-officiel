/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle; //test commentaire

/**
 *
 * @author stard
 */
public class Plafond {
    private String id;
    private double surface_plaf;
    private double prix_plaf;
    private Revêtement revêtement_plaf;

// FUNCTION
public boolean contrôle(Revêtement r){
    boolean result=(r.getPourPlafond()).equals("1");
    return result;
}
}
