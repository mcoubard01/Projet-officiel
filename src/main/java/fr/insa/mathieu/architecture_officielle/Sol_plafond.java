
package fr.insa.mathieu.architecture_officielle;
/**
 *
 * @author oscar
 */
//public  abstract class sol_plafond {  //ça marche même si ce n'est pas une majuscule?// pensez a enlever abstract si on veut tester avec le main
  public abstract class Sol_plafond{  
    private String id;
    private Pièce pièce;
    private Revêtement revêtement;
    //TODO à remettre Revêtement standard = new Revêtement(9999);//Revêtement standard
    
    
    //CONSTRUCTOR

    public Sol_plafond(){//CONSTRUCTEUR test pour aller plus vite lors des essais   
    }
    public Sol_plafond(Pièce pièce){
        this.revêtement = Architecture_officielle.listeRevêtement.get(0); //par défaut or il faut réussir à faire un lien entre le batiment et le sol plafond pour dire que le revêtement 1 est le revêtement standard
        this.pièce=pièce;
    }
    
    //FUNCTIONS
    public double surface (){
        Mur[] longMinMax = this.pièce.longMaxMin();
        double surface = longMinMax[0].longueur()*longMinMax[1].longueur();
        return  surface;
    }
    public double prix (){
        double prix=0;//initialisation du prix, lorsque c'est égale à 0 c'est qu'il n'a pas pu calculer le prix
        if (this.getRevêtement()==null){
            throw new Error("le prix ne peut être calculer car nous n'avons pas de revêtement adapté à la surface");
        }
        else {
            prix= this.surface()*this.revêtement.getPrix_unitaire();
        }
        return prix;
    }
    abstract boolean contrôle(Revêtement revêtement);

    public static String syntaxeToString(){
        return "#Syntaxe : \"idDuRevêtement\"";
    }
    @Override
    public String toString() {
        return "idDuRevêtement"+this.revêtement.getId();
    }
    
    
    
    // GET
    public String getId() {
        return id;
    }
    public Revêtement getRevêtement() {
        return revêtement;
    }
    public Pièce getPièce() {
        return pièce;
    }

    
    // SET
    public void setId(String id) {
        this.id = id;
    }
    public void setRevêtement(Revêtement revêtement) {
        if (this.contrôle(revêtement)==true){
            this.revêtement=revêtement;
            revêtement.getListe_sol_plafond().add(this);
        }
        else {
            System.out.println("nous n'ajoutons pas le sol_plafond à la liste des durfaces pour ce revêtement !!!");
        }
    }
}