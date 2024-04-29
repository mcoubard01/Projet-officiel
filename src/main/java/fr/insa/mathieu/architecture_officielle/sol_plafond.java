/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;
import static fr.insa.mathieu.architecture_officielle.Mur.longueur;
/**
 *
 * @author oscar
 */
public abstract class sol_plafond {  //ça marche même si ce n'est pas une majuscule?
    
    private String id;
    private Coin supg;// coin supérieur gauche
    private Coin supd;// coin supérieur droit
    private Coin infg;// coin inférieur gauche
    private Coin infd;// coin inférieur droit
    private Revêtement revêtement; 
    Revêtement standard = new Revêtement(1);//Revêtement standard
    
    //CONSTRUCTOR
     public sol_plafond(Coin supg, Coin supd, Coin infg, Coin infd) { // Utilisation de ce constructeur, on ajoutera ensuite le revêtement avec le set
        this.supg = supg;
        this.supd = supd;
        this.infg = infg;
        this.infd = infd;
    }
    /*
    public sol_plafond(String id, Coin supg, Coin supd, Coin infg,Coin infd, Revêtement revêtement) {
        this.id = id;
        this.supg = supg;
        this.supd = supd;
        this.infg = infg;
        this.infd = infd;
        if (this.contrôle(revêtement)==true){
            this.revêtement=revêtement;
            revêtement.getListe_sol_plafond().add(this);
        }
        else {
            System.out.println("pas possible d'appliquer ce revêtement");
        }
    }

    public sol_plafond(Coin supg, Coin supd, Coin infg,Coin infd, Revêtement revêtement) {
        this.id = "idc";// a remplir automatiquement 
        this.supg = supg;
        this.supd = supd;
        this.infg = infg;
        this.infd = infd;
        if (this.contrôle(revêtement)==true){
            this.revêtement=revêtement;
            revêtement.getListe_sol_plafond().add(this);
        }
        else {
            System.out.println("pas possible d'appliquer ce revêtement");
        }
        
    }
*/    
    public sol_plafond(){//CONSTRUCTEUR test pour aller plus vite lors des essais   
    }
    
    //FUNCTION
    public static double surface (Coin supg, Coin supd, Coin infg){
        double surface = longueur(supg,supd)*longueur (supg,infg);
        return surface; 
    }
    public double prix (){
        double prix=0;//initialisation du prix, lorsque c'est égale à 0 c'est qu'il n'a pas pu calculer le prix
        if (this.getRevêtement()==null){
            throw new Error("le prix ne peut être calculer car nous n'avons pas de revêtement adapté à la surface");
        }
        else {
            prix= surface (this.getSupg(),this.getSupd() ,this.getInfg())* (this.getRevêtement().getPrix_unitaire());// Fonction de prix de base déjà crée
        }
        return prix;
    }
    abstract boolean contrôle(Revêtement revêtement);
    
    // GET
    public String getId() {
        return id;
    }
    public Coin getSupg() {
        return supg;
    }
    public Coin getSupd() {
        return supd;
    }
    public Coin getInfg() {
        return infg;
    }
    public Revêtement getRevêtement() {
        return revêtement;
    }
    
    // SET
    public void setId(String id) {
        this.id = id;
    }
    public void setSupg(Coin supg) {
        this.supg = supg;
    }
    public void setSupd(Coin supd) {
        this.supd = supd;
    }
    public void setInfg(Coin infg) {
        this.infg = infg;
    }
    public void setRevêtement(Revêtement revêtement) {
        if (this.contrôle(revêtement)==true){
            this.revêtement=revêtement;
            revêtement.getListe_sol_plafond().add(this);
        }
        else {
            System.out.println("nous n'ajoutons pas le sol_plafond à la liste des durfaces pour ce revêtement !!!");
        }
    }


    
    
    
}
