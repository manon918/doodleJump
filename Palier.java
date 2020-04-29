import javax.swing.*;
import java.util.LinkedList;


public class Palier extends Element {

    protected int width;
    protected int height;
	final int HEIGHT = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();// la fenêtre s'adapte à la taille de l'écran ordinateur
	final int WIDTH= HEIGHT/2; // initialisation largeur Fenetre de jeu

    public Palier(int x, int y, JLabel palier){
        super(x,y, palier);
        this.width = (int)(HEIGHT/18);
        this.height = (int)(WIDTH/29);
        palier.setBounds(x, y, width, height);
        palier.setLayout(null);
    }

}


