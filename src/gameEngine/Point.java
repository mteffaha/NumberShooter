package gameEngine;

/**
 * Represente unn point
 * @author Anthony
 *
 */
public class Point {
	private float x;
	private float y;
	
	/**
	 * Creer un point de coordonnee x,y
	 * @param x La coordonnee x
	 * @param y La coordonnee y
	 */
	public Point(float y, float x){
		this.y = y;
		this.x = x;

	}
	
	public float getX(){
		return this.x;
	}
	
	public float getY(){
		return this.y;
	}
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y2){
		this.y = y2;
	}
	
	
}
