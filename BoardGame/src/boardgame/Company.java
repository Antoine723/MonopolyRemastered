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
public class Company extends Property{
    
    private int number=0;
    //A ajouter loyer=4*montant des dés si une compagnie, si les 2 *10
    public Company(int price, String name, int caseNumber,int mortgage){                //      AJOUT DE MORTGAGE
        super(name,caseNumber,mortgage);
        this.setBoughtPrice(price);
        
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    
    @Override
    public int computeRent(){
        return super.computeRent()*number;
    }
    
}
