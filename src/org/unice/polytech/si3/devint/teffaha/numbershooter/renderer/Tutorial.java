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
import t2s.son.JukeBox;

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
public class Tutorial extends LevelState {

    private static final int WIDTH_ELEMENT = 300;
    private static final int MARGIN = 25;
    List<Enemy> enemies;
    public boolean updateLogic = true;
    private Equation equation;
    private Enemy dmnE;
    private ship character;
    private boolean finished = false;
    private boolean won = false;
    private static final int TOTALCOUNT=700;
    private int counter = 0;
    int step = -1;
    private JukeBox shipWellcome;
    private JukeBox enemywellcome;
    private JukeBox equationWellcome;
    private JukeBox barWellcome;
    private JukeBox scoreWellcome;
    private JukeBox pret;
    private JukeBox partez;

    private Animation boss;

    public Tutorial(){

       Image[] bosss = new Image[5];
       for(int i=1;i<=5;i++){
           bosss[i-1] = RessourceManager.getGraphics("boss"+i);
       }
       boss =new Animation(bosss,150);

        shipWellcome = new JukeBox("ressources/sons/ship.wav");
        enemywellcome =  new JukeBox("ressources/sons/enemy.wav");
        equationWellcome = new JukeBox("ressources/sons/equation.wav");
        barWellcome =  new JukeBox("ressources/sons/bar.wav");
        scoreWellcome = new JukeBox("ressources/sons/score.wav");
        pret = new JukeBox("ressources/sons/pret.wav");
        partez = new JukeBox("ressources/sons/partez.wav");

        enemies =  new ArrayList<Enemy>();
        equation = EquationFactory.getInstance().getEquation(getLevel());
        int width = MainRenderer.screenWidth;
        int height = MainRenderer.screenHeight;
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
        if(org.unice.polytech.si3.devint.teffaha.numbershooter.renderer.input.InputManager.isShootPressed() && step < 6){
            counter = -1;
            step = 5;
        }
        counter--;
        if(counter <= 0){
            step++;
            counter = TOTALCOUNT;
            switch (step){
                case 0:
                    shipWellcome.playSound();
                    break;
                case 1:
                    enemywellcome.playSound();
                    break;
                case 2:
                    equationWellcome.playSound();
                    break;
                case 3:
                    barWellcome.playSound();
                    break;
                case 4:
                    scoreWellcome.playSound();
                    break;
                case 6:
                    shipWellcome.stop();
                    enemywellcome.stop();
                    equationWellcome.stop();
                    barWellcome.stop();
                    scoreWellcome.stop();
                    shipWellcome.stop();

                    enemywellcome.stop();

                    equationWellcome.stop();
                    barWellcome.stop();
                    scoreWellcome.stop();
                    pret.playSound();
                    break;
                case 7:
                    partez.playSound();
                    break;

            }
            if(step >= 8){
                finished = true;
            }
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
        int amount= (500*100)/550;
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
        int xs = (width-400)/2;
        int ys = (height-250)/2;
        switch (step){
            case 0:



                // Ship
                g.drawImage(RessourceManager.getGraphics("arrowu"),character.getxPosition(),character.getyPosition()-250);
                break;
            case 1:
                // Enemies
                g.drawImage(RessourceManager.getGraphics("arrowd"),enemies.get(0).getxPosition()+40,enemies.get(0).getyPosition()+250);
                break;
            case 2:
                // Equation
                g.drawImage(RessourceManager.getGraphics("arrowd"),450,100);
                break;
            case 3:
                // Time bar
                g.drawImage(RessourceManager.getGraphics("arrowu"),225,height-350);

                break;
            case 4:
            case 5:
                g.drawImage(RessourceManager.getGraphics("arrowu"),45,height-450);
                g.drawImage(RessourceManager.getGraphics("arrowd"),(float)(width*0.60)+200,100);
                break;
            case 6:
                g.setColor(Color.black);
                g.fillRoundRect(xs-5,ys-5,410,260,10);
                g.setColor(Color.white);
                g.fillRoundRect(xs, ys, 400, 250, 10);
                g.setColor(Color.black);
                g.drawString("Pret !!",xs+50,ys+50);
                break;
            case 7:


                g.setColor(Color.black);
                g.fillRoundRect(xs-5,ys-5,410,260,10);
                g.setColor(Color.white);
                g.fillRoundRect(xs, ys, 400, 250, 10);
                g.setColor(Color.black);
                g.drawString("Partez",xs+50,ys+50);

                break;
            case 8:

                break;


        }



        if(step < 6){

        //g.drawAnimation(boss,200,200);
        g.fillRoundRect(width-650,height-500,650,400,10);
        g.setColor(Color.black);
        g.drawString("appuyer sur",width-640,height-490);
        g.drawString("espace pour",width-640,height-390);
        g.drawString("passé l'intro",width-640,height-290);
        }

    }

    @Override
    public RenderState updateState() {

        if(finished){
            return new LevelState();
        }
        return this;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Equation getEquation() {
        return equation;
    }
}
