package org.unice.polytech.si3.devint.teffaha.numbershooter.renderer.input;

import org.newdawn.slick.Input;

/**
 * Created with IntelliJ IDEA.
 * User: teffaha
 * Date: 12/05/13
 * Time: 18:43
 * To change this template use File | Settings | File Templates.
 */
public class InputManager {
    private static Input provider;
    private InputManager(){

    }


    public static void update(Input input){
           provider = input;
    }

    public static boolean isShootPressed(){
        return provider.isKeyDown(Input.KEY_SPACE);
    }

    public static boolean isLeftPressed(){
        return provider.isKeyDown(Input.KEY_LEFT);
    }

    public static boolean isRightPressed(){
        return provider.isKeyDown(Input.KEY_RIGHT);
    }

    public static boolean isExit(){
        return provider.isKeyDown(Input.KEY_ESCAPE);
    }

}
