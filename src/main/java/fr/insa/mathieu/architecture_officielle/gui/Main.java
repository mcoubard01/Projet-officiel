/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle.gui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 *
 * @author thomas beverly
 */
    

//dans le pom : ligne 53 : <mainClass>fr.insa.mathieu.architecture_officielle.gui.Main</mainClass> 
//ceci indique que Main.java est la classe éxécutable de javaFX

//dans le pom, ligne 12 : <exec.mainClass>fr.insa.mathieu.architecture_officielle.gui.Main</exec.mainClass>
//ceci indique que gui.Main est la nouvelle classe par défaut du projet. 
//Je ne pouvais laisser Architecture_officielle comme classe éxécutable principale, car sinon javafx ne fonctionnait pas...

//dans  projet:architecture_officielle > clic droit > Properties > Actions > Run Project > "Execute Goals" > "clean javafx:run"
//"clean javafx:run" est l'instruction que De Beuvron recommendait d'indiquer ici
//sans ça, la console indique l'erreur "javaFX runtime components are missing"... Mais je n'en sais pas plus sur le fonctionneùment de JFX.

/**
 * JavaFX App
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        //var scene = new Scene(new StackPane(label), 640, 480);
        Scene scene = new Scene(new MainPane());
        stage.setScene(scene);
        /*
        Scene scene = new Scene(new MainPane());
        stage.setScene(scene);
*/
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}

