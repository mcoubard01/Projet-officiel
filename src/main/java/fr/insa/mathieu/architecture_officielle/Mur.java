/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;
import static java.lang.Math.sqrt;

public class Mur {
    private String id;
    private Coin debut;
    private Coin fin;
    private Revêtement revêtement_mur;
    private Etage étage_mur;

    public Mur(Coin debut, Coin fin) {  //test commentaire thomas 27/03
        
        //test thomas 2
        this.id = "idc"; // INTEGRER LES ID AUTOMATIQUES
        this.debut = debut;
        this.fin = fin;
    }

    public Mur(Revêtement revêtement) {
        this.revêtement_mur = revêtement;
        // Il faudrait ajouter le prix, mais pour ça il faut la surface du mur
    }
   
    public static double longueur(Coin d,Coin f){
        double L=sqrt(((f.getX()-d.getX())*(f.getX()-d.getX())+(f.getY()-d.getY())*(f.getY()-d.getY())));
        return L;   
    }
    public static double surface(Coin d, Coin f, Etage etage){
        double surface = longueur(d,f)*(etage.getHauteur_etage());////////////LE COMMENTAIRE TEST
        return surface;
    }
      public double prix(){  //prix est repris dans pièce
        return surface(this.getDebut(),this.getFin(),this.getÉtage())*(this.getRevêtement_mur().getPrix_unitaire());
    }
 
    public String getId() {
        return id;
    }

    public Coin getDebut() {
        return debut;
    }

    public Coin getFin() {
        return fin;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDebut(Coin debut) {
        this.debut = debut;
    }

    public void setFin(Coin fin) {
        this.fin = fin;
    }
    
     public Revêtement getRevêtement_mur() {
        return revêtement_mur;
    }
       public Etage getÉtage() {
        return étage_mur;
    }
       
        public static void main(String [] args){   //un main pour tester longueur et surface
        Coin debut1 , fin1;
        debut1= new Coin(2,1);
        fin1 = new Coin(5,1);
        Etage etageTest = new Etage(5);
        Mur mur = new Mur(debut1,fin1);
        System.out.println("l = " + longueur(debut1, fin1)+"surface is " + surface(debut1,fin1,etageTest) + "price is " + mur.prix());
        
    }
       
       
       
}
