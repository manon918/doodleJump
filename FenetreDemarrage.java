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

	LinkedList<Pallier> listePallier1 = new LinkedList<Pallier>(); // initialisation de la liste contenant les palliers
	
    // Les Widgets à déclarer en dehors du constructeur
    private JTextField id;
    private JButton monBoutonDemarrer;
    private FenetreJeu maFenetreJeu;

    Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();// la fenêtre s'adapte à la taille de l'écran ordinateur
    final int HAUTEUR = (int)dimension.getHeight();

    final int WIDTH=HAUTEUR/2; // initialisation largeur Fenetre de démarrage
    final int HEIGHT=HAUTEUR;// initialisation longeur Fenetre de démarrage

    ImageIcon imageDoodle = new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\Doodle.png"); //à modifier selon l'emplacement de l'image sur votre ordi et le nom
	ImageIcon imagePallier= new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\palier.png");
	ImageIcon imageFond = new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\Fond.png");
	ImageIcon imageTitre= new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\Titre.png");
	/*
    ImageIcon imageDoodle = new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\Doodle.png"); //à modifier selon l'emplacement de l'image sur votre ordi et le nom
    ImageIcon imagePallier= new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\palier.png");
    ImageIcon imageFond = new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\Fond.png");
	*/
    JLabel labelDoodle = new JLabel(imageDoodle);
    JLabel labelPallier1= new JLabel(imagePallier) ;

    private int x=(WIDTH-10)/2; //position initiale doodle en largeur
    private int y=(HEIGHT/2)+50; // position initiale doodle en HAUTEUR
    
    Doodle monDoodle = new Doodle(x, y, labelDoodle); //creation objet doodle
	Pallier monPallier1= new Pallier(x, y, labelPallier1); //creation objet pallier
	
	int hauteurMax= HAUTEUR*2/5;
	int score= 0;

    public FenetreDemarrage(){
		
		Timer mt= new Timer(40,this); // réglage timer // initialisation du timer
		mt.start();

        this.setTitle("DoodleJump ");
        this.setSize(WIDTH,HEIGHT);
        this.setLocationRelativeTo(null); // Pour placer la fenêtre au centre de l'écran
        this.setResizable(false); // Pour empêcher le redimensionnement de la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Pour permettre la fermeture de la fenêtre lors de l'appui sur la croix rouge
        
        this.add(labelDoodle);//affichage Doodle

        /*JLabel titre = new JLabel();
        titre.setText("DOODLEJUMP");
        titre.setBounds((WIDTH/2)-50,10,100,100);
        this.add(titre);*/
        
        imageTitre = new ImageIcon(imageTitre.getImage().getScaledInstance(WIDTH, HEIGHT/6, Image.SCALE_DEFAULT));	//Redimensionnement de l'image de fond pour ajustement à la fenêtre
        JLabel labelTitre = new JLabel(imageTitre);
        labelTitre.setBounds(0, 0,WIDTH,HEIGHT/6);
        this.add(labelTitre);  // ajout titre

        monBoutonDemarrer = new JButton("Démarrer");
        monBoutonDemarrer.setBounds((WIDTH/2)-80,HEIGHT-200,160,50);
        monBoutonDemarrer.setBackground(new Color(10,144,10));
        monBoutonDemarrer.setForeground(Color.white);

        monBoutonDemarrer.addActionListener(this);// branchement de l'écouteur
        this.add(monBoutonDemarrer);

		
		//ajout d'un pallier fixe
		int c= (WIDTH-30)/2;
		int d= ((HEIGHT/2)+50)+monDoodle.height;
		imagePallier = new ImageIcon(imagePallier.getImage().getScaledInstance(monPallier1.width, monPallier1.height, Image.SCALE_DEFAULT)); // permet de redimensionner le pallier si besoin
		labelPallier1= new JLabel (imagePallier);
        monPallier1 = new Pallier(c, d, labelPallier1);
        this.add(labelPallier1);
        listePallier1.add(monPallier1); 


        imageFond = new ImageIcon(imageFond.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT));	//Redimensionnement de l'image de fond pour ajustement à la fenêtre
        JLabel labelFond = new JLabel(imageFond);
        labelFond.setBounds(0, 0, WIDTH, HEIGHT);
        this.add(labelFond);  // ajout fond

        this.setVisible(true);// Pour rendre la fenêtre visible

    }
    
		public void collision (){
			for(int i= 0; i<listePallier1.size(); i++){
				if(monDoodle.vitesseY>0) {
					if (((monDoodle.y + monDoodle.height) < (listePallier1.get(i).y + listePallier1.get(i).height +5)) && ((monDoodle.y + monDoodle.height + 5) > (listePallier1.get(i).y))) { //test des Y
						if (((monDoodle.x + monDoodle.width - 22) > (listePallier1.get(i).x)) && ((monDoodle.x) < (listePallier1.get(i).x + listePallier1.get(i).width))) {             //test des x
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
			labelDoodle.setLocation(monDoodle.x,monDoodle.y);
			collision();
			
        if (e.getSource()== monBoutonDemarrer){
            maFenetreJeu = new FenetreJeu();
            maFenetreJeu.setVisible(true);
            this.setVisible (false);
        }
    }
}

