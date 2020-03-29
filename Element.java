import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Element extends JFrame {

    // *************parametres communs a tous les elements (doodle, plate-formes, monstre)*******************
    protected int x;
    protected int y;
    protected int width;
    protected int heigth;
    protected JLabel support;

    // *********************************************constructeur**********************************************
    public Element(JLabel support){
        this.support=support;
    }
    public Element (int x, int y , JLabel support){
        this.width= width;
        this.heigth= heigth;
        this.support=support;
    }


    //**********************************************getters****************************************************
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {

        return width;
    }

    public int getHeigth() {
        return heigth;
    }

    //**************************setters, ca permet de réécrire les variables (pas besoin pour la largeur et la hauteur)****************************************

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    //****************************les 2 méthodes par rapport au deplacement************************************************
    public void bougeX(int k){
        x += k;
    }
    public void bougeY(int k){
        y += k;
    }
}
