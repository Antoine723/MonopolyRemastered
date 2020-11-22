/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgame;

import java.util.ArrayList;

/**
 *
 * @author Antoine
 */

/**
 * La classe Attack correspond aux différentes cartes attaques disponibles
 * <br>
 * <br>
 * Cette classe est caractérisée par les informations suivantes :
 * <br>
 * <br>
 * Le paramètre name qui correspond au nom de la carte attaque
 * <br>
 * Le paramètre effect qui correspond à l'effet de la carte
 * <br>
 * Le paramètre isUsed pour savoir si la carte a été utilisée ou non
 * <br>
 * On retrouve dans cette classe les fonctions permettant de récupérer nom effet et usage de la carte ainsi que les fonctions permettant de mofifier ces trois attributs
 * @author thibb
 */
public abstract class Attack {
    
    private String name;
    private String effect;
    private boolean isUsed=false;
    
    
    /**
     * Cette méthode permet de récupérer le nom de la carte
     * @return  Cette méthode retourne le nom de la carte
     */

    public String getName() {
        return name;
    }
    
    /**
     * Cette méthode permet de récupérer l'effet de la carte
     * @return  Cette méthode retourne l'effet de la carte
     */
    public String getEffect() {
        return effect;
    }
    
    /**
     * Cette méthode permet de récupérer le booléen pour savoir si la carte a été utilisée ou non
     * @return  Cette méthode retourne le booléen pour savoir si la carte a été utilisée ou non
     */
    public boolean isItUsed() {
        return isUsed;
    }

    /**
     * Cette méthode permet de modifier l'état de la carte (utilisée ou non)
     * @param isUsed
     *      Le paramètre correspond à l'état de la carte
     */
    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }
    
    /**
     * Cette méthode permet de modifier le nom de la carte
     * @param name
     *      Le paramètre correspond au nom de la carte
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Cette méthode permet de modifier l'effet de la carte
     * @param effect
     *      Le paramètre correspond à l'effet de la carte
     */

    public void setEffect(String effect) {
        this.effect = effect;
    }
    
    /**
     * Cette méthode permet d'appliquer les effets des différentes cartes
     * @param players
     *      Le paramètre correspond à la liste des joueurs
     * @param attacker
     *      Le paramètre correspond au joueur qui lance l'attaque
     * @param board
     *      Le paramètre correspond au plateau de jeu
     * @return Cette méthode retourne vrai ou faux si l'effet de la carte s'est bien produit ou non
     */
    public abstract boolean effect(ArrayList <Player> players, Player attacker,ArrayList <Case> board);
    
    
    
}
