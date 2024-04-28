/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;
import static fr.insa.mathieu.architecture_officielle.Architecture_officielle.donnee_enregistree;
import static fr.insa.mathieu.architecture_officielle.Architecture_officielle.lecture;
/**
 *
 * @author stard
 */
public class Sol extends sol_plafond {
    /*
    private String id;
    private Coin supg;// coin supérieur gauche
    private Coin supd;// coin supérieur droit
    private Coin infg;// coin inférieur gauche
    private Coin infd;
*/
    private Revêtement revêtement_sol;
    
    //Revêtement standard = new Revêtement(1); // Mise en place d'un revêtement standard, pas utile car on a la lecture du fichier dans le main test
    
//CONSTRUCTEUR
    public Sol(String id, Coin supg, Coin supd, Coin infg, Coin infd, Revêtement revêtement_sol) {
        super(id,supg,supd,infg,infd,revêtement_sol);
    }
    public Sol(Coin supg, Coin supd, Coin infg,Coin infd, Revêtement revêtement_sol) {
        super(supg,supd,infg,infd,revêtement_sol);
    }
 
// FONCTION
   // @Override
    public boolean contrôle(Revêtement r){ // Contrôle
    boolean result=(r.getPourSol()).equals("1");
    return result;
}
 
 
 
 
 //////// MAIN du test pour le sol
 public static void main(String []args ){
    //////////////LECTURE FICHIER. IL s'appelle Revêtement_test.txt
    System.out.println("Donnez le nom de votre fichier :");
    String nom_fichier = Lire.S();
    donnee_enregistree = lecture(nom_fichier); // Lecture est ici une fonction qui renverra une ArrayList de tableau de chaînes de caractères
    
    System.out.println("Revêtement :"); // Prise en compte du revêtement
    int id = Lire.i();
    Revêtement r = new Revêtement(id);
    Coin supg = new Coin(2,4); // sup gauche
    Coin supd = new Coin(5,4); // sup droit
    Coin infg = new Coin(2,1); // inf gauche
    Coin infd = new Coin(5,1); // inf droit
    Sol sol = new Sol(supg,supd,infg,infd,r); // Création de mon sol
    Plafond plafond = new Plafond(supg,supd,infg,infd,r);
    System.out.println("revêtement du plafond : "+plafond.getRevêtement_plaf());
    System.out.println("contrôle sol :"+plafond.contrôle(r));
    System.out.println("surface du sol :"+plafond.surface(supg,supd,infg));
    //System.out.println("Prix total = "+plafond.prix()+" € !");
 }

}
