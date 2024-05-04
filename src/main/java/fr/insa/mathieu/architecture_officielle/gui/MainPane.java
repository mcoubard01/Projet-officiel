/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle.gui;

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
    private Button brevêtement;
    private Button bporte;
    private Button bfenêtre;
    
    //CONSTRUCTOR
    public MainPane(){
        this.rbSelect=new RadioButton("Select");
        this.rbcrmur=new RadioButton("créer mur");
        this.rbidpiece=new RadioButton("identifier une pièce");
        this.rbidappart=new RadioButton("identifier un appart");
        this.rbcrpiece2=new RadioButton("créer une pièce avec 2 pnts");
        this.rbcrpiece3=new RadioButton("créer une pièce avec 3 pnts");
        this.rbporte=new RadioButton("ajout d'une porte");
        this.rbfenêtre=new RadioButton("ajout d'une fenêtre");
        this.rbrevêtement_rap=new RadioButton("Ajout rapide revêtement");
        this.brevêtement = new Button("revêtement");
        this.bfenêtre=new Button("fenêtre");
        this.bporte=new Button("porte");
        
        //disposition des éléments node entre eux (les uns au dessus des autres)
        VBox vbGauche;
        vbGauche = new VBox(this.rbSelect,this.rbcrmur,this.rbcrpiece2,this.rbcrpiece3,this.rbidappart,this.rbidpiece, this.rbfenêtre,this.rbporte,this.rbrevêtement_rap,
                this.brevêtement,this.bfenêtre,this.bporte);
        //Position des éléments sur la scene
        this.setLeft(vbGauche);
        
        this.dcdessin=new DessinCanvas();
        this.setCenter(this.dcdessin);
        
        this.brevêtement.setOnMouseEntered((t) -> {
            System.out.println("position d'entrée : ("+t.getX()+","+t.getY()+")"); // donne la position d'entré de la souris sur le bouton
            this.brevêtement.setOnAction((ActionEvent t1) -> { // lorsque on clique sur le bouton
                System.out.println("J'ai cliqué sur le bouton");
                //<objet selectionné>.setRevêtement(revêtement);  
                
            });
        });
    }
/*
    private RadioButton rbSelect;
    public MainPane(){
        this.rbSelect=new RadioButton("Select");
        VBox vbGauche = new VBox(this.rbSelect);
        this.setCenter(vbGauche);
    }
   */ 
}
