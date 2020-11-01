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
public class Property extends Case {
    private boolean isBought;
    private int boughtPrice;
    private int rent;
    private String associatedPlayer;
    
    public Property(String name, int caseNumber){
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

    public String getAssociatedPlayer() {
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

    public void setAssociatedPlayer(String associatedPlayer) {
        this.associatedPlayer = associatedPlayer;
    }
    
    public int computeRent(){
        return rent;
    }
    
    public int buy(Player player,int price){
        if(player.getCapital()-price>0){ //A voir si on met la condition ici ou dans le jeu
            player.setCapital(player.getCapital()-price);
            this.associatedPlayer=player.getName();
            player.setNumberOfProperty(player.getNumberOfProperty()+1);
            this.isBought=true;
            return 1; //Si renvoie 1, alors l'achat a été effectué correctement, si -1, alors non
        } 
        else {
            System.out.println("Vous n'avez pas assez d'argent pour acheter cette propriété");
            return -1;
        }
    }
    public int sellToSomeone(Player seller, Player buyer, int price){
        if(buyer.getCapital()-price>0){//Pareil ici que pour buy
            buyer.setCapital(buyer.getCapital()-price);
            seller.setCapital(seller.getCapital()+price);
            this.associatedPlayer=buyer.getName();
            /*seller.setNumberOfProperty(seller.getNumberOfProperty()-1);
            buyer.setNumberOfProperty(buyer.getNumberOfProperty()+1);*/
            return 1;
        }
        else{
            System.out.println("Vous n'avez pas assez d'argent pour acheter cette propriété");
            return -1;
        } 
        
    }
    public void sell(Player seller, int price){ //Fonction vendre à la banque
        seller.setCapital(seller.getCapital()+price);
        //seller.setNumberOfProperty(seller.getNumberOfProperty()-1);
        this.associatedPlayer=null;
        this.isBought=false;
        
    }
}
