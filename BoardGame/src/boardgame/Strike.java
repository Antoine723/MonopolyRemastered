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
 * La classe Strike permet d'implémenter l'évènement Strike
 * <br>
 * <br>
 * Cette classe est caractérisée par les informations suivantes :
 * <br>
 * <br>
 * Le boolean inAction permet de vérifier si l'évènement est toujours en cours
 * <br>
 * Le random rand qui génère un nombre aléatoire entre 1 et 6 pour savoir pendant combien de tours va durer l'évènement Srike
 * <br>
 * On retrouve les fonctions permettant de fermer et ouvrir l'ensemble des gares
 * <br>
 * On retrouve aussi les fonctions permettant de récupérer le booléen inAction et de le modifier
 * @author thibb
 */
public final class Strike extends Event {
    
    Random rand = new Random();
    private boolean inAction=false;
    
    
    /**
     * <b> Constructeur de Strike </b>
     * <br>
     * On attribut un nom à l'évènement
     */
    public Strike(){
        this.setName("Strike");
    }
    
    /**
     * Cette méthode permet de savoir si l'évènement est toujours en cours
     * @return  Cette méthode retourne le paramètre inAction
     */
    public boolean isInAction() {
        return inAction;
    }

    /**
     * Cette méthode permet de modifier le paramètre inAction
     * @param inAction
     *      Ce paramètre permet d'indiquer si l'évènement Strike est toujours en cours
     */
    public void setInAction(boolean inAction) {
        this.inAction = inAction;
    }
    
    /**
     * Cette méthode permet de fermer l'ensemble des gares en modifiant leurs loyers à 0
     * @param board
     *      Le paramètre correspond au plateau de jeu
     * @param activationTurn
     *      Le paramètre correspond au tour pendant lequel l'évènement a eu lieu
     * @return  Cette méthode retourne le nombre de tours pendant lequel l'évènement se produit
     */
    public int closeRailRoad(ArrayList <Case> board,int activationTurn)              
    {
        this.inAction=true;
        int strike =rand.nextInt(6)+1;
        System.out.println("Quel dommage ! Il fait si beau aujourd'hui que la SNCF a décidé de faire grève pendant " + strike + " jour(s)");
        for(int i=0;i<board.size();i++){
            if(board.get(i) instanceof RailRoad){
                RailRoad railRoad=(RailRoad) (board.get(i));
                railRoad.setRent(0);
                
            }
        }
        return strike+activationTurn;
    }
    
    /**
     * Cette méthode permet d'ouvrir l'ensemble des gares (loyers remis aux montants précédants)
     * @param board
     *      Le paramètre correspond au plateau de jeu
     */
    public void openRailRoad(ArrayList <Case> board){
        this.inAction=false;
        System.out.println("Il pleut demain, les gares sont de nouveau ouvertes");
        for(int i=0;i<board.size();i++){
            if(board.get(i) instanceof RailRoad){
                RailRoad railRoad=(RailRoad) (board.get(i));
                railRoad.setRent(railRoad.computing(railRoad, railRoad.getAssociatedPlayer()));
                
            }
        }
    }
}
