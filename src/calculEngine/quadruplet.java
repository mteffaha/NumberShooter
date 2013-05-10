package calculEngine;

public class quadruplet {
	
	private int[] retenues;
	private int[] numerateur;
	private int[] denominateur;
	private int[] resultat;
	
	public quadruplet(int[] ret, int[] num, int[] den, int[] res){
		setRetenues(ret);
		setNumerateur(num);
		setDenominateur(den);
		setResultat(res);
	}
	
	public int[] getRetenues() {
		return retenues;
	}
	public void setRetenues(int[] retenues) {
		this.retenues = retenues;
	}
	public int[] getNumerateur() {
		return numerateur;
	}
	public void setNumerateur(int[] numerateur) {
		this.numerateur = numerateur;
	}
	public int[] getDenominateur() {
		return denominateur;
	}
	public void setDenominateur(int[] denominateur) {
		this.denominateur = denominateur;
	}
	public int[] getResultat() {
		return resultat;
	}
	public void setResultat(int[] resultat) {
		this.resultat = resultat;
	}
	
	

}
