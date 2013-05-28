package org.unice.polytech.si3.devint.teffaha.numbershooter.renderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;
import org.unice.polytech.si3.devint.teffaha.numbershooter.core.Config;
import org.unice.polytech.si3.devint.teffaha.numbershooter.equation.Equation;

/**
 * Created with IntelliJ IDEA.
 * User: teffaha
 * Date: 15/05/13
 * Time: 10:34
 * To change this template use File | Settings | File Templates.
 */
public class DoneState extends RenderState {
    private static final int TOTALCOUNTER = 550;
    private Equation equation;
    private LevelState previousState;
    private int counter = 550;

    private static int count = 3;
    public DoneState(LevelState previousLevel){
        count--;
        equation = previousLevel.getEquation();
        this.previousState = previousLevel;
    }
    @Override
    protected void updateLogic() {
        counter--;
    }

    @Override
    protected void updateGraphics() {
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        previousState.render(gc,g);
        int xStart= MainRenderer.screenWidth;
        int yStart = MainRenderer.screenHeight;
        xStart = (xStart - 900)/2;
        yStart = (yStart - 500)/2;
        g.setColor(Color.white);

        g.fillRoundRect(xStart,yStart,900,500,50);
        g.setColor(Color.black);
        g.setFont(RessourceManager.getFont("wellcome"));
        //RessourceManager.getSynthtiser().playText("Bravo Niveau "+getLevel()+" termine");
        g.drawString("Bravo !!!", xStart + 150, yStart + 10);

        g.drawString("Score: ", xStart + 70, yStart + 170);

        g.setColor(Color.red);
        g.drawString(""+MainRenderer.score, xStart + 500, yStart + 170);
        int amount= (counter*100)/TOTALCOUNTER;
        ShapeFill healthFill = new GradientFill(0, amount / 2, Color.red, amount, amount - 1, Color.orange, true);

        Rectangle rec =  new Rectangle(xStart+150,yStart+380,(500*amount)/100,50);
        g.fill(rec,healthFill);

    }

    @Override
    public RenderState updateState() {
        if(counter > 0){
            return this;  //To change body of implemented methods use File | Settings | File Templates.
        }else{
            if(count <= 0){
                count=3;
            incrementLevel();
            return new WinState(this.previousState);
            }else{

                return new LevelState();
            }
        }
    }
}