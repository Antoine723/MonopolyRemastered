/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgame;

import static boardgame.BoardGame.board;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Antoine
 */

/**
 * La classe Avenue correspond aux différentes avenues présentes sur le plateau
 * <br>
 * <br>
 * Cette classe est caractérisée par les informations suivantes :
 * <br>
 * <br>
 * Le paramètre house qui indique le nombre de maison sur une avenue
 * <br>
 * Le paramètre hotel qui indique le nombre d'hôtels sur une avenue
 * <br>
 * Le paramètre color qui indique la couleur d'une avenue
 * <br>
 * Le paramètre priceOfHouseAndHotels  indiquant le prix des maisons et des hôtels
 * <br>
 * Le paramètre soldAvenueCoeff qui correspond à un coefficient lors de la vente d'une avenue
 * <br>
 * Le paramètre basedRent qui correspond au prix de base de l'avenue
 * <br>
 * On retrouve dans cette classe les fonctions permettant de récupérer et modifier les précédents paramètres
 * <br>
 * On retrouve aussi la fonction permettant d'ajuster le loyer des avenues selon le nombre de maisons et d'hôtels
 * @author thibb
 */

public final class Avenue extends Property{
    
    private int house=0;
    private int hotel=0;
    private ColorAvenue color;
    private int priceOfHouseAndHotels = 0;
    private float soldAvenueCoeff = 0 ;
    private int basedRent;
    //Coefficient multiplicateur nombre de maison/hotel avec loyer
    
    /**
     * <b> Constructeur de Avenue </b>
     * <br>
     * @param price
     *      Le paramètre correspond au prix d'achat de l'avenue
     * @param basedRent
     *      Le paramètre correspond au loyer sur terrain nu de l'avenue
     * @param name
     *      Le paramètre correspond au nom de l'avenue
     * @param caseNumber
     *      Le paramètre correspond au numéro de case de l'avenue
     * @param color
     *      Le paramètre correspond à la couleur de l'avenue
     * @param priceOfHouseAndHotels
     *      Le paramètre correspond aux prix des maisons et des hôtels sur l'avenue
     * @param mortgage 
     *      Le paramètre correspond à l'hypothèque de l'avenue
     */
    public Avenue(int price,int basedRent, String name, int caseNumber, ColorAvenue color, int priceOfHouseAndHotels, int mortgage){      // AJOUT DE MORTGAGE
        this.setName(name);
        this.setCaseNumber(caseNumber);
        this.setBoughtPrice(price);
        this.basedRent=basedRent;
        this.setRent(this.basedRent);
        this.priceOfHouseAndHotels=priceOfHouseAndHotels;
        this.setMortgage(mortgage);
        this.color=color;
    }
    
    
    /**
     * @return  Cette méthode retourne le nombre de maisons sur une avenue
     */
    public int getHouse() {
        return house;
    }
    
    
    /**
     * @return  Cette méthode retourne le nombre d'hôtels sur une avenue
     */
    public int getHotel() {
        return hotel;
    }
    
    /**
     * @return  Cette méthode retourne le prix des maisons et hôtels sur une avenue
     */
    public int getPriceOfHouseAndHotels() {
        return priceOfHouseAndHotels;
    }
    
    /**
     * @return  Cette méthode retourne la couleur d'une avenue
     */
    public ColorAvenue getColor() {
        return color;
    }
    
    /**
     * @return Cette méthode retourne le loyer sur terrain nu d'une avenue
     */
    public int getBasedRent() {
        return basedRent;
    }

       /**
     * @return Cette méthode retourne le coefficient lors de la vente d'une avenue
     */
    public float getSoldAvenueCoeff() {
        return soldAvenueCoeff;
    }
    
    /**
     * Cette méthode modifie le coefficient lors de la vente d'une avenue
     * @param soldAvenueCoeff
     *      Le paramètre correpond au coefficient lors de la vente d'une avenue
     */
    public void setSoldAvenueCoeff(float soldAvenueCoeff) {
        this.soldAvenueCoeff = soldAvenueCoeff;
    }
    
    /**
     * Cette méthode modifie le nombre de maisons sur une avenue
     * @param house
     *      Le paramètre correpond au nombre de maisons sur une avenue
     */
    public void setHouse(int house) {
        this.house = house;
    }
    
     /**
     * Cette méthode modifie le nombre d'hôtels sur une avenue
     * @param hotel
     *      Le paramètre correpond au nombre d'hôtels sur une avenue
     */
    public void setHotel(int hotel) {
        this.hotel = hotel;
    }
    
    /**
     * Cette méthode modifie le loyer sur terrain nu d'une avenue
     * @param basedRent 
     *       Le paramètre correpond au loyer sur terrain nu d'une avenue
     */
    public void setBasedRent(int basedRent) {
        this.basedRent = basedRent;
    }

    
    /**
     * Cette méthode permet d'attribuer à chaque propriété un loyer basé sur le nombre de maisons et d'hôtels
     * @param player
     *      Le paramètre correspond à l'ensemble des joueurs
     * @param prop
     *      Le paramètre correspond à la propriété étudiée
     * @return  Cette méthode retourne le loyer de la propriété étudiée
     */
    @Override
    public int computing(Property prop,Player player)
    {
        float houseCoefficient;
        float hotelCoefficient;
        String line;
        int numberCase;
        String nameInstance=null;
        try (BufferedReader reader = new BufferedReader(new FileReader("coeffs.txt"))) {
            while( (line=reader.readLine())!=null){
                String [] splitted=line.split("\t"); //On split la ligne récupérée au niveau des tabulations et on la stocke dans un tableau
                numberCase=Integer.parseInt(splitted[0]);
                houseCoefficient=Float.parseFloat(splitted[1]);
                hotelCoefficient=Float.parseFloat(splitted[2]);
                if(splitted.length==4) nameInstance=splitted[3];
                if(this.getCaseNumber()==numberCase){
                    if(this.getHouse()>0){
                        if(nameInstance!=null && player.getClass().getSimpleName().equals(nameInstance)) this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient*2));
                        else this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient));
                    }
                    else if (this.getHouse()==0 && this.getHotel()==0)
                    {
                        if(nameInstance!=null && player.getClass().getSimpleName().equals(nameInstance)) this.setRent((int)this.basedRent *2);
                        else this.setRent((int)this.basedRent);
                    }
                    else if(this.getHotel() > 0)
                    {
                        if(nameInstance!=null && player.getClass().getSimpleName().equals(nameInstance)) this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient));
                        else this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient));
                    }
                }
                
            }   
            reader.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("Fichier introuvable");
        }
        catch(IOException ex){
            System.out.println("Input/Output Exception");
        }
        return this.getRent();
    }  
    }


