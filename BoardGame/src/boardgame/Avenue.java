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
public final class Avenue extends Property{
    
    private int house=0;
    private int hotel=0;
    private ColorAvenue color;
    private int priceOfHouseAndHotels = 0;
    private float soldAvenueCoeff = 0 ;
    private int basedRent;
    //Coefficient multiplicateur nombre de maison/hotel avec loyer
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

    public int getHouse() {
        return house;
    }

    public int getHotel() {
        return hotel;
    }

    public int getPriceOfHouseAndHotels() {
        return priceOfHouseAndHotels;
    }

    public ColorAvenue getColor() {
        return color;
    }

    public int getBasedRent() {
        return basedRent;
    }


    public float getSoldAvenueCoeff() {
        return soldAvenueCoeff;
    }

    public void setSoldAvenueCoeff(float soldAvenueCoeff) {
        this.soldAvenueCoeff = soldAvenueCoeff;
    }
    
    public void setHouse(int house) {
        this.house = house;
    }

    public void setHotel(int hotel) {
        this.hotel = hotel;
    }

    public void setBasedRent(int basedRent) {
        this.basedRent = basedRent;
    }

    
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
                String [] splitted=line.split("\t"); //On split la ligne récupéré au niveau des tabulations et on la stocke dans un tableau
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


