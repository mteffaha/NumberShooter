package org.unice.polytech.si3.devint.teffaha.numbershooter.renderer;
import t2s.son.JukeBox;

public enum Sound {
	
	TIR_LASER("ressources/sons/LASER1.WAV"),
	EXPLOSION("ressources/sons/explosion-02.wav"),
    WL_SHIP("ressources/sons/ship.wav");
	
	private String file;


	private Sound(String file){
		this.file = file;
	}
	
	public void play(){
		JukeBox jk = new JukeBox(file);

		jk.playSound();
	}
	
}
