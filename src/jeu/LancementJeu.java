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
        new jeu.MenuJeu("Mathefique");
    	
        /*
    	Score score = new Score();
    	score.increaseScore();
    	score.increaseScore();

    	FrameEndGame frame = new FrameEndGame(score);
    	frame.setVisible(true);
    	*/
        
    	//Renderer2.getInstance().startRender();
    	
    	//Adapter.getInstance().render();
    	
    	/*
    	Equation eq = new Equation(1);
    	AppGameContainer app = new AppGameContainer(new Calculation(eq));

		app.setDisplayMode(800, 600, false);
		app.start();
    	*/
    	
    	
    }

}
