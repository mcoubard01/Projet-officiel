/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;
//sx
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author stard
 */
public class Appartement {
    private Etage etage;
    private ArrayList<Pièce> liste_pièce; // avec le même étage
    private int id;
    private Color paint;

    
    //CONSTRUCTOR
    public Appartement(Etage etage) {
        
        this.etage = etage;
        etage.getListe_appartement().add(this);
        this.liste_pièce=new ArrayList<>();
        this.id = IDManager.newId(this);
    }
    
    //FUNCTION
    public void add_pièce(Pièce pièce){
        if (pièce.getAppartement()!=this){
            if(pièce.getAppartement()==null || this.etage.getListPièceOrpheline().contains(pièce)){
                this.liste_pièce.add(pièce);//On ajoute la pièce à la liste des pièce de cet appartement
                this.etage.getListPièceOrpheline().remove(pièce);
            }
            else{
                throw new Error ("La pièce appartient déjà à un autre appartement"); // TODO affichage de message d'erreur dans l'interface graphique
            }
            
        }
        else {
            System.out.println("La pièce est déjà dans l'appartement");
        }   
    }
    public double prix(){
        double prix=0;//initialisation du prix
        for(Pièce pièce:this.liste_pièce){
            prix = prix + pièce.prix(); // Pour chaque pièce de l'appartement : je calcule le prix
        }
        return prix;
    }

    /**
     * merci de ne pas faire de changement substantiel dans la syntaxe des toStringSauvegarde()
     * //////////Attention : cette syntaxe est utiulisée dans IDManager.récupérerUnCoin() !!!!
     * //////////Si on change la syntaxe de coin.toString(), il faut changer la méthode susdite.
     * @return String
     */
    public static String syntaxeToString(){
        return "#Syntaxe : \"Appartement;id;liste_pièce(identifiants)\"";
    }
    /**ceci est le toString() de sauvegarde.
    *MERCI DE NE PAS MODIFIER CETTE FONCTION sans me consulter
    * @return CoinEnString : String
    */
    public String toStringSauvegarde() {
        //#Syntaxe : \"Appartement;id;IdDUEtage;liste_pièce(identifiants)\" 
        ArrayList<Integer> listeDesIdDesPièces = new ArrayList<>();
        for (int i=0 ; i< listeDesIdDesPièces.size() ; i++){
            listeDesIdDesPièces.add(liste_pièce.get(i).getId());
        }
        return "Appartement;" + id + ";liste_pièce=" + listeDesIdDesPièces;
    }
    
    @Override
    public String toString() {
        
        return "Appartement; id :" + id + ";id de l'étage :" + etage.getId() + ";liste_pièce=" + this.liste_pièce;
        //éventuellement il  n'y aura pas besoin de etage.getId(), puisque l'id de l'appartement intègre déjà l'id de l'étage.
    }
    public void dessine(GraphicsContext context){
        for (Pièce pièce : this.liste_pièce){
            pièce.dessine(context);
            context.setFill(paint);
            Mur[] longMaxMin = pièce.longMaxMin();
            Coin positionCentrale = pièce.positionCentrale();
            double longMax = longMaxMin[0].longueur();
            double longMin = longMaxMin[1].longueur();
            if (longMaxMin[0].horizontal()){
                System.out.println("Je suis censé dessiner les carrés des pièces de l'appartemennt");
                context.fillRect(positionCentrale.getX()-0.2*longMax, positionCentrale.getY()-0.2*longMin, 0.4*longMax, 0.4*longMin);
            }
            else {
                context.fillRect(positionCentrale.getX()-0.2*longMin, positionCentrale.getY()-0.2*longMax, 0.4*longMin, 0.4*longMax);
            }
            
        }
    }
    
    
    //GET
    public Etage getEtage() {
        return etage;
    }
    public ArrayList<Pièce> getListe_pièce() {
        return liste_pièce;
    }  
    public int getId(){
        return id;
    }
    
    //SET 
//!!pas de setId() !!!
    
    public void setEtage(Etage etage) {
        this.etage = etage;
    }

    public void setListe_pièce(ArrayList<Pièce> liste_pièce) {
        this.liste_pièce = liste_pièce;
    }
    public void setPaint(Color paint) {
        this.paint = paint;
    }
    
}
