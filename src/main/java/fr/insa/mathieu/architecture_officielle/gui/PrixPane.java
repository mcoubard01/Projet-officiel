/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle.gui;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * Comme vu dans github : il doit doit afficher les différents prix en continu.
 * avec la possibilité : si possible de filtrer, à la rigueur comme c'est possible qu'il y ait beaucoup d'éléments, d'en faire un scrollPane
 * @author stard
 */
public class PrixPane extends BorderPane{
    private double prix_total;
    private RadioButton rbAppart;
    private RadioButton rbRevêtement;
    private RadioButton rbEtage;
    private RadioButton rbCroissant;
    private RadioButton rbDecroissant;
    
    public PrixPane(){
        this.prix_total=10.12;
        this.rbAppart=new RadioButton("Appartement");
        this.rbEtage=new RadioButton("Etage");
        this.rbRevêtement=new RadioButton("Revêtement");
        this.rbCroissant=new RadioButton("Croissant");
        this.rbDecroissant=new RadioButton("Decroissant");
        
        HBox hboxType;
        hboxType = new HBox(this.rbAppart,this.rbEtage,this.rbRevêtement);
        
        HBox hboxEvol;
        hboxEvol = new HBox(this.rbCroissant,this.rbDecroissant);
        
        VBox filtre;
        filtre = new VBox(hboxType,hboxEvol);
        
        
        Label prixTotal = new Label("Prix total = "+prix_total+" € !");
        this.setBottom(prixTotal);
        this.setTop(filtre);
    }
    
}