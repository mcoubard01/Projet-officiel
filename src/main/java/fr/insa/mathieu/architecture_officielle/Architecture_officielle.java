/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package fr.insa.mathieu.architecture_officielle;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
//import fr.insa.mathieu.architecture_officielle.Mur.longueur;
//Sachant que l' "import" n'est pas nécessaire ici : 
//il suffit dans le texte d'écrire "Mur.longueur()", qui fait appel à la classe 
//(comme Lire.i() au lieu de "import fr.insa.mathieu.architecture_officielle.Lire.i;")


import static fr.insa.mathieu.architecture_officielle.Revêtement.todouble;
import java.io.*;
import java.util.ArrayList;


public class Architecture_officielle { 
    public static ArrayList<String[]> donnee_enregistree; // Liste de tableaux de chaines de caractère qui est utilisée pour le stockage des Revêtements
    
    
    
    
    public static void test_surfacePiece_et_Prix(){
        //////////////TEST SURFACE PIECE + PRIX
        Coin a = new Coin(2,6);
        Coin b = new Coin(4,6);
        Coin c= new Coin(2,0);
        Coin d= new Coin(4,0);
        Revêtement e = new Revêtement(2); // gazon normalement
        System.out.println("Le nom du revêtement est : "+e.getDésignation());
        System.out.println("Le prix du ce revêtement est : "+ e.getPrix_unitaire());
        
        Sol sol = new Sol("id",a,b,c,d);
        sol.setRevêtement(e);
        System.out.println("longueur a et b  : "+Mur.longueur(a, b));   //l'appel de "longueur" ici aurait pu être "Mur.longeur(a,b)"
        System.out.println("longueur a et b  : "+Mur.longueur(a, c));  
        System.out.println("la surface du sol   : "+sol.surface(a, b, c));
        System.out.println("le prix du sol au m² est  : "+sol.prix());
        System.out.println("hello");
    }
    
    public static void test_prixMur(){
        //////////////TEST PRIX MUR
    Coin debut1 , fin1;
    debut1= new Coin(2,1);
    fin1 = new Coin(5,1);
        
    Etage etage1 = new Etage(5);
    Revêtement test=new Revêtement(2);
    Mur mur = new Mur(debut1,fin1, etage1);
    mur.setRevêtement(test);
        
    System.out.println("Le prix du revêtement est : "+test.getPrix_unitaire());
    System.out.println("Length = " + mur.longueur());
    System.out.println("Hauteur du mur : "+etage1.getHauteur_etage());
    System.out.println("Surface is " + mur.surface());
    System.out.println("Price is " + mur.prix());
        }
    
    public static void test_Revêtement(){
        ////////////// TEST Classe revêtement peut me renvoyer les attributs
    // Le numéro à rentrer pour tester le code correspond à l'identifiant sur le fichier text Revêtement_test.txt

    System.out.println("choisi une identité entre 1 et 8");
    int id=Lire.i();
    Revêtement a = new Revêtement(id);
    System.out.println("Désignation de a :"+a.getDésignation() );
    System.out.println("Pour mur de a :"+a.getPourMur() );
    System.out.println("pour sol de a :"+a.getPourSol() );
    System.out.println("pour plafond de a :"+a.getPourPlafond() );
    System.out.println("prix unitaire de a :"+a.getPrix_unitaire() );
    System.out.println("opération "+a.getPrix_unitaire()/4); // TEST pour la conversion du prix, afin de savoir si on peut manipuler le nombre
    
    }
    
    
    public static void faireDesTests(){   //cette méthode permet de nettoyer un peu le main, et permettra plsu tard de faire des tests selon nos besoins.
        System.out.println("""
                Quel test voulez vous effectuer?
                entrez 1 pour le TEST SURFACE PIECE + PRIX
                entrez 2 pour le TEST PRIX MUR
                entrez 3 poour le Test de la classe revêtement
                entrez 0 pour sortir d'ici
                """);
        int testAFaire = Lire.i();
        if (testAFaire == 1){ test_surfacePiece_et_Prix(); }
        if (testAFaire == 2) { test_prixMur(); }
        if (testAFaire == 3) { test_Revêtement(); }

        if (testAFaire == 0) { System.out.println("non? passons à la suite alors...\n"); }

        else {System.out.println("pas compris, dsl");}
    }
    
    public static void launchProgramm(){
        boolean run = true;
        
        /**
         * IdEtageActuel est l'étage dans lequel on se trouve actuellement. 
         * On peut changer ceci (temporairement) quand on est dans la boucle (run == true). ( voir plus bas)
         */
        int IdEtageActuel = 0;  
        
        Etage etageActuel; //voir l'option "5", "3"...
        
        Coin coinTest1 = new Coin(2,3); //des objets de test 
        Coin coinTest2 = new Coin(2,0);
        Etage etageTest1 = new Etage(5);
        Mur murTest1 = new Mur(coinTest1,coinTest2,etageTest1);
        
        ArrayList<String> objetsCréés = new ArrayList<>(){{
        add(etageTest1.toString());
        add(coinTest1.toString());
        add(coinTest2.toString());
        add(murTest1.toString());
        
        }};
        
        
        while (run){
            System.out.println("""
                               Que voulez vous faire? 
                               1 : consulter les objets créés
                               2 : créer un nouveau coin 
                               3 : créer un nouveau mur avec de nouveaux coins
                               4 : créer un nouveau mur avec des coins existants
                               5 : se placer à un étage, créer un nouvel étage
                               """);
            int optionChoisie = Lire.i();
            switch (optionChoisie){
                case 1 : 
                    System.out.println(objetsCréés);
                    break;
                case 2 :  //créer un nouveau coin
                    System.out.println("entrez sa coordonnée x");
                    int coordX = Lire.i();
                    System.out.println("entrez sa coordonnée y");
                    int coordY = Lire.i();
                    Coin coin = new Coin(coordX,coordY);
                    objetsCréés.add(coin.toString());
                    //jusqu'ici, on ajoute archaïquement les objets créees à l'ArrayList. Possibles améliorations : 
                    
                    //TODO : créer l'Arraylist "objetsCréés" depuis les maps contenant les identifiants
                    //afin " d'automatiser" le processus.  --> faie en sorte que l'Arratlist se se recrée pas à chaque nouvel objet, pour ne pas alourdir.
                    
                    //TODO: inclure l'utilistation de l'ArrayList "objetsCréés" dans la sauvegarde et la récupération des données.
                    break;
                case 3 : //créer un nouveau mur avec de nouveaux coins
                    System.out.println("nouveau mur. création de coin début");
                    System.out.println("entrez sa coordonnée x");
                    int coordXDebut = Lire.i();
                    System.out.println("entrez sa coordonnée y");
                    int coordYDebut = Lire.i();
                    Coin coinDebut1 = new Coin(coordXDebut,coordYDebut);
                    objetsCréés.add(coinDebut1.toString());
                    System.out.println("création de coin fin");
                    System.out.println("entrez sa coordonnée x");
                    int coordXFin = Lire.i();
                    System.out.println("entrez sa coordonnée y");
                    int coordYFin = Lire.i();
                    Coin coinFin1 = new Coin(coordXFin,coordYFin);
                    objetsCréés.add(coinFin1.toString());
                    
                    //TODO : si on "crée" un nouveau coin alors que ce coin existe déjà, le programme doit annuler la création et automatiquement utiliser le coin existant.
                    
                    etageActuel = IDManager.getObjetEtage(IdEtageActuel);
                    Mur nouveauMur1 = new Mur(coinDebut1,coinFin1,etageActuel);
                    objetsCréés.add(nouveauMur1.toString());
                    
                    objetsCréés.set(0, etageTest1.toString()); 
                    //ceci est une méthode archaïque pour "mettre à jour" l'étage rdc, qui a été ajouté à objetsCréés avant la boucle.
                    //TODO : implémenter une méthode plus systématique pour "mettre à jour" les éléments de objetsCréés (arraylist de String). 
                    //peut-être créer des sous-arraylists contenant de manière séparée les coins, les murs, les étages... 
                    
                    break;
                case 4 : //créer un nouveau mur avec des coins existants
                    System.out.println("Cette option n'a pas été encore configurée");
                    break;
                case 5 :
                    System.out.print("quel étage voulez vous modifier? ");
                    System.out.println("(il y a actuellement " + IDManager.mapEtage.size() + " étages (rdc : 0). Entrez un numéro plus grand pour en créer un nouveau et s'y placer.)");
                    //TODO : vérifirer que IDManager.mapEtage fonctionne encore une fois la forme définitive de IDManager atteinte.
                    int IdEtageSouhaité = Lire.i();
                    if (IdEtageSouhaité > IDManager.mapEtage.size()){//si size() = 3, on entre 4 pour créer l'étage 3.
                        System.out.println("quelle hauteur pour ce nouvel étage? entrez un int."); //TODO : Il faudrait plus tard transformer hauteru_etage en double.
                        int hauteurNouvelEtage = Lire.i();
                        Etage nouvelEtage = new Etage(hauteurNouvelEtage);
                        
                    }
                case 9999 :
                    run = false;
            default : break;
            }
        }
    }
    
    //à terme, il faudrait créer une classe séparée contenant les méthodes lecture, écriture, sauvegarde...
    
    //TODO : la méthode "lecture" est peut-être trop spécialisée (lecture de Revêtements_test.txt), pas assez "flexible", non? 
    //à voir dans notre utilisation
    
    
    
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
        for (int i=0;i<=1126;i++){
            ligne_array.add(null);
        }
        System.out.println("FFnombre de ligne :"+nbr_ligne); // Affichage du nombre de ligne dans le fichier text lu par le BufferedReader
        for (int k = 1; k <= nbr_ligne; k++) {      // Debut boucle utile pour la réalisation du stockage dans les listes
            int a=k-1;                            // 1ere ligne du fichier texte correspond à l'indice 0 de la liste data
            String[] elements =data.get(a).split(";"); //Création d'un tableau pour chaque indice de 'data' avec chaque case du tableau un élèment se situant entre les ";"
            int index =Integer.parseInt(elements[0]);
            System.out.println("index"+index);
            ligne_array.set(index, elements);// ajout à l'indice a le tableau créé et rempli juste avant.
            System.out.println("contenue à l'index "+index+" : "+ligne_array.get(index)[1]+" adresse :"+ligne_array.get(index));
        }
        return (ligne_array) ;                      // Lorsque toutes les lignes du fichier texte ont été lues, nous retournons l'Arraylist de tableau de String
            
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
    
    public static ArrayList<String[]> ecriture(String nom_fichier){
        System.out.print("helloworld");
        return null; //temporaire
    }


public static void main(String[] args) {
   /////////////LECTURE FICHIER. IL s'appelle Revêtement_test.txt
    System.out.println("Donnez le nom de votre fichier :");
    String nom_fichier = Lire.S();
    donnee_enregistree = lecture(nom_fichier); // lecture est ici une fonction qui renverra une ArrayList de tableau de chaînes de caractères
    
    launchProgramm();
    //La méthode ci-dessous est utilisée surtout pour vider le main des tests menés.
    faireDesTests(); //une méthode qui permet de nettoyer le main. Voir plus haut : elle est utilisée pour regrouper les tests que l'on veut faire.
    
    
   
    
    
    
    }

}
