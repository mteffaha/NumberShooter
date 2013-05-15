package org.unice.polytech.si3.devint.teffaha.numbershooter.renderer;


import org.newdawn.slick.*;
import org.unice.polytech.si3.devint.teffaha.numbershooter.core.Config;
import org.unice.polytech.si3.devint.teffaha.numbershooter.renderer.input.InputManager;

/**
 * @author TEFFAHA Mortadha
 */
public class MainRenderer extends BasicGame {
    private RenderState renderState;
    public static int score = 0;

    public MainRenderer()
    {
        super((String)Config.getParameterByName("applicationtitle"));


    }

    @Override
    public void init(GameContainer gc)
            throws SlickException {
        RessourceManager.init();
        renderState =  new LevelState();

    }

    @Override
    public void update(GameContainer gc, int delta)
            throws SlickException
    {
        InputManager.update(gc.getInput());


        if(renderState != null){
            renderState.updateLogic();
            renderState.updateGraphics();

            this.renderState = renderState.updateState();
        }

        if(InputManager.isExit()){
            System.exit(0);
        }


    }

    public void render(GameContainer gc, Graphics g) throws SlickException{
          if(renderState != null){
              renderState.render(gc,g);
          }
    }







    public static void Start() throws SlickException {
        AppGameContainer app =
                new AppGameContainer(new MainRenderer());

        app.setDisplayMode((Short)Config.getParameterByName("windowwidth"), (Short)Config.getParameterByName("windowheight"), (Boolean)Config.getParameterByName("isrendererfullscreen"));
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
