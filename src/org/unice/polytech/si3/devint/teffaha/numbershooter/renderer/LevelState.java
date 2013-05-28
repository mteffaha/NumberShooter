package org.unice.polytech.si3.devint.teffaha.numbershooter.renderer;

import org.newdawn.slick.*;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
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
    private static final int TOTALCOUNT=3500;
    private int counter = TOTALCOUNT;

    public LevelState(){

        enemies =  new ArrayList<Enemy>();
        equation = EquationFactory.getInstance().getEquation(getLevel());
        int width = MainRenderer.screenWidth;
        int height = MainRenderer.screenHeight;
        System.out.println("WIDDDDDDDDDDDTH : "+width+" HEEEEEEEEEEEEIGT : "+height);
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
        counter--;
        character.updateLogic();

        int amount= (counter*100)/TOTALCOUNT;
        int cscore = amount*20/100;

        if(cscore == 0){
            MainRenderer.score+=cscore;
            finished = true;
            won = false;
        }



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
        g.drawString("Score :"+MainRenderer.score,(float)(width*0.60),15);
        g.setColor(Color.black);
        g.drawString("Equation :"+equation,40,15);
        dmnE.render(gc,g);
        int amount= (counter*100)/TOTALCOUNT;
        ShapeFill healthFill = new GradientFill(0, amount / 2, Color.red, amount, amount - 1, Color.orange, true);

        RoundedRectangle rec =   new RoundedRectangle(200,height-100,(200*amount)/100,50,10);
        RoundedRectangle border =   new RoundedRectangle(197,height-103,206,56,10);
        Circle circle =  new Circle(125,height-125,70);
        Circle innerCircle =  new Circle(125,height-125,60);



        g.setColor(Color.white);
        g.setLineWidth(6);
        g.fill(circle);

        g.draw(border);
        g.fill(rec,healthFill);
        g.fill(innerCircle,healthFill);

        g.setFont(RessourceManager.getFont("enemy"));
        int cscore = amount*20/100;
        g.drawString(""+cscore,75,height-190);


    }

    @Override
    public RenderState updateState() {

        if(finished){
            if(won){
                won = false;
                finished = false;
                int amount= (counter*100)/TOTALCOUNT;
                int cscore = amount*20/100;
                MainRenderer.score+=  cscore;
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
