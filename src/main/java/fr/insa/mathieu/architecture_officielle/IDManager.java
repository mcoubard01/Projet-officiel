/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathieu.architecture_officielle;
import java.util.*;


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
    
    
    
    public static ArrayList<String> imprimerLesObjetsCréés(){
        ArrayList<String> listeARenvoyer = new ArrayList<>();
        if (! mapIdVersCoin.isEmpty()){ //si la map n'est PAS vide, alors:
            listeARenvoyer.add("=== liste des Coins ===");
            listeARenvoyer.add("Syntaxe  : \"Coin;id;x;y\"");
            // \" permet de mettre des guillemets dans le System.out.print()
            for (int i = 0;i<=mapIdVersCoin.lastKey();i++){
                String coinAEnregistrer = mapIdVersCoin.get(i).toString(); 
                //coinAEnregistrer est la version toString() du coin dont l'id est "i"
                listeARenvoyer.add(coinAEnregistrer);
            } //cete boucle "for" ajoute à listeARenvoyer tous les objets Coin qui ont été créés.
        }
        
        if (! mapIdVersMur.isEmpty()){ //si la map n'est PAS vide, alors:
            listeARenvoyer.add("=== liste des Murs ===");
            listeARenvoyer.add("Syntaxe  : \"Mur;id;idDuCoinDebut;idDuCoinFin;idDeEtageDuMur;idDePièce1;idDePièce2;liste_ouverture\"");

            for (int i = 0;i<=mapIdVersMur.lastKey();i++){
                String murAEnregistrer = mapIdVersMur.get(i).toString(); 
                //MurAEnregistrer est la version toString() du mur dont l'id est "i"
                listeARenvoyer.add(murAEnregistrer);
            } //cete boucle "for" ajoute à listeARenvoyer tous les objets Mur qui ont été créés.
        }
        if (! mapIdVersPièce.isEmpty()){ //si la map n'est PAS vide, alors:
            listeARenvoyer.add("=== liste des Pièces ===");
            listeARenvoyer.add(Pièce.syntaxeToString()); //syntaxe que l'on peut mettre à jour
            //Syntaxe actuelle : "\"Pièce;id;nom_pièce;liste_mur(identifiants);revêtementDuSol;revêtementDuPlafond;idDuAppartement \""
            for (int i = 0;i<=mapIdVersPièce.lastKey();i++){
                String pièceAEnregistrer = mapIdVersPièce.get(i).toString(); 
                //pièceAEnregistrer est la version toString() de la pièce dont l'id est "i"
                listeARenvoyer.add(pièceAEnregistrer);
            } //cete boucle "for" ajoute à listeARenvoyer tous les objets Pièce qui ont été créés.
        }
        if (! mapIdVersEtage.isEmpty()){ //si on a effectivement créé des étages
            listeARenvoyer.add("=== liste des Etages ===");
            listeARenvoyer.add(Etage.syntaxeToString());
            //actuellemnt : "Syntaxe : \"Etage;id;hauteur_etage;liste_appartement\""
            for (int i = 0;i<=mapIdVersEtage.lastKey();i++){
                String étageAEnregistrer = mapIdVersEtage.get(i).toString(); 
                //étageAEnregistrer est la version toString() de l'étage dont l'id est "i"
                listeARenvoyer.add(étageAEnregistrer);
            } //cete boucle "for" ajoute à listeARenvoyer tous les objets Etage qui ont été créés.
        }
        
        if (! mapIdVersAppartement.isEmpty()){ //si on a effectivement créé des étages
             listeARenvoyer.add("=== liste des Appartements ===");
             listeARenvoyer.add(Appartement.syntaxeToString());
             //actuellement : "Syntaxe : \"Appartement;id;IdDuEtage;liste_pièce\""
            for (int i = 0;i<=mapIdVersAppartement.lastKey();i++){
                String appartementAEnregistrer = mapIdVersAppartement.get(i).toString(); 
                //appartementAEnregistrer est la version toString() de l'appartement dont l'id est "i"
                listeARenvoyer.add(appartementAEnregistrer);
            } //cete boucle "for" ajoute à listeARenvoyer tous les objets Appartement qui ont été créés.
        }
        
        //j'imaginais le fichier txt de sauvegarde ainsi : 
        //Tous les coins
        //Tous les murs (pour plus de lisibilité, mur.toString() devrait renvoyer seulement les id des coins composant le mur, plus les revêtements)
        //Tous les pièces (pour plus de lisibilité, pièce.toString() devrait renvoyer seulement les id des murs composant la pièce)
        //Tous les appartements (pour plus de lisibilité, appartement.toString() devrait renvoyer seulement les id des pièces composant l'appartement)
        //Tous les Etages (pour plus de lisibilité, etage.toString() devrait renvoyer seulement les id des appartements composant l'étage)
        
        //Et ensuite une fonction dans IDManager qui remplisse les maps en lisant le fichier texte
        //Peut-être devrait-on écrire les objet.toString() avec des points virgules pour être lus par "lecture"

        
        
       return listeARenvoyer;
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
        int idDuAppart = compteurAppartement + 1000 * appartement.getEtage().getId();   
//ainsi, pour l'étage zéro, 0<=idDuAppart<=999 ; pour l'étage 1, 1000<= idDuAppart <=1999 ,etc.
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
        mapFacade.put(facade,compteurFacade); //rdc : 0 , 1er étage : 1 ,etc. 
        mapIdVersFacade.put(compteurFacade, facade);
        compteurFacade++;
        return compteurFacade-1; //renvoie l'id associé à l'objet etage
    }
    public static int newId(Pièce pièce){
        int idDeLaPièce = compteurPièce + 1000* pièce.getAppartement().getEtage().getId(); //étage auquel la pièce se trouve
//ainsi, pour l'étage ézéro, 0<=idDeLaPièce<=999 ; pour l'étage 1, 1000<= idDeLaPièce <=1999 ,etc.
        mapPièce.put(pièce,idDeLaPièce);
        mapIdVersPièce.put(idDeLaPièce,pièce);
        compteurPièce++;
        return idDeLaPièce;
    }
    
    public static int newId(Mur mur){
        int idDuMur = compteurMur + 1000 * mur.getÉtage().getId();  
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
    
    
    
    //SET
    //pas de "set" car cela dérèglerait le compteur automatique
    
    
    
    
    public static void main(String[] args){
        

        Architecture_officielle batiment = new Architecture_officielle();
        Etage etage1 = new Etage(5,batiment);
        Coin coin = new Coin(23,23);
        Mur mur1 = new Mur();
        mur1.setÉtage(etage1);
        System.out.println("etage1 is assoc to " + mapEtage.get(etage1) +", which is its ID. "); //etage1.getId() fonctionne aussi, donc
        System.out.println("mur 1 has an Id of "+ mur1.getId());
        Etage etage2 = new Etage(4,batiment);
        System.out.print(mapContainsValue(0,"Etage"));
        for (int i = 0; i<imprimerLesObjetsCréés().size();i++){
            System.out.println(imprimerLesObjetsCréés().get(i));
        }
    }
    
    
}
