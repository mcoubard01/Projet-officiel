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
    private Revêtement revêtement;   
    private static List<Coin> coins;
     private Pièce pièce;
    
    Revêtement standard = new Revêtement(9999);//Revêtement standard
    
   
    //Revêtement standard = new Revêtement(1); // Mise en place d'un revêtement standard, pas utile car on a la lecture du fichier dans le main test
    
    //CONSTRUCTEUR
    /* NON utilisé car on rajoute le revêtement après avoir créer le sol ou plafond
    public Sol(String id, Coin supg, Coin supd, Coin infg, Coin infd, Revêtement revêtement) {
        super(id,supg,supd,infg,infd,revêtement);
    }
    public Sol(Coin supg, Coin supd, Coin infg,Coin infd, Revêtement revêtement) {
        super(supg,supd,infg,infd,revêtement);
    }
*/
    public Sol() {
    }  
    public Sol(String id, Coin supg, Coin supd, Coin infg, Coin infd) {
        super(id, supg, supd, infg, infd);
    }
    /*
    public Sol(List<Coin> coins){
        super (coins); 
        this.revêtement=standard;
        this.coins =new ArrayList<Coin>();  
    }
    */
    public Sol(List<Coin> coins) {
        super(coins);
    }
    
    
    // FONCTION
    @Override
    public boolean contrôle(Revêtement r){ // Contrôle
        boolean result=(r.getPourSol()).equals("1");
        return result;
    }
    public static String indente (String toIndente, String prefix){// présenter dans la vidéo 1 du prof 
        return prefix +toIndente.replaceAll("\n","\n"+ prefix);
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

// méthode permettant l'ajout de coins dans une liste 
public void add(Coin c){ // NE MARCHE PAS
 if (c.getSol()!= this){ 
        this.coins.add(c);
        c.setSol (this);
  }
}
    //GET
/**
 * QUELLE EST L'UTILITE !!!! on les met déjà dans la classe sol_plafond... (les get et set) les set j'ai enlevé
 * Ils ne sont pas censé détecter automatiquement si nous sommes sur un sol ou plafond ????
 * @return 
 */
/*
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
*/
    public List<Coin> getCoins() {
        return coins;
    }
    //SET
    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }
    public void setPièce(Pièce pièce) {
        this.pièce = pièce;
    }

  // TEST FUNCTION permet de faire un test dans le main plus bas (permet seulement d'initialiser une liste de coin
    public static Sol soltest(){
        Sol sol= new Sol(coins);
        sol.add(new Coin("coin1", 0, 0));
        sol.add(new Coin("coin2", 0, 2));
        sol.add(new Coin("coin3", 2, 2));
        return  sol;
    }

 //////// MAIN du test pour le sol
 public static void main(String []args ){
    //////////////LECTURE FICHIER. IL s'appelle Revêtement_test.txt
    /*
    System.out.println("Donnez le nom de votre fichier :");
    String nom_fichier = Lire.S();
*/
    donnee_enregistree = lecture("Revêtement_test.txt"); // Lecture est ici une fonction qui renverra une ArrayList de tableau de chaînes de caractères
    //System.out.println("Revêtement :"); // Prise en compte du revêtement
    //int id = Lire.i();
    //Revêtement r = new Revêtement(id);
    Revêtement r = new Revêtement(9999);// pensez a changer le 9999 par le bon identifiant mais cela permet de testre si ca marche   
    Coin supg = new Coin(2,4);
    Coin supd = new Coin(5,4); // sup droit
    Coin infg = new Coin(2,1); // inf gauche
    Coin infd = new Coin(5,1); // inf droit
    Sol sol = new Sol("id",supg,supd,infg,infd); // Création de mon sol
    Plafond plafond = new Plafond("id",supg,supd,infg,infd);
    sol.setRevêtement(r);
    plafond.setRevêtement(r);
    System.out.println ("est ce que ca marche");
    System.out.println("contrôle plafond :"+plafond.contrôle(r));
    System.out.println("revêtement du plafond : "+plafond.getRevêtement());
    System.out.println("surface du sol :"+plafond.surface(supg,supd,infg));
    System.out.println("Prix total = "+plafond.prix()+" € !");
    System.out.println("revêtement du sol : "+sol.getRevêtement());
    System.out.println("surface du sol :"+sol.surface(supg, supd, infg));
    System.out.println("prix du sol :"+sol.prix());
    
    /* TRES  BANCAL
    Sol sol = soltest();
    System.out.println("Pièce test : \n" + sol); 
    System.out.println("Est-ce que ça marche ?");
    System.out.println("La surface calculée est : " + soltest().trouverEtCalculerSurface());
    System.out.println("le prix est de : "+ soltest().trouverEtCalculerPrix());
    */
    }

}
