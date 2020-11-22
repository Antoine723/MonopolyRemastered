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
 * La classe RailRoad correspond aux cases gares du plateau de jeu
 * <br>
 * <br>
 * Cette classe est caractérisée par les informations suivantes :
 * <br>
 * <br>
 * Le paramètre number qui précise le nombre de gares que possède un joueur
 * <br>
 * On retrouve les fonctions permettant d'ajuster le loyer des gares
 * On retrouve aussi les fonctions permettant de récupérer et modifier le nombre de gares possédées par un joueur
 * @author thibb
 */
public final class RailRoad extends Property {
    
    private int number=0;
    
    /**
     * <b> Constructeur de RailRoad </b>
     * <br>
     * @param price
     *      Le paramètre correspond au prix d'achat de la gare
     * @param rent
     *      Le paramètre correspond au loyer d'une gare
     * @param name
     *      Le paramètre correspond au nom de la gare
     * @param caseNumber
     *      Le paramètre correspond au numéro de case de la gare
     * @param mortgage 
     *      Le paramètre correspond à l'hypothèque de la gare
     */
    public RailRoad(int price,int rent, String name, int caseNumber,int mortgage){          // AJOUT DE MORTGAGE
        this.setName(name);
        this.setCaseNumber(caseNumber);
        this.setMortgage(mortgage);
        this.setBoughtPrice(price);
        this.setRent(rent);
    }
    
    
    /**
     * Cette méthode permet de récupérer le nombre de gares que possède un joueur
     * @return  Cette méthode retourne le nombre de gare possédé par un joueur
     */
    public int getNumber() {
        return number;
    }
    
    /**
     * Cette méthode permet de modifier le nombre de gare que possède un joueur
     * @param number
     *      Le paramètre correspond au nombre de gares que possède un joueur
     */
    public void setNumber(int number) {
        this.number = number;
    }
    
    
    /**
     * Cette méthode permet d'ajuster les loyers des gares en fonction du nombre de gares que possède chaque joueur
     * @param prop
     *      Le paramètre correspond à la propriété étudiée, ici une gare
     * @param player
     *      Le paramètre correspond à la liste des joueurs
     * @return  Cette méthode retourne le loyer des différentes gares
     */
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
