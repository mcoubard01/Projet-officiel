/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle; //test commentaire
import static fr.insa.mathieu.architecture_officielle.Architecture_officielle.donnee_enregistree;
import static fr.insa.mathieu.architecture_officielle.Architecture_officielle.lecture;
import static fr.insa.mathieu.architecture_officielle.Mur.longueur;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author stard
 */
public class Plafond extends sol_plafond{
    
    //private String id;
    private Coin supg;// coin supérieur gauche
    private Coin supd;// coin supérieur droit
    private Coin infg;// coin inférieur gauche
    private Coin infd;
     private Revêtement revêtement_plaf;   
    private static List<Coin> coins;
     private Pièce pièce;
     Revêtement standard = new Revêtement(9999);//Revêtement standard

//CONSTRUCTOR
    public Plafond(List<Coin> coins){// permet de creer un plafond a partir d'une liste de coin 
        super (coins);
         Revêtement revêtement_standard = new Revêtement(9999);// le 9999 permet de tester avec le programme que Thomas avit créé quand on veut éviter de lire le fichier 
        this.revêtement_plaf=revêtement_standard;
        this.coins = new ArrayList<Coin>();
    }
    public Plafond(String id, Coin supg, Coin supd, Coin infg, Coin infd) {
        super(id, supg, supd, infg, infd);
    }
    
  
    // FUNCTION
    @Override
    public boolean contrôle(Revêtement r){
        boolean result=(r.getPourPlafond()).equals("1");
        return result;
    }

    public static String indente (String toIndente, String prefix){// meme methode que dans la 1er vidéo du cours.
        return prefix +toIndente.replaceAll("\n","\n"+ prefix);
    }
    // permet d'ajouter un coin dans une liste 
    public void add(Coin c){
        if (c.getPlafond()!= this){ // NE MARCHE PAS pareil que pour sol
            this.coins.add(c);
            c.setPlafond (this);
        }
    }
    @Override
    public String toString() {
        StringBuilder liste_coin = new StringBuilder("Plafond {\n");
        if (coins != null && !coins.isEmpty()) {
            for (Coin coin : coins) {
                liste_coin.append(indente(coin.toString(), "    ")).append("\n");
            }
        } else {
            liste_coin.append("    Aucun coin n'est défini\n");
        }
        liste_coin.append("}");
        return liste_coin.toString();
    } 

    //GET
    public Coin getSupd() {
        return supd;
    }
    public Coin getInfg() {
        return infg;
    }
    public Coin getInfd() {
        return infd;
    }
    public static List<Coin> getCoins() {
        return coins;
    }
    public Pièce getPièce() {
        return pièce;
    }
    
    //SET
    public void setSupg(Coin supg) {
        this.supg = supg;
    }
    public void setSupd(Coin supd) {
        this.supd = supd;
    }
    public void setInfg(Coin infg) {
        this.infg = infg;
    }
    public void setInfd(Coin infd) {
        this.infd = infd;
    }
    public void setRevêtement_plaf(Revêtement revêtement_plaf) {
        this.revêtement_plaf = revêtement_plaf;
    }
    public static void setCoins(List<Coin> coins) {
        Plafond.coins = coins;
    }
    public void setPièce(Pièce pièce) {
        this.pièce = pièce;
    } 

    // TEST FUNCTION
    public static Plafond plafondtest(){
        Plafond plafond = new Plafond(coins);
        plafond.add(new Coin("coin1", 0, 0));
        plafond.add(new Coin("coin2", 0, 2));
        plafond.add(new Coin("coin3", 2, 2));
        return  plafond;
    }
    
    //MAIN TEST
    public static void main(String []args ){
        Plafond plafond = plafondtest();
        System.out.println("Pièce test : \n" + plafond); 
        System.out.println("Est-ce que ça marche ?");
        System.out.println("La surface calculée est : " + plafondtest().trouverEtCalculerSurface());
        System.out.println("le prix est de : "+ plafondtest().trouverEtCalculerPrix());
 
    }  
}
