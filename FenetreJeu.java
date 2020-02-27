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
		ImageIcon doodle = new ImageIcon("C:\\Users\\manon\\IdeaProjects\\doodleJump\\src\\Doodle.png");
		JLabel Doodle = new JLabel(doodle);
		private int x = 15 ; //postion initiale en largeur
		private int y= 500 ; // hauteur initiale du doodle
		int newWidth; // initialisation largeur Fenetre de jeu
		int newHeight;// initialisation longeur Fenetre de jeu

		public FenetreJeu() {
			addKeyListener(this); // je sais pas trop à quoi ça sert le code fonctionne sans mais bon
			this.setTitle("DoodleJump ");
			this.setSize(1000, 2000); // choisir taille initiale de la fenêtre
			// Pour placer la fenêtre au centre de l'écran
			//~ this.setLocationRelativeTo(null);
			this.setLocation(0, 0);
			// Pour empêcher le redimensionnement de la fenêtre
			this.setResizable(false);
			// Pour permettre la fermeture de la fenêtre lors de l'appui sur la croix rouge
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			//Ajout doodle
			//Redimensionnement du doodle
			int Width = 60;
			int Height = 60;
			doodle = new ImageIcon(doodle.getImage().getScaledInstance(Width, Height, Image.SCALE_DEFAULT));
			Doodle.setBounds(x, y, 60, 60);
			Doodle.setLayout(null);

			/**
			 Mon panneau Global
			 */
			JPanel panneauGlobal = new JPanel();
			panneauGlobal.setBounds(0, 0, 1000, 2000);
			panneauGlobal.setLayout(null);
			add(panneauGlobal);
			panneauGlobal.add(Doodle);

			//Redimensionnement de l'image de fond pour ajustement à la fenêtre
			ImageIcon fond = new ImageIcon("C:\\Users\\manon\\IdeaProjects\\doodleJump\\src\\Fond.png");
			newWidth = panneauGlobal.getWidth();
			newHeight = panneauGlobal.getHeight();
			fond = new ImageIcon(fond.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT));
			JLabel Fond = new JLabel(fond);
			Fond.setBounds(0, 0, 1000, 2000);
			panneauGlobal.add(Fond);

			// Pour créer la seconde fenêtre qui est invisible à sa création

			//maFenetreDemarrage = new FenetreDemarrage();
			//maFenetreDemarrage.setVisible(true);


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