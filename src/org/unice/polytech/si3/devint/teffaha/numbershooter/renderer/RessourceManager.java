package org.unice.polytech.si3.devint.teffaha.numbershooter.renderer;


import org.newdawn.slick.*;
import org.newdawn.slick.Font;
import org.newdawn.slick.Image;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.Effect;
import org.newdawn.slick.font.effects.OutlineEffect;
import org.unice.polytech.si3.devint.teffaha.numbershooter.core.Config;

import java.awt.*;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: teffaha
 * Date: 12/05/13
 * Time: 03:02
 * To change this template use File | Settings | File Templates.
 */
public class RessourceManager {

    private static Map<String,Image> graphics;
    private static Map<String,Font> fonts;
    //private static SIVOXDevint vox;


    public static void playSound(String type){
            if(type.compareTo("explosion")==0){
                Sound.EXPLOSION.play();
                return;
            }
            if(type.compareTo("laser") == 0){
                Sound.TIR_LASER.play();
                return;
            }
    }
    private RessourceManager(){

    }


    public static Font getFontByName(String name){
        init();
        return fonts.get(name);
    }

    public static Image getGraphics(String key){
        init();
        return graphics.get(key);
    }

    public static Font getFont(String key){
        init();
        return fonts.get(key);
    }


        /*
    public static SIVOXDevint getSynthtiser(){
        return vox;
    }
    */

    private static boolean isInisialised = false;
    public static void init(){
        if(isInisialised){
            return;
        }
       // vox = new SIVOXDevint();
        graphics =  new HashMap<String, Image>();
        fonts =  new HashMap<String, Font>();
        try {
            UnicodeFont wellcome = new UnicodeFont((String)Config.getParameterByName("ressourcesroot")+"fonts/bebas.ttf", 150, false, false);
            wellcome.addAsciiGlyphs();
            wellcome.addGlyphs(400, 600);
            wellcome.getEffects().add(new ColorEffect());
            wellcome.loadGlyphs();

            UnicodeFont enemy = new UnicodeFont((String)Config.getParameterByName("ressourcesroot")+"fonts/PoetsenOne-Regular.ttf", 100, false, false);
            enemy.addAsciiGlyphs();
            enemy.addGlyphs(400, 600);
            enemy.getEffects().add(new ColorEffect());
            enemy.loadGlyphs();

            //information
            UnicodeFont information = new UnicodeFont((String)Config.getParameterByName("ressourcesroot")+"fonts/PoetsenOne-Regular.ttf", 80, false, false);
            information.addAsciiGlyphs();
            information.addGlyphs(400, 600);
            information.getEffects().add(new ColorEffect());
            information.loadGlyphs();

            fonts.put("wellcome",wellcome);
            fonts.put("enemy",enemy);
            fonts.put("information",information);
        } catch (SlickException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        try {
            Image bg =  new Image((String)Config.getParameterByName("ressourcesroot")+"images/bg.png");
            graphics.put("background",bg);
            Image enemy =  new Image((String)Config.getParameterByName("ressourcesroot")+"images/enemy.png");
            graphics.put("enemy",enemy);
            for(int i=1;i<=11;i++){
                graphics.put("explosion"+i,new Image((String)Config.getParameterByName("ressourcesroot")+"images/explosion/exp"+i+".png"));
            }
            graphics.put("beam",new Image((String)Config.getParameterByName("ressourcesroot")+"images/ship/beam.png"));
            graphics.put("prebeam",new Image((String)Config.getParameterByName("ressourcesroot")+"images/ship/preBeam.png"));
            graphics.put("bgshooting",new Image((String)Config.getParameterByName("ressourcesroot")+"images/ship/shootingbehind.png"));
            for(int i=1;i<=4;i++){
                graphics.put("ship"+i,new Image((String)Config.getParameterByName("ressourcesroot")+"images/ship/f"+i+".png"));
            }
        } catch (SlickException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        System.out.println((String)Config.getParameterByName("ressourcesroot")+"fonts/bebaas.ttf");

        isInisialised = true;
    }
}
