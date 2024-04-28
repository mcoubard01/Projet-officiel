/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;
import static fr.insa.mathieu.architecture_officielle.Architecture_officielle.donnee_enregistree;
import static fr.insa.mathieu.architecture_officielle.Architecture_officielle.lecture;
import static java.lang.Math.sqrt;

public class Mur {  
    private int id;
    private Coin debut;
    private Coin fin;
    private Revêtement revêtement_mur;
    private Etage étage_mur;
    
    private Pièce pièce;

   
   
    
    
//On utilise la méthode avec les maps (classe IDManager). Toutefois, on pourrait créer un ID dans la classe mur qui s'incrémante tout seul.
    //private static int compteurID = 0;
//dans le constructeur : 
    //this.id = compteurID;    
    //compteurID++;
    
    //CONSTRUCTOR
    public Mur(Coin debut, Coin fin, Etage étage, Revêtement revêtement){
       // this.id = IDManager.newId(this); //renvoie un int. !!! cet int est entre 0 et 999 si c'est au RDC, entre 1000 et 1999 si c'etst au 1er étage...
        this.debut = debut;
        this.fin = fin;
        this.revêtement_mur = revêtement;
        this.étage_mur = étage;
    }
    public Mur(Coin debut, Coin fin) {  // INCOMPLET  
       // Revêtement revêtement_standard = new Revêtement(1);
        //this.id = IDManager.newId(this); //l'étage est nécessaire à cette méthode
        //ici on ne crée pas d'ID car on connaît pas l'étage
        
        //TODO : une fonction qui détecte sur quel étage on se trouve actuellement dans l'éxécution.
     
        this.debut = debut;
        this.fin = fin;
       // this.revêtement_mur=revêtement_standard;
    }

    public Mur (Coin debut, Coin fin, Etage étage){
        this.debut = debut;
        this.fin = fin;
        this.étage_mur =étage;
        Revêtement revêtement_standard = new Revêtement(9999);// le 9999 permet de tester avec le programme que Thomas avit créé quand on veut éviter de lire le fichier 
        this.revêtement_mur=revêtement_standard;
    }
    
    
    public Mur() {  //Constructeur vide servant à faire des tests (p.ex.)
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
        double s = this.longueur()*(this.getÉtage().getHauteur_etage()); //this désigne l'objet instancié (le mur)
        return s;
    }
    //TO DO controle si ouverture pour recalcul de surface
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
    public int getId() {
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

    public Pièce getPièce() {
        return pièce;
    }

    // SET
    public void setId(int id) { //ne pas utiliser setID car cela interférerait avec compteurID qui s'incrément automatiquement
        this.id = id;
    }
    public void setDebut(Coin debut) {
        this.debut = debut;
    }
    public void setFin(Coin fin) {
        this.fin = fin;
    }

    public void setÉtage_mur(Etage étage_mur) {
        this.étage_mur = étage_mur;
    }
    
     void setPièce(Pièce pièce) {
        this.pièce = pièce;
    }
    
     
       
       
    public static void main(String [] args){   //un main pour tester longueur et surface
    //test 30/03/24 (thomas) (merci de ne pas y toucher sans vérifier qu'il fonctionne encore)
    //////////////LECTURE FICHIER. IL s'appelle Revêtement_test.txt
    /*
    System.out.println("Donnez le nom de votre fichier :");
    String nom_fichier = Lire.S();
    donnee_enregistree = lecture(nom_fichier); // Lecture est ici une fonction qui renverra une ArrayList de tableau de chaînes de caractères
*/
    //Création des coins pour faire le mur 
    Coin debut1 , fin1;
    debut1= new Coin(2,4);
    fin1 = new Coin(2,1);  
    Etage etage1 = new Etage(5);
    Etage etage2 = new Etage(5);
    Revêtement test=new Revêtement(9999);  //id=9999 est un raccourci pour mettre définir prix_unitaire à 5.55 et c'est tout (pas de lecture de donnee enregistree
    //System.out.println("numéros revêtement : ");
    //int id = Lire.i();
    //Revêtement test=new Revêtement(id);  
    Mur mur = new Mur(debut1,fin1,etage1);
     
    System.out.println("Le prix du revêtement est : "+test.getPrix_unitaire());  //test.getPrix_unitaire ne fonctionne pas sans la lgne ci-dessus car le fichier donnee_enregristree n'est pas encore lu
     System.out.println("Length = " + mur.longueur());
    System.out.println("Hauteur du mur : "+etage1.getHauteur_etage());
 System.out.println("Surface is " + mur.surface());
    System.out.println("Price is " + mur.prix());
   /* System.out.println("contrôle result :"+ mur.contrôle(test)); // renvoie true si le revêtement est applicable, false sinon
   
    Fenêtre fen= new Fenêtre(2,1.5,'E',etage2);
    Porte porte= new Porte(2,2,'E',etage2);
    System.out.println(" l'ouverture appartient au mur ? "+fen.appartenance(mur)); 
    */
    }

    @Override
    public String toString() {
        return "Mur{" + "debut=" + debut + ", fin=" + fin + '}';
    }

   
       
       
       
}
