/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;
import static fr.insa.mathieu.architecture_officielle.Architecture_officielle.donnee_enregistree;
import static fr.insa.mathieu.architecture_officielle.Architecture_officielle.lecture;
import static java.lang.Math.sqrt;

public class Mur {  
    private String id;
    private Coin debut;
    private Coin fin;
    private Revêtement revêtement_mur;
    private Etage étage_mur;
    
    //CONSTRUCTOR
    public Mur(Coin debut, Coin fin, Etage étage, Revêtement revêtement){
        this.id = "idc"; // INTEGRER LES ID AUTOMATIQUES
        this.debut = debut;
        this.fin = fin;
        this.revêtement_mur = revêtement;
        this.étage_mur = étage;
    }
    public Mur(Coin debut, Coin fin) {  // INCOMPLET
        Revêtement revêtement_standard = new Revêtement(1);
        this.id = "idc"; // INTEGRER LES ID AUTOMATIQUES
        this.debut = debut;
        this.fin = fin;
        this.revêtement_mur=revêtement_standard;
    }
    
    // FUNCTIONS
    public double longueur(){ //appeler "mur.longueur()" renvoie la longeueur du mur
        return sqrt(((this.getFin().getX()-this.getDebut().getX())*(this.getFin().getX()-this.getDebut().getX())+(this.getFin().getY()-this.getDebut().getY())*(this.getFin().getY()-this.getDebut().getY())));
    } 
    public static double longueur(Coin d,Coin f){
        double L=sqrt(((f.getX()-d.getX())*(f.getX()-d.getX())+(f.getY()-d.getY())*(f.getY()-d.getY())));
        return L;   
    }
    //la méthode principale du surface
    public double surface(){ //appeler : mur.surface() renvoie la surface de l'objet mur
        double s = this.longueur()*((this.getÉtage()).getHauteur_etage()); //this désigne l'objet instancié (le mur)
        return s;
    }
    /* PAS TRES UTILE vu que tu as déjà le calcul de surface d'avant qui est nickel. A voir si on garde cette fonction ( si on en a besoin)
    public static double surface(Coin d, Coin f, Etage etage){
        double surface = longueur(d,f)*(etage.getHauteur_etage());
        return surface;
    }
    */
      public double prix(){  //appeler "mur.prix()" !! : il faut que le prix_unitaire du revêtement soit défini (ou bien, temporairement écrire revetment.setPrix_unitaire([nombre]) )
        double p = this.surface()*((this.getRevêtement_mur()).getPrix_unitaire());
        return p;
    }
    public boolean contrôle(Revêtement r){
        boolean result=(r.getPourMur()).equals("1");
        return result;
    }
 
    // GET  
    public String getId() {
        return id;
    }
    public Coin getDebut() {
        return debut;
    }
    public Coin getFin() {
        return fin;
    }
    public Revêtement getRevêtement_mur() {
        return revêtement_mur;
    }
    public Etage getÉtage() {
        return étage_mur;
    }
    
    // SET
    public void setId(String id) {
        this.id = id;
    }
    public void setDebut(Coin debut) {
        this.debut = debut;
    }
    public void setFin(Coin fin) {
        this.fin = fin;
    }
    
     
       
       
    public static void main(String [] args){   //un main pour tester longueur et surface
    //test 30/03/24 (thomas) (merci de ne pas y toucher sans vérifier qu'il fonctionne encore)
    //////////////LECTURE FICHIER. IL s'appelle Revêtement_test.txt
    System.out.println("Donnez le nom de votre fichier :");
    String nom_fichier = Lire.S();
    donnee_enregistree = lecture(nom_fichier); // Lecture est ici une fonction qui renverra une ArrayList de tableau de chaînes de caractères

    //Création des coins pour faire le mur 
    Coin debut1 , fin1;
    debut1= new Coin(2,1);
    fin1 = new Coin(5,1);  
    Etage etage1 = new Etage(5);
    //Revêtement test=new Revêtement(9999);  //id=9999 est un raccourci pour mettre définir prix_unitaire à 5.55 et c'est tout (pas de lecture de donnee enregistree
    System.out.println("numéros revêtement : ");
    int id = Lire.i();
    Revêtement test=new Revêtement(id);  
    Mur mur = new Mur(debut1,fin1, etage1, test);
      
    System.out.println("Le prix du revêtement est : "+test.getPrix_unitaire());  //test.getPrix_unitaire ne fonctionne pas sans la lgne ci-dessus car le fichier donnee_enregristree n'est pas encore lu
    System.out.println("Length = " + mur.longueur());
    System.out.println("Hauteur du mur : "+etage1.getHauteur_etage());
    System.out.println("Surface is " + mur.surface());
    System.out.println("Price is " + mur.prix());
    System.out.println("contrôle result :"+ mur.contrôle(test)); // renvoie true si le revêtement est applicable, false sinon
        
    }
       
       
       
}
