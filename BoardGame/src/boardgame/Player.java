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
 * <br>
 * Le paramètre capital qui indique le capitale du joueur
 * <br>
 * Le paramètre playerCase qui indique la case sur laquelle se trouve le joueur
 * <br>
 * Le paramètre isInJail indiquant si le joueur est en prison
 * <br>
 * Le paramètre playerNumber qui indique le numéro du joueur
 * <br>
 * Le paramètre freeCard qui indique le nombre de cartes "libéré de prison" que possède un joueur
 * <br>
 * Le paramètre numberOfRailRoads qui correspond au nombre de gares que possède un joueur
 * <br>
 * Le paramètre numberOfCompanies qui correspond au nombre de compagnies que possède un joueur
 * <br>
 * Le paramètre inflated qui indique si le joueur a été attaqué avec une carte Inflation
 * <br>
 * Le paramètre scamed qui indique si le joueur a été attaqué avec une carte Robber
 * <br>
 * Le paramètre amountScamed qui précise le montant que le joueur a perdu suite à l'utilisation de la carte Robber
 * <br>
 * Le paramètre attackCard qui précise la carte attaque que possède le joueur
 * <br>
 * Le paramètre properties qui représente la liste de propriété que possède un joueur
 * <br>
 * Le paramètre avenues qui représente la liste d'avenues que possède un joueur
 * <br>
 * On retrouve dans cette classe les fonctions permettant de récupérer et modifier les précédents paramètres
 * <br>
 * On retrouve aussi la fonction permettant de construire maisons et hôtels ainsi que la fonction permettant de lancer les dés
 * <br>
 * De plus on retrouve la fonction permettant d'hypothéquer une propriété et de savoir si le joueur est en prison
 * <br>
 * Enfin une dernière fonction permet de classer les joueurs par leurs ordres de jeu
 * @author thibb
 */
public abstract class Player implements Comparable<Player> {
    
    private String name;
    private int capital=150000;
    private Case playerCase;
    private boolean isInJail=false;
    private int playerNumber;
    private int freeCard=0;
 
    private int numberOfRailRoads=0;
    private int numberOfCompanies=0;
    private boolean inflated=false;
    private boolean scamed=false;
    private int amountScamed=0;
    
    private Attack attackCard=null;
    private ArrayList <Property> properties=new ArrayList();
    private ArrayList <Avenue> avenues=new ArrayList();
    
    /**
     * @return  Cette méthode retourne le nom du joueur
     */
    public String getName() {
        return name;
    }
    
    
    /**
     * @return  Cette méthode retourne le capital du joueur
     */
    public int getCapital() {
        return capital;
    }
    
    /**
     * @return  Cette méthode indique si le joueur est en prison
     */
    public boolean isIsInJail() {
        return isInJail;
    }
    /**
     * @return  Cette méthode retourne le numéro du joueur
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * @return  Cette méthode retourne le nombre de carte "Libéré de prison"
     */
    public int getFreeCard() {
        return freeCard;
    }
    

    /**
     * @return  Cette méthode retourne le nombre de gare possédées par un joueur
     */
    public int getNumberOfRailRoads() {
        return numberOfRailRoads;
    }
    
    
    /**
     * @return  Cette méthode retourne le nombre de compagnie possédées par un joueur
     */
    public int getNumberOfCompanies() {
        return numberOfCompanies;
    }
    
    
    /**
     * @return  Cette méthode le nom de la carte attaque que possède le joueur
     */
    public Attack getAttackCard() {
        return attackCard;
    }
    
    
    /**
     * @return  Cette méthode retourne la liste de propriétés que possède un joueur
     */
    public ArrayList<Property> getProperties() {
        return properties;
    }
    
    
    /**
     * @return Cette méthode indique si le joueur a été attaqué par une carte attaque Inflation
     */
    public boolean isInflated() {
        return inflated;
    }
    
    
    /**
     * @return  Cette méthode retourne la case sur laquelle se trouve le joueur
     */
    public Case getPlayerCase() {
        return playerCase;
    }
    
    
     /**
     * @return  Cette méthode retourne la liste d'avenues que possède un joueur
     */
    public ArrayList<Avenue> getAvenues() {
        return avenues;
    }
    
    
   /**
     * @return Cette méthode indique si le joueur a été attaqué par une carte attaque Robber
     */
    public boolean isScamed() {
        return scamed;
    }
    
    
    /**
     * @return Cette méthode retourne la quantité d'argent perdu par un joueur suite à l'attaque par une carte attaque Robber
     */
    public int getAmountScamed() {
        return amountScamed;
    }
    
    
    /**
     * Cette méthode modifie le nom du joueur
     * @param name
     *      Le paramètre indique le nom du joueur
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
    /**
     * Cette méthode modifie le numéro du joueur
     * @param playerNumber
     *      Le paramètre indique le numéro du joueur
     */
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
    
    
    /**
     * Cette méthode modifie le capital du joueur
     * @param capital
     *      Le paramètre indique le capital du joueur
     */
    public void setCapital(int capital) {
        this.capital = capital;
    }
    
    
    /**
     * Cette méthode modifie la case sur laquelle se trouve le joueur
     * @param playerCase 
     *      Le paramètre indique la case sur laquelle se trouve le joueur
     */
    public void setPlayerCase(Case playerCase) {
        this.playerCase = playerCase;
    }
    
    
    /**
     * Cette méthode modifie l'état du joueur (en prison ou non)
     * @param isInJail 
     *      Le paramètre indique si le joueur est en prison
     */
    public void setIsInJail(boolean isInJail) {
        this.isInJail = isInJail;
    }

    /**
     * Cette méthode modifie le nombre de cartes "Libéré de prison" du joueur
     * @param freeCard 
     *      Le paramètre indique le nombre de cartes "Libéré de prison" du joueur
     */
    public void setFreeCard(int freeCard) {
        this.freeCard = freeCard;
    }

    /**
     * Cette méthode modifie le nombre de gare possédés par le joueur
     * @param numberOfRailRoads
     *      Le paramètre indique le nombre de gare possédées par le joueur
     */
    public void setNumberOfRailRoads(int numberOfRailRoads) {
        this.numberOfRailRoads = numberOfRailRoads;
    }
    
    
     /**
     * Cette méthode modifie le nombre de compagnie possédés par le joueur
     * @param numberOfCompanies
     *      Le paramètre indique le nombre de compagnie possédées par le joueur
     */
    public void setNumberOfCompanies(int numberOfCompanies) {
        this.numberOfCompanies = numberOfCompanies;
    }
    
    
    /**
     * Cette méthode modifie la carte attaque que possède le joueur
     * @param attackCard
     *      Le paramètre indique la carte attaque que possède le joueur
     */
    public void setAttackCard(Attack attackCard) {
        this.attackCard = attackCard;
    }
    
    
    /**
     * Cette méthode ajoute une propriété à un joueur
     * @param prop
     *      Le paramètre indique la propriété ajoutée pour le joueur
     */
    public void addProperty(Property prop){
        this.properties.add(prop);
    }
    
    /**
     * Cette méthode retire une propriété à un joueur
     * @param prop 
     *      Le paramètre indique la propriété retirée pour le joueur
     */
    public void removeProperty(Property prop){
        this.properties.remove(prop);
    }
    
    /**
     * Cette méthode ajoute une avenue à un joueur
     * @param avenue
     *      Le paramètre indique l'avenue ajoutée pour le joueur
     */
    public void addAvenue(Avenue avenue){
        this.avenues.add(avenue);
    }
    
    /**
     * Cette méthode retire une avenue à un joueur
     * @param avenue
     *      Le paramètre indique l'avenue retirée pour le joueur
     */
    public void removeAvenue(Avenue avenue){
        this.avenues.remove(avenue);
    }
    
    
    /**
     * Cette méthode modifie l'état du joueur (attaqué par la carte attaque Inflation ou non)
     * @param inflated 
     *      Le paramètre indique l'état du joueur (attaqué par la carte attaque Inflation ou non)
     */
    public void setInflated(boolean inflated) {
        this.inflated = inflated;
    }
    
     /**
     * Cette méthode modifie l'état du joueur (attaqué par la carte attaque Robber ou non)
     * @param scamed 
     *      Le paramètre indique l'état du joueur (attaqué par la carte attaque Robber ou non)
     */
    public void setScamed(boolean scamed) {
        this.scamed = scamed;
    }
    
    
    /**
     *  Cette méthode modifie le montant perdu par un joueur aprés avoir été attaqué par la carte attaque Robber
     * @param amountScamed
     *     Le paramètre indique le montant perdu par un joueur aprés avoir été attaqué par la carte attaque Robber 
     */
    public void setAmountScamed(int amountScamed) {
        this.amountScamed = amountScamed;
    }
    
    /**
     * Cette méthode implémente le lancer de dés
     * @return  Cette méthode retourne la somme des deux dés lancés
     */
    public ArrayList <Integer> rollsDice(){
        Random rand=new Random();
        ArrayList <Integer> dice= new ArrayList();
            dice.add(rand.nextInt(6)+1);
            dice.add(rand.nextInt(6)+1);
        
        return dice;
    }
    
    
    /**
     * Cette méthode permet de construire une maison
     * @param avenue
     *      Le paramètre correspond à l'avenue sur laquelle une maison est construite
     * @param groupAvenues
     *      Le paramètre correspond à l'ensemble des avenues possédés par le joueur et de même couleur
     */
    public void putHouse(Avenue avenue, ArrayList <Avenue> groupAvenues){
        boolean isPutHouse=true;
        if(avenue.getHouse()==4 || avenue.getHotel()==1){
            System.out.println("Vous ne pouvez pas mettre plus de 4 maisons sur votre propriété, ni mettre des maisons si vous avez un hôtel. Si vous n'avez pas d'hôtels, vous pouvez en poser un.");
            isPutHouse=false;
        }
        else{
            for(int i=0;i<groupAvenues.size();i++){
                if(groupAvenues.get(i).getColor().equals(avenue.getColor()) && (groupAvenues.get(i).getHouse()<avenue.getHouse() && groupAvenues.get(i).getHotel()==0 )) isPutHouse=false;
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
    
     /**
     * Cette méthode permet de construire un hôtel
     * @param avenue
     *      Le paramètre correspond à l'avenue sur laquelle un hôtel est construit
     * @param groupAvenues
     *      Le paramètre correspond à l'ensemble des avenues possédés par le joueur et de même couleur
     */
    public void putHotel(Avenue avenue, ArrayList <Avenue> groupAvenues ){
        boolean isPutHotel=true;
        if(avenue.getHotel()==1){
            System.out.println("Vous ne pouvez pas mettre plus d'un hôtel sur votre propriété");
            isPutHotel=false;
        }
        else{
            for(int i=0;i<groupAvenues.size();i++){
                if(groupAvenues.get(i).getColor().equals(avenue.getColor()) && groupAvenues.get(i).getHouse()!=4) isPutHotel=false;
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
    
    
    /**
     * Cette méthode permet de savoir si le joueur est en prison
     * @param board
     *      Le paramètre correspond au plateau de jeu
     */
    public void inJail(ArrayList <Case> board){
        isInJail=true;
        for(int i=0;i<board.size();i++){
            if(board.get(i).getName().equals("Prison")) playerCase=board.get(i);
        }
    }
    
    
    
    /**
     * Cette méthode permet de comparer les joueurs par rapport à leur ordre de jeu
     * @param player
     *      Le paramètre correspond à l'ensemble des joueurs
     * @return  Cette méthode retourne une liste des joueurs triée selon leur ordre de jeu
     */
    @Override //Pour comparer le numéro de 2 joueurs et pouvoir les trier dans l'ordre de jeu
    public int compareTo(Player player) {
        return this.getPlayerNumber()-player.getPlayerNumber();
    }
    
    /**
     * Cette méthode permet d'hypothéquer une propriété
     * @param player
     *      Le paramètre correspond à l'ensemble des joueurs
     * @param prop
     *      Le paramètre correspond à la propriété qui est hypothéquée
     */
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
