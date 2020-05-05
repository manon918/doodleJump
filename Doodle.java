import javax.swing.*;

public class Doodle extends Element {

    final int HEIGHT = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    final int WIDTH= HEIGHT/2;
    final int LARGEUR;
    final int HAUTEUR;
    protected int vitesseX;
    protected int vitesseY;
    final int VMAXY= 40;
    final int VMAXX= 15;

    protected boolean droite= false;
    protected boolean gauche= false;
    protected boolean stopDroite= false;
    protected boolean stopGauche= false;

    public Doodle(int x, int y, JLabel doodle){

        super(x,y,doodle);
        this.HAUTEUR = (HEIGHT/20);
        this.LARGEUR = (WIDTH/10);
        doodle.setBounds(x,y,LARGEUR, HAUTEUR);
        doodle.setLayout(null);
        vitesseX = 0;
        vitesseY = -25;
    }
    /** reproduit les effets de la gravité sur le Doodle*/
    public void tombeDoodle(){
        int gravite= 1;
        if(vitesseY< VMAXY){
            y+= vitesseY;
            vitesseY+= gravite;
        } else {
            vitesseY = VMAXY;
            y += vitesseY;
        }
    }

    public void saut (){
        vitesseY= -25;
    }
    public void petitSaut (){
        vitesseY= -15;
    }
    public void moyenSaut (){
        vitesseY= -35;
    }
    public void superSaut (){
        vitesseY= -60;
    }

    /**Augmente la vitesse latérale du Doodle lorsque l'on appuie que les flèches G et D*/
    public void bougeX() {
        if(droite) {
            if (vitesseX < VMAXX) {
                vitesseX += 2;
            } else {
                vitesseX = VMAXX;
            }
            x += vitesseX;
        }
        if(gauche){
            if (vitesseX > -VMAXX) {
                vitesseX -= 2;
            } else {
                vitesseX = -VMAXX;
            }
            x += vitesseX;
        }
        if(stopDroite) {
            vitesseX=0;
        }
        if(stopGauche){
            vitesseX=0;
        }
    }
}











