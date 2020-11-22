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
 * La classe Covid permet d'implémenter l'évènement Covid
 * <br>
 * <br>
 * Cette classe est caractérisée par les informations suivantes :
 * <br>
 * <br>
 * Le boolean inAction permet de vérifier si l'évènement est toujours en cours
 * <br>
 * On retrouve dans cette classe les fonctions permettant de fermer et d'ouvrir les hôtels
 * On retrouve aussi les fonctions permettant de récupérer le booléen inAction et de le modifier
 * @author thibb
 */
public final class Covid extends Event{
    
    private boolean inAction=false;
    
    
    /**
     * <b> Constructeur de Covid </b>
     * <br>
     * On attribut un nom à l'évènement
     * 
     */
    
    public Covid(){
        this.setName("Covid");
    }

    /**
     *Cette méthode permet de savoir si l'évènement est toujours en cours
     * 
     * @return
     *      Cette méthode retourne le paramètre inAction
     */
    public boolean isInAction() {
        return inAction;
    }

    /**
     *
     * @param inAction
     *      Ce paramètre permet d'indiquer si l'évènement Covid est toujours en cours
     */
    public void setInAction(boolean inAction) {
        this.inAction = inAction;
    }
    
    /**
     * Cette méthode permet d'appliquer une hausse du loyer, une baisse ou bien la fermeture de l'hôtel selon le niveau d'alerte
     * @param players
     *        Le paramètre correspond à une liste de joueurs pour ainsi accéder à l'ensemble de leurs propriétés
     * @param warning
     *        Le paramètre correspond à un entier indiquant le niveau d'alerte du covid, cet entier varie entre 1 et 3
     * @return   La méthode retourne le niveau d'alerte warning
     * 
     */
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
    
    /**
     * Cette méthode permet d'ouvrir tous les hôtels une fois que l'évènement Covid est terminé
     * @param players
     *     Le paramètre correspond à une liste de joueurs pour ainsi accéder à l'ensemble de leurs propriétés
     */
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
