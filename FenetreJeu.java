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

	public  class FenetreJeu extends JFrame /*implements ActionListener*/ {

		// Les Widgets à déclarer en dehors du constructeur

		public FenetreJeu() {
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
			ImageIcon doodle = new ImageIcon("C:\\Users\\manon\\IdeaProjects\\doodleJump\\src\\Doodle.png");
			//Redimensionnement du doodle
			int Width = 100;
			int Height = 100;
			doodle = new ImageIcon(doodle.getImage().getScaledInstance(Width,Height,Image.SCALE_DEFAULT));
			JLabel Doodle = new JLabel(doodle);
			Doodle.setBounds(0,0,500,500);
			Doodle.setLayout(null);

			/**
		 * Mon panneau Global
		 */
			JPanel panneauGlobal = new JPanel();
			panneauGlobal.setBounds(0,0,1000,2000);
			panneauGlobal.setLayout(null);
			add(panneauGlobal);
			panneauGlobal.add(Doodle);

			//Redimensionnement de l'image de fond pour ajustement à la fenêtre
			ImageIcon fond = new ImageIcon("C:\\Users\\manon\\IdeaProjects\\doodleJump\\src\\Fond.png");
			int newWidth = panneauGlobal.getWidth() ;
			int newHeight = panneauGlobal.getHeight();
			fond = new ImageIcon(fond.getImage().getScaledInstance(newWidth,newHeight,Image.SCALE_DEFAULT));
			JLabel Fond = new JLabel(fond);
			Fond.setBounds(0,0,1000,2000);
			panneauGlobal.add(Fond);


			this.setVisible(true);
		}
	}




