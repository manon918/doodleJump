import javax.swing.*;

public class Pallier extends Element {
    //***********************************les 2 parametres de doodle pas dans element********************************
    protected int vitesseX;
    protected int vitesseY;
    protected int width;
    protected int heigth;
    //************************************constructeur***********************************************************
    public Pallier(int x, int y, JLabel pallier){
        super(x,y, pallier);
        this.width = 58;
        this.heigth = 15;
        vitesseX = 0;
        vitesseY = 40;//c est un exemple je voudrais que la vitesse initiale fasse comme s'il y avait un saut au debut comme dans le vrai jeu
    }

    public Pallier(JLabel labelPallier) {
        super(labelPallier);
        this.width = 58;
        this.heigth = 15;
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

    public void setHeigth(int Heigth) {
        this.heigth = heigth;
    }



    //*******************************************méthode pour deplacer le doodle*********************************
    public void bouge(){
        vitesseY = 20;


        //********************axe X *****************************************************************************

    }
}

