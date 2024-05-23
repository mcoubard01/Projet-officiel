/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle; //test commentaire

/**
 *
 * @author stard
 */
public class Plafond extends Sol_plafond{
 private String id;
    Revêtement standard = new Revêtement(9999);//Revêtement standard
    
    //CONSTRUCTEUR
    public Plafond(Pièce pièce) {
        super(pièce);
    }
    public Plafond() {
    }

    // FONCTION
    @Override
    public boolean contrôle(Revêtement r){ // Contrôle
        boolean result=(r.getPourPlafond()).equals("1");
        return result;
    }
    public static String indente (String toIndente, String prefix){// présenter dans la vidéo 1 du prof 
        return prefix +toIndente.replaceAll("\n","\n"+ prefix);
    }
}
    /*
    @Override
    public String toString() {
        StringBuilder liste_coin = new StringBuilder("Sol {\n");
        if (coins != null && !coins.isEmpty()) {
            for (Coin coin : coins) {
                liste_coin.append(indente(coin.toString(), "    ")).append("\n");
            }
        } else {
            liste_coin.append("    Aucun coin n'est défini\n");
        }
    liste_coin.append("}");
    return liste_coin.toString();
    }
    */
