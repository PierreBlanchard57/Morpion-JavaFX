

	import java.util.ArrayList;
	import java.util.Random;
import java.util.Vector;

	
	public class GestionJeu {

		//Matrice contenant les chiffres répartis selon la règle du carré magique :
		//la somme des lignes, des colonnes et des diagonales est toujours égale à 15
		//pour chaque case on stocke le chiffre et son propriétaire (0 chiffre disponible, 1 chiffre appartenant au joueur 1, 2 chiffre appartenant à l'ordinateur ou joueur 2
		private Case [][] _carreMagique;
		
		//répartition des chiffres dans le carré magique : l'indice du tableau est le chiffre et son contenu est un point indiquant n°col, n°ligne dans le carré magique
		private  Place[] _placeChiffres;

		//Chiffre choisi par le joueur
		private int _nbJoueur;
		
		//Chiffre choisi par l'ordinateur
		private int _nbOrdi;
		
		//Nombre gagnant
		private int _nbGagnant;
		
		//Générateur de nombre aléatoire
		private Random _alea; 

		//Nom du joueur
		private String _nomJoueur;
	 
		/*pour générer les scénarios	 
		private int _nb;
		private int [] _aleaS3 = {3,7,1} ;
		private int [] _aleaS4 = {1,3,6} ;
		private int [] _aleaS5 = {9,1} ;
		private int [] _aleaS6 = {5,3,4,6,9,3,7,5,1,4} ;
		*/

		///////////////////////////////////////////////////////////////////////////////////////////////
	    //Constructeur
		///////////////////////////////////////////////////////////////////////////////////////////////
	    GestionJeu() {
	    	this._nbGagnant = 15;
	    	this._nbJoueur = 0;
	    	this._nbOrdi = 0;
	    	this._alea = new Random();
	    	this._carreMagique = new Case[3][3];
	    	this._placeChiffres = new Place[9];
	    	    	
	    	//indique que tous les chiffres sont disponibles
	    	// et les place dans le carré magique
	    	InitialiseChiffres();
	    	
	    	this._nomJoueur = "";
	    	
	    	//pour les scénarios
	    	//this._nb=0;
	    }
		
		///////////////////////////////////////////////////////////////////////////////////////////////
	    //Accesseurs/modifieurs
		///////////////////////////////////////////////////////////////////////////////////////////////

	    /**
		 * @return the _carreMagique
		 */
		public Case[][] get_carreMagique() {
			return _carreMagique;
		}


		/**
		 * @param _carreMagique the _carreMagique to set
		 */
		public void set_carreMagique(Case[][] _carreMagique) {
			this._carreMagique = _carreMagique;
		}

		/**
		 * @return the _placeChiffres
		 */
		public Place[] get_placeChiffres() {
			return _placeChiffres;
		}


		/**
		 * @return the _nbJoueur
		 */
		public int get_nbJoueur() {
			return _nbJoueur;
		}


		/**
		 * @param _nbJoueur the _nbJoueur to set
		 */
		public void set_nbJoueur(int _nbJoueur) {
			this._nbJoueur = _nbJoueur;
		}


		/**
		 * @return the _nbOrdi
		 */
		public int get_nbOrdi() {
			return _nbOrdi;
		}

		/**
		 * @param _nbOrdi the _nbOrdi to set
		 */
		public void set_nbOrdi(int _nbOrdi) {
			this._nbOrdi = _nbOrdi;
		}

		/**
		 * @return the _nbGagnant
		 */
		public int get_nbGagnant() {
			return _nbGagnant;
		}

		
		/**
		 * @return the _alea
		 */
		public Random get_alea() {
			return _alea;
		}

		/**
		 * @param _alea the _alea to set
		 */
		public void set_alea(Random _alea) {
			this._alea = _alea;
		}

		/**
		 * @return the _nomJoueur
		 */
		public String get_nomJoueur() {
			return _nomJoueur;
		}

		/**
		 * @param _nomJoueur the _nomJoueur to set
		 */
		public void set_nomJoueur(String _nomJoueur) {
			this._nomJoueur = _nomJoueur;
		}

		
		///////////////////////////////////////////////////////////////////////////////////////////////
	    //Gestion du jeu
		///////////////////////////////////////////////////////////////////////////////////////////////

		/**
		 * Initialise le carré magique:
		 *  - chaque chiffre est réparti de sorte que la somme des lignes/colonnes/diagonales donne 15
		 *  - chaque case est par défaut disponible pour les joueurs, donc proprietaire=0  
		 * 
		 * Initialise _placeChiffres pour indiquer pour chaque chiffre sa place dans le carré magique
		 *     0 1 2
		 *   --------->col
		 * 0 | 4 9 2
		 * 1 | 3 5 7
		 * 2 | 8 1 6
		 *   v
		 *  lig 
		 * 
		 *  L'indice i du tableau _placeChiffres correspond au chiffre i+1
		 *  Le point p stocké pour chaque indice correspond à n° col, n° ligne du chiffre dans le carré magique.
		 *  Par exemple, le chiffre 3 est représenté par la case n°2 du tableau et stocke la place (1,0)
		 */		 
		public void InitialiseChiffres() { 
		
			int i, j;
			for (i=0;i<3;i++)
				for (j=0;j<3;j++)
				{
					this._carreMagique[i][j] = new Case(0,0);
				}
	
			
			i = 2; j = 1; /* i = n - 1; j = n / 2; */
			for (int k = 1; k <= 9; k++) { /*for (int k = 1; k <=  n * n; ++k) {*/
				this._carreMagique[i][j].set_chiffre(k);
				this._placeChiffres[k-1] = new Place (i,j);
			    if ((k % 3) == 0)  /* if ((k % n) == 0)*/
			    	i = i - 1;
			    else {
			    	i = (i + 1) % 3; /*i = (i + 1) % n;*/ 
			        j = (j + 1) % 3; /*j = (j + 1) % n;*/
			    }	
			}
		}
	
		/**
	 	 * Le carré magique est réinitialisé : les cases sont toutes rendues disponibles pour le joueur ou l'ordinateur
		 */

		public void RendreChiffresDisponibles() {
			for (int i=0;i<3;i++) {
				for (int j=0; j<3;j++) {
					this._carreMagique[i][j].set_proprietaire(0);
				}
			}		
		}
		/**
		 * Le jeu est réinitialisé : aucune case du carré magique n'est encore choisie par le joueur ou l'ordinateur
		 */
		public void Init() {
	    	this._nbJoueur = 0;
	    	this._nbOrdi = 0;
	    	this._alea = new Random();
	    	RendreChiffresDisponibles();
		}
		
	
		/**
		 * indique si le chiffre c peut être choisi par le joueur ou l'ordinateur
		 */
		public boolean EstChiffreDispo(int c) {
			return (this._carreMagique[this._placeChiffres[c-1].get_lig()][this._placeChiffres[c-1].get_col()].get_proprietaire()==0);
		}
		
		/**
		 * indique si le chiffre se trouvant à la position (i,j) peut être choisi par le joueur ou l'ordinateur
		 */
		public boolean EstChiffreDispo(int i, int j) {
			return(this._carreMagique[i][j].get_proprietaire()==0);
		}

		
		/**
		 * indique si le chiffre se trouvant à la position (i,j) a été choisi par le joueur
		 */
		public boolean EstChiffreJoueur(int i, int j, int n) {
			return (this._carreMagique[i][j].get_proprietaire()==n);
			
		}
		
		
		
		/**
		 * 
		 * @return true s'il reste des chiffres disponibles
		 */
		public boolean ChiffresDispos( ) {
			boolean trouve=false;
			int i, j;
			
			i=0;
			do {
				j=0;
				do {
					trouve = EstChiffreDispo(i, j);	
					j++;
				}while ((!trouve) && (j<3));
				i++;
			}while ((!trouve) && (i<3));
			return trouve;
		}
		
		
		
		/**
		 * 
		 * @return un tableau contenant la liste des chiffres encore disponibles
		 */
		public ArrayList<Integer> DonneChiffresDispos() {
			ArrayList<Integer> chDispos = new ArrayList<Integer>();
			
			for (int i=0;i<3;i++) {
				for (int j=0; j<3;j++) {
					if (EstChiffreDispo(i, j)) {
						chDispos.add(this._carreMagique[i][j].get_chiffre());
					}
				}
			}
			return chDispos;
		}
		
		/**
		 * 
		 * @return un tableau contenant la liste des chiffres choisis par le joueur n (1 pour le joueur 1 et 2 pour l'ordinateur/joueur2
		 * 
		 */
		public ArrayList<Integer> DonneChiffresJoueur(int n) {
			ArrayList<Integer> chJoueur = new ArrayList<Integer>();
			
			for (int i=0;i<3;i++) {
				for (int j=0; j<3;j++) {
					if (EstChiffreJoueur(i, j, n)) {
						chJoueur.add(this._carreMagique[i][j].get_chiffre());
					}
				}
			}
			return chJoueur;
		}

	/**
	 * On indique dans le carré magique que le chiffre c est pris par le joueur j
	 * @param j
	 * @param c
	 */
		void ChiffrePris(int j, int c) {
			this._carreMagique[this._placeChiffres[c-1].get_lig()][this._placeChiffres[c-1].get_col()].set_proprietaire(j);
		}
		
		/**
		 * indique si le jeu est terminé. Un joueur gagne s'il est le premier à trouver trois chiffres dont la somme est 15. 
		 *  En d'autres termes, il gagne s'il est le premier à avoir pris tous les chiffres d'une ligne, d'une colonne ou
		 *   d'une diagonale du carré magique
		 * 
		 * @param v : contiendra les 3 chiffres dont la somme fait 15 s'il y a un gagnant
		 * @return entier indiquant les 4 cas possibles :
		 *  - 0 : il reste des chiffres disponibles
		 *  - 1 : le joueur 1 gagne
		 *  - 2 : l'ordinateur ou le joueur 2 gagne
		 *  - 3 : égalité, tous les chiffres sont pris mais aucun joueur n'a 3 chiffres dont la somme est 15.
		 *  
		 *  
		 */
		public int FinJeu(Vector<Integer> v) {
			
			for (int i=0; i<3;i++) {
				if ((!EstChiffreDispo(i,0)) && (this._carreMagique[i][0].get_proprietaire()==this._carreMagique[i][1].get_proprietaire())
						&& (this._carreMagique[i][1].get_proprietaire()==this._carreMagique[i][2].get_proprietaire())) {
				
					v.add(this._carreMagique[i][0].get_chiffre());
					v.add(this._carreMagique[i][1].get_chiffre());
					v.add(this._carreMagique[i][2].get_chiffre());
					return this._carreMagique[i][0].get_proprietaire();
				}
			}
			
			/*on cherche si un joueur a une colonne de chiffres */
			for (int i=0; i<3;i++) {
				if ((!EstChiffreDispo(0,i)) &&  (this._carreMagique[0][i].get_proprietaire()==this._carreMagique[1][i].get_proprietaire())
						&& (this._carreMagique[1][i].get_proprietaire()==this._carreMagique[2][i].get_proprietaire())) {
					
					v.add(this._carreMagique[0][i].get_chiffre());
					v.add(this._carreMagique[1][i].get_chiffre());
					v.add(this._carreMagique[2][i].get_chiffre());
					return this._carreMagique[0][i].get_proprietaire();
				}
			}
			
			/*on cherche si un joueur a une diagonale de chiffre */
			if ((!EstChiffreDispo(0,0)) &&  (this._carreMagique[0][0].get_proprietaire()==this._carreMagique[1][1].get_proprietaire())
					&& (this._carreMagique[1][1].get_proprietaire()==this._carreMagique[2][2].get_proprietaire())){
				
				v.add(this._carreMagique[0][0].get_chiffre());
				v.add(this._carreMagique[1][1].get_chiffre());
				v.add(this._carreMagique[2][2].get_chiffre());
				return this._carreMagique[0][0].get_proprietaire();	
			}
			
			if ((!EstChiffreDispo(0,2)) &&  (this._carreMagique[0][2].get_proprietaire()==this._carreMagique[1][1].get_proprietaire())
					&& (this._carreMagique[1][1].get_proprietaire()==this._carreMagique[2][0].get_proprietaire())){
				
				v.add(this._carreMagique[0][2].get_chiffre());
				v.add(this._carreMagique[1][1].get_chiffre());
				v.add(this._carreMagique[2][0].get_chiffre());
				return this._carreMagique[0][2].get_proprietaire();			
			}
			
			/*on teste si tous les chiffres sont pris mais que personne n'a gagné*/
			if (!ChiffresDispos())
				return 3;
			
			/*le jeu n'est pas terminé*/
			return 0;			
		}   

		/**
			retourne le chiffre choisi par l'ordinateur
		 */
		public int ChoixOrdinateur() {
			int nb;
			do
			{
				nb = this._alea.nextInt(9)+1;					
			} while (!EstChiffreDispo(nb));
			return nb;
		
		/* pour les scénarios
			this._nb++;	
			return this._aleaS3[this._nb-1];
			//return this._aleaS4[this._nb-1];
			// this._aleaS5[this._nb-1];
			//return this._aleaS6[this._nb-1];
		 */
		 
		}
		
		/**
			Affiche le carré magique
		 */
		void DebugCarreMagique() {
			for (int i=0;i<3;i++) {
				System.out.print("| "); 
				for(int j=0;j<3;j++) 
					System.out.print(this._carreMagique[i][j].get_chiffre()+"-"+this._carreMagique[i][j].get_proprietaire() + " ");
				System.out.println("|");
			}
		}

	}

		
		

