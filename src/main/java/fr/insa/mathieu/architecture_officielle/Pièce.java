/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;
import static fr.insa.mathieu.architecture_officielle.sol_plafond.trouverEtCalculerSurface;
import java.util.ArrayList;
import java.util.List;

// Modification effectué par Oscar je modifie pour faire fonctionner avec les liste de murs  dnc je mets ce qu'on avait fait avant en commentaire. 
/**
 * CHOIX POUR LA PIECE, les attributs (2 propositions)
 * Travailler avec une liste de mur (première partie en commentaire), sol, plafond
 * Travailler avec liste de mur, liste de coin, sol, plafond (deuxième partie sans commentaire.)Normalement marche avec les fonctions d'Oscar
 * REMARQUE : comme nous travaillons avec des ArrayList : pas besoin de redéfinir les types d'objets dans l'ArrayLsit car nous les avons déjà mis en place lors des attributs
 * @author stard
 */
public class Pièce {
        /**
         * PREMIERE PROPOSITION
         * on peut travailler soit avec une liste de Mur ou 4 murs individuels pour commencer pour ne pas se préoccuper des listes
         * private int id;
         * private ArrayList<Mur> liste_mur;
         * private Sol sol;
         * private Plafond plafond;
         * private Appartement appartement;
         */
        
        /**
         * DEUXIEME PROPOSITION
         * censé fonctionner
         * Pourquoi mettre les attributs en static ??????????????? ne sert à rien si ???ça fait vraiment moche
         */
    private String nom_pièce; // sera remplacé par private int id...
    //nom_pi-ce est peut-être un bonne idée : après tout, on prévoyait que l'utilisateur puisse nommer sa pièce comme iel veut.
    private int id;
    private List<Coin> coins;
    private List<Mur> composition;
    private Sol sol;
    private Plafond plafond;
    private Appartement appartement;
// Constructor
 
 
  public Pièce(String nom_pièce){
    this.id = IDManager.newId(this);
    this.composition =new ArrayList<>();
    this.coins= new ArrayList<>();
    this.sol = new Sol(this.coins);
    this.plafond = new Plafond(this.coins);
    this.nom_pièce= nom_pièce;

    }
  public Pièce(){// permet de crféér une pièce a partir d'une liste de coin de mur et d'un plafond et d'un sol 
    this.id = IDManager.newId(this);
     this.composition =new ArrayList<>();
    this.coins= new ArrayList<>();
    this.sol = new Sol(this.coins);
    this.plafond = new Plafond(this.coins);
    String nom = "pièce n°" + this.id;
    this.nom_pièce = nom;
    }


 // fonction 
public void add(Mur m){//permet d'ajouter un mur dans la liste 
 if (m.getPièce()!= this){ 
       
    this.composition.add(m);
    m.setPièce (this);
  }
}
public void add(Coin c){// permet d'ajouetr un coin dans la liste 
 if (c.getPièce()!= this){ // NE MARCHE PAS perplexe sur le fait de mettre pièce en attribut de coin
    this.coins.add(c);
    c.setPièce (this);
    this.sol.add(c);
    this.plafond.add(c);
  }
}
   public static double prix (Mur a,Mur b, Mur c, Mur d) {//Plafond e, Sol f)
        double p;
        p=a.prix() + b.prix() + c.prix() + d.prix() ;//+ e.prix() + f.prix();
        return p;
    }
   
   // TODO tester si ca marche dèes que le la surface des sol et plafond est capable de se faire automatiquement a partir des liste de coins 
   public double prix() {// méthode permettant de calculer le prix total d'une pièce 
        double p;
        p=this.composition.get(0).prix() + this.composition.get(1).prix() + this.composition.get(2).prix() + this.composition.get(3).prix() + sol.prix() + plafond.prix();
        return p;
    }
   @Override
    public String toString() {
        String res = "Pièce: "+ nom_pièce+" {\n";
        for (int i=0; i<this.composition.size();i++){
            res=res+indente(this.composition.get(i).toString()," ")+ "\n";
        }
      
             String ess =""; // a modier plus tard c ar cela ne sert pas c'était juste opour tester si on pouvait rajouetz des choses
        for (int i=0; i<this.coins.size();i++){
            ess=ess+indente(this.coins.get(i).toString()," ")+ "\n";
        }

         String essai ="";
        if (this.sol != null) {
        essai = indente(this.sol.toString(), " ") + "\n";
    }
          String essais ="";
        if (this.plafond != null) {
        essais = indente(this.plafond.toString(), " ") + "\n";
    }
          return res + ess+ essai+essais+")";
    }
    public static String indente (String toIndente, String prefix){// meme machin que dans la premièrer vidéo du prof. 
    return prefix +toIndente.replaceAll("\n","\n"+ prefix);
}
    
    //GET
    public Appartement getAppartement() {
        return appartement;
    }

    public List<Mur> getComposition() { // Pour que l'IDManager fonctionne
        return composition;
    }

    public String getNom_pièce() {
        return nom_pièce;
    }

    public int getId() {
        return id;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public Sol getSol() {
        return sol;
    }

    public Plafond getPlafond() {
        return plafond;
    }
    
    
    //SET
    //!!!pas de setId()
    public void setAppartement(Appartement appartement) {
        this.appartement = appartement;
    }
    

    
    // en dessous ce sont des test pas forcement utile mais permmette de vérifiez si ca fonctionne. 
public static void  piècetest(){
    Etage e1 = new Etage(2);
    Coin c1 =new Coin("c1",1,2);
    Coin c2 =new Coin("c2",1,6);
    Coin c3 =new Coin("c3",5,6);
    Coin c4 =new Coin("c4",5,2);
    Coin c5 =new Coin("c5",7,2);
    Coin c6 =new Coin("c6",7,6);
    
    Mur m1= new Mur("m1",c1,c2,e1);
    Mur m2= new Mur("m2",c2,c3,e1);  
    Mur m3= new Mur("m3",c3,c4,e1); 
    Mur m4= new Mur("m4",c4,c1,e1);
    Mur m5= new Mur("m5",c4,c5,e1);
    Mur m6= new Mur("m6",c5,c6,e1);
    Mur m7= new Mur("m7",c6,c3,e1);
      
    Pièce pièce1= new Pièce("p1");
   
    pièce1.add(c1);
    pièce1.add(c2);
    pièce1.add(c3);
    pièce1.add(c4);
    pièce1.add(m1);
    pièce1.add(m2);
    pièce1.add(m3);
    pièce1.add(m4);
    System.out.println(pièce1);
    System.out.println("la surface est du sol est de : "+trouverEtCalculerSurface() ); // UNE METHODE STATIC DONC PAS DE "sol.trouverEtCalculerSurface()" !!!!!
    //System.out.println( "le prix est du sol  :"+ sol.trouverEtCalculerPrix()+" €");
    //   System.out.println("la surface est du plafond est de : "+plafond.trouverEtCalculerSurface() );   // ceci n'est pas nécessaire
    //System.out.println( "le prix est du plafond  :"+ plafond.trouverEtCalculerPrix()+" €");
    Pièce pièce2= new Pièce("p2");
   
    pièce2.add(c3);
    pièce2.add(c4);
    pièce2.add(c5);
    pièce2.add(c6);
    pièce2.add(m3);
    pièce2.add(m5);
    pièce2.add(m6);
    pièce2.add(m7);
    
    System.out.println(pièce2);
    //System.out.println("la surface est du sol est de : "+sol.trouverEtCalculerSurface() ); 
    //System.out.println( "le prix est du sol  :"+ sol.trouverEtCalculerPrix()+" €");
    //System.out.println("la surface est du plafond est de : "+plafond.trouverEtCalculerSurface() );   // ceci n'est pas nécessaire et NE MARCHE PASSSS
    //System.out.println( "le prix est du plafond  :"+ plafond.trouverEtCalculerPrix()+" €");

}
    //MAIN
    public static void main (String[] args){
        System.out.println ("est c e que ca marche"); 
        piècetest();
    }
}