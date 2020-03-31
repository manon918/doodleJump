/*
 * FenetreJeu.java
 * 
 * Copyright 2020 manon <manon@DESKTOP-OQ2RB61>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

	public  class FenetreJeu extends JFrame implements KeyListener, ActionListener{

		LinkedList<Pallier> listePallier = new LinkedList<Pallier>(); // initialisation de la liste contenant les palliers

		// Les Widgets à déclarer en dehors du constructeur

		/*
		ImageIcon imageDoodle = new ImageIcon("C:\\Users\\manon\\IdeaProjects\\doodleJump\\src\\Doodle.png"); //à modifier selon l'emplacement de l'image sur votre ordi et le nom
		ImageIcon imagePallier= new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\pallier.png");
		ImageIcon imageFond = new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\Fond.png");
		*/
		ImageIcon imageDoodle = new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\Doodle.png"); //à modifier selon l'emplacement de l'image sur votre ordi et le nom
		ImageIcon imagePallier= new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\pallier.png");
		ImageIcon imageFond = new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\Fond.png");

		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();// la fenêtre s'adapte à la taille de l'écran ordinateur
		final int HAUTEUR = (int)dimension.getHeight();

		final int WIDTH=HAUTEUR/2; // initialisation largeur Fenetre de jeu
		final int HEIGHT=HAUTEUR;// initialisation longeur Fenetre de jeu

		JLabel labelDoodle = new JLabel(imageDoodle);
		JLabel labelPallier= new JLabel(imagePallier) ;

		private int x=WIDTH/2; //position initiale doodle en largeur
		private int y=HEIGHT/2; // position initiale doodle en hauteur

		Doodle monDoodle = new Doodle(x, y, labelDoodle); //creation objet doodle
		Pallier monPallier= new Pallier(x, y, labelPallier); //creation objet pallier

		int hauteurMax= HAUTEUR*2/5;
		int score= 0;

		public FenetreJeu() {

			Timer mt= new Timer(40,this); // réglage timer // initialisation du timer
			mt.start();

			this.setSize(WIDTH, HEIGHT);
			this.setLocationRelativeTo(null);// Place la fenêtre au centre de l'écran
			this.setResizable(false);// Empêche le redimensionnement de la fenêtre
			this.setTitle("DoodleJump ");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Pour permettre la fermeture de la fenêtre lors de l'appui sur la croix rouge
			addKeyListener(this);// connecte la fenêtre de jeu à keylistener

			/** Mon panneau Global*/
			JPanel panneauGlobal = new JPanel();
			panneauGlobal.setBounds(0, 0, WIDTH, HEIGHT);
			panneauGlobal.setLayout(null);
			add(panneauGlobal);
			panneauGlobal.add(labelDoodle);
			
			//ajout d'un palliers position aléatoire
			double pourcentage = 0.03;
			int calculNbPallier= (int)(HEIGHT*pourcentage);
			for (int i=0 ; i<calculNbPallier; i++) {
				imagePallier = new ImageIcon(imagePallier.getImage().getScaledInstance(monPallier.getWidth(), monPallier.getHeight(), Image.SCALE_DEFAULT)); // permet de redimensionner le pallier si besoin
				labelPallier= new JLabel (imagePallier);
				int a = (int) (Math.random() * HEIGHT) ;
				int b = (int) (Math.random() * (WIDTH-58));
				monPallier= new Pallier(b, a, labelPallier);
				panneauGlobal.add(labelPallier);
				listePallier.add(monPallier);
			}
			// éliminer les palliers qui se superposent
			

			JLabel test = new JLabel("pallier" + listePallier.size()); //test nombre de pallier dans la liste
			test.setBounds(0, 0, 100, 200);
			panneauGlobal.add(test);

			//Ajout fond
			imageFond = new ImageIcon(imageFond.getImage().getScaledInstance(panneauGlobal.getWidth(), panneauGlobal.getHeight(), Image.SCALE_DEFAULT));	//Redimensionnement de l'image de fond pour ajustement à la fenêtre
			JLabel labelFond = new JLabel(imageFond);
			labelFond.setBounds(0, 0, WIDTH, HEIGHT);
			panneauGlobal.add(labelFond);
			this.setVisible(false);// Pour rendre la fenêtre visible

		}

		public void collision (){
			for(int i= 0; i<listePallier.size(); i++){
				if(monDoodle.getVitesseY()>0) {
					if (((monDoodle.getY() + monDoodle.height) < (listePallier.get(i).getY() + listePallier.get(i).height +5)) && ((monDoodle.getY() + monDoodle.height + 5) > (listePallier.get(i).getY()))) { //test des Y
						if (((monDoodle.getX() + monDoodle.width - 22) > (listePallier.get(i).getX())) && ((monDoodle.getX()) < (listePallier.get(i).getX() + listePallier.get(i).width))) {             //test des x
							monDoodle.saut();
						}
					}
				}
			}
		}

		public void checkSortieEcran(){
			if(monDoodle.x>WIDTH) {
				monDoodle.x=0;
			}
			if(monDoodle.x<0) {
				monDoodle.x=WIDTH;
			}
		}

		public void bougeEcran() {
			if(monDoodle.getY()< hauteurMax ){
				int depassement= hauteurMax- monDoodle.getY();
				monDoodle.setY(hauteurMax);
				for (int i= 0; i<listePallier.size(); i++){
					listePallier.get(i).setY(listePallier.get(i).getY()+depassement);
					listePallier.get(i).getpallier().setLocation(listePallier.get(i).getX(),listePallier.get(i).getY()+depassement);
					if (listePallier.get(i).getY()> HEIGHT) {
						listePallier.get(i).setY(-20);
						listePallier.get(i).setX((int) (Math.random() * (WIDTH-58)));
					}
				}
				score += depassement;
			}
		}

		/*public void checkMort() {
			if((monDoodle.getY()+monDoodle.height)> HEIGHT){
				FenetreMort maFenetreMort = new FenetreMort();
				maFenetreMort.setVisible(true);
			}
		}*/

		public void actionPerformed(ActionEvent e) {
			this.setTitle("DoodleJump " + score); // le chrono s'afficha à coté du titre de la fenêtre de jeu
			monDoodle.tombeDoodle();
			monDoodle.bougeX();
			bougeEcran();
			labelDoodle.setLocation(monDoodle.x,monDoodle.y);
			collision();
			checkSortieEcran();
			//checkMort();
		}

		public void keyPressed (KeyEvent e){
			switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
				// action fleche droite
					monDoodle.droite= true;
					monDoodle.stopDroite= false;
					monDoodle.gauche=false;
					monDoodle.stopGauche=false;
					break;
				case KeyEvent.VK_LEFT:
					// action flèche gauche
					monDoodle.gauche= true;
					monDoodle.stopGauche= false;
					monDoodle.droite= false;
					monDoodle.stopDroite= false;
					break;

				/** appuyer sur la flèche du bas pour faire revenir doodle */
				case KeyEvent.VK_DOWN:
					// action flèche bas
					monDoodle.y= HEIGHT/2;
					monDoodle.x= WIDTH/2;
					monDoodle.vitesseX=0;
					monDoodle.vitesseY=-25;
					monDoodle.setLocation(monDoodle.x , monDoodle.y);
					break;

				/** appuyer sur la flèche du haut pour faire sauter doodle */
				case KeyEvent.VK_UP:
					// action flèche haut
					monDoodle.saut();
					break;
			}
		}
		/** ces deux là sont pas utiles mais si on le met pas la fonctionnalité keyListener peut pas fonctionner sans **/
		public void keyReleased (KeyEvent e) {
			if(e.getKeyCode()== KeyEvent.VK_RIGHT && monDoodle.getVitesseX()>0) {
				// action relachement fleche droite
				monDoodle.stopDroite= true;
				monDoodle.droite= false;
				monDoodle.stopGauche= false;
				monDoodle.gauche= false;
			}
			if(e.getKeyCode()== KeyEvent.VK_LEFT && monDoodle.getVitesseX()<0) {
				// action relachement flèche gauche
				monDoodle.stopGauche= true;
				monDoodle.gauche= false;
				monDoodle.stopDroite= false;
				monDoodle.droite= false;
			}
		}
		public void keyTyped (KeyEvent e){
		}

	}

