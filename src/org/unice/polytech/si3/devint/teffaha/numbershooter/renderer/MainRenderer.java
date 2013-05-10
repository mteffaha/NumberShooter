package org.unice.polytech.si3.devint.teffaha.numbershooter.renderer;

import org.newdawn.slick.*;
import org.unice.polytech.si3.devint.teffaha.numbershooter.core.Config;

/**
 * @author TEFFAHA Mortadha
 */
public class MainRenderer extends BasicGame {

    public MainRenderer()
    {
        super("Slick2DPath2Glory - SimpleGame");
    }

    @Override
    public void init(GameContainer gc)
            throws SlickException {

    }

    @Override
    public void update(GameContainer gc, int delta)
            throws SlickException
    {

    }

    public void render(GameContainer gc, Graphics g)
            throws SlickException
    {

    }

    public static void Start() throws SlickException {
        AppGameContainer app =
                new AppGameContainer(new MainRenderer());

        app.setDisplayMode((Short)Config.getParameterByName("windowwidth"), (Short)Config.getParameterByName("windowheight"), false);
        app.start();
    }

    public static void main(String[] args){
        System.out.println("Running");
        try {

            MainRenderer.Start();
        } catch (SlickException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
