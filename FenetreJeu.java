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

	public  class FenetreJeu extends JFrame implements KeyListener, ActionListener{

		// Les Widgets à déclarer en dehors du constructeur

		ImageIcon ImageDoodle = new ImageIcon("C:\\Users\\manon\\IdeaProjects\\doodleJump\\src\\Doodle.png"); //à modifier selon l'emplacement de l'image sur votre ordi et le nom
		ImageIcon ImagePallier= new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\pallier.png");
		ImageIcon ImageFond = new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\Fond.png");
		JLabel Doodle = new JLabel(ImageDoodle);
		JLabel Palliera ;
		private int x; //postion initiale en largeur
		private int y; // hauteur initiale du doodle
		int newWidth; // initialisation largeur Fenetre de jeu
		int newHeight;// initialisation longeur Fenetre de jeu
		doodle monDoodle = new doodle(x,y);
		element monPallier = new element(58,15);
		int chrono=0;


		public FenetreJeu() {
		/** la fenêtre s'adapte à la taille de l'écran ordinateur*/
			Timer mt= new Timer(1000,this);
			mt.start();
			Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			int hauteur = (int)dimension.getHeight();
			addKeyListener(this);// je sais pas trop à quoi ça sert le code fonctionne sans mais bon
			this.setTitle("DoodleJump ");
			this.setSize(hauteur/2, hauteur); // choisir taille initiale de la fenêtre
			// Pour placer la fenêtre au centre de l'écran
			//~ this.setLocationRelativeTo(null);
			this.setLocation(0, 0);
			// Pour empêcher le redimensionnement de la fenêtre
			this.setResizable(false);
			// Pour permettre la fermeture de la fenêtre lors de l'appui sur la croix rouge
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			/**
			 Mon panneau Global
			 */
			JPanel panneauGlobal = new JPanel();
			panneauGlobal.setBounds(0, 0, 1000, 2000);
			panneauGlobal.setLayout(null);
			add(panneauGlobal);
			newWidth = panneauGlobal.getWidth();
			newHeight = panneauGlobal.getHeight();


			//Ajout doodle
			//Redimensionnement du doodle
			x = newWidth/2;
			y = newHeight/4;

			ImageDoodle = new ImageIcon(ImageDoodle.getImage().getScaledInstance(monDoodle.getWidth(), monDoodle.getHeigth(), Image.SCALE_DEFAULT));
			Doodle.setBounds(x, y, 60, 60);
			Doodle.setLayout(null);
			for (int i =0; i<10; i ++){
				y=y+monDoodle.getVitesseY();
				Doodle.setLocation(x, y);

			}

			//ajout d'un pallier fixe
			//Redimensionnement des palliers
			ImagePallier= new ImageIcon(ImagePallier.getImage().getScaledInstance(monPallier.getWidth(), monPallier.getHeigth(), Image.SCALE_DEFAULT)); // permet de redimensionner le pallier si besoin
			JLabel Pallierf = new JLabel(ImagePallier);
			Pallierf.setBounds(x-5, y+100, 58, 15);
			Pallierf.setLayout(null);


			//ajout d'un palliers postion aléatoire
			//Redimensionnement des palliers
			// éliminer les palliers qui se superposent
			double pourcentage = 0.05;
			int calculNbPallier= (int)(panneauGlobal.getHeight()*pourcentage);
			for (int i=0 ; i<calculNbPallier; i++) {
				ImagePallier = new ImageIcon(ImagePallier.getImage().getScaledInstance(monPallier.getWidth(), monPallier.getHeigth(), Image.SCALE_DEFAULT)); // permet de redimensionner le pallier si besoin
				Palliera = new JLabel(ImagePallier);
				int a = (int) (Math.random() * (panneauGlobal.getHeight()) - 60) ;
				int b = (int) (Math.random() * (panneauGlobal.getWidth()) - 15);
				Palliera.setBounds(b, a, monPallier.getWidth(), monPallier.getHeigth());
				Palliera.setLayout(null);
				panneauGlobal.add(Palliera);
			}


			panneauGlobal.add(Doodle);
			panneauGlobal.add(Pallierf);


			//Redimensionnement de l'image de fond pour ajustement à la fenêtre

			//ImageIcon fond = new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\Fond.png");
			ImageFond = new ImageIcon(ImageFond.getImage().getScaledInstance(panneauGlobal.getWidth(), panneauGlobal.getHeight(), Image.SCALE_DEFAULT));
			JLabel Fond = new JLabel(ImageFond);
			Fond.setBounds(0, 0, newWidth, newHeight);
			panneauGlobal.add(Fond);


			//Test ajout plusieurs palliers aléatoirement le long de la fenêtre de jeu immobile


			// Pour rendre la fenêtre visible
			this.setVisible(false);

		}
		/** saut constant du doodle relié au timer **/
		public void actionPerformed(ActionEvent e) {
			this.setTitle("DoodleJump " + String.valueOf(chrono)); // le chrono s'afficha à coté du titre de la fenêtre de jeu
			chrono++;
			if (monDoodle.y == monPallier.y) { // rebond sur un pallier ne fonctionne pas
				y = y - monDoodle.getVitesseY();
				Doodle.setLocation(x, y);
			}
		}

		public void keyPressed (KeyEvent e){
			switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					// action fleche droite
					if(x<newWidth) {
						x = x + 10;
					}else {
						x=0;
					}
					Doodle.setLocation(x + 10, y);
					break;
				case KeyEvent.VK_LEFT:
					// action flèche gauche
					if(x>0) {
						x = x - 10;
					}else {
						x=newWidth;
					}
					Doodle.setLocation(x-10 , y );
					break;
			}
		}
		/** ces deux là sont pas utiles mais si on le met pas la fonctionnalité keyListener peut pas fonctionner **/
		public void keyReleased (KeyEvent e) {

		}
		public void keyTyped (KeyEvent e){
		}

	}