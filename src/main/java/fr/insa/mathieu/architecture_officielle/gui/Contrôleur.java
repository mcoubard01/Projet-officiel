/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle.gui;

/**
 *  VIDEO à reprendre (la 7 je crois)
 * @author stard
 */
public class Contrôleur {
    private int etat;
    private MainPane vue;
 
    public Contrôleur(MainPane vue){
        this.vue=vue;
    }
    public void changeEtat(int etat){
        switch(etat){
            case 20:
                break;
            case 30:
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                break;
            case 40:
        }
    }
}
