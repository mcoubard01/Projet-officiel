/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;
import static fr.insa.mathieu.architecture_officielle.Architecture_officielle.donnee_enregistree;
import static fr.insa.mathieu.architecture_officielle.Architecture_officielle.lecture;
import static java.lang.Math.sqrt;
import java.util.ArrayList;

public class Mur {  
    private int id;
    private Coin debut;
    private Coin fin;
    private Revêtement revêtement_mur;
    private Etage étage_mur;
    private ArrayList<Ouverture> liste_ouverture;
//On utilise la méthode avec les maps (classe IDManager). Toutefois, on pourrait créer un ID dans la classe mur qui s'incrémante tout seul.
    //private static int compteurID = 0;
//dans le constructeur : 
    //this.id = compteurID;    
    //compteurID++;
    
    //CONSTRUCTOR
    public Mur(Coin debut, Coin fin, Etage étage, Revêtement revêtement){
        this.id = IDManager.newId(this); //renvoie un int. !!! cet int est entre 0 et 999 si c'est au RDC, entre 1000 et 1999 si c'etst au 1er étage...
        this.debut = debut;
        this.fin = fin;
        this.revêtement_mur = revêtement;
        this.étage_mur = étage;
        this.liste_ouverture=new ArrayList<Ouverture>();
    }

    public Mur(Coin debut, Coin fin) {  // INCOMPLET TO incorporer l'étage automatiquement
        Revêtement revêtement_standard = new Revêtement(1); 
        //this.id = IDManager.newId(this); //l'étage est nécessaire à cette méthode
        //ici on ne crée pas d'ID car on connaît pas l'étage
        
        //TODO : une fonction qui détecte sur quel étage on se trouve actuellement dans l'éxécution,, afin que the IDManager.newId() fonctionne
        // Solution : Juste un get qui renvoie l'étage du mur selectionné => FAIT
        this.debut = debut;
        this.fin = fin;
        this.revêtement_mur=revêtement_standard;
        this.liste_ouverture=new ArrayList<Ouverture>();
        this.étage_mur=null;
        //etage.getListe_mur().add(this); TO DO à améliorer mais l'id est de ajouter automatique le mur qu'on créer à la liste de mur de l'étage.
    }

    public Mur(Coin debut, Coin fin, Etage étage_mur) {
        this.étage_mur = étage_mur;
        this.debut = debut;
        this.fin = fin;
        this.id = IDManager.newId(this);
       
        
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
        double s = this.longueur()*((this.getÉtage()).getHauteur_etage()); //this désigne l'objet instancié (le mur)
        return s;
    }
    //TO DO controle si ouverture pour recalcul de surface soustraire les surfaces des ouvertures
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

    @Override
    public String toString() {
        return "Mur{" + "id=" + id + ", debut=" + debut + ", fin=" + fin + ", \u00e9tage_mur=" + étage_mur.getId() + '}'; // + ", rev\u00eatement_mur=" + revêtement_mur + 
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

    public ArrayList<Ouverture> getListe_ouverture() {
        return liste_ouverture;
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
    public void setEtage(Etage etage){
        this.étage_mur = etage;
    }
    public void setRevêtement_mur(Revêtement revêtement_mur) {
        this.revêtement_mur = revêtement_mur;
        revêtement_mur.getListe_mur().add(this);
    }
    
    
    
     
       
       
    public static void main(String [] args){   //un main pour tester longueur et surface
    //test 30/03/24 (thomas) (merci de ne pas y toucher sans vérifier qu'il fonctionne encore)
    //////////////LECTURE FICHIER. IL s'appelle Revêtement_test.txt
    
    System.out.println("Donnez le nom de votre fichier :");
    String nom_fichier = Lire.S();
    donnee_enregistree = lecture(nom_fichier); // Lecture est ici une fonction qui renverra une ArrayList de tableau de chaînes de caractères

    //Création des coins pour faire le mur 
    Coin debut1 , fin1;
    debut1= new Coin(2,4);
    fin1 = new Coin(2,1);
    Etage etage1 = new Etage(5);
    Etage etage2 = new Etage(5);
    //Revêtement test=new Revêtement(9999);  //id=9999 est un raccourci pour mettre définir prix_unitaire à 5.55 et c'est tout (pas de lecture de donnee enregistree
    //System.out.println("numéros revêtement : ");
    //int id = Lire.i();
    //Revêtement test=new Revêtement(id);  

    Mur mur = new Mur(debut1,fin1, etage1);

    /*  
    System.out.println("Le prix du revêtement est : "+test.getPrix_unitaire());  //test.getPrix_unitaire ne fonctionne pas sans la lgne ci-dessus car le fichier donnee_enregristree n'est pas encore lu
    System.out.println("Length = " + mur.longueur());
    System.out.println("Hauteur du mur : "+etage1.getHauteur_etage());
    System.out.println("Surface is " + mur.surface());
    System.out.println("Price is " + mur.prix());
    System.out.println("contrôle result :"+ mur.contrôle(test)); // renvoie true si le revêtement est applicable, false sinon
    */
    Fenêtre fen1 = new Fenêtre(2,2,'N',mur);
    Fenêtre fen2 = new Fenêtre(2,2,'N',mur);
    Fenêtre fen3 = new Fenêtre(2,2,'N',mur);
    Porte porte1 = new Porte(2,2,'N',mur);
    fen1.setMur2(mur1);
    fen2.setMur2(mur1);
    porte1.setMur2(mur1);
    /*
    Porte porte= new Porte(2,2,'E',mur,mur1);
    System.out.println("mur 1 : "+porte.getMur1());
    System.out.println("mur 2 :"+porte.getMur2());

    System.out.println(" l'ouverture appartient au mur ? "+fen.appartenance(mur));

        System.out.println(mur);


    System.out.println("fen.getMur1() : "+fen.getMur1());
*/
    System.out.println("taille de la liste_ouverture :"+mur.getListe_ouverture().size());
    System.out.println("mur.getListe_ouverture() : ");
    for (int i=0;i<mur.getListe_ouverture().size();i++){
        System.out.println("i = "+i+" =>"+mur.getListe_ouverture().get(i));
    }
    System.out.println("mur1.getListe_ouverture() : ");
    for (int i=0;i<mur1.getListe_ouverture().size();i++){
        System.out.println("i = "+i+" =>"+mur1.getListe_ouverture().get(i));
    }

    }
       
       
       
}
