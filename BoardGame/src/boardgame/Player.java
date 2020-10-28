/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgame;

import java.util.Random;

/**
 *
 * @author Antoine
 */
public abstract class Player implements Comparable<Player> {
    
    private String name;
    private int capital=1000;
    private int numberCase=1;
    private boolean isInJail=false;
    private int playerNumber;
    private int numberOfAvenues=0;
    
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

    public int getNumberOfAvenues() {
        return numberOfAvenues;
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
    
    public int rollsDice(){
        Random rand=new Random();
        return rand.nextInt(11)+2;
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
        this.numberCase=11; //Case prison
    }
    
    
    
    
    @Override //Pour comparer le numéro de 2 joueurs et pouvoir les trier dans l'ordre de jeu
    public int compareTo(Player player) {
        return this.getPlayerNumber()-player.getPlayerNumber();
    }
    
    
    
}
