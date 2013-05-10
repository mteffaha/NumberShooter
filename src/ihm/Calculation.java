package ihm;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;

import calculEngine.Equation;

public class Calculation{
	public void drawCalculation(GameContainer gc, Graphics g,Equation eq) throws SlickException{
		//background.draw(0, 0);
		
		Input input = gc.getInput();
		
		String numerateur = ""+eq.getNumerateur();
		String symbol = ""+eq.getSymbol();
		String denominateur = ""+eq.getDenominateur();
		String result = ""+eq.getResult();

		g.drawString(numerateur, 20, 100);
		g.drawString(symbol,0,120);
		g.drawString(denominateur, 20, 120);
		g.drawString(result, 20, 140);
	
	}
}