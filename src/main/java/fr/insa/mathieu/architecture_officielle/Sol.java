/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;
import static fr.insa.mathieu.architecture_officielle.Architecture_officielle.donnee_enregistree;
import static fr.insa.mathieu.architecture_officielle.Architecture_officielle.lecture;
import static fr.insa.mathieu.architecture_officielle.Mur.longueur;
 import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//ATTENTION ATTENTION tous n'est pas dans lordre les set et get ne sont pas bien placé tout est un peu melangé mais ca marche ( a peu prèes).
/**
 *
 * @author stard
 */
public class Sol extends sol_plafond {
    
   // private String id;
    private Coin supg;// coin supérieur gauche
    private Coin supd;// coin supérieur droit
    private Coin infg;// coin inférieur gauche
    private Coin infd;
    private Revêtement revêtement_sol;   
    private static List<Coin> coins;
     private Pièce pièce;
    
    Revêtement standard = new Revêtement(9999);//Revêtement standard
    
   
    //Revêtement standard = new Revêtement(1); // Mise en place d'un revêtement standard, pas utile car on a la lecture du fichier dans le main test
    
//CONSTRUCTEUR
    public Sol(String id, Coin supg, Coin supd, Coin infg, Coin infd, Revêtement revêtement_sol) {
        super(id,supg,supd,infg,infd,revêtement_sol);
    }
    public Sol(Coin supg, Coin supd, Coin infg,Coin infd, Revêtement revêtement_sol) {
        super(supg,supd,infg,infd,revêtement_sol);
    }
  
    public Sol(List<Coin> coins){
        super (coins);
         Revêtement revêtement_standard = new Revêtement(9999);// le 9999 permet de tester avec le programme que Thomas avit créé quand on veut éviter de lire le fichier 
        this.revêtement_sol=revêtement_standard;
        this.coins =new ArrayList<Coin>();
        
    }
    
// FONCTION
   // @Override
    /*
    public boolean contrôle(Revêtement r){ // Contrôle
    boolean result=(r.getPourSol()).equals("1");
    return result;
}
 */
public static String indente (String toIndente, String prefix){// présenter dans la vidéo 1 du prof 
    return prefix +toIndente.replaceAll("\n","\n"+ prefix);
}

// permet de faire un test dans le main plus bas (permet seulement d'initialiser une liste de coin
public static Sol soltest(){
    Sol sol= new Sol(coins);
    sol.add(new Coin("coin1", 0, 0));
    sol.add(new Coin("coin2", 0, 2));
    sol.add(new Coin("coin3", 2, 2));
    return  sol;
}

// méthode permettant l'ajout de coins dans une liste 
public void add(Coin c){
 if (c.getSol()!= this){ 
     
    this.coins.add(c);
    c.setSol (this);
  }
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

    public Coin getInfd() {
        return infd;
    }

    public Revêtement getRevêtement_sol() {
        return revêtement_sol;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public Pièce getPièce() {
        return pièce;
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

    public void setInfd(Coin infd) {
        this.infd = infd;
    }

    public void setRevêtement_sol(Revêtement revêtement_sol) {
        this.revêtement_sol = revêtement_sol;
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }

    public void setPièce(Pièce pièce) {
        this.pièce = pièce;
    }

  
@Override
public String toString() {
    StringBuilder liste_coin = new StringBuilder("Sol {\n");
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


  


 
 //////// MAIN du test pour le sol
 public static void main(String []args ){
    //////////////LECTURE FICHIER. IL s'appelle Revêtement_test.txt
    /*System.out.println("Donnez le nom de votre fichier :");
    String nom_fichier = Lire.S();
    donnee_enregistree = lecture(nom_fichier); // Lecture est ici une fonction qui renverra une ArrayList de tableau de chaînes de caractères
    
    System.out.println("Revêtement :"); // Prise en compte du revêtement
    //int id = Lire.i();
    Revêtement r = new Revêtement(9999);// pensez a changer le 9999 par le bon identifiant mais cela permet de testre si ca marche 
    System.out.println("le prix du revetement est de :"+r.getPrix_unitaire());
    Coin supga = new Coin(2,4); // sup gauche
    Coin supdr = new Coin(5,4); // sup droit
    Coin infga = new Coin(2,1); // inf gauche
    Coin infdr = new Coin(5,1); // inf droit
    
    Sol sol = new Sol(supga,supdr,infga,infdr,r); // Création de mon sol
    System.out.println ("est ce que ca marche");
    Plafond plafond = new Plafond(supga,supdr,infga,infdr,r);
    System.out.println("revêtement du sol : "+sol.getRevêtement_sol());
    //System.out.println("contrôle sol :"+plafond.contrôle(r));
    System.out.println("surface du sol :"+sol.surface(supga,supdr,infga));
    System.out.println("prix du sol :"+sol.prix());
    */
    //System.out.println("Prix total = "+plafond.prix()+" € !");
    
     Sol sol = soltest();
    System.out.println("Pièce test : \n" + sol); 
    System.out.println("Est-ce que ça marche ?");
        System.out.println("La surface calculée est : " + soltest().trouverEtCalculerSurface());
        System.out.println("le prix est de : "+ soltest().trouverEtCalculerPrix());
 }

}
