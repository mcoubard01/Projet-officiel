/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle.gui;

import fr.insa.mathieu.architecture_officielle.Appartement;
import fr.insa.mathieu.architecture_officielle.Architecture_officielle;
import fr.insa.mathieu.architecture_officielle.Coin;
import fr.insa.mathieu.architecture_officielle.Etage;
import fr.insa.mathieu.architecture_officielle.Fenêtre;
import fr.insa.mathieu.architecture_officielle.Lire;
import fr.insa.mathieu.architecture_officielle.Mur;
import fr.insa.mathieu.architecture_officielle.Pièce;
import fr.insa.mathieu.architecture_officielle.Revêtement;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *  VIDEO à reprendre (la 7 et 8)
 * 
 * TODO : ptLineDist(double px, double py) pour avoir la distance de ton clic (en paramètre sa position X et Y) à une ligne (le mur)<mur>.ptLineDist(t.getX(),t.getY());
 * @author stard
 */
public class Contrôleur {
    private ETAT etat;
    private MainPane vue;    
    private RevêtementPane vueRevetement;

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
    //private ArrayList<Double> pos=new ArrayList<>();
    private double[] pos = new double[100];
    private ArrayList<Etage> listeEtage;
    private Etage etagePrimitif;
    private Architecture_officielle batiment;
    private ArrayList<Mur> listeMurSelectionné;
    public double DISTMAXCLIQUE=20;
    
    
    public Contrôleur(MainPane vue){
        this.vue=vue;
        this.listeMurSelectionné=new ArrayList<>();
        this.listeEtage=new ArrayList<>();
    }
    public Contrôleur(MainPane vue,RevêtementPane vueRevetement){
        this.vue=vue;
        this.vueRevetement=vueRevetement;
        this.listeEtage=new ArrayList<>();
        this.listeMurSelectionné=new ArrayList<>();
    }
    public void changeEtat(ETAT nouvelEtat){//int nouvelEtat => Etat nouvelEtat
        switch(nouvelEtat){
            case SELECT://case :Etat.Select:
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
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                this.vue.getRbrevêtement_rap().setDisable(true);
                break;
            case CREA_MURp2:
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                this.vue.getRbrevêtement_rap().setDisable(true);
                break;
            case CREA_PIECE_2PNT_p1:
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                this.vue.getRbrevêtement_rap().setDisable(true);
                break;
            case CREA_PIECE_2PNT_p2:
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                this.vue.getRbrevêtement_rap().setDisable(true);
                break;
            case CREA_PIECE_3PNT_p1:
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                this.vue.getRbrevêtement_rap().setDisable(true);
                break;
            case CREA_PIECE_3PNT_p2:
                this.vue.getRbidappart().setDisable(true);
                this.vue.getRbidpiece().setDisable(true);
                this.vue.getRbfenêtre().setDisable(true);
                this.vue.getRbporte().setDisable(true);
                this.vue.getRbEtageAj().setDisable(true);
                this.vue.getRbrevêtement().setDisable(true);
                this.vue.getRbrevêtement_rap().setDisable(true);
                break;
            case CREA_PIECE_3PNT_p3:
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
            case AJOUT_ETAGEp2:
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
        
        switch (etat){
            case SELECT:
                System.out.println("Je suis en etat SELECT");
                this.pos[0]=t.getX();
                this.pos[1]=t.getY();
                double distanceMinimale=Double.POSITIVE_INFINITY;
                Coin coinCliq=new Coin(this.pos[0],this.pos[1]);
                Mur murLePlusProche = new Mur();
                for (Etage etage:this.listeEtage){
                    for(Pièce pièce:etage.getListPièceOrpheline()){
                        for(Mur mur : pièce.getListe_mur()){
                            if(mur.DistanceMurClique(coinCliq, DISTMAXCLIQUE)<distanceMinimale){
                                distanceMinimale=mur.DistanceMurClique(coinCliq, DISTMAXCLIQUE);
                                //TODO enregistrer le mur corespondant à la distance minimale
                                murLePlusProche=mur;
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
                                    System.out.println("mur le plus proche de ta deuxième boucle : "+mur1.toString());
                                }
                            }
                        }
                    }
                    for(Mur mur2 : etage.getListe_mur()){
                        if(mur2.DistanceMurClique(coinCliq, DISTMAXCLIQUE)<distanceMinimale){
                            distanceMinimale=mur2.DistanceMurClique(coinCliq, DISTMAXCLIQUE);
                            murLePlusProche=mur2; 
                            System.out.println("mur le plus proche de ta troisième boucle : "+mur2.toString());
                        }
                    }
                    System.out.println("mur le plus proche retenu  : "+murLePlusProche.toString());
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
                System.out.println("Liste de mur SELECTIONNE : ");
                for(int i =0;i<listeMurSelectionné.size();i++){
                    System.out.println(listeMurSelectionné.get(i).toString());
                }
                System.out.println("Le mur le plus proche est : "+murLePlusProche.toString());
                this.vue.highlight(murLePlusProche);//TODO ne marche pas
                this.activeBoutonSuivantSelection();
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
                System.out.println("coordonée du clic 2 : (x,y) => ("+x2+","+y2+")");
                //Mur mur = new Mur(new Coin(this.pos.get(0), this.pos.get(1)), new Coin(x2, y2));
                Mur mur = new Mur(new Coin(this.pos[0], this.pos[1]), new Coin(x2, y2));
                //d'après vidéo il fautdrait mettre this.vue.redrawAll()
                System.out.println("mur.toString() :"+mur.toString());
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
                Mur murAB=new Mur(coinA, coinB);
                System.out.println("Je suis entre la création du premier mur et celle du deuxième mur ");
                Mur murBC=new Mur(coinB, coinC);
                Mur murCD=new Mur(coinC, coinD);
                Mur murDA=new Mur(coinD, coinA);
                System.out.println("Je suis juste AVT le contrôleur de pièce vide");
                int nbrEtage = this.listeEtage.size();
                Pièce pièce2 = new Pièce(this.listeEtage.get(nbrEtage-1));//TODO MARCHE SI ON TRAVAIL sur le dernier etage. 
                System.out.println("Je suis juste APRES le contrôleur de pièce vide");
                pièce2.setÉtage(this.vue.getModel().getListe_etage().get(0)); ///// TODO etage actuelle à selectionner
                pièce2.add(murAB);
                System.out.println("Je suis entre l'ajout du premier mur et du deuxième mur à la pièce");
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
                this.changeEtat(ETAT.CREA_PIECE_2PNT_p1);
                System.out.println("RETOUR ETAT CREA_PIECE_2PNT_p2 de création de pièce 2 points");
                break;
                /**
                 * TODO : la création de pièce 3 points possède un défaut de fonctionnement...
                 */
            case CREA_PIECE_3PNT_p1:
                System.out.println("ETAT CREA_PIECE_3PNT_p1 de création de pièce 3 points");
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
                Mur murEF=new Mur(coinE, coinF);
                Mur murFH=new Mur(coinF, coinH);
                Mur murHG=new Mur(coinH, coinG);
                Mur murGE=new Mur(coinG, coinE);
                Pièce pièce3 = new Pièce(this.vue.getModel().getListe_etage().get(0));
                pièce3.add(murEF);
                pièce3.add(murFH);
                pièce3.add(murHG);
                pièce3.add(murGE);
                System.out.println("Pièce.toString(): "+pièce3.toString());
                this.vue.redrawAll();
                this.changeEtat(ETAT.CREA_PIECE_3PNT_p1);
                break;
            case AJOUT_FEN_p1: //création de fenêtre, p1
                System.out.println("ETAT AJOUT_FENp1 de créationd e fenêtre ");
                this.pos[0]=t.getX();
                this.pos[1]=t.getY();
                //TODO Détection du mur 
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
            case AJOUT_ETAGEp1:
                System.out.println("AJOUT d'ETAGEp1");
                if(etagePrimitif!=null){
                    System.out.println("Quel est la hauteur de l'étage que vous vouliez avoir ? supérieur à 2m");
                    double hauteur=Lire.d();
                    Etage etageCréé = new Etage(hauteur, this.batiment);
                    this.listeEtage.add(etageCréé);
                    this.changeEtat(ETAT.SELECT);
                }
                else{
                    System.out.println("Veuillez cliquer sur les limites de l'ETAGE (2 cliques)");
                    this.pos[0]=t.getX();
                    this.pos[1]=t.getY();
                    System.out.println("coordonée du clic 1 : (x,y) => ("+this.pos[0]+","+this.pos[1]+")");
                    this.changeEtat(ETAT.AJOUT_ETAGEp2);
                }
                break;
            case AJOUT_ETAGEp2:
                System.out.println("AJOUT d'ETAGEp1");
                x2=t.getX();
                y2=t.getY();
                batiment=new Architecture_officielle();
                System.out.println("coordonée du clic 2 : (x,y) => ("+x2+","+y2+")");
                etagePrimitif=new Etage(3, batiment);
                etagePrimitif.add(new Mur(new Coin(this.pos[0], this.pos[1]),new Coin(this.pos[0], y2)));
                etagePrimitif.add(new Mur(new Coin(this.pos[0], y2),new Coin(x2, y2)));
                etagePrimitif.add(new Mur(new Coin(x2,y2),new Coin(x2, this.pos[1])));
                etagePrimitif.add(new Mur(new Coin(x2, this.pos[1]),new Coin(this.pos[0], this.pos[1])));
                this.listeEtage.add(etagePrimitif);
                //System.out.println("Etage.toString : "+etagePrimitif.toString());
                this.vue.getModel().getListe_etage().add(etagePrimitif);
                this.vue.redrawAll();
                this.changeEtat(ETAT.SELECT);

        }
        //TODO dans la video il fait model.add(new point) 
        //Je ne peux pas faire ça car je n'ai pas de méthode pour 
        
    }
    public void activeBoutonSuivantSelection() {
        this.vue.getRbcrmur().setDisable(true);
        this.vue.getRbEtageAj().setDisable(true);
        this.vue.getRbcrpiece3().setDisable(true);
        this.vue.getRbcrpiece2().setDisable(true);
        System.out.println("taille de la liste de mur selectionné :"+this.listeMurSelectionné.size());
        if (this.listeMurSelectionné.size()>1){
            this.vue.getRbporte().setDisable(true);
            this.vue.getRbfenêtre().setDisable(true);
            this.vue.getRbrevêtement().setDisable(false);
            this.vue.getRbidpiece().setDisable(false);
            
        }
        
    }
    void boutonSelect(ActionEvent t) {
        this.changeEtat(ETAT.SELECT);
    }
    void ajoutGrpRevetement(ActionEvent t) {
        System.out.println("Je me trouve dans la méthode AjoutGrpRevêtement");
        if (this.etat==ETAT.SELECT && this.listeMurSelectionné.size()>1){
            System.out.println("Quel revêtement veux tu pour tes murs ? ");
            Revêtement revêtement = new Revêtement(Lire.i());
            for(Mur murSelectionné : this.listeMurSelectionné){
                murSelectionné.add(revêtement);//soit on fait un "mur.add(revêtement) ou un mur.set(revêtement) avec avec le mur avec déjà un revêtement standard
                System.out.println("murSelectionné.toString() : "+murSelectionné.toString());
            }
        }
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
 
    
    //GET/SET

    public MainPane getVue() {
        return vue;
    }

    public RevêtementPane getVueRevetement() {
        return vueRevetement;
    }
    
}
