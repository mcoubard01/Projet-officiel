/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;
import static fr.insa.mathieu.architecture_officielle.sol_plafond.trouverEtCalculerSurface;
import java.util.ArrayList;
import java.util.List;

// Modification effectué par Oscar je modifie pour faire fonctionner avec les liste de murs  dnc je mets ce qu'on avait fait avant en commentaire. 

public class Pièce {
 private List<Mur> composition;
 private static  List<Coin> coins;
 private static Sol sol;
 private static Plafond plafond;
 private String nom_pièce;
 

 public Pièce(){
 this.composition =new ArrayList<Mur>();
 this.coins= new ArrayList<Coin>();
 this.sol = new Sol(this.coins);
 this.plafond = new Plafond(this.coins);
    }
  public Pièce(String nom_pièce){
 this.composition =new ArrayList<Mur>();
 this.coins= new ArrayList<Coin>();
 this.sol = new Sol(this.coins);
 this.plafond = new Plafond(this.coins);
 this.nom_pièce= nom_pièce;
    }


 
public void add(Mur m){
 if (m.getPièce()!= this){ 
       
    this.composition.add(m);
    m.setPièce (this);
  }
}
public void add(Coin c){
 if (c.getPièce()!= this){ 
     
    this.coins.add(c);
    c.setPièce (this);
    this.sol.add(c);
    this.plafond.add(c);
  }
}


public void add(Sol s){

}
public void add( Plafond p){
    
}
   public static double prix (Mur a,Mur b, Mur c, Mur d) {//Plafond e, Sol f)
        double p;
        p=a.prix() + b.prix() + c.prix() + d.prix() ;//+ e.prix() + f.prix();
        return p;
    }
   
   public double prix() {
        double p;
        p=this.composition.get(0).prix() + this.composition.get(1).prix() + this.composition.get(2).prix() + this.composition.get(3).prix() + sol.prix() + plafond.prix();
        return p;
    }
   
public static String indente (String toIndente, String prefix){
    return prefix +toIndente.replaceAll("\n","\n"+ prefix);
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

    

}

public static void test1(){
   
    
   
    System.out.println("la surface est du sol est de : "+sol.trouverEtCalculerSurface() );   // ceci n'est pas nécessaire 
   System.out.println( "le prix est du sol  :"+ sol.trouverEtCalculerPrix()+" €");
   System.out.println("la surface est du plafond est de : "+plafond.trouverEtCalculerSurface() );   // ceci n'est pas nécessaire
   System.out.println( "le prix est du plafond  :"+ plafond.trouverEtCalculerPrix()+" €");
   
    System.out.println();
}



public static void main (String[] args){
  System.out.println ("est c e que ca marche"); 
  piècetest();
  System.out.println("la surface est du sol est de : "+sol.trouverEtCalculerSurface() ); 
}

}
