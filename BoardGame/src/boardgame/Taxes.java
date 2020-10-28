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
public class Taxes extends Case {
    
    private int price;
    
    public Taxes(String name, int caseNumber,int price){
        this.setName(name);
        this.setCaseNumber(caseNumber);
        this.price=price;
        
    }
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
}
