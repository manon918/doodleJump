/**
 * La fenêtre principale pour sélectionner les courbes à étudier
 * Placement des widgets
 * Interaction avec le bouton
 * Rendre visible la seconde fenêtre
 *
 */

// Chargement des bibliothèques Swing et AWT
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class FenetreDemarrage extends JFrame implements ActionListener{

    // Les Widgets à déclarer en dehors du constructeur
    private JTextField titre;
    private JTextField id;
    private JButton monBoutonDemarrer;
    private FenetreJeu maFenetreJeu;

    public FenetreDemarrage(){

        this.setTitle("DoodleJump ");
        this.setSize(500,500);

        this.setLocationRelativeTo(null); // Pour placer la fenêtre au centre de l'écran
        //this.setLocation(300,200);
        this.setResizable(false); // Pour empêcher le redimensionnement de la fenêtre

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Pour permettre la fermeture de la fenêtre lors de l'appui sur la croix rouge


        /**
         * Mon panneau 1 Titre
         */
        JPanel titre = new JPanel();
        titre.setBounds(20,20,500,900);
        titre.setLayout(null);
        titre.setBackground(Color.white);

        JLabel d = new JLabel();
        d.setText("DOODLEJUMP");
        d.setBounds(30,5,100,100);
        titre.add(d);


        monBoutonDemarrer = new JButton("Démarrer");
        monBoutonDemarrer.setBounds(200,10,160,50);
        monBoutonDemarrer.setBackground(new Color(10,144,10));
        monBoutonDemarrer.setForeground(Color.white);

        /* branchement de l'écouteur*/
        monBoutonDemarrer.addActionListener(this);
        titre.add(monBoutonDemarrer);

        //pour entre le nom du joueur + ajouter fenêtre dialogue
        id = new JTextField();
        id.setBounds(15,70,330,50);
        titre.add(id);

        /** Mon panneau Global
         */
        JPanel panneauGlobal = new JPanel();
        panneauGlobal.setBounds(0,0,500,500);
        panneauGlobal.setLayout(null);
        panneauGlobal.add(titre);

        this.add(panneauGlobal);


        maFenetreJeu = new FenetreJeu();
        // Pour rendre la fenêtre visible
        this.setVisible(true);

    }

    /**
     * Suite à un événement
     */
    public void actionPerformed (ActionEvent e){
        if (e.getSource()== monBoutonDemarrer){
            maFenetreJeu.setVisible(true);
        }
    }
}

