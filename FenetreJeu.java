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

		LinkedList<JLabel>listePallier = new LinkedList<JLabel>(); // initialisation de la liste contenant les palliers

		// Les Widgets à déclarer en dehors du constructeur

		ImageIcon imageDoodle = new ImageIcon("C:\\Users\\manon\\IdeaProjects\\doodleJump\\src\\Doodle.png"); //à modifier selon l'emplacement de l'image sur votre ordi et le nom
		ImageIcon imagePallier= new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\pallier.png");
		ImageIcon imageFond = new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\Fond.png");

		JLabel labelDoodle = new JLabel(imageDoodle);
		JLabel labelPallier= new JLabel(imagePallier) ;

		private int x; //postion initiale doodle en largeur
		private int y; // position initiale doodle en hauteur

		final int WIDTH=1000; // initialisation largeur Fenetre de jeu
		final int HEIGHT=2000;// initialisation longeur Fenetre de jeu

		Doodle monDoodle = new Doodle(x,y); //creation objet doodle

		Pallier monPallier= new Pallier(58,15);
		int chrono=0; //initialisation timer


		public FenetreJeu() {
			Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();// la fenêtre s'adapte à la taille de l'écran ordinateur
			Timer mt= new Timer(70,this); // réglage timer // initialisation du timer
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


			JLabel test = new JLabel("pallier" + listePallier.size()); //test nombre de pallier dans la liste
			test.setBounds(0, 0, 100, 200);
			panneauGlobal.add(test);


			//Ajout doodle
			x =  100;
			y =  100 ;
			imageDoodle = new ImageIcon(imageDoodle.getImage().getScaledInstance(monDoodle.getWidth(), monDoodle.getHeigth(), Image.SCALE_DEFAULT));//Redimensionnement de l'image doodle
			labelDoodle.setBounds(x, y, monDoodle.getWidth(), monDoodle.getHeigth());
			labelDoodle.setLayout(null);
			panneauGlobal.add(labelDoodle);


			//ajout d'un palliers postion aléatoire
			double pourcentage = 0.05;
			int calculNbPallier= (int)(HEIGHT*pourcentage);
			for (int i=0 ; i<calculNbPallier; i++) {
				imagePallier = new ImageIcon(imagePallier.getImage().getScaledInstance(monPallier.getWidth(), monPallier.getHeigth(), Image.SCALE_DEFAULT)); // permet de redimensionner le pallier si besoin
				labelPallier= new JLabel (imagePallier);
				int a = (int) (Math.random() * HEIGHT) ;
				int b = (int) (Math.random() * WIDTH);
				labelPallier.setBounds(b, a, monPallier.getWidth(), monPallier.getHeigth());
				labelPallier.setLayout(null);
				panneauGlobal.add(labelPallier);
				listePallier.add(labelPallier);
			}
			// éliminer les palliers qui se superposent


			//Ajout fond
			imageFond = new ImageIcon(imageFond.getImage().getScaledInstance(panneauGlobal.getWidth(), panneauGlobal.getHeight(), Image.SCALE_DEFAULT));	//Redimensionnement de l'image de fond pour ajustement à la fenêtre
			JLabel labelFond = new JLabel(imageFond);
			labelFond.setBounds(0, 0, WIDTH, HEIGHT);
			panneauGlobal.add(labelFond);

			this.setVisible(false);// Pour rendre la fenêtre visible

		}

		public void gravite (){
			int grav = 15;
			int vitesse = monDoodle.getVitesseY();
			vitesse = vitesse + grav;
			y = y + vitesse;
			labelDoodle.setLocation(x, y);

		}

		public void collision (){
			if ((monDoodle.getY()==monPallier.getY())&&(monDoodle.getX()==monPallier.getX())){
				saut();
			}

		}
		public void saut (){
			y=monDoodle.getY()+monDoodle.getVitesseY();
			monDoodle.setLocation(x,y);
		}
		/** saut constant du doodle relié au timer **/


		public void actionPerformed(ActionEvent e) {
			this.setTitle("DoodleJump " + String.valueOf(chrono)); // le chrono s'afficha à coté du titre de la fenêtre de jeu
			gravite();
			//collision();
			chrono++;



		}

		public void keyPressed (KeyEvent e){
			switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					// action fleche droite
					if(x<WIDTH) {
						x = x + 10;
					}else {
						x=0;
					}
					labelDoodle.setLocation(x + 10, y);
					break;
				case KeyEvent.VK_LEFT:
					// action flèche gauche
					if(x>0) {
						x = x - 10;
					}else {
						x=WIDTH;
					}
					labelDoodle.setLocation(x-10 , y );
					break;

				/** appuyer sur la flèche du bas pour faire revenier doodle */
				case KeyEvent.VK_DOWN:
					// action flèche bas
					if(y>HEIGHT) {
						y = 0;
					}
					labelDoodle.setLocation(x , y);
					break;
			}
		}
		/** ces deux là sont pas utiles mais si on le met pas la fonctionnalité keyListener peut pas fonctionner sans **/
		public void keyReleased (KeyEvent e) {

		}
		public void keyTyped (KeyEvent e){
		}

	}