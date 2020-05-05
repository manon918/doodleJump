import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetreDemarrage extends JFrame implements ActionListener{

    // à modifier selon l'emplacement des images
    ImageIcon imageDoodle = new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\Doodle.png");
    ImageIcon imagePalier= new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\palier.png");
    ImageIcon imageFond = new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\Fond.png");
    ImageIcon imageTitre= new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\Titre.png");

    private final JButton DEMARRER;

    final int HEIGHT = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    final int WIDTH=HEIGHT/2;

    final Doodle MONDOODLE;
    final Palier MONPALIER;
    final JLabel LABELDOODLE;
    final JLabel LABELPALIER;

    public FenetreDemarrage(){

        this.setTitle("DoodleJump ");
        this.setSize(WIDTH,HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Timer mt= new Timer(40,this);
		mt.start();

        imageDoodle = new ImageIcon(imageDoodle.getImage().getScaledInstance((WIDTH/10) ,(HEIGHT/20), Image.SCALE_DEFAULT));
        LABELDOODLE = new JLabel (imageDoodle);
        int x = (WIDTH - (WIDTH / 10))/2;
        int y = (HEIGHT + (HEIGHT/20))/2;
        MONDOODLE= new Doodle(x, y,LABELDOODLE);
        this.add(MONDOODLE.SUPPORT);

        imageTitre = new ImageIcon(imageTitre.getImage().getScaledInstance(WIDTH, HEIGHT/6, Image.SCALE_DEFAULT));
        JLabel labelTitre = new JLabel(imageTitre);
        labelTitre.setBounds(0, 0, WIDTH, HEIGHT/6);
        labelTitre.setLayout(null);
        this.add(labelTitre);

        DEMARRER = new JButton("Démarrer");
        DEMARRER.setBounds((WIDTH/2 -130),HEIGHT*4/5,260,100);
        DEMARRER.setBackground(new Color(10,144,10));
        DEMARRER.setForeground(Color.white);
        DEMARRER.addActionListener(this);
        this.add(DEMARRER);

		imagePalier = new ImageIcon(imagePalier.getImage().getScaledInstance((HEIGHT/18),(WIDTH/29) , Image.SCALE_DEFAULT));
		LABELPALIER= new JLabel (imagePalier);
        MONPALIER = new Palier((WIDTH-HEIGHT/18)/2, MONDOODLE.y+MONDOODLE.height, LABELPALIER,0);
        LABELPALIER.setLayout(null);
        this.add(MONPALIER.SUPPORT);

        imageFond = new ImageIcon(imageFond.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT));
        JLabel labelFond = new JLabel(imageFond);
        labelFond.setLocation(0, 0);
        labelFond.setLayout(null);
        this.add(labelFond);

        this.setVisible(true);
    }
    /** Détecte la collision entre le palier et le Doodle, appel la méthode saut()*/
    public void collision() {
            if (MONDOODLE.vitesseY > 0) {
                if (((MONDOODLE.y + MONDOODLE.height) < (MONPALIER.y + 1.25*MONPALIER.height)) && ((MONDOODLE.y + 1.1*MONDOODLE.height) > (MONPALIER.y))) {
                    if (((MONDOODLE.x + 0.6*MONDOODLE.width) > (MONPALIER.x)) && ((MONDOODLE.x) < (MONPALIER.x + MONPALIER.width))) {
                        MONDOODLE.saut();
                    }
                }
            }
        }

    /**
     * Suite à un événement
     */
    public void actionPerformed (ActionEvent e){
			this.setTitle("DoodleJump ");
			MONDOODLE.tombeDoodle();
			MONDOODLE.bougeX();
            MONDOODLE.SUPPORT.setLocation(MONDOODLE.x,MONDOODLE.y);
			collision();
			
        if (e.getSource()== DEMARRER){
            FenetreJeu maFenetreJeu = new FenetreJeu();
            maFenetreJeu.setVisible(true);
            this.setVisible (false);
        }
    }
}

