package calculEngine;

public abstract class tabAutomation {
	
	/**
	 * Transforme un nombre en le tableau de chiffres correspondant.
	 * @param max
	 * Le nombre a transformer.
	 * @return
	 * Le tableau d'entiers.
	 */
	public static int[] inToTab(int max) {
		String number = String.valueOf(max);
		int size = number.length();
		int i;
		int[] result = new int[size];
		
		for(i = 0;i < size ; i++){
			result[i] = Integer.parseInt(""+number.charAt(i));
		}
		
		return result;
	}
	
	/**
	 * Transforme le tableau de chiffre en le nombre correspondant.
	 * @param tab
	 * le tableau a convertir.
	 * @return
	 */
	public static int tabToInt(int[] tab){
		return (new Integer(tabAutomation.tabToString(tab, tab.length))).intValue();
	}
	
	public static String makeInterrogation(int taille){
		String result = "";
		for(int i = 0;i<taille;i++){
			result +="  ";
		}
		result+="? ?";
		
		return result;
	}
	
	/**
	 * Renvoie un tableau de 0.
	 * @param num
	 * taille du tableau.
	 * @return
	 */
	public static int[] iniTab(int num) {
		int[] result = new int[num];
		int i;
		
		for(i=0;i<=result.length-1;i++){
			result[i] = 0;
		}
		
		return result;
	}
	
	/**
	 * Transforme un tableau d'entier en le String correspondant.
	 * @param values
	 * Tableau de chiffres
	 * @param max
	 * Taille du String
	 * @return
	 */
	public static String tabToString(int[] values,int max){
		String result ="";
		int i;
		
		if(values.length != 1 || values[0]!=0){
		
		for(i=0;i<max - values.length;i++){
			result += "0 ";
		}
		
		for(i=0;i<values.length;i++){
			if(values[i] != 1)result += values[i] + " ";
			else result += values[i] + "  ";
		}
		}
		else{
			for(i=0;i<max - values.length;i++){
				result += "? ";
			}
			
			for(i=0;i<values.length;i++){
				result += '?' + " ";
			}
		}
		
		return result;
	}
}
