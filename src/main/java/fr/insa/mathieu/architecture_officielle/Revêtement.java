/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;

import java.util.ArrayList;
import java.util.Arrays;


/**
 *
 * @author stard
 * L'OBJECTIF ICI EST DE RENSEIGNER LES INFORMATIONS LIEES AU REVËTEMENT
 * IL DOIT REUSSIR A REPRENDRE LES DONNEES DU TABLEAU FAIT DANS LE MAIN. COMMENT ?
 */
public class Revêtement extends Architecture_officielle {
    private int id;
    private String désignation;
    private String pourMur;
    private String pourSol;
    private String pourPlafond;
    private double prix_unitaire;
    
    // id correspond à l'information de la première colonne de notre tableau donnée_ligne.
    // L'id du revêtement correspond = numéro de ligne
    // ex : id = 1 nous donne les informations pour la ligne 1
    // ex : id = 3 nous donne les informations pour la ligne 3
    
    // CONSTRUCTOR 
    public Revêtement(int id) {
        int a = id -1;
        this.id = a;
        // A CHANGER pour récupérer les bonnes données 
        // LISTE DES INDICES de l'arraylist donnee_enregistrée : 
        // 0 : numéros d'identifiant
        // 1 : désignation 
        // 2 : aplication sur le mur
        // 3 : application sur le sol
        // 4 : application sur le plafond
        // 5 : prix unitaire que je converti en double grâce à la fonction "todouble"
        
        if (id==9999){
            this.prix_unitaire = 5.55;
        }  //cette propriété permet de faire des tests dans les classes individuelles : 
        //en effet, dans celles ci, donnee_enregistree n'ets pas appelée et on ne peut pas appeler un revêtement (voir Mur, 30/03/24,thomas)
        else{
        String[] info= donnee_enregistree.get(a);
        this.désignation = info[1];
        this.pourMur = info[2];
        this.pourSol = info[3];
        this.pourPlafond = info[4];
        this.prix_unitaire = todouble(info[5]);
        }
        
        
    }
    // FONCTION 
   // TO DO fonction pour lister les revêtements selon l'application sur tel ou tel surface
    public static double todouble(String aconvertir){
        Double D=Double.valueOf(aconvertir);
	double finalvalue=D.doubleValue();
        return finalvalue;
    }
    public static ArrayList<String[]> rev_mur(){
        ArrayList<String[]> rev_mur = new ArrayList<>();
        for (int k =0;k<=donnee_enregistree.size()-1;k++){
            String[] info = donnee_enregistree.get(k);
            if (info[2].equals("1")){
                rev_mur.add(info);
            }
        }
        return rev_mur;
    }
    public static ArrayList<String[]> rev_sol(){
        ArrayList<String[]> rev_mur = new ArrayList<>();
        for (int k =0;k<=donnee_enregistree.size()-1;k++){
            String[] info = donnee_enregistree.get(k);
            if (info[3].equals("1")){
                rev_mur.add(info);
            }
        }
        return rev_mur;
    }
    public static ArrayList<String[]> rev_plafond(){
        ArrayList<String[]> rev_mur = new ArrayList<>();
        for (int k =0;k<=donnee_enregistree.size()-1;k++){
            String[] info = donnee_enregistree.get(k);
            if (info[4].equals("1")){
                rev_mur.add(info);
            }
        }
        return rev_mur;
    }
    
    // GET 
    public int getId() {
        return id;
    }
    public String getDésignation() {
        return désignation;
    }
    public String getPourMur() {
        return pourMur;
    }
    public String getPourSol() {
        return pourSol;
    }
    public String getPourPlafond() {
        return pourPlafond;
    }
    public double getPrix_unitaire() {
        return prix_unitaire;
    }

    // SET
    public void setPrix_unitaire(double prix_unitaire) { //ce setter permet tester des méthodes sans connaître le revêtement utilisé. (voir mur (27/03/24) ) .
        this.prix_unitaire = prix_unitaire;
    }
    
    
    
    // MAIN
    public static void main(String[]args){
    //////////////LECTURE FICHIER. IL s'appelle Revêtement_test.txt
    System.out.println("Donnez le nom de votre fichier :");
    String nom_fichier = Lire.S();
    donnee_enregistree = lecture(nom_fichier); // Lecture est ici une fonction qui renverra une ArrayList de tableau de chaînes de caractères
    
    ArrayList<String[]> rev_mur = new ArrayList<>();
    rev_mur=rev_sol();
    for (int k=0;k<=rev_mur.size()-1;k++){
        System.out.println("indice "+k+" : "+Arrays.toString(rev_mur.get(k)));
    }

    }
}
