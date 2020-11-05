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
    static Scanner scanner = new Scanner(System.in);
    static Scanner prison_choice = new Scanner(System.in);
    
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
        for(int i=0;i<numberOfPlayers;i++){
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
        for (int i=0;i<numberOfPlayers;i++){
            attacks.get(i).setAssociatedPlayer(players.get(i));
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
        cov.setInAction(true);*/
        
        for(int i=0;i<numberOfPlayers;i++){
            displayInventory(players.get(i),board);
        }
        //Tant que la partie n'est pas terminée (tant qu'il reste plus d'un joueur en jeu
        while(players_in_game.size()>1){
            numberOfTurns++;
            for(int i=0;i<players_in_game.size();i++){ //On fait le tour des joueurs encore en jeu
                if(players_in_game.get(i).isIsInJail()) getOutOfJail(players_in_game.get(i));//On teste d'abord si le joueur est en prison
                else{ //S'il ne l'est pas, il joue son tour normalement
                    displayInventory(players_in_game.get(i),board);
                    //choice();
                }
            }
            
            
            
        }
        
        
        
    
        
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
        
        for(int i=0;i<numberOfPlayers;i++){
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
        board.add(new Avenue(60,2,"Boulevard de Belleville",1,"Marron"));
        board.add(new Bonus("Caisse de communauté",2));
        board.add(new Avenue(60,4,"Rue Lecourbe",3,"Marron"));
        board.add(new Taxes("Impôt sur le revenu",4,200));
        board.add(new RailRoad(200,25,"Gare Montparnasse",5));
        board.add(new Avenue(100,6,"Rue Vaugirard",6,"Ciel"));
        board.add(new Bonus("Chance",7));
        board.add(new Avenue(100,6,"Rue de Courcelles",8,"Ciel"));
        board.add(new Avenue(120,8,"Avenue de la République",9,"Ciel"));
        board.add(new Prison("Prison",10));
        board.add(new Avenue(140,10,"Boulevard de la Villette",11,"Rose"));
        board.add(new Company(150,"Compagnie de distribution d'électricité",12));
        board.add(new Avenue(140,10,"Avenue de Neuilly",13,"Rose"));
        board.add(new Avenue(160,12,"Rue de Paradis",14,"Rose"));
        board.add(new RailRoad(200,25,"Gare de Lyon",15));
        board.add(new Avenue(180,14,"Avenue Mozart",16,"Orange"));
        board.add(new Bonus("Caisse de communauté",17));
        board.add(new Avenue(180,14,"Boulevard Saint-Michel",18,"Orange"));
        board.add(new Avenue(200,16,"Place Pigalle",19,"Orange"));
        board.add(new Case("Parc gratuit",20));
        board.add(new Avenue(220,18,"Avenue Matignon",21,"Rouge"));
        board.add(new Bonus("Chance",22));
        board.add(new Avenue(220,18,"Boulevard Malesherbes",23,"Rouge"));
        board.add(new Avenue(240,20,"Avenue Henri-Martin",24,"Rouge"));
        board.add(new RailRoad(200,25,"Gare du Nord",25));
        board.add(new Avenue(260,22,"Faubourg Saint-Honoré",26,"Jaune"));
        board.add(new Avenue(260,22,"Place de la bourse",27,"Jaune"));
        board.add(new Company(150,"Compagnie de distribution des eaux",28));
        board.add(new Avenue(280,24,"Rue La Fayette",29,"Jaune"));
        board.add(new Case("Allez en prison",30));
        board.add(new Avenue(300,26,"Avenue de Breuteuil",31,"Vert"));
        board.add(new Avenue(300,26,"Avenue Foch",32,"Vert"));
        board.add(new Bonus("Caisse de communauté",33));
        board.add(new Avenue(320,28,"Boulevard des Capucines",34,"Vert"));
        board.add(new RailRoad(200,25,"Gare Saint-Lazare",35));
        board.add(new Bonus("Chance",36));
        board.add(new Avenue(350,35,"Avenue des Champs-Elysées",37,"Bleu"));
        board.add(new Taxes("Taxe de luxe",38,100));
        board.add(new Avenue(400,50,"Rue de la Paix",39,"Bleu"));
        System.out.println(board.get(1).getClass().getSuperclass().getSimpleName());
    }

    public static void choice(){
        
        
        
    }
    
    
    
    
    public static void displayInventory(Player player, ArrayList <Case> board){
        Property prop=null;
        Avenue av=null;
        System.out.println("");
        System.out.println(player.getName());
        System.out.println("Vous avez "+player.getCapital()+" €");
        System.out.println("");
        System.out.println("Vous possèdez : ");
        for(int i=0;i<board.size();i++){
            if(board.get(i) instanceof Property){ 
                prop=(Property) (board.get(i));
                if(prop.getAssociatedPlayer()!= null && prop.getAssociatedPlayer().equals(player)){ //Si la propriété appartient au joueur actuel, on affiche le nom de sa propriété
                    if(board.get(i) instanceof Avenue){ //Si la propriété est une avenue, on affiche le nombre de maisons/hôtel s'il y en a
                        av=(Avenue) (board.get(i));
                        if(av.getHouse()>0) System.out.println(av.getName()+" avec "+av.getHouse()+" maison(s) dessus, ");
                        else if(av.getHotel()>0) System.out.println(av.getName()+" avec "+av.getHotel()+" hôtel dessus, ");
                    }
                }
            } //Si la case est une propriété, on caste la case pour pouvoir utiliser les méthodes propres à la classe Property
            
        }
        for(int i=0;i<numberOfPlayers;i++){ //Car nombre de joueurs=nombre de cartes attaque pour l'instant
            if(attacks.get(i).getAssociatedPlayer().equals(player) && !attacks.get(i).isItUsed()){
                System.out.println("");
                System.out.println("Vous pouvez utiliser votre carte attaque "+attacks.get(i).getName());
                System.out.println("En utilisant cette carte, "+attacks.get(i).getEffect());
            }
        }
        System.out.println("");
        if(covid.isInAction()){
            System.out.println("Le covid circule actuellement");//Ajouter affichage du niveau d'alerte
        }
        if(strike.isInAction()){
            System.out.println("Il y a actuellement une grève des trains");
        }
        
        
}
    
    public static void getOutOfJail(Player player)
    {
        System.out.println("Pour sortir, faites votre choix : freecard si vous avec une carte de libération, pay si vous voulez payer (50€), ou roll si vous voulez tenter votre chance avec un double");
        switch(prison_choice.nextLine())                           // on lit la réponse du joueur
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