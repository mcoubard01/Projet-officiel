/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author stard
 */
public class MainPane extends BorderPane {
    
    private Canvas cdessin;         //création de la zone de dessin
    
    private RadioButton rbSelect;   // bouton Selectionner
    private RadioButton rbcrmur;      // bouton CRer un mur
    private RadioButton rbidpiece;    //bouton IDentifier une pièce (en cliquant sur les murs composonts la pièce si possible)
    private RadioButton rbidappart;   //bouton IDentifier un appart (en cliquant sur les pièces contenues si possible)
    private RadioButton rbcrpiece2;   //bouton CRer une pièce avec 2 points
    private RadioButton rbcrpiece3;   //bouton CRer une pièce avec 3 points
    
    public MainPane(){
        this.rbSelect=new RadioButton("Select");
        this.rbcrmur=new RadioButton("créer mur");
        this.rbidpiece=new RadioButton("identifier une pièce");
        this.rbidappart=new RadioButton("identifier un appart");
        this.rbcrpiece2=new RadioButton("créer une pièce avec 2 pnts");
        this.rbcrpiece3=new RadioButton("créer une pièce avec 3 pnts");
        VBox vbGauche=new VBox(this.rbSelect,this.rbcrmur,this.rbcrpiece2,this.rbcrpiece3,this.rbidappart,this.rbidpiece);
        this.setLeft(vbGauche);
        
        this.cdessin=new Canvas(200,200);
        this.setCenter(this.cdessin);
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
