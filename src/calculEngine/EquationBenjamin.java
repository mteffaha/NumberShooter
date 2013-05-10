package calculEngine;

import java.util.Random;

import org.newdawn.slick.Font;

public class EquationBenjamin {
	private int numerateur;
	private int denominateur;
	private int result;
	private char symbol;
	private int solution;
	private int level;
	
	public EquationBenjamin(int level){
		this.level = level;
		this.symbol = randomSymbol();
		if(symbol != '/'){
		this.numerateur = randomInt(symbol);
		this.denominateur = randomInt(symbol,numerateur);
		}
		else{
			initDivision(level);
		}
		this.result = this.getResultat();
		this.solution = 0;
		emptyRandomField();
		
	}
	
	public int getLevel(){
		return level;
	}
	
	private void initDivision(int level) {
		Random rnd = new Random();
		if(level < 5){
		this.denominateur = rnd.nextInt(10);
		this.result = rnd.nextInt(10);
		this.numerateur = denominateur*result;
		}
		else{
			this.denominateur = rnd.nextInt(89)+11;
			this.result = rnd.nextInt(10);
			this.numerateur = denominateur*result;
		}
		
	}

	public void display(Font f,int xc,int yc,int taillePolice){
		int[] num=tabAutomation.inToTab(numerateur);
		int[] denom=tabAutomation.inToTab(denominateur);
		int[] res=tabAutomation.inToTab(result);
		
		int tailleNum = num.length;
		int tailleRes = res.length;
		int tailleMax = Math.max(tailleRes, tailleNum);
		
		/**
		 * Decallage pour laisser place au symbole.
		 */
		int nxc = xc + 100; 
		
		f.drawString(xc, yc+15+ f.getLineHeight(),"_______");
		f.drawString(xc, yc+f.getLineHeight(), symbol+"");
		
		f.drawString(nxc, yc, tabAutomation.tabToString(num,tailleMax));
		f.drawString(nxc, yc+f.getLineHeight(), tabAutomation.tabToString(denom,tailleMax));
		f.drawString(nxc,yc+2*f.getLineHeight(),tabAutomation.tabToString(res,tailleMax));
		
		/*if(numerateur == 0)f.drawString(nxc, yc, tabAutomation.makeInterrogation(tailleMax+1));
		else f.drawString(nxc, yc, tabAutomation.tabToString(num,tailleMax));
		if (denominateur == 0) f.drawString(nxc, yc+f.getLineHeight(), tabAutomation.makeInterrogation(tailleMax));
		else f.drawString(nxc, yc+f.getLineHeight(), tabAutomation.tabToString(denom,tailleMax));		
		if (result == 0) f.drawString(nxc,yc+2*f.getLineHeight(),tabAutomation.makeInterrogation(tailleMax));
		else f.drawString(nxc,yc+2*f.getLineHeight(),tabAutomation.tabToString(res,tailleMax));
		*/
	}
	
	public int getNumerateur(){
		return numerateur;
	}
	
	public int getDenominateur(){
		return denominateur;
	}
	
	public int getResult(){
		return result;
	}
	
	public char getSymbol(){
		return symbol;
	}
	
	public int getSolution(){
		return this.solution;
	}
	
	private int randomInt(char symbol, int value) {
		int level = this.level;
		int taille = 1;
		Random random = new Random();
		if(symbol == '+')taille+=level-1;
		else if(symbol == '-')taille+=(level/2)-1;
		else if(symbol == 'x')taille+=(level/3)-1;
		else taille+=(level/4)-1;
		
		taille = Math.min(taille, 3);
		
		int valueMax = value-(int)Math.pow((long)10,(long)taille-1);
		int result = random.nextInt(valueMax+1)+(int)Math.pow((long)10,(long)taille-1);
		
		return result;
	}
	
	private int getResultat(){
		switch(symbol){
		case'+':return (numerateur+denominateur);
		case'-':return (numerateur-denominateur);
		case'x':return (numerateur*denominateur);
		case'/':return (numerateur/denominateur);
		}
		return -1;
	}
	
	
	public Boolean checkSolution(){
		switch(symbol){
		case'+':return (numerateur+denominateur)==result;
		case'-':return (numerateur-denominateur)==result;
		case'x':return (numerateur*denominateur)==result;
		case'/':return (numerateur/denominateur)==result;
		}
		return false;
	}
	
	private void setNumerateur(int num){
		numerateur = num;
	}
	
	private void setDenominateur(int num){
		denominateur = num;
	}
	
	private void setResult(int num){
		result = num;
	}
	
	public void setBlank(int num){
		if(numerateur == 0) setNumerateur(num);
		else if(denominateur == 0) setDenominateur(num);
		else setResult(num);
	}
	
	/*public void displayResolutionSteps() throws SlickException{
		switch(symbol){
		case'+':Calculation.launcher(this);break;
		case'-':break;
		case'*':break;
		case'/':break;
		}
	}*/

	private void emptyRandomField() {
		int random = (new Random()).nextInt(3);
		if (random==0){
			this.solution = this.numerateur;
			this.numerateur = 0;
		}
		else if(random==1){
			this.solution = this.denominateur;
			this.denominateur = 0;
		}
		else{
			this.solution = this.result;
			this.result = 0;
		}
		
	}

	private int randomInt(char symbol) {
		int level = this.level;
		int taille = 1;
		Random random = new Random();
		if(symbol == '+')taille+=level-1;
		else if(symbol == '-')taille+=(level/2)-1;
		else if(symbol == 'x')taille+=(level/3)-1;
		else taille+=(level/4)-1;

		taille = Math.min(taille, 3);
		
		int value = (int)(Math.pow((long)10,(long)taille)-Math.pow((long)10,(long)(taille-1)));
		int result = random.nextInt(1+value)+(int)Math.pow((long)10,(long)(taille-1));
		return result;
	}

	private char randomSymbol() {
		int level = this.level;
		double random = Math.random();
		if(level == 1) return '+';
		if(level == 2){
			if(random<0.5) return '+'; else return '-';
		}
		if(level == 3){
			if(random<0.33) return '+';
			else if(random<0.66) return '-';
			else return 'x';
		}
		if(random<0.25) return '+';
		else if(random<0.5) return '-';
		else if(random<0.75) return 'x';
		else return '/';
		
	}

	 public String toString(){
	        String res = "";

	        if(numerateur == 0){
	            res += " ?? ";
	        }else{
	            res += numerateur+" ";
	        }

	        res += " "+symbol+" ";

	        if(denominateur== 0){
	            res += " ?? ";
	        }else{
	            res += denominateur+" ";
	        }

	        res += " = ";

	        if(result == 0){
	            res+= " ?? ";
	        }else{
	            res += result;
	        }

	        return res;
	    }

	
}