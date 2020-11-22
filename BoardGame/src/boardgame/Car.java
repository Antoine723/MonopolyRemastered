/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgame;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Antoine
 */

/**
 * La classe Car correspond au pouvoir Car permettant de se déplacer sur la case choisie par l'utilisateur
 * <br>
 * <br>
 * Cette classe est caractérisée par les informations suivantes :
 * <br>
 * <br>
 * On retrouve dans cette classe les fonctions permettant de se déplacer sur la case souhaitée et de bénéficier du bonus de couleur d'avenue
 * @author thibb
 */
public final class Car extends Player implements Citizen{
    
    
    /**
     * <b> Constructeur de Car </b>
     * <br>
     * @param name
     *       Le paramètre indique le nom du joueur qui a choisi le pouvoir Car
     * @param playerNumber 
     *       Le paramètre indique le numéro du joueur qui a choisi le pouvoir Car
     */
    public Car(String name,int playerNumber){
        this.setName(name);
        this.setPlayerNumber(playerNumber);
    }
    
    Scanner caseChoiceScanner = new Scanner(System.in);
    
    
    /**
     * Cette méthode permet de se déplacer sur la case sélectionnée
     * @param board
     *      Le paramètre correspond au plateau de jeu pour pouvoir se déplacer sur la case indiquée
     */
    public void moveTo(ArrayList <Case> board){
        boolean isChoiceCorrect=false;
        String caseChoiceName;
        boolean correctMove=false;
        Case caseChoice=null;
        System.out.println("Choisissez la case sur laquelle vous souhaitez aller");
        do{
            caseChoiceName=caseChoiceScanner.nextLine();
            for(int i=0;i<board.size();i++){
                if(board.get(i).getName().equals(caseChoiceName)) caseChoice=board.get(i);
            }
            if(caseChoice==this.getPlayerCase())System.out.println("Veuillez saisir une autre case que celle sur laquelle vous êtes actuellement");
            else correctMove=true;
        }while(!correctMove);
        
        this.setPlayerCase(caseChoice);
    }
    
    
    /**
     Cette méthode permet de doubler le loyer des avenues dont la couleur correspond au bonus du pouvoir
     * @param avenue
     *      Le paramètre permet de doubler le loyer de l'avenue si celle-ci est d'une couleur identique à celle du bonus
     */
    @Override
    public void doubleRent(Avenue avenue)
    {
        if(avenue.getColor().equals(ColorAvenue.ORANGE))                                   // SI COULEUR CORRESPOND AU BONUS
        {
            avenue.setRent(avenue.getRent() * 2);                               // ON DOUBLE
        }
        else
        {
            avenue.setRent(avenue.getRent());                                   // ON NE CHANGE RIEN
        }
    }
    
}
