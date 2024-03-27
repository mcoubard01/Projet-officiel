/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;


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
    // ex : id = 3 nous donne les informations pour la ligne 3 (soit 
    public static double todouble(String aconvertir){
        Double D=Double.valueOf(aconvertir);
	double finalvalue=D.doubleValue();
        return finalvalue;
    }
    public Revêtement(int id) {
        this.id = id;
        // A CHANGER pour récupérer les bonnes données
        String[] info= donnee_enregistree.get(id);
        this.désignation = info[1];
        this.pourMur = info[2];
        this.pourSol = info[3];
        this.pourPlafond = info[4];
        this.prix_unitaire = todouble(info[5]);
    }

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
    
    
}
