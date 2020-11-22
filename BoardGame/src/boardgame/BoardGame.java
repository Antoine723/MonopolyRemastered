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
    static int index_player;
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
    static boolean choiceDone=false;
    static boolean turnChoice;
    static boolean firstTurnChoice;
    static boolean sellProp; 
    static boolean putHouseHotel;
    static boolean canDestroy;
    static boolean canSell;
    static boolean putAgain;
    static boolean canMortgage;
    static boolean isChoiceCorrect;
    
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
        board_creation("board.txt");
        //Attribution des cartes attaques
        for (i=0;i<numberOfPlayers;i++){
            players.get(i).setAttack_card(attacks.get(rand.nextInt(attacks.size())));
            players.get(i).setPlayer_case(board.get(0));
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
            
            for(index_player=0;index_player<playersInGame.size();index_player++){ //On fait le tour des joueurs encore en jeu
                if(playersInGame.get(index_player).isIsInJail()) {
                    displayInventory(playersInGame.get(index_player));
                    getOutOfJail(playersInGame.get(index_player));
                }//On teste d'abord si le joueur est en prison
                else{ //S'il ne l'est pas, il joue son tour normalement
                    displayInventory(playersInGame.get(index_player));
                    choice(playersInGame.get(index_player));
                }
            }
            
            
            
        }
        
    }
    
    public static void initialize(){
        
        System.out.println("Bonjour, voici une version du Monopoly remasterisée par Antoine Asset et Thibaut Blasselle");
        System.out.println("Combien de joueurs vont jouer ? (maximum 3)"); //Max 4 pour l'instant car 4 pions
        while(!choiceDone){
            try{ //Saisie du nombre de joueurs
            numberOfPlayers=numberPlayerScanner.nextInt();
            choiceDone=true;
            }
            catch(InputMismatchException e){
                System.out.println("Veuillez saisir un entier");
                numberPlayerScanner.nextLine();
            }
        }
        choiceDone=false;
        
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
    public static void board_creation(String pathName){
        try (BufferedReader reader = new BufferedReader(new FileReader(pathName))) {
            while( (line=reader.readLine())!=null){
                String [] splitted=line.split("\t"); //On split la ligne récupéré au niveau des tabulations et on la stocke dans un tableau
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
            if(!player.getAttack_card().isItUsed() && !firstTurnChoice) {
                System.out.println("Utiliser votre carte attaque (attack) "+player.getAttack_card().getName()+" "+player.getAttack_card().getEffect());
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
            choiceDone=false;
            while(!choiceDone){
                switch(stringScanner.next()){
                    case "shoot":
                        ((Cannon)player).shoot(avenuesWhereDestroyHouse);
                        turnChoice=true;
                        choiceDone=true;
                        break;
                    case "attack": //Attention prendre en compte si l'utilisateur effectue une action qu'il n'a pas le droit de faire
                        if(!player.getAttack_card().isItUsed()){
                            turnChoice=player.getAttack_card().effect(players,player,board);
                        }
                        else System.out.println("Vous avez déjà utilisé votre carte");
                        choiceDone=true;
                        break;
                    case "sell": //Fini, mais voir pour ajouter while par rapport au choix et optimiser le code (ex : déclaration de variables)
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
                                if(propToSell.isMortgaged()){
                                    System.out.println("Vous avez hypothéqué cette propriété. Pour pouvoir la vendre, vous devez payer le prix de l'hypothèque qui est de "+propToSell.getMortgage()+" Francs");
                                    System.out.println("Voulez-vous payer ce prix pour la vendre ?");
                                    choiceDone=false;
                                    do{
                                        switch(stringScanner.nextLine()){
                                        case "Oui":case"oui":case"OUI":
                                            player.setCapital(player.getCapital()-propToSell.getMortgage());
                                            propToSell.setMortgaged(false);
                                            choiceDone=true;
                                            break;
                                        case "Non":case"non":case"NON":
                                            canSell=false;
                                            choiceDone=true;
                                            System.out.println("Vous n'avez pas payé le prix de l'hypothèque, vous ne pouvez donc pas vendre cette propriété");
                                            break;
                                        default:
                                            System.out.println("Veuillez choisir un argument valide");
                                            break;
                                    }
                                    }while(!choiceDone);
                                }
                                if(canSell){
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
                        choiceDone=true;
                        break;
                        }
                    case "scam":
                        ((Hat)player).scam(playersInGame);
                        turnChoice=true;
                        choiceDone=true;
                        break;
                    case "putHouse":case"puthouse":
                        if(putHouseHotel){ //Même remarque que pour sellProp
                            if(!firstTurnChoice){
                                //Scanner avenues_put_house_hotel_scanner=new Scanner(System.in);
                                //Scanner choice_house_or_hotel_scanner=new Scanner(System.in);
                                avenuesPlayerCanPut.clear();
                                for(i=0;i<player.getAvenues().size();i++){
                                    if(groupColor.contains((player.getAvenues().get(i)).getColor())) avenuesPlayerCanPut.add(player.getAvenues().get(i));
                                }
                                putAgain=true;
                                while(putAgain){
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
                        choiceDone=true;
                        break;
                    case "mortgage":
                        if(!firstTurnChoice){
                            canMortgage=false;
                            while(!canMortgage){
                                //Scanner prop_to_mortgage_scanner= new Scanner(System.in);
                                propToMortgage=null;
                                System.out.println("Quelle propriété voulez-vous hypothéquer ?");
                                displayProperties(player);
                                stringScanner.nextLine();
                                while(propToMortgage==null){
                                    propToMortgageName=stringScanner.nextLine();
                                    for(i=0;i<player.getProperties().size();i++){
                                        if(player.getProperties().get(i).getName().equals(propToMortgageName)) propToMortgage=(Property)(player.getProperties().get(i));
                                    }
                                    if(propToMortgage==null) System.out.println("Veuillez saisir une propriété valide");
                                }
                                
                                if(propToMortgage.isMortgaged()) System.out.println("Vous avez déjà hypothéqué cette maison, veuillez en choisir une autre");
                                else{
                                    player.putOnMortgage(player, propToMortgage);
                                    System.out.println("Vous avez bien hypothéqué "+propToMortgage.getName());
                                    canMortgage=true;
                                }
                            }
                            firstTurnChoice=true;
                            choiceDone=true;
                        }
                        break;
                    case "rollsdice":
                        countDouble=0;
                        do{
                            resultOfDice=player.rollsDice();
                            sumOfDice=resultOfDice.get(0)+resultOfDice.get(1);
                            if(resultOfDice.get(0).equals(resultOfDice.get(1))) {
                                countDouble++;
                                System.out.println("Vous avez fait un double, quel petit veinard !");
                            }
                            move(sumOfDice,player); //Effet de la case départ (rémunération) géré dans move
                            if(player.getPlayer_case() instanceof Bonus) ((Bonus)player.getPlayer_case()).effect(player, board); //Effet de cases bonus
                            if(player.getPlayer_case() instanceof Taxes) {
                                player.setCapital(player.getCapital()-((Taxes)player.getPlayer_case()).getPrice());
                                System.out.println("Vous avez payé "+((Taxes)player.getPlayer_case()).getPrice()+" francs");
                            }
                            if(player.getPlayer_case().equals(board.get(30))) {
                                player.inJail(board);
                                countDouble=3; //On met cette condition pour sortir de la boucle et donc pour que le joueur ne puisse pas rejouer même s'il a fait un double
                            }
                            if(player.getPlayer_case().getName().equals("Parc gratuit") && player instanceof Car) ((Car)player).moveTo(board);
                            if(player.getPlayer_case() instanceof Property && ((Property)player.getPlayer_case()).isItBought() && !((Property)player.getPlayer_case()).getAssociatedPlayer().equals(player)){
                                Property prop=(Property)player.getPlayer_case();
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
                                    System.out.println("Vous êtes chez "+prop.getAssociatedPlayer().getName()+ ", vous avez payé "+prop.getRent());

                                }
                            }
                            else if(player.getPlayer_case() instanceof Property && !((Property)player.getPlayer_case()).isItBought()){ //Dernier cas à faire : si la propriété n'est pas achetée, on propose au joueur de l'acheter
                                System.out.println("Voulez-vous acheter "+player.getPlayer_case().getName()+" ? (Oui/Non)");
                                isChoiceCorrect=false;
                                while(!isChoiceCorrect){
                                    switch(stringScanner.next()){
                                       case "Oui":case"oui":case"OUI":
                                           ((Property)player.getPlayer_case()).buy(player);
                                           System.out.println("Vous avez acheté "+player.getPlayer_case().getName());
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
                        }while(resultOfDice.get(0).equals(resultOfDice.get(1)) && countDouble<3);

                        if(countDouble==3){
                            System.out.println("Vous avez fait 3 doubles de suite, vous allez en prison !");
                            player.inJail(board);
                        }
                        turnChoice=true;
                        choiceDone=true;
                        break;
                    default:
                        System.out.println("Veuillez saisir un argument valide");
                        break;
                }
            }

        }




        }

    
    public static void displayPlayers(Player player){
        for(i=0;i<players.size();i++){
            if(!players.get(i).equals(player)){
                System.out.println(players.get(i).getName());
            }
        }
    }
    
    public static void displayProperties(Player player){
        for(i=0;i<player.getProperties().size();i++){
            if(player.getProperties().get(i) instanceof Avenue){
                System.out.println(player.getProperties().get(i).getName()+", de couleur "+((Avenue)player.getProperties().get(i)).getColor());
            }
        }
    }
    
    public static void move(int sum_of_dice, Player player){
        System.out.println("Vous avez fait "+sum_of_dice);
        arrivalCaseNumber= (player.getPlayer_case().getCaseNumber()+sum_of_dice)%40;
        arrivalCase = null;
        for(i=0;i<board.size();i++){
            if(board.get(i).getCaseNumber()==arrivalCaseNumber) arrivalCase=board.get(i);
        }
        if(arrivalCaseNumber<player.getPlayer_case().getCaseNumber()){
            player.setCapital(player.getCapital()+20000);
        }
        if(arrivalCase!=null){ //Juste pour éviter des warnings, mais normalement arrivalCase ne devrait jamais être null
            player.setPlayer_case(arrivalCase);
            System.out.println("Vous êtes sur la case "+arrivalCase.getName());
        }
        else System.out.println("Cette erreur ne devrait pas arriver");
    }
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
                else System.out.println((player.getProperties().get(i)).getName());
            }
            else if(player.getProperties().get(i) instanceof RailRoad){
                System.out.println(((RailRoad)player.getProperties().get(i)).getName());
            }
            else if(player.getProperties().get(i) instanceof Company){
                System.out.println( ((Company)player.getProperties().get(i)).getName() );
            } //Si la case est une propriété, on caste la case pour pouvoir utiliser les méthodes propres à la classe Property
            
        }
        if(!player.getAttack_card().isItUsed()){
            System.out.println("");
            System.out.println("Vous pouvez utiliser votre carte attaque "+player.getAttack_card().getName());
            System.out.println("En utilisant cette carte, "+player.getAttack_card().getEffect());
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
        System.out.println("Vous êtes actuellement sur la case n° "+player.getPlayer_case().getCaseNumber()+", "+player.getPlayer_case().getName());
    }
    
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
            if(!groupBoardAvenues.isEmpty() && allBoardAvenues.get(i).getColor().equals(groupBoardAvenues.get(0).getColor())) groupBoardAvenues.add(allBoardAvenues.get(i)); //group_board_avenues est une liste qui va contenir les avenues qui sont de même couleur
            else if(!groupBoardAvenues.isEmpty() && !allBoardAvenues.get(i).getColor().equals(groupBoardAvenues.get(0).getColor())){
                if(avenues.containsAll(groupBoardAvenues)) colorGroups.add(groupBoardAvenues.get(0).getColor());
                groupBoardAvenues.clear();
                groupBoardAvenues.add(allBoardAvenues.get(i));
            }
            else{
                groupBoardAvenues.add(allBoardAvenues.get(i));
            }
            
        }
        return colorGroups; //On va, au fur et à mesure, insérer dans une liste (group_board_avenues) les avenues de même couleur, puis comparer avec celles possédées du joueur pour savoir s'il a le groupe en question, si c'est le cas, on ajotue à notre liste "color_group" la couleur du groupe qu'il possède
    }
   
    
    
    public static void getOutOfJail(Player player)
    {
        tryDone=false;
        System.out.println("Pour sortir, faites votre choix : freecard si vous avec une carte de libération, pay si vous voulez payer ("+deposit+" Francs), ou roll si vous voulez tenter votre chance avec un double");
        do {
            switch(stringScanner.nextLine())                           // on lit la réponse du joueur
            {
                case "freecard":                

                    if(player.getFree_card() > 0)               // si le joueur posséde une carte, il sort de prison
                    {
                        player.setIsInJail(false);
                        player.setFree_card(player.getFree_card() - 1);     // on actualise le nombre de cartes
                        System.out.println("Il vous reste à présent " + player.getFree_card() + " cartes pour sortir de prison");
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
}