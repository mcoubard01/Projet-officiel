/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle.gui;

import fr.insa.mathieu.architecture_officielle.Architecture_officielle;
import fr.insa.mathieu.architecture_officielle.Revêtement;
import static fr.insa.mathieu.architecture_officielle.Revêtement.getKeyFromValue;
import static fr.insa.mathieu.architecture_officielle.Revêtement.rev_Total;
import static fr.insa.mathieu.architecture_officielle.Revêtement.rev_mur;
import static fr.insa.mathieu.architecture_officielle.Revêtement.rev_plafond;
import static fr.insa.mathieu.architecture_officielle.Revêtement.rev_sol;
import java.util.HashMap;
import java.util.function.BiConsumer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import fr.insa.mathieu.architecture_officielle.gui.Contrôleur;

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
    
    Contrôleur contrôleur;
    MainPane mainPane;
    private Revêtement revêtementCliqué;
    private VBox vbox;
    private HashMap<Revêtement,String> map;
    private ListView<String> listView;

    public void setMap(HashMap<Revêtement, String> map) {
        this.map = map;
    }
    
//CONSTRUCTOR
    public RevêtementPane(MainPane mainPane){
        this.map=rev_Total();
        this.mainPane=mainPane;
        this.listView=listView(map);
        this.contrôleur=mainPane.getContrôleur();
        //this.revêtementCliqué=new Revêtement();
        Architecture_officielle.donnee_enregistree=Architecture_officielle.lecture("Revêtement_final.txt");
        //TODO en fonction de notre état : affichier soit la liste des revêtements des murs, ou des sols, ou des plafonds. Je fais donc 3 HashMap pour assigner un String à un Revêtement
        //Toutes les listView possibles à afficher selon notre mode de fonctionnement
        //DONNEES de base
        
        
        VBox vbox=new VBox(this.listView);//changer en listViewSol ou ListViewPlafond
        this.setCenter(vbox);
        this.listView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String oldString, String newString) -> {// Changer avec listViewSol ou listViewPlafond
            System.out.println("Revêtement cliqué : "+newString);
            Revêtement revêtementTrouvé = getKeyFromValue(map, newString);
            this.revêtementCliqué=revêtementTrouvé;
            System.out.println("revêtementTROUV2.toString() : "+revêtementTrouvé.toString());
            System.out.println("revêtementTROUV2.getPrix_unitaire() : "+revêtementTrouvé.getPrix_unitaire());
            System.out.println("id du revêtement (numéro)"+revêtementTrouvé.getId());
        });
    }
    
    
    //FUNCTION
    public void affichageMur(){
        this.setMap(rev_mur());
        this.listView=listView(map);
    }
    public void affichageSol(){
        this.setMap(rev_sol());
        this.listView=listView(map);
    }
    public void affichagePlafond(){
        this.setMap(rev_plafond());
        this.listView=listView(map);
    }
    
    public Revêtement getRevêtementCliqué() {
        return revêtementCliqué;
    }
    public static ListView<String> listView(HashMap<Revêtement,String> map ){
        ListView<String> listview = new ListView<>();
        map.forEach(new BiConsumer<Revêtement, String>() { // BiConsumer apparu juste parce que je voulais aps utiliser l'expression lambda
            @Override
            public void accept(Revêtement t, String u) {
                listview.getItems().add(u);
            }
        });
           
        return listview;
    }

}