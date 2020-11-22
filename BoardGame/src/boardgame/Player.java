/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgame;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Antoine
 */

/**
 * La classe Player correspond aux différents joueurs d'une partie
 * <br>
 * <br>
 * Cette classe est caractérisée par les informations suivantes :
 * <br>
 * <br>
 * Le paramètre name qui indique le nom du joueur
 * Le paramètre capital qui indique le capitale du joueur
 * Le paramètre playerCase qui indique la case sur laquelle se trouve le joueur
 * Le paramètre isInJail indiquant si le joueur est en prison
 * Le paramètre playerNumber qui indique le numéro du joueur
 * Le paramètre freeCard qui indique si le nombre de cartes "libéré de prison" que possède un joueur
 * Le paramètre numberOfRailRoads qui correspond au nombre de gare que possède un joueur
 * Le paramètre numberOfCompanies qui correspond au nombre de compagnie que possède un joueur
 * Le paramètre inflated qui indique si le joueur a été attaqué avec une carte Inflation
 * Le paramètre scamed qui indique si le joueur a été attaqué avec une carte Robber
 * Le paramètre amountScamed qui précise le montant que le joueur a perdu suite à l'utilisation de la carte Robber
 * Le paramètre attackCard qui précise la carte attaque que possède le joueur
 * Le paramètre properties qui représente la liste de propriété que possède un joueur
 * Le paramètre avenues qui représente la liste d'avenues que possède un joueur
 * On retrouve dans cette classe les fonctions permettant de récupérer et modifier les précédents paramètres
 * On retrouve aussi la fonction permettant d'ajuster le loyer des propriétés ainsi que les fonctions pour acheter et vendre des propriétés
 * @author thibb
 * @author thibb
 */
public abstract class Player implements Comparable<Player> {
    
    private String name;
    private int capital=60000;
    private Case player_case;
    private boolean isInJail=false;
    private int playerNumber;
    private int free_card=0;
 
    private int numberOfRailRoads=0;
    private int numberOfCompanies=0;
    private boolean inflated=false;
    private boolean scamed=false;
    private int amountScamed=0;
    
    private Attack attack_card=null;
    private ArrayList <Property> properties=new ArrayList();
    private ArrayList <Avenue> avenues=new ArrayList();
    
    public String getName() {
        return name;
    }

    public int getCapital() {
        return capital;
    }

    public boolean isIsInJail() {
        return isInJail;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getFree_card() {
        return free_card;
    }
    


    public int getNumberOfRailRoads() {
        return numberOfRailRoads;
    }

    public int getNumberOfCompanies() {
        return numberOfCompanies;
    }

    public Attack getAttack_card() {
        return attack_card;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public boolean isInflated() {
        return inflated;
    }

    public Case getPlayer_case() {
        return player_case;
    }

    public ArrayList<Avenue> getAvenues() {
        return avenues;
    }

    public boolean isScamed() {
        return scamed;
    }

    public int getAmountScamed() {
        return amountScamed;
    }
    
    
    
    
    public void setName(String name) {
        this.name = name;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public void setPlayer_case(Case player_case) {
        this.player_case = player_case;
    }

    public void setIsInJail(boolean isInJail) {
        this.isInJail = isInJail;
    }


    public void setFree_card(int free_card) {
        this.free_card = free_card;
    }


    public void setNumberOfRailRoads(int numberOfRailRoads) {
        this.numberOfRailRoads = numberOfRailRoads;
    }

    public void setNumberOfCompanies(int numberOfCompanies) {
        this.numberOfCompanies = numberOfCompanies;
    }

    public void setAttack_card(Attack attack_card) {
        this.attack_card = attack_card;
    }

    public void addProperty(Property prop){
        this.properties.add(prop);
    }
    public void removeProperty(Property prop){
        this.properties.remove(prop);
    }
    
    public void addAvenue(Avenue avenue){
        this.avenues.add(avenue);
    }
    public void removeAvenue(Avenue avenue){
        this.avenues.remove(avenue);
    }

    public void setInflated(boolean inflated) {
        this.inflated = inflated;
    }

    public void setScamed(boolean scamed) {
        this.scamed = scamed;
    }

    public void setAmountScamed(int amountScamed) {
        this.amountScamed = amountScamed;
    }
    
    
    public ArrayList <Integer> rollsDice(){
        Random rand=new Random();
        ArrayList <Integer> dice= new ArrayList();
            dice.add(rand.nextInt(6)+1);
            dice.add(rand.nextInt(6)+1);
        
        return dice;
    }
    
    public void putHouse(Avenue avenue, ArrayList <Avenue> group_avenues){
        boolean isPutHouse=true;
        if(avenue.getHouse()==4){
            System.out.println("Vous ne pouvez pas mettre plus de 4 maisons sur votre propriété. Cependant, vous pouvez mettre un hôtel");
            isPutHouse=false;
        }
        else{
            for(int i=0;i<group_avenues.size();i++){
                if(group_avenues.get(i).getColor().equals(avenue.getColor()) && (group_avenues.get(i).getHouse()<avenue.getHouse() && group_avenues.get(i).getHotel()==0 )) isPutHouse=false;
            }
            if(!isPutHouse) System.out.println("Vous devez avoir le même nombre de maisons sur chacune des avenues du même groupe pour pouvoir ajouter une maison sur l'une d'elle");
            else {
                avenue.setHouse(avenue.getHouse()+1);
                avenue.computing(avenue, this);
                this.capital=this.capital-avenue.getPriceOfHouseAndHotels();
                System.out.println("Vous avez posé 1 maison sur "+avenue.getName()+" pour la somme de "+avenue.getPriceOfHouseAndHotels()+" Francs");
            }   
        }
        

    }
    public void putHotel(Avenue avenue, ArrayList <Avenue> group_avenues ){
        boolean isPutHotel=true;
        if(avenue.getHotel()==1){
            System.out.println("Vous ne pouvez pas mettre plus d'un hôtel sur votre propriété");
            isPutHotel=false;
        }
        else{
            for(int i=0;i<group_avenues.size();i++){
                if(group_avenues.get(i).getColor().equals(avenue.getColor()) && group_avenues.get(i).getHouse()!=4) isPutHotel=false;
            }
            if(!isPutHotel) System.out.println("Vous devez avoir 4 maisons sur toutes les avenues du groupe pour pouvoir mettre un hôtel sur l'une d'elles");
            else {
                avenue.setHotel(1);
                avenue.computing(avenue, this);
                this.capital=this.capital-avenue.getPriceOfHouseAndHotels();
                System.out.println("Vous avez posé 1 hôtel sur "+avenue.getName()+" pour la somme de "+avenue.getPriceOfHouseAndHotels()+" Francs");
            }
        }
    }
    
    public void inJail(ArrayList <Case> board){
        isInJail=true;
        for(int i=0;i<board.size();i++){
            if(board.get(i).getName().equals("Prison")) player_case=board.get(i);
        }
    }
    
    
    
    
    @Override //Pour comparer le numéro de 2 joueurs et pouvoir les trier dans l'ordre de jeu
    public int compareTo(Player player) {
        return this.getPlayerNumber()-player.getPlayerNumber();
    }
    
    
    public void putOnMortgage(Player player, Property prop)                     // mortgage = hypothèque
    {
        if (prop instanceof Avenue)                                             // si c'est une avenue on supprime maisons et hôtels
        {
            ((Avenue) prop).setHotel(0);
            ((Avenue) prop).setHouse(0);
            player.setCapital(player.getCapital() + (prop.getMortgage()));
            prop.setRent(0);
        }
        else
        {
            player.setCapital(player.getCapital() + prop.getMortgage());
            prop.setRent(0);
        }
        prop.setMortgaged(true);
    }
}
