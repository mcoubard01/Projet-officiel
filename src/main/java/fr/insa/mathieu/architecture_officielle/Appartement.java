/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;
//sx
import static fr.insa.mathieu.architecture_officielle.Mur.longueur;
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
        this.paint=Color.color(Math.random(), Math.random(), Math.random(),0.30);
    }
    public Appartement(ArrayList<Pièce> liste_pièce){
        //il faut ajouter manuellement l'étage
        this.liste_pièce=liste_pièce;
        this.id = IDManager.newId(this);
        this.paint=Color.color(Math.random(), Math.random(), Math.random());

    }
    
    //FUNCTION
    public void add_pièce(Pièce pièce){
        System.out.println("entrée dans add_pièce");
        this.liste_pièce.add(pièce);
                System.out.println("l'appartment : " + this);


        if (pièce.getAppartement()!=this || pièce.getAppartement()==null){
            if(this.etage.getListPièceOrpheline().contains(pièce)){
                        System.out.println("ajout dans ans add_pièce");

                //this.liste_pièce.add(pièce);//On ajoute la pièce à la liste des pièce de cet appartement
                this.etage.getListPièceOrpheline().remove(pièce);
            }
            else{
                throw new Error ("La pièce appartient déjà à un autre appartement"); // TODO affichage de message d'erreur dans l'interface graphique
            }
            
        }
        else {
            System.out.println("La pièce est déjà dans l'appartement");
        }   
        System.out.println("la pièce "+ pièce.toString() + "a été ajouté à " + this.toString());
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
        for (int i=0 ; i< liste_pièce.size() ; i++){
            listeDesIdDesPièces.add(liste_pièce.get(i).getId());
        }
        return "Appartement;" + id + ";liste_pièce=" + listeDesIdDesPièces;
    }
    
    @Override
    public String toString() {
        int idEtage;
        if (etage == null){
            idEtage = 9999;
        }else{
            idEtage = etage.getId();
        }
        return "Appartement; id :" + id + ";id de l'étage :" + idEtage + ";liste_pièce=" + this.liste_pièce;
        //éventuellement il  n'y aura pas besoin de etage.getId(), puisque l'id de l'appartement intègre déjà l'id de l'étage.
    }
    public void dessine(GraphicsContext context){
        for (Pièce pièce : this.liste_pièce){
            pièce.dessine(context);
            context.setFill(paint);
            Mur[] listeMur = pièce.longMaxMin();
            Coin positionCentrale = pièce.positionCentrale();
            double longMax = longueur(listeMur[0].getDebut(),listeMur[0].getFin()); // utilisation de cette fonction plutôt que <Mur>.longueur car <Mur>.longueur fait la conversion en même temps
            double longMin = longueur(listeMur[1].getDebut(),listeMur[1].getFin()); // utilisation de cette fonction plutôt que <Mur>.longueur car <Mur>.longueur fait la conversion en même temps
            if (listeMur[0].horizontal()){
                //System.out.println("Je suis censé dessiner les carrés des pièces de l'appartemennt");
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
