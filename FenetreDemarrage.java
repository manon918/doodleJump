/**
 * La fenêtre principale pour sélectionner les courbes à étudier
 * Placement des widgets
 * Interaction avec le bouton
 * Rendre visible la seconde fenêtre
 *
 */

// Chargement des bibliothèques Swing et AWT
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class FenetreDemarrage extends JFrame implements ActionListener{

    // Les Widgets à déclarer en dehors du constructeur
    private JTextField titre;
    private JTextField id;
    private JButton monBoutonDemarrer;
    private FenetreJeu maFenetreJeu;
    
    Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();// la fenêtre s'adapte à la taille de l'écran ordinateur
		final int hauteur = (int)dimension.getHeight();

		final int WIDTH=hauteur/2; // initialisation largeur Fenetre de jeu
		final int HEIGHT=hauteur;// initialisation longeur Fenetre de jeu
		
		/*
		ImageIcon imageDoodle = new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\Doodle.png"); //à modifier selon l'emplacement de l'image sur votre ordi et le nom
		ImageIcon imagePallier= new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\palier.png");
		ImageIcon imageFond = new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\Fond.png");
		*/
		ImageIcon imageDoodle = new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\Doodle.png"); //killian
		ImageIcon imagePallier= new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\pallier.png"); //killian
		ImageIcon imageFond = new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\Fond.png");//killian
		
		JLabel labelDoodle = new JLabel(imageDoodle);
		JLabel labelPallier= new JLabel(imagePallier) ;

		private int x=(WIDTH-10)/2; //position initiale doodle en largeur
		private int y=HEIGHT/2; // position initiale doodle en hauteur
		
		Doodle monDoodle = new Doodle(x, y, labelDoodle); //creation objet doodle
		Pallier monPallier= new Pallier(x, y, labelPallier); //creation objet pallier


    public FenetreDemarrage(){

        this.setTitle("DoodleJump ");
        this.setSize(WIDTH,HEIGHT);

        this.setLocationRelativeTo(null); // Pour placer la fenêtre au centre de l'écran
        this.setResizable(false); // Pour empêcher le redimensionnement de la fenêtre

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Pour permettre la fermeture de la fenêtre lors de l'appui sur la croix rouge


        /**
         * Mon panneau 1 Titre
         */
        JPanel titre = new JPanel();
        titre.setBounds(20,20,500,900);
        titre.setLayout(null);
        titre.setBackground(Color.white);

        JLabel d = new JLabel();
        d.setText("DOODLEJUMP");
        d.setBounds(180,10,100,100);
        titre.add(d);


        monBoutonDemarrer = new JButton("Démarrer");
        monBoutonDemarrer.setBounds(150,HEIGHT-220,160,50);
        monBoutonDemarrer.setBackground(new Color(10,144,10));
        monBoutonDemarrer.setForeground(Color.white);

        /* branchement de l'écouteur*/
        monBoutonDemarrer.addActionListener(this);
        titre.add(monBoutonDemarrer);

        //pour entre le nom du joueur + ajouter fenêtre dialogue
        id = new JTextField();
        id.setBounds(60,HEIGHT-150,330,50);
        titre.add(id);
        
        //ajout d'un pallier fixe
			imagePallier= new ImageIcon(imagePallier.getImage().getScaledInstance(58,15, Image.SCALE_DEFAULT)); // permet de redimensionner le pallier si besoin
			JLabel pallier = new JLabel(imagePallier);
			monPallier= new Pallier(58, 15, labelPallier);
			pallier.setBounds(x-10, y+60, 58, 15);
			titre.add(pallier);
						
			// affichage Doodle
			titre.add(labelDoodle);
			
			// ajout fond
			imageFond = new ImageIcon(imageFond.getImage().getScaledInstance(titre.getWidth(), titre.getHeight(), Image.SCALE_DEFAULT));	//Redimensionnement de l'image de fond pour ajustement à la fenêtre
			JLabel labelFond = new JLabel(imageFond);
			labelFond.setBounds(0, 0, WIDTH, HEIGHT);
			titre.add(labelFond);
			
			this.setVisible(true);// Pour rendre la fenêtre visible

        /** Mon panneau Global
         */
        JPanel panneauGlobal = new JPanel();
        panneauGlobal.setBounds(0,0,500,500);
        panneauGlobal.setLayout(null);
        panneauGlobal.add(titre);

        this.add(panneauGlobal);


        maFenetreJeu = new FenetreJeu();
        // Pour rendre la fenêtre visible
        this.setVisible(true);

    }

    public void collision (){
				if(monDoodle.getVitesseY()>0) {
					if (((monDoodle.getY() + monDoodle.height) < (monPallier.getY() + monPallier.height +5)) && ((monDoodle.getY() + monDoodle.height + 5) > (monPallier.getY()))) { //test des Y
						if (((monDoodle.getX() + monDoodle.width - 22) > (monPallier.getX())) && ((monDoodle.getX()) < (monPallier.getX() + monPallier.width))) {             //test des x
							monDoodle.saut();
						
					}
				}
			}
		}
					

    /**
     * Suite à un événement
     */
    public void actionPerformed (ActionEvent e){
			monDoodle.tombeDoodle();
			monDoodle.bougeX();
			labelDoodle.setLocation(monDoodle.x,monDoodle.y);
			collision();
        if (e.getSource()== monBoutonDemarrer){
            FenetreJeu maFenetreJeu = new FenetreJeu();
            maFenetreJeu.setVisible(true);
        }
    }
}

