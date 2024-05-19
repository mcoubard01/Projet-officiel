/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle.gui;

import fr.insa.mathieu.architecture_officielle.Architecture_officielle;
import fr.insa.mathieu.architecture_officielle.Mur;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Transform;

/**
 *
 * @author stard
 */
public class DessinCanvas extends Pane{
    private MainPane main;
    private Canvas realCanvas;

    //CONSTRUCTOR
    public DessinCanvas(MainPane main){
        this.main=main;
        this.realCanvas=new Canvas(this.getWidth(), this.getHeight()); // (this.getWidth() reprend la valeur de largeur du Pane (la classe mère))
        this.getChildren().add(this.realCanvas); // pour ajouter le node Canvas à au Pane mère
        System.out.println("w ="+this.getWidth()+" ,h = "+this.getHeight());
        this.realCanvas.heightProperty().bind(this.heightProperty());
        this.realCanvas.widthProperty().bind(this.widthProperty());
        
        this.realCanvas.heightProperty().addListener((o) -> {
           System.out.println("w(Canvas) = "+this.realCanvas.getWidth()+" ,h(Canvas) = "+this.realCanvas.getHeight());
           this.redrawAll();
        });
        this.realCanvas.widthProperty().bind(this.widthProperty());
        this.realCanvas.widthProperty().addListener((o) -> {
           System.out.println("w(Canvas) = "+this.realCanvas.getWidth()+" ,h(Canvas) = "+this.realCanvas.getHeight());
           this.redrawAll();
        });
        
        this.realCanvas.setOnMouseClicked((t) -> {
            System.out.println("\nsetOnMouseCLICKED");
            Contrôleur control = this.main.getContrôleur();
            control.clicDansZoneDessin(t);
        });
        
        if (this.main.getContrôleur().getEtageActuel()!= null){
            
            this.redrawAll();
        }
        else {
            this.drawInitial();
        }

    }
    
    //FUNCTION
    public void redrawAll(){
        GraphicsContext context = this.realCanvas.getGraphicsContext2D();
        Architecture_officielle model= this.main.getModel();
        model.dessine(context);
        /*
        context.setFill(Color.SALMON);
        context.fillRect(0, 0, this.realCanvas.getWidth(), this.realCanvas.getHeight());
*/
    }
    private void drawInitial() {
        System.out.println("Je suis au sein de la methode drawInitial() pour le dessin du text lorsque etageActuel est null");
        GraphicsContext context = this.realCanvas.getGraphicsContext2D();
        context.setFill(Color.GOLD);
        context.strokeText("Vous devez sélectionner/ajouter un etage pour pouvoir l'afficher ou dessiner", 100, 100);
    }
    void highlight(Mur murLePlusProche) {
        GraphicsContext context = this.realCanvas.getGraphicsContext2D();
        Architecture_officielle model=this.main.getModel();
        model.highlight(context,murLePlusProche);
    }
    
    //Cette méthode est utilisée dans le controleur de De Beuvron.
    //Elle sert, je crois, à avoi la position en direct, de la souris
    public Transform getTransform() {
        return this.realCanvas.getGraphicsContext2D().getTransform();
    }

    public Canvas getRealCanvas() {
        return realCanvas;
    }
    

    
    
}