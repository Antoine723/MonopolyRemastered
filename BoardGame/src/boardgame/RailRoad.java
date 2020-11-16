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
    
    public RailRoad(int price,int rent, String name, int caseNumber,int mortgage){          // AJOUT DE MORTGAGE
        super(name,caseNumber,mortgage);
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
    public int computing(Property prop,Player player)
    {
        switch(prop.getAssociatedPlayer().getNumberOfRailRoads())
            {
                case 2:
                    prop.setRent(5000);                                         // valeurs indiquées sur les cartes
                    break;

                case 3:
                    prop.setRent(10000);
                    break;

                case 4:
                    prop.setRent(20000);
                    break;
            }
        return prop.getRent();                                                  // s' il n'y a qu'une gare on renvoie juste le loyer
    }
}
