/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle.gui;

import fr.insa.mathieu.architecture_officielle.Architecture_officielle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author stard
 */
public class MainPane extends BorderPane {
    
    private DessinCanvas dcdessin;         //création de la zone de dessin
    
    private RadioButton rbSelect;   // bouton Selectionner
    private RadioButton rbcrmur;      // bouton CRer un mur
    private RadioButton rbidpiece;    //bouton IDentifier une pièce (en cliquant sur les murs composonts la pièce si possible)
    private RadioButton rbidappart;   //bouton IDentifier un appart (en cliquant sur les pièces contenues si possible)
    private RadioButton rbcrpiece2;   //bouton CRer une pièce avec 2 points
    private RadioButton rbcrpiece3;   //bouton CRer une pièce avec 3 points
    private RadioButton rbporte;
    private RadioButton rbrevêtement_rap;
    private RadioButton rbfenêtre;
    private RadioButton rbrevêtement;
    private RadioButton rbsupp;
    private RadioButton rbEtageAj;
    
    private Contrôleur contrôleur;
    private Architecture_officielle model;

    //CONSTRUCTOR
    public MainPane(){
        
    }
    public MainPane(Architecture_officielle model){
        this.model=model;
        this.contrôleur=new Contrôleur(this);
        this.rbSelect=new RadioButton("Select");
        this.rbcrmur=new RadioButton("créer mur");
        this.rbidpiece=new RadioButton("identifier une pièce");
        this.rbidappart=new RadioButton("identifier un appart");
        this.rbcrpiece2=new RadioButton("créer une pièce avec 2 pnts");
        this.rbcrpiece3=new RadioButton("créer une pièce avec 3 pnts");
        this.rbporte=new RadioButton("ajout d'une porte");
        this.rbfenêtre=new RadioButton("ajout d'une fenêtre");
        this.rbrevêtement_rap=new RadioButton("Ajout rapide revêtement");
        this.rbrevêtement = new RadioButton("revêtement");
        this.rbfenêtre=new RadioButton("fenêtre");
        this.rbporte=new RadioButton("porte");
        this.rbsupp= new RadioButton("Supprimer");
        this.rbEtageAj= new RadioButton("ajout étage");
        
        //disposition des éléments node entre eux (les uns au dessus des autres)
        VBox vbGauche;
        vbGauche = new VBox(this.rbSelect,this.rbcrmur,this.rbcrpiece2,this.rbcrpiece3,this.rbidappart,this.rbidpiece, this.rbfenêtre,this.rbporte,this.rbrevêtement_rap,
                this.rbrevêtement,this.rbfenêtre,this.rbporte, this.rbEtageAj);
        System.out.println("Classe MainePane : vbGauche.toString()"+vbGauche.toString());
        //Position des éléments sur la scene
        this.setLeft(vbGauche);
        
        this.dcdessin=new DessinCanvas(this);
        this.setCenter(this.dcdessin);
        
        this.rbrevêtement.setOnMouseEntered((t) -> {
            System.out.println("position d'entrée : ("+t.getX()+","+t.getY()+")"); // donne la position d'entré de la souris sur le bouton
            this.rbrevêtement.setOnAction((ActionEvent t1) -> { // lorsque on clique sur le bouton
                System.out.println("J'ai cliqué sur le bouton");
                //<objet selectionné>.setRevêtement(revêtement);  
                
            });
        });
    
    }
    
    //GET
    
    public Architecture_officielle getModel() {
        return model;
    }
    public Contrôleur getContrôleur() {
        return contrôleur;
    }
    public RadioButton getRbSelect() {
        return rbSelect;
    }

    public RadioButton getRbcrmur() {
        return rbcrmur;
    }

    public RadioButton getRbidpiece() {
        return rbidpiece;
    }

    public RadioButton getRbidappart() {
        return rbidappart;
    }

    public RadioButton getRbcrpiece2() {
        return rbcrpiece2;
    }

    public RadioButton getRbcrpiece3() {
        return rbcrpiece3;
    }

    public RadioButton getRbporte() {
        return rbporte;
    }

    public RadioButton getRbrevêtement_rap() {
        return rbrevêtement_rap;
    }

    public RadioButton getRbfenêtre() {
        return rbfenêtre;
    }

    public RadioButton getRbrevêtement() {
        return rbrevêtement;
    }

    public RadioButton getRbsupp() {
        return rbsupp;
    }

    public RadioButton getRbEtageAj() {
        return rbEtageAj;
    }
}