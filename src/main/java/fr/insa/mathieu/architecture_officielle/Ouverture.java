/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;

/**
 *
 * @author stard
 */
public abstract class Ouverture {
    private double ori_x;
    private double ori_y;
    private char orientation;
    private Etage etage;

    //CONSTRUCTOR

    public Ouverture(double ori_x, double ori_y, char orientation, Etage etage) {
        this.ori_x = ori_x;
        this.ori_y = ori_y;
        this.orientation = orientation;
        this.etage = etage;
    }
    
    
    //FONCTION
    public abstract boolean appartenance(Mur mur);
    
    // GET
    public double getOri_x() {
        return ori_x;
    }
    public double getOri_y() {
        return ori_y;
    }
    public char getOrientation() {
        return orientation;
    }
    public Etage getEtage() {
        return etage;
    }
    
    
}
