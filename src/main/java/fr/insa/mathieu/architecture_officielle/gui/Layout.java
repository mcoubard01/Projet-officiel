/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle.gui;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author stard
 */
/*
public class Layout extends Pane{
    private GridPane gridPane;
    
    public Layout(){
        GridPane gridPane = new GridPane(); // Ce GridPane composera la fenêtre principale où je disposerai mes autres fenêtres
        this.gridPane=gridPane;
        
        System.out.println("AVT NOEUD LAYOUT");
        this.getChildren().add(gridPane);
        System.out.println("AP NOEUD LAYOUT");
        
        MainPane mainPane = new MainPane(); // Ce MainPane() sera composé de ma zone de dessin (Canvas) et mes outils
        RevêtementPane revêtementPane = new RevêtementPane(); // Ce Pane composera la liste de mes revêtements en liste déroulantes cliquable
        PrixPane prixPane = new PrixPane();
        
        //Mise en place et initialiser les tailles des colonnes et lignes
        gridPane.getColumnConstraints().add(new ColumnConstraints(650)); // column 0 is 650 wide
        gridPane.getColumnConstraints().add(new ColumnConstraints (150));// colonne à une largeur de 150
        gridPane.getRowConstraints().add(new RowConstraints(250));       // ligne à une hauteur de 250
        gridPane.getRowConstraints().add(new RowConstraints(250));       // ligne à une hauteur de 250
        gridPane.getRowConstraints().add(new RowConstraints(100));       // ligne à une hauteur de 100
        
        //Mettre mes Panes dans les différentes cases de mon grid
        gridPane.add(mainPane, 0, 0,1, 3); // je mets la fenêtre mainPane à la colonne 0, ligne 0, j'étale mon Pane sur 1 colonne et 3 lignes
        gridPane.add(revêtementPane,1,0,1, 1);//je mets la fenêtre mainPane à la colonne 1, ligne 0, j'étale mon Pane sur 1 colonne et 1 lignes
        gridPane.add(prixPane, 1, 2, 1, 1);//je mets la fenêtre mainPane à la colonne 1, ligne 2, j'étale mon Pane sur 1 colonne et 1 lignes
        
        this.widthProperty().addListener((o) -> {
            System.out.println("largeur : "+this.getWidth());
            gridPane.getColumnConstraints().add(0, new ColumnConstraints(0.75*this.getWidth())); // column 0 is 650 wide
            System.out.println("première colonne w : "+gridPane.getColumnConstraints().get(0).getPrefWidth());
            gridPane.getColumnConstraints().add(1, new ColumnConstraints (0.25*this.getWidth()));// colonne à une largeur de 150
            System.out.println("deuxième colonne w : "+gridPane.getColumnConstraints().get(1).getPrefWidth());
            System.out.println("RevêtementPane largeur w : "+revêtementPane.getWidth());
            System.out.println("PrixPane largeur w : "+prixPane.getWidth());
            System.out.println("MainPane largeur w : "+mainPane.getWidth());            
            System.out.println("Canvas largeur w :"+mainPane.getDcdessin().getWidth());
            mainPane.getDcdessin().redrawAll();
            System.out.println();
            
        });
        this.heightProperty().addListener((o) -> {
            System.out.println("hauteur : "+this.getHeight());
            gridPane.getRowConstraints().add(0, new RowConstraints(0.40*this.getHeight())); // column 0 is 650 wide
            System.out.println("première ligne h : "+gridPane.getRowConstraints().get(0).getPrefHeight());
            gridPane.getRowConstraints().add(1, new RowConstraints (0.40*this.getHeight()));// colonne à une largeur de 150
            System.out.println("deuxième ligne h : "+gridPane.getRowConstraints().get(1).getPrefHeight());
            gridPane.getRowConstraints().add(2, new RowConstraints (0.20*this.getHeight()));// colonne à une largeur de 150
            System.out.println("troisième ligne h : "+gridPane.getRowConstraints().get(2).getPrefHeight());
            System.out.println("RevêtementPane hauteur h : "+revêtementPane.getHeight());
            System.out.println("PrixPane hauteur h : "+prixPane.getHeight());
            System.out.println("MainPane hauteur h : "+mainPane.getHeight());
            System.out.println("Canvas hauteur h :"+mainPane.getDcdessin().getHeight());
            mainPane.getDcdessin().redrawAll();
            System.out.println();
        });
        
    }

    public GridPane getGridPane() {
        return gridPane;
    }
    
}
*/