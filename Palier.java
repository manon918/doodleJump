import javax.swing.*;


public class Palier extends Element {

    final int HEIGHT = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();// la fenêtre s'adapte à la taille de l'écran ordinateur
    final int WIDTH= HEIGHT/2;

    public Palier(int x, int y, JLabel palier){
        super(x,y, palier);
        this.width = HEIGHT/18;
        this.height = WIDTH/29;
        palier.setBounds(x, y, width, height);
        palier.setLayout(null);
    }
    public Palier(int x, int y){
        super(x,y);
        this.width = (HEIGHT/18);
        this.height = (WIDTH/29);
    }
}


