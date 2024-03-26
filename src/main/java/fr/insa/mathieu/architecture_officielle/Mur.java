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
    

    public Mur(Coin debut, Coin fin) {
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
    
    
    
}
