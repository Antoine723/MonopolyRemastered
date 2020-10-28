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
public class Case {
    private String name;
    private int caseNumber;
    
    public Case(String name, int caseNumber){ //Constructeur de cases pour des cases telles que "Départ" ou "Parc gratuit" qui ne font rien de particulier (l'argent touché en passant par la case départ se fait en dehors)
        this.name=name;
        this.caseNumber=caseNumber;
    }
    
    public Case(){ //Constructeur vide pour les classes filles qui disposent de leur propre constructeur
        
    }
    public String getName() {
        return name;
    }

    public int getCaseNumber() {
        return caseNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCaseNumber(int caseNumber) {
        this.caseNumber = caseNumber;
    }
    
    
}
