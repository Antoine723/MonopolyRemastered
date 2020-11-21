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
public final class Prison extends Case {
    
    private Player player=null;

    public Prison(String name, int caseNumber){
        this.setName(name);
        this.setCaseNumber(caseNumber);
        
    }
    public Player getPlayerName() {
        return player;
    }

    public void setPlayerName(Player player) {
        this.player = player;
    }
    
}
