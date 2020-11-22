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
public final class RailRoad extends Property {
    
    private int number=0;
    
    public RailRoad(int price,int rent, String name, int caseNumber,int mortgage){          // AJOUT DE MORTGAGE
        this.setName(name);
        this.setCaseNumber(caseNumber);
        this.setMortgage(mortgage);
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
        if(player!=null){
            System.out.println("Nombre de gares du joueur= "+prop.getAssociatedPlayer().getNumberOfRailRoads());
            for(int i=0; i<player.getProperties().size();i++){
                if(player.getProperties().get(i) instanceof RailRoad){
                    switch(prop.getAssociatedPlayer().getNumberOfRailRoads())
                    {
                        case 1:
                            player.getProperties().get(i).setRent(2500);                                         // valeurs indiquées sur les cartes
                            break;
                        case 2:
                            player.getProperties().get(i).setRent(5000);                                         // valeurs indiquées sur les cartes
                            break;

                        case 3:
                            player.getProperties().get(i).setRent(10000);
                            break;

                        case 4:
                            player.getProperties().get(i).setRent(20000);
                            break;
                    }
                }

            }
        }
        else{
            prop.setRent(2500);
        }
        
        return prop.getRent();                                                  // s' il n'y a qu'une gare on renvoie juste le loyer
    }
}
