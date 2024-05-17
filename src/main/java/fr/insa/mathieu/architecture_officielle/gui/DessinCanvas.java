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
        System.out.println("DessinCanvas : je suis AVANT le this.getChildren().add(this.realCanvas)");
        this.getChildren().add(this.realCanvas); // pour ajouter le node Canvas à au Pane mère
        System.out.println("DessinCanvas : je suis APRES le this.getChildren().add(this.realCanvas)");
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
            System.out.println("setOnMouseCLICKED");
            Contrôleur control = this.main.getContrôleur();
            control.clicDansZoneDessin(t);
        });
        
        
        this.redrawAll();

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

    void highlight(Mur murLePlusProche) {
        GraphicsContext context = this.realCanvas.getGraphicsContext2D();
        Architecture_officielle model=this.main.getModel();
        model.highlight(context,murLePlusProche);
    }
    
    
}