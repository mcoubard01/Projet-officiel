/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;
import static fr.insa.mathieu.architecture_officielle.Mur.longueur;
import fr.insa.mathieu.architecture_officielle.Lire;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author oscar
 */
//public  abstract class sol_plafond {  //ça marche même si ce n'est pas une majuscule?// pensez a enlever abstract si on veut tester avec le main
  public abstract class sol_plafond{  
    private String id;
    private Coin supg;// coin supérieur gauche
    private Coin supd;// coin supérieur droit
    private Coin infg;// coin inférieur gauche
    private Coin infd;// coin inférieur droit
    private Revêtement revêtement_sol;
    private static List<Coin> coins;
     private Pièce pièce;
    
    Revêtement standard = new Revêtement(9999);//Revêtement standard

    public sol_plafond(String id, Coin supg, Coin supd, Coin infg,Coin infd, Revêtement revêtement_sol) {
        this.id = id;
        this.supg = supg;
        this.supd = supd;
        this.infg = infg;
        this.infd = infd;
        this.revêtement_sol = revêtement_sol;
    }

    public sol_plafond(Coin supg, Coin supd, Coin infg,Coin infd, Revêtement revêtement_sol) {
        this.id = "idc";// a remplir automatiquement 
        this.supg = supg;
        this.supd = supd;
        this.infg = infg;
        this.infd = infd;
        this.revêtement_sol = revêtement_sol;// la fonction de mathieu a redéfinir dans sol et plafond 
    }
    public sol_plafond( List<Coin> coins){// pour l'insatnt avec un revetement standard 
        this.coins = coins;
        Revêtement revêtement_standard = new Revêtement(9999);// le 9999 permet de tester avec le programme que Thomas avit créé quand on veut éviter de lire le fichier 
        this.revêtement_sol=revêtement_standard;
        
    }

    // Méthode pour trouver un seul et unique Coin par son nom donc pas très utile
    /*
    public Coin trouverCoinParNom() {
        try( Scanner scanner = new Scanner(System.in)){
        System.out.println("Donnez le nom du coin :");
        String nomCoin = scanner.nextLine();
       

        for (Coin coin : coins) {
            if (coin.getNom_coin()!= null &&coin.getNom_coin().equals(nomCoin)) {
                return coin;
            }
        }
        }
        return null; // Retourne null si aucun Coin correspondant n'est trouvé
}
    */
     private static Coin trouverCoin(Scanner scanner, String message) {
        System.out.println(message);
        String nomCoin = scanner.nextLine();
        return coins.stream()
                    .filter(coin -> coin.getNom_coin() != null && coin.getNom_coin().equals(nomCoin))
                    .findFirst()
                    .orElse(null);
    }
public  static double trouverEtCalculerSurface() {// c'est aussi une méthode pour calculer la surface sur le long terme je pense qu'on utilisera plus celle la que l'autre en dessouis 
         double surface = -9999;// obliger d'initialiser la variable car sinon j'ai une erreur a la fin pour le return 
        // plus facile pour detecter les erreur si on a -9999 comme valeur de surface. 
        
        Scanner scanner = new Scanner(System.in);// obliger d'utiliser un scanner car Lire renvoyait une erreur. 
       
            Coin supg = trouverCoin(scanner, "Donnez le nom du coin supérieur gauche:");
            Coin supd = trouverCoin(scanner, "Donnez le nom du coin supérieur droit:");
            Coin infg = trouverCoin(scanner, "Donnez le nom du coin inférieur gauche:");

            if (supg != null && supd != null && infg != null) {
                 surface = surface(supg, supd, infg);
                //double prix = prix();
                
            } else {
                System.out.println("Un ou plusieurs coins n'ont pas été trouvés.");
            }
       
        
        return surface;
    }
   
    
public double trouverEtCalculerPrix(){// permet d'obtenir le prix quand on a une liste de point 
  double trouverEtCalculerPrix = trouverEtCalculerSurface()*this.getRevêtement_sol().getPrix_unitaire() ;
  return trouverEtCalculerPrix;
}    
    


    
    //FUNCTION
    public static double surface (Coin supg, Coin supd, Coin infg){
        double surface = longueur(supg,supd)*longueur (supg,infg);
        return surface; 
    }
    public double prix (){
        double prix= surface (this.getSupg(),this.getSupd() ,this.getInfg())* (this.getRevêtement_sol().getPrix_unitaire());
        return prix;
    }
      
    
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
    public Revêtement getRevêtement_sol() {
        return revêtement_sol;
    }

    public Pièce getPièce() {
        return pièce;
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
    public void setRevêtement_sol(Revêtement revêtement_sol) {
        this.revêtement_sol = revêtement_sol;
    }

    
    
     //attention le main n'est normalement pas utilisable car on est dans une class abstract pour l'utiliser pensez a supprimez le abstract
   /*
    public static void main(String[] args) {
        List<Coin> coins = new ArrayList<>();
        coins.add(new Coin( "coin1",0, 0));
        coins.add(new Coin("coin2",0, 2));
        coins.add(new Coin("coin3",2, 2));

        sol_plafond solPlafond = new sol_plafond(coins);
        System.out.println ("est c e que ca marche");
      solPlafond.trouverEtCalculerSurface();
       /*    System.out.println("le prix est de : "+ solPlafond.trouverEtCalculerPrix());
    
       /* if (foundCoin != null) {
            System.out.println("Coin trouvé : " + foundCoin);
             int xCoordinate = foundCoin.getX();
             System.out.println("La coordonnée X du coin '" + foundCoin.getNom_coin() + "' est : " + xCoordinate);
        } 
        
            System.out.println("Aucun coin avec ce nom n'a été trouvé.");
        }
    
    }
   */ 

   
    
}
