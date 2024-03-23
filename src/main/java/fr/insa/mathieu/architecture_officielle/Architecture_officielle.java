/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
// essay pour voir si ça marche 
package fr.insa.mathieu.architecture_officielle;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Architecture_officielle {
    public static String [][] donnee_enregistree;

    public static void main(String[] args) {
        
    System.out.println("Donnez le nom de votre fichier :");
    String nom_fichier = Lire.S();
    donnee_enregistree=new String[100][100];
    donnee_enregistree = lecture(nom_fichier);
    /*
    System.out.println("ligne :");
    int l = Lire.i();
    System.out.println("colonne :");
    int c = Lire.i();
    System.out.println("résultat : "+donnee_enregistree[l][c]);
   */
    
    // TEST pour savoir si ça marche ca n'aide pas au programme mais ça permet de savoir si le programme marche : est ce que la classe revêtement peut me renvoyer les attributs 
    /*System.out.println("choisi une identité entre 1 et 8");
    int id=Lire.i();
    Revêtement a = new Revêtement(id);
    System.out.println("Désignation de a :"+a.getDésignation() );
    System.out.println("Pour mur de a :"+a.getPourMur() );
    System.out.println("pour sol de a :"+a.getPourSol() );
    System.out.println("pour plafond de a :"+a.getPourPlafond() );
    System.out.println("prix unitaire de a :"+a.getPrix_unitaire() );
    */
    // System.out.println("opération "+a.getPrix_unitaire()/4); // TEST pour la conversion du prix
        //
        
        
    }

    public static String[][] lecture(String nom_fichier){
    String ligne;//chaîne de caractères pour enregistrer les lignes du document texte
    ArrayList data = new ArrayList();
    int nbr_ligne=1;
    try {
        BufferedReader entre=new BufferedReader(new FileReader(nom_fichier));
        while ((ligne = entre.readLine())!= null){
            System.out.println(ligne);// on écrit la ligne dans le moniteur pour savoir où nous en sommes et le contenu du fichier lu
            data.add(ligne);//on ajoute la ligne lu par le BufferedReader à la liste qui s'appelle data
            nbr_ligne=nbr_ligne+1; // l'objectif ici est de connaître le nombre de ligne du fichier texte pour faire un tableau à la bonne dimension
        }
        entre.close();
        String [][] donnee_ligne = new String[nbr_ligne][12]; 
// crréation d'une matrice qui fonctionnera de la manière suivante. 
// Chaque ligne de la matrice sera une ligne du fichier texte
// chaque colonne correspondra aux informations suivantes : id, désignation, application, prix unitaire
//System.out.println("AVT PATTERN");
        Pattern separateur = Pattern.compile("\\d+(\\.\\d{1,2})?|\\w*"); // veut dire un nombre avec la possibilité de mettre une virgule ou un mot.
            
//System.out.println("AVT BOUCLE FOR");
        for (int k = 1;k< nbr_ligne;k++){ // pour chaque indice de ArrayList => recherche de l'information nombre
          //System.out.println("RENTRE DANS LA BOUCLE FOR, k= "+k);
            Matcher matcher = separateur.matcher((CharSequence) data.get(k-1));//data.get correspond à l'indice : l'endroit ou je recherhce l'information 
            int nbr_find=0;
            while (matcher.find()){                    
              //System.out.println("Rentre dans la boucle while");
              //System.out.println("nbr_find :"+nbr_find+" matcher : "+matcher.group()); // PB il fait 12 étapes pour trouver 6 correspondance
                donnee_ligne[k][nbr_find] = matcher.group();
              //System.out.println("ligne "+k+" ,colonne "+nbr_find+" : "+donnee_ligne[k][nbr_find]);
                nbr_find = nbr_find +1;        
            }
            //System.out.println("nbr de correspondance entre pattern et l'indice "+k+" de la liste : "+nbr_find);
                
        }
            
        /* System.out.println("AFFICHAGE RESULTAT"); //NE MARCHE PAS, IMPOSSIBLE DE L'AFFICHER
        for (int k=0;k<nbr_ligne;k++){
            for (int j=0;j<=15;j++){
                System.out.print(donnee_ligne[k][j]+" ");
            }
            System.out.println();
        }
        */ 
        /*
        System.out.println("ligne :"); MARCHE POUR CHOPER L'INFORMATION DANS LE TABLEAU
        int l = Lire.i();
        System.out.println("colonne :");
        int c = Lire.i();
        System.out.println("résultat : "+donnee_ligne[l][c]);
        */
        return donnee_ligne;
            
    }
    catch(FileNotFoundException err){
        System.out.println("Erreur : le fichier n'existe pas !\n"+err);
        return null;
    }
    catch (IOException err){
        System.out.println("Erreur:\n"+err);
        return null;
    }
    }
}
