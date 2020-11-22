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
 * La classe Prison correspond à la case prison du Monopoly
 * <br>
 * <br>
 * Cette classe est caractérisée par les informations suivantes :
 * <br>
 * <br>
 * Le paramètre player permettant de savoir si un joueur est arrivé sur la case
 * On retrouve dans cette classe les fonctions permettant de récupérer le joueur qui est en prison 
 */
public final class Prison extends Case {                     //A CHEKER
    
    private Player player=null;
    
    /**
     * <b> Constructeur de Prison </b>
     * <br>
     * @param name
     *       Le paramètre indique le nom de la case
     * @param caseNumber
     *      Le paramètre indique le numéro de la case
     */
    public Prison(String name, int caseNumber){
        this.setName(name);
        this.setCaseNumber(caseNumber);
        
    }
    
    /**
     * Cette méthode permet de récupérer joueur qui est en prison
     * @return  Cette méthode retourne le joueur en prison
     */
    public Player getPlayer() {
        return player;
    }
    
    /**
     * Cette méthode permet de modifier le joueur pour le faire sortir ou entrer en prison
     * @param player
     *      Le paramètre correspond au joueur qui est allé en prison
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
    
}
