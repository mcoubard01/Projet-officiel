/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;
import static fr.insa.mathieu.architecture_officielle.Mur.longueur;
import static fr.insa.mathieu.architecture_officielle.IDManager.compteurEtage;
import static fr.insa.mathieu.architecture_officielle.IDManager.compteurPorte;
import static fr.insa.mathieu.architecture_officielle.IDManager.compteurFenêtre;

/**
 *OHHHHHHHHH C'EST QUOI CE TRUC
 * ta fonction surface tu la déclares comme static et tu l'appelles : facade.surface(); CA NE PEUT PAS MARCHER
 * @author oscar
 */
public class Facade {
    private int id;
    private Coin debut;
    private Coin fin;
    private Revêtement revêtement_facade;
    private Etage[] liste_etage= new Etage[compteurEtage]  ;
    private Porte[] liste_porte =new Porte [compteurPorte];
    private Porte[] liste_fenêtre =new Porte [compteurFenêtre];

    public Facade(Coin debut, Coin fin) {
        this.debut = debut;
        this.fin = fin;
      
    }
    
public static double surface(Coin d, Coin f ,Etage[] etage ){
    double s=0;
    int i=0;
    double somme;
    for (i=0;i<=compteurEtage;i++){
        somme=longueur(d,f)*etage[i].getHauteur_etage() ;
        s=s+somme;
    }
           return s;
    
}
     
     

    public static void main(String [] args){ 
    Architecture_officielle batiment = new Architecture_officielle();
    Etage etage1 = new Etage(5,batiment);
        Etage etage2 = new Etage(6,batiment);
        int[] etage= new int [compteurEtage];
        System.out.println("id of etage1 is " + etage1.getId());
        System.out.println("id of etage2 is " + etage2.getId());
        
        Coin coin1 = new Coin(2,4);
        Coin coin2 = new Coin (2,15);
        System.out.println("id of coin1 is " + coin1.getId());
        System.out.println("id of coin2 is " + coin2.getId());
        Facade facade=new Facade(coin1,coin2);
        //System.out.println("surface"+facade.surface(coin1,coin2,etage));
  
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
    public void setListe_etage(Etage[] liste_etage) {
        this.liste_etage = liste_etage;
    }
    public void setListe_porte(Porte[] liste_porte) {
        this.liste_porte = liste_porte;
    }
    public void setListe_fenêtre(Porte[] liste_fenêtre) {
        this.liste_fenêtre = liste_fenêtre;
    }
    
}


