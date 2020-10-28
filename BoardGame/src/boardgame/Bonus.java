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
public class Bonus extends Case{
    
    public Bonus(String caseName, int numCase){
        this.setCaseNumber(numCase);
        this.setName(caseName);
    }
    
}
