/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;
import static fr.insa.mathieu.architecture_officielle.Architecture_officielle.lecture;
import fr.insa.mathieu.architecture_officielle.gui.Contrôleur;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.awt.geom.Line2D.Double; 

public class Mur {  
    private int id;
    private Coin debut;
    private Coin fin;
    private Revêtement revêtement;
    //les trois la dessous j'ai du les rajoutez mais je suis pas sur que ce que j'ai fait soit juste .

    private Pièce pièce1;
    private Etage étage; // pas sur que le mur soit relié à l'étage directement. 
    private String nom_mur;
    private Pièce pièce2;//a supprimer
    private ArrayList<Ouverture> liste_ouverture;
    
//On utilise la méthode avec les maps (classe IDManager). Toutefois, on pourrait créer un ID dans la classe mur qui s'incrémante tout seul.
    //private static int compteurID = 0;
//dans le constructeur : 
    //this.id = compteurID;    
    //compteurID++;
    
    //CONSTRUCTOR
    public Mur(String nom_mur,Coin debut, Coin fin) {  // INCOMPLET TO incorporer l'étage automatiquement
        Revêtement revêtement_standard = new Revêtement(1); 
        //this.id = IDManager.newId(this); //l'étage est nécessaire à cette méthode
        //ici on ne crée pas d'ID car on connaît pas l'étage
        //TODO : une fonction qui détecte sur quel étage on se trouve actuellement dans l'éxécution,, afin que the IDManager.newId() fonctionne
        // Solution : Juste un get qui renvoie l'étage du mur selectionné => FAIT
        this.nom_mur=nom_mur;
        this.debut = debut;
        this.fin = fin;       
            // this.revêtement=revêtement_standard;
    }
          // j'ai du le rajoutez pour pouvoir faire un test il pourra etre supprimé par la suite
    
    
      public Mur(Coin debut, Coin fin) {  // INCOMPLET TO incorporer l'étage automatiquement
       
        this.id = IDManager.newId(this); //l'étage est nécessaire à cette méthode
        //ici on ne crée pas d'ID car on connaît pas l'étage
        //TODO : une fonction qui détecte sur quel étage on se trouve actuellement dans l'éxécution,, afin que the IDManager.newId() fonctionne
        // Solution : Juste un get qui renvoie l'étage du mur selectionné => FAIT
        this.debut = debut;
        this.fin = fin;
       // this.revêtement=revêtement_standard;
    }
    
      //@Thomas : je vote pour supprimer ce constructeur.
     public Mur (String nom_mur,Coin debut, Coin fin, Etage étage, Pièce pièce1){
        this.id = IDManager.newId(this); //renvoie un int. !!! cet int est entre 0 et 999 si c'est au RDC, entre 1000 et 1999 si c'etst au 1er étage...
        this.debut = debut;
        this.nom_mur= nom_mur;
        this.fin = fin;
        this.étage =étage;
        this.pièce1 = pièce1;
       /*NORMALEMENT nous n'entrons pas directement le revêtement dans le constructeur, on le rajoute après avec un set pour permettre un contrôle de correspondance entre surface et revêtement.
        Revêtement revêtement_standard = new Revêtement(9999);// le 9999 permet de tester avec le programme que Thomas avit créé quand on veut éviter de lire le fichier 
        this.revêtement=revêtement_standard;
        */
        this.liste_ouverture=new ArrayList<>();
    }
     public Mur (Coin debut, Coin fin, Etage étage, Pièce pièce1){
        this.id = IDManager.newId(this); //renvoie un int. !!! cet int est entre 0 et 999 si c'est au RDC, entre 1000 et 1999 si c'etst au 1er étage...
        this.debut = debut;
        this.fin = fin;
        this.étage =étage;
        this.pièce1 = pièce1;
       /*NORMALEMENT nous n'entrons pas directement le revêtement dans le constructeur, on le rajoute après avec un set pour permettre un contrôle de correspondance entre surface et revêtement.
        Revêtement revêtement_standard = new Revêtement(9999);// le 9999 permet de tester avec le programme que Thomas avit créé quand on veut éviter de lire le fichier 
        this.revêtement=revêtement_standard;
        */
        this.liste_ouverture=new ArrayList<>();
    }
     
// on utilise celui la 
    public Mur(Coin debut, Coin fin, Etage étage_mur) {
        this.étage = étage_mur;
        this.debut = debut;
        this.fin = fin;
        this.id = IDManager.newId(this);
        this.liste_ouverture=new ArrayList<>();
        if (this.pièce1==null){
            this.étage.addOrphelin(this);    
        } 
    }
    public Mur() {  //Constructeur vide servant à faire des tests (p.ex.)
    }   

    // FUNCTIONS
    
    //TODO peut-être renommer cette méthode en distanceMurClique() pour respecter les conventions de casse. (casse : majuscule ou minuscule)
    /**
     * @param cliqueSouris : Coin
     * @param distMax : double
     * @return la distance entre un point et son projeté orthogonal sur un segment.
     */
    public double DistanceMurClique(Coin cliqueSouris, double distMax){
        boolean result=false;
        //System.out.println("Je suis dans classe distanceMur_clique");
        //System.out.println("this.getDebut() (Classe mur)"+this.getDebut());
        Double ligne = new Double(this.debut.getX(), this.debut.getY(),this.fin.getX(), this.fin.getY());
        double distanceClique_Mur = ligne.ptSegDist(cliqueSouris.getX(),cliqueSouris.getY()); 
        //"line.ptSegDist" renvoie la distance entre le clic et son projeté orthogonal sur le mur
        return distanceClique_Mur;
    }
    
    public double longueur(){ //appeler "<nom_mur>.longueur()" renvoie la longeueur du mur
        return sqrt(((this.getFin().getX()-this.getDebut().getX())*(this.getFin().getX()-this.getDebut().getX())+(this.getFin().getY()-this.getDebut().getY())*(this.getFin().getY()-this.getDebut().getY())));
    }
    
    public static double longueur(Coin d,Coin f){
        double L=sqrt(((f.getX()-d.getX())*(f.getX()-d.getX())+(f.getY()-d.getY())*(f.getY()-d.getY())));
        return L;   
    }
    
    //la méthode principale du surface
    public double surface(){ //appeler : mur.surface() renvoie la surface de l'objet mur
        double surface=0;
        surface = surface + this.longueur()*this.étage.getHauteur_etage();
        return surface;
    }
    //TODO controle si ouverture pour recalcul de surface soustraire les surfaces des ouvertures
    /* PAS TRES UTILE vu que tu as déjà le calcul de surface d'avant qui est nickel. A voir si on garde cette fonction ( si on en a besoin)
    public static double surface(Coin d, Coin f, Etage etage){
        double surface = longueur(d,f)*(etage.getHauteur_etage());
        return surface;
    }
    */
      public double prix(){  //appeler "mur.prix()" !! : il faut que le prix_unitaire du revêtement soit défini (ou bien, temporairement écrire revetment.setPrix_unitaire([nombre]) )
        double p = this.surface()*((this.getRevêtement()).getPrix_unitaire());
        return p;
    }
    public boolean contrôle(Revêtement r){
        boolean result=(r.getPourMur()).equals("1");
        return result;
    }
    public void add(Revêtement revêtement){
        if (this.contrôle(revêtement)==true){
            System.out.println("Le revêtement est applicable");
            this.revêtement=revêtement;
        }
        else{
            System.out.println("Le revêtement n'est pas applicable");
    }
}

    /**
     * merci de ne pas faire de changement substanciel dans la syntaxe des toStringSauvegarde()
     * //////////Attention : cette syntaxe est utiulisée dans IDManager.récupérerUnMur() !!!!
     * //////////Si on change la syntaxe de mur.toStringSauvegarde(), il faut changer la méthode susdite.
     * @return String
     */
    public static String syntaxeToString(){
        return "#Syntaxe  : \"Mur;id;idDuCoinDebut;idDuCoinFin;idDuRevêtement";
    }
    /**ceci est le toString() de sauvegarde.
    *MERCI DE NE PAS MODIFIER CETTE FONCTION sans me consulter
    * @return MurEnString : String
    */
    public String toStringSauvegarde() {
        //Syntaxe : "Mur;id;idCoinDebut;idCoinFin"
        return "Mur;" + this.id + ";" + debut.getId() + ";" + fin.getId() + ";" + this.revêtement.getId() ;
    }
    
    @Override
    public String toString() {
        //Syntaxe : "Mur;id;idDuCoinDebut;idDuCoinFin;idDeEtageDuMur;idDePièce1;idDePièce2;liste_ouverture
        String résultat = "Mur; id :" + this.id + "; coin1: " + debut.getId() + "; coin2: " + fin.getId() ;
            résultat += ";liste_ouverture=" + liste_ouverture ;
//        int idDePièce1;
//        int idDePièce2;
//        if (pièce1 == null){
//            idDePièce1 = 9999;
//        }else{idDePièce1=pièce1.getId();}
//        
//        if (pièce1 == null){
//            idDePièce2 = 9999;
//        } else {idDePièce2=pièce1.getId();}
//        String résultat = "Mur;" + id + ";" + debut.getId() + ";" + fin.getId() ;
//            résultat += ";" + étage.getId() + ";" + idDePièce1 + ";" + idDePièce2 + ";liste_ouverture=" + liste_ouverture ;
        return résultat;
    }
  //ATTENTION!! désormais, il est important que toString() reste ainsi, car IDManager.récupérerLesObjetsCréés() repose dessus!!

    


    //toString1() est une alternative plus lisible à toString()
    public String toString1() {
        return "Mur{" + "id=" + this.id + ", Coin debut=" + this.debut.toString() + ", Coin fin=" + this.fin.toString()
                + "étage_mur.getId()= " + this.étage.getId() + '}'; // + ", rev\u00eatement_mur=" + revêtement + 
    }

    /*  
    @Override
    public String toString2() {
        return "Mur: "+nom_mur+"{ debut=" + debut + ", fin=" + fin + '}';
    }
    */
    public void dessine(GraphicsContext context){
        this.debut.dessine(context);
        this.fin.dessine(context);
        context.setStroke(Color.BLACK);
        context.strokeLine(this.getDebut().getX(), this.getDebut().getY(), this.getFin().getX(), this.getFin().getY());
    }
    public void highlight(GraphicsContext context){
        System.out.println("HIGHLIGHT de la classe Mur");
        this.debut.dessine(context);
        this.fin.dessine(context);
        context.setStroke(Color.RED);
        context.strokeLine(this.getDebut().getX(), this.getDebut().getY(), this.getFin().getX(), this.getFin().getY());  
    }
    public boolean horizontal() {
        boolean result;
        if(this.getDebut().getY()-this.getFin().getY()==0){ // mur horizontal
            result=true;
        }
        else {
            result=false;
        }
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
    public Revêtement getRevêtement() {
        return revêtement;
    }
    public Etage getÉtage() {
        return étage;
    }
    public Pièce getPièce1() {
        return pièce1;
    }
    
    public String getNom_mur() {
        return nom_mur;
    }
    public ArrayList<Ouverture> getListe_ouverture() {
        return liste_ouverture;
    }
  
    // SET
    public void setDebut(Coin debut) {
        this.debut = debut;
    }
    public void setFin(Coin fin) {
        this.fin = fin;
    }
    public void setÉtage(Etage étage_mur) {
        this.étage = étage_mur;
    }
    public void setPièce1(Pièce pièce1) {
        this.étage.getListMurOrphelin().remove(this);
        //une fois la pièce initialisée, le mur n'est plus orphelin
        this.pièce1 = pièce1;
    }
    
    public void setNom_mur(String nom_mur) {
        this.nom_mur = nom_mur;
    }
    public void setRevêtement(Revêtement revêtement) {
        if(this.contrôle(revêtement)==true){
            this.revêtement = revêtement;
            revêtement.getListe_mur().add(this);
        }
        else {
            System.out.println("LE revêtement ne peut être appliqué sur le mur !!!!");
        }
        
    }
    public void setListe_ouverture(ArrayList<Ouverture> liste_ouverture){
        this.liste_ouverture = liste_ouverture;
    }
    public void addOuverture(Ouverture ouverture){
        this.getListe_ouverture().add(ouverture);
    }
    

    /////// TEST MAIN     
    public static void main(String [] args){   //un main pour tester longueur et surface
    //test 30/03/24 (thomas) (merci de ne pas y toucher sans vérifier qu'il fonctionne encore)
    //////////////LECTURE FICHIER. IL s'appelle Revêtement_test.txt
    /*
    System.out.println("Donnez le nom de votre fichier :");
    String nom_fichier = Lire.S();
    */
    Architecture_officielle batiment = new Architecture_officielle();
    ArrayList<String[]> listeRevêt = batiment.getDonnee_enregistree();
    //Création des coins pour faire le mur 
    Coin debut1 , fin1;
    debut1= new Coin(2,4);
    fin1 = new Coin(2,1);
    Etage etage1 = new Etage(5,batiment);
    Etage etage2 = new Etage(5,batiment);
    Mur mur = new Mur(debut1,fin1, etage1);
    Mur mur1 = new Mur(debut1,fin1, etage1);    
    /**
     * Deux manière de mettre le revêtement, soit celui automatique par défault ou en lisant le fichier et en rentrant manuellement le numéros de l'identifiant correspondant
     */
    //Revêtement test=new Revêtement(9999);  //id=9999 est un raccourci pour mettre définir prix_unitaire à 5.55 et c'est tout (pas de lecture de donnee enregistree
    System.out.println("numéros revêtement : ");
    int id = Lire.i();
    Revêtement revêtement=new Revêtement(id);
    mur.setRevêtement(revêtement);
    while (mur.revêtement==null){
        System.out.println("numéros revêtement : ");
        id = Lire.i();
        revêtement=new Revêtement(id);
        mur.setRevêtement(revêtement);
    }
    System.out.println("Le revêtement est appliqué");
    System.out.println("mur.getRevêtement"+mur.toString());
    /*  
    System.out.println("Le prix du revêtement est : "+test.getPrix_unitaire());  //test.getPrix_unitaire ne fonctionne pas sans la lgne ci-dessus car le fichier donnee_enregristree n'est pas encore lu
    System.out.println("Length = " + mur.longueur());
    System.out.println("Hauteur du mur : "+etage1.getHauteur_etage());
    System.out.println("Surface is " + mur.surface());
    System.out.println("Price is " + mur.prix());
    System.out.println("contrôle result :"+ mur.contrôle(test)); // renvoie true si le revêtement est applicable, false sinon
    
    Fenêtre fen1 = new Fenêtre(2,3,'N',mur);
    Fenêtre fen2 = new Fenêtre(2,2,'N',mur);
    Fenêtre fen3 = new Fenêtre(2,2,'N',mur);
    Porte porte1 = new Porte(2,2,'N',mur);
    fen1.setMur2(mur1);
    fen2.setMur2(mur1);
    porte1.setMur2(mur1);
    
    Porte porte= new Porte(2,2,'E',mur,mur1);
    System.out.println("mur 1 : "+porte.getMur1());
    System.out.println("mur 2 :"+porte.getMur2());

    System.out.println(" l'ouverture appartient au mur ? "+fen.appartenance(mur));

        System.out.println(mur);


    System.out.println("fen.getMur1() : "+fen.getMur1());

    System.out.println("taille de la liste_ouverture :"+mur.getListe_ouverture().size());
    System.out.println("mur.getListe_ouverture() : ");
    for (int i=0;i<mur.getListe_ouverture().size();i++){
        System.out.println("i = "+i+" =>"+mur.getListe_ouverture().get(i));
    }
    System.out.println("mur1.getListe_ouverture() : ");
    for (int i=0;i<mur1.getListe_ouverture().size();i++){
        System.out.println("i = "+i+" =>"+mur1.getListe_ouverture().get(i));
    }
    System.out.println("revêtement.getListe_mur() : ");
    for (int i=0;i<revêtement.getListe_mur().size();i++){
        System.out.println("i ="+i+" => "+revêtement.getListe_mur().get(i));
    }
    */
    }
}
