/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


/**
 *
 * @author stard
 * L'OBJECTIF ICI EST DE RENSEIGNER LES INFORMATIONS LIEES AU REVËTEMENT
 * IL DOIT REUSSIR A REPRENDRE LES DONNEES DU TABLEAU FAIT DANS LE MAIN. COMMENT ?
 * Revêtement standard est le 1 : peinture à 48.00 euros applicable sur toutes surfaces : 
 * prix moyen pour un peinture = 40€ HT plus 20% de TVA
 */
public class Revêtement {
    private int id;
    private String désignation;
    private String pourMur;
    private String pourSol;
    private String pourPlafond;
    private double prix_unitaire;
    private ArrayList<Mur> liste_mur;
    private ArrayList<Sol_plafond> listeSolPlafond;
    // id correspond à l'information de la première colonne de notre tableau donnée_ligne.
    // L'id du revêtement correspond = numéro de ligne
    // ex : id = 1 nous donne les informations pour la ligne 1
    // ex : id = 3 nous donne les informations pour la ligne 3
    
    // CONSTRUCTOR 
    public Revêtement(){
        this.listeSolPlafond=new ArrayList<>();
        this.liste_mur=new ArrayList<>();
    }
    public Revêtement(String[] elements,int id) {
        System.out.println("Je suis dans le constructeur de Revêtement");
        System.out.println(elements);
        
        
        this.id = id;
        // TODO A CHANGER pour récupérer les bonnes données 
        // LISTE DES INDICES de l'arraylist donnee_enregistrée : 
        // 0 : numéros d'identifiant
        // 1 : désignation 
        // 2 : aplication sur le mur
        // 3 : application sur le sol
        // 4 : application sur le plafond
        // 5 : prix unitaire que je converti en double grâce à la fonction "todouble"
        this.désignation = elements[1];
        this.pourMur = elements[2];
        this.pourSol = elements[3];
        this.pourPlafond = elements[4];
        this.prix_unitaire = todouble(elements[5]);
        this.liste_mur=new ArrayList();
        this.listeSolPlafond=new ArrayList();
    }
    // FONCTION 
   // TODO fonction pour lister les revêtements selon l'application sur tel ou tel surface
    public static double todouble(String aconvertir){
        Double D=Double.valueOf(aconvertir);
	double finalvalue=D.doubleValue();
        return finalvalue;
    }
    /**
     * Pour chaque couple de clé-valeur : on test si la valeur correspond à celle que nous cherchons
     * @param map
     * @param value
     * @return 
     */   
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
    public ArrayList<Mur> getListe_mur() {
        return liste_mur;
    }
    public ArrayList<Sol_plafond> getListe_sol_plafond() {
        return listeSolPlafond;
    }

    // SET
    public void setPrix_unitaire(double prix_unitaire) { //ce setter permet tester des méthodes sans connaître le revêtement utilisé. (voir mur (27/03/24) ) .
        this.prix_unitaire = prix_unitaire;
    }
        public void setId(int id) {
        this.id = id;
    }
    
    
    // MAIN
}
