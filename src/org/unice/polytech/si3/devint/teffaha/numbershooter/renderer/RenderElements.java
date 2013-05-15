package org.unice.polytech.si3.devint.teffaha.numbershooter.renderer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Created with IntelliJ IDEA.
 * User: teffaha
 * Date: 12/05/13
 * Time: 19:52
 * To change this template use File | Settings | File Templates.
 */
public interface RenderElements {
    void update();
    void render(GameContainer gc, Graphics g);
    boolean remove();   }
