import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Doodle extends Element {

    //***********************************les 2 parametres de doodle pas dans element********************************
    protected int vitesseX;
    protected int vitesseY;
    protected int width = 60;
    protected int heigth = 60;


    //************************************constructeur***********************************************************
    public Doodle(int x, int y, JLabel doodle){
        super(x,y, doodle);
        this.heigth = 60;
        this.width = 60;
        doodle.setBounds(x,y,width, heigth);
        doodle.setLayout(null);
        vitesseX = 0;
        vitesseY = 10;//c est un exemple je voudrais que la vitesse initiale fasse comme s'il y avait un saut au debut comme dans le vrai jeu
    }

    //**************************************getters sur les nouveaux parametres********************************************************
    public int getVitesseX() {
        return vitesseX;
    }

    public int getVitesseY() {
        return vitesseY;
    }

    public int getHeigth(){
        return heigth;
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

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }



    //*******************************************méthode pour deplacer le doodle*********************************
    public void bouge() {



    }
}
