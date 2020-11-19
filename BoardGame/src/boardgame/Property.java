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
public abstract class Property extends Case {
    private boolean isBought;
    private int boughtPrice;
    private int rent;
    private Player associatedPlayer;
    private int mortgage;
    
    public Property(String name, int caseNumber,int mortgage){                  // AJOUT DE MORTGAGE
        this.setName(name);
        this.setCaseNumber(caseNumber);
        
    }
            
    public boolean isItBought() {
        return isBought;
    }

    public int getBoughtPrice() {
        return boughtPrice;
    }

    public int getRent() {
        return rent;
    }

    public int getMortgage() {                                                  // AJOUT DE GETTER ET SETTER POUR MORTGAGE
        return mortgage;
    }

    public void setMortgage(int mortgage) {
        this.mortgage = mortgage;
    }

    public Player getAssociatedPlayer() {
        return associatedPlayer;
    }

    public void setIsBought(boolean isBought) {
        this.isBought = isBought;
    }

    public void setBoughtPrice(int boughtPrice) {
        this.boughtPrice = boughtPrice;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public void setAssociatedPlayer(Player associatedPlayer) {
        this.associatedPlayer = associatedPlayer;
    }
    
    
    public int buy(Player player){
        int price;
        if(player.isInflated()) price=2*this.getBoughtPrice();
        else price=this.getBoughtPrice();
        
        
        if(player.getCapital()-price>0){
            player.setCapital(player.getCapital()-price);
            this.associatedPlayer=player;
            player.addProperty(this);
            player.setInflated(false);
            if(this instanceof Avenue){
                player.addAvenue((Avenue)(this));
                checkDoubleRent(player);
                
            }
            this.isBought=true;
            return 1; //Si renvoie 1, alors l'achat a été effectué correctement, si -1, alors non
        }
        
        else {
            System.out.println("Vous n'avez pas assez d'argent pour acheter cette propriété");
            return -1;
        }
    }
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
                return 1; 
                }
        else{
            System.out.println("Vous n'avez pas assez d'argent pour acheter cette propriété");
            return -1;
        }
        
        
        
    }
    public void sell(Player seller){ //Fonction vendre à la banque
        if(this instanceof Avenue){
            seller.setCapital(seller.getCapital()+((Avenue) (this)).getBoughtPrice()/2 + ((Avenue) (this)).getPriceOfHouseAndHotels()* (int) ((Avenue) (this)).getSoldAvenueCoeff());
            seller.removeAvenue((Avenue)this);
            checkDoubleRent(seller);
        }
        else{
            seller.setCapital(seller.getCapital()+this.getBoughtPrice());
        }
        seller.removeProperty(this);
        this.associatedPlayer=null;
        this.isBought=false;
        
    }
    
    public int computing(Property prop,Player player)                           
    {
        return prop.getRent();
    }
        
    public void checkDoubleRent(Player player){
        if(player instanceof Car) ((Car)(player)).doubleRent((Avenue)this);
        else if(player instanceof Cannon) ((Cannon)(player)).doubleRent((Avenue)this);
        else if(player instanceof Hat) ((Hat)(player)).doubleRent((Avenue)this);
    }
}
