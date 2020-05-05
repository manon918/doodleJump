import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public  class FenetreJeu extends JFrame implements KeyListener, ActionListener{

		final LinkedList<Palier> LISTEPALIER= new LinkedList<>();
		final LinkedList<Palier> LISTEPALIERSTOCK= new LinkedList<>();

		// à modifier selon l'emplacement des images
		ImageIcon imageDoodle = new ImageIcon("C:\\Users\\manon\\IdeaProjects\\doodleJump\\src\\Doodle.png");
		ImageIcon imageFond = new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\Fond.png");
		ImageIcon imagePalier0= new ImageIcon("C:\\Users\\manon\\DoodleFinal\\src\\palier.png");
		final ImageIcon imagePalier1= new ImageIcon("C:\\Users\\manon\\DoodleFinal\\src\\palier1.png");
		final ImageIcon imagePalier2= new ImageIcon("C:\\Users\\manon\\DoodleFinal\\src\\palier2.png");
		final ImageIcon imagePalier3= new ImageIcon("C:\\Users\\manon\\DoodleFinal\\src\\palier3.png");
		final ImageIcon imagePalier4= new ImageIcon("C:\\Users\\manon\\DoodleFinal\\src\\palier4.png");
		final ImageIcon imagePalier5= new ImageIcon("C:\\Users\\manon\\DoodleFinal\\src\\palier5.png");

		final int HEIGHT = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		final int WIDTH= HEIGHT/2;

		final JLabel LABELDOODLE;
		JLabel labelPalier;
		final Doodle MONDOODLE;
		Palier monPalier;

		private int deltaY=0;
		final int HAUTEURMAX= HEIGHT*2/5;
		int score;
		final Timer MT= new Timer(40,this);

	public FenetreJeu() {

			this.setTitle("DoodleJump");
			this.setSize(WIDTH, HEIGHT);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			addKeyListener(this);
			MT.start();

			imageDoodle = new ImageIcon(imageDoodle.getImage().getScaledInstance((WIDTH/10) ,(HEIGHT/20), Image.SCALE_DEFAULT));
			LABELDOODLE = new JLabel (imageDoodle);
			MONDOODLE= new Doodle(((WIDTH-(WIDTH/10))/2), (HEIGHT+(HEIGHT/20))/2,LABELDOODLE);
			this.add(MONDOODLE.SUPPORT);

			JLabel labelA=new JLabel (imagePalier3);
			Palier palierA =new Palier(HEIGHT/2,-10,labelA,3);
			this.add(palierA.SUPPORT);
			LISTEPALIER.add(palierA);
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
			labelFond.setLayout(null);
			this.add(labelFond);

			this.setVisible(true);
		}

		/**créée et place des palliers de manière aléatoire*/
		public void creationPalier(int a, int b ){
			int s = (int)(95*Math.random());
			int ty = type(s);
			labelPalier = new JLabel (imageType(ty));
			monPalier = new Palier(b, a, labelPalier, ty);
			this.add(monPalier.SUPPORT);
			LISTEPALIER.add(monPalier);
		}

		/** créée des paliers de secours hors de la fenêtres qui descendent lorsque la distance entre le doodle et les paliers est trop grande*/
		public void palierDeSecours() {
			if (deltaY >HEIGHT/6) {
				int a = -20;
				int b = (int) (Math.random() * (WIDTH-58));
				if (LISTEPALIERSTOCK.size ()>=1) {
					LISTEPALIERSTOCK.get(0).setX(b);
					LISTEPALIERSTOCK.get(0).setY(a);
					LISTEPALIERSTOCK.get(0).type=0;
					imagePalier0 = new ImageIcon(imagePalier0.getImage().getScaledInstance(monPalier.width, monPalier.height, Image.SCALE_DEFAULT));
					LISTEPALIERSTOCK.get(0).SUPPORT.setIcon(imagePalier0);
					LISTEPALIER.add(LISTEPALIERSTOCK.get(0));
					LISTEPALIERSTOCK.remove(0);
					deltaY = 0;
				}
			}
		}
		/** détermine la collision entre le doodle et les paliers en fonction de leur type et lance les méthodes de saut , toucher un palier de type 5: c'est perdu*/
		public void collision() {
			for (Palier palier : LISTEPALIER) {
				if (MONDOODLE.vitesseY > 0) {
					if (((MONDOODLE.y + MONDOODLE.height) < (palier.y + 1.25 * palier.height)) && ((MONDOODLE.y + 1.1 * MONDOODLE.height) > (palier.y))) {
						if (((MONDOODLE.x + 0.6 * MONDOODLE.width) > (palier.x)) && ((MONDOODLE.x) < (palier.x + palier.width))) {
							switch (palier.type) {
								case 0:
									MONDOODLE.saut();
									break;
								case 1:
									MONDOODLE.petitSaut();
									break;
								case 2:
									MONDOODLE.moyenSaut();
									break;
								case 3:
									MONDOODLE.superSaut();
									break;
								case 4:
									MONDOODLE.saut();
									palier.x = (int) (Math.random() * (WIDTH -HEIGHT/18));
									palier.y = -1000;
									break;
							}
						}
					}
				}
				if (((MONDOODLE.y ) < (palier.y +0.6*palier.height)) && ((MONDOODLE.y + 0.6*MONDOODLE.height) > (palier.y)) &&
						((MONDOODLE.x + 0.6*MONDOODLE.width) > (palier.x)) && ((MONDOODLE.x) < (palier.x + palier.width)) && palier.type == 5 ) {
						FenetreMort maFenetreMort = new FenetreMort(score);
						maFenetreMort.setVisible(true);
						MT.stop();
						this.setVisible(false);
				}
			}
		}

		/** Détermine si le Doodle est toujours dans l'écran ,permet au doodle et au palier de type 3 la sortie de l'écran d'un côté pour revenir de l'autre*/
		public void checkSortieEcran(){

			if((MONDOODLE.y+MONDOODLE.height)> 1.3*HEIGHT) {
				FenetreMort maFenetreMort = new FenetreMort(score);
				maFenetreMort.setVisible(true);
				MT.stop();
				this.setVisible(false);
			}
			if(MONDOODLE.x>WIDTH) {
				MONDOODLE.x=-MONDOODLE.width;
			}
			if(MONDOODLE.x+MONDOODLE.width<0) {
				MONDOODLE.x=WIDTH;
			}
			for (Palier palier : LISTEPALIER) {
				if ((palier.type == 2) && (palier.x > WIDTH)) {
					palier.setX(-palier.width);
				}
				if ((palier.type == 2) && (palier.x + palier.width < 0)) {
					palier.setX(WIDTH);
				}
			}
		}

	/** déplace les palliers vers le bas proportionellement à la hauteur du doodle
	 Diminue le nombre de palliers en fonction du score, recycle les paliers qui tombe en les ramenant plus haut que l'écran de jeu et changent leurs types
	 */
	public void bougeEcran(){
			 if(MONDOODLE.y< HAUTEURMAX) {
				 int depassement = HAUTEURMAX- MONDOODLE.y;
				 MONDOODLE.setY(HAUTEURMAX);
				 for (int i = 0; i < LISTEPALIER.size(); i++) {
					 LISTEPALIER.get(i).setY(LISTEPALIER.get(i).y + depassement);
					 LISTEPALIER.get(i).SUPPORT.setLocation(LISTEPALIER.get(i).x, LISTEPALIER.get(i).y);
					 double a = Math.random();
					 double prob = 0.90;
					 if (LISTEPALIER.get(i).y > HEIGHT) {
						 if (a < prob) {
							 int b = (int) (-20 - Math.random()*80);
							 int d = (int) (Math.random() * (WIDTH - monPalier.width));
							 LISTEPALIER.get(i).setY(b);
							 LISTEPALIER.get(i).setX(d);
							 if(LISTEPALIER.get(i).type != 5 && LISTEPALIER.get(i).type != 1) {
								 deltaY = 0;
							 }
							 int s = (int)(100*Math.random());
							 int w =  type(s);
							 ImageIcon img = imageType(w);
							 LISTEPALIER.get(i).type=w;
							 LISTEPALIER.get(i).SUPPORT.setIcon(img);
						 } else {
							 LISTEPALIERSTOCK.add(LISTEPALIER.get(i));
							 LISTEPALIER.remove(i);
						 }
					 }
				 }
				 score += depassement;
				 deltaY += depassement;
			 }
	}

	/** renvoie l'image correspondante au type*/
	public ImageIcon imageType(int p){
		ImageIcon imagePalier=imagePalier0;
		switch (p){
			case 0:
			imagePalier = new ImageIcon(imagePalier0.getImage().getScaledInstance(HEIGHT / 18, WIDTH / 29, Image.SCALE_DEFAULT));
			break;
			case 1:
			imagePalier = new ImageIcon(imagePalier1.getImage().getScaledInstance(HEIGHT / 18, WIDTH / 29, Image.SCALE_DEFAULT));
			break;
			case 2:
			imagePalier = new ImageIcon(imagePalier2.getImage().getScaledInstance(HEIGHT / 18, WIDTH / 29, Image.SCALE_DEFAULT));
			break;
			case 3 :
			imagePalier = new ImageIcon(imagePalier3.getImage().getScaledInstance(HEIGHT / 18, WIDTH / 29, Image.SCALE_DEFAULT));
			break;
			case 4:
			imagePalier = new ImageIcon(imagePalier4.getImage().getScaledInstance(HEIGHT / 18, WIDTH / 29, Image.SCALE_DEFAULT));
			break;
			case 5:
			imagePalier = new ImageIcon(imagePalier5.getImage().getScaledInstance(HEIGHT / 18, WIDTH / 29, Image.SCALE_DEFAULT));
			break;
		}
		return imagePalier;
	}

		/** renvoie le type */
		public int type(int p){
			int type=0;
			if ((p>71)&&(p<=76)) {
				type = 1;
			}else if ((p>76)&&(p<=83)) {
				type = 2;
			}else if ((p>83)&&(p<=89)) {
				type = 3;
			}else if ((p>89)&&(p<95)) {
				type = 4;
			}else if (p>95){
				type = 5;
			}return type;
		}

		/** déplace de manière continue les paliers de type 2 vers la gauche ou la droite selon leurs position dans la liste LISTEPALIER*/
		public void bougePalier(){
			int d = 3;
			for(int i=0 ; i<LISTEPALIER.size();i++){
				if ((LISTEPALIER.get(i).type==2)&&(i%2==0)){
					LISTEPALIER.get(i).setX(LISTEPALIER.get(i).x + d);
					LISTEPALIER.get(i).SUPPORT.setLocation(LISTEPALIER.get(i).x,LISTEPALIER.get(i).y );
				}
				else if ((LISTEPALIER.get(i).type==2)&&(i%2!=0)){
					LISTEPALIER.get(i).setX(LISTEPALIER.get(i).x - d);
					LISTEPALIER.get(i).SUPPORT.setLocation(LISTEPALIER.get(i).x,LISTEPALIER.get(i).y );
				}
			}
		}

	/**
	 * Suite à un événement
	 */
		public void actionPerformed(ActionEvent e) {

			this.setTitle("DoodleJump " + score);
			MONDOODLE.SUPPORT.setLocation(MONDOODLE.x,MONDOODLE.y);
			MONDOODLE.tombeDoodle();
			MONDOODLE.bougeX();
			bougeEcran();
			bougePalier();
			collision();
			palierDeSecours();
			checkSortieEcran();
		}
		public void keyPressed (KeyEvent e){
			switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					MONDOODLE.droite= true;
					MONDOODLE.stopDroite= false;
					MONDOODLE.gauche=false;
					MONDOODLE.stopGauche=false;
					break;
				case KeyEvent.VK_LEFT:

					MONDOODLE.gauche= true;
					MONDOODLE.stopGauche= false;
					MONDOODLE.droite= false;
					MONDOODLE.stopDroite= false;
					break;
			}
		}
		public void keyReleased (KeyEvent e) {
			if(e.getKeyCode()== KeyEvent.VK_RIGHT && MONDOODLE.vitesseX>0) {
				MONDOODLE.stopDroite= true;
				MONDOODLE.droite= false;
				MONDOODLE.stopGauche= false;
				MONDOODLE.gauche= false;
			}
			if(e.getKeyCode()== KeyEvent.VK_LEFT && MONDOODLE.vitesseX<0) {
				MONDOODLE.stopGauche= true;
				MONDOODLE.gauche= false;
				MONDOODLE.stopDroite= false;
				MONDOODLE.droite= false;
			}
		}
		public void keyTyped (KeyEvent e){
		}

	}

