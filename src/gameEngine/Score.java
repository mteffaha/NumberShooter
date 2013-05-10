package gameEngine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Score {

	private int score;
	private int level;
	private int winPoint;
	private String name;
	private int goodEquationWithoutError;
	
	private final static String pathFile = "../ressources/score.txt";
	
	/**
	 * Creer un nouveau score initialise a 0
	 * Avec un niveau a 1
	 * @param name Le nom du joueur
	 */
	public Score(String name){
		this.name = name;
		this.score = 0;
		this.level = 1;
		this.winPoint = 6;
	}
	
	public Score(){
		this("Invite");
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getScore(){
		return this.score;
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public int getWithoutError(){
		return this.goodEquationWithoutError;
	}
	
	/**
	 * Augmente le score 
	 * ainsi que le niveau si suffisamment d'equation on ete realisee
	 * @return Le nouveau niveau d'equation
	 */
	public int increaseScore(){
		this.score += this.winPoint;
		goodEquationWithoutError++;
		if((goodEquationWithoutError % 3) == 0){
			level++;
			winPoint+= 3*level;
		}
		if(goodEquationWithoutError >= 5) this.score += (15 + goodEquationWithoutError*2);
		return this.level;
	}
	
	/**
	 * Fonction stoppant la serie de bonne reponse
	 * A utiliser si le joueur ne touche aucune meteorite
	 */
	public void stopBonus(){
		this.goodEquationWithoutError = 0;
		//score--;
	}
	
	@Override
	public String toString(){
		return "Score :" +this.score;
	}
	
	public static int maxScore(){
		try {
			BufferedReader l = new BufferedReader(new FileReader(Score.pathFile));
			String scoreMax;
			if((scoreMax = l.readLine()) == null){
				l.close();
				return 0;
			}
			
			String chaine[] = scoreMax.split(" : ");
			l.close();
			
			return Integer.valueOf(chaine[1]);
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Fichier non trouve");
			return 0;
		} catch (IOException e) {
			return 0;
		}
	}
	
	public static int minScore(){
		try {
			BufferedReader l = new BufferedReader(new FileReader(Score.pathFile));
			String line, lastLine = new String(); 
			
			while((line = l.readLine()) != null){
				lastLine = line;
			}
			String chaine[] = lastLine.split(" : ");
			l.close();
			
			return Integer.valueOf(chaine[1]);
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Fichier non trouve");
			return 0;
		} catch (IOException e) {
			return 0;
		}
	}
	
	public static int numberOfScore(){
		try {
			BufferedReader l = new BufferedReader(new FileReader(Score.pathFile));
			int nbScore = 0;
			while((l.readLine()) != null){
				nbScore++;
			}
			l.close();
			
			return nbScore;
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Fichier non trouve");
			return 0;
		} catch (IOException e) {
			return 0;
		}
	}
	
	/**
	 * Test si le score fait parti des 10 meilleurs
	 * @return 1 si il est LE meilleur score, 0 si il fait partie du top 10 et -1 sinon
	 */
	public int isInTop10(){
		
		if(Score.maxScore() < this.score) return 1;
		if(Score.numberOfScore() < 10 || Score.minScore() < this.score) return 0;
		return -1;
		
	}
	
	/**
	 * Ecrit le score sur le fichier de sauvegarde
	 */
	public void saveScoreOnFile(){
		try {
			ArrayList<String> scores = new ArrayList<String>();		
			BufferedReader l = new BufferedReader(new FileReader(Score.pathFile));
			String line;

			while((line = l.readLine()) != null){
				scores.add(line);
			}
			
			if(scores.size() == 0) scores.add(this.serial());
			else{
				int scoreEgaux = 0;
				for(int i = 0; i < scores.size() ; i++){
					String chaine[] = scores.get(i).split(" : ");
					if( Integer.valueOf(chaine[1]) == this.score){
						scoreEgaux++;
						if( scoreEgaux == scores.size()){
							scores.add(scoreEgaux, this.serial());
							break;
						}
					}
					else if( Integer.valueOf(chaine[1]) < this.score){
						scores.add(i+scoreEgaux, this.serial());
						break;
					}
				}
			}
			
			l.close();
			
			FileWriter w = new FileWriter(Score.pathFile,false);
			for(int i = 0; i<15 && i<scores.size() ;i++)
				if(i == 0)w.write(scores.get(i));
				else w.write("\n"+scores.get(i));
			
			w.close();
		}catch (IOException e) {
    		System.out.println("pb ecriture fichier");
    		e.printStackTrace();
    	}
	}
	
	private String serial(){
		return this.name + " : " + this.score;
	}
}
