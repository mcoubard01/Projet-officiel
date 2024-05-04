/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle.gui;

import fr.insa.mathieu.architecture_officielle.Architecture_officielle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    
    ObservableList<String> designation = FXCollections.observableArrayList();
    ListView<String> listeDesignation = new ListView(designation);
    
    
//CONSTRUCTOR
    public RevêtementPane(){
        System.out.println("Je suise dans le constructeur :");
        ObservableList<String> revêtementObs = ObservableRevêtement();
        for (int i=0;i<revêtementObs.size();i++){
            this.designation.add(revêtementObs.get(i));
            System.out.println("contenu attribut :"+this.designation.get(i));
        }
        
        this.listeDesignation=new ListView<>(designation);
        VBox vbox=new VBox(listeDesignation);
        this.setCenter(vbox);
        listeDesignation.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            System.out.println("Selected item: " + newValue);
        });
    }
    
    
    //FUNCTION
    public static ObservableList<String> ObservableRevêtement(){
        Architecture_officielle.donnee_enregistree=Architecture_officielle.lecture("Revêtement_final.txt");
        ObservableList<String> observRevêtement = FXCollections.observableArrayList();
        
        for (int i=0;i<Architecture_officielle.donnee_enregistree.size();i++){
            if (Architecture_officielle.donnee_enregistree.get(i)!=null){
                String[] info = Architecture_officielle.donnee_enregistree.get(i);
                String désignation = info[1];
                String prixUnitaire = info[5];
                System.out.println("Désignation : "+désignation+" ; prixUnitaire : "+prixUnitaire+" €/m²");
                String Revêtement = "+"+désignation+" : Prix unitaire : "+prixUnitaire+" €/m²";
                observRevêtement.add(Revêtement);
                
            }
            else{
                
            }
        }
        
        System.out.println("contenu de observRevêtement : ");
        for(int i=0;i<observRevêtement.size();i++){
            System.out.print(", "+observRevêtement.get(i));    
        }
        
        return observRevêtement;
    
    }
}
/*
Architecture_officielle.donnee_enregistree=Architecture_officielle.lecture("Revêtement_final.txt");
        ObservableList<String> observRevêtement = FXCollections.observableArrayList();
        
        for (int i=0;i<Architecture_officielle.donnee_enregistree.size();i++){
            if (Architecture_officielle.donnee_enregistree.get(i)!=null){
                String[] info = Architecture_officielle.donnee_enregistree.get(i);
                String désignation = info[1];
                System.out.println("Désignation : "+désignation);
                observRevêtement.add(désignation);
                
            }
            else{
                
            }
        }
        
        System.out.println("contenu de observRevêtement : ");
        for(int i=0;i<observRevêtement.size();i++){
            System.out.print(", "+observRevêtement.get(i));    
        }

        
        
        return observRevêtement;
    }
*/
/*
        System.out.println("Je suise dans le constructeur :");
        ObservableList<String> revêtementObs = ObservableRevêtement();
        for (int i=0;i<revêtementObs.size();i++){
            this.revêtement.add("revêtementObs.get(i)");
            System.out.println("contenu attribut :"+this.revêtement.get(i));
        }
        
        this.listRevêtement=new ListView<>(revêtement);
*/