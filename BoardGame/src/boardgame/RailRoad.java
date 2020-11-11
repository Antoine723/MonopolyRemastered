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
    public int computeRent(){
        return super.getRent()*number;
    }
    
    
    public int computing(Property prop,Player player)                          // AJOUT D'UNE FONCTION COMPUTING POUR TOUT CALCULER D'UN COUP -> SUPPRIMER COMPUTE RENT ??
    {
        if(prop instanceof Avenue)
        {
            return prop.getRent();   //faire fonction compute avenue
        }
        else if (prop instanceof RailRoad)
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
            return prop.getRent();                                              // s' il n'y a qu'une gare on renvoie juste le loyer
        }
        
        else
        {
            if (prop.getAssociatedPlayer().getNumberOfCompanies() == 1)         // si on a 1 compagnie
            {
                prop.setRent((player.rollsDice().get(0) + player.rollsDice().get(1)) * 400);        // instructions de la carte (400*valuer indiquée par les dés)
            }
            else if(prop.getAssociatedPlayer().getNumberOfCompanies() == 2)
                prop.setRent((player.rollsDice().get(0) + player.rollsDice().get(1)) * 1000);           // idem
            
            return prop.getRent();
        }
    }
}
