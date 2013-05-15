package org.unice.polytech.si3.devint.teffaha.numbershooter.renderer;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.unice.polytech.si3.devint.teffaha.numbershooter.core.Config;
import org.unice.polytech.si3.devint.teffaha.numbershooter.renderer.elements.Enemy;
import org.unice.polytech.si3.devint.teffaha.numbershooter.renderer.input.InputManager;

/**
 * Created with IntelliJ IDEA.
 * User: teffaha
 * Date: 13/05/13
 * Time: 02:31
 * To change this template use File | Settings | File Templates.
 */
public class ship extends RenderState{

    private int xPosition=0;
    private int yPosition=0;
    private Animation anim;
    private Image bgShooting;
    private Image preBeam;
    private Image beam;

    private boolean isShooting = false;

    public ship(int x,int y){
        this.xPosition =x;
        this.yPosition =y;
        Image[] img =  new Image[4];
        for(int i=1;i<=4;i++){
            img[i-1] = RessourceManager.getGraphics("ship"+i);
        }
        this.anim =  new Animation(img,150);
        bgShooting = RessourceManager.getGraphics("bgshooting");
        preBeam = RessourceManager.getGraphics("prebeam");
        beam = RessourceManager.getGraphics("beam");
    }
    @Override
    protected void updateLogic() {
        if(InputManager.isRightPressed()){
            xPosition+=3;
            if(xPosition >= ((Short) Config.getParameterByName("windowwidth")-150)){
                 xPosition= ((Short) Config.getParameterByName("windowwidth")-150);
            }
        }

        if(InputManager.isLeftPressed()){
            xPosition-=3;
            if(xPosition <= 0){
                xPosition= 0;
            }
        }
        if(InputManager.isShootPressed()){
            isShooting = true;
            RessourceManager.playSound("laser");

        }else{
            isShooting = false;
        }
    }

    public boolean doesCollide(Enemy enm){
        if(!isShooting){
            return false;
        }
        int start =xPosition+56+(( anim.getWidth()-bgShooting.getWidth())/2 );
        int enmStart = (int)enm.getxPosition();
        int enmEnd = (int)enm.getxPosition()+200;
        int beamStart = start;
        int beamEnd = (start+beam.getWidth());
        boolean cond1 = beamStart <= enmStart && enmStart <=  beamEnd;
        boolean cond2 = beamStart >= enmStart && beamEnd <= enmEnd;
        boolean cond3 = beamStart <= enmEnd && enmEnd <= beamEnd ;

        if(cond1 ||cond2 || cond3){
            return true;
        }

        return false;
    }

    @Override
    protected void updateGraphics() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void render(GameContainer gc, Graphics g) {

        int stBG =3+(( anim.getWidth()-bgShooting.getWidth())/2 );
        if(isShooting){

            g.drawImage(bgShooting,stBG+xPosition,yPosition-45);
            for(int i=yPosition-45;i>-20;i--){
                g.drawImage(beam,stBG+xPosition+53,i);
            }
        }else{
            for(int i=yPosition-10;i>-20;i--){
                g.drawImage(preBeam,stBG+xPosition+53,i);
            }
        }

        g.drawAnimation(anim,xPosition,yPosition);
    }

    @Override
    public RenderState updateState() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
