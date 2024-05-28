/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.util.*;
import java.util.Map.Entry;


/**
 * @version 1.1
 * @author thomas beverly
  branche de thomas
 */

public class IDManager {
    //Je veux une map pour chaque classe
    //Qui prend en entrée un object, et lui associe un identifiant
    //Dans les constructeurs des objets, on pourra assigner l'attribut id à la valeur associée à this, l'obejt en question.
    //Ainsi, dans le constructeur, l'attribut Id se verra assigner l'int associé à l'objet dans la map : "this.id=mapObjet.get(this)"
    //on pourra récupérer l'ID d'un objet grâce à obj.getId()
    //on crééra une méthode qui permet d'incrémenter l'ID dans le map (située à  l'intérieur de IDManager) voir p.ex. la méthode addId ci-dessous
    
    //pour le bâtiment
    static private Architecture_officielle bâtiment;
//pour les Appartments
    static private HashMap<Appartement,Integer> mapAppartement = new HashMap<>();
    static private TreeMap<Integer,Appartement> mapIdVersAppartement = new TreeMap<>();
    static int compteurAppartement = 0;
    
    
//pour les étages
    static public HashMap<Etage,Integer> mapEtage = new HashMap<>(); //pourquoi un hashmap? je ne sais pas. d'après ce que je comprends, on ne peut pas créer de "map = new Map<>()" (Map est abstraite et ne peut doc être instanciée, d'après le messsge de debug
    static private TreeMap<Integer,Etage> mapIdVersEtage = new TreeMap<Integer,Etage>();
    static int compteurEtage = 0;
    
//pour les pièces
    static public HashMap<Pièce,Integer> mapPièce = new HashMap<>();
    static private TreeMap<Integer,Pièce> mapIdVersPièce = new TreeMap<>();
    static int compteurPièce = 0;  //TODO faire une méthode où la pièce a le numéro de l'étage devant
    
//pour les murs
    static public HashMap<Mur,Integer> mapMur = new HashMap<>();
    static private TreeMap<Integer,Mur> mapIdVersMur = new TreeMap<>();
    static int compteurMur = 0;
    
//pour les coins
    static public HashMap<Coin,Integer> mapCoin = new HashMap<>();
    static private TreeMap<Integer,Coin> mapIdVersCoin = new TreeMap<>();
    static int compteurCoin = 0;
    
//pour les fenêtres
    static public HashMap<Fenêtre,Integer> mapFenêtre = new HashMap<>();
        static private TreeMap<Integer,Fenêtre> mapIdVersFenêtre = new TreeMap<>();
    static int compteurFenêtre = 0;
    
//pour les portes
    static public HashMap<Porte,Integer> mapPorte = new HashMap<>();
    static private TreeMap<Integer,Porte> mapIdVersPorte = new TreeMap<>();
    static int compteurPorte = 0;
    
    //pour les façade
    static public HashMap<Facade,Integer> mapFacade = new HashMap<>();
    static private TreeMap<Integer,Facade> mapIdVersFacade = new TreeMap<>();
    static int compteurFacade = 0;
//Pour manipuler
    //public ArrayList<HashMap<>> listeDesMapObjet = new ArrayList<>();
    //j'aurais voulu faire un tableau ou une arraylist des maps, publique, comme ça on peut appeler listesDesMapObjet[0], p.ex. 
    
    

    
    /* POURQUOI "STATIC" ? voir ci-dessous : 
    //excellente explication de static https://www.freecodecamp.org/news/static-variables-in-java/ 
    //"Whenever a variable is declared as static, this means there is only one copy of it for the entire class,
    rather than each instance (object of the class) having its own copy. 
    A static method means it can be called without creating an instance of the class. "
    c'est exactement ce qu'on recherche!! compteurID sera une varaible qui ne dépend pas des objets créés.
    */

    
    
    //CONSTRUCTOR
    //pas de constructeurs dans cette classe. On ajoute un id en appelant IDManager.newId(Etage etage) (ou mur, ou fenêtre...)
    public IDManager(){} //contructeur test mais INUTILISé (fait acte de présence)
    
    
    //FUNCTIONS
    /**
     * Cette fonction nécessite qu'un objet contrôleur ait été créé.
     * @return 
     */
    public static ArrayList<String> imprimerLaSauvegarde(){
        //DEVIS + SAUVEGARDE
        ArrayList<String> newList = new ArrayList<>();
        newList= imprimerLeDevis();
        newList.addAll(imprimerLesObjetsCréés());
        return newList;
    }
    public static ArrayList<String> imprimerLeDevis(){
        ArrayList<String> listeARenvoyer = new ArrayList<>();
        listeARenvoyer.add("=== DEVIS GENERAL===");
        String prixDuBat = String.valueOf(bâtiment.prixTotal());
        listeARenvoyer.add("#PRIX TOTAL : " + prixDuBat + "€");
        for (String text : bâtiment.prixParRevêtement()){
            listeARenvoyer.add("#" + text);
        }
        return listeARenvoyer;
    }
    public static ArrayList<String> imprimerLesObjetsCréés(){
        ArrayList<String> listeARenvoyer = new ArrayList<>();
        
        listeARenvoyer.add("\n===DONNEES SAUVEGARDEES===");
        if (! mapIdVersCoin.isEmpty()){ //si la map n'est PAS vide, alors:
            listeARenvoyer.add("\n=== liste des Coins ===");
            listeARenvoyer.add(Coin.syntaxeToString());
            // \" permet de mettre des guillemets dans le System.out.print()
            for (int i = 0;i<=mapIdVersCoin.lastKey();i++){
                String coinAEnregistrer = mapIdVersCoin.get(i).toStringSauvegarde(); 
                //coinAEnregistrer est la version toStringSauvegarde() du coin dont l'id est "i"
                listeARenvoyer.add(coinAEnregistrer);
            } //cete boucle "for" ajoute à listeARenvoyer tous les objets Coin qui ont été créés.
        }
        
        if (!mapIdVersFacade.isEmpty()){
            listeARenvoyer.add("\n=== liste des Facades ===");
            listeARenvoyer.add(Facade.syntaxeToString());

            for (int i = 0;i<=mapIdVersFacade.lastKey();i++){
                String facadeAEnregistrer = mapIdVersFacade.get(i).toStringSauvegarde(); 
                //MurAEnregistrer est la version toStringSauvegarde() du mur dont l'id est "i"
                listeARenvoyer.add(facadeAEnregistrer);
            } //cete boucle "for" ajoute à listeARenvoyer tous les objets Mur qui ont été créés.
        }
        if (! mapIdVersMur.isEmpty()){ //si la map n'est PAS vide, alors:
            listeARenvoyer.add("\n=== liste des Murs ===");
            listeARenvoyer.add(Mur.syntaxeToString());

            for (int i = 0;i<=mapIdVersMur.lastKey();i++){
                String murAEnregistrer = mapIdVersMur.get(i).toStringSauvegarde(); 
                //MurAEnregistrer est la version toStringSauvegarde() du mur dont l'id est "i"
                listeARenvoyer.add(murAEnregistrer);
            } //cete boucle "for" ajoute à listeARenvoyer tous les objets Mur qui ont été créés.
        }
        if (! mapIdVersPièce.isEmpty()){ //si la map n'est PAS vide, alors:
            listeARenvoyer.add("\n=== liste des Pièces ===");
            listeARenvoyer.add(Pièce.syntaxeToString()); //syntaxe que l'on peut mettre à jour
            //Syntaxe actuelle : "\"Pièce;id;nom_pièce;liste_mur(identifiants);revêtementDuSol;revêtementDuPlafond;idDuAppartement \""
            for (int i = 0;i<=mapIdVersPièce.lastKey();i++){
                String pièceAEnregistrer = mapIdVersPièce.get(i).toStringSauvegarde(); 
                //pièceAEnregistrer est la version toStringSauvegarde() de la pièce dont l'id est "i"
                listeARenvoyer.add(pièceAEnregistrer);
            } //cete boucle "for" ajoute à listeARenvoyer tous les objets Pièce qui ont été créés.
        }
        
        if (! mapIdVersAppartement.isEmpty()){ //si on a effectivement créé des étages
             listeARenvoyer.add("\n=== liste des Appartements ===");
             listeARenvoyer.add(Appartement.syntaxeToString());
             //actuellement : "Syntaxe : \"Appartement;id;IdDuEtage;liste_pièce\""
            for (int i = 0;i<=mapIdVersAppartement.lastKey();i++){
                String appartementAEnregistrer = mapIdVersAppartement.get(i).toStringSauvegarde(); 
                //appartementAEnregistrer est la version toStringSauvegarde() de l'appartement dont l'id est "i"
                listeARenvoyer.add(appartementAEnregistrer);
            } //cete boucle "for" ajoute à listeARenvoyer tous les objets Appartement qui ont été créés.
        }
        
        if (! mapIdVersEtage.isEmpty()){ //si on a effectivement créé des étages
            listeARenvoyer.add("\n=== liste des Etages ===");
            listeARenvoyer.add(Etage.syntaxeToString());
            //actuellemnt : "Syntaxe : \"Etage;id;hauteur_etage;liste_appartement\""
            for (int i = 0;i<=mapIdVersEtage.lastKey();i++){
                String étageAEnregistrer = mapIdVersEtage.get(i).toStringSauvegarde(); 
                //étageAEnregistrer est la version toStringSauvegarde() de l'étage dont l'id est "i"
                listeARenvoyer.add(étageAEnregistrer);
            } //cete boucle "for" ajoute à listeARenvoyer tous les objets Etage qui ont été créés.
        }
        listeARenvoyer.add("\n===Le bâtiment===");
        listeARenvoyer.add(IDManager.bâtiment.toStringSauvegarde());
        
        //j'imaginais le fichier txt de sauvegarde ainsi : 
        //Tous les coins
        //Tous les murs (pour plus de lisibilité, mur.toStringSauvegarde() devrait renvoyer seulement les id des coins composant le mur, plus les revêtements)
        //Tous les pièces (pour plus de lisibilité, pièce.toStringSauvegarde() devrait renvoyer seulement les id des murs composant la pièce)
        //Tous les appartements (pour plus de lisibilité, appartement.toStringSauvegarde() devrait renvoyer seulement les id des pièces composant l'appartement)
        //Tous les Etages (pour plus de lisibilité, etage.toStringSauvegarde() devrait renvoyer seulement les id des appartements composant l'étage)
        
        //Et ensuite une fonction dans IDManager qui remplisse les maps en lisant le fichier texte
        //Peut-être devrait-on écrire les objet.toStringSauvegarde() avec des points virgules pour être lus par "lecture"
       return listeARenvoyer;
    }
    
    public static Architecture_officielle récupérerLesDonnéesEnregistrées(String nom_fichier){
        IDManager.clearEverything(); //efface tout le contenu de IDManaeger (les maps) et de la mémoire (dans l'idéal : System.gc en fait à sa tête...)
        System.out.println("\nentrée dans récupérerLesDonnéesEnregistrées");
        Architecture_officielle résultatARenvoyer = new Architecture_officielle();
        //le construcuteur de Archit_offi lis automatiquement "Revêtement_final.txt" et le met dans Architecture_officielle.donnee_enregistree (publique, static)
        ArrayList<String> donnéesATraiter = Architecture_officielle.lectureGénérale(nom_fichier);
        for (String ligneATraiter : donnéesATraiter){
            if ( ! (ligneATraiter.startsWith("#")) && ( ! ligneATraiter.startsWith("="))  && ( ! ligneATraiter.isEmpty())){ //isEmpty pour traiter les retous à la ligne
                //"si la ligne est une ligne de sauvegarde et non d'information"
                String[] objetEnString =  ligneATraiter.split(";");
                String typeDeObjet = objetEnString[0];
                switch (typeDeObjet){
                    case "Coin" : 
                        récupérerUnCoin(objetEnString);
                        break;
                    case "Facade" : 
                        récupérerUneFacade(objetEnString);
                        break;
                    case "Mur":
                        récupérerUnMur(objetEnString);
                        break;
                    case "Pièce":
                        récupérerUnePièce(objetEnString);
                        break;
                    case "Appartement":
                        récupérerUnAppartement(objetEnString);
                        break;
                    case "Etage":
                        récupérerUnEtage(objetEnString);
                    default :
                        break;
                }
            
            }
        }
        ArrayList<String> données = imprimerLesObjetsCréés();
            for (String ligne : données){
                System.out.println(ligne);
            }//imrpimer dans la console la sauvegarde : ce qui devrait s'afficher, en somme...
        return résultatARenvoyer;
    }
    
    /**
     * prend en entrée un tableau String[], dont les cases sont les éléments séparés par ";"
     * (Coin;id;x;y) 
     * crée le coin avec les données fournies
     * @param coinEnString - String[]
     */
    public static void récupérerUnCoin(String[] coinEnString){
        //au 07/05/24 : "#Syntaxe  : \"Coin;id;x;y\""
        int idSouhaité= parseInt(coinEnString[1]);
        double x = parseDouble(coinEnString[2]);
        double y = parseDouble(coinEnString[3]);
        int idQuiSeraCréé = compteurCoin;
        System.out.println("Création du coin dont l'id est " + idSouhaité);
       
        Coin coinACréer = new Coin(x,y); //crée un coin dont l'identifiant est compteurCoin
        System.out.println("nouveau coin, id devrait être : "+idSouhaité);
        System.out.println("l'id est " + coinACréer.getId());
        if (idQuiSeraCréé != idSouhaité){
            //en créant automatiquement son identifiant, coinACréer a peut-être prit l'identifiant d'un autre coin.
            //Cet autre coin est coinEcrasé, sont identifiant était idQuiSeraCréé
            mapCoin.replace(coinACréer,idSouhaité); //on assigne à coinACréer l'identifiant voulu
            //coinEcrasé est toujours lié vers idQuiSeraCréé dans mapCoin
            
            Coin coinEcrasé = mapIdVersCoin.get(idQuiSeraCréé);
            mapIdVersCoin.put(idQuiSeraCréé,coinEcrasé); //met coinEcrasé sur son id originel
            mapIdVersCoin.put(idSouhaité,coinACréer); // met coinACréer sur le bon id.
            System.out.println("Changements fait. id devait être"+idSouhaité);
            System.out.println("it est maintenant"+mapCoin.get(coinACréer));

        }
    }
    public static void récupérerUneFacade(String[] facadeEnString){
        //"#Syntaxe  : \"Facade;id;idDuCoinDebut;idDuCoinFin;idDuRevêtement;liste_ouverture"
        int idSouhaité = parseInt(facadeEnString[1]);
        int idDuCoinDebut = parseInt(facadeEnString[2]);
        int idDuCoinFin = parseInt(facadeEnString[3]);
        int idDuRevêtement = parseInt(facadeEnString[4]);
        
        System.out.println("création de nouvlle facade. id souhaité : " + idSouhaité + ", id créé : " + compteurFacade);
        Coin coinDebut = IDManager.getObjetCoin(idDuCoinDebut);
        Coin coinFin = IDManager.getObjetCoin(idDuCoinFin);
        Revêtement rev = bâtiment.getListeRevêtement().get(idDuRevêtement);
        Facade nouvelleFacade = new Facade(coinDebut, coinFin);
        nouvelleFacade.setRevêtement(rev);
        
        facadeEnString[5] = facadeEnString[5].replaceAll("\\[", "");
        facadeEnString[5] = facadeEnString[5].replaceAll("\\]", "");
        if ( ! facadeEnString[5].equals("")){
            String[] listeDesOuvertures = facadeEnString[5].split(",");
            //une liste est naturellement séparée par des virgules plutôt que des points-virgules.

            for (String ouvertureEnString : listeDesOuvertures){
                récupérerUneOuverture(ouvertureEnString); //le constructeur de Ouverture appelle autpmatiquement facade.getListe_ouverture.add(this)
            }
        }
        System.out.println("facade crée : " + nouvelleFacade);
        
        //ATTENTION! il faudra faire un facade.setEtage dans la fonction récupérerUnEtage pour tous les étages!!
    }
    
    public static void récupérerUnMur(String[] murEnString){
        //"#Syntaxe  : \"Mur;id;idDuCoinDebut;idDuCoinFin;idDuRevêtement;liste_ouverture"
        System.out.println(Arrays.toString(murEnString));
        int idSouhaité = parseInt(murEnString[1]);
        int idDuCoinDebut = parseInt(murEnString[2]);
        int idDuCoinFin = parseInt(murEnString[3]);
        int idDuRevêtement = parseInt(murEnString[4]);
        
        System.out.println("création de nouveau mur. id souhaité : " + idSouhaité + ", id créé : " + compteurMur);
        Coin coinDebut = IDManager.getObjetCoin(idDuCoinDebut);
        Coin coinFin = IDManager.getObjetCoin(idDuCoinFin);
        Revêtement rev = bâtiment.getListeRevêtement().get(idDuRevêtement);
        Mur nouveauMur = new Mur(coinDebut, coinFin);
        nouveauMur.setRevêtement(rev);
        
        //PUNAISE DE REGEX IL FAUT SEPARER MURENSTRING[5] mais il faut aussi lui ajouter les autres éléements...
        //car ouverture.toStringSauvegarde sépare les éléments avec des ";", donc le "split" dans "récupérerLesDonnéesEnregistrées"
        //ne marche pas. 
        //IDEE DE SOLUTION : remplacer dans ouverrture.toStringSauvegarde() les ";" par des ":" ou des "-", que sais-je....
        System.out.println("murEnString[5] : " + murEnString[5]);
        
        murEnString[5] = murEnString[5].replaceAll("\\[", "");
        murEnString[5] = murEnString[5].replaceAll("\\]", "");
        
        if (! murEnString[5].equals("")){
            if ( !  murEnString[5].contains(", ")){
                murEnString[5] = murEnString[5] + ", ";
            } //sinon le regex ne fonctionne pas 
            
            String[] listeDesOuvertures = murEnString[5].split(", ");
            
            //une liste est naturellement séparée par des virgules plutôt que des points-virgules.
            System.out.println("liste ouvertures : " + Arrays.toString(listeDesOuvertures));
            if ( listeDesOuvertures.length != 0){
                for (String ouvertureEnString : listeDesOuvertures){
                récupérerUneOuverture(ouvertureEnString); //le constructeur de Ouverture appelle autpmatiquement mur1.getListe_ouverture.add(this)
                }
            }
            System.out.println("mur créé : " + nouveauMur);
        }
        
        //ATTENTION! il faudra fait un mur.setEtage dans la fonction récupérerUnEtage pour tous les étages!!
    }
    
    /**
     * création de l'ouverture décrite par ouvertureEnString. 
     * l'ouverture assigne s'assigne elle-m^me automatiquement à  son murPropiétaire 
     * @param ouvertureEnString 
     */
    public static void récupérerUneOuverture(String ouvertureEnString){
        System.out.println(ouvertureEnString);
        String[] elements = ouvertureEnString.split(":");
        //"#Syntaxe : \"Ouverture;ori_x;ori_y;orientation(char);longueur;idDuMur1\"";
        //"#un 'F' avant l'id d'un mur signifie que c'est une façade"
        System.out.println("ouverture : " + Arrays.toString(elements));
        double ori_x = parseDouble(elements[1]);
        double ori_y = parseDouble(elements[2]);
        char orientation = elements[3].charAt(0);
        double longueur = parseDouble(elements[4]);
        int idDuMur1 ;
        Mur mur1;
        Facade facade;
        if (elements[5].startsWith("F")){
            elements[5] = elements[5].replaceAll("F", "");
            idDuMur1 = parseInt(elements[5]);
            facade = IDManager.getObjetFacade(idDuMur1);
            if (longueur == 1.20){
                Fenêtre fenACréer = new Fenêtre(ori_x, ori_y, orientation, facade);
                System.out.println("log : création de " + fenACréer.toString());
            }else if(longueur == 0.90){
                Porte porteACréer = new Porte(ori_x, ori_y, orientation, facade);
                System.out.println("log : création de " + porteACréer.toString());
            }else{
                System.out.println("erreur dans la longueur. Aucune ouverture n'a été créée pour le mur F" + idDuMur1 );
            }
            System.out.println("la façade devrait désormais avoir une nouvelle ouverture : " + facade);
        }else{
            idDuMur1 = parseInt(elements[5]);
            mur1 = IDManager.getObjetMur(idDuMur1);
            if (longueur == 1.20){
                Fenêtre fenACréer = new Fenêtre(ori_x, ori_y, orientation, mur1);
                System.out.println("log : création de " + fenACréer.toString());

            }else if(longueur == 0.90){
                Porte porteACréer = new Porte(ori_x, ori_y, orientation, mur1);
                System.out.println("log : création de " + porteACréer.toString());
            }else{
                System.out.println("erreur dans la longueur fournie. Donc Aucune ouverture n'a été créée pour le mur " + idDuMur1 );
            }
            System.out.println("le mur devrait désormais avoir une nouvelle ouverture : " + mur1);

        }
        
                
    }
    public static void récupérerUnePièce(String[] pièceEnString){
        //"#Syntaxe : \"Pièce;id;nom_pièce;liste_mur(identifiants);revêtementDuSol;revêtementDuPlafond\""
        Pièce nouvellePièce = null;
        int idSouhaité = parseInt(pièceEnString[1]);
        System.out.println("id de pièce souhaité : " + idSouhaité + "id réel : " + compteurPièce);
        String nomPièce = pièceEnString[2];
        String stringDeLaListeMur = pièceEnString[3];
        stringDeLaListeMur = stringDeLaListeMur.replaceAll("liste_mur=\\[", "");
        stringDeLaListeMur = stringDeLaListeMur.replaceAll("\\]", "");
        Mur mur1 = null;
        Mur mur2 = null;
        Mur mur3 = null;
        Mur mur4 = null;
        int idDuRevSol = parseInt(pièceEnString[4]);
        int idDuRevPlafond = parseInt(pièceEnString[5]);
        Revêtement revSol = bâtiment.getListeRevêtement().get(idDuRevSol);
        Revêtement revPlafond = bâtiment.getListeRevêtement().get(idDuRevPlafond);
        Mur[] listeDesMurs = {mur1,mur2,mur3,mur4};
        if (! stringDeLaListeMur.equals("")){
            String [] listeDesMursTexte = stringDeLaListeMur.split(", ");
            for (int i = 0;i<4; i++){
                int idDuMur = parseInt(listeDesMursTexte[i]);
                listeDesMurs[i] = IDManager.getObjetMur(idDuMur);
                
            }
            nouvellePièce = new Pièce(listeDesMurs[0], listeDesMurs[1], listeDesMurs[2], listeDesMurs[3]);
            nouvellePièce.setNom_pièce(nomPièce);
            nouvellePièce.getSol().setRevêtement(revSol);
            nouvellePièce.getPlafond().setRevêtement(revPlafond);
            //à la création de la pièce, le sol et le plafond sont de revêtement "Standard" (index 0) 
            //dans récupérerUnEtage, il faudra faire mur.setPièce1 pour tous les murs de chaque pièce.
        }else{
            System.out.println("erreur : la pièce n'a pas ou pas assez de murs. il en fallait 4...");
        }
         System.out.println("création de la pièce, dont l'id devait être : " + idSouhaité + " et dont l'id est finalement " + nouvellePièce.getId() + ". la pièce est " + nouvellePièce.toString() );
    }
    public static void récupérerUnAppartement(String[] appartementEnString){
        //"#Syntaxe : \"Appartement;id;liste_pièce(identifiants)\""
        Appartement nouvelAppartement = null;
        int idSouhaité = parseInt(appartementEnString[1]);
        String stringListeDesPièces = appartementEnString[2];
        stringListeDesPièces = stringListeDesPièces.replaceAll("liste_pièce=\\[", "");
        stringListeDesPièces = stringListeDesPièces.replaceAll("\\]", "");
        if ( ! stringListeDesPièces.equals("")){
            ArrayList<Pièce> listeDesPièces = new ArrayList<>();
            String[] listeDesPiècesTexte = stringListeDesPièces.split(", ");
            for (int i = 0; i < listeDesPiècesTexte.length; i++){
                int numéro = parseInt(listeDesPiècesTexte[i]);
                Pièce pièce= IDManager.getObjetPièce(numéro);
                listeDesPièces.add(pièce);
                pièce.setAppartement(nouvelAppartement);
            }
            nouvelAppartement = new Appartement(listeDesPièces);
            //il faudra ajouter manuellemnt l'étage.
        }else{
            System.out.println("erreur : un appartement a forcément des pièces");
        }
        System.out.println("création de l'appartement, dont l'id devait être : " + idSouhaité + " et dont l'id est finalement " + nouvelAppartement.getId() + ". la pièce est " + nouvelAppartement.toString() );

    }
    public static void récupérerUnEtage(String[] étageEnString){
        //"#Syntaxe : \"Etage;id;hauteur_etage; liste_mur_facade (id);liste_appartement (id) ;listPiècesOrphelines (id);listeMurOrphelin (id)\""
        Etage nouvelEtage = null;
        int idSouhaité = parseInt(étageEnString[1]);
        double hauteur_etage = parseDouble(étageEnString[2]);
        String stringListe_mur_facade = étageEnString[3];
        String stringListe_appartement = étageEnString[4];
        String stringListePièceOrphelines = étageEnString[5];
        String stringListeMurOrphelin = étageEnString[6];
        
        stringListe_mur_facade = stringListe_mur_facade.replaceAll("\\[", "");
        stringListe_mur_facade = stringListe_mur_facade.replaceAll("\\]", "");
        stringListe_appartement = stringListe_appartement.replaceAll("\\[", "");
        stringListe_appartement = stringListe_appartement.replaceAll("\\]", "");
        stringListePièceOrphelines = stringListePièceOrphelines.replaceAll("\\[", "");
        stringListePièceOrphelines = stringListePièceOrphelines.replaceAll("\\]", "");
        stringListeMurOrphelin = stringListeMurOrphelin.replaceAll("\\[", "");
        stringListeMurOrphelin = stringListeMurOrphelin.replaceAll("\\]", "");
        
        if ( ! stringListe_mur_facade.equals("")){
            nouvelEtage = new Etage(hauteur_etage, bâtiment);
            
            String [] listeMur_FacadeTexte = stringListe_mur_facade.split(", ");
            
            for (int i = 0;i<4; i++){
                int numéro = parseInt(listeMur_FacadeTexte[i]);
                Facade facade = getObjetFacade(i);
                nouvelEtage.getListe_mur_facade().add(facade);
                facade.getListe_étage().add(nouvelEtage);
                
            }
            if ( ! stringListe_appartement.equals("")){
                String [] listeDesAppartmentsTexte = stringListe_appartement.split(", ");
                //si la liste des appartments n'est pas vide
                for (int i=0 ; i<listeDesAppartmentsTexte.length ; i++){
                    int numéro = parseInt(listeDesAppartmentsTexte[i]);
                    Appartement appart = getObjetAppartement(numéro);
                    appart.setEtage(nouvelEtage);
                    nouvelEtage.getListe_appartement().add(appart);
                    for (Pièce pièce : appart.getListe_pièce()){
                        pièce.setÉtage(nouvelEtage);
                        for (Mur mur : pièce.getListe_mur()){
                            mur.setÉtage(nouvelEtage);
                            mur.setPièce1(pièce); //setPièce1 vérifie si mur
                        }
                    }
                }
            }
            if (! stringListePièceOrphelines.equals("")){
                String [] listeDesPiècesOphelinesTexte = stringListePièceOrphelines.split(", ");
                for (int i=0; i<listeDesPiècesOphelinesTexte.length ; i++){
                    int numéro = parseInt(listeDesPiècesOphelinesTexte[i]);
                    Pièce pièce = getObjetPièce(numéro);
                    pièce.setÉtage(nouvelEtage);
                    nouvelEtage.getListPièceOrpheline().add(pièce);
                    for (Mur mur : pièce.getListe_mur()){
                        mur.setÉtage(nouvelEtage);
                        mur.setPièce1(pièce);
                    }
                }
            }
            if (! stringListeMurOrphelin.equals("")){
                String [] listeDesMursOrphelins = stringListeMurOrphelin.split(", ");
                for (int i = 0; i < listeDesMursOrphelins.length ; i++){
                    int numéro = parseInt(listeDesMursOrphelins[i]);
                    Mur mur = getObjetMur(numéro);
                    mur.setÉtage(nouvelEtage);
                    nouvelEtage.getListMurOrphelin().add(mur);
                }
            }          
        }else{
            System.out.println("erreur : étage doit avoir des façades.");
        }
        System.out.println("l'étage "+ nouvelEtage.getId() + ", qui devait avoir un id de "+ idSouhaité + ", a été créé : "+ nouvelEtage.toString());
    }

    
    
    /**
     * 
     * IDManager.newId(objet)
     * @param un objet
     * @return un identifiant (int)
     */
    //j'avais en tête la structure suivante, mais soit instanceof ne fonctionne pas, soit "Object can't be converted to Etage"...
    /*
    public static int newId(Object obj){
        if (obj instanceof Etage){  
        mapEtage.put(obj,compteurEtage);
        compteurEtage++;
        return mapEtage.get(obj);
        }
    //répéter le test
    }
    */
    //sauf que ça ne fonctionne pas. pour l'instant, il faut faire chque classse manuellement. Ce qui est fait ci-dessous.
    
    public static int newId(Appartement appartement){
        int idDuAppart = compteurAppartement;   
        mapAppartement.put(appartement,idDuAppart);
        mapIdVersAppartement.put(idDuAppart, appartement);
        compteurAppartement++;
        return idDuAppart;//renvoie l'id de l'appartement. on aurait pu utiliser ici "compteurAppartement-1"
    }
    
    public static int newId(Etage etage){
        mapEtage.put(etage,compteurEtage); //rdc : 0 , 1er étage : 1 ,etc. 
        mapIdVersEtage.put(compteurEtage, etage);
        compteurEtage++;
        return compteurEtage-1; //renvoie l'id associé à l'objet etage
    }
        public static int newId(Facade facade){
            //il y a aura au maximum quatre facades
            if (compteurFacade > 4){
                throw new Error("il ne peut y avoir que quatre facades.");
                //avec throw new Error, je crois que le progrmame continue par ailleurs de tourner.
            }else{
                mapFacade.put(facade,compteurFacade);  
                mapIdVersFacade.put(compteurFacade, facade);
                compteurFacade++;
                return compteurFacade-1; //renvoie l'id associé à l'objet etage
            }
    }
    public static int newId(Pièce pièce){
        int idDeLaPièce = compteurPièce ;//+ 1000* pièce.getEtage().getId(); //étage auquel la pièce se trouve
//ainsi, pour l'étage ézéro, 0<=idDeLaPièce<=999 ; pour l'étage 1, 1000<= idDeLaPièce <=1999 ,etc.
        mapPièce.put(pièce,idDeLaPièce);
        mapIdVersPièce.put(idDeLaPièce,pièce);
        compteurPièce++;
        return idDeLaPièce;
    }
    
    public static int newId(Mur mur){
        int idDuMur = compteurMur ;//+ 1000 * mur.getÉtage().getId();  
//ainsi, pour l'étage zéro, 0<=idDuMur<=999 ; pour l'étage 1, 1000<= idDuMur <=1999 ,etc.
        mapMur.put(mur,idDuMur);
        mapIdVersMur.put(idDuMur,mur);
        compteurMur++;
        return idDuMur;
    }
    
    public static int newId(Coin coin){
        //TODO : à réfléchir : Peut-être coin devrait il utiliser un compteru similaire à mur? (id supérieur à 1000 pour le 1er étage...)
        mapCoin.put(coin,compteurCoin);
        mapIdVersCoin.put(compteurCoin,coin);
        compteurCoin++;
        return compteurCoin-1;
    }
    
    //il n'y a pas d'Id pour les ouvertures actuellement. Les méthodes ci-dessous n'ont donc pas d'utilité à ce jour (12/04/24)
    public static int newId(Fenêtre fenêtre){
        mapFenêtre.put(fenêtre,compteurFenêtre);
        mapIdVersFenêtre.put(compteurFenêtre,fenêtre);
        compteurFenêtre++;
        return compteurFenêtre-1;
    }
    
    public static int newId(Porte porte){
        mapPorte.put(porte,compteurPorte);
        mapIdVersPorte.put(compteurPorte,porte);
        compteurPorte++;
        return compteurPorte-1 ;
    }
    
    public static void clearEverything(){
        //pour le bâtiment
    bâtiment = null;
    for (Entry entry : mapIdVersAppartement.entrySet()){
        entry=null; //cela permet de mettre tous les objets contenus dans la map à null, afin que le gaarbage collecor (System.gc() ) repère ces éléments inutiles dans la RAM
    }
    mapAppartement.clear();
    mapIdVersAppartement.clear();
    compteurAppartement = 0;
    
    for (Entry entry : mapIdVersEtage.entrySet()){
        entry=null; //cela permet de mettre tous les objets contenus dans la map à null, afin que le gaarbage collecor (System.gc() ) repère ces éléments inutiles dans la RAM
    }
    mapEtage.clear();
    mapIdVersEtage.clear();
    compteurEtage = 0;
    
    for (Entry entry : mapIdVersPièce.entrySet()){
        entry=null; //cela permet de mettre tous les objets contenus dans la map à null, afin que le gaarbage collecor (System.gc() ) repère ces éléments inutiles dans la RAM
    }
    mapPièce.clear();
    mapIdVersPièce.clear();
    compteurPièce = 0; 
    
    for (Entry entry : mapIdVersMur.entrySet()){
        entry=null; //cela permet de mettre tous les objets contenus dans la map à null, afin que le gaarbage collecor (System.gc() ) repère ces éléments inutiles dans la RAM
    }
    mapMur.clear();
    mapIdVersMur.clear();
    compteurMur = 0;
    
    for (Entry entry : mapIdVersCoin.entrySet()){
        entry=null; //cela permet de mettre tous les objets contenus dans la map à null, afin que le gaarbage collecor (System.gc() ) repère ces éléments inutiles dans la RAM
    }
    mapCoin.clear();
    mapIdVersCoin.clear();
    compteurCoin = 0;
    
    mapFenêtre.clear();
    mapIdVersFenêtre.clear();
    compteurFenêtre = 0;
    
    mapPorte.clear();
    mapIdVersPorte.clear();
    compteurPorte = 0;
    
    for (Entry entry : mapIdVersFacade.entrySet()){
        entry=null; //cela permet de mettre tous les objets contenus dans la map à null, afin que le gaarbage collecor (System.gc() ) repère ces éléments inutiles dans la RAM
    }
    mapFacade.clear();
    mapIdVersFacade.clear();
    compteurFacade = 0;
    
    System.gc();
    }
    
    //GET
    
    //Méthodes getObjetTyped'objet(idDeL'objet) : 
    //ces méthodes permettraient de récupérer un objet en fournissant un id. 
    //Elles pourraient ^tre utile dans l'écriture d'un fichier de sauvegarde
    
    /** appel : IDManager.getObjetAppartement(identifiant);
     * @param IdDuAppartement : id de l'objet que l'on veut obtenir
     * @return l'objet associé à l'id fourni.
     */
    public static Appartement getObjetAppartement(int IdDuAppartement){
        return mapIdVersAppartement.get(IdDuAppartement); //renvoie l'ojet de type étage associé à l'identifiant IdDuAppartement
    }
    /** appel : IDManager.getObjetEtage(identifiant);
     * @param IdDuEtage : id de l'objet que l'on veut obtenir
     * @return l'objet associé à l'id fourni.
     */
    public static Etage getObjetEtage(int IdDuEtage){
        return mapIdVersEtage.get(IdDuEtage); //renvoie l'ojet de type étage associé à l'identifiant IdDuEtage
    }
       /** appel : IDManager.getObjetFacade(identifiant);
     * @param IdDuFacade : id de l'objet que l'on veut obtenir
     * @return l'objet associé à l'id fourni.
     */
    public static Facade getObjetFacade(int IdDuFacade){
        return mapIdVersFacade.get(IdDuFacade); //renvoie l'ojet de type étage associé à l'identifiant IdDuEtage
    }
    
    /** appel : IDManager.getObjetPièce(identifiant);
     * @param IdDuPièce : id de l'objet que l'on veut obtenir
     * @return l'objet associé à l'id fourni.
     */
    public static Pièce getObjetPièce(int IdDuPièce){
        return mapIdVersPièce.get(IdDuPièce); //renvoie l'ojet de type étage associé à l'identifiant IdDuPièce
    }
    /** appel : IDManager.getObjetMur(identifiant);
     * @param IdDuMur : id de l'objet que l'on veut obtenir
     * @return l'objet associé à l'id fourni.
     */
    public static Mur getObjetMur(int IdDuMur){
        return mapIdVersMur.get(IdDuMur); //renvoie l'ojet de type étage associé à l'identifiant IdDuMur
    }
    /** appel : IDManager.getObjetCoin(identifiant);
     * @param IdDuCoin : id de l'objet que l'on veut obtenir
     * @return l'objet associé à l'id fourni.
     */
    public static Coin getObjetCoin(int IdDuCoin){
        return mapIdVersCoin.get(IdDuCoin); //renvoie l'ojet de type étage associé à l'identifiant IdDuCoin
    }
    /** appel : IDManager.getObjetFenêtre(identifiant);
     * @param IdDuFenêtre : id de l'objet que l'on veut obtenir
     * @return l'objet associé à l'id fourni.
     */
    
    //Porte et Fenêtre : A supprimmer?
    public static Fenêtre getObjetFenêtre(int IdDuFenêtre){
        return mapIdVersFenêtre.get(IdDuFenêtre); //renvoie l'ojet de type étage associé à l'identifiant IdDuFenêtre
    }/** appel : IDManager.getObjetPorte(identifiant);
     * @param IdDuPorte : id de l'objet que l'on veut obtenir
     * @return l'objet associé à l'id fourni.
     */
    public static Porte getObjetPorte(int IdDuPorte){
        return mapIdVersPorte.get(IdDuPorte); //renvoie l'ojet de type étage associé à l'identifiant IdDuPorte
    }
   //Je ne sais pas si c'est utile pour Fenêtre et     
    //getObjetFenêtre
    //getObjetPorte
    
    
    //TODO : faire des méthodes permettant de manipuler la map (en fonction de nos besoins)
    //les maps sont actuellement en privé
    
    /**
     * UTILITÉ : vérifier si la mapObjet d'un typeDeObjet contient l'identifiant IdARechercher
     * cette méthode aurait put être séparée en plusieurs méthodes indépendantes 
     * (comme c'est le cas pour la méthode newId(objet))
     * @param IdARechercher l'int dont on veut vérifier l'existence
     * @param typeDeObjet écrire l'objet dont on recherche l'ID : 'Etage', 'Pièce', 'Mur', 'Coin' , 'Fenêtre', 'Porte'
     * @return TRUE si IdARechchercher a déjà été créé (de manière automatique) pour l'objet spécifié (typeDeObjet), FALSE sinon (et par défaut)
     */
    public static boolean mapContainsValue(int IdARechercher, String typeDeObjet){ //cette méthode permet de chercher dans une map une valeur d'identifiant
        boolean résultat = false;
        switch (typeDeObjet){
            
            case "Etage": //TODO : idée : Ecrire le test tel que la fonction comprenne les paramètres Etage, Étage, étage, etage, etc. pour plus de facilité d'usage? (case-insensitive test)
                résultat = mapEtage.containsValue(IdARechercher);
                break;
            //TODO : case "Appartement"
            case "Pièce":
                 résultat = mapPièce.containsValue(IdARechercher);
                 break;
            case "Mur":
                 résultat =  mapMur.containsValue(IdARechercher);
                 break;
            case "Coin":
                 résultat =  mapCoin.containsValue(IdARechercher);
                 break;
            case "Fenêtre":
                 résultat =  mapFenêtre.containsValue(IdARechercher);
                 break;
            case "Porte":
                 résultat =  mapPorte.containsValue(IdARechercher);
                 break;
            default : 
                System.out.println("vous n'avez pas entré un typeDeObjet valide en paramètre formel");
                résultat = false;
                break;
        }
        
        return résultat; //si on entre pas un typeDeObket valide, cela renvoie flase
    }    
    
    /**
     * cette méthode renvoie en ArrayList toutes les pièces de l'étage en paramètre, quelles soient orphelines ou non.
     * @param étageActuel
     * @return toutesLesPiècesDeCetEtage : ArrayList<Pièce>
     */
    public static ArrayList<Pièce> toutesLesPièces(Etage étageActuel){
        ArrayList<Pièce> resultat = new ArrayList<>();
        
        //mapIdVersPièce.forEach(action); 
                            //CTRL+espace sur "action" donne la suggestion : 
                            //"(? super Integer t, ? super Pièce u) -> expr.void)"
        mapIdVersPièce.forEach((t, u) -> {//t est un Integer, u une pièce
            if (getObjetPièce(t).getEtage()==étageActuel){ //apparemment, " ==" a l'air de fonctionner.
                resultat.add(u);
            }
        });
            
        return resultat;
    }
    
    
    
    //SET
    //pas de "set" car cela dérèglerait le compteur automatique
    
    //mais en revanche...
    public static void setBâtiment(Architecture_officielle leBâtiment){
        IDManager.bâtiment = leBâtiment;
    }
    
    
    
    public static void main(String[] args){
        

         Architecture_officielle batiment = new Architecture_officielle();
        
        Etage e1 = new Etage(2);
        Coin ext1 = new Coin(20,25);
        Coin ext2 = new Coin(30,25);
        Coin ext3 = new Coin(30, 20);
        Coin ext4 = new Coin(20,20);
        
        Facade f1= new Facade(ext1,ext2);   
                e1.add(f1);
        Facade f2= new Facade(ext2,ext3); 
                 e1.add(f2);
        Facade f3= new Facade(ext3,ext4);
                 e1.add(f3);
        Facade f4= new Facade(ext4,ext3);
                 e1.add(f4);
        
        Coin c1 =new Coin(1,100);
        Coin c2 =new Coin(1,5);
        Coin c4 =new Coin(100,5);
        Coin c6 =new Coin(100,100);

        Mur m1= new Mur(c1,c2,e1);        
        Mur m2= new Mur(c2,c4,e1); 
        Mur m3= new Mur(c4,c6,e1);
        Mur m4= new Mur(c6,c1,e1);
        
        Fenêtre fen = new Fenêtre(2,5,'E',m2);
        
        Pièce pièce1= new Pièce(e1);
        
        pièce1.add(m1);
        pièce1.add(m2);
        pièce1.add(m3);
        pièce1.add(m4);
        
        Appartement appartement = new Appartement(e1);
                System.out.println("avant add_pièce");

        appartement.add_pièce(pièce1);
        System.out.println("après add_pièce");
                System.out.println(appartement);

        batiment.add(e1);
        Architecture_officielle.sauvegardeParDéfaut();
        /*IDManager.récupérerLesDonnéesEnregistrées("saveFile.txt");
        for (String texte : imprimerLesObjetsCréés()){
            System.out.println(texte);
        }*/
         String test = " : boNjour 12 € : 133";
         System.out.println(test.replaceAll(("[0-9]?\\w|\\ |\\€|[a-z]|[A-Z]|\\:"), ""));
        
    }
    
    
}
