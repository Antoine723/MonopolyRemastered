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
 * Cette interface permet d'implémenter les différents pouvoirs des pions
 * @author thibb
 */
public interface Citizen {
    
    /**
     * Cette méthode permet d'appliquer un bonus de loyer si la couleur de l'avenue correspond à celle du pion
     * @param avenue
     *      Le paramètre permet de savoir si l'avenue est bien de couleur identique à celle du pion
     */
    public void doubleRent(Avenue avenue);
    
}
