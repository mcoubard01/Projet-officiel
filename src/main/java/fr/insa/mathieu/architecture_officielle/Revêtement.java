/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;

import static fr.insa.mathieu.architecture_officielle.Architecture_officielle.donnee_enregistree;
import static fr.insa.mathieu.architecture_officielle.Architecture_officielle.lecture;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


/**
 *
 * @author stard
 * L'OBJECTIF ICI EST DE RENSEIGNER LES INFORMATIONS LIEES AU REVËTEMENT
 * IL DOIT REUSSIR A REPRENDRE LES DONNEES DU TABLEAU FAIT DANS LE MAIN. COMMENT ?
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
    public Revêtement(int id) {
        this.id = id;
        // TODO A CHANGER pour récupérer les bonnes données 
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
        String[] info= Architecture_officielle.getDonnee_enregistree().get(id);
        this.désignation = info[1];
        this.pourMur = info[2];
        this.pourSol = info[3];
        this.pourPlafond = info[4];
        this.prix_unitaire = todouble(info[5]);
        this.liste_mur=new ArrayList();
        this.listeSolPlafond=new ArrayList();
        }
        
        
    }
    // FONCTION 
   // TODO fonction pour lister les revêtements selon l'application sur tel ou tel surface
    public static double todouble(String aconvertir){
        Double D=Double.valueOf(aconvertir);
	double finalvalue=D.doubleValue();
        return finalvalue;
    }
    
    public static HashMap<Revêtement,String> rev_mur(){
        HashMap<Revêtement, String> mapRevêtMur=new HashMap<>();
        for (int k =0;k<=donnee_enregistree.size()-1;k++){
            //System.out.println("La taille de la liste donnee_enregistree : "+Architecture_officielle.getDonnee_enregistree().size());
            String[] info = Architecture_officielle.getDonnee_enregistree().get(k);
            if (info==null){
                //System.out.println("info est nul");
                continue; // Cette ligne permet au programme de ne pas faire la suite des conditions car la première n'est pas réalisée. source : chatgpt
            }
            else if(info[2].equals("1")){
                Revêtement revêtementMur = new Revêtement(k);
                String valeur=revêtementMur.getId()+" : "+revêtementMur.getDésignation()+" € : "+revêtementMur.getPrix_unitaire();
                mapRevêtMur.put(revêtementMur, valeur);
            }
            else {
                // Ca veut dire que le revêtement n'est pas appliquable sur le mur
            }
        }
        return mapRevêtMur;
    }
    public static HashMap<Revêtement,String> rev_sol(){
        HashMap<Revêtement, String> mapRevêtSol=new HashMap<>();
        for (int k =0;k<=donnee_enregistree.size()-1;k++){
            //System.out.println("La taille de la liste donnee_enregistree : "+Architecture_officielle.getDonnee_enregistree().size());
            String[] info = Architecture_officielle.getDonnee_enregistree().get(k);
            if (info==null){
                //System.out.println("info est nul");
                continue; // Cette ligne permet au programme de ne pas faire la suite des conditions car la première n'est pas réalisée. source : chatgpt
            }
            else if(info[3].equals("1")){
                Revêtement revêtementSol = new Revêtement(k);
                String valeur=revêtementSol.getId()+" : "+revêtementSol.getDésignation()+" € : "+revêtementSol.getPrix_unitaire();
                mapRevêtSol.put(revêtementSol, valeur);
            }
            else {
                // Ca veut dire que le revêtement n'est pas appliquable sur le mur
            }
        }
        return mapRevêtSol;
    }
    public static HashMap<Revêtement,String> rev_plafond(){
        HashMap<Revêtement, String> mapRevêtPlafond=new HashMap<>();
        for (int k =0;k<=donnee_enregistree.size()-1;k++){
            //System.out.println("La taille de la liste donnee_enregistree : "+Architecture_officielle.getDonnee_enregistree().size());
            String[] info = Architecture_officielle.getDonnee_enregistree().get(k);
            if (info==null){
                //System.out.println("info est nul");
                continue; // Cette ligne permet au programme de ne pas faire la suite des conditions car la première n'est pas réalisée. source : chatgpt
            }
            else if(info[4].equals("1")){
                Revêtement revêtementPlafond = new Revêtement(k);
                String valeur=revêtementPlafond.getId()+" : "+revêtementPlafond.getDésignation()+" € : "+revêtementPlafond.getPrix_unitaire();
                mapRevêtPlafond.put(revêtementPlafond, valeur);
            }
            else {
                // Ca veut dire que le revêtement n'est pas appliquable sur le mur
            }
        }
        return mapRevêtPlafond;
    }
    /**
     * Pour chaque couple de clé-valeur : on test si la valeur correspond à celle que nous cherchons
     * @param map
     * @param value
     * @return 
     */
    public static Revêtement getKeyFromValue(HashMap<Revêtement, String> map, String value) {
        for (HashMap.Entry<Revêtement, String> entry : map.entrySet()) {//la classe Entry possède par pair : la clé et de la valeur
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
    
    
    
    //Liste des revêtements par type de surface possible
    public static ArrayList<String[]> rev_Mur(){
        ArrayList<String[]> rev_mur = new ArrayList<>();
        for (int k =0;k<=Architecture_officielle.getDonnee_enregistree().size()-1;k++){
            String[] info = Architecture_officielle.getDonnee_enregistree().get(k);
            if (info[2].equals("1")){
                rev_mur.add(info);
            }
        }
        return rev_mur;
    }
    public static ArrayList<String[]> rev_Sol(){
        ArrayList<String[]> rev_mur = new ArrayList<>();
        for (int k =0;k<=Architecture_officielle.getDonnee_enregistree().size()-1;k++){
            String[] info = Architecture_officielle.getDonnee_enregistree().get(k);
            if (info[3].equals("1")){
                rev_mur.add(info);
            }
        }
        return rev_mur;
    }
    public static ArrayList<String[]> rev_Plafond(){
        ArrayList<String[]> rev_mur = new ArrayList<>();
        for (int k =0;k<=Architecture_officielle.getDonnee_enregistree().size()-1;k++){
            String[] info = Architecture_officielle.getDonnee_enregistree().get(k);
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
    
    
    // MAIN
}
