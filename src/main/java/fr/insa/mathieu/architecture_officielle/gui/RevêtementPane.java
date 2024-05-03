/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle.gui;

import javafx.scene.control.ScrollPane;

/**
 *L'objectif ici est de créer la fenêtre en haut à gauche de la liste déroulante des revêtements
 *  Si possible afficher des revêtements qui sont cliquables pour les actionner si un mur, un sol, un plafond est selectionné
 * 
 * ex : si on clique sur un mur : on nous affiche dans cette liste la liste des revêtements possibles pour le mur
 *      si on clique sur un sol : on nous affiche dans cette liste la liste des revpetements possibles pour le sol
 *      ...
 *  Si rien n'est sélectionné : seulement afficher la liste des revêtements avec le prix au m² 
 * @author stard
 */
public class RevêtementPane extends ScrollPane{
    
    
    //CONSTRUCTOR
    public RevêtementPane(){
    
    }
}
