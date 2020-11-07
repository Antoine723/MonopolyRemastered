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
    
    
    public int computing(Property prop)
    {
        if(prop instanceof Avenue)
        {
            return prop.getRent();
        }
        else if (prop instanceof RailRoad)
        {
            switch(prop.getAssociatedPlayer().getNumberOfRailRoads())
            {
                case 2:
                    prop.setRent(prop.getRent() + (int) 0.5*prop.getRent());
                    break;

                case 3:
                    prop.setRent(prop.getRent() + (int) 1.5*prop.getRent());
                    break;

                case 4:
                    prop.setRent(prop.getRent() + (int) 2*prop.getRent());
                    break;
            }
            return prop.getRent();
        }
        
        else
        {
            if (prop.getAssociatedPlayer().getNumberOfCompanies() == 2)
            {
                prop.setRent(prop.getRent() + (int) 2*prop.getRent());
            }
            return prop.getRent();
        }
    }
}
