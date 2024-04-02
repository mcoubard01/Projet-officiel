/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
// verification test et essai de merge (oscar)
// Verification push pour Mathieu

package fr.insa.mathieu.architecture_officielle;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
import static fr.insa.mathieu.architecture_officielle.Mur.longueur;
import java.io.*;
import java.util.ArrayList;


public class Architecture_officielle { // TEST 27/03 + thomas's test +nouveau commentaire
    public static ArrayList<String[]> donnee_enregistree; // Liste de tableau de chaine de caractère qui est utilisé pour le stockage des Revêtements
    
    public static void main(String[] args) {
   //////////////LECTURE FICHIER. IL s'appelle Revêtement_test.txt
    System.out.println("Donnez le nom de votre fichier :");
    String nom_fichier = Lire.S();
    donnee_enregistree = lecture(nom_fichier); // Lecture est ici une fonction qui renverra une ArrayList de tableau de chaînes de caractères

   
      Coin a = new Coin(2,6);
    Coin b = new Coin(4,6);
    Coin c= new Coin(2,0);
    Revêtement r =new Revêtement(2);
    Sol sol = new Sol(a,b,c,r);
    System.out.println("le prix est de teyteytyg"+sol.prix());
    

    
   //////////////TEST SURFACE PIECE + PRIX
   /*
    Coin a = new Coin(2,6);
    Coin b = new Coin(4,6);
    Coin c= new Coin(2,0);
    Revêtement e = new Revêtement(2); // gazon normalement
    System.out.println("Le nom du revêtement est : "+e.getDésignation());
    System.out.println("Le prix du ce revêtement est : "+ e.getPrix_unitaire());
    Sol sol =new Sol (a,b,c,e);
    System.out.println("longueur a et b  : "+longueur(a,b));
    System.out.println("longueur a et b  : "+longueur(a,c));  
    System.out.println("la surface du sol   : "+Sol.surface(a,b,c));
    System.out.println("le prix du sol au m² est  : "+sol.prix());
    */
   
    //////////////TEST PRIX MUR
    /*
    Coin debut1 , fin1;
    debut1= new Coin(2,1);
    fin1 = new Coin(5,1);
        
    Etage etage1 = new Etage(5);
    Revêtement test=new Revêtement(2);
    Mur mur = new Mur(debut1,fin1, etage1, test);
        
    System.out.println("Le prix du revêtement est : "+test.getPrix_unitaire());
    System.out.println("Length = " + mur.longueur());
    System.out.println("Hauteur du mur : "+etage1.getHauteur_etage());
    System.out.println("Surface is " + mur.surface());
    System.out.println("Price is " + mur.prix());
    */ 
    
    ////////////// TEST Classe revêtement peut me renvoyer les attributs
    // Le numéro à rentrer pour tester le code correspond à l'identifiant sur le fichier text Revêtement_test.txt
    /*
    System.out.println("choisi une identité entre 1 et 8");
    int id=Lire.i();
    Revêtement a = new Revêtement(id);
    System.out.println("Désignation de a :"+a.getDésignation() );
    System.out.println("Pour mur de a :"+a.getPourMur() );
    System.out.println("pour sol de a :"+a.getPourSol() );
    System.out.println("pour plafond de a :"+a.getPourPlafond() );
    System.out.println("prix unitaire de a :"+a.getPrix_unitaire() );
    System.out.println("opération "+a.getPrix_unitaire()/4); // TEST pour la conversion du prix, afin de savoir si on peut manipuler le nombre
    */        
    }

    public static ArrayList<String[]> lecture(String nom_fichier){
    String ligne;                                   //chaîne de caractères pour enregistrer les lignes du document texte
    ArrayList <String>data = new ArrayList();       //Création de l'ArrayList qui sera utilisé pour récupérer le fichier lu dans la boucle WHILE
    ArrayList<String[]>ligne_array = new ArrayList<>(); // Création de l'ArrayList de chaîne de caractère qui sera utilisée pour stocker les revêtements
    int nbr_ligne=0;                                // Compte le nombre de ligne que contiendra le fichier à lire. S'il ne contient rien il retournera 0. 
    
    try {                                           // Pour gérer les exceptions de fichiers : fichier non trouvé...
        BufferedReader entre=new BufferedReader(new FileReader(nom_fichier));
        while ((ligne = entre.readLine())!= null){
            System.out.println(ligne);            // on écrit la ligne dans le moniteur pour analyser ce que lit le bufferedReader
            data.add(ligne);                      //on ajoute la ligne lu par le BufferedReader à la liste qui s'appelle data
            nbr_ligne=nbr_ligne+1;                  //Connaître le nombre de ligne du fichier pour faciliter la gestion de l'ArrayList
        }
        entre.close();                              //On ferme le BufferedReader
        System.out.println("nombre de ligne :"+nbr_ligne); // Affichage du nombre de ligne dans le fichier text lu par le BufferedReader
        for (int k = 1; k <= nbr_ligne; k++) {      // Debut boucle utile pour la réalisation du stockage dans les listes
            int a = k-1;                            // 1ere ligne du fichier texte corresond à l'indice 0 de la liste data
            String[] elements =data.get(a).split(";"); //Création d'un tableau pour chaque indice de 'data' avec chaque case du tableau un élèment se situant entre les ";"
            ligne_array.add(a, elements);// ajout à l'indice a le tableau créé et rempli juste avant.
        }
        return (ligne_array) ;                      // Lorsque toutes les lignes du fichier texte ont été lu, nous retournons l'Arraylist de tableau de String
            
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
