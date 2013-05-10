package gameEngine;

/**
 * User: mteffaha
 * Date: 3/27/13
 * Time: 1:44 PM
 */
public class Projectile {


    Point center;
    float angle;
    float slope;
    float n;


    public Projectile(Point center,float angle){
        this.center = center;
        this.angle = angle;
        /*
           m can be calculated by angle; m = tan(angle) And if you know a start point then you can find n

tan(angle) * startPoint_X + n = startPoint_Y

so n = startPoint_Y - (tan ( angle) * startPoint_X )

         */

        slope = (float)Math.tan(angle);
        n = center.getY() - (slope*center.getX());
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }


    public Point getCenter() {
        return center;
    }



    public float getNewY(float X){
        return slope*X+n;

    }

    public float getNewX(float y){

        return (y-n)/slope;
    }



    public void setCenter(Point center) {
        this.center = center;
    }
}
