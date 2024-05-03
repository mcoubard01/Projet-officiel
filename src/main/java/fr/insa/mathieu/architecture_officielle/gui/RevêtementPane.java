/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


/**
 *L'objectif ici est de créer la fenêtre en haut à gauche de la liste déroulante des revêtements
 *  Si possible afficher des revêtements qui sont cliquables pour les actionner si un mur, un sol, un plafond est selectionné
 * 
 * ex : si on clique sur un mur : on nous affiche dans cette liste la liste des revêtements possibles pour le mur
 *      si on clique sur un sol : on nous affiche dans cette liste la liste des revpetements possibles pour le sol
 *      ...
 *  Si rien n'est sélectionné : seulement afficher la liste des revêtements avec le prix au m² 
 * 
 * comment faire ??? peut-être avec la foncitonnalité Listview (https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ListView.html)
 * @author stard
 */
public class RevêtementPane extends BorderPane{
    
    ObservableList<String> names = FXCollections.observableArrayList("Julia", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise");
    ListView<String> listView = new ListView(names);
    
//CONSTRUCTOR
    public RevêtementPane(){
        
        VBox vbox=new VBox(listView);
        this.setCenter(vbox);
    }
}
