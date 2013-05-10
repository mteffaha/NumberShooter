package gameEngine;

public class Circle {

    private Point center;
    private float rayon;

    /**
     * Creer un cercle
     * @param x La coordonee x du centre du cercle
     * @param y La coordonee y du centre du cercle
     * @param rayon Le rayon du cercle
     */
    public Circle(float x, float y, float rayon ){
        this.center = new Point(x, y);
        this.rayon = rayon;
    }

    /**
     * Creer un cercle de coordonnee (0,0) et de rayon 0
     */
    public Circle(){
        this(0,0,0);
    }

    /**
     * Renvoi le centre du cercle
     * @return
     */
    public Point getCenter(){
        return this.center;
    }

    /**
     * Renvoi le coin haut a gauche du carre entourant le cercle
     * @return
     */
    public Point getLeftHigh(){
        return new Point(this.center.getX()-rayon, this.center.getY()+rayon);
    }

    /**
     * Renvoi le coin haut a droite du carre entourant le cercle
     * @return
     */
    public Point getRightHigh(){
        return new Point(this.center.getX()+rayon, this.center.getY()+rayon);
    }

    /**
     * Renvoi le coin bas a gauche du carre entourant le cercle
     * @return
     */
    public Point getLeftLow(){
        return new Point(this.center.getX()-rayon, this.center.getY()-rayon);
    }

    /**
     * Renvoi le coin bas a droite du carre entourant le cercle
     * @return
     */
    public Point getRightLow(){
        return new Point(this.center.getX()+rayon, this.center.getY()-rayon);
    }

    /**
     * Vector entre le point gauche en haut et le laser
     * @param l laser
     * @return
     */
    private Point getVectorLeftHigh(Laser l){
        return new Point(this.getLeftHigh().getX() - l.getBaseOfLaser().getX(),
                this.getLeftHigh().getY() - l.getBaseOfLaser().getY());
    }

    /**
     * Vector entre le point gauche en bas et le laser
     * @param l laser
     * @return
     */
    private Point getVectorLeftLow(Laser l){
        return new Point(this.getLeftLow().getX() - l.getBaseOfLaser().getX(),
                this.getLeftLow().getY() - l.getBaseOfLaser().getY());
    }

    /**
     * Vector entre le point droite en bas et le laser
     * @param l laser
     * @return
     */
    private Point getVectorRightLow(Laser l){
        return new Point(this.getRightLow().getX() - l.getBaseOfLaser().getX(),
                this.getRightLow().getY() - l.getBaseOfLaser().getY());
    }

    /**
     * Vector entre le point droite en bas et le laser
     * @param l laser
     * @return
     */
    private Point getVectorRightHigh(Laser l){
        return new Point(this.getRightHigh().getX() - l.getBaseOfLaser().getX(),
                this.getRightHigh().getY() - l.getBaseOfLaser().getY());
    }

    /**
     * Realise le produit scalaire entre 2 point
     * @param p1 Le point 1
     * @param p2 Le point 2
     * @return Le resultat du produit scalaire
     */
    private float dotProduct(Point p1, Point p2){
        return p1.getX()*p2.getX() + p1.getY()+p2.getY();
    }

    /**
     * Fonction disant su le tir du laser touche le cercle en utilisant les vecteurs
     * @param l Le laser qui tir
     * @return true si le laser touche et false sinon
     */
    public boolean pointIsInCircle(Laser l){
        float dotProduct1 =  dotProduct(l.getDirectionTir(), getVectorLeftHigh(l));
        float dotProduct2 =  dotProduct(l.getDirectionTir(), getVectorLeftLow(l));
        float dotProduct3 =  dotProduct(l.getDirectionTir(), getVectorRightLow(l));
        float dotProduct4 =  dotProduct(l.getDirectionTir(), getVectorRightHigh(l));

        if(dotProduct1 != 0 || dotProduct2 != 0 || dotProduct3 != 0 || dotProduct3 != 0)
            return true;

        if( (Math.signum(dotProduct4) == Math.signum(dotProduct3)) &&
                (Math.signum(dotProduct4) == Math.signum(dotProduct2)) &&
                (Math.signum(dotProduct4) == Math.signum(dotProduct1)))
            return false;

        else return true;
    }

}
