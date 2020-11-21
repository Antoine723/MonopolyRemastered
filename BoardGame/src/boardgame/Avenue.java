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
public final class Avenue extends Property{
    
    private int house=0;
    private int hotel=0;
    private ColorAvenue color;
    private int priceOfHouseAndHotels = 0;
    private float soldAvenueCoeff = 0 ;
    private int mortgage = 0;
    private int basedRent;
    //Coefficient multiplicateur nombre de maison/hotel avec loyer
    public Avenue(int price,int basedRent, String name, int caseNumber, ColorAvenue color, int priceOfHouseAndHotels, int mortgage){      // AJOUT DE MORTGAGE
        super(name,caseNumber,mortgage);
        this.setBoughtPrice(price);
        this.basedRent=basedRent;
        this.setRent(this.basedRent);
        this.priceOfHouseAndHotels=priceOfHouseAndHotels;
        this.mortgage=mortgage;
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
    
   
    /*public void updatePriceOfHousesAndHotels()
    {
        switch(this.color)
        {
            case VIOLET:
                this.priceOfHouseAndHotels=5000;
                this.soldAvenueCoeff=0.2F;                                 // selon la couleur de la case on attribue le prix des hôtels et maisons
                
                break;                                                           // on attribue aussi un coefficient pour la carte attaque GiveAway
                
            case CIEL:
                this.priceOfHouseAndHotels=5000;
                this.soldAvenueCoeff=0.2F;  
                break;
                
            case ROSE:
                this.priceOfHouseAndHotels=10000;
                this.soldAvenueCoeff=0.4F;  
                break; 
               
            case ORANGE:
                this.priceOfHouseAndHotels=10000;
                this.soldAvenueCoeff=0.4F;  
                break;
               
            case ROUGE:
                this.priceOfHouseAndHotels=15000;
                this.soldAvenueCoeff=0.6F;  
                break;
                
            case JAUNE:
                this.priceOfHouseAndHotels=15000;
                this.soldAvenueCoeff=0.6F;  
                break;
            case VERT:
                this.priceOfHouseAndHotels=20000;
                this.soldAvenueCoeff=0.8F;
                break;
                
            case BLEU:
            this.priceOfHouseAndHotels=20000;
            this.soldAvenueCoeff=0.8F;
            break;

        }
    }  */ 
    
    @Override
    public int computing(Property prop,Player player)
    {
        float houseCoefficient;
        float hotelCoefficient;
        
        switch(this.getCaseNumber())
        {
            case 1:
                if(this.getHouse() > 0)
                {
                    houseCoefficient =1.2F;
                    this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient));
                }
                else if(this.getHotel() > 0)
                {
                    hotelCoefficient = 2.0F;
                    this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient));
                }
                break;
            
            case 3:
                if(this.getHouse() > 0)
                {
                    houseCoefficient =1.2F;
                    this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient));
                }
                else if(this.getHotel() > 0)
                {
                    hotelCoefficient = 2.0F;
                    this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient));
                }
                break;
            
            case 6:
                if(this.getHouse() > 0)
                {
                    houseCoefficient =1.3F;
                    this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient));
                }
                else if(this.getHotel() > 0)
                {
                    hotelCoefficient = 2.1F;
                    this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient));
                }
                break;
                
            case 8:
                if(this.getHouse() > 0)
                {
                    houseCoefficient =1.4F;
                    this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient));
                }
                else if(this.getHotel() > 0)
                {
                    hotelCoefficient = 2.2F;
                    this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient));
                }
                break;
                
            case 9:
                if(this.getHouse() > 0)
                {
                    houseCoefficient =1.5F;
                    this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient));
                }
                else if(this.getHotel() > 0)
                {
                    hotelCoefficient = 2.3F;
                    this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient));
                }
                break;
                
            case 11:
                if(this.getHouse() > 0)
                {
                    houseCoefficient =1.6F;
                    if(player instanceof Cannon){
                        this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient*2));
                    }
                    else this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient));
                }
                else if(this.getHotel() > 0)
                {
                    hotelCoefficient = 2.4F;
                    if(player instanceof Cannon){
                        this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient*2));
                    }
                    else this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient));
                }
                break;
                
            case 13:
                if(this.getHouse() > 0)
                {
                    houseCoefficient =1.7F;
                    if(player instanceof Cannon){
                        this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient*2));
                    }
                    else this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient));
                }
                else if(this.getHotel() > 0)
                {
                    hotelCoefficient = 2.5F;
                    if(player instanceof Cannon){
                        this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient*2));
                    }
                    else this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient));
                }
                break;
                
            case 14:
                if(this.getHouse() > 0)
                {
                    houseCoefficient =1.8F;
                    if(player instanceof Cannon){
                        this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient*2));
                    }
                    else this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient));
                }
                else if(this.getHotel() > 0)
                {
                    hotelCoefficient = 2.6F;
                    if(player instanceof Cannon){
                        this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient*2));
                    }
                    else this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient));
                }
                break;
                
            case 16:
                if(this.getHouse() > 0)
                {
                    houseCoefficient =1.9F;
                    if(player instanceof Car){
                        this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient*2));
                    }
                    else this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient));
                }
                else if(this.getHotel() > 0)
                {
                    hotelCoefficient = 2.7F;
                    if(player instanceof Car){
                        this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient*2));
                    }
                    else this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient));
                }
                break;
                
            case 18:
                if(this.getHouse() > 0)
                {
                    houseCoefficient = 2.0F;
                    if(player instanceof Car){
                        this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient*2));
                    }
                    else this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient));
                }
                else if(this.getHotel() > 0)
                {
                    hotelCoefficient = 2.8F;
                    if(player instanceof Car){
                        this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient*2));
                    }
                    else this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient));
                }
                break;
                
            case 19:
                if(this.getHouse() > 0)
                {
                    houseCoefficient = 2.1F;
                    if(player instanceof Car){
                        this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient*2));
                    }
                    else this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient));
                }
                else if(this.getHotel() > 0)
                {
                    hotelCoefficient = 2.9F;
                    if(player instanceof Car){
                        this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient*2));
                    }
                    else this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient));
                }
                break;
                
            case 21:
                if(this.getHouse() > 0)
                {
                    houseCoefficient = 2.2F;
                    if(player instanceof Hat){
                        this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient*2));
                    }
                    else this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient));
                }
                else if(this.getHotel() > 0)
                {
                    hotelCoefficient = 3.0F;
                    if(player instanceof Hat){
                        this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient*2));
                    }
                    else this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient));
                }
                break;
                
            case 23:
                if(this.getHouse() > 0)
                {
                    houseCoefficient = 2.3F;
                    if(player instanceof Hat){
                        this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient*2));
                    }
                    else this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient));
                }
                else if(this.getHotel() > 0)
                {
                    hotelCoefficient = 3.1F;
                    if(player instanceof Hat){
                        this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient*2));
                    }
                    else this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient));
                }
                break;
                
            case 24:
                if(this.getHouse() > 0)
                {
                    houseCoefficient = 2.4F;
                    if(player instanceof Hat){
                        this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient*2));
                    }
                    else this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient));
                }
                else if(this.getHotel() > 0)
                {
                    hotelCoefficient = 3.2F;
                    if(player instanceof Hat){
                        this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient*2));
                    }
                    else this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient));
                }
                break;
                
            case 26:
                if(this.getHouse() > 0)
                {
                    houseCoefficient = 2.5F;
                    this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient));
                }
                else if(this.getHotel() > 0)
                {
                    hotelCoefficient = 3.3F;
                    this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient));
                }
                break;
                
            case 27:
                if(this.getHouse() > 0)
                {
                    houseCoefficient = 2.6F;
                    this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient));
                }
                else if(this.getHotel() > 0)
                {
                    hotelCoefficient = 3.4F;
                    this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient));
                }
                break;
                
            case 29:
                if(this.getHouse() > 0)
                {
                    houseCoefficient = 2.7F;
                    this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient));
                }
                else if(this.getHotel() > 0)
                {
                    hotelCoefficient = 3.5F;
                    this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient));
                }
                break;
                
            case 31:
                if(this.getHouse() > 0)
                {
                    houseCoefficient = 2.8F;
                    this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient));
                }
                else if(this.getHotel() > 0)
                {
                    hotelCoefficient = 3.6F;
                    this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient));
                }
                break;
                
            case 32:
                if(this.getHouse() > 0)
                {
                    houseCoefficient = 2.9F;
                    this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient));
                }
                else if(this.getHotel() > 0)
                {
                    hotelCoefficient = 3.7F;
                    this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient));
                }
                break;
                
            case 34:
                if(this.getHouse() > 0)
                {
                    houseCoefficient = 3.0F;
                    this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient));
                }
                else if(this.getHotel() > 0)
                {
                    hotelCoefficient = 3.8F;
                    this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient));
                }
                break;
                
            case 37:
                if(this.getHouse() > 0)
                {
                    houseCoefficient = 3.1F;
                    this.setRent((int) (this.basedRent * this.getHouse() *houseCoefficient));
                }
                else if(this.getHotel() > 0)
                {
                    hotelCoefficient = 3.9F;
                    this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient));
                }
                break;
                
            case 39:
                if(this.getHouse() > 0)
                {
                    houseCoefficient = 3.2F;
                    this.setRent((int) (this.basedRent* this.getHouse() *houseCoefficient));
                }
                else if(this.getHotel() > 0)
                {
                    hotelCoefficient = 4.0F;
                    this.setRent((int) (this.basedRent * this.getHotel() *hotelCoefficient));
                }
                break;
        }
        return this.getRent();
    }  
    }


