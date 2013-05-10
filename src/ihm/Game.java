package ihm;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import calculEngine.Equation;

public class Game extends BasicGame{
	Image background = null;
	Image laser = null;
	Image projectile = null;
	Equation eq ;
	int niveau;

	public Game()
	{
		super("Mathefique");
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		background = new Image("../ressources/images/bg.jpg");
		laser = new Image("../ressources/images/preLaser.png");
		projectile = new Image("../ressources/images/projectile.png");
		niveau = 1;
		eq = new Equation(niveau);
		

	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();
		float angle,speed=0.1f;
        if(input.isKeyDown(Input.KEY_F))
        {
        	angle = -speed * delta;
            laser.rotate(angle);
            projectile.rotate(angle);
        }
  
        if(input.isKeyDown(Input.KEY_J))
        {
        	angle = speed * delta;
            laser.rotate(angle);
            projectile.rotate(angle);
        }
        if(input.isKeyDown(Input.KEY_SPACE)){
        	shot();
        }
        
        if(input.isKeyPressed(Input.KEY_ESCAPE)) {
            gc.exit();
         }
  
	}

	private void shot() throws SlickException{
		/*int value = getMeteorShotNumber();
		if(value != -1){
			eq.setBlank(value);
			if(eq.checkSolution()){
				eq = new Equation(++niveau);
			}
			else{
				eq.displayResolutionSteps();
			}
		}*/
	}

	private int getMeteorShotNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void render(GameContainer gc, Graphics g) throws SlickException{
		background.draw(0,0);
		laser.draw(350, 480, 1.0f);
		projectile.draw(900,900);
	}

	public static void main(String[] args) throws SlickException
	{
		AppGameContainer app = new AppGameContainer(new Game());

		app.setDisplayMode(800, 600, false);
		app.start();
	}
}