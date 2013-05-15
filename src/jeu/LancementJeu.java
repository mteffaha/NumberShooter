package jeu;

import ihm.FrameEndGame;
import gameEngine.Adapter;
import gameEngine.Renderer2;
import gameEngine.Score;

import org.newdawn.slick.SlickException;

/** classe pour lancer le jeu
 * Elle cr�� simplement une instance de MenuJeu
 * 
 * @author helene
 *
 */
public class LancementJeu{

    public static void main(String args[]) throws SlickException{
    	//Sound.TIR_LASER.play();
        new jeu.MenuJeu("Number Shooter");

    	
    	
    }

}
