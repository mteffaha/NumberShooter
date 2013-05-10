package gameEngine;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import calculEngine.EquationBenjamin;

/**
 * User: mteffaha
 * Date: 3/15/13
 * Time: 3:19 PM
 *
 * the purpose of this class is to play the rule a the man in the middle between the game logic and the rendrering side
 * of the the game making it indepentend and thus changes made to the game code will not require drastic changes the rendering
 * engine.
 * this class uses a singleton design pattern since we can have only one renderer.
 */
public class Adapter {
    /*
     * General Purpose Methods
     */

	private static final int NB_METEOR = 3;
	
    /**
     * Private constructor to prevent it's instanciation
     */
    private Adapter(){

    }

    private static Adapter instance;

    public static Adapter getInstance(){
        if(instance == null){
            instance = new Adapter();
        }
        return instance;
    }


    /**
     * Get the current Equation to display
     * @param level the level of the equation
     * @return the equation formated as fitted to the rendrer
     */
    EquationBenjamin getEquation(int level){
        return new EquationBenjamin(level);
    }

    /**
     * Checks if an iteger resolves the current equation
     * @param solution the proposed solution
     * @return True if it constitues a solution , False otherwise
     */
    boolean checkSolution(Integer solution){
        Random rand = new Random();
        return rand.nextBoolean();
    }

    /**
     * Get the list of proposition to display to the user
     * @return
     */
    List<Integer> getProposition(EquationBenjamin equation){

        Integer solution = equation.getSolution();

        Random rand = new Random();
        int min = solution - 20;
        int max = solution + 20;
        List<Integer> listProposition = new ArrayList<Integer>();

        // we generate random numbers close to our solution
        for(int i=0;i<NB_METEOR;i++){
        	int propo = min+(rand.nextInt(max-min));
        	while((listProposition.contains(propo) || propo ==  solution))
        		propo = min+(rand.nextInt(max-min));
        	
        	listProposition.add(propo);
        }
        // we make sure our solution exists at least once
        listProposition.add(rand.nextInt(listProposition.size()-1), solution);
        return listProposition;
    }


    /*
     * Methods that will be called by outside classes
     */

    /**
     * Intialise the Renderer.
     * Loads needed ressources but does not start rendering
     */
    public void intialise(){

    }

    /**
     * Starts the rendering process.
     * this method need to be called only once and the rendrer will take care of all the rest
     */
    public void render(){
        Renderer2.getInstance().startRender();
    }

    public static void lancer(){
    	Adapter adapt = new Adapter();
    	adapt.render();
    }

    // TODO : Remove once the menu is implemented
    public static void main(String[] args){
        Adapter.getInstance().render();

    }

}
