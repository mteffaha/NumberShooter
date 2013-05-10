package gameEngine;

public class Laser {

    private final static int origineLaser = 0;
    private final static int origineTipCoord_Y = 50;

    private int angle;
    private Point baseOfLaser;
    private Point tipOfLaser;

    /**
     * Creer un laser avec les coordonee indiquee
     * @param base La coordonnee de la base du laser
     * @param angle L'angle de rotation du laser
     * @param tip La coordonnee du bout du laser
     */
    public Laser(Point base, int angle,Point tip){
        this.baseOfLaser = base;
        this.angle = angle;
    }

    /**
     * Creer un laser avec les coordonnee par defaut
     */
    public Laser(){
        this(new Point(origineLaser, origineLaser),90, new Point(origineLaser, origineTipCoord_Y));
    }

    /**
     * Renvoi les coordonnees de la base du laser
     * @return Point representant la base du laser
     */
    public Point getBaseOfLaser(){
        return this.baseOfLaser;
    }

    /**
     * Renvoi le vecteur direction du tir
     */
    public Point getDirectionTir(){

        return new Point(this.tipOfLaser.getX() - this.baseOfLaser.getX(),
                this.tipOfLaser.getY() - this.baseOfLaser.getY());

    }

    /**
     * Tourne le laser de 1 degre vers la gauche
     */
    public void leftRotationLaser(){
        this.angle--;
        rotationLaser();

    }

    /**
     *Tourne le laser de 1 degre vers la droite
     */
    public void rightRotationLaser(){
        this.angle++;
        rotationLaser();
    }

    /**
     * Setter pour la coordonnee Y de la pointe du laser
     * @param y nouvelle coordonnee
     */
    private void setTipCoord_Y(float y){
        this.tipOfLaser.setY(y);
    }
    /**
     * Setter pour la coordonnee X de la pointe du laser
     * @param x nouvelle coordonnee
     */
    private void setTipCoord_X(float d){
        this.tipOfLaser.setX(d);
    }

    /**
     * Effectue la rotation du laser
     * Il faut au prealable modifier la valeur de l'angle
     */
    private void rotationLaser(){

        /*
        float d = Math.sqrt((Math.pow(this.baseOfLaser.getX() + this.tipOfLaser.getX(), 2) +
                Math.pow(this.baseOfLaser.getY() + this.tipOfLaser.getY(), 2)));

        float rad = Math.toDegrees(angle);

        setTipCoord_X(d * Math.cos(rad) + this.baseOfLaser.getX());
        setTipCoord_Y(d * Math.sin(rad) + this.baseOfLaser.getY());
    */
    }
}
