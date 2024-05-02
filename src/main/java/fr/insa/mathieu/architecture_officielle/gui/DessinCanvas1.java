/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author stard
 */
public class DessinCanvas1 extends Canvas{
    public DessinCanvas1(double w,double h){
        super(w,h);
        GraphicsContext context = this.getGraphicsContext2D();
        context.setFill(Color.RED);
        context.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
    
}
