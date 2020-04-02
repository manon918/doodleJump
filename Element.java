import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Element extends JFrame {

    // *************parametres communs a tous les elements (doodle, plate-formes, monstre)*******************
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected JLabel support;

    // *********************************************constructeur**********************************************
    public Element (int x, int y , JLabel support){
        this.x= x;
        this.y= y;
        this.support=support;
    }

    /**permet de réécrire les variables (pas besoin pour la largeur et la hauteur)*/

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
