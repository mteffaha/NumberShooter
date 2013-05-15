package org.unice.polytech.si3.devint.teffaha.numbershooter.renderer.elements;

import org.newdawn.slick.*;
import org.unice.polytech.si3.devint.teffaha.numbershooter.core.Config;
import org.unice.polytech.si3.devint.teffaha.numbershooter.renderer.RenderElements;
import org.unice.polytech.si3.devint.teffaha.numbershooter.renderer.RessourceManager;

/**
 * Created with IntelliJ IDEA.
 * User: teffaha
 * Date: 12/05/13
 * Time: 21:50
 * To change this template use File | Settings | File Templates.
 */
public class Enemy implements RenderElements {
    private static final float DEFAULT_SPEED = 0.03f;

    private static final float XMARGIN = 87;
    private static final float YMARGIN = 40;
    private Font font;

    private Image image;

    public float getxPosition() {
        return xPosition;
    }

    private float xPosition;

    public float getyPosition() {
        return yPosition;
    }

    private float yPosition;
    private float speed =  DEFAULT_SPEED;

    public int getNumber() {
        return number;
    }

    private int number;
    private Animation explostion;
    private boolean exploding = false;
    private boolean done=false;

    public Enemy(int number,int x ,int y){
        Image enemy = RessourceManager.getGraphics("enemy");
        this.number = number;
        try {
            this.image = new Image(300,300);
        } catch (SlickException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        this.font = RessourceManager.getFontByName("enemy");
        Image[] expoImg =  new Image[11];
        for(int i=1;i<=11;i++){

            expoImg[i-1] = RessourceManager.getGraphics("explosion"+i);
        }
        this.explostion =  new Animation(expoImg,50);

        Graphics g = null;
        try {
            g = image.getGraphics();
        } catch (SlickException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        g.drawImage(enemy,0,0);
        g.setColor(new Color(222,88,59));
        g.setFont(this.font);
        if(this.number <= 9){
            g.drawString(""+this.number,XMARGIN+15,YMARGIN);
        }else{
            g.drawString(""+this.number,XMARGIN,YMARGIN);
        }
        this.xPosition = x;
        this.yPosition = y;
    }




    @Override
    public void update() {
        yPosition+=speed;
    }

    private static int count = 0;
    @Override
    public void render(GameContainer gc, Graphics g) {
        if(this.done){
            return;
        }



        if(this.exploding){
            g.drawAnimation(this.explostion,xPosition,yPosition);
        }else{
            g.drawImage(image,xPosition,yPosition);
        }
        if(this.explostion.getFrame() >= (this.explostion.getFrameCount()-1)){
            this.done = true;
        }

    }

    @Override
    public boolean remove() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setExplode() {
        RessourceManager.playSound("explosion");
        this.exploding = true;
    }
}
