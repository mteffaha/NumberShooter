package org.unice.polytech.si3.devint.teffaha.numbershooter.menu;

import org.newdawn.slick.SlickException;
import org.unice.polytech.si3.devint.teffaha.numbershooter.devintAPI.MenuAbstrait;
import org.unice.polytech.si3.devint.teffaha.numbershooter.renderer.MainRenderer;
import org.unice.polytech.si3.devint.teffaha.numbershooter.renderer.Sound;
import t2s.son.JukeBox;

/**
 * Created with IntelliJ IDEA.
 * User: teffaha
 * Date: 28/05/13
 * Time: 09:54
 * To change this template use File | Settings | File Templates.
 */
public class MenuJeu extends MenuAbstrait {

    public JukeBox jouer;
    public JukeBox quitter;

    /** constructeur
     * @param title : le nom du jeu
     */
    public MenuJeu(String title) {
        super(title);
        jouer = new JukeBox("ressources/sons/jouer.wav");
        quitter = new JukeBox("ressources/sons/quitter.wav");
       // wellcome.playSound();
    }

    /** renvoie le nom des options du menu
     * vous pouvez d�finir autant d'options que vous voulez
     **/
    protected String[] nomOptions() {
        String[] noms = {"Jouer","Quitter"};
        return noms;
    }

    /** lance l'action associ�e au bouton n�i
     * la num�rotation est celle du tableau renvoy� par nomOption
     */
    protected void lancerOption(int i) {
        switch (i){
            case 0 :
                try {

                    MainRenderer.Start();

                } catch (SlickException e) {
                    e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                }
                break;
            case 1 : System.exit(0);break;
            default: System.err.println("action non d�finie");
        }
    }

    // renvoie le fichier wave contenant le message d'accueil
// ces fichiers doivent �tre plac�s dans ressources/sons/
    protected String wavAccueil() {
        return "ressources/sons/wellcome.wav";
    }

    // renvoie le fichier wave contenant la r�gle du jeu
    protected String wavRegleJeu() {
        return "";
    }

}