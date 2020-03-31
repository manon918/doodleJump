import javax.swing.*;
import java.util.LinkedList;


public class Pallier extends Element {
    //***********************************le parametre de pallier pas dans element********************************
    protected boolean existe;
    //************************************constructeur***********************************************************
    public Pallier(int x, int y, JLabel pallier){
        super(x,y, pallier);
        this.width = 58;
        this.height = 15;
        pallier.setBounds(x, y, width, height);
        pallier.setLayout(null);
    }

    //**************************************getters sur les nouveaux parametres********************************************************
    public JLabel getpallier() {
        return this.support;
    }

    //******************************************setters*********************************************************


}


