/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle.gui;

import fr.insa.mathieu.architecture_officielle.Architecture_officielle;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * TODO : réactualiser le prixPane avec la surface et prix
 * Comme vu dans github : il doit doit afficher les différents prix en continu.
 * avec la possibilité : si possible de filtrer, à la rigueur comme c'est possible qu'il y ait beaucoup d'éléments, d'en faire un scrollPane
 * @author stard
 */
public class PrixPane extends BorderPane{
    private RadioButton rbAppart;
    private RadioButton rbRevêtement;
    private RadioButton rbEtage;
    private RadioButton rbCroissant;
    private RadioButton rbDecroissant;
    private MainPane mainPane;
    private VBox global;
    private Contrôleur contrôleur;
    private double prixTotal;
    private double surfaceTotaleHabitable;
    private Label affichePrix;
    private Label afficheSurface;
   
    public PrixPane(MainPane mainPane){
        this.mainPane=mainPane;
        this.prixTotal=0;
        this.surfaceTotaleHabitable=0;
        
        this.rbAppart=new RadioButton("Appartement");
        this.rbEtage=new RadioButton("Etage");
        this.rbRevêtement=new RadioButton("Revêtement");
        this.rbCroissant=new RadioButton("Croissant");
        this.rbDecroissant=new RadioButton("Decroissant");
        
        HBox hboxType = new HBox(this.rbAppart,this.rbEtage,this.rbRevêtement);
        ToggleGroup tg = new ToggleGroup();
        this.rbAppart.setToggleGroup(tg);
        this.rbEtage.setToggleGroup(tg);
        this.rbRevêtement.setToggleGroup(tg);
        
        HBox hboxEvol;
        hboxEvol = new HBox(this.rbCroissant,this.rbDecroissant);
        ToggleGroup tg2 = new ToggleGroup();
        this.rbCroissant.setToggleGroup(tg2);
        this.rbDecroissant.setToggleGroup(tg2);
        
        VBox filtre = new VBox(hboxType,hboxEvol);
        System.out.println("Classe Prix Pane : filtre.toString()"+filtre.toString());
        
        Label titre = new Label("Devis : ça va vous couter cher");
        VBox presentation = new VBox(titre,filtre);
        
        this.affichePrix = new Label("Prix total = "+this.prixTotal+" € !");
        this.afficheSurface= new Label ("surface habitable = "+this.surfaceTotaleHabitable+" UNITE");
        this.global = new VBox(this.affichePrix,this.afficheSurface);
        
        this.setBottom(global);
        this.setTop(presentation);
    }
    
    public void reCalculeSurface(Architecture_officielle batiment){
        System.out.println("Je suis dans la boucle de recalcule");
        this.surfaceTotaleHabitable=batiment.surfaceTotalHabitable();
        this.afficheSurface.setText("surface habitable = "+this.surfaceTotaleHabitable+" UNITE");
        System.out.println("prix total : "+this.prixTotal);
        System.out.println("surface totale au sol : "+this.surfaceTotaleHabitable);
    }
    public void reCalculePrix(Architecture_officielle batiment){
        this.prixTotal=batiment.prixTotal();
        this.affichePrix.setText("prix total : "+this.prixTotal+" €");
        System.out.println("prix total : "+this.prixTotal);
        System.out.println("surface totale au sol : "+this.surfaceTotaleHabitable);
    }
    
    //GET
     public MainPane getMainPane() {
        return mainPane;
    }
    //SET
    public void setPrix_total(double prix_total) {
        this.prixTotal = prix_total;
    }
    public void setContrôleur(Contrôleur contrôleur) {
        this.contrôleur = contrôleur;
    }
}