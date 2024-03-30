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
    // ex : id = 3 nous donne les informations pour la ligne 3
    
    public static double todouble(String aconvertir){
        Double D=Double.valueOf(aconvertir);
	double finalvalue=D.doubleValue();
        return finalvalue;
    }
    
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
        
        if (id==9999){this.prix_unitaire = 5.55;}  //cette propriété permet de faire des tests dans les classes individuelles : 
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
}
