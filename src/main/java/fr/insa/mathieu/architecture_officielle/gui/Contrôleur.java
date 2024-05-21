/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle.gui;

import fr.insa.mathieu.architecture_officielle.Appartement;
import fr.insa.mathieu.architecture_officielle.Architecture_officielle;
import fr.insa.mathieu.architecture_officielle.Coin;
import fr.insa.mathieu.architecture_officielle.Etage;
import fr.insa.mathieu.architecture_officielle.Facade;
import fr.insa.mathieu.architecture_officielle.Fenêtre;
import fr.insa.mathieu.architecture_officielle.IDManager;
import fr.insa.mathieu.architecture_officielle.Lire;
import fr.insa.mathieu.architecture_officielle.Mur;
import fr.insa.mathieu.architecture_officielle.Pièce;
import fr.insa.mathieu.architecture_officielle.Revêtement;
import java.io.File;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
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
    private ETAT etat;
    private OBJET_SELECTIONNE objetSélectionné;
    private MainPane vue;    
    private RevêtementPane vueRevetement;
    private PrixPane vuePrix;
    //private ArrayList<Double> pos=new ArrayList<>();
    private double[] pos = new double[100];
    private ArrayList<Etage> listeEtage;
    private Etage etagePrimitif;
    private Architecture_officielle batiment;
    private ArrayList<Mur> listeMurSelectionné;
    private ArrayList<Pièce> listePièceSelectionnée;
    public static double DISTMAXCLIQUE=20;
    private Etage etageActuel;
    private double hauteurEtage;
    enum ETAT{
        SELECT,
        SELECT_SURBRILLANCE,
        AJOUT_ETAGEp1,
        AJOUT_ETAGEp2,
        CREA_MURp1,
        CREA_MURp2,
        CREA_PIECE_2PNT_p1,
        CREA_PIECE_2PNT_p2,
        CREA_PIECE_3PNT_p1,
        CREA_PIECE_3PNT_p2,
        CREA_PIECE_3PNT_p3,
        AJOUT_FEN_p1,
        AJOUT_FEN_p2,
        AJOUT_PORTE_p1,
        AJOUT_PORTE_p2,
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
        this.vuePrix=vuePrix;
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
                this.vue.getRbidpiece().setDisable(false);
                this.vue.getRbfenêtre().setDisable(false);
                this.vue.getRbporte().setDisable(false);
                this.vue.getRbEtageAj().setDisable(false);
                this.vue.getRbrevêtement().setDisable(false);
                this.vue.getRbcrpiece2().setDisable(false);
                this.vue.getRbcrpiece3().setDisable(false);
                this.vue.getRbrevêtement_rap().setDisable(false);
                this.vue.getRbsupp().setDisable(false);
                this.vue.getRbcrmur().setDisable(false);
                break;
            case SELECT_SURBRILLANCE:
                this.vue.getRbidappart().setDisable(false);
                this.vue.getRbidpiece().setDisable(false);
                this.vue.getRbfenêtre().setDisable(false);
                this.vue.getRbporte().setDisable(false);
                this.vue.getRbEtageAj().setDisable(false);
                this.vue.getRbrevêtement().setDisable(false);
                this.vue.getRbcrpiece2().setDisable(false);
                this.vue.getRbcrpiece3().setDisable(false);
                this.vue.getRbrevêtement_rap().setDisable(false);
                this.vue.getRbsupp().setDisable(false);
                this.vue.getRbcrmur().setDisable(false);
                break;
            case CREA_MURp1:
                this.vue.changeMessage("clic pour définir le début du mur");
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                this.vue.getRbrevêtement_rap().setDisable(true);
                break;
            case CREA_MURp2:
                this.vue.changeMessage("clic pour définir la fin du mur");
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                this.vue.getRbrevêtement_rap().setDisable(true);
                break;
            case CREA_PIECE_2PNT_p1:
                this.vue.changeMessage("clic pour définir le début de la pièce");
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                this.vue.getRbrevêtement_rap().setDisable(true);
                break;
            case CREA_PIECE_2PNT_p2:
                this.vue.changeMessage("clic pour définir l'autre extrémité de la diagonale de la pièce");
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                this.vue.getRbrevêtement_rap().setDisable(true);
                break;
            case CREA_PIECE_3PNT_p1:
                this.vue.changeMessage("clic pour définir le premier point de la pièce");
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                this.vue.getRbrevêtement_rap().setDisable(true);
                break;
            case CREA_PIECE_3PNT_p2:
                this.vue.changeMessage("clic pour définir le deuxième point de la pièce (c'est une diagonale)");
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                this.vue.getRbrevêtement_rap().setDisable(true);
                break;
            case CREA_PIECE_3PNT_p3:
                this.vue.changeMessage("clic pour définir le troisième point");
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                this.vue.getRbrevêtement_rap().setDisable(true);
                break;
            case AJOUT_FEN_p1:
                
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                this.vue.getRbrevêtement_rap().setDisable(true);
//this.vue.getRbfenêtre().setDisable(true);
                break;
            case AJOUT_FEN_p2:
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                this.vue.getRbrevêtement_rap().setDisable(true);
                //this.vue.getRbfenêtre().setDisable(true);
                break;
            case AJOUT_PORTE_p1:
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                this.vue.getRbrevêtement_rap().setDisable(true);
                //this.vue.getRbporte().setDisable(true);
                break;
            case AJOUT_PORTE_p2:
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                //this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                this.vue.getRbrevêtement_rap().setDisable(true);
                break;
            case AJOUT_REVETEMENT_p1:
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                //this.vue.getRbrevêtement().setDisable(true);
                this.vue.getRbrevêtement_rap().setDisable(false);
                break;
            case AJOUT_REVETEMENT_p2:
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                //this.vue.getRbrevêtement().setDisable(true);
                this.vue.getRbrevêtement_rap().setDisable(false);
                break;
            case AJOUT_REVETEMENT_p3:
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                //this.vue.getRbrevêtement().setDisable(true);
                this.vue.getRbrevêtement_rap().setDisable(false);
                break;
            case AJOUT_ETAGEp1:
                
                if (etagePrimitif==null){
                    this.vue.changeMessage("cliquez pour définir un coin du contour du bâtiment, PUIS ENTREZ LA HAUTEUR DE L'ETAGE");
                    this.vue.getRbSelect().setDisable(true);
                    //lors de la création du premier étage, on ne peut pas encore select.
                }else{
                    this.vue.changeMessage("Merci cliquer n'importe où, puis d'entrer la hauteur du nouvel étage.");
                }
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(false);
                this.vue.getRbcrmur().setDisable(true);
                this.vue.getRbcrpiece2().setDisable(true);
                this.vue.getRbcrpiece3().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                this.vue.getRbrevêtement_rap().setDisable(true);
                
                break;
            case AJOUT_ETAGEp2: //ATTENTION : AJOUT_ETAGEp2 n'est actif que au tout début, à la création du premier étage.
                this.vue.changeMessage("merci de cliquer pour entrer l'autre extrémité de la diagonale de l'étage (qui est en forme de rectangle)");
                this.vue.getRbSelect().setDisable(true);
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbcrmur().setDisable(true);
                this.vue.getRbcrpiece2().setDisable(true);
                this.vue.getRbcrpiece3().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                this.vue.getRbrevêtement_rap().setDisable(true);
                break;               
        }
        this.etat=nouvelEtat;
    }

    void clicDansZoneDessin(MouseEvent t) {
        if (etat!= ETAT.SELECT && etat!= ETAT.SELECT_SURBRILLANCE){ //je ne sais pas à quoi SELECT_SURBRILLANCE sert, je le mets au cas où.
            //le highlight de la sélection fonctionne sur les deux listes ci-dessous. 
            //tant que ces deux listes sont non-vides, ce qu'elles contiennent sont en mode Highlight.
            this.listeMurSelectionné.clear();
            this.listePièceSelectionnée.clear();
            this.vue.redrawAll(); //cela permet d'enlever le mode Highlight aux objets précedemment sélectionnés.
        }
        switch (etat){
            case SELECT:
                System.out.println("Je suis en etat SELECT");
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
                
                
                    //for(Pièce pièce : IDManager.toutesLesPièces(this.etageActuel)){
                    //la fonction ci dessus pourrait remplacer remplacer la deuxième boucle 
                for(Pièce pièce : etageActuel.getListPièceOrpheline()){
                    //System.out.println("resultat de la methode pieceSelect"+pièce.pieceSelect(coinCliq));
                    if(pièce.pieceSelect(coinCliq)==true){
                        //System.out.println("Selection de pièce dans l'ETAT SELECT de la classe contrôleur ligne 275");
                        pièceSelectionnée=pièce;
                        this.objetSélectionné=OBJET_SELECTIONNE.PIECE;
                    }
                }
                //la boucle ci-dessous et celle ci dessus pourrait-être remplacée par une seule boucle itérant sur IDManager.toutesLesPièces(etageActuel) que je viens de créer (T.B., 19/05/24)
                for(Appartement appartement : etageActuel.getListe_appartement()){
                    for(Pièce pièce:appartement.getListe_pièce()){
                        if(pièce.pieceSelect(coinCliq)){
                            pièceSelectionnée=pièce;
                            this.objetSélectionné=OBJET_SELECTIONNE.PIECE;
                        }

                    }
                }
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
                if (objetSélectionné != OBJET_SELECTIONNE.PIECE){
                    //Si on a sélectionné une pièce, notre clic ne sélectionne pas un mur en même temps.
                    for (Etage etage:this.listeEtage){
                        for(Pièce pièce:etage.getListPièceOrpheline()){
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
                        for (Appartement appartement : etage.getListe_appartement()){
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
                        for(Facade facade : etage.getListe_mur_facade()){
                            if(facade.distanceFacadeClique(coinCliq, DISTMAXCLIQUE)<distanceMinimale){
                                distanceMinimale=facade.distanceFacadeClique(coinCliq, DISTMAXCLIQUE);
                                murLePlusProche=facade; 
                                this.objetSélectionné=OBJET_SELECTIONNE.FACADE;
                                System.out.println("mur le plus proche de ta troisième boucle : "+facade.toString());
                            }
                        }
                        System.out.println("mur le plus proche retenu  : "+murLePlusProche.toString());
                    }
                }//fin de la condition (objSel != OBJ_SEL.PIECE) 
                
                if (objetSélectionné==OBJET_SELECTIONNE.MUR || objetSélectionné==OBJET_SELECTIONNE.FACADE){
                    //" si l'objet sélectionné est un mur ou une facade, l'ajouter à listeMurSelectionné
                    if (!this.listePièceSelectionnée.isEmpty()){
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
                System.out.println("Liste de mur SELECTIONNE : "+listeMurSelectionné.size());
                for(int i =0;i<listeMurSelectionné.size();i++){
                    System.out.println(listeMurSelectionné.get(i).toString());

                }
                
                //this.vue.highlight(murLePlusProche);//TODO ne marche pas
                this.activeBoutonSuivantSelection();
                objetSélectionné = OBJET_SELECTIONNE.RIEN;//réinitialisatioon du type d'objet sélectionné.
                this.vue.redrawAll(); //permet de faire le highlight de la sélection
                this.changeEtat(ETAT.SELECT); //il faut le remettre sinon les boutons normalement actifs sont désactivés.
                break;

            case CREA_MURp1:
                System.out.println("ETAT CREA_MURp1 de création de mur");
                //this.pos.add(0, t.getX());
                //this.pos.add(1, t.getY());
                this.pos[0]=t.getX();
                this.pos[1]=t.getY();
                //System.out.println("coordonée du clic : (x,y) => ("+this.pos.get(0)+","+this.pos.get(1)+")");
                System.out.println("coordonée du clic 1 : (x,y) => ("+this.pos[0]+","+this.pos[1]+")");
                this.changeEtat(ETAT.CREA_MURp2);
                break;
            case CREA_MURp2:
                System.out.println("ETAT CREA_MURp2 de création de mur");
                double x2=t.getX();
                double y2=t.getY();
                System.out.println("coordonnée du clic 2 : (x,y) => ("+x2+","+y2+")");
                //Mur mur = new Mur(new Coin(this.pos.get(0), this.pos.get(1)), new Coin(x2, y2));
                Etage étageDuMur = this.vue.getModel().getEtageActuel();
                Mur mur = new Mur(new Coin(this.pos[0], this.pos[1]), new Coin(x2, y2), étageDuMur);
                //le constructeur Mur(coin,coin,etage) ajoute automatiquement this à etage.getListMurOrphelins.
                System.out.println("mur.toString() :"+mur.toString());
                System.out.println("ce mur appartient-il aux mur orphelins ? (boolean) " + étageDuMur.getListMurOrphelin().contains(mur));
                this.vue.redrawAll();
                this.changeEtat(ETAT.CREA_MURp1);
                break;
            case CREA_PIECE_2PNT_p1:
                System.out.println("ETAT CREA_PIECE_2PNT_p1 de création de pièce 2 points");
                //this.pos.add(0, t.getX());
                //this.pos.add(1, t.getY());
                this.pos[0]=t.getX();
                this.pos[1]=t.getY();
                System.out.println("coordonée du clic 1 : (x,y) => ("+this.pos[0]+","+this.pos[1]+")");
                this.changeEtat(ETAT.CREA_PIECE_2PNT_p2);
                break;
            case CREA_PIECE_2PNT_p2:
                System.out.println("ETAT CREA_PIECE_2PNT_p1 de création de pièce 2 points");
                System.out.println("EtageActuel : "+this.etageActuel);
                x2=t.getX();
                y2=t.getY();
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
                this.vuePrix.reCalcule(batiment);
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
                System.out.println("coordonée du clic 1 : (x,y) => ("+this.pos[0]+","+this.pos[1]+")");
                this.changeEtat(ETAT.CREA_PIECE_3PNT_p2);
                break;
            case CREA_PIECE_3PNT_p2:   
                System.out.println("ETAT CREA_PIECE_3PNT_p2 de création de pièce 2 points");
                //CLIC 2
                this.pos[2]=t.getY();
                System.out.println("coordonée du clic 2 : (x,y) => (rien enregistré,"+this.pos[2]+")");
                this.changeEtat(ETAT.CREA_PIECE_3PNT_p3);
                break;
            case CREA_PIECE_3PNT_p3:
                System.out.println("ETAT CREA_PIECE_3PNT_p3 de création de pièce 3 points");
                this.pos[3]=t.getX();
                System.out.println("coordonée du clic 3 : (x,y) => ("+this.pos[4]+",rien enregistré)");
                Coin coinE= new Coin(this.pos[0], this.pos[1]);
                Coin coinF= new Coin(this.pos[0], this.pos[2]);
                Coin coinG= new Coin(this.pos[3],this.pos[1]);
                Coin coinH= new Coin(this.pos[3], this.pos[2]);
                Mur murEF=new Mur(coinE, coinF,this.etageActuel);
                Mur murFH=new Mur(coinF, coinH,this.etageActuel);
                Mur murHG=new Mur(coinH, coinG,this.etageActuel);
                Mur murGE=new Mur(coinG, coinE,this.etageActuel);
                Pièce pièce3 = new Pièce(etageActuel);
                pièce3.add(murEF);
                pièce3.add(murFH);
                pièce3.add(murHG);
                pièce3.add(murGE);
                System.out.println("Pièce.toString(): "+pièce3.toString());
                this.vue.redrawAll();
                this.vuePrix.reCalcule(batiment);
                this.changeEtat(ETAT.CREA_PIECE_3PNT_p1);
                break;
            case AJOUT_FEN_p1: //création de fenêtre, p1

                System.out.println("ETAT AJOUT_FENp1 de créationd e fenêtre ");
                this.pos[0]=t.getX();
                this.pos[1]=t.getY();
                Coin positionClic = new Coin();
                positionClic.setX(pos[0]);
                positionClic.setY(pos[1]);
                
                //TODO Détection du mur 
                this.vue.redrawAll();
                this.changeEtat(ETAT.AJOUT_FEN_p2);             
                break;
            case AJOUT_FEN_p2://création de fenêtre, p2
                System.out.println("ETAT AJOUT_FENp2 de créationd e fenêtre ");
                double posx1 = t.getX();
                double posy1 = t.getY();
                Mur nouveauMur1 = new Mur(new Coin(23,23),new Coin(24,24));

                //TODO détection de l'orientation
                char orientation1 = 'E';
                //Fenêtre fenêtre1 = new Fenêtre(this[0],this.pos[1],orientation1,nouveauMur1);
                this.changeEtat(ETAT.AJOUT_FEN_p1);
                break;
            case AJOUT_PORTE_p1:
                System.out.println("ETAT AJOUT_PORTEp1 de création de fenêtre ");
                this.changeEtat(ETAT.AJOUT_PORTE_p2);
                break;
            case AJOUT_PORTE_p2:
                System.out.println("ETAT AJOUT_PORTEp2 de création de fenêtre ");
                this.changeEtat(ETAT.AJOUT_PORTE_p1);
                break;
                
            case AJOUT_REVETEMENT_p1:
                System.out.println("ETAT AJOUT_REVETEMENT_p1 d'ajout de revêtement ");
                this.changeEtat(ETAT.AJOUT_REVETEMENT_p2);
                break;

            case AJOUT_REVETEMENT_p2:
                System.out.println("ETAT AJOUT_REVETEMENT_p2 d'ajout de revêtement ");
                this.changeEtat(ETAT.AJOUT_REVETEMENT_p1);
                break;
            case AJOUT_REVETEMENT_p3:
                System.out.println("ETAT AJOUT_REVETEMENT_p3 d'ajout de revêtement ");
                this.changeEtat(ETAT.AJOUT_REVETEMENT_p1);
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
                    //double hauteur=Lire.d();
                    Etage etageCréé = new Etage(this.hauteurEtage,batiment);
                    for (int i=0;i<this.listeEtage.get(0).getListe_mur_facade().size();i++){
                        etageCréé.add(this.listeEtage.get(0).getListe_mur_facade().get(i));
                    }
                    this.listeEtage.add(etageCréé);
                    //TODO Ajout de bouton étage, dessiner les limites de l'étagee primitif
                    this.vue.ajoutBtEtage(this.listeEtage.size());
                    this.etageActuel=etageCréé;
                    this.vue.redrawAll();
                    this.changeEtat(ETAT.SELECT);
                }
                else{
                    this.vue.entrerHauteurEtage();
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
                batiment=new Architecture_officielle();
                System.out.println("coordonée du clic 2 : (x,y) => ("+x2+","+y2+")");
                //TODO rentrer la valeur de l'étage
                System.out.println("quelle est la hauteur du premier étage du bâtiment?");
                double hauteurDuRDC = this.hauteurEtage;//TODO rentrer la valeur de l'étage
                etagePrimitif=new Etage(hauteurDuRDC, batiment);
                
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

                this.listeEtage.add(etagePrimitif);
                //System.out.println("Etage.toString : "+etagePrimitif.toString());
                this.vue.getModel().getListe_etage().add(etagePrimitif);
                this.etageActuel=etagePrimitif;
                this.vue.getModel().setEtageActuel(etageActuel);
                this.vue.ajoutBtEtage(this.listeEtage.size());
                //Creation de bouton etage avec le label "etage primitif"
                this.vue.redrawAll();
                this.changeEtat(ETAT.SELECT);
                break;

        }
        //TODO dans la video il fait model.add(new point) 
        //Je ne peux pas faire ça car je n'ai pas de méthode pour 
} 

    
    public void activeBoutonSuivantSelection() {
        this.vue.getRbcrmur().setDisable(true);
        this.vue.getRbEtageAj().setDisable(true);
        this.vue.getRbcrpiece3().setDisable(true);
        this.vue.getRbcrpiece2().setDisable(true);
        this.vue.getRbsupp().setDisable(false);
        System.out.println("taille de la liste de mur selectionné :"+this.listeMurSelectionné.size());
        if (this.listeMurSelectionné.size()>1 || this.listePièceSelectionnée.size()>=1){
            this.vue.getRbporte().setDisable(true);
            this.vue.getRbfenêtre().setDisable(true);
            this.vue.getRbrevêtement().setDisable(false);
            this.vue.getRbidpiece().setDisable(false);
            this.vue.getRbidappart().setDisable(false);
            
        }
        
    }
    void boutonSelect(ActionEvent t) {
        this.changeEtat(ETAT.SELECT);
    }
    void ajoutGrpRevetement(ActionEvent t) {
        //System.out.println("Je me trouve dans la méthode AjoutGrpRevêtement");
        if (this.etat==ETAT.SELECT && this.listeMurSelectionné.size()>1){
            System.out.println("Quel revêtement veux tu pour tes murs ? ");
            this.vueRevetement.affichageMur();
            Revêtement revêtementChoisi=new Revêtement();
            while(this.vueRevetement.getRevêtementCliqué()==null){
                System.out.println("Je suis dans la boucle");
            }
            revêtementChoisi=this.vueRevetement.getRevêtementCliqué();
            System.out.println("revêtementChoisi : "+revêtementChoisi.toString());
            this.listeMurSelectionné.get(0).add(revêtementChoisi);
            System.out.println("Mur.toString() : "+this.listeMurSelectionné.get(0).toString());
            if(this.listeMurSelectionné.size()>1){
                for(Mur murSelectionné : this.listeMurSelectionné){
                    murSelectionné.add(revêtementChoisi);//soit on fait un "mur.add(revêtement) ou un mur.set(revêtement) avec avec le mur avec déjà un revêtement standard
                    System.out.println("murSelectionné.toString() : "+murSelectionné.toString());
                }
            }
        }
        if (this.etat==ETAT.SELECT && !this.listePièceSelectionnée.isEmpty()){
            /**
             * Affichage de deux boutons : un pour le sol, un pour le plafond pour l'ajout de revêtement. 
             */
            this.vue.ajoutBtSOL_PLAFOND();
        }
        this.vuePrix.reCalcule(batiment);
    }

    void boutonIdAppart(ActionEvent t) {
        
        if(this.etat==ETAT.SELECT && this.listePièceSelectionnée.size()>=1){
            Appartement appartement=new Appartement(etageActuel);
            System.out.println("taille de la liste de pièce selectionnée :"+this.listePièceSelectionnée.size());
        for (Pièce pièce : this.listePièceSelectionnée){
            appartement.add_pièce(pièce);
        }
            System.out.println("appartement.toString() : "+appartement.toString());
        }
    }
    void annulerSelection(ActionEvent t) {
        if (this.etat==ETAT.SELECT){
            if(!this.getListePièceSelectionnée().isEmpty()){ // pareil que mettre this.getListePièceSelectionnée()>0
                this.getListePièceSelectionnée().clear();
            }
            else{
                
            }
            if(!this.getListeMurSelectionné().isEmpty()){
                this.getListeMurSelectionné().clear();
            }
            else{
                
            }
        }
        System.out.println("ETAT dans fonction ANNULER : ");
        System.out.println("Pièce [] : "+this.listePièceSelectionnée.toString());
        System.out.println("Mur [] : "+this.listeMurSelectionné.toString());
        this.changeEtat(etat.SELECT);
    }

    public static void recherchePièces(Contrôleur contrôleur,MouseEvent t) {
        Pièce pièceSelectionnée=new Pièce();
        Coin coinCliq=new Coin();
        coinCliq.setX(t.getX());
        coinCliq.setY(t.getY());
        for(Pièce pièce : contrôleur.getEtageActuel().getListPièceOrpheline()){
                    //System.out.println("resultat de la methode pieceSelect"+pièce.pieceSelect(coinCliq));
                    if(pièce.pieceSelect(coinCliq)==true){
                        //System.out.println("Selection de pièce dans l'ETAT SELECT de la classe contrôleur ligne 275");
                        pièceSelectionnée=pièce;
                    }
                    
                }
                for(Appartement appartement : contrôleur.getEtageActuel().getListe_appartement()){
                    for(Pièce pièce:appartement.getListe_pièce()){
                        if(pièce.pieceSelect(coinCliq)){
                            pièceSelectionnée=pièce;
                        }
                    }
                }
                
                if(t.isControlDown()){
                            if(contrôleur.getListePièceSelectionnée().contains(pièceSelectionnée)){
                                contrôleur.getListePièceSelectionnée().remove(pièceSelectionnée);
                            }
                            else{
                                contrôleur.getListePièceSelectionnée().add(pièceSelectionnée);
                            }
                 } 
                 else {
                            contrôleur.getListePièceSelectionnée().clear();
                            contrôleur.getListePièceSelectionnée().add(pièceSelectionnée);
                        }
    }

    public ArrayList<Mur> getListeMurSelectionné() {
        return listeMurSelectionné;
    }

    public ArrayList<Pièce> getListePièceSelectionnée() {
        return listePièceSelectionnée;
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
                    
                    for(Mur mur : contrôleur.etageActuel.getListe_mur_facade()){
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
    
    public void menuSave(ActionEvent t) {
        //issu du tutoVideoDessin.
        System.out.println("action \"menuSave\"");
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
    
    void ajoutPorte(ActionEvent t){
        this.changeEtat(ETAT.AJOUT_PORTE_p1);
    }
    void ajoutFenetre(ActionEvent t) {
        this.changeEtat(ETAT.AJOUT_FEN_p1);
        
    }

    void boutonSelect(MouseEvent t) {
        System.out.println("BoutonSelectMOUSE_EVENT");
        this.changeEtat(ETAT.SELECT_SURBRILLANCE);
    }

    void boutonCrmur(ActionEvent t) {
        this.changeEtat(ETAT.CREA_MURp1);
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
 
    
    //GET

    public ArrayList<Mur> getListeMurSélectionné(){
        return this.listeMurSelectionné;
    }
    public ArrayList<Pièce> getListePièceSélectionnée(){
        return this.listePièceSelectionnée;
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
    
}
