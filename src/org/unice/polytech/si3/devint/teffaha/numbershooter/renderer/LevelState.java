package org.unice.polytech.si3.devint.teffaha.numbershooter.renderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.unice.polytech.si3.devint.teffaha.numbershooter.core.Config;
import org.unice.polytech.si3.devint.teffaha.numbershooter.equation.Equation;
import org.unice.polytech.si3.devint.teffaha.numbershooter.equation.EquationFactory;
import org.unice.polytech.si3.devint.teffaha.numbershooter.renderer.elements.Enemy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: teffaha
 * Date: 12/05/13
 * Time: 19:36
 * To change this template use File | Settings | File Templates.
 */
public class LevelState extends RenderState {

    private static final int WIDTH_ELEMENT = 300;
    private static final int MARGIN = 25;
    List<Enemy> enemies;
    public boolean updateLogic = true;
    private Equation equation;
    private Enemy dmnE;
    private ship character;
    private boolean finished = false;
    private boolean won = false;

    public LevelState(){

        enemies =  new ArrayList<Enemy>();
        equation = EquationFactory.getInstance().getEquation(getLevel());
        int width = (Short)Config.getParameterByName("windowwidth");
        int height = (Short)Config.getParameterByName("windowheight");
        character = new ship((width-150)/2 ,height-200);
        int nbProps = ((width/(WIDTH_ELEMENT+(2*MARGIN))));

        List<Integer> props = EquationFactory.getInstance().getProposition(equation.getRight(),nbProps);

        System.out.println("Right :::::::::::::::::::::: "+EquationFactory.listToSTring(props));
        int start =(width%(WIDTH_ELEMENT+(2*MARGIN)))/2;

        for(Iterator<Integer> it= props.iterator();it.hasNext();){
            Integer prop = it.next();
            enemies.add(new Enemy(prop,start+MARGIN,100));
            start+=WIDTH_ELEMENT+(2*MARGIN);
            System.out.println("Prop :"+prop+" Nb:"+enemies.get(enemies.size()-1).getNumber());
        }
        dmnE =  new Enemy(start+MARGIN,950,10);
        enemies.add(dmnE);
        System.out.println("props"+props.size()+"  Start="+start);
    }

    @Override
    protected void updateLogic() {
        character.updateLogic();

        int target = (Short)Config.getParameterByName("windowheight")-400;
        if(enemies.get(0).getyPosition() >= target){
            finished = true;
            won = false;
        }
        for(int i=0;i<enemies.size();i++){
            Enemy enm = enemies.get(i);
            if(character.doesCollide(enm)){
                enm.setExplode();
                if(this.equation.isValidResponse(enm.getNumber())){
                    System.out.println("Equation"+this.equation+" Right : "+equation.getRight()+" Nb:"+enm.getNumber());
                    finished = true;
                    won = true;
                }else{
                    finished = false;
                    won = false;
                }
            }
        }
        for(Iterator<Enemy> it= enemies.iterator();it.hasNext();){
            it.next().update();
        }
    }

    @Override
    protected void updateGraphics() {


    }

    @Override
    public void render(GameContainer gc, Graphics g) {

        // Clear Background
        int width = (Short)Config.getParameterByName("windowwidth");
        int height = (Short)Config.getParameterByName("windowheight");
        Image img = RessourceManager.getGraphics("background");



        g.drawImage(RessourceManager.getGraphics("background"),(width-img.getWidth())/2,0);
        character.render(gc,g);
        for(Iterator<Enemy> it= enemies.iterator();it.hasNext();){
            it.next().render(gc,g);
        }



        g.setColor(Color.white);
        g.fillRoundRect(10, 5, (float) (width * 0.95), 100, 10);

        g.setFont(RessourceManager.getFont("information"));
        g.setColor(Color.red);
        g.drawString("Score :"+MainRenderer.score,(float)(width*0.70),15);
        g.setColor(Color.black);
        g.drawString("Equation :"+equation,40,15);
        dmnE.render(gc,g);
    }

    @Override
    public RenderState updateState() {
        if(finished){
            if(won){
                won = false;
                finished = false;
                return new DoneState(this);
            }else{
                won = false;
                finished = false;
                return new LooseState(this);

            }
        }
        return this;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Equation getEquation() {
        return equation;
    }
}
