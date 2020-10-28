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
public class Prison extends Case {
    
    private String playerName=null;

    public Prison(String name, int caseNumber){
        this.setName(name);
        this.setCaseNumber(caseNumber);
        
    }
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    
    
    
}
