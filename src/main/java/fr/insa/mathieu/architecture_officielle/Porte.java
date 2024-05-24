/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;
import static fr.insa.mathieu.architecture_officielle.Mur.longueur;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author stard
 */
public class Porte extends Ouverture {
    //private final double longueur=0.90;
    private final double hauteur =2.10;
    
    // CONSTRUCTOR
    /*
    public Porte(double ori_x, double ori_y, char orientation,Mur mur1, Mur mur2) {
        super(ori_x, ori_y,orientation,0.90,mur1, mur2);
    }
*/
    public Porte(double ori_x, double ori_y, char orientation, Mur mur) {
        super(ori_x, ori_y, orientation, 0.90, mur);
    }//la longueur 0.90 est utilisée dans MainPane.entrerOrigineFenêtre(). attention à ne pas la modifier.
    public Porte() {
    }
 
    //FUNCTION
    
  
    // GET

    
    
}
