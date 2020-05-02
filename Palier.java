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

    public void bougePalierX(int cp) {
        int reste = ((cp/50) % 2);
        if (reste == 0) {
            x += 5 ;
        } else {
            x -= 5 ;
        }
    }
    public void bougePalierY(int cp) {
        int reste =((cp/50) % 2);
        if (reste == 0) {
            y += 5 ;
        } else {
            y -= 5 ;
        }
    }
}


