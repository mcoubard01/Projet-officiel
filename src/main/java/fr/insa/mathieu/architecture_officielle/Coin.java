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

    public Coin(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coin(int id, int x, int y) {
        this.id = IDManager.newId(this);
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

    @Override
    public String toString() {
        return "{(" + x + "," + y + ") ,id=" + id +  '}';
    }
    
    
    
    
}
