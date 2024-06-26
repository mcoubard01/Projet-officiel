/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle.gui;

import fr.insa.mathieu.architecture_officielle.Architecture_officielle;
import fr.insa.mathieu.architecture_officielle.Coin;
import fr.insa.mathieu.architecture_officielle.Etage;
import fr.insa.mathieu.architecture_officielle.Mur;
import fr.insa.mathieu.architecture_officielle.Appartement;
import fr.insa.mathieu.architecture_officielle.Facade;
import fr.insa.mathieu.architecture_officielle.gui.Contrôleur.ETAT;
import static fr.insa.mathieu.architecture_officielle.gui.Contrôleur.OBJET_SELECTIONNE.MUR;
import static fr.insa.mathieu.architecture_officielle.gui.Contrôleur.OBJET_SELECTIONNE.PLAFOND;
import static fr.insa.mathieu.architecture_officielle.gui.Contrôleur.OBJET_SELECTIONNE.SOL;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;

/**
 *
 * @author stard
 */
public class MainPane extends BorderPane {
    
    private DessinCanvas dcdessin;         //création de la zone de dessin

    private VBox vbox;
    private RadioButton rbSelect;   // bouton Selectionner
    private RadioButton rbcrpiece2;   //bouton CRer une pièce avec 2 points
    private RadioButton rbcrpiece3;   //bouton CRer une pièce avec 3 points
   
    private RadioButton rbidappart;   //bouton IDentifier un appart (en cliquant sur les pièces contenues si possible)

    private RadioButton rbouverture;
    private RadioButton rbrevêtement_rap;
    private RadioButton rbrevêtement;
    
    private RadioButton rbAnnule;
    private RadioButton rbsupp;
    private RadioButton rbEtageAj;
    
    private Contrôleur contrôleur;
    private RevêtementPane revêtementPane;
    private PrixPane prixPane;
    private Architecture_officielle model;
    
    HashMap<Etage,Button> mapEtage_Button;
    
    //issu du projet tutoVideoDessin de Beuvron (à compléter)
    private TextField tfMessage; //cela sert à afficher une instruction en bas de l'écran
    //p.ex : "merci de cliquer, entrer la hauteur de l'étage, et cliquer à nouveau."
    //ou bien : "merci de cliquer sur boutonRevetement, pour assigner un revetmeent à ce mur
    //voir changeEtat(nouvelEtat^) dans la classe Contrôleur.

    private MainMenu menu;

    //CONSTRUCTOR
    public MainPane(Architecture_officielle model){
        this.mapEtage_Button=new HashMap<>();
        this.model=model;
        this.revêtementPane= new RevêtementPane(this);
        this.prixPane = new PrixPane(this);
        this.contrôleur=new Contrôleur(this,this.revêtementPane,this.prixPane);
        this.model.setContrôleur(contrôleur);
        
        this.rbSelect=new RadioButton("Select");
        this.rbSelect.setOnAction((t) -> {
            this.contrôleur.boutonSelect(t);
        });
        
        this.rbidappart=new RadioButton("identifier un appart");
        this.rbidappart.setOnAction((t) -> {
            this.contrôleur.boutonIdAppart(t);
        });
        
        this.rbcrpiece2=new RadioButton("créer une pièce avec 2 pnts");
        this.rbcrpiece2.setOnAction((t) -> {
            this.contrôleur.boutonCrpiece2(t);
        });
        
        this.rbcrpiece3=new RadioButton("créer une pièce avec 3 pnts");
        this.rbcrpiece3.setOnAction((t) -> {
            this.contrôleur.boutonCrpiece3(t);
        });
        //rbouverture n'est activé QUE si un et un seul mur est sélectionné. (listeMurSlectionnées.size() == 1 )

        
        //rbouverture n'est activé QUE si un et un seul mur est sélectionné. (listeMurSlectionnées.size() == 1 )
        this.rbouverture=new RadioButton("ajout ouverture");
        this.rbouverture.setOnAction((t) -> {
            this.contrôleur.ajoutOuverture(t);
        });
                
                
//                .setOnAction((t) -> {
//            this.contrôleur.ajoutFenetre(t);
//            this.contrôleur.clicDansZoneDessin(null);});
        
        
        this.rbrevêtement = new RadioButton("revêtement");
        this.rbrevêtement.setOnAction((t) -> {
            if (!this.contrôleur.getListePièceSelectionnée().isEmpty()){
                    /**
                    * Affichage de deux boutons : un pour le sol, un pour le plafond pour l'ajout de revêtement. 
                    */
                    this.ajoutBtSOL_PLAFOND();
                }
            else {
                this.contrôleur.setObjetSélectionné(MUR);
            }
            this.contrôleur.affichageRevêtement();
        });
        //TODO : les initialiser avec setOnACtion(mouseevent)
        this.rbsupp= new RadioButton("Supprimer Objet");
        
        this.rbAnnule = new RadioButton("Annuler Selection");
        this.rbAnnule.setOnAction((t) -> {
            this.contrôleur.annulerSelection(t);
        });
        this.rbEtageAj= new RadioButton("ajout étage");
        this.rbEtageAj.setOnAction((t) -> {
            this.contrôleur.boutonAjEtage(t);
        });
        
        ToggleGroup bgEtat = new ToggleGroup();
        this.rbSelect.setToggleGroup(bgEtat);
        this.rbcrpiece2.setToggleGroup(bgEtat);
        this.rbcrpiece3.setToggleGroup(bgEtat);
        this.rbouverture.setToggleGroup(bgEtat);
        this.rbidappart.setToggleGroup(bgEtat);
        this.rbrevêtement.setToggleGroup(bgEtat);
        this.rbEtageAj.setToggleGroup(bgEtat);
        this.rbAnnule.setToggleGroup(bgEtat);
        //this.rbEtageAj.setSelected(true);
        //ANCIEN : this.rbSelect.setSelected(true);
        //désormais, quand on ouvre le programme, il faut créer le premier étage : 
        //clic1 -->ETAT.AJOUT_ETAGEp2, puis clic2, puis dans la console, donner la ahuteur de étagePrimitif.
        
        //disposition des éléments node entre eux (les uns au dessus des autres)
        this.vbox= new VBox(this.rbSelect,this.rbcrpiece2,this.rbcrpiece3,

                this.rbidappart, this.rbouverture,
                this.rbrevêtement, this.rbEtageAj,this.rbsupp, this.rbAnnule);
                //new Label("Pour le moment, on peut que dessiner en mode plein écran."));TODO à mieux intégrer (pas dans le VBox car pas pratique du tout
                //TODO : faire en sorte que le message ci-dessus ne prenne pas trop de place.
                //actuellement, il a doublé la largeur du VBox... BAH OUI il ne noit pas être là. Eventuellement refaire une ligne au grid pane pour mettre ces labels
      //Pourquoi Faire ???
        //System.out.println("Classe MainePane : vbGauche.toString()"+vbGauche.toString());
        //Position des éléments sur la scene
        this.vbox.setSpacing(5);
        this.setLeft(this.vbox);
        
        this.dcdessin=new DessinCanvas(this);
        this.setCenter(this.dcdessin);
        
        this.menu = new MainMenu(this);
//        this.setTop(this.menu);
        
        this.tfMessage = new TextField();
//        this.setBottom(this.tfMessage);
        
        //this.contrôleur.changeEtat(ETAT.AJOUT_ETAGE_p1);
    }
    
    //GET
    
    public PrixPane getPrixPane() {
        return prixPane;
    }
    public Architecture_officielle getModel() {
        return model;
    }
    public Contrôleur getContrôleur() {
        return contrôleur;
    }
    public RadioButton getRbSelect() {
        return rbSelect;
    }
    public RadioButton getRbidappart() {
        return rbidappart;
    }
    public RadioButton getRbcrpiece2() {
        return rbcrpiece2;
    }
    public RadioButton getRbcrpiece3() {
        return rbcrpiece3;
    }
    public RadioButton getRbouverture() {
        return rbouverture;
    }
    public RadioButton getRbrevêtement() {
        return rbrevêtement;
    }
    public RadioButton getRbsupp() {
        return rbsupp;
    }
    public RadioButton getRbEtageAj() {
        return rbEtageAj;
    }
    public DessinCanvas getDcdessin() {
        return dcdessin;
    }
    public RevêtementPane getRevêtementPane() {
        return revêtementPane;
    }
    public RadioButton getRbAnnule() {
        return rbAnnule;
    }

    /**
     * @return the menu
     */
    public MainMenu getMenu() {
        return menu;
    }

    public TextField getTfMessage() {
        return tfMessage;
    }
    
    public void changeMessage(String message) {
        this.tfMessage.setText(message);
    } 
    void redrawAll() {
        this.dcdessin.redrawAll();
    }
    void highlight(Mur murLePlusProche){
        this.dcdessin.highlight(murLePlusProche);
    }

    void ajoutBtEtage(int size) {
        Button butonEtage=new Button("Etage "+size);
        this.mapEtage_Button.put(this.contrôleur.getListeEtage().get(size-1), butonEtage);
        this.vbox.getChildren().add(butonEtage);

        butonEtage.setOnAction((t) -> {
            this.contrôleur.setEtageActuel(getKeyFromValue(mapEtage_Button, butonEtage));
            this.model.setEtageActuel(getKeyFromValue(mapEtage_Button, butonEtage));
            System.out.println("Etage actuel du batiment : "+ this.model.getEtageActuel().toString());
            GraphicsContext context  = this.dcdessin.getRealCanvas().getGraphicsContext2D();
            context.clearRect(0, 0, this.dcdessin.getRealCanvas().getWidth(), this.dcdessin.getRealCanvas().getHeight());
            this.model.dessine(context);
        });
    }
    
    public static Etage getKeyFromValue(HashMap<Etage, Button> map, Button buttonSelected) {
        for (HashMap.Entry<Etage, Button> entry : map.entrySet()) {//la classe Entry possède par pair : la clé et de la valeur
            if (entry.getValue().equals(buttonSelected)) {
                return entry.getKey();
            }
        }
        return null;
    }
/**
 * fonction appelé par le contrôleur au moment de rentrer la hauteur de l'étage grâce à un textField
 */
    public void entrerHauteurEtage() {
        this.changeMessage("Entrer la hauteur de l'étage");
        Label label = new Label("Entrez la hauteur de l'étage : ");
        TextField textField = new TextField();
        
        Button valider = new Button("valider");
        
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.getChildren().addAll(textField,valider);
        this.vbox.getChildren().addAll(label, hbox); // je superpose le label avec le HBox
        
        valider.setOnAction((t) -> {
            this.changeMessage("cliquer sur la zone de dessin pour définir les limites de l'étage");
            String input = textField.getText();
            try {
                double hauteur = Double.parseDouble(input);
                Etage etageCréé = new Etage (hauteur,this.getModel());
                
                this.getModel().add(etageCréé); // ajout de l'etage créé à la liste des étages du batiment
                this.getModel().setEtageActuel(etageCréé); // l'etage actuel du batiment est l'étage créé
                
                if(!this.contrôleur.getListeEtage().isEmpty()){
                    for (int i=0;i<this.getModel().getListe_etage().get(0).getListe_mur_facade().size();i++){
                        etageCréé.add(this.getModel().getListe_etage().get(0).getListe_mur_facade().get(i));
                    }
                }
                
                this.contrôleur.getListeEtage().add(etageCréé);
                this.contrôleur.setEtageActuel(etageCréé); // on met l'étage créé comme etage actuel du contrôleur
                
                System.out.println("taille de la liste des étages du contrôleur : "+this.contrôleur.getListeEtage().size());
                System.out.println("taille de la liste des étages du modèle : "+this.getModel().getListe_etage().size());
                
                this.ajoutBtEtage(this.getModel().getListe_etage().size());
                this.vbox.getChildren().removeAll(label,hbox);
                GraphicsContext context  = this.dcdessin.getRealCanvas().getGraphicsContext2D();
                context.clearRect(0, 0, this.dcdessin.getRealCanvas().getWidth(), this.dcdessin.getRealCanvas().getHeight());
                this.model.dessine(context);
            }
            catch (NumberFormatException e){
                System.out.println("veuillez entrez un nombre valide");
            }
            
        });
        
    }
    
    void ajoutBtSOL_PLAFOND() {
        this.changeMessage("cliquer sur SOIT SOL ou PLAFOND");
        Button sol = new Button("sol");
        Button plafond = new Button("plafond");
        Button valider = new Button("valider");
        HBox hb = new HBox();
        hb.setSpacing(5);
        hb.getChildren().addAll(sol,plafond, valider);
        this.vbox.getChildren().add(hb);
        
        this.rbEtageAj.setDisable(true);
        this.rbSelect.setDisable(true);
        this.rbcrpiece2.setDisable(true);
        this.rbcrpiece3.setDisable(true);
        this.rbouverture.setDisable(true);
        this.rbidappart.setDisable(true);
        this.rbsupp.setDisable(true);
        this.rbAnnule.setDisable(false);
        
        sol.setOnAction((t) -> {
            this.changeMessage("cliquer sur le revêtement souhaité dans la fenêtre à droite");
            System.out.print("appui sur bouton Sol");
            this.contrôleur.setObjetSélectionné(SOL);
            this.contrôleur.affichageRevêtement();
        });
        plafond.setOnAction((t) -> {
            this.changeMessage("cliquer sur le revêtement souhaité dans la fenêtre à droite");
            System.out.print("appui sur bouton Plafond");
            this.contrôleur.setObjetSélectionné(PLAFOND);
            this.contrôleur.affichageRevêtement();
        });
        valider.setOnAction((t) -> {
            this.vbox.getChildren().removeAll(hb);
            this.contrôleur.changeEtat(ETAT.SELECT);
        });
        //remove les bouttons 
    }    

    void entrerNomPièce() {
        HBox hb = new HBox();
        this.changeMessage("entrez le nom de la pièce"); // marche pour l'étage primitif
        
        Label nomPièce = new Label ("entrez le nom de la pièce");
        TextField textField = new TextField();
        Button valider = new Button("valider");
        hb.setSpacing(5);
        hb.getChildren().addAll(textField,valider);
        this.vbox.getChildren().addAll(nomPièce,hb);
        valider.setOnAction((t) -> {
            this.changeMessage("cliquer sur la deuxième coin limite de la pièce");
            String input = textField.getText();
            try {
                this.contrôleur.setNomPièce(input);
                this.vbox.getChildren().removeAll(nomPièce,hb);
            }
            catch (NumberFormatException e){
                System.out.println("veuillez entrez un nombre valide");
            }
            
        });
        
    }
}
