/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author oscar
 */
public class Facade extends Mur  {

    private int id; 
    //Si je cromprends bien, on ne doit créer dans le bâtiment QUE 4 FACADES
    // donc id<=4
    
    private Coin debut;
    private Coin fin;
    private Revêtement revêtement_facade;
    private ArrayList<Ouverture> liste_ouverture;
    private ArrayList<Etage> liste_étage; //  TODO recuperer les 4 murs qui servent a redefinir les coins pour redtrouver avec les ouvertures

    public Facade(Coin debut, Coin fin) {
        this.id = IDManager.newId(this);
        //on ne doit créer que quatre facades maximum.
        this.debut = debut;
        this.fin = fin;
       
        this.liste_ouverture = new ArrayList<>();
        this.liste_étage = new ArrayList<>();

    }
//TODO faire le prix pour cette classe
    
    
    //FUNCTIONS
    public double surface() {
        int i;
        double hauteur_bat = 0;
        System.out.println("la taille de la liste est de"+ liste_étage.size() );
        for (i = 0; i < liste_étage.size(); i++) {
            hauteur_bat = hauteur_bat + liste_étage.get(i).getHauteur_etage();
        }
        double surface_ouverture=0;
        System.out.println("la taille de la liste est de"+ liste_ouverture.size() );
        int j;
        for (j = 0; j < liste_ouverture.size(); j++) {
            // TODO rajoutez une vérification d'appartenance a la facade mais je ne sait pas commment rédiger ça 
            if (liste_ouverture.get(j).getLongueur()==0.90){
                surface_ouverture= surface_ouverture + (liste_ouverture.get(j).getLongueur()*2.10);
         System.out.println("c'est une porte");
            }else{
                 surface_ouverture= surface_ouverture + (liste_ouverture.get(j).getLongueur()*1.20);
                System.out.println("c'est une fenetre");
            }
         }
                double s = (longueur(debut,fin)*hauteur_bat)-surface_ouverture ;

                return s;

    }//fin surface()
    
    /**
     *dessine une facade. Il faut donc appeler quatre fois cette méthode pour dessiner le contour de tous les étages.
     * @param context
     */
    @Override
    public void dessine(GraphicsContext context){
        this.debut.dessine(context);
        this.fin.dessine(context);
        context.setStroke(Color.FORESTGREEN);
        context.strokeLine(this.getDebut().getX(), this.getDebut().getY(), this.getFin().getX(), this.getFin().getY());
    }
    
    @Override
    public void highlight(GraphicsContext context){
        System.out.println("HIGHLIGHT de la classe Facade");
        this.debut.dessine(context);
        this.fin.dessine(context);
        context.setStroke(Color.RED);
        context.strokeLine(this.getDebut().getX(), this.getDebut().getY(), this.getFin().getX(), this.getFin().getY());  
    }
    
    /**
     * ceci est l'implémentation de DistanceMurClique dans la classe Facade, afin que la sélection de mur fonctionne.
     * @param coinCliq
     * @param distMax
     * @return super.DistanceMurClique : double
     */
    public double distanceFacadeClique(Coin coinCliq, double distMax){
        //return super.DistanceMurClique(coinCliq, distMax);
        boolean result=false;
        Line2D.Double ligne = new Line2D.Double(this.debut.getX(), this.debut.getY(),this.fin.getX(), this.fin.getY());
        double distanceClique_Mur = ligne.ptSegDist(coinCliq.getX(),coinCliq.getY()); 
        //"line.ptSegDist" renvoie la distance entre le clic et son projeté orthogonal sur le mur
        return distanceClique_Mur;
    }
    
    /**
     * merci de ne pas faire de changement substanciel dans la syntaxe des toStringSauvegarde()
     * //////////Attention : cette syntaxe est utiulisée dans IDManager.récupérerUnMur() !!!!
     * //////////Si on change la syntaxe de mur.toStringSauvegarde(), il faut changer la méthode susdite.
     * @return String
     */
    public static String syntaxeToString(){
        return "#Syntaxe  : \"Mur;id;idDuCoinDebut;idDuCoinFin;idDuRevêtement";
    }
    /**ceci est le toString() de sauvegarde.
    *MERCI DE NE PAS MODIFIER CETTE FONCTION sans me consulter
    * @return MurEnString : String
    */
    @Override
    public String toStringSauvegarde() {
        //Syntaxe : "Mur;id;idCoinDebut;idCoinFin"
        return "Mur;" + this.id + ";" + debut.getId() + ";" + fin.getId() + ";" + this.revêtement_facade.getId() ;
    }
    
    @Override
    public String toString() {
        //Syntaxe : "Mur;id;idDuCoinDebut;idDuCoinFin;idDeEtageDuMur;idDePièce1;idDePièce2;liste_ouverture
        String résultat = "Facade; id :" + this.id + "; coin1: " + debut.getId() + "; coin2: " + fin.getId() ;
            résultat += ";liste_ouverture=" + liste_ouverture ;
            return résultat;
    }


    //GET

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Coin getDebut() {
        return debut;
    }
    

    @Override
    public Coin getFin() {
        return fin;
    }

    public Revêtement getRevêtement_facade() {
        return revêtement_facade;
    }

    @Override
    public ArrayList<Ouverture> getListe_ouverture() {
        return liste_ouverture;
    }

    public ArrayList<Etage> getListe_étage() {
        return liste_étage;
    }
    
    
    //SET
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setDebut(Coin debut) {
        this.debut = debut;
    }

    @Override
    public void setFin(Coin fin) {
        this.fin = fin;
    }
    

    public void setRevêtement_facade(Revêtement revêtement_facade) {
        this.revêtement_facade = revêtement_facade;
    }
    /*
    public void setListe_etage(Etage[] liste_etage) {
        this.liste_etage = liste_etage;
    }
    public void setListe_porte(Porte[] liste_porte) {
        this.liste_porte = liste_porte;
    }
    public void setListe_fenêtre(Porte[] liste_fenêtre) {
        this.liste_fenêtre = liste_fenêtre;
    }
     */
    
     public static void main(String[] args) {
        // créer et initialiser l'ArrayList
      
        Architecture_officielle batiment = new Architecture_officielle();
        Etage etage1 = new Etage(5,batiment);
        Etage etage2 = new Etage(6,batiment);
        int[] etage= new int [IDManager.mapEtage.size()];
        System.out.println("id of etage1 is " + etage1.getId());
        System.out.println("id of etage2 is " + etage2.getId());
        
        Coin coin1 = new Coin(2,4);
        Coin coin2 = new Coin (2,15);
        System.out.println("id of coin1 is " + coin1.getId());
        System.out.println("id of coin2 is " + coin2.getId());
        Facade facade=new Facade(coin1,coin2);
        //System.out.println("surface"+facade.surface(coin1,coin2,etage));
       
       //test n°2
        Etage etageTest1 = new Etage(2,batiment);
        
         Etage etageTest2 = new Etage(2,batiment);
        Etage etageTest3 = new Etage(2,batiment);
        
        Coin c1 = new Coin(1, 1);
        Coin c2 = new Coin(1, 5);
        Mur m1= new Mur(c1,c2,etageTest2);
        Fenêtre Fenêtre1= new Fenêtre(1,1,'E',m1);
        Porte Porte1= new Porte(1,1,'E',m1);
         
        Facade F1 = new Facade(c1, c2);
         F1.liste_étage.add(etageTest1);
        F1.liste_étage.add(etageTest2);
        F1.liste_étage.add(etageTest3);
        F1.liste_ouverture.add(Fenêtre1);
              F1.liste_ouverture.add(Porte1);
        System.out.println("la surface de la facade est de " +F1.surface() );
        System.out.println(F1.toStringSauvegarde());

        

    }
}
