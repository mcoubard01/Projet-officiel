/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle.gui;

import fr.insa.mathieu.architecture_officielle.Architecture_officielle;
import fr.insa.mathieu.architecture_officielle.Revêtement;
import java.util.HashMap;
import java.util.function.BiConsumer;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
    private RadioButton surface;
    private RadioButton prix;
    private RadioButton rbCroissant;
    private RadioButton rbDecroissant;
    private MainPane mainPane;
    private VBox global;
    private VBox particulier;
    private Contrôleur contrôleur;
    private double prixTotal;
    private double surfaceTotaleHabitable;
    private Label affichePrixTotal;
    private Label afficheSurface;
    
    private HashMap<Revêtement,String> map;
    private ListView<String> listView;
    
    public PrixPane(MainPane mainPane){
        this.mainPane=mainPane;
        this.prixTotal=0;
        this.surfaceTotaleHabitable=0;

        this.surface=new RadioButton("surface");
        this.prix=new RadioButton("prix");
        this.rbCroissant=new RadioButton("Croissant");
        this.rbDecroissant=new RadioButton("Decroissant");
        
        HBox hboxType = new HBox(this.surface,this.prix);
        ToggleGroup tg = new ToggleGroup();
        this.surface.setToggleGroup(tg);
        this.prix.setToggleGroup(tg);
        
        HBox hboxEvol;
        hboxEvol = new HBox(this.rbCroissant,this.rbDecroissant);
        ToggleGroup tg2 = new ToggleGroup();
        this.rbCroissant.setToggleGroup(tg2);
        this.rbDecroissant.setToggleGroup(tg2);
        
        VBox filtre = new VBox(hboxType,hboxEvol);
        System.out.println("Classe Prix Pane : filtre.toString()"+filtre.toString());
        
        Label titre = new Label("Devis : ça va vous couter cher");
        VBox presentation = new VBox(titre,filtre);
        
        this.affichePrixTotal = new Label("Prix total = "+this.prixTotal+" € !");
        this.afficheSurface= new Label ("surface habitable = "+this.surfaceTotaleHabitable+" UNITE");
        this.global = new VBox(this.affichePrixTotal,this.afficheSurface);
       
        this.setBottom(global);
        this.setTop(presentation);
    }
    
    //FUNCTION
    public void affichageListRevêtement(){
        this.setMap(this.mapRevêtement());
        this.listView=listView(map);        
        this.setCenter(this.listView);
        //System.out.println("Affichage Mur de la classe REVETEMENT PANE");
        System.out.println("this.contrôleur :"+this.contrôleur.toString());
    }
    public HashMap<Revêtement,String> mapRevêtement(){
        HashMap<Revêtement, String> mapRevêtement=new HashMap<>();
        for (Revêtement revêtement : this.mainPane.getModel().getListeRevêtement()){
            if(revêtement.getPourMur().equals("1")){
                String valeur=revêtement.getDésignation()+", Surface: "+this.mainPane.getModel().surface(revêtement)+"m², Prix : "+this.mainPane.getModel().prix(revêtement)+" €";
                mapRevêtement.put(revêtement, valeur);
            }
        }
        return mapRevêtement;
    }
    public ListView<String> listView(HashMap<Revêtement,String> map ){
        ListView<String> listview = new ListView<>();
        map.forEach(new BiConsumer<Revêtement, String>() { // BiConsumer apparu juste parce que je voulais aps utiliser l'expression lambda
            @Override
            public void accept(Revêtement t, String u) {
                listview.getItems().add(u);
            }
        });
        return listview;
    }
    public void reCalcule(Architecture_officielle batiment){
        System.out.println("Je suis dans la boucle de recalcule");
        
        this.surfaceTotaleHabitable=batiment.surfaceTotalHabitable();
        this.afficheSurface.setText("surface habitable = "+this.surfaceTotaleHabitable+" UNITE");
        
        this.prixTotal=batiment.prixTotal();
        this.affichePrixTotal.setText("prix total : "+this.prixTotal+" €");
        
        this.affichageListRevêtement();
        System.out.println("prix total : "+this.prixTotal);
        System.out.println("surface totale au sol : "+this.surfaceTotaleHabitable);
    }
    /*
    public void reCalculePrix(Architecture_officielle batiment){
        this.prixTotal=batiment.prixTotal();
        this.affichePrixTotal.setText("prix total : "+this.prixTotal+" €");
        this.affichageListRevêtement();
        System.out.println("prix total : "+this.prixTotal);
        System.out.println("surface totale au sol : "+this.surfaceTotaleHabitable);
    }
*/
    
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
    public void setMap(HashMap<Revêtement, String> map) {
        this.map = map;
    }
    
}