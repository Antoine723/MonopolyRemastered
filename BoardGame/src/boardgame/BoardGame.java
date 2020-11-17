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
    static Scanner scanner = new Scanner(System.in);
    static Scanner prison_choice_scanner = new Scanner(System.in);
    static Scanner turn_choice_scanner=new Scanner(System.in);
    static Random rand=new Random();
    static boolean useAttack=true;
    
    static GiveAway giveAway=new GiveAway();
    static Inflation inflation=new Inflation();
    static Robber robber=new Robber();
    
    static Covid covid=new Covid();
    static Earthquake earthquake=new Earthquake();
    static Strike strike=new Strike();
    
    
    
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
        add(giveAway);
        add(robber);
        add(inflation);
    }};
    static ArrayList <Event> events=new ArrayList(){{
        add(covid);
        add(earthquake);
        add(strike);
    }};
    
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
        
        //Attribution des cartes attaques
        for (i=0;i<numberOfPlayers;i++){
            players.get(i).setAttack_card(attacks.get(rand.nextInt(attacks.size())));
        }
        players_in_game.addAll(players);
        board_creation();
        /*Avenue ave= (Avenue) board.get(1);
        ave.setAssociatedPlayer(players.get(0));
        players.get(0).setNumberOfAvenues(players.get(0).getNumberOfAvenues());
        ave.setHouse(ave.getHouse()+2);
        Avenue ave2= (Avenue) board.get(3);
        ave2.setAssociatedPlayer(players.get(0));
        players.get(0).setNumberOfAvenues(players.get(0).getNumberOfAvenues());
        ave2.setHotel(ave2.getHotel()+1);
        Covid cov=(Covid) (events.get(0));
        cov.setInAction(true);
        RailRoad rai= (RailRoad) (board.get(15));
        rai.setAssociatedPlayer(players.get(0));
        Company com=(Company) (board.get(12));
        com.setAssociatedPlayer(players.get(0));*/
        
        /*players.get(0).addProperty((Avenue)(board.get(1)));
        players.get(0).addProperty((Avenue)(board.get(3)));
        players.get(0).addProperty((Avenue)(board.get(6)));
        players.get(0).addProperty((Avenue)(board.get(9)));
        ArrayList <Avenue> play_ave = new ArrayList();
        for(i=0;i<players.get(0).getProperties().size();i++){
            if(players.get(0).getProperties().get(i) instanceof Avenue) play_ave.add((Avenue)(players.get(0).getProperties().get(i)));
        }
        ArrayList <String> test=groupOfAvenues(players.get(0),play_ave);
        for(i=0;i<test.size();i++){
            System.out.println(test.get(i));
        }
        /*for(i=0;i<numberOfPlayers;i++){
            displayInventory(players.get(i));
        }*/
        //Tant que la partie n'est pas terminée (tant qu'il reste plus d'un joueur en jeu
        /*while(players_in_game.size()>1){
            numberOfTurns++;
            for(i=0;i<players_in_game.size();i++){ //On fait le tour des joueurs encore en jeu
                if(players_in_game.get(i).isIsInJail()) getOutOfJail(players_in_game.get(i));//On teste d'abord si le joueur est en prison
                else{ //S'il ne l'est pas, il joue son tour normalement
                    displayInventory(players_in_game.get(i));
                    //choice();
                }
            }
            
            
            
        }*/
        
        
        
    
        
    }
    
    public static void initialize(){
        
        System.out.println("Bonjour, voici une version du Monopoly remasterisée par Antoine Asset et Thibaut Blasselle");
        System.out.println("Combien de joueurs vont jouer ? (maximum 3)"); //Max 3 pour l'instant car 3 cartes attaques, sinon possibilité d'avoir plusieurs joeuurs avec la même carte attaque
       
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
        board.add(new Avenue(60,200,"Boulevard de Belleville",1,"Marron",3000));        // AJOUT DE L'HYPOTHEQUE EN DERNIER ARGUMENT + VRAI PRIX DES LOYERS
        board.add(new Bonus("Caisse de communauté",2));
        board.add(new Avenue(60,400,"Rue Lecourbe",3,"Marron",3000));
        board.add(new Taxes("Impôt sur le revenu",4,200));
        board.add(new RailRoad(200,2500,"Gare Montparnasse",5,10000));
        board.add(new Avenue(100,600,"Rue de Vaugirard",6,"Ciel",5000));
        board.add(new Bonus("Chance",7));
        board.add(new Avenue(100,600,"Rue de Courcelles",8,"Ciel",5000));
        board.add(new Avenue(120,800,"Avenue de la République",9,"Ciel",6000));
        board.add(new Prison("Prison",10));
        board.add(new Avenue(140,1000,"Boulevard de la Villette",11,"Rose",7000));
        board.add(new Company(150,"Compagnie de distribution d'électricité",12,7500));
        board.add(new Avenue(140,1000,"Avenue de Neuilly",13,"Rose",7000));
        board.add(new Avenue(160,1200,"Rue de Paradis",14,"Rose",8000));
        board.add(new RailRoad(200,2500,"Gare de Lyon",15,10000));
        board.add(new Avenue(180,1400,"Avenue Mozart",16,"Orange",9000));
        board.add(new Bonus("Caisse de communauté",17));
        board.add(new Avenue(180,1400,"Boulevard Saint-Michel",18,"Orange",9000));
        board.add(new Avenue(200,1600,"Place Pigalle",19,"Orange",10000));
        board.add(new Case("Parc gratuit",20));
        board.add(new Avenue(220,1800,"Avenue Matignon",21,"Rouge",11000));
        board.add(new Bonus("Chance",22));
        board.add(new Avenue(220,1800,"Boulevard Malesherbes",23,"Rouge",11000));
        board.add(new Avenue(240,2000,"Avenue Henri-Martin",24,"Rouge",12000));
        board.add(new RailRoad(200,2500,"Gare du Nord",25,10000));
        board.add(new Avenue(260,2200,"Faubourg Saint-Honoré",26,"Jaune",13000));
        board.add(new Avenue(260,2200,"Place de la bourse",27,"Jaune",13000));
        board.add(new Company(150,"Compagnie de distribution des eaux",28,7500));
        board.add(new Avenue(280,2400,"Rue La Fayette",29,"Jaune",14000));
        board.add(new Case("Allez en prison",30));
        board.add(new Avenue(300,2600,"Avenue de Breuteuil",31,"Vert",15000));
        board.add(new Avenue(300,2600,"Avenue Foch",32,"Vert",15000));
        board.add(new Bonus("Caisse de communauté",33));
        board.add(new Avenue(320,2800,"Boulevard des Capucines",34,"Vert",16000));
        board.add(new RailRoad(200,2500,"Gare Saint-Lazare",35,10000));
        board.add(new Bonus("Chance",36));
        board.add(new Avenue(350,3500,"Avenue des Champs-Elysées",37,"Bleu",17500));
        board.add(new Taxes("Taxe de luxe",38,100));
        board.add(new Avenue(400,5000,"Rue de la Paix",39,"Bleu",20000));
    }

    public static void choice(Player player){
        ArrayList <Avenue> player_avenues= new ArrayList();
        ArrayList <String> group_color=new ArrayList();
        Scanner prop_to_sell_scanner=new Scanner(System.in);
        boolean turn_choice=false;//booléen qui va permettre de savoir si l'utilisateur joue sa carte attaque (et donc ne fait que ça lors de son tour)
        boolean first_turn_choice=false; //booléen qui va permettre de savoir si l'utilisateur a déjà fait un premier choix (vente de propriété ou pose de bâtiment), car il peut faire l'un des 2 + lancer les dés
        boolean sellProp=false; //variables booléennes pour savoir ce qu'on va proposer comme choix au joueur
        boolean putHouseHotel=false;
        for(i=0;i<player.getProperties().size();i++){ //On va remplir la liste d'avenues du joueur à partir de sa liste de propriétés (pour proposer des choix sur ses avenues par la suite)
            if(player.getProperties().get(i) instanceof Avenue){
                player_avenues.add((Avenue)(player.getProperties().get(i))); //On ajoute toutes les avenues appartenant au joueur dans une ArrayList
            }
        }
        if(!player.getProperties().isEmpty()) sellProp=true; //Si le joueur possède des propriétés, on pourra lui propsoer d'en vendre
        group_color=groupOfAvenues(player,player_avenues);
        if(!group_color.isEmpty()) putHouseHotel=true; //Si le joueur possède un groupe complet d'avenues (de même couleur), on va pouvoir lui proposer de poser maisons et hôtels
        System.out.println("Rappel : utiliser votre carte attaque vous fait passer votre tour");
        System.out.println("Vous pouvez : ");
        System.out.println("Lancer les dés (entrez \"rollsdice\") ");
        if(!player.getAttack_card().isItUsed()) System.out.println("Utiliser votre carte attaque (entrez \"attack\") "+player.getAttack_card().getName()+" "+player.getAttack_card().getEffect());
        if(sellProp) System.out.println("Vendre des propriétés (entrez \"sell\")");
        if(putHouseHotel) System.out.println("Poser des maisons ou hôtel sur vos avenues (entrez \"putHouse\")");
        
        while(!turn_choice){
            switch(turn_choice_scanner.nextLine()){
                case "attack": //Attention prendre en compte si l'utilisateur effectue une action qu'il n'a pas le droit de faire
                    if(!player.getAttack_card().isItUsed()){
                        player.getAttack_card().effect(players,player,board);
                        turn_choice=true;
                    }
                    else System.out.println("Vous avez déjà utilisé votre carte");
                    break;
                case "sell": //Fini, mais voir pour ajouter while par rapport au choix et optimiser le code (ex : déclaration de variables)
                    if(sellProp){
                        if(!first_turn_choice){
                            System.out.println("Laquelle de vos propriétés souhaitez-vous vendre ?");
                            displayProperties(player);
                            String prop_to_sell_name=prop_to_sell_scanner.nextLine();
                            Property prop_to_sell=null;
                            for(i=0;i<player.getProperties().size();i++){
                                if(player.getProperties().get(i).equals(prop_to_sell_name)) prop_to_sell=player.getProperties().get(i);
                            }
                            System.out.println("Souhaitez-vous la vendre à un joueur ou à la banque ?");
                            Scanner who_to_sell_scanner=new Scanner(System.in);
                            switch(who_to_sell_scanner.nextLine()){
                                case "Joueur": 
                                    System.out.println("A qui souhaitez-vous le vendre ?");
                                    displayPlayers(player);
                                    Player player_to_sell=null;
                                    String player_to_sell_name=who_to_sell_scanner.nextLine();
                                    for(i=0;i<players.size();i++){
                                        if(players.get(i).equals(player_to_sell_name)) player_to_sell=players.get(i);
                                    }
                                    System.out.println(player_to_sell.getName()+", souhaitez-vous acheter "+prop_to_sell+" à "+player.getName()+" ?");
                                    Scanner answer_from_buyer_scanner= new Scanner(System.in);
                                    switch(answer_from_buyer_scanner.nextLine()){
                                        case "Oui":
                                            prop_to_sell.sellToSomeone(player, player_to_sell);
                                            break;
                                        case"Non":
                                            break;
                                    }
                                    
                                    break;
                        case "Banque":
                            prop_to_sell.sell(player);
                            break;
                        default:
                            System.out.println("Indiquez un choix possible");
                            break;
                        }
                        first_turn_choice=true;
                    }
                    else System.out.println("Vous n'avez pas de propriétés à vendre");
                    break;
                    }
                case "putHouse":
                    if(putHouseHotel){ //Même remarque que pour sellProp
                        if(!first_turn_choice){
                            Scanner avenues_put_house_hotel_scanner=new Scanner(System.in);
                            Scanner choice_house_or_hotel_scanner=new Scanner(System.in);
                            ArrayList <Avenue> avenues_player_have_all_group=new ArrayList();
                            for(i=0;i<player.getProperties().size();i++){
                                if(player.getProperties().get(i) instanceof Avenue && group_color.contains(((Avenue)(player.getProperties().get(i))).getColor())) avenues_player_have_all_group.add((Avenue)(player.getProperties().get(i)));
                            }
                            System.out.println("Sur quelle(s) avenue(s) voulez-vous poser une maison ou un hôtel ?");
                            for(i=0;i<avenues_player_have_all_group.size();i++){
                                System.out.println(avenues_player_have_all_group.get(i));
                            }
                            Avenue avenue_where_put_house=null;
                            String avenue_where_put_house_name;
                            do{
                                avenue_where_put_house_name=avenues_put_house_hotel_scanner.nextLine();
                                for(i=0;i<avenues_player_have_all_group.size();i++){
                                    if(avenue_where_put_house_name.equals(avenues_player_have_all_group.get(i).getName())) avenue_where_put_house=avenues_player_have_all_group.get(i);
                                }
                                System.out.println("Voulez-vous mettre un hôtel ou une maison ?");
                                switch(choice_house_or_hotel_scanner.nextLine()){
                                    case "hotel":
                                        boolean able_to_put_hotel=true;
                                        if(avenue_where_put_house.getHouse()!=4) System.out.println("Vous devez avoir 4 maisons sur votre avenue pour pouvoir mettre un hôtel");
                                        else{
                                            for(i=0;i<avenues_player_have_all_group.size();i++){
                                                if(avenues_player_have_all_group.get(i).getColor().equals(avenue_where_put_house.getColor()) && avenues_player_have_all_group.get(i).getHouse()!=4) able_to_put_hotel=false;
                                            }
                                            if(!able_to_put_hotel) System.out.println("Vous devez avoir 4 maisons sur toutes les avenues du groupe pour pouvoir mettre un hôtel sur l'une d'elles");
                                            else player.putHotel(avenue_where_put_house);
                                        }
                                        break;
                                    case "maison":
                                        boolean able_to_put_house=true;
                                        for(i=0;i<avenues_player_have_all_group.size();i++){
                                            if(avenues_player_have_all_group.get(i).getColor().equals(avenue_where_put_house.getColor()) && avenues_player_have_all_group.get(i).getHouse()!=avenue_where_put_house.getHouse()) able_to_put_house=false;
                                        }
                                        if(!able_to_put_house) System.out.println("Vous devez avoir le même nombre de maisons sur chacune des avenues du même groupe pour pouvoir ajouter une maison sur l'une d'elle");
                                        else player.putHouse(avenue_where_put_house);
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
                case "rollsdice":
                    ArrayList <Integer> result_of_dice=player.rollsDice();
                    int sum_of_dice=result_of_dice.get(0)+result_of_dice.get(1);
                    int count_double=0;
                    do{
                        if(result_of_dice.get(0).equals(result_of_dice.get(1))) count_double++;
                        move(sum_of_dice,player);
                        //Effet de la case départ (rémunération) géré dans move
                        if(player.getPlayer_case() instanceof Bonus) ((Bonus)(player.getPlayer_case())).effect(player, board); //Effet de cases bonus
                        if(player.getPlayer_case() instanceof Taxes) player.setCapital(player.getCapital()-((Taxes)(player.getPlayer_case())).getPrice());
                        if(player.getPlayer_case() instanceof Prison) {
                            player.inJail(board);
                            count_double=3; //On met cette condition pour sortir de la boucle et donc pour que le joueur ne puisse pas rejouer même s'il a fait un double
                        }
                        if(player.getPlayer_case().getName().equals("Parc gratuit") && player instanceof Car) ((Car)(player)).moveTo(board);
                        if(player.getPlayer_case() instanceof Property && ((Property)(player.getPlayer_case())).isItBought()) player.setCapital(player.getCapital()-((Property)(player.getPlayer_case())).getRent());
                        else if(player.getPlayer_case() instanceof Property && !((Property)(player.getPlayer_case())).isItBought()){ //Dernier cas à faire : si la propriété n'est pas achetée, on propose au joueur de l'acheter
                            
                        }
                    
                    
                    }while(result_of_dice.get(0).equals(result_of_dice.get(1)) && count_double<3);
                    
                    if(count_double==3) player.inJail(board);
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
            System.out.println(player.getProperties().get(i));
        }
    }
    
    public static void move(int sum_of_dice, Player player){
        int arrival_case_number= (player.getPlayer_case().getCaseNumber()+sum_of_dice)%40;
        Case arrival_case = null;
        boolean start=false;
        for(i=0;i<board.size();i++){
            if(board.get(i).getCaseNumber()==arrival_case_number) arrival_case=board.get(i);
        }
        if(arrival_case_number<player.getPlayer_case().getCaseNumber()){
            player.setCapital(player.getCapital()+2000);
        }
        player.setPlayer_case(arrival_case);
    }
    public static void displayInventory(Player player){
        System.out.println("");
        System.out.println(player.getName());
        System.out.println("Vous avez "+player.getCapital()+" €");
        System.out.println("");
        System.out.println("Vous possèdez : ");
        for(i=0;i<player.getProperties().size();i++){
            if(player.getProperties().get(i) instanceof Avenue){ //Si la propriété est une avenue, on affiche le nombre de maisons/hôtel s'il y en a
                if( ((Avenue)(player.getProperties().get(i))).getHouse()>0) System.out.println(((Avenue)(player.getProperties().get(i))).getName()+" avec "+((Avenue)(player.getProperties().get(i))).getHouse()+" maison(s) dessus, ");
                else if(((Avenue)(player.getProperties().get(i))).getHotel()>0) System.out.println(((Avenue)(player.getProperties().get(i))).getName()+" avec "+((Avenue)(player.getProperties().get(i))).getHotel()+" hôtel dessus, ");
            }
            else if(player.getProperties().get(i) instanceof RailRoad){
                System.out.println(((RailRoad)(player.getProperties().get(i))).getName());
            }
            else if(player.getProperties().get(i) instanceof Company){
                System.out.println( ((Company)(player.getProperties().get(i))).getName() );
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
    }
    
    public static ArrayList <String> groupOfAvenues(Player player,ArrayList <Avenue> avenues){ //Va regarder si le joueur passé en paramètre possède un groupe complet d'avenues de même couleurs
        ArrayList <Avenue> all_board_avenues=new ArrayList();
        ArrayList <Avenue> group_board_avenues=new ArrayList();
        ArrayList <String> color_groups=new ArrayList();
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
        System.out.println("Pour sortir, faites votre choix : freecard si vous avec une carte de libération, pay si vous voulez payer (50€), ou roll si vous voulez tenter votre chance avec un double");
        switch(prison_choice_scanner.nextLine())                           // on lit la réponse du joueur
            {
                case "freecard":                

                    if(player.getFree_card() > 0)               // si le joueur posséde une carte, il sort de prison
                    {
                        player.setIsInJail(false);
                        player.setFree_card(player.getFree_card() - 1);     // on actualise le nombre de cartes
                        System.out.println("Il vous reste à présent " + player.getFree_card() + " cartes pour sortir de prison");
                    }
                    break;

                case "pay":

                    if(player.getCapital() >= 50)                           // si le joueur souhaite payer la caution
                    {
                        player.setIsInJail(false);
                        player.setCapital(player.getCapital() - 50);        // on actualise le capital
                        System.out.println("Vous disposez à présent de " + player.getCapital() + " euros");
                    }
                    break;

                case "roll":
                    if (player.rollsDice().get(0)==player.rollsDice().get(1) )    // si le joueur a fait un double
                    {
                        player.setIsInJail(false);
                        System.out.println("Félicitations ! Votre double vous permet de sortir de prison");
                    }
                    else
                    {
                        System.out.println("Dommage ! Vous restez en prison");
                        player.setIsInJail(true);
                    }
                    break;
            }
    }
}