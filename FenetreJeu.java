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

		LinkedList<Pallier> listePallier = new LinkedList<Pallier>(); // initialisation de la liste contenant les palliers

		// Les Widgets à déclarer en dehors du constructeur
		private FenetreMort maFenetreMort;

		ImageIcon imageDoodle = new ImageIcon("C:\\Users\\manon\\IdeaProjects\\doodleJump\\src\\Doodle.png"); //à modifier selon l'emplacement de l'image sur votre ordi et le nom
		ImageIcon imagePallier= new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\pallier.png");
		ImageIcon imageFond = new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\Fond.png");

		/*ImageIcon imageDoodle = new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\Doodle.png"); //à modifier selon l'emplacement de l'image sur votre ordi et le nom
		ImageIcon imagePallier = new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\palier.png");
		ImageIcon imageFond = new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\Fond.png");*/
		
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();// la fenêtre s'adapte à la taille de l'écran ordinateur
		final int HAUTEUR = (int)dimension.getHeight();
		final int WIDTH=HAUTEUR/2; // initialisation largeur Fenetre de jeu
		final int HEIGHT=HAUTEUR;// initialisation longeur Fenetre de jeu

		JLabel labelDoodle = new JLabel(imageDoodle);
		JLabel labelPallier= new JLabel(imagePallier) ;

		private int x=WIDTH/2; //position initiale doodle en largeur
		private int y=HEIGHT/2; // position initiale doodle en hauteur
		private int deltaY=0; // utile pour plateforme de secours

		Doodle monDoodle = new Doodle(x, y, labelDoodle); //creation objet doodle
		Pallier monPallier = new Pallier(x, y, labelPallier); //creation objet pallier

		int hauteurMax= HAUTEUR*2/5;
		int score;
		int scoreP = 1000000;


	public FenetreJeu() {

			Timer mt= new Timer(40,this); //initialisation du timer
			mt.start();

			this.setSize(WIDTH, HEIGHT);
			this.setLocationRelativeTo(null);// Place la fenêtre au centre de l'écran
			this.setResizable(false);// Empêche le redimensionnement de la fenêtre
			this.setTitle("DoodleJump ");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Pour permettre la fermeture de la fenêtre lors de l'appui sur la croix rouge
			addKeyListener(this);// connecte la fenêtre de jeu à keylistener

			this.add(labelDoodle);//affichage Doodle
			
			//ajout des palliers position aléatoire plateau initial
			double pourcentage = 0.04;
			int calculNbPallier= (int)(HEIGHT*pourcentage);
			for (int i=0 ; i<calculNbPallier; i++) {
				imagePallier = new ImageIcon(imagePallier.getImage().getScaledInstance(monPallier.width, monPallier.height, Image.SCALE_DEFAULT)); // permet de redimensionner le pallier si besoin
				labelPallier= new JLabel (imagePallier);
				int a = (int) (Math.random() * (HEIGHT-15));
				int b = (int) (Math.random() * (WIDTH-58));
				monPallier = new Pallier(b, a, labelPallier);
				this.add(monPallier.support);
				listePallier.add(monPallier);
			}

			//Ajout fond
			imageFond = new ImageIcon(imageFond.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT));	//Redimensionnement de l'image de fond pour ajustement à la fenêtre
			JLabel labelFond = new JLabel(imageFond);
			labelFond.setBounds(0, 0, WIDTH, HEIGHT);
			this.add(labelFond);
			this.setVisible(false);// Pour rendre la fenêtre visible

		}
		/** foireux**/
		public void decollerPallier(){
			for (int i =0; i< listePallier.size()-1 ; i++){
				if( Math.abs(listePallier.get(i).x-listePallier.get(i+1).x)<60){
					listePallier.get(i).setX(listePallier.get(i).x+100);
				}
				if (Math.abs(listePallier.get(i).y-listePallier.get(i+1).y)<20){
					listePallier.get(i).setY(listePallier.get(i).y-30);
				}
			}
		}

		public void pallierDeSecours() {
			if (deltaY >100) {
			}
		}
		public void collision() {
			for (int i = 0; i < listePallier.size(); i++) {
				if (monDoodle.vitesseY > 0) {
					if (((monDoodle.y + monDoodle.height) < (listePallier.get(i).y + listePallier.get(i).height + 5)) && ((monDoodle.y + monDoodle.height + 5) > (listePallier.get(i).y))) { //test des Y
						if (((monDoodle.x + monDoodle.width - 22) > (listePallier.get(i).x)) && ((monDoodle.x) < (listePallier.get(i).x + listePallier.get(i).width))) {             //test des x
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

	/** déplace les palliers vers le bas proportionellement à la hauteur du doodle
	 Diminue le nombre de palliers en fonction du score
	 **/
	public void bougeEcran(){
			 if(monDoodle.y< hauteurMax ) {
				 int depassement = hauteurMax - monDoodle.y;
				 monDoodle.setY(hauteurMax);
				 for (int i = 0; i < listePallier.size(); i++) {
					 listePallier.get(i).setY(listePallier.get(i).y + depassement);
					 listePallier.get(i).support.setLocation(listePallier.get(i).x, listePallier.get(i).y + depassement);
					 double a =Math.random();
					 double test =1;
					 for (int j= 3000 ;j< scoreP ;j++){
						 if ((listePallier.get(i).y > HEIGHT)&&(score < j)){
							 int b =(int) (Math.random()*80);
							 listePallier.get(i).setY(-20-b);
							 listePallier.get(i).setX((int) (Math.random() * (WIDTH - 58)));
						 }
						 if ((listePallier.get(i).y > HEIGHT)&&(score > j)&&(score< j+ 3000)){
							if (a < test){
								int b =(int) (Math.random()*80);
								listePallier.get(i).setY(-20-b);
								listePallier.get(i).setX((int) (Math.random() * (WIDTH - 58)));
							}else {
								listePallier.remove(i);
							}
							 //deltaY=0;
						 }
						 j=j+3000;
						 test= test-0.05;
					 }
				 }
				 score += depassement;
				 deltaY+=depassement;
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
			monDoodle.tombeDoodle();
			monDoodle.bougeX();
			bougeEcran();
			labelDoodle.setLocation(monDoodle.x,monDoodle.y);
			collision();
			checkSortieEcran();
			checkMort();
			//pallierDeSecours();

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
				/*case KeyEvent.VK_DOWN:
					// action flèche bas
					monDoodle.y= HEIGHT/2;
					monDoodle.x= WIDTH/2;
					monDoodle.vitesseX=0;
					monDoodle.vitesseY=-25;
					monDoodle.setLocation(monDoodle.x , monDoodle.y);
					break;

				/** appuyer sur la flèche du haut pour faire sauter doodle */
					/*case KeyEvent.VK_UP:
					// action flèche haut
					monDoodle.saut();
					break;*/ 
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

