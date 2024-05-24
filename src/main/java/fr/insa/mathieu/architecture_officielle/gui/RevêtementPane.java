/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle.gui;

import fr.insa.mathieu.architecture_officielle.Architecture_officielle;
import fr.insa.mathieu.architecture_officielle.Revêtement;
import fr.insa.mathieu.architecture_officielle.Sol_plafond;


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
import java.util.ArrayList;

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
        this.mainPane=mainPane;
        this.vbox=new VBox();
        
    }
    
    
    //FUNCTION
    public void affichageMur(){
        this.setMap(this.rev_Mur());
        this.listView=listView(map);        
        this.setCenter(this.listView);
        //System.out.println("Affichage Mur de la classe REVETEMENT PANE");
        System.out.println("this.contrôleur :"+this.contrôleur.toString());
    }
    public void affichageSol(){
        this.setMap(this.rev_Sol());
        this.listView=listView(map);
        this.setCenter(this.listView);
        //System.out.println("Affichage Sol de la classe REVETEMENT PANE");
    }
    public void affichagePlafond(){
        this.setMap(this.rev_Plafond());
        this.listView=listView(map);
        //System.out.println("Affichage Plafond de la classe REVETEMENT PANE");
        this.setCenter(this.listView);
    }
    
    public Revêtement getRevêtementCliqué() {
        return revêtementCliqué;
    }
    public ListView<String> listView(HashMap<Revêtement,String> map ){
        ListView<String> listview = new ListView<>();
        map.forEach(new BiConsumer<Revêtement, String>() { // BiConsumer apparu juste parce que je voulais aps utiliser l'expression lambda
            @Override
            public void accept(Revêtement t, String u) {
                listview.getItems().add(u);
            }
        });
        listview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newString) -> {
            if (newString != null) {
                System.out.println("Revêtement cliqué : "+newString);
                Revêtement revêtementTrouvé = getKeyFromValue(map, newString);
                this.revêtementCliqué=revêtementTrouvé;
                //System.out.println("revêtementTROUV2.toString() : "+revêtementTrouvé.toString());
                //System.out.println("revêtementTROUV2.getPrix_unitaire() : "+revêtementTrouvé.getPrix_unitaire());
                //System.out.println("id du revêtement (numéro)"+revêtementTrouvé.getId());
                this.contrôleur.ClicDansRevêtementPane(revêtementTrouvé);
            }
        });
        return listview;
    }
    
    public HashMap<Revêtement,String> rev_Mur(){
        HashMap<Revêtement, String> mapRevêtMur=new HashMap<>();
        for (Revêtement revêtement : this.mainPane.getModel().getListeRevêtement()){
            if(revêtement.getPourMur().equals("1")){
                String valeur=revêtement.getId()+" : "+revêtement.getDésignation()+" € : "+revêtement.getPrix_unitaire();
                mapRevêtMur.put(revêtement, valeur);
            }
        }
        
        return mapRevêtMur;
    } 
    public HashMap<Revêtement,String> rev_Sol(){
        HashMap<Revêtement, String> mapRevêtSol=new HashMap<>();
        for (Revêtement revêtement : this.mainPane.getModel().getListeRevêtement()){
            if(revêtement.getPourSol().equals("1")){
                String valeur=revêtement.getId()+" : "+revêtement.getDésignation()+" € : "+revêtement.getPrix_unitaire();
                mapRevêtSol.put(revêtement, valeur);
            }
        }
        
        return mapRevêtSol;
    } 
    public HashMap<Revêtement,String> rev_Plafond(){
        HashMap<Revêtement, String> mapRevêtPlafond=new HashMap<>();
        for (Revêtement revêtement : this.mainPane.getModel().getListeRevêtement()){
            if(revêtement.getPourPlafond().equals("1")){
                String valeur=revêtement.getId()+" : "+revêtement.getDésignation()+" € : "+revêtement.getPrix_unitaire();
                mapRevêtPlafond.put(revêtement, valeur);
            }
        }
        
        return mapRevêtPlafond;
    } 
    public HashMap<Revêtement,String> rev_Total(){
        HashMap<Revêtement, String> mapRevêtTotal=new HashMap<>();
        for (Revêtement revêtement : this.mainPane.getModel().getListeRevêtement()){
                String valeur=revêtement.getId()+" : "+revêtement.getDésignation()+" € : "+revêtement.getPrix_unitaire();
                mapRevêtTotal.put(revêtement, valeur);
            }
        
        return mapRevêtTotal;
    }
    /**
     * Pour chaque couple de clé-valeur : on test si la valeur correspond à celle que nous cherchons
     * @param map
     * @param value
     * @return 
     */
    public static Revêtement getKeyFromValue(HashMap<Revêtement, String> map, String value) {
        for (HashMap.Entry<Revêtement, String> entry : map.entrySet()) {//la classe Entry possède par pair : la clé et de la valeur
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
    
    
    /*
    //Liste des revêtements par type de surface possible
    public ArrayList<Revêtement> rev_Mur(){
        ArrayList<Revêtement> rev_mur = new ArrayList<>();
        for (Revêtement revêtement : this.mainPane.getModel().getListeRevêtement()){
            if (revêtement.getPourMur().equals("1")){
                rev_mur.add(revêtement);
            }
        }
        
        return rev_mur;
    }
    public ArrayList<Revêtement> rev_Sol(){
        ArrayList<Revêtement> rev_mur = new ArrayList<>();
        for (Revêtement revêtement : this.mainPane.getModel().getListeRevêtement()){
            if (revêtement.getPourSol().equals("1")){
                rev_mur.add(revêtement);
            }
        }
        return rev_mur;
    }
    public ArrayList<Revêtement> rev_Plafond(){
        ArrayList<Revêtement> rev_plafond = new ArrayList<>();
        for (Revêtement revêtement : this.mainPane.getModel().getListeRevêtement()){
            if (revêtement.getPourPlafond().equals("1")){
                rev_plafond.add(revêtement);
            }
        }
        return rev_plafond;
    }
*/

    public void setContrôleur(Contrôleur contrôleur) {
        this.contrôleur = contrôleur;
    }
    
}