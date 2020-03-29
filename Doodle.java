import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Doodle extends Element {

    //***********************************les 2 parametres de doodle pas dans element********************************
    protected int vitesseX;
    protected int vitesseY;
    protected int width = 60;
    protected int height = 60;


    //************************************constructeur***********************************************************
    public Doodle(int x, int y, JLabel doodle){
        super(x,y, doodle);
        this.height = 60;
        this.width = 60;
        doodle.setBounds(x,y,width, height);
        doodle.setLayout(null);
        vitesseX = 0;
        vitesseY = -25;//c est un exemple je voudrais que la vitesse initiale fasse comme s'il y avait un saut au debut comme dans le vrai jeu
    }

    //**************************************getters sur les nouveaux parametres********************************************************
    public int getVitesseX() {
        return vitesseX;
    }

    public int getVitesseY() {
        return vitesseY;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    //******************************************setters*********************************************************

    public void setVitesseX(int vitesseX) {
        this.vitesseX = vitesseX;
    }

    public void setVitesseY(int vitesseY) {
        this.vitesseY = vitesseY;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }



    //*******************************************méthode pour deplacer le doodle*********************************
    public void deplaceDoodle(){
        int gravite= 1;
        y+= vitesseY;
        vitesseY+= gravite;
    }

    public void saut (){
		vitesseY= -25;
    }


}






