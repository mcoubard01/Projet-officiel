/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.yava to edit this template
 */
package fr.insa.mathieu.architecture_officielle;

import java.util.ArrayList;

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
    private int id; 
    private int x;
    private int y;
    private String nom_coin; // il faudra le replacer par la suite car cela correspond un  l'identifiant 
    private Pièce pièce; 
    private Plafond plafond; 
    private Sol sol;

    //CONSTRUCTOR
    public Coin(int x, int y) {
        this.id = IDManager.newId(this);
        this.x = x;
        this.y = y;
    }
    // Juste un constructeur test qui ne sert à rien en tant que tel dans le code
    public Coin(){
        this.id = IDManager.newId(this);
    }
   public Coin(String nom_coin, int x, int y) {
       this.nom_coin = nom_coin;
        this.x = x;
        this.y = y;
    }
   
   //FUNCTION
   @Override
    public String toString() {
        return "Coin: "+nom_coin+"{ x=" + x + ", y=" + y + '}';// le nom_coin correspond a l'identifiant 
    }   
    
    // GET
    public int getId() {
        return id;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public String getNom_coin() {
        return nom_coin;
    }
    //pas sur de l'utilité de ça du tout ça m'étonnerait énormément!
    public Pièce getPièce() {
        return pièce;
    }
    public Sol getSol() {
        return sol;
    }
    public Plafond getPlafond() {
        return plafond;
    }

    // SET
    public void setId(int id) {
        this.id = id;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setPièce(Pièce pièce) {
        this.pièce = pièce;
    }
    public void setSol(Sol sol) {
        this.sol = sol;
    }
    public void setPlafond(Plafond plafond) {
        this.plafond = plafond;
    }
}
