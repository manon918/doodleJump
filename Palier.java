import javax.swing.*;
import java.awt.event.KeyEvent;


public class Palier extends Element {

    final int HEIGHT = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();// la fenêtre s'adapte à la taille de l'écran ordinateur
    final int WIDTH= HEIGHT/2;
    protected int type ;

    public Palier(int x, int y, JLabel palier, int t){
        super(x,y, palier);
        this.width = HEIGHT/18;
        this.height = WIDTH/29;
        palier.setBounds(x, y, width, height);
        palier.setLayout(null);
        this.type = t;
    }

    public void bougePalierX(Timer t) {
        /*int reste = t/4000 % 2;
        if (reste == 0) {
           x += 2 ;
        } else {
            x -= 2 ;
        }*/
    }
    public void bougePalierY(Timer t) {
        /*int reste = t/4000 % 2;
        if (reste == 0) {
           y += 2 ;
        } else {
            y -= 2 ;
        }*/
    }
}


