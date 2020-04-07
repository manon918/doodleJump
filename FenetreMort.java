// Chargement des bibliothèques Swing et AWT
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetreMort extends JFrame implements ActionListener{

	private JButton monBoutonRejouer;
	ImageIcon imageFond = new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\Fond.png");
	ImageIcon imagePerdu = new ImageIcon ("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\gameover.png");
	//ImageIcon imageFond = new ImageIcon("C:\\Users\\faust\\Downloads\\doodleJump-master\\Fond.png");
	//ImageIcon imagePerdu = new ImageIcon ("C:\\Users\\faust\\Downloads\\doodleJump-master\\doodleJump-master\\gameover.png");

	protected int score ;
	private JLabel monEtiquetteScore;


	Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();// la fenêtre s'adapte à la taille de l'écran ordinateur
	final int HAUTEUR = (int)dimension.getHeight();
	final int WIDTH=HAUTEUR/2; // initialisation largeur Fenetre de démarrage
	final int HEIGHT=HAUTEUR;// initialisation longeur Fenetre de démarrage

	public FenetreMort(int sc) {
		
		this.score = sc;
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);// Place la fenêtre au centre de l'écran
		this.setResizable(false);// Empêche le redimensionnement de la fenêtre
		this.setTitle("DoodleJump ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Pour permettre la fermeture de la fenêtre lors de l'appui sur la croix rouge

		JLabel perdu = new JLabel(imagePerdu);
		perdu.setBounds(30,(500/2)-200,500-10,200);
		perdu.setLayout(null);
		this.add(perdu);

		//JLabel test= new JLabel();
		//test.setBounds(0,0,10,10);
		monBoutonRejouer = new JButton("Rejouer");
		monBoutonRejouer.setBounds((500/2)-80,500-200,160,50);
		monBoutonRejouer.setBackground(Color.RED);
		monBoutonRejouer.setForeground(Color.white);
		monBoutonRejouer.addActionListener(this);// branchement de l'écouteur
		this.add(monBoutonRejouer);
		
		
		monEtiquetteScore = new JLabel("Score = "+Integer.toString(score));
		monEtiquetteScore.setBounds((500/2)-80,(500/2),1000,50);
		Font font = new Font("Arial",Font.BOLD,25);
		monEtiquetteScore.setFont(font);
		monEtiquetteScore.setForeground(Color.black);
		this.add(monEtiquetteScore);		
		

		imageFond = new ImageIcon(imageFond.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT));	//Redimensionnement de l'image de fond pour ajustement à la fenêtre
		JLabel labelFond = new JLabel(imageFond);
		labelFond.setBounds(0, 0, WIDTH, HEIGHT);
		this.add(labelFond);

		this.setVisible(false);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== monBoutonRejouer){
			FenetreJeu maFenetreJeu = new FenetreJeu();
			maFenetreJeu.setVisible(true);
		}
	}
}

