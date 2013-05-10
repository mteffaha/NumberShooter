package ihm;

import gameEngine.Score;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FrameEndGame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel labelFinPartie;
	private JLabel labelTop10Score;
	private JPanel panelTop10Score;
	private JTextField fieldName;
	private JButton validateButton;
	private Font font;
	private Score score;
	private JLabel labelError;
	
	public FrameEndGame(Score score){
		
		this.score = score;
		font = new Font(null, Font.PLAIN, 30);
		
		this.setBounds(200, 200, 700, 275);
		this.setLayout(new FlowLayout());
		
		Container contentPane = ((JFrame) this).getContentPane();
		fieldName = new JTextField();
		validateButton = new JButton("OK");
		validateButton.addActionListener(this);
		
		
		labelTop10Score = new JLabel();
		labelError = new JLabel();
		
		labelFinPartie = new JLabel("Vous venez de terminer la partie");
		
		labelFinPartie.setFont(font);
		labelTop10Score.setFont(font);
		fieldName.setFont(font);
		validateButton.setFont(font);
		labelError.setFont(font);
		
		contentPane.add(labelError);
		
		if(this.score.isInTop10() == 1){
			labelTop10Score.setText("Excellent!!! Vous avez battu le meilleur score");
			
			
			panelTop10Score = new JPanel();
			panelTop10Score.setLayout(new GridLayout(0,1));
			panelTop10Score.add(labelTop10Score);
			panelTop10Score.add(fieldName);
			panelTop10Score.setBorder(new EmptyBorder(10,10,10,10));
			
			contentPane.add(labelFinPartie);
			contentPane.add(panelTop10Score);
		}
		else if(this.score.isInTop10() == 0){
			labelTop10Score.setText("Excellent!!! Vous faites partie du top 10");

			panelTop10Score = new JPanel();
			panelTop10Score.setLayout(new GridLayout(0,1));
			panelTop10Score.add(labelTop10Score);
			panelTop10Score.add(fieldName);
			panelTop10Score.setBorder(new EmptyBorder(10,10,10,10));
			
			contentPane.add(labelFinPartie);
			contentPane.add(panelTop10Score);
		}
		else{
			labelTop10Score.setText("Vous ne faites pas partie du top 10");
			
			contentPane.add(labelFinPartie);
			contentPane.add(labelTop10Score);
		}
	
		contentPane.add(validateButton);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(!fieldName.getText().equals("")){
			this.score.setName(fieldName.getText());
			this.score.saveScoreOnFile();
			this.dispose();
		}
		else labelError.setText("Erreur vous devez rentrer un nom");
	}
	
	
	
}
