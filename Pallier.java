import javax.swing.*;

public class Pallier extends Element {
    //***********************************le parametre de pallier pas dans element********************************
    protected boolean existe;
    //************************************constructeur***********************************************************
    public Pallier(int x, int y, JLabel pallier){
        super(x,y, pallier);
        this.width = 58;
        this.height = 15;

    }

    public Pallier(JLabel labelPallier) {
        super(labelPallier);
        this.width = 58;
        this.height = 15;
    }

    //**************************************getters sur les nouveaux parametres********************************************************


    //******************************************setters*********************************************************


}


