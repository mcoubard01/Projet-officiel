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
    private double ori_x;
    private double ori_y;
    private char orientation;
    private Etage etage;
    private double longueur;

    //CONSTRUCTOR
    public Ouverture(double ori_x, double ori_y, char orientation, Etage etage, double longueur) {

    //public Ouverture(double ori_x, double ori_y, char orientation, Etage etage) {
        this.ori_x = ori_x;
        this.ori_y = ori_y;
        this.orientation = orientation;
        this.etage = etage;
        this.longueur=longueur;
    }
    
    
    //FONCTION
    public boolean appartenance(Mur mur){
    boolean result=false;
    double min_x = Math.min(mur.getDebut().getX(),mur.getFin().getX());
    double max_x = Math.max(mur.getDebut().getX(),mur.getFin().getX());
    double min_y = Math.min(mur.getDebut().getY(),mur.getFin().getY());
    double max_y = Math.max(mur.getDebut().getY(),mur.getFin().getY());
        if (this.getEtage().getHauteur_etage()==mur.getÉtage().getHauteur_etage()){ /// TO DO A changer "niveau" et "hauteur etage"
            if ( (this.getOri_x()>min_x && this.getOri_x()<max_x && this.getOri_y()==mur.getDebut().getY())||(this.getOri_y()>min_y && this.getOri_y()<max_y && this.getOri_x()==mur.getDebut().getX())){
                if ((mur.getDebut().getX())-(mur.getFin().getX())==0){
                    if(this.getOrientation()=='N'||this.getOrientation()=='S'){
                        switch(this.getOrientation()){
                            case 'N' -> {
                                if (this.getOri_y()+this.longueur<max_y){
                                    System.out.println("la porte est comprise dans le mur");
                                    result=true;
                                }
                                else {
                                    System.out.println("la porte dépasse le mur (NORD)");
                                    result=false;
                                }
                            }
                            case 'S' -> {
                                if (this.getOri_y()-this.longueur>min_y){
                                    System.out.println("longueur ouverture :"+this.longueur);
                                    System.out.println("la porte est comprise dans le mur");
                                    result=true;
                                }
                                else {
                                    System.out.println("la porte dépasse le mur (SUD)");
                                    result=false;
                                }
                            }
                        }
                    }
                    else {
                        System.out.println("L'orientation est mauvaise");
                        result=false;
                    }
                }
                if ((mur.getDebut().getY())-(mur.getFin().getY())==0){
                    if (this.getOrientation()=='O'||this.getOrientation()=='E'){
                        switch(this.getOrientation()){
                            case 'E' -> {
                                if (this.getOri_x()+this.longueur<max_x){
                                    System.out.println("la porte est comprise dans le mur");
                                    result=true;
                                }
                                else {
                                    System.out.println("la porte dépasse le mur");
                                    result=false;
                                }
                            }
                            case 'O' -> {
                                if (this.getOri_x()-this.longueur>min_x){
                                    System.out.println("la porte est comprise dans le mur");
                                    result=true;
                                }
                                else {
                                    System.out.println("la porte dépasse le mur");
                                    result=false;
                                }
                            }
                        }
                    }
                    else {
                        System.out.println("L'orientation est mauvaise");
                        result=false;
                    }
                }
            }
            else{
                System.out.println("L'origine de ta porte ne se trouve pas sur un mur");
                result=false;
            }
        }
        else {
            result=false;
        }
    return result;
    }
    
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
    public Etage getEtage() {
        return etage;
    }

    public double getLongueur() {
        return longueur;
    }
    
    
}
