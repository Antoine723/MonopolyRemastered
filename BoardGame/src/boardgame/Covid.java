/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgame;
import java.util.Random;
/**
 *
 * @author Antoine
 */
public class Covid extends Event{
    
    Random rand=new Random();
    
    public Covid(String name, String effect){
        this.setName(name);
        this.setEffect(effect);
    }
    
    public void closeHotel(Avenue avenue ,Player player)  // gérer la notion de tour
    {
        if(avenue.getHotel() > 0)               // l'event ne concerne que les hôtels
        {
            switch(rand.nextInt(3)+1)           // on génère un nombre entre 1 et 3 niveaux d'alertes
            {
                case 1:
                    System.out.println("Des cas covid ont été identifiés, il faut prévoir un budget pour la désinfection de l'hôtel");
                    avenue.setRent(avenue.getRent() + 200); // à voir si montant trop grand ou trop petit
                    break;
                case 2:
                    System.out.println("On dénombre de plus en plus de cas, les clients ont désormais peur de venir");
                    avenue.setRent(avenue.getRent() / 2);  // moins de clients -> baisse de revenue
                    break;
                case 3:
                    System.out.println("Le gouvernement vient d'annoncer un reconfinement, vos hôtels doivent fermer");
                    avenue.setRent(0); // plus de loyer
                    break;
            }
        }
    }
    
}
