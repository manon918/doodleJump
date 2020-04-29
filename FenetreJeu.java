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
//import com.sun.org.apache.xalan.internal.xsltc.dom.AbsoluteIterator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;


public  class FenetreJeu extends JFrame implements KeyListener, ActionListener{

		LinkedList<Palier> listePalier = new LinkedList<Palier>(); // initialisation de la liste contenant les palliers
		LinkedList<Palier> listePalierStock = new LinkedList<Palier>(); // initialisation de la liste contenant les palliers

		// Les Widgets à déclarer en dehors du constructeur
		private FenetreMort maFenetreMort;

		ImageIcon imageDoodle = new ImageIcon("C:\\Users\\manon\\IdeaProjects\\doodleJump\\src\\Doodle.png"); //à modifier selon l'emplacement de l'image sur votre ordi et le nom
		ImageIcon imagePalier= new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\pallier.png");
		ImageIcon imageFond = new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\Fond.png");
		/*ImageIcon imageDoodle = new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\Doodle.png"); //à modifier selon l'emplacement de l'image sur votre ordi et le nom
		ImageIcon imagePalier = new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\palier.png");
		ImageIcon imageFond = new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\Fond.png");*/
		

		final int HEIGHT = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();// la fenêtre s'adapte à la taille de l'écran ordinateur
		final int WIDTH= HEIGHT/2; // initialisation largeur Fenetre de jeu

		JLabel labelDoodle = new JLabel(imageDoodle);
		JLabel labelPalier= new JLabel(imagePalier) ;

		private int x=WIDTH/2; //position initiale doodle en largeur
		private int y=HEIGHT/2; // position initiale doodle en hauteur

		Doodle monDoodle = new Doodle(x, y, labelDoodle); //creation objet doodle
		Palier monPalier=new Palier(x, y, labelPalier);

	private int deltaY=0; // utile pour plateforme de secours
		int hauteurMax= HEIGHT*2/5;
		int score;
		int scoreP = 1000000;
		Timer mt= new Timer(40,this); //initialisation du timer


	public FenetreJeu() {
			mt.start();
			this.setTitle("DoodleJump");
			this.setSize(WIDTH, HEIGHT);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			addKeyListener(this);
			this.add(monDoodle.support);

			double pourcentage = 0.04;
			int calculNbPalier= (int)(HEIGHT*pourcentage);
			for (int i=0 ; i<calculNbPalier; i++) {
				int a = (int) (Math.random() * (HEIGHT - 15));
				int b = (int) (Math.random() * (WIDTH - 58));
				creationPalier(a,b);
			}

			imageFond = new ImageIcon(imageFond.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT));	//Redimensionnement de l'image de fond pour ajustement à la fenêtre
			JLabel labelFond = new JLabel(imageFond);
			labelFond.setBounds(0, 0, WIDTH, HEIGHT);
			this.add(labelFond);

			this.setVisible(true);
		}

		public void creationPalier(int a, int b ){
				imagePalier = new ImageIcon(imagePalier.getImage().getScaledInstance(monPalier.width, monPalier.height, Image.SCALE_DEFAULT)); // permet de redimensionner le pallier si besoin
				labelPalier= new JLabel (imagePalier);
				monPalier = new Palier(b, a, labelPalier);
				this.add(monPalier.support);
				listePalier.add(monPalier);
		}

	/** ne fonctionne pas
	 */
		public void CheckPallier(int a,int b){
			boolean test;
			for (int i =0; i< listePalier.size()-1 ; i++){
				if (( Math.abs(listePalier.get(i).x-listePalier.get(i+1).x)<60)&& (Math.abs(listePalier.get(i).y-listePalier.get(i+1).y)<20)){
					listePalier.get(i).setX(listePalier.get(i).x+100);
					listePalier.get(i).setY(listePalier.get(i).y-30);
				}
			}
		}

		/** créée des paliers de secours hors de la fenêtres qui descendent lorsque la distance entre le doodle et les paliers existant
		 *  est supérieure à la distance parcouru par un saut (en hauteur) */
		public void palierDeSecours() {
			if (deltaY >200) {
				int a = -20;
				int b = (int) (Math.random() * (WIDTH-58));
				if (listePalierStock.size ()>=1) {
					listePalierStock.get(0).setX(b);
					listePalierStock.get(0).setY(a);
					listePalier.add(listePalierStock.get(0));
					listePalierStock.remove(0);
					deltaY = 0;
				}
			}
		}

		/** détermine la collision entre le doodle et les paliers et lance la méthode saut*/
		public void collision() {
			for (int i = 0; i < listePalier.size(); i++) {
				if (monDoodle.vitesseY > 0) {
					if (((monDoodle.y + monDoodle.height) < (listePalier.get(i).y + listePalier.get(i).height + 5)) && ((monDoodle.y + monDoodle.height + 5) > (listePalier.get(i).y))) { //test des Y
						if (((monDoodle.x + monDoodle.width - 22) > (listePalier.get(i).x)) && ((monDoodle.x) < (listePalier.get(i).x + listePalier.get(i).width))) {             //test des x
							monDoodle.saut();
						}
					}
				}
			}
		}

		/** permet la sortie de l'écran d'un côté pour revenir de l'autre*/
		public void checkSortieEcran(){
			if(monDoodle.x>WIDTH) {
				monDoodle.x=0;
			}
			if(monDoodle.x<0) {
				monDoodle.x=WIDTH;
			}
		}

	/** déplace les palliers vers le bas proportionellement à la hauteur du doodle
	 Diminue le nombre de palliers en fonction du score
	 */
	public void bougeEcran(){
			 if(monDoodle.y< hauteurMax ) {
				 int depassement = hauteurMax - monDoodle.y;
				 monDoodle.setY(hauteurMax);
				 for (int i = 0; i < listePalier.size(); i++) {
				 	listePalier.get(i).setY(listePalier.get(i).y + depassement);
				 	listePalier.get(i).support.setLocation(listePalier.get(i).x, listePalier.get(i).y);

				 	double a =Math.random();
				 	double prob= 0.80;
				 	if ((listePalier.get(i).y > HEIGHT)&&(score < 3000)){
				 		int b =(int) (Math.random()*80);
				 		listePalier.get(i).setY(-20-b);
				 		listePalier.get(i).setX((int) (Math.random() * (WIDTH - 58)));
				 		deltaY=0;
				 	}
				 	if ((listePalier.get(i).y > HEIGHT)&&(score>3000)){
				 		if (a < prob){
				 			int b =(int) (Math.random()*80);
				 			listePalier.get(i).setY(-20-b);
				 			listePalier.get(i).setX((int) (Math.random() * (WIDTH - 58)));
				 			deltaY=0;
				 		}else {
				 			listePalierStock.add(listePalier.get(i));
				 			listePalier.remove(i);
				 		}
				 	}
				 }
				 score += depassement;
				 deltaY+= depassement;
			 }
		 }

		public void checkMort() {
			if((monDoodle.y+monDoodle.height)> 2*HEIGHT) {
				maFenetreMort = new FenetreMort(score);
				monDoodle.y=0;// sinon la fenêtre s'ouvre constamment faut trouve un moyen d'arrêter totalement la fenêtre jeu
				maFenetreMort.setVisible(true);
				this.setVisible(false);
			}
		}

		public void actionPerformed(ActionEvent e) {
			this.setTitle("DoodleJump " + score); // le chrono s'afficha à coté du titre de la fenêtre de jeu
			monDoodle.support.setLocation(monDoodle.x,monDoodle.y);
			monDoodle.tombeDoodle();
			monDoodle.bougeX();
			bougeEcran();
			collision();
			checkSortieEcran();
			checkMort();
			palierDeSecours();

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


				 //appuyer sur la flèche du haut pour faire sauter doodle à supprimer
					case KeyEvent.VK_UP:
					// action flèche haut
					monDoodle.saut();
					break;
			}
		}
		public void keyReleased (KeyEvent e) {
			if(e.getKeyCode()== KeyEvent.VK_RIGHT && monDoodle.vitesseX>0) {
				// action relachement fleche droite
				monDoodle.stopDroite= true;
				monDoodle.droite= false;
				monDoodle.stopGauche= false;
				monDoodle.gauche= false;
			}
			if(e.getKeyCode()== KeyEvent.VK_LEFT && monDoodle.vitesseX<0) {
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

