package gameEngine;

import ihm.FrameEndGame;
import ihm.MeteorIHM;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import t2s.SIVOXDevint;

import org.unice.polytech.si3.devint.teffaha.numbershooter.renderer.Sound;
import calculEngine.EquationBenjamin;


/**
 * User: mteffaha
 * Date: 3/15/13
 * Time: 3:18 PM
 *
 * the actual render of the game whose responsibility is to display a the current level.
 * all communication with this engine need to be made through the adapter class
 */

public class Renderer2 extends BasicGame {

	private static final float SCREEN_WIDTH = (float)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private static final float SCREEN_HEIGHT = (float)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight()-100;

	private static final int TAILLE_POLICE_EQUATION = 150;

	private static final int DELAY_MESSAGE=50;

	private Score score;

	private boolean displayWinMessage = true;
	private int delayMessage = DELAY_MESSAGE;
	private float widthMetorite;
	private float heightMetorite;
	private float meteorSpeed = 0.9f;

	private float laserAngle = 90.0f;
	private Point laserStartPosition;
	private Point laserEndPosition;

	private EquationBenjamin currentEquation;
	private List<MeteorIHM> propositions;

	private boolean isShooting = false;

	private Point startRenderWindow;
	private Point endRenderWindow;

	private Point startEquationDisplay;
	private Point endEquationDisplay;

	private Image cannonImg;
	private Image meteorImage;
	private Image tirImage;

	private UnicodeFont equationFont;
	private UnicodeFont meteorFont;
	private UnicodeFont scoreFont;
	private UnicodeFont endFont;

	private boolean finish = false;
	private boolean displayMeteor = true;
	private boolean started = false;
	private int yMinStart = 0;
	private boolean alreadyBad = false;
	private boolean endGameNotDisplay;
	private static final int positionYBadReponse = 75;

	private static Renderer2 instance;

	public static Renderer2 getInstance(){
		if(instance == null)
			Renderer2.instance =  new Renderer2();
		return Renderer2.instance;
	}


	private Renderer2(){
		super("Mathefique");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void init(GameContainer gc) throws SlickException{
		cannonImg = new Image("ressources/images/preLaser.png");
		meteorImage = new Image("ressources/images/meteor2.png");
		tirImage = new Image("ressources/images/projectile.png");

		widthMetorite= meteorImage.getWidth();
		heightMetorite = meteorImage.getHeight();
		displayWinMessage = false;
		finish = false;
		this.endGameNotDisplay = true;

		score = new Score();

		equationFont =  new UnicodeFont("ressources/fonts/bebas.ttf", TAILLE_POLICE_EQUATION, false, false);

		equationFont.addAsciiGlyphs();
		equationFont.getEffects().add(new ColorEffect(java.awt.Color.BLACK));
		equationFont.loadGlyphs();

		scoreFont =  new UnicodeFont("ressources/fonts/bebas.ttf", 100, false, false);

		scoreFont.addAsciiGlyphs();
		scoreFont.getEffects().add(new ColorEffect(java.awt.Color.red));
		scoreFont.loadGlyphs();

		meteorFont =  new UnicodeFont("ressources/fonts/bignoodle.ttf", 110, false, false);

		meteorFont.addAsciiGlyphs();
		meteorFont.getEffects().add(new ColorEffect(java.awt.Color.white));
		meteorFont.loadGlyphs();

		endFont =  new UnicodeFont("ressources/fonts/bebas.ttf", TAILLE_POLICE_EQUATION, false, false);
		endFont.addAsciiGlyphs();
		endFont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
		endFont.loadGlyphs();

		// Main window takes 70% height and 70 % width;
		startRenderWindow =  new Point(0,0);
		endRenderWindow =  new Point((int)(SCREEN_HEIGHT),(int)(SCREEN_WIDTH * 0.65f));

		// Equation takes 30% height and 70% width
		startEquationDisplay =  new Point(0,(int)SCREEN_WIDTH * 0.65f);
		endEquationDisplay =  new Point(SCREEN_HEIGHT,SCREEN_WIDTH);

		initLevel(score.getLevel());


		// init Laser
		float heightRenderWindow = endRenderWindow.getY() - startRenderWindow.getY();
		float widthRenderWindow  = endRenderWindow.getX() - startRenderWindow.getX();
		laserStartPosition =  new Point(heightRenderWindow-60,((widthRenderWindow-50)/2));
		laserEndPosition=  new Point(heightRenderWindow,laserStartPosition.getX()+50);
		cannonImg.setRotation(laserAngle);

	}

	private void initLevel(int level){
		alreadyBad = false;
		float spaceBetweenMeteor = (endRenderWindow.getX() - startRenderWindow.getX()) / 5 +30;
		currentEquation = Adapter.getInstance().getEquation(level);
		List<Integer> propsInt = Adapter.getInstance().getProposition(currentEquation);

		isShooting = false;
		
		SIVOXDevint voix = new SIVOXDevint();
		
		voix.playText(currentEquation.toString());
		
		int xTranslation = 50;

		int yEcart  = 30;
		propositions = new ArrayList<MeteorIHM>();

		for(Integer i : propsInt){
			float xCoord = xTranslation;
			float yCoord = yMinStart - new Random().nextInt(yEcart);

			propositions.add(new MeteorIHM(i,yCoord,xCoord));
			xTranslation += spaceBetweenMeteor;
		}
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException{

		if(started){


			if(!finish){

				if(displayWinMessage){
					if(delayMessage > 0){
						delayMessage--;
					}else{
						delayMessage = DELAY_MESSAGE;
						displayWinMessage = false;
						System.out.println("update niveau :"+score.getLevel());
						initLevel(score.getLevel());	
					}
					return;
				}

				// Check Level

				// Render Lazer Beam
				if(isShooting){
					Projectile pr =  new Projectile(new Point(laserStartPosition.getY()+cannonImg.getCenterOfRotationY(),laserStartPosition.getX()+cannonImg.getCenterOfRotationX()),(float)Math.toRadians(laserAngle));
					float y = pr.getCenter().getY();
					while(y > 0){
						float x = pr.getNewX(y);
						for(int i=0;i<propositions.size();i++){
							Point met = propositions.get(i).getCentre();
							if(x >= met.getX() && x <= met.getX()+(widthMetorite)){
								if(y >= met.getY() && y <= met.getY()+(heightMetorite)){
									if(propositions.get(i).getValue() == currentEquation.getSolution()){
										displayWinMessage = true;
										score.increaseScore();
										//des que la meteorite est touche la premiere fois on quitte la boucle
										return;
									}
									else{
										score.stopBonus();
									}
									if(!propositions.get(i).isDestroy()) {
										Sound.EXPLOSION.play();
										propositions.get(i).setDestroy();
										// update static data
										if(!alreadyBad){
											yMinStart += positionYBadReponse;
											alreadyBad =true;
										}
									}
								}
							}
						}
						y--;
					}

				}

				// update static data
				for(MeteorIHM m : this.propositions){
					m.descente(meteorSpeed);
					if(m.touchGround(endRenderWindow.getY())){	
						this.finish = true;
					}
				}

				// Read input
				Input input = gc.getInput();

				if(input.isKeyDown(Input.KEY_RIGHT)){
					if(!isShooting){
						laserAngle++;
						if(laserAngle >= 180){
							laserAngle = 180;
						}
					}
					cannonImg.setRotation(laserAngle);
				}

				if(input.isKeyPressed(Input.KEY_M)){
					displayMeteor = !displayMeteor;
				}


				if(input.isKeyDown(Input.KEY_LEFT))
				{
					if(!isShooting){
						laserAngle--;
						if(laserAngle <= 0){
							laserAngle = 0;
						}
					}
					cannonImg.setRotation(laserAngle);
				}

				if(input.isKeyDown(Input.KEY_SPACE) || input.isKeyDown(Input.KEY_ENTER))
				{
					isShooting = true;
					Sound.TIR_LASER.play();
				}else{
					isShooting = false;
				}
			}
			// Process Logic
		}
		else{
			Input input = gc.getInput();
			
			if(input.isKeyDown(Input.KEY_SPACE)){
				started = true;
			}
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException{

		
		
		//    public void fillRect(float x, float y, float width, float height, org.newdawn.slick.Image pattern, float offX, float offY)
		// Clearing Render Space
		g.setColor(Color.black);
		g.fillRect(startRenderWindow.getX(),startRenderWindow.getY(),endRenderWindow.getX() - startRenderWindow.getX(),endRenderWindow.getY()-startRenderWindow.getY());

		//Viseur
		Projectile pr =  new Projectile(new Point(laserStartPosition.getY()+cannonImg.getCenterOfRotationY(),laserStartPosition.getX()+cannonImg.getCenterOfRotationX()),(float)Math.toRadians(laserAngle));
		float y = pr.getCenter().getY();
		g.setColor(Color.pink);
		while(y > 0){
			g.fillRect(pr.getNewX(y)-5, y-5, 10, 10);
			y--;
		}

		// Render Lazer Beam
		if(isShooting){
			float y2 = pr.getCenter().getY();
			g.setColor(Color.green);
			while(y2 > 0){
				tirImage.setRotation(laserAngle);
				tirImage.draw(pr.getNewX(y2)-10, y2-10, 20, 20);
				y2--;
			}
		}

		// Render Laser Gun
		cannonImg.draw(laserStartPosition.getX(),laserStartPosition.getY());

		// FIlling Eqution Space
		g.setColor(Color.white);
		//afiche centre rotation laser
		//g.fillOval(laserStartPosition.getX()+cannonImg.getCenterOfRotationX(),laserStartPosition.getY()+cannonImg.getCenterOfRotationY(),10,10);
		g.fillRect(startEquationDisplay.getX(),startEquationDisplay.getY(),endEquationDisplay.getX()-startEquationDisplay.getX(),endEquationDisplay.getY()-startEquationDisplay.getY());
		//	g.fillRect(startInfoDisplay.getX(),startInfoDisplay.getY(),endInfoDisplay.getX()-startInfoDisplay.getX(),endInfoDisplay.getY()-startInfoDisplay.getY());

		// Render Equation
		g.setColor(Color.black);
		g.setFont(equationFont);
		//g.drawString(currentEquation.toString(),startEquationDisplay.getX()+20,20);
		currentEquation.display(equationFont,(int)startEquationDisplay.getX()+30 , (int)200, TAILLE_POLICE_EQUATION);
		scoreFont.drawString((int)startEquationDisplay.getX()+30 , 20,score.toString());

		// Render Meteors
		for(MeteorIHM met : propositions){
			if(!met.isDestroy()){
				g.setColor(Color.red);
				g.setColor(Color.white);
				g.setFont(meteorFont);
				if(displayMeteor) met.displayImage(meteorFont);
				else met.displayCircle(meteorFont, g);
			}
		}

		if(displayWinMessage){
			float renderWidth = endRenderWindow.getX() - startRenderWindow.getX();
			float renderHeight = endRenderWindow.getY() - startRenderWindow.getY();

			float xs = startRenderWindow.getX() + (100);
			float ys = startRenderWindow.getY() + (renderHeight/2)-100;
			g.setColor(Color.white);
			g.setFont(equationFont);
			g.fillRect(xs,ys,650,250);

			System.out.println("render niveau :"+ score.getLevel());
			g.drawString("Bravo!!!",xs+20,ys+20);
		}

		if(finish){

			if(endGameNotDisplay){
				this.endGameNotDisplay = false;
				FrameEndGame endGame = new FrameEndGame(this.score);
				endGame.setVisible(true);
				gc.exit();
			}
		}

		
	}

	public void startRender(){
		try{
			AppGameContainer app = new AppGameContainer(new Renderer2());
			app.setTargetFrameRate(30);
			app.setDisplayMode((int)SCREEN_WIDTH, (int)SCREEN_HEIGHT, false);
			app.setForceExit(false);
			app.start();


		}catch(Exception e){
			e.printStackTrace();
		}
	}
}