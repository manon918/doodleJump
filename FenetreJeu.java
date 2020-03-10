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
import java.util.*;


	public  class FenetreJeu extends JFrame implements KeyListener{

		// Les Widgets à déclarer en dehors du constructeur
		// ImageIcon doodle = new ImageIcon("C:\\Users\\manon\\IdeaProjects\\doodleJump\\src\\Doodle.png");  //Manon //à modifier selon l'emplacement de l'image sur votre ordi et le nom
		ImageIcon doodle1 = new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\Doodle.png");  //killian               //à modifier selon l'emplacement de l'image sur votre ordi et le nom
		// ImageIcon pallier= new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\pallier.png");	// Manon			
		ImageIcon pallier= new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\p-green.png"); // Killian              //à modifier selon l'emplacement de l'image sur votre ordi et le nom
		JLabel Doodle = new JLabel(doodle1);
		private int x; //position initiale en largeur
		private int y; // hauteur initiale du doodle
		int newWidth; // initialisation largeur Fenetre de jeu
		int newHeight;// initialisation longeur Fenetre de jeu

		public FenetreJeu() {
			Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			int hauteur = (int)dimension.getHeight(); 
			
			
			addKeyListener(this); // je sais pas trop à quoi ça sert le code fonctionne sans mais bon
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
			
			
			
			panneauGlobal.setBounds(0, 0, hauteur, hauteur/3);
			panneauGlobal.setLayout(null);
			add(panneauGlobal);
			//newWidth = panneauGlobal.getWidth();
			//newHeight = panneauGlobal.getHeight();


			//Ajout doodle
			//Redimensionnement du doodle
			
			x = newWidth/2;
			y = newHeight/4;
			doodle monDoodle = new doodle(x,y);
			doodle1 = new ImageIcon(doodle1.getImage().getScaledInstance(monDoodle.getWidth(), monDoodle.getHeigth(), Image.SCALE_DEFAULT));
			Doodle.setBounds(x, y, 60, 60);
			Doodle.setLayout(null);


			//ajout d'un pallier fixe
			//Redimensionnement des palliers
			pallier= new ImageIcon(pallier.getImage().getScaledInstance(58, 15, Image.SCALE_DEFAULT)); // permet de redimensionner le pallier si besoin
			JLabel Pallierf = new JLabel(pallier);
			Pallierf.setBounds(x-5, y+100, 58, 15);
			Pallierf.setLayout(null);


			//ajout d'un palliers position aléatoire
			//Redimensionnement des palliers
			double pourcentage = 0.1;
			int calculNbPallier= (int)(panneauGlobal.getHeight()*pourcentage);
			for (int i=0 ; i<calculNbPallier; i++) {
				pallier = new ImageIcon(pallier.getImage().getScaledInstance(58, 15, Image.SCALE_DEFAULT)); // permet de redimensionner le pallier si besoin
				JLabel Palliera = new JLabel(pallier);
				int a = (int) (Math.random() * 1000 - 1); // (panneauGlobal.getHeight()));
				int b = (int) (Math.random() * 900 - 1); //(panneauGlobal.getWidth()));
				Palliera.setBounds(b, a, 100, 100);
				Palliera.setLayout(null);
				panneauGlobal.add(Palliera);
			}

			panneauGlobal.add(Doodle);
			panneauGlobal.add(Pallierf);


			//Redimensionnement de l'image de fond pour ajustement à la fenêtre
			//ImageIcon fond = new ImageIcon("C:\\Users\\manon\\IdeaProjects\\doodleJump\\src\\Fond.png");		//à modifier selon l'emplacement de l'image sur votre ordi et le nom
			ImageIcon fond = new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\Fond.png");       		//à modifier selon l'emplacement de l'image sur votre ordi et le nom
			fond = new ImageIcon(fond.getImage().getScaledInstance(hauteur/2, hauteur, Image.SCALE_DEFAULT));
			JLabel Fond = new JLabel(fond);
			Fond.setBounds(0, 0, newWidth, newHeight);
			panneauGlobal.add(Fond);


			//Test ajout plusieurs palliers aléatoirement le long de la fenêtre de jeu immobile


			// Pour rendre la fenêtre visible
			this.setVisible(false);
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
