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
 private List<Coin> coins;
 private Sol sol;
 private Plafond plafond;
 

 public Pièce(){
 this.composition =new ArrayList<Mur>();
 this.coins= new ArrayList<Coin>();
 this.sol= new Sol(coins);
 this.plafond =new Plafond(coins);

 }
 
public void add(Mur m){
 if (m.getPièce()!= this){ 
    if (m.getPièce() !=null){
        throw new Error("mur dèja dans une autre pièce ");
    }     
    this.composition.add(m);
    m.setPièce (this);
  }
}
public void add(Coin c){
 if (c.getPièce()!= this){ 
    if (c.getPièce() !=null){
        throw new Error("mur dèja dans une autre pièce ");
    }     
    this.coins.add(c);
    c.setPièce (this);
  }
}

   public static double prix (Mur a,Mur b, Mur c, Mur d) {//Plafond e, Sol f)
        double p;
        p=a.prix() + b.prix() + c.prix() + d.prix() ;//+ e.prix() + f.prix();
        return p;
    }
public static String indente (String toIndente, String prefix){
    return prefix +toIndente.replaceAll("\n","\n"+ prefix);
}
    @Override
    public String toString() {
        String res = "Pièce {\n";
        for (int i=0; i<this.composition.size();i++){
            res=res+indente(this.composition.get(i).toString()," ")+ "\n";
        }
      
             String ess =""; // a modier plus tard c ar cela ne sert pas c'était juste opour tester si on pouvait rajouetz des choses
        for (int i=0; i<this.coins.size();i++){
            ess=ess+indente(this.coins.get(i).toString()," ")+ "\n";
        }
          return res + ess+")";
    }


    
    // en dessous ce sont des test pas forcement utile mais permmette de vérifiez si ca fonctionne. 
public static Pièce piècetest(){
    Etage e1 = new Etage(2);
    Coin c1 =new Coin(1,2);
    Coin c2 =new Coin(1,6);
    Coin c3 =new Coin(5,6);
    Coin c4 =new Coin(5,2);
    
    Mur m1= new Mur(c1,c2,e1);
    Mur m2= new Mur(c2,c3,e1);  
    Mur m3= new Mur(c3,c4,e1); 
    Mur m4= new Mur(c4,c1,e1); 
    Pièce pièce1= new Pièce();
    pièce1.add(c1);
    pièce1.add(c2);
    pièce1.add(c3);
    pièce1.add(c4);
     pièce1.add(m4);
     pièce1.add(m3);
     pièce1.add(m2);
     pièce1.add(m1);
     
     System.out.println ("est c e que ca marche");
        
        System.out.println("la surface est de : "+trouverEtCalculerSurface() );
                
   System.out.println( "le prix est de ");
    System.out.println();
    return pièce1;

}




public static void main (String[] args){
    piècetest();
}

}
