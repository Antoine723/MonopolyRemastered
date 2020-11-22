/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgame;

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
    static boolean isChoiceCorrect;
    static int randomNum;
    static int i;
    static int index_player;
    static Scanner scanner = new Scanner(System.in);
    static Scanner prison_choice_scanner = new Scanner(System.in);
    static Scanner turn_choice_scanner=new Scanner(System.in);
    static Scanner wantToBuyScanner = new Scanner(System.in);
    static Random rand=new Random();
    static boolean useAttack=true;
    static int activationTurn=0;
    static int endActivationTurn=0;
    static int warning=1;
    static boolean choiceDone=false;

    
    
    
    static ArrayList <String> names= new ArrayList();
    static ArrayList <Integer> order= new ArrayList();
    static ArrayList <String> choices= new ArrayList();
    static ArrayList <Player> players = new ArrayList();
    static ArrayList <Player> players_in_game=new ArrayList();
    static ArrayList <Case> board=new ArrayList();
    static ArrayList <String> pieces= new ArrayList() {{
        add("Hat");
        add("Cannon");
        add("Car");
        add("Mayor");
    }};
    static ArrayList desc_pieces= new ArrayList() {{
        add("Hat, capacité = réduction de 20% sur les propriétés de couleurs bleue et verte (les plus chers) + loyer doublé pour les propriétés de couleur rouge 1 tour sur 4");
        add("Cannon, capacité = retirer une maison du joueur souhaité (mais passe son tour) + loyer doublé pour les propriétés de couleur rose 1 tour sur 4");
        add("Car, capacité = déplacement sur la case de son choix dès lors que le pion se trouve sur la  case \"Parc Gratuit\" +loyer doublé pour les propriétés de couleur orange 1 tour sur 4 ");
        add("Mayor, capacité = possibilité de poser un hôtel directement + somme touchée en passant sur la case départ est proportionnelle au nombre de propriétés");
    }};
    
    static ArrayList <Attack> attacks=new ArrayList(){{
        add(new GiveAway());
        add(new Robber());
        add(new Inflation());
    }};

    static Covid covid=new Covid();
    static Strike strike = new Strike();
    static Earthquake earthquake= new Earthquake();
    
    //--------------------------------------------------------------------------
    
    
    
    
    
    public static void main(String[] args) {
        initialize();
        //Instanciation des pions dans le main selon l'initialisation de la partie (choix des joueurs)
        for(i=0;i<numberOfPlayers;i++){
            if(players.get(i).equals("Hat")){
                Hat hat=(Hat) (players.get(i));
            }
            else if(players.get(i).equals("Cannon")){
                Cannon cannon=(Cannon) (players.get(i));
            }
            else if(players.get(i).equals("Car")){
                Car car=(Car) (players.get(i));
            }
            else if(players.get(i).equals("Mayor")){
                Mayor mayor=(Mayor) (players.get(i));
                
            }
        }
        Collections.sort(players); //Tri de l'arraylist joueurs par leur ordre de jeu
        board_creation();
        //Attribution des cartes attaques
        for (i=0;i<numberOfPlayers;i++){
            players.get(i).setAttack_card(attacks.get(rand.nextInt(attacks.size())));
            players.get(i).setPlayer_case(board.get(0));
        }
        players_in_game.addAll(players);
        
        ((Property)board.get(16)).buy(players.get(0));
        ((Property)board.get(18)).buy(players.get(0));
        ((Property)board.get(19)).buy(players.get(0));
        //((Property)board.get(1)).buy(players.get(0));
        /*players.get(1).putHouse((Avenue)board.get(3));
        players.get(0).putHouse((Avenue)board.get(1));*/
        

        
        //Tant que la partie n'est pas terminée (tant qu'il reste plus d'un joueur en jeu
        while(players_in_game.size()>1){
            System.out.println(((Avenue)board.get(16)).getRent());
            System.out.println(((Avenue)board.get(18)).getRent());
            System.out.println(((Avenue)board.get(19)).getRent());
            System.out.println("");
            numberOfTurns++;
            System.out.println("Tour n°"+numberOfTurns);
            System.out.println("");
                if( numberOfTurns%7==0 && numberOfTurns>=10){
                    if(!earthquake.isDone()) randomNum=rand.nextInt(3)+1;
                    else randomNum=rand.nextInt(2)+1;
                    switch(randomNum){
                        case 1:
                            warning=covid.closeHotel(players_in_game, warning);
                            activationTurn=numberOfTurns;
                            break;
                        case 2:
                            endActivationTurn=strike.closeRailRoad(board, numberOfTurns);
                            activationTurn=numberOfTurns;
                            break;
                        case 3:
                            earthquake.collapse(players_in_game);
                            break;
                    }
                }
            if(covid.isInAction()){
                if(numberOfTurns==activationTurn+2 || numberOfTurns==activationTurn+4 ){
                    warning=covid.closeHotel(players_in_game, warning);
                }
                else if(numberOfTurns==activationTurn+6){
                    covid.setInAction(false);
                    covid.openHotel(players_in_game);
                }
            }
            if(strike.isInAction() && numberOfTurns==endActivationTurn){
                strike.setInAction(false);
                strike.openRailRoad(board);
            }
            
            for(index_player=0;index_player<players_in_game.size();index_player++){ //On fait le tour des joueurs encore en jeu
                if(players_in_game.get(index_player).isIsInJail()) {
                    displayInventory(players_in_game.get(index_player));
                    getOutOfJail(players_in_game.get(index_player));
                }//On teste d'abord si le joueur est en prison
                else{ //S'il ne l'est pas, il joue son tour normalement
                    displayInventory(players_in_game.get(index_player));
                    choice(players_in_game.get(index_player));
                }
            }
            
            
            
        }
        
        
        
    
        
    }
    
    public static void initialize(){
        
        System.out.println("Bonjour, voici une version du Monopoly remasterisée par Antoine Asset et Thibaut Blasselle");
        System.out.println("Combien de joueurs vont jouer ? (maximum 3)"); //Max 4 pour l'instant car 4 pions
       
        try{ //Saisie du nombre de joueurs
            numberOfPlayers=scanner.nextInt();
            
        }
        catch(InputMismatchException e){
            System.out.println("Veuillez saisir un entier");

        }
        
        for(i=0;i<numberOfPlayers;i++){
            isChoiceCorrect=false;
            System.out.println("Entrez le nom du joueur");
            names.add(scanner.next());//Saisie du nom des joueurs et ajout de ceux-ci dans une arrayList de noms
            
            //Génération aléatoire de l'ordre
            Random rand=new Random(); 
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
            for(int j=0;j<desc_pieces.size();j++){
                System.out.println(desc_pieces.get(j));
            }
            
            while(isChoiceCorrect==false){
                String choice=scanner.next();
                
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
    public static void board_creation(){
        
        board.add(new Case("Départ",0));
        board.add(new Avenue(60,200,"Boulevard de Belleville",1,ColorAvenue.VIOLET,5000,3000));        // AJOUT DE L'HYPOTHEQUE EN DERNIER ARGUMENT + VRAI PRIX DES LOYERS
        board.add(new Bonus("Caisse de communauté",2));
        board.add(new Avenue(60,400,"Rue Lecourbe",3,ColorAvenue.VIOLET,5000,3000));
        board.add(new Taxes("Impôt sur le revenu",4,200));
        board.add(new RailRoad(200,2500,"Gare Montparnasse",5,10000));
        board.add(new Avenue(100,600,"Rue de Vaugirard",6,ColorAvenue.CIEL,5000,5000));
        board.add(new Bonus("Chance",7));
        board.add(new Avenue(100,600,"Rue de Courcelles",8,ColorAvenue.CIEL,5000,5000));
        board.add(new Avenue(120,800,"Avenue de la République",9,ColorAvenue.CIEL,5000,6000));
        board.add(new Prison("Prison",10));
        board.add(new Avenue(140,1000,"Boulevard de la Villette",11,ColorAvenue.ROSE,10000,7000));
        board.add(new Company(150,"Compagnie de distribution d'électricité",12,7500));
        board.add(new Avenue(140,1000,"Avenue de Neuilly",13,ColorAvenue.ROSE,10000,7000));
        board.add(new Avenue(160,1200,"Rue de Paradis",14,ColorAvenue.ROSE,10000,8000));
        board.add(new RailRoad(200,2500,"Gare de Lyon",15,10000));
        board.add(new Avenue(180,1400,"Avenue Mozart",16,ColorAvenue.ORANGE,10000,9000));
        board.add(new Bonus("Caisse de communauté",17));
        board.add(new Avenue(180,1400,"Boulevard Saint-Michel",18,ColorAvenue.ORANGE,10000,9000));
        board.add(new Avenue(200,1600,"Place Pigalle",19,ColorAvenue.ORANGE,10000,10000));
        board.add(new Case("Parc gratuit",20));
        board.add(new Avenue(220,1800,"Avenue Matignon",21,ColorAvenue.ROUGE,15000,11000));
        board.add(new Bonus("Chance",22));
        board.add(new Avenue(220,1800,"Boulevard Malesherbes",23,ColorAvenue.ROUGE,15000,11000));
        board.add(new Avenue(240,2000,"Avenue Henri-Martin",24,ColorAvenue.ROUGE,15000,12000));
        board.add(new RailRoad(200,2500,"Gare du Nord",25,10000));
        board.add(new Avenue(260,2200,"Faubourg Saint-Honoré",26,ColorAvenue.JAUNE,15000,13000));
        board.add(new Avenue(260,2200,"Place de la bourse",27,ColorAvenue.JAUNE,15000,13000));
        board.add(new Company(150,"Compagnie de distribution des eaux",28,7500));
        board.add(new Avenue(280,2400,"Rue La Fayette",29,ColorAvenue.JAUNE,15000,14000));
        board.add(new Case("Allez en prison",30));
        board.add(new Avenue(300,2600,"Avenue de Breuteuil",31,ColorAvenue.VERT,20000,15000));
        board.add(new Avenue(300,2600,"Avenue Foch",32,ColorAvenue.VERT,20000,15000));
        board.add(new Bonus("Caisse de communauté",33));
        board.add(new Avenue(320,2800,"Boulevard des Capucines",34,ColorAvenue.VERT,20000,16000));
        board.add(new RailRoad(200,2500,"Gare Saint-Lazare",35,10000));
        board.add(new Bonus("Chance",36));
        board.add(new Avenue(350,3500,"Avenue des Champs-Elysées",37,ColorAvenue.BLEU,20000,17500));
        board.add(new Taxes("Taxe de luxe",38,100));
        board.add(new Avenue(400,5000,"Rue de la Paix",39,ColorAvenue.BLEU,20000,20000));
    }

    public static void choice(Player player){
        
        ArrayList <ColorAvenue> group_color=new ArrayList();
        ArrayList <Avenue> avenues_where_to_destroy_house= new ArrayList();
        Scanner prop_to_sell_scanner=new Scanner(System.in);
        boolean turn_choice=false;//booléen qui va permettre de savoir si l'utilisateur joue sa carte attaque (et donc ne fait que ça lors de son tour)
        boolean first_turn_choice=false; //booléen qui va permettre de savoir si l'utilisateur a déjà fait un premier choix (vente de propriété ou pose de bâtiment), car il peut faire l'un des 2 + lancer les dés
        boolean sellProp=false; //variables booléennes pour savoir ce qu'on va proposer comme choix au joueur
        boolean putHouseHotel=false;
        boolean canDestroy=false;
        while(!turn_choice){
            if(!player.getProperties().isEmpty()) sellProp=true; //Si le joueur possède des propriétés, on pourra lui propsoer d'en vendre
            group_color=groupOfAvenues(player,player.getAvenues());
            if(!group_color.isEmpty() && !first_turn_choice) putHouseHotel=true; //Si le joueur possède un groupe complet d'avenues (de même couleur), on va pouvoir lui proposer de poser maisons et hôtels
            System.out.println("Vous pouvez : ");
            System.out.println("Lancer les dés (rollsdice) ");
            if(!player.getAttack_card().isItUsed() && !first_turn_choice) {
                System.out.println("Utiliser votre carte attaque (attack) "+player.getAttack_card().getName()+" "+player.getAttack_card().getEffect());
                System.out.println("Rappel : utiliser votre carte attaque vous fait passer votre tour");
            }
            if(sellProp && !first_turn_choice) {
                System.out.println("Vendre des propriétés (sell)");
                System.out.println("Hypothéquer une propriété (mortgage)");
            }
            for(i=0;i<players.size();i++){ //Cette partie ne sert que pour le pion canon
                if(players.get(i)!=player){
                    for(int j=0;j<players.get(i).getAvenues().size();j++){
                        if(players.get(i).getAvenues().get(j).getHouse()>0) {
                            canDestroy=true;
                            avenues_where_to_destroy_house.add(players.get(i).getAvenues().get(j));
                        }
                    }
                }
            }
            if(player instanceof Cannon && canDestroy) System.out.println("Détruire une maison (shoot)");
            if(putHouseHotel && !first_turn_choice) System.out.println("Poser des maisons ou hôtel sur vos avenues (putHouse)"); 
            if(player instanceof Hat && numberOfTurns%2==0 && numberOfTurns>=5) System.out.println("Essayer de récupérer de l'argent en arnaquant les autres joueurs (scam), attention, cela passe votre tour");
                switch(turn_choice_scanner.nextLine()){
                    case "shoot":
                        ((Cannon)player).shoot(avenues_where_to_destroy_house);
                        turn_choice=true;
                        break;
                    case "attack": //Attention prendre en compte si l'utilisateur effectue une action qu'il n'a pas le droit de faire
                        if(!player.getAttack_card().isItUsed()){
                            turn_choice=player.getAttack_card().effect(players,player,board);
                        }
                        else System.out.println("Vous avez déjà utilisé votre carte");
                        break;
                    case "sell": //Fini, mais voir pour ajouter while par rapport au choix et optimiser le code (ex : déclaration de variables)
                        if(sellProp){
                            if(!first_turn_choice){
                                boolean canSell=true;
                                System.out.println("Laquelle de vos propriétés souhaitez-vous vendre ?");
                                displayProperties(player);
                                String prop_to_sell_name=prop_to_sell_scanner.nextLine();
                                Property prop_to_sell=null;
                                for(i=0;i<player.getProperties().size();i++){
                                    if(player.getProperties().get(i).getName().equals(prop_to_sell_name)) prop_to_sell=player.getProperties().get(i);
                                }
                                if(prop_to_sell.isMortgaged()){
                                    System.out.println("Vous avez hypothéqué cette propriété. Pour pouvoir la vendre, vous devez payer le prix de l'hypothèque qui est de "+prop_to_sell.getMortgage()+" Francs");
                                    System.out.println("Voulez-vous payer ce prix pour la vendre ?");
                                    choiceDone=false;
                                    do{
                                        switch(wantToBuyScanner.nextLine()){
                                        case "Oui":
                                            player.setCapital(player.getCapital()-prop_to_sell.getMortgage());
                                            prop_to_sell.setMortgaged(false);
                                            choiceDone=true;
                                            break;
                                        case "Non":
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
                                    Scanner who_to_sell_scanner=new Scanner(System.in);
                                    System.out.println("A qui souhaitez-vous le vendre ?");
                                    displayPlayers(player);
                                    Player player_to_sell=null;
                                    String player_to_sell_name=who_to_sell_scanner.nextLine();
                                    for(i=0;i<players.size();i++){
                                        if(players.get(i).getName().equals(player_to_sell_name)) player_to_sell=players.get(i);
                                    }
                                    System.out.println(player_to_sell_name+", souhaitez-vous acheter "+prop_to_sell_name+" à "+player.getName()+" ?");
                                    Scanner answer_from_buyer_scanner= new Scanner(System.in);
                                    switch(answer_from_buyer_scanner.nextLine()){
                                        case "Oui":
                                            prop_to_sell.sellToSomeone(player, player_to_sell);
                                            break;
                                        case "Non":
                                            break;
                                    }
                                    first_turn_choice=true;
                                }
                        }
                        else System.out.println("Vous n'avez pas de propriétés à vendre");
                        break;
                        }
                    case "scam":
                        ((Hat)player).scam(players_in_game);
                        turn_choice=true;
                        break;
                    case "putHouse":
                        if(putHouseHotel){ //Même remarque que pour sellProp
                            if(!first_turn_choice){
                                Scanner avenues_put_house_hotel_scanner=new Scanner(System.in);
                                Scanner choice_house_or_hotel_scanner=new Scanner(System.in);
                                ArrayList <Avenue> avenues_player_have_all_group=new ArrayList();
                                for(i=0;i<player.getAvenues().size();i++){
                                    if(group_color.contains((player.getAvenues().get(i)).getColor())) avenues_player_have_all_group.add(player.getAvenues().get(i));
                                }
                                System.out.println("Sur quelle(s) avenue(s) voulez-vous poser une maison ou un hôtel ?");
                                for(i=0;i<avenues_player_have_all_group.size();i++){
                                    System.out.println(avenues_player_have_all_group.get(i).getName());
                                }
                                Avenue avenue_where_put_house=null;
                                String avenue_where_put_house_name;
                                do{
                                    System.out.println("");
                                    System.out.println("Saisissez le nom de l'avenue");
                                    avenue_where_put_house_name=avenues_put_house_hotel_scanner.nextLine();
                                    for(i=0;i<avenues_player_have_all_group.size();i++){
                                        if(avenue_where_put_house_name.equals(avenues_player_have_all_group.get(i).getName())) avenue_where_put_house=avenues_player_have_all_group.get(i);
                                    }
                                    if(player instanceof Mayor){
                                        System.out.println("Vous pouvez mettre un hôtel directement pour la somme de 50 000 Francs");
                                    }
                                    System.out.println("Voulez-vous mettre un hôtel ou une maison ?");
                                    switch(choice_house_or_hotel_scanner.nextLine()){
                                        case "hotel":
                                            if(player instanceof Mayor) ((Mayor)player).buildHotel(avenue_where_put_house);
                                            else player.putHotel(avenue_where_put_house,avenues_player_have_all_group);
                                            break;
                                        case "maison":
                                            player.putHouse(avenue_where_put_house,avenues_player_have_all_group);
                                            break;
                                        default:
                                            System.out.println("Veuillez choisir un argument valide");
                                            break;
                                    }

                                }while(!avenue_where_put_house_name.equals("Stop"));
                                first_turn_choice=true;
                            }

                        }
                        else System.out.println("Vous ne pouvez pas poser de maison ou d'hôtel, il vous faut un groupe complet d'avenues de même couleur pour pouvoir en poser");
                        break;
                    case "mortgage":
                        if(!first_turn_choice){
                            boolean canMortgage=false;
                            do{
                                Scanner prop_to_mortgage_scanner= new Scanner(System.in);
                                Property prop_to_mortgage=null;
                                System.out.println("Quelle propriété voulez-vous hypothéquer ?");
                                displayProperties(player);
                                String prop_to_mortgage_name=prop_to_mortgage_scanner.nextLine();
                                for(i=0;i<player.getProperties().size();i++){
                                    if(player.getProperties().get(i).getName().equals(prop_to_mortgage_name)) prop_to_mortgage=(Property)(player.getProperties().get(i));
                                }
                                if(prop_to_mortgage.isMortgaged()) System.out.println("Vous avez déjà hypothéqué cette maison, veuillez en choisir une autre");
                                else{
                                    player.putOnMortgage(player, prop_to_mortgage);
                                    canMortgage=true;
                                }
                            }while(!canMortgage);
                            first_turn_choice=true;
                        }
                        break;
                    case "rollsdice":
                        int count_double=0;
                        ArrayList <Integer> result_of_dice;
                        do{
                            result_of_dice=player.rollsDice();
                            int sum_of_dice=result_of_dice.get(0)+result_of_dice.get(1);
                            if(result_of_dice.get(0).equals(result_of_dice.get(1))) {
                                count_double++;
                                System.out.println("Vous avez fait un double, quel petit veinard !");
                            }
                            move(sum_of_dice,player);
                            //Effet de la case départ (rémunération) géré dans move
                            if(player.getPlayer_case() instanceof Bonus) ((Bonus)(player.getPlayer_case())).effect(player, board); //Effet de cases bonus
                            if(player.getPlayer_case() instanceof Taxes) {
                                player.setCapital(player.getCapital()-((Taxes)(player.getPlayer_case())).getPrice());
                                System.out.println("Vous avez payé "+((Taxes)(player.getPlayer_case())).getPrice()+" francs");
                            }
                            if(player.getPlayer_case().equals(board.get(30))) {
                                player.inJail(board);
                                count_double=3; //On met cette condition pour sortir de la boucle et donc pour que le joueur ne puisse pas rejouer même s'il a fait un double
                            }
                            if(player.getPlayer_case().getName().equals("Parc gratuit") && player instanceof Car) ((Car)(player)).moveTo(board);
                            if(player.getPlayer_case() instanceof Property && ((Property)(player.getPlayer_case())).isItBought() && !((Property)(player.getPlayer_case())).getAssociatedPlayer().equals(player) ){
                                Property prop=(Property)player.getPlayer_case();
                                if( prop.isMortgaged()){
                                    System.out.println("Cette propriété a été hypothéquée, voulez-vous la racheter au prix de "+(prop.getBoughtPrice()+prop.getMortgage())+" Francs ?");
                                    switch(wantToBuyScanner.nextLine()){
                                        case "Oui":
                                            prop.buy(player);
                                            break;
                                    }
                                }
                                else{
                                    player.setCapital(player.getCapital()-(prop.getRent()));
                                    System.out.println("Vous êtes chez "+prop.getAssociatedPlayer().getName()+ ", vous avez payé "+prop.getRent());

                                }
                            }
                            else if(player.getPlayer_case() instanceof Property && !((Property)(player.getPlayer_case())).isItBought()){ //Dernier cas à faire : si la propriété n'est pas achetée, on propose au joueur de l'acheter
                                System.out.println("Voulez-vous acheter "+player.getPlayer_case().getName()+" ? (Oui/Non)");
                                Scanner want_to_buy_scanner = new Scanner(System.in);
                                switch(want_to_buy_scanner.nextLine()){
                                    case "Oui":
                                        ((Property)(player.getPlayer_case())).buy(player);
                                        System.out.println("Vous avez acheté "+player.getPlayer_case().getName());
                                }
                            }
                        }while(result_of_dice.get(0).equals(result_of_dice.get(1)) && count_double<3);

                        if(count_double==3){
                            System.out.println("Vous avez fait 3 doubles de suite, vous allez en prison !");
                            player.inJail(board);
                        }
                        turn_choice=true;
                        break;

                }

            }




        }
        //Possibilité de poser maisons/hôtels sur ses propriétés SI il en a ET qu'il a tout le groupe de couleur (ET qu'il a assez d'argent), il peut lancer les dés ensuite
        //Possibilité de vendre propriétés SI il en possède une OK
        //Possibilité de jouer carte attaque SI il ne l'a pas encore utilisée
        //Possibilité de lancer les dés --> Affiche le nombre qu'il a fait, déplace le joueur et affiche la case sur laquelle il est : 
        //      SI propriété --> SI appartient à un autre joueur, affiche montant à payer et paye le joueur en question
        //                   --> SI n'appartient à personne, affiche qu'il peut l'acheter + montant à payer + attente du choix, SI oui alors attribue la prop au joueur + réduit son capital
        //      SI pas propriété --> effectue l'effet s'il en a un
        
        //ATTENTION TOUJOURS PRENDRE EN COMPTE EVENEMENT
        
        //AJOUTER EFFET DU PION

    
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
        int arrival_case_number= (player.getPlayer_case().getCaseNumber()+sum_of_dice)%40;
        Case arrival_case = null;
        boolean start=false;
        for(i=0;i<board.size();i++){
            if(board.get(i).getCaseNumber()==arrival_case_number) arrival_case=board.get(i);
        }
        if(arrival_case_number<player.getPlayer_case().getCaseNumber()){
            player.setCapital(player.getCapital()+20000);
        }
        player.setPlayer_case(arrival_case);
        System.out.println("Vous êtes sur la case "+arrival_case.getName());
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
                if(((Avenue)(player.getProperties().get(i))).getHotel()>0) System.out.println((player.getProperties().get(i)).getName()+", de couleur "+((Avenue)player.getProperties().get(i)).getColor()+" avec "+((Avenue)(player.getProperties().get(i))).getHotel()+" hôtel dessus, ");
                else if( ((Avenue)(player.getProperties().get(i))).getHouse()>0) System.out.println((player.getProperties().get(i)).getName()+", de couleur "+((Avenue)player.getProperties().get(i)).getColor()+ " avec "+((Avenue)(player.getProperties().get(i))).getHouse()+" maison(s) dessus, ");
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
        ArrayList <Avenue> all_board_avenues=new ArrayList();
        ArrayList <Avenue> group_board_avenues=new ArrayList();
        ArrayList <ColorAvenue> color_groups=new ArrayList();
        for(i=0;i<board.size();i++){ //On récupère la liste des avenues du plateau de jeu
            if(board.get(i) instanceof Avenue){
                all_board_avenues.add((Avenue)(board.get(i)));
            }
        }
        for(i=0;i<all_board_avenues.size();i++){ //On parcourt cette liste
            if(!group_board_avenues.isEmpty() && all_board_avenues.get(i).getColor().equals(group_board_avenues.get(0).getColor())) group_board_avenues.add(all_board_avenues.get(i)); //group_board_avenues est une liste qui va contenir les avenues qui sont de même couleur
            else if(!group_board_avenues.isEmpty() && !all_board_avenues.get(i).getColor().equals(group_board_avenues.get(0).getColor())){
                if(avenues.containsAll(group_board_avenues)) color_groups.add(group_board_avenues.get(0).getColor());
                group_board_avenues.clear();
                group_board_avenues.add(all_board_avenues.get(i));
            }
            else{
                group_board_avenues.add(all_board_avenues.get(i));
            }
            
        }
        return color_groups; //On va, au fur et à mesure, insérer dans une liste (group_board_avenues) les avenues de même couleur, puis comparer avec celles possédées du joueur pour savoir s'il a le groupe en question, si c'est le cas, on ajotue à notre liste "color_group" la couleur du groupe qu'il possède
    }
   
    
    
    public static void getOutOfJail(Player player)
    {
        boolean tryDone=false;
        System.out.println("Pour sortir, faites votre choix : freecard si vous avec une carte de libération, pay si vous voulez payer (50€), ou roll si vous voulez tenter votre chance avec un double");
        do {
            switch(prison_choice_scanner.nextLine())                           // on lit la réponse du joueur
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

                    if(player.getCapital() >= 50)                           // si le joueur souhaite payer la caution
                    {
                        player.setIsInJail(false);
                        player.setCapital(player.getCapital() - 50);        // on actualise le capital
                        System.out.println("Vous disposez à présent de " + player.getCapital() + " euros");
                        tryDone=true;
                    }
                    break;

                case "roll":
                    int firstDice=player.rollsDice().get(0);
                    int secondDice=player.rollsDice().get(1);
                    System.out.println("Vous avez fait "+firstDice+" et "+secondDice);
                    if (firstDice==secondDice)    // si le joueur a fait un double
                    {
                        player.setIsInJail(false);
                        System.out.println("Félicitations ! Votre double vous permet de sortir de prison");
                    }
                    else
                    {
                        System.out.println("Dommage ! Vous restez en prison");
                        player.setIsInJail(true);
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