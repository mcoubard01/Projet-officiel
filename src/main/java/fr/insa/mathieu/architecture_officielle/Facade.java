/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;

import java.util.ArrayList;
import java.util.List;

/**
 * OHHHHHHHHH C'EST QUOI CE TRUC ta fonction surface tu la déclares comme static
 * et tu l'appelles : facade.surface(); CA NE PEUT PAS MARCHER
 *
 * @author oscar
 */
public class Facade extends Mur  {

    private int id;
    private Coin debut;
    private Coin fin;
    private Revêtement revêtement_facade;
    private ArrayList<Ouverture> liste_ouverture;
    private ArrayList<Etage> liste_étage;

    public Facade(Coin debut, Coin fin,Revêtement revêtement_facade) {
          super(debut,fin, revêtement_facade);
          this.id = IDManager.newId(this);
         this.debut = debut;
        this.fin = fin;
      this.revêtement_facade = revêtement_facade;
        this.liste_ouverture = new ArrayList<>();
        this.liste_étage = new ArrayList<>();

    }
    /*
       public Facade(Coin debut, Coin fin) {
          super(debut,fin);
          this.id = IDManager.newId(this);
         this.debut = debut;
        this.fin = fin;
      Revêtement standard = new Revêtement(9999);
        this.liste_ouverture = new ArrayList<>();
        this.liste_étage = new ArrayList<>();

    }
    */
//TODO faire le prix pour cette classe
    
    
    public double surface() {
        int i;
        double hauteur_bat = 0;
        System.out.println("la taille de la liste est de"+ liste_étage.size() );
        for (i = 0; i < liste_étage.size(); i++) {
            hauteur_bat = hauteur_bat + liste_étage.get(i).getHauteur_etage();
        }
       
double surface_ouverture=0;

int j;


System.out.println("la taille de la liste d'ouverture est de"+ this.getListe_ouverture().size() );
 
for (j = 0; j < this.getListe_ouverture().size(); j++) {
    
        if (this.getListe_ouverture().get(j).getLongueur()==0.90){
        surface_ouverture= surface_ouverture + (this.getListe_ouverture().get(j).getLongueur()*2.10);
 System.out.println("c'est une porte");
    }else{
         surface_ouverture= surface_ouverture + (this.getListe_ouverture().get(j).getLongueur()*1.20);
        System.out.println("c'est une fenetre");
    } 
 
}
        double s = (longueur(debut,fin)*hauteur_bat)-surface_ouverture ;
        return s;
    }
    
  

    public static void main(String[] args) {

        /*
    Etage etage1 = new Etage(5);
        Etage etage2 = new Etage(6);
        int[] etage= new int [compteurEtage];
        System.out.println("id of etage1 is " + etage1.getId());
        System.out.println("id of etage2 is " + etage2.getId());
        
        Coin coin1 = new Coin(2,4);
        Coin coin2 = new Coin (2,15);
        System.out.println("id of coin1 is " + coin1.getId());
        System.out.println("id of coin2 is " + coin2.getId());
        Facade facade=new Facade(coin1,coin2);
        //System.out.println("surface"+facade.surface(coin1,coin2,etage));
         */
        // créer et initialiser l'ArrayList
      
        Etage Etage1 = new Etage(2);
       Revêtement revet_facade= new Revêtement (9999);
         Etage Etage2 = new Etage(2);
        Etage Etage3 = new Etage(2);
        
        Coin c1 = new Coin(1, 1);
        Coin c2 = new Coin(1, 5);
          Facade F1 = new Facade(c1, c2, revet_facade);
          
        Fenêtre Fenêtre1= new Fenêtre(1,2,'N',F1);
        Porte Porte1= new Porte(3,1,'E',F1);
         
      
        F1.liste_étage.add(Etage1);
        F1.liste_étage.add(Etage2);
        F1.liste_étage.add(Etage3);
    
        System.out.println("la surface de la facade est de " +F1.surface() );
        System.out.println("le prix de la facade est de " +F1.prix() );


    }

    public int getId() {
        return id;
    }

    //SET
    public void setId(int id) {
        this.id = id;
    }

    public void setDebut(Coin debut) {
        this.debut = debut;
    }

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
}
