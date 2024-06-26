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
import fr.insa.mathieu.architecture_officielle.gui.Contrôleur;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Architecture_officielle { 
    public ArrayList<String[]> donnee_enregistree; // Liste de tableaux de chaines de caractère qui est utilisée pour le stockage des Revêtements
    private ArrayList<Etage> liste_etage;
    private Etage étageActuel;
    private Contrôleur contrôleur;
    public static ArrayList<Revêtement> listeRevêtement;

    

        
    //TODO : l'utilsatuer doit pouvoir spécifier la hauteur de son premier étage.
    //Cela pourrait être un paramètre du constructeur ci-dessous. 
    //On ne créérait m^me pas le bâtiment tout de suite, 
        //On de manderait D'ABORD la hauteur du premier étage. 
    //Mais ça voudrait dire qu'on ne êut pas fournir de modèle au MainPan dans la méthode start...
    public Architecture_officielle(){
        //this.contrôleur=contrôleur; //19/05/24, T.B. : ceci n'a pas l'air d'être important.
        listeRevêtement=new ArrayList<>();
        donnee_enregistree=lecture("Revêtement_final.txt");
        this.liste_etage=new ArrayList<>();
        IDManager.setBâtiment(this);
        //IDManager a aussi un attribut bâtiment
        //this.étageActuel = new Etage(2.5);
        //TODO : éventuellement, il faudra que l'utilisateur choisisse son étage de début!!
    }
    
    public void add(Etage etage){
        if(this.liste_etage.contains(etage)){
            System.out.println("Le batiment possède déjà l'étage");
        }
        else{
            this.liste_etage.add(etage);
        }
        
    }
    /**
     * TODO à faire foncitonner avec la liste des etages seulement
     * @param context 
     */
    public void dessine(GraphicsContext context){
        //System.out.println("etage actuel de la classe Batiment : "+this.étageActuel.toString());
        if(this.étageActuel!=null){
            System.out.println("quel est l'etage actuel de la classe Batiment : "+this.étageActuel.toString());
            this.étageActuel.dessine(context);
        }
    }
    
    /**
     * 
     * @param context
     * @param murSélectionné 
     */
    public void highlight(GraphicsContext context, Mur murSélectionné) {
        System.out.println("HIGHLIGHT(context,mur) de Architecture_Officiel");
        murSélectionné.highlight(context);
    }
    public void highlight(GraphicsContext context, Pièce pièceSélectionnée) {
        pièceSélectionnée.highlight(context);
    }
    /**
     * CALCUL de surface
     * @return 
     */
    public double surface(Revêtement revêtement){
        double surfaceRevêtement=0;
        for(Etage etage: this.getListe_etage()){
            for(Pièce pièce : etage.getListPièceOrpheline()){
                for (Mur mur : pièce.getListe_mur()){
                    if (mur.getRevêtement().equals(revêtement)){
                        surfaceRevêtement = surfaceRevêtement + mur.surface();
                    }
                }
                if (pièce.getSol().getRevêtement().equals(revêtement)){
                    surfaceRevêtement = surfaceRevêtement + pièce.getSol().surface();
                }
                if (pièce.getPlafond().getRevêtement().equals(revêtement)){
                    surfaceRevêtement = surfaceRevêtement + pièce.getPlafond().surface();
                }
            }
            for (Appartement appartement : etage.getListe_appartement()){
                for(Pièce pièce : appartement.getListe_pièce()){
                    for (Mur mur : pièce.getListe_mur()){
                        if (mur.getRevêtement().equals(revêtement)){
                            surfaceRevêtement = surfaceRevêtement + mur.surface();
                        }
                    }
                    if (pièce.getSol().getRevêtement().equals(revêtement)){
                        surfaceRevêtement = surfaceRevêtement + pièce.getSol().surface();
                    }
                    if (pièce.getPlafond().getRevêtement().equals(revêtement)){
                        surfaceRevêtement = surfaceRevêtement + pièce.getPlafond().surface();
                    }
                }
            }
        }
        surfaceRevêtement=(double)Math.round(surfaceRevêtement*100)/100;
        return surfaceRevêtement;
    }
    public double surfaceTotalHabitable(){
        double surfaceTotale=0;
        for(Etage etage: this.liste_etage){
            for(Appartement appartement : etage.getListe_appartement()){
                for(Pièce pièce : appartement.getListe_pièce()){
                    surfaceTotale = surfaceTotale + pièce.getSol().surface();
                }
            }
            for(Pièce pièce : etage.getListPièceOrpheline()){
                surfaceTotale = surfaceTotale + pièce.getSol().surface();
                
            }
        }
        surfaceTotale=(double)Math.round(surfaceTotale*100)/100;
        return surfaceTotale;
    }
    
    /**
     * CALCUL de PRIX
     * @return 
     */
    public double prixTotal(){
        double prixTotal =0;
        for (Etage etage : this.liste_etage){
            System.out.println("je suis dans la fonction prix Total de batiment");
            prixTotal = prixTotal+ etage.prix();
        }
        prixTotal=(double)Math.round(prixTotal*100)/100;
        return prixTotal;
    }
    public double prix(Revêtement revêtement){
        double prixRevêtement=0;
        for(Etage etage: this.getListe_etage()){
            for(Pièce pièce : etage.getListPièceOrpheline()){
                for (Mur mur : pièce.getListe_mur()){
                    if (mur.getRevêtement().equals(revêtement)){
                        prixRevêtement = prixRevêtement + mur.prix();
                    }
                }
                if (pièce.getSol().getRevêtement().equals(revêtement)){
                    prixRevêtement = prixRevêtement + pièce.getSol().prix();
                }
                if (pièce.getPlafond().getRevêtement().equals(revêtement)){
                    prixRevêtement = prixRevêtement + pièce.getPlafond().prix();
                }
            }
            for (Appartement appartement : etage.getListe_appartement()){
                for(Pièce pièce : appartement.getListe_pièce()){
                    for (Mur mur : pièce.getListe_mur()){
                        if (mur.getRevêtement().equals(revêtement)){
                            prixRevêtement = prixRevêtement + mur.prix();
                        }
                    }
                    if (pièce.getSol().getRevêtement().equals(revêtement)){
                        prixRevêtement = prixRevêtement + pièce.getSol().prix();
                    }
                    if (pièce.getPlafond().getRevêtement().equals(revêtement)){
                        prixRevêtement = prixRevêtement + pièce.getPlafond().prix();
                    }
                }
            }
        }
        prixRevêtement=(double)Math.round(prixRevêtement*100)/100;
        return prixRevêtement;
    }
    
    public ArrayList<String> prixParRevêtement(){
         ArrayList<String> prixParRev = new  ArrayList<>();
        HashMap<Revêtement,String> mapRev = this.contrôleur.getVueRevetement().rev_Total();
        for (Entry entry : mapRev.entrySet()){
            prixParRev.add((String) entry.getValue());
            //renvoie le String du prix d'un revêtement
        }
        return prixParRev;
    }

    
    ///TEST
    public static Architecture_officielle Test_batiment(){
        
        Architecture_officielle batiment = new Architecture_officielle();
        
        Etage e1 = new Etage(2);
        
        Coin c1 =new Coin(1,100);
        Coin c2 =new Coin(1,5);
        Mur m1= new Mur(c1,c2,e1);
        
        Coin c3 =new Coin(1,5); //supression de " Coin c3=new Coin("c3",1,5) "
        Coin c4 =new Coin(100,5);
        Mur m2= new Mur(c3,c4,e1); 
        
        Coin c5 =new Coin(100,5);
        Coin c6 =new Coin(100,100);
        Mur m3= new Mur(c5,c6,e1);
        
        Coin c7 =new Coin(100,100);
        Coin c8 =new Coin(1,100);
        Mur m4= new Mur(c7,c8,e1);
        
        Pièce pièce1= new Pièce(e1);
        
        pièce1.add(m1);
        pièce1.add(m2);
        pièce1.add(m3);
        pièce1.add(m4);
        
        Appartement appartement = new Appartement(e1);
        
        appartement.add_pièce(pièce1);
        batiment.add(e1);
        System.out.println("Je suis dans le test_bâtiment et je l'ai fini");
        return batiment;
        
    }
    public static void test_surfacePiece_et_Prix(Pièce pièce){
        //////////////TEST SURFACE PIECE + PRIX
        Architecture_officielle batiment = new Architecture_officielle();
        Revêtement e = listeRevêtement.get(0);
        System.out.println("Le nom du revêtement est : "+e.getDésignation());
        System.out.println("Le prix du ce revêtement est : "+ e.getPrix_unitaire());
        
        Sol sol = new Sol(pièce);
        //sol.setRevêtement(e); 
        System.out.println("la surface du sol   : "+pièce.surface());
        System.out.println("le prix du sol au m² est  : "+sol.prix());
        System.out.println("hello");
    }      
    public static void test_prixMur(){
        //////////////TEST PRIX MUR
    Architecture_officielle batiment = new Architecture_officielle();
    Coin debut1 , fin1;
    debut1= new Coin(2,1);
    fin1 = new Coin(5,1);
        
    Etage etage1 = new Etage(5,batiment);
    Revêtement test=listeRevêtement.get(0);
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
    /*
    Revêtement a = new Revêtement(id);
    System.out.println("Désignation de a :"+a.getDésignation() );
    System.out.println("Pour mur de a :"+a.getPourMur() );
    System.out.println("pour sol de a :"+a.getPourSol() );
    System.out.println("pour plafond de a :"+a.getPourPlafond() );
    System.out.println("prix unitaire de a :"+a.getPrix_unitaire() );
    System.out.println("opération "+a.getPrix_unitaire()/4); // TEST pour la conversion du prix, afin de savoir si on peut manipuler le nombre
    */
    }
    
    
    public static void faireDesTests(Architecture_officielle batiment){   //cette méthode permet de nettoyer un peu le main, et permettra plsu tard de faire des tests selon nos besoins.
        System.out.println("""
                Quel test voulez vous effectuer?
                entrez 1 pour le TEST SURFACE PIECE + PRIX
                entrez 2 pour le TEST PRIX MUR
                entrez 3 poour le Test de la classe revêtement
                entrez 0 pour sortir d'ici
                """);
        int testAFaire = Lire.i();
        if (testAFaire == 1){ 
            Etage étageTest = new Etage(5,batiment);
            Pièce pièce = new Pièce(étageTest);
            test_surfacePiece_et_Prix(pièce); }
        if (testAFaire == 2) { test_prixMur(); }
        if (testAFaire == 3) { test_Revêtement(); }

        if (testAFaire == 0) { System.out.println("non? passons à la suite alors...\n"); }

        else {System.out.println("pas compris, dsl");}
    }
    
    public static void launchProgramm(){
        boolean run = true;
        
        /**
         * idEtageActuel est l'étage dans lequel on se trouve actuellement. 
         * On peut changer ceci (temporairement) quand on est dans la boucle (run == true). ( voir plus bas)
         */

        int idEtageActuel = 0;  
        
        Etage etageActuel; //voir l'option "5", "3"...
        
        Architecture_officielle batiment = new Architecture_officielle();
        Coin coinTest1 = new Coin(0,0); //des objets de test 
        Coin coinTest2 = new Coin(3,0);
        Coin coinTest3 = new Coin(3,3);
        Coin coinTest4 = new Coin(0,3);
        Etage rdc = new Etage(5, batiment);//rez de chaussé
        etageActuel = rdc;
        Mur murTest1 = new Mur(coinTest1,coinTest2,rdc);
        Mur murTest2 = new Mur(coinTest2,coinTest3,rdc);
        Mur murTest3 = new Mur(coinTest3,coinTest4,rdc);
        Mur murTest4 = new Mur(coinTest4,coinTest1,rdc);
        Porte ouvertureTest1 = new Porte(1.5,0,'E',murTest1);
        Fenêtre fenêtreTest1 = new Fenêtre(1.4,0,'O',murTest1);
        Pièce pièceTest1 = new Pièce(murTest1,murTest2,murTest3,murTest4);
        //crée une pièce ET un nouvel appartement

        
        
        while (run){
            System.out.println("""
                               Que voulez vous faire? 
                               1 : consulter les objets créés
                               2 : créer un nouveau coin 
                               3 : créer un nouveau mur avec de nouveaux coins
                               4 : créer un nouveau mur avec des coins existants
                               5 : se placer à un étage, créer un nouvel étage
                               6 : enregistrer un fichier de sauvegarde
                               """);
            int optionChoisie = Lire.i();
            switch (optionChoisie){
                case 1 : 
                    int nombreDeItérations = IDManager.imprimerLesObjetsCréés().size();
                    for (int j = 0; j < nombreDeItérations; j++){
                        System.out.println(IDManager.imprimerLesObjetsCréés().get(j));
                        //imprilme tout à tour chaque case de IDManager.imprimerLesObjetsCréés(); (arraylist de string)
                    }
                    break;
                case 2 :  //créer un nouveau coin
                    System.out.println("entrez sa coordonnée x");
                    int coordX = Lire.i();
                    System.out.println("entrez sa coordonnée y");
                    int coordY = Lire.i();
                    Coin coin = new Coin(coordX,coordY);
                    break;
                    
                case 3 : //créer un nouveau mur avec de nouveaux coins
                    System.out.println("nouveau mur. création de coin début");
                    System.out.println("entrez sa coordonnée x");
                    int coordXDebut = Lire.i();
                    System.out.println("entrez sa coordonnée y");
                    int coordYDebut = Lire.i();
                    Coin coinDebut1 = new Coin(coordXDebut,coordYDebut);
                    System.out.println("création de coin fin");
                    System.out.println("entrez sa coordonnée x");
                    int coordXFin = Lire.i();
                    System.out.println("entrez sa coordonnée y");
                    int coordYFin = Lire.i();
                    Coin coinFin1 = new Coin(coordXFin,coordYFin);
                    
                    //TODO : si on "crée" un nouveau coin alors que ce coin existe déjà, le programme doit annuler la création et automatiquement utiliser le coin existant.
                    
                    Mur nouveauMur1 = new Mur(coinDebut1,coinFin1,etageActuel);                    
                    break;
                    
                case 4 : //créer un nouveau mur avec des coins existants
                    System.out.println("Cette option n'a pas été encore configurée");
                    break;
                case 5 :
                    System.out.print("création d'un nouvel étage ");
                    //TODO ajouter l'option de modifier un étage (suppression de murs...)
                    System.out.println("(il y a actuellement " + IDManager.mapEtage.size() + " étages (Attention! rdc : 0). "
                            + "Entrez un numéro plus grand pour en créer un nouveau et s'y placer.)"
                            + "(création de l'étage juste au dessus)");
                    //TODO : vérifier que IDManager.mapEtage fonctionne encore une fois la forme définitive de IDManager atteinte.
                    int IdEtageSouhaité = Lire.i();

                    while (IdEtageSouhaité < IDManager.mapEtage.size()){
                        System.out.println("mmerci d'entrer un nombre plus grand que " + IDManager.mapEtage.size() );
                        IdEtageSouhaité = Lire.i();

                    }
                    System.out.println("quelle hauteur pour ce nouvel étage? entrez un double."); 
                    double hauteurNouvelEtage = Lire.d();
                    Etage nouvelEtage = new Etage(hauteurNouvelEtage,batiment);
                    idEtageActuel = nouvelEtage.getId();
                    etageActuel = IDManager.getObjetEtage(idEtageActuel);
                    
                case 6 : //enregistrer nos données
                    sauvegardeParDéfaut();
                    break;
                case 9999 :
                    run = false;
            default : break;
            }
        }
    }
    
    
    //TODO : la méthode "lecture" est peut-être trop spécialisée (lecture de Revêtements_test.txt), pas assez "flexible", non? 
    //à voir dans notre utilisation
   //La méthode lectureGénérale() est plus générale, car simpliste (voir plus bas)
    
    
    public ArrayList<String[]> lecture(String nom_fichier){
        String ligne;                                   //chaîne de caractères pour enregistrer les lignes du document texte
        ArrayList <String>data = new ArrayList();       //Création de l'ArrayList qui sera utilisé pour récupérer le fichier lu dans la boucle WHILE
        ArrayList<String[]>ligne_array = new ArrayList<>(); // Création de l'ArrayList de chaîne de caractère qui sera utilisée pour stocker les revêtements
        int nbr_ligne=0;                                // Compte le nombre de ligne que contiendra le fichier à lire. S'il ne contient rien il retournera 0. 
    
        try {                                           // Pour gérer les exceptions de fichiers : fichier non trouvé...
            BufferedReader entre =new BufferedReader(new FileReader(nom_fichier));
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
            int b=0;
            for (int k = 1; k <= nbr_ligne; k++) {      // Debut boucle utile pour la réalisation du stockage dans les listes
                int a=k-1;                      // 1ere ligne du fichier texte correspond à l'indice 0 de la liste data
                String[] elements =data.get(a).split(";"); //Création d'un tableau pour chaque indice de 'data' avec chaque case du tableau un élèment se situant entre les ";"
                int index =Integer.parseInt(elements[0]);
                //System.out.println("index"+index);
                ligne_array.set(index, elements);// ajout à l'indice a le tableau créé et rempli juste avant.
                Revêtement revet = new Revêtement(elements,index);
                System.out.println("revet.toString()"+revet.toString());
                listeRevêtement.add(revet);
                revet.setId(b);
                b=b+1;
                System.out.println("REVETEMENT.TOSTRING()"+revet.toString());
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
    
    
    /**
     * Architecture_officille.lectureGénérale est une méthode simpliste
     * elle nécessite un traitement de données supplémentaire.
     * @param nom_fichier : String
     * @return ArrayList<String>
     */
    public static ArrayList<String> lectureGénérale(String nom_fichier){
        String ligne;                                   //chaîne de caractères pour enregistrer les lignes du document texte
        ArrayList <String> data = new ArrayList();       //Création de l'ArrayList qui sera utilisé pour récupérer le fichier lu dans la boucle WHILE
 
        try {                                           // Pour gérer les exceptions de fichiers : fichier non trouvé...
            BufferedReader entre =new BufferedReader(new FileReader(nom_fichier));
            while ((ligne = entre.readLine())!= null){
                System.out.println(ligne);            // on écrit la ligne dans le moniteur pour analyser ce que lit le bufferedReader
                data.add(ligne);                      //on ajoute la ligne lu par le BufferedReader à la liste qui s'appelle data
            }
            entre.close(); 
            return data;
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
    
    /**
     * méthode GENERIQUE d'écriture
     * @param nomDuFichier :String
     * @param donnéesAEnregistrerEnTexte : ArrayList<String>  : une arraylist dont chaque case sera une ligne du fichier texte 
     */
    public static void écriture(String nomDuFichier, ArrayList<String> donnéesAEnregistrerEnTexte){
        //p.ex: écriture("saveFile.txt", IDManager.imprimerLesObjetsCréés())
        try{
            BufferedWriter out =new BufferedWriter(new FileWriter(nomDuFichier,false)); //"false" : le fichier est écrasé et réécrit entièrement
            for (int i=0 ; i<donnéesAEnregistrerEnTexte.size() ; i++){
                out.write(donnéesAEnregistrerEnTexte.get(i));
                out.newLine();
            }
            out.close();
        }
        catch (IOException err){
            System.out.println("Erreur :\n"+err);
        }// TEST DECRITURE
        System.out.println("la sauvegarde a fonctionné");
    }
    /**
     * Une méthode pour enregistrer dans une fichier qui s'appelle "saveFile.txt" 
     * ce fichier se trouve dans C:\Users\"votre nom"\Documents\NetBeansProjects\Projet-officiel
     * (comme les fichiers texte révêtement)
     */
    public static void sauvegardeParDéfaut(){
        /*Coin coin3 = new Coin(300,22);
        Coin coin4 = new Coin(301,30);
        Coin coin5 = new Coin(302,28); 
        */ //c'était des tests
        ArrayList<String> listeDesObjetsCréés = IDManager.imprimerLaSauvegarde();
        écriture("saveFile.txt", listeDesObjetsCréés);
        //imprimerLesObjetsCréés() est une méthode qui lit toutes les valeurs (objets créés) de chaque clé (identifiants) de chaque mapIdVersTypeDeObjet dans IDManager.
        //elle place dans chaque case de l'ArrayList l'objet.toString() .
        
    }
    public static Architecture_officielle ouvrirBâtiment(String nom_fichier){
        Architecture_officielle bâtimentSauvegardé = null; //temporaire
                                                    //=IDManager.récupérerLesDonnéesEnregistrées(nom_fichier)
        System.out.println("ouverture du fichier de sauvegarde");
        return bâtimentSauvegardé;
    }
    
    /**
     * merci de ne pas faire de changement substantiel dans la syntaxe des toStringSauvegarde()
     * //////////Attention : cette syntaxe est utiulisée dans IDManager.récupérerUnMur() !!!!
     * //////////Si on change la syntaxe de mur.toStringSauvegarde(), il faut changer la méthode susdite.
     * @return String
     */
    public static String syntaxeToString(){
        return "#Syntaxe  : \"Bâtiment;ListeDesEtages (id)\"";
    }
    /**ceci est le toString() de sauvegarde.
    *MERCI DE NE PAS MODIFIER CETTE FONCTION sans me consulter
    * @return BâtimentEnString : String
    */
    public String toStringSauvegarde() {
        //"#Syntaxe  : \"Bâtiment;ListeDesEtages (id)"
        ArrayList<Integer> listeDesIdDesEtages = new ArrayList<>();
        for (int i = 0; i < this.liste_etage.size() ; i++){
            listeDesIdDesEtages.add(liste_etage.get(i).getId());
            //créée une liste des identifiants des murs qui forment la pièce
        }
        return "Bâtiment;" + listeDesIdDesEtages ;
    }
    
    @Override
    public String toString(){
        return "Bâtiment;" + this.liste_etage;
    }
        

//GET
    public ArrayList<Etage> getListe_etage() {
        return liste_etage;
    }

    public Etage getEtageActuel(){
        return this.étageActuel;
    }
//SET
    public void setEtageActuel(Etage étage){
        this.étageActuel = étage;
    }
    public void setContrôleur(Contrôleur contrôleur) {
        this.contrôleur = contrôleur;
    }
    public ArrayList<Revêtement> getListeRevêtement() {
        return listeRevêtement;
    }
    
    public  ArrayList<String[]> getDonnee_enregistree() {
        return donnee_enregistree;
    }

public static void main(String[] args) {
   /////////////LECTURE FICHIER. IL s'appelle Revêtement_test.txt
   System.out.println("lecture du fichier");
    
    //La méthode ci-dessous est utilisée surtout pour vider le main des tests menés.
    faireDesTests(new Architecture_officielle()); //une méthode qui permet de nettoyer le main. Voir plus haut : elle est utilisée pour regrouper les tests que l'on veut faire.
    
    // ATTENTION aux objets que vous créez dans le main... il y en a déjà pas mal de créé dans laucnhProgramm()...
    launchProgramm();
    
    
    
   
    
    
    
    }

}
