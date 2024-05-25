
package fr.insa.mathieu.architecture_officielle.gui;

import fr.insa.mathieu.architecture_officielle.Coin;
import fr.insa.mathieu.architecture_officielle.Fenêtre;
import fr.insa.mathieu.architecture_officielle.Mur;
import fr.insa.mathieu.architecture_officielle.Ouverture;
import fr.insa.mathieu.architecture_officielle.Porte;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * Creation d'un Dialog pour permettre à l'utilisateur d'entrer les coordonnées
 * et la couleur d'un Point. Classe structurée en s'inspirant de l'exemple 5 de
 * :
 * https://examples.javacodegeeks.com/desktop-java/javafx/dialog-javafx/javafx-dialog-example/
 *
 * @author francois de Beuvron
 */
// la classe Dialog est une classe générique : le paramètre de type (ici Point)
// indique quel est le type de l'objet éventuellement créé/retourné par le Dialog
public class EnterOuvertureDialog extends Dialog<Ouverture> {
    
    private TextField  tfCoordOrigine;
    private TYPE_OUVERTURE typeDeOuvertureCréée;
    private double[] intervalleCoordOrigine = new double[2];
    private enum TYPE_OUVERTURE{
        FENÊTRE,
        PORTE,
    }
    private RadioButton rbPorte;
    private RadioButton rbFenêtre;
            

        
    /**
     * Entrer une ouverture grâce à la fenêtre de dialogue.
     * @param mur1
     * @param mur2 //mur2 n'a désormais plus lieu d'être. Parce que nous devons rendre le projet, nous avons décidé de ne pas assigner de mur2 aux Ouvertures. 
     * //il pourrait cependant être utile pour donner plus de fonctionnalités au programme par la suite.
     */
    public EnterOuvertureDialog(Mur mur1, Mur mur2) {
        this.setTitle("entrez les coordonnées du Point d'origine :");
        this.setResizable(true);
        
        this.intervalleCoordOrigine = this.setIntervalle(mur1, mur2);
        //intervalleCoordOrigine est l'intervalle dans lequel il est sécurisé 
        //(vis à vis de l'apparteneance au mur) de mettre l'origine du point
        Label lInstructions = new Label("merci d'entrer entre " + this.intervalleCoordOrigine[0] + " et " + this.intervalleCoordOrigine[1] + ".");
        Label lCoordOrigine = new Label("coord origine:");
        this. tfCoordOrigine = new TextField("");
        
        this.rbFenêtre=new RadioButton("Fenêtre");
        this.rbFenêtre.setOnAction((t) -> {
            typeDeOuvertureCréée = TYPE_OUVERTURE.FENÊTRE;
        });
        this.rbPorte=new RadioButton("Porte");
        this.rbPorte.setOnAction((t) -> {
            typeDeOuvertureCréée = TYPE_OUVERTURE.PORTE;
        });
        ToggleGroup typeDeOuverture = new ToggleGroup();
        this.rbFenêtre.setToggleGroup(typeDeOuverture);
        this.rbPorte.setToggleGroup(typeDeOuverture);
        //this.rbPorte.setSelected(true);

        GridPane grid = new GridPane();
        grid.add(this.rbFenêtre, 0, 0);
        grid.add(this.rbPorte, 1, 0);
        grid.add(lInstructions, 0, 1);
        grid.add(lCoordOrigine, 0, 2);
        grid.add(this. tfCoordOrigine, 1, 2);
        this.getDialogPane().setContent(grid);

        ButtonType bOK = new ButtonType("OK", ButtonData.OK_DONE);
        ButtonType bCancel = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
        this.getDialogPane().getButtonTypes().add(bOK);
        this.getDialogPane().getButtonTypes().add(bCancel);

        Coin origineOuverture = new Coin();
        if (mur1.horizontal()){
            origineOuverture.setY(mur1.getDebut().getY());
        }else{
            origineOuverture.setX(mur1.getDebut().getX());
        }
        this.setResultConverter((p) -> {
            if (p == bOK) {
                double coordOrigine;
                char orientation = ' ';
                try {
                    coordOrigine = Double.parseDouble(this. tfCoordOrigine.getText());
                    System.out.println("EnterOuvertureDialog : création du point d'origine.");
                    if (mur1.horizontal()){
                        origineOuverture.setX(coordOrigine);
                        orientation = 'E';
                    }
                    else{
                        origineOuverture.setY(coordOrigine);
                        orientation = 'N';

                    }
                } catch (NumberFormatException ex) {
                    return null;
                }
                
                if ( ! (this.intervalleCoordOrigine[0] < coordOrigine && coordOrigine < intervalleCoordOrigine[1])){
                    //"si la coordinnée spécifié n'est pas dans le bon intervalle"
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("OPERATION ANNULEE");
                    alert.setHeaderText(null);
                    alert.setContentText("vous n'avez pas spécifié des valeurs dans le bon intervalle\n"
                    + "la création a été annulée\n");
                    alert.showAndWait();
                    return null;
                }else{
                    if (this.typeDeOuvertureCréée == TYPE_OUVERTURE.FENÊTRE){
                        Fenêtre resultat = new Fenêtre(origineOuverture.getX(),origineOuverture.getY(),orientation,mur1);
                        System.out.println("création de l'ouverture : " + resultat.toString());
                        return resultat;
                    }
                    else if (this.typeDeOuvertureCréée == TYPE_OUVERTURE.PORTE){
                        Porte resultat = new Porte(origineOuverture.getX(),origineOuverture.getY(),orientation,mur1);
                        System.out.println("création de l'ouverture : " + resultat.toString());
                        return resultat;

                    }
                    else{
                        return null;
                    }
                }
            } else {
                return null;
            }
        }
        );

    }
    /**
     * 
     * @param mur1
     * @param mur2
     * @return intervalle : intervalle de valeurs dans lequel il est sécurisé de mettre l'orogine de l'ouverture.
     */
    public double[] setIntervalle(Mur mur1, Mur mur2){
        System.out.println("vous êtes dans setIntervalle");
        Mur  murMin = mur1;
        if (mur2 != null){
             if (mur2.longueur() > mur1.longueur()){
                 murMin = mur2;
                 //cas de deux murs superposés :     --------    <--ici, c'est lui le murMin
                 //                            -----------------------
             }
            //L'ouverture, si elle a deux murs, doit être comprise dans le mur le plus petit des deux.
        }
        double rapportMètresVersPixel = 100/2;// 100 pixels = 2 mètres
        double décalage = 2*rapportMètresVersPixel;
        double[] intervalle = new double[2];
        if (typeDeOuvertureCréée == TYPE_OUVERTURE.FENÊTRE){
            décalage=1.20*rapportMètresVersPixel; //l'ouverture est orientée EST ou NORD: on ne doit donc pas entrer une origine trop proche du coin le plus à droite ou le plus en haut.
        }else if(typeDeOuvertureCréée == TYPE_OUVERTURE.PORTE){
            décalage = 0.90*rapportMètresVersPixel;
        }
        
        if(murMin.horizontal()){
            intervalle[0] = Math.min(murMin.getDebut().getX(),murMin.getFin().getX()); //le coin le plus à gauche (origine du repère en haute à gauche de l'écran)
            intervalle[1] = Math.max(murMin.getDebut().getX(), murMin.getFin().getX()) - décalage - 3; //le coin le plus à droite
            //l'Ouverture étant orientée EST, l'ouverture ne doit pas dépasser des limites du mur. d'où le décalage.
        }else{
            intervalle[0] = Math.min(murMin.getDebut().getY(),murMin.getDebut().getY()) + décalage + 3; //le coin le plus haut (origine du repère en haut à gauche de l'écran)
            intervalle[1] = Math.max(murMin.getFin().getY(),murMin.getDebut().getY()); //le coin le plus bas.
            //l'Ouverture étant orientée NORD, l'uverture ne doit pas dépasser des limites du mur. d'ooù le décalage.
        }
        intervalle[0] = Math.round(intervalle[0]);
        intervalle[1] = Math.round(intervalle[1]);
        return intervalle;
        
    }
    
    public static Optional<Ouverture> nouvelleOuverture(Mur mur1, Mur mur2) {
        EnterOuvertureDialog dialog = new EnterOuvertureDialog(mur1, mur2);
        return dialog.showAndWait();
    }

}
