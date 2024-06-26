/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle.gui;

import fr.insa.mathieu.architecture_officielle.Coin;
import fr.insa.mathieu.architecture_officielle.Mur;
import fr.insa.mathieu.architecture_officielle.Pièce;
import fr.insa.mathieu.architecture_officielle.Plafond;
import fr.insa.mathieu.architecture_officielle.Sol;
import fr.insa.mathieu.architecture_officielle.Appartement;
import fr.insa.mathieu.architecture_officielle.Etage;
import fr.insa.mathieu.architecture_officielle.Facade;
import fr.insa.mathieu.architecture_officielle.Porte;
import fr.insa.mathieu.architecture_officielle.Fenêtre;
import fr.insa.mathieu.architecture_officielle.Architecture_officielle;
import fr.insa.mathieu.architecture_officielle.IDManager;
import fr.insa.mathieu.architecture_officielle.Lire;
import fr.insa.mathieu.architecture_officielle.Ouverture;
import java.io.File;//TODO à voir si utilisé
import static fr.insa.mathieu.architecture_officielle.Mur.longueur;
import fr.insa.mathieu.architecture_officielle.Revêtement;
import fr.insa.mathieu.architecture_officielle.Sol_plafond;
import static fr.insa.mathieu.architecture_officielle.gui.Main.customLaunch;
import java.util.ArrayList;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 *  VIDEO à reprendre (la 7 et 8)
 * 
 * TODO : ptLineDist(double px, double py) pour avoir la distance de ton clic (en paramètre sa position X et Y) à une ligne (le mur)<mur>.ptLineDist(t.getX(),t.getY());
 FAIT
 * @author mathieu
 */
public class Contrôleur {
    ETAT etat;
    private OBJET_SELECTIONNE objetSélectionné;
    private MainPane vue;    
    private RevêtementPane vueRevetement;
    private PrixPane vuePrix;
    //private ArrayList<Double> pos=new ArrayList<>();
    private double[] pos = new double[100];
    private ArrayList<Etage> listeEtage;
    private Etage etagePrimitif;
    private ArrayList<Mur> listeMurSelectionné;
    private ArrayList<Pièce> listePièceSelectionnée;
    public static double DISTMAXCLIQUE=20;
    private Etage etageActuel;

    private double hauteurEtage;// TODO a voir si tuilisé
    private Mur dernierMurSélectionné;
    private Coin[] coinProche = new Coin[3];
    private String nomPièce;//TODO a voir si utilisé
    
    public enum ETAT{
        SELECT,
        AJOUT_ETAGEp1,
        AJOUT_ETAGEp2,
        CREA_PIECE_2PNT_p1,
        CREA_PIECE_2PNT_p2,
        CREA_PIECE_3PNT_p1,
        CREA_PIECE_3PNT_p2,
        CREA_PIECE_3PNT_p3,
        AJOUT_OUVERTURE,
        AJOUT_REVETEMENT_p1,
        AJOUT_REVETEMENT_p2,
        AJOUT_REVETEMENT_p3,

        //bloc note
    }
    
    /**
     * OBJET_SELECTIONNE est prévu pour être comme un test boolean. 
     * par exemple:
     * Quand on sélectionne une pièce, on assigne à this.objetSélectionné la valeur OBJET_SELECTIONNE.PIECE .
     * listePièceSelectionnée ne s'agrandit si la sélection actuelle est bien une pièce
     * voir plus bas.
     */
    enum OBJET_SELECTIONNE{
        RIEN,
        COIN, //inutilisé?
        MUR,
        FACADE, //inutilisé?
        PIECE,
        SOL,
        PLAFOND,
        APPARTEMENT, 
    }
        
    public Contrôleur(MainPane vue){
        this.vue=vue;
        this.listeMurSelectionné=new ArrayList<>();
        this.listeEtage=new ArrayList<>();
    }
    public Contrôleur(MainPane vue,RevêtementPane vueRevetement, PrixPane vuePrix){
        this.vue=vue;
        this.vueRevetement=vueRevetement;
        this.vueRevetement.setContrôleur(this);
        this.vuePrix=vuePrix;
        this.vuePrix.setContrôleur(this);
        this.listeEtage=new ArrayList<>();
        this.listeMurSelectionné=new ArrayList<>();
        this.listePièceSelectionnée=new ArrayList<>();
    }
    public void changeEtat(ETAT nouvelEtat){//int nouvelEtat => Etat nouvelEtat
        //this.vue.changeMessage("bonjour"); //affiche le message voulu dans la zone de texte en bas de l'écran.
        switch(nouvelEtat){
            case SELECT://case :Etat.Select:
                this.vue.changeMessage("clic pour selection ; <shift> + clic : ajouter ; <ctrl> + clic : ajouter/enlever");
                this.vue.getRbSelect().setDisable(false);
                //il faut le rallumer car je l'ai éteint dans l'état AJOUT_ETAGEp2.
                this.vue.getRbidappart().setDisable(false);
                if (this.listeMurSelectionné.size()==1){
                    this.vue.getRbouverture().setDisable(false);
                }
                else{
                    this.vue.getRbouverture().setDisable(true);
                }


                this.vue.getRbEtageAj().setDisable(false);
                this.vue.getRbcrpiece3().setDisable(false);
                this.vue.getRbcrpiece2().setDisable(false);
                this.vue.getRbrevêtement().setDisable(false);
                this.vue.getRbidappart().setDisable(false);
                this.vue.getRbsupp().setDisable(false);
                this.vue.getRbAnnule().setDisable(false);
                break;
            case CREA_PIECE_2PNT_p1:
                this.vue.changeMessage("clic pour définir le début de la pièce");
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbouverture().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                break;
            case CREA_PIECE_2PNT_p2:
                //this.vue.changeMessage("Rentrer le nom de la pièce");
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbouverture().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                break;
            case CREA_PIECE_3PNT_p1:
                this.vue.changeMessage("clic pour définir le premier point de la pièce");
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbouverture().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                break;
            case CREA_PIECE_3PNT_p2:
                this.vue.changeMessage("clic pour définir le deuxième point de la pièce (c'est une diagonale)");
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbouverture().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                break;
            case CREA_PIECE_3PNT_p3:
                this.vue.changeMessage("clic pour définir le troisième point");
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbouverture().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                break;
            case AJOUT_OUVERTURE:
                this.vue.changeMessage("merci d'entrer dans la fenêtre de dialogue les coordonnées de l'ouverture.");
                this.vue.getRbSelect().setDisable(true);
                    //il faut le rallumer car je l'ai éteint dans l'état AJOUT_ETAGEp2.
                this.vue.getRbidappart().setDisable(true);         
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                this.vue.getRbcrpiece2().setDisable(true);
                this.vue.getRbcrpiece3().setDisable(true);
                this.vue.getRbsupp().setDisable(true);
                break;
            case AJOUT_ETAGEp1: 
                if (etagePrimitif==null){
                    this.vue.changeMessage("cliquez pour définir un coin du contour du bâtiment");
                    this.vue.getRbSelect().setDisable(true);
                    //lors de la création du premier étage, on ne peut pas encore select.
                }else{
                    this.vue.changeMessage("Merci cliquer n'importe où, puis ENTRER LA HAUTEUR DE L'ETAGE");

                }
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbouverture().setDisable(true);
                this.vue.getRbEtageAj().setDisable(false);
                this.vue.getRbcrpiece2().setDisable(true);
                this.vue.getRbcrpiece3().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                break;
            case AJOUT_ETAGEp2: //ATTENTION : AJOUT_ETAGEp2 n'est actif que au tout début, à la création du premier étage.
                this.vue.getRbSelect().setDisable(true);
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbouverture().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbcrpiece2().setDisable(true);
                this.vue.getRbcrpiece3().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                break;         
        }
        this.etat=nouvelEtat;
    }
    void ClicDansRevêtementPane(Revêtement revêtementTrouvé) {
        switch (objetSélectionné){
            case MUR:
                for (Mur mur : this.listeMurSelectionné){
                    System.out.println("AJOUT DU REVETEMENT" );
                    mur.setRevêtement(revêtementTrouvé);
                    System.out.println("ID DU MUR POUR VERIFICATION : "+mur.toString());
                }
                this.vuePrix.reCalcule(this.vue.getModel());
                this.objetSélectionné= OBJET_SELECTIONNE.RIEN;
                this.vue.changeMessage("cliquer sur valider");
                break;
            case SOL:
                for(Pièce pièce : this.listePièceSelectionnée){
                    System.out.println("AJOUT DU REVETEMENT" );
                    pièce.getSol().setRevêtement(revêtementTrouvé);
                    System.out.println("pièce.toString()  : "+pièce.toString());
                }
                this.vuePrix.reCalcule(this.vue.getModel());
                this.objetSélectionné= OBJET_SELECTIONNE.RIEN;
                this.vue.changeMessage("cliquer sur valider");
                break;
            case PLAFOND:
                for(Pièce pièce : this.listePièceSelectionnée){
                    System.out.println("AJOUT DU REVETEMENT" );
                    pièce.getPlafond().setRevêtement(revêtementTrouvé);
                    System.out.println("plafond.toString();"+pièce.toString());
                }
                this.vuePrix.reCalcule(this.vue.getModel());
                this.objetSélectionné= OBJET_SELECTIONNE.RIEN;
                this.vue.changeMessage("cliquer sur valider");
                break;
        }
    }

    void clicDansZoneDessin(MouseEvent t) {
        switch (etat){
            case SELECT:
                System.out.println("Je suis en etat SELECT");
                System.out.println("ETAGE ACTUEL DU CONTROLEUR"+this.etageActuel);
                this.vue.changeMessage("clic pour selection ; <shift> + clic : ajouter ; <ctrl> + clic : ajouter/enlever");
                this.pos[0]=t.getX();
                this.pos[1]=t.getY();
                double distanceMinimale=Double.POSITIVE_INFINITY; //C'est quoi ça? Merci de mettre un commentaire explicatif svp merci
                Coin coinCliq=new Coin(); //construit un Coin qui n'a pas d'identifiant.
                coinCliq.setX(this.pos[0]);
                coinCliq.setY(this.pos[1]);

                System.out.println("position du clique : (x,y) : ("+this.pos[0]+","+this.pos[1]+")");
                //TODO : coinCliq n'ets pas une bonne idée car cela va ajouter un coin au gestionnaire d'identifiant!!
                
                Pièce pièceSelectionnée = new Pièce();
                Mur murLePlusProche = new Mur();
                objetSélectionné = OBJET_SELECTIONNE.RIEN;
                
                    //for(Pièce pièce : IDManager.toutesLesPièces(this.etageActuel)){
                    //la fonction ci dessus pourrait remplacer remplacer la deuxième boucle 
          /**
          * Première partie : selection de pièce
          */
                
                for(Pièce pièce : this.etageActuel.getListPièceOrpheline()){
                    //System.out.println("resultat de la methode pieceSelect"+pièce.pieceSelect(coinCliq));
                    if(pièce.pieceSelect(coinCliq)==true){
                        System.out.println("Selection de pièce dans l'ETAT SELECT de la classe contrôleur ligne 275");
                        pièceSelectionnée=pièce;
                        this.objetSélectionné=OBJET_SELECTIONNE.PIECE;
                    }
                }
                //la boucle ci-dessous et celle ci dessus pourrait-être remplacée par une seule boucle itérant sur IDManager.toutesLesPièces(etageActuel) que je viens de créer (T.B., 19/05/24)
                for(Appartement appartement : this.etageActuel.getListe_appartement()){
                    for(Pièce pièce:appartement.getListe_pièce()){
                        if(pièce.pieceSelect(coinCliq)){
                            pièceSelectionnée=pièce;
                            this.objetSélectionné=OBJET_SELECTIONNE.PIECE;
                        }

                    }
                }
                
                //recherchePièces(this,t);
        /**
         * Deuxième Partie : touche control poour les pièces à selectionner
         */       
                
                if (objetSélectionné==OBJET_SELECTIONNE.PIECE){
                    
                    if (! this.listeMurSelectionné.isEmpty()){
                        
                        //"si je sélectionne une pièce APRES avoir selectionné un mur, plus aucun mur n'est sélectionné.
                        listeMurSelectionné.clear();
                    }
                    if(t.isControlDown()){
                                if(this.listePièceSelectionnée.contains(pièceSelectionnée)){
                                    this.listePièceSelectionnée.remove(pièceSelectionnée);
                                }
                                else{
                                    this.listePièceSelectionnée.add(pièceSelectionnée);
                                    
                                }
                     } 
                     else {
                                this.listePièceSelectionnée.clear();
                                this.listePièceSelectionnée.add(pièceSelectionnée);
                            }
                }
        /**
         * Troisième partie : selection de mur
         */
                
                if (objetSélectionné != OBJET_SELECTIONNE.PIECE){
                    //Si on a sélectionné une pièce, notre clic ne sélectionne pas un mur en même temps.
                    
                        for(Pièce pièce:this.etageActuel.getListPièceOrpheline()){
                            for(Mur mur : pièce.getListe_mur()){
                                if(mur.DistanceMurClique(coinCliq, DISTMAXCLIQUE)<distanceMinimale){
                                    distanceMinimale=mur.DistanceMurClique(coinCliq, DISTMAXCLIQUE);
                                    //TODO enregistrer le mur corespondant à la distance minimale
                                    murLePlusProche=mur;
                                    this.objetSélectionné=OBJET_SELECTIONNE.MUR;
                                    System.out.println("mur le plus proche de ta première boucle : "+mur.toString());
                                }
                            }
                        }
                        for (Appartement appartement : this.etageActuel.getListe_appartement()){
                            for(Pièce pièceAppart:appartement.getListe_pièce()){
                                for(Mur mur1:pièceAppart.getListe_mur()){
                                    if(mur1.DistanceMurClique(coinCliq, DISTMAXCLIQUE)<distanceMinimale){
                                        distanceMinimale=mur1.DistanceMurClique(coinCliq, DISTMAXCLIQUE);
                                        murLePlusProche=mur1;
                                        this.objetSélectionné=OBJET_SELECTIONNE.MUR;
                                        System.out.println("mur le plus proche de ta deuxième boucle : "+mur1.toString());
                                    }
                                }
                            }
                        }
                        for(Facade facade : this.etageActuel.getListe_mur_facade()){
                            if(facade.distanceFacadeClique(coinCliq, DISTMAXCLIQUE)<distanceMinimale){
                                distanceMinimale=facade.distanceFacadeClique(coinCliq, DISTMAXCLIQUE);
                                murLePlusProche=facade; 
                                this.objetSélectionné=OBJET_SELECTIONNE.FACADE;
                                System.out.println("mur le plus proche de ta troisième boucle : "+facade.toString());
                            }
                        }
                        System.out.println("mur le plus proche retenu  : "+murLePlusProche.toString());
                    
                }//fin de la condition (objSel != OBJ_SEL.PIECE) 
        /**
         * Quatrième partie : touche control pour la selection de mur
         */
                
                if (objetSélectionné==OBJET_SELECTIONNE.MUR || objetSélectionné==OBJET_SELECTIONNE.FACADE){
                    //" si l'objet sélectionné est un mur ou une facade, l'ajouter à listeMurSelectionné
                    if ( ! this.listePièceSelectionnée.isEmpty()){ //si PAS vide, alors : 
                        //"si je sélectionne un mur APRES avoir selectionné une pièce, plus aucune poèce n'est sélectionnée.
                        listePièceSelectionnée.clear();
                    }
                    if(t.isControlDown()){
                       if(this.listeMurSelectionné.contains(murLePlusProche)){
                           this.listeMurSelectionné.remove(murLePlusProche);
                       }
                       else{
                           this.listeMurSelectionné.add(murLePlusProche);
                       }
                    } 
                    else {
                       this.listeMurSelectionné.clear();
                       this.listeMurSelectionné.add(murLePlusProche);
                    }
                    
                }
        
                System.out.println("l'objet sélectionné par ce clic est : "+ objetSélectionné.name());
                System.out.println("Liste de mur SELECTIONNE : "+listeMurSelectionné.size() + "\nles voici : ");
                for(int i =0;i<listeMurSelectionné.size();i++){
                    System.out.println(listeMurSelectionné.get(i).toString());
                }
                if(!this.listeMurSelectionné.isEmpty()){
                    this.dernierMurSélectionné = this.getListeMurSélectionné().get(this.listeMurSelectionné.size()-1);
                }
                //listeMurSélectionné est réinitialisée à l'entrée de SELECT.

                if ( ! this.listeMurSelectionné.isEmpty()){
                    this.dernierMurSélectionné = this.listeMurSelectionné.get(this.listeMurSelectionné.size()-1);
                }
                //listeMurSélectionné est réinitialisée à l'entrée de SELECT, et on réentre dans SELECT plusieurs fois, apparemment...
                this.activeBoutonSuivantSelection();
                //System.out.println(this.dernierMurSélectionné.surface());
                objetSélectionné = OBJET_SELECTIONNE.RIEN;//réinitialisatioon du type d'objet sélectionné.
                this.vue.redrawAll(); //permet de faire le highlight de la sélection
                this.changeEtat(ETAT.SELECT); //il faut le remettre sinon les boutons normalement actifs sont désactivés.
                break;
            case CREA_PIECE_2PNT_p1:
                System.out.println("ETAT CREA_PIECE_2PNT_p1 de création de pièce 2 points");
                //this.pos.add(0, t.getX());
                //this.pos.add(1, t.getY());
                this.pos[0]=t.getX();
                this.pos[1]=t.getY();
                System.out.println("coordonée du clic 1 : (x,y) => ("+this.pos[0]+","+this.pos[1]+")");
                Coin clic = new Coin();
                clic.setX(this.pos[0]);
                clic.setY(this.pos[1]);
                
                this.coinProche[0]=this.coinProche(clic);
                if (this.coinProche[0]!=null){
                    this.pos[0]= this.coinProche[0].getX();
                    this.pos[1]= this.coinProche[0].getY();
                }
                else {
                    System.out.println("Il n'y a pas de coin assez proche");
                    
                }
                this.vue.entrerNomPièce();
                this.changeEtat(ETAT.CREA_PIECE_2PNT_p2);
                break;
            case CREA_PIECE_2PNT_p2:
                System.out.println("ETAT CREA_PIECE_2PNT_p1 de création de pièce 2 points");
                System.out.println("EtageActuel : "+this.etageActuel);
                double x2=t.getX();
                double y2=t.getY();
                /**
                 * Recherche de coin les plus proche pour les utiliser pour dessiner. 
                 */
                Coin clic2 = new Coin();
                clic2.setX(x2);
                clic2.setY(y2);
                this.coinProche[1]=this.coinProche(clic2);
                if (this.coinProche[1]!=null){
                    x2 = this.coinProche[1].getX();
                    y2 = this.coinProche[1].getY();
                }
                else {
                    System.out.println("Il n'y a pas de coin assez proche");
                }
                
                //System.out.println("coordonée du clic : (x,y) => ("+this.pos.get(0)+","+this.pos.get(1)+")");
                System.out.println("coordonée du clic 2 : (x,y) => ("+x2+","+y2+")");
                //Coin coinA= new Coin(this.pos.get(0), this.pos.get(1));
                Coin coinA= new Coin(this.pos[0], this.pos[1]);
                //Coin coinB= new Coin(this.pos.get(0), y2);
                Coin coinB= new Coin(this.pos[0], y2);
                Coin coinC= new Coin(x2, y2);
                //Coin coinD= new Coin(x2, this.pos.get(1));
                Coin coinD= new Coin(x2, this.pos[1]);
                System.out.println("COORDONEE des points : Coin A ("+coinA.getX()+","+coinA.getY()+")");
                System.out.println("COORDONEE des points : Coin B ("+coinB.getX()+","+coinB.getY()+")");
                System.out.println("COORDONEE des points : Coin C ("+coinC.getX()+","+coinC.getY()+")");
                System.out.println("COORDONEE des points : Coin D ("+coinD.getX()+","+coinD.getY()+")");
                Mur murAB=new Mur(coinA, coinB, this.etageActuel);
                System.out.println("Je suis entre la création du premier mur et celle du deuxième mur ");
                Mur murBC=new Mur(coinB, coinC,this.etageActuel);
                Mur murCD=new Mur(coinC, coinD,this.etageActuel);
                Mur murDA=new Mur(coinD, coinA,this.etageActuel);
                System.out.println("Je suis juste AVT le contrôleur de pièce vide");
                int nbrEtage = this.listeEtage.size();
                Pièce pièce2 = new Pièce(etageActuel);//TODO essai avec EtageActuel
                pièce2.setNom_pièce(nomPièce);
                System.out.println("Je suis juste APRES le contrôleur de pièce vide");
                pièce2.setÉtage(this.vue.getModel().getListe_etage().get(0)); ///// TODO etage actuelle à selectionner
                pièce2.add(murAB);
                pièce2.add(murBC);
                pièce2.add(murCD);
                pièce2.add(murDA);
                System.out.println("murAB.toString() :"+murAB.toString());
                System.out.println("murBC.toString() :"+murBC.toString());
                System.out.println("murCD.toString() :"+murCD.toString());
                System.out.println("murDA.toString() :"+murDA.toString());
                //d'après vidéo il fautdrait mettre this.vue.redrawAll()
                System.out.println("pièce.toString() : "+pièce2.toString());
                this.vue.redrawAll();
                this.vuePrix.reCalcule(this.vue.getModel());
                this.changeEtat(ETAT.CREA_PIECE_2PNT_p1);
                System.out.println("RETOUR ETAT CREA_PIECE_2PNT_p2 de création de pièce 2 points");
                break;
                /**
                 * TODO : la création de pièce 3 points possède un défaut de fonctionnement...
                 * 18/05,brnch de thomas : actuellement elle fonctionne.
                 */
            case CREA_PIECE_3PNT_p1:

                System.out.println("ETAT CREA_PIECE_3PNT_p1 de création de pièce 3 points");
                System.out.println("EtageActuel : "+this.etageActuel);
                //CLIC 1
                
                this.pos[0]=t.getX();
                this.pos[1]=t.getY();
                clic = new Coin();
                clic.setX(this.pos[0]);
                clic.setY(this.pos[1]);                
                this.coinProche[0]=this.coinProche(clic);
                if (this.coinProche[0]!=null){
                    this.pos[0]= this.coinProche[0].getX();
                    this.pos[1]= this.coinProche[0].getY();
                }
                else {
                    System.out.println("Il n'y a pas de coin assez proche");
                    
                }
                this.vue.entrerNomPièce();
                this.changeEtat(ETAT.CREA_PIECE_3PNT_p2);
                break;
            case CREA_PIECE_3PNT_p2:   
                System.out.println("ETAT CREA_PIECE_3PNT_p2 de création de pièce 2 points");
                //CLIC 2
                this.pos[2]=t.getY();
                x2=t.getX();
                
                System.out.println("coordonée du clic 2 : (x,y) => (rien enregistré,"+this.pos[2]+")");
                clic2 = new Coin();
                clic2.setX(x2);
                clic2.setY(this.pos[2]);
                this.coinProche[1]=this.coinProche(clic2);
                if (this.coinProche[1]!=null){
                    System.out.println("J'ai trouvé un coin proche");
                    this.pos[2] = this.coinProche[1].getY();
                }
                else {
                    System.out.println("Il n'y a pas de coin assez proche");
                }
                this.changeEtat(ETAT.CREA_PIECE_3PNT_p3);
                break;
            case CREA_PIECE_3PNT_p3:
                System.out.println("ETAT CREA_PIECE_3PNT_p3 de création de pièce 3 points");
                this.pos[3]=t.getX();
                y2=t.getY();
                Coin clic3 = new Coin();
                clic3.setX(this.pos[3]);
                clic3.setY(y2);
                
                this.coinProche[2]=this.coinProche(clic3);
                if (this.coinProche[2]!=null){
                    System.out.println("J'ai trouvé un coin proche p3");
                    this.pos[3] = this.coinProche[2].getX();
                }
                else {
                    System.out.println("Il n'y a pas de coin assez proche");
                }
                System.out.println("coordonée du clic 3 : (x,y) => ("+this.pos[3]+",rien enregistré)");
                Coin coinE= new Coin(this.pos[0], this.pos[1]);
                Coin coinF= new Coin(this.pos[0], this.pos[2]);
                Coin coinG= new Coin(this.pos[3],this.pos[1]);
                Coin coinH= new Coin(this.pos[3], this.pos[2]);
                Mur murEF=new Mur(coinE, coinF,this.etageActuel);
                Mur murFH=new Mur(coinF, coinH,this.etageActuel);
                Mur murHG=new Mur(coinH, coinG,this.etageActuel);
                Mur murGE=new Mur(coinG, coinE,this.etageActuel);
                Pièce pièce3 = new Pièce(etageActuel);
                pièce3.setNom_pièce(nomPièce);
                pièce3.add(murEF);
                pièce3.add(murFH);
                pièce3.add(murHG);
                pièce3.add(murGE);
                System.out.println("Pièce.toString(): "+pièce3.toString());
                this.vue.redrawAll();
                this.vuePrix.reCalcule(this.vue.getModel());
                this.changeEtat(ETAT.CREA_PIECE_3PNT_p1);
                break;
            case AJOUT_OUVERTURE: 
                
                //tout pour cet état est désormais géré par "ajoutOuverture", qui lance la classe "EnterOuvertureDialog"
                //c'est un état fantôme...
                
//                //TODO : on doit pouvoir détecter un deuxième Mur proche.
//                //TODO ajouter nouvelleFenêtre au mur2 s'il y en a un
                this.vue.redrawAll();
                this.changeEtat(ETAT.SELECT);             
                break;

                /**
                 * Lorsque l'étage primitif a déjà été déssiné, nous n'irons jamais dans la partie 2 de création d'étage. 
                 * lorsque l'étage primitf a déjà été déssiné, nous rentrons seulement la hauteur de l'étage que nous voulons à présent construire. 
                 */
            case AJOUT_ETAGEp1:
                System.out.println("AJOUT d'ETAGEp1");
                if(etagePrimitif!=null){
                    System.out.println("Quel est la hauteur de l'étage que vous vouliez avoir ? supérieur à 2m");
                    this.vue.entrerHauteurEtage();
                    this.changeEtat(ETAT.SELECT);
                }
                else{
                    this.vue.entrerHauteurEtage(); // création du label et textField pour rentrer la hauteur de l'étage : lorsque le bouton valider est préssé, l'étage est créé
                    System.out.println("On crée actuellement le premier étage, afin di'initialiser le bâtiment.");
                    System.out.println("Veuillez cliquer sur les limites de l'ETAGE (2 cliques)");
                    this.pos[0]=t.getX();
                    this.pos[1]=t.getY();
                    System.out.println("coordonée du clic 1 : (x,y) => ("+this.pos[0]+","+this.pos[1]+")");
                    this.changeEtat(ETAT.AJOUT_ETAGEp2);
                }
                break;
            case AJOUT_ETAGEp2: //ATTENTION : ajout_Etagep2 n'est active que lors de la création dupremier étage. 
                //le reste du temps, AJOUT_ETAGEp1 suffit à créer un nouvel étage.
                System.out.println("AJOUT d'ETAGEp2");
                x2=t.getX();
                y2=t.getY();
              
                System.out.println("coordonée du clic 2 : (x,y) => ("+x2+","+y2+")");
                //TODO rentrer la valeur de l'étage
                System.out.println("quelle est la hauteur du premier étage du bâtiment?");
                double hauteurDuRDC = this.etageActuel.getHauteur_etage();//TODO rentrer la valeur de l'étage
                
                etagePrimitif=this.etageActuel;
                
                Coin premierClic = new Coin(this.pos[0], this.pos[1]);
                Coin premierClicVersSecond = new Coin(this.pos[0], y2);
                Coin secondClic = new Coin(x2, y2);
                Coin secondClicVersPremier = new Coin(x2, this.pos[1]);
                etagePrimitif.add(new Facade(premierClic,premierClicVersSecond));
                etagePrimitif.add(new Facade(premierClicVersSecond,secondClic));
                etagePrimitif.add(new Facade(secondClic,secondClicVersPremier));
                etagePrimitif.add(new Facade(secondClicVersPremier,premierClic));
                System.out.println("log : création des facades.");
                System.out.println(etagePrimitif.getListe_mur_facade());
                
                this.vue.redrawAll();
                this.changeEtat(ETAT.SELECT);
                break;

        }
        //TODO dans la video il fait model.add(new point) 
        //Je ne peux pas faire ça car je n'ai pas de méthode pour 
} 

    public void setNomPièce(String nomPièce) {
        this.nomPièce = nomPièce;
    }

    
    public void activeBoutonSuivantSelection() {

        System.out.println("Dans ACTIVE BOUTON SUIVANT");
        switch (objetSélectionné){
            case PIECE:
                //System.out.println("cas SELECTION DE PIECE");
                
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbcrpiece3().setDisable(true);
                this.vue.getRbcrpiece2().setDisable(true);
                
                this.vue.getRbouverture().setDisable(true);
            
                this.vue.getRbSelect().setDisable(false);
                this.vue.getRbrevêtement().setDisable(false);
                this.vue.getRbidappart().setDisable(false);
                this.vue.getRbsupp().setDisable(false);
                this.vue.getRbAnnule().setDisable(false);
                
                break;
            case MUR:
                
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbcrpiece3().setDisable(true);
                this.vue.getRbcrpiece2().setDisable(true);
                this.vue.getRbSelect().setDisable(false);
                this.vue.getRbrevêtement().setDisable(false);
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbsupp().setDisable(false);
                this.vue.getRbAnnule().setDisable(false);
                
                if(this.listeMurSelectionné.size()>1){
                    this.vue.getRbouverture().setDisable(true);
                }
                break;    
        }
    }
    void boutonSelect(ActionEvent t) {
        this.changeEtat(ETAT.SELECT);
    }

    void ajoutOuverture(ActionEvent t) {
        this.entrerOuvertureParDialog();
    }
  
    void ajoutGrpRevetement(ActionEvent t) {
        //System.out.println("Je me trouve dans la méthode AjoutGrpRevêtement");
        if (this.etat==ETAT.SELECT && this.listeMurSelectionné.size()>1){
            System.out.println("Quel revêtement veux tu pour tes murs ? ");
            this.vueRevetement.affichageMur();
            Revêtement revêtementChoisi=new Revêtement();
        }
    }
    void affichageRevêtement() {
            System.out.println("Le revêtement à selectionner dans le revêtementPane");
            switch (objetSélectionné){
                case MUR:
                    System.out.println("swich Case Mur de afichageRevêtement()");
                    this.vueRevetement.affichageMur();
                    break;
                case SOL:
                    System.out.println("swich Case Sol de afichageRevêtement()");
                    this.vueRevetement.affichageSol();
                    break;
                case PLAFOND:
                    System.out.println("swich Case Plafond de afichageRevêtement()");
                    this.vueRevetement.affichagePlafond();
                    break;      
            }       
        this.vuePrix.reCalcule(this.vue.getModel());
    }

    void boutonIdAppart(ActionEvent t) {
        if(this.etat==ETAT.SELECT && this.listePièceSelectionnée.size()>=1){
            Appartement appartement = new Appartement(etageActuel);
            System.out.println("taille de la liste de pièce selectionnée :"+this.listePièceSelectionnée.size());
        for (Pièce pièce : this.listePièceSelectionnée){
            appartement.add_pièce(pièce);
        }
            System.out.println("appartement.toString() : "+appartement.toString());
        }
    }
    void boutonSelect(MouseEvent t) { //pourquoi y en a t'il deux?
        System.out.println("BoutonSelectMOUSE_EVENT");
    }

    void boutonCrpiece2(ActionEvent t) {
        this.changeEtat(ETAT.CREA_PIECE_2PNT_p1);
    }

    void boutonCrpiece3(ActionEvent t) {
        this.changeEtat(ETAT.CREA_PIECE_3PNT_p1);
    }
    void boutonAjEtage(ActionEvent t) {
        this.changeEtat(ETAT.AJOUT_ETAGEp1);
    } 
    void annulerSelection(ActionEvent t) {
        if (this.etat==ETAT.SELECT){
            if(!this.getListePièceSelectionnée().isEmpty()){ // pareil que mettre this.getListePièceSélectionnée()>0
                this.getListePièceSelectionnée().clear();
            }
            else{
                
            }
            if(!this.getListeMurSélectionné().isEmpty()){
                this.getListeMurSélectionné().clear();
            }
            else{
                
            }
        }
        
        System.out.println("Pièce [] : "+this.listePièceSelectionnée.toString());
        System.out.println("Mur [] : "+this.listeMurSelectionné.toString());
        this.changeEtat(etat.SELECT);
    }
    
    public Coin coinProche(Coin clic){
        int distanceMaxClic = 15;
        for (Appartement appart : this.etageActuel.getListe_appartement()){
            for (Pièce pièce : appart.getListe_pièce()){
                for (Mur mur : pièce.getListe_mur()){
                    if (longueur(mur.getDebut(),clic)<10){
                        Coin VraiCoin = new Coin(mur.getDebut().getX(), mur.getDebut().getY());
                        return VraiCoin;
                    }
                    if (longueur(mur.getFin(),clic)<10){
                        Coin VraiCoin = new Coin(mur.getFin().getX(), mur.getFin().getY());
                        return VraiCoin;
                    } 
                }
            }
        }
        for (Pièce pièce : this.etageActuel.getListPièceOrpheline()){
            for (Mur mur : pièce.getListe_mur()){
                    if (longueur(mur.getDebut(),clic)<10){
                        Coin VraiCoin = new Coin(mur.getDebut().getX(), mur.getDebut().getY());
                        return VraiCoin;
                    }
                    if (longueur(mur.getFin(),clic)<10){
                        Coin VraiCoin = new Coin(mur.getFin().getX(), mur.getFin().getY());
                        return VraiCoin;
                    } 
                }
        }
        for(Facade facade : this.etageActuel.getListe_mur_facade()){
            if (longueur(facade.getDebut(),clic)<10){
                        Coin VraiCoin = new Coin(facade.getDebut().getX(), facade.getDebut().getY());
                        return VraiCoin;
                    }
                    if (longueur(facade.getFin(),clic)<10){
                        Coin VraiCoin = new Coin(facade.getFin().getX(), facade.getFin().getY());
                        return VraiCoin;
                    }
        }
        return null;
    }

    public static void recherchePièces(Contrôleur contrôleur, MouseEvent t) {
        Pièce pièceSelectionnée=new Pièce();
        Coin coinCliq=new Coin();
        coinCliq.setX(t.getX());
        coinCliq.setY(t.getY());
        for(Pièce pièce : contrôleur.etageActuel.getListPièceOrpheline()){
                    //System.out.println("resultat de la methode pieceSelect"+pièce.pieceSelect(coinCliq));
                    if(pièce.pieceSelect(coinCliq)==true){
                        System.out.println("Selection de pièce dans l'ETAT SELECT de la classe contrôleur methode recherche pièce");
                        pièceSelectionnée=pièce;
                        contrôleur.objetSélectionné=OBJET_SELECTIONNE.PIECE;
                    }
                }
                //la boucle ci-dessous et celle ci dessus pourrait-être remplacée par une seule boucle itérant sur IDManager.toutesLesPièces(etageActuel) que je viens de créer (T.B., 19/05/24)
                for(Appartement appartement : contrôleur.etageActuel.getListe_appartement()){
                    for(Pièce pièce:appartement.getListe_pièce()){
                        if(pièce.pieceSelect(coinCliq)){
                            pièceSelectionnée=pièce;
                            contrôleur.objetSélectionné=OBJET_SELECTIONNE.PIECE;
                        }

                    }
                }
                
                if(t.isControlDown()){
                            if(contrôleur.listePièceSelectionnée.contains(pièceSelectionnée)){
                                contrôleur.listePièceSelectionnée.remove(pièceSelectionnée);
                            }
                            else{
                                contrôleur.listePièceSelectionnée.add(pièceSelectionnée);
                            }
                 } 
                 else {
                            contrôleur.listePièceSelectionnée.clear();
                            contrôleur.listePièceSelectionnée.add(pièceSelectionnée);
                        }
    }


    
    public static void rechercheMurs(Contrôleur contrôleur,MouseEvent t) {
        contrôleur.pos[0]=t.getX();
        contrôleur.pos[1]=t.getY();
        double distanceMinimale=Double.POSITIVE_INFINITY;
        Coin coinCliq=new Coin();
        coinCliq.setX(contrôleur.pos[0]);
        coinCliq.setY(contrôleur.pos[1]);
        Mur murLePlusProche = new Mur();
        for(Pièce pièce:contrôleur.etageActuel.getListPièceOrpheline()){
                        for(Mur mur : pièce.getListe_mur()){
                            double distanceMurClique = mur.DistanceMurClique(coinCliq, DISTMAXCLIQUE);
                            if(distanceMurClique<distanceMinimale){
                                distanceMinimale=distanceMurClique;
                                //TODO enregistrer le mur corespondant à la distance minimale
                                murLePlusProche=mur;
                                System.out.println("mur le plus proche de ta première boucle : "+mur.toString());
                            }
                        }
                    }
                    for (Appartement appartement :contrôleur.etageActuel.getListe_appartement()){
                        for(Pièce pièceAppart:appartement.getListe_pièce()){
                            for(Mur mur1:pièceAppart.getListe_mur()){
                                double distanceMurClique = mur1.DistanceMurClique(coinCliq, DISTMAXCLIQUE);
                                if(distanceMurClique<distanceMinimale){
                                    distanceMinimale=distanceMurClique;
                                    murLePlusProche=mur1;
                                    System.out.println("mur le plus proche de ta deuxième boucle : "+mur1.toString());
                                }
                            }
                        }
                    }
                    
                    for(Facade mur : contrôleur.etageActuel.getListe_mur_facade()){
                        double distanceMurClique = mur.DistanceMurClique(coinCliq, DISTMAXCLIQUE);
                        if(distanceMurClique<distanceMinimale){
                            distanceMinimale=distanceMurClique;
                            murLePlusProche=mur; 
                            System.out.println("mur le plus proche de ta troisième boucle : "+mur.toString());
                        }
                    }
                    
                    System.out.println("LE MUR LE PLUS PROCHE : "+murLePlusProche.toString());
                    
                
               if(t.isControlDown()){
                   if(contrôleur.listeMurSelectionné.contains(murLePlusProche)){
                       contrôleur.listeMurSelectionné.remove(murLePlusProche);
                   }
                   else{
                       contrôleur.listeMurSelectionné.add(murLePlusProche);
                   }
               } 
               else {
                   contrôleur.listeMurSelectionné.clear();
                   contrôleur.listeMurSelectionné.add(murLePlusProche);
               }

    }//fin rechercheMurs
    
    
    void entrerOuvertureParDialog(){
        //mur2 (ici de valeur null) aurait dû être codé.
        System.out.println("EntrerOuvertureParDialog");
        Optional<Ouverture> p = EnterOuvertureDialog.nouvelleOuverture(this.dernierMurSélectionné,null);
        if (p.isPresent()) {
            this.dernierMurSélectionné.addOuverture(p.get());
//            this.vue.getModel().add(p.get());
            this.vue.redrawAll();
            this.changeEtat(ETAT.SELECT);

        }else{
            this.changeEtat(ETAT.SELECT);
            System.out.println("processus annulé");
        }
    }
    public void menuSave(ActionEvent t) {
        //issu du tutoVideoDessin.
        System.out.println("action \"menuSave\"");
        Architecture_officielle.sauvegardeParDéfaut();
    }
    /**
     * A finir
     * @param t ActionEvent
     */
    public void menuSaveAs(ActionEvent t) {
//        FileChooser chooser = new FileChooser();
//        File f = chooser.showSaveDialog(this.vue.getInStage());
//        if (f != null) {
//            this.realSave(f);
//        }
        System.out.println("action \"menuSaveAs\"");
    }
    
    /**
     * A finir
     * @param t ActionEvent
     */
    public void menuOpen(ActionEvent t) {
        
        //on a pas eu le temps de faire un FileChooser();
        
//        FileChooser chooser = new FileChooser();
//        File f = chooser.showOpenDialog(this.vue.getInStage());
//        if (f != null) {
//            try {
//                Figure lue = Figure.lecture(f);
//                Groupe glu = (Groupe) lue;
//                Stage nouveau = new Stage();
//                nouveau.setTitle(f.getName());
//                Scene sc = new Scene(new MainPane(nouveau, f, glu), 800, 600);
//                nouveau.setScene(sc);
//                nouveau.show();
//            } catch (Exception ex) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Erreur");
//                alert.setHeaderText("Problème durant la sauvegarde");
//                alert.setContentText(ex.getLocalizedMessage());
//
//                alert.showAndWait();
//            } finally {
//                this.changeEtat(20);
//            }
//        }
        System.out.println("action \"menuOpen\"");
        Architecture_officielle nouveauModèle = IDManager.récupérerLesDonnéesEnregistrées("saveFile.txt");
        this.etagePrimitif = IDManager.getObjetEtage(0);
        nouveauModèle.setEtageActuel(this.etagePrimitif);
        this.etageActuel = etagePrimitif;
        try {
            
            Scene scene;
            Stage stage = new Stage();
    //        Architecture_officielle batiment = new Architecture_officielle();
            GridPane gridPane = new GridPane(); // Ce GridPane composera la fenêtre principale où je disposerai mes autres fenêtres
            MainPane mainPane; // Ce MainPane() sera composé de ma zone de dessin (Canvas) et mes outils

            //Mise en place et initialiser les tailles des colonnes et lignes
            gridPane.getColumnConstraints().add(new ColumnConstraints(650)); // column 0 is 650 wide
            gridPane.getColumnConstraints().add(new ColumnConstraints (150));// colonne à une largeur de 150
            gridPane.getRowConstraints().add(new RowConstraints(25));
            gridPane.getRowConstraints().add(new RowConstraints(250));       // ligne à une hauteur de 250
            gridPane.getRowConstraints().add(new RowConstraints(250));       // ligne à une hauteur de 250
            gridPane.getRowConstraints().add(new RowConstraints(100));       // ligne à une hauteur de 100
            gridPane.getRowConstraints().add(new RowConstraints(25));
    //        mainPane=new MainPane(batiment);
                    mainPane=new MainPane(nouveauModèle);
                    this.changeEtat(ETAT.SELECT);
                    this.vue.getRbSelect().setSelected(true);
            

    //Mettre mes Panes dans les différentes cases de mon grid        
            gridPane.add(mainPane.getMenu(), 0, 0,2,1); ////je mets la menuBar à la colonne 0, ligne 0, j'étale mon Pane sur 2 colonne et 1 ligne
            gridPane.add(mainPane, 0, 1,1, 3); // je mets la fenêtre mainPane à la colonne 1, ligne 1, j'étale mon Pane sur 1 colonne et 3 lignes
            gridPane.add(mainPane.getRevêtementPane(),1,1,1, 1);//je mets la fenêtre revêtmentPane à la colonne 1, ligne 1, j'étale mon Pane sur 1 colonne et 1 lignes
            gridPane.add(mainPane.getPrixPane(), 1, 2, 1, 2);//je mets la fenêtre prixPane à la colonne 1, ligne 2, j'étale mon Pane sur 1 colonne et 2 lignes
            gridPane.add(mainPane.getTfMessage(),0,4,2,1);//je mets tfMessage à la colonne 0, ligne 4, j'étale mon Pane sur 2 colonne et 1 ligne
            scene = new Scene(gridPane,800,650);

            scene.widthProperty().addListener((o) -> {
                gridPane.getColumnConstraints().add(0,new ColumnConstraints(0.75*gridPane.getWidth())); // column 0 is 650 wide
                gridPane.getColumnConstraints().add(1,new ColumnConstraints (0.25*gridPane.getWidth()));// colonne à une largeur de 150
            });
            scene.heightProperty().addListener((o) -> { 
                gridPane.getRowConstraints().set(0, new RowConstraints(25));      // ligne à une hauteur de 250            
                gridPane.getRowConstraints().set(1, new RowConstraints(0.40*gridPane.getHeight()-25));      // ligne à une hauteur de 250
                gridPane.getRowConstraints().add(2,new RowConstraints(0.40*gridPane.getHeight()));       // ligne à une hauteur de 250
                gridPane.getRowConstraints().add(3,new RowConstraints(0.20*gridPane.getHeight()-25));
                gridPane.getRowConstraints().set(4, new RowConstraints(25));      // ligne à une hauteur de 250

            });

            
            
            stage.setScene(scene);
            stage.setTitle("LE MEILLEUR LOGICIEL D'ARCHITECTURE ! (made by Mathieu, Thomas, Oscar )");
            stage.show(); // Affichage de la fenêtre
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Problème durant la sauvegarde");
            alert.setContentText(ex.getLocalizedMessage());
            alert.showAndWait();
        } 
        System.out.println("l'ouverture est réussie.");
    }
    
    /**
     * A finir
     * @param t ActionEvent
     */
    public void menuNouveau(ActionEvent t) {
        //issu du tutoVideoDessin.
//        Stage nouveau = new Stage();
//        nouveau.setTitle("Nouveau");
//        Scene sc = new Scene(new MainPane(nouveau), 800, 600);
//        nouveau.setScene(sc);
//        nouveau.show();
        System.out.println("action \"menuNouveau\"");
    }
    public void menuApropos(ActionEvent t) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("A propos");
        alert.setHeaderText(null);
        alert.setContentText("Trop super ce micro-logiciel de dessin vectoriel 2D\n"
                + "réalisé par MC,OB, et TB\n"
                + "comme projet pour le semestre2\n"
                + "à l'INSA de Strasbourg");

        alert.showAndWait();
    }
    

    
 
    
    //GET

    public ArrayList<Mur> getListeMurSélectionné(){
        return this.listeMurSelectionné;
    }
    public MainPane getVue() {
        return vue;
    }
    public RevêtementPane getVueRevetement() {
        return vueRevetement;
    }
    public Etage getEtageActuel() {
        return etageActuel;
    }

    public ArrayList<Etage> getListeEtage() {
        return listeEtage;
    }

    public ArrayList<Pièce> getListePièceSelectionnée() {
        return listePièceSelectionnée;
    }

    public ETAT getEtat(String nomEtat) {
        return ETAT.valueOf(nomEtat);
    }
    
    //SET
    public void setEtageActuel(Etage etageActuel) {
        this.etageActuel = etageActuel;
    }
    public Double getHauteurEtage() {
        return hauteurEtage;
    }

    public void setHauteurEtage(Double hauteurEtage) {
        this.hauteurEtage = hauteurEtage;
    }


    public void setObjetSélectionné(OBJET_SELECTIONNE objetSélectionné) {
        this.objetSélectionné = objetSélectionné;
    }

    public void setEtat(String nomEtat){
        this.etat = ETAT.valueOf(nomEtat);
    }
    
    
    
}
