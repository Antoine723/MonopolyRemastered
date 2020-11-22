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

/**
 * Cette classe correspond au pouvoir Mayor permettant de construire un hôtel sur l'avenue souhaitée
 * <br>
 * <br>
 * Cette classe est caractérisée par les informations suivantes :
 * <br>
 * <br>
 * On retrouve dans cette classe la fonction permettant de construire un hôtel sur l'avenue sélectionnée
 * @author thibb
 */
public final class Mayor extends Player {
    
   /**
    * <b> Constructeur de Mayor </b>
    * <br>
    * @param name
    *       Le paramètre indique le nom du joueur qui a choisi le pouvoir Mayor
    * @param playerNumber 
    *       Le paramètre indique le numéro du joueur qui a choisi le pouvoir Mayor
    */
    
    public Mayor(String name,int playerNumber){
        this.setName(name);
        this.setPlayerNumber(playerNumber);
    }
    
    /**
     * Cette méthode permet de faire un construire un hôtel sur une avenue
     * @param avenue
     *      Le paramètre indique l'avenue sur laquelle l'hôtel est construit
     */
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
