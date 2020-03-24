public class pallier extends element {
    //***********************************les 2 parametres de doodle pas dans element********************************
    protected int vitesseX;
    protected int vitesseY;
    private int width = 60;
    public int heigth = 60;
    //************************************constructeur***********************************************************
    public pallier(int x, int y){
        super(x,y);
        this.width = 58;
        this.heigth = 15;
        vitesseX = 0;
        vitesseY = 40;//c est un exemple je voudrais que la vitesse initiale fasse comme s'il y avait un saut au debut comme dans le vrai jeu
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

