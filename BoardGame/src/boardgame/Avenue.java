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
    private String color;
    //Coefficient multiplicateur nombre de maison/hotel avec loyer
    public Avenue(int price,int rent, String name, int caseNumber, String color){
        super(name,caseNumber);
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

    public String getColor() {
        return color;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public void setHotel(int hotel) {
        this.hotel = hotel;
    }
    
    
    @Override
    public int computeRent() {
        if(house ==0 && hotel ==0) return super.getRent();
        else return super.computeRent()*(house>0 ? house : hotel); 
    }
    
}
