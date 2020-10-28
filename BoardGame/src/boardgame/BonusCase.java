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
public class BonusCase extends Case {

    private String cardName;
    private String effect;
    private boolean kept;
    
    /*public BonusCase(String name, int caseNumber){
        this.setName(name);
        this.setCaseNumber(caseNumber);
        
    }*/
    public String getCardName() {
        return cardName;
    }

    public String getEffect() {
        return effect;
    }

    public boolean isKept() {
        return kept;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public void setKept(boolean kept) {
        this.kept = kept;
    }
    
    
    

}
