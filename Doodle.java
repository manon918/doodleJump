import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Doodle extends Element {

    //***********************************les 2 parametres de doodle pas dans element********************************
    protected int width;
    protected int height;

    protected int vitesseX;
    protected int vitesseY;
    protected int vitesseMaxY= 40;
    protected int vitesseMaxX= 15;

    protected boolean droite= false;
    protected boolean gauche= false;
    protected boolean stopDroite= false;
    protected boolean stopGauche= false;
    
	final int HEIGHT = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();// la fenêtre s'adapte à la taille de l'écran ordinateur
	final int WIDTH= HEIGHT/2; // initialisation largeur Fenetre de jeu

    //************************************constructeur***********************************************************
    public Doodle(int x, int y, JLabel doodle){
        super(x,y,doodle);
        this.height = (int)(HEIGHT/8);
        this.width = (int)(WIDTH/6);
        doodle.setBounds(x,y,width, height);
        doodle.setLayout(null);
        vitesseX = 0;
        vitesseY = -25;
    }

    //*******************************************méthode pour deplacer le doodle*********************************
    public void tombeDoodle(){
        int gravite= 1;
        if(vitesseY< vitesseMaxY){
            y+= vitesseY;
            vitesseY+= gravite;
        } else {
            vitesseY = vitesseMaxY;
            y += vitesseY;
        }
    }

    public void saut (){
		vitesseY= -25;
    }

    public void bougeX() {
        if(droite) {
            if (vitesseX < vitesseMaxX) {
                vitesseX += 2;
                x += vitesseX;
            } else {
                vitesseX = vitesseMaxX;
                x += vitesseX;
            }
        }

        if(gauche){
            if (vitesseX > -vitesseMaxX) {
                vitesseX -= 2;
                x += vitesseX;
            } else {
                vitesseX = -vitesseMaxX;
                x += vitesseX;
            }
        }
        if(stopDroite) {
            vitesseX=0;
        }
        if(stopGauche){
            vitesseX=0;
        }
    }
}






