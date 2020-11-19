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
public class Avenue extends Property{
    
    private int house=0;
    private int hotel=0;
    private ColorAvenue color;
    private int priceOfHouseAndHotels = 0;
    private float soldAvenueCoeff = 0 ;
    private int mortgage = 0;
    //Coefficient multiplicateur nombre de maison/hotel avec loyer
    public Avenue(int price,int rent, String name, int caseNumber, ColorAvenue color,int mortgage){      // AJOUT DE MORTGAGE
        super(name,caseNumber,mortgage);
        this.setBoughtPrice(price);
        this.setRent(rent);
        this.color=color;
    }

    public int getHouse() {
        return house;
    }

    public int getHotel() {
        return hotel;
    }

    public int getPriceOfHouseAndHotels() {
        return priceOfHouseAndHotels;
    }

    public ColorAvenue getColor() {
        return color;
    }

    public void setPriceOfHouseAndHotels(int priceOfHouseAndHotels) {
        this.priceOfHouseAndHotels = priceOfHouseAndHotels;
    }

    public float getSoldAvenueCoeff() {
        return soldAvenueCoeff;
    }

    public void setSoldAvenueCoeff(float soldAvenueCoeff) {
        this.soldAvenueCoeff = soldAvenueCoeff;
    }
    
    public void setHouse(int house) {
        this.house = house;
    }

    public void setHotel(int hotel) {
        this.hotel = hotel;
    }
    
   
    public void updatePriceOfHousesAndHotels(Avenue avenue)
    {
        switch(avenue.color)
        {
            case VIOLET:
                avenue.setPriceOfHouseAndHotels(5000);
                avenue.setSoldAvenueCoeff(0.2F);                                 // selon la couleur de la case on attribue le prix des hôtels et maisons
                
                break;                                                           // on attribue aussi un coefficient pour la carte attaque GiveAway
                
            case CIEL:
                avenue.setPriceOfHouseAndHotels(5000);
                avenue.setSoldAvenueCoeff(0.2F);  
                break;
                
            case ROSE:
                avenue.setPriceOfHouseAndHotels(10000);
                avenue.setSoldAvenueCoeff(0.4F);  
                break; 
               
            case ORANGE:
                avenue.setPriceOfHouseAndHotels(10000);
                avenue.setSoldAvenueCoeff(0.4F);  
                break;
               
            case ROUGE:
                avenue.setPriceOfHouseAndHotels(15000);
                avenue.setSoldAvenueCoeff(0.6F);  
                break;
                
            case JAUNE:
                avenue.setPriceOfHouseAndHotels(15000);
                avenue.setSoldAvenueCoeff(0.6F);  
                break;
            case VERT:
                avenue.setPriceOfHouseAndHotels(20000);
                avenue.setSoldAvenueCoeff(0.8F);
                avenue.setMortgage(150);
                break;
                
            case BLEU:
            avenue.setPriceOfHouseAndHotels(20000);
            avenue.setSoldAvenueCoeff(0.8F);
            avenue.setMortgage(250);
            break;

        }
    }   
    
    @Override
    public int computing(Property prop,Player player)
    {
        return prop.getRent();
    }

}
