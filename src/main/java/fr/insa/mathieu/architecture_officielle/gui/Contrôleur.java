/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle.gui;

import fr.insa.mathieu.architecture_officielle.Architecture_officielle;
import fr.insa.mathieu.architecture_officielle.Coin;
import fr.insa.mathieu.architecture_officielle.Etage;
import fr.insa.mathieu.architecture_officielle.Fenêtre;
import fr.insa.mathieu.architecture_officielle.Mur;
import fr.insa.mathieu.architecture_officielle.Pièce;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *  VIDEO à reprendre (la 7 je crois)
 * @author stard
 */
public class Contrôleur {
    private int etat;
    private MainPane vue;
    
    private ArrayList<Double> pos=new ArrayList<>();
 
    public Contrôleur(MainPane vue){
        this.vue=vue;
    }
    public void changeEtat(int nouvelEtat){
        switch(nouvelEtat){
            case 20:
                break;
            case 30:
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                break;
            case 31:
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                break;
            case 40:
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                break;
            case 41:
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                break;
            case 50:
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                break;
            case 51:
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                break;
            case 52:
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                break;
            case 70:
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                //this.vue.getRbfenêtre().setDisable(true);
                break;
            case 71:
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                //this.vue.getRbfenêtre().setDisable(true);
                break;
            case 80:
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                //this.vue.getRbporte().setDisable(true);
                break;
            case 81:
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                //this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                break;
            case 90:
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                //this.vue.getRbrevêtement().setDisable(true);
                break;
            case 91:
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                //this.vue.getRbrevêtement().setDisable(true);
                break;
            case 92:
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                //this.vue.getRbrevêtement().setDisable(true);
                break;
        }
        this.etat=nouvelEtat;
    }

    void clicDansZoneDessin(MouseEvent t) {
        
        switch (etat){
            case 30:
                this.pos.add(0, t.getX());
                this.pos.add(1, t.getY());
                this.changeEtat(31);
                break;
            case 31:
                double x2=t.getX();
                double y2=t.getY();
                Mur mur = new Mur(new Coin(this.pos.get(0), this.pos.get(1)), new Coin(x2, y2));
                //d'après vidéo il fautdrait mettre this.vue.redrawAll()
                this.changeEtat(30);
            case 40:
                this.pos.add(0, t.getX());
                this.pos.add(1, t.getY());
                this.changeEtat(41);
                break;
            case 41:
                x2=t.getX();
                y2=t.getY();
                Coin coinA= new Coin(this.pos.get(0), this.pos.get(1));
                Coin coinB= new Coin(this.pos.get(0), y2);
                Coin coinC= new Coin(x2, y2);
                Coin coinD= new Coin(x2, this.pos.get(1));
                Mur murAB=new Mur(coinA, coinB);
                Mur murBC=new Mur(coinB, coinC);
                Mur murCD=new Mur(coinC, coinD);
                Mur murDA=new Mur(coinD, coinA);
                Pièce pièce2 = new Pièce(murAB, murBC, murCD, murDA);
                //d'après vidéo il fautdrait mettre this.vue.redrawAll()
                this.changeEtat(40);
                break;
            case 50:
                this.pos.add(0, t.getX());
                this.pos.add(1, t.getY());
                this.changeEtat(51);
                break;
            case 51:
                this.pos.add(2, t.getX());
                this.pos.add(3, t.getY());
                this.changeEtat(52);
                break;
            case 52:
                this.pos.add(4, t.getX());
                this.pos.add(5, t.getY());
                Coin coinE= new Coin(this.pos.get(0), this.pos.get(1));
                Coin coinF= new Coin(this.pos.get(2), this.pos.get(3));
                Coin coinG= new Coin(this.pos.get(4),this.pos.get(3));
                Coin coinH= new Coin(this.pos.get(4), this.pos.get(5));
                Mur murEF=new Mur(coinE, coinF);
                Mur murFG=new Mur(coinF, coinG);
                Mur murGH=new Mur(coinG, coinH);
                Mur murHE=new Mur(coinH, coinE);
                Pièce pièce3 = new Pièce(murEF, murFG, murGH, murHE);
                this.changeEtat(50);
                break;
            case 70: //création de fenêtre, p1
                this.pos.add(0,t.getX());
                this.pos.add(1,t.getY());
                //TODO Détection du mur 
                
                
                this.changeEtat(71);             
                break;
            case 71://création de fenêtre, p2
                double posx1 = t.getX();
                double posy1 = t.getY();
                Mur nouveauMur1 = new Mur(new Coin(23,23),new Coin(24,24));

                //TODO détection de l'orientation
                char orientation1 = 'E';
                Fenêtre fenêtre1 = new Fenêtre(this.pos.get(0),this.pos.get(1),orientation1,nouveauMur1);
                this.changeEtat(70);
                break;
            case 80:
                this.changeEtat(81);
                break;
            case 81:
                this.changeEtat(80);
                break;
            case 90:
                this.changeEtat(91);
                break;
            case 91:
                this.changeEtat(90);
                break;
        }
        //TODO dans la video il fait model.add(new point) 
        //Je ne peux pas faire ça car je n'ai pas de méthode pour 
        
    }

    void boutonSelect(ActionEvent t) {
        this.changeEtat(20);
    }

    void boutonCrmur(ActionEvent t) {
        this.changeEtat(30);
    }

    void boutonCrpiece2(ActionEvent t) {
        this.changeEtat(40);
    }

    void boutonCrpiece3(ActionEvent t) {
        this.changeEtat(50);
    }
    
}
