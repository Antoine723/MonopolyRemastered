/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgame;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author Antoine
 */

/**
 * La classe Strike permet d'implémenter l'évènement Earthquake
 * <br>
 * <br>
 * Cette classe est caractérisée par les informations suivantes :
 * <br>
 * <br>
 * Le boolean done permet de vérifier si l'évènement a eu lieu ou non
 * <br>
 * <br>
 * Le random rand qui rend aléatoire la magnitude du séisme compris entre 1 et 9
 * On retrouve la fonction permettant de détruire les maisons
 * On retrouve aussi les fonctions permettant de récupérer le booléen done
 * @author thibb
 */
public final class Earthquake extends Event {
    Random rand=new Random();
    
    
     /**
     * <b> Constructeur de Earthquake </b>
     * <br>
     * On attribut un nom à l'évènement
     */
    public Earthquake(){
        this.setName("Earthquake");
    }
    private boolean done=false;
    
    
   /**
     * Cette méthode permet de savoir si l'évènement a eu lieu ou non
     * @return  Cette méthode retourne le paramètre done
     */
    public boolean isDone() {
        return done;
    }
    
    /**
     * Cette méthode permet de détruire plus ou moins de maisons selon la magnitude du séisme
     * @param players
     *      Le paramètre correspond à la liste des joueurs
     */
    public void collapse(ArrayList <Player> players)            // gérer la notion de tour
    {
        boolean hasEffect=false;
        int magnitude = rand.nextInt(9)+1;
        for(int i=0;i<players.size();i++){
            if(!players.get(i).getAvenues().isEmpty()){
                for(int j=0;j<players.get(i).getAvenues().size();j++){
                    if(players.get(i).getAvenues().get(j).getHouse()>0){
                        
                        if (magnitude == 9)
                        {
                            players.get(i).getAvenues().get(j).setHouse(0);                // toutes les maisons sont détruites
                            hasEffect=true;
                        }
                        else if (magnitude >= 4 && magnitude < 9)
                        {
                            players.get(i).getAvenues().get(j).setHouse(players.get(i).getAvenues().get(j).getHouse() > 1 ? players.get(i).getAvenues().get(j).getHouse() - 2 : 0);     // si on a plus d'une maison on peut retirer deux maisons sinon on en retire 1, il n'y a donc plus de maisons
                            hasEffect=true;
                        }
                        else
                        {
                            players.get(i).getAvenues().get(j).setHouse(players.get(i).getAvenues().get(j).getHouse() - 1);
                            hasEffect=true;
                        }
                    }
                }
            }
        }
        if(!hasEffect) System.out.println("Il y a eu un séisme, mais heureusement il n'y avait aucune maison. Il n'a donc fait aucun dégât");
        else{
            if(magnitude==9) System.out.println("Quelle catastrophe, un séisme de magnitude " + magnitude + " a frappé le territoire");
            else if(magnitude >= 4 && magnitude < 9) System.out.println("Quelle catastrophe, un séisme de magnitude " + magnitude + " a frappé le territoire");
            else System.out.println("Quelle catastrophe, un séisme de magnitude " + magnitude + " a frappé le territoire");

        }
        this.done=true;
    }

    
}
