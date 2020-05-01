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

		LinkedList<Palier> listePalier = new LinkedList<>();
		LinkedList<Palier> listePalierStock = new LinkedList<>();

		/*ImageIcon imageDoodle = new ImageIcon("C:\\Users\\manon\\IdeaProjects\\doodleJump\\src\\Doodle.png"); //à modifier selon l'emplacement de l'image sur votre ordi et le nom
		ImageIcon imagePalier= new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\pallier.png");
		ImageIcon imageFond = new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\Fond.png");*/
		/*ImageIcon imageDoodle = new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\Doodle.png"); //à modifier selon l'emplacement de l'image sur votre ordi et le nom
		ImageIcon imagePalier = new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\palier.png");
		ImageIcon imageFond = new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\Fond.png");*/
		ImageIcon imageDoodle = new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\Doodle.png"); //killian//killian//killian//killian
		ImageIcon imageFond = new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\Fond.png");//killian//killian//killian//killian//killian
		ImageIcon imagePalier0= new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\palier.png");//killian//killian//killian//killian
		ImageIcon imagePalier1= new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\palier1.png");//killian//killian//killian//killian
		ImageIcon imagePalier2= new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\palier2.png");//killian//killian//killian//killian
		ImageIcon imagePalier3= new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\palier3.png");//killian//killian//killian//killian
		ImageIcon imagePalier4= new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\palier4.png");//killian//killian//killian//killian
		ImageIcon imagePalier5= new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\palier5.png");//killian//killian//killian//killian

		final int HEIGHT = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		final int WIDTH= HEIGHT/2;

		JLabel labelDoodle;
		JLabel labelPalier;

		Doodle monDoodle;
		Palier monPalier;

		private int deltaY=0;
		int hauteurMax= HEIGHT*2/5;
		int score;
		Timer mt= new Timer(40,this);


	public FenetreJeu() {
			mt.start();
			this.setTitle("DoodleJump");
			this.setSize(WIDTH, HEIGHT);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			addKeyListener(this);

			imageDoodle = new ImageIcon(imageDoodle.getImage().getScaledInstance((WIDTH/10) ,(HEIGHT/20), Image.SCALE_DEFAULT));
			labelDoodle = new JLabel (imageDoodle);
			monDoodle= new Doodle(((WIDTH-(WIDTH/10))/2), (HEIGHT+(HEIGHT/20))/2,labelDoodle);
			this.add(monDoodle.support);

			double pourcentage = 0.02;
			int calculNbPalier= (int)(HEIGHT*pourcentage);
			for (int i=0 ; i<calculNbPalier; i++) {
				int a = (int) (Math.random() * (HEIGHT - WIDTH/29));
				int b = (int) (Math.random() * (WIDTH -HEIGHT/18));
				creationPalier(a,b);
			}

			imageFond = new ImageIcon(imageFond.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT));
			JLabel labelFond = new JLabel(imageFond);
			labelFond.setBounds(0, 0, WIDTH, HEIGHT);
			this.add(labelFond);

			this.setVisible(true);
		}

		public void creationPalier(int a, int b ){
				double q = 100*Math.random();
				int type =0;
				if(q<75) {
					type = 0;
					imagePalier0 = new ImageIcon(imagePalier0.getImage().getScaledInstance(HEIGHT / 18, WIDTH / 29, Image.SCALE_DEFAULT));
					labelPalier= new JLabel (imagePalier0);
				}else if (q<80) {
					type = 1;
					imagePalier1 = new ImageIcon(imagePalier1.getImage().getScaledInstance(HEIGHT / 18, WIDTH / 29, Image.SCALE_DEFAULT));
					labelPalier= new JLabel (imagePalier1);
				}else if (q<85) {
					type = 2;
					imagePalier2 = new ImageIcon(imagePalier2.getImage().getScaledInstance(HEIGHT / 18, WIDTH / 29, Image.SCALE_DEFAULT));
					labelPalier= new JLabel (imagePalier2);
				}else if (q<90) {
					type = 3;
					imagePalier3 = new ImageIcon(imagePalier3.getImage().getScaledInstance(HEIGHT / 18, WIDTH / 29, Image.SCALE_DEFAULT));
					labelPalier= new JLabel (imagePalier3);
				}else if (q<95) {
					type = 4;
					imagePalier4 = new ImageIcon(imagePalier4.getImage().getScaledInstance(HEIGHT / 18, WIDTH / 29, Image.SCALE_DEFAULT));
					labelPalier= new JLabel (imagePalier4);
				}else if (q<101){
					type = 5;
					imagePalier5 = new ImageIcon(imagePalier5.getImage().getScaledInstance(HEIGHT / 18, WIDTH / 29, Image.SCALE_DEFAULT));
					labelPalier= new JLabel (imagePalier5);
				}

				monPalier = new Palier(b, a, labelPalier, type);
				this.add(monPalier.support);
				listePalier.add(monPalier);
		}

	/** ne fonctionne pas
	 */
		public boolean checkPalier(int a ,int b){
			boolean test= true;
			for (int i = 0; i < listePalier.size(); i++) {
				if (((b+ monPalier.height) > (listePalier.get(i).y)) && (b < (listePalier.get(i).y+ monPalier.height))) {
					if (((a + monPalier.width) > (listePalier.get(i).x)) && (a < (listePalier.get(i).x+monPalier.width))) {
						test = false;
					}
				}
			}
			return test;
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
			boolean b = false;
			for (Palier palier : listePalier) {
				if (monDoodle.vitesseY > 0) {
					if (((monDoodle.y + monDoodle.height) < (palier.y + 1.25*palier.height)) && ((monDoodle.y + 1.1*monDoodle.height) > (palier.y))) {
						if (((monDoodle.x + 0.6*monDoodle.width) > (palier.x)) && ((monDoodle.x) < (palier.x + palier.width))) {
							switch (palier.type) {
								case 0:
									monDoodle.saut();
									break;
								case 1:
									monDoodle.saut();
									break;
								case 2:
									monDoodle.saut();
									break;
								case 3:
									monDoodle.superSaut();
									break;
								case 4:
									monDoodle.saut();
									b = true;
									//int i
									break;
							}

						}
					}
				} if (((monDoodle.y ) < (palier.y +0.6*palier.height)) && ((monDoodle.y + 0.6*monDoodle.height) > (palier.y)) &&
					((monDoodle.x + 0.6*monDoodle.width) > (palier.x)) && ((monDoodle.x) < (palier.x + palier.width)) && palier.type == 5 ){
					FenetreMort maFenetreMort = new FenetreMort(score);
					maFenetreMort.setVisible(true);
					mt.stop();
					this.setVisible(false);
				}
			}
			//if(b){
				//listePalierStock.add(listePalier.get(i));
				//listePalier.remove(i);
			//}
		}

		/** permet la sortie de l'écran d'un côté pour revenir de l'autre*/
		public void checkSortieEcran(){
			if(monDoodle.x>WIDTH) {
				monDoodle.x=0;
			}
			if(monDoodle.x+monDoodle.width<0) {
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

					 double a = Math.random();
					 double prob = 0.90;
					 if (listePalier.get(i).y > HEIGHT) {
						 if (a < prob) {
							 int b = (int) (-20 - Math.random()*80);
							 int d = (int) (Math.random() * (WIDTH - monPalier.width));
							 while (!checkPalier(d, b)) {
								 b = (int) (-20 - Math.random()*80);
								 d = (int) (Math.random() * (WIDTH - monPalier.width));
								 System.out.println(b);    //pour voir
								 System.out.println(d);
							 }
							 listePalier.get(i).setY(b);
							 listePalier.get(i).setX(d);
							 if(listePalier.get(i).type != 5) {
								 deltaY = 0;
							 }
						 } else {
							 listePalierStock.add(listePalier.get(i));
							 listePalier.remove(i);
						 }
					 }
				 }

				 score += depassement;
				 deltaY += depassement;
			 }
		 }

		public void checkMort() {
			if((monDoodle.y+monDoodle.height)> 2*HEIGHT) {
				FenetreMort maFenetreMort = new FenetreMort(score);
				maFenetreMort.setVisible(true);
				mt.stop();
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
			for (Palier palier : listePalier) {
				if (palier.type == 2) {
					palier.bougePalierX(mt);
				} if (palier.type == 1) {
					palier.bougePalierY(mt);
				}
			}

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

