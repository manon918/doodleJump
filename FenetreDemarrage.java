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

public class FenetreDemarrage extends JFrame implements ActionListener{

    // Les Widgets à déclarer en dehors du constructeur
    private JTextField id;
    private JButton monBoutonDemarrer;
    private FenetreJeu maFenetreJeu;

    Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();// la fenêtre s'adapte à la taille de l'écran ordinateur
    final int HAUTEUR = (int)dimension.getHeight();

    final int WIDTH=HAUTEUR/2; // initialisation largeur Fenetre de démarrage
    final int HEIGHT=HAUTEUR;// initialisation longeur Fenetre de démarrage

    /*ImageIcon imageDoodle = new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\Doodle.png"); //à modifier selon l'emplacement de l'image sur votre ordi et le nom
	ImageIcon imagePallier= new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\palier.png");
	ImageIcon imageFond = new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\Fond.png");*/

    ImageIcon imageDoodle = new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\Doodle.png"); //à modifier selon l'emplacement de l'image sur votre ordi et le nom
    ImageIcon imagePallier= new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\palier.png");
    ImageIcon imageFond = new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\Fond.png");

    JLabel labelDoodle = new JLabel(imageDoodle);
    JLabel labelPallier= new JLabel(imagePallier) ;

    private int x=(WIDTH-10)/2; //position initiale doodle en largeur
    private int y=HEIGHT/2; // position initiale doodle en HAUTEUR

    public FenetreDemarrage(){

        this.setTitle("DoodleJump ");
        this.setSize(WIDTH,HEIGHT);
        this.setLocationRelativeTo(null); // Pour placer la fenêtre au centre de l'écran
        this.setResizable(false); // Pour empêcher le redimensionnement de la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Pour permettre la fermeture de la fenêtre lors de l'appui sur la croix rouge

        JLabel titre = new JLabel();
        titre.setText("DOODLEJUMP");
        titre.setBounds(180,10,100,100);
        this.add(titre);

        monBoutonDemarrer = new JButton("Démarrer");
        monBoutonDemarrer.setBounds((WIDTH/2)-80,HEIGHT-400,160,50);
        monBoutonDemarrer.setBackground(new Color(10,144,10));
        monBoutonDemarrer.setForeground(Color.white);

        monBoutonDemarrer.addActionListener(this);// branchement de l'écouteur
        this.add(monBoutonDemarrer);


        id = new JTextField();
        id.setBounds(WIDTH/2 - 165,HEIGHT-200,330,50);
        this.add(id); //pour entre le nom du joueur + ajouter fenêtre dialogue

        labelPallier.setBounds(x-10, y+60, 58, 15);
        this.add(labelPallier); //ajout d'un pallier fixe

        labelDoodle.setBounds(x, y, 60, 60);
        this.add(labelDoodle);// affichage Doodle

        imageFond = new ImageIcon(imageFond.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT));	//Redimensionnement de l'image de fond pour ajustement à la fenêtre
        JLabel labelFond = new JLabel(imageFond);
        labelFond.setBounds(0, 0, WIDTH, HEIGHT);
        this.add(labelFond);  // ajout fond

        this.setVisible(true);// Pour rendre la fenêtre visible

    }

    /**
     * Suite à un événement
     */
    public void actionPerformed (ActionEvent e){
        if (e.getSource()== monBoutonDemarrer){
            maFenetreJeu = new FenetreJeu();
            maFenetreJeu.setVisible(true);
        }
    }
}

