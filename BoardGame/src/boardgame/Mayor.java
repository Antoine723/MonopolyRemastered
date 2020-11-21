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
public final class Mayor extends Player {
    
    public Mayor(String name,int playerNumber){
        this.setName(name);
        this.setPlayerNumber(playerNumber);
    }
    
    
    public void buildHotel(Avenue avenue){
        if(avenue.getHotel()==1){
            System.out.println("Vous ne pouvez pas mettre plus d'un hôtel sur votre propriété");
        }
        else{
            avenue.setHotel(1);
            avenue.computing(avenue, this);
            this.setCapital(this.getCapital()-avenue.getPriceOfHouseAndHotels()*4);
            System.out.println("Vous avez posé 1 hôtel sur "+avenue.getName()+" pour la modique somme de "+ avenue.getPriceOfHouseAndHotels()*4+" Francs");
        }
        
    }
    
}
