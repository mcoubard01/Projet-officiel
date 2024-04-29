/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.yava to edit this template
 */
package fr.insa.mathieu.architecture_officielle;

/**
 * 
 * @author stard
 */
public class Coin {  
    private int id; 
    private int x;
    private int y;
    private Pièce pièce;
    private Plafond plafond; 
    private Sol sol;
    private String nom_coin; 


    public Coin(int x, int y) {
           this.id = IDManager.newId(this);
        this.x = x;
        this.y = y;
    }

   
public Coin(int id, int x, int y) {
        this.id = IDManager.newId(this);
        this.x = x;
        this.y = y;
    }

   public Coin(String nom_coin, int x, int y) {
       this.nom_coin = nom_coin;
        this.x = x;
        this.y = y;
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

    public Pièce getPièce() {
        return pièce;
    }

    public void setPièce(Pièce pièce) {
        this.pièce = pièce;
    }

    public Sol getSol() {
        return sol;
    }

    public void setSol(Sol sol) {
        this.sol = sol;
    }

    public Plafond getPlafond() {
        return plafond;
    }

    public void setPlafond(Plafond plafond) {
        this.plafond = plafond;
    }
    

    @Override
    public String toString() {
        return "Coin: "+nom_coin+"{ x=" + x + ", y=" + y + '}';
    }

 
    
    
    
}
