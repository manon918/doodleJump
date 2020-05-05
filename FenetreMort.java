import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetreMort extends JFrame implements ActionListener{

	// à modifier selon l'emplacement
	ImageIcon imageFond = new ImageIcon("C:\\Users\\manon\\ProjetDoodleJump\\Fond.png");
	final ImageIcon PERDU = new ImageIcon ("C:\\Users\\manon\\ProjetDoodleJump\\gameover.png");

	private final JButton DEMARRER;
	protected final int SCORE ;

	final int HEIGHT = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	final int WIDTH=HEIGHT/2;

	public FenetreMort(int sc) {
		this.SCORE = sc;
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("DoodleJump ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel perdu = new JLabel(PERDU);
		perdu.setBounds(30,(500/2)-200,500-10,200);
		perdu.setLayout(null);
		this.add(perdu);

		DEMARRER = new JButton("Rejouer");
		DEMARRER.setBounds((500/2)-80,500-200,160,50);
		DEMARRER.setBackground(Color.RED);
		DEMARRER.setForeground(Color.white);
		DEMARRER.addActionListener(this);
		this.add(DEMARRER);

		JLabel monEtiquetteScore = new JLabel("Score = " + SCORE);
		monEtiquetteScore.setBounds((500/2)-80,(500/2),1000,50);
		Font font = new Font("Arial",Font.BOLD,25);
		monEtiquetteScore.setFont(font);
		monEtiquetteScore.setForeground(Color.black);
		monEtiquetteScore.setLayout(null);
		this.add(monEtiquetteScore);		
		
		imageFond = new ImageIcon(imageFond.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT));
		JLabel labelFond = new JLabel(imageFond);
		labelFond.setBounds(0, 0, WIDTH, HEIGHT);
		labelFond.setLayout(null);
		this.add(labelFond);

		this.setVisible(false);
	}
	/**
	 * Suite à un événement
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== DEMARRER){
			FenetreJeu maFenetreJeu = new FenetreJeu();
			maFenetreJeu.setVisible(true);
			this.setVisible (false);
		}
	}
}

