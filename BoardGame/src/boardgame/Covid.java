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
public final class Covid extends Event{
    
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
    

    public int closeHotel(ArrayList <Player> players , int warning)  // gérer la notion de tour
    {
        this.inAction=true;
        for(int i=0;i<players.size();i++){
            if(!players.get(i).getAvenues().isEmpty()){
                for(int j=0;j<players.get(i).getAvenues().size();j++){
                    if(players.get(i).getAvenues().get(j).getHotel()>0){
                        switch(warning)           
                        {
                            case 1:
                                System.out.println("Des cas covid ont été identifiés, il faut prévoir un budget pour la désinfection de l'hôtel");
                                players.get(i).getAvenues().get(j).setRent(players.get(i).getAvenues().get(j).getRent() + 200); // à voir si montant trop grand ou trop petit
                                warning++;
                                break;
                            case 2:
                                System.out.println("On dénombre de plus en plus de cas, les clients ont désormais peur de venir");
                                players.get(i).getAvenues().get(j).setRent(players.get(i).getAvenues().get(j).getRent() / 2);  // moins de clients -> baisse de revenue
                                warning++;
                                break;
                            case 3:
                                System.out.println("Le gouvernement vient d'annoncer un reconfinement, vos hôtels doivent fermer");
                                players.get(i).getAvenues().get(j).setRent(0); // plus de loyer
                                warning=1;
                                break;
                        }
                    }
                }
            }
        }
        
            
        
        return warning;
    }
    
    public void openHotel(ArrayList <Player> players){
        for(int i=0;i<players.size();i++){
            if(!players.get(i).getAvenues().isEmpty()){
                for(int j=0;j<players.get(i).getAvenues().size();j++){
                    if(players.get(i).getAvenues().get(j).getHotel()>0){
                        players.get(i).getAvenues().get(j).setRent(players.get(i).getAvenues().get(j).computing(players.get(i).getAvenues().get(j), players.get(i)));
                        
                    }
                }
            }
        }
    }
 
    
}
