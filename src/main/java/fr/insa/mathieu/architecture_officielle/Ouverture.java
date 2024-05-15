/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;

/**
 *
 * @author stard
 */
public abstract class Ouverture {
    //ATTENTION! si on cahnge quoique ce soit dans les attributs, il faut changer eet faire foctionnner:
    // - la toString(), la syntaxeToString() , et IDManager.récupérerOuvertures()
    private double ori_x;
    private double ori_y;
    private char orientation;
    private double longueur;
    private Mur mur1 = null;
    private Mur mur2 = null; //ainsi, une ouverture peut n'avoir (que temporairement) qu'un seul mur

    //CONSTRUCTOR
    /*
    public Ouverture(double ori_x, double ori_y, char orientation, double longueur, Mur mur1, Mur mur2) {
        this.ori_x = ori_x;
        this.ori_y = ori_y;
        this.orientation = orientation;        
        this.longueur=longueur;
        if (this.appartenance(mur1)==true){
            this.mur1=mur1;
            mur1.getListe_ouverture().add(this);
        }
        else {
            this.mur1=null;
            System.out.println("L'ouverture demandée ne peut pas appartenir au mur");
            
        }
        if (this.appartenance(mur2)==true){
            this.mur2=mur2;
            mur2.getListe_ouverture().add(this);
        }
        else {
            this.mur2=null;
            System.out.println("L'ouverture demandée ne peut pas appartenir au mur");
        }
        System.out.println("L'ouverture doit être supprimée");
        
    }
*/
    public Ouverture(double ori_x, double ori_y, char orientation, double longueur, Mur mur) {
        this.ori_x = ori_x;
        this.ori_y = ori_y;
        this.orientation = orientation;
        this.longueur = longueur;
        if (this.appartenance(mur)==true){
            this.mur1=mur;
            mur.getListe_ouverture().add(this);
        }
        else {
            //System.out.println("L'ouverture doit être supprimée");//Juste l'ouverture est ignorée car n'apparait pas dans les liste des ouvertures des murs
        }
    }
    
    
    //FUNCTIONS
    public boolean appartenance(Mur mur){
    boolean result=false;
    double min_x = Math.min(mur.getDebut().getX(),mur.getFin().getX());
    double max_x = Math.max(mur.getDebut().getX(),mur.getFin().getX());
    double min_y = Math.min(mur.getDebut().getY(),mur.getFin().getY());
    double max_y = Math.max(mur.getDebut().getY(),mur.getFin().getY());
        //if (this.getEtage().getId()==mur.getÉtage().getId()){ /// TO DO A changer "niveau" et "hauteur etage"
            if ( (this.getOri_x()>min_x && this.getOri_x()<max_x && this.getOri_y()==mur.getDebut().getY())||(this.getOri_y()>min_y && this.getOri_y()<max_y && this.getOri_x()==mur.getDebut().getX())){
                if ((mur.getDebut().getX())-(mur.getFin().getX())==0){
                    if(this.getOrientation()=='N'||this.getOrientation()=='S'){
                        switch(this.getOrientation()){
                            case 'N' -> {
                                if (this.getOri_y()+this.longueur<max_y){
                                    //System.out.println("l'ouverture est comprise dans le mur");
                                    result=true;
                                }
                                else {
                                    //System.out.println("l'ouverture dépasse le mur (NORD)");
                                    result=false;
                                }
                            }
                            case 'S' -> {
                                if (this.getOri_y()-this.longueur>min_y){
                                    //System.out.println("longueur ouverture :"+this.longueur);
                                    //System.out.println("l'ouverture est comprise dans le mur");
                                    result=true;
                                }
                                else {
                                    //System.out.println("l'ouverture dépasse le mur (SUD)");
                                    result=false;
                                }
                            }
                        }
                    }
                    else {
                        //System.out.println("L'orientation est mauvaise");
                        result=false;
                    }
                }
                if ((mur.getDebut().getY())-(mur.getFin().getY())==0){
                    if (this.getOrientation()=='O'||this.getOrientation()=='E'){
                        switch(this.getOrientation()){
                            case 'E' -> {
                                if (this.getOri_x()+this.longueur<max_x){
                                    //System.out.println("l'ouverture est comprise dans le mur");
                                    result=true;
                                }
                                else {
                                    //System.out.println("l'ouverture dépasse le mur");
                                    result=false;
                                }
                            }
                            case 'O' -> {
                                if (this.getOri_x()-this.longueur>min_x){
                                    //System.out.println("l'ouverture est comprise dans le mur");
                                    result=true;
                                }
                                else {
                                    //System.out.println("l'ouverture dépasse le mur");
                                    result=false;
                                }
                            }
                        }
                    }
                    else {
                        //System.out.println("L'orientation est mauvaise");
                        result=false;
                    }
                }
            }
            else{
                //System.out.println("L'origine de ta porte ne se trouve pas sur un mur");
                result=false;
            }
        //}
        /*
        else {
            result=false;
        }
        */
    return result;
    }

    public static String syntaxeToString(){
        return "#Syntaxe : \"Ouverture;ori_x;ori_y;orientation(char);longueur;idDuMur1;idDuMur2 \"";
                //merci de ne pas faire de changement substanciel dans la syntaxe des toString()

    }
    @Override
    public String toString() {
        int idMur2;
        if (mur2 == null){
            idMur2 = 9999; //ne fonctionnera pas si on crée 9999 murs
            //Attention!! Ne pas changer idMur2 = 9999 sans changer cette particularité dans IDManager.récupérerOuvertures()
        }
        else{
            idMur2 = mur2.getId();
        }
        return "Ouverture" + ";" + ori_x + ";" + ori_y + ";" + orientation + ";" + longueur + ";" + mur1.getId() + ";" + idMur2;
    }//on distinguera les portes des fénêtres grâce à leur longeur
    
    
    
    // GET
    public double getOri_x() {
        return ori_x;
    }
    public double getOri_y() {
        return ori_y;
    }
    public char getOrientation() {
        return orientation;
    }
    public double getLongueur() {
        return longueur;
    }
    public Mur getMur1() {
        return mur1;
    }

    public Mur getMur2() {
        return mur2;
    }
    
    //SET

    public void setMur2(Mur mur2) {
        if (this.appartenance(mur2)==true){
            this.mur2 = mur2;
            mur2.getListe_ouverture().add(this);
        }
        else {
            //System.out.println("IMPOSSIBLE d'ajouter ce mur à l'ouverture");
            this.mur2=null;
        }
    }
    
    
    
    
    
    
}
