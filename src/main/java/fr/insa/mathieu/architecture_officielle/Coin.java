/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.yava to edit this template
 */
package fr.insa.mathieu.architecture_officielle;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * je serai étonné de l'utilité d'avoir des attributs sol pièce et plafond à par si utilisées ensuite 
 * 
 * @author stard
 */

/**
     * REMARQUE
     * Pour que tes fonctions fonctionnent OScar a du créer ces attributs mais je suis très perplexe de relier directement :
     * Le coin à ton plafond, ton sol, ta pièce
     * Je serai passer par les murs pour obtenir les points, les fonctions auraient été plus courte et avec moins d'attributs.
     * Pour le moment on laisse comme ça si ça fonctionne mais il faudra modifier ça à un moment pour que ce soit plus propre...
     */
    
public class Coin {
    public static double RAYON_IN_DRAW=5;
    private int id; 
    private double x;
    private double y;
    

    //CONSTRUCTOR
    public Coin(double x, double y) {
        this.id = IDManager.newId(this);
        this.x = x;
        this.y = y;
    }
    // Juste un constructeur test qui ne sert à rien en tant que tel dans le code
    public Coin(){
        //this.id = IDManager.newId(this);
    }
   
   //FUNCTIONS
   
   //le but de toString1 est de permettre d'écrire une syntaxe toString() différente pou d'autres utilisations. 
    public String toString1() {
        return "Coin: "+this.id +"{ x=" + x + ", y=" + y + '}';
    }
    
    public static String syntaxeToString(){
                //merci de ne pas faire de changement substanciel dans la syntaxe des toString()
        return "#Syntaxe  : \"Coin;id;x;y\"";
        //////////Attention : cette syntaxe est utiulisée dans IDManager.récupérerUnCoin() !!!!
        //////////Si on change la syntaxe de coin.toString(), il faut changer la méthode susdite.
    }
    @Override
    //ceci est le toString() par défaut.
    public String toString() {
        //Syntaxe : "Coin;id;x;y"
        return "Coin;" + this.id + ";" + x + ";" + y ;
    }
    public void dessine(GraphicsContext context){
        context.setFill(Color.BLACK);
        context.fillOval(this.x-RAYON_IN_DRAW, this.y-RAYON_IN_DRAW, 2*RAYON_IN_DRAW, 2*RAYON_IN_DRAW);
    }
    
    // GET
    public int getId() {
        return id;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    

    // SET
    public void setId(int id) {
        this.id = id;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    
}
