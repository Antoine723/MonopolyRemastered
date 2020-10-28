/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgame;

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
    
    Scanner scanner = new Scanner(System.in);
    int choice=this.getNumberCase();
    
    public void moveTo(){
        boolean isChoiceCorrect=false;
        System.out.println("Choisissez la case sur laquelle vous souhaitez aller");
        
        while(choice==this.getNumberCase()){
            choice= scanner.nextInt();
            if(choice==this.getNumberCase()) System.out.println("Veuillez saisir une autre case que celle sur laquelle vous êtes actuellement");
        }
        this.setNumberCase(choice);
    }
    
    public void doubleRent(){
        
        
    }
    
}
