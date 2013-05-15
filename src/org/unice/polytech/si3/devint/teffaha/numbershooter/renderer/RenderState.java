package org.unice.polytech.si3.devint.teffaha.numbershooter.renderer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Created with IntelliJ IDEA.
 * User: teffaha
 * Date: 12/05/13
 * Time: 03:01
 * To change this template use File | Settings | File Templates.
 */
public abstract class RenderState {
    private static int level= 0;

    public void update(){
        updateLogic();
        updateGraphics();
    }
    abstract protected void updateLogic();
    abstract protected void updateGraphics();
    abstract public void render(GameContainer gc, Graphics g);
    abstract public RenderState updateState();

    public int getLevel() {
        return level;
    }

    public void incrementLevel() {
        this.level++;
    }
}
