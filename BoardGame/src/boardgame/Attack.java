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
public class Attack {
    
    private String name;
    private String effect;
    private boolean isUsed=false;
    private String associatedPlayer=null;

    public String getName() {
        return name;
    }

    public String getEffect() {
        return effect;
    }

    public boolean isItUsed() {
        return isUsed;
    }

    public String getAssociatedPlayer() {
        return associatedPlayer;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public void setAssociatedPlayer(String associatedPlayer) {
        this.associatedPlayer = associatedPlayer;
    }
    
    
    
    
}
