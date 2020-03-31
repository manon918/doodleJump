// Chargement des bibliothèques Swing et AWT
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetreMort extends JFrame implements ActionListener{
	
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();// la fenêtre s'adapte à la taille de l'écran ordinateur
		final int hauteur = (int)dimension.getHeight();

		final int WIDTH=hauteur/2; // initialisation largeur Fenetre de jeu
		final int HEIGHT=hauteur;// initialisation longeur Fenetre de jeu	
		
		/*
		ImageIcon imageFond = new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\Fond.png");
		ImageIcon imagePerdu = new ImageIcon ("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\perdu.png");
		*/
		ImageIcon imageFond = new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\Fond.png");//killian
		ImageIcon imagePerdu = new ImageIcon ("C:\\Users\\utilisateur\\doodleJump\\perdu.PNG"); //killian

		
	
	public FenetreMort() {
		
		this.setSize(WIDTH, HEIGHT);
		this.setLocationRelativeTo(null);// Place la fenêtre au centre de l'écran
		this.setResizable(false);// Empêche le redimensionnement de la fenêtre
		this.setTitle("DoodleJump ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Pour permettre la fermeture de la fenêtre lors de l'appui sur la croix rouge
		//addKeyListener(this);// connecte la fenêtre de jeu à keylistener
		this.setVisible(true);
		
		/** Mon panneau Global*/
			//JLabel labelDoodle = new JLabel(imageDoodle);	
			//Doodle monDoodle = new Doodle(x, y, labelDoodle); //creation objet doodle
			
			JPanel panneauGlobal = new JPanel();
			panneauGlobal.setBounds(0, 0, WIDTH, HEIGHT);
			panneauGlobal.setLayout(null);
			add(panneauGlobal);
			
			
			JLabel perdu = new JLabel(imagePerdu);
			perdu.setBounds(10,(HEIGHT/2)-200, WIDTH-10,200);
			perdu.setLayout(null);
			
			
			panneauGlobal.add(perdu);
			
			imageFond = new ImageIcon(imageFond.getImage().getScaledInstance(panneauGlobal.getWidth(), panneauGlobal.getHeight(), Image.SCALE_DEFAULT));	//Redimensionnement de l'image de fond pour ajustement à la fenêtre
			JLabel labelFond = new JLabel(imageFond);
			labelFond.setBounds(0, 0, WIDTH, HEIGHT);
			panneauGlobal.add(labelFond);
		
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
	}
}

