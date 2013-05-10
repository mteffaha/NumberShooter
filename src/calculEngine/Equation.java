package calculEngine;

import java.util.Random;

public class Equation {
	private int numerateur;
	private int denominateur;
	private int result;
	private char symbol;
    private int unkown = -1;
	
	public Equation(int level){
		this.symbol = randomSymbol(level);
		this.numerateur = randomInt(level,symbol);
		this.denominateur = randomInt(level,symbol);
		this.result = this.getResultat();
		emptyRandomField();
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
	
	private int getResultat(){
		switch(symbol){
		case'+':return (numerateur+denominateur);
		case'-':return (numerateur-denominateur);
		case'*':return (numerateur*denominateur);
		case'/':return (numerateur/denominateur);
		}
		return -1;
	}
	
	public Boolean checkSolution(){
		switch(symbol){
		case'+':return (numerateur+denominateur)==result;
		case'-':return (numerateur-denominateur)==result;
		case'*':return (numerateur*denominateur)==result;
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
		if (random==0) {
            unkown = 0;
            this.numerateur = 0;
        }
		else if(random==1) {
            unkown = 0;
            this.denominateur = 0;
        }
		else {
            unkown = 0;
            this.result = 0;
        }
		
	}

    public int getUnkown(){
        return unkown;
    }


    public int getPossibleSolution(){
        if(numerateur == 0){
            switch(symbol){
                case'+':return result - denominateur;
                case'-':return result + denominateur;
                case'*':return result / denominateur;
                case'/':return result * denominateur;
            }
        } else if(denominateur == 0){
            switch(symbol){
                case'+':return result - denominateur;
                case'-':return result + denominateur;
                case'*':return result / denominateur;
                case'/':return result * denominateur;
            }

        }   else if (result == 0){
            switch(symbol){
                case'+':return (numerateur+denominateur);
                case'-':return (numerateur-denominateur);
                case'*':return (numerateur*denominateur);
                case'/':return (numerateur/denominateur);
            }

        }

        return -1;
    }



	private int randomInt(int level, char symbol) {
		int taille = 2;
		Random random = new Random();
		/*if(symbol == '+')taille+=level-1;
		else if(symbol == '-')taille+=level-2;
		else if(symbol == '*')taille+=level-3;
		else taille+=level-4;
		
		int result = random.nextInt(
                (int)(
                        Math.pow((long)10,(long)taille)-Math.pow((long)10,(long)taille-1)))+(int)Math.pow((long)10,(long)taille-1);
                        */
		return random.nextInt(99);
	}

	private char randomSymbol(int level) {
		double random = Math.random();
		if(level == 1) return '+';
		if(level == 2){
			if(random<0.5) return '+'; else return '-';
		}
		if(level == 3){
			if(random<0.33) return '+';
			else if(random<0.66) return '-';
			else return '*';
		}
		if(random<0.25) return '+';
		else if(random<0.5) return '-';
		else if(random<0.75) return '*';
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
