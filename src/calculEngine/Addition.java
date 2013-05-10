package calculEngine;

public class Addition {
	private static final char symbol = '+';
	
	private int[] numerateur;
	private int[] denominateur;
	private int[] retenues;
	private int[] result;
	private int resultat;
	
	public Addition(int num,int denom){
		this.numerateur = tabAutomation.inToTab(num);
		this.denominateur = tabAutomation.inToTab(denom);
		this.retenues = tabAutomation.iniTab(Math.max(denominateur.length,numerateur.length)-1);
		this.result = tabAutomation.iniTab(String.valueOf(num+denom).length());
		this.resultat = num + denom;
	}
	
	public void stringBase(){
		int max = Math.max(numerateur.length,denominateur.length) + 1;
		
		System.out.println(tabAutomation.tabToString(numerateur,max));
		System.out.println(symbol);	
		System.out.println(tabAutomation.tabToString(denominateur,max));
	}
	
	public Addition newStep(){
		Addition a = null;
		return a;
	}
	
	public void setResult(int result){
		this.result = tabAutomation.inToTab(result);
	}

	public int getResultat() {
		return resultat;
	}
	
	public Boolean isCorrect(){
		return (tabAutomation.tabToInt(this.numerateur) + tabAutomation.tabToInt(this.denominateur)) == tabAutomation.tabToInt(this.result) ;
		
	}

	public void display() {
		System.out.println("Not done already.");
	}
	
	
	
}
