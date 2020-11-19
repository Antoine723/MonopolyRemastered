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
public class Car extends Player implements Citizen{
    
    public Car(String name,int playerNumber){
        this.setName(name);
        this.setPlayerNumber(playerNumber);
    }
    
    Scanner case_choice_scanner = new Scanner(System.in);
    
    
    public void moveTo(ArrayList <Case> board){
        boolean isChoiceCorrect=false;
        String case_choice_name;
        boolean correct_move=false;
        Case case_choice=null;
        System.out.println("Choisissez la case sur laquelle vous souhaitez aller");
        do{
            case_choice_name=case_choice_scanner.nextLine();
            for(int i=0;i<board.size();i++){
                if(board.get(i).getName().equals(case_choice_name)) case_choice=board.get(i);
            }
            if(case_choice==this.getPlayer_case())System.out.println("Veuillez saisir une autre case que celle sur laquelle vous êtes actuellement");
            else correct_move=true;
        }while(!correct_move);
        
        this.setPlayer_case(case_choice);
    }
    
    @Override
    public void doubleRent(Avenue avenue)
    {
        if(avenue.getColor().ORANGE.equals(avenue))                                   // SI COULEUR CORRESPOND AU BONUS
        {
            avenue.setRent(avenue.getRent() * 2);                               // ON DOUBLE
        }
        else
        {
            avenue.setRent(avenue.getRent());                                   // ON NE CHANGE RIEN
        }
    }
    
}
