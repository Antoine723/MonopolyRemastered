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
public class Covid extends Event{
    
    private boolean inAction=false;
    
    public Covid(){
        this.setName("Covid");
    }

    public boolean isInAction() {
        return inAction;
    }

    public void setInAction(boolean inAction) {
        this.inAction = inAction;
    }
    public void closeHotel(Avenue avenue ,Player player, int warning)  // gérer la notion de tour
    {
        warning = 1;
        if(avenue.getHotel() > 0 && inAction == true)               // l'event ne concerne que les hôtels
        {
            switch(warning)           
            {
                case 1:
                    System.out.println("Des cas covid ont été identifiés, il faut prévoir un budget pour la désinfection de l'hôtel");
                    avenue.setRent(avenue.getRent() + 200); // à voir si montant trop grand ou trop petit
                    warning ++ ;
                    break;
                case 2:
                    System.out.println("On dénombre de plus en plus de cas, les clients ont désormais peur de venir");
                    avenue.setRent(avenue.getRent() / 2);  // moins de clients -> baisse de revenue
                    warning ++;
                    break;
                case 3:
                    System.out.println("Le gouvernement vient d'annoncer un reconfinement, vos hôtels doivent fermer");
                    avenue.setRent(0); // plus de loyer
                    warning = 1 ;
                    break;
            }
        }
    }
    
}
