/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;

public class Pièce {
    private String id;
    //private ArrayList <Mur> liste_mur;
    private Mur[] liste_mur = new Mur[4];
   /* private Mur a;
    private Mur b;
    private Mur c; 
    private Mur td;
    inutile?*/
    private double prix;
    
// Constructeur
    public Pièce(Mur a, Mur b, Mur c, Mur d) { //temporaire : on veut à terme avoir autant de murs que l'on souhaite
        //contrôle d'entrée : les murs a,b,c,d se suivent ils strictement et forment ils une pièce fermée?
        //while ((a.getDebut()).equals(d.getFin())){System.out.println("hello");} 
        this.liste_mur[0]= a;
        this.liste_mur[1]=b;
        this.liste_mur[2]=c;
        this.liste_mur[3]=d;
        
    }

    //FUNCTIONS
    //? : créer une méthode prix() qui utilise directement les attributs de Pièce (+ ajouter un attribut plafond et sol du coup) ?
    
    public static double prix (Mur a,Mur b, Mur c, Mur d, Plafond e, Sol f){
        double p;
        p=a.prix() + b.prix() + c.prix() + d.prix() ;//+ e.prix() + f.prix();
        return p;
    }
    
    //a t'on besoin ici d'une fonction spécifique pour le contrôle d'entée?  (voir le constructeur)
    
    // GET
    public Mur[] getListe_mur() {
        return liste_mur;
    }    
    public double getPrix() { 
        return prix;
    }
    
    public static void main(String[] args){
        //création d'un exemple
        
    }
    
    
    
    
}
