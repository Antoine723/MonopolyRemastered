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
 * La classe Company correspond aux cases compagnies du plateau de jeu
 * <br>
 * <br>
 * Cette classe est caractérisée par les informations suivantes :
 * <br>
 * <br>
 * On retrouve les fonctions permettant d'ajuster le loyer des compagnies
 * @author thibb
 */
public final class Company extends Property{
 
    /**
     * <b> Constructeur de Compagny </b>
     * <br>
     * @param price
     *      Le paramètre correspond au prix d'achat de la compagnie
     * @param name
     *      Le paramètre correspond au nom de la compagnie
     * @param caseNumber
     *      Le paramètre correspond au numéro de case de la compagnie
     * @param mortgage 
     *      Le paramètre correspond à l'hypothèque de la compagnie
     */
    public Company(int price, String name, int caseNumber,int mortgage){                //      AJOUT DE MORTGAGE
        this.setName(name);
        this.setCaseNumber(caseNumber);
        this.setMortgage(mortgage);
        this.setBoughtPrice(price);
        
    }

    
  /**
     * Cette méthode permet d'ajuster les loyers des compagnies en fonction du nombre de compagnie que possède chaque joueur
     * @param prop
     *      Le paramètre correspond à la propriété étudiée, ici une compagnie
     * @param player
     *      Le paramètre correspond à la liste des joueurs
     * @return  Cette méthode retourne le loyer des différentes compagnies
     */
   @Override
   public int computing(Property prop,Player player)
   {
       for(int i=0; i<player.getProperties().size();i++){
            if(player.getProperties().get(i) instanceof RailRoad){
                if (prop.getAssociatedPlayer().getNumberOfCompanies() == 1)         // si on a 1 compagnie
                {
                    player.getProperties().get(i).setRent((player.rollsDice().get(0) + player.rollsDice().get(1)) * 400);        // instructions de la carte (400*valuer indiquée par les dés)
                }

                else if(prop.getAssociatedPlayer().getNumberOfCompanies() == 2)
                {
                    player.getProperties().get(i).setRent((player.rollsDice().get(0) + player.rollsDice().get(1)) * 1000);        // idem
                }           
            }
       }
       return prop.getRent();
   }
        
}
