/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle.gui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 *
 * @author thomas beverly
 */
public class MainMenu extends MenuBar{
    
    private MainPane main;
    
    public MainMenu(MainPane main) {
        this.main = main;
        Menu file = new Menu("Fichier");
        MenuItem nouveau = new MenuItem("Nouveau");
        nouveau.setOnAction((t) -> {
            this.main.getContrôleur().menuNouveau(t);
        });
        MenuItem save = new MenuItem("Sauvegarder");
        save.setOnAction((t) -> {
            this.main.getContrôleur().menuSave(t);
        });
        MenuItem saveAs = new MenuItem("Sauvegarder sous...");
        saveAs.setOnAction((t) -> {
            this.main.getContrôleur().menuSaveAs(t);
        });
        MenuItem load = new MenuItem("Ouvrir");
        load.setOnAction((t) -> {
            this.main.getContrôleur().menuOpen(t);
        });        
        file.getItems().addAll(nouveau,save,saveAs,load);
        Menu help = new Menu("Aide");
        MenuItem apropos = new MenuItem("A propos");
        apropos.setOnAction((t) -> {
            this.main.getContrôleur().menuApropos(t);
        });        
        help.getItems().addAll(apropos);
        
        this.getMenus().addAll(file,help);
    }
    
}
