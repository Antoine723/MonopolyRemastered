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
 * La classe Taxes correspond à la case taxe du Monopoly
 * <br>
 * <br>
 * Cette classe est caractérisée par les informations suivantes :
 * <br>
 * <br>
 * L'entier price qui indique le tarif à payer une fois arrivé sur la case
 * On retrouve dans cette classe les fonctions permettant de récupérer le tarif 
 */
public final class Taxes extends Case {
    
    private final int price;
    
    
    /**
     * <b> Constructeur de Taxes </b>
     * <br>
     * @param name
     *       Le paramètre indique le nom de la case
     * @param caseNumber
     *      Le paramètre indique le numéro de la case
     * @param price
     *      Le paramètre indique le tarif de la case
     */
    
    public Taxes(String name, int caseNumber,int price){
        this.setName(name);
        this.setCaseNumber(caseNumber);
        this.price=price;
        
    }
    
    /**
     * Cette méthode permet de récupérer le tarif à payer sur la case Taxe
     * @return  Cette méthode retourne le tarif
     */
    public int getPrice() {
        return price;
    }

}
