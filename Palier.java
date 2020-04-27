import javax.swing.*;
import java.util.LinkedList;


public class Palier extends Element {

    protected int width;
    protected int height;

    public Palier(int x, int y, JLabel palier){
        super(x,y, palier);
        this.width = 58;
        this.height = 15;
        palier.setBounds(x, y, width, height);
        palier.setLayout(null);
    }

}


