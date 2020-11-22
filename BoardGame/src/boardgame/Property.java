/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgame;

/**
 *
 * @author Antoine
 */

/**
 * La classe Property correspond aux différentes propriétés présentes sur le plateau soit gares, compagnies et avenues
 * <br>
 * <br>
 * Cette classe est caractérisée par les informations suivantes :
 * <br>
 * <br>
 * Le paramètre isBought qui indique si la propriété est achetée ou non
 * <br>
 * Le paramètre boughtPrice qui indique le prix d'achat d'une propriété
 * <br>
 * Le paramètre rent qui indique le loyer d'une propriété
 * <br>
 * Le paramètre associatedPlayer  indiquant le joueur associé à la propriété
 * <br>
 * Le paramètre mortgage qui correspond au montant de l'hypothèque d'une propriété
 * <br>
 * Le paramètre mortgaged qui indique si la propriété est hypothéquée ou non
 * <br>
 * On retrouve dans cette classe les fonctions permettant de récupérer et modifier les précédents paramètres
 * <br>
 * On retrouve aussi la fonction permettant d'ajuster le loyer des propriétés ainsi que les fonctions pour acheter et vendre des propriétés
 * @author thibb
 */

public abstract class Property extends Case {
    private boolean isBought;
    private int boughtPrice;
    private int rent;
    private Player associatedPlayer;
    private int mortgage;
    private boolean mortgaged=false;
    
    
    /**
     * @return  Cette méthode indique si la propriété est achetée
     */
    public boolean isItBought() {
        return isBought;
    }
    
    /**
     * @return Cette méthode retourne le prix d'achat d'une propriété
     */
    public int getBoughtPrice() {
        return boughtPrice;
    }
    
    /**
     * @return Cette méthode retourne le loyer d'une propriété
     */
    public int getRent() {
        return rent;
    }
    
    /**
     * @return Cette méthode retourne le montant de l'hypothèque d'une propriété
     */
    public int getMortgage() {                                                  // AJOUT DE GETTER ET SETTER POUR MORTGAGE
        return mortgage;
    }
    
    /**
     * @return  Cette méthode indique si la propriété est hypothéquée ou non
     */
    public boolean isMortgaged() {
        return mortgaged;
    }

    /**
     * @return  Cette méthode retourne le joueur associé à la propriété
     */
    public Player getAssociatedPlayer() {
        return associatedPlayer;
    }
    
    /**
     * Cette méthode modifie l'état de la propriété (achetée ou non)
     * @param isBought
     *      Le paramètre indique si la propriété est achetée ou non
     */
    public void setIsBought(boolean isBought) {
        this.isBought = isBought;
    }
    
    
    /**
     * Cette méthode modifie le prix d'achat d'une propriété
     * @param boughtPrice
     *      Le paramètre indique le prix d'achat d'une propriété
     */
    public void setBoughtPrice(int boughtPrice) {
        this.boughtPrice = boughtPrice;
    }
    
    
    /**
     * Cette méthode modifie le loyer d'une propriété
     * @param rent
     *      Le paramètre indique le loyer d'une propriété
     */
    public void setRent(int rent) {
        this.rent = rent;
    }
    
    
    /**
     * Cette méthode modifie le joueur associé à la propriété
     * @param associatedPlayer
     *      Le paramètre indique le joueur associé à la propriété
     */
    public void setAssociatedPlayer(Player associatedPlayer) {
        this.associatedPlayer = associatedPlayer;
    }
    
    /**
     * Cette méthode modifie le montant de l'hypothèque d'une propriété
     * @param mortgage
     *      Le paramètre correspond au montant de l'hypothèque d'une propriété
     */
    public void setMortgage(int mortgage) {
        this.mortgage = mortgage;
    }
    
    
    /**
     * Cette méthode modifie l'état de la propriété (hypothéquée ou non)
     * @param mortgaged
     *      Ce paramètre indique si la propriété est hypothéquée ou non
     */
    public void setMortgaged(boolean mortgaged) {
        this.mortgaged = mortgaged;
    }
    
    /**
     * Cette méthode permet d'acheter une propriété
     * @param player
     *      Le paramètre indique le joueur qui achète la propriété
     * @return  Cette méthode retourne 1 si l'achat s'est bien passé -1 sinon
     */
    public boolean buy(Player player){
        int price;
        if(player.isInflated()) price=2*this.getBoughtPrice();
        else price=this.getBoughtPrice();
        if(this.mortgaged) price+=this.mortgage;
        if(player.getCapital()-price>0){
            player.setCapital(player.getCapital()-price);
            if(this.mortgaged) {
                this.setMortgaged(false);
                this.associatedPlayer.removeAvenue((Avenue)this);
                this.associatedPlayer.removeProperty(this);
            }
            this.associatedPlayer=player;
            player.addProperty(this);
            player.setInflated(false);
            if(this instanceof Avenue){
                player.addAvenue((Avenue)(this));
                checkDoubleRent(player);
                
            }
            else if(this instanceof RailRoad){
                player.setNumberOfRailRoads(player.getNumberOfRailRoads()+1);
            }
            else if(this instanceof Company){
                player.setNumberOfCompanies(player.getNumberOfCompanies()+1);
            }
            this.computing(this, player);
            this.isBought=true;
            return true; //Si renvoie true, alors l'achat a été effectué correctement, si -false alors non
        }
        
        else {
            System.out.println("Vous n'avez pas assez d'argent pour acheter cette propriété");
            return false;
        }
    }
    
    /**
     * Cette méthode permet à un joueur de vendre une propriété à un autre joueur
     * @param seller
     *      Le paramètre indique le joueur qui vend la propriété
     * @param buyer
     *       Le paramètre indique le joueur qui achète la propriété
     * @return  Cette méthode retourne 1 si la vente s'est bien passé -1 sinon
     */
    public int sellToSomeone(Player seller, Player buyer){
        int price;
        if(this instanceof Avenue) price = ((Avenue) (this)).getBoughtPrice()/2 + ((Avenue) (this)).getPriceOfHouseAndHotels()* (int) ((Avenue) (this)).getSoldAvenueCoeff();
        
        else price=this.getBoughtPrice();
        
        if(buyer.getCapital()-price>0){//Pareil ici que pour buy
                buyer.setCapital(buyer.getCapital()-price);
                seller.setCapital(seller.getCapital()+price);
                this.associatedPlayer=buyer;
                seller.removeProperty(this);
                buyer.addProperty(this);
                
                if(this instanceof Avenue){
                    seller.removeAvenue((Avenue)(this));
                    buyer.addAvenue((Avenue)this);
                    checkDoubleRent(buyer);       
                    }
                else if(this instanceof RailRoad){
                    buyer.setNumberOfRailRoads(buyer.getNumberOfRailRoads()+1);
                    seller.setNumberOfRailRoads(seller.getNumberOfRailRoads()-1);
                }
                else if(this instanceof Company){
                    buyer.setNumberOfCompanies(buyer.getNumberOfCompanies()+1);
                    seller.setNumberOfCompanies(seller.getNumberOfCompanies()-1);
                }
                this.computing(this, buyer);
                return 1; 
        }
        else{
            System.out.println("Vous n'avez pas assez d'argent pour acheter cette propriété");
            return -1;
        }
        
        
        
    }
   
    /**
     * Cette méthode permet d'ajuster les loyers des propriétés en appelant cette méthode dans les classes filles de property
     * @param prop
     *      Le paramètre correspond à la propriété étudiée
     * @param player
     *      Le paramètre correspond au joueur qui possède la propriété
     * @return  Cette méthode retourne les loyers modifiés des différentes propriétés
     */
    public abstract int computing(Property prop,Player player);
    
    
    /**
     * Cette méthode permet d'attribuer un bonus de loyer pour chaque pion
     * @param player
     *      Le paramètre correspond au pion du joueur
     */
    public void checkDoubleRent(Player player){
        if(player instanceof Car) ((Car)(player)).doubleRent((Avenue)this);
        else if(player instanceof Cannon) ((Cannon)(player)).doubleRent((Avenue)this);
        else if(player instanceof Hat) ((Hat)(player)).doubleRent((Avenue)this);
    }
}
