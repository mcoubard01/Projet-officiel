/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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

    private Etage étage;   
    private Appartement appartement;
    private String nom_pièce; // l'utilisateur peut renommer ses pièces
    private int id;
    private List<Mur> liste_mur;
    private Sol sol;
    private Plafond plafond;


    //CONSTRUCTOR
    
    //Constructeur principal
    public Pièce(Etage étage, Appartement appartement, String nom_pièce, List<Mur> liste_mur) {
        this.étage = étage;
        this.appartement = appartement;
        this.nom_pièce = nom_pièce;
        this.liste_mur = liste_mur;
        this.sol = new Sol(this);
        this.plafond = new Plafond(this);
        this.id = IDManager.newId(this);
    }
      
  public Pièce(String nom_pièce, Etage étage, Appartement appartement){
      this.liste_mur =new ArrayList<>();
    this.étage = étage;
    this.appartement = appartement;
    this.sol = new Sol(this); 
    this.plafond = new Plafond(this);
     this.id = IDManager.newId(this);
    this.nom_pièce= nom_pièce;
  }
  /**
   * crée automaitquement un appartement, une liste vide de murs, un sol et un plafond
   * @param nom_pièce
   * @param étage 
   */
  public Pièce(String nom_pièce, Etage étage){ 
      this.liste_mur =new ArrayList<>();
    this.étage = étage;
    this.appartement = new Appartement(étage);
    this.sol = new Sol(this); //TODO à regarder car pas sûr que ça marche
    this.plafond = new Plafond(this);
     this.id = IDManager.newId(this);
    this.nom_pièce= nom_pièce;
    

    }

    /**
     *
     * @param étage
     */
    public Pièce(Etage étage){// permet de crféér une pièce a partir d'une liste de coin de mur et d'un plafond et d'un sol 
    this.liste_mur =new ArrayList<>();   
    this.étage = étage;
    this.étage.getListPièceOrpheline().add(this);
    //this.appartement = new Appartement(étage);
    String nom = "pièce n°" + this.id;
    this.sol = new Sol(this); //TODO à regarder car pas sûr que ça marche
    this.plafond = new Plafond(this);
    this.nom_pièce = nom;
    this.id = IDManager.newId(this);
    }

  /**
   * CONSTRUCTEUR très restrictif : lors de la construction de la pièce on créé automatiquement un appartement.
   * @param liste_mur
   * @param étage 
   */
  public Pièce(ArrayList<Mur> liste_mur, Etage étage){// permet de crféér une pièce a partir d'une liste de coin de mur et d'un plafond et d'un sol 
    this.étage = étage;
    this.appartement = new Appartement(étage); 
    this.sol = new Sol(this); //TODO à regarder car pas sûr que ça marche
    this.plafond = new Plafond(this);
    this.id = IDManager.newId(this);
    this.liste_mur =new ArrayList<>();    
    String nom = "pièce n°" + this.id;
    this.nom_pièce = nom;
    }
  
  public Pièce(ArrayList<Mur> liste_mur){ //utile pour créer une pièce plus facilement
      this(liste_mur,liste_mur.get(1).getÉtage());
  }
  
  /**
   * Créée un automtiquement un nouveau sol et plafond
   * @param mur1
   * @param mur2
   * @param mur3
   * @param mur4
   * @param étage
   * @param appartement 
   */
  public Pièce(Mur mur1,Mur mur2,Mur mur3,Mur mur4,Etage étage,Appartement appartement){
      this.étage = étage;
    this.appartement = appartement;
    this.sol = new Sol(this); //TODO à regarder car pas sûr que ça marche
    this.plafond = new Plafond(this);
    this.liste_mur =new ArrayList<>();   
    liste_mur.add(mur1);
    liste_mur.add(mur2);
    liste_mur.add(mur3);
    liste_mur.add(mur4);
    String nom = "pièce n°" + this.id;
    this.nom_pièce = nom;
    this.id = IDManager.newId(this);
  }
    /**
     * Créé automaitquement un nouvel appartement, et nouveau Sol et plafond
     * @param mur1
     * @param mur2
     * @param mur3
     * @param mur4
     * @param étage 
     */
    public Pièce(Mur mur1,Mur mur2,Mur mur3,Mur mur4,Etage étage){// permet de crféér une pièce a partir d'une liste de coin de mur et d'un plafond et d'un sol 
        this.étage = étage;
        this.sol = new Sol(this); //TODO à regarder car pas sûr que ça marche
        this.plafond = new Plafond(this);
        this.liste_mur =new ArrayList<>();   
        liste_mur.add(mur1);
        liste_mur.add(mur2);
        liste_mur.add(mur3);
        liste_mur.add(mur4);
        String nom = "pièce n°" + this.id;
        this.nom_pièce = nom;
        System.out.println("cration de la pièce " + id + ", sans appartement");
        this.id = IDManager.newId(this);
        
    }
    public Pièce(Mur mur1,Mur mur2,Mur mur3,Mur mur4){ //utile pour créer plus facilement
        
        this.sol = new Sol(this); //TODO à regarder car pas sûr que ça marche
        this.plafond = new Plafond(this);
        this.liste_mur =new ArrayList<>();   
        liste_mur.add(mur1);
        liste_mur.add(mur2);
        liste_mur.add(mur3);
        liste_mur.add(mur4);
        String nom = "pièce n°" + this.id;
        this.nom_pièce = nom;
        this.id = IDManager.newId(this);
        System.out.println("cration de la pièce " + id + ", sans appart ni étage");
    }
    public Pièce(){
        
    }
    
 // FUNCTION
    /*
    public void add(Mur m){//permet d'ajouter un mur dans la liste 
        //Attention! ajoute à la pièce1 du mur par défaut!!
        if (m.getPièce1()!= this){     
            this.liste_mur.add(m);
            m.setPièce1 (this);
        }
    }
    */
    /**
     * la fonction pieceSelect renvoie true si le clique souris se trouve dans la zone pour selectionner la pièce. 
     * TODO dessiner un petit carré rouge à l'interieur si la pièce est selectionnée
     * @param coinCliqué
     * @return 
     */
     public void highlight(GraphicsContext context){
        System.out.println("HIGHLIGHT de la classe Pièce");
        for (Mur mur : this.liste_mur){
            mur.highlight(context);
        }
    }
    public Mur[] longMaxMin(){
        Mur[] listeMur = new Mur[2];
        double longueurMax=0;
        double longueurMin=Double.POSITIVE_INFINITY; //par récurrence, longueurMin sera réduite dans la boucle plus bas.
        Coin positionCentrale;

        for (Mur mur : this.getListe_mur()){
            if (mur.longueur()>longueurMax){
                longueurMax=mur.longueur();
                listeMur[0]=mur;
            }
            else{
                
            }
            if(mur.longueur()<longueurMin){
                longueurMin=mur.longueur();
                listeMur[1]=mur;
            }
        }
        return listeMur;
    }
    public Coin positionCentrale(){
        Coin positionCentrale = new Coin();
        Mur[] listeMur = this.longMaxMin();
        
        if (listeMur[0].horizontal()){
            System.out.println("Le mur le plus grand est horizontal");
            /**
             * Position du point Central de la pièce => marche BIEN
             */
            if (listeMur[0].getDebut().getX()<listeMur[0].getFin().getX()){

                positionCentrale.setX(listeMur[0].getDebut().getX()+listeMur[0].longueur()/2);

            }
            else{
                positionCentrale.setX(listeMur[0].getFin().getX()+listeMur[0].longueur()/2);
            }

            if (listeMur[1].getDebut().getY()<listeMur[1].getFin().getY()){ //"Si le coinDebut se trouve au Sud du coinFin"
                positionCentrale.setY(listeMur[1].getDebut().getY()+listeMur[1].longueur()/2);

            }
            else{
                positionCentrale.setY(listeMur[1].getFin().getY()+listeMur[1].longueur()/2);
            }
        }
        else {
            //System.out.println("Le mur le plus grand est Vertical");
            if (listeMur[0].getDebut().getY()<listeMur[0].getFin().getY()){
                positionCentrale.setY(listeMur[0].getDebut().getY()+listeMur[0].longueur()/2);
            }
            else{
                positionCentrale.setY(listeMur[0].getFin().getY()+listeMur[0].longueur()/2);
            }
            if (listeMur[1].getDebut().getX()<listeMur[1].getFin().getX()){
                positionCentrale.setX(listeMur[1].getDebut().getX()+listeMur[1].longueur()/2);
            }
            else{
                positionCentrale.setX(listeMur[1].getFin().getX()+listeMur[1].longueur()/2);
            }
        }
        return positionCentrale;
    }
    
    public boolean pieceSelect(Coin coinCliqué){
        boolean result;
        
        Coin positionCentrale = this.positionCentrale();
        Mur[] listeMur = this.longMaxMin();
        
        if (listeMur[0].horizontal()){

            System.out.println("Le coin Centrale est : "+positionCentrale.toString());
            double voisinSUPX = positionCentrale.getX()+0.2*listeMur[0].longueur();
            double voisinINFX = positionCentrale.getX()-0.2*listeMur[0].longueur();
            double voisinSUPY = positionCentrale.getY()+0.2*listeMur[1].longueur();
            double voisinINFY = positionCentrale.getY()-0.2*listeMur[1].longueur();

            System.out.println("voisinage en x lorsque longueur max horizontal : ["+voisinINFX+","+voisinSUPX+"]");
            System.out.println("voisinage en y lorsque longueur max horizontal : ["+voisinINFY+","+voisinSUPY+"]");

            if(coinCliqué.getX()<voisinSUPX && coinCliqué.getX()>voisinINFX){
                //System.out.println("Le clic se situe dans le bon voisinage du point central en x");
                if(coinCliqué.getY()<voisinSUPY && coinCliqué.getY()>voisinINFY){
                    result=true;
                    //System.out.println("Le clique souris se situe dans l'espace prédéfini pour slectionner une pièce");
                }
                else{
                    result=false;
                }
            } else {
                result=false;
            }
        }
        else {
            System.out.println("Le coin Centrale est : "+positionCentrale.toString());
            double voisinSUPX = positionCentrale.getX()+0.2*listeMur[0].longueur();
            double voisinINFX = positionCentrale.getX()-0.2*listeMur[0].longueur();
            double voisinSUPY = positionCentrale.getY()+0.2*listeMur[1].longueur();
            double voisinINFY = positionCentrale.getY()-0.2*listeMur[1].longueur();
            System.out.println("voisinage en x lorsque longueur max horizontal : ["+voisinINFX+","+voisinSUPX+"]");
            System.out.println("voisinage en y lorsque longueur max horizontal : ["+voisinINFY+","+voisinSUPY+"]");
            
            if(voisinINFX<coinCliqué.getX() && coinCliqué.getX()<voisinSUPX){
                if(coinCliqué.getY()<voisinSUPY && coinCliqué.getY()>voisinINFY){
                    result=true;
                    //System.out.println("Le clique souris se situe dans l'espace prédéfini pour slectionner une pièce");
                }
                else{
                    result=false;
                }
            } else {
                result=false;
            }
        }

        return result;
    } 
    public void add(Mur m){// permet d'ajouter un mur dans la liste de pièce avec l'appel <Pièce>.add(Mur mur);
            this.liste_mur.add(m);
            m.setPièce1(this);
    }

    public ArrayList<Mur> consécutif(){ //TODO cette fonction doit dire si la pièce est fermée ou 
        ArrayList<Mur> mur_consécutif = new ArrayList();
        Mur référence = this.getListe_mur().get(0);
        System.out.println ("donnée du mur de référence : "+référence.toString());
        int i=1;
        /*boolean test1 = (référence.getDebut().getX()!=this.getListe_mur().get(i).getDebut().getX());
        boolean test2 = (référence.getDebut().getY()!=this.getListe_mur().get(i).getDebut().getY());
        //test1&2 permettent de tester si coinDebut du mur référence est le coinDebut du mur1.
        
        boolean test3 = (référence.getDebut().getX()!=this.getListe_mur().get(i).getFin().getX());
        boolean test4 = (référence.getDebut().getY()!=this.getListe_mur().get(i).getFin().getY());
        test3&4 permettent de tester si coinDebut du mur référence est le coinFin du mur1.

        
        boolean test5 = (référence.getFin().getX()!=this.getListe_mur().get(i).getDebut().getX());
        boolean test6 = (référence.getFin().getY()!=this.getListe_mur().get(i).getDebut().getY());
        test5&6 permettent de tester si coinFin du mur référence est le coinDebut du mur1.

        
        boolean test7 = (référence.getFin().getX()!=this.getListe_mur().get(i).getFin().getX());
        boolean test8 = (référence.getFin().getY()!=this.getListe_mur().get(i).getFin().getY());
        test7&8 permettent de tester si coinFin du mur référence est le coinFin du mur1.

        
        while ((test1&&test2)&&(test3&&test4)&&(test5&&test6)&&(test7&&test8))
            */
        while (((référence.getDebut().getX()!=this.getListe_mur().get(i).getDebut().getX())&&(référence.getDebut().getY()!=this.getListe_mur().get(i).getDebut().getY()))&&((référence.getDebut().getX()!=this.getListe_mur().get(i).getFin().getX())&&(référence.getDebut().getY()!=this.getListe_mur().get(i).getFin().getY()))&&((référence.getFin().getX()!=this.getListe_mur().get(i).getDebut().getX())&&(référence.getFin().getY()!=this.getListe_mur().get(i).getDebut().getY()))&&((référence.getFin().getX()!=this.getListe_mur().get(i).getFin().getX())&&(référence.getFin().getY()!=this.getListe_mur().get(i).getFin().getY()))){
            System.out.println("incrémentation : "+i);
            i=i+1;
        }
        mur_consécutif.add(référence);
        mur_consécutif.add(this.getListe_mur().get(i));
        return mur_consécutif;
    }
    
    public double surface(){
        //double surface = this.consécutif().get(0).longueur()*this.consécutif().get(1).longueur();
        double surface = 0;
        double surfaceMur = 0;
        for (Mur mur:this.liste_mur){
            surfaceMur = surfaceMur+mur.surface();
        }
        surface = 2*this.sol.surface()+surfaceMur;
        return surface;
    }
   // TODO tester si ca marche dèes que le la surface des sol et plafond est capable de se faire automatiquement a partir des liste de coins 
   public double prix() {// méthode permettant de calculer le prix total d'une pièce 
        double prix=0;
        for(Mur mur:this.liste_mur){
            prix = prix + mur.prix();
        }
        prix = prix + this.plafond.prix();
        prix = prix + this.sol.prix();
        
        return prix;
    }
   
   //toString1() sert à écrire autre chose que le toString() principal
    public String toString1() {
        //Affichage des infos du mur
        String res = "Pièce: "+ nom_pièce+" {\n";
        for (int i=0; i<this.liste_mur.size();i++){
            res=res+indente(this.liste_mur.get(i).toString()," ")+ "\n";
        }
        /*Affichage des infos des coins
        String ess =""; // a modier plus tard c ar cela ne sert pas c'était juste opour tester si on pouvait rajouetz des choses
        for (int i=0; i<this.coins.size();i++){
            ess=ess+indente(this.coins.get(i).toString()," ")+ "\n";
        }
        */
        //Affichage des informations du sol 
        String essai ="";
        if (this.sol != null) {
        essai = indente(this.sol.toString(), " ") + "\n";
    }
        //Affichage des informations du plafond
        String essais ="";
        if (this.plafond != null) {
        essais = indente(this.plafond.toString(), " ") + "\n";
    }
          return res+essai+essais+")";
    }

    /**
     * merci de ne pas faire de changement substanciel dans la syntaxe des toStringSauvegarde()
     * //////////Attention : cette syntaxe est utilisée dans IDManager.récupérerUnePièce() !!!!
     * //////////Si on change la syntaxe de pièce.toString(), il faut changer la méthode susdite.
     * @return String
     */
    public static String syntaxeToString(){
        return "#Syntaxe : \"Pièce;id;nom_pièce;liste_mur(identifiants);revêtementDuSol;revêtementDuPlafond\"";
    }
    /**ceci est le toString() de sauvegarde.
    *MERCI DE NE PAS MODIFIER CETTE FONCTION sans me consulter
    * @return PièceEnString : String
    */
    public String toStringSauvegarde() {
        //#Syntaxe : \"Pièce;id;nom_pièce;liste_mur(identifiants);revêtementDuSol;revêtementDuPlafond\"
        ArrayList<Integer> listeDesIdDesMurs = new ArrayList<>();
        for (int i = 0; i < liste_mur.size() ; i++){
            listeDesIdDesMurs.add(liste_mur.get(i).getId());
            //créée une liste des identifiants des murs qui forment la pièce
        }
        return "Pièce;" + id + ";" + nom_pièce  + ";liste_mur=" + listeDesIdDesMurs + ";" + sol.toString() + ";" + plafond.toString();
    }
    
    @Override
    public String toString() {
        //Syntaxe : voir la méthode syntxeToString()
        ArrayList<Integer> listeDesIdDesMurs = new ArrayList<>();
        for (int i = 0; i < liste_mur.size() ; i++){
            listeDesIdDesMurs.add(liste_mur.get(i).getId());
            //créée une liste des identifiants des murs qui forment la pièce
        }
        return "Pièce;" + id + ";" + nom_pièce  + ";liste_mur=" + listeDesIdDesMurs + ";" + sol.toString() + ";" + plafond.toString();
    }
    
    //inutile désormais 
    public static String indente (String toIndente, String prefix){// meme machin que dans la premièrer vidéo du prof. 
    return prefix +toIndente.replaceAll("\n","\n"+ prefix);
}
    public void dessine(GraphicsContext context){
        for (Mur mur : this.liste_mur){
            mur.dessine(context);
        }
    }
    
    //GET
    public Appartement getAppartement() {
        return appartement;
    }
    public List<Mur> getListe_mur() { // Pour que l'IDManager fonctionne
        return liste_mur;
    }

    public String getNom_pièce() {
        return nom_pièce;
    }

    public int getId() {
        return id;
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
    public void setÉtage(Etage étage) {
        this.étage = étage;
    }
    

    
    //MAIN
    public static void main (String[] args){
        Architecture_officielle batiment = new Architecture_officielle();
        Etage e1 = new Etage(2,batiment);
        
        Coin c1 =new Coin(1,2);
        Coin c2 =new Coin(1,5);
        Mur m1= new Mur(c1,c2,e1);
        
        Coin c3 =new Coin(1,5); //supression de " Coin c3=new Coin("c3",1,5) "
        Coin c4 =new Coin(4,5);
        Mur m2= new Mur(c3,c4,e1); 
        
        Coin c5 =new Coin(4,5);
        Coin c6 =new Coin(4,2);
        Mur m3= new Mur(c5,c6,e1);
        
        Coin c7 =new Coin(4,2);
        Coin c8 =new Coin(1,2);
        Mur m4= new Mur(c7,c8,e1);
        
        Pièce pièce1= new Pièce(e1);
        pièce1.add(m1);
        pièce1.add(m2);
        pièce1.add(m3);
        pièce1.add(m4);
        
        System.out.println("surface de la putin de pièce : "+pièce1.surface());
    }

    Etage getEtage() {
        return this.étage;
    }

}
/* PARTIE OSCAR
//ATTRIBUT SUPPLEMENTAIRE
private List<Coin> coins;

//CONSTRUCTOR
 public Pièce(String nom_pièce){
    this.liste_mur =new ArrayList<>();
    this.coins= new ArrayList<>();
    this.sol = new Sol(this.coins);
    this.plafond = new Plafond(this.coins);
    this.nom_pièce= nom_pièce;
}

//FUNCTION
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

//TEST MAIN
public static void  piècetest(){
    Etage e1 = new Etage(2);
    Coin c1 =new Coin("c1",1,2);
    Coin c2 =new Coin("c2",1,6);
    Coin c3 =new Coin("c3",5,6);
    Coin c4 =new Coin("c4",5,2);
    Coin c5 =new Coin("c5",7,2);
    Coin c6 =new Coin("c6",7,6);
    
//l'attribut "nom_mur" sera supprimé éventuellement
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

*/