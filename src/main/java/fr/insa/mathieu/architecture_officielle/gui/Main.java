/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle.gui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
/**
 *
 * @author thomas beverly
 */
    

//dans le pom : ligne 53 : <mainClass>fr.insa.mathieu.architecture_officielle.gui.Main</mainClass> 
//ceci indique que Main.java est la classe éxécutable de javaFX

//dans le pom, ligne 12 : <exec.mainClass>fr.insa.mathieu.architecture_officielle.gui.Main</exec.mainClass>
//ceci indique que gui.Main est la nouvelle classe par défaut du projet. 
//Je ne pouvais laisser Architecture_officielle comme classe éxécutable principale, car sinon javafx ne fonctionnait pas...

//dans  projet:architecture_officielle > clic droit > Properties > Actions > Run Project > "Execute Goals" > "clean javafx:run"
//"clean javafx:run" est l'instruction que De Beuvron recommendait d'indiquer ici
//sans ça, la console indique l'erreur "javaFX runtime components are missing"... Mais je n'en sais pas plus sur le fonctionneùment de JFX.

/**
 * TODO J'aimerais diviser mon écran en part égale. Les cases de la grille doivent s'adapter en fonction de la taille de la fenêtre.
 * J'organise mon interface en grille avec colonnes et lignes à largeur et longueur variable (avec les contraintes)
 * Je dispose mes Panes dans les différentes lignes et colonnes pour avoir une bonne disposition. 
 * JavaFX App
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        /*
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();
        
        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        */
        Scene scene;
        
        GridPane gridPane = new GridPane(); // Ce GridPane composera la fenêtre principale où je disposerai mes autres fenêtres
        MainPane mainPane; // Ce MainPane() sera composé de ma zone de dessin (Canvas) et mes outils
        RevêtementPane revêtementPane = new RevêtementPane(); // Ce Pane composera la liste de mes revêtements en liste déroulantes cliquable
        
        //Mise en place et initialiser les tailles des colonnes et lignes
        gridPane.getColumnConstraints().add(new ColumnConstraints(650)); // column 0 is 650 wide
        gridPane.getColumnConstraints().add(new ColumnConstraints (150));// colonne à une largeur de 150
        gridPane.getRowConstraints().add(new RowConstraints(250));       // ligne à une hauteur de 250
        gridPane.getRowConstraints().add(new RowConstraints(250));       // ligne à une hauteur de 250
        gridPane.getRowConstraints().add(new RowConstraints(100));       // ligne à une hauteur de 100
        mainPane=new MainPane();
        //Mettre mes Panes dans les différentes cases de mon grid
        gridPane.add(mainPane, 0, 0,1, 3); // je mets la fenêtre mainPane à la colonne 0, ligne 0, j'étale mon Pane sur 1 colonne et 3 lignes
        gridPane.add(revêtementPane,1,0,1, 1);//je mets la fenêtre mainPane à la colonne 1, ligne 0, j'étale mon Pane sur 1 colonne et 1 lignes
        gridPane.add(new PrixPane(), 1, 2, 1, 1);//je mets la fenêtre mainPane à la colonne 1, ligne 2, j'étale mon Pane sur 1 colonne et 1 lignes
        scene = new Scene(gridPane,800,600);
        
        scene.widthProperty().addListener((o) -> {
            System.out.println("taille de GridPane : w = "+gridPane.getWidth()+" , h = "+gridPane.getHeight());
            
            gridPane.getColumnConstraints().add(0,new ColumnConstraints(0.75*gridPane.getWidth())); // column 0 is 650 wide
            System.out.println("première colonne : "+gridPane.getColumnConstraints().get(0).getPrefWidth());
            gridPane.getColumnConstraints().add(1,new ColumnConstraints (0.25*gridPane.getWidth()));// colonne à une largeur de 150
            System.out.println("deuxième colonne : "+gridPane.getColumnConstraints().get(1).getPrefWidth());
        });
        scene.heightProperty().addListener((o) -> {
            System.out.println("taille de GridPane : w = "+gridPane.getWidth()+" , h = "+gridPane.getHeight());
            
            gridPane.getRowConstraints().set(0, new RowConstraints(0.40*gridPane.getHeight()));      // ligne à une hauteur de 250
            System.out.println("première ligne : "+gridPane.getRowConstraints().get(0).getPrefHeight());
            gridPane.getRowConstraints().add(1,new RowConstraints(0.40*gridPane.getHeight()));       // ligne à une hauteur de 250
            System.out.println("deuxième ligne : "+gridPane.getRowConstraints().get(1).getPrefHeight());
            gridPane.getRowConstraints().add(2,new RowConstraints(0.20*gridPane.getHeight()));
            System.out.println("troisième ligne : "+gridPane.getRowConstraints().get(2).getPrefHeight());
        });
        
        
        stage.setScene(scene);
        stage.setTitle("LE MEILLEUR LOGICIEL D'ARCHITECTURE ! (made by Mathieu, Thomas, Oscar )");
        stage.show(); // Affichage de la fenêtre
    }

    public static void main(String[] args) {
        launch();
        
    }

}