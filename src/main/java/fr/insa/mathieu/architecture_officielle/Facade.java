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

    public Facade(Coin debut, Coin fin) {
         this.id = IDManager.newId(this);
         this.debut = debut;
        this.fin = fin;
       
        this.liste_ouverture = new ArrayList<>();
        this.liste_étage = new ArrayList<>();

    }

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
        
         Etage Etage2 = new Etage(2);
        Etage Etage3 = new Etage(2);
        
        Coin c1 = new Coin(1, 1);
        Coin c2 = new Coin(1, 5);
        Mur m1= new Mur(c1,c2,Etage2);
        Fenêtre Fenêtre1= new Fenêtre(1,1,'E',m1);
        Porte Porte1= new Porte(1,1,'E',m1);
         
        Facade F1 = new Facade(c1, c2);
         F1.liste_étage.add(Etage1);
        F1.liste_étage.add(Etage2);
        F1.liste_étage.add(Etage3);
        F1.liste_ouverture.add(Fenêtre1);
              F1.liste_ouverture.add(Porte1);
        System.out.println("la surface de la facade est de " +F1.surface() );


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
