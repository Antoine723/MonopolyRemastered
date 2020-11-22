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
 * Cette classe correspond aux évenements qui selon une certaine fréquence influence plus ou moins fortement la partie
 * <br>
 * <br>
 * Cette classe est caractérisée par les informations suivantes :
 * <br>
 * <br>
 * La chaîne de caractères name qui indique le nom de l'évènement
 * <br>
 * On retrouve dans cette classe la fonction permettant de récupérer le nom de l'évènement et celle permettant de le modifier
 * @author thibb
 */
public abstract class Event {
    
    /**
     * @param name
     *      Le paramètre indique le nom de l'évènement
     */
    private String name;
    
    /**
     * Cette méthode permet de récupérer le nom de l'évènement
     * @return  La méthode retourne le nom de l'évènement
     */

    public String getName() {
        return name;
    }

    /**
     * Cette méthode permet de modifier le nom de l'évènement
     * @param name
     *      Le paramètre indique le nom de l'évènement
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
