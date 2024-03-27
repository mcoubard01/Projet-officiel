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

    //le constructeur officiel pour le mur
    public Mur(Coin debut, Coin fin, Etage étage, Revêtement revêtement){
        this.id = "idc"; // INTEGRER LES ID AUTOMATIQUES
        this.debut = debut;
        this.fin = fin;
        this.revêtement_mur = revêtement;
        this.étage_mur = étage;
    }
    
    //un constructeur annexe, simplifié mais incomplet
    public Mur(Coin debut, Coin fin) { 
        
        //test thomas 2
        this.id = "idc"; // INTEGRER LES ID AUTOMATIQUES
        this.debut = debut;
        this.fin = fin;
    }
    
    //un constructeur annexe, simplifié mais incomplet
    public Mur(Revêtement revêtement) {
        this.revêtement_mur = revêtement;
    }
    
    
    public double longueur(){ //appeler "mur.longueur()" renvoie la longeueur du mur
        return sqrt(((this.getFin().getX()-this.getDebut().getX())*(this.getFin().getX()-this.getDebut().getX())+(this.getFin().getY()-this.getDebut().getY())*(this.getFin().getY()-this.getDebut().getY())));
    }
   
    public static double longueur(Coin d,Coin f){
        double L=sqrt(((f.getX()-d.getX())*(f.getX()-d.getX())+(f.getY()-d.getY())*(f.getY()-d.getY())));
        return L;   
    }
    
    //la méthode principale du surface
    public double surface(){ //appeler : mur.surface() renvoie la surface de l'objet mur
     return this.longueur()*(this.getÉtage().getHauteur_etage()); //this désigne l'objet instancié (le mur)
    
    }
    public static double surface(Coin d, Coin f, Etage etage){
        double surface = longueur(d,f)*(etage.getHauteur_etage());
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
        //test 27/03/24 (thomas)
        Coin debut1 , fin1;
        debut1= new Coin(2,1);
        fin1 = new Coin(5,1);
        Etage etageTest = new Etage(5);
        Revêtement test=new Revêtement();
        test.setPrix_unitaire(5.0);
        Mur mur = new Mur(debut1,fin1, etageTest, test); 
        System.out.println("length = " + mur.longueur()+", surface is " + mur.surface() + ", price is " + mur.prix());
        //résultat : "length = 3.0, surface is 15.0, price is 75.0"
        
        
    }
       
       
       
}
