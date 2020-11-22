/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Antoine
 */


 /**
 * La classe BoardGame correspond au plateau de jeu
 * <br>
 * <br>
 * Cette classe est caractérisée par les informations suivantes :
 * <br>
 * <br>
 * Le paramètre numberOfPlayers qui indique le nombre de joueurs
 * <br>
 * Le paramètre numberOfTurns qui indique le nombre de tours
 * <br>
 * Le paramètre isChoiceCorrect qui vérifie si la réponse entrée par l'utilisateur est correcte
 * <br>
 * Le paramètre randomNum qui génère un nombre aléatoire pour éviter que le premier joueur renseigné soit le premier à jouer
 * <br>
 * Le paramètre i ,valeur itérative, qui permet d'éviter tout conflit entre variable lors de l'utilisation de boucles for
 * <br>
 * Le paramètre indexPlayer ,valeur itérative, qui permet de gérer le nombre de tours
 * <br>
 * Le paramètre line qui permet de réaliser la lecture de fichier texte
 * <br>
 * Le paramètre tryDone qui permet d'éviter de passer le tour d'un joueur si la réponse fournie est incorrecte
 * <br>
 * Le paramètre firstDice qui indique le montant réalisé par le premier dé
 * <br>
 * Le paramètre secondDice qui indique le montant réalisé par le deuxième dé
 * <br>
 * Le paramètre arrivalCaseNumber qui précise le numéro de la case sur laquelle un joueur arrive suite à un déplacement 
 * <br>
 * Le paramètre useAttack qui vérifie si le joueur a utilisé sa carte attaque
 * <br>
 * Le paramètre activationTurn qui indique le numéro du tour pendant lequel un évènement s'est produit
 * <br>
 * Le paramètre endActivationTurn qui indique le numéro du tour où l'évènement sera fini
 * <br>
 * Le paramètre warning qui indique le niveau d'alerte de l'évènement Covid (entre 1 et 3)
 * <br>
 * Le paramètre names qui contient l'ensemble des noms des joueurs
 * <br>
 * Le paramètre order qui contient l'ensemble des numéros des joueurs
 * <br>
 * Le paramètre choices qui contient les noms des pions
 * <br>
 * Le paramètre players qui contient l'ensemble des joueurs  
 * <br>
 * Le paramètre playersInGame qui contient l'ensemble des joueurs en jeu
 * <br>
 * Le paramètre board qui contient l'ensemble des cases du jeu
 * <br>
 * Le paramètre allBoardAvenues qui contient l'ensemble des avenues du jeu
 * <br>
 * Le paramètre groupBoardAvenues qui contient l'ensemble des avenues de même couleur du jeu
 * <br>
 * Le paramètre colorGroups qui contient les couleurs des avenues dont le joueur en question possède le groupe complet
 * <br>
 * Le paramètre groupColor qui contient toutes les avenues d'un même groupe de couleur (contiendra un groupe à la fois)
 * <br>
 * Le paramètre pieces qui contient les noms des différents pions du jeu
 * <br>
 * Le paramètre descPieces qui contient les noms des différents pions du jeu avec leurs capacités correspondantes
 * <br>
 * 
 * Le paramètre propToSellName qui va contenir le nom de la propriété que le joueur souhaite vendre
 * <br>
 * Le paramètre playerToSellName qui va contenir le nom du joueur auquel le vendeur souhaite vendre sa propriété
 * <br>
 * Le paramètre avenuePutHouseName qui va contenir le nom de l'avenue où le joueur souhaite mettre une maison ou un hôtel
 * <br>
 * Le paramètre propToMortgageName qui va contenir le nom de la propriété que le joueur souhaite hypothéquer
 * <br>
 * Le paramètre numberPlayerScanner qui correspond au scanner du nombre de joueur
 * <br>
 * Le paramètre stringScanner qui correspond à notre 2ème scanner qui va être utilisé dès que le programme doit interagir avec l'utilisateur
 * <br>
 * Le paramètre rand qui correspond à un random qui va être utilisé dès que le programme a besoin de générer un paramètre aléatoire
 * <br>
 * Le paramètre turnChoice qui va permettre de savoir si l'utilisateur a fini son tour ou non (en prenant en compte les actions qui font passer son tour)
 * <br>
 * Le paramètre firstTurnChoice qui va permettre de proposer à l'utilisateur les actions possibles pendant son tour de jeu après avoir déjà réalisé une 1ère action
 * <br>
 * Le paramètre sellProp qui va indiquer si le joueur peut vendre une propriété
 * <br>
 * Le paramètre putHouseHotel qui va indiquer si le joueur peut poser une maison ou un hôtel (donc s'il possède toutes les avenues d'un même groupe de couleur)
 * <br>
 * Le paramètre canDestroy qui va indiquer si le joueur qui a choisi le pion "Canon" peut détruire une maison ou non
 * <br>
 * Le paramètre canSell qui va indiquer si le joueur peut vendre une propriété sans avoir à payer l'hypothèque
 * <br>
 * Le paramètre putAgain qui va indiquer si le joueur veut continuer de poser des maisons et des hôtels
 * <br>
 * Le paramètre canMortgage qui va éviter que l'utilisateur hypothèqe une propriété déjà hypothéquée
 * <br>
 * Le paramètre isChoiceCorrect qui permet à l'utilisateur de saisir à nouveau son choix, et est utilisé pour chaque choix qu'il doit faire
 * <br>
 * Le paramètre propToSell qui va correspondre à la propriété que le joueur souhaite vendre
 * <br>
 * Le paramètre propToMortgage qui va correspondre à la propriété que le joueur souhaite hypothéquer
 * <br>
 * Le paramètre avenuePutHouse qui va correspondre à l'avenue sur laquelle le joueur souhaite poser une maison ou un hôtel
 * <br>
 * Le paramètre playerToSell qui va correspondre au joueur à qui le joueur actuel veut vendre une propriété
 * <br>
 * Le paramètre attacks qui contient l'ensemble des cartes attaques
 * <br>
 * Le paramètre avenuesWhereDestroyHouse qui contient l'ensemble des avenues qui possède au moins 1 maison
 * <br>
 * Le paramètre avenuesPlayerCanPut qui contient l'ensemble des avenues du joueur sur lesquelles il peut poser une maison ou un hôtel
 * <br>
 * Le paramètre resultOfDice qui va contenir les 2 résultats des dés
 * <br>
 * Le paramètre covid qui correspond à l'évènement Covid
 * <br>
 * Le paramètre strike qui correspond à l'évènement Strike              
 * <br>
 * Le paramètre earthquake qui correspond à l'évènement Earthquake              
 * <br>
 * Le paramètre arrivalCase qui précise la case sur laquelle un joueur arrive suite à un déplacement     
 * <br>
 * On retrouve dans cette classe les fonction permettant d'initialiser le jeu et de créer le plateau ainsi que d'afficher les inventaires des joueurs
 * <br>
 * On retrouve également les fonctions permettant de se déplacer sur une autre case, d'afficher les joueurs et les propriétés
 * <br>
 * Enfin on retrouve les fonctions pour regrouper les avenues par couleur et la fonction permettant de gérer le choix d'un joueur en prison
 * @author thibb
 */
public class BoardGame{
    
    //--------------------------------------------------------------------------
    //DECLARATION DES VARIABLES
    static int numberOfPlayers=0;
    static int numberOfTurns=0;
    static int activationTurn=0;
    static int endActivationTurn=0;
    static int warning=1;
    static int sumOfDice;
    static int randomNum;
    static int i;
    static int indexPlayer;
    static int countDouble;
    static int deposit=50;
    static int firstDice;
    static int secondDice;
    static int arrivalCaseNumber;
    
    static String propToSellName;
    static String playerToSellName;
    static String avenuePutHouseName;
    static String propToMortgageName;
    static String line;
    
    static Scanner numberPlayerScanner = new Scanner(System.in);
    static Scanner stringScanner= new Scanner(System.in);
    
    static Random rand=new Random();
    
    static boolean useAttack=true;
    static boolean tryDone=false;
    static boolean turnChoice;
    static boolean firstTurnChoice;
    static boolean sellProp; 
    static boolean putHouseHotel;
    static boolean canDestroy;
    static boolean canSell;
    static boolean putAgain;
    static boolean canMortgage;
    static boolean isChoiceCorrect=false;
    
    static Case arrivalCase;
    static Property propToSell;
    static Property propToMortgage;
    static Avenue avenuePutHouse;
    
    static Player playerToSell;

    static Covid covid=new Covid();
    static Strike strike = new Strike();
    static Earthquake earthquake= new Earthquake();

    
    static ArrayList <String> names= new ArrayList();
    static ArrayList <Integer> order= new ArrayList();
    static ArrayList <String> choices= new ArrayList();
    static ArrayList <Player> players = new ArrayList();
    static ArrayList <Player> playersInGame=new ArrayList();
    static ArrayList <Case> board=new ArrayList();
    static ArrayList <ColorAvenue> groupColor=new ArrayList();
    static ArrayList <Avenue> allBoardAvenues=new ArrayList();
    static ArrayList <Avenue> groupBoardAvenues=new ArrayList();
    static ArrayList <ColorAvenue> colorGroups=new ArrayList();
    static ArrayList <Avenue> avenuesWhereDestroyHouse= new ArrayList();
    static ArrayList <Avenue> avenuesPlayerCanPut=new ArrayList();
    static ArrayList <Integer> resultOfDice;
    static ArrayList <String> pieces= new ArrayList() {{
        add("Hat");
        add("Cannon");
        add("Car");
        add("Mayor");
    }};
    static ArrayList descPieces= new ArrayList() {{
        add("Hat, capacité = 1 fois tous les 2 tours à partir du 5ème tour de jeu, possibilité de récupérer de l'argent des autres joueurs en les arnaquant (mais passe son tour) + loyer doublé pour les propriétés de couleur rouge");
        add("Cannon, capacité = retirer une maison du joueur souhaité (mais passe son tour) + loyer doublé pour les propriétés de couleur rose");
        add("Car, capacité = déplacement sur la case de son choix dès lors que le pion se trouve sur la  case \"Parc Gratuit\" +loyer doublé pour les propriétés de couleur orange");
        add("Mayor, capacité = possibilité de poser un hôtel directement");
    }};
    
    static ArrayList <Attack> attacks=new ArrayList(){{
        add(new GiveAway());
        add(new Robber());
        add(new Inflation());
    }};
    
    //--------------------------------------------------------------------------

    
    public static void main(String[] args) {
        initialize();
        //Instanciation des pions dans le main selon l'initialisation de la partie (choix des joueurs)
        Collections.sort(players); //Tri de l'arraylist joueurs par leur ordre de jeu*/
        boardCreation("board.txt");
        //Attribution des cartes attaques
        for (i=0;i<numberOfPlayers;i++){
            players.get(i).setAttackCard(attacks.get(rand.nextInt(attacks.size())));
            players.get(i).setPlayerCase(board.get(0));
        }
        playersInGame.addAll(players);
        //Tant que la partie n'est pas terminée (tant qu'il reste plus d'un joueur en jeu
        while(playersInGame.size()>1){
            numberOfTurns++;
            System.out.println("Tour n°"+numberOfTurns);
            System.out.println("");
                if( numberOfTurns%7==0 && numberOfTurns>=10){
                    if(!earthquake.isDone()) randomNum=rand.nextInt(3)+1;
                    else randomNum=rand.nextInt(2)+1;
                    switch(randomNum){
                        case 1:
                            warning=covid.closeHotel(playersInGame, warning);
                            activationTurn=numberOfTurns;
                            break;
                        case 2:
                            endActivationTurn=strike.closeRailRoad(board, numberOfTurns);
                            activationTurn=numberOfTurns;
                            break;
                        case 3:
                            earthquake.collapse(playersInGame);
                            break;
                    }
                }
            if(covid.isInAction()){
                if(numberOfTurns==activationTurn+2 || numberOfTurns==activationTurn+4 ){
                    warning=covid.closeHotel(playersInGame, warning);
                }
                else if(numberOfTurns==activationTurn+6){
                    covid.setInAction(false);
                    covid.openHotel(playersInGame);
                }
            }
            if(strike.isInAction() && numberOfTurns==endActivationTurn){
                strike.setInAction(false);
                strike.openRailRoad(board);
            }
            for(indexPlayer=0;indexPlayer<playersInGame.size();indexPlayer++){ //On teste si un joueur n'a plus d'argent : il est éliminé
                if(playersInGame.get(indexPlayer).getCapital()<=0) {
                    System.out.println(playersInGame.get(indexPlayer).getName()+", vous avez fait faillite, vous êtes éliminé");
                    suppressPlayer(playersInGame.get(indexPlayer));
                    playersInGame.remove(playersInGame.get(indexPlayer));
                }
            }
            if(playersInGame.size()==1){
                System.out.println("Bravo "+playersInGame.get(0).getName()+" vous avez gagné la partie !");
            }
            else{
                for(indexPlayer=0;indexPlayer<playersInGame.size();indexPlayer++){ //On fait le tour des joueurs encore en jeu
                    if(playersInGame.get(indexPlayer).isIsInJail()) {
                        displayInventory(playersInGame.get(indexPlayer));
                        getOutOfJail(playersInGame.get(indexPlayer));
                    }//On teste d'abord si le joueur est en prison
                    else{ //S'il ne l'est pas, il joue son tour normalement
                        displayInventory(playersInGame.get(indexPlayer));
                        choice(playersInGame.get(indexPlayer));
                    }
                }
            }
            
            
            
            
        }
        
    }
    
    /**
     * Cette méthode permet d'initialiser notre jeu notamment en demandant le nom des joueurs, le nombre de joueur ainsi que leurs pions
     */
    public static void initialize(){
        
        System.out.println("Bonjour, voici une version du Monopoly remasterisée par Antoine Asset et Thibaut Blasselle");
        System.out.println("Combien de joueurs vont jouer ? (maximum 3)"); //Max 4 pour l'instant car 4 pions
        while(!isChoiceCorrect){
            try{ //Saisie du nombre de joueurs
            numberOfPlayers=numberPlayerScanner.nextInt();
            isChoiceCorrect=true;
            }
            catch(InputMismatchException e){
                System.out.println("Veuillez saisir un entier");
                numberPlayerScanner.nextLine();
            }
        }
        isChoiceCorrect=false;
        
        for(i=0;i<numberOfPlayers;i++){
            isChoiceCorrect=false;
            System.out.println("Entrez le nom du joueur");
            names.add(numberPlayerScanner.next());//Saisie du nom des joueurs et ajout de ceux-ci dans une arrayList de noms
            
            //Génération aléatoire de l'ordre 
            if(i==0) order.add(rand.nextInt(numberOfPlayers)+1);
            else{
                randomNum=rand.nextInt(numberOfPlayers)+1;
                while(order.contains(randomNum)){
                    randomNum=rand.nextInt(numberOfPlayers)+1;
                }
                order.add(randomNum);
                
            }
            
            //Choix du pion
            
            System.out.println("Choisissez votre pion :");
            for(int j=0;j<descPieces.size();j++){
                System.out.println(descPieces.get(j));
            }
            
            while(!isChoiceCorrect){
                String choice=stringScanner.next();
                
                if(pieces.contains(choice)){ //Voir pour gérer la casse
                    isChoiceCorrect=true;
                    pieces.remove(choice);
                    choices.add(choice);
                    
                }
                else System.out.println("Sélectionner un pion existant et disponible");
            }
            
            //Instanciation des joueurs (pions) à mettre sous forme de fonction (éventuellement pareil pour choix de pion et ordre
            if(choices.get(i).equals("Hat")){
                players.add(new Hat(names.get(i), order.get(i)));
            }
            else if(choices.get(i).equals("Cannon")){
                players.add(new Cannon(names.get(i), order.get(i)));
            }
            else if(choices.get(i).equals("Car")){
                players.add(new Car(names.get(i), order.get(i)));
            }
            else if(choices.get(i).equals("Mayor")){
                players.add(new Mayor(names.get(i), order.get(i)));
                
            }
            
        }        
        
        
    }
    
    /**
     * Cette méthode permet de créer le plateau de jeu en parcourant nos fichiers texte qui contiennent l'ensemble des cases
     * @param pathName
     *      Le paramètre correspond au nom du chemin où est enregistré le fichier texte
     */
    public static void boardCreation(String pathName){
        try (BufferedReader reader = new BufferedReader(new FileReader(pathName))) {
            while( (line=reader.readLine())!=null){
                String [] splitted=line.split("\t"); //On split la ligne récupérée au niveau des tabulations et on la stocke dans un tableau
                if(splitted[0].equals("Case")){
                    board.add(new Case(splitted[1],Integer.parseInt(splitted[2])));
                }
                else if(splitted[0].equals("Taxes")){
                    board.add(new Taxes(splitted[1],Integer.parseInt(splitted[2]),Integer.parseInt(splitted[3])));
                }
                else if (splitted[0].equals("RailRoad")){
                    board.add(new RailRoad(Integer.parseInt(splitted[1]),Integer.parseInt(splitted[2]),splitted[3],Integer.parseInt(splitted[4]),Integer.parseInt(splitted[5])));
                }
                else if(splitted[0].equals("Bonus")){
                    board.add(new Bonus(splitted[1],Integer.parseInt(splitted[2])));
                }
                else if(splitted[0].equals("Company")){
                    board.add(new Company(Integer.parseInt(splitted[1]),splitted[2],Integer.parseInt(splitted[3]),Integer.parseInt(splitted[4])));
                }
                else if(splitted[0].equals("Avenue")){
                    board.add(new Avenue(Integer.parseInt(splitted[1]),Integer.parseInt(splitted[2]),splitted[3],Integer.parseInt(splitted[4]),ColorAvenue.valueOf(splitted[5]),Integer.parseInt(splitted[6]),Integer.parseInt(splitted[7])));
                }
            }   
            reader.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("Fichier introuvable");
        }
        catch(IOException ex){
            System.out.println("Input/Output Exception");
        }
    }
    
    /**
     * Cette méthode va proposer les différentes actions possibles au joueur et les réaliser selon son choix.
     * @param player 
     *      Ce paramètre permet d'effectuer toutes les actions par rapport à ce joueur
     */
    
    
    public static void choice(Player player){
        avenuesWhereDestroyHouse.clear();
        turnChoice=false;//booléen qui va permettre de savoir si l'utilisateur joue sa carte attaque (et donc ne fait que ça lors de son tour)
        firstTurnChoice=false; //booléen qui va permettre de savoir si l'utilisateur a déjà fait un premier choix (vente de propriété ou pose de bâtiment), car il peut faire l'un des 2 + lancer les dés
        sellProp=false; //variables booléennes pour savoir ce qu'on va proposer comme choix au joueur
        putHouseHotel=false;
        canDestroy=false;
        while(!turnChoice){
            if(!player.getProperties().isEmpty()) sellProp=true; //Si le joueur possède des propriétés, on pourra lui propsoer d'en vendre
            groupColor=groupOfAvenues(player,player.getAvenues());
            if(!groupColor.isEmpty() && !firstTurnChoice) putHouseHotel=true; //Si le joueur possède un groupe complet d'avenues (de même couleur), on va pouvoir lui proposer de poser maisons et hôtels
            System.out.println("Vous pouvez : ");
            System.out.println("Lancer les dés (rollsdice) ");
            if(!player.getAttackCard().isItUsed() && !firstTurnChoice) {
                System.out.println("Utiliser votre carte attaque (attack) "+player.getAttackCard().getName()+" : "+player.getAttackCard().getEffect());
                System.out.println("Rappel : utiliser votre carte attaque vous fait passer votre tour");
            }
            if(sellProp && !firstTurnChoice) {
                System.out.println("Vendre des propriétés (sell)");
                System.out.println("Hypothéquer une propriété (mortgage)");
            }
            for(i=0;i<players.size();i++){ //Cette partie ne sert que pour le pion canon
                if(players.get(i)!=player){
                    for(int j=0;j<players.get(i).getAvenues().size();j++){
                        if(players.get(i).getAvenues().get(j).getHouse()>0) {
                            canDestroy=true;
                            avenuesWhereDestroyHouse.add(players.get(i).getAvenues().get(j));
                        }
                    }
                }
            }
            if(player instanceof Cannon && canDestroy) System.out.println("Détruire une maison (shoot)");
            if(putHouseHotel && !firstTurnChoice) System.out.println("Poser des maisons ou hôtel sur vos avenues (putHouse)"); 
            if(player instanceof Hat && numberOfTurns%2==0 && numberOfTurns>=5) System.out.println("Essayer de récupérer de l'argent en arnaquant les autres joueurs (scam), attention, cela passe votre tour");
            isChoiceCorrect=false;
            while(!isChoiceCorrect){
                switch(stringScanner.next()){
                    case "shoot":
                        ((Cannon)player).shoot(avenuesWhereDestroyHouse);
                        turnChoice=true;
                        isChoiceCorrect=true;
                        break;
                    case "attack":
                        if(!player.getAttackCard().isItUsed()){
                            turnChoice=player.getAttackCard().effect(players,player,board);
                        }
                        else System.out.println("Vous avez déjà utilisé votre carte");
                        isChoiceCorrect=true;
                        break;
                    case "sell": 
                        if(sellProp){
                            if(!firstTurnChoice){
                                canSell=true;
                                propToSell=null;
                                System.out.println("Laquelle de vos propriétés souhaitez-vous vendre ?");
                                displayProperties(player);
                                stringScanner.nextLine();
                                while(propToSell==null){
                                    propToSellName=stringScanner.nextLine();
                                    for(i=0;i<player.getProperties().size();i++){
                                        if(player.getProperties().get(i).getName().equals(propToSellName)) propToSell=player.getProperties().get(i);
                                    }
                                    if(propToSell==null) System.out.println("Veuillez saisir une propriété valide");
                                }
                                if(propToSell.isMortgaged()){ //S'il a hypothéqué la propriété qu'il veut vendre
                                    System.out.println("Vous avez hypothéqué cette propriété. Pour pouvoir la vendre, vous devez payer le prix de l'hypothèque qui est de "+propToSell.getMortgage()+" Francs");
                                    System.out.println("Voulez-vous payer ce prix pour la vendre ?");
                                    isChoiceCorrect=false;
                                    do{
                                        switch(stringScanner.nextLine()){
                                        case "Oui":case"oui":case"OUI":
                                            player.setCapital(player.getCapital()-propToSell.getMortgage());
                                            propToSell.setMortgaged(false);
                                            isChoiceCorrect=true;
                                            break;
                                        case "Non":case"non":case"NON":
                                            canSell=false;
                                            isChoiceCorrect=true;
                                            System.out.println("Vous n'avez pas payé le prix de l'hypothèque, vous ne pouvez donc pas vendre cette propriété");
                                            break;
                                        default:
                                            System.out.println("Veuillez choisir un argument valide");
                                            break;
                                    }
                                    }while(!isChoiceCorrect);
                                }
                                if(canSell){ //Cette condition permet de donner la possibilité au joueur de vendre sa propriété uniquement si elle n'est pas hypothéquée ou qu'il paye l'hypothèque en plus pour la vendre
                                    System.out.println("A qui souhaitez-vous la vendre ?");
                                    displayPlayers(player);
                                    playerToSell=null;
                                    while(playerToSell==null){
                                        playerToSellName=stringScanner.nextLine();
                                        for(i=0;i<players.size();i++){
                                            if(players.get(i).getName().equals(playerToSellName)) playerToSell=players.get(i);
                                        }
                                        if(playerToSell==null) System.out.println("Veuillez saisir un joueur valide");
                                    }
                                    System.out.println(playerToSellName+", souhaitez-vous acheter "+propToSellName+" à "+player.getName()+" ?");
                                    isChoiceCorrect=false;
                                    while(!isChoiceCorrect){
                                        switch(stringScanner.nextLine()){
                                            case "Oui":
                                                propToSell.sellToSomeone(player, playerToSell);
                                                System.out.println(player.getName()+" a bien vendu "+propToSell.getName()+" à "+playerToSell.getName());
                                                isChoiceCorrect=true;
                                                break;
                                            case "Non":
                                                isChoiceCorrect=true;
                                                System.out.println(player.getName()+" n'a pas vendu "+propToSell.getName()+" à "+playerToSell.getName());
                                                break;
                                            default:
                                                System.out.println("Veuillez saisir une réponse valide (Oui/Non)");
                                                break;
                                        }
                                    }
                                    
                                    firstTurnChoice=true;
                                }
                        }
                        else System.out.println("Vous n'avez pas de propriétés à vendre");
                        isChoiceCorrect=true;
                        break;
                        }
                    case "scam":
                        ((Hat)player).scam(playersInGame);
                        turnChoice=true;
                        isChoiceCorrect=true;
                        break;
                    case "putHouse":case"puthouse":
                        if(putHouseHotel){
                            if(!firstTurnChoice){
                                avenuesPlayerCanPut.clear();
                                for(i=0;i<player.getAvenues().size();i++){
                                    if(groupColor.contains((player.getAvenues().get(i)).getColor())) avenuesPlayerCanPut.add(player.getAvenues().get(i));
                                }
                                putAgain=true;
                                while(putAgain){ //Permet de donner la possibilité au joueur de poser plusieurs maisons/hôtels pendant son tour de jeu
                                    System.out.println("Sur quelle(s) avenue(s) voulez-vous poser une maison ou un hôtel ?");
                                    for(i=0;i<avenuesPlayerCanPut.size();i++){
                                        System.out.println(avenuesPlayerCanPut.get(i).getName());
                                    }

                                    System.out.println("");
                                    System.out.println("Saisissez le nom de l'avenue");
                                    avenuePutHouse=null;
                                    stringScanner.nextLine();
                                    while(avenuePutHouse==null){
                                        avenuePutHouseName=stringScanner.nextLine();
                                        for(i=0;i<avenuesPlayerCanPut.size();i++){
                                            if(avenuePutHouseName.equals(avenuesPlayerCanPut.get(i).getName())) avenuePutHouse=avenuesPlayerCanPut.get(i);
                                        }
                                        if(avenuePutHouse==null) System.out.println("Veuillez saisir une avenue valide");
                                    }
                                    if(player instanceof Mayor){
                                        System.out.println("Vous pouvez mettre un hôtel directement pour la somme de 50 000 Francs");
                                    }
                                    System.out.println("Voulez-vous mettre un hôtel ou une maison ?");
                                    isChoiceCorrect=false;
                                    while(!isChoiceCorrect){
                                        switch(stringScanner.nextLine()){
                                           case "hotel":case"Hotel":case"HOTEL":case"hôtel":case"Hôtel":case"HÔTEL":
                                               if(player instanceof Mayor) ((Mayor)player).buildHotel(avenuePutHouse);
                                               else player.putHotel(avenuePutHouse,avenuesPlayerCanPut);
                                               isChoiceCorrect=true;
                                               break;
                                           case "maison":case"Maison":case"MAISON":
                                               player.putHouse(avenuePutHouse,avenuesPlayerCanPut);
                                               isChoiceCorrect=true;
                                               break;
                                           case "stop":case"STOP":case"Stop":
                                               isChoiceCorrect=true;
                                               break;
                                           default:
                                               System.out.println("Veuillez choisir un argument valide (hotel/maison/stop)");
                                               break;
                                       }   
                                    }
                                    isChoiceCorrect=false;
                                    System.out.println("Voulez-vous poser une autre maison ou un autre hôtel ?");
                                    while(!isChoiceCorrect){
                                        switch(stringScanner.nextLine()){
                                            case "Oui":case"oui":case"OUI":
                                                isChoiceCorrect=true;
                                                break;
                                            case "Non":case"non":case"NON":
                                                isChoiceCorrect=true;
                                                putAgain=false;
                                                break;
                                            default:
                                                System.out.println("Veuillez choisir un argument valide (Oui/Non)");
                                                break;
                                        }
                                    }
                            }

                                }
                                firstTurnChoice=true;
                            }
                        else System.out.println("Vous ne pouvez pas poser de maison ou d'hôtel, il vous faut un groupe complet d'avenues de même couleur pour pouvoir en poser");
                        isChoiceCorrect=true;
                        break;
                    case "mortgage":
                        if(!firstTurnChoice){
                            canMortgage=false;
                            while(!canMortgage){
                                propToMortgage=null;
                                System.out.println("Quelle propriété voulez-vous hypothéquer ?");
                                displayProperties(player);
                                stringScanner.nextLine();
                                while(propToMortgage==null){
                                    propToMortgageName=stringScanner.nextLine();
                                    for(i=0;i<player.getProperties().size();i++){
                                        if(player.getProperties().get(i).getName().equals(propToMortgageName)) propToMortgage=(Property)(player.getProperties().get(i));
                                    }
                                    if(propToMortgage==null) System.out.println("Veuillez saisir une propriété valide ou indiquer \"Stop\" si vous ne voulez pas hypothéquer");
                                }
                                if(propToMortgage.isMortgaged()) System.out.println("Vous avez déjà hypothéqué cette maison, veuillez en choisir une autre");
                                else{
                                    player.putOnMortgage(player, propToMortgage);
                                    System.out.println("Vous avez bien hypothéqué "+propToMortgage.getName());
                                    canMortgage=true;
                                }
                            }
                            firstTurnChoice=true;
                            isChoiceCorrect=true;
                        }
                        break;
                    case "rollsdice":
                        countDouble=0;
                        do{
                            if(!player.isIsInJail()){ //On vérifie cette condition car le joueur peut se retrouver en prison pendant son tour de jeu (s'il a fait un double mais qu'il est en prison entre temps par exemple)
                                resultOfDice=player.rollsDice();
                                sumOfDice=resultOfDice.get(0)+resultOfDice.get(1);
                                if(resultOfDice.get(0).equals(resultOfDice.get(1))) {
                                    countDouble++;
                                    System.out.println("Vous avez fait un double, quel petit veinard !");
                                }
                                move(sumOfDice,player); //Effet de la case départ (rémunération) géré dans move
                                if(player.getPlayerCase() instanceof Bonus) ((Bonus)player.getPlayerCase()).effect(player, board); //Effet de cases bonus
                                if(player.getPlayerCase() instanceof Taxes) {
                                    player.setCapital(player.getCapital()-((Taxes)player.getPlayerCase()).getPrice());
                                    System.out.println("Vous avez payé "+((Taxes)player.getPlayerCase()).getPrice()+" francs");
                                }
                                if(player.getPlayerCase().equals(board.get(30))) { //Si le joueur est sur la case "allez en prison"
                                    player.inJail(board);
                                }
                                if(player.getPlayerCase().getName().equals("Parc gratuit") && player instanceof Car) ((Car)player).moveTo(board);
                                if(player.getPlayerCase() instanceof Property && ((Property)player.getPlayerCase()).isItBought() && !((Property)player.getPlayerCase()).getAssociatedPlayer().equals(player)){
                                    Property prop=(Property)player.getPlayerCase();
                                    if(prop.isMortgaged()){
                                        System.out.println("Cette propriété a été hypothéquée, voulez-vous la racheter au prix de "+(prop.getBoughtPrice()+prop.getMortgage())+" Francs ?");
                                        isChoiceCorrect=false;
                                        while(!isChoiceCorrect){
                                            switch(stringScanner.nextLine()){
                                                case "Oui":case"oui":case"OUI":
                                                    prop.buy(player);
                                                    isChoiceCorrect=true;
                                                    System.out.println("Vous avez racheté la propriété hypothéquée");
                                                    break;
                                                case "Non":case"non":case"NON":
                                                    isChoiceCorrect=true;
                                                    break;
                                                default:
                                                    System.out.println("Veuillez saisir une réponse valide (Oui/Non)");
                                                    break;

                                            }
                                        }

                                    }
                                    else{
                                        player.setCapital(player.getCapital()-(prop.getRent()));
                                        System.out.println("Vous êtes chez "+prop.getAssociatedPlayer().getName()+ ", vous avez payé "+prop.getRent()+" Francs");

                                    }
                                }
                                else if(player.getPlayerCase() instanceof Property && !((Property)player.getPlayerCase()).isItBought() ){ //Si la propriété n'est pas achetée, on propose au joueur de l'acheter
                                    System.out.println("Voulez-vous acheter "+player.getPlayerCase().getName()+" pour la somme de "+((Property)player.getPlayerCase()).getBoughtPrice()+" Francs ? (Oui/Non)");
                                    isChoiceCorrect=false;
                                    while(!isChoiceCorrect){
                                        switch(stringScanner.next()){
                                           case "Oui":case"oui":case"OUI":
                                               if(((Property)player.getPlayerCase()).buy(player)) System.out.println("Vous avez acheté "+player.getPlayerCase().getName()+" pour "+((Property)player.getPlayerCase()).getBoughtPrice()+" Francs");
                                               isChoiceCorrect=true;
                                               break;
                                           case "Non":case"non":case"NON":
                                               isChoiceCorrect=true;
                                               break;
                                           default:
                                               System.out.println("Veuillez saisir une réponse valide (Oui/Non)");
                                               break;

                                       }   
                                    }
                                }
                            }
                            
                        }while(resultOfDice.get(0).equals(resultOfDice.get(1)) && countDouble<3);

                        if(countDouble==3){
                            System.out.println("Vous avez fait 3 doubles de suite, vous allez en prison !");
                            player.inJail(board);
                        }
                        turnChoice=true;
                        isChoiceCorrect=true;
                        break;
                    default:
                        System.out.println("Veuillez saisir un argument valide");
                        break;
                }
            }

        }




        }

    
    
    /**
     * Cette méthode permet d'afficher les noms des différents joueurs
     * @param player
     *      Le paramètre correspond au joueur dont on affiche le nom
     */
    public static void displayPlayers(Player player){
        for(i=0;i<players.size();i++){
            if(!players.get(i).equals(player)){
                System.out.println(players.get(i).getName());
            }
        }
    }
    
    
    /**
     * Cette méthode permet d'afficher l'ensemble des propriétés détenues par un joueur
     * @param player
     *      Le paramètre correspond au joueur dont on affiche les propriétés
     */
    public static void displayProperties(Player player){
        for(i=0;i<player.getProperties().size();i++){
            if(player.getProperties().get(i) instanceof Avenue){
                System.out.println(player.getProperties().get(i).getName()+", de couleur "+((Avenue)player.getProperties().get(i)).getColor());
            }
        }
    }
    
    
    /**
     * Cette méthode permet de déplacer un joueur suite à un lancer de dés
     * @param sumOfDice
     *      Le paramètre correspond au montant affiché par les deux dés
     * @param player
     *      Le paramètre correspond au joueur qui a lancé les dés
     */
    public static void move(int sumOfDice, Player player){
        System.out.println("Vous avez fait "+sumOfDice);
        arrivalCaseNumber= (player.getPlayerCase().getCaseNumber()+sumOfDice)%40;
        arrivalCase = null;
        for(i=0;i<board.size();i++){
            if(board.get(i).getCaseNumber()==arrivalCaseNumber) arrivalCase=board.get(i);
        }
        if(arrivalCaseNumber<player.getPlayerCase().getCaseNumber()){
            player.setCapital(player.getCapital()+20000);
        }
        if(arrivalCase!=null){ //Juste pour éviter des warnings, mais normalement arrivalCase ne devrait jamais être null
            player.setPlayerCase(arrivalCase);
            System.out.println("Vous êtes sur la case "+arrivalCase.getName());
        }
        else System.out.println("Cette erreur ne devrait pas arriver");
    }
    
    /**
     * Cette méthode permet d'afficher l'ensemble des objets que possède un joueur (propriétés, cartes attaques)
     * @param player
     *      Le paramètre correspond au joueur dont on affiche l'inventaire
     */
    public static void displayInventory(Player player){
        System.out.println("");
        if(player.isScamed()){
            System.out.println("On vous a arnaqué ! Vous avez perdu "+player.getAmountScamed()+" Francs");
            player.setScamed(false);
        }
        System.out.println("Au tour de "+player.getName());
        System.out.println("Vous avez "+player.getCapital()+" €");
        System.out.println("");
        System.out.println("Vous possèdez : ");
        for(i=0;i<player.getProperties().size();i++){
            if(player.getProperties().get(i) instanceof Avenue){ //Si la propriété est une avenue, on affiche le nombre de maisons/hôtel s'il y en a
                if(((Avenue)player.getProperties().get(i)).getHotel()>0) System.out.println((player.getProperties().get(i)).getName()+", de couleur "+((Avenue)player.getProperties().get(i)).getColor()+" avec "+((Avenue)(player.getProperties().get(i))).getHotel()+" hôtel dessus, ");
                else if( ((Avenue)player.getProperties().get(i)).getHouse()>0) System.out.println((player.getProperties().get(i)).getName()+", de couleur "+((Avenue)player.getProperties().get(i)).getColor()+ " avec "+((Avenue)(player.getProperties().get(i))).getHouse()+" maison(s) dessus, ");
                else System.out.println((player.getProperties().get(i)).getName()+", de couleur "+((Avenue)player.getProperties().get(i)).getColor());
            }
            else if(player.getProperties().get(i) instanceof RailRoad){
                System.out.println(((RailRoad)player.getProperties().get(i)).getName());
            }
            else if(player.getProperties().get(i) instanceof Company){
                System.out.println( ((Company)player.getProperties().get(i)).getName() );
            } //Si la case est une propriété, on caste la case pour pouvoir utiliser les méthodes propres à la classe Property
            
        }
        if(!player.getAttackCard().isItUsed()){
            System.out.println("");
            System.out.println("Vous pouvez utiliser votre carte attaque "+player.getAttackCard().getName());
            System.out.println("En utilisant cette carte, "+player.getAttackCard().getEffect());
            useAttack=true;
        }
        else useAttack=false;
        System.out.println("");
        if(covid.isInAction()){
            System.out.println("Le covid circule actuellement");//Ajouter affichage du niveau d'alerte
        }
        if(strike.isInAction()){
            System.out.println("Il y a actuellement une grève des trains");
        }
        System.out.println("Vous êtes actuellement sur la case n° "+player.getPlayerCase().getCaseNumber()+", "+player.getPlayerCase().getName());
    }
    
    
    /**
     * Cette méthode va regarder parmi toutes les avenues du joueur, s'il possède un ou plusieurs groupes d'avenues de même couleur pour pouvoir poser des maisons et hôtel dessus
     * @param player
     *      Le paramètre correspond au joueur qui possède les avenues
     * @param avenues
     *      Le paramètre correspond à la liste des avenues que possède le joueur
     * @return  Cette méthode renvoit la liste des couleurs dont le joueur possède le groupe complet d'avenues
     */
    public static ArrayList <ColorAvenue> groupOfAvenues(Player player,ArrayList <Avenue> avenues){ //Va regarder si le joueur passé en paramètre possède un groupe complet d'avenues de même couleurs
        colorGroups.clear();
        groupBoardAvenues.clear();
        allBoardAvenues.clear();
        for(i=0;i<board.size();i++){ //On récupère la liste des avenues du plateau de jeu
            if(board.get(i) instanceof Avenue){
                allBoardAvenues.add((Avenue)(board.get(i)));
            }
        }
        for(i=0;i<allBoardAvenues.size();i++){ //On parcourt cette liste
            if(!groupBoardAvenues.isEmpty() && allBoardAvenues.get(i).getColor().equals(groupBoardAvenues.get(0).getColor())) groupBoardAvenues.add(allBoardAvenues.get(i)); //groupBoardAvenues est une liste qui va contenir les avenues qui sont de même couleur
            else if(!groupBoardAvenues.isEmpty() && !allBoardAvenues.get(i).getColor().equals(groupBoardAvenues.get(0).getColor())){
                if(avenues.containsAll(groupBoardAvenues)) colorGroups.add(groupBoardAvenues.get(0).getColor());
                groupBoardAvenues.clear();
                groupBoardAvenues.add(allBoardAvenues.get(i));
            }
            else{
                groupBoardAvenues.add(allBoardAvenues.get(i));
            }
            
        }
        return colorGroups; //On va, au fur et à mesure, insérer dans une liste (groupBoardAvenues) les avenues de même couleur, puis comparer avec celles possédées du joueur pour savoir s'il a le groupe en question, si c'est le cas, on ajotue à notre liste "colorGroups" la couleur du groupe qu'il possède
    }
   
    
    /**
     * Cette méthode permet au joueur d'interagir avec le jeu lorsqu'il est en prison
     * @param player
     *      Le paramètre correspond au joueur qui est en prison
     */
    public static void getOutOfJail(Player player)
    {
        tryDone=false;
        System.out.println("Pour sortir, faites votre choix : freecard si vous avec une carte de libération, pay si vous voulez payer ("+deposit+" Francs), ou roll si vous voulez tenter votre chance avec un double");
        stringScanner.nextLine();
        do {
            switch(stringScanner.nextLine())                           // on lit la réponse du joueur
            {
                case "freecard":                

                    if(player.getFreeCard() > 0)               // si le joueur posséde une carte, il sort de prison
                    {
                        player.setIsInJail(false);
                        player.setFreeCard(player.getFreeCard() - 1);     // on actualise le nombre de cartes
                        System.out.println("Il vous reste à présent " + player.getFreeCard() + " cartes pour sortir de prison");
                        tryDone=true;
                    }
                    else System.out.println("Vous n'avez pas de cartes pour sortir de prison");
                        
                    break;

                case "pay":

                    if(player.getCapital() >= deposit)                           // si le joueur souhaite payer la caution
                    {
                        player.setIsInJail(false);
                        player.setCapital(player.getCapital() - deposit);        // on actualise le capital
                        System.out.println("Vous disposez à présent de " + player.getCapital() + " Francs");
                        tryDone=true;
                    }
                    break;

                case "roll":
                    firstDice=player.rollsDice().get(0);
                    secondDice=player.rollsDice().get(1);
                    System.out.println("Vous avez fait "+firstDice+" et "+secondDice);
                    if (firstDice==secondDice)    // si le joueur a fait un double
                    {
                        player.setIsInJail(false);
                        System.out.println("Félicitations ! Votre double vous permet de sortir de prison");
                    }
                    else
                    {
                        System.out.println("Dommage ! Vous restez en prison");
                    }
                    tryDone=true;
                    break;
                default:
                    System.out.println("Veuillez saisir une commande valide");
                    break;
           }
    }while(!tryDone);
}
    /**
     * Cette méthode permet de désapproprier le joueur pour toutes ses propriétés et de réinitialiser le loyer. Elle est utilisée lorsqu'un joueur a perdu
     * @param player
     *      Ce paramètre correspond au joueur qui a perdu et qui se voit désapproprié
     */
    public static void suppressPlayer(Player player){
        for(int k=0;k<player.getProperties().size();k++){
            player.getProperties().get(k).setAssociatedPlayer(null);
            player.getProperties().get(k).setIsBought(false);
            player.getProperties().get(k).setMortgaged(false);
            if(player.getProperties().get(k) instanceof Avenue) player.getProperties().get(k).setRent(((Avenue)player.getProperties().get(k)).getBasedRent());
            else if(player.getProperties().get(k) instanceof RailRoad) player.getProperties().get(k).setRent(2500);
        }
    }
}