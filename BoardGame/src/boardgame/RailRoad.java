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
public class RailRoad extends Property {
    
    private int number=0;
    
    public RailRoad(int price,int rent, String name, int caseNumber){
        super(name,caseNumber);
        this.setBoughtPrice(price);
        this.setRent(rent);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    
    @Override
    public int computeRent(){
        return super.getRent()*number;
    }
}
