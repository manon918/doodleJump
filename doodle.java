public class doodle extends element {

    //***********************************les 2 parametres de doodle pas dans element********************************
    protected int vitesseX;
    protected int vitesseY;

    //************************************constructeur***********************************************************
    public doodle(int x, int y, int width, int heigth){
        super(x,y,width,heigth);
        vitesseX = 0;
        vitesseY = 10;//c est un exemple je voudrai que la vitesse initiale fasse comme s'il y avait un saut au debut comme dans le vrai jeu
    }

    //**************************************getters sur les nouveaux parametres********************************************************

    public int getVitesseX() {
        return vitesseX;
    }

    public int getVitesseY() {
        return vitesseY;
    }

    //******************************************setters*********************************************************

    public void setVitesseX(int vitesseX) {
        this.vitesseX = vitesseX;
    }

    public void setVitesseY(int vitesseY) {
        this.vitesseY = vitesseY;
    }

    //*******************************************méthode pour deplacer le doodle*********************************
    public void bouge(){

        //********************axe X *****************************************************************************

    }
}
