/**
 * La fenêtre principale pour sélectionner les courbes à étudier
 * Placement des widgets
 * Interaction avec le bouton
 * Rendre visible la seconde fenêtre
 *
 */

// Chargement des bibliothèques Swing et AWT
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class FenetreDemarrage extends JFrame implements ActionListener{

	LinkedList<Palier> listePalier = new LinkedList<Palier>(); // initialisation de la liste contenant les palliers

    private JButton monBoutonDemarrer;
    private FenetreJeu maFenetreJeu;

    final int HEIGHT = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();// la fenêtre s'adapte à la taille de l'écran ordinateur
    final int WIDTH=HEIGHT/2; // initialisation largeur Fenetre de démarrage

    /*ImageIcon imageDoodle = new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\Doodle.png"); //à modifier selon l'emplacement de l'image sur votre ordi et le nom
	ImageIcon imagePalier= new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\palier.png");
	ImageIcon imageFond = new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\Fond.png");
	ImageIcon imageTitre= new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\Titre.png");*/

    /*ImageIcon imageDoodle = new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\Doodle.png"); //à modifier selon l'emplacement de l'image sur votre ordi et le nom
    ImageIcon imagePalier= new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\palier.png");
    ImageIcon imageFond = new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\Fond.png");
    ImageIcon imageTitre= new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\Titre.png");*/

    ImageIcon imageDoodle = new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\Doodle.png"); //killian
    ImageIcon imagePalier= new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\palier.png");
    ImageIcon imageFond = new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\Fond.png");
    ImageIcon imageTitre= new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\Titre.png");


    JLabel labelDoodle = new JLabel(imageDoodle);
    JLabel labelPalier= new JLabel(imagePalier) ;

    private int x=(WIDTH-10)/2; //position initiale doodle en largeur
    private int y=(HEIGHT/2)+50; // position initiale doodle en HAUTEUR

    Doodle monDoodle = new Doodle(x, y, labelDoodle); //creation objet doodle
	Palier monPalier= new Palier(x, y, labelPalier); //creation objet pallier

    public FenetreDemarrage(){
		
		Timer mt= new Timer(40,this); // réglage timer // initialisation du timer
		mt.start();

        this.setTitle("DoodleJump ");
        this.setSize(WIDTH,HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(labelDoodle);

        imageTitre = new ImageIcon(imageTitre.getImage().getScaledInstance(WIDTH, HEIGHT/6, Image.SCALE_DEFAULT));	//Redimensionnement de l'image de fond pour ajustement à la fenêtre
        JLabel labelTitre = new JLabel(imageTitre);
        labelTitre.setBounds(0, 0, WIDTH, HEIGHT/6);
        this.add(labelTitre);

        monBoutonDemarrer = new JButton("Démarrer");
        monBoutonDemarrer.setBounds((WIDTH/2)-80,HEIGHT-200,160,50);
        monBoutonDemarrer.setBackground(new Color(10,144,10));
        monBoutonDemarrer.setForeground(Color.white);
        monBoutonDemarrer.addActionListener(this);
        this.add(monBoutonDemarrer);

		
		//ajout d'un pallier fixe
		imagePalier = new ImageIcon(imagePalier.getImage().getScaledInstance(monPalier.width, monPalier.height, Image.SCALE_DEFAULT)); // permet de redimensionner le pallier si besoin
		labelPalier= new JLabel (imagePalier);
        monPalier = new Palier((WIDTH-30)/2, ((HEIGHT/2)+50)+monDoodle.height, labelPalier);
        this.add(labelPalier);
        listePalier.add(monPalier);


        imageFond = new ImageIcon(imageFond.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT));	//Redimensionnement de l'image de fond pour ajustement à la fenêtre
        JLabel labelFond = new JLabel(imageFond);
        labelFond.setLocation(0, 0);
        this.add(labelFond);  // ajout fond

        this.setVisible(true);// Pour rendre la fenêtre visible

    }
    
		public void collision (){
			for(int i= 0; i<listePalier.size(); i++){
				if(monDoodle.vitesseY>0) {
					if (((monDoodle.y + monDoodle.height) < (listePalier.get(i).y + listePalier.get(i).height +5)) && ((monDoodle.y + monDoodle.height + 5) > (listePalier.get(i).y))) { //test des Y
						if (((monDoodle.x + monDoodle.width - 22) > (listePalier.get(i).x)) && ((monDoodle.x) < (listePalier.get(i).x + listePalier.get(i).width))) {             //test des x
							monDoodle.saut();
						}
					}
				}
			}
		}


    /**
     * Suite à un événement
     */
    public void actionPerformed (ActionEvent e){
			this.setTitle("DoodleJump " );
			monDoodle.tombeDoodle();
			monDoodle.bougeX();
            monDoodle.support.setLocation(monDoodle.x,monDoodle.y);
			collision();
			
        if (e.getSource()== monBoutonDemarrer){
            maFenetreJeu = new FenetreJeu();
            maFenetreJeu.setVisible(true);
            this.setVisible (false);
        }
    }
}

