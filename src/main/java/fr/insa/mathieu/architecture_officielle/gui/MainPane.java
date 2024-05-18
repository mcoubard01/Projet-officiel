/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle.gui;

import fr.insa.mathieu.architecture_officielle.Architecture_officielle;
import fr.insa.mathieu.architecture_officielle.Etage;
import fr.insa.mathieu.architecture_officielle.Mur;
import fr.insa.mathieu.architecture_officielle.gui.Contrôleur.ETAT;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;

/**
 *
 * @author stard
 */
public class MainPane extends BorderPane {
    
    private DessinCanvas dcdessin;         //création de la zone de dessin
    
    private VBox vbox;
    private RadioButton rbSelect;   // bouton Selectionner
    private RadioButton rbcrmur;      // bouton CRer un mur
    private RadioButton rbcrpiece2;   //bouton CRer une pièce avec 2 points
    private RadioButton rbcrpiece3;   //bouton CRer une pièce avec 3 points
    
    private RadioButton rbidpiece;    //bouton IDentifier une pièce (en cliquant sur les murs composonts la pièce si possible)
    private RadioButton rbidappart;   //bouton IDentifier un appart (en cliquant sur les pièces contenues si possible)
    private RadioButton rbporte;
    private RadioButton rbrevêtement_rap;
    private RadioButton rbfenêtre;
    private RadioButton rbrevêtement;
    private RadioButton rbsupp;
    private RadioButton rbEtageAj;
    
    private Contrôleur contrôleur;
    private RevêtementPane revêtementPane;
    private Architecture_officielle model;
    
    HashMap<Etage,Button> mapEtage_Button;

    //CONSTRUCTOR
    public MainPane(){
        this(new Architecture_officielle());
    }
    public MainPane(Architecture_officielle model){
        this.mapEtage_Button=new HashMap<>();
        this.model=model;
        this.revêtementPane= new RevêtementPane(this);
        this.contrôleur=new Contrôleur(this,this.revêtementPane);
        
        this.rbSelect=new RadioButton("Select");
        this.rbSelect.setOnAction((t) -> {
            this.contrôleur.boutonSelect(t);
        });
        
     
        this.rbcrmur=new RadioButton("créer mur");
        this.rbcrmur.setOnAction((t) -> {
            this.contrôleur.boutonCrmur(t);
        });
        
        this.rbidpiece=new RadioButton("identifier une pièce");
        
        this.rbidappart=new RadioButton("identifier un appart");
        this.rbidappart.setOnAction((t) -> {
            this.contrôleur.boutonIdAppart(t);
        });
        
        this.rbcrpiece2=new RadioButton("créer une pièce avec 2 pnts");
        this.rbcrpiece2.setOnAction((t) -> {
            this.contrôleur.boutonCrpiece2(t);
        });
        
        this.rbcrpiece3=new RadioButton("créer une pièce avec 3 pnts");
        this.rbcrpiece3.setOnAction((t) -> {
            this.contrôleur.boutonCrpiece3(t);
        });
        
        this.rbporte=new RadioButton("ajout d'une porte");
        this.rbporte.setOnAction((t) -> {
            this.contrôleur.ajoutPorte(t);
        });
        
        this.rbfenêtre=new RadioButton("ajout d'une fenêtre");
        this.rbfenêtre.setOnAction((t) -> {
            this.contrôleur.ajoutFenetre(t);
        });
        this.rbrevêtement_rap=new RadioButton("Ajout rapide revêtement");
        
        this.rbrevêtement = new RadioButton("revêtement");
        this.rbrevêtement.setOnAction((t) -> {
            this.contrôleur.ajoutGrpRevetement(t);
        });
        this.rbfenêtre=new RadioButton("fenêtre");
        this.rbporte=new RadioButton("porte");
        this.rbsupp= new RadioButton("Supprimer/annuler");
        this.rbEtageAj= new RadioButton("ajout étage");
        this.rbEtageAj.setOnAction((t) -> {
            this.contrôleur.boutonAjEtage(t);
        });
        
        ToggleGroup bgEtat = new ToggleGroup();
        this.rbSelect.setToggleGroup(bgEtat);
        this.rbcrmur.setToggleGroup(bgEtat);
        this.rbcrpiece2.setToggleGroup(bgEtat);
        this.rbcrpiece3.setToggleGroup(bgEtat);
        this.rbfenêtre.setToggleGroup(bgEtat);
        this.rbidappart.setToggleGroup(bgEtat);
        this.rbidpiece.setToggleGroup(bgEtat);
        this.rbporte.setToggleGroup(bgEtat);
        this.rbrevêtement.setToggleGroup(bgEtat);
        this.rbrevêtement_rap.setToggleGroup(bgEtat);
        this.rbEtageAj.setToggleGroup(bgEtat);
        this.rbSelect.setSelected(true);
        
        //disposition des éléments node entre eux (les uns au dessus des autres)
        this.vbox= new VBox(this.rbSelect,this.rbcrmur,this.rbcrpiece2,this.rbcrpiece3,
                this.rbidappart,this.rbidpiece, this.rbfenêtre,this.rbporte,
                this.rbrevêtement_rap,this.rbrevêtement, this.rbEtageAj, this.rbsupp);
        
        //Position des éléments sur la scene
        this.setLeft(this.vbox);
        
        this.dcdessin=new DessinCanvas(this);
        this.setCenter(this.dcdessin);
    this.contrôleur.changeEtat(ETAT.SELECT);
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
    public DessinCanvas getDcdessin() {
        return dcdessin;
    }
    public RevêtementPane getRevêtementPane() {
        return revêtementPane;
    }
    void redrawAll() {
        this.dcdessin.redrawAll();
    }
    void highlight(Mur murLePlusProche){
        this.dcdessin.highlight(murLePlusProche);
    }

    void ajoutBtEtage(int size) {
        Button butonEtage=new Button("Etage "+size);
        this.mapEtage_Button.put(this.contrôleur.getListeEtage().get(size-1), butonEtage);
        this.vbox.getChildren().add(butonEtage);

        butonEtage.setOnAction((t) -> {
            this.contrôleur.setEtageActuel(getKeyFromValue(mapEtage_Button, butonEtage));
            this.model.setEtageActuel(getKeyFromValue(mapEtage_Button, butonEtage));
            System.out.println("Etage selectionné : "+ this.contrôleur.getEtageActuel().toString());
            System.out.println("Etage actuel du batiment : "+ this.model.getEtageActuel().toString());
            
            this.redrawAll();
        });
    }
    
    public static Etage getKeyFromValue(HashMap<Etage, Button> map, Button buttonSelected) {
        for (HashMap.Entry<Etage, Button> entry : map.entrySet()) {//la classe Entry possède par pair : la clé et de la valeur
            if (entry.getValue().equals(buttonSelected)) {
                return entry.getKey();
            }
        }
        return null;
    }
}