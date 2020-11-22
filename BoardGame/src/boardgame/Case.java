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
 * La classe Case correspond aux différentes cases du plateau de jeu
 * <br>
 * <br>
 * Cette classe est caractérisée par les informations suivantes :
 * <br>
 * <br>
 * Le paramètre name qui correspond au nom de la case
 * <br>
 * Le paramètre caseNumber qui correspond au numéro de la case
 * <br>
 * On retrouve dans cette classe les fonctions permettant de récupérer nom et numéro de case ainsi que les fonctions permettant de modifier ces deux attributs
 * @author thibb
 */
public class Case {
    private String name;
    private int caseNumber;
    
    
    /**
     * <b> Constructeur de Case </b>
     * <br>
     * @param name
     *       Le paramètre indique le nom de la case
     * @param caseNumber
     *      Le paramètre indique le numéro de la case
     */
    public Case(String name, int caseNumber){ //Constructeur de cases pour des cases telles que "Départ" ou "Parc gratuit" qui ne font rien de particulier (l'argent touché en passant par la case départ se fait en dehors)
        this.name=name;
        this.caseNumber=caseNumber;
    }
    
    /**
     * <b> Autre Constructeur de Case </b>
     * <br>
     * Ce constructeur vide permet aux classes filles d'adopter leurs propres constructeurs
     */
    public Case(){ //Constructeur vide pour les classes filles qui disposent de leur propre constructeur
        
    }
    
    /**
     * Cette méthode permet de récupérer le nom de la case
     * @return  Cette méthode retourne le nom de la case
     */
    public String getName() {
        return name;
    }
    
    /**
     * Cette méthode permet de récupérer le numéro de la case
     * @return  Cette méthode retourne le numéro de la case
     */
    public int getCaseNumber() {
        return caseNumber;
    }
    
    /**
     * Cette méthode permet de modifier le nom de la case
     * @param name
     *      Le paramètre correspond au nom de la case
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Cette méthode permet de modifier le nom de la case
     * @param caseNumber
     *      Le paramètre correspond au numéro de la case
     */
    public void setCaseNumber(int caseNumber) {
        this.caseNumber = caseNumber;
    }
    
    
}
