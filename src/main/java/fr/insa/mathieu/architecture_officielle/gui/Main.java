/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle.gui;
import fr.insa.mathieu.architecture_officielle.Architecture_officielle;
import fr.insa.mathieu.architecture_officielle.gui.Contrôleur.ETAT;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
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
    private static Architecture_officielle model ;

    @Override
    public void start(Stage stage) {
       
        Scene scene;
//        Architecture_officielle batiment = new Architecture_officielle();
        GridPane gridPane = new GridPane(); // Ce GridPane composera la fenêtre principale où je disposerai mes autres fenêtres
        MainPane mainPane; // Ce MainPane() sera composé de ma zone de dessin (Canvas) et mes outils
        
        //Mise en place et initialiser les tailles des colonnes et lignes
        gridPane.getColumnConstraints().add(new ColumnConstraints(650)); // column 0 is 650 wide
        gridPane.getColumnConstraints().add(new ColumnConstraints (150));// colonne à une largeur de 150
        gridPane.getRowConstraints().add(new RowConstraints(25));
        gridPane.getRowConstraints().add(new RowConstraints(250));       // ligne à une hauteur de 250
        gridPane.getRowConstraints().add(new RowConstraints(250));       // ligne à une hauteur de 250
        gridPane.getRowConstraints().add(new RowConstraints(100));       // ligne à une hauteur de 100
        gridPane.getRowConstraints().add(new RowConstraints(25));
//        mainPane=new MainPane(batiment);
                mainPane=new MainPane(model);
                mainPane.getRbEtageAj().setSelected(true);
                mainPane.getContrôleur().changeEtat(ETAT.AJOUT_ETAGEp1);


        
//Mettre mes Panes dans les différentes cases de mon grid        
        gridPane.add(mainPane.getMenu(), 0, 0,2,1); ////je mets la menuBar à la colonne 0, ligne 0, j'étale mon Pane sur 2 colonne et 1 ligne
        gridPane.add(mainPane, 0, 1,1, 3); // je mets la fenêtre mainPane à la colonne 1, ligne 1, j'étale mon Pane sur 1 colonne et 3 lignes
        gridPane.add(mainPane.getRevêtementPane(),1,1,1, 1);//je mets la fenêtre revêtmentPane à la colonne 1, ligne 1, j'étale mon Pane sur 1 colonne et 1 lignes
        gridPane.add(mainPane.getPrixPane(), 1, 2, 1, 2);//je mets la fenêtre prixPane à la colonne 1, ligne 2, j'étale mon Pane sur 1 colonne et 2 lignes
        gridPane.add(mainPane.getTfMessage(),0,4,2,1);//je mets tfMessage à la colonne 0, ligne 4, j'étale mon Pane sur 2 colonne et 1 ligne
        scene = new Scene(gridPane,800,650);
        
        scene.widthProperty().addListener((o) -> {
            gridPane.getColumnConstraints().add(0,new ColumnConstraints(0.75*gridPane.getWidth())); // column 0 is 650 wide
            gridPane.getColumnConstraints().add(1,new ColumnConstraints (0.25*gridPane.getWidth()));// colonne à une largeur de 150
        });
        scene.heightProperty().addListener((o) -> { 
            gridPane.getRowConstraints().set(0, new RowConstraints(25));      // ligne à une hauteur de 250            
            gridPane.getRowConstraints().set(1, new RowConstraints(0.40*gridPane.getHeight()-25));      // ligne à une hauteur de 250
            gridPane.getRowConstraints().add(2,new RowConstraints(0.40*gridPane.getHeight()));       // ligne à une hauteur de 250
            gridPane.getRowConstraints().add(3,new RowConstraints(0.20*gridPane.getHeight()-25));
            gridPane.getRowConstraints().set(4, new RowConstraints(25));      // ligne à une hauteur de 250
            
        });
        
        
        stage.setScene(scene);
        stage.setTitle("LE MEILLEUR LOGICIEL D'ARCHITECTURE ! (made by Mathieu, Thomas, Oscar )");
        stage.show(); // Affichage de la fenêtre
    }
    
    public static void customLaunch(Architecture_officielle nouveauModèle){
        model = nouveauModèle;
        launch();
    }

    public Architecture_officielle getModel() {
        return model;
    }

    public static void setModel(Architecture_officielle newModel) {
        model = newModel;
    }
    
    
    public static void main(String[] args) {
//        launch();
            customLaunch(new Architecture_officielle());
        
    }

}