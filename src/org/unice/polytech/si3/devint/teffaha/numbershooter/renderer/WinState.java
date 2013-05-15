package org.unice.polytech.si3.devint.teffaha.numbershooter.renderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.unice.polytech.si3.devint.teffaha.numbershooter.core.Config;
import org.unice.polytech.si3.devint.teffaha.numbershooter.equation.Equation;

/**
 * Created with IntelliJ IDEA.
 * User: teffaha
 * Date: 13/05/ e13
 * Time: 04:04
 * To change this template use File | Settings | File Templates.
 */
public class WinState extends RenderState {
    private Equation equation;
    private LevelState previousState;
    private int counter = 550;

    public WinState(LevelState previousLevel){
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
        int xStart= (Short) Config.getParameterByName("windowwidth");
        int yStart = (Short) Config.getParameterByName("windowheight");
        xStart = (xStart - 900)/2;
        yStart = (yStart - 500)/2;
        g.setColor(Color.white);

        g.fillRoundRect(xStart,yStart,900,500,50);
        g.setFont(RessourceManager.getFont("wellcome"));
        //RessourceManager.getSynthtiser().playText("Bravo Niveau "+getLevel()+" termine");
        g.setColor(Color.red);
        g.drawString("Niveau"+getLevel(),xStart+150,yStart+150);
        g.setColor(Color.black);
        g.drawString("Termine",xStart+150,yStart+290);
    }

    @Override
    public RenderState updateState() {
        if(counter > 0){
        return this;  //To change body of implemented methods use File | Settings | File Templates.
        }else{
            MainRenderer.score+=5;
            incrementLevel();
            return new LevelState();
        }
    }
}
