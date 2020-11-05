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
public class Strike extends Event {
    Random rand = new Random();
    private boolean inAction=false;
    public Strike(){
        this.setName("Strike");
    }
    public boolean isInAction() {
        return inAction;
    }

    public void setInAction(boolean inAction) {
        this.inAction = inAction;
    }
    public void closeRailRoad(ArrayList <Case> board)              
    {
        
        int strike =rand.nextInt(6);
        System.out.println("Quel dommage ! Il fait si beau aujourd'hui que la SNCF a décidé de faire grève pendant " + strike + " jours");
        for(int i=0;i<board.size();i++){
            if(board.get(i) instanceof RailRoad){
                RailRoad railRoad=(RailRoad) (board.get(i));
                railRoad.setRent(0);
                
            }
        }
    }
}
