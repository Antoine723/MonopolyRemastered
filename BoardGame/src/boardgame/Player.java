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
    private int free_card=0;
    private int numberOfAvenues=0;
    private int numberOfRailRoads=0;
    private int numberOfCompanies=0;
    private boolean inflated=false;
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

    public void setInflated(boolean inflated) {
        this.inflated = inflated;
    }
    
    
    public ArrayList <Integer> rollsDice(){
        Random rand=new Random();
        ArrayList <Integer> dice= new ArrayList(){{
            add(rand.nextInt(6)+1);
            add(rand.nextInt(6)+1);
        }};
        
        return dice;
    }
    
    public void putHouse(Avenue avenue,int numberOfHouses){
        avenue.setHouse(numberOfHouses);
        updateRentOfAvenue(avenue);
        
        
    }
    public void putHotel(Avenue avenue){
        avenue.setHotel(1);
        updateRentOfAvenue(avenue);
        
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
    
    
    public void updateRentOfAvenue(Avenue avenue)                 // AJOUT D'UNE FONCTION QUI DETERMINE LOYER APRES LA PAUSE D'UN HOTEL OU MAISON                                           
    {
        float houseCoefficient;
        float hotelCoefficient;
        
        switch(avenue.getCaseNumber())
        {
            case 1:
                if(avenue.getHouse() > 0)
                {
                    houseCoefficient =1.2F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHouse() *houseCoefficient));
                }
                else if(avenue.getHotel() > 0)
                {
                    hotelCoefficient = 2.0F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHotel() *hotelCoefficient));
                }
                break;
            
            case 3:
                if(avenue.getHouse() > 0)
                {
                    houseCoefficient =1.2F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHouse() *houseCoefficient));
                }
                else if(avenue.getHotel() > 0)
                {
                    hotelCoefficient = 2.0F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHotel() *hotelCoefficient));
                }
                break;
            
            case 6:
                if(avenue.getHouse() > 0)
                {
                    houseCoefficient =1.3F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHouse() *houseCoefficient));
                }
                else if(avenue.getHotel() > 0)
                {
                    hotelCoefficient = 2.1F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHotel() *hotelCoefficient));
                }
                break;
                
            case 8:
                if(avenue.getHouse() > 0)
                {
                    houseCoefficient =1.4F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHouse() *houseCoefficient));
                }
                else if(avenue.getHotel() > 0)
                {
                    hotelCoefficient = 2.2F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHotel() *hotelCoefficient));
                }
                break;
                
            case 9:
                if(avenue.getHouse() > 0)
                {
                    houseCoefficient =1.5F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHouse() *houseCoefficient));
                }
                else if(avenue.getHotel() > 0)
                {
                    hotelCoefficient = 2.3F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHotel() *hotelCoefficient));
                }
                break;
                
            case 11:
                if(avenue.getHouse() > 0)
                {
                    houseCoefficient =1.6F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHouse() *houseCoefficient));
                }
                else if(avenue.getHotel() > 0)
                {
                    hotelCoefficient = 2.4F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHotel() *hotelCoefficient));
                }
                break;
                
            case 13:
                if(avenue.getHouse() > 0)
                {
                    houseCoefficient =1.7F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHouse() *houseCoefficient));
                }
                else if(avenue.getHotel() > 0)
                {
                    hotelCoefficient = 2.5F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHotel() *hotelCoefficient));
                }
                break;
                
            case 14:
                if(avenue.getHouse() > 0)
                {
                    houseCoefficient =1.8F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHouse() *houseCoefficient));
                }
                else if(avenue.getHotel() > 0)
                {
                    hotelCoefficient = 2.6F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHotel() *hotelCoefficient));
                }
                break;
                
            case 16:
                if(avenue.getHouse() > 0)
                {
                    houseCoefficient =1.9F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHouse() *houseCoefficient));
                }
                else if(avenue.getHotel() > 0)
                {
                    hotelCoefficient = 2.7F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHotel() *hotelCoefficient));
                }
                break;
                
            case 18:
                if(avenue.getHouse() > 0)
                {
                    houseCoefficient = 2.0F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHouse() *houseCoefficient));
                }
                else if(avenue.getHotel() > 0)
                {
                    hotelCoefficient = 2.8F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHotel() *hotelCoefficient));
                }
                break;
                
            case 19:
                if(avenue.getHouse() > 0)
                {
                    houseCoefficient = 2.1F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHouse() *houseCoefficient));
                }
                else if(avenue.getHotel() > 0)
                {
                    hotelCoefficient = 2.9F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHotel() *hotelCoefficient));
                }
                break;
                
            case 21:
                if(avenue.getHouse() > 0)
                {
                    houseCoefficient = 2.2F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHouse() *houseCoefficient));
                }
                else if(avenue.getHotel() > 0)
                {
                    hotelCoefficient = 3.0F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHotel() *hotelCoefficient));
                }
                break;
                
            case 23:
                if(avenue.getHouse() > 0)
                {
                    houseCoefficient = 2.3F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHouse() *houseCoefficient));
                }
                else if(avenue.getHotel() > 0)
                {
                    hotelCoefficient = 3.1F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHotel() *hotelCoefficient));
                }
                break;
                
            case 24:
                if(avenue.getHouse() > 0)
                {
                    houseCoefficient = 2.4F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHouse() *houseCoefficient));
                }
                else if(avenue.getHotel() > 0)
                {
                    hotelCoefficient = 3.2F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHotel() *hotelCoefficient));
                }
                break;
                
            case 26:
                if(avenue.getHouse() > 0)
                {
                    houseCoefficient = 2.5F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHouse() *houseCoefficient));
                }
                else if(avenue.getHotel() > 0)
                {
                    hotelCoefficient = 3.3F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHotel() *hotelCoefficient));
                }
                break;
                
            case 27:
                if(avenue.getHouse() > 0)
                {
                    houseCoefficient = 2.6F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHouse() *houseCoefficient));
                }
                else if(avenue.getHotel() > 0)
                {
                    hotelCoefficient = 3.4F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHotel() *hotelCoefficient));
                }
                break;
                
            case 29:
                if(avenue.getHouse() > 0)
                {
                    houseCoefficient = 2.7F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHouse() *houseCoefficient));
                }
                else if(avenue.getHotel() > 0)
                {
                    hotelCoefficient = 3.5F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHotel() *hotelCoefficient));
                }
                break;
                
            case 31:
                if(avenue.getHouse() > 0)
                {
                    houseCoefficient = 2.8F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHouse() *houseCoefficient));
                }
                else if(avenue.getHotel() > 0)
                {
                    hotelCoefficient = 3.6F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHotel() *hotelCoefficient));
                }
                break;
                
            case 32:
                if(avenue.getHouse() > 0)
                {
                    houseCoefficient = 2.9F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHouse() *houseCoefficient));
                }
                else if(avenue.getHotel() > 0)
                {
                    hotelCoefficient = 3.7F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHotel() *hotelCoefficient));
                }
                break;
                
            case 34:
                if(avenue.getHouse() > 0)
                {
                    houseCoefficient = 3.0F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHouse() *houseCoefficient));
                }
                else if(avenue.getHotel() > 0)
                {
                    hotelCoefficient = 3.8F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHotel() *hotelCoefficient));
                }
                break;
                
            case 37:
                if(avenue.getHouse() > 0)
                {
                    houseCoefficient = 3.1F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHouse() *houseCoefficient));
                }
                else if(avenue.getHotel() > 0)
                {
                    hotelCoefficient = 3.9F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHotel() *hotelCoefficient));
                }
                break;
                
            case 39:
                if(avenue.getHouse() > 0)
                {
                    houseCoefficient = 3.2F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHouse() *houseCoefficient));
                }
                else if(avenue.getHotel() > 0)
                {
                    hotelCoefficient = 4.0F;
                    avenue.setRent((int) (avenue.getBoughtPrice() * avenue.getHotel() *hotelCoefficient));
                }
                break;
        }
    }  
}
