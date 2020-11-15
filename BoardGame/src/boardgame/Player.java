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
public abstract class Player implements Comparable<Player> {
    
    private String name;
    private int capital=1000;
    private int numberCase=0;
    private boolean isInJail=false;
    private int playerNumber;
    private int numberOfAvenues=0;
    private int free_card=0;
    private int numberOfProperty=0;
    private int numberOfRailRoads=0;
    private int numberOfCompanies=0;
    private Attack attack_card=null;
    private ArrayList <Property> properties=new ArrayList();
    
    public String getName() {
        return name;
    }

    public int getCapital() {
        return capital;
    }

    public int getNumberCase() {
        return numberCase;
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
    
    public int getNumberOfAvenues() {
        return numberOfAvenues;
    }

    public int getNumberOfProperty() {
        return numberOfProperty;
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
    
    
    
    
    public void setName(String name) {
        this.name = name;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public void setNumberCase(int numberCase) {
        this.numberCase = numberCase;
    }

    public void setIsInJail(boolean isInJail) {
        this.isInJail = isInJail;
    }

    public void setNumberOfAvenues(int numberOfAvenues) {
        this.numberOfAvenues = numberOfAvenues;
    }

    public void setFree_card(int free_card) {
        this.free_card = free_card;
    }

    public void setNumberOfProperty(int numberOfProperty) {
        this.numberOfProperty = numberOfProperty;
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
    
    
    public ArrayList <Integer> rollsDice(){
        Random rand=new Random();
        ArrayList <Integer> dice= new ArrayList(){{
            add(rand.nextInt(6)+1);
            add(rand.nextInt(6)+1);
        }};
        
        return dice;
    }
    
    public void putHouse(Avenue avenue,int numberOfHouses,int price){
        avenue.setHouse(numberOfHouses);
        this.capital=this.capital-price;
        
    }
    public void putHotel(Avenue avenue,int price){
        avenue.setHotel(1);
        this.capital=this.capital-price;
        
    }
    
    public void inJail(){
        this.numberCase=10; //Case prison
        this.isInJail=true;
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
        }
        else
        {
            player.setCapital(player.getCapital() + prop.getMortgage());
        }
    }
}
